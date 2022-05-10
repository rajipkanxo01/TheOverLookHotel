import model.Guest;
import model.HotelModelManager;
import model.RoomList;

import java.time.LocalDate;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelModelManager manager = new HotelModelManager("rooms.bin" , "guests.bin");
    manager.addRooms();
//    RoomList temp = manager.getAllRooms();
    System.out.println(manager.getAllAvailableRooms(LocalDate.now(),LocalDate.parse("2022-05-15")));
    manager.addGuest(new Guest("first name" , "last name" , "address", "12" , "nepal" ,
        LocalDate.parse("2002-03-08")));
    System.out.println(LocalDate.parse("2002-03-08"));;
  }
}
