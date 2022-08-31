package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.core.Item.Stat;

class ItemTest {
	
	
	
	
	
	@Test
	public void testItem() {
		Item item = new Item(0, "meth", 2, null, 10, 2);
		
		Assertions.assertEquals("meth", item.getName());
		Assertions.assertEquals(2, item.getAmount());
	}
	
	@Test
	public void testUseItem() {
	
		Item item = new Item(0, "meth", 2, Stat.MAXHEALTH, 10, 2);
		Item item1 = new Item(1, "meth", 2, Stat.ATTACK, 10, 2);
		Item item2 = new Item(2, "meth", 2, Stat.CURRENTHEALTH, 10, 2);
		Item item3 = new Item(3, "meth", 2, Stat.STATUS, 10, 2);
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200);
		Monster monster1 = new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200);
		Monster monster2 = new Monster(0, "Fireboy", Monster.Type.FIRE, 70, 20, 200);
		Monster monster3 = new Monster(0, "Fireboy", Monster.Type.FIRE, 10, 20, 200);
		
		int damage = 60;
		monster1.receiveDamage(damage);
		monster2.receiveDamage(damage);
		monster3.receiveDamage(damage);
		
		item.useItem(monster);
		item3.useItem(monster1);
		item2.useItem(monster2);
		item1.useItem(monster);
		
		
		Assertions.assertEquals(2, item.getAmount());
		Assertions.assertEquals(42, monster.getCurrentHealth());
		Assertions.assertEquals(2, monster1.getCurrentHealth());
		Assertions.assertEquals(12, monster2.getCurrentHealth());
		Assertions.assertEquals(Stat.MAXHEALTH, item.getStat());
		Assertions.assertEquals(2, item1.getAmount());
		Assertions.assertThrows(IllegalStateException.class, () -> item2.useItem(monster));
		Assertions.assertThrows(IllegalStateException.class, () -> item3.useItem(monster));
		Assertions.assertThrows(IllegalStateException.class, () -> item2.useItem(monster3));
		
	}
	
	@Test
	public void testToString() {
		Item item2 = new Item(2, "meth", 2, Stat.CURRENTHEALTH, 10, 2);
		String description =  "Item: " + "meth" + " Effect: increases " + "Current Health" + " by " + "2";
		Assertions.assertEquals(description, item2.toString());
	}
	
	@Test
	public void testBuyDescription() {
		Item item2 = new Item(2, "meth", 2, Stat.CURRENTHEALTH, 10, 2);
		String description = "[Buy Price: " + "10" + "] " + "Item: " + "meth" + " Effect: increases " + "Current Health" + " by " + "2";
		Assertions.assertEquals(description, item2.buyDescription());
	}

	@Test
	public void testSellDescription() {
		Item item2 = new Item(2, "meth", 2, Stat.CURRENTHEALTH, 10, 2);
		String description = "[Sell Price: " + "5" + "] " + "Item: " + "meth" + " Effect: increases " + "Current Health" + " by " + "2";
		Assertions.assertEquals(description, item2.sellDescription());
		
	}
	


}
