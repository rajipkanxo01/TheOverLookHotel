package model;

/**
 * A class that is used to store the price,smoking area, availability, type of the room and availability of the room.
 * @author Rodrigo Reyes
 * @version 1.0.0
 */
public class Room
{
  private double price;
  private boolean smoking;
  private boolean available;
  private int roomNumber;
  private String type;

  /**
   * Instantiates a new Room.
   *
   * @param price      the price of the room
   * @param smoking    whether the room has a smoking area
   * @param available  the availability of the room
   * @param roomNumber the room number
   */
  public Room(double price, boolean smoking, boolean available, int roomNumber,String type)
  {
    this.price = price;
    this.smoking = smoking;
    this.available = available;
    this.roomNumber = roomNumber;
    this.type = type;
  }

  /**
   * Sets room number.
   *
   * @param roomNumber the room number
   */
  public void setRoomNumber(int roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  /**
   * Gets the room number.
   *
   * @return the room number
   */
  public int getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * Gets the price of the room.
   *
   * @return the price of the room.
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * If room has smoking area.
   *
   * @return boolean as true or false if smoking or not
   */
  public boolean ifSmoking()
  {
    return smoking;
  }

  /**
   * Sets if room needs smoking area.
   *
   * @param smoking the smoking area
   */
  public void setSmoking(Boolean smoking)
  {
    this.smoking = smoking;
  }

  /**
   * Change the availability.
   *
   * @param available the availability of the room
   */
  public void changeAvailability(Boolean available)
  {
    this.available = available;
  }

  /**
   * Gets the availability of the room.
   *
   * @return true or false depending on the availability of the room
   */
  public boolean ifAvailable()
  {
    return available;
  }

  /**
   * Gets the  type of the room.
   *
   * @return the type of the room
   */
  public String getType()
  {
    return type;
  }

  /**
   * Sets the type of the room.
   *
   * @param type returns the type of the room
   */
  public void setType(String type)
  {
    this.type = type;
  }


}
