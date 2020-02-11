package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SytemDataTest {

	@Test
	void ElevatordataContentTest() {
		SystemData sd = new SystemData(3);
		sd.setElevatorData(new ElevatorData[] {new ElevatorData(0,3), new ElevatorData(0,3)});

		assertNotNull(sd.getElevatorData());
	}
	
	@Test
	void BuildingdataUpContentTest() {
		SystemData sd = new SystemData(3);
		sd.setBuildingButonUpData(new BuildingData(3));

		assertNotNull(sd.getBuildingButtonUpData());
	}

	@Test
	void BuildingdataDownContentTest() {
		SystemData sd = new SystemData(3);
		sd.setBuildingButonDownData(new BuildingData(3));

		assertNotNull(sd.getBuildingButtonDownData());
	}
}
