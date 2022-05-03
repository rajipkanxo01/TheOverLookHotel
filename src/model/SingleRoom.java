package model;

public class SingleRoom extends Room
{
  private String type;

  public SingleRoom(double price, boolean smoking, boolean available,
      int roomNumber, String type)
  {
    super(price, smoking, available, roomNumber);
    this.type = type;
  }

  public void setType(String type)
  {
    this.type = type;
  }
  public String getType()
  {
    return type;
  }
}


