package at.fhhagenberg.sqelevator.controller;

import java.rmi.RemoteException;

import at.fhhagenberg.sqelevator.model.Building;
import at.fhhagenberg.sqelevator.model.BuildingData;
import at.fhhagenberg.sqelevator.model.Elevator;
import at.fhhagenberg.sqelevator.model.ElevatorActions;
import at.fhhagenberg.sqelevator.model.ElevatorData;

/**
 * @author Martin Schram Class for scheduling the elevators.
 *
 */
public class ElevatorScheduler {

	/**
	 * Method to schedule the next targets or set values from gui on man mode
	 * 
	 * @param elevators  all instantiated elevators
	 * @param guiactions actions set from gui
	 * @throws RemoteException
	 * @throws CloneNotSupportedException
	 */
	public static void Schedule(Elevator[] elevators, ElevatorActions[] guiactions, Building building)
			throws RemoteException, CloneNotSupportedException {
		for (int i = 0; i < elevators.length; i++)
			if (!guiactions[i].AutoMode)
				elevators[i].SetActions(guiactions[i]);
		if (building.StateChanged())
			ScheduleTargets(elevators, guiactions, building);
	}

	/**
	 * Main logic for calculating next targets
	 * 
	 * @param elevators  all instantiated elevators
	 * @param guiactions actions set from gui
	 * @throws CloneNotSupportedException
	 * @throws RemoteException
	 */
	private static void ScheduleTargets(Elevator[] elevators, ElevatorActions[] guiactions, Building building)
			throws CloneNotSupportedException, RemoteException {
		for (int i = 0; i < elevators.length; i++) {
			if (guiactions[i].AutoMode) {
				BuildingData bd = building.GetFloorButtonDownStates();
				BuildingData bu = building.GetFloorButtonUpStates();
				ElevatorData ev = elevators[i].GetData();

				if (ev.GetTarget() != ev.GetElevatorposition()) {

					for (int j = 0; j < ev.GetservicesFloors().length; j++) {
						if (ev.GetTarget() > ev.GetElevatorposition() && bu.GetStatus(j))
							elevators[i]
									.SetActions(new ElevatorActions(j, true, true, elevators[i].GetServicedFloors()));
						else if (bd.GetStatus(j))
							elevators[i]
									.SetActions(new ElevatorActions(j, true, true, elevators[i].GetServicedFloors()));
					}

				} else {
					boolean[] eb = ev.GetElevatorButtons();
					int min = eb.length;
					int t = 0;
					for (int j = 0; j < eb.length; j++) {
						if (eb[j] && Math.abs(j - ev.GetElevatorposition()) < min) {
							min = Math.abs(j - ev.GetElevatorposition());
							t = j;
						}
					}
					if (min < eb.length)
						elevators[i].SetActions(new ElevatorActions(t, true, true, guiactions[i].ServicesFloors));
					else {
						for (int j = 0; j < ev.GetservicesFloors().length; j++) {
							if (bu.GetStatus(j) || bd.GetStatus(j)) {
								elevators[i].SetActions(
										new ElevatorActions(j, true, true, elevators[i].GetServicedFloors()));

							}
						}
					}
				}
			}
		}
	}
}