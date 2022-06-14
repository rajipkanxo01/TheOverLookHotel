package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
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
  @FXML private Button bookingSave;

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
  @FXML private CheckBox checkInSmoking;

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
  @FXML private CheckBox checkOutSmoking;

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
  @FXML private DatePicker bookingSelectDate;

  /**
   * runs when gui loads
   *
   * @param url            The location used to resolve relative paths for the root object, or
   *                       null if the location is not known.
   * @param resourceBundle This is a ResourceBundle object that contains the
   *                       resources for the application.
   */
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

    bookingRoomType.setStyle("-fx-opacity: 1.0");
    bookingRoomNumber.setStyle("-fx-opacity: 1.0");
    bookingSmoking.setStyle("-fx-opacity: 1.0");
    checkInSmoking.setStyle("-fx-opacity: 1");
    checkOutSmoking.setStyle("-fx-opacity: 1");

    // styling for align items in center

    roomStatusColumnPrice.setStyle("-fx-alignment: CENTER;");
    roomStatusColumnType.setStyle("-fx-alignment: CENTER;");
    checkInNumberColumn.setStyle("-fx-alignment: CENTER;");
    roomStatusTableView.setStyle("-fx-alignment: CENTER;");
    checkInBookedBy.setStyle("-fx-alignment: CENTER;");
    checkOutColumnNumber.setStyle("-fx-alignment: CENTER;");
    checkOutCheckedIn.setStyle("-fx-alignment: CENTER;");

    createBooking.setDisable(true);

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

      bookingSmoking.setSelected(isSmoking.isSelected());
      bookingSmoking.setDisable(true);

      roomStatusError.setText("");

      // refresh table and clear everything
      roomArrivalDate.getEditor().clear();
      roomDepartureDate.getEditor().clear();

      isSmoking.setSelected(false);
      roomStatusTableView.getItems().clear();

      createBooking.setDisable(false);
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
   * @author Rajib Paudyal
   */
  @FXML private void createBack(ActionEvent actionEvent)
  {

    // go back to room status tab
    SingleSelectionModel<Tab> selectionModelCreateBackButton = tabPane.getSelectionModel();
    selectionModelCreateBackButton.select(roomStatus);

    createBooking.setDisable(true);
  }

  /**
   * When the user clicks the "Check In" button, the program will switch to the
   * "Check In" tab.
   *
   * @param actionEvent This is the event that is triggered when the button is
   *                    clicked.
   * @author Rajib Paudyal
   */
  @FXML private void goToCheckIn(ActionEvent actionEvent)
  {
    // go to check in tab
    SingleSelectionModel<Tab> selectionModelGoToCheckIn = tabPane.getSelectionModel();
    selectionModelGoToCheckIn.select(checkInTab);
  }

  /**
   * This function searches the available room from binary file when search button is clicked
   *
   * @author Rajib Paudyal
   */
  @FXML private void searchAvailableRooms()
  {
    roomStatusColumnPrice.setCellValueFactory(
        new PropertyValueFactory<Room, Double>("price"));
    roomStatusColumnType.setCellValueFactory(
        new PropertyValueFactory<Room, String>("type"));
    roomStatusColumnNumber.setCellValueFactory(
        new PropertyValueFactory<Room, String>("roomNumber"));

    LocalDate arrivalDate = roomArrivalDate.getValue();
    LocalDate departureDate = roomDepartureDate.getValue();
    boolean smoking = isSmoking.isSelected();

    // search available rooms according to provided data
    RoomList allAvailableRooms = manager.getAllAvailableRooms(arrivalDate,
        departureDate, smoking);
    // adding smoking and non smoking rooms to observable list
    ObservableList<Room> allRooms = FXCollections.observableArrayList();
    ObservableList<Room> allSmokingRooms = FXCollections.observableArrayList();
    for (int i = 0; i < allAvailableRooms.getTotalNumberOfRooms(); i++)
    {
      allRooms.add(allAvailableRooms.getRoom(i));
      if (allAvailableRooms.getRoom(i).isSmoking())
      {
        allSmokingRooms.add(allAvailableRooms.getRoom(i));
      }
    }
    if (smoking)
    {
      roomStatusTableView.setItems(allSmokingRooms);
    }
    else
    {
      roomStatusTableView.setItems(allRooms);
    }
  }

  /**
   * If the check-out date is before the check-in date, display an error message
   *
   * @param event The event that triggered the method.
   * @author Pramesh Shrestha
   */
  @FXML private void disableDepartureDate(ActionEvent event)
  {
    if (roomArrivalDate.getValue() != null)
    {
      if (roomDepartureDate.getValue().isBefore(roomArrivalDate.getValue()))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Invalid Check-out Date");
        alert.setContentText("Please enter a valid check-out date.");
        alert.showAndWait();
        roomDepartureDate.getEditor().clear();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("The Overlook Hotel");
      alert.setHeaderText("Check in date not entered");
      alert.setContentText("Please enter a check-in date first");
      alert.showAndWait();
    }
  }

  /**
   * If the user enters a date that is before today's date, an error message is
   * displayed
   */
  public void disablePastArrivalDate()
  {
    if (roomArrivalDate.getValue() != null)
    {
      if (roomArrivalDate.getValue().isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Invalid  Date");
        alert.setContentText("Arrival Date is before today's date.");
        alert.showAndWait();
        roomArrivalDate.getEditor().clear();
      }
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
   *
   * @author Rajib Paudyal
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
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("TheOverLookHotel");
      alert.setHeaderText("Error");
      alert.setContentText("Please, input all values.");
      alert.showAndWait();
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

      // exports to xml
      manager.exportRoomsToXML();

      // show dialog box when booking is done
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("TheOverLookHotel");
      alert.setHeaderText("Booking Confirmed");
      alert.setContentText(
          "Booking Confirmed under " + bookingFirstNameText + " "
              + bookingLastNameText);
      alert.showAndWait();

      SingleSelectionModel<Tab> selectionModelCreateBackButton = tabPane.getSelectionModel();
      selectionModelCreateBackButton.select(roomStatus);

      createBooking.setDisable(true);
    }
  }

  /**
   * If the date of birth is after today's date, display an alert
   *
   * @param event The event that triggered the method.
   * @author Pramesh Shrestha
   */
  @FXML private void validDateOfBirth(ActionEvent event)
  {
    if (bookingDateOfBirth.getValue().isAfter(LocalDate.now()))
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("The Overlook Hotel");
      alert.setContentText("Invalid Date of Birth");
      alert.showAndWait();
    }
    if ((LocalDate.now().getYear() - bookingDateOfBirth.getValue().getYear()
        < 18))
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("The Overlook Hotel");
      alert.setContentText("The age is under 18");
      alert.showAndWait();
      bookingDateOfBirth.getEditor().clear();
    }
  }

  /**
   * When the user clicks the "Go to Check-In" button in the Booking tab, the
   * Check-In tab is selected
   *
   * @author Rajib Paudyal
   */
  @FXML private void bookingGoToCheckIn()
  {
    SingleSelectionModel<Tab> selectionBookingToCheckIn = tabPane.getSelectionModel();
    selectionBookingToCheckIn.select(checkInTab);
  }

  /**
   * A function that is called when the user clicks the back button on the booking
   * tab. It takes the user back to the room status tab.
   *
   * @author Rajib Paudyal
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
    //switch back to Create Booking tab
    SingleSelectionModel<Tab> selectionCheckInBackButton = tabPane.getSelectionModel();
    selectionCheckInBackButton.select(createBooking);
  }

  /**
   * This function is used to set the room number combo box in the check in tab
   *
   * @author Pramesh Shrestha
   */
  @FXML private void setRoomToCheckInComboBox()
  {
    //adding rooms to combo box
    ObservableList<String> list = FXCollections.observableArrayList();
    list.addAll("SBS-C1", "SBS-C2", "SBS-C3", "3SBS-C4", "2SBS-C5", "SR-A1",
        "SR-A2", "SR-A3", "SR-A4", "SR-A5", "SR-A6", "SR-A7", "SR-A8", "SR-A9",
        "SR-A10", "DR-A11", "DR-A12", "DR-A13", "DR-A14", "DR-A15", "DR-A16",
        "DR-A17", "DR-A18", "DR-B19", "DR-B20", "DR-B21", "DR-B22", "DR-B23",
        "DR-B24", "DR-B25", "DR-B26", "DR-B27", "DR-B28", "DR-B29", "DR-B30",
        "DR-B31", "DR-B32", "DR-B33", "DR-B34", "DR-B35", "DR-B36", "DR-B37");
    checkInRoomNumber.setItems(list);
    checkInRoomNumber.setEditable(true);
  }

  /**
   * This is an event in a checkIn Tab, that searches for a booking by the guest's first name, last name, and phone
   * number, and if it finds one, it displays the guest's information in the
   * check-in tab
   *
   * @param event The event that triggered the method.
   * @author Rajib Paudyal
   */
  @FXML private void checkInSearch(ActionEvent event)
  {
    /*Setting up what data to display in the two columns of Table.
    The TableView in JavaFX is used to show the which room(room number) was booked
    in the first column and by whom (the first name and the last name)
    in the second column*/

    checkInNumberColumn.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("roomNumber"));

    checkInBookedBy.setCellValueFactory(
        new PropertyValueFactory<Booking, String>("fullName"));

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
        checkInDateOfBirth.setValue(
            bookingList.getBookingByIndex(i).getGuest().getDateOfBirth());
        checkInAddress.setText(
            bookingList.getBookingByIndex(i).getGuest().getAddress());
        checkInDateOfBirth.setValue(
            bookingList.getBookingByIndex(i).getGuest().getDateOfBirth());
        checkInCheckedInDate.setValue(
            bookingList.getBookingByIndex(i).getArrivalDate());
        checkInCheckOutDate.setValue(
            bookingList.getBookingByIndex(i).getDepartureDate());

        checkInSmoking.setSelected(bookingList.getBookingByIndex(i).ifSmokes());
        booked = true;
        break;
      }
    }
    //Alert message in case no booking is matched with the provided firstName, lastName and phoneNumber
    if (!booked)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The OverLook Hotel");
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
   * @author Rajib Paudyal
   */
  @FXML private void checkIn(ActionEvent event)
  {
    //getting string value from the GUI and assigning to them to the variables of respective data type
    String firstName = checkInFirstName.getText().trim();
    String lastName = checkInLastName.getText().trim();
    String phoneNumber = checkInPhoneNumber.getText().trim();
    String nationality = checkInNationality.getText().trim();
    String address = checkInAddress.getText().trim();
    String roomNumber = checkInRoomNumber.getValue().trim();
    LocalDate dateOfBirth = checkInDateOfBirth.getValue();
    LocalDate checkInDate = checkInCheckedInDate.getValue();
    LocalDate checkoutDate = checkInCheckOutDate.getValue();
    boolean smoking = checkInSmoking.isSelected();

    //If any field is left out it alerts the user with a warning message
    if (firstName.equals("") || lastName.equals("") || phoneNumber.equals("")
        || nationality.equals("") || address.equals("") || dateOfBirth == null
        || checkInDate == null || roomNumber.equals(""))
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("The OverLook Hotel");
      alert.setHeaderText(null);
      alert.setContentText("All fields required.");
      alert.showAndWait();
    }
    //creates check in and then clears all the text fields
    else
    {
      manager.createCheckIn(firstName, lastName, address, phoneNumber,
          nationality, dateOfBirth, checkInDate, checkoutDate, roomNumber,
          smoking);
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("The OverLook Hotel");
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
   * @author Rajib Paudyal
   */
  @FXML private void disableCheckOutDate(ActionEvent event)
  {
    //Alert box pops up check out date is before check in date and then clears check out field in GUI
    if (checkInCheckOutDate.getValue()
        .isBefore(checkInCheckedInDate.getValue()))
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("The OverLook Hotel");
      alert.setHeaderText("Invalid Check-out Date");
      alert.setContentText("Please enter a valid check-out date.");
      alert.showAndWait();
      checkInCheckOutDate.getEditor().clear();
    }
    else
    {
      //check in button is on available to be used
      checkInButton.setDisable(false);
    }

  }

  /**
   * It clears all the text fields  except the room TextField in the check in tab
   * @author Rajib Paudyal
   */
  @FXML private void checkInClear()
  {
    //clearing out fields in GUI
    clearCheckInBox();
    checkInRoomNumber.getEditor().clear();
    checkInCheckedInDate.getEditor().clear();
  }

  /**
   * It clears all the text fields in the check in tab
   * @author Pramesh Shrestha
   */
  @FXML private void clearCheckInBox()
  {
    //clear fields in GUI
    checkInFirstName.clear();
    checkInLastName.clear();
    checkInPhoneNumber.clear();
    checkInNationality.clear();
    checkInAddress.clear();
    checkInDateOfBirth.getEditor().clear();
    checkInCheckOutDate.getEditor().clear();
  }

  /**
   * This function clears the check-in search box.
   * @author Pramesh Shrestha
   */
  @FXML private void clearCheckInSearchBox()
  {
    //clears Check-In tab's search box's  fields
    checkInSearchFirstName.clear();
    checkInSearchLastName.clear();
    checkInSearchPhoneNumber.clear();

  }

  // -------------------------- check out methods starts from here ------------------------------

  /**
   * This function searches the Room number and the Checked-In date for the given guest .
   *
   * @author Rodrigo Reyes
   */
  @FXML private void searchCheckIn(ActionEvent event)
  {
    /*if in the Search of check-In in the binary file does not match any check-in
    returns an alert that it was not found*/
    if (manager.searchCheckIn(checkOutSearchFirstName.getText(),
        checkOutSearchLastName.getText(), checkOutSearchPhoneNumber.getText())
        == null)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The OverLook Hotel");
      alert.setContentText("The check-in was not found");
      alert.showAndWait();
    }
    else
    {
      /*if the Search of check-In in the binary file matches any check-in
    then adds in the table view*/
      Guest guest1 = manager.searchCheckIn(
          checkOutSearchFirstName.getText().trim(),
          checkOutSearchLastName.getText().trim(),
          checkOutSearchPhoneNumber.getText().trim());

      checkOutColumnNumber.setCellValueFactory(
          new PropertyValueFactory<>("roomNumber"));

      checkOutCheckedIn.setCellValueFactory(
          new PropertyValueFactory<>("checkedInDate"));

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

      checkOutSmoking.setSelected(guest1.ifSmoking());
    }
  }

  /**
   * This function sets the number of nights stayed according to the given date interval.
   * @author Rodrigo Reyes
   */

  @FXML private void setNumberOfNights(ActionEvent event)
  {
        /*sets the number of nights in the text field
         according to the check-out date that was selected*/
    if (checkedOutCheckOutDate.getValue() != null)
    {
      int numberOfNightsStayed = manager.calculateNumberOfNights(
          checkedOutCheckInDate.getValue(), checkedOutCheckOutDate.getValue());
      checkedOutNightsStayed.setText(String.valueOf(numberOfNightsStayed));
    }

  }

  /**
   * This function calculates the final price according to the nights stayed whether or not a given discount.
   * @author Rodrigo Reyes
   */
  @FXML private void checkOutCalculate(ActionEvent event)
  {
      /*sets the final price for the number of nights stayed , the given discount
      and if smoking fee added*/
    Guest guest1 = manager.searchCheckIn(checkOutSearchFirstName.getText(),
        checkOutSearchLastName.getText(), checkOutSearchPhoneNumber.getText());

    double price = manager.calculatePrice(checkedOutCheckInDate.getValue(),
        checkedOutCheckOutDate.getValue(), guest1.getRoomNumber(),
        Double.parseDouble(checkedOutDiscountAmount.getText()),
        checkOutSmoking.isSelected());
    checkedOutFinalPrice.setText(String.valueOf(price));
  }

  /**
   * This function deletes guest from the guest list and booking list.
   * @author Rodrigo Reyes
   */

  @FXML private void checkOutSave(ActionEvent event)
  {
    /*If all text fields are not filled then displays an alert message
    alerting that check in was not found or text fields are missing, otherwise
    removes check-in and booking from binary files and clears all fields afterwards*/
    if (checkOutSearchFirstName.getText().equals("")
        || checkOutSearchLastName.getText().equals("")
        || checkOutSearchPhoneNumber.getText().equals("")
        || checkedOutCheckInDate.getValue() == null
        || checkedOutCheckOutDate.getValue() == null
        || checkedOutNightsStayed.getText().equals("")
        || checkOutRoomNumber.getText().equals("")
        || checkedOutInitialPrice.getText().equals("")
        || checkedOutDiscountAmount.getText().equals("")
        || checkedOutFinalPrice.getText().equals(""))
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The OverLook Hotel");
      alert.setContentText("Fill all the fields");
      alert.showAndWait();
    }
    else if (manager.searchCheckIn(checkOutSearchFirstName.getText(),
        checkOutSearchLastName.getText(), checkOutSearchPhoneNumber.getText())
        == null)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The OverLook Hotel");
      alert.setContentText("The check-in was not found");
      alert.showAndWait();
    }
    else
    {
      Guest guest1 = manager.searchCheckIn(checkOutSearchFirstName.getText(),
          checkOutSearchLastName.getText(),
          checkOutSearchPhoneNumber.getText());

      manager.removeCheckIn(guest1.getRoomNumber());

      manager.deleteBookings(guest1.getFirstName(), guest1.getLastName(),
          guest1.getPhone());

      // exports to xml
      manager.exportRoomsToXML();

      checkOutClear();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The OverLook Hotel");
      alert.setContentText("Check Out saved");
      alert.showAndWait();
    }
  }

  /**
   * This function clear the text fields in the tab check-out
   * @author Rajib Paudyal
   */

  @FXML private void checkOutClear()
  {
    /*function to clear all text fields in the check-out tab */
    checkedOutCheckInDate.getEditor().clear();
    checkedOutCheckOutDate.getEditor().clear();
    checkedOutNightsStayed.clear();
    checkedOutInitialPrice.clear();
    checkedOutDiscountAmount.clear();
    checkedOutFinalPrice.clear();
    checkOutSearchFirstName.clear();
    checkOutSearchLastName.clear();
    checkOutSearchPhoneNumber.clear();
    checkOutTableView.getItems().clear();

  }

  // -------------------------- All check-ins tab starts from here ------------------------------

  /**
   * When the user clicks the button, get all the check-ins from the database and
   * display them in the table.
   *
   * @param event The event that triggered the action.
   * @author Pramesh Shrestha
   */

  @FXML private void allCheckInsButton(ActionEvent event)
  {
    //setting up columns of table
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

    //creating observableArrayList
    ObservableList<Guest> checkIns = FXCollections.observableArrayList();
    GuestList allCheckIns = manager.getAllCheckedIn();

    for (int i = 0; i < allCheckIns.getNumberOfGuest(); i++)
    {
      //adding guest that checked in into the observableArrayList
      checkIns.add(allCheckIns.getGuestByIndex(i));
    }
    checkInCombo.getSelectionModel().clearSelection();
    allCheckInTableView.setItems(checkIns);

  }

  /**
   * This function is used to set the room numbers to the combo box
   * @author Pramesh Shrestha
   */
  @FXML private void setRoomToComboBox()
  {
    //adding rooms to the combo box
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
   * @author Pramesh Shrestha
   * @param event The event that triggered the method.
   */
  @FXML private void getAllGuestsByRoomNumber(ActionEvent event)
  {
    //displays all the guests that checked-in in a certain room by room number
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

    //if a guest is check-in into the given room number, the guest is added into the observableArrayList and then displayed in the table
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
   * @author Rajib Paudyal
   */
  @FXML private void displayAllBookings()
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
   * @author Rajib Paudyal
   */
  @FXML private void removeBookingFromTable()
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

  /**
   * The function takes the date selected from the date picker and searches the
   * booking list for any bookings on that date. If there are bookings on that
   * date, the function displays them in a table view. If there are no bookings on
   * that date, the function displays an error message
   * @author Rajib Paudyal
   */
  @FXML private void bookingSearchForDay()
  {
    BookingList allBookings = manager.getAllBookings();
    ObservableList<Booking> bookingsOnDay = FXCollections.observableArrayList();
    for (int i = 0; i < allBookings.getTotalNumberOfBookings(); i++)
    {
      if (allBookings.getBookingByIndex(i).getArrivalDate()
          .isEqual(bookingSelectDate.getValue()))
      {
        bookingsOnDay.add(allBookings.getBookingByIndex(i));
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("The OverLook Hotel");
        alert.setContentText("No Bookings on " + bookingSelectDate.getValue());
        alert.showAndWait();
      }
    }

    // sets data to display for table columns
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

    allBookingsTableView.setItems(bookingsOnDay);
  }

  //----------------------------------Menu items starts here--------------------------------

  /**
   * The function asks the user for confirmation to close the program
   *
   * @param event The event that triggered the action.
   * @author Pramesh Shrestha
   */
  @FXML private void exitWindow(ActionEvent event)
  {
    //Ask user for the confirmation to close the program
    Alert alert = new Alert(Alert.AlertType.WARNING,
        "Do you really want to exit?", ButtonType.YES, ButtonType.NO);
    alert.setTitle("Overlook Hotel");
    alert.setHeaderText(null);
    alert.showAndWait();
    if (alert.getResult() == ButtonType.YES)
    {
      System.exit(0);
    }
  }

  // Clearing all the fields in the tabs.
  @FXML private void clearAllFields(ActionEvent event)
  {
    //clears all the tabs' fields
    bookingClear();
    checkInClear();
    checkOutClear();
  }

  /**
   * It opens a new window with the title "About the Software" and the content of
   * the About.fxml file
   * @author Pramesh Shrestha
   */
  @FXML private void about()
  {
    try
    {
      FXMLLoader fxmlLoader = new FXMLLoader(
          getClass().getResource("About.fxml"));
      Parent root = fxmlLoader.load();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.setResizable(false);
      stage.setTitle("About the Software");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.show();

    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }


}
