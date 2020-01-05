
package at.fhhagenberg.sqelevator.view;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ElevatorUI implements Initializable{

    @FXML
    private ToggleButton tbtnStatusAutomatic;

    @FXML
    private ImageView imgViewDoors;

    @FXML
    private ImageView imgViewDir;

    @FXML
    private ToggleButton tbtnOpModeManual;

    @FXML
    private ToggleButton tbtnStatusManual;

    @FXML
    private Label lblPayload;

    @FXML
    private Label lblVelocity;

    @FXML
    private ToggleButton tbtnOpModeAuto;


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
        InputStream in= getClass().getResourceAsStream("../images/elevCloseDoor.png");
        elevCloseDoorImg = new Image(in);
        in= getClass().getResourceAsStream("../images/elevOpenDoor.png");
        elevOpenDoorImg= new Image(in);
        imgViewDoors.setImage(elevCloseDoorImg);
        DoorsOpen = false;

	}

    public void ToogleDoor(){
        if(DoorsOpen){
            imgViewDoors.setImage(elevOpenDoorImg);
            DoorsOpen=false;
        }
        else{
            imgViewDoors.setImage(elevCloseDoorImg);
            DoorsOpen=true;
        }
    }


    public void onBtnPressedHandle(ActionEvent actionEvent) {
        double value = imgViewDir.getRotate();
        imgViewDir.setRotate(value+180.0);
        ToogleDoor();


	}

}
