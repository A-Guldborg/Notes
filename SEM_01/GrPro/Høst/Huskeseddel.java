import java.util.Scanner;
public class Huskeseddel {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt() + 1;
        String output = "";
        for (int i = 1; i <= 4; i++) {
            output += (int) ((a % Math.pow(10, i) - a % Math.pow(10, i-1)) / Math.pow(10,i-1));
        }
        System.out.println(output);
    }
}
