package model;

import java.util.ArrayList;

public class RoomList
{
  private ArrayList<Room> rooms;

  public RoomList()
  {
    rooms = new ArrayList<>();
  }

  public void addRoom(Room room)
  {
    rooms.add(room);
  }

  public Room getRoomByNumber(int roomNumber)
  {
    for (int i = 0; i <rooms.size() ; i++)
    {
      if (rooms.get(i).getRoomNumber() == roomNumber)
      {
        return rooms.get(i);
      }
    }
    return null;
  }

  public RoomList getRoomOfType(String type)
  {
    for (int i = 0; i <rooms.size() ; i++)
    {
      if (rooms.get(i).ge)
      {
      }
    }
  }
}
