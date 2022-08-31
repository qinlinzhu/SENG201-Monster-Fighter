package monsterfighter.ui.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.ui.GameEnvironmentUi;

/**
 * A screen used to access a player's party through a {@link GameEnvironment}
 */
public class PartyScreen extends Screen{
	
	// Button for setting a monsters nickname
	private JButton btnNickname;
	
	// Button for using an item on a monster
	private JButton btnUseItem;
	
	// Toggle button for switching monsters in the party
	private JToggleButton btnSwitchMonsters;

	// Button for selling a monster
	private JButton btnSellMonster;

	// Toggle button for the first monster in the party
	private JToggleButton btnMonsterOne;

	// Toggle button for the second monster in the party
	private JToggleButton btnMonsterTwo;

	// Toggle button for the third monster in the party
	private JToggleButton btnMonsterThree;

	// Toggle button for the fourth monster in the party
	private JToggleButton btnMonsterFour;

	// Toggle button representing the selected monster toggle button
	private JToggleButton btnSelectedMonster;

	// Monster that correlates to the selected monster toggle button
	private Monster selectedMonster;

	// Monster to switch the previously selected monster with
	private Monster selectedMonsterSwitch;

	// Item to use on a selected monster
	private Item selectedItem;

	// List of all buttons on the screen
	private List<AbstractButton> listOptionButtons;

	// ButtonGroup of all the monster toggle buttons
	private ButtonGroup buttonGroupPartyMonsters;

	// Label for a players gold
	private JLabel lblGold;

	// Label for the sell price of a selected monster
	private JLabel lblSellPrice;
	
	/**
	 * Creates this screen.
	 * 
	 * @param gameEnvironment The game environment that the screen communicates with
	 * @param backButtonRoute A string representation of the screen that the back button transitions to
	 */
	protected PartyScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Monster Fighter Party", gameEnvironment, backButtonRoute);
	}
	
	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		if (getBackButtonRoute().equals("INVENTORY")) {
			selectedItem = (Item)getGameEnvironment().getSelectedObject();
		}
		
		addlabels(container);
		addMonsterBtns(container);
		addOptionBtns(container);
		
		if (getBackButtonRoute().equals("SHOP")) {
			addLabelsSell(container);
		}
	}

	/**
	 * Creates the labels for selling a monster adds them to the container.
	 * 
	 * @param container The container to add the labels to
	 */
	private void addLabelsSell(Container container) {
		
		lblGold = new JLabel("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGold.setBounds(411, 11, 113, 43);
		container.add(lblGold);	
		
		lblSellPrice = new JLabel("Sell Price:");
		lblSellPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSellPrice.setVisible(false);
		lblSellPrice.setBounds(198, 356, 137, 43);
		container.add(lblSellPrice);
		
	}

	/**
	 * Creates the generic labels and adds them to the container.
	 * 
	 * @param container The container to add the labels to
	 */
	private void addlabels(Container container) {
		
		JLabel partyLabel = new JLabel("Party");
		partyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		partyLabel.setBounds(21, 11, 168, 43);
		container.add(partyLabel);
			
		JLabel lblSlotFourOne = new JLabel("Slot 1");
		lblSlotFourOne.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFourOne.setBounds(79, 58, 46, 14);
		container.add(lblSlotFourOne);
		
		JLabel lblSlotFourTwo = new JLabel("Slot 2");
		lblSlotFourTwo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFourTwo.setBounds(296, 58, 46, 14);
		container.add(lblSlotFourTwo);
		
		JLabel lblSlotFourThree = new JLabel("Slot 3");
		lblSlotFourThree.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFourThree.setBounds(79, 204, 46, 14);
		container.add(lblSlotFourThree);
		
		JLabel lblSlotFour = new JLabel("Slot 4");
		lblSlotFour.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSlotFour.setBounds(296, 204, 46, 14);
		container.add(lblSlotFour);
		
	}
	
	/**
	 * Creates the toggle buttons that represent the party {@link Monster}s and adds them to the container.
	 * 
	 * @param container The container to add the toggle buttons to
	 */
	private void addMonsterBtns(Container container) {
		
		buttonGroupPartyMonsters = new ButtonGroup();
		ActionListener listenerPartyMonsters = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (AbstractButton button: listOptionButtons) {
					button.setEnabled(buttonGroupPartyMonsters.getSelection()!=null);
				}
				btnSelectedMonster = (JToggleButton) e.getSource();
				if (e.getSource().equals(btnMonsterOne)) {
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(0);
				} else if (e.getSource().equals(btnMonsterTwo)){
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(1);
				} else if (e.getSource().equals(btnMonsterThree)) {
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(2);
				} else {
					selectedMonster = getGameEnvironment().getPlayer().getParty().get(3);
				}
				if (getBackButtonRoute().equals("SHOP")) {
					lblSellPrice.setVisible(true);
					lblSellPrice.setText("Sell Price: " + selectedMonster.getSellPrice());
				}
				if (btnSwitchMonsters!=null && btnSwitchMonsters.isSelected()) {
					Monster monster = getGameEnvironment().getPlayer().getLeadingMonster();
					getGameEnvironment().switchMonsters(selectedMonsterSwitch, selectedMonster);
					// If the player is fighting and they switched their leading monster around
					if (!monster.equals(getGameEnvironment().getPlayer().getLeadingMonster()) && getGameEnvironment().getBattleRunning()) {
						getGameEnvironment().setSelectedObject(monster);
						getGameEnvironment().transitionScreen(getBackButtonRoute(), "PARTY");
					}
					selectedMonsterSwitch = null;
					buttonGroupPartyMonsters.clearSelection();
					btnSwitchMonsters.setSelected(false);
					for (AbstractButton button: listOptionButtons) {
						button.setEnabled(buttonGroupPartyMonsters.getSelection()!=null);
					}
					paintBtnsMonsters();
				}
			}
		};


		btnMonsterOne = new JToggleButton();
		btnMonsterOne.addActionListener(listenerPartyMonsters);
		btnMonsterOne.setBounds(69, 83, 182, 106);
		container.add(btnMonsterOne);
		

		btnMonsterTwo = new JToggleButton();
		btnMonsterTwo.addActionListener(listenerPartyMonsters);
		btnMonsterTwo.setBounds(282, 83, 182, 106);
		container.add(btnMonsterTwo);
		

		btnMonsterThree = new JToggleButton();
		btnMonsterThree.addActionListener(listenerPartyMonsters);
		btnMonsterThree.setBounds(69, 229, 182, 106);
		container.add(btnMonsterThree);
		

		btnMonsterFour = new JToggleButton();
		btnMonsterFour.addActionListener(listenerPartyMonsters);
		btnMonsterFour.setBounds(282, 229, 182, 106);
		container.add(btnMonsterFour);
		
		paintBtnsMonsters();
		
		buttonGroupPartyMonsters.add(btnMonsterOne);
		buttonGroupPartyMonsters.add(btnMonsterTwo);
		buttonGroupPartyMonsters.add(btnMonsterThree);
		buttonGroupPartyMonsters.add(btnMonsterFour);
			
	}
	
	/**
	 * Sets the button text for the monster buttons.
	 */
	private void paintBtnsMonsters() {
		
		if (getGameEnvironment().getPlayer().getParty().size()>=1) {
			btnMonsterOne.setText(getGameEnvironment().getPlayer().getParty().get(0).getNickname());
			btnMonsterOne.setToolTipText(getGameEnvironment().getPlayer().getParty().get(0).tooltipText());
		} else {
			btnMonsterOne.setEnabled(false);
			btnMonsterOne.setText(null);
		}
		
		if (getGameEnvironment().getPlayer().getParty().size()>=2) {
			btnMonsterTwo.setText(getGameEnvironment().getPlayer().getParty().get(1).getNickname());
			btnMonsterTwo.setToolTipText(getGameEnvironment().getPlayer().getParty().get(1).tooltipText());
		} else {
			btnMonsterTwo.setEnabled(false);
			btnMonsterTwo.setText(null);
		}
		
		if (getGameEnvironment().getPlayer().getParty().size()>=3) {
			btnMonsterThree.setText(getGameEnvironment().getPlayer().getParty().get(2).getNickname());
			btnMonsterThree.setToolTipText(getGameEnvironment().getPlayer().getParty().get(2).tooltipText());
		} else {
			btnMonsterThree.setEnabled(false);
			btnMonsterThree.setText(null);
		}
		
		if (getGameEnvironment().getPlayer().getParty().size()==4) {
			btnMonsterFour.setText(getGameEnvironment().getPlayer().getParty().get(3).getNickname());
			btnMonsterFour.setToolTipText(getGameEnvironment().getPlayer().getParty().get(3).tooltipText());
		} else {
			btnMonsterFour.setEnabled(false);
			btnMonsterFour.setText(null);
		}
	}
	
	/**
	 * Creates the option buttons and adds them to the container.
	 * 
	 * @param container The container to add the buttons to
	 */
	private void addOptionBtns(Container container) {
		
		listOptionButtons = new ArrayList<AbstractButton>();
		
		JButton btnBack = new JButton("Back");
		if (getBackButtonRoute().equals("BATTLE") && getGameEnvironment().getPlayer().getLeadingMonster().isFainted()) {
			btnBack.setEnabled(false);
		}
		btnBack.addActionListener(e -> {
			if (getBackButtonRoute().equals("MAIN_MENU")) {
				getGameEnvironment().transitionScreen(getBackButtonRoute(), "PARTY");
			} else {
				getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU");
			}
		});
		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
	
		if (getBackButtonRoute().equals("MAIN_MENU")) {
			btnNickname = new JButton("Nickname");
			btnNickname.setEnabled(false);
			btnNickname.addActionListener(e -> {
				optionPaneNickname();
				paintBtnsMonsters();
			});
			btnNickname.setBounds(189, 358, 105, 42);
			container.add(btnNickname);
			listOptionButtons.add(btnNickname);
			
			btnUseItem = new JButton("Use Item");
			btnUseItem.setEnabled(false);
			btnUseItem.addActionListener(e -> {
				getGameEnvironment().setSelectedObject(selectedMonster);
				getGameEnvironment().transitionScreen("INVENTORY", "PARTY");
			});
			btnUseItem.setBounds(304, 358, 105, 42);
			container.add(btnUseItem);
			listOptionButtons.add(btnUseItem);
		
		} if (getBackButtonRoute().equals("MAIN_MENU") || getBackButtonRoute().equals("BATTLE")) {
			btnSwitchMonsters = new JToggleButton("Switch");
			btnSwitchMonsters.setEnabled(false);
			btnSwitchMonsters.addActionListener(e -> { 
				selectedMonsterSwitch = selectedMonster;
				btnSelectedMonster.setPressedIcon(null);
			});
			btnSwitchMonsters.setBounds(419, 358, 105, 42);
			container.add(btnSwitchMonsters);
			listOptionButtons.add(btnSwitchMonsters);
			
		} else if (getBackButtonRoute().equals("SHOP")) {
			btnSellMonster = new JButton("Sell");
			btnSellMonster.setEnabled(false);
			btnSellMonster.addActionListener(e -> {
				if (getGameEnvironment().getPlayer().getGoldBalance() + selectedMonster.getSellPrice() < 200 && 
						getGameEnvironment().getPlayer().getParty().size() == 1) {
					 optionPaneEndGame();
				} else {
					sellMonster();
				}
			});
			btnSellMonster.setBounds(419, 358, 105, 42);
			container.add(btnSellMonster);
			listOptionButtons.add(btnSellMonster);
			
		} else if (getBackButtonRoute().equals("INVENTORY")) {
			btnUseItem = new JButton("Use Item");
			btnUseItem.setEnabled(false);
			btnUseItem.addActionListener(e -> {
				getGameEnvironment().useItem(selectedMonster, selectedItem);
				getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU");
			});
			btnUseItem.setBounds(419, 358, 105, 42);
			container.add(btnUseItem);
			listOptionButtons.add(btnUseItem);
		}
	}
	
	/**
	 * Creates an option pane for giving a monster a nickname.
	 */
	private void optionPaneNickname() {
		final JButton btnCancel = new JButton("Cancel");
		final JButton btnAccept = new JButton("Accept");
		final JTextField fieldNickname = new JTextField();
		JLabel lblNickname = new JLabel("Change Nickname");
		
		btnAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedMonster.setNickname(fieldNickname.getText());
				JOptionPane.getRootFrame().dispose();   
			}
			
		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();   
			}
			
		});
		
		fieldNickname.setColumns(50);
		fieldNickname.getDocument().addDocumentListener(new DocumentListener() {
	        protected void update() {
	        	boolean validMonsterNickname = fieldNickname.getText().matches(GameEnvironmentUi.NICKNAME_REGEX) || fieldNickname.getText().isEmpty();
	            btnAccept.setEnabled(validMonsterNickname);
	            lblNickname.setText(validMonsterNickname ? "Change Nickname" : "<html>Change Nickname <font color='red'>(" + GameEnvironmentUi.MONSTER_NAME_REQUIREMENTS + ")</font></html>");
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            update();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            update();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            update();
	        }
		});
		
        JOptionPane.showOptionDialog(null,  new Object[] {lblNickname, fieldNickname}, "Set Nickname", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
        {btnCancel, btnAccept}, btnCancel);
	}
	
	/**
	 * Creates an option pane for ending the game if the user decides to sell a monster,
	 * while they have insufficient gold to buy another.
	 */
	private void optionPaneEndGame() {
		final JButton btnCancel = new JButton("Cancel");
		final JButton btnSellAnyway = new JButton("Sell Anyway");
		final JButton btnEndGame = new JButton("End Game");
		final JLabel lblMessage = new JLabel("<html><font color='red'>WARNING:</font> Selling " + selectedMonster.getNickname() + " will "
				+ "leave you without monsters and not enough gold to buy another.<br> "
				+ "You can choose to cancel the sale, sell and carry on the game or sell and end the game here.");
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();   
			}
			
		});
		
		btnSellAnyway.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sellMonster();
				JOptionPane.getRootFrame().dispose();
			}
			
		});
		
		btnEndGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sellMonster();
				JOptionPane.getRootFrame().dispose();
				getGameEnvironment().transitionScreen("GAME_OVER", "PARTY");
			}
		});
		
		JOptionPane.showOptionDialog(null,  new Object[] {lblMessage}, "Set Nickname", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new JButton[]
		        {btnCancel, btnSellAnyway, btnEndGame}, btnCancel);
	}
	
	/**
	 * Updates the screen after a {@link Monster} is sold.
	 */
	private void sellMonster() {
		getGameEnvironment().sellMonster(selectedMonster);
		buttonGroupPartyMonsters.clearSelection();
		lblSellPrice.setVisible(false);
		lblGold.setText("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		btnSellMonster.setEnabled(false);
		selectedMonster=null;
		paintBtnsMonsters();
	}
	
}