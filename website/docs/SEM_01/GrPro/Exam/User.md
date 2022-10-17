---
title: User
---

```java
import java.util.ArrayList;

public class User {
    ArrayList<Media> medias;

    public User() {
        this.medias = new ArrayList<Media>();
    }

    public void addMediaToList(Media media) {
        this.medias.add(media);
    }

}
```
