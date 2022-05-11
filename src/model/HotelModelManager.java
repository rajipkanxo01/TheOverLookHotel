package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class HotelModelManager implements Serializable
{
  /**
   * @author Pramesh Shrestha, Rajib Paudyal, Rodrigo Reyes
   * @version 1.0.0
   */
  private String roomFileName;
  private String guestFileName;
  private String bookingFileName;

  /**
   * initializes the hotel model manager class
   *
   * @param roomFileName    file name for room.bin
   * @param guestFileName   file name for guests.bin
   * @param bookingFileName file name for bookings.bin
   */
  public HotelModelManager(String roomFileName, String guestFileName,
      String bookingFileName)
  {
    this.roomFileName = roomFileName;
    this.guestFileName = guestFileName;
    this.bookingFileName = bookingFileName;
  }

  // Room status methods starts from here

  // get All Rooms

  /**
   * This method reads the binary file containing all the rooms and returns a
   * RoomList object containing all the rooms
   *
   * @return A RoomList object
   */
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();
    try
    {
      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(roomFileName);

    }
    catch (FileNotFoundException e)
    {
      System.err.println("File not found");
    }
    catch (IOException e)
    {
      System.err.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println("Class Not Found");
    }
    return allRooms;
  }

  // add Rooms

  /**
   * This function adds all the rooms to the room list and then writes the room
   * list to the room file
   */
  public void addRooms()
  {
    RoomList rooms = new RoomList();

    // adding 3 single bedroom suite
    for (int i = 1; i <= 3; i++)
    {
      rooms.addRoom(
          new Room(256, false, true, "SBS-C" + i, "Single Bedroom Suite"));
    }

    // adding 3-Single Bedroom Suite
    rooms.addRoom(
        new Room(339, true, true, "3SBS-C4", "3-Single Bedroom Suite"));

    // adding 2-Single Bedroom Suite
    rooms.addRoom(
        new Room(399, true, true, "2SBS-C5", "2-Single Bedroom Suite"));

    // adding single rooms to first floor(A)
    for (int i = 1; i <= 10; i++)
    {
      rooms.addRoom(new Room(129, false, true, "SR-A" + i, "Single Room"));
    }

    // adding double rooms to first floor(A)
    for (int i = 11; i <= 18; i++)
    {
      rooms.addRoom(new Room(169, false, true, "DR-A" + i, "Double Room"));
    }

    // adding double rooms to second floor (B)
    for (int i = 19; i <= 37; i++)
    {
      rooms.addRoom(new Room(169, false, true, "DR-B" + i, "Double Room"));
    }

    // adding room object to file
    try
    {
      MyFileHandler.writeToBinaryFile(roomFileName, rooms);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  // update room in file
  //  public void updateRoom(Room room)
  //  {
  //    RoomList allRooms = getAllRooms();
  //    try
  //    {
  //      MyFileHandler.writeToBinaryFile(roomFileName, room);
  //    }
  //    catch (FileNotFoundException e)
  //    {
  //      System.err.println("File Not Found");
  //    }
  //    catch (IOException e)
  //    {
  //      System.err.println("IO Exception Error");
  //    }
  //  }

  // get All Available Rooms

  /**
   * "Get all the rooms that are available for the given arrival and departure
   * dates."
   * The method calls getAllAvailableRooms. It takes two parameters:
   * arrivalDate and departureDate. It returns a RoomList object
   *
   * @param arrivalDate   The date the guest is arriving
   * @param departureDate The date the guest is leaving the hotel.
   * @return A list of all available rooms.
   */
  public RoomList getAllAvailableRooms(LocalDate arrivalDate,
      LocalDate departureDate)
  {
    RoomList allRooms = getAllRooms();
    RoomList allAvailableRooms = new RoomList();
    for (int i = 0; i < allRooms.getTotalNumberOfRooms(); i++)
    {
      if (allRooms.getRoom(i).ifAvailable(arrivalDate, departureDate))
      {
        allAvailableRooms.addRoom(allRooms.getRoom(i));
      }
    }
    return allAvailableRooms;
  }

  // getBookedRooms

  /**
   * Get all the rooms that are booked between the given dates.
   *
   * @param arrivalDate   The date the guest is arriving
   * @param departureDate The date the guest is leaving the hotel.
   * @return A list of all the rooms that are booked.
   */
  public RoomList getBookedRooms(LocalDate arrivalDate, LocalDate departureDate)
  {
    RoomList allRooms = getAllRooms();
    RoomList allBookedRooms = new RoomList();
    for (int i = 0; i < allRooms.getTotalNumberOfRooms(); i++)
    {
      if (!(allRooms.getRoom(i).ifAvailable(arrivalDate, departureDate)))
      {
        allBookedRooms.addRoom(allRooms.getRoom(i));

      }
    }
    return allBookedRooms;
  }

  // create booking tabs starts from here

  //create booking

  /**
   * It creates a new booking and adds it to the list of bookings
   *
   * @param extraBed      If the guest needs an extra bed.
   * @param numberOfGuest The number of guests in the booking.
   * @param smoking       If the guest needs smoking room
   * @param room          The room that the guest wants to book.
   * @param firstName     The first name of the guest
   * @param lastName      The last name of the guest
   * @param address       The address of the guest
   * @param phone         The phone number of the guest
   * @param nationality   The nationality of the guest
   * @param dateOfBirth   The date of birth of the guest.
   * @param arrivalDate   The date the guest arrives
   * @param departureDate The date the guest is leaving the hotel.
   */
  public void createBooking(boolean extraBed, int numberOfGuest,
      boolean smoking, Room room, String firstName, String lastName,
      String address, String phone, String nationality, LocalDate dateOfBirth,
      LocalDate arrivalDate, LocalDate departureDate)
  {
    Guest guest = new Guest(firstName, lastName, address, phone, nationality,
        dateOfBirth);
    DateInterval dateInterval = new DateInterval(arrivalDate, departureDate);
    RoomList allRooms = getAllRooms();

    Booking booking = new Booking(extraBed, numberOfGuest, smoking, room, guest,
        dateInterval);
    BookingList bookingList = getAllBookings();
    bookingList.addBooking(booking);
    allRooms.getRoomByRoomNumber(room.getRoomNumber())
        .changeAvailability(arrivalDate, departureDate);

    try
    {
      MyFileHandler.writeToBinaryFile(bookingFileName, bookingList);
      MyFileHandler.writeToBinaryFile(roomFileName,allRooms);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
  }


  //getAllBookings

  /**
   * It reads the binary file containing all the bookings and returns the list of
   * bookings
   *
   * @return A BookingList object.
   */
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();
    try
    {
      allBookings = (BookingList) MyFileHandler.readFromBinaryFile(
          bookingFileName);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File not found");
    }
    catch (IOException e)
    {
      System.err.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println("Class Not Found");
    }
    return allBookings;
  }

  // removes the booking according to first name , last name , phone

  /**
   * Delete a booking from the list of bookings.
   *
   * @param firstName The first name of the customer
   * @param lastName  The last name of the person who made the booking.
   * @param phone     The phone number of the customer
   */
  public void deleteBookings(String firstName, String lastName, String phone)
  {
    BookingList allBookings = getAllBookings();
    allBookings.removeBooking(searchBooking(firstName, lastName, phone));

    try
    {
      MyFileHandler.writeToBinaryFile(bookingFileName, allBookings);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      System.err.println("IO Exception Error");
    }
  }



  // check-In tab methods starts from here


  // search for booking
  public Booking searchBooking(String firstName, String lastName,
      String phoneNumber)
  {
    BookingList bookingList = getAllBookings();
    for (int i = 0; i < bookingList.getTotalNumberOfBookings(); i++)
    {
      if (bookingList.getBookingByIndex(i).getGuest().getFirstName()
          .equals(firstName) && bookingList.getBookingByIndex(i).getGuest()
          .getLastName().equals(lastName) && bookingList.getBookingByIndex(i)
          .getGuest().getPhone().equals(phoneNumber))
      {
        return bookingList.getBookingByIndex(i);
      }
    }
    return null;
  }


  //Create-Check-In

  /**
   * "Create a new check-in by adding a new guest to the guest list."
   * This method creates a check in and add it to the guest file.
   *
   * @param firstName   The first name of the guest
   * @param lastName    The last name of the guest.
   * @param address     The address of the guest.
   * @param phone       String
   * @param nationality String
   * @param dateOfBirth The date of birth of the guest.
   * @param checkInDate The date the guest checked in.
   * @param roomNumber  The room number of the room the guest is checking into.
   */
  public void createCheckIn(String firstName, String lastName, String address,
      String phone, String nationality, LocalDate dateOfBirth,
      LocalDate checkInDate, String roomNumber)
  {
    GuestList guests = getAllCheckedIn();
    guests.addGuest(
        new Guest(firstName, lastName, address, phone, nationality, dateOfBirth,
            checkInDate, roomNumber));
    try
    {
      MyFileHandler.writeToBinaryFile(guestFileName, guests);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      System.err.println("IO Exception Error");
    }
  }

  //allCheckedIns

  /**
   * This function reads the guest file and returns a list of all guests who are
   * checked in
   *
   * @return A list of all the guests that are checked in.
   */
  public GuestList getAllCheckedIn()
  {
    BookingList bookingList = new BookingList();
    GuestList checkedIn = new GuestList();
    try
    {
      checkedIn = (GuestList) MyFileHandler.readFromBinaryFile(guestFileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }

    return checkedIn;
  }


  // search check in

  /**
   * Search for a check made by guest using first name, last name, and phone number, and return the
   * guest if found, otherwise return null.
   *
   * @param firstName   The first name of the guest
   * @param lastName    The last name of the guest.
   * @param phoneNumber The phone number of the guest.
   * @return A guest object.
   */
  public Guest searchCheckIn(String firstName, String lastName,
      String phoneNumber)
  {
    GuestList allGuests = getAllCheckedIn();
    for (int i = 0; i < allGuests.getNumberOfGuest(); i++)
    {
      if (allGuests.getGuestByIndex(i).getFirstName().equals(firstName)
          && allGuests.getGuestByIndex(i).getLastName().equals(lastName)
          && allGuests.getGuestByIndex(i).getPhone().equals(phoneNumber))
      {
        return allGuests.getGuestByIndex(i);
      }
    }
    return null;
  }

  //Check-Out methods

  //Create check-out

  /**
   * This function takes in a room number and removes all guests from the guest
   * list that are checked into that room
   *
   * @param roomNumber The room number of the guest that is checking out.
   */
  public void createCheckOut(String roomNumber)
  {
    GuestList guests = getAllCheckedIn();
    RoomList allRoooms = getAllRooms();
    ArrayList<Guest> tempGuest = new ArrayList();
    for (int i = 0; i < guests.getNumberOfGuest(); i++)
    {
      if (guests.getGuestByIndex(i).getRoomNumber().equals(roomNumber))
      {
        tempGuest.add(guests.getGuestByIndex(i));
      }
    }
    allRoooms.getRoomByRoomNumber(roomNumber).changeAvailabilityAtCheckOut();
    guests.removeGuestList(tempGuest);
    try
    {
      MyFileHandler.writeToBinaryFile(guestFileName, guests);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      System.err.println("IO Exception Error");
    }

  }

  // calculate price for nights stayed

  /**
   * "Calculate the price of a room for a given number of nights, given a discount
   * percentage."
   * The function is doing too many things. It's calculating the number of nights,
   * getting the price of the room, and calculating the final price
   *
   * @param arrivalDate     The date the guest is arriving
   * @param departureDate   The date the guest is leaving the hotel.
   * @param roomNumber      The room number of the room that the customer wants to book.
   * @param discountPercent The percentage of the discount.
   * @return The price of the room after the discount has been applied.
   */
  public double calculatePrice(LocalDate arrivalDate, LocalDate departureDate,
      String roomNumber, double discountPercent)
  {
    DateInterval dateInterval = new DateInterval(arrivalDate, departureDate);
    int numberOfNights = dateInterval.getNumberOfNight(arrivalDate,
        departureDate);
    RoomList allRooms = getAllRooms();
    double price = allRooms.getRoomByRoomNumber(roomNumber).getPrice();
    double initialPrice = numberOfNights * price;

    return initialPrice - ((initialPrice) * ((discountPercent) / 100));
  }

  // guest methods

  // add Guest

  /**
   * This function adds a guest to the guest list and writes to the binary file
   *
   * @param guest The guest object to be added to the guest list.
   */
  public void addGuest(Guest guest)
  {
    GuestList guests = new GuestList();
    guests.addGuest(guest);
    try
    {
      MyFileHandler.writeToBinaryFile(guestFileName, guests);
    }
    catch (FileNotFoundException e)
    {
      System.err.println("File Not Found");
    }
    catch (IOException e)
    {
      System.err.println("IO Exception Error");
    }
  }



}
