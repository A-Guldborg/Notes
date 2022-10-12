public class Employee {
    protected String name;
    protected double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public void display() {
        System.out.println(" ** EMPLOYEE INFO ** ");
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}