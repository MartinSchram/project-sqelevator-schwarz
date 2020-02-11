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
	private ElevatorScheduler scheduler;
	private ElevatorUI m_UIController;
	private SystemData observerdata;

	/**
	 * Backend interfce binded to gui
	 */
	public IElevator BackEnd;

	/**
	 * Binding interface to the gui
	 */
	public ElevatorActions[] GuiActions;

	/**
	 * default ctor of controller
	 */
	public ElevatorController() {

	}


	public ElevatorController(ElevatorUI UIController) {
		this.addObserver(UIController);
		m_UIController=UIController;
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
		this.BackEnd = inst;
	}

	/**
	 * Get rmi inst from system
	 * @return successfully attached
	 */
	public boolean ConnectRMI() {
		try {
			this.BackEnd = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
			return true;
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			return false;

		}
	}
	
	/**
	 * Status flag
	 * @return TRUE -> switched on 
	 */
	public boolean IsOn() {
		return this.on;
	}
	
	/**
	 * set rmi inst from manually
	 * @return successfully attached
	 */
	public boolean SetInst(IElevator inst) {
		if(inst != null) {
			this.BackEnd = inst;
			return true;
		}else return false;
	}

	/**
	 * Setter for the elevator count
	 * 
	 * @param count number of elevators in building
	 */
	public void SetElevatorCount(int count) {
		this.elevatorCount = count;
	}

	/**
	 * Setter for the floor count
	 * 
	 * @param count number of floors in building
	 */
	public void SetFloorCount(int count) {
		this.floors = count;
	}

	/**
	 * Switches the elevator in on mode and calls init() for all elevators
	 * 
	 * @return TRUE -> successfully switched on
	 */
	public boolean SwitchOn() {
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
	public SystemData GetObserverData() throws CloneNotSupportedException, RemoteException {
		this.observerdata.SetBuildingButonDownData(building.GetFloorButtonDownStates());
		this.observerdata.SetBuildingButonUpData(building.GetFloorButtonUpStates());
		ElevatorData[] ted = new ElevatorData[elevatorCount];
		for (int i = 0; i < ted.length; i++) {
			ted[i] = elevators[i].GetData();
		}
		this.observerdata.SetElevatorData(ted);
		return this.observerdata;
	}

	/**
	 * Switches the elevator in on mode and calls init() for all elevators
	 * 
	 * @return TRUE -> successfully switched on
	 */
	public void SwitchOff() {
		this.on = false;
	}

	/**
	 * Initialization of the elevators and data structures. - gets the rmi interface
	 * instance
	 * 
	 * @return TRUE -> successfully initialized
	 */
	public boolean init() {
		if (!this.on && GetPropertiesFromBackend()) {
			this.GuiActions = new ElevatorActions[elevatorCount];
			this.elevators = new Elevator[elevatorCount];
			this.observerdata = new SystemData(elevatorCount);

			try {

				for(int i=0;i<elevatorCount;i++){
					this.GuiActions[i]=m_UIController.m_ElevatorVector.get(i).oneElevAction;
				}

				for (int i = 0; i < elevators.length; i++) {
					elevators[i] = new Elevator(i, floors, this.BackEnd);
				}
				this.building = new Building(floors, this.BackEnd);
				this.on = true;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return this.on;

	}

	public void RunCyclic() {
		while (this.on) {
			try {
				RunCycle();
			} catch (RemoteException | CloneNotSupportedException | InterruptedException e) {
				this.SwitchOff();
			}
		}
	}

	public void RunCycle() throws RemoteException, CloneNotSupportedException, InterruptedException {
		if (this.on) {
			for (Elevator e : elevators)
				e.UpdateData();
			building.Update();
			ElevatorScheduler.Schedule(elevators, GuiActions.clone(), building);
			for (Elevator e : elevators)
				e.UpdateTarget();
			Thread.sleep(building.GetClockTick());
			for (Elevator e : elevators)
				e.UpdateData();
			setChanged();
			SystemData sd = GetObserverData();
			notifyObservers(sd);
		}
	}

	private boolean GetPropertiesFromBackend() {
		if (this.floors == 0 || this.elevatorCount == 0) {
			try {
				SetElevatorCount(BackEnd.getElevatorNum());
				SetFloorCount(BackEnd.getFloorNum());
				return true;
			} catch (RemoteException e) {
				return false;
			}
		} else
			return true;
	}

}
