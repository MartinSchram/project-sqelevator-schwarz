package at.fhhagenberg.sqelevator.model;

/**
 * @author Martin Schram
 * Container for elevator actions.
 *
 */
public class ElevatorActions {


	/**
	 * default-ctor for Actions to be set onto the elevator on a cycle
	 */
	public ElevatorActions() {		
	}
	
	/**
	 * ctor with init of all members for Actions to be set onto the elevator on a cycle
	 */
	public ElevatorActions(int target, boolean commitedDirIsUp, boolean isAuto, boolean[] servicedFloors) {	
		this.TargetFl=target;
		this.CommitedDirIsUp =commitedDirIsUp;
		this.AutoMode=isAuto;
		this.ServicesFloors=servicedFloors;
	}
	
	/**
	 * Clone to get shallow copy.
	 */
	@Override
    protected ElevatorActions clone() throws CloneNotSupportedException {
        return (ElevatorActions)super.clone();
    }
	
	/**
	 * Set the Targetfloor
	 * Property to be set by gui-binding
	 */
	public int TargetFl;
	
	/**
	 * Set the ComitedDir
	 * Property to be set by gui-binding
	 */
	public boolean CommitedDirIsUp;
	
	/**
	 * Set Automode
	 * Property to be set by gui-binding
	 */
	public boolean AutoMode;
	
	
	/**
	 * Set Servicefloors directly
	 * Property to be set by gui-binding
	 */
	public boolean[] ServicesFloors;
	
	
	
}
