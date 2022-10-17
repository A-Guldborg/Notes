---
title: Gennemsnitsvaegt
---

```java
import java.util.Scanner;
public class Gennemsnitsvaegt {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int animals = 0, weight = 0;
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            double w = s.nextDouble();
            if (w < 10 || w > 2000) {
                i++;
                if (i < n) {
                    s.nextDouble();
                }
            } else {
                animals++;
                weight += w;
            }
        }
        System.out.println((double) weight / animals);
    }
}
```
