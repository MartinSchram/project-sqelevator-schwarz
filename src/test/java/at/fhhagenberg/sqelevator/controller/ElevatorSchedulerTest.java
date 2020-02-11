package at.fhhagenberg.sqelevator.controller;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import at.fhhagenberg.sqelevator.model.Building;
import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;
import sqelevator.IElevator;


@ExtendWith(MockitoExtension.class)
public class ElevatorSchedulerTest {

	@Mock
	IElevator iMock;

	@Test
	void ManualSchedulingTest() {
		
		Elevator[] e = { new Elevator(0, 2, iMock) };
		ElevatorActions[] ea =new ElevatorActions[] { new ElevatorActions(1, true, false, new boolean[] { true, true })};

		try {
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			ElevatorScheduler.schedule(e,
					ea.clone(),
					new Building(2, iMock));
					
			assertEquals(1, e[0].getData().getTarget());

	        Mockito.verify(iMock).getTarget(0);
	        
		} catch (Exception ex) {
			fail("Exception thrown at SchedulerNoSchedulingTest: "+ ex.toString());
		}
	}
	
	@Test
	void AutoSchedulingTest() {
		
		Elevator[] e = { new Elevator(0, 2, iMock),new Elevator(1, 2, iMock) };
		ElevatorActions[] ea =new ElevatorActions[] { new ElevatorActions(1, true, true, new boolean[] { true, true }),
				new ElevatorActions(1, true, true, new boolean[] { true, true })};
		Building b = new Building(2,iMock);		
		
			
		try {
			Mockito.when(iMock.getFloorButtonUp(0)).thenReturn(false);
			b.update();
			b.storeState();
			
			Mockito.when(iMock.getFloorButtonUp(0)).thenReturn(true);
			Mockito.when(iMock.getTarget(0)).thenReturn(1);
			ElevatorScheduler.schedule(e,ea.clone(),b);		
			
			assertEquals(1, e[0].getData().getTarget());

	        Mockito.verify(iMock).getTarget(0);
	        
		} catch (Exception ex) {
			fail("Exception thrown at SchedulerNoSchedulingTest: "+ ex.toString());
		}
	}

}
