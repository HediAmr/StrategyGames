package common.board;

import common.characters.Unit;


/**
 * Cell defines a cell
 * It can be created without a board; but a board cannot be created without cells
 *
 * @author Group 7
 * @version 1.0
 */

public class Cell {
	
	private int x;
	private int y;
	private Ground type;
	private Unit unit;
	
	/**
	 * Cell constructor
	 * 
	 * @param x the x coordinate of the cell
	 * @param y the y coordinate of the cell
	 * @param type the type of the cell
	 */
	public Cell(int x, int y, Ground type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	/**
	 * x attribute Getter
	 *
	 * @return the x coordinate of the cell
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * y attribute Getter
	 *
	 * @return the y coordinate of the cell
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * type attribute Getter
	 *
	 * @return the type of the cell
	 */
	public Ground getType() {
		return this.type;
	}
	
	/**
	 * unit attribute Getter
	 *
	 * @return the unit set on the cell, can be <code>null</code>
	 */
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * Check if a cell is grounded (a unit cannot be deployed on a ocean cell)
	 *
	 * @return <code>true</code> if the cell type is different from Ocean, <code>false</code> if not
	 */
	public boolean isGrounded() {
		return this.type != Ground.Ocean;
	}
	
	/**
	 * Set a unit on the cell only if the cell is grounded
	 *
	 * @param unit the unit to set on the cell
	 */
	public void setUnit(Unit unit) {
		if (this.isGrounded()) {
			this.unit = unit;
		}
	}
	
	/**
	 * Remove a unit from the cell, even if there is already no unit on it
	 */
	public void removeUnit() {
		this.unit = null;
	}
	
	/** 
	 * Gives an external representation of a cell
	 * This shows the cell positions such as (x, y)
	 *
	 * @return a string that can be printed to display the cell position in a board
	 */
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}

}
