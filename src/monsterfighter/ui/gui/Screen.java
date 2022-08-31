package monsterfighter.ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monsterfighter.core.GameEnvironment;

/**
 * Defines common behaviour supported by a gui screen.
 * Class adapted from the Screen class in the seng201 RocketManager example project.
 */
public abstract class Screen {

    // The frame for this screen
    private JFrame frame;

    // The game environment that the screen interacts with
    private final GameEnvironment gameEnvironment;
    
    // The name of the screen that a back button implementation will transition to
    private final String backButtonRoute;

    /**
     * Creates this screen.
     *
     * @param title The title for the screen
     * @param gameEnvironment The {@link GameEnvironment} that this screen interacts with
     */
    protected Screen(final String title, final GameEnvironment gameEnvironment, String backButtonRoute) {
        this.gameEnvironment = gameEnvironment;
        this.backButtonRoute = backButtonRoute;
        initialise(title);
    }

    /**
     * Initialises this screen's UI.
     * Method adapted from the seng201 rocket manager example project.
     */
    private void initialise(final String title) {
        frame = new JFrame();
        frame.setTitle(title);

        // Prevent the frame from closing immediately when the user clicks the close button.
        // Instead we add a WindowListener so we can tell our rocket manager that the user
        // has requested to quit. This allows the rocket manager to perform actions that may
        // be required before quitting E.g. Confirming the user really wants to quit,
        // saving state etc.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameEnvironment.onFinish();
            }
        });

        initialise(frame);

        frame.setLocationRelativeTo(null);
        
        frame.setResizable(false);
        
        frame.getContentPane().setLayout(null);
    }

    /**
     * Creates and adds the required graphical components to the given container.
     *  Method adapted from the seng201 rocket manager example project.
     *
     * @param container The container to add content to
     */
    protected abstract void initialise(Container container);

    /**
     * Gets the top level component of this screen.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @return The top level component
     */
    protected Component getParentComponent() {
        return frame;
    }

    /**
     * Gets the {@link GameEnvironment} that this screen supports.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @return The game environment for this screen
     */
    protected GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }
    
    /**
     * Gets the name of the previous screen
     * 
     * @return A string representation of the previous screen
     */
    protected String getBackButtonRoute() {
    	return backButtonRoute;
    }

    /**
     * Shows this screen by making it visible.
     * Method adapted from the seng201 rocket manager example project.
     */
    protected void show() {
        frame.setVisible(true);
    }

    /**
     * Confirms if the user wants to quit this screen.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @return true to quit, false otherwise
     */
    protected boolean confirmQuit() {
        int selection = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?",
                "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return selection == JOptionPane.YES_OPTION;
    }

    /**
     * Quits this screen. This should dispose of the screen as necessary.
     * Method adapted from the seng201 rocket manager example project.
     */
    void quit() {
        frame.dispose();
    }

    /**
     * Reports the given error to the user.
     * Method adapted from the seng201 rocket manager example project.
     *
     * @param error The error to report
     */
    void showError(String error) {
        JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

