package at.fhhagenberg.sqelevator;

public class ElevatorData {

	private int committedDirection;
	
	private int elevatorAccel;
	
	private boolean []elevatorButtons;
	
	private int elevatorDoorStatus;
	
	private int elevatorFloor;

	private int elevatorPosition;

	private int elevatorSpeed;

	private int elevatorWeight;

	private boolean []floorButtonsDown;

	private boolean []floorButtonsUp;

	private int logging;

	private boolean []servicesFloors;

	private int target;

	private int error;
	
	public ElevatorData() {
		
	}
	
	public int getFloorHeight() {
		// BEGIN_DEBUG_ONLY
		return 10;
		// END_DEBUG_ONLY
	}
	
	public int getFloorNum() {
		// BEGIN_DEBUG_ONLY
		return 10;
		// END_DEBUG_ONLY
	}
}
