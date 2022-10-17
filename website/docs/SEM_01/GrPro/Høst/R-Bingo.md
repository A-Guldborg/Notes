---
title: Bingo
---

```java
import java.util.Scanner;
import java.util.Arrays;
public class Bingo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        s.nextLine(); // B I N G O
        int[] numbers = new int[25];
        boolean hasFirstBingo = false;
        for (int i = 0; i < 25; i++) {
            if (i == 12) {
                s.next();
                continue;
            }
            numbers[i] = s.nextInt();
        }
        numbers[12] = 0;
        int[] testgroups = new int[13];
        Arrays.fill(testgroups, 0);
        testgroups[2]++; // Middle horizontal
        testgroups[7]++; // Middle vertical
        testgroups[10]++; // Vertical bottom left to top right
        testgroups[11]++; // Vertical top left to bottom right

        for (int i = 0; i < 75; i++) {
            int num = s.nextInt();
            for (int j = 0; j < 25; j++) {
                if (num == numbers[j]) {
                    // Check if number is on bingo cards
                    // First check vertical match
                    if (num <= 15) {
                        testgroups[5]++;
                    } else if (num <= 30) {
                        testgroups[6]++;
                    } else if (num <= 44) {
                        testgroups[7]++;
                    } else if (num <= 59) {
                        testgroups[8]++;
                    } else {
                        testgroups[9]++;
                    }

                    testgroups[j/5]++; // Horizontal match
                    if (j % 6 == 0) {
                        // We test diag topleft to bottomright first, because 24 is divisible by 6 and 4 but only in this diagonal line
                        testgroups[11]++;
                    } else if (j % 4 == 0) {
                        testgroups[10]++;
                    }
                    testgroups[12]++; // Full bingo card
                    break;
                }
            }

            if (hasFirstBingo) {
                if (testgroups[12] == 24) {
                    System.out.println(num + " bingo!");
                    return;
                }
            } else {
                for (int k = 0; k < 12; k++) {
                    if (testgroups[k] == 5) {
                        System.out.println(num + " bingo!");
                        hasFirstBingo = true;
                        break;
                    }
                }
            }
        }
    }
}
```
