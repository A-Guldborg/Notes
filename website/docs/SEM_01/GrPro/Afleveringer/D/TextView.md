---
title: TextView
---

```java
public class TextView {
    protected ICustomerTracker cTracker;
    public TextView(ICustomerTracker cTracker) {
        this.cTracker = cTracker;
    }
    
    public void printToday() {
        System.out.println("Today: " + cTracker.today());
    }
    
    public void printAvgThisWeek() {
        System.out.println("Average this week: " + cTracker.avgThisWeek());
    }
    
    public void printComparedToWeek(int week) {
        double diff = cTracker.comparedToWeek(week);
        if (diff == 0) {
            System.out.println("no difference");
        } else if (diff < 0) {
            System.out.println("Decrease by " + diff + " customers");
        } else {
            System.out.println("Increase by " + diff + " customers");
        }
    }
}
```
