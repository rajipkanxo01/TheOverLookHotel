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
    RoomList temp = manager.getAllRooms();
    String room1 = temp.getRoom(2).getRoomNumber();
    //    String room2 = temp.getRoom(0).getRoomNumber();
    String room3 = temp.getRoom(1).getRoomNumber();

    //    manager.createBooking(true,5,false,temp.getRoom(0),"Rajib", "Paudyal", "Horsens", "1234", "Nepal",
    //        LocalDate.now(),LocalDate.now(),LocalDate.now());
    //    manager.createBooking(true,2,false,temp.getRoom(2),"Pramesh", "Shrestha", "Kolding", "5678", "Nepal",
    //        LocalDate.now(),LocalDate.now(),LocalDate.now());
    //    System.out.println(manager.getAllBookings());
    //    manager.createCheckIn("Pramesh", "Shrestha", "Kolding", "5678", "Nepal",
    //                LocalDate.now(),LocalDate.now(),room);
    //    System.out.println(manager.getAllBookings());
    //    manager.createCheckIn("Rajib", "Paudyal", "Horsens", "1234", "Nepal",
    //              LocalDate.now(),LocalDate.now(), room2);
    //    System.out.println(manager.getAllCheckedIn());
    //   System.out.println(manager.searchCheckIn("Rajib", "Paudyal","1234"));

    ArrayList<Guest> guests = new ArrayList<>();
    Guest guest1 = new Guest("Rodrigo", "Reyes", "Norregade", "71889516",
        "French", LocalDate.now(), LocalDate.now(), room3);
    Guest guest2 = new Guest("Pramesh", "Reyes", "Norregade", "71889516",
        "French", LocalDate.now(), LocalDate.now(), room3);

    guests.add(guest1);
    guests.add(guest2);
//        manager.createBooking(true, 5, false, temp.getRoom(3), "Rodrigo", "Reyes",
//            "Horsens", "71889516", "Nepal", LocalDate.now(), LocalDate.now(),
//            LocalDate.now());
//    System.out.println( manager.searchBooking("Rodrigo", "Reyes" ,"71889516"));

//            manager.createCheckIn("Rodrigo", "Reyes", "Norregade", "71889516", "French",
//                                LocalDate.now(),LocalDate.now(),room3);
//            manager.createCheckIn("Pramesh", "Reyes", "Norregade", "71889516", "French",
//                LocalDate.now(),LocalDate.now(),room3);

   manager.createCheckOut(room3);

    System.out.println(manager.getAllCheckedIn());
//    System.out.println(manager.getAllBookings());

  }
}
