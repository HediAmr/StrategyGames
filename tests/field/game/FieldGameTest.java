package field.game;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Board;

import field.actions.FieldActions;
import field.players.WorkerPlayer;

public class FieldGameTest {

	@Test
	public void testGetPlayer() {
		
		FieldGame.setPlayer(null);
		
		assertEquals(FieldGame.getPlayer(), null);
	}
	
	@Test
	public void testGetMax() {
		assertEquals(FieldGame.getMax(), 6);
	}
	
	@Test
	public void testSetPlayer() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Johnny");
		
		FieldGame.setPlayer(wplayer);
		assertSame(FieldGame.getPlayer(), wplayer);
	}
	
	@Test
	public void testIsOverByTurn() {
		
		assertEquals(FieldGame.isOverByTurn(), false);
		
		for (int i = 0; i < FieldGame.getMax(); i++) {
			FieldGame.nextTurn();
		}
		
		assertEquals(FieldGame.isOverByTurn(), true);
	}
	
	@Test
	public void testInitGame() {
		
		Board b = new Board();
		
		FieldGame.setBoard(null);
		
		assertEquals(FieldActions.getBoard(), null);
		
		FieldGame.initGame();
		
		assertEquals(FieldActions.getBoard(), null);
		
		FieldGame.setBoard(b);
		FieldGame.initGame();
		
		assertSame(FieldActions.getBoard(), b);
	}
	
	@Test
	public void testPlayTurn() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Johnny");
		
		FieldGame.setPlayer(null);
		
		assertEquals(FieldActions.getPlayer(), null);
		
		FieldGame.playTurn();
		
		assertEquals(FieldActions.getPlayer(), null);
		
		FieldGame.setPlayer(wplayer);
		FieldGame.playTurn();
		
		assertSame(FieldActions.getPlayer(), wplayer);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(FieldGameTest.class);
    }
    
}
