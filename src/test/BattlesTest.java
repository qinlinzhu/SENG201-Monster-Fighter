package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Battle;
import monsterfighter.core.Battles;
import monsterfighter.core.GameEnvironment;
import monsterfighter.core.GameEnvironment.Difficulty;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.core.WildBattle;

class BattlesTest {
	




	@Test
	public void testFillBattles() {
		Battles wildBattles = new Battles();
		Battles trainerBattles = new Battles();
		
		
		Battles battle = trainerBattles;
		
		List<Item> allItems = new ArrayList<>();
		allItems.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		allItems.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		
		List<Monster> allMonsters = new ArrayList<>();
		allMonsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		allMonsters.add(new Monster(1, "Watergirl", Monster.Type.WATER, 55, 15, 200));
		
		
		Battles battle1 = wildBattles;
		
		List<Item> allItems1 = new ArrayList<>();
		allItems1.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		allItems1.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		
		List<Monster> allMonsters1 = new ArrayList<>();
		allMonsters1.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		allMonsters1.add(new Monster(1, "Watergirl", Monster.Type.WATER, 55, 15, 200));
		
		battle1.fillBattles(allItems1, allMonsters1, Difficulty.EASY, 1, 2);
		battle.fillBattles(allItems1, allMonsters1, Difficulty.EASY, 1, 2);
		
		wildBattles.fillBattles(allItems1, allMonsters1, Difficulty.EASY, 1, 2);
		trainerBattles.fillBattles(allItems1, allMonsters1, Difficulty.EASY, 1, 2);
		
		Assertions.assertEquals(battle1, wildBattles);
		Assertions.assertEquals(battle, trainerBattles);
	}
		
		
		
	
	

	@Test
	public void testCalculatePoints() {
		Battles wildBattles = new Battles();
		Battles trainerBattles = new Battles();
		String battleType = "Wild"; 
		int partySize = 2;
		Battles battle = wildBattles;
		
		
		String battleType1 = "Trainer"; 
		int partySize1 = 2;
		Battles battle1 = trainerBattles;
		
		Assertions.assertEquals(100, battle.calculatePoints(battleType, partySize, Difficulty.EASY));
		Assertions.assertEquals(200, battle1.calculatePoints(battleType1, partySize1, Difficulty.EASY));
	
	}
	
	@Test
	public void testCalculateGold() {
		Battles wildBattles = new Battles();
		Battles battle = wildBattles;
		int partySize = 2;
		
		Assertions.assertEquals(250, battle.calculateGold(partySize, Difficulty.EASY));
	
		
	}
	
		
}


