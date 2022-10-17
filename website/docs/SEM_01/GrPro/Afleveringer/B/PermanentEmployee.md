---
title: PermanentEmployee
sidebar-position: 6
---

```java
import java.util.ArrayList;

public class PermanentEmployee extends Employee {
    protected ArrayList<String> projects;
    
    PermanentEmployee(String name, double salary) {
        super(name, salary);
        this.projects = new ArrayList<String>();
    }
    
    /* Show employee info */
    public void display() {
        super.display();
        if (projects.size() > 0) {
            System.out.println("Current projects: ");
            for(String p : projects) {
                System.out.println("- " + p);
            }
        }
    }
    
    /* Add new project to the project list. */
    public void addProject(String project) {
        projects.add(project);
    }
}
```
