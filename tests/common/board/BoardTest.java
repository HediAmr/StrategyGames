package common.board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import field.characters.Worker;
import field.players.WorkerPlayer;

public class BoardTest {
	
	@Test
	public void TestBoardCreation1() {
		
		Board b = new Board();
		Generator g = b.getGenerator();
			
		assertEquals(b.getDimX(), 10);
		assertEquals(b.getDimY(), 10);
			
		assertSame(b.getGenerator(), g);
		assertSame(b.getCells(), g.getCells());
		assertSame(b.getAvailableCells(), g.getAvailableCells());
		
		assertEquals(b.getEmptyCells(), g.getAvailableCells().size());
	}

	@Test
	public void TestBoardCreation2() {
		int dimy = 7;
			
		Board b = new Board(dimy);
		Generator g = b.getGenerator();
			
		assertEquals(b.getDimX(), 3*dimy);
		assertEquals(b.getDimY(), dimy);
			
		assertSame(b.getGenerator(), g);
		assertSame(b.getCells(), g.getCells());
		assertSame(b.getAvailableCells(), g.getAvailableCells());
		
		assertEquals(b.getEmptyCells(), g.getAvailableCells().size());
	}

	@Test
	public void TestBoardCreation3() {
		int dimx = 10;
		int dimy = 7;
		
		Board b = new Board(dimx, dimy);
		Generator g = b.getGenerator();
		
		assertEquals(b.getDimX(), dimx);
		assertEquals(b.getDimY(), dimy);
		
		assertSame(b.getGenerator(), g);
		assertSame(b.getCells(), g.getCells());
		assertSame(b.getAvailableCells(), g.getAvailableCells());
		
		assertEquals(b.getEmptyCells(), g.getAvailableCells().size());
	}

	@Test
	public void TestBoardCreation4() {
		int dimx = 10;
		int dimy = 7;
		int id = 0;
		
		Generator g = new Generator(id, dimx, dimy);
		Board b = new Board(g);
			
		assertEquals(b.getDimX(), dimx);
		assertEquals(b.getDimY(), dimy);
			
		assertSame(b.getGenerator(), g);
		assertSame(b.getCells(), g.getCells());
		assertSame(b.getAvailableCells(), g.getAvailableCells());
		
		assertEquals(b.getEmptyCells(), g.getAvailableCells().size());
	}
	
	@Test
	public void testGetDimX() {
		int dimx = 10;
		int dimy = 7;
		
		Board b = new Board(dimx, dimy);
		
		assertEquals(b.getDimX(), dimx);
	}
	
	@Test
	public void testGetDimY() {
		int dimx = 10;
		int dimy = 7;
		
		Board b = new Board(dimx, dimy);
		
		assertEquals(b.getDimY(), dimy);
	}
	
	@Test
	public void testGetGenerator() {
		int dimx = 10;
		int dimy = 7;
		int id = 0;
		
		Generator g = new Generator(id, dimx, dimy);
		Board b = new Board(g);
			
		assertSame(b.getGenerator(), g);
	}
	
	@Test
	public void testGetCells() {
		int dimx = 10;
		int dimy = 7;
		int id = 0;
		
		Generator g = new Generator(id, dimx, dimy);
		Board b = new Board(g);
			
		assertSame(b.getCells(), g.getCells());
	}
	
	@Test
	public void testGetAvailableCells() {
		int dimx = 10;
		int dimy = 7;
		int id = 0;
		
		Generator g = new Generator(id, dimx, dimy);
		Board b = new Board(g);
			
		assertSame(b.getAvailableCells(), g.getAvailableCells());
	}
	
	@Test
	public void testGetEmptyCells() {
		Board b = new Board();
		
		assertEquals(b.getAvailableCells().size(), b.getEmptyCells());
	}
	
	@Test
	public void testRemoveAvailableCells() {
		Board b = new Board();
		
		ArrayList<Cell> cells = b.getAvailableCells();
		int size = cells.size();
		
		assertEquals(b.getEmptyCells(), size);
		
		b.removeAvailableCell(cells.get(size - 1));
		assertEquals(b.getEmptyCells(), size - 1);
		
		int size2 = cells.size();
		
		b.removeAvailableCell(cells.get(size2 - 1));
		
		assertEquals(size2, size - 1);
		assertEquals(b.getEmptyCells(), size2 - 1);
	}
	
	@Test
	public void testAddAvailableCells() {
		Board b = new Board();
		
		ArrayList<Cell> cells = b.getAvailableCells();
		int size = cells.size();
		
		Cell cell1 = cells.get(size - 1);
		Cell cell2 = cells.get(size - 2);
		
		assertEquals(b.getEmptyCells(), size);
		
		b.removeAvailableCell(cell1);
		b.removeAvailableCell(cell2);
		
		assertEquals(b.getEmptyCells(), size - 2);
		
		b.addAvailableCell(cell1);
		
		assertEquals(b.getEmptyCells(), size - 1);
		
		b.addAvailableCell(cell2);
		
		assertEquals(b.getEmptyCells(), size);
	}
	
	
	@Test
	public void testIsFull() {
		Board b = new Board();
		
		ArrayList<Cell> cells = b.getAvailableCells();
		Cell c = cells.get(0);
		
		assertEquals(b.isFull(), false);
		
		while (!cells.isEmpty()) {
			b.removeAvailableCell(cells.get(0));
		}
		
		assertEquals(b.isFull(), true);
		
		b.addAvailableCell(c);
		
		assertEquals(b.isFull(), false);
	}
	
	@Test
	public void testIsCellAvailable() {
		Board b = new Board();
		
		ArrayList<Cell> cells = b.getAvailableCells();
		Cell c = cells.get(0);
		
		assertEquals(b.isCellAvailable(c), true);
		
		b.removeAvailableCell(c);
		
		assertEquals(b.isCellAvailable(c), false);
		
		b.addAvailableCell(c);
		
		assertEquals(b.isCellAvailable(c), true);

	}
	
	@Test
	public void testSelectCell() {
		Board b = new Board();
		Cell c1 = b.selectCell(1, 0);
		Cell c2 = b.selectCell(0, 19);
		Cell c3 = b.selectCell(42, -3);
		
		assertSame(c1, b.getCells()[0][1]);
		assertEquals(c2, null);
		assertEquals(c3, null);
	}
	
	@Test
	public void testGetAdjacentCells() {
		Board b = new Board();
		Cell c = b.getRandomAvailableCells();
		
		ArrayList<Cell> adjcells = b.getAdjacentCellsOf(c);
		
		assertEquals(adjcells.size() <= 4, true);
	}
	
	@Test
	public void testSetUnit() {
		Board b = new Board();
		Cell c = b.getRandomAvailableCells();
		int size = b.getEmptyCells();
		
		WorkerPlayer wplayer = new WorkerPlayer("Léo");
		
		Worker w = new Worker(wplayer);
		
		w.setCell(c);
		
		assertEquals(c.getUnit(), null);
		
		b.setUnit(w);
		
		assertSame(c.getUnit(), w);
		assertEquals(b.getEmptyCells(), size - 1);
	}
	
	@Test
	public void testRemoveUnit() {
		Board b = new Board();
		Cell c = b.getRandomAvailableCells();
		int size = b.getEmptyCells();
		
		WorkerPlayer wplayer = new WorkerPlayer("Louis");
		
		Worker w = new Worker(wplayer);
		
		w.setCell(c);
		
		b.setUnit(w);
		
		assertSame(c.getUnit(), w);
		assertEquals(b.getEmptyCells(), size - 1);
		
		b.removeUnit(w);
		
		assertSame(c.getUnit(), null);
		assertEquals(b.getEmptyCells(), size);
		
		b.removeUnit(w);
		
		assertSame(c.getUnit(), null);
		assertEquals(b.getEmptyCells(), size);
	}

	

	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(BoardTest.class);
    }
    
}