package field.players;

import static org.junit.Assert.*;

import org.junit.Test;

import field.characters.Worker;

public class WorkerPlayerTest {

	@Test
	public void testWorkerPlayerCreation() {
		WorkerPlayer wplayer = new WorkerPlayer("Louis");
		
		assertEquals(wplayer.getGold(), 15);
		assertEquals(wplayer.getDeployed().isEmpty(), true);
	}
	
	@Test
	public void testGetDeployed() {
		WorkerPlayer wplayer = new WorkerPlayer("Louis");
		
		assertEquals(wplayer.getDeployed().isEmpty(), true);
	}
	
	@Test
	public void testAddWorker() {
		WorkerPlayer wplayer = new WorkerPlayer("Louis");
		Worker w = new Worker(wplayer);
		
		assertEquals(wplayer.getDeployed().isEmpty(), true);
		
		wplayer.addWorker(w);
		
		assertEquals(wplayer.getDeployed().isEmpty(), false);
		assertSame(wplayer.getDeployed().get(0), w);
	}
	
	@Test
	public void testRemoveWorker() {
		WorkerPlayer wplayer = new WorkerPlayer("Louis");
		Worker w = new Worker(wplayer);
		
		wplayer.addWorker(w);
		
		assertEquals(wplayer.getDeployed().isEmpty(), false);
		
		wplayer.removeWorker(w);
		
		assertEquals(wplayer.getDeployed().isEmpty(), true);
		
	}
	
	
	
	/* ----- Tests execution ----- */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WorkerPlayerTest.class);
    }

}
