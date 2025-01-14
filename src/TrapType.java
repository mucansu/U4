/**
 * Enum som representerar olika typer av fällor i spelet.
 * Varje typ har ett namn och en effekt som kan vara en fast poängförlust,
 * procentförlust till motståndaren eller besättningsförlust.
 *
 * @author Mustafa
 */
public enum TrapType {
    FIXED_SCORE_LOSS("Fixed Score Loss", 100),
    PERCENT_TO_OPPONENT("Percent Score To Opponent", 20),
    CREW_LOSS("Crew Loss", 1);

    private final String name;
    private final int effectValue;
    /**
     * Skapar en ny fälltyp med ett namn och en effekt.
     *
     * @param name Namnet på fällan.
     * @param effectValue Effektens värde (t.ex. poängförlust).
     */
    TrapType(String name, int effectValue) {
        this.name = name;
        this.effectValue = effectValue;
    }

    public String getName() { return name; }
    public int getEffectValue() { return effectValue; }
}