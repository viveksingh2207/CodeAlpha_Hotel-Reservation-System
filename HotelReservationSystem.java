import java.util.*;

class Room {
    int roomId;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomId, String category, double price) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId + ", Category: " + category + ", Price: $" + price + ", Available: " + isAvailable;
    }
}

class Booking {
    int bookingId;
    String customerName;
    Room room;
    String checkInDate;
    String checkOutDate;
    boolean paymentStatus;

    public Booking(int bookingId, String customerName, Room room, String checkInDate, String checkOutDate) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.paymentStatus = false;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Customer: " + customerName + ", Room: [" + room + 
               "], Check-In: " + checkInDate + ", Check-Out: " + checkOutDate + 
               ", Payment: " + (paymentStatus ? "Completed" : "Pending");
    }
}

public class HotelReservationSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int bookingCounter = 1;

    public HotelReservationSystem() {
        // Initialize sample rooms
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(103, "Suite", 300.0));
        rooms.add(new Room(104, "Double", 150.0));
    }

    public void searchRooms(String category) {
        System.out.println("Available rooms in category: " + category);
        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    public void bookRoom(String customerName, int roomId, String checkInDate, String checkOutDate) {
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomId == roomId && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available!");
            return;
        }

        Booking booking = new Booking(bookingCounter++, customerName, selectedRoom, checkInDate, checkOutDate);
        bookings.add(booking);
        selectedRoom.isAvailable = false;

        System.out.println("Room booked successfully! Booking details:");
        System.out.println(booking);
    }

    public void viewBookings() {
        System.out.println("All Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void processPayment(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.bookingId == bookingId) {
                if (booking.paymentStatus) {
                    System.out.println("Payment already completed for this booking!");
                } else {
                    booking.paymentStatus = true;
                    System.out.println("Payment successful for Booking ID: " + bookingId);
                }
                return;
            }
        }
        System.out.println("Booking ID not found!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelReservationSystem system = new HotelReservationSystem();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single/Double/Suite): ");
                    String category = scanner.next();
                    system.searchRooms(category);
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.next();
                    System.out.print("Enter room ID to book: ");
                    int roomId = scanner.nextInt();
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    String checkIn = scanner.next();
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    String checkOut = scanner.next();
                    system.bookRoom(name, roomId, checkIn, checkOut);
                    break;

                case 3:
                    system.viewBookings();
                    break;

                case 4:
                    System.out.print("Enter Booking ID to process payment: ");
                    int bookingId = scanner.nextInt();
                    system.processPayment(bookingId);
                    break;

                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}

