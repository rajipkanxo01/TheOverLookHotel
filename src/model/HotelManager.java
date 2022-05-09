package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HotelManager
{
  private String roomFileName;
  private String guestFileName;

  public HotelManager(String roomFileName, String guestFileName)
  {
    this.roomFileName = roomFileName;
    this.guestFileName = guestFileName;
  }

  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();
    try
    {
      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(roomFileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allRooms;
  }
}
