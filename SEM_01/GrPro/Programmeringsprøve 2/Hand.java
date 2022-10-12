import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Hand {
    private List<Card> cards;
    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int total() {
        int total = 0;
        for (Card c : cards) {
            total += c.getValue();
        }
        return total;
    }

    public void display() {
        System.out.println(total() + " POINTS");
        for (Card c : cards) {
            char[][] cardarray = new char[10][9];
            int value = c.getValue();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == 0 || j == 0 || i == 9 || j == 8) {
                        cardarray[i][j] = '#';
                    } else if ((i == 2 && j == 2) || (i == 7 && j == 6)) {
                        if (c instanceof FaceCard) {
                            FaceCard facecard = (FaceCard) c;
                            cardarray[i][j] = facecard.face.toCharArray()[0];
                        } else {
                            if (value == 10) {
                                if (i == 2) {
                                    cardarray[i][j] = '1';
                                } else {
                                    cardarray[i][j-1] = '1';
                                    cardarray[i][j] = '0';
                                }
                            } else {
                                cardarray[i][j] = String.valueOf(c.getValue()).toCharArray()[0];
                            }
                        }
                    } else if (value == 10 && i == 2 && j == 3 && c instanceof NumberCard) {
                        cardarray[i][j] = '0';
                    } else {
                        cardarray[i][j] = ' ';
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    if (i*2 + j < value) {
                        if (i*2 + j == 9) {
                            cardarray[2][5] = c.suit.toCharArray()[0];
                        } else {
                            cardarray[3+i][3+j*2] = c.suit.toCharArray()[0];
                        }
                    }
                }
            }

            for (char[] arr : cardarray) {
                for (char character : arr) {
                    System.out.print(character);
                }
                System.out.println();
            }
            System.out.println();
        }
        
        // Ser lidt skævt ud da spar/klør/ruder/hjerter fylder lidt mere end en standard ASCII 
    }

    public void sort() {
        Collections.sort(cards);
    }
}
