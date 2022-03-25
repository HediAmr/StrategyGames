package war;

import java.util.ArrayList;
import java.util.Scanner;

import common.board.Board;
import common.board.Cell;
import war.actions.action.Deploy;
import war.game.WarGame;
import war.players.ArmyPlayer;


/**
 * Main class for the war game (choices are made by the user on this game)
 * 
 * @author Group 7
 * @version 1.0
 */

public class WarMainInput {
	
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
		
		WarGame.setBoard(board);
		WarGame.initGame();
		
		if (args.length > 8) {
			System.out.println("Au maximum 8 joueurs peuvent participer au jeu !\n");
		}
		
		ArrayList<ArmyPlayer> players = new ArrayList<ArmyPlayer>();
		
		/* If no arguments given at program execution */
		if (args.length == 0) {
			players.add(new ArmyPlayer("Default1"));
			players.add(new ArmyPlayer("Default2"));
			
			System.out.println("Parti par défaut lancé !\n");
		}
		
		
		
		for (int i = 0; (i < args.length && i < 8); i++) {
			players.add(new ArmyPlayer(args[i]));
		}
		System.out.println(String.format("\n%d joueurs participent au jeu !\n", args.length));
		
		
		while (!WarGame.isOver()) {
			for (ArmyPlayer gamer : players) {
				
				if (WarGame.isOverByBoardFilled()) {
					break;
				}
				
				System.out.println(board);
				
				WarGame.setPlayer(gamer);
				WarGame.playTurn();
				System.out.println(String.format("C'est à %s de jouer !\n", gamer));
				
				System.out.println("Que voulez-vous faire ?");
				System.out.println(" - Déployer une armée (tapez 0)");
				System.out.println(" - Ne rien faire (tapez 1)");
				
				int choice = input.nextInt();
				WarGame.setChoice(choice);
				
				if (choice == 0) {
					
					System.out.println("Choisissez la taille de l'armée (entre 1 et 5) ?");
					System.out.println("Note: La taille sera redéfinis automatiquement si l'entier saisi est invalide");
					int size = input.nextInt();
					
					boolean available = false;
					
					while (!available) {
						System.out.println("A quelles coordonnées voulez-vous déployer votre armée ?");
						System.out.print("Coordonnée x: ");
						int x = input.nextInt();
						System.out.print("Coordonnée y: ");
						int y = input.nextInt();
						
						Cell c = board.selectCell(x, y);
						
						if (board.isCellAvailable(c)) {
							available = true;
							Deploy.setToDeploy(size);
							Deploy.setLocation(c);
						}
						
						else {
							System.out.println("Veuillez choisir une cellule valide !\n");
						}
						
					}
					
				}
				
				WarGame.playInputAction();
				
				/* To keep track of golds, the food and the armies deployed by the player during his turn */
				System.out.println(String.format("%s possède maintenant %d g et %d de nourriture.", gamer, gamer.getGold(), gamer.getFoodStock()));
				System.out.print(String.format("Voici les armées actuellement déployés sur le terrain par %s: \n%s", gamer, gamer.deployedToString()));
				System.out.println("__________________________________________________________________________________________________________\n\n");
				
			}
			WarGame.nextTurn();
		}
		
		if (WarGame.isOverByTurn()) {
			System.out.println(String.format("%d tours sont passés !", WarGame.getMax()));
		}
		else {
			System.out.println("Toutes les cases du board ont été remplies !");
		}
		System.out.println("Le jeu est terminé !\n");
		
		
		WarGame.findWinner(players);
		System.out.println(String.format("Le gagnant est %s !\n", WarGame.getWinner()));
		
		
		System.out.println("Voici le tableau des scores:");
		for (ArmyPlayer p : players) {
			System.out.println(String.format("%s : %d points.", p, p.getPoints()));
		}
		
		input.close();
	}

}
