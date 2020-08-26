package util;
public enum ComputerType {
    PERSONAL_COMPUTER, DESKTOP_COMPUTER, SERVER_COMPUTER;

    public String toString() {
        switch (this) {
            case PERSONAL_COMPUTER: return "PERSONAL_COMPUTER";
            case SERVER_COMPUTER: return "SERVER_COMPUTER";
            case DESKTOP_COMPUTER: return "DESKTOP_COMPUTER";
            default: return "Unspecified";
        }
    }
}
