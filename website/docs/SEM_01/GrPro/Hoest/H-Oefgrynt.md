---
title: Oefgrynt
---

```java
import java.util.Scanner;
public class Oefgrynt {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 1; i <= t; i++) {
            if (i % 15 == 0) {
                System.out.println("øfgrynt");
            } else if (i % 5 == 0) {
                System.out.println("grynt");
            } else if (i % 3 == 0) {
                System.out.println("øf");
            } else {
                System.out.println(i % 100);
            }
        }
    }
}
```
