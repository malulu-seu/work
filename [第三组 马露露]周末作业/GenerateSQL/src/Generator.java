import whereExpression.connector.Connector;
import whereExpression.operator.SubSQL;

import java.util.*;

public class Generator {


    private List<Connector> connectors = new ArrayList<>();//连接符
    private List<SubSQL> subSQLs = new ArrayList<>();//where子句
    private List<Boolean> operatorNOT = new ArrayList<>();//操作符是否加上not


    public void putConnector(Connector connector) {
        connectors.add(connector);
    }

    public void putSubSQL(SubSQL subSQL) {
        subSQLs.add(subSQL);
    }

    public void putNot(boolean not) { operatorNOT.add(not); }

    public String generateSQL() {
        String sql = "select * from customers where ";

        if(connectors.size() == 0) {
            sql = sql + subSQLs.get(0).generateWhereSQL(operatorNOT.get(0));
            return sql;
        }

        for (int i = 0;i<connectors.size();i++) {
            sql = sql + "("+subSQLs.get(i).generateWhereSQL(operatorNOT.get(i))+")";

            sql = sql + connectors.get(i).getSql();
        }
        sql = sql + "(" + subSQLs.get(subSQLs.size()-1).generateWhereSQL(operatorNOT.get(subSQLs.size()-1))+")";
        return sql;
    }
}
