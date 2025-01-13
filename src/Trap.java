// Trap class
public class Trap extends Diggable {
    private int trapType;

    public Trap(int trapType) {
        super("Trap", -20);
        this.trapType = trapType;
    }

    @Override
    public void onDig(GameController controller, GameView view) {
        switch (trapType) {
            case 1: // Player loses fixed points
                controller.addScore(-100);
                view.displayMessage("You fell into a trap! -100 points!");
                break;
            case 2: // Opponent gains a percentage of current player's points
                int lostPoints = controller.getPlayerScore() / 10; // 10% of current score
                controller.addScore(-lostPoints);
                controller.addOpponentScore(lostPoints);
                view.displayMessage("Trap activated! Opponent gains " + lostPoints + " points!");
                break;
            case 3: // Player loses a crew member
                controller.reduceCrew();
                view.displayMessage("You lost a crew member due to a trap!");
                break;
        }
        //view.updateBoard(controller.getCurrentRow(), controller.getCurrentCol(), "Trap");
        view.updateBoard(controller.getCurrentRow(), controller.getCurrentCol(), "Bum!");

    }
}
