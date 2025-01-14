/**
 * Representerar en grupp av sammanhängande skatter som delas upp i flera delar.
 * Gruppens poäng tillfaller spelaren som gräver den sista delen av skatten.
 *
 * @author Mustafa
 */

public class TreasureGroup {
    private String name;        // Örneğin "T-form treasure"
    private int totalValue;     // Tüm define bitince verilecek puan
    private int totalCells;     // Kaç hücre var
    private int dugCells;       // Kaç hücre kazıldı
    private boolean claimed;    // Daha önce bir oyuncu bu define puanını aldı mı?
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
            // Zaten puan birine gitti, bu demektir ki define tam kazılmış
            // ama tekrardan kazılıyor gibi bir durum normalde olmaz.
            return new DigResult(name, 0, 0,
                    "Already claimed treasure: " + name,
                    false, false);
        }
        dugCells++;
        if (dugCells < totalCells) {
            // Henüz define tamamlanmadı, 0 puan
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
