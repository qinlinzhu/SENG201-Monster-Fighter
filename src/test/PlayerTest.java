package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;

public class PlayerTest {
	String name = "pep";
	
	
	int gold = 200;

	ArrayList<Item> items = new ArrayList<>();
	
	

	Player player = new Player(name, items);
	Player player1 = new Player(name, items);

	
	
	public PlayerTest() {
		
	}
	
	//player name and inventory test
	@Test
	public void testPlayer() {
		
		Assertions.assertEquals("pep", player.getName());
		Assertions.assertEquals(0, player.getInventory().size());
	}
	@Test
	public void testAddMonsterToParty() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster3 = new Monster(3, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster4 = new Monster(4, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.addMonsterToParty(monster2);
		player.addMonsterToParty(monster3);
		
		Assertions.assertThrows(IllegalStateException.class, () -> player.addMonsterToParty(monster4));
	}
	@Test
	public void testRemoveMonsterFromParty() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster3 = new Monster(3, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.addMonsterToParty(monster2);
		player.addMonsterToParty(monster3);
		player.removeMonsterFromParty(monster2);
		player.removeMonsterFromParty(monster3);
		
		Assertions.assertEquals(2, player.getParty().size());
	}
	
	@Test
	public void testGetLeadingMonster() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fir", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fire", Monster.Type.FIRE, 50, 20, 200);
		Monster monster3 = new Monster(3, "Firebo", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.addMonsterToParty(monster2);
		player.addMonsterToParty(monster3);
		
		Assertions.assertEquals(monster, player.getLeadingMonster());
	}
	
	@Test
	public void testSetGoldBalance() {
		
	
		player.setGoldBalance(gold);
		player.increaseTotalGold(gold);
		player.increaseTotalGold(gold);
		Assertions.assertEquals(200, player.getGoldBalance());
		Assertions.assertEquals(600, player.getTotalGold());
		Assertions.assertThrows(IllegalStateException.class, () -> player1.setGoldBalance(-200));
		
	}

	@Test
	public void inventoryIsEmpty() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		items.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		items.add(new Item(2, "Huge Potion", 120, Item.Stat.CURRENTHEALTH, 80, 1));
		items.add(new Item(3, "Attack Snack", 5, Item.Stat.ATTACK, 50, 1));
		items.add(new Item(4, "Max Health Snack", 10, Item.Stat.MAXHEALTH, 50, 1));
		items.add(new Item(5, "Revive candy", 40, Item.Stat.STATUS, 60, 1));
		Player player = new Player(name, items);
		player.addItemToInventory(items.get(0));
		Assertions.assertEquals(false, player.inventoryIsEmpty());
	}
	
	
		
	@Test
	public void testSwitchMonsters() {
	
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.switchMonsters(monster, monster1);
		
	
		player1.addMonsterToParty(monster1);
		

		
		
		Assertions.assertEquals(monster1, player.getParty().get(0));
		Assertions.assertEquals(monster, player.getParty().get(1));
	
		Assertions.assertThrows(IllegalStateException.class, () -> player1.switchMonsters(monster, monster));
	}
	
	@Test
	public void testPartyFainted() {

		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
	
		player.addMonsterToParty(monster1);
		player.partyFainted();
		
	
	
		player1.addMonsterToParty(monster2);
		
		int damage = 60;
		monster2.receiveDamage(damage);
		

		
		Assertions.assertEquals(false, player.partyFainted());
		Assertions.assertEquals(true, player1.partyFainted());
		
	}
	
	@Test
	public void testGetConsciousMonsters() {
		
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster1);
		player1.addMonsterToParty(monster2);
		int damage = 60;
		monster2.receiveDamage(damage);
		
	
		player.getConsciousMonsters();
		player1.getConsciousMonsters();
		Assertions.assertEquals(1, player.getConsciousMonsters());
		Assertions.assertEquals(0, player1.getConsciousMonsters());
	}
	
	@Test
	public void testInventoryNumItems() {
		
		ArrayList<Item> items1 = new ArrayList<>();
		items1.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		items1.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		
		
		
		player.inventoryNumItems();
		
		Player player1 = new Player(name, items1);
		Item item = new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3);
		Item item1 = new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3);
		Item item2 = new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3);
		player1.addItemToInventory(item);
		player1.addItemToInventory(item1);
		player1.addItemToInventory(item2);
		player1.removeItemFromInventory(item2);
		
		
		
		
		player1.inventoryNumItems();
		Assertions.assertEquals(0, player.inventoryNumItems());
		Assertions.assertEquals(2, player1.inventoryNumItems());
	}
	
	
	
			
	
	
	
	

}
