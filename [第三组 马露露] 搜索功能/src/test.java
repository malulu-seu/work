import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.*;

public class test {



    public static void main (String[] args) {
        Inventory inventory = new Inventory();

        //添加电脑
        Map properities = new HashMap();
        properities.put("Computer_type", ComputerType.PERSONAL_COMPUTER);
        properities.put("Brand", Brand.DELL);
        properities.put("Processor", Processor.AMD);
        properities.put("ScreenSize", ScreenSize.Below_Eleven);
        properities.put("Type", Type.GameComputer);
        ComputerSpec spec = new ComputerSpec(properities);

        Map properities_2 = new HashMap();
        properities.put("Computer_type", ComputerType.DESKTOP_COMPUTER);
        properities_2.put("Brand", Brand.HUAWEI);
        properities_2.put("Processor", Processor.InterCoreM);
        properities_2.put("ScreenSize", ScreenSize.Eleven_Point_Six);
        properities_2.put("Type", Type.NormalComputer);
        ComputerSpec spec_2 = new ComputerSpec(properities_2);


        inventory.addComputer("1", 1000.0, spec);
        inventory.addComputer("2", 1200.0, spec_2);

        System.out.println("---------------下列是仓库里所有的电脑：-------------");
        for(Computer e: inventory.getComputers())
            System.out.println(e.toString());


        System.out.println("\n-----------开始测试查询符合条件的电脑----------------：");

        HashMap<String,Object> searchproperties = new HashMap<>();
        searchproperties.put("ScreenSize",ScreenSize.Eleven_Point_Six);
        searchproperties.put("Processor", Processor.InterCoreM);
        ComputerSpec matcheProductSpec = new ComputerSpec(searchproperties);
        System.out.println("测试条件："+matcheProductSpec.toString());

        //测试match结果

        List<Computer> matchresult =  inventory.search(matcheProductSpec, 1000.00, 2000.00);
        if(matchresult.size()==0)
            System.out.println("当前库存里没有符合查询条件的电脑！");
        else{
            System.out.println("成功搜索到满足条件的电脑：");
            for(Computer e:matchresult)
                System.out.println(e.toString());
        }
    }

}
