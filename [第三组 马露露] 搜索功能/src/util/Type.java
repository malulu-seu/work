package util;

public enum Type {

    GameComputer("GameComputer"),
    LightweightComputer("LightweightComputer"),
    NormalComputer("NormalComputer");

    private final String type;
    private Type(String type) {
        this.type = type;
    }
}
