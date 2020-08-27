import java.util.Map;

public class NecessaryCoffee extends Coffee{
    private String coffeeType;
    private String cupSize;
    private String temperature;
    private String milk;
    PropertiesHelper properties;

    private Map<String, Double> coffeeType_price;

    public NecessaryCoffee(String coffeeType, String cupSize, String temperature, String milk) {
        this.coffeeType = coffeeType;
        this.cupSize = cupSize;
        this.temperature = temperature;
        this.milk = milk;
        properties = PropertiesHelper.getInstance();
    }

    @Override
    public double computePrice() {

        double base_price = properties.getCoffeePrice(coffeeType) * properties.getSizePrice(cupSize);

        double Add_milk_price = base_price + properties.getMilkPrice(milk);

        return Add_milk_price;
    }
}
