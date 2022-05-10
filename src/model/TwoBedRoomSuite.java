package model;

import java.io.Serializable;

/**
 * A class that is used to create a new Room of type TwoBedRoomSuite that inherits the methods of Room
 * @author Rodrigo Reyes
 * @version 1.0.0
 */
public class TwoBedRoomSuite extends Room implements Serializable
{
  /**
   * Instantiates a new Two Bed Room Suite.
   *
   * @param price      the price of the room
   * @param smoking    if smoking area needed
   * @param available  the availability of the room
   * @param roomNumber the room number
   * @param type       the type of the room
   */
  public TwoBedRoomSuite(double price, boolean smoking, boolean available,
      String roomNumber, String type)
  {
    super(price, smoking, available, roomNumber,type);
  }

  public void setType(String type)
  {
    super.setType(type);
  }
  public String getType()
  {
    return super.getType();
  }
}

