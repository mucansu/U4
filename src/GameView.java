import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

class GameView extends JFrame {
    private JButton[][] buttons;
    private JLabel[] scoreLabels;
    private JLabel[] crewLabels;
    private JLabel turnLabel;
    private GameController controller;
    private String playerName;
    private int extraTurns = 0;
    private boolean randomTurn = false;

    private boolean isGameEnded = false;
    private int randomRow;
    private int randomCol;
    private Random random = new Random();
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

        JPanel infoPanel = new JPanel(new GridLayout(3, 2));
        scoreLabels = new JLabel[2];
        crewLabels = new JLabel[2];
        for (int i = 0; i < 2; i++) {
            scoreLabels[i] = new JLabel("Player " + (i + 1) + " Score: 0");
            crewLabels[i] = new JLabel("Player " + (i+1) + " Crew: 3");
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

    public boolean isExtraTurnsActive(){
        return extraTurns > 0;
    }
    public void decreaseExtraTurns(){
        extraTurns--;
    }

    public void updateBoard(int row, int col, String type) {
        buttons[row][col].setText(type);
    }

    public void updateScore(int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            scoreLabels[i].setText("Player " + (i + 1) + " Score: " + scores[i]);
        }
    }

    public void updateCrew(int[] crew){
        for (int i = 0; i < crew.length; i++) {
            crewLabels[i].setText("Player " + (i+1) + " Crew: " + crew[i]);
        }
    }

    public void updateTurn(int currentPlayer){
        turnLabel.setText("Player " + (currentPlayer + 1) + "'s turn");
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void setExtraTurns(int extraTurns){
        this.extraTurns = extraTurns;
    }

    public void setRandomTurn(){
        this.randomTurn = true;
        do {
            randomRow = random.nextInt(buttons.length);
            randomCol = random.nextInt(buttons[0].length);
        } while (buttons[randomRow][randomCol].getText() != "");
        controller.dig(randomRow,randomCol);
    }
    public boolean isRandomTurnActive(){
        return randomTurn;
    }
    public void resetTurnFlags(){
        randomTurn = false;
    }
    public void endGame(){
        isGameEnded = true;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    public boolean isGameEnded(){
        return isGameEnded;
    }

    public String getPlayerName(){
        playerName = JOptionPane.showInputDialog(this, "Oyuncu adınızı girin:", "Oyuncu Adı", JOptionPane.PLAIN_MESSAGE);
        if(playerName == null || playerName.isEmpty()){
            playerName = "Oyuncu " + (controller.getCurrentPlayer() + 1);
        }
        return playerName;
    }

    public void showHighScores(List<HighScoreEntry> highScores) {
        StringBuilder sb = new StringBuilder("Yüksek Skorlar:\n");

        for (HighScoreEntry entry : highScores) {
            sb.append(entry.getName()).append(": ").append(entry.getScore()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
    /*public void endGame(){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }*/
    }

