import javax.swing.*;
import java.awt.*;
/**
 * Klass som hanterar det grafiska användargränssnittet för spelet.
 * Innehåller spelbrädet och kontrollpaneler för att visa spelarpoäng, besättningsstatus
 * och andra interaktiva element.
 *
 * @author Mustafa Cansu
 */
public class GameView extends JFrame {
    private JButton[][] buttons;
    private JLabel[] scoreLabels;
    private JLabel[] crewLabels;
    private JLabel turnLabel;

    private GameController controller;

    /**
     * Skapar ett nytt grafiskt fönster för spelet med ett visst brädstorlek.
     *
     * @param size Storleken på spelbrädet (antal rader och kolumner).
     */
    public GameView(int size) {
        setTitle("Treasure Hunt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        buttons = new JButton[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton("");
                int finalI = i;
                int finalJ = j;

                buttons[i][j].addActionListener(e -> controller.dig(finalI, finalJ));
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);



        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2));
        scoreLabels = new JLabel[2];
        crewLabels = new JLabel[2];

        JButton endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> controller.endGameManually());

        infoPanel.add(endGameButton);
        for (int i = 0; i < 2; i++) {
            scoreLabels[i] = new JLabel("Player " + (i + 1) + " Score: 0");
            crewLabels[i] = new JLabel("Player " + (i + 1) + " Crew: 3");
            infoPanel.add(scoreLabels[i]);
            infoPanel.add(crewLabels[i]);
        }

        turnLabel = new JLabel("Player 1's turn");
        infoPanel.add(turnLabel);

        add(infoPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
    public int chooseBoard() {
        Object[] options = {"Board 1", "Board 2"};
        return JOptionPane.showOptionDialog(this, "Choose Board", "New Game", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    /**
     * Kopplar GameController till vyn för att hantera användarens interaktioner.
     *
     * @param controller Kontrollklass som hanterar spelets logik.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void updateBoard(int row, int col, String text, Color cellColor) {
        buttons[row][col].setText(text);
        buttons[row][col].setEnabled(false);
        buttons[row][col].setBackground(cellColor);

    }

    public void updateScore(int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            scoreLabels[i].setText("Player " + (i + 1) + " Score: " + scores[i]);
        }
    }

    public void updateCrew(int[] crew) {
        for (int i = 0; i < crew.length; i++) {
            crewLabels[i].setText("Player " + (i + 1) + " Crew: " + crew[i]);
        }
    }

    public void updateTurn(int currentPlayer) {
        turnLabel.setText("Player " + (currentPlayer + 1) + "'s turn");
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    public String showTop10Message() {

        String name = JOptionPane.showInputDialog("Congrats, you made the top 10! Enter your name:");
        if (name == null || name.trim().isEmpty()) {
            name = "Anonymous";
        }
        return name.trim();
    }
    public void resetBoard() {
        for (int i = 0; i < controller.getBoard().length; i++) {
            for (int j = 0; j < controller.getBoard().length; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(null);

            }
        }
    }

}
