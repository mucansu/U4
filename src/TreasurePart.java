/**
 * Definierar vad som händer när denna del grävs.
 *
 * @return Ett {@link DigResult} som innehåller resultatet av grävningen.
 * @author Mustafa Cansu
 */

public class TreasurePart extends Diggable {
    private TreasureGroup group;
    /**
     * Skapar en ny del av en skattgrupp.
     *
     * @param group Skattgruppen som delen tillhör.
     */
    public TreasurePart(TreasureGroup group) {

        super(group.getName(), group.getValue());
        this.group = group;
    }
    /**
     * Definierar vad som händer när denna del grävs.
     *
     * @return Ett {@link DigResult} som innehåller resultatet av grävningen.
     */
    @Override
    public DigResult onDig() {

        return group.digPart();
    }
}
