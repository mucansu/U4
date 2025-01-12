import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreManager {
    private List<HighScoreEntry> highScores;
    private static final String HIGH_SCORE_FILE = "highscores.txt";

    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    public void addHighScore(String name, int score) {
        highScores.add(new HighScoreEntry(name, score));
        Collections.sort(highScores, Comparator.comparingInt(HighScoreEntry::getScore).reversed());
        if (highScores.size() > 10) {
            highScores.remove(10); // En yüksek 10 skoru tut
        }
    }

    public List<HighScoreEntry> getHighScores() {
        return highScores;
    }

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
            // Dosya yoksa veya format hatalıysa sorun değil, boş liste ile devam et
        }
    }

    public void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            for (HighScoreEntry entry : highScores) {
                writer.write(entry.getName() + "," + entry.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Hata olursa konsola yazdır
        }
    }
}
