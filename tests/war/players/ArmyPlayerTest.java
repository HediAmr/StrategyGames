package war.players;

import static org.junit.Assert.*;

import org.junit.Test;

import war.characters.Army;

public class ArmyPlayerTest {

	@Test
	public void testArmyPlayerCreation() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		
		assertEquals(aplayer.getFoodStock(), 10);
		assertEquals(aplayer.getWarriorsLeft(), 35);
		assertEquals(aplayer.getDeployed().isEmpty(), true);
	}
	
	@Test
	public void testGetFoodStock() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		
		assertEquals(aplayer.getFoodStock(), 10);
	}
	
	@Test
	public void testGetWarriorsLeft() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		
		assertEquals(aplayer.getWarriorsLeft(), 35);
	}
	
	@Test
	public void testGetDeployed() {
		ArmyPlayer aplayer = new ArmyPlayer("François");

		assertEquals(aplayer.getDeployed().isEmpty(), true);
	}
	
	@Test
	public void testAddFood() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		
		assertEquals(aplayer.getFoodStock(), 10);
		
		aplayer.addFood(117);
		
		assertEquals(aplayer.getFoodStock(), 127);
	}
	
	@Test
	public void testAddArmy() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		Army a = new Army(aplayer, 2);
		
		assertEquals(aplayer.getDeployed().isEmpty(), true);
		
		aplayer.addArmy(a);
		
		assertEquals(aplayer.getWarriorsLeft(), 35 - a.getSize());
		assertEquals(aplayer.getDeployed().isEmpty(), false);
	}
	
	@Test
	public void testAddRalliedArmy() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		Army a = new Army(aplayer, 2);
		
		assertEquals(aplayer.getDeployed().isEmpty(), true);
		
		aplayer.addRalliedArmy(a);
		
		assertEquals(aplayer.getWarriorsLeft(), 35);
		assertEquals(aplayer.getDeployed().isEmpty(), false);
	}
	
	@Test
	public void testRemoveArmy() {
		ArmyPlayer aplayer = new ArmyPlayer("François");
		Army a = new Army(aplayer, 2);
		
		assertEquals(aplayer.getDeployed().isEmpty(), true);
		
		aplayer.addArmy(a);
		
		assertEquals(aplayer.getWarriorsLeft(), 35 - a.getSize());
		assertEquals(aplayer.getDeployed().isEmpty(), false);
		
		aplayer.removeArmy(a);
		
		assertEquals(aplayer.getWarriorsLeft(), 35 - a.getSize());
		assertEquals(aplayer.getDeployed().isEmpty(), true);
	}
	
	
	
    /* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(ArmyPlayerTest.class);
    }

}
