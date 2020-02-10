package at.fhhagenberg.sqelevator.controller;

import java.rmi.RemoteException;

import at.fhhagenberg.sqelevator.model.Building;
import at.fhhagenberg.sqelevator.model.BuildingData;
import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;


/**
 * @author Martin Schram Class for scheduling the elevators.
 *
 */
public class ElevatorScheduler {

	/**
	 * Method to schedule the next targets or set values from gui on man mode
	 * @param elevators all instantiated elevators
	 * @param guiactions actions set from gui
	 * @throws RemoteException 
	 * @throws CloneNotSupportedException 
	 */
	public static void Schedule(Elevator[] elevators, ElevatorActions[] guiactions, Building building) throws RemoteException, CloneNotSupportedException {
		for (int i = 0; i < elevators.length; i++) 
			if (!guiactions[i].AutoMode)
				elevators[i].SetActions(guiactions[i]);
		if(building.StateChanged())
			ScheduleTargets(elevators,guiactions,building);		
	}

	/**
	 * Main logic for calculating next targets
	 * @param elevators all instantiated elevators
	 * @param guiactions actions set from gui
	 * @throws CloneNotSupportedException 
	 */
	private static void ScheduleTargets(Elevator[] elevators,ElevatorActions[] guiactions, Building building) throws CloneNotSupportedException{
		for(int i=0;i<elevators.length;i++) {
			if(guiactions[i].AutoMode) {
				BuildingData bd=building.GetFloorButtonDownStates();
				BuildingData bu=building.GetFloorButtonUpStates();
				// TODO insert logic
				elevators[i].SetActions(new ElevatorActions(i,true, true, elevators[i].GetServicedFloors()));
			}
		}
	}
}
