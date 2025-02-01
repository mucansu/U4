import java.awt.*;

/**
 * Representerar en fälla i spelet som kan påverka spelaren negativt vid grävning.
 * Varje fälla har en typ och en specifik effekt, till exempel poängförlust eller besättningsförlust.
 *
 * @author Mustafa Cansu
 */
public class Trap extends Diggable {
    private TrapType trapType;
    /**
     * Skapar en ny fälla av en specifik typ.
     *
     * @param trapType Typen av fällan.
     */
    public Trap(TrapType trapType) {
        super(trapType.getName(), trapType.getEffectValue(), Color.RED);
        this.trapType = trapType;
    }
    /**
     * Definierar vad som händer när fällan grävs.
     *
     * @return Ett {@link DigResult} som beskriver effekten av fällan.
     */
    @Override
    public DigResult onDig() {

        switch (trapType) {
            case FIXED_SCORE_LOSS:

                return new DigResult(
                        trapType.getName(),
                        -trapType.getEffectValue(), // -100
                        0,
                        "You triggered a trap: " + trapType.getName() + " (lose " + trapType.getEffectValue() + " points).",
                        false,
                        false
                );
            case PERCENT_TO_OPPONENT:

                return new DigResult(
                        trapType.getName(),
                        0, // puanı buradan düşmemek, Controller handle edebilir
                        0,
                        "Trap: " +  trapType.getName() + ", gave " + trapType.getEffectValue() +" procent of your points to opponent!",
                        false,
                        false
                );
            case CREW_LOSS:

                return new DigResult(
                        trapType.getName(),
                        0,
                        -1,
                        "Trap: " + trapType.getName() + "! You lost a crew member.",
                        false,
                        false
                );
            default:
                return new DigResult(
                        "UnknownTrap", 0, 0, "No effect", false, false
                );
        }
    }
}


