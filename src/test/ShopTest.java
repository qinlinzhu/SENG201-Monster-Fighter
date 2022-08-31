package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Purchasable;
import monsterfighter.core.Shop;


public class ShopTest {
	
	
	
	@Test
	public void testFillShop() {
		int day = 1;
		List<Item> items = new ArrayList<>(5);
		
		items.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		items.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		items.add(new Item(2, "Huge Potion", 120, Item.Stat.CURRENTHEALTH, 80, 1));
		items.add(new Item(3, "Attack Snack", 5, Item.Stat.ATTACK, 50, 1));
		items.add(new Item(4, "Max Health Snack", 10, Item.Stat.MAXHEALTH, 50, 1));
		items.add(new Item(5, "Revive candy", 40, Item.Stat.STATUS, 60, 1));
		
		List<Monster> monsters = new ArrayList<>(6);
		
		monsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monsters.add(new Monster(1, "Watergirl", Monster.Type.WATER, 55, 15, 200));
		monsters.add(new Monster(2, "Dirt", Monster.Type.GRASS, 80, 10, 200));
		monsters.add(new Monster(3, "BrightStar", Monster.Type.LIGHT, 60, 12, 200));
		monsters.add(new Monster(4, "DarkStar", Monster.Type.DARK, 30, 24, 200));
		monsters.add(new Monster(5, "Normie", Monster.Type.NORMAL, 120, 6, 200));
		
		Shop shop = new Shop();
		shop.fillShop(day, items, monsters);
		
		Assertions.assertEquals(9, shop.getShopInventory().size());

	}
	
	@Test
	public void TestShopIsEmpty() {
		int day = 1;
		List<Item> items = new ArrayList<>(5);
		
		items.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		items.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		items.add(new Item(2, "Huge Potion", 120, Item.Stat.CURRENTHEALTH, 80, 1));
		items.add(new Item(3, "Attack Snack", 5, Item.Stat.ATTACK, 50, 1));
		items.add(new Item(4, "Max Health Snack", 10, Item.Stat.MAXHEALTH, 50, 1));
		items.add(new Item(5, "Revive candy", 40, Item.Stat.STATUS, 60, 1));
		
		List<Monster> monsters = new ArrayList<>(6);
		
		monsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monsters.add(new Monster(1, "Watergirl", Monster.Type.WATER, 55, 15, 200));
		monsters.add(new Monster(2, "Dirt", Monster.Type.GRASS, 80, 10, 200));
		monsters.add(new Monster(3, "BrightStar", Monster.Type.LIGHT, 60, 12, 200));
		monsters.add(new Monster(4, "DarkStar", Monster.Type.DARK, 30, 24, 200));
		monsters.add(new Monster(5, "Normie", Monster.Type.NORMAL, 120, 6, 200));
		
		Shop shop = new Shop();
		shop.fillShop(day, items, monsters);
		
		Assertions.assertEquals(false, shop.shopIsEmpty());
	
	}
	
	@Test
	public void TestRemoveObject() {
		
		int day = 1;
		List<Item> items = new ArrayList<>(5);
		
		items.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		items.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		items.add(new Item(2, "Huge Potion", 120, Item.Stat.CURRENTHEALTH, 80, 1));
		items.add(new Item(3, "Attack Snack", 5, Item.Stat.ATTACK, 50, 1));
		items.add(new Item(4, "Max Health Snack", 10, Item.Stat.MAXHEALTH, 50, 1));
		items.add(new Item(5, "Revive candy", 40, Item.Stat.STATUS, 60, 1));
		
		List<Monster> monsters = new ArrayList<>(6);
		
		monsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monsters.add(new Monster(1, "Watergirl", Monster.Type.WATER, 55, 15, 200));
		monsters.add(new Monster(2, "Dirt", Monster.Type.GRASS, 80, 10, 200));
		monsters.add(new Monster(3, "BrightStar", Monster.Type.LIGHT, 60, 12, 200));
		monsters.add(new Monster(4, "DarkStar", Monster.Type.DARK, 30, 24, 200));
		monsters.add(new Monster(5, "Normie", Monster.Type.NORMAL, 120, 6, 200));
		
		Shop shop = new Shop();
		shop.fillShop(day, items, monsters);

		shop.removeObject(items.get(0));
		
		Assertions.assertEquals(9, shop.getShopInventory().size());
		
	}

}

