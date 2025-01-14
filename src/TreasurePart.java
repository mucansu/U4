/**
 * Definierar vad som händer när denna del grävs.
 *
 * @return Ett {@link DigResult} som innehåller resultatet av grävningen.
 */

public class TreasurePart extends Diggable {
    private TreasureGroup group;
    /**
     * Skapar en ny del av en skattgrupp.
     *
     * @param group Skattgruppen som delen tillhör.
     */
    public TreasurePart(TreasureGroup group) {
        // group.getName() gibi bir isim, group.getValue() gibi bir toplam puan olabilir
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
        // Bu hücre kazıldığında, grup içinde "1 hücre daha kazıldı" diye işaretleriz
        return group.digPart();
    }
}
