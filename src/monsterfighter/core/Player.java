package monsterfighter.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that models a player 
 */
public class Player {
	
	// The ArrayList containing the player's items
	private final List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();
	
	// The ArrayList containing the player's monsters
	private ArrayList<Monster> party = new ArrayList<Monster>();
	
	// The name of the player using this manager
	private String name;
	
	// The player's gold
	private int goldBalance = 0;
	
	// The total gold the player earned over the course of the game
	private int totalGold = 0;
	
	// The player's points
	private int points = 0;
	
	/**
	 * Constructs a player with a given name, and formats the player's
	 * inventory using the total number of items in the game.
	 * 
	 * @param name The name of the player
	 * @param allItems All the items present in a {@link GameEnvironment}
	 */
	public Player(String name, List<Item> allItems) {
		this.name = name;
		//Adds in an ArrayList for each item 
		for (int i = 0; i < allItems.size(); i++) {
			this.inventory.add(new ArrayList<Item>());
		}
	}
	
	/**
	 * Gets the players inventory.
	 * 
	 * @return An 2D containing items that acts as the players inventory
	 */
	public List<ArrayList<Item>> getInventory() {
		return inventory;
	}
	
	/**
	 * Adds an {@link Item} to the corresponding ArrayList in the 
	 * players inventory.
	 * 
	 * @param item The item to be added to the players inventory
	 */
	public void addItemToInventory(Item item) {
		inventory.get(item.getIndex()).add(item);
	}
	
	/**
	 * Removes an {@link Item} from the corresponding ArrayList in the 
	 * players inventory.
	 * 
	 * @param item The item type to be removed from the players inventory
	 */
	public void removeItemFromInventory(Item item) {
		inventory.get(item.getIndex()).remove(item);
	}
	
	/**
	 * Gets the player's party of {@link Monster}'s.
	 * 
	 * @return An array of monsters
	 */
	public ArrayList<Monster> getParty() {
		return party;
	}

	/**
	 * Adds a {@link Monster} to the party.
	 * 
	 * @param monster The {@link Monster} to be added to the party
	 * @throws IllegalStateException if the players party is already full
	 */
	public void addMonsterToParty(Monster monster) {
		if (party.size() >= 4) {
			throw new IllegalStateException("Party full, cannot add another monster to party!\n");	
		} else {
			party.add(monster);
		}
	}
	
	/**
	 * Removes a {@link Monster} from the party.
	 * 
	 * @param monster The {@link Monster} to be removed from the party
	 */
	public void removeMonsterFromParty(Monster monster) {
		party.remove(monster);
	}	
	
	/**
	 * Gets the {@link Monster} from the first slot in the party.
	 * 
	 * @return The {@link Monster} that occupies the first index of the party
	 */
	public Monster getLeadingMonster() {
		return party.get(0);
	}

	/**
	 * Gets the player's name.
	 * 
	 * @return The player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get's the player's gold balance.
	 * 
	 * @return 
	 */
	public int getGoldBalance() {
		return goldBalance;
	}

	/**
	 * Adds or subtracts gold from the player's gold balance.
	 * 
	 * @param gold The amount of gold that will be added to or taken from 
	 * the players gold balance
	 * @throws IllegalStateException if adding the gold will put the players 
	 * gold balance into the negatives
	 */
	public void setGoldBalance(int gold) {
		if (goldBalance+gold<0) {
			throw new IllegalStateException("Not enough gold!");
		}
		this.goldBalance += gold;
		if (gold > 0) {
			increaseTotalGold(gold);
		}
	}

	/**
	 * Returns the total gold the player has ever earned.
	 * 
	 * @return The total gold that the player has ever had in their gold balance
	 */
	public int getTotalGold() {
		return totalGold;
	}

	/**
	 * Increases the players total gold.
	 * 
	 * @param gold The amount of gold to add to the players total gold
	 */
	public void increaseTotalGold(int gold) {
		this.totalGold += gold;
	}

	/**
	 * Gets the player's points.
	 * 
	 * @return The amount of points the player has acquired
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Increases the player's points.
	 * 
	 * @param points The number of points to add the player's points
	 */
	public void increasePoints(int points) {
		this.points += points;
	}
	
	/**
	 * Checks to see if inventory is empty.
	 * 
	 * @return isEmpty States whether the player's inventory is empty or not
	 */
	public boolean inventoryIsEmpty() {
		boolean isEmpty = true;
		for (ArrayList<Item> item : inventory) {
			if (!item.isEmpty()) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	/**
	 * Given the two {@link Monster}s in party, switches them around.
	 * 
	 * @param monster The monster that is to be switched in party
	 * @param monsterSwitch The monster that is to be switched with in party
	 * @throws IllegalStateException if the given monsters are not 
	 * in the players party
	 */
	public void switchMonsters(Monster monster, Monster monsterSwitch) {
		int monsterID = -1;
		int monsterSwitchID = -1;
			
		int i = 0;	
		for (Monster m : party) {
			if (monster.equals(m)) {
				monsterID = i;
			}
			if (monsterSwitch.equals(m)) {
				monsterSwitchID = i;
			}
			i++;
		}
		if (monsterID!=-1 && monsterSwitchID!=-1) {
			Collections.swap(party, monsterID, monsterSwitchID);
		} else {
			throw new IllegalStateException("Invalid Monsters selected");
		}
	}
	
	/**
	 * Returns a boolean based on whether there exists a conscious monster in party.
	 * 
	 * @return Boolean that's true if all the monster's in party are fainted 
	 * or false if there exists a conscious monster
	 */
	public boolean partyFainted() {
		boolean fainted = true;
		for (Monster monster: party) {
			if (!monster.isFainted()) {
				fainted = false;
			}
		}
		return fainted;	
	}

	/**
	 * Returns the number of conscious {@link Monster}'s within the player's party.
	 * 
	 * @return The number of {@link Monster}'s in party with Status.CONSCIOUS
	 */
	public int getConsciousMonsters() {
		int conscious = party.size();
		for (Monster monster: party ) {
			if (monster.isFainted()) {
				conscious -= 1;
			}
		}
		return conscious;
	}

	/**
	 * Returns the number of {@link Item}'s in the player's inventory.
	 * 
	 * @return The number of {@link Item}'s in the player's inventory
	 */
	public int inventoryNumItems() {
		int numItems = 0;
		for (ArrayList<Item> items: inventory) {
			for(Item item: items) {
				numItems += 1;
			}
		}
		return numItems;
	}
	
}
