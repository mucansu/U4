public abstract class Diggable {
    protected String type; // Örneğin "Spiked Pit" veya "Gold Box"
    protected int value;   // Kazma durumunda etki edebilecek puan/etki değeri

    public Diggable(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    /**
     * Artık GameController nesnesi almıyor,
     * sadece ne etkisi olacağını anlatan bir DigResult döndürüyor.
     */
    public abstract DigResult onDig();
}
