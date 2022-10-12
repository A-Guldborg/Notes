public class Room {
    private int floor;
    private int room;
    private boolean booked;

    public Room(int floor, int room) {
        this.floor = floor;
        this.room = room;
        booked = false;
    }

    public boolean getBooked() { return booked; }

    public void setBooked() { booked = true; }
}