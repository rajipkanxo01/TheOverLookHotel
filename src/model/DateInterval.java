package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * class that is used to store the arrival and departure
 * dates of a guest.
 *
 * @author Rajib Paudyal
 * @version 1.0.0
 */
public class DateInterval implements Serializable
{


  private LocalDate arrivalDate;
  private LocalDate departureDate;

  /**
   * instantiates the date interval
   *
   * @param arrivalDate   arrival date of guest
   * @param departureDate departure date of guest
   */
  public DateInterval(LocalDate arrivalDate, LocalDate departureDate)
  {
    this.arrivalDate = arrivalDate;
    this.departureDate = departureDate;
  }

  /**
   * Get the number of nights between two dates.
   *
   * @param arrivalDate   The date the guest arrives at the hotel.
   * @param departureDate The date the guest is leaving the hotel.
   * @return The number of nights between the arrival date and the departure date.
   */
  public int getNumberOfNight(LocalDate arrivalDate, LocalDate departureDate)
  {
    return (int) (ChronoUnit.DAYS.between(arrivalDate, departureDate));
  }

  /**
   * The toString() method returns a string representation of DateInterval
   *
   * @return The arrival date and departure date.
   */
  public String toString()
  {
    return "Arrival Date: " + arrivalDate + " ,Departure Date: "
        + departureDate;
  }

  /**
   * If the object is not a DateInterval, returns false. Otherwise, compare the
   * arrival and departure dates of the two DateIntervals
   *
   * @param obj The object to compare with.
   * @return boolean
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof DateInterval))
    {
      return false;
    }
    DateInterval others = (DateInterval) obj;
    return arrivalDate.equals(others.arrivalDate) && departureDate.equals(
        others.departureDate);
  }
}
