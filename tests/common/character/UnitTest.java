package common.character;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Cell;
import common.board.Ground;

import field.characters.Worker;
import field.players.WorkerPlayer;

public class UnitTest {

	@Test
	public void testUnitCreation() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		assertEquals(worker.getGold(), 0);
		assertEquals(worker.getCell(), null);
		assertEquals(worker.getGround(), null);
		assertEquals(worker.getRessource(), null);
	}
	
	@Test
	public void testGetGold() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		assertEquals(worker.getGold(), 0);
	}

	@Test
	public void testGetCell() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		assertEquals(worker.getCell(), null);
	}
	
	@Test
	public void testGetGround() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		assertEquals(worker.getGround(), null);
	}
	
	@Test
	public void testGetRessource() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		assertEquals(worker.getRessource(), null);
	}
	
	@Test
	public void testSetCell() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		Cell c = new Cell(4, 20, Ground.Mountain);
		
		assertEquals(worker.getCell(), null);
		assertEquals(worker.getGround(), null);
		assertEquals(worker.getRessource(), null);
		
		worker.setCell(c);
		
		assertSame(worker.getCell(), c);
		assertEquals(worker.getGround(), c.getType());
		assertEquals(worker.getRessource(), c.getType().getRessource());
	}
	
	@Test
	public void testfreeCell() {
		WorkerPlayer player = new WorkerPlayer("Jean");
		Worker worker = new Worker(player);
		
		Cell c = new Cell(4, 20, Ground.Mountain);
		
		worker.setCell(c);
		
		assertSame(worker.getCell(), c);
		assertEquals(worker.getGround(), c.getType());
		assertEquals(worker.getRessource(), c.getType().getRessource());
		
		worker.removeCell();
		
		assertEquals(worker.getCell(), null);
		assertEquals(worker.getGround(), null);
		assertEquals(worker.getRessource(), null);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(UnitTest.class);
    }

}
