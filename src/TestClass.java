import model.*;

import java.time.LocalDate;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelModelManager manager = new HotelModelManager("rooms.bin", "guests.bin",
        "bookings.bin");

    manager.addRooms();
    RoomList roomList = manager.getAllRooms();
    Room room1 = roomList.getRoom(1);
    Room room2 = roomList.getRoom(2);

//        manager.createBooking(false,5,false,room1, "Pramesh","Shrestha", "Kolding","1234","Nepali",
//            LocalDate.parse("2000-05-08"), LocalDate.parse("2022-05-20"), LocalDate.parse("2022-05-29"));
//
//        manager.createBooking(false,5,false,room2, "Rajib","Shrestha", "Kolding","12345","Nepali",
//            LocalDate.parse("2000-05-08"), LocalDate.parse("2022-05-20"), LocalDate.parse("2022-05-29"));
//
//        manager.createBooking(false,5,false,room2, "Rodrigo","Shrestha", "Kolding","123456","Nepali",
//            LocalDate.parse("2000-05-08"), LocalDate.parse("2022-05-20"), LocalDate.parse("2022-05-29"));
    //    System.out.println(manager.getAllBookings());
    //    System.out.println(manager.getAllCheckedIn());
    //
    //    System.out.println(manager.getAllRooms().getRoomByRoomNumber("3SBS-C4"));
  }

}
