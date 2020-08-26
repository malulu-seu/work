package util;

public enum Processor {

    InterCoreM("Intel CoreM"),
    AMD("AMD系列"),
    Intel_i9_lower("Intel i9低功能耗版"),
    Intel_i9_Normal("Intel i9标准电压版"),
    Intel_i7_Normal("Intel i7标准电压版");



    private final String type;
    private Processor(String type) {
        this.type = type;
    }


}
