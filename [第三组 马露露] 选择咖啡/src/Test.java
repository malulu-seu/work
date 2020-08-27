import java.util.*;

public class Test {

    public static void main (String[] args) {

        PropertiesHelper properties = PropertiesHelper.getInstance();

        System.out.println("我们提供的咖啡种类有：");

        Map<String, Double> coffeeType_price = properties.getCoffeeType_price();
        Map<Integer, String> coffee_num = new HashMap<>();
        int num = 1;
        for (String coffeeType : coffeeType_price.keySet()) {
            System.out.println("     "+coffeeType+"("+num+")"+"-------------"+coffeeType_price.get(coffeeType)+"元");
            coffee_num.put(num, coffeeType);
            num = num + 1;
        }


        System.out.println("请输入您需要的咖啡编号：");

        Scanner scan = new Scanner(System.in);

        String coffeeType = coffee_num.get(Integer.parseInt(scan.next()));

        System.out.println("根据您选择的咖啡种类，我们提供的各个杯型咖啡的价格如下：");

        Map<String, Double> size_price = properties.getSize_price();

        Map<Integer, String> cup_num = new HashMap<>();
        num = 1;
        for (String cupSize : size_price.keySet()) {
            double price = coffeeType_price.get(coffeeType) * size_price.get(cupSize);
            System.out.println("     "+cupSize + "("+num+")"+"------------- "+price+"元") ;
            cup_num.put(num, cupSize);
            num = num + 1;
        }

        System.out.println("请输入您需要的杯型编号：");

        String cupSize = cup_num.get(Integer.parseInt(scan.next()));


        System.out.println("我们提供的咖啡温度有：");

        List<String> temperature = properties.getTemperature();
        Map<Integer, String> temp_num = new HashMap<>();

        for (int i = 0;i<temperature.size();i++) {
            temp_num.put( i,temperature.get(i));
            System.out.print(temperature.get(i)+"("+i+")"+",");
        }

        System.out.println();

        System.out.println("请输入您需要的温度编号：");

        String temp = temp_num.get(Integer.parseInt(scan.next()));



        System.out.println("我们提供的牛奶种类有：");
        Map<String, Double> milk_price = properties.getMilk_price();
        Map<Integer, String> milk_num = new HashMap<>();
        num = 1;
        for (String milk : milk_price.keySet()) {
            System.out.println("     "+milk+"("+num+")"+"-------------"+milk_price.get(milk)+"元");
            milk_num.put(num, milk);
            num = num + 1;
        }

        System.out.println("请输入您需要添加的牛奶编号：");

        String milk = milk_num.get(Integer.parseInt(scan.next()));

        System.out.println("我们提供的风味糖浆有：");

        Map<String, Double> Syrup_price = properties.getSyrup_price();
        Map<Integer, String> syrup_num = new HashMap<>();
        num = 1;
        for (String syrup : Syrup_price.keySet()) {
            System.out.println("     "+syrup + "("+num+")"+"------------- "+Syrup_price.get(syrup)+"元") ;
            syrup_num.put(num, syrup);
            num = num + 1;
        }
        System.out.println("     "+"不需要"+ "("+num+")"+"------------- ");
        syrup_num.put(num,"不需要");

        System.out.println("请依次输入您需要添加的糖浆编号：（以空格分隔多种糖浆）");


        List<String> syrups = new ArrayList<>();//选择的糖浆名称列表
        scan.nextLine();
        String line = scan.nextLine();
        for (int i=0;i<line.split(" ").length;i++){
            String s = line.split(" ")[i];
            syrups.add(syrup_num.get(Integer.parseInt(s)));
        }


        System.out.println("请依次输入您需要添加的糖浆份数：（以空格分隔多种糖浆）");
        line = scan.nextLine();
        List<Integer> syrups_num = new ArrayList<>();//选择的糖浆份数列表
        for (int i=0;i<line.split(" ").length;i++){
            syrups_num.add(Integer.parseInt(line.split(" ")[i]));
        }



        Map<String, Integer> syrupNum = new HashMap<>();
        for (int i=0; i< syrups.size();i++){
            syrupNum.put(syrups.get(i),syrups_num.get(i));
        }


        System.out.println("我们提供的淋酱有：");
        Map<String, Double> Sauce_price = properties.getSauce_price();
        Map<Integer, String> sauce_num = new HashMap<>();
        num = 1;
        for (String sauce : Sauce_price.keySet()) {
            System.out.println("     "+sauce + "("+num+")"+"------------- "+Sauce_price.get(sauce)+"元") ;
            sauce_num.put(num, sauce);
            num = num + 1;
        }
        System.out.println("     "+"不需要"+ "("+num+")"+"------------- ");
        sauce_num.put(num,"不需要");

        System.out.println("请依次输入您需要添加的淋酱编号：（以空格分隔多种淋酱）");

        List<String> sauces = new ArrayList<>();//选择的淋酱名称列表
        line = scan.nextLine();
        for (int i=0;i<line.split(" ").length;i++){
            String s = line.split(" ")[i];
            sauces.add(sauce_num.get(Integer.parseInt(s)));
        }
        Map<String, Integer> sauceNum = new HashMap<>();
        for (int i=0; i< sauces.size();i++){
            sauceNum.put(sauces.get(i),1);
        }

        Coffee coffee;
        if(syrups.size() == 1 && syrups.get(0).equals("不需要")) {
            if(sauces.size() == 1 && sauces.get(0).equals("不需要")){
                coffee = new CoffeeFactory().getCoffee(coffeeType, cupSize, temp, milk);
            } else {
                coffee = new CoffeeFactory().getSauceCoffee(coffeeType, cupSize, temp, milk, sauceNum);
            }
        } else {
            if(sauces.size() == 1 && sauces.get(0).equals("不需要")){
                coffee = new CoffeeFactory().getSyrupCoffee(coffeeType, cupSize, temp,milk,syrupNum);
            } else {
                coffee = new CoffeeFactory().getSyrupAndSauceCoffee(coffeeType,cupSize,temp,milk,syrupNum,sauceNum);
            }
        }

        System.out.println("\n您需要支付的价格是："+ coffee.computePrice());







    }
}
