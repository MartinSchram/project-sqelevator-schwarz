package at.fhhagenberg.sqelevator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OneElevatorUIController extends AnchorPane {

    @FXML
    private ToggleButton tbtnStatusAutomatic;

    @FXML
    private ImageView imgViewDir;

    @FXML
    private Label lblElevatorName;

    @FXML
    private ImageView imgVOpMode;

    @FXML
    private ImageView imgViewDoors;



    @FXML
    private ToggleButton tbtnStatusManual;

    @FXML
    private Label lblPayload;

    @FXML
    private Label lblVelocity;

    @FXML
    private ImageView iViewStage1;

    @FXML
    private ImageView iViewStage3;

    @FXML
    private ImageView iViewStage2;

    @FXML
    private ImageView iViewStage4;


    private Image elevCloseDoorImg;
    private Image elevOpenDoorImg;

    private Image elevOpModeAutoImg;
    private Image elevOpModeManualImg;

    private Image elevStageEmpty;
    private Image elevStageFull;

    private boolean DoorsOpen;

    private ArrayList<ImageView> mlistStages=new ArrayList<ImageView>();

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
        in= getClass().getResourceAsStream("../images/OpModeAuto.png");
        elevOpModeAutoImg= new Image(in);
        in= getClass().getResourceAsStream("../images/OpModeManual.png");
        elevOpModeManualImg= new Image(in);
        in= getClass().getResourceAsStream("../images/EmptyStage.png");
        elevStageEmpty= new Image(in);
        in= getClass().getResourceAsStream("../images/FullStage.png");
        elevStageFull= new Image(in);

        imgViewDoors.setImage(elevCloseDoorImg);
        DoorsOpen = false;

        SetOpCurrentOpMode(OperationMode.eAUTO);

        mlistStages.add(iViewStage4);
        mlistStages.add(iViewStage3);
        mlistStages.add(iViewStage2);
        mlistStages.add(iViewStage1);

        mlistStages.get(0).setImage(elevStageFull);

    }

    public enum OperationMode{
        eAUTO,
        eMANUAL
    }

    public void SetOpCurrentOpMode(OperationMode modeToSet){
        switch (modeToSet){
            case eAUTO:
                imgVOpMode.setImage(elevOpModeAutoImg);
                break;
            case eMANUAL:
                imgVOpMode.setImage(elevOpModeManualImg);
                break;
            default:

        }

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
            imgViewDoors.setImage(elevOpenDoorImg);
            DoorsOpen=false;
        }
        else{
            imgViewDoors.setImage(elevCloseDoorImg);
            DoorsOpen=true;
        }
    }


    public void ToogleImageDir(){
        imgViewDir.setRotate(imgViewDir.getRotate()+180);
    }


}
