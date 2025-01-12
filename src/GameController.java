class GameController {
    private Diggable[][] board;
    private int currentPlayer = 0; // 0 for player 1, 1 for player 2
    private int[] playerScores = {0, 0};
    private int[] crewMembers = {3, 3};
    private GameView view;
    private HighScoreManager highScoreManager;

    public GameController(int size, GameView view) {
        this.view = view;
        board = new Diggable[size][size];
        initializeBoard();
        highScoreManager = new HighScoreManager();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    private void initializeBoard() {
        // Basic treasure placement (expand this)
        board[2][2] = new Treasure(50);
        board[5][5] = new Trap();
        board[8][1] = new Treasure(25);
    }

    public void dig(int row, int col) {
        if (board[row][col] != null) {
            Diggable item = board[row][col];
            view.updateBoard(row, col, item.getType());

            if (item instanceof Treasure) {
                playerScores[currentPlayer] += item.getValue();
            } else if (item instanceof Trap) {
                crewMembers[currentPlayer]--;
                playerScores[currentPlayer] += item.getValue();//Negatif puan ekleniyor
                view.displayMessage("Tuzağa yakalandın! Puan Kaybı ve Mürettebat Kaybı!");
            } else if (item instanceof Surprise) {
                Surprise surprise = (Surprise) item;
                handleSurprise(surprise.getSurpriseType());
            }

            view.updateScore(playerScores);
            view.updateCrew(crewMembers);
            board[row][col] = null; // Tekrar kazmayı engelle

            checkGameOver();
            if(!view.isExtraTurnsActive() && !view.isRandomTurnActive()) { //Ekstra tur yoksa ve rastgele tur aktif değilse sıra değişir
                currentPlayer = 1 - currentPlayer; // Sıra değiştir
                view.updateTurn(currentPlayer);
            }
            view.resetTurnFlags();
        } else {
            view.updateBoard(row, col, "Boş");
            currentPlayer = 1 - currentPlayer;
            view.updateTurn(currentPlayer);
        }
    }


    private void handleSurprise(int surpriseType) {
        switch (surpriseType) {
            case 1:
                crewMembers[currentPlayer]++;
                view.displayMessage("Ekstra mürettebat kazandın!");
                break;
            case 2:
                view.setExtraTurns(crewMembers[currentPlayer]);
                view.displayMessage("Ekstra turlar kazandın!");
                break;
            case 3:
                // Rastgele bir hazineyi aç (Daha sonra implemente edilecek - İleri seviye)
                view.displayMessage("Rastgele bir hazine açıldı! (Henüz implemente edilmedi)");
                break;
            case 4:
                view.setRandomTurn();
                view.displayMessage("Sıradaki turun rastgele seçildi!");
                break;
        }
        view.updateCrew(crewMembers);
    }
    public void checkGameOver(){
        if (crewMembers[currentPlayer] <= 0) {
            view.displayMessage("Oyuncu " + (currentPlayer + 1) + " tüm mürettebatını kaybetti!");
            int winner = 1 - currentPlayer;
            String playerName = view.getPlayerName();
            highScoreManager.addHighScore(playerName, playerScores[winner]);
            highScoreManager.saveHighScores();
            view.showHighScores(highScoreManager.getHighScores());
            view.endGame();
        }
    }
}