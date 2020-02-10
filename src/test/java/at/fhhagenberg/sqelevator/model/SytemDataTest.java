package at.fhhagenberg.sqelevator.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SytemDataTest {

	@Test
	void ElevatordataContentTest() {
		SystemData sd = new SystemData(3);
		sd.SetElevatorData(new ElevatorData[] {new ElevatorData(0,3), new ElevatorData(0,3)});

		assertNotNull(sd.GetElevatorData());
	}
	
	@Test
	void BuildingdataUpContentTest() {
		SystemData sd = new SystemData(3);
		sd.SetBuildingButonUpData(new BuildingData(3));

		assertNotNull(sd.GetBuildingButtonUpData());
	}

	@Test
	void BuildingdataDownContentTest() {
		SystemData sd = new SystemData(3);
		sd.SetBuildingButonDownData(new BuildingData(3));

		assertNotNull(sd.GetBuildingButtonDownData());
	}
}
