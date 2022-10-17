---
title: Main
---

```java
public class Main {
    public static void main(String[] args) {
        Smurf superDan = new Smurf("Super Dan", 200);
        Village village = new Village("Smurftown", superDan);
        String[] smurfNames = {"Dee", "Fao", "Ery", "Deedee", "Jack"};
        for (String name : smurfNames) {
            village.addSmurf(new Smurf(name));
        }
        village.addSmurf(new Smurf("Dovne Robert", 50));
        village.printVillagers();
        village.sendToWork();
    }
}
```
