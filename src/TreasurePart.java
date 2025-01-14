public class TreasurePart extends Diggable {
    private TreasureGroup group;

    public TreasurePart(TreasureGroup group) {
        // group.getName() gibi bir isim, group.getValue() gibi bir toplam puan olabilir
        super(group.getName(), group.getValue());
        this.group = group;
    }

    @Override
    public DigResult onDig() {
        // Bu hücre kazıldığında, grup içinde "1 hücre daha kazıldı" diye işaretleriz
        return group.digPart();
    }
}
