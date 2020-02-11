package at.fhhagenberg.sqelevator.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import sqelevator.IElevator;


@ExtendWith(MockitoExtension.class)
class ElevatorControllerUnitTest {

	@Mock
	IElevator iMock;
	
	
	@Test
	void ManualDestructTest() {
		ElevatorController ev = new ElevatorController();
		ev.SetInst(iMock);

		ev.SwitchOn();
		ev.SwitchOff();

		assertEquals(false, ev.IsOn());
		
	}
	
	@Test
	void ManualInitializationTest() {
		ElevatorController ev = new ElevatorController();
		ev.SetInst(iMock);
		ev.SetElevatorCount(1);
		ev.SetFloorCount(5);
		
		assertEquals(true, ev.SwitchOn());
		
	}
	
	@Test
	void AutoMemberInitializationTest() {
		ElevatorController ev = new ElevatorController();
		
		try {
			Mockito.when(iMock.getElevatorNum()).thenReturn(1);
			Mockito.when(iMock.getFloorNum()).thenReturn(5);
			ev.SetInst(iMock);
		} catch (Exception e) {
			fail("Exception thrown at AutoInitializationTest: "+ e.toString());			
		}
		
		assertEquals(true, ev.SwitchOn());
		
	}
	
	@Test
	void AutoInitializationTest() {
		
		ElevatorController ev = new ElevatorController(1,5,iMock);
		
		assertEquals(true, ev.SwitchOn());		
	}

	@Test
	void InitializationOnInstFailedTest() {
		
		ElevatorController ev = new ElevatorController();
		
		assertEquals(false,ev.SetInst(null));	
		assertEquals(false,ev.SwitchOn());
	}
}
