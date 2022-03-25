package war.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Board;

import war.players.ArmyPlayer;

public class WarActionsTest {
	
	@Test
	public void testGetBoard() {
		WarActions.setBoard(null);
		assertEquals(WarActions.getBoard(), null);
	}

	@Test
	public void testGetPlayer() {
		
		ArmyPlayer aplayer = new ArmyPlayer("Nicolas");
		
		WarActions.setPlayer(aplayer);
		assertSame(WarActions.getPlayer(), aplayer);
	}
	
	@Test
	public void testSetBoard() {
		Board b = new Board();
		
		WarActions.setBoard(b);
		assertSame(WarActions.getBoard(), b);
	}
	
	@Test
	public void testSetPlayer() {
		
		ArmyPlayer aplayer = new ArmyPlayer("Nicolas");
		
		WarActions.setPlayer(aplayer);
		assertSame(WarActions.getPlayer(), aplayer);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WarActionsTest.class);
    }
    
}
