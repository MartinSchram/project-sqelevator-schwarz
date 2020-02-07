package at.fhhagenberg.sqelevator.model;

import java.rmi.RemoteException;

/**
 * @author Martin Schram Container for building data. Updates floor buttons
 *         pushed. Provides history.
 *
 */
public class Building {
	private IElevator inst = null;
	private int floors;

	private BuildingData hStateButtonUp;
	private BuildingData hStateButtonDown;

	private BuildingData iStateButtonUp;
	private BuildingData iStateButtonDown;

	/**
	 * ctor inits history and container
	 * 
	 * @param pfloors floor count in building
	 * @param ins     instance of rmi - model
	 */
	public Building(int pfloors, IElevator ins) {
		this.inst = ins;
		this.floors = pfloors;
		this.hStateButtonDown = new BuildingData(floors);
		this.hStateButtonUp = new BuildingData(floors);
		ResetHStates();
		ResetIStates();
	}
	
	
	/**
	 * Getter for the system clock tick
	 * @return current clock tick
	 * @throws RemoteException
	 */
	public long GetClockTick() throws RemoteException {
		return this.inst.getClockTick();
	}

	/**
	 * Gets button down state on floor,
	 * 
	 * @param floor floor to get the state of
	 * @return TRUE -> is active
	 * @throws RemoteException rmi-interface hung up
	 */
	public boolean GetFloorButtonUp(int floor) throws RemoteException {
		if (floor > floors || floor < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			return inst.getFloorButtonUp(floor);
		}
	}

	/**
	 * Gets button up state on floor,
	 * 
	 * @param floor floor to get the state of
	 * @return TRUE -> is active
	 * @throws RemoteException rmi-interface hung up
	 */
	public boolean GetFloorButtonDown(int floor) throws RemoteException {
		if (floor > floors || floor < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			return inst.getFloorButtonDown(floor);
		}
	}

	/**
	 * Updates the internal states from current on the rmi.
	 * 
	 * @throws RemoteException rmi-interface hung up
	 */
	public void Update() throws RemoteException {
		for (int i = 0; i < floors; i++) {
			iStateButtonUp.Status[i] = GetFloorButtonUp(i);
			iStateButtonDown.Status[i] = GetFloorButtonDown(i);
		}
	}

	/**
	 * Stores the internal states in history buffer
	 * 
	 * @throws RemoteException rmi-interface hung up
	 */
	public void StoreState() throws RemoteException {
		for (int i = 0; i < floors; i++) {
			hStateButtonUp.Status[i] = iStateButtonUp.Status[i];
			hStateButtonDown.Status[i] = iStateButtonDown.Status[i];
		}
	}

	/**
	 * Checks if any state has changed from previous update
	 * 
	 * @return TRUE -> any state has changed
	 * @throws RemoteException rmi-interface hung up
	 */
	public boolean StateChanged() throws RemoteException {
		for (int i = 0; i < floors; i++) {
			if (iStateButtonUp.Status[i] != GetFloorButtonUp(i) || iStateButtonDown.Status[i] != GetFloorButtonDown(i))
				return true;
		}
		return false;
	}

	/**
	 * Getter for the internal states of the floor down buttons
	 * 
	 * @return building data object where index represents floor and TRUE -> button
	 *         active
	 * @throws CloneNotSupportedException
	 */
	public BuildingData GetFloorButtonDownStates() throws CloneNotSupportedException {
		return this.iStateButtonDown.clone();
	}

	/**
	 * Getter for the internal states of the floor up buttons
	 * 
	 * @return building data object where index represents floor and TRUE -> button
	 *         active
	 * @throws CloneNotSupportedException
	 */
	public BuildingData GetFloorButtonUpStates() throws CloneNotSupportedException {
		return this.iStateButtonUp.clone();
	}

	private void ResetIStates() {
		for (int i = 0; i < floors; i++) {
			iStateButtonUp.Status[i] = false;
			iStateButtonDown.Status[i] = false;
		}

	}

	private void ResetHStates() {
		for (int i = 0; i < floors; i++) {
			hStateButtonUp.Status[i] = false;
			hStateButtonDown.Status[i] = false;
		}

	}

}
