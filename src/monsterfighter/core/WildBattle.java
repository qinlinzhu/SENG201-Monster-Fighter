package monsterfighter.core;

import java.util.ArrayList;

/**
 * Class that models a wild battle 
 */
public class WildBattle extends Battle{

	// The item that is awarded upon victory
	final private Item reward;
	
	/**
	 * Constructs a wild battle with a set number of points, an array of 
	 * {@link Monster}'s and an {@link Item} reward.
	 * 
	 * @param reward The item that is awarded upon victory
	 * @param points The amount of points that the battle should award upon victory
	 * @param monsters The list of {@link Monster}'s that may appear in the Battle
	 */
	public WildBattle(Item reward, int points, ArrayList<Monster> monsters) {
		super(points, monsters);
		this.reward = reward;
	}

	/**
	 * Gets the item that is awarded upon victory.
	 * 
	 * @return The item that is awarded upon victory
	 */
	public Item getReward() {
		return reward;
	}
	
	/**
	 * Provides a basic description of this wild battle.
	 * 
	 * @return A description that includes the monster and it's type, the amount of points
	 * and item reward
	 */
	@Override
	public String toString() {
		String description = "Monster: " + getMonsters().get(0).getName() + " Type: " + getMonsters().get(0).getType().value + " " + super.toString() + " Reward: " + reward.getName();
		return description;
	}
	
}
