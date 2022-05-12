package model;

import java.io.Serializable;
import java.sql.Struct;
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
    this.available = true;
    this.roomNumber = roomNumber;
    this.type = type;
  }

  /**
   * initializes the room
   *
   * @param price         the price of the room
   * @param smoking       whether the room has a smoking area
   * @param available     the availability of the room
   * @param roomNumber    the room number
   * @param type          type of room
   * @param bookStartDate room booked start date
   * @param bookEndDate   room booked end date
   */

  public Room(double price, boolean smoking, boolean available,
      String roomNumber, String type, LocalDate bookStartDate,
      LocalDate bookEndDate)
  {
    this.price = price;
    this.smoking = smoking;
    this.available = available;
    this.roomNumber = roomNumber;
    this.type = type;
    this.bookStartDate = bookStartDate;
    this.bookEndDate = bookEndDate;
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
   * @param arrivalDate   The date the guest will arrive
   * @param departureDate The date the guest is leaving the hotel
   */
  public void changeAvailability(LocalDate arrivalDate, LocalDate departureDate)
  {
    if (available)
    {
      bookEndDate = departureDate;
      bookStartDate = arrivalDate;
      available = false;
    }
    else
    {
      available = true;
    }
  }

  /**
   * This function changes the value of the variable available to true.
   */
  public void changeAvailabilityAtCheckOut()
  {
    available = true;
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
    if ((arrivalDate.isAfter(bookEndDate)))
    {
      available = true;
    }
    else
    {
      available = false;
    }
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

  public String toString()
  {
    return "Room{" + "price=" + price + ", smoking=" + smoking + ", available="
        + available + ", roomNumber='" + roomNumber + '\'' + ", type='" + type
        + '\'' + ", bookStartDate=" + bookStartDate + ", bookEndDate="
        + bookEndDate + "}\n";
  }
}
