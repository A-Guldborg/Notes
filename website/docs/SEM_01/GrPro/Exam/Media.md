---
title: Media
---

```java
public abstract class Media {
    private String title;
    private int year;

    public Media(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
    
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
```
