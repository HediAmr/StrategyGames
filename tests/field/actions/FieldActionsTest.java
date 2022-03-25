package field.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Board;

import field.players.WorkerPlayer;

public class FieldActionsTest {
	
	@Test
	public void testGetBoard() {
		FieldActions.setBoard(null);
		
		assertEquals(FieldActions.getBoard(), null);
	}

	@Test
	public void testSetBoard() {
		Board b = new Board();
		
		FieldActions.setBoard(b);
		
		assertEquals(FieldActions.getBoard(), b);
	}

	@Test
	public void testGetPlayer() {
		FieldActions.setPlayer(null);
		
		assertEquals(FieldActions.getPlayer(), null);
	}

	@Test
	public void testSetPlayer() {
		WorkerPlayer wplayer = new WorkerPlayer("John");
		
		FieldActions.setPlayer(wplayer);
		
		assertEquals(FieldActions.getPlayer(), wplayer);
	}

	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(FieldActionsTest.class);
    }
    
}
