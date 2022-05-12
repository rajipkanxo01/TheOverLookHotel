import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelModelManager manager = new HotelModelManager("rooms.bin", "guests.bin",
        "bookings.bin");
    manager.addRooms();
    RoomList allRooms = manager.getAllRooms();
    //    manager.addRooms();
//    System.out.println(manager.getAllRooms());
    manager.createBooking(false, 5, false, allRooms.getRoom(0), "Pramesh",
        "Shrestha", "Kolding" , "1234", "Nepalese" , LocalDate.now(), LocalDate.now(),LocalDate.now());
    System.out.println(manager.getAllBookings());
  }
}
