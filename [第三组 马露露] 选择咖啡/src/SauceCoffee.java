import java.util.HashMap;
import java.util.Map;

public class SauceCoffee extends Coffee {

    Map<String, Integer> sauceNum = new HashMap<>();

    Coffee coffee;

    PropertiesHelper properties;

    public SauceCoffee (Coffee coffee, Map sauceNum) {
        this.coffee = coffee;
        this.sauceNum = sauceNum;
        properties = PropertiesHelper.getInstance();
    }

    @Override
    public double computePrice() {

        double basePrice = coffee.computePrice();

        double saucePrice = 0;

        for (String sauceType : sauceNum.keySet()) {
            saucePrice = saucePrice + properties.getSaucePrice(sauceType) * sauceNum.get(sauceType);
        }

        return basePrice + saucePrice;
    }
}
