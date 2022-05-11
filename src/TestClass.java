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
    Room room1 = manager.getAllRooms().getRoom(0);
    //    manager.createBooking(false, 2, true, room1, "Pramesh", "Shrestha",
    //        "Kolding", "420" , "Danish" , LocalDate.parse("2019-01-01") , LocalDate.now() , LocalDate.parse("2022-05-12"));
    //    manager.getAllBookings();
    //    System.out.println(manager.searchBooking("Pramesh" , "Shrestha" , "420"));
    //    manager.createCheckIn("Pramesh", "Shrestha", "Kolding", "420", "Danish",
    //        LocalDate.parse("2019-01-01"), LocalDate.now(), room1.getRoomNumber());
    //    manager.createCheckIn("Rodrigo", "Reyes", "Horsens", "404", "Nepali",
    //        LocalDate.parse("2014-01-01"), LocalDate.now(), room1.getRoomNumber());

    //    System.out.println(manager.searchCheckIn("Pramesh" , "Shrestha" , "420"));
    //    System.out.println(manager.getAllCheckedIn());

    manager.createCheckOut(room1.getRoomNumber());
    System.out.println(
        manager.calculatePrice(LocalDate.now(), LocalDate.parse("2022-05-12"),
            room1.getRoomNumber(), 5 ));
  }
}
