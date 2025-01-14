public abstract class Diggable {
    protected String type;
    protected int value;

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
     * Model katmanı, kazma sonucunda neler olduğunu DigResult ile döndürür.
     */
    public abstract DigResult onDig();
}
