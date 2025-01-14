public class Trap extends Diggable {
    private TrapType trapType;

    public Trap(TrapType trapType) {
        // trapType.getEffect() negatif sayı
        super(trapType.getName(), trapType.getEffect());
        this.trapType = trapType;
    }

    @Override
    public DigResult onDig() {
        // Tuzak kazılınca neler olsun?
        // Örneğin: -50 puan, 0 ekip değişimi, "Tuzak yakalandı" mesajı
        return new DigResult(
                trapType.getName(),       // name
                trapType.getEffect(),     // scoreChange (negatif değer)
                0,                        // crewChange
                "Triggered trap: " + trapType.getName()
        );
    }
}


