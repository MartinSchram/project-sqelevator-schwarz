package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BuildingDataTest {

	
	@Test
	void testOutofUpperBoundsSetFloorButtonPressed() {
		BuildingData ev = new BuildingData(3);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.setStatus(3, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}

	@Test
	void testOutofUpperBoundsGetFloorButtonPressed() {
		BuildingData ev = new BuildingData(3);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.getStatus(3),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
	}
	
	@Test
	void testOutofLowerrBoundsSetFloorButtonPressed() {
		BuildingData ev = new BuildingData(3);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.setStatus(-1, true),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));

	}

	@Test
	void testOutofLowerBoundsGetFloorButtonPressed() {
		BuildingData ev = new BuildingData(3);
		IllegalArgumentException thrown =
		        assertThrows(IllegalArgumentException.class,
		           () -> ev.getStatus(-1),
		           " expected floor number out of bounds!");

		    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
	}
	
	@Test
	void testInBoundsGetSetFloorButtonPressed() {
		BuildingData ev = new BuildingData(3);
		ev.setStatus(2, true);
		
		assertEquals(true,ev.getStatus(2));
		assertEquals(false,ev.getStatus(1));	
	}
}
