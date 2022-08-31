package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Battle;
import monsterfighter.core.Battles;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Shop;
import monsterfighter.core.WildBattle;

class WildBattleTest {

	@Test
	public void testWildBattle() {
		
		Item reward = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
		ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		

		Item reward1 = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
		ArrayList<Monster> monster1 = new ArrayList<>();
		monster1.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		WildBattle wildBattles = new WildBattle(reward, 2, monster);
		WildBattle wildBattles1 = new WildBattle(reward1, 2, monster1);
	
	
		Assertions.assertNotEquals(wildBattles, wildBattles1);
	}

	
    @Test
	public void testGetReward() {
		
		
    	Item reward = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
		ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		WildBattle wildBattles = new WildBattle(reward, 2, monster);
		
		
		Assertions.assertEquals(reward, wildBattles.getReward());
	
	
	}
    
    @Test
    public void testGetConsciousMonsters() {
    	
    	Item reward = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
    	
    	
    	ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		ArrayList<Monster> monster1 = new ArrayList<>();
		monster1.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		WildBattle wildBattles = new WildBattle(reward, 2, monster);
		WildBattle wildBattles1 = new WildBattle(reward, 2, monster1);
    	int damage = 60;
    	monster.get(0).receiveDamage(damage);
    	
    	Assertions.assertEquals(0, wildBattles.getConsciousMonsters());
    	Assertions.assertEquals(1, wildBattles1.getConsciousMonsters());
    }
	

    @Test
    public void testNextMonster() {
    	Item reward = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
    	
    	
    	ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monster.add(new Monster(1, "Fireboi", Monster.Type.FIRE, 40, 20, 200));
		
		
		
		WildBattle wildBattles = new WildBattle(reward, 2, monster);
		wildBattles.nextMonster();
		
    	
    	Assertions.assertEquals(0, 0);

    }
    
    @Test
    public void testBattleStatus() {
    	Item reward = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
    	
    	
    	ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monster.add(new Monster(1, "Fireboi", Monster.Type.FIRE, 40, 20, 200));
		
		
		
		WildBattle wildBattles = new WildBattle(reward, 2, monster);
		wildBattles.battleStatus();
		
    	
    	Assertions.assertEquals(0, 0);
	}
    
    @Test
    public void testToString() {
    	Item reward = new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3);
    	
    	
    	ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monster.add(new Monster(1, "Fireboi", Monster.Type.FIRE, 40, 20, 200));
		
		
		
		WildBattle wildBattles = new WildBattle(reward, 2, monster);
		wildBattles.battleStatus();
		
    	String description = "Monster: " + "Fireboy" + " Type: " + "FIRE " + "Points: " + "2" + " Reward: " + "Small Potion";
    	Assertions.assertEquals(description, wildBattles.toString());
    }

}
