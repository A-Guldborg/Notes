---
title: Demo
---

```java
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        
        ArrayList<Media> medias = new ArrayList<Media>();
        medias.add(new Film("The Godfather", 1972));
        medias.add(new Film("Schindler's List", 1993));
        medias.add(new Film("The Shawshank Redemption", 1994));
        
        for (Media med : medias) {
            String oldtitle = med.getTitle();
            String newtitle = oldtitle + " SuperDan";
            med.setTitle(newtitle);
        }
        
        for (Media med : medias) {
            System.out.println(med.getTitle());
        }
    }
}

// Film eksempler:
// The Godfather; 1972; Crime, Drama; 9,2;
// Schindler's List; 1993; Biography, Drama, History; 8,9;
// The Shawshank Redemption; 1994; Drama; 9,3;
// Serie eksempler
// Game Of Thrones; 2011-; Action, Adventure, Drama; 9,5; 1-10, 2-10, 3-10, 4-10, 5-10, 6-10, 7-7;
// Breaking Bad; 2008-2013; Crime, Drama, Thriller; 9,5; 1-7, 2-13, 3-13, 4-13, 5-16;
// The Wire; 2001-2008; Crime, Drama, Thriller; 9,3; 1-13, 2-12, 3-12, 4-13, 5-10; 
```
