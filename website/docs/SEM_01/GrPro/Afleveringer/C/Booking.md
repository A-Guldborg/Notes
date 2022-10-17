---
title: Booking
---

```java
public class Booking {
    private String contactPerson;
    private int guests;
    private boolean breakfastIncluded;
    private Room room;

    public Booking(String contactPerson, int guests, boolean breakfastIncluded, Room room) throws InvalidGuestsException {
        if (guests <= 0) {
            throw new InvalidGuestsException();
        }
        this.contactPerson = contactPerson;
        this.guests = guests;
        this.breakfastIncluded = breakfastIncluded;
        this.room = room;
    }

    public int getGuests() { return guests; }
    
    public boolean getBreakfastIncluded() { return breakfastIncluded; }
}
```
