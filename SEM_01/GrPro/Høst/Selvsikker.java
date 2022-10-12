import java.util.Scanner;
public class Selvsikker {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        input = input.substring(0, input.length() - 1) + "!";
        String[] inputArray = input.split(" ");
        String temp = inputArray[0].toLowerCase();
        inputArray[0] = inputArray[1].substring(0, 1).toUpperCase() + inputArray[1].substring(1);
        inputArray[1] = temp;
        
        System.out.println(String.join(" ", inputArray));
    }
}