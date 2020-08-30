package whereExpression.operator;

public class ComparisonSQL implements SubSQL {
    private String leftObject;
    private String rightObject;
    private String operator;

    public ComparisonSQL(String leftObject,String operator, String rightObject) {
        this.leftObject = leftObject;
        this.operator = operator;
        this.rightObject = rightObject;
    }



    @Override
    public String generateWhereSQL(boolean not) {

        return String.format("%s %s %s", leftObject, operator, rightObject);
    }
}
