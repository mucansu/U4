public class Trap implements Diggable {
    private String type = "Trap";

    @Override
    public int getValue() {
        return -10; // Örnek ceza
    }

    @Override
    public String getType() {
        return type;
    }
}