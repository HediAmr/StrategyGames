package war.actions.action;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Cell;
import common.board.Ground;

public class DeployTest {

	@Test
	public void testGetToDeploy() {
		Deploy.setToDeploy(0);
		assertEquals(Deploy.getToDeploy(), 0);
	}
	
	@Test
	public void testGetLocation() {
		Deploy.setLocation(null);
		assertEquals(Deploy.getLocation(), null);
	}
	
	@Test
	public void testSetToDeploy() {
		
		int n = 3;
		
		Deploy.setToDeploy(n);
		assertEquals(Deploy.getToDeploy(), n);
	}
	
	@Test
	public void testSetLocation() {
		
		Cell c = new Cell(8, 11, Ground.Forest);
		
		Deploy.setLocation(c);
		assertSame(Deploy.getLocation(), c);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(DeployTest.class);
    }
    
}
