package monsterfighter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.ui.CmdLineUi;
import monsterfighter.ui.GameEnvironmentUi;
import monsterfighter.ui.gui.Gui;

/**
 * Class where application execution begins. If {@code cmd} is passed as a program argument the
 * application will run as a command line application, otherwise a GUI will be used.
 * Class adapted from the Main class in the seng201 RocketManager example project.
 */
public class Main {
	
    /**
     * Application entry point for the monster fighter application.
     *
     * @param args The command line arguments. This application supports a single argument: {@code cmd}.
     *             If {@code cmd} is present the application will use a command line interface.
     *             When no argument is specified the application will use a graphical interface.
     */
	public static void main(String[] args) {
		final List<Monster> monsters = new ArrayList<>(6);
		
		monsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
		monsters.add(new Monster(1, "Watergirl", Monster.Type.WATER, 55, 15, 200));
		monsters.add(new Monster(2, "Dirt", Monster.Type.GRASS, 80, 10, 200));
		monsters.add(new Monster(3, "BrightStar", Monster.Type.LIGHT, 60, 12, 200));
		monsters.add(new Monster(4, "DarkStar", Monster.Type.DARK, 30, 24, 200));
		monsters.add(new Monster(5, "Normie", Monster.Type.NORMAL, 120, 6, 200));
		
		
		final List<Item> items = new ArrayList<>(5);
		
		items.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 20, 3));
		items.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 40, 2));
		items.add(new Item(2, "Huge Potion", 120, Item.Stat.CURRENTHEALTH, 60, 1));
		items.add(new Item(3, "Attack Snack", 5, Item.Stat.ATTACK, 50, 1));
		items.add(new Item(4, "Max Health Snack", 10, Item.Stat.MAXHEALTH, 50, 1));
		items.add(new Item(5, "Revive candy", 40, Item.Stat.STATUS, 40, 1));
		
		GameEnvironmentUi ui;


       if (args.length > 0 && (args[0].equals("cmd"))) {
           ui = new CmdLineUi();
           GameEnvironment gameEnvironment = new GameEnvironment(ui, monsters, items);
           gameEnvironment.start();
        } else {
	       ui = new Gui();
	       GameEnvironment gameEnvironment = new GameEnvironment(ui, monsters, items);
	
	        // Ensure the GameEnvironment is started on the Swing event dispatch thread (EDT). To be thread safe,
	        // all swing code should run on this thread unless explicitly stated as being thread safe.
	        SwingUtilities.invokeLater(() -> gameEnvironment.start());
        }
    }
}
