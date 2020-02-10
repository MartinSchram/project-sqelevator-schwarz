
package at.fhhagenberg.sqelevator.view;


import java.net.URL;
import java.security.InvalidParameterException;
import java.util.*;

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

public class ElevatorUI implements Initializable{

	@FXML
    private FlowPane flowPaneContainer;

	private Vector<OneElevatorUIController> m_ElevatorVector;

	private int CountElevatorStages;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        m_ElevatorVector=new Vector<OneElevatorUIController>();

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

            flowPaneContainer.getChildren().add(new CallsControll(CountStages));
            flowPaneContainer.getChildren().add(new StatusControll());
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



}
