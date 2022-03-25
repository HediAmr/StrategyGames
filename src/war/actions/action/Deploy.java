package war.actions.action;

import war.actions.WarActions;

import java.util.ArrayList;

import common.board.Cell;
import war.characters.Army;


/**
 * Deploy defines the action of deploying an army
 * It needs a size, a player, a board and a location to work properly
 * 
 * @author Group 7
 * @version 1.0
 */

public final class Deploy extends WarActions {
	
	private static int todeploy;
	private static Cell location;
	
	/**
	 * Deploy constructor
	 */
	private Deploy() {
	}
	
	/**
	 * todeploy attribute getter
	 * 
	 * @return the size of the future deployed army
	 */
	public static int getToDeploy() {
		return todeploy;
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
	 * todeploy attribute setter
	 * 
	 * @param n the amount of warriors to deploy
	 */
	public static void setToDeploy(int n) {
		Deploy.todeploy = n;
	}
	
	/**
	 * location attribute setter
	 * 
	 * @param cell the location to set
	 */
	public static void setLocation(Cell cell) {
		Deploy.location = cell;
	}
	
	/**
	 * Deploy an army in the game (the deployment will affect armies located in adjacent cells of location)
	 */
	public static void deploy() {
		
		if (todeploy > location.getType().getMaxArmySize()) todeploy = location.getType().getMaxArmySize();
		else if (todeploy <= 0) todeploy = 1;
		
		System.out.println(String.format("%s déploie une armée de %d guerriers en %s.\n", player, todeploy, location));
		
		Army army = new Army(player, todeploy);
		
		army.setCell(location);
		player.addArmy(army);
		board.setUnit(army);
		
		ArrayList<Army> neighbours = getNeighbourhood();
		for (Army a : neighbours) {
			army.checkNeighbourhood(a);
		}
	}
	
    /**
     * During a deployment, this method scans cells that are adjacent to location and return the adjacent armies if there is
     * 
     * @return the list of the adjacent armies
     */
    private static ArrayList<Army> getNeighbourhood() {
    	
    	ArrayList<Army> neighbours = new ArrayList<Army>();
    	ArrayList<Cell> adjCells = board.getAdjacentCellsOf(location);
    	
    	System.out.println(String.format("Armées adjacentes à %s:", location));
    	
    	for (Cell c : adjCells) {
    		if (c.getUnit() instanceof Army) {
    			
    			neighbours.add((Army) c.getUnit());
    			
    			System.out.println(String.format(" %s", (Army) c.getUnit()));
    		}
    	}
    	
    	System.out.println();
    	return neighbours;
    }
}

