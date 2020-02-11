package at.fhhagenberg.sqelevator.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import at.fhhagenberg.sqelevator.model.Building;
import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;
import at.fhhagenberg.sqelevator.model.ElevatorData;
import at.fhhagenberg.sqelevator.model.SystemData;
import sqelevator.IElevator;

@ExtendWith(MockitoExtension.class)
class ElevatorControllerIntegrationTest implements Observer {

	@Mock
	Observer uiMock = Mockito.mock(Observer.class);

	@Mock
	IElevator iMock;

	ElevatorController ev;
	ElevatorData od;
	
	int recv;

	@BeforeEach
	void setUp() throws Exception {
		ev = new ElevatorController();
		ev.setInst(iMock);
		ev.setElevatorCount(1);
		ev.setFloorCount(4);
		ev.addObserver(uiMock);
		ev.switchOn();
		recv = 0;
	}

	@Test
	void ControllerDataForwardTest() {
		ev.addObserver(uiMock);
		ev.switchOn();
		ElevatorActions ea = new ElevatorActions(2, true, false, new boolean[] { true, true, true, true });
		ev.guiActions = new ElevatorActions[] { ea };

		try {
			ev.runCycle();

			assertEquals(1, Mockito.mockingDetails(uiMock).getInvocations().size());

		} catch (RemoteException | CloneNotSupportedException | InterruptedException e) {
			fail("Exception thrown at ControllerDataForwardTest: " + e.toString());
		}
	}

	@Test
	void ControllerUpdateDataVerificationTest() {

		ev.addObserver(this);
		ev.switchOn();
		ElevatorActions ea = new ElevatorActions(2, true, false, new boolean[] { true, true, true, true });
		ev.guiActions = new ElevatorActions[] { ea };
		ElevatorData r = new ElevatorData(0, 2);
		Building b = new Building(4, iMock);
		
		r.setElevatorButtonAtFloorEnabled(0, true);
		r.setElevatornumber(0);
		r.setElevatoraccel(2);
		r.setElevatorspeed(200);
		r.setElevatordoorstatus(2);
		r.setElevatorfloor(2);
		r.setElevatorposition(2);
		r.setElevatorweight(100);
		r.setTarget(1);

		try {
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			Mockito.when(iMock.getServicesFloors(0, 0)).thenReturn(false);
			Mockito.when(iMock.getElevatorDoorStatus(0)).thenReturn(1);
			Mockito.when(iMock.getElevatorFloor(0)).thenReturn(2);
			Mockito.when(iMock.getElevatorPosition(0)).thenReturn(2);
			Mockito.when(iMock.getElevatorSpeed(0)).thenReturn(200);
			Mockito.when(iMock.getElevatorWeight(0)).thenReturn(100);
			Mockito.when(iMock.getElevatorButton(0, 0)).thenReturn(true);
			Mockito.when(iMock.getElevatorAccel(0)).thenReturn(2);

			ev.runCycle();

			
			assertEquals(1, this.recv);
			
			assertEquals(1,od.getTarget());
			assertEquals(false,od.getservicesFloors()[0]);
			assertEquals(1,od.getElevatordoorstatus());
			assertEquals(2,od.getElevatorfloor());
			assertEquals(2,od.getElevatorposition());
			assertEquals(200, od.getElevatorspeed());
			assertEquals(100, od.getElevatorweight());
			assertEquals(true,od.getElevatorButtons()[0]);
			assertEquals(2,od.getElevatoraccel());
			

		} catch (RemoteException | CloneNotSupportedException | InterruptedException e) {
			fail("Exception thrown at ControllerUpdateDataVerificationTest: " + e.toString());
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		SystemData t = (SystemData) arg1;
		this.od =t.getElevatorData()[0];
		recv++;
	}

}
