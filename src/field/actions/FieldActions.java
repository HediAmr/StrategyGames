package field.actions;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import common.board.Board;

import field.players.WorkerPlayer;
import field.characters.Worker;


/**
 * FieldActions defines actions that can be played for the field game
 * It needs a player and a board to work properly
 * 
 * @author Group 7
 * @version 1.0
 */

public abstract class FieldActions {
	
	protected static Board board;
	protected static WorkerPlayer player;
	
	/**
	 * FieldActions constructor
	 */
	protected FieldActions() {
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
	public static WorkerPlayer getPlayer() {
		return player;
	}
	
	/**
	 * board attribute setter
	 * 
	 * @param board the board to set
	 */
	public static void setBoard(Board board) {
		FieldActions.board = board;
	}
	
	/**
	 * player attribute setter
	 * 
	 * @param player the player to set
	 */
	public static void setPlayer(WorkerPlayer player) {
		FieldActions.player = player;
	}
	
	/**
	 * Allow the player to harvest resources produced by his workers
	 */
	public static void recoltRessources() {
		ArrayList<Worker> deployedWorkers = player.getDeployed();
		for (Worker w : deployedWorkers) {
			w.recoltRess();
		}
		System.out.println(String.format("%s recolte les ressources produites par ses ouvriers.\n", player));
	}
	
	/**
	 * Make the player pay his workers (the choice of which worker to remove is random)
	 */
	public static void payWorkers() {
		ArrayList<Worker> deployedWorkers = player.getDeployed();
		int topay = 0;
		
		for (Worker w : deployedWorkers) {
			topay += w.getPay();
		}
		
		if (topay <= player.getGold()) {
			
			for (Worker w : deployedWorkers) {
				w.getPaid();
			}
			
			System.out.println(String.format("%s vient de rémunérer ses ouvriers.\n", player));
		}
		
		else {
			
			if (deployedWorkers.size() == 1) {
				Worker lastworker = deployedWorkers.get(0);
				
				System.out.println(String.format("%s ne peut plus rémunérer son dernier ouvrier, l'ouvrier en %s est parti...\n", player, lastworker.getCell()));
				
				lastworker.getCell().removeUnit();
				player.removeWorker(lastworker);
				lastworker.loseUnit();
			}
			
			else {
				int randomindex = ThreadLocalRandom.current().nextInt(0, deployedWorkers.size());
				Worker randomworker = deployedWorkers.get(randomindex);
				
				System.out.println(String.format("%s ne peut plus payer la totalité de ses ouvriers, son ouvrier en %s est donc parti.", player, randomworker.getCell()));
				
				randomworker.getCell().removeUnit();
				player.removeWorker(randomworker);
				randomworker.loseUnit();
				
				payWorkers();
			}
		}
	}
	
	/**
	 * Make the player pay his workers (the player chooses which worker to remove)
	 */
	public static void payWorkersWithInteraction() {
		ArrayList<Worker> deployedWorkers = player.getDeployed();
		int topay = 0;
		
		for (Worker w : deployedWorkers) {
			topay += w.getPay();
		}
		
		if (topay <= player.getGold()) {
			
			for (Worker w : deployedWorkers) {
				w.getPaid();
			}
			
			System.out.println(String.format("%s vient de rémunérer ses ouvriers.\n", player));
		}
		
		else {
			
			if (deployedWorkers.size() == 1) {
				Worker lastworker = deployedWorkers.get(0);
				
				System.out.println(String.format("%s ne peut plus rémunérer son dernier ouvrier, l'ouvrier en %s est parti...\n", player, lastworker.getCell()));
				
				lastworker.getCell().removeUnit();
				player.removeWorker(lastworker);
				lastworker.loseUnit();
			}
			
			else {
				Scanner inputindex = new Scanner(System.in);
				
				System.out.println("Vous ne pouvez plus rémunérer tous vos ouvriers, veuillez choisir un ouvrier à perdre.");
				System.out.println("Choisissez un indice correspondant à la position dans la liste de l'ouvrier à supprimer.");
				System.out.println(String.format("%s", deployedWorkers));
				
				int index = inputindex.nextInt();
				Worker leavingworker = deployedWorkers.get(index);
				
				System.out.println(String.format("%s ne peut plus payer la totalité de ses ouvriers, son ouvrier en %s est donc parti.", player, leavingworker.getCell()));
				
				leavingworker.getCell().removeUnit();
				player.removeWorker(leavingworker);
				leavingworker.loseUnit();
				
				payWorkersWithInteraction();
			}
		}
	}

}
