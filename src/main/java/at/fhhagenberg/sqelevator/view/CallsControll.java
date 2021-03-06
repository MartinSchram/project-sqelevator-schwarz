package at.fhhagenberg.sqelevator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * @author Onur Polat Ui controller for the CallsUI
 *
 */
public class CallsControll extends AnchorPane {

    @FXML
    private VBox vBoxStages;

    private Image elevActiveCallDownArrow;
    private Image elevInactiveCallDownArrow;

    private int m_CountStages=0;


    /**
     * Constructor
     * @param CountStages Amount of Stages in the Building
     */
    public CallsControll(int CountStages) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CallsControll.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        m_CountStages=CountStages;
        CreateStages(CountStages);

    }

    /**
     * @param NumberStages
     * @throws InvalidParameterException
     */
    private void CreateStages(int NumberStages) throws InvalidParameterException {

        if(NumberStages>0){
            for(int i=0;i< NumberStages;i++){
                vBoxStages.getChildren().add(new CallsStageItem(i+1));
            }
        }
        else{
            throw new InvalidParameterException("Check input Parameter");
        }


    }


    public void SetStageDown(int StageNum,boolean Active){

        CallsStageItem stage= (CallsStageItem) vBoxStages.getChildren().get(StageNum-1);

        if(Active){
            stage.SetArrowActive(CallsStageItem.ArrowTypes.eDOWN);
        }
        else{
            stage.SetArrowInactive(CallsStageItem.ArrowTypes.eDOWN);
        }

    }


    public void SetStageUp(int StageNum,boolean Active){
        CallsStageItem stage= (CallsStageItem) vBoxStages.getChildren().get(StageNum-1);

        if(Active){
            stage.SetArrowActive(CallsStageItem.ArrowTypes.eUP);
        }
        else{
            stage.SetArrowInactive(CallsStageItem.ArrowTypes.eUP);
        }
    }
}
