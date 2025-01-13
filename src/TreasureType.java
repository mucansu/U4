

public enum TreasureType {
    WOODENBOX(10, "Wooden Box"),
    BRONZBOX(20, "Bronz Box"),
    SILVERBOX(50, "Silver Box"),
    GOLDBOX(75, "Gold Box"),
    DIAMONDBOX(100, "Diamond Box");
    private final String name;
    private final int value;

    TreasureType(int value,String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName(){
        return name;
    }
}
