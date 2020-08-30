package whereExpression.operator;

public class BetweenSQL implements SubSQL {
    private String leftObject;
    private String rightValue1;
    private String rightValue2;

    public BetweenSQL(String leftObject, String rightValue1, String rightValue2) {
        this.leftObject = leftObject;
        this.rightValue1 = rightValue1;
        this.rightValue2 = rightValue2;
    }

    @Override
    public String generateWhereSQL(boolean not) {

        String operator = not  ? "not between" : "between";

        return String.format("%s %s %s and %s", leftObject, operator, rightValue1, rightValue2);
    }
}
