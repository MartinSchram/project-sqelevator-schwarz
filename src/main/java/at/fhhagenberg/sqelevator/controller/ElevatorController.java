package at.fhhagenberg.sqelevator.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Observable;

import at.fhhagenberg.sqelevator.view.ElevatorUI;
import sqelevator.IElevator;
import at.fhhagenberg.sqelevator.model.*;

/**
 * @author Martin Schram Class for representing the elevator controller
 *         -instantiates interface of the elevator module. -handles gui inputs,
 *         starts and controls flow -provides state data.
 */
public class ElevatorController extends Observable {

	private boolean on;
	private int floors;
	private int elevatorCount;
	private Elevator[] elevators;
	private Building building;
	private ElevatorUI uIController;
	private SystemData observerdata;

	/**
	 * Backend interfce binded to gui
	 */
	public IElevator backEnd;

	/**
	 * Binding interface to the gui
	 */
	public ElevatorActions[] guiActions;

	/**
	 * default ctor of controller
	 */
	public ElevatorController() {

	}

	public ElevatorController(ElevatorUI uIController) {
		this.addObserver(uIController);
		uIController = uIController;
	}

	/**
	 * ctor of controller
	 * 
	 * @param pelevs number of elevators in building
	 * @param pfl    number of floors in building
	 * @param inst   instance of backend
	 */
	public ElevatorController(int pelevs, int pfl, IElevator inst) {
		this.elevatorCount = pelevs;
		this.floors = pfl;
		this.backEnd = inst;
	}

	/**
	 * Get rmi inst from system
	 * 
	 * @return successfully attached
	 */
	public boolean connectRMI() {
		try {
			this.backEnd = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
			return true;
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			return false;

		}
	}

	/**
	 * Status flag
	 * 
	 * @return TRUE -> switched on
	 */
	public boolean isOn() {
		return this.on;
	}

	/**
	 * set rmi inst from manually
	 * 
	 * @return successfully attached
	 */
	public boolean setInst(IElevator inst) {
		if (inst != null) {
			this.backEnd = inst;
			return true;
		} else
			return false;
	}

	/**
	 * Setter for the elevator count
	 * 
	 * @param count number of elevators in building
	 */
	public void setElevatorCount(int count) {
		this.elevatorCount = count;
	}

	/**
	 * Setter for the floor count
	 * 
	 * @param count number of floors in building
	 */
	public void setFloorCount(int count) {
		this.floors = count;
	}

	/**
	 * Switches the elevator in on mode and calls init() for all elevators
	 * 
	 * @return TRUE -> successfully switched on
	 */
	public boolean switchOn() {
		this.on = init();
		return this.on;
	}

	/**
	 * Getter for the data structure of the observer data. Updates all data to be
	 * observed.
	 * 
	 * @return updated data
	 * @throws CloneNotSupportedException
	 * @throws RemoteException            rmi interface hung up
	 */
	public SystemData getObserverData() throws CloneNotSupportedException, RemoteException {
		this.observerdata.setBuildingButonDownData(building.getFloorButtonDownStates());
		this.observerdata.setBuildingButonUpData(building.getFloorButtonUpStates());
		ElevatorData[] ted = new ElevatorData[elevatorCount];
		for (int i = 0; i < ted.length; i++) {
			ted[i] = elevators[i].getData();
		}
		this.observerdata.setElevatorData(ted);
		return this.observerdata;
	}

	/**
	 * Switches the elevator in on mode and calls init() for all elevators
	 * 
	 * @return TRUE -> successfully switched on
	 */
	public void switchOff() {
		this.on = false;
	}

	/**
	 * Initialization of the elevators and data structures. - gets the rmi interface
	 * instance
	 * 
	 * @return TRUE -> successfully initialized
	 */
	public boolean init() {
		if (!this.on && getPropertiesFromBackend()) {
			this.guiActions = new ElevatorActions[elevatorCount];
			this.elevators = new Elevator[elevatorCount];
			this.observerdata = new SystemData(elevatorCount);

			try {
				if (uIController != null) {
					for (int i = 0; i < elevatorCount; i++) {
						this.guiActions[i] = uIController.m_ElevatorVector.get(i).oneElevAction;
					}
				}

				for (int i = 0; i < elevators.length; i++) {
					elevators[i] = new Elevator(i, floors, this.backEnd);
				}
				this.building = new Building(floors, this.backEnd);
				this.on = true;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return this.on;

	}

	public void runCyclic() {
		while (this.on) {
			try {
				runCycle();
			} catch (Exception e) {
				this.switchOff();
			}
		}
	}

	public void runCycle() throws RemoteException, CloneNotSupportedException, InterruptedException {
		if (this.on) {
			for (Elevator e : elevators)
				e.updateData();
			building.update();
			ElevatorScheduler.schedule(elevators, guiActions.clone(), building);
			for (Elevator e : elevators)
				e.updateTarget();
			Thread.sleep(building.getClockTick());
			for (Elevator e : elevators)
				e.updateData();
			setChanged();
			SystemData sd = getObserverData();
			notifyObservers(sd);
		}
	}

	private boolean getPropertiesFromBackend() {
		if(this.backEnd == null) return false;
		if (this.floors == 0 || this.elevatorCount == 0) {
			try {
				setElevatorCount(backEnd.getElevatorNum());
				setFloorCount(backEnd.getFloorNum());
				return true;
			} catch (RemoteException e) {
				return false;
			}
		} else
			return true;
	}

}
