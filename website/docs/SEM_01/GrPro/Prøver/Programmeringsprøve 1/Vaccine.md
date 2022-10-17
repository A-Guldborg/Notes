---
title: Vaccine
---

```java
/**
 * @author Carl Clampenkoder
 */
public class Vaccine 
{
    private String type;
    private int nJabs;
    private String id;

    // Constructor
    public Vaccine(String type, int nJabs, String id){
        this.type = type;
        this.id = id;
        this.nJabs = nJabs;   
    }
    // Returnerer det unikke ID for vaccinen
    public String getId(){
        return id;
    }
    // Returnerer typen (navnet) af vaccinen
    public String getType(){
        return type;
    }
    // Returnerer antallet af vacciner som skal 
    // til for at faerdiggoere vaccine programmet.
    public int getRequiredJabs(){
        return nJabs;
    }
}
```
