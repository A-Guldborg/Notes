---
title: Hoest
---

```java
import java.util.Scanner;
public class Hoest {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int bredde = s.nextInt()-1, højde = s.nextInt()-1, x = s.nextInt(), y = s.nextInt();
        StringBuilder out = new StringBuilder();
        // Gå til (0, 0)
        while (y > 0) {
            out.append("^");
            y--;
        }
        // Drej mod vest
        out.append("<");
        while (x > 0) {
            out.append("^");
            x--;
        }
        // Drej mod øst
        out.append(">>");
        double radians = 0;
        while (x != bredde || y != højde) {
            // Så længe vi ikke har ramt sydøstligste hjørne
            if (x == 0 && Math.cos(radians) == -1) {
                out.append("<^<"); // Gå en y ned og drej
                y++;
                radians += Math.PI;
            } else if (x == bredde && Math.cos(radians) == 1) {
                out.append(">^>"); // Gå en y ned og drej
                y++;
                radians += Math.PI;
            } else {
                out.append("^"); // Gå frem (mod en af markens sider)
                x += Math.cos(radians);
            }
        }
        
        if (Math.cos(radians) != -1) {
            // Drej mod vest
            radians += Math.PI;
            out.append(">>");
        }
        while (x > 0) {
            out.append("^");
            x--;
        }
        out.append(">"); // Drej mod nord
        while (y > 0) {
            out.append("^");
            y--;
        }
        System.out.println(out);
    }
}
```
