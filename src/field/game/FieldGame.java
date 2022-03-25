package field.game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import common.board.Cell;
import common.game.Game;

import field.actions.FieldActions;
import field.actions.action.*;
import field.characters.Worker;
import field.players.WorkerPlayer;


/**
 * FieldGame defines how the field game behaves
 * 
 * @author Group 7
 * @version 1.0
 */

public final class FieldGame extends Game {

	private static WorkerPlayer player;
	private final static int max = 6;
	
	/**
	 * FieldGame constructor
	 */
	private FieldGame() {
		super();
	}
	
	/**
	 * player attribute getter
	 * 
	 * @return the player of the turn
	 */
	public static WorkerPlayer getPlayer() {
		return player;
	}
	
	/**
	 * max attribute getter
	 * 
	 * @return the amount of turn the field game has
	 */
	public static int getMax() {
		return max;
	}
	
	/**
	 * player attribute setter
	 * 
	 * @param player the player who will play the turn
	 */
	public static void setPlayer(WorkerPlayer player) {
		FieldGame.player = player;
	}
	
	/**
	 * Check if the last turn has been played
	 * @return <code>true</code> if it has been played, <code>false</code> if not
	 */
	public static boolean isOverByTurn() {
		return number >= max;
	}
	
	/**
	 * Check if the game is over
	 * 
	 * @return <code>true</code> if the game is over, <code>false</code> if not
	 */
	public static boolean isOver() {
		return isOverByTurn() || isOverByBoardFilled();
	}
	
	/**
	 * Initialize the game with the board attribute
	 */
	public static void initGame() {
		FieldActions.setBoard(board);
	}
	
	/**
	 * Start a new player turn with the player attribute
	 */
	public static void playTurn() {
		FieldActions.setPlayer(player);
	}

	/**
	 * Choose randomly between 3 actions
	 */
	public static void chooseAction() {
		choice = ThreadLocalRandom.current().nextInt(0, 3);
	}
	
	/**
	 * Play common actions
	 */
	public static void playCommonActions() {
		FieldActions.recoltRessources();
		FieldActions.payWorkers();
	}
	
	/**
	 * Play common actions
	 */
	public static void playInputCommonActions() {
		FieldActions.recoltRessources();
		FieldActions.payWorkersWithInteraction();
	}
	
	/**
	 * Play the chosen action
	 */
	public static void playAction() {
		if (choice == 0) {
			Cell cell = board.getRandomAvailableCells();
			
			Deploy.setLocation(cell);
				
			Deploy.deploy();
		}
		else if (choice == 1) {
			Exchange.exchange();
		}
		else if (choice == 2) {
			DoNothing.getPaid();
		}
		
		/* Add other action based on choice here */
		
		playCommonActions();
	}
	
	/**
	 * Play the chosen action
	 */
	public static void playInputAction() {
		if (choice == 0) {
			Deploy.deploy();
		}
		else if (choice == 1) {
			Exchange.exchange();
		}
		else if (choice == 2) {
			DoNothing.getPaid();
		}
		
		/* Add other action based on choice here */
		
		playInputCommonActions();
	}
	
	/**
	 * Find and set the winner of the game (only if there is participant)
	 * 
	 * @param players the list of the field game participant
	 */
	public static void findWinner(List<WorkerPlayer> players) {
		
		if (players.size() > 0) {
			
			for (WorkerPlayer p : players) {
				int points = 0;
				
				for (Worker w : p.getDeployed()) {
					points += w.getGold();
				}
				
				p.addPoints(points);
			}
			
			WorkerPlayer wp = players.get(0);
			for (WorkerPlayer p: players) {
				if (wp.getPoints() < p.getPoints()) {
					wp = p;
				}
			}
			
			winner = wp;
		}
	}

}
