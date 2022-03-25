package common.characters;

import common.board.Cell;
import common.board.Ground;
import common.board.Ressource;


/**
 * Unit gathers common attributes and methods for unit used in both games
 *
 * @author Group 7
 * @version 1.0
 */

public abstract class Unit {
	
    protected int gold;
    protected Cell cell;
    protected Ground ground;
    protected Ressource ressource;

    /**
     * Unit constructor
     */
    protected Unit() {
        this.gold = 0;
        
        this.cell = null;
        this.ground = null;
        this.ressource = null;
    }

	/**
	 * gold attribute Getter
	 *
	 * @return the amount of gold a unit has
	 */
    public int getGold() {
        return this.gold;
    }

	/**
	 * cell attribute Getter
	 *
	 * @return the cell where the unit stands on
	 */
    public Cell getCell() {
        return this.cell;
    }

	/**
	 * ground attribute Getter
	 *
	 * @return the ground of the cell
	 */
    public Ground getGround() {
        return this.ground;
    }

	/**
	 * resource attribute Getter
	 *
	 * @return the resource type harvested by the unit
	 */
    public Ressource getRessource() {
        return this.ressource;
    }
    
	/**
	 * Set cell related attributes
	 *
	 * @param c the cell used to set attributes
	 */
    public void setCell(Cell c) {
    	this.cell = c;
    	this.ground = c.getType();
    	this.ressource = c.getType().getRessource();
    }

	/**
	 * Unset cell related attributes
	 */
    public void removeCell() {
    	this.ground = null;
    	this.ressource = null;
        this.cell = null;
    }

	/**
	 * Gives an external representation of a unit
	 * This shows details about the unit
	 *
	 * @return a string that can be printed to display information
	 */
    public String toString() {
    	return String.format("Gold: %d, Cell: %s, Ground: %s, Ressource: %s", this.gold, this.cell, this.ground, this.ressource);
    }
    
}