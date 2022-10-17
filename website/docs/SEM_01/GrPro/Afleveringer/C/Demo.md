---
title: Demo
---

```java
/** OBS: Du skal ikke ændre koden i denne klasse, andet end at udkommentere metoder undervejs. */

public class Demo {
    static void opg1() {
        Hotel h = new Hotel("Harriott", 4, 3);
        h.avgGuests();
        h.addBooking("Anne Andersen", 7, true, 2, 2);
        h.addBooking("Berit Bjergsen", 2, false, 1, 2);
        h.addBooking("Christian Christoffersen", 3, false, 3, 0);
        h.addBooking("Dennis Demitri", 2, true, 3, 1);
        h.addBooking("Esther Eskildsen", 2, true, 4, 1);
    }

    static void opg2() {
        Hotel h = new Hotel("Harriott", 4, 3);
        h.addBooking("Frederik Frandsen", 0, true, 3, 0);    
        try {
            h.checkAddBooking("Hans Henriksen", 2, true, 2 ,1);
        } catch (RoomAlreadyBookedException e) {
            System.out.println(e.getMessage());
            System.out.println(e.requestedRoom());
        }
    }

    static void opg4() {
        Hotel h = new Hotel("Harriott", 4, 3);
        h.addBooking("Anne Andersen", 7, true, 2, 2);
        h.addBooking("Berit Bjergsen", 2, false, 1, 2);
        h.addBooking("Christian Christoffersen", 3, false, 3, 0);
        h.addBooking("Dennis Demitri", 2, true, 3, 1);
        h.showHotel();
        System.out.print(h.breakfastFactor());
    }
}
```
