package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class HotelModelManager implements Serializable
{
  /**
   * @author Pramesh Shrestha, Rajib Paudyal, Rodtigo Reyes
   * @version 1.0.0
   */
  private String roomFileName;
  private String guestFileName;

  public HotelModelManager(String roomFileName, String guestFileName)
  {
    this.roomFileName = roomFileName;
    this.guestFileName = guestFileName;
  }

  /**
   * This method reads the binary file containing all the rooms and returns a
   * RoomList object containing all the rooms
   *
   * @return A RoomList object
   */
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();
    try
    {
      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(roomFileName);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File not found");
    }
    catch (IOException e)
    {
      System.err.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println("Class Not Found");
    }
    return allRooms;
  }

  /**
   * This function adds all the rooms to the room list and then writes the room
   * list to the room file
   */
  public void addRooms()
  {
    RoomList rooms = new RoomList();


    // adding 3 single bedroom suite
    for (int i = 1; i <= 3; i++)
    {
      rooms.addRoom(
          new Room(256, false, true, "SBS-C" + i, "Single Bedroom Suite"));
    }

    // adding 3-Single Bedroom Suite
    rooms.addRoom(
        new Room(339, true, true, "3SBS-C4", "3-Single Bedroom Suite"));

    // adding 2-Single Bedroom Suite
    rooms.addRoom(
        new Room(399, true, true, "2SBS-C5", "2-Single Bedroom Suite"));

    // adding single rooms to first floor(A)
    for (int i = 1; i <= 10; i++)
    {
      rooms.addRoom(new Room(129, false, true, "SR-A" + i, "Single Room"));
    }

    // adding double rooms to first floor(A)
    for (int i = 11; i <= 18; i++)
    {
      rooms.addRoom(new Room(169, false, true, "DR-A" + i, "Double Room"));
    }

    // adding double rooms to second floor (B)
    for (int i = 19; i <= 37; i++)
    {
      rooms.addRoom(new Room(169, false, true, "DR-B" + i, "Double Room"));
    }

    // adding room object to file
    try
    {
      MyFileHandler.writeToBinaryFile(roomFileName, rooms);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * This function adds a guest to the guest list and writes to the binary file
   *
   * @param guest The guest object to be added to the guest list.
   */
  public void addGuest(Guest guest)
  {
    GuestList guests = new GuestList();
    guests.addGuest(guest);
    try
    {
      MyFileHandler.writeToBinaryFile(guestFileName, guests);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      System.err.println("IO Exception Error");
    }
  }

  public RoomList getAllAvailableRooms(LocalDate arrivalDate,
      LocalDate departureDate)
  {
    RoomList allRooms = getAllRooms();
    RoomList allAvailableRooms = new RoomList();
    for (int i = 0; i < allRooms.getTotalNumberOfRooms(); i++)
    {
      if (allRooms.getRoom(i).ifAvailable(arrivalDate, departureDate))
      {
        allAvailableRooms.addRoom(allRooms.getRoom(i));

      }
    }
    return allAvailableRooms;
  }

}
