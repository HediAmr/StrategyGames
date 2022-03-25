package field.actions.action;

import java.util.ArrayList;

import field.actions.FieldActions;
import field.characters.Worker;


/**
 * DoNothing defines the action of doing nothing
 * It needs a player to work properly
 * 
 * @author Group 7
 * @version 1.0
 */

public final class DoNothing extends FieldActions {
	
	/**
	 * DoNothing constructor
	 */
	private DoNothing() {
		super();
	}
	
	/**
	 * The player receives golds based on where his workers are deployed
	 */
	public static void getPaid() {
		System.out.println(String.format("%s décidé de ne rien faire, il recevra: \n", player)
				+ " - 1 gold pour chaque ouvrier recoltant dans une plaine\n"
				+ " - 2 gold pour chaque ouvrier recoltant dans un désert\n");
		
		ArrayList<Worker> deployedWorkers = player.getDeployed();
		for (Worker w : deployedWorkers) {
			w.receiveGolds();
		}
	}
}
