package whereExpression.operator;

public class NullSQL implements SubSQL {

    private String leftObject;

    public NullSQL(String leftObject) {
        this.leftObject = leftObject;
    }

    @Override
    public String generateWhereSQL(boolean not) {
        String operator = not  ? "is not null" : "is null";
        return String.format("%s %s", leftObject, operator);
    }
}
