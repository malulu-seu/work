package whereExpression.operator;

public class LikeSQL implements SubSQL {

    private String leftObject;

    private String rightObject;

    public LikeSQL(String leftObject, String rightObject) {
        this.leftObject = leftObject;
        this.rightObject = rightObject;
    }

    @Override
    public String generateWhereSQL(boolean not) {
        String operator = not  ? "not like" : "like";

        return String.format("%s %s '%s'", leftObject, operator, rightObject);
    }
}
