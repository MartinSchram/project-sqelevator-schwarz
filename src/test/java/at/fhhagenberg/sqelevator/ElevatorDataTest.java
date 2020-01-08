package at.fhhagenberg.sqelevator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ElevatorDataTest {

	@Test
	void testDefaultValueFloorHeight() {
		ElevatorData ev = new ElevatorData();
		assertEquals(10, ev.getFloorHeight());

	}
	@Test
	void testDefaultValueFloorNum() {
		ElevatorData ev = new ElevatorData();
		assertEquals(10, ev.getFloorNum());

	}

}
