package at.fhhagenberg.sqelevator.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import at.fhhagenberg.sqelevator.model.Building;
import at.fhhagenberg.sqelevator.model.BuildingData;
import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;
import at.fhhagenberg.sqelevator.model.ElevatorData;
import sqelevator.IElevator;


@ExtendWith(MockitoExtension.class)
public class ElevatorSchedulerTest {

	@Mock
	IElevator iMock;

	@Test
	void SchedulerNoSchedulingTest() {
		
		Elevator[] e = { new Elevator(0, 2, iMock) };

		try {
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			ElevatorScheduler.Schedule(e,
					new ElevatorActions[] { new ElevatorActions(1, true, false, new boolean[] { true, true })},
					new Building(2, iMock));
					
			assertEquals(1, e[0].GetData().GetTarget());

	        Mockito.verify(iMock).getTarget(0);
	        
		} catch (Exception ex) {
			fail("Exception thrown at SchedulerNoSchedulingTest: "+ ex.toString());
		}
	}

}
