package at.fhhagenberg.sqelevator.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class StatusControll extends AnchorPane {

    public StatusControll(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatusControll.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
