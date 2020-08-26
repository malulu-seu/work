import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    private List<Computer> computers = new ArrayList<>();


    public void addComputer(String serialNum, double price, ComputerSpec spec) {
        Computer computer = new Computer(serialNum, price, spec);
        computers.add(computer);
    }

    //SRP原则
    public List<Computer> search(ComputerSpec spec, Double minPrice, Double maxPrice) {
        List<Computer> result = new ArrayList<>();
        Map<String, Object> properites = spec.getProperites();

        for (int i = 0;i < computers.size(); i++) {
            if (computers.get(i).getSpec().matches(spec) ) {
                if ((minPrice == null && maxPrice == null) || (comparePrice(computers.get(i), minPrice, maxPrice))) {
                    result.add(computers.get(i));
                }
            }
        }
        return result;
    }


    public boolean comparePrice(Computer computer, double minPrice, double maxPrice) {
        if (computer.getPrice() >= minPrice && computer.getPrice() <= maxPrice)
            return true;
        return false;
    }
}
