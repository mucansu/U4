import javax.swing.*;

// Main class to start the game
public class TreasureHunt {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView view = new GameView(10); // 10x10 grid
            GameController controller = new GameController(10, view);
            view.setController(controller);
        });
    }
}