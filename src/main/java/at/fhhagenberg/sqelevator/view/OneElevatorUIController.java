package at.fhhagenberg.sqelevator.view;

import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author Onur Polat Ui controller for one Elevator
 *
 */
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
    private VBox StagesVBox;

    public int CountStages = 0;
    private Image elevCloseDoorImg;
    private Image elevOpenDoorImg;

    private Image elevOpModeAutoImg;
    private Image elevOpModeManualImg;

    private Image elevStageEmpty;
    private Image elevStageFull;

    private Image elevDirArrowUp;
    private Image elevDirArrowDown;


    private ObservableList<ToggleButton> m_StagesList=FXCollections.observableArrayList();
    private ObservableList<ImageView> m_StagesStatusList=FXCollections.observableArrayList();
    private int mCountStages=0;

    public ElevatorActions oneElevAction;

    /**
     * Constructor for one Elevator
     * @param Stages
     * @param ElevatorName
     */
    public OneElevatorUIController(int Stages, String ElevatorName) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OneElevatorUI.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        InputStream in = getClass().getResourceAsStream("../images/elevCloseDoor.png");
        elevCloseDoorImg = new Image(in);
        in = getClass().getResourceAsStream("../images/elevOpenDoor.png");
        elevOpenDoorImg = new Image(in);
        in = getClass().getResourceAsStream("../images/OpModeAuto.png");
        elevOpModeAutoImg = new Image(in);
        in = getClass().getResourceAsStream("../images/OpModeManual.png");
        elevOpModeManualImg = new Image(in);
        in = getClass().getResourceAsStream("../images/EmptyStage.png");
        elevStageEmpty = new Image(in);
        in = getClass().getResourceAsStream("../images/FullStage.png");
        elevStageFull = new Image(in);
        in = getClass().getResourceAsStream("../images/ArrowDown.png");
        elevDirArrowDown = new Image(in);
        in = getClass().getResourceAsStream("../images/ArrowUp.png");
        elevDirArrowUp = new Image(in);

        imgViewDoors.setImage(elevCloseDoorImg);

        tbtnStatusManual.setSelected(true);
        SetOpCurrentOpMode(OperationModes.eMANUAL);

        InitilizeElevator(Stages, ElevatorName);

        oneElevAction=new ElevatorActions();
        oneElevAction.ServicesFloors=new boolean[mCountStages];
        for(int i=0;i<mCountStages;i++){
            oneElevAction.ServicesFloors[i]=true;
        }

    }

    /**
     * Sets the Stages on the Elevator
     *
     * @param CountStages  Count of Stages of the Elevator
     * @param elevatorName Elevator Name
     * @throws InvalidParameterException Throws an Exception when CountStages==0 or elevatorName==""
     */
    private void InitilizeElevator(int CountStages, String elevatorName) throws InvalidParameterException {

        if (CountStages > 0 && elevatorName != "") {
            mCountStages = CountStages;
            lblElevatorName.setText(elevatorName);
            for (int i = 0; i < CountStages; i++) {
                HBox Temp=new HBox();

                ToggleButton ToggleBtn=new ToggleButton();
                ToggleBtn.setPrefWidth(50);
                ToggleBtn.setText(Integer.toString(i+1));
                ToggleBtn.setOnAction(StageButtonEventHandler);

                ImageView img=new ImageView();
                img.setImage(elevStageEmpty);
                m_StagesList.add(ToggleBtn);
                m_StagesStatusList.add(img);
                Temp.getChildren().add(ToggleBtn);
                Temp.getChildren().add(img);
                StagesVBox.getChildren().add(0,Temp);

            }

        }
        else {
            throw new InvalidParameterException("Check Method Parameter");
        }

    }


    // Creating the mouse event handler
    EventHandler<ActionEvent> StageButtonEventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {

            ToggleButton selectedBTN = (ToggleButton) e.getTarget();
            int numberStagePressed= Integer.parseInt(selectedBTN.getText());

            for(int i=0;i<mCountStages;i++){
                if((i+1)==numberStagePressed){
                    m_StagesList.get(i).setSelected(true);
                }
                else{
                    m_StagesList.get(i).setSelected(false);
                }
            }
            oneElevAction.TargetFl=numberStagePressed-1;
        }
    };


    public enum OperationModes {
        eAUTO,
        eMANUAL
    }

    public enum DoorsState {
        eOpen,
        eClosen
    }

    public enum ElevatorDir {
        eUp,
        eDown
    }


    @FXML
    void onBtnPressedHandle(ActionEvent event) {

        if(event.getTarget().equals(tbtnStatusAutomatic)){
            if(!oneElevAction.AutoMode) {
                oneElevAction.AutoMode = true;
                tbtnStatusManual.setSelected(false);
                SetOpCurrentOpMode(OperationModes.eAUTO);
            }
            else{
                tbtnStatusAutomatic.setSelected(true);
            }
        }
        else{
            if(oneElevAction.AutoMode) {
                oneElevAction.AutoMode = false;
                tbtnStatusAutomatic.setSelected(false);
                SetOpCurrentOpMode(OperationModes.eMANUAL);
            }
            else {
                tbtnStatusManual.setSelected(true);
            }
        }

    }

    /**
     * Sets the Current state of the elevator on the UI
     * @param currState Current Stage of the Elevator To Set
     * @throws InvalidParameterException Elevator Current Stage
     */
    public void SetElevatorCurrentState(int currState) throws InvalidParameterException {

        if(currState>=0 && currState< mCountStages) {
            for (int i = 0; i < mCountStages; i++) {
                if (i == currState) {
                    m_StagesStatusList.get(i).setImage(elevStageFull);
                } else {
                    m_StagesStatusList.get(i).setImage(elevStageEmpty);
                }
            }
        }
        else{
            throw new InvalidParameterException("Elevator Current Stage Invalid Parameter");
        }

    }

    /**
     * Sets the Elevator
     * @param DirectionToShow State of the Doors
     * @throws InvalidParameterException when Method Parameter is not a value defined in ElevatorDir enum
     */
    public void SetElevatorDirection(ElevatorDir DirectionToShow) throws InvalidParameterException {

        switch (DirectionToShow) {
            case eUp:
                imgViewDir.setImage(elevDirArrowUp);
                break;
            case eDown:
                imgViewDir.setImage(elevDirArrowDown);
                break;
            default:
                throw new InvalidParameterException("Check Parameter");
        }
    }

    /**
     * Setting Elevator Mode in UI
     *
     * @param modeToSet Operation Mode use the defined enum OperationModes
     * @throws InvalidParameterException when Method Parameter is not a value defined in OperationMode enum
     */
    public void SetOpCurrentOpMode(OperationModes modeToSet) throws InvalidParameterException {

        switch (modeToSet) {
            case eAUTO:
                imgVOpMode.setImage(elevOpModeAutoImg);
                break;
            case eMANUAL:
                imgVOpMode.setImage(elevOpModeManualImg);
                break;
            default:
                throw new InvalidParameterException("Check Parameter");
        }

    }

    /**
     * Setting the State of the Door Image
     *
     * @param StateToSet Operation Mode use the defined enum DoorsState
     * @throws InvalidParameterException when Method Parameter is not a value defined in DoorsState enum
     */
    public void SetDoorState(DoorsState StateToSet) throws InvalidParameterException {


        switch (StateToSet) {
            case eClosen:
                imgViewDoors.setImage(elevOpenDoorImg);
                break;
            case eOpen:
                imgViewDoors.setImage(elevCloseDoorImg);
                break;
            default:
                throw new InvalidParameterException("Check Parameter");
        }

    }

    /**
     * Setting Payload on view
     *
     * @param PayLoadToSet Value of the Payload for showing
     * @throws InvalidParameterException PayLoadToSet is invalid when value is <0
     */
    public void SetPayloadLabel(int PayLoadToSet) throws InvalidParameterException {

        if(PayLoadToSet >= 0){
            lblPayload.setText(Integer.toString(PayLoadToSet));
        }
        else {
            throw new InvalidParameterException("Payload is not Valid ");
        }

    }



    /**
     * Setting Velocity on Elevator Window
     *
     * @param VelocityToSet Sets the value of the
     */
    public void SetVelocity(int VelocityToSet) {

        lblVelocity.setText(Integer.toString(VelocityToSet));

    }


}
