package war.players;

import java.util.ArrayList;

import common.players.Player;

import war.characters.Army;


/**
 * ArmyPlayer defines the base player for the war game
 * 
 * @author Group 7
 * @version 1.0
 */

public class ArmyPlayer extends Player {
	
	private int foodstock;
	private int warriorsleft;
	private ArrayList<Army> deployed;

	/**
	 * ArmyPlayer constructor
	 * 
	 * @param name the name of the player
	 */
	public ArmyPlayer(String name) {
		
		super(name);
		
		this.foodstock = 10;
		this.warriorsleft = 35;
		
		this.deployed = new ArrayList<Army>();
		
	}
	
	/**
	 * foodstock attribute getter
	 * 
	 * @return the amount of food the player has
	 */
	public int getFoodStock() {
		return this.foodstock;
	}
	
	/**
	 * warriorleft attribute getter
	 * 
	 * @return the amount of warriors the player can still deploy
	 */
	public int getWarriorsLeft() {
		return this.warriorsleft;
	}

	/**
	 * deployed attribute getter
	 * 
	 * @return a list of the deployed armies
	 */
	public ArrayList<Army> getDeployed() {
		return this.deployed;
	}
	
	/**
	 * Add food to the player
	 * 
	 * @param n the amount of food to add
	 */
	public void addFood(int n) {
		this.foodstock += n;
	}

	/**
	 * Flag an army as deployed
	 * Add an army after a deployment
	 * 
	 * @param army the army to be flagged
	 */
	public void addArmy(Army army) {
		this.deployed.add(army);
		this.warriorsleft -= army.getSize();
	}
	
	/**
	 * Flag an army as deployed
	 * Add an army after a betray
	 * 
	 * @param army the army to be flagged
	 */
	public void addRalliedArmy(Army army) {
		this.deployed.add(army);
	}
	
	/**
	 * Unflag an army
	 *  
	 * @param army the army to be unflagged
	 */
	public void removeArmy(Army army) {
		this.deployed.remove(army);
	}
	
	/**
	 * Gives a cleaner representation of the player deployed armies
	 *
	 * @return a string that can be printed to show the deployed armies
	 */
	public String deployedToString() {
		String str = "";
		for (Army a : this.deployed) {
			str += "- " + a.toString() + " -\n";
		}
		return str;
	}

}
