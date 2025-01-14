public class TreasureGroup {
    private String name;        // Örneğin "T-form treasure"
    private int totalValue;     // Tüm define bitince verilecek puan
    private int totalCells;     // Kaç hücre var
    private int dugCells;       // Kaç hücre kazıldı
    private boolean claimed;    // Daha önce bir oyuncu bu define puanını aldı mı?

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
     * Her bir hücre kazıldığında çağrılır.
     * Son hücre kazıldıysa DigResult ile puanı "son kazan oyuncu" alacak şekilde iletir.
     * Daha önce claim edilmediyse puan verilir.
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
