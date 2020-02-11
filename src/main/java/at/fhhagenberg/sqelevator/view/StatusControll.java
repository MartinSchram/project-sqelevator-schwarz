package at.fhhagenberg.sqelevator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StatusControll extends AnchorPane {

    private class ListStatusItem extends HBox {
        private ImageView m_imgView=new ImageView();
        private Label m_label=new Label();

        public ListStatusItem(Image img, String TextToShow){
            super();
            m_imgView.setImage(img);
            m_label.setText(" "+ TextToShow);
            this.getChildren().addAll(m_imgView,m_label);

        }

    }

    @FXML
    private ListView<ListStatusItem> mListView;

    final ObservableList<ListStatusItem> m_ItemsContainer = FXCollections.observableArrayList();



    private Image imgErrorSign;
    private Image imgInfoSign;

    public StatusControll(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatusControll.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }

        InputStream in = getClass().getResourceAsStream("../images/StatusErrorSign.png");
        imgErrorSign = new Image(in);
        in = getClass().getResourceAsStream("../images/StatusInfoSign.png");
        imgInfoSign= new Image(in);

        mListView.setItems(m_ItemsContainer);
        mListView.editableProperty().setValue(false);
        mListView.setMouseTransparent( true );
        mListView.setFocusTraversable( false );

//        AddStatus(StatusIdentifyer.eError, "ajsldöfjasöldfjölsadjfölsadjfölsadjfölsadjföladsjfölsadjfölsajf");
    }

    public enum StatusIdentifyer{
        eError,
        eInfo
    }

    public void addStatus(StatusIdentifyer Identify, String Text){

        switch (Identify)
        {
            case eError:
                m_ItemsContainer.add(new ListStatusItem(imgErrorSign,Text));
                break;
            case eInfo:
                m_ItemsContainer.add(new ListStatusItem(imgInfoSign,Text));
                break;
            default:
                break;
        }


    }


}
