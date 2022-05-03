package model;

/**
 * The type Room.
 */
public class Room
{
  private double price;
  private boolean smoking;
  private boolean available;
  private int roomNumber;

  /**
   * Instantiates a new Room.
   *
   * @param price      the price
   * @param smoking    the smoking
   * @param available  the available
   * @param roomNumber the room number
   */
  public Room(double price, boolean smoking, boolean available, int roomNumber)
  {
    this.price = price;
    this.smoking = smoking;
    this.available = available;
    this.roomNumber = roomNumber;

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
   * Gets room number.
   *
   * @return the room number
   */
  public int getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * Gets price.
   *
   * @return the price
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * If smoking boolean.
   *
   * @return boolean as true or false if smoking or not
   */
  public boolean ifSmoking()
  {
    return smoking;
  }

  /**
   * Sets smoking.
   *
   * @param smoking the smoking
   */
  public void setSmoking(Boolean smoking)
  {
    this.smoking = smoking;
  }

  /**
   * Change availability.
   *
   * @param available the available
   */
  public void changeAvailability(Boolean available)
  {
    this.available = available;
  }

  /**
   * If available boolean.
   *
   * @return the boolean
   */
  public boolean ifAvailable()
  {
    return available;
  }




}
