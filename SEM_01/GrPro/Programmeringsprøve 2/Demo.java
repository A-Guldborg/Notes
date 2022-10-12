public class Demo {    
    public static void demo() {
        Hand myHand = new Hand();
        myHand.add(new FaceCard("\u2660", "Q"));
        myHand.add(new NumberCard("\u2663", 2));
        myHand.add(new NumberCard("\u2660", 9));
        myHand.add(new NumberCard("\u2660", 1));
        myHand.add(new NumberCard("\u2663", 3));
        myHand.add(new NumberCard("\u2663", 5));
        myHand.add(new NumberCard("\u2663", 6));
        myHand.add(new NumberCard("\u2660", 4));
        myHand.add(new NumberCard("\u2663", 7));
        myHand.add(new NumberCard("\u2660", 10));
        myHand.add(new FaceCard("\u2663", "J"));
        myHand.add(new FaceCard("\u2660", "K"));
        myHand.sort();
        myHand.display();
    }
}
