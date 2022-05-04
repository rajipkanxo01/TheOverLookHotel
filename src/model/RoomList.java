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
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getRoomNumber() == roomNumber)
      {
        return rooms.get(i);
      }
    }
    return null;
  }

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