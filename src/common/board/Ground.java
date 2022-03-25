package common.board;


/**
 * Ground defines the "biome" of a cell
 * It contains details such as what resource is produced on this ground for example
 * 
 * @author Group 7
 * @version 1.0
 */

public enum Ground {
	
	Mountain(Ressource.STONE, 3, 2, 5, "M"),
	Plain(Ressource.WHEAT, 5, 0, 1, "P"),
	Desert(Ressource.SAND, 3, 0, 3, "D"),
	Forest(Ressource.WOOD, 5, 0, 1, "F"),
	Ocean(null, 0, 0, 0, " ");
	
	private final Ressource resource;
	private final int maxarmysize;
	private final int buffsize;
	private final int payvalue;
	private final String str;

	/**
	 * Ground constructor
	 * 
	 * @param resource the resource of the ground
	 * @param maxarmysize the max allowed army size of the ground
	 * @param buffsize the size buff given to an army on a specific ground
	 * @param payvalue the pay value of a worker on a specific ground
	 * @param str the first letter of the ground name
	 */
	private Ground(Ressource resource, int maxarmysize, int buffsize, int payvalue, String str) {
		this.resource = resource;
		this.maxarmysize = maxarmysize;
		this.buffsize = buffsize;
		this.payvalue = payvalue;
		this.str = str;
	}
	
	/**
	 * resource attribute Getter
	 *
	 * @return the resource produce on the ground
	 */
	public Ressource getRessource() {
		return this.resource;
	}
	
	/**
	 * maxarmysize attribute Getter
	 *
	 * @return the army size limiter of the ground
	 */
	public int getMaxArmySize() {
		return this.maxarmysize;
	}
	
	/**
	 * buffsize attribute Getter
	 *
	 * @return the army size modifier of the ground
	 */
	public int getBuffSize() {
		return this.buffsize;
	}
	
	/**
	 * payvalue attribute Getter
	 *
	 * @return the amount of gold to pay a worker on it
	 */
	public int getPayValue() {
		return this.payvalue;
	}
	
	/**
	 * Gives an external representation of the ground
	 * This is used when the board is display to see ground properties
	 *
	 * @return a string that can be printed to display the property of the ground
	 */
	public String toString() {
		return this.str;
	}

}
