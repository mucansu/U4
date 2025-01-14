import java.io.*;
import java.util.*;
/**
 * Klass som hanterar highscore-listan, inklusive att spara, ladda och lägga till nya poster.
 * Highscore-listan sparas i en textfil och är begränsad till de 10 bästa resultaten.
 *
 * @author Mustafa
 */
public class HighScoreManager {
    private List<HighScoreEntry> highScores;
    private static final String HIGH_SCORE_FILE = "highscores.txt";


    /**
     * Skapar en ny instans av HighScoreManager och laddar befintliga poäng från filen.
     */
    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }
    /**
     * Lägger till en ny highscore till listan och sorterar den.
     * Tar bort den lägsta posten om listan överstiger 10 poster.
     *
     * @param name Namnet på spelaren.
     * @param score Spelarens poäng.
     */
    public void addHighScore(String name, int score) {
        // Yeni skor kaydını ekle
        highScores.add(new HighScoreEntry(name, score));

        // Manuel sıralama
        for (int i = 0; i < highScores.size() - 1; i++) {
            for (int j = i + 1; j < highScores.size(); j++) {
                if (highScores.get(i).getScore() < highScores.get(j).getScore()) {
                    // İkisini yer değiştir
                    HighScoreEntry temp = highScores.get(i);
                    highScores.set(i, highScores.get(j));
                    highScores.set(j, temp);
                }
            }
        }

        // İlk 10'dan fazlaysa sonuncuyu kaldır
        if (highScores.size() > 10) {
            highScores.remove(highScores.size() - 1);
        }
    }

    /**
     * Hämtar alla highscore-poster.
     *
     * @return En lista över highscore-poster.
     */
    public List<HighScoreEntry> getHighScores() {
        return highScores;
    }
    /**
     * Laddar highscore-poster från en textfil.
     */
    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highScores.add(new HighScoreEntry(name, score));
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Dosya yoksa sorun değil, boş liste
        }
    }
    /**
     * Sparar den aktuella highscore-listan till en textfil.
     */
    public void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            for (HighScoreEntry entry : highScores) {
                writer.write(entry.getName() + "," + entry.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Kontrollerar om en given poäng kvalificerar sig för highscore-listan.
     *
     * @param score Poängen som ska kontrolleras.
     * @return True om poängen kvalificerar sig, annars false.
     */
    public boolean qualifies(int score) {
        // Henüz 10’dan az kayıt varsa otomatik olarak girer
        if (highScores.size() < 10) {
            return true;
        }
        // Aksi halde, listede en düşük 10. skordan büyükse girer
        // highScores listesi en yüksekten düşüğe sıralanmışsa,
        // son eleman en düşük skordur
        int lastIndex = highScores.size() - 1;
        int lowestTopScore = highScores.get(lastIndex).getScore();
        return score > lowestTopScore;
    }
}
