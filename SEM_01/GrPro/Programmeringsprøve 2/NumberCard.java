public class NumberCard extends Card {
    protected int number;
    public NumberCard(String suit, int number) {
        super(suit);
        this.number = number;
    }

    @Override
    public int getValue() {
        return number;
    }
    
    @Override
    public String toString() {
        return "[" + suit + number + "]";
    }
}
