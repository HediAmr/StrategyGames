package field.actions.action;

import common.board.Cell;

import field.actions.FieldActions;
import field.characters.Worker;


/**
 * Deploy defines the action of deploying a worker
 * It needs a player, a board and a location to work properly
 * 
 * @author Group 7
 * @version 1.0
 */

public final class Deploy extends FieldActions {
	
	private static Cell location;
	
	/**
	 * Deploy constructor
	 */
	private Deploy() {
		super();
	}
	
	/**
	 * location attribute getter
	 * 
	 * @return the location in which the unit will be deployed
	 */
	public static Cell getLocation() {
		return location;
	}
	
	/**
	 * location attribute setter
	 * 
	 * @param cell the location to set
	 */
	public static void setLocation(Cell cell) {
		location = cell;
	}	
	
	/**
	 * Deploy a worker in the game
	 */
	public static void deploy() {
		Worker worker = new Worker(player);
		
		worker.setCell(location);
		player.addWorker(worker);
		board.setUnit(worker);
		
		System.out.println(String.format("%s a déployé un ouvrier en %s.\n", player, location));
	}

}
