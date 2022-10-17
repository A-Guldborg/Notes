---
title: TestVaccine
---

```java
public class TestVaccine
{
    public void testVaccineInit ()
    {
        Vaccine vac1 = new Vaccine("Cerveza",15, "CR2021abc");
        Vaccine vac2 = new Vaccine("Modelo",200, "CR2021abc");
        Vaccine vac3 = new Vaccine("Sol",40, "CR2021abc");
        int counter = 0;
        
        if (vac1.getType() == "Cerveza" && vac1.getRequiredJabs() == 15){
            System.out.println("Test1 - OK");
            counter ++;
            
        } else {
            System.out.println("Fejl - Du har ikke debugget koden korrekt!");
        }
        
        if (vac2.getType() == "Modelo" && vac2.getRequiredJabs() == 200){
            System.out.println("Test2 - OK");
            counter ++;
            
        } else {
            System.out.println("Fejl - Du har ikke debugget koden korrekt!");
        }
        
        if (vac3.getType() == "Sol" && vac3.getRequiredJabs() == 40){
            System.out.println("Test3 - OK");
            counter ++;
            
        } else {
            System.out.println("Fejl - Du har ikke debugget koden korrekt!");
        }
        
        if (counter == 3){
            System.out.println("Det ser ud til koden er debugget korrekt!");
        }
    }
}
```
