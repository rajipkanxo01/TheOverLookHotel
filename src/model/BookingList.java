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
   * Remove a booking from the booking list.
   *
   * @param booking The booking to be removed from the booking list.
   */
  public void removeBooking(Booking booking)
  {
    bookingList.remove(booking);
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
