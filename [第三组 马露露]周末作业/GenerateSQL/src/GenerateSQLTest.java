
import common.ComparisionOperator;
import org.junit.Test;
import whereExpression.connector.ANDConnector;
import whereExpression.connector.ORConnector;
import whereExpression.operator.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GenerateSQLTest {

    @Test
    public void testComparisionSQL(){
        assertEquals("select * from customers where companyName = HTSC",
                getComparisionSQL("companyName", ComparisionOperator.EQUAL, "HTSC"));

        assertEquals("select * from customers where companyName != HTSC",
                getComparisionSQL("companyName", ComparisionOperator.NOT_EQUAL, "HTSC"));

        assertEquals("select * from customers where age > 20",
                getComparisionSQL("age", ComparisionOperator.GREATER_THAN, "20"));

        assertEquals("select * from customers where age >= 20",
                getComparisionSQL("age", ComparisionOperator.GREATER_THAN_OR_EQUAL, "20"));

        assertEquals("select * from customers where age < 40",
                getComparisionSQL("age", ComparisionOperator.LESS_THAN, "40"));

        assertEquals("select * from customers where age <= 40",
                getComparisionSQL("age", ComparisionOperator.LESS_THAN_OR_EQUAL, "40"));
    }

    @Test
    public void testBetweenSQL(){
        assertEquals("select * from customers where age between 20 and 40",
                getBetweenSQL("age", "20", "40", false));

        assertEquals("select * from customers where age not between 20 and 40",
                getBetweenSQL("age", "20", "40", true));
    }

    @Test
    public void testInSQL(){
        assertEquals("select * from customers where age in (10, 20, 30, 40, 50)",
                getInSQL("age", false, "10", "20", "30", "40", "50"));

        assertEquals("select * from customers where age not in (10, 20, 30, 40, 50)",
                getInSQL("age", true, "10", "20", "30", "40", "50"));
    }

    @Test
    public void testLikeSQL(){
        assertEquals("select * from customers where postalCode like '80%'",
                getLikeSQL("postalCode", "80%", false));

        assertEquals("select * from customers where postalCode not like '80%'",
                getLikeSQL("postalCode", "80%", true));
    }

    @Test
    public void testNullSQL(){
        assertEquals("select * from customers where postalCode is null",
                getNullSQL("postalCode", false));

        assertEquals("select * from customers where postalCode is not null",
                getNullSQL("postalCode", true));
    }

    @Test
    public void testANDSQL(){
        Generator generator = new Generator();
        SubSQL s1 = new ComparisonSQL("companyName", ComparisionOperator.EQUAL, "HTSC");
        SubSQL s2 = new ComparisonSQL("age", ComparisionOperator.GREATER_THAN, "20");
        assertEquals("select * from customers where (companyName = HTSC) and (age > 20)",
                getConnector(generator, new String[]{"and"}, new SubSQL[]{s1,s2}, new boolean[]{false, false}));

    }

    @Test
    public void testORSQL(){
        Generator generator = new Generator();
        SubSQL s1 = new ComparisonSQL("companyName", ComparisionOperator.EQUAL, "HTSC");
        SubSQL s2 = new ComparisonSQL("age", ComparisionOperator.GREATER_THAN, "20");
        assertEquals("select * from customers where (companyName = HTSC) or (age > 20)",
                getConnector(generator, new String[]{"or"}, new SubSQL[]{s1,s2}, new boolean[]{false, false}));
    }

    @Test
    public void testANDORSQL() {
        Generator generator = new Generator();
        SubSQL s1 = new ComparisonSQL("companyName", ComparisionOperator.EQUAL, "HTSC");
        SubSQL s2 = new ComparisonSQL("age", ComparisionOperator.GREATER_THAN, "20");
        SubSQL s3 = new ComparisonSQL("age", ComparisionOperator.LESS_THAN, "40");
        assertEquals("select * from customers where (companyName = HTSC) and (age > 20) or (age < 40)",
                getConnector(generator, new String[]{"and","or"}, new SubSQL[]{s1,s2,s3}, new boolean[]{false, false, false}));
    }



    private String getComparisionSQL(String column_name, String operator, String value) {
        Generator generator = new Generator();
        SubSQL subSQL = new ComparisonSQL(column_name, operator, value);
        generator.putSubSQL(subSQL);
        generator.putNot(false);
        return generator.generateSQL();
    }

    private String getBetweenSQL(String column_name, String value1, String value2, boolean not) {
        Generator generator = new Generator();
        SubSQL subSQL = new BetweenSQL(column_name, value1, value2);
        generator.putSubSQL(subSQL);
        generator.putNot(not);
        return generator.generateSQL();
    }

    private String getInSQL(String column_name, boolean not, String... inList) {
        Generator generator = new Generator();
        List<String> list = new ArrayList<>();
        for (String i : inList){
            list.add(i);
        }
        SubSQL subSQL = new InSQL(column_name, list);
        generator.putSubSQL(subSQL);
        generator.putNot(not);
        return generator.generateSQL();
    }

    private String getLikeSQL(String column_name, String value,boolean not) {
        Generator generator = new Generator();
        SubSQL subSQL = new LikeSQL(column_name, value);
        generator.putSubSQL(subSQL);
        generator.putNot(not);
        return generator.generateSQL();
    }

    private String getNullSQL(String column_name, boolean not) {
        Generator generator = new Generator();
        SubSQL subSQL = new NullSQL(column_name);
        generator.putSubSQL(subSQL);
        generator.putNot(not);
        return generator.generateSQL();
    }

    private String getConnector(Generator generator, String[] connector, SubSQL[] subSQLs, boolean[] not) {
        for (int i = 0;i < subSQLs.length-1; i++) {
            generator.putSubSQL(subSQLs[i]);
            generator.putNot(not[i]);
            if (connector[i].equals("and")) {
                generator.putConnector(new ANDConnector());
            } else {
                generator.putConnector(new ORConnector());
            }
        }
        generator.putSubSQL(subSQLs[subSQLs.length-1]);
        generator.putNot(not[not.length-1]);
        return generator.generateSQL();
    }



}

