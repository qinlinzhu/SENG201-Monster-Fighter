package monsterfighter.ui.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import monsterfighter.core.Battle;
import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.core.Item;
import monsterfighter.core.TrainerBattle;
import monsterfighter.core.WildBattle;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * A screen used to run a battle from a {@link GameEnvironment}.
 */
public class BattleScreen extends Screen{
	
	// The battle the user is fighting against
	private Battle opponent;

	// The panel for the opponent's monster
	private JPanel opponentPanel;
	
	// The label for the name of the opponent's monster
	private JLabel lblOpponentMonsterName;
	
	// The label for the size of the opponent's party
	private JLabel lblOpponentPartySize;
	
	// The label of the opponent's monster's type
	private JLabel lblOpponentType;
	
	// The panel representing the opponent's monster's health 
	private JPanel panelOpponentHealthBar;
	
	// The label representing the opponent's monster's health
	private JLabel lblOpponentHealth;
	
	// The panel for the player's monster
	private JPanel userPanel;
	
	// The label for the name of the player's monster
	private JLabel lblPlayerMonsterName;
	
	// The label of the player's monster's type
	private JLabel lblPlayerPartySize;
	
	// The label for the size of the player's party
	private JLabel lblPlayerType;
	
	// The panel representing the player's monster's health 
	private JPanel panelPlayerHealthBar;
	
	// The label representing the player's monster's health
	private JLabel lblPlayerHealth;
	
	// The label that displays the battle messages
	private JLabel lblBattleMessage;
	
	/**
	 * Creates this screen.
	 * 
	 * @param gameEnvironment The game environment that the screen communicates with
	 * @param backButtonRoute A string representation of the screen that the back button transitions to
	 */
	protected BattleScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Monster Fighter Battle", gameEnvironment, backButtonRoute);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(490, 517);
		
		opponent = getGameEnvironment().getBattles().getCurrentBattle();
		
		Monster playerMonster = getGameEnvironment().getPlayer().getLeadingMonster();
		Monster opponentMonster = opponent.getMonsters().get(0);
		int playerMonsterHealth = playerMonster.getCurrentHealth();

		addOpponentPanel(container);
		addPlayerPanel(container);
		setMonsterInformation();
		addBattleMessagePanel(container);
		addButtons(container);
		
		if (getBackButtonRoute().equals("BATTLE_SELECT")) {
			setBattleTextStartBattle();
		} else if (getBackButtonRoute().equals("INVENTORY")) {
			getGameEnvironment().manageBattle(opponent);
			setBattleTextUseItem(playerMonster, opponentMonster, playerMonsterHealth);
		} else if (getBackButtonRoute().equals("PARTY")) {
			if (!((Monster) getGameEnvironment().getSelectedObject()).isFainted()) {
				getGameEnvironment().manageBattle(opponent);
			}
			setBattleTextSwitchMonsters(playerMonster, opponentMonster, playerMonsterHealth);
		} else if (getBackButtonRoute().equals("MAIN_MENU")) {
			setBattleTextBasic();
		} 
	}

	/**
	 * Creates the opponent's {@link Monster} panel, along with the labels and panels that populate it, and adds it to the container.
	 * 
	 * @param container The container to add the panel to
	 */
	private void addOpponentPanel(Container container) {
		opponentPanel = new JPanel();
		opponentPanel.setBackground(Color.WHITE);
		opponentPanel.setBounds(229, 23, 235, 95);
		container.add(opponentPanel);
		opponentPanel.setLayout(null);
		
		lblOpponentMonsterName = new JLabel();
		lblOpponentMonsterName.setBounds(10, 11, 150, 42);
		opponentPanel.add(lblOpponentMonsterName);
		
		lblOpponentPartySize = new JLabel();
		lblOpponentPartySize.setBounds(197, 11, 54, 42);
		opponentPanel.add(lblOpponentPartySize);
		
		lblOpponentType = new JLabel();
		lblOpponentType.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpponentType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOpponentType.setForeground(Color.WHITE);
		lblOpponentType.setOpaque(true);
		lblOpponentType.setBounds(138, 22, 54, 20);
		opponentPanel.add(lblOpponentType);
		
		panelOpponentHealthBar = new JPanel();
		panelOpponentHealthBar.setBackground(Color.GREEN);
		panelOpponentHealthBar.setBounds(12, 56, 146, 26);
		opponentPanel.add(panelOpponentHealthBar);
				
		JPanel panelOpponentHealthBarBorder = new JPanel();
		panelOpponentHealthBarBorder.setBackground(Color.GRAY);
		panelOpponentHealthBarBorder.setBorder(new MatteBorder(2, 2, 2, 2, new Color(102,146,61)));
		panelOpponentHealthBarBorder.setBounds(10, 54, 150, 30);
		opponentPanel.add(panelOpponentHealthBarBorder);
		
		lblOpponentHealth = new JLabel();
		lblOpponentHealth.setForeground(new Color(102,146,61));
		lblOpponentHealth.setBounds(170, 59, 46, 14);
		opponentPanel.add(lblOpponentHealth);
	}
	
	/**
	 * Creates the player's {@link Monster} panel, along with the labels and panels that populate it, and adds it to the container.
	 * 
	 * @param container The container to add the panel to
	 */
	private void addPlayerPanel(Container container) {
		userPanel = new JPanel();
		userPanel.setBackground(Color.WHITE);
		userPanel.setBounds(10, 163, 235, 95);
		container.add(userPanel);
		userPanel.setLayout(null);
		
		lblPlayerMonsterName = new JLabel();
		lblPlayerMonsterName.setBounds(10, 11, 150, 42);
		userPanel.add(lblPlayerMonsterName);
		
		lblPlayerType = new JLabel();
		lblPlayerType.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlayerType.setForeground(Color.WHITE);
		lblPlayerType.setOpaque(true);
		lblPlayerType.setBounds(138, 22, 54, 20);
		userPanel.add(lblPlayerType);
		
		lblPlayerPartySize = new JLabel();
		lblPlayerPartySize.setBounds(197, 11, 54, 42);
		userPanel.add(lblPlayerPartySize);
		
		panelPlayerHealthBar = new JPanel();
		panelPlayerHealthBar.setBackground(Color.GREEN);
		panelPlayerHealthBar.setBounds(12, 56, 146, 26);
		userPanel.add(panelPlayerHealthBar);
			
		JPanel panelPlayerHealthBarBorder = new JPanel();
		panelPlayerHealthBarBorder.setBackground(Color.GRAY);
		panelPlayerHealthBarBorder.setBorder(new MatteBorder(2, 2, 2, 2, new Color(102,146,61)));
		panelPlayerHealthBarBorder.setBounds(10, 54, 150, 30);
		userPanel.add(panelPlayerHealthBarBorder);
		
		lblPlayerHealth = new JLabel();
		lblPlayerHealth.setForeground(new Color(102,146,61));
		lblPlayerHealth.setBounds(170, 59, 46, 14);
		userPanel.add(lblPlayerHealth);
		
	}
	
	/**
	 * Updates the player and opponent {@link Monster} panels with the current details.
	 */
	private void setMonsterInformation() {
		opponentPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) opponent.getMonsters().get(0).getType().colour));
		lblOpponentMonsterName.setText(opponent.getMonsters().get(0).getNickname());
		lblOpponentPartySize.setText(opponent.getConsciousMonsters() + "x M");
		lblOpponentType.setText(opponent.getMonsters().get(0).getType().value);
		lblOpponentType.setBackground(opponent.getMonsters().get(0).getType().colour);
		lblOpponentHealth.setText(opponent.getMonsters().get(0).getCurrentHealth() + "/" + opponent.getMonsters().get(0).getMaxHealth());
		// Opponent Monster health bar
		int opponentWidth = (int) (146 * ((double)opponent.getMonsters().get(0).getCurrentHealth() / opponent.getMonsters().get(0).getMaxHealth()));
		panelOpponentHealthBar.setBounds(12, 56, opponentWidth, 26);
		
		userPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) getGameEnvironment().getPlayer().getLeadingMonster().getType().colour));
		lblPlayerMonsterName.setText(getGameEnvironment().getPlayer().getLeadingMonster().getNickname());
		lblPlayerPartySize.setText(getGameEnvironment().getPlayer().getConsciousMonsters() + "x M");
		lblPlayerType.setText(getGameEnvironment().getPlayer().getLeadingMonster().getType().value);
		lblPlayerType.setBackground(getGameEnvironment().getPlayer().getLeadingMonster().getType().colour);
		lblPlayerHealth.setText(getGameEnvironment().getPlayer().getLeadingMonster().getCurrentHealth() + "/" 
				+ getGameEnvironment().getPlayer().getLeadingMonster().getMaxHealth());
		// Monster health bar
		int playerWidth = (int) (146 * ((double)getGameEnvironment().getPlayer().getLeadingMonster().getCurrentHealth() 
				/ getGameEnvironment().getPlayer().getLeadingMonster().getMaxHealth()));
		panelPlayerHealthBar.setBounds(12, 56, playerWidth, 26);
	}
	
	/**
	 * Creates the battle message panel, along with the battle message label, and adds it to the container.
	 * 
	 * @param container The container to add the panel to
	 */
	private void addBattleMessagePanel(Container container) {
		JPanel pnlBattleMessages = new JPanel();
		pnlBattleMessages.setBorder(new MatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		pnlBattleMessages.setBounds(10, 280, 454, 124);
		container.add(pnlBattleMessages);
		pnlBattleMessages.setLayout(null);
		
		lblBattleMessage = new JLabel();
		lblBattleMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBattleMessage.setBounds(10, 11, 434, 102);
		pnlBattleMessages.add(lblBattleMessage);
	}
	
	/**
	 * Sets the battle label text to the starting message.
	 */
	private void setBattleTextStartBattle() {
		String message = "<html>";
		if (opponent instanceof WildBattle) {
			message += "A wild " + opponent.getMonsters().get(0).getName() + " attacked!<br>";
		} else {
			message += "Trainer " + ((TrainerBattle) opponent).getTrainer() + " sent out " + opponent.getMonsters().get(0).getName() + "!<br>";
		}
		message += "Go get em' " + getGameEnvironment().getPlayer().getLeadingMonster().getNickname() + "!<br>";
		addBattleText(message);
	}
	
	/**
	 * Sets the battle label text to the basic message.
	 */
	private void setBattleTextBasic() {
		String message = "<html>" + getGameEnvironment().getPlayer().getLeadingMonster().getNickname() + " is waiting on your instruction<br>";
		addBattleText(message);
	}
	
	/**
	 * Sets the battle label text to the attack message.
	 * 
	 * @param opponentMonsterHealth The opponent's monster's health at the start of the turn
	 * @param playerMonsterHealth The player's monster's health at the start of the turn
	 * @param opponentMonster The opponent's monster at the start of the turn
	 * @param playerMonster The player's monster at the start of the turn
	 */
	private void setBattleTextAttack(Monster playerMonster, Monster opponentMonster, int playerMonsterHealth, int opponentMonsterHealth) {

		String message = "<html>" + playerMonster.getNickname() + " did " + (opponentMonsterHealth - opponentMonster.getCurrentHealth())
				+ " damage to opponent " + opponentMonster.getNickname() + "<br>";
		if (opponentMonster.isFainted()) {
			message += "Opponent " + opponentMonster.getNickname() + " fainted!<br>";
			if (opponent instanceof TrainerBattle) {
				message += "Trainer " + ((TrainerBattle) opponent).getTrainer() + " sent out " + opponent.getMonsters().get(0).getName() + "!<br>";
			}
		} else {
			message += "Opponent " + opponentMonster.getNickname() + " did " + (playerMonsterHealth - playerMonster.getCurrentHealth())
					+ " damage to " + playerMonster.getNickname() + "<br>";
		}
		addBattleText(message);
	}

	/**
	 * Sets the battle label text to the use item message.
	 * 
	 * @param playerMonster The player's monster at the start of the turn
	 * @param opponentMonster The opponent's monster at the start of the turn
	 * @param playerMonsterHealth The player's monster's health before the turn
	 */
	private void setBattleTextUseItem(Monster playerMonster, Monster opponentMonster, int playerMonsterHealth) {
		String message = "<html>Used " + ((Item) getGameEnvironment().getSelectedObject()).getName() + " on " 
				+ getGameEnvironment().getPlayer().getLeadingMonster().getNickname() + "<br>";
		message += "Opponent " + opponentMonster.getNickname() + " did " + (playerMonsterHealth - playerMonster.getCurrentHealth())
				+ " damage to " + playerMonster.getNickname() + "<br>";
		addBattleText(message);
	}

	/**
	 * Sets the battle label text to the switch monsters message.
	 * 
	 * @param playerMonster The player's monster at the start of the turn
	 * @param opponentMonster The opponent's monster at the start of the turn
	 * @param playerMonsterHealth The player's monster's health before the turn
	 */
	private void setBattleTextSwitchMonsters(Monster playerMonster, Monster opponentMonster, int playerMonsterHealth) {

		String message = "<html>Switched " + ((Monster) getGameEnvironment().getSelectedObject()).getNickname() + " out with " 
				+ getGameEnvironment().getPlayer().getLeadingMonster().getNickname() + "<br>";
		if (!((Monster) getGameEnvironment().getSelectedObject()).isFainted()) {
			message += "Opponent " + opponentMonster.getNickname() + " did " + (playerMonsterHealth - playerMonster.getCurrentHealth())
					+ " damage to " + playerMonster.getNickname() + "<br>";
		}
		addBattleText(message);
	}

	/**
	 * Sets the battle label text, and opens a pop up if either the battle stops or the player's {@link Monster} is {@link Monster.Status#FAINTED}.
	 * 
	 * @param message The text that will be displayed in the battle panel
	 */
	private void addBattleText(String message) {
		if (getGameEnvironment().getPlayer().getLeadingMonster().isFainted()) {
			message += getGameEnvironment().getPlayer().getLeadingMonster().getNickname() + " fainted!<br>";
		}
		message+="</html>";
		getGameEnvironment().setSelectedObject(null);
		setMonsterInformation();
		lblBattleMessage.setText(message);
		if (!getGameEnvironment().getBattleRunning()) {
			optionPanelEndBattle();
		} else if (getGameEnvironment().getPlayer().getLeadingMonster().isFainted()) {
			optionPanelSwitchFaintedMonster();
		}
	}

	/**
	 * Creates the buttons and adds them to the container.
	 * 
	 * @param container The container to add the buttons to
	 */
	private void addButtons(Container container) {
		JButton btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Monster playerMonster = getGameEnvironment().getPlayer().getLeadingMonster();
				Monster opponentMonster = opponent.getMonsters().get(0);
				int playerMonsterHealth = playerMonster.getCurrentHealth();
				int opponentMonsterHealth = opponentMonster.getCurrentHealth();
				
				playerMonster.attack(opponentMonster);
				getGameEnvironment().manageBattle(opponent);
				setBattleTextAttack(playerMonster, opponentMonster, playerMonsterHealth, opponentMonsterHealth);
			}
			
		});
		btnAttack.setBounds(20, 425, 141, 42);
		container.add(btnAttack);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getGameEnvironment().transitionScreen("INVENTORY", "BATTLE");
			}
			
		});
		btnUseItem.setBounds(171, 425, 136, 42);
		container.add(btnUseItem);
		
		JButton btnSwitchMonster = new JButton("Switch Monster");
		btnSwitchMonster.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getGameEnvironment().transitionScreen("PARTY", "BATTLE");
			}
			
		});
		btnSwitchMonster.setBounds(317, 425, 136, 42);
		container.add(btnSwitchMonster);
	}
	
	/**
	 * Creates an option panel for the end of the battle.
	 */
	private void optionPanelEndBattle() {
		final JButton btnOkay = new JButton("Okay");
		JLabel lblMessage = new JLabel();
		String message = "<html>";
		
		btnOkay.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();
				getGameEnvironment().getBattles().setCurrentBattle(null);
				getGameEnvironment().transitionScreen("BATTLE_SELECT", "MAIN_MENU");
			}
			
		});
		
		if (!getGameEnvironment().getPlayer().partyFainted()) {
			message += "You Won!<br>"
					+ "Received:<br>"
					+ "Points: " + opponent.getPoints() + "<br>";
			if (opponent instanceof WildBattle) {
				WildBattle wildOpponent = (WildBattle) opponent;
				message += "Item: " + wildOpponent.getReward().getName()+ "<br>";
			} else if (opponent instanceof TrainerBattle) {
				TrainerBattle trainerOpponent = (TrainerBattle) opponent;
				message += "Gold: " + trainerOpponent.getGold()+ "<br>";
			}
		} else {
			message += "You Lost...<br>"
					+ "Received:<br>"
					+ "Points: 0<br>";
			
		}
		message += "<br>Team:<br>";
		for (Monster monster: getGameEnvironment().getPlayer().getParty()) {
			message += monster.getNickname() + " | ";
			if (!monster.isFainted()) {
				message += " Health " + monster.getCurrentHealth() + "/" + monster.getMaxHealth();
			} else {
				message += " [FAINTED]";
			}
			message += "<br>";
		}
		message+="</html>";
		lblMessage.setText(message);
		
		int result = JOptionPane.showOptionDialog(null,  new Object[] {lblMessage}, "Battle Report", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
		        {btnOkay}, btnOkay);
		if (result == -1) {
			JOptionPane.getRootFrame().dispose();
			getGameEnvironment().getBattles().setCurrentBattle(null);
			getGameEnvironment().transitionScreen("BATTLE_SELECT", "MAIN_MENU");
		}
	}
	
	/**
	 * Creates an option panel for switching out a {@link Monster.Status#FAINTED} {@link Monster}.
	 */
	private void optionPanelSwitchFaintedMonster() {
		final JButton btnOkay = new JButton("Okay");
		JLabel lblMessage = new JLabel("<html>" + getGameEnvironment().getPlayer().getLeadingMonster().getNickname() + " has fainted!<br>"
				+ "Switch in another monster to carry on the fight</html>");
		
		btnOkay.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();
				getGameEnvironment().transitionScreen("PARTY", "BATTLE");
			}
			
		});
		
		
		int result = JOptionPane.showOptionDialog(null,  new Object[] {lblMessage}, "Monster Fainted", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
		        {btnOkay}, btnOkay);
		if (result == -1) {
			JOptionPane.getRootFrame().dispose();
			getGameEnvironment().transitionScreen("PARTY", "BATTLE");
		}
	}

}
