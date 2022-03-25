package common.board;


/**
 * Resource defines the different resources present in each game
 * It contains details about every resource such as the name for example
 *
 * @author Group 7
 * @version 1.0
 */

public enum Ressource {
	
	STONE("Roche", 8, 0),
	WHEAT("Blé", 2, 5),
	SAND("Sable", 5, 0),
	WOOD("Bois", 2, 1);
	
	private final String name;
	private final int value;
	private final int foodvalue;
	
	/**
	 * Resource constructor
	 * 
	 * @param name the name of the resource
	 * @param value the amount of gold the resource worth
	 * @param foodvalue the amount of food the resource worth
	 */
	private Ressource(String name, int value, int foodvalue) {
		this.name = name;
		this.value = value;
		this.foodvalue = foodvalue;
	}
	
	/**
	 * name attribute Getter
	 *
	 * @return the name of the resource
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * value attribute Getter
	 *
	 * @return the amount of gold the resource worth
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * foodvalue attribute Getter
	 *
	 * @return the amount of food the resource worth
	 */
	public int getFoodValue() {
		return this.foodvalue;
	}
	
	/**
	 * Gives an external representation of a resource
	 * Used to keep track of the resource exchanges, harvests...
	 *
	 * @return a string that can be printed to get the resource name
	 */
	public String toString() {
		return this.name;
	}

}
