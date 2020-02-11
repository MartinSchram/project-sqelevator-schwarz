
package at.fhhagenberg.sqelevator.view;


import java.net.URL;
import java.security.InvalidParameterException;
import java.util.*;

import at.fhhagenberg.sqelevator.model.*;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ElevatorUI implements Initializable, Observer {

	@FXML
    private FlowPane flowPaneContainer;

    public ObservableList<OneElevatorUIController> m_ElevatorVector=FXCollections.observableArrayList();
//    public Vector<OneElevatorUIController> m_ElevatorVector;


	private int CountElevatorStages;

    private CallsControll m_ViewCallsCtrl;
    private StatusControll m_ViewStatusCtrl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//        m_ElevatorVector=new Vector<OneElevatorUIController>();

	}


    /**
     * @param CountElevators
     * @param CountStages
     * @throws InvalidParameterException
     */
    public void SetupUi(int CountElevators, int CountStages) throws InvalidParameterException{

	    if(CountElevators>0 && CountStages>0){
            for(int i=0;i<CountElevators;i++){
                String ElevatorName="Elevator "+ (i+1);

                OneElevatorUIController elem= new OneElevatorUIController(CountStages,ElevatorName);
                m_ElevatorVector.add(elem);
                flowPaneContainer.getChildren().add(elem);
            }

            CountElevatorStages=CountStages;

            m_ViewCallsCtrl=new CallsControll(CountStages);
            m_ViewStatusCtrl=new StatusControll();

            flowPaneContainer.getChildren().add(m_ViewCallsCtrl);
            flowPaneContainer.getChildren().add(m_ViewStatusCtrl);
        }
	    else{
            throw new InvalidParameterException("Check the Parameters");
        }

    }

    /**
     * @param ElevNum Number of the Elevator for Setting the Paylod
     * @param Payload Payload to Set
     * @throws IndexOutOfBoundsException
     */
    public void SetPayload(int ElevNum,int Payload) throws IndexOutOfBoundsException {

	    if (ElevNum < CountElevatorStages || ElevNum > CountElevatorStages){
            m_ElevatorVector.get(ElevNum).SetPayloadLabel(Payload);
        }
	    else{
	        throw new IndexOutOfBoundsException("Elevator Number is not in Range");
        }
    }

    /**
     * @param ElevNum Number of the Elevator for Setting the Speed
     * @param SpeedValue Speed to Set
     * @throws IndexOutOfBoundsException
     */
    public void SetSpeed(int ElevNum,int SpeedValue) throws IndexOutOfBoundsException {

        if (ElevNum < CountElevatorStages || ElevNum > CountElevatorStages){
            m_ElevatorVector.get(ElevNum).SetVelocity(SpeedValue);
        }
        else{
            throw new IndexOutOfBoundsException("Elevator Number is not in Range");
        }
    }



    private void SetStageCalls(BuildingData StageDownCalls,BuildingData StageUpCalls){

        for(int i = 0;i<CountElevatorStages;i++){
            m_ViewCallsCtrl.SetStageDown(i+1,StageDownCalls.GetStatus(i));
            m_ViewCallsCtrl.SetStageUp(i+1,StageUpCalls.GetStatus(i));
        }


    }

    private void SetElevatorDoorState(int ElevNum,int DoorStatus){
        if(DoorStatus==1){
            m_ElevatorVector.get(ElevNum).SetDoorState(OneElevatorUIController.DoorsState.eOpen);
        }
        else{
            m_ElevatorVector.get(ElevNum).SetDoorState(OneElevatorUIController.DoorsState.eClosen);
        }

    }

    private void SetElevatorDirection(int ElevNum,boolean DirectionUp){

        if(DirectionUp){
            m_ElevatorVector.get(ElevNum).SetElevatorDirection(OneElevatorUIController.ElevatorDir.eUp);
        }
        else{
            m_ElevatorVector.get(ElevNum).SetElevatorDirection(OneElevatorUIController.ElevatorDir.eDown);
        }

    }

    private void SetElevatorCurrFloor(int ElevNum,int StageNum){
        m_ElevatorVector.get(ElevNum).SetElevatorCurrentState(StageNum);
    }

    private void UpdateElevators(ElevatorData[] elevatorData){

        for (var elem:  elevatorData) {
            int ElevNumber=elem.GetElevatornumber();
            SetPayload(ElevNumber,elem.GetElevatorweight());
            SetSpeed(ElevNumber,elem.GetElevatorspeed());

            int DoorStatus=elem.GetElevatordoorstatus();
            SetElevatorDoorState(ElevNumber,DoorStatus);
            SetElevatorDirection(ElevNumber,elem.GetCommittedDirIsUp());
            SetElevatorCurrFloor(ElevNumber,elem.GetElevatorfloor());
//            if(elem.GetCommittedDirIsUp()){
//                m_ElevatorVector.get(ElevNumber).SetElevatorDirection(OneElevatorUIController.ElevatorDir.eUp);
//            }
//            else{
//                m_ElevatorVector.get(ElevNumber).SetElevatorDirection(OneElevatorUIController.ElevatorDir.eDown);
//            }

        }

    }

    @Override
    public void update(Observable o, Object arg) {
        SystemData Data=(SystemData)arg;


        Platform.runLater(new Runnable() {
            public void run() {
                UpdateElevators(Data.GetElevatorData());

            }
        });



        SetStageCalls(Data.GetBuildingButtonDownData(),Data.GetBuildingButtonUpData());

    }



}
