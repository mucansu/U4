import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Diggable[][] board;
    private int currentPlayer;          // 0: P1, 1: P2
    private int[] playerScores;         // 2 oyuncu
    private int[] crewMembers;          // 2 oyuncu
    private GameView view;
    private HighScoreManager highScoreManager;

    // Örnek: next move random
    private boolean[] forcedRandom;     // [2] => her oyuncu için

    // Bilgi: extra moves left
    private int[] extraMoves;           // Sürpriz ile geldi diyelim

    // TreasureGroup’ları takip edelim
    private List<TreasureGroup> allTreasures;

    public GameController(int size, GameView view) {
        this.view = view;
        board = new Diggable[size][size];
        playerScores = new int[]{0, 0};
        crewMembers = new int[]{3, 3};
        forcedRandom = new boolean[]{false, false};
        extraMoves = new int[]{0, 0};

        highScoreManager = new HighScoreManager();
        allTreasures = new ArrayList<>();

        initializeBoard();
    }

    private void initializeBoard() {

        //   1) L şekilli define (toplam 4 hücre)
        TreasureGroup lShape = new TreasureGroup("L-Shape", 50, 4);
        //   Bu define’ı 4 hücreye yerleştirelim, diyelim (0,0), (1,0), (2,0), (2,1)
        board[0][0] = new TreasurePart(lShape);
        board[1][0] = new TreasurePart(lShape);
        board[2][0] = new TreasurePart(lShape);
        board[2][1] = new TreasurePart(lShape);
        allTreasures.add(lShape);

        // 2) Tek hücrelik define
        TreasureGroup single1 = new TreasureGroup("SingleGold", 75, 1);
        board[0][5] = new TreasurePart(single1);
        allTreasures.add(single1);

        // 3) T şekilli define
        TreasureGroup tShape = new TreasureGroup("T-Shape", 60, 4);
        board[4][4] = new TreasurePart(tShape);
        board[4][5] = new TreasurePart(tShape);
        board[4][6] = new TreasurePart(tShape);
        board[5][5] = new TreasurePart(tShape);
        allTreasures.add(tShape);

        // 4) Kare (Square) Shape
        TreasureGroup squareShape = new TreasureGroup("SquareShape", 40, 4);
        board[7][7] = new TreasurePart(squareShape);
        board[7][8] = new TreasurePart(squareShape);
        board[8][7] = new TreasurePart(squareShape);
        board[8][8] = new TreasurePart(squareShape);
        allTreasures.add(squareShape);

        // 5) İkili (2 hücreli) Treasure
        TreasureGroup twoCellTreasure = new TreasureGroup("TwoCell", 30, 2);
        board[3][0] = new TreasurePart(twoCellTreasure);
        board[3][1] = new TreasurePart(twoCellTreasure);
        allTreasures.add(twoCellTreasure);

        // 4) Trap’ler
        board[3][3] = new Trap(TrapType.FIXED_SCORE_LOSS);
        board[2][7] = new Trap(TrapType.PERCENT_TO_OPPONENT);
        board[6][6] = new Trap(TrapType.CREW_LOSS);

        board[0][1] = new Trap(TrapType.CREW_LOSS);
        board[1][1] = new Trap(TrapType.CREW_LOSS);
        board[9][9] = new Trap(TrapType.CREW_LOSS);
        board[9][8] = new Trap(TrapType.CREW_LOSS);
        board[9][7] = new Trap(TrapType.CREW_LOSS);

        // 5) Surprise’ler
        board[8][2] = new Surprise(SurpriseType.EXTRA_CREW);
        board[7][7] = new Surprise(SurpriseType.EXTRA_TURN_PER_CREW);


        // Geri kalan hücreler boş
    }
    private void initializeBoard2() {
        // 1) L-Shape
        TreasureGroup lShape = new TreasureGroup("L-Shape", 50, 4);
        board[1][1] = new TreasurePart(lShape);
        board[2][1] = new TreasurePart(lShape);
        board[3][1] = new TreasurePart(lShape);
        board[3][2] = new TreasurePart(lShape);
        allTreasures.add(lShape);

        // 2) Tek hücrelik define
        TreasureGroup single1 = new TreasureGroup("SingleGold", 75, 1);
        board[9][9] = new TreasurePart(single1);
        allTreasures.add(single1);

        // 3) T-Shape
        TreasureGroup tShape = new TreasureGroup("T-Shape", 60, 4);
        board[5][5] = new TreasurePart(tShape);
        board[5][6] = new TreasurePart(tShape);
        board[5][7] = new TreasurePart(tShape);
        board[6][6] = new TreasurePart(tShape);
        allTreasures.add(tShape);

        // 4) Kare (Square) Shape
        TreasureGroup squareShape = new TreasureGroup("SquareShape", 40, 4);
        board[2][6] = new TreasurePart(squareShape);
        board[2][7] = new TreasurePart(squareShape);
        board[3][6] = new TreasurePart(squareShape);
        board[3][7] = new TreasurePart(squareShape);
        allTreasures.add(squareShape);

        // 5) İkili (2 hücreli) Treasure
        TreasureGroup twoCellTreasure = new TreasureGroup("TwoCell", 30, 2);
        board[8][4] = new TreasurePart(twoCellTreasure);
        board[8][5] = new TreasurePart(twoCellTreasure);
        allTreasures.add(twoCellTreasure);

        // 6) Trap’ler (Daha fazla Crew Loss eklenmiştir)
        board[1][3] = new Trap(TrapType.FIXED_SCORE_LOSS);
        board[7][1] = new Trap(TrapType.PERCENT_TO_OPPONENT);
        board[4][4] = new Trap(TrapType.CREW_LOSS);
        board[2][2] = new Trap(TrapType.CREW_LOSS);
        board[6][2] = new Trap(TrapType.CREW_LOSS);
        board[5][3] = new Trap(TrapType.CREW_LOSS);

        // 7) Surprise’ler (FORCED_RANDOM_NEXT kaldırıldı)
        board[0][2] = new Surprise(SurpriseType.EXTRA_CREW);
        board[6][8] = new Surprise(SurpriseType.EXTRA_TURN_PER_CREW);

        // Kalan hücreler boş
    }


    public void dig(int row, int col) {
        if (crewMembers[currentPlayer] <= 0) {
            view.displayMessage("Player " + (currentPlayer+1) + " has no crew!");
            return;
        }

        // Zorunlu rastgele hamle varsa?
        if (forcedRandom[currentPlayer]) {
            int[] r = pickRandomCell();
            row = r[0]; col = r[1];
            forcedRandom[currentPlayer] = false; // resetliyoruz
        }

        // Hücre dolu mu?
        if (board[row][col] != null) {
            Diggable entity = board[row][col];
            DigResult result = entity.onDig();
            board[row][col] = null; // Kazdık, artık boş

            // Puan güncellemesi
            int newScore = playerScores[currentPlayer] + result.getScoreChange();
            if (newScore < 0) newScore = 0;
            playerScores[currentPlayer] = newScore;

            // Crew güncellemesi
            int newCrew = crewMembers[currentPlayer] + result.getCrewChange();
            if (newCrew < 0) newCrew = 0;
            crewMembers[currentPlayer] = newCrew;

            // Trap: eğer "Percent Score to Opponent" vb. (trapType = PERCENT_TO_OPPONENT)
            if (result.getName().equals("Percent Score To Opponent")) {
                // %20 rakibe verelim
                int give = (int) (playerScores[currentPlayer] * 0.2);
                playerScores[currentPlayer] -= give;
                playerScores[1 - currentPlayer] += give;
            }

            // Renk seçimi: treasure -> turuncu, trap -> kırmızı, surprise -> mavi
            Color cellColor = Color.GRAY; // default
            if (entity instanceof TreasurePart) cellColor = Color.ORANGE;
            else if (entity instanceof Trap) cellColor = Color.RED;
            else if (entity instanceof Surprise) cellColor = Color.CYAN;

            // Arayüzde butonun text’i, rengi
            view.updateBoard(row, col, result.getName(), cellColor);
            view.updateScore(playerScores);
            view.updateCrew(crewMembers);

            // Mesaj
            if (result.getMessage()!=null && !result.getMessage().isEmpty()) {
                view.displayMessage("Player " + (currentPlayer+1) + ": " + result.getMessage());
            }

            // Sürpriz ek efekt
            if (result.isExtraTurn()) {
                // Örneğin "extraMoves[currentPlayer] = crewMembers[currentPlayer];"
                extraMoves[currentPlayer] = crewMembers[currentPlayer];
            }
            if (result.isForcedRandomNext()) {
                forcedRandom[currentPlayer] = true;
            }

        } else {
            // Boş hücre
            view.updateBoard(row, col, "X", Color.LIGHT_GRAY);
        }

        // Hamle bitti
        // ExtraMove varsa bir hamle daha kullanılabilir
        if (extraMoves[currentPlayer] > 0) {
            extraMoves[currentPlayer]--;
            // Yine currentPlayer kazmaya devam
            view.displayMessage("Extra move for Player " + (currentPlayer+1));
        } else {
            nextTurn();
        }

        checkGameOver();
    }

    private void nextTurn() {
        currentPlayer = 1 - currentPlayer;
        view.updateTurn(currentPlayer);
    }

    public Diggable[][] getBoard() {
        return board;
    }
    /**
     * Oyun bitiş kontrolü:
     * 1. Ekip 0 ise bitiyor
     * 2. Tüm define’ler kazıldıysa da bitiyor
     */
    private void checkGameOver() {
        // Ekip 0?
        if (crewMembers[currentPlayer] <= 0) {
            endGame(1 - currentPlayer);
            return;
        }
        // 2. Tüm hücreler kazıldıysa oyun biter
        boolean allCellsDug = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    allCellsDug = false;
                    break;
                }
            }
            if (!allCellsDug) break;
        }

        if (allCellsDug) {
            int winner = getWinner();

            endGame(winner);
        }
    }

    private int getWinner() {
        int winner;

        // Puan kontrolü
        if (playerScores[0] > playerScores[1]) {
            winner = 0;
        } else if (playerScores[1] > playerScores[0]) {
            winner = 1;
        } else {
            // Eğer puanlar eşitse crew sayısına bak
            if (crewMembers[0] > crewMembers[1]) {
                winner = 0;
            } else if (crewMembers[1] > crewMembers[0]) {
                winner = 1;
            } else {
                // Eğer puanlar ve crew sayısı da eşitse bir kazanan belirleyin (örneğin Player 1)
                winner = 2; // Varsayılan olarak Player 1 kazansın
            }
        }
        return winner;
    }

    private void endGame(int winnerIndex) {
        if (winnerIndex==2){
            view.displayMessage("GAME OVER. No Winner! ");
        }
        else {
            // Skorları göster
            view.displayMessage("GAME OVER. Winner is Player " + (winnerIndex + 1)
                    + " with score " + playerScores[winnerIndex]);

            // Highscore list (eğer yeterince yüksekse adını sor)
            int winnerScore = playerScores[winnerIndex];
            if (highScoreManager.qualifies(winnerScore)) {
                // Kullanıcıdan isim al
                String winnerName = view.showTop10Message();
                highScoreManager.addHighScore(winnerName, winnerScore);
                highScoreManager.saveHighScores();
            }

            // Highscores göster
            StringBuilder sb = new StringBuilder("=== Highscores ===\n");
            for (HighScoreEntry e : highScoreManager.getHighScores()) {
                sb.append(e.getName()).append(" -> ").append(e.getScore()).append("\n");
            }
            view.displayMessage(sb.toString());

            // Yeni oyun başlat
            startNewGame();
        }


    }


    private void startNewGame() {
        // Tekrar 10x10 board kur vs.
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                board[i][j] = null;
            }
        }
        playerScores[0] = 0; playerScores[1] = 0;
        crewMembers[0] = 3; crewMembers[1] = 3;
        forcedRandom[0] = false; forcedRandom[1] = false;
        extraMoves[0] = 0; extraMoves[1] = 0;
        allTreasures.clear();

        initializeBoard();
        view.resetBoard();
        view.updateScore(playerScores);
        view.updateCrew(crewMembers);
        currentPlayer = 0;
        view.updateTurn(currentPlayer);
    }

    private int[] pickRandomCell() {
        // Rastgele bir hücre seç
        int rows = board.length;
        int cols = board[0].length;
        while (true) {
            int r = (int)(Math.random()*rows);
            int c = (int)(Math.random()*cols);
            // Orayı kazılmamış bile kazabilir.
            return new int[]{r,c};
        }
    }

    public void endGameManually() {
        if (playerScores[0]==playerScores[1]){
           endGame(2);
        }
        int winner = (playerScores[0] >= playerScores[1]) ? 0 : 1;
        endGame(winner);
    }
}
