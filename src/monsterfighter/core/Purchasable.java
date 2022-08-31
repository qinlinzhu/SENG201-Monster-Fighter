package monsterfighter.core;

/**
 * Models the purchasable interface that allows an object to be bought 
 * from a {@link Shop} and sold for gold
 */
public interface Purchasable {

	/**
	 * A description of a purchasable object that includes the 
	 * the buy price of the object and a basic description.
	 * 
	 * @return A description including basic details about the object and the buy price
	 */
	public String buyDescription();
	
	/**
	 * A description of a purchasable object that includes the 
	 * the sell price of the object and a basic description.
	 * 
	 * @return A description including basic details about the object and the sell price
	 */
	public String sellDescription();
	
	/**
	 * Gets the purchasable object's buy price.
	 * 
	 * @return The buy price of the purchasable object
	 */
	public int getBuyPrice();
	
	/**
	 * Gets the purchasable object's sell price.
	 * 
	 * @return The sell price of the purchasable object
	 */
	public int getSellPrice();
	
	/**
	 * Gets the index value of a purchasable object.
	 * 
	 * @return the index of a purchasable object.
	 */
	public int getIndex();

}


