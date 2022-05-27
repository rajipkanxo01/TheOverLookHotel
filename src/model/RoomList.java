package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that is used to add into an Arraylist all the rooms
 *
 * @author Rodrigo Reyes
 * @version 1.0.0
 */
public class RoomList implements Serializable
{
  private ArrayList<Room> rooms;

  /**
   * Constructor that instantiates a new Room list
   */
  public RoomList()
  {
    rooms = new ArrayList<>();
  }

  /**
   * Add room object of type room.
   *
   * @param room the object room
   */
  public void addRoom(Room room)
  {
    rooms.add(room);
  }



  /**
   * Gets the rooms of that type.
   *
   * @param type the type of the room
   * @return the rooms of that type
   */
  public ArrayList<Room> getRoomsOfType(String type)
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
   * Gets booked rooms of type provided.
   *
   * @param type the type of the room
   * @return the booked rooms of the type given
   */
  public ArrayList<Room> getBookedRoomsOfType(String type,
      LocalDate arrivalDate, LocalDate departureDate)
  {
    ArrayList<Room> roomsType = new ArrayList<Room>();
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getType().equals(type))
      {
        if (rooms.get(i).ifAvailable(arrivalDate, departureDate))
        {
          roomsType.add(rooms.get(i));
        }
      }
    }
    return roomsType;
  }



  /**
   * This function returns the total number of rooms in the hotel.
   *
   * @return The number of rooms in the hotel.
   */
  public int getTotalNumberOfRooms()
  {
    return rooms.size();
  }


  /**
   * This function returns the room at the given index of rooms list.
   *
   * @param index The index of the room you want to get.
   * @return The room at the given index.
   */
  public Room getRoom(int index)
  {
    return rooms.get(index);
  }

  /**
   * Return the room with the given room number, or null if no such room exists.
   *
   * @param roomNumber The room number of the room you want to get.
   * @return The room object that matches the room number.
   */
  public Room getRoomByRoomNumber(String roomNumber)
  {
    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getRoomNumber().equals(roomNumber))
      {
        return rooms.get(i);
      }
    }
    return null;
  }

  @Override public String toString()
  {
    return "RoomList{" + "rooms=" + rooms + '}';
  }
}