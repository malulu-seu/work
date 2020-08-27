import java.util.Map;

public class CoffeeFactory {

    /**
     * 得到定制化的Coffee
     */



    //只选了必选的选项
    public Coffee getCoffee(String coffeeType, String cupSize, String temperature, String milk) {
        return new NecessaryCoffee(coffeeType, cupSize, temperature, milk);
    }

    //加了糖浆
    public Coffee getSyrupCoffee(String coffeeType, String cupSize, String temperature, String milk,
                                         Map<String, Integer> syrupNum) {
        return new SyrupCoffee(new NecessaryCoffee(coffeeType, cupSize, temperature, milk), syrupNum);
    }

    //加了淋酱
    public Coffee getSauceCoffee(String coffeeType, String cupSize, String temperature, String milk,
                                 Map<String, Integer> sauceNum) {
        return new SauceCoffee(new NecessaryCoffee(coffeeType, cupSize, temperature, milk), sauceNum);
    }

    //加了糖浆和淋酱
    public Coffee getSyrupAndSauceCoffee(String coffeeType, String cupSize, String temperature, String milk,
                                         Map<String, Integer> syrupNum, Map<String, Integer> sauceNum) {
        return new SauceCoffee(new SyrupCoffee(new NecessaryCoffee(coffeeType, cupSize, temperature, milk), syrupNum), sauceNum);
    }
}
