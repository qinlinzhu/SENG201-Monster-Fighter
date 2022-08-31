package monsterfighter.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import monsterfighter.core.GameEnvironment.Difficulty;

/**
 * Class that models the construction of the 
 * {@link Battle}'s used in the application.
 * 
 */
public class Battles {
	
	// An ArrayList of all available wild battles
	private ArrayList<Battle> wildBattles;
	
	// An ArrayList of all available trainer battles
	private ArrayList<Battle> trainerBattles;
	
	// List of trainer names
	private final List<String> trainers = Arrays.asList("Ben","Matt","Lee","Ian","John","Eva","Nancy","Haley","Jade","Beth");
	
	// Points to a battle in wildBattles or trainerBattles
	private Battle currentBattle;

	// A random number selector 
	private Random rng = new Random();
	
	/**
	 * Creates a Battles object with two list's for
	 * {@link WildBattle}'s and {@link TrainerBattle}'s.
	 */
	public Battles() {
		wildBattles = new ArrayList<Battle>(); 
		trainerBattles = new ArrayList<Battle>();
	}

	/**
	 * Gets the {@link WildBattle}'s available in Battles.
	 * 
	 * @return the {@link WildBattle}'s available in Battles
	 */
	public ArrayList<Battle> getWildBattles() {
		return wildBattles;
	}

	/**
	 * Adds a {@link WildBattle} to the wildBattles list.
	 * 
	 * @param wildBattle The {@link WildBattle} that is to be added 
	 * to the {@link WildBattle}'s list
	 */
	public void addWildBattle(Battle wildBattle) {
		wildBattles.add(wildBattle);
	}
	
	/**
	 * Removes a {@link WildBattle} from the wildBattles list.
	 * 
	 * @param wildBattle The {@link WildBattle} that is to be removed 
	 * from the {@link WildBattle}'s list
	 */
	public void removeWildBattle(Battle wildBattle) {
		wildBattles.remove(wildBattle);
	}

	/**
	 * Gets the {@link TrainerBattle}'s available in Battles.
	 * 
	 * @return the {@link TrainerBattle}'s available in Battles
	 */
	public ArrayList<Battle> getTrainerBattles() {
		return trainerBattles;
	}

	/**
	 * Adds a {@link TrainerBattle} to the trainerBattles list.
	 * 
	 * @param trainerBattle The {@link TrainerBattle} that is to be added 
	 * to the {@link TrainerBattle}'s list
	 */
	public void addTrainerBattle(Battle trainerBattle) {
		trainerBattles.add(trainerBattle);
	}
	
	/**
	 * Removes a {@link TrainerBattle} from the {@link TrainerBattle}'s list.
	 * 
	 * @param trainerBattle The {@link TrainerBattle} that is to be removed 
	 * from the {@link TrainerBattle}'s list
	 */
	public void removeTrainerBattle(Battle trainerBattle) {
		trainerBattles.remove(trainerBattle);
	}
	
	/**
	 * Sets the {@link Battle} that the user is engaged with.
	 * 
	 * @param currentBattle the {@link Battle} that the user is engaged with. 
	 */
	public void setCurrentBattle(Battle currentBattle) {
		this.currentBattle = currentBattle;
	}
	
	/**
	 * Gets the {@link Battle} that the user is engaged with.
	 * 
	 * @return the {@link Battle} that the user is engaged with. 
	 */
	public Battle getCurrentBattle() {
		return currentBattle;
	}

	/**
	 * Parent function that links to the functions that populate
	 * the {@link WildBattle}'s and {@link TrainerBattle}'s lists.
	 * 
	 * @param allItems List of all available {@link Item}'s
	 * @param allMonsters List of all available {@link Monster}'s
	 * @param difficulty The {@link GameEnvironment.Difficulty} chosen by the user
	 * @param day The current day for the game
	 * @param partySize The size of the {@link Player}'s party of {@link Monster}'s
	 */
	public void fillBattles(List<Item> allItems, List<Monster> allMonsters, GameEnvironment.Difficulty difficulty, int day, int partySize) {
		fillWildBattles(allItems, allMonsters, difficulty, day);
		fillTrainerBattles(allItems, allMonsters, difficulty, day, partySize);
		
	}
	
	/**
	 * Fills the {@link WildBattle}'s list with {@link WildBattle}'s 
	 * based on given parameters.
	 * 
	 * @param allItems List of all available {@link Item}'s
	 * @param allMonsters List of all available {@link Monster}'s
	 * @param difficulty The {@link GameEnvironment.Difficulty} chosen by the user
	 * @param day The current day for the game
	 */
	public void fillWildBattles(List<Item> allItems, List<Monster> allMonsters, Difficulty difficulty, int day) {
		// Clears out the previous day's wild battles
		if (wildBattles.size() > 0) {
			wildBattles.clear();
		}
		
		for (int i = 0; i < 2; i++) {
			// Selects a random item as a reward
			int randomNumber = rng.nextInt(0, allItems.size());
			Item reward = new Item(allItems.get(randomNumber));
			
			// Selects and scales a random monster
			ArrayList<Monster> monsters = new ArrayList<>();
			randomNumber = rng.nextInt(allMonsters.size());
			Monster monster = new Monster(allMonsters.get(randomNumber));
			monster.scaleMonster(day - 3);
			monsters.add(monster);
			
			// Selects the correct amount of points
			int points = calculatePoints("Wild", monsters.size(), difficulty);
			
			wildBattles.add(new WildBattle(reward, points, monsters));
		}
	}
	
	/**
	 * Fills the {@link TrainerBattle}'s list with {@link TrainerBattle}'s 
	 * based on given parameters.
	 * 
	 * @param allItems List of all available {@link Item}'s
	 * @param allMonsters List of all available {@link Monster}'s
	 * @param difficulty The {@link GameEnvironment.Difficulty} chosen by the user
	 * @param day The current day for the game
	 * @param partySize The size of the {@link Player}'s party of {@link Monster}'s
	 */
	public void fillTrainerBattles(List<Item> allItems, List<Monster> allMonsters, Difficulty difficulty, int day, int partySize) {
		// Clears out the previous day's trainer battles
		if (trainerBattles.size() > 0) {
			trainerBattles.clear();
		}
		for (int i = 0; i < 3; i++) {
			// Selects a random party size based on the size of the users party
			int randomNumber = rng.nextInt(partySize + 1);
			
			// Fills the party with scaled monsters
			ArrayList<Monster> monsters = new ArrayList<>();
			for (int j = 0; j <= Math.min(randomNumber, 3); j++) {
				Monster monster = new Monster(allMonsters.get(rng.nextInt(allMonsters.size())));
				monster.scaleMonster(day - 1);
				monsters.add(monster);
			}
			
			// Selects a random name for the trainer
			String trainer = trainers.get(rng.nextInt(trainers.size()));
			
			// Selects the correct amount of points
			int points = calculatePoints("Trainer", monsters.size(), difficulty);
			
			// Selects the correct amount of gold
			int gold = calculateGold(monsters.size(), difficulty);
		
			trainerBattles.add(new TrainerBattle(gold, points, monsters, trainer));
		}
	}
	
	/**
	 * Calculates the correct quantity of points that a {@link Battle} should have 
	 * based on given parameters.
	 * 
	 * @param battleType The type of {@link Battle}
	 * @param partySize The number of {@link Monster}s in the {@link Battle} 
	 * @param difficulty The {@link GameEnvironment.Difficulty} chosen by the user
	 * @return The quantity of points for a {@link Battle}
	 */
	public int calculatePoints(String battleType, int partySize, GameEnvironment.Difficulty difficulty) {
		int points = 50 * partySize + difficulty.battlePoints;
		// Bonus points for taking on trainer battles
		if (!battleType.equals("Wild")) {
			points += 100;
		}
		return points;
	}
	
	/**
	 * Calculates the correct amount of gold that a {@link Battle} should have 
	 * based on given parameters.
	 * 
	 * @param partySize The number of {@link Monster}s in the {@link Battle} 
	 * @param difficulty The {@link GameEnvironment.Difficulty} chosen by the user
	 * @return The amount of gold for a {@link Battle}
	 */
	public int calculateGold(int partySize, GameEnvironment.Difficulty difficulty) {
		int gold = 100 * partySize + difficulty.battleGold;
		return gold;
	}
}
