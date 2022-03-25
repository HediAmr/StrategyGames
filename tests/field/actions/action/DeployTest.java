package field.actions.action;

import static org.junit.Assert.*;

import org.junit.Test;

import common.board.Cell;
import common.board.Ground;

public class DeployTest {

	@Test
	public void testGetLocation() {
		Deploy.setLocation(null);
		
		assertEquals(Deploy.getLocation(), null);
	}
	
	@Test
	public void testSetLocation() {
		
		Cell c = new Cell(3, 22, Ground.Plain);
		
		Deploy.setLocation(c);
		assertSame(Deploy.getLocation(), c);
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(DeployTest.class);
    }

}
