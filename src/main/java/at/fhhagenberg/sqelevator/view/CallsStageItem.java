package at.fhhagenberg.sqelevator.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class CallsStageItem  extends AnchorPane {

    @FXML
    private ImageView imgVArrowDown;

    @FXML
    private ImageView imgVArrowUp;

    @FXML
    private Label lblStage;

    public enum ArrowTypes{
        eUP,
        eDOWN
    }

    private Image elevActiveCallDownArrow;
    private Image elevInactiveCallDownArrow;

    public CallsStageItem(int StageNum) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CallsStageItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }

        InputStream in= getClass().getResourceAsStream("../images/ArrowStagesDownActive.png");
        elevActiveCallDownArrow = new Image(in);
        in= getClass().getResourceAsStream("../images/ArrowStagesDown.png");
        elevInactiveCallDownArrow = new Image(in);

        lblStage.setText(Integer.toString(StageNum));

    }

    public void SetArrowActive(ArrowTypes arrType){

        switch (arrType){
            case eUP:
                imgVArrowUp.setImage(elevActiveCallDownArrow);
                break;
            case eDOWN:
                imgVArrowDown.setImage(elevActiveCallDownArrow);
            default:
                break;
        }
    }

    public void SetArrowInactive(ArrowTypes arrType){

        switch (arrType){
            case eUP:
                imgVArrowUp.setImage(elevInactiveCallDownArrow);
                break;
            case eDOWN:
                imgVArrowDown.setImage(elevInactiveCallDownArrow);
            default:
                break;
        }
    }

}
