
package model;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utils.MyFileHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * this class deals with everything related to files writing and GUI
 * @author Pramesh Shrestha, Rajib Paudyal, Rodrigo Reyes
 * @version 1.0.0
 */

public class HotelModelManager implements Serializable
{

  private String roomFileName;
  private String guestFileName;
  private String bookingFileName;

  /**
   * instantiates the hotel model manager class
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

  // -------------------------- room starts from here ------------------------------

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
          new Room(256, false, true, "SBS-C" + i, "Single Bedroom Suite",
              LocalDate.MIN, LocalDate.MIN));
    }

    // adding 3-Single Bedroom Suite
    rooms.addRoom(new Room(339, true, true, "3SBS-C4", "3-Single Bedroom Suite",
        LocalDate.MIN, LocalDate.MIN));

    // adding 2-Single Bedroom Suite
    rooms.addRoom(new Room(399, true, true, "2SBS-C5", "2-Single Bedroom Suite",
        LocalDate.MIN, LocalDate.MIN));

    // adding single rooms to first floor(A)
    for (int i = 1; i <= 10; i++)
    {
      rooms.addRoom(
          new Room(129, false, true, "SR-A" + i, "Single Room", LocalDate.MIN,
              LocalDate.MIN));
    }

    // adding double rooms to first floor(A)
    for (int i = 11; i <= 18; i++)
    {
      rooms.addRoom(
          new Room(169, false, true, "DR-A" + i, "Double Room", LocalDate.MIN,
              LocalDate.MIN));
    }

    // adding double rooms to second floor (B)
    for (int i = 19; i <= 37; i++)
    {
      rooms.addRoom(
          new Room(169, false, true, "DR-B" + i, "Double Room", LocalDate.MIN,
              LocalDate.MIN));
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
      LocalDate departureDate, boolean smoking)
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


  /**
   * This function takes in a RoomList object and writes it to a binary file
   *
   * @param rooms The RoomList object that contains all the rooms.
   */
  public void updateRoom(RoomList rooms)
  {
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
      System.err.println("IO Exception Error");
    }
  }

  // updates availability status of room in binary file

  /**
   * This function updates the availability of a room by changing the
   * availability of the room in the room list
   *
   * @param roomNumber    The room number of the room to be updated
   * @param arrivalDate   The date the guest is arriving
   * @param departureDate The date the guest is leaving the hotel
   */
  public void updateRoomAvailable(String roomNumber, LocalDate arrivalDate,
      LocalDate departureDate)
  {
    RoomList roomList = getAllRooms();
    roomList.getRoomByRoomNumber(roomNumber)
        .changeAvailability(arrivalDate, departureDate);
    try
    {
      MyFileHandler.writeToBinaryFile(roomFileName, roomList);
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

  // -------------------------- create booking starts from here ------------------------------

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
    // create guest object
    Guest guest = new Guest(firstName, lastName, address, phone, nationality,
        dateOfBirth);

    // creating booking object , adding it to booking object and save it to file
    Booking booking = new Booking(extraBed, numberOfGuest, smoking, room, guest,
        arrivalDate, departureDate);
    BookingList bookingList = getAllBookings();
    bookingList.addBooking(booking);

    // change availability status of room
    updateRoomAvailable(room.getRoomNumber(), arrivalDate, departureDate);

    // updates booking in booking file name
    updateBooking(bookingList);
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
   * It deletes a booking by searching for it in the booking list, then changing
   * the availability of the room to true, and then updating the booking list and
   * room list in the binary files
   *
   * @param firstName the first name of the guest
   * @param lastName  The last name of the guest
   * @param phone     String
   */
  public void deleteBookings(String firstName, String lastName, String phone)
  {
    // get all bookings stored in file and search for booking according to first name, last name and phone number
    BookingList allBookings = getAllBookings(); // this takes 1
    Booking searchedBooking = searchBooking(firstName, lastName,
        phone); // this runs O(n) times as we call function searchBooking which run n times
    RoomList allRooms = getAllRooms(); // this takes 1

    // changes availability status of room
    allRooms.getRoomByRoomNumber(searchedBooking.getRoomNumber())
        .changeAvailabilityAtCheckOut(); // this runs O(n) times as we call function getRoomByRoomNumber which runs n times

    BookingList bookings = new BookingList(); // this take 1
    for (int i = 0; i
        < allBookings.getTotalNumberOfBookings(); i++) // this runs O(n) times
    {
      if (!(allBookings.getBookingByIndex(i).getGuest().getFirstName()
          .equals(firstName) && allBookings.getBookingByIndex(i).getGuest()
          .getLastName().equals(lastName) && allBookings.getBookingByIndex(i)
          .getGuest().getPhone()
          .equals(phone))) // this comparison runs O(n) times
      {
        bookings.addBooking(
            allBookings.getBookingByIndex(i)); // this runs O(n-1) times
      }
    }

    // updates room and booking in binary file
    updateRoom(allRooms); // this takes 1
    updateBooking(bookings); // this takes 1

    // We have no recursion, so we do not need base case
    // T(n) = 1+n+1+n+1+n+n+(n-1)+1+1 = 4+5n
    // So, ignoring all constants, we get time complexity of,
    // T(n) = O(n)
    // We choose this method since it calls another function which uses for loop
  }

  // updates booking in the file

  /**
   * This updates the booking to the binary file
   *
   * @param booking The booking object to be updated.
   */
  public void updateBooking(BookingList booking)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(bookingFileName, booking);
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

  // -------------------------- check in  starts from here ------------------------------

  // search for booking
  /**
   * Search booking from booking list using first name, last name and phone number.
   *
   * @param firstName The first name of the guest
   * @param lastName The last name of the guest.
   * @param phoneNumber The phone number of the guest.
   * @return A booking object.
   */
  public Booking searchBooking(String firstName, String lastName,
      String phoneNumber)
  {
    BookingList bookingList = getAllBookings();

    // search booking from booking list using first name, last name and phone number
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
      LocalDate checkInDate, LocalDate checkOutDate, String roomNumber,
      boolean smoking)
  {
    GuestList guests = getAllCheckedIn();
    guests.addGuest(
        new Guest(firstName, lastName, address, phone, nationality, dateOfBirth,
            checkInDate, checkOutDate, roomNumber, smoking));

    updateGuest(guests);
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

    // search guest from guest list
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

  /**
   * It removes a guest from the list of checked in guests
   *
   * @param roomNumber The room number of the guest to be removed.
   */
  public void removeCheckIn(String roomNumber)
  {
    GuestList allGuests = getAllCheckedIn();
    GuestList newGuestList = new GuestList();
    /* search guest that are not in the Guest list and creates a new list to
    remove those that already have a check-in*/
    for (int i = 0; i < allGuests.getNumberOfGuest(); i++)
    {
      if (!(allGuests.getGuestByIndex(i).getRoomNumber().equals(roomNumber)))
      {
        newGuestList.addGuest(allGuests.getGuestByIndex(i));
      }
    }
    updateCheckIn(newGuestList);
  }

  /**
   * This function takes in a guest list and writes it to the guest file
   *
   * @param guestList The guest list object that contains the updated guest list.
   */
  public void updateCheckIn(GuestList guestList)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(guestFileName, guestList);
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

  /**
   * This function returns the room number of the guest who is checked in
   *
   * @param roomNumber The room number of the guest you want to display.
   * @return The room number of the guest.
   */
  public String displayCheckInsByRoomNumber(String roomNumber)
  {
    GuestList allGuests = getAllCheckedIn();
    for (int i = 0; i < allGuests.getNumberOfGuest(); i++)
    {
      if (allGuests.getGuestByIndex(i).getRoomNumber().equals(roomNumber))
      {
        return allGuests.getGuestByIndex(i).getRoomNumber();
      }
    }
    return null;
  }

  // -------------------------- check out starts from here ------------------------------

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
   * @param smoking         if smoking or not
   * @return The price of the room after the discount has been applied.
   */
  public double calculatePrice(LocalDate arrivalDate, LocalDate departureDate,
      String roomNumber, double discountPercent, boolean smoking)
  {
    // Calculates date interval and gets the price
    DateInterval dateInterval = new DateInterval(arrivalDate,
        departureDate); // this takes 1
    int numberOfNights = dateInterval.getNumberOfNight(arrivalDate,
        departureDate); // this takes 1
    RoomList allRooms = getAllRooms(); // this takes 1
    double price = allRooms.getRoomByRoomNumber(roomNumber)
        .getPrice(); // this runs O(n) times as we call getRoomByRoom Number which runs n times
    double initialPrice = numberOfNights * price; // this takes 1
    double smokingFee = 25; // this takes 1

    // adds smoking fee if it is true
    if (!smoking)
    {
      return initialPrice - ((initialPrice) * ((discountPercent)
          / 100)); // this takes 1
    }
    else
    {
      return (initialPrice + smokingFee) - ((initialPrice) * ((discountPercent)
          / 100)); // this also takes 1 if above condition doesn't execute
    }
    // We have no recursion, so we do need base case
    // T(n) = 1+1+1+n+1+1+1 = 6 + n
    // So, ignoring all coefficients, we get,
    // T(n) = O(n)
    // We choose this method since it calls another function which uses for loop
  }

  /**
   * Calculate number of nights the guest stayed.
   *
   * @param arrivalDate   The date the guest is arriving
   * @param departureDate The date the guest is leaving the hotel.
   * @return numberOfNights The Number of Nights the guest stayed
   */
  public int calculateNumberOfNights(LocalDate arrivalDate,
      LocalDate departureDate)
  {
    DateInterval tempDate = new DateInterval(arrivalDate, departureDate);
    int numberOfNights = tempDate.getNumberOfNight(arrivalDate, departureDate);
    return numberOfNights;
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
    GuestList guests = getAllCheckedIn();
    guests.addGuest(guest);

    updateGuest(guests);
  }

  // update guest in the file

  /**
   * This function updates guests to a binary file
   *
   * @param guests The guest list to be updated
   */
  public void updateGuest(GuestList guests)
  {
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

  /**
   * It creates an XML file called rooms.xml and writes all the rooms in the hotel
   * to it
   */
  public void exportRoomsToXML()
  {
    try
    {
      RoomList allRooms = getAllRooms();

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.newDocument();

      // root element
      Element rootElement = doc.createElement("RoomList");
      doc.appendChild(rootElement);


      for (int i = 0; i < allRooms.getTotalNumberOfRooms(); i++)
      {
        Room room = allRooms.getRoom(i);

        // rooms element
        Element rooms = doc.createElement("rooms");
        Attr attrType = doc.createAttribute("roomNumber");
        attrType.setValue(room.getRoomNumber());
        rooms.setAttributeNode(attrType);
        rootElement.appendChild(rooms);

        // roomType element
        Element roomType = doc.createElement("roomType");
        roomType.appendChild(doc.createTextNode(room.getType()));
        rooms.appendChild(roomType);


        // bookStartDate element
        Element bookStartDate = doc.createElement("bookStartDate");
        bookStartDate.appendChild(
            doc.createTextNode(String.valueOf(room.getBookStartDate())));
        rooms.appendChild(bookStartDate);

        //bookEndDate element
        Element bookEndDate = doc.createElement("bookEndDate");
        bookEndDate.appendChild(
            doc.createTextNode(String.valueOf(room.getBookEndDate())));
        rooms.appendChild(bookEndDate);
      }


      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(rootElement);
      StreamResult result = new StreamResult(new File("rooms.xml"));
      transformer.transform(source,result);


    }
    catch (ParserConfigurationException e)
    {
      System.err.println("Parsing Error");
    }
    catch (TransformerConfigurationException e)
    {
      System.err.println("Transformer Configuration Error");
    }
    catch (TransformerException e)
    {
      System.err.println("Transformer Exception");
    }

  }

}
