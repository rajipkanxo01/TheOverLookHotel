package model;

public class Room
{
  private double price;
  private String type;
  private boolean smoking;
  private boolean available;
  private String roomNumber;

  public Room(double price, String type, boolean smoking, boolean available, String roomNumber)
  {
    this.price = price;
    this.type = type;
    this.smoking = smoking;
    this.available = available;
    this.roomNumber = roomNumber;

  }

  public void setRoomNumber(String roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  public String getRoomNumber()
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
