package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * The type Hotel gui controller.
 *
 * @author Pramesh Shrestha, Rajib Paudyal, Rodrigo Reyes
 * @version 1.0.0
 */
public class HotelGUIController implements Initializable
{

  private HotelModelManager manager = new HotelModelManager("rooms.bin",
      "guests.bin", "bookings.bin");

  // Room status tab private fields
  @FXML private TextField bookingAddress;
  @FXML private DatePicker bookingArrivalDate;
  @FXML private DatePicker bookingDateOfBirth;
  @FXML private DatePicker bookingDepartureDate;
  @FXML private CheckBox bookingExtraBed;
  @FXML private TextField bookingFirstName;
  @FXML private TextField bookingLastName;
  @FXML private TextField bookingNationality;
  @FXML private TextField bookingPhoneNumber;
  @FXML private TextField bookingRoomNumber;
  @FXML private TextField bookingRoomType;
  @FXML private CheckBox bookingSmoking;
  @FXML private Label roomStatusError;

  // Create Booking tab private fields
  @FXML private Tab createBooking;
  @FXML private CheckBox isSmoking;
  @FXML private DatePicker roomArrivalDate;
  @FXML private DatePicker roomDepartureDate;
  @FXML private Tab roomStatus;
  @FXML private TableColumn<Room, String> roomStatusColumnNumber;
  @FXML private TableColumn<Room, Double> roomStatusColumnPrice;
  @FXML private TableColumn<Room, String> roomStatusColumnType;
  @FXML private TableView<Room> roomStatusTableView;
  @FXML private TabPane tabPane;
  @FXML private Spinner<Integer> bookingNumberOfGuest;
  @FXML private Label createBookingError;

  // Check In tab private fields
  @FXML private TextField checkInAddress;
  @FXML private TableColumn<Booking, String> checkInBookedBy;
  @FXML private DatePicker checkInDateOfBirth;
  @FXML private TextField checkInFirstName;
  @FXML private TextField checkInLastName;
  @FXML private TextField checkInNationality;
  @FXML private TableColumn<Booking, String> checkInNumberColumn;
  @FXML private TextField checkInPhoneNumber;
  @FXML private TextField checkInSearchFirstName;
  @FXML private TextField checkInSearchLastName;
  @FXML private TextField checkInSearchPhoneNumber;
  @FXML private DatePicker checkInCheckedInDate;
  @FXML private DatePicker checkInCheckOutDate;
  @FXML private Tab checkInTab;
  @FXML private TableView<Booking> checkInTableView;
  @FXML private ComboBox<String> checkInCombo;
  @FXML private ComboBox<String> checkInRoomNumber;
  @FXML private Button checkInButton;

  // Check Out tab private fields
  @FXML private TableColumn<Guest, LocalDate> checkOutCheckedIn;
  @FXML private TableColumn<Guest, String> checkOutColumnNumber;
  @FXML private TextField checkOutSearchFirstName;
  @FXML private TextField checkOutSearchLastName;
  @FXML private TextField checkOutSearchPhoneNumber;
  @FXML private Tab checkOutTab;
  @FXML private TableView<Guest> checkOutTableView;
  @FXML private DatePicker checkedOutCheckInDate;
  @FXML private DatePicker checkedOutCheckOutDate;
  @FXML private TextField checkedOutDiscountAmount;
  @FXML private TextField checkedOutFinalPrice;
  @FXML private TextField checkedOutInitialPrice;
  @FXML private TextField checkedOutNightsStayed;
  @FXML private TextField checkOutRoomNumber;

  // All Check-In tab private fields
  @FXML private Tab allCheckInsTab;
  @FXML private TableColumn<Guest, LocalDate> allCheckInCheckedInDateColumn;
  @FXML private TableColumn<Guest, LocalDate> allCheckInCheckedOutDateColumn;
  @FXML private TableColumn<Guest, String> allCheckInFirstNameColumn;
  @FXML private TableColumn<Guest, String> allCheckInLastNameColumn;
  @FXML private TableColumn<Guest, String> allCheckInPhoneNumberColumn;
  @FXML private TableColumn<Guest, String> allCheckInRoomNumberColumn;
  @FXML private TableView<Guest> allCheckInTableView;

  //All Bookings tab private fields

  @FXML private TableColumn<Booking, LocalDate> allBookingsArrivalDate;
  @FXML private TableColumn<Booking, LocalDate> allBookingsDepartureDate;
  @FXML private TableColumn<Booking, String> allBookingsFirstName;
  @FXML private TableColumn<Booking, String> allBookingsLastName;
  @FXML private TableColumn<Booking, String> allBookingsPhoneNumber;
  @FXML private TableColumn<Booking, String> allBookingsRoomNumber;
  @FXML private TableView<Booking> allBookingsTableView;

  public void initialize(URL url, ResourceBundle resourceBundle)
  {
    // Configure the spinner with values 1 to 10
    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
        0, 10, 0);
    this.bookingNumberOfGuest.setValueFactory(spinnerValueFactory);

    //This method sets all the rooms to comboBox
    setRoomToComboBox();
    setRoomToCheckInComboBox();
    checkInButton.setDisable(true);
  }
  // -------------------------- room status methods starts from here ------------------------------

  /**
   * The function is called when the user clicks the "Next" button on the "Room"
   * tab. It sets the "Create Booking" tab as the selected tab and sets the arrival
   * and departure dates on the "Create Booking" tab to the arrival and departure
   * dates on the "Room" tab
   *
   * @author Rajib Paudyal
   */
  @FXML private void roomNext()
  {
    if (roomStatusTableView.getSelectionModel().getSelectedItem() != null)
    {
      // change tab from room status to create booking
      SingleSelectionModel<Tab> selectionModelNextButton = tabPane.getSelectionModel();
      selectionModelNextButton.select(createBooking);

      // get data from room status tab and set it to create booking tab
      bookingArrivalDate.setValue(roomArrivalDate.getValue());
      bookingDepartureDate.setValue(roomDepartureDate.getValue());
      bookingRoomType.setText(
          roomStatusTableView.getSelectionModel().getSelectedItem().getType());
      bookingRoomNumber.setText(
          roomStatusTableView.getSelectionModel().getSelectedItem()
              .getRoomNumber());

      roomStatusError.setText("");

      // refresh table and clear everything
      roomArrivalDate.getEditor().clear();
      roomDepartureDate.getEditor().clear();
      isSmoking.setSelected(false);
      roomStatusTableView.getItems().clear();

    }
    else
    {
      roomStatusError.setText("Select any room to continue");
    }
  }

  /**
   * When user clicks a "back" button, the program will switch to "room status" tab
   *
   * @param actionEvent The event that triggered the action.
   */
  @FXML private void createBack(ActionEvent actionEvent)
  {
    // go back to room status tab
    SingleSelectionModel<Tab> selectionModelCreateBackButton = tabPane.getSelectionModel();
    selectionModelCreateBackButton.select(roomStatus);
  }

  /**
   * When the user clicks the "Check In" button, the program will switch to the
   * "Check In" tab.
   *
   * @param actionEvent This is the event that is triggered when the button is
   *                    clicked.
   */
  @FXML private void goToCheckIn(ActionEvent actionEvent)
  {
    // go to check in tab
    SingleSelectionModel<Tab> selectionModelGoToCheckIn = tabPane.getSelectionModel();
    selectionModelGoToCheckIn.select(checkInTab);
  }

  /**
   * This function searches the available room from binary file when search button is clicked
   */
  @FXML void searchAvailableRooms()
  {
    roomStatusColumnPrice.setCellValueFactory(
        new PropertyValueFactory<Room, Double>("price"));
    roomStatusColumnPrice.setStyle("-fx-alignment: CENTER;");

    roomStatusColumnType.setCellValueFactory(
        new PropertyValueFactory<Room, String>("type"));
    roomStatusColumnType.setStyle("-fx-alignment: CENTER;");

    roomStatusColumnNumber.setCellValueFactory(
        new PropertyValueFactory<Room, String>("roomNumber"));
    roomStatusTableView.setItems(getRoom());
    roomStatusTableView.setStyle("-fx-alignment: CENTER;");
    roomStatusTableView.refresh();
  }

  /**
   * If the check-out date is before the check-in date, display an error message
   *
   * @param event The event that triggered the method.
   */
  @FXML private void disableDepartureDate(ActionEvent event)
  {
    if(roomDepartureDate.getValue().isBefore(roomArrivalDate.getValue()))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Invalid Check-out Date");
      alert.setContentText("Please enter a valid check-out date.");
      alert.showAndWait();
      roomDepartureDate.getEditor().clear();
    }

  }

  /**
   * The function intitializeTable() is called when the FXML file is loaded. It
   * sets the cell value factory for each column in the table view to the
   * appropriate property in the Room class
   */


  /**
   * It returns an ObservableList of Room objects that are available for the given
   * arrival and departure dates and smoking preference
   *
   * @return The method is returning an ObservableList of Room objects.
   */
  @FXML private ObservableList<Room> getRoom()
  {

    // getting arrival date, departure date and smoking value from gui
    LocalDate arrivalDate = roomArrivalDate.getValue();
    LocalDate departureDate = roomDepartureDate.getValue();
    boolean smoking = isSmoking.isSelected();

    // search available rooms according to provided data
    RoomList allAvailableRooms = manager.getAllAvailableRooms(arrivalDate,
        departureDate, smoking);
    // adding smoking and non smoking rooms to observable list
    ObservableList<Room> allNonSmokingRooms = FXCollections.observableArrayList();
    ObservableList<Room> allSmokingRooms = FXCollections.observableArrayList();
    for (int i = 0; i < allAvailableRooms.getTotalNumberOfRooms(); i++)
    {
      if (!allAvailableRooms.getRoom(i).ifSmoking())
      {
        allNonSmokingRooms.add(allAvailableRooms.getRoom(i));
      }
      else
      {
        allSmokingRooms.add(allAvailableRooms.getRoom(i));
      }
    }

    // checking if smoking is selected or not
    if (isSmoking.isSelected())
    {
      return allSmokingRooms;
    }
    else
    {
      return allNonSmokingRooms;
    }
  }

  // -------------------------- create booking methods starts from here ------------------------------

  /**
   * It clears all the text fields and the date picker
   *
   * @author Rajib Paudyal
   */
  @FXML private void bookingClear()
  {
    bookingFirstName.clear();
    bookingLastName.clear();
    bookingPhoneNumber.clear();
    bookingAddress.clear();
    bookingNationality.clear();
    bookingDateOfBirth.getEditor().clear();
    bookingNumberOfGuest.getEditor().clear();
    createBookingError.setText("");
    bookingSmoking.setSelected(false);
    bookingExtraBed.setSelected(false);
    bookingArrivalDate.getEditor().clear();
    bookingDepartureDate.getEditor().clear();
    bookingRoomType.clear();
    bookingRoomNumber.clear();
  }

  /**
   * It creates a booking from the information given in the fields
   */
  @FXML private void bookingSave()
  {

    // get information from fields and assign it to the variable
    String bookingFirstNameText = bookingFirstName.getText().trim();
    String bookingLastNameText = bookingLastName.getText().trim();
    String bookingPhoneNumberText = bookingPhoneNumber.getText().trim();
    String bookingNationalityText = bookingNationality.getText().trim();
    String bookingAddressText = bookingAddress.getText().trim();
    LocalDate bookingDateOfBirthValue = bookingDateOfBirth.getValue();
    boolean smokingSelected = bookingSmoking.isSelected();
    boolean extraBedSelected = bookingExtraBed.isSelected();
    Integer numberOfGuestValue = bookingNumberOfGuest.getValue();

    // get room according to the room number given in field
    RoomList allRooms = manager.getAllRooms();
    Room room = allRooms.getRoomByRoomNumber(
        bookingRoomNumber.getText().trim());

    // get arrival and departure date
    LocalDate arrivalDateValue = bookingArrivalDate.getValue();
    LocalDate departureDateValue = bookingDepartureDate.getValue();

    // if any field is empty, gives an error message
    if (bookingFirstNameText.equals("") || bookingLastNameText.equals("")
        || bookingPhoneNumberText.equals("") || bookingNationalityText.equals(
        "") || bookingAddressText.equals("") || numberOfGuestValue == 0)
    {
      createBookingError.setText("Fields can't be empty.");
      //      bookingClear();
    }
    else
    {
      // create booking from the information given above
      manager.createBooking(extraBedSelected, (int) numberOfGuestValue,
          smokingSelected, room, bookingFirstNameText, bookingLastNameText,
          bookingAddressText, bookingPhoneNumberText, bookingNationalityText,
          bookingDateOfBirthValue, arrivalDateValue, departureDateValue);



      // clear everything after booking is created
      bookingClear();

      // show dialog box when booking is done
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("TheOverLookHotel");
      alert.setHeaderText("Booking Confirmed");
      alert.setContentText("Your Booking is Confirmed. Enjoy Your Stay");
      alert.showAndWait();
    }

  }

  /**
   * When the user clicks the "Go to Check-In" button in the Booking tab, the
   * Check-In tab is selected
   */
  @FXML private void bookingGoToCheckIn()
  {
    SingleSelectionModel<Tab> selectionBookingToCheckIn = tabPane.getSelectionModel();
    selectionBookingToCheckIn.select(checkInTab);
  }

  /**
   * A function that is called when the user clicks the back button on the booking
   * tab. It takes the user back to the room status tab.
   */
  @FXML private void bookingBack()
  {
    SingleSelectionModel<Tab> selectionBookingBack = tabPane.getSelectionModel();
    selectionBookingBack.select(roomStatus);
  }

  // -------------------------- check in methods starts from here ------------------------------

  /**
   * When the user clicks the "Check In" button, the tab pane will switch to the
   * "Create Booking" tab
   *
   * @param actionEvent This is the event that is triggered when the button is
   *                    clicked.
   * @author Pramesh Shrestha
   */
  @FXML private void checkInBack(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionCheckInBackButton = tabPane.getSelectionModel();
    selectionCheckInBackButton.select(createBooking);
  }

  /**
   * This function is used to set the room number combo box in the check in tab
   */
  public void setRoomToCheckInComboBox()
  {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("SBS-C1", "SBS-C2","SBS-C3","3SBS-C4","2SBS-C5",
        "SR-A1","SR-A2","SR-A3","SR-A4","SR-A5","SR-A6","SR-A7","SR-A8","SR-A9","SR-A10",
        "DR-A11","DR-A12","DR-A13","DR-A14","DR-A15","DR-A16","DR-A17","DR-A18",
        "DR-B19","DR-B20","DR-B21","DR-B22","DR-B23","DR-B24","DR-B25","DR-B26","DR-B27",
        "DR-B28","DR-B29","DR-B30","DR-B31","DR-B32","DR-B33","DR-B34","DR-B35","DR-B36","DR-B37");
    checkInRoomNumber.setItems(list);
    checkInRoomNumber.setEditable(true);
  }

  /**
   * This is an event in a checkIn Tab, that searches for a booking by the guest's first name, last name, and phone
   * number, and if it finds one, it displays the guest's information in the
   * check-in tab
   *
   * @param event The event that triggered the method.
   */
  @FXML private void checkInSearch(ActionEvent event)
  {
    /*Setting up what data to display in the two columns of Table.
    The TableView in JavaFX is used to show the which room(room number) was booked
    in the first column and by whom (the first name and the last name)
    in the second column*/

    checkInNumberColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("roomNumber"));
    checkInNumberColumn.setStyle("-fx-alignment: CENTER;");

    checkInBookedBy.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("fullName"));
    checkInBookedBy.setStyle("-fx-alignment: CENTER;");

    ObservableList<Booking> booking = FXCollections.observableArrayList();

    //The trim method at the end removes any extra spaces
    //Getting texts from the TextField
    String firstName = checkInSearchFirstName.getText().trim();
    String lastName = checkInSearchLastName.getText().trim();
    String phoneNumber = checkInSearchPhoneNumber.getText().trim();
    //initializing a boolean value for the alert message
    boolean booked = false;

    /*This calls the search booking method which compares the provided parameters
    in the BookingList and returns a booking that matches*/
    Booking bookingBy = manager.searchBooking(firstName, lastName, phoneNumber);
    booking.add(bookingBy);
    //This will set the data from the booking that matched in the TableView.
    checkInTableView.setItems(booking);

    /*Compares the firstname, lastname and the phone in the booking list and
     * sets the corresponding TextFields with firstname, lastname,phoneNumber,
     * nationality,roomNumber, address and dateOfBirth from the matched booking. */
    BookingList bookingList = manager.getAllBookings();
    for (int i = 0; i < bookingList.getTotalNumberOfBookings(); i++)
    {
      if (bookingList.getBookingByIndex(i).getGuest().getFirstName()
          .equals(firstName) && bookingList.getBookingByIndex(i).getGuest()
          .getLastName().equals(lastName) && bookingList.getBookingByIndex(i)
          .getGuest().getPhone().equals(phoneNumber))
      {
        checkInFirstName.setText(firstName);
        checkInLastName.setText(lastName);
        checkInPhoneNumber.setText(phoneNumber);
        checkInNationality.setText(
            bookingList.getBookingByIndex(i).getGuest().getNationality());
        checkInRoomNumber.setValue(
            bookingList.getBookingByIndex(i).getRoomNumber());
        checkInDateOfBirth.setValue(bookingList.getBookingByIndex(i).getGuest().getDateOfBirth());
        checkInAddress.setText(
            bookingList.getBookingByIndex(i).getGuest().getAddress());
        checkInDateOfBirth.setValue(
            bookingList.getBookingByIndex(i).getGuest().getDateOfBirth());
        checkInCheckedInDate.setValue(bookingList.getBookingByIndex(i).getArrivalDate());
        booked = true;
        break;
      }
    }
    //Alert message in case no booking is matched with the provided firstName, lastName and phoneNumber
    if (!booked)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText(null);
      alert.setContentText(
          "No booking found under " + firstName + " " + lastName + "'s name.");
      alert.showAndWait();
    }

  }

  /**
   * The function checks if the user has left any fields blank, if they have it
   * alerts the user with a warning message, if not it creates a check in and then
   * clears all the text fields
   *
   * @param event The event that triggered the method.
   */
  @FXML private void checkIn(ActionEvent event)
  {
    String firstName = checkInFirstName.getText().trim();
    String lastName = checkInLastName.getText().trim();
    String phoneNumber = checkInPhoneNumber.getText().trim();
    String nationality = checkInNationality.getText().trim();
    String address = checkInAddress.getText().trim();
    String roomNumber = checkInRoomNumber.getValue().trim();
    LocalDate dateOfBirth = checkInDateOfBirth.getValue();
    LocalDate checkInDate = checkInCheckedInDate.getValue();
    LocalDate checkoutDate = checkInCheckOutDate.getValue();

    //If any field is left out it alerts the user with a warning message
    if (firstName.equals("") || lastName.equals("") || phoneNumber.equals("")
        || nationality.equals("") || address.equals("") || dateOfBirth == null
        || checkInDate == null || roomNumber.equals(""))
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText(null);
      alert.setContentText("All fields required.");
      alert.showAndWait();
    }
    //creates check in and then clears all the text fields
    else
    {
      manager.createCheckIn(firstName, lastName, address, phoneNumber,
          nationality, dateOfBirth, checkInDate, checkoutDate, roomNumber);
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("Checked in");
      alert.setContentText("Guest successfully checked-in");
      alert.showAndWait();

      clearCheckInBox();
      clearCheckInSearchBox();
    }
  }

  /**
   * If the check-out date is before the check-in date, display an error message
   *
   * @param event The event that triggered the method.
   */
  @FXML private void disableCheckOutDate(ActionEvent event)
  {
    if(checkInCheckOutDate.getValue().isBefore(checkInCheckedInDate.getValue()))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Invalid Check-out Date");
      alert.setContentText("Please enter a valid check-out date.");
      alert.showAndWait();
      checkInCheckOutDate.getEditor().clear();
    }
    else
    {
      checkInButton.setDisable(false);
    }


  }

  /**
   * It clears all the text fields  except the room TextField in the check in tab
   *
   * @param event The event that triggered the action.
   */
  @FXML private void checkInClear(ActionEvent event)
  {
    clearCheckInBox();
    checkInRoomNumber.getEditor().clear();
    checkInCheckedInDate.getEditor().clear();
  }

  /**
   * It clears all the text fields in the check in tab
   */
  public void clearCheckInBox()
  {
    checkInFirstName.clear();
    checkInLastName.clear();
    checkInPhoneNumber.clear();
    checkInNationality.clear();
    checkInAddress.clear();
    checkInDateOfBirth.getEditor().clear();
    checkInCheckOutDate.getEditor().clear();
    checkInCheckedInDate.getEditor().setText(checkInCheckedInDate.getValue().toString());
  }

  /**
   * This function clears the check-in search box.
   */
  public void clearCheckInSearchBox()
  {
    checkInSearchFirstName.clear();
    checkInSearchLastName.clear();
    checkInSearchPhoneNumber.clear();

  }

  // -------------------------- check out methods starts from here ------------------------------

  /**
   * This function searches the Room number and the Checked-In date for the given guest .
   */
  @FXML private void searchCheckIn(ActionEvent event)
  {
    Guest guest1 = manager.searchCheckIn(
        checkOutSearchFirstName.getText().trim(),
        checkOutSearchLastName.getText().trim(),
        checkOutSearchPhoneNumber.getText().trim());

    checkOutColumnNumber.setCellValueFactory(
        new PropertyValueFactory<>("roomNumber"));
    checkOutColumnNumber.setStyle("-fx-alignment: CENTER;");

    checkOutCheckedIn.setCellValueFactory(new PropertyValueFactory<>("checkedInDate"));
    checkOutCheckedIn.setStyle("-fx-alignment: CENTER;");

    ObservableList<Guest> guest = FXCollections.observableArrayList();
    guest.add(new Guest(guest1.getRoomNumber(), guest1.getCheckedInDate()));
    checkOutTableView.setItems(guest);

    //Set the price and check-in of the book searched in the textField Initial Price

    checkedOutCheckInDate.setValue(guest1.getCheckedInDate());
    checkedOutInitialPrice.setText(String.valueOf(
        manager.searchBooking(checkOutSearchFirstName.getText(),
            checkOutSearchLastName.getText(),
            checkOutSearchPhoneNumber.getText()).getRoom().getPrice()));
    checkOutRoomNumber.setText(guest1.getRoomNumber());
    checkedOutCheckOutDate.setValue(LocalDate.now());
  }

  /**
   * This function sets the number of nights stayed according to the given date interval .
   */

  @FXML private void setNumberOfNights(ActionEvent event)
  {

    if (checkedOutCheckOutDate.getValue() != null)
    {
      int numberOfNightsStayed = manager.calculateNumberOfNights(
          checkedOutCheckInDate.getValue(), checkedOutCheckOutDate.getValue());
      checkedOutNightsStayed.setText(String.valueOf(numberOfNightsStayed));
    }

  }

  /**
   * This function calculates the final price according to the nights stayed whether or not a given discount.
   */
  @FXML private void checkOutCalculate(ActionEvent event)
  {
    Guest guest1 = manager.searchCheckIn(checkOutSearchFirstName.getText(),
        checkOutSearchLastName.getText(), checkOutSearchPhoneNumber.getText());
    double price = manager.calculatePrice(checkedOutCheckInDate.getValue(),
        checkedOutCheckOutDate.getValue(), guest1.getRoomNumber(),
        Double.parseDouble(checkedOutDiscountAmount.getText()));
    checkedOutFinalPrice.setText(String.valueOf(price));
  }

  /**
   * This function delates guest from the guest list and booking list.
   */

  @FXML private void checkOutSave(ActionEvent event)
  {
    if (!checkOutSearchFirstName.getText().equals("")
        && !checkOutSearchLastName.getText().equals("")
        && !checkOutSearchPhoneNumber.getText().equals(""))
    {
      Guest guest1 = manager.searchCheckIn(checkOutSearchFirstName.getText(),
          checkOutSearchLastName.getText(),
          checkOutSearchPhoneNumber.getText());

//      manager.createCheckOut(guest1.getRoomNumber());

      manager.removeCheckIn(guest1.getRoomNumber());

      manager.deleteBookings(guest1.getFirstName(), guest1.getLastName(),
          guest1.getPhone());

//      manager.getAllRooms().getRoomByRoomNumber(guest1.getRoomNumber());


      checkOutClear();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The OverLook Hotel");
      alert.setContentText("Check Out saved");
      alert.showAndWait();
    }
  }
  /**
   * This function clear the text fields in the tab check-out
   */


  @FXML private void checkOutClear()
  {
    checkedOutCheckInDate.getEditor().clear();
    checkedOutCheckOutDate.getEditor().clear();
    checkedOutNightsStayed.clear();
    checkedOutInitialPrice.clear();
    checkedOutDiscountAmount.clear();
    checkedOutFinalPrice.clear();
    checkOutSearchFirstName.clear();
    checkOutSearchLastName.clear();
    checkOutSearchPhoneNumber.clear();
    checkInTableView.getItems().clear();
  }

  // -------------------------- All check-ins tab starts from here ------------------------------

  /**
   * When the user clicks the button, get all the check-ins from the database and
   * display them in the table.
   *
   * @param event The event that triggered the action.
   * @author Pramesh Shrestha
   */
  //-----------------------------------------------------------------------------------------------------------------
  @FXML private void allCheckInsButton(ActionEvent event)
  {
    allCheckInRoomNumberColumn.setCellValueFactory(
        new PropertyValueFactory<Guest, String>("roomNumber"));
    allCheckInFirstNameColumn.setCellValueFactory(
        (new PropertyValueFactory<Guest, String>("firstName")));
    allCheckInLastNameColumn.setCellValueFactory(
        (new PropertyValueFactory<Guest, String>("lastName")));
    allCheckInPhoneNumberColumn.setCellValueFactory(
        (new PropertyValueFactory<Guest, String>("phone")));
    allCheckInCheckedInDateColumn.setCellValueFactory(
        new PropertyValueFactory<Guest, LocalDate>("checkedInDate"));
    allCheckInCheckedOutDateColumn.setCellValueFactory(
        new PropertyValueFactory<Guest, LocalDate>("checkOutDate"));

    ObservableList<Guest> checkIns = FXCollections.observableArrayList();
    GuestList allCheckIns = manager.getAllCheckedIn();

    for (int i = 0; i < allCheckIns.getNumberOfGuest(); i++)
    {
      checkIns.add(allCheckIns.getGuestByIndex(i));
    }
    checkInCombo.getSelectionModel().clearSelection();
    allCheckInTableView.setItems(checkIns);

  }

  public void setRoomToComboBox()
  {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("SBS-C1", "SBS-C2", "SBS-C3", "3SBS-C4", "2SBS-C5", "SR-A1",
        "SR-A2", "SR-A3", "SR-A4", "SR-A5", "SR-A6", "SR-A7", "SR-A8", "SR-A9",
        "SR-A10", "DR-A11", "DR-A12", "DR-A13", "DR-A14", "DR-A15", "DR-A16",
        "DR-A17", "DR-A18", "DR-B19", "DR-B20", "DR-B21", "DR-B22", "DR-B23",
        "DR-B24", "DR-B25", "DR-B26", "DR-B27", "DR-B28", "DR-B29", "DR-B30",
        "DR-B31", "DR-B32", "DR-B33", "DR-B34", "DR-B35", "DR-B36", "DR-B37");
    checkInCombo.setItems(list);
  }

  /**
   * This function gets all the guests that are checked in by room number
   *
   * @param event The event that triggered the method.
   */
  @FXML private void getAllGuestsByRoomNumber(ActionEvent event)
  {
    String selected = checkInCombo.getSelectionModel().getSelectedItem();
    String roomNumber = manager.displayCheckInsByRoomNumber(selected);

    allCheckInRoomNumberColumn.setCellValueFactory(
        new PropertyValueFactory<Guest, String>("roomNumber"));
    allCheckInFirstNameColumn.setCellValueFactory(
        (new PropertyValueFactory<Guest, String>("firstName")));
    allCheckInLastNameColumn.setCellValueFactory(
        (new PropertyValueFactory<Guest, String>("lastName")));
    allCheckInPhoneNumberColumn.setCellValueFactory(
        (new PropertyValueFactory<Guest, String>("phone")));
    allCheckInCheckedInDateColumn.setCellValueFactory(
        new PropertyValueFactory<Guest, LocalDate>("checkedInDate"));
    allCheckInCheckedOutDateColumn.setCellValueFactory(
        new PropertyValueFactory<Guest, LocalDate>("checkOutDate"));

    ObservableList<Guest> checkIns = FXCollections.observableArrayList();
    GuestList allCheckIns = manager.getAllCheckedIn();

    for (int i = 0; i < allCheckIns.getNumberOfGuest(); i++)
    {
      if (allCheckIns.getGuestByIndex(i).getRoomNumber().equals(roomNumber))
      {
        checkIns.add(allCheckIns.getGuestByIndex(i));
      }
    }
    allCheckInTableView.setItems(checkIns);

  }



  // -------------------------- All Bookings tab starts from here ------------------------------

  /**
   * This function displays all bookings.
   *
   * @author Rajiv Paudyal
   */
  public void displayAllBookings()
  {
    allBookingsRoomNumber.setCellValueFactory(
        new PropertyValueFactory<>("roomNumber"));
    allBookingsFirstName.setCellValueFactory(
        new PropertyValueFactory<>("firstName"));
    allBookingsLastName.setCellValueFactory(
        new PropertyValueFactory<>("lastName"));
    allBookingsPhoneNumber.setCellValueFactory(
        new PropertyValueFactory<>("phone"));
    allBookingsArrivalDate.setCellValueFactory(
        new PropertyValueFactory<>("arrivalDate"));
    allBookingsDepartureDate.setCellValueFactory(
        new PropertyValueFactory<>("departureDate"));

    ObservableList<Booking> bookings = FXCollections.observableArrayList();
    BookingList allBookings = manager.getAllBookings();
    for (int i = 0; i < allBookings.getTotalNumberOfBookings(); i++)
    {
      bookings.add(allBookings.getBookingByIndex(i));
    }

    allBookingsTableView.setItems(bookings);
  }

  /**
   * When the user clicks the remove button, remove the selected row from the
   * table.
   */
  public void removeBookingFromTable()
  {
    // selects booking from the table and deletes it from file
    Booking selectedBooking = allBookingsTableView.getSelectionModel()
        .getSelectedItem();

    manager.deleteBookings(selectedBooking.getFirstName(),
        selectedBooking.getLastName(), selectedBooking.getPhone());

    // updates the table
    displayAllBookings();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("The OverLook Hotel");
    alert.setContentText("Booking Removed");
    alert.showAndWait();

  }
}
