package at.fhhagenberg.sqelevator.model;

/**
 * @author Martin Schram Class as helper container for building properties
 *
 */
public class BuildingData {
	public boolean[] Status;

	public BuildingData(int floor) {
		this.Status = new boolean[floor];
	}

	/**
	 * Clone to get shallow copy.
	 */
	@Override
	protected BuildingData clone() throws CloneNotSupportedException {
		return (BuildingData) super.clone();
	}
}