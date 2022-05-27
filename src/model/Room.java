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
   * instantiates the room
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
  public boolean isSmoking()
  {
    return smoking;
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
    bookStartDate = LocalDate.MIN;
    bookEndDate = LocalDate.MIN;
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

//  public boolean isAvailable()
//  {
//    return available;
//  }

  /**
   * This function returns the bookStartDate
   *
   * @return The bookStartDate is being returned.
   */
  public LocalDate getBookStartDate()
  {
    return bookStartDate;
  }

  /**
   * This function returns the bookEndDate
   *
   * @return The bookEndDate is being returned.
   */
  public LocalDate getBookEndDate()
  {
    return bookEndDate;
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
   * The toString() method returns a string representation of the object
   *
   * @return The room number, price, smoking status, and availability.
   */
  public String toString()
  {
    return "Room{" + "price=" + price + ", smoking=" + smoking + ", available="
        + available + ", roomNumber='" + roomNumber + "}\n";
  }


}
