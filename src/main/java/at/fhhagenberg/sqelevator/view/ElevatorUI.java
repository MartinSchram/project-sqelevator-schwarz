
package at.fhhagenberg.sqelevator.view;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class ElevatorUI implements Initializable{

    @FXML
    private Label lblVelocity2;

    @FXML
    private Label lblVelocity1;

    @FXML
    private ToggleButton tbtnStatusAutomatic1;

    @FXML
    private Group ElevGroup2;

    @FXML
    private Label lblPayload2;

    @FXML
    private Group ElevGroup1;

    @FXML
    private ToggleButton tbtnStatusAutomatic2;

    @FXML
    private Label lblPayload1;

    @FXML
    private ImageView imgViewDoors1;

    @FXML
    private ImageView imgViewDoors2;

    @FXML
    private ToggleButton tbtnOpModeManual2;

    @FXML
    private ToggleButton tbtnOpModeAuto2;

    @FXML
    private ToggleButton tbtnOpModeAuto1;

    @FXML
    private ToggleButton tbtnOpModeManual1;

    @FXML
    private ToggleButton tbtnStatusManual1;

    @FXML
    private ImageView imgViewDir2;

    @FXML
    private ImageView imgViewDir1;

    @FXML
    private ToggleButton tbtnStatusManual2;

    @FXML
    private javafx.scene.layout.FlowPane mFlowPane;

    private Image elevCloseDoorImg;
    private Image elevOpenDoorImg;
    public boolean DoorsOpen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

//        imgViewDoors=new ImageView(image);
//        imgViewDors.setImage(image);
//        imgViewDors.setRotate(imgViewDors.getRotate() + 90);
//        System.out.println( getClass().getResource(getClass().getSimpleName() + ".class") );

//        File file = new File("../images/ArrowDown.png");

        // load the pictures into the class
        InputStream in= getClass().getResourceAsStream("../images/elevCloseDoor.png");
        elevCloseDoorImg = new Image(in);
        in= getClass().getResourceAsStream("../images/elevOpenDoor.png");
        elevOpenDoorImg= new Image(in);
        imgViewDoors1.setImage(elevCloseDoorImg);
        DoorsOpen = false;



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


    public void onBtnPressedHandle(ActionEvent actionEvent) {
        double value = imgViewDoors1.getRotate();
        imgViewDoors1.setRotate(value+180.0);
        ToogleDoor();


	}

}
