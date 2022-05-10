package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class that is used to store the price,smoking area, availability, type of the room and availability of the room.
 *
 * @author Rodrigo Reyes
 * @version 1.0.0
 */
public class Room implements Serializable
{
  private double price;
  private boolean smoking;
  private boolean available;
  private String roomNumber;
  private String type;
  private LocalDate bookStartDate;
  private LocalDate bookEndDate;

  /**
   * Instantiates a new Room.
   *
   * @param price      the price of the room
   * @param smoking    whether the room has a smoking area
   * @param available  the availability of the room
   * @param roomNumber the room number
   */
  public Room(double price, boolean smoking, boolean available,
      String roomNumber, String type)
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
  public void setRoomNumber(String roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  /**
   * Gets the room number.
   *
   * @return the room number
   */
  public String getRoomNumber()
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
   * This function changes the availability of a room
   *
   * @param available     true if the room is available, false if it is not
   * @param arrivalDate   The date the guest will arrive
   * @param departureDate The date the guest is leaving the hotel
   */
  public void changeAvailability(Boolean available, LocalDate arrivalDate,
      LocalDate departureDate)
  {
    bookEndDate = departureDate;
    bookStartDate = departureDate;
    this.available = available;

  }

  /**
   * If the arrival date is after the end date of the booking, return false.
   * Otherwise, return true
   *
   * @param arrivalDate   The date the user wants to arrive
   * @param departureDate The date the guest is leaving
   * @return A boolean value.
   */
  public boolean ifAvailable(LocalDate arrivalDate, LocalDate departureDate)
  {
    if (available)
    {
      return true;
    }
    else
    {
      if ((arrivalDate.isAfter(bookEndDate)))
      {
        return false;
      }
      else
      {
        return true;
      }
    }
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

  public String toString()
  {
    return "Room{" + "price=" + price + ", smoking=" + smoking + ", available="
        + available + ", roomNumber='" + roomNumber + '\'' + ", type='" + type
        + '\'' + ", bookStartDate=" + bookStartDate + ", bookEndDate="
        + bookEndDate + "}\n";
  }
}
