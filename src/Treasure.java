public class Treasure implements Diggable {
    private int value;
    private String type = "Treasure";

    public Treasure(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getType() {
        return type;
    }
}