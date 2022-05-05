package model;

import java.util.ArrayList;

/**
 * The type Room list.
 */
public class RoomList
{
  private ArrayList<Room> rooms;

  /**
   * Instantiates a new Room list.
   */
  public RoomList()
  {
    rooms = new ArrayList<>();
  }

  /**
   * Add room.
   *
   * @param room the room
   */
  public void addRoom(Room room)
  {
    rooms.add(room);
  }

  /**
   * Gets room by number.
   *
   * @param roomNumber the room number
   * @return the room by number
   */
  public Room getRoomByNumber(int roomNumber)
  {
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getRoomNumber() == roomNumber)
      {
        return rooms.get(i);
      }
    }
    return null;
  }

  /**
   * Gets room of type.
   *
   * @param type the type
   * @return the room of type
   */
  public ArrayList<Room> getRoomOfType(String type)
  {
    ArrayList<Room> roomsType = new ArrayList<Room>();
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getType().equals(type))
      {
        roomsType.add(rooms.get(i));
      }
    }
    return roomsType;
  }

  /**
   * Gets booked rooms of type.
   *
   * @param type the type
   * @return the booked rooms of type
   */
  public ArrayList<Room> getBookedRoomsOfType(String type)
  {
    ArrayList<Room> roomsType = new ArrayList<Room>();
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getType().equals(type))
      {
        if (rooms.get(i).ifAvailable())
        {
          roomsType.add(rooms.get(i));
        }
      }
    }
    return roomsType;
  }

  /**
   * Gets price of room type.
   *
   * @param type the type
   * @return the price of room type
   */
  public double getPriceOfRoomType(String type)
  {
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getType().equals(type))
      {
        return rooms.get(i).getPrice();
      }
    }
    return 0;
  }
}