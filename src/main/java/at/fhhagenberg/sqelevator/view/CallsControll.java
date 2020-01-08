package at.fhhagenberg.sqelevator.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CallsControll extends AnchorPane {

    @FXML
    private ImageView arrDown4;

    @FXML
    private ImageView arrDown3;

    @FXML
    private ImageView arrUp1;

    @FXML
    private ImageView arrUp0;

    @FXML
    private ImageView arrUp3;

    @FXML
    private ImageView arrUp2;

    @FXML
    private ImageView arrUp4;

    @FXML
    private ImageView arrDown2;

    @FXML
    private ImageView arrDown1;

    @FXML
    private ImageView arrDown0;


    private ArrayList<ImageView> mlistStagesCallDownImgs=new ArrayList<ImageView>();
    private ArrayList<ImageView> mlistStagesCallUpImgs=new ArrayList<ImageView>();

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

        InputStream in= getClass().getResourceAsStream("../images/ArrowStagesDownActive.png");
        elevActiveCallDownArrow = new Image(in);
        in= getClass().getResourceAsStream("../images/ArrowStagesDown.png");
        elevInactiveCallDownArrow = new Image(in);


        mlistStagesCallDownImgs.add(arrDown4);
        mlistStagesCallDownImgs.add(arrDown3);
        mlistStagesCallDownImgs.add(arrDown2);
        mlistStagesCallDownImgs.add(arrDown1);
        mlistStagesCallDownImgs.add(arrDown0);

        mlistStagesCallUpImgs.add(arrUp4);
        mlistStagesCallUpImgs.add(arrUp3);
        mlistStagesCallUpImgs.add(arrUp2);
        mlistStagesCallUpImgs.add(arrUp1);
        mlistStagesCallUpImgs.add(arrUp0);

    }


}
