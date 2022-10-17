---
title: Kornsilo
---

```java
import java.util.Scanner;
public class Kornsilo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double n = s.nextDouble(), k = s.nextDouble(), h = s.nextDouble();
        int output = (int) Math.ceil(n / h) - (int) k;
        if (output <= 0) {
            System.out.println(0);
        } else {
            System.out.println(output);
        }
    }
}
```
