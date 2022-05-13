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

  private HotelModelManager manager = new HotelModelManager("rooms.bin", "guests.bin" , "bookings.bin");


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
  @FXML private TextField checkInRoomNumber;
  @FXML private Tab checkInTab;
  @FXML private TableView<Booking> checkInTableView;

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

  public void initialize(URL url, ResourceBundle resourceBundle)
  {
    // Configure the spinner with values 1 to 10
    SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
        0, 10, 0);
    this.bookingNumberOfGuest.setValueFactory(spinnerValueFactory);

    // creates hotel manager object
    HotelModelManager manager = new HotelModelManager();

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
  @FXML void searchAvailableRooms()
  {
    intitializeTable();
  }

  /**
   * The function intitializeTable() is called when the FXML file is loaded. It
   * sets the cell value factory for each column in the table view to the
   * appropriate property in the Room class
   *
   * @author Rajib Paudyal
   */
  @FXML private void intitializeTable()
  {
    roomStatusColumnPrice.setCellValueFactory(
        new PropertyValueFactory<Room, Double>("price"));
    roomStatusColumnType.setCellValueFactory(
        new PropertyValueFactory<Room, String>("type"));
    roomStatusColumnNumber.setCellValueFactory(
        new PropertyValueFactory<Room, String>("roomNumber"));
    roomStatusTableView.setItems(getRoom());
  }

  /**
   * It returns an ObservableList of Room objects that are available for the given
   * arrival and departure dates and smoking preference
   *
   * @return The method is returning an ObservableList of Room objects.
   * @author Rajib Paudyal
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
  }

  /**
   * It creates a booking from the information given in the fields
   *
   * @author Rajib Paudyal
   */
  @FXML private void bookingSave()
  {

    // get information from fields and assign it to the variable
    String bookingFirstNameText = bookingFirstName.getText();
    String bookingLastNameText = bookingLastName.getText();
    String bookingPhoneNumberText = bookingPhoneNumber.getText();
    String bookingNationalityText = bookingNationality.getText();
    String bookingAddressText = bookingAddress.getText();
    LocalDate bookingDateOfBirthValue = bookingDateOfBirth.getValue();
    boolean smokingSelected = bookingSmoking.isSelected();
    boolean extraBedSelected = bookingExtraBed.isSelected();
    Integer numberOfGuestValue = bookingNumberOfGuest.getValue();

    // get room according to the room number given in field
    RoomList allRooms = manager.getAllRooms();
    Room room = allRooms.getRoomByRoomNumber(bookingRoomNumber.getText());

    // get arrival and departure date
    LocalDate arrivalDateValue = bookingArrivalDate.getValue();
    LocalDate departureDateValue = bookingDepartureDate.getValue();

    // if any field is empty, gives an error message
    if (bookingFirstNameText.equals("") || bookingLastNameText.equals("")
        || bookingPhoneNumberText.equals("") || bookingNationalityText.equals(
        "") || bookingAddressText.equals("") || numberOfGuestValue == 0)
    {
      createBookingError.setText("Fields can't be empty.");
    }
    else
    {
      // create booking from the information given above
      manager.createBooking(extraBedSelected, (int) numberOfGuestValue,
          smokingSelected, room, bookingFirstNameText, bookingLastNameText,
          bookingAddressText, bookingPhoneNumberText, bookingNationalityText,
          bookingDateOfBirthValue, arrivalDateValue, departureDateValue);

      // change available status of booked room
      manager.updateRoomAvailable(bookingRoomNumber.getText(), arrivalDateValue,
          departureDateValue);

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
    SingleSelectionModel<Tab> selectionCheckInBackButton = tabPane.getSelectionModel();
    selectionCheckInBackButton.select(createBooking);
  }

  @FXML private void checkInSearch(ActionEvent event)
  {
    String firstName = checkInSearchFirstName.getText();
    String lastName = checkInSearchLastName.getText();
    String phoneNumber = checkInSearchPhoneNumber.getText();

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
        break;
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(
            "No booking found under " + firstName + " " + lastName + "'s name.");
        alert.showAndWait();
      }
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
    if(firstName.equals("") || lastName.equals("") || phoneNumber.equals("") ||
        nationality.equals("") || address.equals("") || dateOfBirth == null ||
        checkInDate == null || roomNumber.equals(""))
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText(null);
      alert.setContentText("All fields required.");
      alert.showAndWait();
    }
    //creates check in and then clears all the text fields
    else
    {
      manager.createCheckIn(firstName,lastName,address,phoneNumber,nationality,dateOfBirth,checkInDate,roomNumber);
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
   * It clears all the text fields in the check in tab
   *
   * @param event The event that triggered the action.
   */
  @FXML private void checkInClear(ActionEvent event)
  {
    checkInFirstName.clear();
    checkInLastName.clear();
    checkInPhoneNumber.clear();
    checkInNationality.clear();
    checkInAddress.clear();
    checkInRoomNumber.clear();
    checkInDateOfBirth.getEditor().clear();
    checkedOutCheckInDate.getEditor().clear();
  }


  // -------------------------- check out methods starts from here ------------------------------

  @FXML private void searchCheckIn(ActionEvent event)
  {
    Guest guest1 = manager.searchCheckIn(checkOutSearchFirstName.getText(),
        checkOutSearchLastName.getText(),checkOutSearchPhoneNumber.getText());

    checkOutColumnNumber.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getRoomNumber()));
    checkOutCheckedIn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getCheckInDate().toString()));

    ObservableList<Guest> guest = FXCollections.observableArrayList();
    guest.add(new Guest(guest1.getRoomNumber(),guest1.getCheckInDate()));
    checkOutTableView.setItems(guest);
  }

  @FXML private void checkOutSave(ActionEvent event)
  {

  }

  @FXML private void checkOutCalculate(ActionEvent event)
  {
  }
}
