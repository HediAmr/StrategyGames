package common.board;

import static org.junit.Assert.*;

import org.junit.Test;

import field.characters.Worker;
import field.players.WorkerPlayer;

public class CellTest {

	@Test 
	public void testCellCreation() {
		int x = 0;
		int y = 13;
		Ground g = Ground.Desert;
		
		Cell c = new Cell(x, y, g);
		
		assertEquals(c.getType(), g);
		assertEquals(c.getX(), x);
		assertEquals(c.getY(), y);
		assertEquals(c.getUnit(), null);
	}
	
	@Test
	public void testGetX() {
		int x = 3;
		int y = 5;
		Ground g = Ground.Mountain;
		Cell c = new Cell(x, y, g);
		
		assertEquals(c.getX(), x);
	}
	
	@Test
	public void testGetY() {
		int x = 3;
		int y = 5;
		Ground g = Ground.Mountain;
		Cell c = new Cell(x, y, g);
		
		assertEquals(c.getY(), y);
	}
	
	@Test
	public void testGetType() {
		int x = 3;
		int y = 5;
		Ground g = Ground.Mountain;
		Cell c = new Cell(x, y, g);
		
		assertEquals(c.getType(), g);
	}
	
	@Test
	public void testGetUnit() {
		int x = 3;
		int y = 5;
		Ground g = Ground.Mountain;
		Cell c = new Cell(x, y, g);
		
		assertEquals(c.getUnit(), null);
	}

	@Test
	public void testIsGrounded() {
		Cell c1 = new Cell(7, 3, Ground.Forest);
		Cell c2 = new Cell(7, 3, Ground.Ocean);
		
		assertEquals(c1.isGrounded(), true);
		assertEquals(c2.isGrounded(), false);
	}
	
	@Test
	public void testSetUnit() {
		Cell c1 = new Cell(10, 3, Ground.Forest);
		Cell c2 = new Cell(10, 3, Ground.Ocean);
		
		WorkerPlayer wplayer = new WorkerPlayer("Joseph");
		
		Worker w1 = new Worker(wplayer);
		Worker w2 = new Worker(wplayer);
		
		assertEquals(c1.getUnit(), null);
		assertEquals(c2.getUnit(), null);
		
		c1.setUnit(w1);
		assertSame(c1.getUnit(), w1);
		
		c1.setUnit(w2);
		assertSame(c1.getUnit(), w2);
		
		c2.setUnit(w1);
		assertEquals(c2.getUnit(), null);
	}
	
	@Test
	public void testRemoveUnit() {
		Cell c1 = new Cell(10, 3, Ground.Forest);
		
		WorkerPlayer wplayer = new WorkerPlayer("Christophe");
		
		Worker w = new Worker(wplayer);
		
		assertEquals(c1.getUnit(), null);
		
		c1.setUnit(w);
		assertSame(c1.getUnit(), w);

		c1.removeUnit();
		assertEquals(c1.getUnit(), null);
		
		c1.removeUnit();
		assertSame(c1.getUnit(), null);
	}

	
	
	/* ----- Tests execution ----- */
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(CellTest.class);
	}
}