---
title: Citizen
---

```java
public class Citizen {
    private String name;
    private int age;
    private boolean ill;
    private VaccinePassport pass;
    
    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
        this.ill = false;
        this.pass = new VaccinePassport();
    }
    
    public void setIll(boolean b) {
        ill = b;
    }
    
    public void setIll() {
        ill = !ill;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public boolean isIll() {
        return ill;
    }
    
    public void display() {
        String temp;
        if (ill) {
            temp = "syg";
        } else {
            temp = "rask";
        }
        System.out.println(this.name + ", (" + this.age + ") - (" + temp + ")");
    }
    
    public void giveVaccine(Vaccine vaccine) {
        if (isIll()) {
            System.out.println("Afvist - En syg borger må ikke vaccineres");
        } else {
            pass.addVaccine(vaccine);
        }
    }
    
    public int getMissingDoses() {
        return pass.getNumberofMissingJabs();
    }
    
    public boolean isVaccinated() {
        return pass.isVaccinationComplete();
    }
    
    public void showPassport() {
        pass.show();
    }
}
```
