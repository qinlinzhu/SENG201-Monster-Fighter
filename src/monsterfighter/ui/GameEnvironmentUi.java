package monsterfighter.ui;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;

/** 
 * Defines a user interface (UI) for a {@link GameEnvironment}.
 * Class adapted from the RocketManagerUI class in the seng201 RocketManager example project.
 */
public interface GameEnvironmentUi {

    /**
     * A description of the naming convention for the {@link Player}'s name
     */
    String NAME_REQUIREMENTS = "Name must contain only letters and may be from 3-15 characters";
    
    /**
     * A description of the naming convention for a {@link Monster}'s nickname
     */
    String MONSTER_NAME_REQUIREMENTS = "Nickname must contain only letters and have a max of 10 characters";

    /**
     * A regex for validating the name against the specified name requirements.
     */
    String NAME_REGEX = "[a-zA-Z]{3,15}";
    
    /**
     * A regex for validating the monster nickname name against the specified name requirements.
     */
    String NICKNAME_REGEX = "[a-zA-Z]{1,10}";
    
    /**
     * A description of the day requirements for a game of Monster Fighter
     */
    String DAY_REQUIREMENTS = "The number of days must be between 5 and 15";
    
    /**
     * Initialises this UI and sets up the given GameEnvironment with the available monsters and items.
     * Note that setup need not be complete by the time this method returns.
     * Once setup is complete this UI must call 
     * {@link GameEnvironment#onSetupFinished(String, int, Monster, String, monsterfighter.core.GameEnvironment.Difficulty)}.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @param gameEnvironment The game environment that this UI interacts with
     */
    void setup(GameEnvironment gameEnvironment);

    /**
     * Starts this UI allowing the user to play Monster Fighter.
     * Method adapted from the seng201 rocket manager example project.
     */
    void start();

    /**
     * Confirms that the user really wants to quit.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @return true if the user wants to quit, false otherwise
     */

	boolean confirmQuit();
    
	/**
	 * Quits the application.
	 * Method adapted from the seng201 rocket manager example project.
	 */
    void quit();

    /**
     * Reports the given error to the user.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @param error The error to display
     */
    void showError(String error);


}