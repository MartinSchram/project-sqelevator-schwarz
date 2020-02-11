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
		pdata.SetAllFloorsUnOrServiced(true);
		paction = new ElevatorActions();
	}

	/**
	 * Directly set the actions from gui
	 * 
	 * @param a array of actions from the gui
	 */
	public void SetActions(ElevatorActions a) {
		paction = a;
	}
	

	/**
	 * Gets a clone of the data structure of ElevatorData of current elevator
	 * 
	 * @return clone of updated ElevatorData
	 * @throws CloneNotSupportedException
	 * @throws RemoteException            rmi interface hung up
	 */
	public ElevatorData GetData() throws CloneNotSupportedException, RemoteException {
		UpdateData();
		return this.pdata.clone();
	}
	
	
	public boolean[] GetServicedFloors() {
		return this.pdata.GetservicesFloors();
	}

	
	public int GetTarget() {
		return this.pdata.GetTarget();
	}
	
	/**
	 * Updates the data from the rmi and stores in internal ElevatorData inst.
	 * 
	 * @throws RemoteException rmi interface hung up
	 */
	public void UpdateData() throws RemoteException {
		pdata.SetElevatornumber(idx);
		pdata.SetElevatoraccel(inst.getElevatorAccel(idx));
		pdata.SetElevatordoorstatus(inst.getElevatorDoorStatus(idx));
		pdata.SetElevatorfloor(inst.getElevatorFloor(idx));
		pdata.SetElevatorposition(inst.getElevatorPosition(idx));
		pdata.SetElevatorspeed(inst.getElevatorSpeed(idx));
		pdata.SetElevatorweight(inst.getElevatorWeight(idx));
		for (int i = 0; i < fl; i++) {
			pdata.SetFloorIsServiced(i, inst.getServicesFloors(idx, i));
			pdata.SetElevatorButtonAtFloorEnabled(i, inst.getElevatorButton(idx, i));
		}
		pdata.SetTarget(inst.getTarget(idx));
		pdata.SetCommitedDirIsUp(pdata.GetTarget() >pdata.GetElevatorposition());

	}
	
	
	/**
	 * Sets the new target floor
	 * @throws RemoteException rmi interface hung up
	 */
	public void UpdateTarget() throws RemoteException {
		inst.setTarget(idx, paction.TargetFl);
		for(int i =0;i<paction.ServicesFloors.length;i++)
			inst.setServicesFloors(idx, i, paction.ServicesFloors[i]);
	}

}
