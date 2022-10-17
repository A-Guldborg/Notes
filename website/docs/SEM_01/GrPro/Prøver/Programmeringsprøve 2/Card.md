---
title: Card
---

```java
public abstract class Card implements Comparable<Card> {
    protected String suit;
    public Card (String suit) {
        this.suit = suit;
    }
    public abstract int getValue();
    public int compareTo(Card other) {
        if (this.getValue() == other.getValue()) {
            return 0;
        } else if (this.getValue() > other.getValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
```
