package monsterfighter.core;

/**
 * Class that models an item
 */
public class Item implements Purchasable{
	
	/**
	 * Represents the stat that an {@link Item} affects.
	 */
	public enum Stat {
		MAXHEALTH("Max Health"),
		ATTACK("Attack"),
		CURRENTHEALTH("Current Health"),
		STATUS("Consciousness");
		
		/**
		 * A user friendly description of the value of this enum.
		 */
		public final String name;
		
		Stat(String name) {
			this.name = name;
		} 
	}
	
	// Index number denoting the item
	private final int index;
	
	// Item name
	private final String name;
	
	// The amount that the stat is changed by
	private final int amount;
	
	// The stat that the item affects
	private final Stat stat;
	
	// Buy price of the item from a shop
	private final int buyPrice;
	
	// Sell price of the item
	private final int sellPrice;
	
	// Number of items that should be stocked in shop
	private final int shopQuantity;
	
	/**
	 * Creates an item with the given parameters.
	 * 
	 * @param index Index number denoting the item
	 * @param name Name of the item
	 * @param amount The amount that the monster stat is changed by
	 * @param stat The monster stat that the item affects
	 * @param buyPrice Buy price of item from shop
	 * @param shopQuantity Sell price of item
	 */
	public Item(int index, String name, int amount, Stat stat, int buyPrice, int shopQuantity) {
		this.index = index;
		this.name = name;
		this.amount = amount;
		this.stat = stat;
		this.buyPrice = buyPrice;
		this.shopQuantity = shopQuantity;
		sellPrice = buyPrice / 2;
	}
	
	/**
	 * Creates a deep copy of an Item.
	 * 
	 * @param c Item that is being copied
	 */
	Item(Item c) {
		this.index = c.index;
		this.name = c.name;
		this.amount = c.amount;
		this.stat = c.stat;
		this.buyPrice = c.buyPrice;
		this.shopQuantity = c.shopQuantity;
		sellPrice = buyPrice / 2;
	}
	
	@Override
	public int getIndex() {
		return index;
	}
	
	/**
	 * Gets the name of the item.
	 * 
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the value of the {@link Monster} stat change by the item.
	 * 
	 * @return the value of the stat change
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Gets the {@link Monster} stat that is changed by the item.
	 * 
	 * @return the stat that is changed
	 */
	public Stat getStat() {
		return stat;
	}
	
	/**
	 * Gets the value of how many of the item should 
	 * be stocked in the {@link Shop}.
	 * 
	 * @return Returns the number of items in the shop
	 */
	public int getShopQuantity() {
		return shopQuantity;
	}
	
	@Override
	public int getBuyPrice() {
		return buyPrice;
	}

	@Override
	public int getSellPrice() {
		return sellPrice;
	}
	
	/**
	 * Uses the item on a given {@link Monster}.
	 * 
	 * @param monster The monster that the item is used on
	 * @throws IllegalStateException if an item would have no affect on the monster
	 */
	public void useItem(Monster monster) {
		switch (stat) {
        case MAXHEALTH:
        	monster.setMaxHealth(amount);
        	break;
        case ATTACK:
        	monster.setAttack(amount);
        	break;
        case CURRENTHEALTH:
        	if (monster.getCurrentHealth() < monster.getMaxHealth() && monster.getStatus().name == "Conscious") {
        		monster.receiveHealth(amount);
        	} else if (monster.getCurrentHealth() == monster.getMaxHealth()){
        		throw new IllegalStateException("Cannot use " + name + ", " + monster.getNickname() + "'s health is already at max\n");
        	} else {
        		throw new IllegalStateException("Cannot use " + name + ", " + monster.getNickname() + " is unconscious and must first be revived\n");
        	}
        	break;
        case STATUS:
        	if (monster.getStatus().name == "Fainted") {
        		monster.revive(amount);
        	} else {
        		throw new IllegalStateException("Cannot use " + name + ", " + monster.getNickname() + " is not unconscious\n");
        	}
        	break;
		}
	}
	
	/**
	 * Provides a description of the item.
	 * 
	 * @return a description of the item, including the name, affected stat 
	 * and the amount by which the stat is changed.
	 */
	@Override
	public String toString() {
		return "Item: " + name + " Effect: increases " + stat.name + " by " + amount;
	}


	@Override
	public String buyDescription() {
		String description = "[Buy Price: " + buyPrice + "] " + toString();
		return description;
	}

	@Override
	public String sellDescription() {
		String description = "[Sell Price: " + sellPrice + "] " + toString();
		return description;
	}
	
}
