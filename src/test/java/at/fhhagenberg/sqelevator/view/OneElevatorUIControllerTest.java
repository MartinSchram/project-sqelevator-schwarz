package at.fhhagenberg.sqelevator.view;

import com.sun.javafx.logging.PlatformLogger;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.FlowView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import io.datafx.controller.flow.Flow;
import org.testfx.matcher.base.NodeMatchers;


import java.security.InvalidParameterException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class OneElevatorUIControllerTest extends ApplicationTest {


    private OneElevatorUIController m_Controller;

    @Override
    public void start(Stage stage) {
        try {
            Flow flow = new Flow(OneElevatorUIController.class);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void testSetPayloadLabel() {

        int StagesCount=5;
        String ElevatorName="Elevator 1";

        interact(() -> {
            m_Controller = new OneElevatorUIController(StagesCount,ElevatorName);

        });

        int PayloadToSet=-50;
        assertThrows(InvalidParameterException.class,()->{
            m_Controller.SetPayloadLabel(PayloadToSet);
        });


    }

}