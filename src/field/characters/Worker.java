package field.characters;

import common.board.Cell;
import common.board.Ground;
import common.characters.Unit;

import field.players.WorkerPlayer;


/**
 * Worker defines the base unit for the field game
 * 
 * @author Group 7
 * @version 1.0
 */

public class Worker extends Unit {
	
	private WorkerPlayer owner;
	private int pay;
	
    /**
     * Worker constructor
     * 
     * @param owner the owner of the worker
     */
    public Worker(WorkerPlayer owner) {
        super();
        
        this.owner = owner;
        
        this.pay = 0;
    }
    
    /**
     * owner attribute getter
     * 
     * @return the owner of the worker
     */
    public WorkerPlayer getOwner() {
    	return this.owner;
    }
    
    /**
     * pay attribute getter
     * 
     * @return the pay of the worker
     */
    public int getPay() {
    	return this.pay;
    }
    
    /**
     * cell attribute setter
     * 
     * @param c the cell to set
     */
    public void setCell(Cell c) {
    	super.setCell(c);
    	this.pay = this.getGround().getPayValue();
    }

    /**
     * The owner pays the worker
     */
    public void getPaid() {
    	this.gold += this.pay;
    	this.owner.addGold(-this.pay);
    }
    
    /**
     * The owner receives golds based on the ground his worker stands on
     */
    public void receiveGolds() {
    	if (this.ground == Ground.Plain || this.ground == Ground.Forest) {
    		this.owner.addGold(1);
    	}
    	else if (this.ground == Ground.Desert) {
    		this.owner.addGold(2);
    	}
    }
    
    /**
     * The worker is removed from the game
     */
    public void loseUnit() {
    	super.removeCell();
    	this.pay = 0;
        this.owner = null;
    }
    
    /**
     * The worker harvest resources and give them to his owner
     */
    public void recoltRess() {
    	this.owner.updateRessource(1, this.ressource);
    }
    
	/** 
	 * Gives an external representation the worker
	 * This shows details about the worker (such as his pay)
	 *
	 * @return a string that can be printed to display information
	 */
    public String toString() {
    	return String.format("Worker( " + super.toString() + ", Pay: %d)", this.pay);
    }

}