package common.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import common.characters.Unit;


/** 
 * Board allows the creation of a board for both games
 * it can be created with default values, or with specific ones such as with a generator
 * 
 * @author Group 7
 * @version 1.0
 */

public class Board {
	
	private int dimx;
	private int dimy;
	private Generator generator;
	private Cell[][] cells;
	private ArrayList<Cell> availablecells;
	private int emptyCells;
	
	/**
	 * Default Board Constructor
	 */
	public Board() {
		
		this.dimx = 10;
		this.dimy = 10;
		
		this.generator = new Generator(0, this.dimx, this.dimy);
		this.cells = this.generator.getCells();
		this.availablecells = this.generator.getAvailableCells();
		this.emptyCells = this.generator.getAvailableCells().size();
	}
	
	/**
	 * Board Constructor 2
	 * 
	 * @param dimy the width of the grid
	 */
	public Board(int dimy) {
		
		this.dimx = dimy*3;
		this.dimy = dimy;
		
		this.generator = new Generator(0, this.dimx, this.dimy);
		this.cells = this.generator.getCells();
		this.availablecells = this.generator.getAvailableCells();
		this.emptyCells = this.generator.getAvailableCells().size();
	}
	
	/**
	 * Board Constructor 3
	 * 
	 * @param dimx the length of the grid
	 * @param dimy the width of the grid
	 */
	public Board(int dimx, int dimy) {
		
		this.dimx = dimx;
		this.dimy = dimy;
		
		this.generator = new Generator(0, this.dimx, this.dimy);
		
		this.cells = this.generator.getCells();
		this.availablecells = this.generator.getAvailableCells();
		this.emptyCells = this.generator.getAvailableCells().size();
	}
	
	/**
	 * Board Constructor 4
	 * 
	 * @param generator the generator to use (generator already has the grid dimensions)
	 */
	public Board(Generator generator) {
		
		this.generator = generator;
		this.dimx = this.generator.getDimX();
		this.dimy = this.generator.getDimY();
		
		this.cells = this.generator.getCells();
		this.availablecells = this.generator.getAvailableCells();
		this.emptyCells = this.generator.getAvailableCells().size();
		
	}
	
	/**
	 * dimx attribute Getter
	 *
	 * @return the lenght of the board
	 */
	public int getDimX() {
		return this.dimx;
	}
	
	/**
	 * dimy attribute Getter
	 *
	 * @return the width of the board
	 */
	public int getDimY() {
		return this.dimy;
	}
	
	/**
	 * generator attribute Getter
	 *
	 * @return the generator of the board
	 */
	public Generator getGenerator() {
		return this.generator;
	}
	
	/**
	 * cells attribute Getter
	 *
	 * @return the grid of the board which is an array of arrays of cells
	 */
	public Cell[][] getCells() {
		return this.cells;
	}
	
	/**
	 * availablecells attribute Getter
	 *
	 * @return a list of cells that are available for a unit to deploy
	 */
	public ArrayList<Cell> getAvailableCells() {
		return this.availablecells;
	}
	
	/**
	 * emptycells attribute Getter
	 *
	 * @return the amount of available cells
	 */
	public int getEmptyCells() {
		return this.emptyCells;
	}
	
	/**
	 * Give a random available cell on the board
	 *
	 * @return an available cell if there is, <code>null</code> if not
	 */
	public Cell getRandomAvailableCells() {
		if (this.emptyCells == 0) {
			return null;
		}
		else {
			int pos = ThreadLocalRandom.current().nextInt(0, this.emptyCells);
			return this.availablecells.get(pos);
		}
	}
	
	/**
	 * Add an available cell
	 * Used to modify the available cells after a unit remove during a game
	 *
	 * @param cell the cell to be added as available (must be a cell that was previously available)
	 */
	public void addAvailableCell(Cell cell) {
		this.availablecells.add(cell);
		emptyCells++;
	}
	
	/**
	 * Remove an available cell
	 * Used to modify the available cells after a deployment during a game
	 * if the cell is not present, the method will not notify it
	 *
	 * @param cell the cell to be removed from available
	 */
	public void removeAvailableCell(Cell cell) {
		Iterator<Cell> it = this.availablecells.iterator();
		while (it.hasNext()) {
			Cell c = it.next();
			if (c == cell) {
				it.remove();
				this.emptyCells--;
			}
		}
	}
	
	/**
	 * Check if there is no more available cell
	 *
	 * @return <code>true</code> if there is no more available cell, <code>false</code> if not
	 */
	public boolean isFull() {
		return this.availablecells.isEmpty();
	}
	
	/**
	 * Check if a given cell is available
	 *
	 * @param cell the cell to check as available
	 * @return <code>true</code> if the cell is available, <code>false</code> if not
	 */
	public boolean isCellAvailable(Cell cell) {
		for (Cell c : this.availablecells) {
			if (c == cell) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Select a cell in the grid based on given coordinates
	 *
	 * @param x the x coordinate of the cell to find
	 * @param y the y coordinate of the cell to find
	 * @return a cell if the coordinates are correct (in range of the grid), <code>null</code> if not
	 */
	public Cell selectCell(int x, int y) {
		try {
			return this.cells[y][x];
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Give the adjacent cells (the ones that are in North, West, South, East) of a given cell
	 *
	 * @param c the cell 
	 * @return a list of cells (each cell is adjacent to c), the list can be empty
	 */
	public ArrayList<Cell> getAdjacentCellsOf(Cell c) {
		
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
		
		Cell North = this.selectCell(x, y - 1);
		if (North != null) adjacentCells.add(North);
		
		Cell West = this.selectCell(x - 1, y);
		if (West != null) adjacentCells.add(West);
		
		Cell South = this.selectCell(x, y + 1);
		if (South != null) adjacentCells.add(South);
		
		Cell East = this.selectCell(x + 1, y);
		if (East != null) adjacentCells.add(East);
		
		return adjacentCells;
	}
	
	/**
	 * Set a unit on the board and on the cell it stands on (cell is found by means of unit)
	 * This method is used for unit deployment during a game
	 *
	 * @param unit the unit to set on the board (also set it on the cell)
	 */
	public void setUnit(Unit unit) {
		Cell c = unit.getCell();
		if (this.isCellAvailable(c)) {
			c.setUnit(unit);
			this.removeAvailableCell(c);
		}
	}
	
	/**
	 * Remove a unit from the board and from the cell it stands on (cell is found by means of unit)
	 * This method is used for unit remove during a game
	 *
	 * @param unit the unit to remove from the board (also remove it from the cell)
	 */
	public void removeUnit(Unit unit) {
		Cell c = unit.getCell();
		if (c.getUnit() != null) {
			c.removeUnit();
			this.addAvailableCell(c);
		}
	}
	
	/**
	 * Gives an external representation of the board
	 * This helps players to see where they can deploy units
	 *
	 * @return a string that can be printed to display the board
	 */
	public String toString() {
		String outline = "#";
		String str = outline.repeat(dimx + 2) + "\n";
		for (int i = 0; i < dimy; i++) {
			str += outline;
			for (int j = 0; j < dimx; j++) {
				str += this.cells[i][j].getType();
			}
			str += "#\n";
		}
		str += outline.repeat(dimx + 2) + "\n";
		return str;
	}

}
