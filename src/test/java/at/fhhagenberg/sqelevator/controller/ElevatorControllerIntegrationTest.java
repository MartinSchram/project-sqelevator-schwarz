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
		ev.SetInst(iMock);
		ev.SetElevatorCount(1);
		ev.SetFloorCount(4);
		ev.addObserver(uiMock);
		ev.SwitchOn();
		recv = 0;
	}

	@Test
	void ControllerDataForwardTest() {
		ev.addObserver(uiMock);
		ev.SwitchOn();
		ElevatorActions ea = new ElevatorActions(2, true, false, new boolean[] { true, true, true, true });
		ev.GuiActions = new ElevatorActions[] { ea };

		try {
			ev.RunCycle();

			assertEquals(1, Mockito.mockingDetails(uiMock).getInvocations().size());

		} catch (RemoteException | CloneNotSupportedException | InterruptedException e) {
			fail("Exception thrown at ControllerDataForwardTest: " + e.toString());
		}
	}

	@Test
	void ControllerUpdateDataVerificationTest() {

		ev.addObserver(this);
		ev.SwitchOn();
		ElevatorActions ea = new ElevatorActions(2, true, false, new boolean[] { true, true, true, true });
		ev.GuiActions = new ElevatorActions[] { ea };
		ElevatorData r = new ElevatorData(0, 2);
		Building b = new Building(4, iMock);
		
		r.SetElevatorButtonAtFloorEnabled(0, true);
		r.SetElevatornumber(0);
		r.SetElevatoraccel(2);
		r.SetElevatorspeed(200);
		r.SetElevatordoorstatus(2);
		r.SetElevatorfloor(2);
		r.SetElevatorposition(2);
		r.SetElevatorweight(100);
		r.SetTarget(1);

		try {
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			Mockito.when(iMock.getServicesFloors(0, 0)).thenReturn(false);
			Mockito.when(iMock.getElevatorDoorStatus(0)).thenReturn(1);
			Mockito.when(iMock.getElevatorFloor(0)).thenReturn(2);
			Mockito.when(iMock.getElevatorPosition(0)).thenReturn(2);
			Mockito.when(iMock.getElevatorSpeed(0)).thenReturn(200);
			Mockito.when(iMock.getElevatorWeight(0)).thenReturn(100);
			Mockito.when(iMock.getElevatorButton(0, 0)).thenReturn(true);
			Mockito.when(iMock.getCommittedDirection(0)).thenReturn(1);
			Mockito.when(iMock.getElevatorAccel(0)).thenReturn(2);

			ev.RunCycle();

			
			assertEquals(1, this.recv);
			
			assertEquals(true,od.GetCommittedDirIsUp());
			assertEquals(1,od.GetTarget());
			assertEquals(false,od.GetservicesFloors()[0]);
			assertEquals(1,od.GetElevatordoorstatus());
			assertEquals(2,od.GetElevatorfloor());
			assertEquals(2,od.GetElevatorposition());
			assertEquals(200, od.GetElevatorspeed());
			assertEquals(100, od.GetElevatorweight());
			assertEquals(true,od.GetElevatorButtons()[0]);
			assertEquals(2,od.GetElevatoraccel());
			

		} catch (RemoteException | CloneNotSupportedException | InterruptedException e) {
			fail("Exception thrown at ControllerUpdateDataVerificationTest: " + e.toString());
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		SystemData t = (SystemData) arg1;
		this.od =t.GetElevatorData()[0];
		recv++;
	}

}
