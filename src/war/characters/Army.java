package war.characters;

import common.board.Cell;
import common.board.Ground;
import common.characters.Unit;

import war.players.ArmyPlayer;

public class Army extends Unit {
	
	private ArmyPlayer owner;
    private int size;
    private int fakesize;
    private int stamina;

    /**
     * Army Constructor
     * 
     * @param owner the owner of the army
     * @param size the size of the army
     */
    public Army(ArmyPlayer owner, int size) {
    	
        super();
        
        this.owner = owner;

        this.size = size;
        this.fakesize = size;
        this.stamina = size;
    }
    
    /**
     * owner attribute getter
     * 
     * @return the owner of the army
     */
    public ArmyPlayer getOwner() {
        return this.owner;
    }

    /**
     * size attribute getter
     * 
     * @return the size of the army
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * fakesize attribute getter
     * 
     * @return the fakesize of the army
     */
    public int getFakesize() {
    	return this.fakesize;
    }
    
    /**
     * stamina attribute getter
     * 
     * @return the stamina of the army
     */
    public int getStamina() {
        return this.stamina;
    }
    
    /**
     * cell attribute setter
     * Also change the size, fakesize and stamina of the army based on the cell properties
     * 
     * @param c the cell to set
     */
    public void setCell(Cell c) {
    	super.setCell(c);
    	
        if (size > this.ground.getMaxArmySize()) this.size = this.ground.getMaxArmySize();
        else if (size <= 0) this.size = 1;
        
        this.fakesize = this.size + this.ground.getBuffSize();
        
        this.stamina = this.size;
        if (this.ground == Ground.Desert) this.stamina = this.stamina * 2;
    }

    /**
     * The army betrays his owner for a new one
     * 
     * @param newowner the new owner of the army
     */
    public void betrayMaster(ArmyPlayer newowner) {
    	this.owner = newowner;
    }

    /**
     * Check if the owner has enough food to feed his army
     * 
     * @return <code>true</code> if the army cannot get feed, <code>false</code> if not
     */
    public boolean isGoingToStarve() {
    	return (this.owner.getFoodStock() < this.stamina);
    }
    
    /**
     * Feed the army (the owner loses food based on the army stamina)
     */
    public void feed() {
    	this.owner.addFood(-this.stamina);
    }

    /**
     * The army is remove from the game
     */
    public void loseUnit() {
        super.removeCell();
        this.fakesize = size;
        this.stamina = size;
        this.owner = null;
    }
    
    /**
     * The army harvest resources and give them to its owner
     */
    public void recoltRess() {
    	this.owner.updateRessource(1, this.ressource);
    }
    
    /**
     * Allow the update of the fakesize when another army is deployed in an adjacent cell of this one
     */
    public void updateFakeSize() {
    	this.fakesize = this.size + this.ground.getBuffSize();
    }
    
    /**
     * Allow the update of the stamina when another army is deployed in an adjacent cell of this one
     */
    public void updateStamina() {
        this.stamina = this.size;
        if (this.ground == Ground.Desert) this.stamina = this.stamina * 2;
    }
    
    /**
     * This method allows to modifies an army attributes based on this one attributes
     * For example, when an army is deployed, nearby deployed armies can be affected by the deployment
     * 
     * @param army the affected army
     */
    public void checkNeighbourhood(Army army) {
	    	
	    boolean isAlly = army.getOwner() == this.owner;
	    
	    if (!isAlly && army.getFakesize() < this.fakesize) {
	    	
	        if (army.size > 1) {
	            army.size /= 2;
	            army.updateFakeSize();
	            army.updateStamina();
	        }
	        
	        else {
	        	army.getOwner().removeArmy(army);
	        	army.betrayMaster(this.owner);
	        	army.getOwner().addRalliedArmy(army);
	        	this.gold += 2;
	        }
	    }
	    
	    else if (isAlly && army.getSize() < this.size) {
	    	
	    	if ( !(army.getSize() >= 3 && (army.getGround() == Ground.Desert || army.getGround() == Ground.Mountain) ) ) {
		        army.size ++;
	            army.updateFakeSize();
	            army.updateStamina();
		        this.gold ++;
	    	}
	    }
    }
    
	/** 
	 * Gives an external representation the army
	 * This shows details about the army (such as its size)
	 *
	 * @return a string that can be printed to display information
	 */
    public String toString() {
    	return String.format("Army( " + super.toString() + ", Size: %d, Fakesize: %d, Stamina: %d )", this.size, this.fakesize, this.stamina);
    }

}