public class Treasure extends Diggable {
    private TreasureType treasureType;

    public Treasure(TreasureType treasureType) {
        super(treasureType.getName(), treasureType.getValue());
        this.treasureType = treasureType;
    }

    @Override
    public DigResult onDig() {
        // Define kazılınca neler olsun?
        // Örneğin: +50 puan, 0 ekip değişimi, "Define bulundu" mesajı
        return new DigResult(
                treasureType.getName(),
                treasureType.getValue(),
                0,
                "You found a " + treasureType.getName() + "!"
        );
    }
}