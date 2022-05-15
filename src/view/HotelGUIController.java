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
import java.util.Optional;
import java.util.ResourceBundle;

/**
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
  @FXML private TextField checkInRoomNumber;
  @FXML private Tab checkInTab;
  @FXML private TableView<Booking> checkInTableView;
  @FXML private ComboBox<String> checkInCombo;

  // Check Out tab private fields
  @FXML private TableColumn<Guest, String> checkOutCheckedIn;
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

  // All Check-In tab private fields
  @FXML private Tab allCheckInsTab;
  @FXML private TableColumn<Guest, LocalDate>  allCheckInCheckedInDateColumn;
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
    intitializeTable();
  }

  /**
   * The function intitializeTable() is called when the FXML file is loaded. It
   * sets the cell value factory for each column in the table view to the
   * appropriate property in the Room class
   */
  @FXML private void intitializeTable()
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
  }

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

//      // change available status of booked room
//      manager.updateRoomAvailable(bookingRoomNumber.getText().trim(),
//          arrivalDateValue, departureDateValue);

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

  @FXML private void checkInSearch(ActionEvent event)
  {
    String firstName = checkInSearchFirstName.getText().trim();
    String lastName = checkInSearchLastName.getText().trim();
    String phoneNumber = checkInSearchPhoneNumber.getText().trim();
    boolean booked = false;

    checkInNumberColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("roomNumber"));
    checkInNumberColumn.setStyle("-fx-alignment: CENTER;");

    checkInBookedBy.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("fullName"));
    checkInBookedBy.setStyle("-fx-alignment: CENTER;");

    ObservableList<Booking> booking = FXCollections.observableArrayList();

    Booking bookingBy = manager.searchBooking(firstName, lastName, phoneNumber);
    booking.add(bookingBy);
    checkInTableView.setItems(booking);

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
        checkInRoomNumber.setText(
            bookingList.getBookingByIndex(i).getRoomNumber());
        checkInAddress.setText(
            bookingList.getBookingByIndex(i).getGuest().getAddress());
        checkInDateOfBirth.setValue(
            bookingList.getBookingByIndex(i).getGuest().getDateOfBirth());
        booked = true;
        break;
      }
    }
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
    String roomNumber = checkInRoomNumber.getText().trim();
    LocalDate dateOfBirth = checkInDateOfBirth.getValue();
    LocalDate checkInDate = checkInCheckedInDate.getValue();

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
          nationality, dateOfBirth, checkInDate, roomNumber);
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("Checked in");
      alert.setContentText("Guest successfully checked-in");
      alert.showAndWait();

      checkInFirstName.clear();
      checkInLastName.clear();
      checkInPhoneNumber.clear();
      checkInNationality.clear();
      checkInAddress.clear();
      checkInDateOfBirth.getEditor().clear();
      checkedOutCheckInDate.getEditor().clear();
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
    checkInRoomNumber.clear();
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
    checkInCheckedInDate.getEditor().clear();
    checkInCheckOutDate.getEditor().clear();
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

  @FXML private void searchCheckIn(ActionEvent event)
  {
    Guest guest1 = manager.searchCheckIn(
        checkOutSearchFirstName.getText().trim(),
        checkOutSearchLastName.getText().trim(),
        checkOutSearchPhoneNumber.getText().trim());

    checkOutColumnNumber.setCellValueFactory(
        cellData -> new SimpleStringProperty(
            cellData.getValue().getRoomNumber()));
    checkOutColumnNumber.setStyle("-fx-alignment: CENTER;");

    checkOutCheckedIn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getCheckInDate().toString()));
    checkOutCheckedIn.setStyle("-fx-alignment: CENTER;");

    ObservableList<Guest> guest = FXCollections.observableArrayList();
    guest.add(new Guest(guest1.getRoomNumber(), guest1.getCheckInDate()));
    checkOutTableView.setItems(guest);
  }

  @FXML private void checkOutSave(ActionEvent event)
  {

  }

  @FXML private void checkOutCalculate(ActionEvent event)
  {
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
  @FXML
  private void allCheckInsButton(ActionEvent event)
  {
    allCheckInRoomNumberColumn.setCellValueFactory(new PropertyValueFactory<Guest,String>("roomNumber"));
    allCheckInFirstNameColumn.setCellValueFactory((new PropertyValueFactory<Guest, String>("firstName")));
    allCheckInLastNameColumn.setCellValueFactory((new PropertyValueFactory<Guest, String>("lastName")));
    allCheckInPhoneNumberColumn.setCellValueFactory((new PropertyValueFactory<Guest, String>("phone")));
    allCheckInCheckedInDateColumn.setCellValueFactory( new PropertyValueFactory<Guest, LocalDate>("checkedInDate"));
    allCheckInCheckedOutDateColumn.setCellValueFactory( new PropertyValueFactory<Guest, LocalDate>("checkOutDate"));

    ObservableList<Guest> checkIns = FXCollections.observableArrayList();
    GuestList allCheckIns = manager.getAllCheckedIn();

    for(int i = 0; i < allCheckIns.getNumberOfGuest(); i++)
    {
      checkIns.add(allCheckIns.getGuestByIndex(i));
    }
    allCheckInTableView.setItems(checkIns);

  }

  public void setRoomToComboBox()
  {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("SBS-C1", "SBS-C2","SBS-C3","3SBS-C4","2SBS-C5",
        "SR-A1","SR-A2","SR-A3","SR-A4","SR-A5","SR-A6","SR-A7","SR-A8","SR-A9","SR-A10",
        "DR-A11","DR-A12","DR-A13","DR-A14","DR-A15","DR-A16","DR-A17","DR-A18",
        "DR-B19","DR-B20","DR-B21","DR-B22","DR-B23","DR-B24","DR-B25","DR-B26","DR-B27",
        "DR-B28","DR-B29","DR-B30","DR-B31","DR-B32","DR-B33","DR-B34","DR-B35","DR-B36","DR-B37");
    checkInCombo.setItems(list);
    checkInCombo.setPromptText("Select a room");
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

    allCheckInRoomNumberColumn.setCellValueFactory(new PropertyValueFactory<Guest,String>("roomNumber"));
    allCheckInFirstNameColumn.setCellValueFactory((new PropertyValueFactory<Guest, String>("firstName")));
    allCheckInLastNameColumn.setCellValueFactory((new PropertyValueFactory<Guest, String>("lastName")));
    allCheckInPhoneNumberColumn.setCellValueFactory((new PropertyValueFactory<Guest, String>("phone")));
    allCheckInCheckedInDateColumn.setCellValueFactory( new PropertyValueFactory<Guest, LocalDate>("checkedInDate"));
    allCheckInCheckedOutDateColumn.setCellValueFactory( new PropertyValueFactory<Guest, LocalDate>("checkOutDate"));

    ObservableList<Guest> checkIns = FXCollections.observableArrayList();
    GuestList allCheckIns = manager.getAllCheckedIn();

    for(int i = 0; i < allCheckIns.getNumberOfGuest(); i++)
    {
      if(allCheckIns.getGuestByIndex(i).getRoomNumber().equals(roomNumber))
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
   *
   * @param event The event that triggered the method.
   */
  public void removeBookingFromTable(ActionEvent event)
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
