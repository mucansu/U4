/**
 * Klass som representerar resultatet av en grävning i spelet.
 * Innehåller information om poängförändring, besättningsförändring och eventuella effekter.
 *
 * @author Mustafa Cansu
 */
public class DigResult {
    private final String name;
    private final int scoreChange;
    private final int crewChange;
    private final String message;


    private final boolean extraTurn;
    private final boolean forcedRandomNext;
    /**
     * Konstruktor för att skapa ett grävningsresultat.
     *
     * @param name Namn på objektet som grävdes upp.
     * @param scoreChange Poängförändring (+/-).
     * @param crewChange Förändring i besättningsmedlemmar (+/-).
     * @param message Meddelande att visa spelaren.
     * @param extraTurn Om spelaren får en extra tur.
     * @param forcedRandomNext Om nästa drag blir slumpmässigt.
     */
    public DigResult(
            String name,
            int scoreChange,
            int crewChange,
            String message,
            boolean extraTurn,
            boolean forcedRandomNext
    ) {
        this.name = name;
        this.scoreChange = scoreChange;
        this.crewChange = crewChange;
        this.message = message;
        this.extraTurn = extraTurn;
        this.forcedRandomNext = forcedRandomNext;
    }

    /**
     * Hämtar objektets namn.
     *
     * @return Objektets namn.
     */
    public String getName() { return name; }
    /**
     * Hämtar poängförändringen.
     *
     * @return Poängförändringen.
     */
    public int getScoreChange() { return scoreChange; }
    /**
     * Hämtar besättningsförändringen.
     *
     * @return Besättningsförändringen.
     */
    public int getCrewChange() { return crewChange; }
    /**
     * Hämtar meddelandet att visa spelaren.
     *
     * @return Meddelandet.
     */
    public String getMessage() { return message; }
    /**
     * Kontrollerar om spelaren får en extra tur.
     *
     * @return True om spelaren får en extra tur, annars false.
     */
    public boolean isExtraTurn() { return extraTurn; }
    /**
     * Kontrollerar om nästa drag är slumpmässigt.
     *
     * @return True om nästa drag är slumpmässigt, annars false.
     */
    public boolean isForcedRandomNext() { return forcedRandomNext; }
}
