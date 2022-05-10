import model.*;

import java.time.LocalDate;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelModelManager manager = new HotelModelManager("rooms.bin", "guests.bin",
        "bookings.bin");
    manager.addRooms();
    RoomList temp = manager.getAllRooms();
    manager.createBooking(false, 2, false, temp.getRoom(3),
        new Guest("Rajib", "Paudyal", "Horsens", "1234", "Nepal",
            LocalDate.now()), LocalDate.now(), LocalDate.now());

    manager.createBooking(false, 2, false, temp.getRoom(3),
        new Guest("Pramesh", "Shrestha", "Horsens", "1234", "Nepal",
            LocalDate.now()), LocalDate.now(), LocalDate.now());



    System.out.println(manager.getAllBookings());


  }
}
