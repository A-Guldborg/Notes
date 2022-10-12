import java.util.Scanner;
public class Vindmolle {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int start = s.nextInt(), end = s.nextInt();
        int clockwisedist = (end - start);
        if (start > end) {
            clockwisedist += 360;
        }
        if (clockwisedist <= 180) {
            System.out.println(clockwisedist);
        } else {
            System.out.println(-(360-clockwisedist));
        }
    }
}