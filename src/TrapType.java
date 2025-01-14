

enum TrapType {
    SPIKED_PIT("Spiked Pit", -100),
    POISON_GAS("Poison Gas", -50),
    COLLAPSING_TUNNEL("Collapsing Tunnel", -75);

    private final String name;
    private final int effect;

    TrapType(String name, int effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public int getEffect() {
        return effect;
    }
}
