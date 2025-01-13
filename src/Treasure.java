// Treasure class

// Treasure class
public class Treasure extends Diggable {
    private TreasureType treasureType;
    public Treasure(int value, TreasureType treasureType) {
        super("Treasure", value);
        this.treasureType = treasureType;
    }
    public Treasure(TreasureType treasureType) {
        super(treasureType.getName(), treasureType.getValue());
        this.treasureType = treasureType;
    }

    @Override
    public void onDig(GameController controller, GameView view) {
        controller.addScore(value);
        //view.updateBoard(controller.getCurrentRow(), controller.getCurrentCol(), "Treasure");
        view.updateBoard(controller.getCurrentRow(), controller.getCurrentCol(), "Golden Chest");

        //view.displayMessage("You found a treasure! +" + value + " points.");
    }

    public TreasureType getTreasureType() {
        return treasureType;
    }
}

