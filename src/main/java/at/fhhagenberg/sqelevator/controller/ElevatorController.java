package at.fhhagenberg.sqelevator.controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Observable;

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
	 * ctor of controller
	 * 
	 * @param pelevs number of elevators in building
	 * @param pfl    number of floors in building
	 */
	public ElevatorController(int pelevs, int pfl) {
		this.elevatorCount = pelevs;
		this.floors = pfl;
	}
	
	/**
	 * ctor of controller
	 * 
	 * @param pelevs number of elevators in building
	 * @param pfl    number of floors in building
	 * @param inst   instance  of backend
	 */
	public ElevatorController(int pelevs, int pfl,IElevator inst) {
		this.elevatorCount = pelevs;
		this.floors = pfl;
		this.BackEnd = inst;
	}
	
	public boolean ConnectRMI() {
		try {
			this.BackEnd =(IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
			return true;
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			return false;

		}
	}

	/**
	 * Setter for the elevator count
	 * @param count number of elevators in building
	 */
	public void SetElevatorCount(int count) {
		this.elevatorCount=count;
	}
	
	/**
	 * Setter for the floor count
	 * @param count number of floors in building
	 */
	public void SetFloorCount(int count) {
		this.floors=count;
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
		if (!this.on && this.BackEnd != null&& this.floors != 0 && this.elevatorCount !=0) {
			this.GuiActions = new ElevatorActions[elevatorCount];
			this.elevators = new Elevator[elevatorCount];
			this.observerdata = new SystemData(elevatorCount);

			try {
				
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void RunCycle() throws RemoteException, CloneNotSupportedException, InterruptedException {
		if (this.on) {
			for (Elevator e : elevators)
				e.UpdateData();
			building.Update();
			ElevatorScheduler.Schedule(elevators, GuiActions, building);
			for (Elevator e : elevators)
				e.UpdateTarget();
			Thread.sleep(building.GetClockTick() * 3);
			for (Elevator e : elevators)
				e.UpdateData();
			setChanged();
	        notifyObservers();
		}
	}

}
