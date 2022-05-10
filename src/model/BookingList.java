package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BookingList implements Serializable
{
  /**
   * @author Pramesh Shrestha, Rajib Paudyal
   * @version 1.0.0
   */
  private ArrayList<Booking> bookingList;

  // Creating a new ArrayList of Booking objects.
  public BookingList()
  {
    bookingList = new ArrayList<>();
  }

  /**
   * This method returns the booking at the specified index.
   *
   * @param index The index of the booking you want to get.
   * @return The booking at the index specified.
   */
  public Booking getBookingByIndex(int index)
  {
    return bookingList.get(index);
  }

  /**
   * This function adds a booking to the booking list
   *
   * @param booking The booking to be added to the booking list.
   */
  public void addBooking(Booking booking)
  {
    bookingList.add(booking);
  }

  /**
   * This function returns the total number of bookings in the booking list
   *
   * @return The number of bookings in the bookingList.
   */
  public int getTotalNumberOfBookings()
  {
    return bookingList.size();
  }

  public ArrayList<Booking> getAllBookings()
  {
    ArrayList<Booking> allBookings = new ArrayList<>();
    for (int i = 0; i < bookingList.size(); i++)
    {
      allBookings.add(bookingList.get(i));
    }
    return allBookings;
  }

  /**
   * This function returns a booking object if the first name, last name and
   * phone number of the guest matches the booking
   *
   * @param firstName   The first name of the guest
   * @param lastName    The last name of the guest
   * @param phoneNumber The phone number of the guest
   * @return The booking object is being returned.
   */
  public Booking getBooking(String firstName, String lastName,
      String phoneNumber)
  {

    for (int i = 0; i < bookingList.size(); i++)
    {
      if ((bookingList.get(i).getGuest().getFirstName().equals(firstName))
          && (bookingList.get(i).getGuest().getLastName().equals(lastName))
          && (bookingList.get(i).getGuest().getPhone().equals(phoneNumber)))
      {
        return bookingList.get(i);
      }
    }
    return null;
  }

  /**
   * The toString() function returns a string representation of the object
   *
   * @return The bookingList is being returned.
   */
  public String toString()
  {
    return "bookingList=" + bookingList + "\n";
  }

}
