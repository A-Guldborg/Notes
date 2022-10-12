import java.util.ArrayList;

public class EmployeeOverview {
    private ArrayList<Employee> Employees;

    public EmployeeOverview() {
        Employees = new ArrayList<Employee>();
    }

    /* Show employee info */
    public void display() {
        for (Employee e : Employees) {
            e.display();
            System.out.println();
        }
    }

    public void addEmployee(Employee e) {
        Employees.add(e);
    }
}
