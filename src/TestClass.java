import model.*;

import java.time.LocalDate;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelModelManager manager = new HotelModelManager("rooms.bin", "guests.bin",
        "bookings.bin");

        manager.addRooms();
//        RoomList roomList = manager.getAllRooms();
//    Room room = roomList.getRoom(1);
//    manager.createBooking(false, 5, false, room, "Pramesh", "Shrestha",
//        "Kolding", "1234", "Nepali", LocalDate.parse("2000-05-08"),
//        LocalDate.parse("2022-05-20"), LocalDate.parse("2022-05-29"));
//    manager.updateRoomAvailable(room.getRoomNumber(),LocalDate.now(),LocalDate.now());
//        System.out.println(manager.getAllBookings());
    //    System.out.println(manager.getAllCheckedIn());
//    System.out.println(manager.getAllRooms());
//    System.out.println(manager.getAllBookings());
//    System.out.println(manager.getAllCheckedIn().getGuestByIndex(0).getCheckInDate());
    //    System.out.println(manager.getAllRooms().getRoomByRoomNumber("3SBS-C4"));
//    System.out.println(manager.getAllRooms());
//
  }
}
