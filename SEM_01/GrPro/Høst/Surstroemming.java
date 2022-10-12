import java.util.Scanner;
public class Surstroemming {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int n = s.nextInt();
        
        System.out.println("? 100 100");
        int dist1 = s.nextInt();
        System.out.println("? 99 100");
        int dist2 = s.nextInt();
        int x = 99 - (dist1 - dist2 - 1) / 2;
        System.out.println("? 100 99");
        int dist3 = s.nextInt();
        int y = 99 - (dist1 - dist3 - 1) / 2;
        
        int z = (int) Math.sqrt(dist1 - Math.pow(100-x, 2) - Math.pow(100-y, 2));
        System.out.println("! " + x + " " + y + " " + z);
    }
}