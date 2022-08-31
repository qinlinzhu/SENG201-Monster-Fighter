package monsterfighter.core;

import java.awt.Color;
import java.util.Random;

/**
 * Class that models a monster
 */
public class Monster implements Purchasable{
	
	/**
	 * Represents the state of a {@link Monster}.
	 */
	public enum Status {
		CONSCIOUS("Conscious"),
		FAINTED("Fainted");
		
		/**
		 * A description of the value of this enum.
		 */
		public final String name;
		
		Status(String name) {
			this.name = name;
		}
	}
	
	/**
	 * Represents the type of a {@link Monster}.
	 */
	public enum Type {
		NORMAL("NORMAL", Color.GRAY),
	    FIRE("FIRE", Color.RED),
	    WATER("WATER", Color.BLUE),
	    GRASS("GRASS", new Color(34,139,34)),
	    LIGHT("LIGHT", new Color(255, 215, 0)),
	    DARK("DARK", Color.BLACK);

		/**
		 * A description of the value of this enum.
		 */
	    public final String value;
	    
	    /**
	     * The corresponding colour assigned to this enum.
	     */
	    public final Color colour;

	    Type(String value, Color colour) {
	        this.value = value;
	        this.colour = colour;
	    }
	}
	
	// Index number denoting the monster
	private final int index;
	
	// Monster name
	private final String name;
	
	// Monster nickname given by the user
	private String nickname;
	
	// The monsters type
	private final Type type;
	
	// The total health of the monster
	private int maxHealth;
	
	// The attack power of the monster
	private int attack;
	
	// The current health of the monster
	private int currentHealth;
	
	// The state of the monster
	private Status status;
	
	// Buy price of the monster from a shop
	private final int buyPrice;
	
	// The sell price of the monster
	private final int sellPrice;
	
	// Boolean that is true whether the monster has fainted in a day
	private boolean faintedToday = false;
	
	// Number of wins that the monster has had that day
	private int wins = 0;
	private Random rng = new Random();
	
	/**
	 * Creates a monster with the given parameters.
	 * 
	 * @param index Index number denoting the monster
	 * @param name Name of the monster
	 * @param type The monsters type
	 * @param maxHealth The total health of the monster
	 * @param attack The attack power of the monster
	 * @param buyPrice The buy price of the monster from a shop
	 */
	public Monster(int index, String name, Type type, int maxHealth, int attack, int buyPrice) {
		this.index = index;
		this.name = name;
		// Default nickname is the same as the monsters name
		this.nickname = name;
		this.type = type;
		this.maxHealth = maxHealth;
		this.attack = attack;
		this.currentHealth = maxHealth;
		this.status = Status.CONSCIOUS;
		this.buyPrice = buyPrice;
		sellPrice = buyPrice / 2;
	}
	
	/**
	 * Creates a deep copy of a Monster.
	 * 
	 * @param c Monster that is being copied
	 */
	public Monster(Monster c) {
		this.index = c.index;
		this.name = c.name;
		this.nickname = c.name;
		this.type = c.type;
		this.maxHealth = c.maxHealth;
		this.attack = c.attack;
		this.currentHealth = c.maxHealth;
		this.status = c.status;
		this.buyPrice = c.buyPrice;
		sellPrice = buyPrice / 2;
	}
	
	@Override
	public int getIndex() {
		return index;
	}
	
	/**
	 * Gets the name of the monster.
	 * 
	 * @return The name of the monster
	 */
	public String getName() {
		return name;
	}
	 /**
	  * Gets the monster's nickname.
	  * 
	  * @return The monster's nickname
	  */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Gets the type of the monster.
	 * 
	 * @return The type of the monster
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Gets the max health of the monster.
	 * 
	 * @return The max health of the monster
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Gets the attack of the monster.
	 * 
	 * @return The attack of the monster
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Gets the current health of the monster.
	 * 
	 * @return the current health of the monster
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	/**
	 * Gets the state of the monster.
	 * 
	 * @return the state of the monster
	 */
	public Status getStatus() {
		return status;
	}
	
	@Override
	public int getBuyPrice() {
		return buyPrice;
	}
	
	@Override
	public int getSellPrice() {
		return sellPrice;
	}
	
	/**
	 * Returns whether or not the monster has fainted in the current day.
	 * 
	 * @return whether the monster has fainted in the day
	 */
	public boolean getFaintedToday() {
		return faintedToday;
	}
	
	/**
	 * Gets the number of {@link Battle}'s the monster has won
	 * over the course of the current day.
	 * 
	 * @return the number of battles the monster has won in the current day
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Sets the whether or not the monster has fainted in the current day.
	 * 
	 * @param fainted Boolean that will be true if the monster has 
	 * fainted and false otherwise
	 */
	public void setFaintedToday(boolean fainted) {
		this.faintedToday = fainted;
	}
	
	/**
	 * Resets the monsters daily win counter to zero.
	 */
	public void resetWins() {
		wins = 0;
	}
	
	/**
	 * Adds wins onto the monsters daily win counter.
	 * 
	 * @param win The number of wins to add onto the win counter
	 */
	public void addWin(int win) {
		wins += win;
	}

	
	/**
	 * Sets the monsters nickname.
	 * 
	 * @param nickname The nickname that the monster will have
	 */
	public void setNickname(String nickname) {
		if (nickname.length() > 0) {
			this.nickname = nickname;
		}
	}
	
	/**
	 * Returns true if the monster is {@link Status#FAINTED} and false otherwise.
	 * 
	 * @return The status of the monster
	 */
	public boolean isFainted() {
		return status.equals(Status.FAINTED);
	}
	
	/**
	 * Sets the monster's max health based on the current max health plus the 
	 * additional health amount.
	 * 
	 * @param healthBuff The additional health to be added onto the 
	 * monsters current max health
	 */
	public void setMaxHealth(int healthBuff) {
		maxHealth += healthBuff;
		if (!isFainted()) {
			receiveHealth(healthBuff);
		}
	}
	
	/**
	 * Sets the monster's attack based on the current attack plus the 
	 * additional attack value.
	 * 
	 * @param attackBuff The additional attack to be added onto the 
	 * monsters current attack
	 */
	public void setAttack(int attackBuff) {
		attack += attackBuff;
	}

	/**
	 * Heals the monster by increasing the current health by the given amount.
	 * 
	 * @param heal The amount of health that is added to the 
	 * mosnter's current health
	 */
	public void receiveHealth(int heal) {
		currentHealth += heal;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	
	/**
	 * Decreases the monsters current health by the given amount.
	 * 
	 * @param damage The amount of health that is taken 
	 * off the monster's current health
	 */
	public void receiveDamage(int damage) {
		currentHealth -= damage;
		if (currentHealth <= 0) {
			currentHealth = 0;
			status = Status.FAINTED;
			faintedToday = true;
		}
	}
	
	/**
	 * Sets the monster's status to {@link Status#CONSCIOUS} and heals the monster by 
	 * a given amount
	 * 
	 * @param heal The amount of health to be added onto the monster's 
	 * current health
	 */
	public void revive(int heal) {
		status = Status.CONSCIOUS;
		receiveHealth(heal);
	}
	
	/**
	 * The method by which a monster attacks another monster.
	 * 
	 * @param enemyMonster The {@link Monster} that the monster attacks
	 */
	public void attack(Monster enemyMonster) {
		enemyMonster.receiveDamage(attack);
	}
	
	/**
	 * Increases the monsters stats by calling the levelUp() 
	 * function as many times as the scalar's value.
	 * 
	 * @param scalar The amount of times the levelUp() function should be called
	 */
	public void scaleMonster(int scalar) {
		for (int i = 0; i < scalar; i++) {
			levelUp();
		}
	}
	
	/**
	 * Increases either the monsters max health or attack by a restricted 
	 * random amount.
	 */
	public void levelUp() {
		int stat = rng.nextInt(2);
		if (stat == 0) {
			int healthIncrease = rng.nextInt(5, 11);
			setMaxHealth(healthIncrease);
			receiveHealth(healthIncrease);
		} else {
			int attackIncrease = rng.nextInt(1, 6);
			setAttack(attackIncrease);
		}
	}
	
	/**
	 * Text displayed for a monster, while in a {@link Battle}.
	 * 
	 * @return Description of the monster, including name, current health and max health
	 */
	public String battleDescription() {
		String description = name + " " + currentHealth + "/" + maxHealth;
		return description;
	}
	
	/**
	 * Generic description of a monster
	 * 
	 * @return Description of a monster, including name, type, max health and attack
	 */
	public String basicDescription() {
		String description = "Monster: " + name + " Type: " + type.value + " Health: " + maxHealth + " Attack: " + attack;
		return description;
	}
	
	/**
	 * The description of the monster for a Tooltip GUI implementation
	 * 
	 * @return Description of a monster formatted in html, including nickname, 
	 * name, type, max health, current health and attack
	 */
	public String tooltipText() {
		String text = "<html>Nickname: " + nickname + "<br>Monster: " + name + "<br>Type: " + type.value + "<br>Health: " + currentHealth + "/" + maxHealth + "<br>Attack: " + attack + "</html>";
		if (status.name == "Fainted") {
			text = "<html>[FAINTED]<br>" + text.substring(6);
		}
		return text;
	}
	
	@Override
	public String buyDescription() {
		String description = "[Buy Price: " + buyPrice + "] " + basicDescription();
		return description;
	}

	@Override
	public String sellDescription() {
		String description = "[Sell Price: " + sellPrice + "] " + basicDescription();
		return description;
	}
	
	/**
	 * A full description of the monster.
	 * 
	 * @return Description of a monster, including nickname, 
	 * name, type, max health, current health and attack
	 */
	@Override
	public String toString() {
		String description = "Nickname: " + nickname + " Monster: " + name + " Type: " + type.value + " Health: " + currentHealth + "/" + maxHealth + " Attack: " + attack;
		if (status.name == "Fainted") {
			description = "[FAINTED] " + description;
		}
		return description;
	}



}
