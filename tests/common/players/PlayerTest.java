package common.players;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Ressource;

import field.players.WorkerPlayer;

public class PlayerTest {

	@Test
	public void testPlayerCreation() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertSame(p.getName(), name);
		assertEquals(p.getPoints(), 0);
		assertEquals(p.getGold(), 15);
		
		for (int i = 0; i < Ressource.values().length; i++) {
			assertEquals(p.getInventory().containsKey(Ressource.values()[i]), true);
		}
	}
	
	@Test
	public void testGetName() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertSame(p.getName(), name);
	}
	
	@Test
	public void testGetPoints() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertEquals(p.getPoints(), 0);
	}
	
	@Test
	public void testGetGold() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertEquals(p.getGold(), 15);
	}
	
	@Test
	public void testGetInventory() {
		String name = "Pierre";
		
		WorkerPlayer p = new WorkerPlayer(name);
		
		for (int i = 0; i < Ressource.values().length; i++) {
			assertEquals(p.getInventory().containsKey(Ressource.values()[i]), true);
		}
	}
	
	@Test
	public void testAddPoints() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertEquals(p.getPoints(), 0);
		
		p.addPoints(101);
		assertEquals(p.getPoints(), 101);
		
		p.addPoints(-32);
		assertEquals(p.getPoints(), 69);
	}
	
	@Test
	public void testAddGold() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertEquals(p.getGold(), 15);
		
		p.addGold(368);
		assertEquals(p.getGold(), 383);
		
		p.addGold(-40);
		assertEquals(p.getGold(), 343);
	}
	
	@Test
	public void testGetRessourceQuantity() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertEquals(p.getRessourceQuantity(Ressource.SAND), 0);
	}
	
	@Test
	public void testAddRessources() {
		String name = "Pierre";
		WorkerPlayer p = new WorkerPlayer(name);
		
		assertEquals(p.getRessourceQuantity(Ressource.SAND), 0);
		
		p.updateRessource(42, Ressource.SAND);
		assertEquals(p.getRessourceQuantity(Ressource.SAND), 42);
		
		p.updateRessource(23, Ressource.SAND);
		assertEquals(p.getRessourceQuantity(Ressource.SAND), 65);
		
		p.updateRessource(-36, Ressource.SAND);
		assertEquals(p.getRessourceQuantity(Ressource.SAND), 29);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(PlayerTest.class);
    }

}
