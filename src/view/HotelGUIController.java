package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Guest;
import model.HotelModelManager;
import model.Room;
import model.RoomList;

import java.net.URL;
import java.time.LocalDate;
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

  // Check In tab private fields
  @FXML private TextField checkInAddress;
  @FXML private TableColumn<?, ?> checkInBookedBy;
  @FXML private DatePicker checkInDateOfBirth;
  @FXML private TextField checkInFirstName;
  @FXML private TextField checkInLastName;
  @FXML private TextField checkInNationality;
  @FXML private TableColumn<?, ?> checkInNumberColumn;
  @FXML private TextField checkInPhoneNumber;
  @FXML private TextField checkInSearchFirstName;
  @FXML private TextField checkInSearchLastName;
  @FXML private TextField checkInSearchPhoneNumber;
  @FXML private Tab checkInTab;
  @FXML private TableView<?> checkInTableView;

  // Check Out tab private fields
  @FXML private TableColumn<Guest, LocalDate> checkOutCheckedIn;
  @FXML private TableColumn<?, ?> checkOutColumnNumber;
  @FXML private TextField checkOutSearchFirstName;
  @FXML private TextField checkOutSearchLastName;
  @FXML private TextField checkOutSearchPhoneNumber;
  @FXML private Tab checkOutTab;
  @FXML private TableView<?> checkOutTableView;
  @FXML private DatePicker checkedOutCheckInDate;
  @FXML private DatePicker checkedOutCheckOutDate;
  @FXML private TextField checkedOutDiscountAmount;
  @FXML private TextField checkedOutFinalPrice;
  @FXML private TextField checkedOutInitialPrice;
  @FXML private TextField checkedOutNightsStayed;

  // Create booking tab private fields

  // Check in tab private fields

  // Check out tab private fields

  public void initialize(URL url, ResourceBundle resourceBundle)
  {

  }
  //   // -------------------------- room status methods starts from here ------------------------------

  /**
   * The function is called when the user clicks the "Next" button on the "Room"
   * tab. It sets the "Create Booking" tab as the selected tab and sets the arrival
   * and departure dates on the "Create Booking" tab to the arrival and departure
   * dates on the "Room" tab
   *
   * @param actionEvent The event that triggered the action.
   * @author Rajib Paudyal
   */
  @FXML private void roomNext(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModelNextButton = tabPane.getSelectionModel();
    selectionModelNextButton.select(createBooking);
    bookingArrivalDate.setValue(roomArrivalDate.getValue());
    bookingDepartureDate.setValue(roomDepartureDate.getValue());
  }

  /**
   * When user clicks a "back" button, the program will switch to "room status" tab
   *
   * @param actionEvent The event that triggered the action.
   * @author Rajib Paudyal
   */
  @FXML private void createBack(ActionEvent actionEvent)
  {
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
    SingleSelectionModel<Tab> selectionModelGoToCheckIn = tabPane.getSelectionModel();
    selectionModelGoToCheckIn.select(checkInTab);
  }

  @FXML void searchAvailableRooms()
  {
    intitializeTable();
  }

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

  @FXML private ObservableList<Room> getRoom()
  {
    LocalDate arrivalDate = roomArrivalDate.getValue();
    LocalDate departureDate = roomDepartureDate.getValue();
    boolean smoking = isSmoking.isSelected();

    RoomList allAvailableRooms = manager.getAllAvailableRooms(arrivalDate,
        departureDate, smoking);
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
   * @param actionEvent The action event that triggered the action.
   * @author Rajib Paudyal
   */
  @FXML private void clear(ActionEvent actionEvent)
  {
    bookingFirstName.clear();
    bookingLastName.clear();
    bookingPhoneNumber.clear();
    bookingAddress.clear();
    bookingNationality.clear();
    bookingDateOfBirth.getEditor().clear();
  }

  @FXML private void bookingSave(ActionEvent event)
  {
  }

  @FXML private void bookingClear(ActionEvent event)
  {
  }

  @FXML private void bookingGoToCheckIn(ActionEvent event)
  {
  }

  @FXML private void bookingBack(ActionEvent event)
  {
  }

  // -------------------------- check in methods starts from here ------------------------------

  /**
   * When the user clicks the "Check In" button, the tab pane will switch to the
   * "Create Booking" tab
   *
   * @param actionEvent This is the event that is triggered when the button is
   *                    clicked.
   * @author Rajib Paudyal
   */
  @FXML private void checkInBack(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionCheckInBackButton = tabPane.getSelectionModel();
    selectionCheckInBackButton.select(createBooking);

  }

  @FXML private void checkInSearch(ActionEvent event)
  {
  }

  @FXML private void checkInAdd(ActionEvent event)
  {
  }

  @FXML private void checkIn(ActionEvent event)
  {
  }

  // -------------------------- check out methods starts from here ------------------------------
  @FXML private void checkOutSave(ActionEvent event)
  {
  }

  @FXML private void checkOutCalculate(ActionEvent event)
  {
  }
}
