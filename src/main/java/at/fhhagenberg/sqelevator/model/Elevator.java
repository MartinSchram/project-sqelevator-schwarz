package at.fhhagenberg.sqelevator.model;

import java.rmi.RemoteException;
import sqelevator.IElevator;

/**
 * @author Martin Schram Class for representing one instance of elevator.
 *         Communicates with the interface of the elevator module.
 */
public class Elevator {
	private IElevator inst = null;
	private int idx;
	private int fl;
	private ElevatorData pdata;
	private ElevatorActions paction;

	/**
	 * Sets the instance of the module.
	 * 
	 * @param pinst instance from RMI
	 * @return False -> already set.
	 */
	public boolean setInstance(IElevator pinst) {
		if (inst == null) {
			this.inst = pinst;
			return true;
		}
		return false;

	}

	/**
	 * Ctor without module-instance
	 * 
	 * @param pidx elevator number
	 * @param pfl  floors in building
	 */
	public Elevator(int pidx, int pfl) {
		this.idx = pidx;
		this.fl = pfl;
		pdata = new ElevatorData(idx, pfl);
	}

	/**
	 * Ctor with module-instance
	 * 
	 * @param pidx  elevator number
	 * @param pfl   floors in building
	 * @param pinst instance from RMI
	 */
	public Elevator(int pidx, int pfl, IElevator pinst) {
		setInstance(pinst);
		this.idx = pidx;
		this.fl = pfl;
		pdata = new ElevatorData(idx, pfl);
		pdata.setAllFloorsUnOrServiced(true);
		paction = new ElevatorActions();
	}

	/**
	 * Directly set the actions from gui
	 * 
	 * @param a array of actions from the gui
	 */
	public void setActions(ElevatorActions a) {
		paction = a;
	}
	

	/**
	 * Gets a clone of the data structure of ElevatorData of current elevator
	 * 
	 * @return clone of updated ElevatorData
	 * @throws CloneNotSupportedException
	 * @throws RemoteException            rmi interface hung up
	 */
	public ElevatorData getData() throws CloneNotSupportedException, RemoteException {
		updateData();
		return this.pdata.clone();
	}
	
	
	public boolean[] getServicedFloors() {
		return this.pdata.getservicesFloors();
	}

	
	public int getTarget() {
		return this.pdata.getTarget();
	}
	
	/**
	 * Updates the data from the rmi and stores in internal ElevatorData inst.
	 * 
	 * @throws RemoteException rmi interface hung up
	 */
	public void updateData() throws RemoteException {
		pdata.setElevatornumber(idx);
		pdata.setElevatoraccel(inst.getElevatorAccel(idx));
		pdata.setElevatordoorstatus(inst.getElevatorDoorStatus(idx));
		pdata.setElevatorfloor(inst.getElevatorFloor(idx));
		pdata.setElevatorposition(inst.getElevatorPosition(idx));
		pdata.setElevatorspeed(inst.getElevatorSpeed(idx));
		pdata.setElevatorweight(inst.getElevatorWeight(idx));
		for (int i = 0; i < fl; i++) {
			pdata.setFloorIsServiced(i, inst.getServicesFloors(idx, i));
			pdata.setElevatorButtonAtFloorEnabled(i, inst.getElevatorButton(idx, i));
		}
		pdata.setTarget(inst.getTarget(idx));
		pdata.setCommitedDirIsUp(pdata.getTarget() >pdata.getElevatorposition());

	}
	
	
	/**
	 * Sets the new target floor
	 * @throws RemoteException rmi interface hung up
	 */
	public void updateTarget() throws RemoteException {
		inst.setTarget(idx, paction.targetFl);
		for(int i =0;i<paction.servicesFloors.length;i++)
			inst.setServicesFloors(idx, i, paction.servicesFloors[i]);
	}

}
