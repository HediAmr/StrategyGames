package common.board;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneratorTest {
	
	@Test 
	public void testGeneratorCreation() {
		int id = 0;
		int dimx = 10;
		int dimy = 7;
		
		Generator g = new Generator(id, dimx, dimy);
		
		assertEquals(g.getID(), id);
		assertEquals(g.getDimX(), dimx);
		assertEquals(g.getDimY(), dimy);
	}
	
	@Test 
	public void testGetID() {
		int id = 0;
		int dimx = 10;
		int dimy = 7;
		
		Generator g = new Generator(id, dimx, dimy);
		
		assertEquals(g.getID(), id);
	}
	
	@Test 
	public void testGetDimX() {
		int id = 0;
		int dimx = 10;
		int dimy = 7;
		
		Generator g = new Generator(id, dimx, dimy);
		
		assertEquals(g.getDimX(), dimx);
	}
	
	@Test 
	public void testGetDimY() {
		int id = 0;
		int dimx = 10;
		int dimy = 7;
		
		Generator g = new Generator(id, dimx, dimy);
		
		assertEquals(g.getDimY(), dimy);
	}


	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(GeneratorTest.class);
    }

}