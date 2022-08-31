package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that models a shop
 */
public class Shop {

	// The shops inventory
	private ArrayList<ArrayList<Purchasable>> shopInventory;
	
	// Random number generator
	private Random rng = new Random();
	
	/**
	 * Constructs an empty shop.
	 */
	public Shop() {
		this.shopInventory = new ArrayList<ArrayList<Purchasable>>();
	}
	
	/**
	 * Fills the shop with {@link Purchasable} objects, with the amount of each object
	 * varying based on the given day.
	 * 
	 * @param day The current day the game is on
	 * @param items All the items that populate the game
	 * @param monsters All the monsters that populate the game
	 */
	public void fillShop(int day, List<Item> items, List<Monster> monsters) {
		ArrayList<Integer> monsterIndices = new ArrayList<Integer>();
		for (int i = 0; i < monsters.size(); i++) {
			monsterIndices.add(i);
		}
		if (shopInventory.size() > 0) {
			shopInventory.clear();
		}
		for (int i = 0; i < items.size() + Math.min(3, monsterIndices.size()); i++) {
			shopInventory.add(new ArrayList<Purchasable>());
			// Adds items to shop
			if (i < items.size()) {
				// Fills each item array with the appropriate quantity of items
				for (int j = 0; j < ((items.get(i).getShopQuantity()) * ((int) Math.ceil((double)day / 5))) ; j++) {
					shopInventory.get(i).add(new Item(items.get(i)));
				}
			// Adds Monsters to shop
			} else {
				int randomNumber = rng.nextInt(monsterIndices.size());
				Monster monster = new Monster(monsters.get(monsterIndices.get(randomNumber)));
				monster.scaleMonster(day - 1);
				shopInventory.get(i).add(monster);
				monsterIndices.remove(randomNumber);
			}
		}
	}
	
	/**
	 * Gets the shop inventory.
	 * 
	 * @return The list of purchasable objects in the shop
	 */
	public ArrayList<ArrayList<Purchasable>> getShopInventory() {
		return shopInventory;
	}
	
	/**
	 * Checks to see if the shop is empty.
	 * 
	 * @return Boolean that is true if the shop is empty and false otherwise
	 */
	public boolean shopIsEmpty() {
		boolean isEmpty = true;
		for (ArrayList<Purchasable> purchasableObject : shopInventory) {
			if (!purchasableObject.isEmpty()) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	/**
	 * Removes the given object from the shop.
	 * 
	 * @param object The object to be removed from the shop
	 */
	public void removeObject(Purchasable object) {
		for (int i = 0; i < shopInventory.size(); i++) {
			if (object.getClass().equals(shopInventory.get(i).get(0).getClass()) && object.getIndex() == shopInventory.get(i).get(0).getIndex()) {
				shopInventory.get(i).remove(0);
				if (shopInventory.get(i).size()==0) {
					shopInventory.remove(i);
				}
				break;
			}
		}
	}
	
}
