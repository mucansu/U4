public enum SurpriseType {
    EXTRA_CREW("Extra Crew"),
    EXTRA_TURN_PER_CREW("Extra Turn Per Crew"),
    RANDOM_DIG("Random Dig"),
    FORCED_RANDOM_NEXT("Forced Random Next");

    private final String name;

    SurpriseType(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}