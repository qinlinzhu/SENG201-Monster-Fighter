package monsterfighter.ui.gui;

import monsterfighter.core.GameEnvironment;
import monsterfighter.ui.GameEnvironmentUi;

/**
 * A graphical user interface that links to a {@link GameEnvironment}
 * Class adapted from the Gui class in the seng201 RocketManager example project.
 */
public class Gui implements GameEnvironmentUi{

	// The game environment that this gui interacts with
	private GameEnvironment gameEnvironment;
	
	// The active screen
	private Screen screen;
	
	// An enum representing the screens that the user transition to from another screen
    private enum Option {
    	MAIN_MENU("Main Menu"),
        SHOP("Shop"),
        PARTY("Party"),
        INVENTORY("Inventory"),
        BATTLE("Battle"),
        BATTLE_SELECT("Battle Select"),
        GAME_OVER("Game Over");

        public final String name;

        Option(String name) {
            this.name = name;
        }
    }
	
    @Override
    public void setup(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        screen = new SetupScreen(gameEnvironment);
        screen.show();
    }

    @Override
    public void showError(String error) {
        screen.showError(error);
    }

    @Override
    public void start() {
        screen.quit();
        screen = new MainScreen(gameEnvironment);
        screen.show();
    }

    @Override
    public boolean confirmQuit() {
        return screen.confirmQuit();
    }

    @Override
    public void quit() {
        screen.quit();
    }
    
    /**
     * Handles the transition between screens.
     * 
     * @param name The name of the screen that will be shown
     * @param back The name of the screen that a back button implementation will transition to
     */
    public void transitionScreen(String name, String back) {
    	Option option = Option.valueOf(name);
    	screen.quit();
    	switch (option) {
    	 	case MAIN_MENU:
    	 		screen = new MainScreen(gameEnvironment);
    	 		break;
	        case SHOP:
	        	screen = new ShopScreen(gameEnvironment, back);
	            break;
	        case INVENTORY:
	        	screen = new InventoryScreen(gameEnvironment, back);
	            break;
	        case PARTY:
	        	screen = new PartyScreen(gameEnvironment, back);
		        break;
	        case BATTLE:
	        	screen = new BattleScreen(gameEnvironment, back);
		        break;
	        case BATTLE_SELECT:
	        	screen = new BattleSelectionScreen(gameEnvironment, back);
		        break;
	        case GAME_OVER:
	        	screen = new GameOverScreen(gameEnvironment);
		        break;  
	        default:
	        	throw new IllegalStateException("Unexpected value: " + option);
    	}
    	screen.show(); 
    }
 
}
