import java.util.HashMap;

public class CustomerTracker implements ICustomerTracker {
    protected HashMap<Integer, int[]> customerData;
    protected int currentWeek;
    
    public CustomerTracker(MockDB db, int currentWeek) {
        this.customerData = db.getCustomerData();
        this.currentWeek = currentWeek;
    }
    
    public int today() {
        int[] data = customerData.get(currentWeek);
        return data[data.length-1];
    }
    
    private double avgWeek(int week) {
        int[] data = customerData.get(week);
        double sum = 0.0;
        for (int day : data) {
            sum += day;
        }
        return sum / data.length;
    }
    
    public double avgThisWeek() {
        return avgWeek(currentWeek);
    }
    
    public double comparedToWeek(int week) {
        return avgThisWeek() - avgWeek(week);
    }
}