package at.fhhagenberg.sqelevator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;
@ExtendWith(ApplicationExtension.class)
public class ElevatorUIAppTest {
	@Start
	public void start(Stage stage) throws Exception {
		new ElevatorUIApp().start(stage);
	}

//	@Test
//	public void testCall(FxRobot robot) {
//		/*robot.doubleClickOn("tbtnOpModeAuto");
//		robot.doubleClickOn("#b").write("2");
//
//		robot.clickOn("#compute");
//
//		verifyThat(".label.sum", hasText("3.00"));*/
//	}
}
