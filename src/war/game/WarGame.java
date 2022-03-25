package war.game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import common.board.Cell;
import common.board.Ground;
import common.game.Game;

import war.actions.WarActions;
import war.actions.action.Deploy;
import war.characters.Army;
import war.players.ArmyPlayer;


/**
 * WarGame defines how the war game behaves
 * 
 * @author Group 7
 * @version 1.0
 */

public final class WarGame extends Game {

	private static ArmyPlayer player;
	private final static int max = 10;
	
	/**
	 * WarGame constructor
	 */
	private WarGame() {
		super();
	}
	
	/**
	 * player attribute getter
	 * 
	 * @return the player of the turn
	 */
	public static ArmyPlayer getPlayer() {
		return player;
	}
	
	/**
	 * max attribute getter
	 * 
	 * @return the amount of turn the war game has
	 */
	public static int getMax() {
		return max;
	}
	
	/**
	 * player attribute setter
	 * 
	 * @param player the player who will play the turn
	 */
	public static void setPlayer(ArmyPlayer player) {
		WarGame.player = player;
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
		WarActions.setBoard(board);
	}
	
	/**
	 * Start a new player turn with the player attribute
	 */
	public static void playTurn() {
		WarActions.setPlayer(player);
	}

	/**
	 * Choose randomly between 2 actions
	 */
	public static void chooseAction() {
		choice = ThreadLocalRandom.current().nextInt(0, 2);
	}
	
	/**
	 * Choose randomly the number of warriors to deploy
	 * 
	 * @return a integer between 1 and 5 (included)
	 */
	public static int chooseToDeploy() {
		return ThreadLocalRandom.current().nextInt(1, 6);
	}
	
	/**
	 * Play common actions
	 */
	public static void playCommonActions() {
		WarActions.recoltRessources();
		WarActions.feedArmies();
	}
	
	/**
	 * Play common actions
	 */
	public static void playCommonInputActions() {
		WarActions.recoltRessources();
		WarActions.feedArmiesWithInteraction();
	}
	
	/**
	 * Play the chosen action
	 */
	public static void playAction() {
		if (choice == 0) {
			Cell cell = board.getRandomAvailableCells();
			
			int n = chooseToDeploy();
			
			Deploy.setToDeploy(n);
			Deploy.setLocation(cell);
			
			Deploy.deploy();
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
		
		/* Add other action based on choice here */
		
		playCommonInputActions();
	}
	
	/**
	 * Find and set the winner of the game (only if there is participant)
	 * 
	 * @param players the list of the war game participant
	 */
	public static void findWinner(List<ArmyPlayer> players) {
		
		if (players.size() > 0) {
			
			for (ArmyPlayer p : players) {
				int points = 0;
				
				for (Army a : p.getDeployed()) {
					points += a.getGold();
					
					if (a.getGround() == Ground.Plain) points++;
					
					else if (a.getGround() == Ground.Forest) points += 2;
					
					else points += 4;	
				}
				
				points += p.getGold();
				
				if (p.getDeployed().size() >= 10) points += 5;
					
				p.addPoints(points);
			}
			
			ArmyPlayer wp = players.get(0);
			for (ArmyPlayer p: players) {
				if (wp.getPoints() < p.getPoints()) {
					wp = p;
				}
			}
			
			winner = wp;
		}
	}

}
