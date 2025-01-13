import java.util.*;
import javax.swing.*;

// Abstract base class for diggable items
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

    // Abstract method to define digging logic
    public abstract void onDig(GameController controller, GameView view);
}
