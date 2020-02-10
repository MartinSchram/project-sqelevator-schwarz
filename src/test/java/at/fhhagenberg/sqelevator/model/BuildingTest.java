package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;


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
			fail("Exception thrown at TestGetClockTick: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutOfUpperBoundsGetFloorButtonUp() {
		Building b = new Building(2,iMock);
		
		try {
			IllegalArgumentException thrown =
			        assertThrows(IllegalArgumentException.class,
			           () -> b.GetFloorButtonUp(3),
			           " expected floor number out of bounds!");

			    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
		} catch (Exception e) {
			fail("Exception thrown at TestoutOfUpperBoundsGetFloorButtonUp: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutOfUpperBoundsGetFloorButtonDwon() {
		Building b = new Building(2,iMock);
		
		try {
			IllegalArgumentException thrown =
			        assertThrows(IllegalArgumentException.class,
			           () -> b.GetFloorButtonDown(3),
			           " expected floor number out of bounds!");

			    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
		} catch (Exception e) {
			fail("Exception thrown at TestoutOfUpperBoundsGetFloorButtonDwon: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutOfLowerBoundsGetFloorButtonUp() {
		Building b = new Building(2,iMock);
		
		try {
			IllegalArgumentException thrown =
			        assertThrows(IllegalArgumentException.class,
			           () -> b.GetFloorButtonUp(-1),
			           " expected floor number out of bounds!");

			    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
		} catch (Exception e) {
			fail("Exception thrown at TestoutOfLowerBoundsGetFloorButtonUp: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutOfLowerBoundsGetFloorButtonDwon() {
		Building b = new Building(2,iMock);
		
		try {
			IllegalArgumentException thrown =
			        assertThrows(IllegalArgumentException.class,
			           () -> b.GetFloorButtonDown(-1),
			           " expected floor number out of bounds!");

			    assertTrue(thrown.getMessage().contains("The floor number does not exist"));
		} catch (Exception e) {
			fail("Exception thrown at TestoutOfLowerBoundsGetFloorButtonDwon: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutInBoundsGetFloorButtonUp() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonUp(1)).thenReturn(true);
			
			assertEquals(true,b.GetFloorButtonUp(1));
			
		} catch (Exception e) {
			fail("Exception thrown at TestoutInBoundsGetFloorButtonUp: "+ e.toString());
		}			
	}
	
	@Test
	void TestoutInBoundsGetFloorButtonDown() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonDown(1)).thenReturn(true);
			
			assertEquals(true,b.GetFloorButtonDown(1));
			
		} catch (Exception e) {
			fail("Exception thrown at TestoutInBoundsGetFloorButtonDown: "+ e.toString());
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
	
	@Test
	void TestBuildingStatesNotChanged() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonUp(0)).thenReturn(true);
			b.Update();
			b.StoreState();
			assertEquals(false,b.StateChanged());
			
		} catch (Exception e) {
			fail("Exception thrown at TestBuildingStatesNotChanged: "+ e.toString());
		}			
	}
	
	@Test
	void TestBuildingUpStatesChanged() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonUp(0)).thenReturn(true);
			b.Update();
			b.StoreState();
			Mockito.when(iMock.getFloorButtonUp(1)).thenReturn(true);
			assertEquals(true,b.StateChanged());
			
		} catch (Exception e) {
			fail("Exception thrown at TestBuildingUpStatesChanged: "+ e.toString());
		}			
	}
	
	@Test
	void TestBuildingDownStatesChanged() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonDown(0)).thenReturn(true);
			b.Update();
			b.StoreState();
			Mockito.when(iMock.getFloorButtonDown(0)).thenReturn(false);
			assertEquals(true,b.StateChanged());
			
		} catch (Exception e) {
			fail("Exception thrown at TestBuildingDownStatesChanged: "+ e.toString());
		}			
	}
	
	@Test
	void TestSetBuildingDataConsistensyDown() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonDown(0)).thenReturn(true);
			b.Update();
			BuildingData bd = b.GetFloorButtonDownStates();
			
			assertEquals(true,bd.GetStatus(0));
			assertEquals(false,bd.GetStatus(1));
			
		} catch (Exception e) {
			fail("Exception thrown at TestSetBuildingDataConsistensyDown: "+ e.toString());
		}			
	}
	
	@Test
	void TestSetBuildingDataConsistensyUp() {
		Building b = new Building(2,iMock);
		
		try {
			Mockito.when(iMock.getFloorButtonUp(0)).thenReturn(true);
			b.Update();
			BuildingData bd = b.GetFloorButtonUpStates();
			
			assertEquals(true,bd.GetStatus(0));
			assertEquals(false,bd.GetStatus(1));
			
		} catch (Exception e) {
			fail("Exception thrown at TestSetBuildingDataConsistensyUp: "+ e.toString());
		}			
	}

}
