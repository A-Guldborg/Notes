import java.util.Scanner;
import java.math.BigInteger;

public class bigint {
    public static void main(String[] args) {
        BigInteger n = new BigInteger("0");
        n = n.subtract(new BigInteger("1"));
        System.out.println(n);
    }
}