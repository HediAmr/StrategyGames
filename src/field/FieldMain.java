package field;

import java.util.ArrayList;

import common.board.Board;

import field.game.FieldGame;
import field.players.WorkerPlayer;


/**
 * Main class for the field game (choices are random on this game)
 * 
 * @author Group 7
 * @version 1.0
 */

public class FieldMain {

	public static void main(String[] args) {
		
		Board board = new Board();
		
		FieldGame.setBoard(board);
		FieldGame.initGame();
		
		if (args.length > 8) {
			System.out.println("Au maximum 8 joueurs peuvent participer au jeu !\n");
		}
		
		ArrayList<WorkerPlayer> players = new ArrayList<WorkerPlayer>();
		
		/* If no arguments given at program execution */
		if (args.length == 0) {
			players.add(new WorkerPlayer("Default1"));
			players.add(new WorkerPlayer("Default2"));
			System.out.println("Parti par défaut lancé !\n");
		}
		
		
		for (int i = 0; (i < args.length && i < 8); i++) {
			players.add(new WorkerPlayer(args[i]));
		}
		System.out.println(String.format("\n%d joueurs participent au jeu !\n", args.length));
		
		
		while (!FieldGame.isOver()) {
			for (WorkerPlayer gamer : players) {
				
				if (FieldGame.isOverByBoardFilled()) {
					break;
				}
				
				System.out.println(board);
				
				FieldGame.setPlayer(gamer);
				FieldGame.playTurn();
				System.out.println(String.format("C'est à %s de jouer !\n", gamer));
				
				FieldGame.chooseAction();
				
				FieldGame.playAction();
				
				/* To keep track of golds, the inventory and the workers deployed by the player during his turn */
				System.out.println(String.format("%s possède maintenant %d g.", gamer, gamer.getGold()));
				System.out.println(String.format("%s a dans son inventaire les ressources suivantes:\n%s\n", gamer, gamer.inventoryToString()));
				System.out.print(String.format("Voici les ouvriers actuellement déployés sur le terrain par %s: \n%s", gamer, gamer.deployedToString()));
				System.out.println("__________________________________________________________________________________________________________\n\n");
				
			}
			FieldGame.nextTurn();
		}
		
		if (FieldGame.isOverByTurn()) {
			System.out.println(String.format("%d tours sont passés !", FieldGame.getMax()));
		}
		else {
			System.out.println("Toutes les cases du board ont été remplies !");
		}
		System.out.println("Le jeu est terminé !\n");
		
		
		FieldGame.findWinner(players);
		System.out.println(String.format("Le gagnant est %s !\n", FieldGame.getWinner()));
		
		
		System.out.println("Voici le tableau des scores:");
		for (WorkerPlayer p : players) {
			System.out.println(String.format("%s : %d points.", p, p.getPoints()));
		}
	}
}

