package at.fhhagenberg.sqelevator.model;

/**
 * @author Martin Schram Class as helper container for building properties
 *
 */
public class BuildingData {
	private boolean[] status;

	public BuildingData(int floor) {
		this.status = new boolean[floor];
		for(int i =0; i<floor;i++) {
			this.status[i]=false;
		}
	}
	
	/**
	 * Getter for status
	 * @param idx index of floor
	 * @return TRUE -> enabled
	 */
	public boolean getStatus(int fl) {
		if (fl >= status.length || fl < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			return this.status[fl];
		}
	}
	
	
	/** 
	 * Setter for status
	 * @param idx idx index of floor
	 * @param val TRUE -> to be set to enabled
	 */
	public void setStatus(int fl,boolean val) {
		if (fl >= status.length || fl < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			this.status[fl] = val;
		}
		
	}
}
