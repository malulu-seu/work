package util;

public enum ScreenSize {

    Below_Eleven("11英寸及以下"),
    Eleven_Point_Six("11.6英寸"),
    Fourteen_Point_One("14.1英寸"),
    Fifteen("15.0英寸");

    private final String size;
    private ScreenSize (String size) {
        this.size = size;
    }


}
