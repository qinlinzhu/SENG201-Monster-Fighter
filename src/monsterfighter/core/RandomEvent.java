package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that models random events that can occur when a player 
 * rests in the monster fighter application
 */
public class RandomEvent {
	
	// List of booleans that corresponds to the player's party. True if the monster
	// will level up and false otherwise
	private List<Boolean> levelUp;
	// List of booleans that corresponds to the player's party. True if the monster
	// should leave and false otherwise
	private List<Boolean> leaves;
	// Boolean that determines whether a monster will join the player's party
	private boolean joins;
	
	/**
	 * Constructs the random events that may occur when the player rests. 
	 * 
	 * @param party The player's party of monsters
	 */
	public RandomEvent(ArrayList<Monster> party) {
		this.levelUp = monsterLevelUp(party);
		this.leaves = monsterLeaves(party);
		this.joins = monsterJoins(party);
	}
	
	/**
	 * Gets the list of booleans that informs which {@link Monster}'s in the 
	 * {@link Player}'s party will or won't level up.
	 * 
	 * @return An array of booleans that corresponds to the player's party. If the value
	 * is true then the corresponding monster will level up and false otherwise.
	 */
	public List<Boolean> getLevelUp() {
		return levelUp;
	}
	
	/**
	 * Gets the list of booleans that informs which {@link Monster}'s in the 
	 * {@link Player}'s party will or won't leave.
	 * 
	 * @return An array of booleans that corresponds to the player's party. If the value
	 * is true then the corresponding monster will leave the party and false otherwise.
	 */
	public List<Boolean> getMonsterLeaves() {
		return leaves;
	}
	
	/**
	 * Gets a boolean that is true if a {@link Monster} will join the {@link Player}'s
	 * party and false otherwise.
	 * 
	 * @return The boolean corresponding to whether or not a monster will join the
	 * player's party
	 */
	public boolean getMonsterJoins() {
		return joins;
	}
	
	/**
	 * Calculates which {@link Monster}'s in the {@link Player}'s party will 
	 * or won't level up and adds true or false to a list based on the result.
	 * 
	 * @param party The player's party of monsters
	 * @return The list corresponding to the monster's in the player's party that may or 
	 * may not level up
	 */
	public List<Boolean> monsterLevelUp(ArrayList<Monster> party) {
		List<Boolean> levelUp = new ArrayList<>(party.size());
		double odds = 0.1;
		for (int i = 0; i < party.size(); i++) {
			odds += party.get(i).getWins()*0.05;
			if (party.get(i).getFaintedToday()) {
				odds *= 0.5;
			}
			boolean monsterLevelUp = Math.random() < odds;
			levelUp.add(monsterLevelUp);
		}
		return levelUp;
	}
	
	/**
	 * Calculates which {@link Monster}'s in the {@link Player}'s party will 
	 * or won't leave and adds true or false to a list based on the result.
	 * 
	 * @param party The player's party of monsters
	 * @return The list corresponding to the monster's in the player's party that may 
	 * or may not leave
	 */
	public List<Boolean> monsterLeaves(ArrayList<Monster> party) {
		List<Boolean> leaves = new ArrayList<>(party.size());
		double odds = 0.025;
		for (int i = 0; i < party.size(); i++) {
			if (party.get(i).getFaintedToday()) {
				odds *= 2;
			}
			boolean monsterLeaves = Math.random() < odds;
			leaves.add(monsterLeaves);
		}
		return leaves;
		
		
	}
	
	/**
	 * Calculates whether a {@link Monster} will join the {@link Player}'s party
	 * 
	 * @param party The player's party of monsters
	 * @return A boolean corresponding to whether or not a monster will join the
	 * player's party
	 */
	public boolean monsterJoins(ArrayList<Monster> party) {
		double odds = 0 + (0.04 * (4 - party.size()));
		boolean joins = Math.random() < odds;
		return joins;
	
	
	}
	
	
	

}
