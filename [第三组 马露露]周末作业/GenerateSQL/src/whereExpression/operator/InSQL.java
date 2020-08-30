package whereExpression.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class InSQL implements SubSQL {

    private String leftObject;
    private List<String> inList = new ArrayList<>();

    public InSQL(String leftObject, Iterable<String> inList) {
        this.leftObject = leftObject;

        for (String value : inList) {
            this.inList.add(value);
        }
    }

    @Override
    public String generateWhereSQL(boolean not) {
        String sql = String.format("%s in (", leftObject);

        StringJoiner stringJoiner = new StringJoiner(", ");

        for (String value : inList) {
            stringJoiner.add(value);
        }

        String operator = not  ? "not in" : "in";

        return String.format("%s %s (%s)", leftObject, operator, stringJoiner.toString());
    }
}
