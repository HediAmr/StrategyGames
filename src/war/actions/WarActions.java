package war.actions;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import common.board.Board;
import common.board.Ressource;

import war.characters.Army;
import war.players.ArmyPlayer;


/**
 * WarActions defines actions that can be played for the war game
 * It needs a player and a board to work properly
 * 
 * @author Group 7
 * @version 1.0
 */

public abstract class WarActions {
	
	protected static Board board;
	protected static ArmyPlayer player;
	
	/**
	 * WarActions constructor
	 */
	protected WarActions() {
	}
	
	/**
	 * board attribute getter
	 * 
	 * @return board the board in which the action has to be played
	 */
	public static Board getBoard() {
		return board;
	}
	
	/**
	 * player attribute getter
	 * 
	 * @return player the player who play the action
	 */
	public static ArmyPlayer getPlayer() {
		return player;
	}
	
	/**
	 * board attribute setter
	 * 
	 * @param board the board to set
	 */
	public static void setBoard(Board board) {
		WarActions.board = board;
	}
	
	/**
	 * player attribute setter
	 * 
	 * @param player the player to set
	 */
	public static void setPlayer(ArmyPlayer player) {
		WarActions.player = player;
	}
	
	/**
	 * Allow the player to harvest resources produced by his armies
	 */
	public static void recoltRessources() {
		ArrayList<Army> deployedArmies = player.getDeployed();
		for (Army a : deployedArmies) {
			a.recoltRess();
		}
		convertResources();
		System.out.println(String.format("%s recolte les ressources produites par ses armées et les convertit en nourriture.\n", player));
	}
	
	/**
	 * Make the player feed his armies (the choice of which army to remove is random)
	 */
	public static void feedArmies() {
		ArrayList<Army> deployedArmies = player.getDeployed();
		int neededFood = 0;
		
		for (Army a : deployedArmies) {
			neededFood += a.getStamina();
		}
		
		if (neededFood <= player.getFoodStock()) {
			
			for (Army a : deployedArmies) {
				a.feed();
			}
			
			System.out.println(String.format("%s vient de nourrir ses armées.\n", player));
		}
		
		else {
			
			if (deployedArmies.size() == 1) {
				Army lastarmy = deployedArmies.get(0);
				
				System.out.println(String.format("%s ne peut plus nourrir sa dernière armée, l'armée en %s a donc quitté le champs de bataille.\n", player, lastarmy.getCell()));
				
				lastarmy.getCell().removeUnit();
				player.removeArmy(lastarmy);
				lastarmy.loseUnit();
				player.addGold(1);
			}
			
			else {
				int randomindex = ThreadLocalRandom.current().nextInt(0, deployedArmies.size());
				Army randomarmy = deployedArmies.get(randomindex);
				
				System.out.println(String.format("%s ne peut plus nourrir la totalité de ses armées, son armée en %s a donc quitté le champs de bataille.", player, randomarmy.getCell()));
				
				randomarmy.getCell().removeUnit();
				player.removeArmy(randomarmy);
				randomarmy.loseUnit();
				player.addGold(1);
				
				feedArmies();
			}
		}
	}
	
	/**
	 * Make the player feed his armies (the player chooses which army to remove)
	 */
	public static void feedArmiesWithInteraction() {
		ArrayList<Army> deployedArmies = player.getDeployed();
		int neededFood = 0;
		
		for (Army a : deployedArmies) {
			neededFood += a.getStamina();
		}
		
		if (neededFood <= player.getFoodStock()) {
			
			for (Army a : deployedArmies) {
				a.feed();
			}
			
			System.out.println(String.format("%s vient de nourrir ses armées.", player));
		}
		else {

			if (deployedArmies.size() == 1) {
				Army lastarmy = deployedArmies.get(0);
				
				System.out.println(String.format("%s ne peut plus nourrir sa dernière armée, l'armée en %s a donc quitté le champs de bataille.\n", player, lastarmy.getCell()));
				
				lastarmy.getCell().removeUnit();
				player.removeArmy(lastarmy);
				lastarmy.loseUnit();
				player.addGold(1);
			}
			
			else {
				Scanner inputindex = new Scanner(System.in);
				
				System.out.println("Vous ne pouvez plus nourrir la totalité de vos armées, veuillez choisir une armée à abandonner.");
				System.out.println("Choisissez un indice correspondant à la position dans la liste de l'armée à abandonner.");
				System.out.println(String.format("%s", deployedArmies));
				
				int index = inputindex.nextInt();
				Army leavingarmy = deployedArmies.get(index);
				
				System.out.println(String.format("%s ne peut plus nourrir la totalité de ses armées, son armée en %s a donc quitté le champs de bataille.", player, leavingarmy.getCell()));
				
				leavingarmy.getCell().removeUnit();
				player.removeArmy(leavingarmy);
				leavingarmy.loseUnit();
				player.addGold(1);
				
				feedArmiesWithInteraction();			
			}
		}
	}
	
	/**
	 * Convert automatically the player resources
	 */
	private static void convertResources() {
		
		int food = 0;
		Set<Ressource> keys = player.getInventory().keySet();
		
		for (Ressource r : keys) {
			
			int number = player.getInventory().get(r);
			int ressourcefoodvalue = r.getFoodValue() * number;
			food += ressourcefoodvalue;
			
			player.updateRessource(-number, r);
			
			if (number > 0) System.out.println(String.format("%s a échangé %d de %s pour %d de nourriture.", player, number, r, ressourcefoodvalue));
			
		}
		player.addFood(food);
		
		if (food > 0) System.out.println();
	}
	
}
