package monsterfighter.ui.gui;

import java.awt.Container;

import monsterfighter.core.GameEnvironment;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;

/**
 * A screen used to display the end game information of a {@link GameEnvironment}
 */
public class GameOverScreen extends Screen{


	/**
	 * Creates this screen.
	 * 
	 * @param gameEnvironment The game environment that the screen communicates with
	 */
	public GameOverScreen(GameEnvironment gameEnvironment) {
		super("Game Over Screen", gameEnvironment, null);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		addLabels(container);
		addBtnQuit(container);
		
	}

	/**
	 * Creates the generic labels and adds them to the container.
	 * 
	 * @param container The container to add the labels to
	 */
	private void addLabels(Container container) {
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		gameOverLabel.setBounds(10, 11, 514, 80);
		container.add(gameOverLabel);
		
		JLabel lblPoints = new JLabel("Points: " + getGameEnvironment().getPlayer().getPoints());
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPoints.setBounds(10, 229, 514, 30);
		container.add(lblPoints);
		
		JLabel lblTotalGold = new JLabel("Total Gold: " + getGameEnvironment().getPlayer().getTotalGold());
		lblTotalGold.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalGold.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalGold.setBounds(10, 293, 514, 30);
		container.add(lblTotalGold);
		
		JLabel lblDays = new JLabel("Day: " + getGameEnvironment().getDay() + "/" + getGameEnvironment().getTotalDays());
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDays.setBounds(10, 165, 514, 30);
		container.add(lblDays);
		
		JLabel lblName = new JLabel("Name: " + getGameEnvironment().getPlayer().getName());
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(10, 103, 514, 30);
		container.add(lblName);
	}

	/**
	 * Creates the quit button and adds it to the container.
	 * 
	 * @param container The container to add the buttons to
	 */
	private void addBtnQuit(Container container) {
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(e -> quit());
		btnQuit.setBounds(419, 358, 105, 42);
		container.add(btnQuit);
	}


}
