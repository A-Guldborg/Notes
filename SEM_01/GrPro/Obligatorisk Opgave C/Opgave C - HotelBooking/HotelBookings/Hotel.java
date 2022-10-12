import java.util.ArrayList;

public class Hotel {
    private String name;
    private Room[][] rooms;
    private ArrayList<Booking> bookings;

    public Hotel(String name, int floors, int roomsPerFloor) {
        this.name = name;
        rooms = buildHotel(floors, roomsPerFloor);
        bookings = new ArrayList<>();
    }

    /* Tilføjer en ny booking til listen bookings. */
    public void addBooking(String contact, int guests, boolean breakfast, int requestedFloor, int requestedRoomNo) {
        try {
            Room requestedRoom = rooms[requestedFloor][requestedRoomNo];
            bookings.add(new Booking(contact, guests, breakfast, requestedRoom));
            requestedRoom.setBooked();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Room does not exist");
        } catch (InvalidGuestsException e) {
            // Checked exception
            System.out.println(e.getMessage());
        }
    }

    /* Udregner det gennemsnitlige antal gæster per booking. */
    public double avgGuests() {
        int sum = 0;
        for (Booking b : bookings) {
            sum += b.getGuests();
        }
        try {
            return sum / bookings.size();
        } catch (ArithmeticException e) {
            System.out.println("Error: No bookings");
            return sum;
        }
    }

    /* Hjælpemetode til constructoren.
    Opretter Room-objekter, som den tilføjer til rooms. */
    private Room[][] buildHotel(int floors, int roomsPerFloor) {
        Room[][] hotel = new Room[floors][roomsPerFloor];
        for (int i = 0; i < hotel.length; i++) {
            for (int j = 0; j < hotel[i].length; j++) {
                hotel[i][j] = new Room(i, j);
            }
        }
        return hotel;
    }

    /** SKRIV DIN KODE HERUNDER */
    public void checkAddBooking(String contact, int guests, boolean breakfast, int requestedFloor, int requestedRoomNo) throws RoomAlreadyBookedException {
        if (rooms[requestedFloor][requestedRoomNo].getBooked()) {
            throw new RoomAlreadyBookedException(requestedFloor, requestedRoomNo);
        } else {
            addBooking(contact, guests, breakfast, requestedFloor, requestedRoomNo);
        }
    }

    public void showHotel() {
        StringBuilder out = new StringBuilder(); // For speed
        for (int i = rooms.length - 1; i >= 0; i--) {
            // Starter ved length - 1 for at gennemgå array'et bagfra. 
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j].getBooked()) {
                    out.append("[X]");
                } else {
                    out.append("[ ]");
                }
            }
            out.append("\n"); // newline
        }
        System.out.print(out);
    }

    public double breakfastFactor() {
        double totalRooms = 0; // For ikke at få compile error deklareres feltet uden for try catch
        try {
            // double variabel da vi dermed kan dividere uden integer division
            totalRooms = rooms.length * rooms[0].length;
        } catch (IndexOutOfBoundsException e) {
            // Såfremt der ikke er nogle etager har vi altså ingen værelser. 
            // Derfor sikrer vi også, at vi ikke får en ArithmeticException
            // nederst i metoden
            return 0;
        }
        int roomsWithBreakfast = 0;
        for (Booking b : bookings) {
            if (b.getBreakfastIncluded()) {
                roomsWithBreakfast++;
            }
        }
        return roomsWithBreakfast / totalRooms;
    }
}