---
title: Adgangskode
---

```java
import java.util.Scanner;
public class Adgangskode {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int pwlength = s.nextInt(), pets = s.nextInt();
        String[] names = new String[pets];
        for (int i = 0; i < pets; i++) {
            names[i] = s.next();
        }
        
        for (String name1 : names) {
            for (String name2 : names) {
                if (name1.equals(name2)) continue;
                if (name1.length() + name2.length() == pwlength) {
                    System.out.println(name1.toLowerCase() + name2.toLowerCase());
                    System.exit(0);
                }
            }
        }
        
        System.out.println("*umuligt*");
    }
}
```
