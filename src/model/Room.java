package model;


public class Room
{
  private double price;
  private boolean smoking;
  private boolean available;
  private int roomNumber;


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

  public int getRoomNumber()
  {
    return roomNumber;
  }

  public double getPrice()
  {
    return price;
  }

  public boolean ifSmoking()
  {
    return smoking;
  }

  public void setSmoking(Boolean smoking)
  {
    this.smoking = smoking;
  }

  public void changeAvailability(Boolean available)
  {
    this.available = available;
  }

  public boolean ifAvailable()
  {
    return available;
  }




}
