package at.fhhagenberg.sqelevator.model;

/**
 * @author Martin Schram Wrapper for updated data structures from system.
 *         Contains data structures for elevators and building.
 *
 */
public class SystemData {
	private ElevatorData[] elevators;
	private BuildingData buildingButtonUp;
	private BuildingData buildingButtonDown;

	/**
	 * ctor
	 * 
	 * @param elvatorCount how many elevators
	 */
	public SystemData(int elvatorCount) {
		elevators = new ElevatorData[elvatorCount];
	}

	/**
	 * Gets the Elevator data from the wrapper
	 * 
	 * @return array of elevatordata
	 */
	public ElevatorData[] getElevatorData() {
		return this.elevators;
	}

	/**
	 * Gets the building data for floorbuttonsup from the wrapper
	 * 
	 * @return buildingdata
	 */
	public BuildingData getBuildingButtonUpData() {
		return this.buildingButtonUp;
	}
	
	/**
	 * Gets the building data for floorbuttonsdown from the wrapper
	 * 
	 * @return buildingdata
	 */
	public BuildingData getBuildingButtonDownData() {
		return this.buildingButtonDown;
	}

	/**
	 * Sets a new updated data set
	 * 
	 * @param cloned elevator data from system
	 */
	public void setElevatorData(ElevatorData[] cloned) {
		this.elevators = cloned;
	}

	/**
	 * Sets updated building data for floorbuttonsup
	 * 
	 * @param cloned updated building data from system
	 */
	public void setBuildingButonUpData(BuildingData cloned) {
		this.buildingButtonUp = cloned;
	}
	
	/**
	 * Sets updated building data for floorbuttonsdown
	 * 
	 * @param cloned updated building data from system
	 */
	public void setBuildingButonDownData(BuildingData cloned) {
		this.buildingButtonDown = cloned;
	}
}
