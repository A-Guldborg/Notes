public class FaceCard extends Card {
    protected String face;
    public FaceCard(String suit, String face) {
        super(suit);
        this.face = face;
    }

    @Override
    public int getValue() {
        return 10;
    }
    
    @Override
    public String toString() {
        return "[" + suit + face + "]";
    }
}
