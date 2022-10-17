---
title: Opgaver
sidebar-position: 0
---

## Navn

Andreas Guldborg Hansen

## Opgave 2.3

```java
Employee e;
if (isFriday()) {
    e = new MonthlySalaryEmployee (...) ;
} else {
    e = new HourlySalaryEmployee (...) ;
}
```

I compile time vil e's type være superklassen Employee.  
I runtime afhænger e's type af ugedagen. I tilfælde af onsdag er e en HourlySalaryEmployee.

## Opgave 2.4

Ved e.display() for en HourlySalaryEmployee køres dens egen display() metode. Denne metode kalder dog også Employee's display() metode.
