package whereExpression.connector;

public class ORConnector implements Connector {

    @Override
    public String getSql() {
        return " or ";
    }

}
