package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;

import at.fhhagenberg.sqelevator.model.ElevatorData;

import org.junit.jupiter.api.Test;

class ElevatorDataTest {

	@Test
	void testOutofUpperBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.SetElevatorButtonAtFloorEnabled(3, true),
		           " expected testBuffer.dequeue() to throw!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
	@Test
	void testOutofLowerBoundsFloorServiceSet() {
		ElevatorData ev = new ElevatorData(1,2);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.SetElevatorButtonAtFloorEnabled(-1, true),
		           " expected testBuffer.dequeue() to throw!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}
	
//	@Test
//	void testInBoundsFloorServiceSet() {
//		ElevatorData ev = new ElevatorData(1,2);
//		assertDoesNotThrow​(IllegalArgumentException.class,() -> ev.SetElevatorButtonAtFloorEnabled(1, true));
//	}
	
}
