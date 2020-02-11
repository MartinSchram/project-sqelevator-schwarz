package at.fhhagenberg.sqelevator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.Naming;

import at.fhhagenberg.sqelevator.controller.ElevatorController;
import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.view.ElevatorUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import sqelevator.IElevator;

public class MainAppClass extends Application {



	public static void main(String[] args) {

		launch();
//		// init gui
//		// init bindings
//		//
//		int ELEVATORSCOUNT =3;
//		int FLOORS = 5;
//		ElevatorController ev = new ElevatorController(ELEVATORSCOUNT,FLOORS);
//		// init bindings -> for(i<ELEVATORSCOUNT) ElevatorController.GuiActions[i] = gui binding
//		ev.init(null);
//		// ev.addObserver(GUI);
//		// new thread start: ev.RunCyclic();

	}



	private static ElevatorUI m_UiController;
	private static ElevatorController m_ElevatorConnectionController;

	private static Thread PollingThread;

	public Runnable runnable = () -> {
		m_ElevatorConnectionController.RunCyclic();
	};



	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		try {

			// initializing Ui
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("view/ElevatorUI.fxml"));
			Pane myPane = myLoader.load();

			m_UiController= myLoader.<ElevatorUI>getController();

			// Elevator Controller
			m_ElevatorConnectionController=new ElevatorController(m_UiController);
			m_ElevatorConnectionController.ConnectRMI();
			int FloorCount=  m_ElevatorConnectionController.BackEnd.getFloorNum();
			int ElevatorCount= m_ElevatorConnectionController.BackEnd.getElevatorNum();

			// setup Ui with the count of Elevator and Floor count
			m_UiController.SetupUi(ElevatorCount,FloorCount);
//			m_ElevatorConnectionController.addObserver(m_UiController);
			m_ElevatorConnectionController.SwitchOn();
			PollingThread = new Thread(runnable);
			PollingThread.start();
//			m_ElevatorConnectionController.RunCyclic();
//			m_UiController.SetPayload(0,100);

			stage.setTitle("ControllerUI");
			stage.setScene(new Scene(myPane, myPane.getWidth(), myPane.getHeight()));
			stage.minHeightProperty().setValue(300);
			stage.minWidthProperty().setValue(250);
			stage.show();
		}
		catch (Exception ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("Exception happens");
			alert.setContentText("");

			// Create expandable Exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String exceptionText = sw.toString();
			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);
			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);

			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);
			GridPane expContent = new GridPane();

			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);
			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);
			alert.showAndWait();

		}


	}


}
