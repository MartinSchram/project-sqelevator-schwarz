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

	private int error;

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
	public int GetElevatornumber() {
		return this.elevatorNumber;
	}

	/**
	 * Getter for elevatorAccel
	 * 
	 * @return current elevatorAccel
	 */
	public int GetElevatoraccel() {
		return this.elevatorAccel;
	}

	/**
	 * Getter for elevatorDoorStatus
	 * 
	 * @return current elevatorDoorStatus
	 */
	public int GetElevatordoorstatus() {
		return this.elevatorDoorStatus;
	}

	/**
	 * Getter for elevatorFloor
	 * 
	 * @return current elevatorFloor
	 */
	public int GetElevatorfloor() {
		return this.elevatorFloor;
	}

	/**
	 * Getter for elevatorPosition
	 * 
	 * @return current elevatorPosition
	 */
	public int GetElevatorposition() {
		return this.elevatorPosition;
	}

	/**
	 * Getter for elevatorSpeed
	 * 
	 * @return current elevatorSpeed
	 */
	public int GetElevatorspeed() {
		return this.elevatorSpeed;
	}

	/**
	 * Getter for elevatorWeight
	 * 
	 * @return current elevatorWeight
	 */
	public int GetElevatorweight() {
		return this.elevatorWeight;
	}

	/**
	 * Getter for target
	 * 
	 * @return current target
	 */
	public int GetTarget() {
		return this.target;
	}

	/**
	 * Getter for error
	 * 
	 * @return current error
	 */
	public int GetError() {
		return this.error;
	}

	/**
	 * Getter for committedDirIsUp
	 * 
	 * @return FALSE -> when commited dir is down
	 */
	public boolean GetCommittedDirIsUp() {
		return this.committedDirIsUp;
	}

	/**
	 * Getter for elevatorButtons
	 * 
	 * @return [] where TRUE -> floor idx pressed
	 */
	public boolean[] GetElevatorButtons() {
		return this.elevatorButtons;
	}

	/**
	 * Getter for servicesFloors
	 * 
	 * @return [] where TRUE -> floor at idx is serviced
	 */
	public boolean[] GetservicesFloors() {
		return this.servicesFloors;
	}

	/* SETTERS */

	/**
	 * Setter for elevatorNumber
	 * 
	 * @param val to be set
	 */
	public void SetElevatornumber(int val) {
		this.elevatorNumber = val;
	}

	/**
	 * Setter for elevatorAccel
	 * 
	 * @param val to be set
	 */
	public void SetElevatoraccel(int val) {
		this.elevatorAccel = val;
	}

	/**
	 * Setter for elevatorDoorStatus
	 * 
	 * @param val to be set
	 */
	public void SetElevatordoorstatus(int val) {
		this.elevatorDoorStatus = val;
	}

	/**
	 * Setter for elevatorFloor
	 * 
	 * @param val to be set
	 */
	public void SetElevatorfloor(int val) {
		this.elevatorFloor = val;
	}

	/**
	 * Setter for elevatorPosition
	 * 
	 * @param val to be set
	 */
	public void SetElevatorposition(int val) {
		this.elevatorPosition = val;
	}

	/**
	 * Setter for elevatorSpeed
	 * 
	 * @param val to be set
	 */
	public void SetElevatorspeed(int val) {
		this.elevatorSpeed = val;
	}

	/**
	 * Setter for elevatorWeight
	 * 
	 * @param val to be set
	 */
	public void SetElevatorweight(int val) {
		this.elevatorWeight = val;
	}

	/**
	 * Setter for target
	 * 
	 * @param val to be set
	 */
	public void SetTarget(int val) {
		this.target = val;
	}

	public void SetCommitedDirIsUp(boolean is) {
		this.committedDirIsUp = is;
	}

	/**
	 * Setter for error
	 * 
	 * @param val to be set
	 */
	public void SetError(int val) {
		this.error = val;
	}

	/**
	 * Setter for servcedfloor.
	 * 
	 * @param fl floor to be set to serviced.
	 * @throws IllegalArgumentException on non existing floors
	 */
	public void SetFloorIsServiced(int fl, boolean is) {
		if (fl > servicesFloors.length || fl < 0) {
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
	public void SetAllFloorsUnOrServiced(boolean areServiced) {
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
	public void SetElevatorButtonAtFloorEnabled(int fl, boolean is) {
		if (fl > elevatorButtons.length || fl < 0) {
			throw new IllegalArgumentException("The floor number does not exist");
		} else {
			this.elevatorButtons[fl] = is;
		}
	}

}
