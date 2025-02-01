/**
 * Representerar en grupp av sammanhängande skatter som delas upp i flera delar.
 * Gruppens poäng tillfaller spelaren som gräver den sista delen av skatten.
 *
 * @author Mustafa Cansu
 */

public class TreasureGroup {
    private String name;
    private int totalValue;
    private int totalCells;
    private int dugCells;
    private boolean claimed;
    /**
     * Skapar en ny skattgrupp med ett namn, totalt värde och antal celler.
     *
     * @param name Namnet på skattgruppen.
     * @param totalValue Det totala poängvärdet för gruppen.
     * @param totalCells Antalet celler som gruppen består av.
     */
    public TreasureGroup(String name, int totalValue, int totalCells) {
        this.name = name;
        this.totalValue = totalValue;
        this.totalCells = totalCells;
        this.dugCells = 0;
        this.claimed = false;
    }

    public String getName() { return name; }
    public int getValue() { return totalValue; }

    /**
     * Markerar en del av skatten som grävd.
     *
     * @return Ett {@link DigResult} som indikerar om skatten är helt grävd och tilldelar poäng.
     */
    public DigResult digPart() {
        if (claimed) {

            return new DigResult(name, 0, 0,
                    "Already claimed treasure: " + name,
                    false, false);
        }
        dugCells++;
        if (dugCells < totalCells) {

            return new DigResult(name, 0, 0,
                    "You found part of " + name + "! (" + dugCells + "/" + totalCells + ")",
                    false, false);
        } else {
            // Son parçayı kazdın!
            claimed = true;
            return new DigResult(name, totalValue, 0,
                    "You have fully uncovered " + name + "! +" + totalValue + " points.",
                    false, false);
        }
    }
    public boolean isClaimed() {
        return claimed;
    }
}
