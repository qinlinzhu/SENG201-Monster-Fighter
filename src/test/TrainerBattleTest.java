package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.TrainerBattle;
import monsterfighter.core.WildBattle;

class TrainerBattleTest {


	@Test
	public void testTrainerBattle() {
		
		
		ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		

		ArrayList<Monster> monster1 = new ArrayList<>();
		monster1.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		TrainerBattle trainerBattles = new TrainerBattle(5, 2, monster, "goofy");
		TrainerBattle trainerBattles1 = new TrainerBattle(5, 2, monster1, "bobby");
	
	
		Assertions.assertNotEquals(trainerBattles, trainerBattles1);
	}
	
	
	@Test
	public void testBattleStatus() {
		ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		

		ArrayList<Monster> monster1 = new ArrayList<>();
		monster1.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		TrainerBattle trainerBattles = new TrainerBattle(5, 2, monster, "goofy");
		String description = "Trainer: goofy 1x M\r\nFireboy 40/40 ";
		Assertions.assertNotEquals(description, trainerBattles.battleStatus());
	}
	


	@Test
	public void testToString() {
		ArrayList<Monster> monster = new ArrayList<>();
		monster.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		
		ArrayList<Monster> monster1 = new ArrayList<>();
		monster1.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		
		TrainerBattle trainerBattles = new TrainerBattle(5, 2, monster, "goofy");
		String description = "Trainer: goofy 1x Monsters Points: 2 Gold: 5";
		Assertions.assertEquals(description, trainerBattles.toString());
	}

}
