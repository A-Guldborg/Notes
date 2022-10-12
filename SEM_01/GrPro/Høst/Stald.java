import java.util.Scanner;
public class Stald {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int får = 0, minfår = 0, maxfår = 0;
        while (s.hasNext()) {
            if (s.nextLine().equals("Får ind")) {
                får++;
                maxfår = Math.max(maxfår, får);
            } else {
                får--;
                minfår = Math.min(minfår, får);
            }
        }
        System.out.println(maxfår - minfår);
    }
}
