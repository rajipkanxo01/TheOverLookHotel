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
    return rooms.get(roomNumber);
  }

}
