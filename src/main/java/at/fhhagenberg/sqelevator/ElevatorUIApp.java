package at.fhhagenberg.sqelevator;

import java.io.PrintWriter;
import java.io.StringWriter;

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

public class ElevatorUIApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("view/ElevatorUI.fxml"));

			Pane myPane = myLoader.load();
	        stage.setTitle("ControllerUI");
	        stage.setScene(new Scene(myPane, 400, 300));
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

	 public static void main(String[] args) {
		 launch();
	 }
	
}
