package common.board;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Generator is used to generate a grid based on a id
 * It allows implementations of different generations
 * 
 * @author Group 7
 * @version 1.0
 */

public class Generator {
	
	private int dimx;
	private int dimy;
	private int id;
	private Cell[][] cells;
	private ArrayList<Cell> availablecells;
	
	/**
	 * Generator constructor
	 * 
	 * @param id the id used for the generation (filter the methods to call)
	 * @param dimx the length used to generate the grid
	 * @param dimy the width used to generate the grid
	 */
	public Generator(int id, int dimx, int dimy) {
		
		/* Default Board Generator */
		this.id = id;
		if (this.id == 0) {
			
			this.dimx = dimx;
			this.dimy = dimy;
			
			int emptyCells = (this.dimx*this.dimy) / 3;
			if ((emptyCells % 2) != 0) emptyCells -= 1;
			
			this.availablecells = new ArrayList<Cell>();
			
			this.cells = makeArray(this.dimx, this.dimy, emptyCells);
		}
		/* Add other generators based on id here*/
		
		
		else {
			this.availablecells = null;
			this.cells = null;
		}
		
	}
	
	/**
	 * id attribute Getter
	 *
	 * @return the id of the generator
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * dimx attribute Getter
	 *
	 * @return the length of the grid
	 */
	public int getDimX() {
		return this.dimx;
	}
	
	/**
	 * dimy attribute Getter
	 *
	 * @return the width of the grid
	 */
	public int getDimY() {
		return this.dimy;
	}
	
	/**
	 * cells attribute Getter
	 *
	 * @return the grid of cells
	 */
	public Cell[][] getCells() {
		return this.cells;
	}
	
	/**
	 * availablecells attribute Getter
	 *
	 * @return a list of available cells
	 */
	public ArrayList<Cell> getAvailableCells() {
		return this.availablecells;
	}
	
	/**
	 * Grid generation method for ID 0
	 *
	 * @param dimx the length of the grid
	 * @param dimy the width of the grid
	 * @param emptyCells the amount of available cells (no more than 1/3 for this generation)
	 * @return a grid of cells
	 */
	private Cell[][] makeArray(int dimx, int dimy, int emptyCells) {
		
		Cell[][] cells = new Cell[dimy][dimx];
		int groundedcell = emptyCells;

		boolean uneven = false;
		if (dimx % 2 == 1) {
			uneven = true;
		}
		
		for (int i = 0; i < dimy; i++) {
			int bool;
			
			for (int j = 0; j < dimx; j += 2) {
				
				if (uneven && j == dimx - 1) {
					cells[i][j] = new Cell(j, i, Ground.Ocean);
				}
				
				else {
					bool = ThreadLocalRandom.current().nextInt(3);
					
					if (groundedcell != 0 && bool == 1) {
						int type1 = ThreadLocalRandom.current().nextInt(Ground.values().length-1);
						int type2 = ThreadLocalRandom.current().nextInt(Ground.values().length-1);
						Cell a = new Cell(j, i, Ground.values()[type1]);
						Cell b = new Cell(j + 1, i, Ground.values()[type2]);
						cells[i][j] = a;
						cells[i][j + 1] = b;
						
						this.availablecells.add(a);
						this.availablecells.add(b);
						
						groundedcell -= 2;
					}
					
					else {
						cells[i][j] = new Cell(j, i, Ground.Ocean);
						cells[i][j+1] = new Cell(j + 1, i, Ground.Ocean);
					}
				}
			}
		}
		return cells;
	}
	
	/* Add grid new generation methods here */
	
}
