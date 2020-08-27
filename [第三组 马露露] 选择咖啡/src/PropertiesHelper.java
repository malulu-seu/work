import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesHelper {


    private static PropertiesHelper _instance = null;
    private static Map<String, Double> coffeeType_price = new HashMap();
    private static Map<String, Double> size_price = new HashMap<>();
    private static Map<String, Double> Sauce_price = new HashMap();
    private static Map<String, Double> Syrup_price = new HashMap();
    private static Map<String, Double> milk_price = new HashMap();

    private static List<String> temperature = new ArrayList<>();


    private PropertiesHelper() {
        load();
    }

    private static void load() {
        //------不同种类咖啡的价格
        coffeeType_price.put("美式咖啡",30.0);
        coffeeType_price.put("拿铁",35.0);
        coffeeType_price.put("摩卡",40.0);
        coffeeType_price.put("卡布奇诺",40.0);
        coffeeType_price.put("焦糖玛奇朵",50.0);

        //-----中杯大杯超大杯价格的关系
        size_price.put("中杯", 1.0);
        size_price.put("大杯", 1.5);
        size_price.put("超大杯", 2.0);

        //-----多种温度
        temperature.add("特别热");
        temperature.add("热");
        temperature.add("微热");
        temperature.add("冰");
        temperature.add("少冰");
        temperature.add("去冰");

        //------不同糖浆的价格
        Syrup_price.put("原味糖浆",10.0);
        Syrup_price.put("香草糖浆",12.0);
        Syrup_price.put("榛果糖浆",15.0);
        Syrup_price.put("焦糖糖浆",20.0);
        Syrup_price.put("覆盆子糖浆 ",17.0);
        Syrup_price.put("摩卡酱 ",17.0);


        //------不同淋酱的价格
        Sauce_price.put("摩卡淋酱",10.0);
        Sauce_price.put("焦糖风味酱",15.0);

        //------不同牛奶的价格
        milk_price.put("全脂牛奶",10.0);
        milk_price.put("脱脂牛奶",15.0);
        milk_price.put("燕麦奶", 20.0);
        milk_price.put("豆奶", 10.0);

    }


    synchronized public static PropertiesHelper getInstance() {
        if (_instance == null) {
            _instance = new PropertiesHelper();
            load();
        }
        return _instance;
    }


    /**
     * 读取配置信息key - value
     */
    public double getCoffeePrice(String key) {
        return coffeeType_price.get(key);
    }

    public double getSaucePrice(String key) {
        return Sauce_price.get(key);
    }

    public double getSyrupPrice(String key) {
        return Syrup_price.get(key);
    }

    public double getSizePrice(String key) {
        return size_price.get(key);
    }

    public double getMilkPrice(String key) {
        return milk_price.get(key);
    }

    public Map<String, Double> getCoffeeType_price() {
        return coffeeType_price;
    }

    public Map<String, Double> getSize_price() {
        return size_price;
    }

    public Map<String, Double> getSauce_price() {
        return Sauce_price;
    }

    public Map<String, Double> getSyrup_price() {
        return Syrup_price;
    }

    public Map<String, Double> getMilk_price() {
        return milk_price;
    }

    public List<String> getTemperature() {
        return temperature;
    }



}
