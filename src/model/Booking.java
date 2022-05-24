package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Booking.
 * @author Pramesh Shrestha
 */
public class Booking implements Serializable
{
  private boolean extraBed;
  private int numberOfGuest;
  private boolean smokes;
  private Room room;
  private Guest guest;
  private LocalDate arrivalDate;
  private LocalDate departureDate;
  private String roomNumber;
  private String fullName;

  // This is a constructor. It is used to create an object of the Booking class and initialize the fields.
  public Booking(boolean extraBed, int numberOfGuest, boolean smokes, Room room,
      Guest guest, LocalDate arrivalDate , LocalDate departureDate)
  {
    this.extraBed = extraBed;
    this.numberOfGuest = numberOfGuest;
    this.smokes = smokes;
    this.room = room;
    this.guest = guest;
    this.arrivalDate = arrivalDate;
    this.departureDate = departureDate;
    fullName = guest.getFullName();
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName()
  {
    return guest.getFirstName();
  }

  /**
   * This function returns the last name of the guest.
   *
   * @return The last name of the guest.
   */
  public String getLastName()
  {
    return guest.getLastName();
  }

  /**
   * This function returns the phone number of the guest.
   *
   * @return The phone number of the guest.
   */
  public String getPhone()
  {
    return guest.getPhone();
  }

  /**
   * This function returns the arrival date of the flight
   *
   * @return The arrivalDate variable is being returned.
   */
  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  /**
   * This function returns the departure date of the flight
   *
   * @return The departure date.
   */
  public LocalDate getDepartureDate () {
    return departureDate;
  }

  /**
   * This function gets the room number from the room object and returns it
   *
   * @return The room number of the room object.
   */
  public String getRoomNumber()
  {
    roomNumber = room.getRoomNumber();
    return roomNumber;
  }

  /**
   * The function getFullName() returns the full name of the guest
   *
   * @return The full name of the guest.
   */
  public String getFullName()
  {
    fullName = guest.getFullName();
    return fullName;
  }

  /**
   * This method returns a boolean value that indicates whether or not the guest
   * needs an extra bed
   *
   * @return The boolean value of extraBed.
   */
  public boolean ifExtraBed()
  {
    return extraBed;
  }

  /**
   * Returns true if the person smokes, false otherwise.
   *
   * @return The boolean value of the smokes variable.
   */
  public boolean ifSmokes()
  {
    return smokes;
  }


  /**
   * This method returns the number of guests that are expected to check in
   *
   * @return The number of guests.
   */
  public int getNumberOfGuest()
  {
    return numberOfGuest;
  }

  /**

  /**
   * This method returns the room that is booked.
   *
   * @return The room object.
   */
  public Room getRoom()
  {
    return room;
  }

  /**
   * This method returns the guest variable.
   *
   * @return The Guest object.
   */
  public Guest getGuest()
  {
    return guest;
  }



  /**
   * The `toString()` function is a function that is automatically called when you
   * try to print an object
   *
   * @return The toString method is being returned.
   */
  @Override public String toString()
  {
    return "extraBed=" + extraBed + ", numberOfGuest="
        + numberOfGuest + ", smokes=" + smokes + ", room=" + room + ", guest="
        + guest + "\n";
  }

  /**
   * If the object is not a Booking, return false. Otherwise, compare the extraBed,
   * numberOfGuest, smokes, room and guest fields
   *
   * @param obj The object to compare with.
   * @return The hashcode of the object.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Booking))
    {
      return false;
    }
    Booking others = (Booking) obj;
    return extraBed == others.extraBed && numberOfGuest == others.numberOfGuest
        && smokes == others.smokes && room.equals(others.room) && guest.equals(
        others.guest);
  }
}
