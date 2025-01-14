public class GameController {
    private Diggable[][] board;
    private int currentPlayer; // 0: Player1, 1: Player2
    private int[] playerScores;
    private int[] crewMembers;
    private GameView view;
    private HighScoreManager highScoreManager;

    public GameController(int size, GameView view) {
        this.view = view;
        board = new Diggable[size][size];
        playerScores = new int[] {0, 0};
        crewMembers = new int[] {3, 3};
        highScoreManager = new HighScoreManager();
        initializeBoard();
    }

    private void initializeBoard() {
        // Örnek doldurma
        board[0][0] = new Treasure(TreasureType.GOLDBOX);
        board[1][1] = new Treasure(TreasureType.BRONZBOX);
        board[4][4] = new Treasure(TreasureType.SILVERBOX);

        board[2][2] = new Trap(TrapType.COLLAPSING_TUNNEL);
        board[3][3] = new Trap(TrapType.SPIKED_PIT);
        board[9][9] = new Trap(TrapType.POISON_GAS);
        // vs...
    }

    public void dig(int row, int col) {
        if (crewMembers[currentPlayer] <= 0) {
            view.displayMessage("Player " + (currentPlayer + 1) + " has no crew left!");
            return;
        }

        // Hücre boş değilse kaz
        if (board[row][col] != null) {
            Diggable entity = board[row][col];
            DigResult result = entity.onDig(); // Model’den kazma sonucunu al
            board[row][col] = null;           // Kazılan yeri null yap

            // Puan güncellemesi
            int newScore = playerScores[currentPlayer] + result.getScoreChange();
            if (newScore < 0) newScore = 0; // skoru negatif yapmama kuralı
            playerScores[currentPlayer] = newScore;

            // Ekip güncellemesi
            int newCrewCount = crewMembers[currentPlayer] + result.getCrewChange();
            if (newCrewCount < 0) newCrewCount = 0; // olası eksiye düşmeyi engelle
            crewMembers[currentPlayer] = newCrewCount;

            // Görsel güncellemeler
            view.updateBoard(row, col, result.getName()); // buton üst yazısı
            view.updateScore(playerScores);
            view.updateCrew(crewMembers);

            // Mesaj var ise göster
            if (result.getMessage() != null && !result.getMessage().isEmpty()) {
                view.displayMessage("Player " + (currentPlayer + 1) + ": " + result.getMessage());
            }
        } else {
            // Hücre boşsa "Empty"
            view.updateBoard(row, col, "Empty");
        }

        nextTurn();
    }

    private void nextTurn() {
        currentPlayer = 1 - currentPlayer;
        view.updateTurn(currentPlayer);
    }

    public void checkGameOver() {
        // Ekip sıfırlanmışsa vs. bitiş kuralı
        if (crewMembers[currentPlayer] <= 0) {
            endGame();
        }
    }

    private void endGame() {
        // Skorlara göre kazananı bul
        int p1Score = playerScores[0];
        int p2Score = playerScores[1];
        int winner = (p1Score > p2Score) ? 0 : 1;

        String winnerName = "Player " + (winner + 1);
        highScoreManager.addHighScore(winnerName, playerScores[winner]);
        highScoreManager.saveHighScores();

        // Highscores gösterimi
        StringBuilder sb = new StringBuilder("Game Over!\nWinner: " + winnerName + "\n\n");
        sb.append("=== Highscores ===\n");
        for (HighScoreEntry entry : highScoreManager.getHighScores()) {
            sb.append(entry.getName()).append(" -> ").append(entry.getScore()).append("\n");
        }
        view.displayMessage(sb.toString());
    }
}
