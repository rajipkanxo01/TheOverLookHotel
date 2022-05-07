package model;

public class Booking
{
  private boolean extraBed;
  private int numberOfGuest;
  private boolean smokes;
  private Room room;
  private Guest guest;
  private DateInterval dateInterval;



  // This is a constructor. It is used to create an object of the Booking class and initialize the fields.
  public Booking(boolean extraBed, int numberOfGuest, boolean smokes, Room room,
      Guest guest, DateInterval dateInterval)
  {
    this.extraBed = extraBed;
    this.numberOfGuest = numberOfGuest;
    this.smokes = smokes;
    this.room = room;
    this.guest = guest;
    this.dateInterval = dateInterval;
  }

  /**
   * > This method returns a boolean value that indicates whether or not the guest
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
   * This method sets the room of the current object to the room passed in as a
   * parameter.
   * @param room The Room object.
   */
  public void setRoom(Room room)
  {
    this.room = room;
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
  public void setGuest(Guest guest)
  {
    this.guest = guest;
  }

  /**
   * Returns the date interval of this event.
   *
   * @return The dateInterval variable is being returned.
   */
  public DateInterval getDateInterval()
  {
    return dateInterval;
  }

  /**
   * Sets the date interval for the report
   * @param dateInterval The date interval to use for the report.
   */
  public void setDateInterval(DateInterval dateInterval)
  {
    this.dateInterval = dateInterval;
  }

}
