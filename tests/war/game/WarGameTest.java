package war.game;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Board;

import war.actions.WarActions;
import war.players.ArmyPlayer;

public class WarGameTest {

	@Test
	public void testGetPlayer() {
		WarGame.setPlayer(null);
		assertEquals(WarGame.getPlayer(), null);
	}
	
	@Test
	public void testGetMax() {
		assertEquals(WarGame.getMax(), 10);	
	}
	
	@Test
	public void testSetPlayer() {
		ArmyPlayer aplayer = new ArmyPlayer("Francis");
		
		WarGame.setPlayer(aplayer);
		assertSame(WarGame.getPlayer(), aplayer);
	}
	
	@Test
	public void testIsOverByTurn() {
		
		assertEquals(WarGame.isOverByTurn(), false);
		
		for (int i = 0; i < WarGame.getMax(); i++) {
			WarGame.nextTurn();
		}
		
		assertEquals(WarGame.isOverByTurn(), true);
	}
	
	@Test
	public void testInitGame() {
		Board b = new Board();
		
		WarActions.setBoard(null);
		assertEquals(WarActions.getBoard(), null);
		
		WarGame.setBoard(b);
		WarGame.initGame();
		
		assertSame(WarActions.getBoard(), b);
	}
	
	@Test
	public void testPlayTurn() {
		ArmyPlayer aplayer = new ArmyPlayer("Victor");
		
		WarActions.setPlayer(null);
		assertEquals(WarActions.getPlayer(), null);
		
		WarGame.setPlayer(aplayer);
		WarGame.playTurn();
		
		assertSame(WarActions.getPlayer(), aplayer);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WarGameTest.class);
    }

}
