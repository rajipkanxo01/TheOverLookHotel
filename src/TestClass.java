import model.HotelManager;

public class TestClass
{
  public static void main(String[] args)
  {
    HotelManager manager = new HotelManager("rooms.bin" , "guests.bin");
    manager.addRooms();
    System.out.println(manager.getAllRooms());
  }
}
