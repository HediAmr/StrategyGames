package field.characters;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Cell;
import common.board.Ground;
import common.board.Ressource;

import field.players.WorkerPlayer;

public class WorkerTest {

	@Test
	public void testWorkerCreation() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		assertSame(w.getOwner(), wplayer);
		assertEquals(w.getPay(), 0);
	}
	
	@Test
	public void testGetPay() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		assertEquals(w.getPay(), 0);
	}
	
	@Test
	public void testGetOwner() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		assertSame(w.getOwner(), wplayer);
	}
	
	@Test
	public void testSetCell() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		Cell c = new Cell(3, 2, Ground.Desert);
		
		assertEquals(w.getPay(), 0);
		
		w.setCell(c);
		
		assertEquals(w.getPay(), c.getType().getPayValue());
	}
	
	@Test
	public void testGetPaid() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		Cell c = new Cell(1, 3, Ground.Plain);
		
		assertEquals(w.getGold(), 0);
		assertEquals(wplayer.getGold(), 15);
		
		w.getPaid();
		
		assertEquals(w.getGold(), 0);
		assertEquals(wplayer.getGold(), 15);
		
		w.setCell(c);
		w.getPaid();
		
		assertEquals(w.getGold(), w.getPay());
		assertEquals(wplayer.getGold(), 15 - w.getPay());
		
		w.getPaid();
		
		assertEquals(w.getGold(), 2*w.getPay());
		assertEquals(wplayer.getGold(), 15 - 2*w.getPay());
	}
	
	@Test
	public void testReceiveGolds() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		
		Worker w1 = new Worker(wplayer);
		Worker w2 = new Worker(wplayer);
		Worker w3 = new Worker(wplayer);
		Worker w4 = new Worker(wplayer);
		
		Cell c1 = new Cell(3, 2, Ground.Desert);
		Cell c2 = new Cell(4, 3, Ground.Forest);
		Cell c3 = new Cell(5, 4, Ground.Mountain);
		Cell c4 = new Cell(6, 5, Ground.Plain);
		
		w1.setCell(c1);
		w2.setCell(c2);
		w3.setCell(c3);
		w4.setCell(c4);
		
		assertEquals(wplayer.getGold(), 15);
		
		w1.receiveGolds();
		assertEquals(wplayer.getGold(), 17);
		
		w2.receiveGolds();
		assertEquals(wplayer.getGold(), 18);
		
		w3.receiveGolds();
		assertEquals(wplayer.getGold(), 18);
		
		w4.receiveGolds();
		assertEquals(wplayer.getGold(), 19);
	}
	
	@Test
	public void testLoseUnit() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		Cell c = new Cell(4, 8, Ground.Forest);
		
		w.setCell(c);
		
		assertSame(w.getOwner(), wplayer);
		
		w.loseUnit();
		assertEquals(w.getOwner(), null);
		
		w.loseUnit();
		assertEquals(w.getOwner(), null);
	}
	
	@Test
	public void testRecoltRess() {
		
		WorkerPlayer wplayer = new WorkerPlayer("Paul");
		Worker w = new Worker(wplayer);
		
		Cell c = new Cell(3, 2, Ground.Desert);
		
		w.setCell(c);
		
		Ressource r = w.getRessource();
		
		assertEquals(wplayer.getRessourceQuantity(r), 0);
		
		w.recoltRess();
		assertEquals(wplayer.getRessourceQuantity(r), 1);
		
		w.recoltRess();
		assertEquals(wplayer.getRessourceQuantity(r), 2);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WorkerTest.class);
    }

}
