import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelModelManager manager = new HotelModelManager("rooms.bin", "guests.bin",
        "bookings.bin");

//    manager.addRooms();
//    System.out.println(manager.getAllBookings());

//    System.out.println(manager.getAllRooms().getRoomByRoomNumber("3SBS-C4"));
  }
}
