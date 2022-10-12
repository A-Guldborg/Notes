import java.util.Scanner;
import java.math.*;
public class KaninAvl {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int n = s.nextInt();
        BigInteger a = BigInteger.valueOf(1), b = BigInteger.valueOf(1);
        
        for (int i = 0; i < n; i++) {
            System.out.println(a);
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }
    }
}