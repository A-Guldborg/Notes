public interface ICustomerTracker {
    int today();
    double avgThisWeek();
    double comparedToWeek(int week);
}
