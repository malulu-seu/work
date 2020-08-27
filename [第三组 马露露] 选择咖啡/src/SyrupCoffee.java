import java.util.HashMap;
import java.util.Map;

public class SyrupCoffee extends Coffee {

    Map<String, Integer> syrupNum = new HashMap<>();

    Coffee coffee;

    PropertiesHelper properties;

    public SyrupCoffee (Coffee coffee, Map syrupNum) {
        this.coffee = coffee;
        this.syrupNum = syrupNum;
        properties = PropertiesHelper.getInstance();
    }


    @Override
    public double computePrice() {
        double basePrice = coffee.computePrice();

        double syrupPrice = 0;

        for (String syrupType : syrupNum.keySet()) {
            syrupPrice = syrupPrice + properties.getSyrupPrice(syrupType) * syrupNum.get(syrupType);
        }

        return basePrice + syrupPrice;

    }
}
