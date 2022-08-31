package monsterfighter.core;

import java.util.ArrayList;

/**
 * Class that models a trainer battle 
 */
public class TrainerBattle extends Battle {
	
	// The amount of gold the battle should award
	final private int gold;
	
	// The name of the trainer
	final private String trainer;
	
	/**
	 * Constructs a trainer battle with a set number of points, an array of 
	 * {@link Monster}'s, a set amount of gold and a name for the trainer.
	 * 
	 * @param gold The amount of gold that the battle should award upon victory
	 * @param points The amount of points that the battle should award upon victory
	 * @param monsters The list of {@link Monster}'s that may appear in the Battle
	 * @param trainer The name of the monster trainer
	 */
	public TrainerBattle(int gold, int points, ArrayList<Monster> monsters, String trainer) {
		super(points, monsters);
		this.gold = gold;
		this.trainer = trainer;
	}

	/**
	 * Gets the amount of gold for this Battle.
	 * 
	 * @return The amount of gold for this Battle
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * Gets the name of the trainer.
	 * 
	 * @return The trainer's name
	 */
	public String getTrainer() {
		return trainer;
	}
	
	/**
	 * Gets a battle description used for a command line UI implementation of a battle.
	 * 
	 * @return A description that includes the trainer's name, number of 
	 * {@link Monster.Status#CONSCIOUS} {@link Monster}'s and leading monster.
	 */
	@Override
	public String battleStatus() {
		String description = "Trainer: " + trainer + " " + super.getConsciousMonsters() + "x M\n" + super.battleStatus() + " " ;
		return description;
	}
	

	/**
	 * Provides a basic description of this trainer battle.
	 * 
	 * @return A description that includes the trainer's name, number of monsters, points 
	 * awarded and gold given
	 */
	@Override
	public String toString() {
		String description = "Trainer: " + trainer + " " + getMonsters().size() + "x Monsters " + super.toString() +" Gold: " + gold;
		return description;
	}
}

