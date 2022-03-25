package field;

import java.util.ArrayList;
import java.util.Scanner;

import common.board.Board;
import common.board.Cell;
import field.actions.action.Deploy;
import field.game.FieldGame;
import field.players.WorkerPlayer;


/**
 * Main class for the field game (choices are made by the user on this game)
 * 
 * @author Group 7
 * @version 1.0
 */

public class FieldMainInput {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Veuillez choisir les dimensions du plateau !\n");
		
		System.out.print("Longueur : ");
		int dimx = input.nextInt();
		System.out.print("Largeur : ");
		int dimy = input.nextInt();
		
		
		Board board;
		if (dimx >= 0 && dimy >= 0) board = new Board(dimx, dimy);
		else board = new Board();
		
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
				
				System.out.println("Que voulez-vous faire ?");
				System.out.println(" - Déployer un ouvrier (tapez 0)");
				System.out.println(" - Echanger vos ressources (tapez 1)");
				System.out.println(" - Ne rien faire (tapez 2)");
				
				int choice = input.nextInt();
				FieldGame.setChoice(choice);
				
				if (choice == 0) {
					
					boolean available = false;
					
					while (!available) {
						System.out.println("A quelles coordonnées voulez-vous déployer votre ouvrier ?");
						System.out.print("Coordonnée x: ");
						int x = input.nextInt();
						System.out.print("Coordonnée y: ");
						int y = input.nextInt();
						
						Cell c = board.selectCell(x, y);
						
						if (board.isCellAvailable(c)) {
							available = true;
							Deploy.setLocation(c);
						}
						
						else {
							System.out.println("Veuillez choisir une cellule valide !\n");
						}
						
					}
					
				}
				
				FieldGame.playInputAction();
				
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
		
		input.close();
	}
}
