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

    Guest guest1 = new Guest("Rajib", "Paudyal", "Horsens", "1234", "Nepal",
        LocalDate.now());
    Guest guest2 = new Guest("Pramesh", "Shrestha", "Kolding", "5678", "Nepal",
        LocalDate.now());

//    manager.createBooking(true,5,false,temp.getRoom(0),guest1,LocalDate.now(),LocalDate.now());
//    manager.createBooking(true,2,false,temp.getRoom(2),guest2,LocalDate.now(),LocalDate.now());

    System.out.println(manager.searchBooking("Rajib","Paudyal","1234"));


  }
}
