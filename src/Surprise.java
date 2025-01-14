public class Surprise extends Diggable {
    private SurpriseType type;

    public Surprise(SurpriseType type) {
        super(type.getName(), 0);
        this.type = type;
    }

    @Override
    public DigResult onDig() {
        switch (type) {
            case EXTRA_CREW:
                return new DigResult(
                        type.getName(),
                        0,
                        +1,
                        "Overraskning: Gained 1 crew member!",
                        false,
                        false
                );
            case EXTRA_TURN_PER_CREW:
                // Controller bu flag'ları yorumlayacak
                return new DigResult(
                        type.getName(),
                        0,
                        0,
                        "Överraskning: You get extra moves",
                        true, // extraTurn
                        false
                );
            case RANDOM_DIG:
                return new DigResult(
                        type.getName(),
                        0,
                        0,
                        "Överraskning: A random cell was dug automatically!",
                        false,
                        false
                );
            case FORCED_RANDOM_NEXT:
                return new DigResult(
                        type.getName(),
                        0,
                        0,
                        "Overraskning: Your next move will be forced random!",
                        false,
                        true
                );
            default:
                return new DigResult("Unknown Surprise", 0, 0, "", false, false);
        }
    }
}
