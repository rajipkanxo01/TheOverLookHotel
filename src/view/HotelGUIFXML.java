package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelGUIFXML extends Application
{
  public void start(Stage window) throws IOException
  {
    window.setTitle("The Overlook Hotel");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("HotelGUI.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}
