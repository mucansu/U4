import java.util.Random;

// GameController class
public class GameController {
    private Diggable[][] board;
    private int currentPlayer; // 0 for Player 1, 1 for Player 2
    private int currentRow;
    private int currentCol;
    private int[] playerScores;
    private int[] crewMembers;
    private GameView view;

    public GameController(int size, GameView view) {
        this.view = view;
        board = new Diggable[size][size];
        playerScores = new int[] {0, 0};
        crewMembers = new int[] {3, 3};
        initializeBoard();
    }

    private void initializeBoard() {
        // Hard-coded board for simplicity
        board[0][0] = new Treasure(TreasureType.GOLDBOX); // Single-cell treasure
        board[1][1] = new Treasure(TreasureType.BRONZBOX); // Single-cell treasure
        board[4][4] = new Treasure(TreasureType.SILVERBOX); // Single-cell treasure

// Multi-cell treasures
        board[5][5] = new Treasure(TreasureType.WOODENBOX); // Part of "L" shaped treasure
        board[6][5] = new Treasure(TreasureType.DIAMONDBOX);
        board[6][6] = new Treasure(TreasureType.WOODENBOX);

        board[7][7] = new Treasure(TreasureType.WOODENBOX); // Part of "T" shaped treasure
        board[7][8] = new Treasure(TreasureType.GOLDBOX);
        board[6][8] = new Treasure(TreasureType.SILVERBOX);
        board[8][8] = new Treasure(TreasureType.SILVERBOX);

        board[8][0] = new Treasure(TreasureType.WOODENBOX); // Part of "I" shaped treasure
        board[8][1] = new Treasure(TreasureType.WOODENBOX);
        board[8][2] = new Treasure(TreasureType.SILVERBOX);
        board[9][9] = new Trap(-50);
        // Remaining cells are null, representing empty spots
    }

    public void dig(int row, int col) {
        currentRow = row;
        currentCol = col;

        // Immediately mark the button as dug
        if (board[row][col] != null) {
            view.updateBoard(row, col, board[row][col].getType());
            board[row][col].onDig(this, view);
            board[row][col] = null; // Clear the cell after digging
        } else {
            view.updateBoard(row, col, "Empty");
           // view.displayMessage("Nothing here. Try again!");
        }

        nextTurn();
    }

    public void addScore(int score) {
        playerScores[currentPlayer] += score;
        view.updateScore(playerScores);
    }

    public void reduceCrew() {
        crewMembers[currentPlayer]--;
        view.updateCrew(crewMembers);
        checkGameOver();
    }

    public void checkGameOver() {
        if (crewMembers[currentPlayer] <= 0) {
            view.displayMessage("Player " + (currentPlayer + 1) + " is out of crew members. Game over!");
            // Reset or end game logic
        }
    }

    public void nextTurn() {
        currentPlayer = 1 - currentPlayer; // Toggle between 0 and 1
        view.updateTurn(currentPlayer);
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }
    public int getPlayerScore() {
        return playerScores[currentPlayer];
    }
    public void addOpponentScore(int score) {
        int opponent = 1 - currentPlayer;
        playerScores[opponent] += score;
        view.updateScore(playerScores);
    }
}

