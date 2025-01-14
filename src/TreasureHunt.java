import javax.swing.SwingUtilities;
/**
 * Huvudklass för att starta spelet Treasure Hunt.
 * Initialiserar spelet med en spelplan (10x10) och kopplar samman vy och kontroll.
 *
 * Använder sig av SwingUtilities för att säkerställa att GUI-element körs på rätt tråd.
 *
 * @author Mustafa
 */
public class TreasureHunt {
    /**
     * Programstart för Treasure Hunt-spelet.
     *
     * Skapar och visar spelvyn samt kopplar kontrollklassen till vyn.
     *
     * @param args Kommandoradsargument (används inte).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView view = new GameView(10);  // 10x10
            GameController controller = new GameController(10, view);
            view.setController(controller);
        });
    }
}
