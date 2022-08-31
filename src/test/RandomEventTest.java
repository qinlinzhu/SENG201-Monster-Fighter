package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Monster;
import monsterfighter.core.Monster.Status;
import monsterfighter.core.RandomEvent;

class RandomEventTest {
	

	

	
	void testRandomEvent() {

	
	}
	@Test
	public void testMonsterLevelUp() {
		
		ArrayList<Boolean> testList = new ArrayList<>();
		testList.add(false);

		
		
		ArrayList<Boolean> testList1 = new ArrayList<>();
		testList.add(true);

		
		ArrayList<Monster> party = new ArrayList<>();
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		party.add(monster);
		
		
		ArrayList<Monster> party1 = new ArrayList<>();
		Monster monster1 = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		
		party1.add(monster1);
		
		int damage = 60;
		monster1.receiveDamage(damage);

		
		RandomEvent randomEvent = new RandomEvent(party);
		randomEvent.monsterLevelUp(party);
		
		RandomEvent randomEvent1 = new RandomEvent(party1);
		randomEvent1.monsterLevelUp(party1);
		
		if (randomEvent.getLevelUp() == testList ) {
			Assertions.assertTrue(true);
		} else if (randomEvent.getLevelUp() == testList1) {
			Assertions.assertTrue(true);
		} else if (randomEvent1.getLevelUp() == testList) {
			Assertions.assertTrue(true);
		} else if (randomEvent1.getLevelUp() == testList1) {
			Assertions.assertTrue(true);
		}
	

	}
	
	@Test
	public void testMonsterLeaves() {
		
		ArrayList<Boolean> testList = new ArrayList<>();
		testList.add(false);
		
		ArrayList<Boolean> testList1 = new ArrayList<>();
		testList.add(true);
	
		ArrayList<Monster> party = new ArrayList<>();
		
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		party.add(monster);
		
		int damage = 60;
		monster.receiveDamage(damage);
		
		
		
		ArrayList<Monster> party1 = new ArrayList<>();
		Monster monster1 = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		party1.add(monster1);
		

		RandomEvent randomEvent = new RandomEvent(party);
		randomEvent.monsterLeaves(party);
		
		RandomEvent randomEvent1 = new RandomEvent(party1);
		randomEvent1.monsterLevelUp(party1);
	
		if (randomEvent.getLevelUp() == testList ) {
			Assertions.assertTrue(true);
		} else if (randomEvent.getMonsterLeaves() == testList1) {
			Assertions.assertTrue(true);
		} else if (randomEvent1.getMonsterLeaves() == testList) {
			Assertions.assertTrue(true);
		} else if (randomEvent1.getMonsterLeaves() == testList1) {
			Assertions.assertTrue(true);
		}
	
		
	}
	
	@Test
	public void testMonsterJoins() {
		
		ArrayList<Monster> party = new ArrayList<>();
		
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);

		party.add(monster);
	
		
		RandomEvent randomEvent = new RandomEvent(party);
		randomEvent.monsterJoins(party);
		
		Assertions.assertEquals(false, randomEvent.getMonsterJoins());
		
	}
}
