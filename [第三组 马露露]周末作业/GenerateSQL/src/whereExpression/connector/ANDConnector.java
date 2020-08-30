package whereExpression.connector;

public class ANDConnector implements Connector {

    @Override
    public String getSql() {
        return " and ";
    }
}
