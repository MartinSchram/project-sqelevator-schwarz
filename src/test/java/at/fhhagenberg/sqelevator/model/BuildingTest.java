package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;
import sqelevator.IElevator;


@ExtendWith(MockitoExtension.class)
class BuildingTest {

	@Mock
	IElevator iMock;
	
	@Test
	void TestGetClockTick() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getClockTick()).thenReturn((long) 1);
			assertEquals(1,b.GetClockTick());
		} catch (Exception e) {
			fail("Exception thrown at TestUpdateTarget: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutOfBoundsGetFloorButtonUp() {
		Building b = new Building(2,iMock);
		
		try {
			IllegalArgumentException thrown =
			        assertThrows(IllegalArgumentException.class,
			           () -> b.GetFloorButtonUp(3),
			           " expected floor number out of bounds!");

			    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
		} catch (Exception e) {
			fail("Exception thrown at TestUpdateTarget: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutOfBoundsGetFloorButtonDwon() {
		Building b = new Building(2,iMock);
		
		try {
			IllegalArgumentException thrown =
			        assertThrows(IllegalArgumentException.class,
			           () -> b.GetFloorButtonDown(3),
			           " expected floor number out of bounds!");

			    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
		} catch (Exception e) {
			fail("Exception thrown at TestUpdateTarget: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutInBoundsGetFloorButtonUp() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonUp(1)).thenReturn(true);
			
			assertEquals(true,b.GetFloorButtonUp(1));
			
		} catch (Exception e) {
			fail("Exception thrown at TestUpdateTarget: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutInBoundsGetFloorButtonDown() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonDown(1)).thenReturn(true);
			
			assertEquals(true,b.GetFloorButtonDown(1));
			
		} catch (Exception e) {
			fail("Exception thrown at TestUpdateTarget: "+ e.toString());
		}			
	}
	
	
	@Test
	void TestUpdateTarget() {
		
		Elevator e = new Elevator(0,2,iMock);
		e.SetActions(new ElevatorActions(1,true,true,new boolean[] {true, false}));
		
		try {
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			Mockito.when(iMock.getServicesFloors(0,0)).thenReturn(false);
			e.UpdateTarget();
			e.UpdateData();
		} catch (Exception ex) {
			fail("Exception thrown at TestUpdateTarget: "+ ex.toString());
		}
		
		
		boolean[] ret = e.GetServicedFloors();
		
		assertEquals(2,ret.length);
		assertEquals(1,e.GetTarget());
		assertEquals(ret[1],false);
		
	}

}
