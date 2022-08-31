package monsterfighter.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import monsterfighter.ui.gui.Gui;
import monsterfighter.ui.GameEnvironmentUi;

/**
 * Manages the Monster Fighter application.
 */
public class GameEnvironment {

    // The user interface to be used by this manager
	private final GameEnvironmentUi ui;
	
	/**
	 * The maximum number of days that a game can last.
	 */
	public static final int MAX_DAYS = 15;
   
	/**
	 * The minimum number of days that a game can last.
	 */
   	public static final int MIN_DAYS = 5;
	
	// The list of all monsters
	private final List<Monster> allMonsters;
	
	// The list of available starting monsters 
	private final List<Monster> startingMonsters = new ArrayList<Monster>();
	
	// The list of all items
	private final List<Item> allItems;

	// The total number of days the game will last
	private int totalDays;
	
	// The current day
	private int day = 1;
	
	// The game difficulty
	private Difficulty difficulty;

	// Random events that occur upon resting
	private RandomEvent randomEvents;
	
	// Random number generator
	private Random rng = new Random();
	
	// Boolean that signals whether a battle is running
	private boolean battleRunning = false;
	
	// The item or monster that is being handled by the UI
	private Purchasable selectedObject;
	
	// The player running the game
	private Player player;
	
	// The shop that can be accessed
	private Shop shop;
	
	// The battles that can be accessed
	private Battles battles;

	/**
	 * Represents the difficulty of a game.
	 */
    public enum Difficulty {
	    EASY(200, 30, 0,"Easy"),
	    MEDIUM(100, 15, 50, "Medium"),
	    HARD(50, 0, 100, "Hard");

    	/**
    	 * The name of the difficulty.
    	 */
	    public final String name;
	    
	    /**
	     * The gold that is given to the user upon game startup.
	     */
	    public final int startingGold;
	    
	    /**
	     * The bonus gold that is given to the user after each battle.
	     */
	    public final int battleGold;
	    
	    /**
	     * The bonus points that are given to the user after each battle.
	     */
	    public final int battlePoints;

	    Difficulty(int startingGold, int battleGold, int battlePoints, String name){
	        this.startingGold = startingGold;
	        this.battleGold = battleGold;
	        this.battlePoints = battlePoints;
	        this.name = name;
	    }

	    /**
	     * Provides a description of the enum.
	     */
	    @Override
	    public String toString() {
			return name + ": Starting Gold: " + startingGold + " Bonus gold: " 
	    + battleGold + " Bonus points: " + battlePoints;
		}
	}

    /**
     * Constructs the GameEnvironment that will interact with the user interface
     * and call the core classes.
     * 
     * @param ui The user interface that the game will run with
     * @param monsters The {@link Monster}'s that will populate the game
     * @param items The {@link Item}'s that will populate the game
     */
	public GameEnvironment(GameEnvironmentUi ui, List<Monster> monsters, List<Item> items) {
		this.ui = ui;
		this.allMonsters = monsters;
		// Adds and scales three monsters to the starting monsters list
		for (int i = 0; i < Math.min(3, monsters.size()); i++) {
			Monster monster = new Monster(monsters.get(i));
			monster.scaleMonster(1);
			this.startingMonsters.add(monster);
		}
		this.allItems = items;
	}
	

	
	/**
	 * Starts the game. Must be called from the event dispatch thread (EDT) if the user interface is a Swing gui.
	 * This method calls {@link GameEnvironmentUi#setup(GameEnvironment)} to initiate setup of the user interface.
	 * Method adapted from the seng201 RocketManager example project.
	 */
	public void start() {
		ui.setup(this);
	}
	
	/**
	 * This method should be called by the user interface when {@link GameEnvironmentUi#setup(GameEnvironment)}
	 * has been completed. This method calls {@link GameEnvironmentUi#start()} to tell the user interface to start.
	 * Method adapted from the seng201 RocketManager example project.
	 * 
	 * @param name The name of the {@link Player}
	 * @param totalDays The total number of days that the game can run for
	 * @param startingMonster The starting {@link Monster} that the user chose
	 * @param nickname The nickname that the user chose for their starting {@link Monster}
	 * @param difficulty The chosen difficulty for the game
	 */
	public void onSetupFinished(String name, int totalDays, Monster startingMonster, String nickname, Difficulty difficulty) {
		this.player = new Player(name, allItems);
		this.totalDays = totalDays;
		player.addMonsterToParty(startingMonster);
		for (int i = 0; i < 3; i++) {
			player.addItemToInventory(new Item(allItems.get(0)));
		} 
		startingMonster.setNickname(nickname);
		this.difficulty = difficulty;
		player.setGoldBalance(difficulty.startingGold);
		this.shop = new Shop();
		shop.fillShop(day, allItems, allMonsters);
		this.battles = new Battles();
		battles.fillBattles(allItems, allMonsters, difficulty, day, player.getParty().size());
		ui.start();
	}
	
	/**
	 * This method should be called by the {@link GameEnvironmentUi} when the user has requested
	 * to quit the application. This method calls {@link GameEnvironmentUi#quit()} after first confirming the
	 * user really wants to quit.
	 * Method adapted from the seng201 RocketManager example project.
	 */
	public void onFinish() {
		if (ui.confirmQuit()) {
			ui.quit();
		}
	}
	
	/**
	 * Calls the {@link Gui#transitionScreen(String, String)} method if a 
	 * screen transition is requested.
	 * 
	 * @param destination A string representation of the screen that is being transitioned to
	 * @param back A string representation of the screen that is transitioned to by a 
	 * screen's back button
	 */
	public void transitionScreen(String destination, String back) {
		if (ui instanceof Gui) {
			((Gui) ui).transitionScreen(destination, back);
		}
	}
	
	/**
	 * Gets the {@link Player}.
	 * 
	 * @return The {@link Player}
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Gets the {@link Shop}.
	 * 
	 * @return The {@link Shop}
	 */
	public Shop getShop() {
		return shop;
	}
	
	/**
	 * Gets the {@link Battles}.
	 * 
	 * @return The {@link Battles}
	 */
	public Battles getBattles() {
		return battles;
	}
	
	/**
	 * Gets the total number of days that the game may run for.
	 * 
	 * @return The total number of days 
	 */
	public int getTotalDays() {
		return totalDays;
	}
	
	/**
	 * Gets the current day that the game is on.
	 * 
	 * @return The current day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Gets the difficulty chosen by the user.
	 * 
	 * @return The chosen difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Gets the {@link RandomEvent}s that occur after the user rests.
	 * 
	 * @return The {@link RandomEvent}s
	 */
	public RandomEvent getRandomEvent() {
		return randomEvents;
	}
	
	/**
	 * Gets a boolean that signals whether a battle is running.
	 * 
	 * @return True if the user is in a battle and false otherwise
	 */
	public boolean getBattleRunning() {
		return battleRunning;
	}
	
	/**
	 * Gets all the {@link Monster}'s that populate the game environment.
	 * 
	 * @return An unmodifiable list of all {@link Monster}'s
	 */
	public List<Monster> getAllMonsters() {
		return Collections.unmodifiableList(allMonsters);
	}
	
	/**
	 * Gets all the {@link Item}'s that populate the game environment.
	 * 
	 * @return An unmodifiable list of all {@link Item}'s
	 */
	public List<Item> getAllItems() {
		return Collections.unmodifiableList(allItems);
	}
	
	/**
	 * Gets the list of starting {@link Monster}'s that the user can choose from.
	 * 
	 * @return An unmodifiable list of the starting {@link Monster}'s
	 */
	public List<Monster> getStartingMonsters() {
		return Collections.unmodifiableList(startingMonsters);
	}
	
	/**
	 * Creates and returns a copy of the {@link Player}'s inventory that contains lists of size > 0.
	 * Used in the UI to display only existing items.
	 * 
	 * @return An unmodifiable list of lists containing {@link Item}'s 
	 * derived from the {@link Player}'s inventory
	 */
	public List<ArrayList<Item>> getInventoryUI() {
		ArrayList<ArrayList<Item>> inventoryUI = new ArrayList<ArrayList<Item>>();;
		for (ArrayList<Item> item : player.getInventory()) {
			if (!item.isEmpty()) {
				inventoryUI.add(item);
			}
		}
		return Collections.unmodifiableList(inventoryUI);
	}
	
	/**
	 * Gets the item or monster that is being handled by the UI.
	 * 
	 * @return An object of type {@link Purchasable}
	 */
	public Object getSelectedObject() {
		return selectedObject;
	}
	
	/**
	 * Sets the selected object. The Object must extend the {@link Purchasable} interface, 
	 * such as {@link Monster} or {@link Item}.
	 * 
	 * @param selectedObject An object that extends the {@link Purchasable} interface 
	 */
	public void setSelectedObject(Purchasable selectedObject) {
		this.selectedObject = selectedObject;
	}
	
	/**
	 * Switches {@link Monster}'s in the {@link Player}'s party.
	 * 
	 * @param monster The {@link Monster} being switched
	 * @param monsterSwitch The {@link Monster} being switched with.
	 * @throws IllegalStateException if a battle is running and the user tries to switch in a fainted monster
	 */
	public void switchMonsters(Monster monster, Monster monsterSwitch) {
		try {
			if (battleRunning && ((monsterSwitch.isFainted() && monster.equals(player.getLeadingMonster())
					||(monster.isFainted() && monsterSwitch.equals(player.getLeadingMonster()))))) {
				
				throw new IllegalStateException("Cannot switch in a fainted monster. Choose another monster");
			}
			player.switchMonsters(monster, monsterSwitch);
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	/**
	 * Uses a given {@link Item} on a given {@link Monster}.
	 * 
	 * @param monster The {@link Monster} that will an {@link Item} used on it
	 * @param item The {@link Item} that will be used on the {@link Monster} and then
	 * removed from the {@link Player}'s inventory
	 */
	public void useItem(Monster monster, Item item) {
		try {
			item.useItem(monster);
			player.removeItemFromInventory(item);
		} catch(IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	/**
	 * Sells an item from the {@link Player}'s inventory.
	 * 
	 * @param item The item to be sold
	 */
	public void sellItem(Item item) {
		player.setGoldBalance(item.getSellPrice());
		player.removeItemFromInventory(item);
	}
	
	/**
	 * Sells a monster from the {@link Player}'s party
	 * 
	 * @param monster The monster to be sold
	 */
	public void sellMonster(Monster monster) {
		player.setGoldBalance(monster.getSellPrice());
		player.removeMonsterFromParty(monster);
	}
	
	/**
	 * Purchases a {@link Monster} or {@link Item} from the shop.
	 * 
	 * @param object The monster or item that is to be purchased.
	 */
	public void purchase(Purchasable object) {
		try {
			player.setGoldBalance(-object.getBuyPrice());
			if (object instanceof Monster) {
				player.addMonsterToParty((Monster) object);
			} else {
				player.addItemToInventory((Item) object);
			}
			shop.removeObject(object);
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	/**
	 * Goes to the next day, fully heals the players monsters, refreshes the 
	 * available battles, refills the shop and handles any random events that may occur
	 */
	public void nextDay() {
		day += 1;
		shop.fillShop(day, allItems, allMonsters);
		battles.fillBattles(allItems, allMonsters, difficulty, day, player.getParty().size());
		randomEvents = new RandomEvent(player.getParty());
		for (int i = 0; i < player.getParty().size(); i++) {
			if (randomEvents.getLevelUp().get(i) && !randomEvents.getMonsterLeaves().get(i)) {
				player.getParty().get(i).levelUp();
			} 
		} for (int i = 0; i < player.getParty().size(); i++) {
			if (randomEvents.getMonsterLeaves().get(i)) {
				player.removeMonsterFromParty(player.getParty().get(i));
			}
		}
		if (randomEvents.getMonsterJoins()) {
			int randomNumber = rng.nextInt(allMonsters.size());
			Monster monster = allMonsters.get(randomNumber);
			monster.scaleMonster(day-1);
			player.addMonsterToParty(monster);
		}
		for (Monster monster : player.getParty()) {
			monster.revive(monster.getMaxHealth());
			monster.setFaintedToday(false);
			monster.resetWins();
		}
	}
	
	/**
	 * Attempts to start a battle if the player's party has any monsters 
	 * that are conscious. Throws an error otherwise.
	 * 
	 * @throws IllegalStateException if there are no monsters in the players party that can fight
	 */
	public void startBattle() {
		try {
			if (player.getParty().size() == 0 || player.partyFainted()) {
				throw new IllegalStateException("No available monsters to battle");
			}
			int i = 0;
			for (Monster monster: player.getParty()) {
				if (!monster.isFainted()) {
					player.switchMonsters(player.getLeadingMonster(),player.getParty().get(i));
					break;
				} i++;
			}
			battleRunning = true;
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	/**
	 * Manages the battle after the player has their turn.
	 * 
	 * @param opponent The battle that the player is fighting
	 */
	public void manageBattle(Battle opponent) {
		opponentTurn(opponent);
		battleRunning = opponent.getConsciousMonsters() > 0 && !player.partyFainted();
		if (!battleRunning) {
			rewards(opponent);
			if (opponent instanceof WildBattle) {
				battles.removeWildBattle(opponent);
			} else if (opponent instanceof TrainerBattle){
				battles.removeTrainerBattle(opponent);
			}
		}
	}
	
	/**
	 * Decides what the opponent's next move is and manages it.
	 * 
	 * @param opponent The battle that the player is fighting
	 */
	public void opponentTurn(Battle opponent) {
		if (opponent.getMonsters().get(0).isFainted() && opponent.getConsciousMonsters() > 0) {
			opponent.nextMonster();
		} else if (!opponent.getMonsters().get(0).isFainted()) {
			opponent.getMonsters().get(0).attack(player.getLeadingMonster());
		}
		
	}
	
	/**
	 * Gives the player their rewards from the battle
	 * 
	 * @param opponent The battle that the player fought against
	 */
	public void rewards(Battle opponent) {
		if (opponent.getConsciousMonsters() == 0) {
			player.increasePoints(opponent.getPoints());
			if (opponent instanceof WildBattle) {
				WildBattle wildOpponent = (WildBattle) opponent;
				Item reward = wildOpponent.getReward();
				player.addItemToInventory(reward);
			} else if (opponent instanceof TrainerBattle) {
				TrainerBattle trainerOpponent = (TrainerBattle) opponent;
				player.setGoldBalance(trainerOpponent.getGold());
			}
			for (Monster monster: player.getParty()) {
				monster.addWin(1);
			}
		}
	}
	
}

	



