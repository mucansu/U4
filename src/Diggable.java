import java.awt.*;

/**
 * Abstrakt klass som representerar ett grävbart objekt i spelet.
 * Varje grävbart objekt kan vara en skatt, fälla eller överraskning.
 *
 * @author Mustafa Cansu
 */
public abstract class Diggable {
    protected String type;
    protected int value;
    protected Color color;
    /**
     * Konstruktor för att skapa ett grävbart objekt.
     *
     * @param type Typ av objektet (ex: Skatt, Fälla).
     * @param value Värde kopplat till objektet.
     */
    public Diggable(String type, int value, Color color) {
        this.type = type;
        this.value = value;
        this.color = color != null ? color : Color.GRAY;
    }
    /**
     * Hämtar typen av färg.
     *
     * @return Typen av färg.
     */
    public Color getColor() {
        return color;
    }
    /**
     * settar typen av färg.
     *
     */

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Hämtar typen av objektet.
     *
     * @return Typen av objektet.
     */
    public String getType() {
        return type;
    }
    /**
     * Hämtar värdet av objektet.
     *
     * @return Värdet av objektet.
     */
    public int getValue() {
        return value;
    }

    /**
     * Abstrakt metod som definierar vad som händer när objektet grävs.
     *
     * @return Resultatet av grävningen som ett {@link DigResult}.
     */
    public abstract DigResult onDig();

}
