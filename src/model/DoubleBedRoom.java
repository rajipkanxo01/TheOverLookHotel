package model;

public class DoubleBedRoom extends Room
{
  /**
   * Instantiates a new Single room.
   *
   * @param price      the price
   * @param smoking    the smoking
   * @param available  the available
   * @param roomNumber the room number
   * @param type       the type
   */
  public DoubleBedRoom(double price, boolean smoking, boolean available,
      int roomNumber, String type)
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

