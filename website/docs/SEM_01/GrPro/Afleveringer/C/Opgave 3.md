---
title: Opgaver
---

## 3.1 Forestil dig, at du skriver en metode, der skal skrive data ind i en fil på din computer. Du vil gerne fejlsikre dit program og bruger en try-catch i din kode. Ville du gribe en checked eller unchecked exception, hvis filen ikke kan findes?

En unchecked exception da dette vil være en runtime error (vi kan ikke vide, når vi laver programmet, om den efterspurgte fil findes på den korrekte relative filplacering ved programkørsel)

## 3.2 Forestil dig, at du skriver koden for et spørgeskema, som spørger om brugerens alder. Din kode er ikke helt god, da den ikke tjekker, om brugeren indtaster tal eller bogstaver.  Ville en checked eller unchecked exception opstå, hvis brugeren indtaster bogstaver?

En unchecked exception da vi igen arbejder i runtime. (Det afhænger af brugerens input der først sker under programkørsel)

## 3.3 Se på nedenstående kode

```java
try {
    // some code
} catch (Exception e) {
    System.out.println("The program encountered an exception.");
} catch (FileSystemException e) {
    System.out.println("You do not have access to this file.");
}
```

Koden kan compile, men der er noget galt i strukturen. Hvad er "fejlen"?

Exception vil blive "grebet" først, da denne kommer først i koden.  
Da FileSystemException er en unchecked exception vil denne altså ikke kunne kastes før programkørslen, og da enhver exception er subklasse (el. subsubklasse) til Exception, vil catch (Exception e) altså gribe enhver exception.
