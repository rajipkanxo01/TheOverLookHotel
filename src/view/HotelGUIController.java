package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HotelGUIController
{
  @FXML DatePicker roomArrivalDate;
  @FXML DatePicker roomDepartureDate;
  @FXML DatePicker createArrivalDate;
  @FXML DatePicker createDepartureDate;
  @FXML TabPane tabPane;
  @FXML Tab roomStatus;
  @FXML Tab createBooking;


  public void next(ActionEvent actionEvent)
  {
    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(createBooking);
    createArrivalDate.setValue(roomArrivalDate.getValue());
    createDepartureDate.setValue(roomDepartureDate.getValue());
  }
}
