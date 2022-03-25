package common.players;

import java.util.Hashtable;

import common.board.Ressource;


/** 
 * Player gathers common methods and attributes to define a player for both games
 *
 * @author Group 7
 * @version 1.0
 */

public abstract class Player {
	
	protected String name;
	protected int points;
	protected int gold;
	protected Hashtable<Ressource, Integer> inventory;

	
	/**
	 * Player constructor
	 * 
	 * @param name the name of the player
	 */
	protected Player(String name) {
		this.name = name;
		this.points = 0;
		this.gold = 0;
		
		this.inventory = new Hashtable<Ressource, Integer>();
		
		for (int i = 0; i < Ressource.values().length; i++) {
			this.inventory.put(Ressource.values()[i], 0);
		}
	}
	
	/**
	 * name attribute Getter
	 *
	 * @return the player name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * points attribute Getter
	 *
	 * @return the player points
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * gold attribute Getter
	 *
	 * @return the player golds
	 */
	public int getGold() {
		return this.gold;
	}
	
	/**
	 * inventory attribute Getter
	 *
	 * @return the player inventory such as <code>{Ressource1: Amount1, ...}</code>
	 */
	public Hashtable<Ressource, Integer> getInventory() {
		return this.inventory;
	}
	
	/**
	 * Add points to a player
	 *
	 * @param p the amount of points to add
	 */
	public void addPoints(int p) {
		this.points += p;
	}

	/**
	 * Add golds to a player
	 *
	 * @param g the amount of golds to add
	 */
	public void addGold(int g) {
		this.gold += g;
	}
	
	/**
	 * Get the resource amount in a player inventory
	 *
	 * @param r the resource
	 */
	public int getRessourceQuantity(Ressource r) {
		return this.inventory.get(r);
	}

	/**
	 * Add a resource amount to the player inventory
	 *
	 * @param n the amount
	 * @param r the resource
	 */
	public void updateRessource(int n, Ressource r) {
		int oldvalue = getRessourceQuantity(r);
		this.inventory.put(r, oldvalue + n);
	}

	/**
	 * Gives an external representation the player
	 * This shows information about the player
	 *
	 * @return a string that can be printed to these information
	 */
	public String toString() {
		return this.name;
	}
	
	/**
	 * Gives a cleaner representation of the player inventory
	 *
	 * @return a string that can be printed to show the inventory
	 */
	public String inventoryToString() {
		String str = "|";
		for (Ressource r : this.inventory.keySet()) {
			str += " " + r + ": " + this.getRessourceQuantity(r);
		}
		str += " |";
		return str;
	}

}
