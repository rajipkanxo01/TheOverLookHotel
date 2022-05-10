package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable
{
  private boolean extraBed;
  private int numberOfGuest;
  private boolean smokes;
  private Room room;
  private Guest guest;
  private DateInterval dateInterval;
  private LocalDate arrivalDate;



  // This is a constructor. It is used to create an object of the Booking class and initialize the fields.
  public Booking(boolean extraBed, int numberOfGuest, boolean smokes, Room room,
      Guest guest, DateInterval dateInterval)
  {
    this.extraBed = extraBed;
    this.numberOfGuest = numberOfGuest;
    this.smokes = smokes;
    this.room = room;
    this.guest = guest;

  }

  /**
   *  This method returns a boolean value that indicates whether or not the guest
   * needs an extra bed
   * @return The boolean value of extraBed.
   */
  public boolean ifExtraBed()
  {
    return extraBed;
  }


  /**
   * This method returns the number of guests that are expected to check in
   * @return The number of guests.
   */
  public int getNumberOfGuest()
  {
    return numberOfGuest;
  }

  /**
   * This method sets the number of guests that are expected to check in for the reservation
   * @param numberOfGuest The number of guests expected.
   */
  public void setNumberOfGuest(int numberOfGuest)
  {
    this.numberOfGuest = numberOfGuest;
  }

  /**
   * This method returns true if the guest smokes, and false if the guest does
   * not smoke.
   * @return The boolean value of the smokes variable.
   */
  public boolean ifGuestSmokes()
  {
    return smokes;
  }


  /**
   * This method returns the room that is booked.
   * @return The room object.
   */
  public Room getRoom()
  {
    return room;
  }


  /**
   * This method returns the guest variable.
   * @return The Guest object.
   */
  public Guest getGuest()
  {
    return guest;
  }


  /**
   * This method sets the guest variable to the guest variable passed in.
   * @param guest The guest object
   */
  public void changeGuest(Guest guest)
  {
    this.guest = guest;
  }

  @Override public String toString()
  {
    return "Booking{" + "extraBed=" + extraBed + ", numberOfGuest="
        + numberOfGuest + ", smokes=" + smokes + ", room=" + room + ", guest="
        + guest + ", dateInterval=" + dateInterval + ", arrivalDate="
        + arrivalDate + '}';
  }
}
