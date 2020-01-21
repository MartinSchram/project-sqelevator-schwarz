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
import java.util.ArrayList;

public class CallsControll extends AnchorPane {

//    @FXML
//    private ImageView arrDown4;
//
//    @FXML
//    private ImageView arrDown3;
//
//    @FXML
//    private ImageView arrUp1;
//
//    @FXML
//    private ImageView arrUp0;
//
//    @FXML
//    private ImageView arrUp3;
//
//    @FXML
//    private ImageView arrUp2;
//
//    @FXML
//    private ImageView arrUp4;
//
//    @FXML
//    private ImageView arrDown2;
//
//    @FXML
//    private ImageView arrDown1;
//
//    @FXML
//    private ImageView arrDown0;
    @FXML
    private VBox vBoxStages;




//    private ArrayList<ImageView> mlistStagesCallDownImgs=new ArrayList<ImageView>();
//    private ArrayList<ImageView> mlistStagesCallUpImgs=new ArrayList<ImageView>();

    private Image elevActiveCallDownArrow;
    private Image elevInactiveCallDownArrow;

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


        CreateStages(15);

        SetStageDownActive(2);
        SetStageUpActive(9);

    }

    private void CreateStages(int NumberStages) {

        for(int i=0;i<=NumberStages;i++){
            vBoxStages.getChildren().add(new CallsStageItem(i));
        }

    }


    private void SetStageDownActive(int StageNum){
        CallsStageItem stage= (CallsStageItem) vBoxStages.getChildren().get(StageNum);
        stage.SetArrowActive(CallsStageItem.ArrowTypes.eDOWN);
    }


    private void SetStageUpActive(int StageNum){
        CallsStageItem stage= (CallsStageItem) vBoxStages.getChildren().get(StageNum);
        stage.SetArrowActive(CallsStageItem.ArrowTypes.eUP);
    }
}
