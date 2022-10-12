import java.util.Scanner;
import java.math.BigInteger;
import java.util.Arrays;

public class Reproduktionsrater {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int n = s.nextInt(), life = s.nextInt();
        BigInteger[] lifecycle = new BigInteger[life];
        for (int i = 0; i < life; i++) {
            lifecycle[i] = new BigInteger(s.next());
        }
        BigInteger[] canines = new BigInteger[life+1];
        BigInteger totalCanines = new BigInteger("1");
        Arrays.fill(canines, new BigInteger("0"));
        canines[0] = new BigInteger("1");
        System.out.println(totalCanines.toString());
        for (int i = 1; i < n; i++) {
            BigInteger newCanines = new BigInteger("0");
            totalCanines = totalCanines.subtract(canines[life]);
            for (int j = life; j > 0; j--) {
                canines[j] = canines[j-1];
                newCanines = newCanines.add(canines[j].multiply(lifecycle[j-1]));
            }
            canines[0] = newCanines;
            totalCanines = totalCanines.add(newCanines);
            System.out.println(totalCanines.toString());
        }
    }
}