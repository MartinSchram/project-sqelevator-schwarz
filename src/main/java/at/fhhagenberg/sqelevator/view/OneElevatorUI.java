package at.fhhagenberg.sqelevator.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class OneElevatorUI implements Initializable {

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



    private Image elevCloseDoorImg;
    private Image elevOpenDoorImg;
    public boolean DoorsOpen;

//    public OneElevatorUI() {
//        super();
//
//        InputStream in= getClass().getResourceAsStream("../images/elevCloseDoor.png");
//        elevCloseDoorImg = new Image(in);
//        in= getClass().getResourceAsStream("../images/elevOpenDoor.png");
//        elevOpenDoorImg= new Image(in);
//        imgViewDoors1.setImage(elevCloseDoorImg);
//        DoorsOpen = false;
//
//    }


    @FXML
    void onBtnPressedHandle(ActionEvent event) {
        ToogleDoor();
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputStream in= getClass().getResourceAsStream("../images/elevCloseDoor.png");
        elevCloseDoorImg = new Image(in);
        in= getClass().getResourceAsStream("../images/elevOpenDoor.png");
        elevOpenDoorImg= new Image(in);
        imgViewDoors1.setImage(elevCloseDoorImg);
        DoorsOpen = false;
    }
}
