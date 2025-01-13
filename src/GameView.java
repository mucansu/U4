import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;
// GameView class
public class GameView extends JFrame {
    private JButton[][] buttons;
    private JLabel[] scoreLabels;
    private JLabel[] crewLabels;
    private JLabel turnLabel;
    private GameController controller;

    public GameView(int size) {
        setTitle("Treasure Hunt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize board panel
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

        // Initialize info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2));
        scoreLabels = new JLabel[2];
        crewLabels = new JLabel[2];
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

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void updateBoard(int row, int col, String type) {
        buttons[row][col].setText(type);
        buttons[row][col].setEnabled(false);
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
}