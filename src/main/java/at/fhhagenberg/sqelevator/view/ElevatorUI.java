
package at.fhhagenberg.sqelevator.view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ElevatorUI implements Initializable{

    @FXML
    private OneElevatorUIController elev1;

    @FXML
    private OneElevatorUIController elev2;

    @FXML
    private CallsControll callsView;

	@FXML
	private StatusControll statusControll;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

        elev1.SetElevatorName("Elevator 1");
        elev2.SetElevatorName("Elevator 2");

	}


}
