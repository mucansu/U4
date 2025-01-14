public enum TrapType {
    FIXED_SCORE_LOSS("Fixed Score Loss", 100),
    PERCENT_TO_OPPONENT("Percent Score To Opponent", 20),
    CREW_LOSS("Crew Loss", 1);

    private final String name;
    private final int effectValue;

    TrapType(String name, int effectValue) {
        this.name = name;
        this.effectValue = effectValue;
    }

    public String getName() { return name; }
    public int getEffectValue() { return effectValue; }
}