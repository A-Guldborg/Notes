public class RoomAlreadyBookedException extends Exception {
    private int floor;
    private int room;
    public RoomAlreadyBookedException(int requestedFloor, int requestedRoom) {
        super("Room is already booked.");
        this.floor = requestedFloor;
        this.room = requestedRoom;
    }
    
    public String requestedRoom() {
        return "Requested room: floor " + floor + ", room " + room;
    }
}
