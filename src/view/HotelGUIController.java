package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * @version  1.0.0
 */


public class HotelGUIController
{

  // Room status tab private fields
  @FXML DatePicker roomArrivalDate;
  @FXML DatePicker roomDepartureDate;
  @FXML DatePicker createArrivalDate;
  @FXML DatePicker createDepartureDate;
  @FXML TabPane tabPane;
  @FXML Tab roomStatus;
  @FXML Tab createBooking;
  @FXML Tab checkInTab;




  // Create booking tab private fields




  // Check in tab private fields



  // Check out tab private fields


  /**
   * The function is called when the user clicks the "Next" button on the "Room"
   * tab. It sets the "Create Booking" tab as the selected tab and sets the arrival
   * and departure dates on the "Create Booking" tab to the arrival and departure
   * dates on the "Room" tab
   *
   * @param actionEvent The event that triggered the action.
   */
  public void next(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(createBooking);
    createArrivalDate.setValue(roomArrivalDate.getValue());
    createDepartureDate.setValue(roomDepartureDate.getValue());
  }

  public void clear(ActionEvent actionEvent)
  {

  }



  /**
   * When the user clicks the "Check In" button, the program will switch to the
   * "Check In" tab.
   *
   * @param actionEvent This is the event that is triggered when the button is
   * clicked.
   */
  public void goToCheckIn(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionCheckIn = tabPane.getSelectionModel();
    selectionCheckIn.select(checkInTab);
  }

  public void createBack(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(roomStatus);
  }
}
