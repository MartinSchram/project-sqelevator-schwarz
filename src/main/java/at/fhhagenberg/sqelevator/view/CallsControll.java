package at.fhhagenberg.sqelevator.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CallsControll extends HBox {

    public CallsControll() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CallsControll.fxml"));
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
