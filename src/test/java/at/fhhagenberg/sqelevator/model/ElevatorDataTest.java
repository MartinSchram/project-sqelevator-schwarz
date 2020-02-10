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
		           () -> ev.SetElevatorButtonAtFloorEnabled(3, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}

	@Test
	void testOutofLowerBoundsFloorButtonEnabled() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.SetElevatorButtonAtFloorEnabled(-1, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testOutofUpperBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.SetFloorIsServiced(3, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testOutofLowerBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.SetFloorIsServiced(-1, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testInBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		ev.SetElevatorButtonAtFloorEnabled(1, true);
		boolean[] eb = ev.GetElevatorButtons();
		assertEquals(true, eb[1]);
	}
	
}
