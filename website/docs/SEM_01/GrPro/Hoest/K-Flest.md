---
title: Flest
---

```java
import java.util.*;
public class Flest {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> animals = Arrays.asList(s.nextLine().split(" "));
        int[] occurences = new int[animals.size()];
        int[] temp = new int[animals.size()];
        for (int i = 0; i < animals.size(); i++) {
            occurences[i] = 0;
            temp[i] = 0;
        }
        int n = s.nextInt();
        s.nextLine();
        
        for (int i = 0; i < n; i++) {
            String[] input = s.nextLine().toLowerCase().split(" ");
            int max = 0;
            for (String word : input) {
                int index = -1;
                if (word.codePointAt(word.length()-1) < 65) {
                    index = animals.indexOf(word.substring(0, word.length() - 1));
                } else {
                    index = animals.indexOf(word);
                }
                if (index != -1) {
                    temp[index]++;
                    if (temp[index] > max) max = temp[index];
                }
            }
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == max) {
                    occurences[j]++;
                }
                temp[j] = 0;
            }
        }
        
        // find max frequency and output all animals with said frequency
        int maxfreq = 0;
        for (int occ : occurences) {
            if (occ > maxfreq) maxfreq = occ;
        }
        for (int i = 0; i < animals.size(); i++) {
            if (occurences[i] == maxfreq) System.out.println(animals.get(i));
        }
    }
}
```
