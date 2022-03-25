package war.characters;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Cell;
import common.board.Ground;
import common.board.Ressource;

import war.players.ArmyPlayer;

public class ArmyTest {

	@Test
	public void testArmyCreation() {
		int n = 3;
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, n);
		
		assertSame(a.getOwner(), aplayer);
		assertEquals(a.getSize(), n);
		assertEquals(a.getFakesize(), n);
		assertEquals(a.getStamina(), n);
	}
	
	@Test
	public void testGetOwner() {
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, 3);
		
		assertSame(a.getOwner(), aplayer);
		
	}
	
	@Test
	public void testGetSize() {
		int n = 3;
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, n);
		
		assertEquals(a.getSize(), n);
	}
	
	@Test
	public void testGetFakeSize() {
		int n = 3;
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, n);
		
		assertEquals(a.getFakesize(), n);
	}
	
	@Test
	public void testGetStamina() {
		int n = 3;
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, n);
		
		assertEquals(a.getStamina(), n);
	}
	
	@Test
	public void testSetCell() {
		int n = 5;
		
		Cell c1 = new Cell(7, 9, Ground.Desert);
		Cell c2 = new Cell(8, 10, Ground.Mountain);
		Cell c3 = new Cell(9, 11, Ground.Plain);
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, n);
		
		assertEquals(a.getSize(), n);
		assertEquals(a.getFakesize(), n);
		assertEquals(a.getStamina(), n);

		a.setCell(c1);
		
		int size = c1.getType().getMaxArmySize();
		int fakesize = size + c1.getType().getBuffSize();
		
		assertEquals(a.getSize(), size);
		assertEquals(a.getFakesize(), fakesize);
		assertEquals(a.getStamina(), 2*size);
		
		a.setCell(c2);
		
		size = c1.getType().getMaxArmySize();
		fakesize = size + c2.getType().getBuffSize();
		
		assertEquals(a.getSize(), size);
		assertEquals(a.getFakesize(), fakesize);
		assertEquals(a.getStamina(), size);
		
		a.setCell(c3);
		
		size = c1.getType().getMaxArmySize();
		fakesize = size + c3.getType().getBuffSize();
		
		assertEquals(a.getSize(), size);
		assertEquals(a.getFakesize(), fakesize);
		assertEquals(a.getStamina(), size);
	}
	
	@Test
	public void testBetrayMaster() {
		ArmyPlayer aplayer1 = new ArmyPlayer("Jean");
		ArmyPlayer aplayer2 = new ArmyPlayer("John");
		
		Army a = new Army(aplayer1, 3);
		
		assertSame(a.getOwner(), aplayer1);
		
		a.betrayMaster(aplayer2);
		
		assertSame(a.getOwner(), aplayer2);
	}
	
	@Test
	public void testIsGoingToStarve() {
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, 3);
		
		assertEquals(aplayer.getFoodStock(), 10);
		assertEquals(a.isGoingToStarve(), false);
		
		aplayer.addFood(-aplayer.getFoodStock());
		
		assertEquals(aplayer.getFoodStock(), 0);
		assertEquals(a.isGoingToStarve(), true);
		
		aplayer.addFood(3);
		
		assertEquals(aplayer.getFoodStock(), 3);
		assertEquals(a.isGoingToStarve(), false);
	}
	
	@Test
	public void testFeed() {
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, 3);
		
		assertEquals(aplayer.getFoodStock(), 10);
		
		a.feed();
		assertEquals(aplayer.getFoodStock(), 10 - a.getStamina());
		
		a.feed();
		assertEquals(aplayer.getFoodStock(), 10 - 2*a.getStamina());
	}
	
	@Test
	public void testLoseUnit() {
		Cell c = new Cell(7, 9, Ground.Desert);
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, 5);

		a.setCell(c);
		
		int size = c.getType().getMaxArmySize();
		int fakesize = size + c.getType().getBuffSize();
		
		assertEquals(a.getSize(), size);
		assertEquals(a.getFakesize(), fakesize);
		assertEquals(a.getStamina(), 2*size);
		assertSame(a.getOwner(), aplayer);
		
		a.loseUnit();
		
		assertEquals(a.getFakesize(), size);
		assertEquals(a.getStamina(), size);
		assertEquals(a.getOwner(), null);
	}
	
	@Test
	public void testRecoltRess() {
		Cell c = new Cell(7, 9, Ground.Desert);
		
		ArmyPlayer aplayer = new ArmyPlayer("Jean");
		Army a = new Army(aplayer, 5);
		
		a.setCell(c);
		
		Ressource r = c.getType().getRessource();
		assertEquals(aplayer.getRessourceQuantity(r), 0);
		
		a.recoltRess();
		assertEquals(aplayer.getRessourceQuantity(r), 1);
		
		a.recoltRess();
		assertEquals(aplayer.getRessourceQuantity(r), 2);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(ArmyTest.class);
    }

}
