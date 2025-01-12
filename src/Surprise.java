import java.util.Random;

public class Surprise implements Diggable {
    private String type = "Surprise";
    private int surpriseType;
    private Random random = new Random();

    public Surprise() {
        this.surpriseType = random.nextInt(4) + 1; // 1-4 arası rastgele sürpriz tipi
    }

    public int getSurpriseType() {
        return surpriseType;
    }

    @Override
    public int getValue() {
        return 0; // Sürprizlerin doğrudan puan değeri yok
    }

    @Override
    public String getType() {
        return type;
    }
}