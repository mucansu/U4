/**
 * Enum som representerar olika typer av överraskningar i spelet.
 * Överraskningar kan påverka spelaren positivt eller negativt på olika sätt.
 *
 * Typer:
 * - EXTRA_CREW: Ger spelaren en extra besättningsmedlem.
 * - EXTRA_TURN_PER_CREW: Ger spelaren extra turer baserat på antalet besättningsmedlemmar.
 * - RANDOM_DIG: Gräver automatiskt upp en slumpmässig ruta.
 * - FORCED_RANDOM_NEXT: Tvingar spelaren att gräva en slumpmässig ruta i nästa drag.
 *
 * @author Mustafa Cansu
 */
public enum SurpriseType {
    EXTRA_CREW("Extra Crew"),
    EXTRA_TURN_PER_CREW("Extra Turn Per Crew"),
    RANDOM_DIG("Random Dig"),
    FORCED_RANDOM_NEXT("Forced Random Next");

    private final String name;
    /**
     * Skapar en ny typ av överraskning med ett namn.
     *
     * @param name Namnet på överraskningen.
     */
    SurpriseType(String name) {
        this.name = name;
    }
    /**
     * Hämtar namnet på överraskningstypen.
     *
     * @return Namnet på överraskningstypen.
     */
    public String getName() { return name; }
}