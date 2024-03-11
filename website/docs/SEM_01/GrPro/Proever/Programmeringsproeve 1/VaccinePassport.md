---
title: VaccinePassport
---

```java
public class VaccinePassport {
    private Vaccine[] vaccineProgress;
    private int nextFreeVaccine;
    
    public VaccinePassport() {
        nextFreeVaccine = 0;
    }
    
    public void addVaccine(Vaccine vac) {
        if (nextFreeVaccine == 0) {
            vaccineProgress = new Vaccine[vac.getRequiredJabs()];
            vaccineProgress[nextFreeVaccine++] = vac;
        } else if (isVaccinationComplete()) {
            System.out.println("Alle vacciner givet!");
        } else if (vaccineProgress[0].getType() != vac.getType()) {
            System.out.println(vac.getType() + " må ikke bruges, når " + vaccineProgress[0].getType() + " allerede er igangsat.");
        } else {
            vaccineProgress[nextFreeVaccine++] = vac;
        }
    }
    
    public int getNumberofMissingJabs() {
        if (nextFreeVaccine == 0) {
            return -1;
        } else {
            return vaccineProgress[0].getRequiredJabs() - nextFreeVaccine;
        }
    }
    
    public void show() {
        if (nextFreeVaccine == 0) {
            System.out.println("Vaccination endnu ikke begyndt!");
        } else if (isVaccinationComplete()) {
            System.out.println("Vaccine " + vaccineProgress[0].getType() + ": Komplet");
        } else {
            System.out.println("Vaccine " + vaccineProgress[0].getType() + ": Undervejs");
            String out = "[ ";
            for (int i = 0; i < vaccineProgress.length; i++) {
                if (vaccineProgress[i] != null) {
                    out += vaccineProgress[i].getId() + " ";
                } else {
                    out += "- ";
                }
            }
            out += "]";
            System.out.println(out);
        }
    }
    
    public boolean isVaccinationComplete() {
        if (vaccineProgress != null) {
            return nextFreeVaccine == vaccineProgress.length;
        } else {
            return false;
        }
    }
}
```
