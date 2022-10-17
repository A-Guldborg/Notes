---
title: Mark
---

```java
import java.util.Scanner;
public class Mark {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // n is number of corners/points on a polygon
        int n = s.nextInt();
        double ax = s.nextDouble(), ay = s.nextDouble(), bx = s.nextDouble(), by = s.nextDouble();
        double sum = 0;
        for (int i = 0; i < n - 2; i++) {
            double cx = s.nextDouble(), cy = s.nextDouble();
            
            // triangle calculation
            sum += Math.abs(ax*(by - cy) + bx*(cy-ay) + cx *(ay - by)) / 2;
            
            // save new coords as old / prev coords
            bx = cx;
            by = cy;
        }
        
        System.out.println(sum);
    }
}
```
