---
title: Municipality
---

```java
import java.util.*;
/**
 * @author Carl Clampenkoder
 */
public class Municipality
{
    // Felter
    private String name; 
    private HashSet<Citizen> citizens;

    // Contructor
    public Municipality(String name)
    {
        this.name=name;
        this.citizens = new HashSet<Citizen>();
    }

    public void addCitizen(Citizen cit) {
        citizens.add(cit);
    }

    public boolean findCitizen(Citizen cit) {
        return citizens.contains(cit);
    }

    public void displayCitizen(Citizen cit) {
        if (findCitizen(cit)) {
            cit.display();
            cit.showPassport();
        } else {
            System.out.println("Borger ikke i kommunen!");
        }
    }

    public void printCitizens(int number) {
        System.out.println("Liste af borgere der mangler maksimalt " + number + "stik:");
        Iterator<Citizen> it = citizens.iterator();
        while (it.hasNext()) {
            Citizen citizen = it.next();
            if (citizen.getMissingDoses() >= 0 && citizen.getMissingDoses() <= number) {
                citizen.display();
                citizen.showPassport();
            }
        }
    }

    public void displayStatistics() {  
        int v = 0;
        int h = 0;
        int e = 0;        
        for(Citizen cit:citizens){
            if (cit.isVaccinated()){
                v ++;
            }else if (!cit.isIll()){
                h ++;
            }else {
                e ++;
            }
        }
        if (v == citizens.size() && citizens.size() != 0){
            System.out.println("Hurra! - Alle borgere i kommunen er vaccineret");
        } else {
            System.out.println("Kommunen ("+name+") har "
                +citizens.size()+" borgere");

            System.out.println(v+" ud af "+citizens.size()
                +" borgere i "+name+" er vaccineret!");

            System.out.println(h +" ud af "+citizens.size()
                +" borgere i "+name+" er ikke vaccineret!"); 

            System.out.println(e +" ud af "+citizens.size()
                +" borgere i "+name+" kan ikke vaccineres!"); 
        }
    }
    
    public void sortedVaccination(int minAge, int maxAge, Vaccine vac) {
        Iterator<Citizen> it = citizens.iterator();
        while (it.hasNext()) {
            Citizen citizen = it.next();
            if (citizen.getAge() <= maxAge && citizen.getAge() >= minAge && !citizen.isVaccinated()) {
                citizen.giveVaccine(vac);
            }
        }
    }
    
    public void cureAllSick() {
        Iterator<Citizen> it = citizens.iterator();
        while (it.hasNext()) {
            Citizen citizen = it.next();
            citizen.setIll(false);
        }
    }
}
```
