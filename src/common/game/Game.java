package common.game;

import common.board.Board;
import common.players.Player;


/** 
 * Game gathers common methods and attributes used to manage both games
 *
 * @author Group 7
 * @version 1.0
 */

public abstract class Game {
	
	protected static int number;
	protected static int choice;
	protected static Board board;
	protected static Player winner;
	
	/**
	 * Game constructor
	 */
	protected Game() {
		number = 0;
	}
	
	/**
	 * number attribute Getter
	 *
	 * @return the turn of the game
	 */
	public static int getNumber() {
		return number;
	}
	
	/**
	 * choice attribute Getter
	 *
	 * @return the action choice of a player during the game
	 */
	public static int getChoice() {
		return choice;
	}
	
	/**
	 * board attribute Getter
	 *
	 * @return the board to set
	 */
	public static Board getBoard() {
		return board;
	}
	
	/**
	 * winner attribute Getter
	 *
	 * @return the winner of a game
	 */
	public static Player getWinner() {
		return winner;
	}
	
	/**
	 * choice attribute Setter
	 *
	 * @param n the action choice to set
	 */
	public static void setChoice(int n) {
		choice = n;
	}
	
	/**
	 * board attribute Setter
	 *
	 * @param b the board to set
	 */
	public static void setBoard(Board b) {
		board = b;
	}
	
	/**
	 * winner attribute Setter
	 *
	 * @param player the player to set as winner
	 */
	public static void setWinner(Player player) {
		winner = player;
	}
	
	/**
	 * Increments number attribute by 1 (start the next turn)
	 */
	public static void nextTurn() {
		number++;
	}
	
	/**
	 * Check if board is filled (no cell is available)
	 *
	 * @return <code>true</code> if the board is filled, <code>false</code> if not or board is <code>null</code>
	 */
	public static boolean isOverByBoardFilled() {
		try {
			return board.isFull();
		}
		catch (Exception e) {
			return false;
		}
	}
}
