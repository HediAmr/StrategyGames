package field.actions.action;

import java.util.Set;

import common.board.Ressource;

import field.actions.FieldActions;


/**
 * Deploy defines the action of trading resources
 * It needs a player to work properly
 * 
 * @author Group 7
 * @version 1.0
 */

public final class Exchange extends FieldActions {
	
	/**
	 * Exchange constructor
	 */
	private Exchange() {
		super();
	}
	
	/**
	 * The players trades his inventory resources for golds
	 */
	public static void exchange() {
		
		int golds = 0;
		Set<Ressource> keys = player.getInventory().keySet();
		
		for (Ressource r : keys) {
			
			int number = player.getInventory().get(r);
			int ressourcevalue = r.getValue() * number;
			golds += ressourcevalue;
			
			player.updateRessource(-number, r);
			
			if (number > 0) System.out.println(String.format("%s a échangé %d de %s pour %d g.", player, number, r, ressourcevalue));
			
		}
		
		player.addGold(golds);
		System.out.println();
	}

}
