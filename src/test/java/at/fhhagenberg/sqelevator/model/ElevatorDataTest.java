package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;

import at.fhhagenberg.sqelevator.model.ElevatorData;

import org.junit.jupiter.api.Test;

class ElevatorDataTest {

	@Test
	void testOutofUpperBoundsFloorButtonEnabled() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.setElevatorButtonAtFloorEnabled(3, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}

	@Test
	void testOutofLowerBoundsFloorButtonEnabled() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.setElevatorButtonAtFloorEnabled(-1, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testOutofUpperBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.setFloorIsServiced(3, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testOutofLowerBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.setFloorIsServiced(-1, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testInBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		ev.setElevatorButtonAtFloorEnabled(1, true);
		boolean[] eb = ev.getElevatorButtons();
		assertEquals(true, eb[1]);
	}
	
}
