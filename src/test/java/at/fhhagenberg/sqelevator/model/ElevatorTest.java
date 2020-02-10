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
public class ElevatorTest {

	@Mock
	IElevator iMock;
	
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
	void TestUpdateData() {
		
		Elevator e = new Elevator(0,2,iMock);
		ElevatorData r = new ElevatorData(0,5);
		
		try {
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			Mockito.when(iMock.getServicesFloors(0,0)).thenReturn(false);
			Mockito.when(iMock.getElevatorDoorStatus(0)).thenReturn(1);
			Mockito.when(iMock.getElevatorFloor(0)).thenReturn(2);
			Mockito.when(iMock.getElevatorPosition(0)).thenReturn(2);
			Mockito.when(iMock.getElevatorSpeed(0)).thenReturn(200);
			Mockito.when(iMock.getElevatorWeight(0)).thenReturn(100);
			Mockito.when(iMock.getElevatorButton(0,0)).thenReturn(true);
			Mockito.when(iMock.getCommittedDirection(0)).thenReturn(1);
			Mockito.when(iMock.getElevatorAccel(0)).thenReturn(2);
			
			r = e.GetData();

		} catch (Exception ex) {
			fail("Exception thrown at TestUpdateTarget: "+ ex.toString());
		}
		
		
		boolean[] eb= r.GetElevatorButtons();
		
		assertEquals(0,r.GetElevatornumber());
		assertEquals(2,r.GetElevatoraccel());
		assertEquals(200,r.GetElevatorspeed());
		assertEquals(1,r.GetElevatordoorstatus());
		assertEquals(2, r.GetElevatorfloor());
		assertEquals(2,r.GetElevatorposition());
		assertEquals(100, r.GetElevatorweight());
		assertEquals(1,r.GetTarget());
		assertEquals(true, r.GetCommittedDirIsUp());		
		assertEquals(true, eb[0]);
		assertEquals(false, eb[1]);
		
	}
	
	@Test
	void TestInstantioationElevator() {
		Elevator e = new Elevator(0,2);
		
		assertEquals(true,e.setInstance(iMock));
	}
	
	@Test
	void TestMultInstantioationElevator() {
		Elevator e = new Elevator(0,2);
		
		assertEquals(true,e.setInstance(iMock));
		assertEquals(false,e.setInstance(iMock));
	}

}
