package at.fhhagenberg.sqelevator.model;

/**
 * @author Martin Schram Clonable container for elevator data. Contains all
 *         inforamtion of the current state of the elevator.
 */

public class ElevatorData implements Cloneable {

	private int elevatorNumber;

	private int elevatorAccel;

	private int elevatorDoorStatus;

	private int elevatorFloor;

	private int elevatorPosition;

	private int elevatorSpeed;

	private int elevatorWeight;

	private int target;

	private boolean committedDirIsUp;

	private boolean[] elevatorButtons;

	private boolean[] servicesFloors;

	/**
	 * Ctor
	 * 
	 * @param enumb elevator number to contain data for
	 * @param fl    floors in building
	 */
	public ElevatorData(int enumb, int fl) {
		this.servicesFloors = new boolean[fl];
		this.elevatorButtons = new boolean[fl];
		this.elevatorNumber = enumb;
	}

	/**
	 * Clone to get shallow copy.
	 */
	@Override
	protected ElevatorData clone() throws CloneNotSupportedException {
		return (ElevatorData) super.clone();
	}

	/* GETTERS */

	/**
	 * Getter for elevatorNumber
	 * 
	 * @return current elevatorNumber
	 */
	public int getElevatornumber() {
		return this.elevatorNumber;
	}

	/**
	 * Getter for elevatorAccel
	 * 
	 * @return current elevatorAccel
	 */
	public int getElevatoraccel() {
		return this.elevatorAccel;
	}

	/**
	 * Getter for elevatorDoorStatus
	 * 
	 * @return current elevatorDoorStatus
	 */
	public int getElevatordoorstatus() {
		return this.elevatorDoorStatus;
	}

	/**
	 * Getter for elevatorFloor
	 * 
	 * @return current elevatorFloor
	 */
	public int getElevatorfloor() {
		return this.elevatorFloor;
	}

	/**
	 * Getter for elevatorPosition
	 * 
	 * @return current elevatorPosition
	 */
	public int getElevatorposition() {
		return this.elevatorPosition;
	}

	/**
	 * Getter for elevatorSpeed
	 * 
	 * @return current elevatorSpeed
	 */
	public int getElevatorspeed() {
		return this.elevatorSpeed;
	}

	/**
	 * Getter for elevatorWeight
	 * 
	 * @return current elevatorWeight
	 */
	public int getElevatorweight() {
		return this.elevatorWeight;
	}

	/**
	 * Getter for target
	 * 
	 * @return current target
	 */
	public int getTarget() {
		return this.target;
	}


	/**
	 * Getter for committedDirIsUp
	 * 
	 * @return FALSE -> when commited dir is down
	 */
	public boolean getCommittedDirIsUp() {
		return this.committedDirIsUp;
	}

	/**
	 * Getter for elevatorButtons
	 * 
	 * @return [] where TRUE -> floor idx pressed
	 */
	public boolean[] getElevatorButtons() {
		return this.elevatorButtons;
	}

	/**
	 * Getter for servicesFloors
	 * 
	 * @return [] where TRUE -> floor at idx is serviced
	 */
	public boolean[] getservicesFloors() {
		return this.servicesFloors;
	}

	/* SETTERS */

	/**
	 * Setter for elevatorNumber
	 * 
	 * @param val to be set
	 */
	public void setElevatornumber(int val) {
		this.elevatorNumber = val;
	}

	/**
	 * Setter for elevatorAccel
	 * 
	 * @param val to be set
	 */
	public void setElevatoraccel(int val) {
		this.elevatorAccel = val;
	}

	/**
	 * Setter for elevatorDoorStatus
	 * 
	 * @param val to be set
	 */
	public void setElevatordoorstatus(int val) {
		this.elevatorDoorStatus = val;
	}

	/**
	 * Setter for elevatorFloor
	 * 
	 * @param val to be set
	 */
	public void setElevatorfloor(int val) {
		this.elevatorFloor = val;
	}

	/**
	 * Setter for elevatorPosition
	 * 
	 * @param val to be set
	 */
	public void setElevatorposition(int val) {
		this.elevatorPosition = val;
	}

	/**
	 * Setter for elevatorSpeed
	 * 
	 * @param val to be set
	 */
	public void setElevatorspeed(int val) {
		this.elevatorSpeed = val;
	}

	/**
	 * Setter for elevatorWeight
	 * 
	 * @param val to be set
	 */
	public void setElevatorweight(int val) {
		this.elevatorWeight = val;
	}

	/**
	 * Setter for target
	 * 
	 * @param val to be set
	 */
	public void setTarget(int val) {
		this.target = val;
	}

	public void setCommitedDirIsUp(boolean is) {
		this.committedDirIsUp = is;
	}


	/**
	 * Setter for servcedfloor.
	 * 
	 * @param fl floor to be set to serviced.
	 * @throws IllegalArgumentException on non existing floors
	 */
	public void setFloorIsServiced(int fl, boolean is) {
		if (fl >= servicesFloors.length || fl < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			this.servicesFloors[fl] = is;
		}
	}

	/**
	 * Setter for all servcedfloor.
	 * 
	 * @param areServiced TRUE-> all floors will be set to serviced, FALSE -> all
	 *                    floors unserviced.
	 */
	public void setAllFloorsUnOrServiced(boolean areServiced) {
		for (int i = 0; i < servicesFloors.length; i++) {
			this.servicesFloors[i] = areServiced;
		}
	}

	/**
	 * Setter for elevatorButtons.
	 * 
	 * @param fl floor where its button will be enabled.
	 * @throws IllegalArgumentException on non existing floors
	 */
	public void setElevatorButtonAtFloorEnabled(int fl, boolean is) {
		if (fl >= elevatorButtons.length || fl < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			this.elevatorButtons[fl] = is;
		}
	}

}
