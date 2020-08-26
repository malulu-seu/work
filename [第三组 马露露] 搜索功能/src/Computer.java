public class Computer {
    private String serialNumber;
    private double price;
    private ComputerSpec spec;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ComputerSpec getSpec() {
        return spec;
    }

    public void setSpec(ComputerSpec spec) {
        this.spec = spec;
    }

    public Computer(String serialNumber, double price, ComputerSpec spec) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "serialNumber='" + serialNumber + '\'' +
                ", price=" + price +
                ", ComputerSpec =" + spec +
                '}';
    }



}
