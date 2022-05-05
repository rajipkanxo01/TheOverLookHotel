package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.time.LocalDate;

/**
 * @author
 * @version 1.0.0
 */

public class HotelGUIController
{

  // Room status tab private fields
  @FXML DatePicker roomArrivalDate;
  @FXML DatePicker roomDepartureDate;
  @FXML TabPane tabPane;
  @FXML Tab roomStatus;

  // Create booking tab private fields
  @FXML Tab createBooking;
  @FXML DatePicker createArrivalDate;
  @FXML DatePicker createDepartureDate;
  @FXML TextField createFirstName;
  @FXML TextField createLastName;
  @FXML TextField createPhoneNumber;
  @FXML TextField createNationality;
  @FXML TextField createAddress;
  @FXML DatePicker createDateOfBirth;
  @FXML Spinner<Integer> createNumberOfGuest;

  // Check in tab private fields
  @FXML Tab checkInTab;

  // Check out tab private fields

  /**
   * The function is called when the user clicks the "Next" button on the "Room"
   * tab. It sets the "Create Booking" tab as the selected tab and sets the arrival
   * and departure dates on the "Create Booking" tab to the arrival and departure
   * dates on the "Room" tab
   *
   * @param actionEvent The event that triggered the action.
   * @author Rajib Paudyal
   */
  public void next(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModelNextButton = tabPane.getSelectionModel();
    selectionModelNextButton.select(createBooking);
    createArrivalDate.setValue(roomArrivalDate.getValue());
    createDepartureDate.setValue(roomDepartureDate.getValue());
  }

  /**
   * It clears all the text fields and the date picker
   *
   * @author Rajib Paudyal
   * @param actionEvent The action event that triggered the action.
   */
  public void clear(ActionEvent actionEvent)
  {
    createFirstName.clear();
    createLastName.clear();
    createPhoneNumber.clear();
    createAddress.clear();
    createNationality.clear();
    createDateOfBirth.getEditor().clear();
    createNumberOfGuest.cancelEdit();
  }

  /**
   * When the user clicks the "Check In" button, the program will switch to the
   * "Check In" tab.
   *
   * @param actionEvent This is the event that is triggered when the button is
   *                    clicked.
   * @author Rajib Paudyal
   */
  public void goToCheckIn(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModelGoToCheckIn = tabPane.getSelectionModel();
    selectionModelGoToCheckIn.select(checkInTab);
  }

  /**
   * When user clicks a "back" button, the program will switch to "room status" tab
   *
   * @param actionEvent The event that triggered the action.
   * @author Rajib Paudyal
   */
  public void createBack(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModelCreateBackButton = tabPane.getSelectionModel();
    selectionModelCreateBackButton.select(roomStatus);
  }

  /**
   * When the user clicks the "Check In" button, the tab pane will switch to the
   * "Create Booking" tab
   *
   * @param actionEvent This is the event that is triggered when the button is
   *                    clicked.
   * @author Rajib Paudyal
   */
  public void checkInBack(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionCheckInBackButton = tabPane.getSelectionModel();
    selectionCheckInBackButton.select(createBooking);
  }
}
