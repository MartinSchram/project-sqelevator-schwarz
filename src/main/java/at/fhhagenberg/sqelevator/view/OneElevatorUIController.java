package at.fhhagenberg.sqelevator.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class OneElevatorUIController extends AnchorPane {

    @FXML
    private ToggleButton tbtnOpModeAuto1;

    @FXML
    private ToggleButton tbtnOpModeManual1;

    @FXML
    private Label lblVelocity1;

    @FXML
    private ToggleButton tbtnStatusAutomatic1;

    @FXML
    private ToggleButton tbtnStatusManual1;

    @FXML
    private ImageView imgViewDir1;

    @FXML
    private Label lblPayload1;

    @FXML
    private ImageView imgViewDoors1;

    @FXML
    private Label lblElevatorName;

    private Image elevCloseDoorImg;
    private Image elevOpenDoorImg;
    private boolean DoorsOpen;

    public OneElevatorUIController(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OneElevatorUI.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        InputStream in= getClass().getResourceAsStream("../images/elevCloseDoor.png");
        elevCloseDoorImg = new Image(in);
        in= getClass().getResourceAsStream("../images/elevOpenDoor.png");
        elevOpenDoorImg= new Image(in);
        imgViewDoors1.setImage(elevCloseDoorImg);
        DoorsOpen = false;

    }




    public void SetElevatorName(String name) {
        lblElevatorName.setText(name);
    }

    @FXML
    void onBtnPressedHandle(ActionEvent event) {
        ToogleDoor();
        ToogleImageDir();
    }

    public void ToogleDoor(){
        if(DoorsOpen){
            imgViewDoors1.setImage(elevOpenDoorImg);
            DoorsOpen=false;
        }
        else{
            imgViewDoors1.setImage(elevCloseDoorImg);
            DoorsOpen=true;
        }
    }


    public void ToogleImageDir(){
        imgViewDir1.setRotate(imgViewDir1.getRotate()+180);
    }


}
