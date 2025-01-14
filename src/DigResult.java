public class DigResult {
    private final String name;       // Trap/Treasure ismi gibi ekranda göstermek istediğimiz bilgi
    private final int scoreChange;   // Kazanınca eklenen(+), kaybedince çıkarılan(-) puan
    private final int crewChange;    // Kazanınca eklenen(+), kaybedince çıkarılan(-) ekip üyesi
    private final String message;    // Kullanıcıya gösterilecek mesaj

    public DigResult(String name, int scoreChange, int crewChange, String message) {
        this.name = name;
        this.scoreChange = scoreChange;
        this.crewChange = crewChange;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public int getScoreChange() {
        return scoreChange;
    }

    public int getCrewChange() {
        return crewChange;
    }

    public String getMessage() {
        return message;
    }
}
