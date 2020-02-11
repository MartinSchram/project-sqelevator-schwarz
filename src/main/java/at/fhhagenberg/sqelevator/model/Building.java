package at.fhhagenberg.sqelevator.model;

import sqelevator.IElevator;

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
		this.iStateButtonUp = new BuildingData(floors);
		this.iStateButtonDown = new BuildingData(floors);

	}
	
	
	/**
	 * Getter for the system clock tick
	 * @return current clock tick
	 * @throws RemoteException
	 */
	public long getClockTick() throws RemoteException {
		return this.inst.getClockTick();
	}

	/**
	 * Gets button down state on floor,
	 * 
	 * @param floor floor to get the state of
	 * @return TRUE -> is active
	 * @throws RemoteException rmi-interface hung up
	 */
	public boolean getFloorButtonUp(int floor) throws RemoteException {

		if (floor >= floors || floor < 0) {
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
	public boolean getFloorButtonDown(int floor) throws RemoteException {

		if (floor >= floors || floor < 0) {
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
	public void update() throws RemoteException {
		for (int i = 0; i < floors; i++) {
			iStateButtonUp.setStatus(i, getFloorButtonUp(i));
			iStateButtonDown.setStatus(i, getFloorButtonDown(i));
		}
	}

	/**
	 * Stores the internal states in history buffer
	 * 
	 * @throws RemoteException rmi-interface hung up
	 */
	public void storeState() throws RemoteException {
		for (int i = 0; i < floors; i++) {
			hStateButtonUp.setStatus(i,iStateButtonUp.getStatus(i));
			hStateButtonDown.setStatus(i,iStateButtonDown.getStatus(i));
		}
	}

	/**
	 * Checks if any state has changed from previous update
	 * 
	 * @return TRUE -> any state has changed
	 * @throws RemoteException rmi-interface hung up
	 */
	public boolean stateChanged() throws RemoteException {
		for (int i = 0; i < floors; i++) {
			if (iStateButtonUp.getStatus(i) != getFloorButtonUp(i) || iStateButtonDown.getStatus(i) != getFloorButtonDown(i))
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
	public BuildingData getFloorButtonDownStates() throws CloneNotSupportedException {
		return this.iStateButtonDown;
	}

	/**
	 * Getter for the internal states of the floor up buttons
	 * 
	 * @return building data object where index represents floor and TRUE -> button
	 *         active
	 * @throws CloneNotSupportedException
	 */
	public BuildingData getFloorButtonUpStates() throws CloneNotSupportedException {
		return this.iStateButtonUp;
	}

}
