package common.game;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Board;

import field.players.WorkerPlayer;

public class GameTest {

	@Test
	public void testGetNumber() {
		assertEquals(Game.getNumber(), 0);
	}
	
	@Test
	public void testGetBoard() {
		Board b = new Board();
		Game.setBoard(b);
		
		assertSame(Game.getBoard(), b);
	}
	
	@Test
	public void testGetWinner() {
		WorkerPlayer wplayer = new WorkerPlayer("Lucas");
		Game.setWinner(wplayer);
		
		assertSame(Game.getWinner(), wplayer);
	}
	
	@Test
	public void testSetChoice() {
		Game.setChoice(42);
		
		assertEquals(Game.getChoice(), 42);
		
		Game.setChoice(0);
		
		assertEquals(Game.getChoice(), 0);
		
		Game.setChoice(-3);
		
		assertEquals(Game.getChoice(), -3);
	}
	
	@Test
	public void testSetBoard() {
		
		Board b1 = new Board(15, 4);
		Game.setBoard(b1);
		
		assertSame(Game.getBoard(), b1);
		
		Board b2 = new Board();
		Game.setBoard(b2);
		
		assertSame(Game.getBoard(), b2);
	}
	
	@Test
	public void testSetWinner() {
		assertEquals(Game.getWinner(), null);
		
		WorkerPlayer wp1 = new WorkerPlayer("Johnny");
		Game.setWinner(wp1);
		
		assertSame(Game.getWinner(), wp1);
		
		WorkerPlayer wp2 = new WorkerPlayer("Felix");
		Game.setWinner(wp2);
		
		assertSame(Game.getWinner(), wp2);
	}

	@Test
	public void testNextTurn() {
		assertEquals(Game.getNumber(), 0);
		
		Game.nextTurn();
		
		assertEquals(Game.getNumber(), 1);
		
		Game.nextTurn();
		
		assertEquals(Game.getNumber(), 2);
	}

	@Test
	public void testIsOverByBoardFilled() {
		
		assertEquals(Game.isOverByBoardFilled(), false);
		
		Board b1 = new Board();
		
		Game.setBoard(b1);
		
		assertEquals(Game.isOverByBoardFilled(), b1.isFull());
		
		while (!b1.isFull()) {
			b1.removeAvailableCell(b1.getRandomAvailableCells());
		}
		
		assertEquals(Game.isOverByBoardFilled(), true);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(GameTest.class);
    }

}
