package field.players;

import java.util.ArrayList;

import common.players.Player;

import field.characters.Worker;


/**
 * WorkerPlayer defines the base player for the field game
 * 
 * @author Group 7
 * @version 1.0
 */

public class WorkerPlayer extends Player {
	
	private ArrayList<Worker> deployed;

	/**
	 * WorkerPlayer constructor
	 * 
	 * @param name the name of the player
	 */
	public WorkerPlayer(String name) {
		super(name);
		
		this.gold = 15;
		
		this.deployed = new ArrayList<Worker>();
	}
	
	/**
	 * deployed attribute getter
	 * 
	 * @return a list of the deployed workers
	 */
	public ArrayList<Worker> getDeployed() {
		return this.deployed;
	}
	
	/**
	 * Flag a worker as deployed
	 * 
	 * @param worker the worker to be flagged
	 */
	public void addWorker(Worker worker) {
		this.deployed.add(worker);
	}
	
	/**
	 * Unflag a worker
	 *  
	 * @param worker the worker to be unflagged
	 */
	public void removeWorker(Worker worker) {
		this.deployed.remove(worker);
	}
	
	/**
	 * Gives a cleaner representation of the player deployed workers
	 *
	 * @return a string that can be printed to show the deployed workers
	 */
	public String deployedToString() {
		String str = "";
		for (Worker w : this.deployed) {
			str += "- " + w.toString() + " -\n";
		}
		return str;
	}

}
