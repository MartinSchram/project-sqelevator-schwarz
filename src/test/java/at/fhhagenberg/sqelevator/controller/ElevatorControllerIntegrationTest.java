package at.fhhagenberg.sqelevator.controller;
import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;
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
class ElevatorControllerIntegrationTest {

	@Mock 
	Observer uiMock = Mockito.mock(Observer.class);
	
	@Mock 
	IElevator iMock; 
	
	ElevatorController ev;

	
	@BeforeEach
	void setUp() throws Exception {
		ev=new ElevatorController();
		ev.SetInst(iMock);
		ev.SetElevatorCount(1);
		ev.SetFloorCount(4);
		ev.addObserver(uiMock);
		ev.SwitchOn();
	}
	/*
	@Test
	void testObserverOneUpdateMsg() {
		setChanged();
		notifyObservers("Test");

		Mockito.verify(t).update(ArgumentMatchers.eq(this),ArgumentMatchers.eq((String)"Test"));
	}
	 * */
	
	
	@Test
	void test() {
		
		ElevatorActions ea = new ElevatorActions(2,true, false, new boolean[] {true,true,true,true});
		ev.GuiActions = new ElevatorActions [] {ea};
		ElevatorData r = new ElevatorData(0,2);
		Building b = new Building(4,iMock);
		
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
			
			ev.RunCycle();
			
			SystemData sd = new SystemData(1);
			r.SetElevatorButtonAtFloorEnabled(0,true);
			
			r.SetElevatornumber(0);
			r.SetElevatoraccel(2);
			r.SetElevatorspeed(200);
			
			r.SetElevatordoorstatus(2);
			r.SetElevatorfloor(2);
			r.SetElevatorposition(2);
			r.SetElevatorweight(100);
			r.SetTarget(1);
			
			sd.SetElevatorData(new ElevatorData[] {r});
			sd.SetBuildingButonDownData(b.GetFloorButtonDownStates());
			sd.SetBuildingButonUpData(b.GetFloorButtonUpStates());
			
			Mockito.verify(uiMock).update(ev,ArgumentMatchers.eq(sd));
			
		} catch (RemoteException | CloneNotSupportedException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		fail("Not yet implemented");
	}

}
