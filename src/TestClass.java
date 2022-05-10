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
        new Guest("Raji", "Paudyal", "Horsens", "1234", "Nepal", LocalDate.now()),
        LocalDate.now(), LocalDate.now());

    Booking abc = manager.searchBooking("Rajib","Paudyal", "1234");
    System.out.println(abc.getRoom().getRoomNumber() + " , " + abc.getGuest().getFirstName() + " " + abc.getGuest().getLastName() + abc.  ) ;
  }
}
