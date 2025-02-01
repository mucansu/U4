import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Kontrollklass som hanterar spelets logik, spelbrädet och spelarnas interaktioner.
 * Implementerar reglerna och ansvarar för att hantera händelser i spelet.
 *
 * @author Mustafa Cansu
 */
public class GameController {
    private Diggable[][] board;
    private int currentPlayer;
    private int[] playerScores;
    private int[] crewMembers;
    private GameView view;
    private HighScoreManager highScoreManager;


    private boolean[] forcedRandom;

    // extra moves left
    private int[] extraMoves;


    private List<TreasureGroup> allTreasures;
    /**
     * Konstruktor för att skapa en ny GameController.
     *
     * @param size Storleken på spelbrädet.
     * @param view Spelvyn för att uppdatera GUI.
     */
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
    /**
     * Initialiserar spelbrädet med objekt som skatter, fällor och överraskningar.
     */
    private void initializeBoard() {


        TreasureGroup lShape = new TreasureGroup("L-Shape", 50, 4);

        board[0][0] = new TreasurePart(lShape);
        board[1][0] = new TreasurePart(lShape);
        board[2][0] = new TreasurePart(lShape);
        board[2][1] = new TreasurePart(lShape);
        allTreasures.add(lShape);


        TreasureGroup single1 = new TreasureGroup("SingleGold", 75, 1);
        board[0][5] = new TreasurePart(single1);
        allTreasures.add(single1);


        TreasureGroup tShape = new TreasureGroup("T-Shape", 60, 4);
        board[4][4] = new TreasurePart(tShape);
        board[4][5] = new TreasurePart(tShape);
        board[4][6] = new TreasurePart(tShape);
        board[5][5] = new TreasurePart(tShape);
        allTreasures.add(tShape);


        TreasureGroup squareShape = new TreasureGroup("SquareShape", 40, 4);
        board[7][7] = new TreasurePart(squareShape);
        board[7][8] = new TreasurePart(squareShape);
        board[8][7] = new TreasurePart(squareShape);
        board[8][8] = new TreasurePart(squareShape);
        allTreasures.add(squareShape);


        TreasureGroup twoCellTreasure = new TreasureGroup("TwoCell", 30, 2);
        board[3][0] = new TreasurePart(twoCellTreasure);
        board[3][1] = new TreasurePart(twoCellTreasure);
        allTreasures.add(twoCellTreasure);


        board[3][3] = new Trap(TrapType.FIXED_SCORE_LOSS);
        board[2][7] = new Trap(TrapType.PERCENT_TO_OPPONENT);
        board[6][6] = new Trap(TrapType.CREW_LOSS);

        board[0][1] = new Trap(TrapType.CREW_LOSS);
        board[1][1] = new Trap(TrapType.CREW_LOSS);
        board[9][9] = new Trap(TrapType.CREW_LOSS);
        board[9][8] = new Trap(TrapType.CREW_LOSS);
        board[9][7] = new Trap(TrapType.CREW_LOSS);


        board[8][2] = new Surprise(SurpriseType.EXTRA_CREW);
        board[7][7] = new Surprise(SurpriseType.EXTRA_TURN_PER_CREW);



    }
    /**
     * Initialiserar ett alternativt spelbräde med olika konfigurationer.
     */
    private void initializeBoard2() {
        // 1) L-Shape
        TreasureGroup lShape = new TreasureGroup("L-Shape", 50, 4);
        board[1][1] = new TreasurePart(lShape);
        board[2][1] = new TreasurePart(lShape);
        board[3][1] = new TreasurePart(lShape);
        board[3][2] = new TreasurePart(lShape);
        allTreasures.add(lShape);

        // 2)
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

        // 4)  (Square) Shape
        TreasureGroup squareShape = new TreasureGroup("SquareShape", 40, 4);
        board[2][6] = new TreasurePart(squareShape);
        board[2][7] = new TreasurePart(squareShape);
        board[3][6] = new TreasurePart(squareShape);
        board[3][7] = new TreasurePart(squareShape);
        allTreasures.add(squareShape);

        // 5)
        TreasureGroup twoCellTreasure = new TreasureGroup("TwoCell", 30, 2);
        board[8][4] = new TreasurePart(twoCellTreasure);
        board[8][5] = new TreasurePart(twoCellTreasure);
        allTreasures.add(twoCellTreasure);

        // 6) Trap
        board[1][3] = new Trap(TrapType.FIXED_SCORE_LOSS);
        board[7][1] = new Trap(TrapType.PERCENT_TO_OPPONENT);
        board[4][4] = new Trap(TrapType.CREW_LOSS);
        board[2][2] = new Trap(TrapType.CREW_LOSS);
        board[6][2] = new Trap(TrapType.CREW_LOSS);
        board[5][3] = new Trap(TrapType.CREW_LOSS);

        // 7) Surprise
        board[0][2] = new Surprise(SurpriseType.EXTRA_CREW);
        board[6][8] = new Surprise(SurpriseType.EXTRA_TURN_PER_CREW);

    }

    /**
     * Hanterar en spelares grävning på en specifik position.
     *
     * @param row Radpositionen på spelbrädet.
     * @param col Kolumnpositionen på spelbrädet.
     */
    public void dig(int row, int col) {
        boolean hasCrewMember = validateCrewMembers();
        if (!hasCrewMember) return;

        if (board[row][col] != null) {
            handleDigAction(row, col);
        } else {
            updateBoardAndUI(row, col, "X", Color.LIGHT_GRAY);
        }

        finalizeTurn();
    }
    private boolean validateCrewMembers() {
        if (crewMembers[currentPlayer] <= 0) {
            view.displayMessage("Player " + (currentPlayer + 1) + " has no crew left!");
            return false;
        }
        return true;
    }


    private void handleDigAction(int row, int col) {
        Diggable entity = board[row][col];
        DigResult result = entity.onDig();
        board[row][col] = null;

        updateScoresAndCrew(result);
        applyTrapEffect(result);
        Color cellColor = determineCellColor(entity);
        updateBoardAndUI(row, col, result.getName(), cellColor);


        if (result.getMessage() != null && !result.getMessage().isEmpty()) {
            view.displayMessage("Player " + (currentPlayer + 1) + ": " + result.getMessage());
        }

        applySurpriseEffect(result);
    }

    private void updateScoresAndCrew(DigResult result) {

        int newScore = playerScores[currentPlayer] + result.getScoreChange();
        playerScores[currentPlayer] = Math.max(newScore, 0);

        int newCrew = crewMembers[currentPlayer] + result.getCrewChange();
        crewMembers[currentPlayer] = Math.max(newCrew, 0);
    }

    private void applyTrapEffect(DigResult result) {
        if (result.getName().equals("Percent Score To Opponent")) {
            int give = (int) (playerScores[currentPlayer] * 0.2);
            playerScores[currentPlayer] -= give;
            playerScores[1 - currentPlayer] += give;
        }
    }

    private Color determineCellColor(Diggable entity) {
        if (entity instanceof TreasurePart) return Color.ORANGE;
        if (entity instanceof Trap) return Color.RED;
        if (entity instanceof Surprise) return Color.CYAN;
        return Color.GRAY; // Varsayılan
    }

    private void updateBoardAndUI(int row, int col, String text, Color cellColor) {
        view.updateBoard(row, col, text, cellColor);
        view.updateScore(playerScores);
        view.updateCrew(crewMembers);
    }

    private void applySurpriseEffect(DigResult result) {
        if (result.isExtraTurn()) {
            extraMoves[currentPlayer] = crewMembers[currentPlayer];
        }
        if (result.isForcedRandomNext()) {
            forcedRandom[currentPlayer] = true;
        }
    }

    private void finalizeTurn() {
        if (extraMoves[currentPlayer] > 0) {
            extraMoves[currentPlayer]--;
            view.displayMessage("Player " + (currentPlayer + 1) + " gets an extra move!");
        } else {
            nextTurn();

        }
        checkGameOver();
    }

    /**
     * Växlar tur till nästa spelare.
     */
    private void nextTurn() {
        currentPlayer = 1 - currentPlayer;
        view.updateTurn(currentPlayer);
    }

    public Diggable[][] getBoard() {
        return board;
    }
    /**
     * Kontrollerar om spelet är över och hanterar avslutningslogik.
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
    /**
     * Hämtar värdet int som index för vinnare.
     *
     * @return Index för vinnande spelare.
     */
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
    /**
     * Hanterar spelets slut, visar vinnaren och highscore-listan.
     *
     * @param winnerIndex Index för vinnande spelare (0 eller 1).
     */
    private void endGame(int winnerIndex) {
        if (winnerIndex==2){
            view.displayMessage("GAME OVER. No Winner! ");
        }
        else {

            view.displayMessage("GAME OVER. Winner is Player " + (winnerIndex + 1)
                    + " with score " + playerScores[winnerIndex]);

            int winnerScore = playerScores[winnerIndex];
            if (highScoreManager.qualifies(winnerScore)) {

                String winnerName = view.showTop10Message();
                highScoreManager.addHighScore(winnerName, winnerScore);
                highScoreManager.saveHighScores();
            }

            StringBuilder sb = new StringBuilder("=== Highscores ===\n");
            for (HighScoreEntry e : highScoreManager.getHighScores()) {
                sb.append(e.getName()).append(" -> ").append(e.getScore()).append("\n");
            }
            view.displayMessage(sb.toString());

            startNewGame();
        }


    }

    /**
     * Startar ett nytt spel och återställer spelbrädet.
     */
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

    /**
     * Avslutar spelet manuellt via GUI.
     */
    public void endGameManually() {
        if (playerScores[0]==playerScores[1]){
           endGame(2);
        }
        int winner = (playerScores[0] >= playerScores[1]) ? 0 : 1;
        endGame(winner);
    }
}
