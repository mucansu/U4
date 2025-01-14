/**
 * Klass som representerar en post i highscore-listan.
 * Innehåller spelarens namn och poäng.
 *
 * @author Mustafa
 */
public class HighScoreEntry {
    private String name;
    private int score;
    /**
     * Skapar en ny highscore-post.
     *
     * @param name Spelarens namn.
     * @param score Spelarens poäng.
     */
    public HighScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * Hämtar spelarens namn.
     *
     * @return Spelarens namn.
     */
    public String getName() {
        return name;
    }
    /**
     * Hämtar spelarens poäng.
     *
     * @return Spelarens poäng.
     */
    public int getScore() {
        return score;
    }
}
