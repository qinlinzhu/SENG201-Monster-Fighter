package monsterfighter.ui.gui;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;

import java.awt.Color;
import java.awt.Container;

import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;

/**
 * A screen used to access a player's inventory through a {@link GameEnvironment}
 */
public class InventoryScreen extends Screen{

	// List of items in the player's inventory
	private JList<ArrayList<Item>> listInventory;
	
	// ListModel for the inventory JList
	private DefaultListModel<ArrayList<Item>> inventoryListModel;
	
	// Button to use an item
	private JButton btnUseItem;
	
	// Button to sell an item
	private JButton btnSellItem;
	
	// Selected monster to use an item on
	private Monster selectedMonster;
	
	// Name of selected monster
	private JLabel lblMonster;
	
	// Health of selected monster
	private JLabel lblHealth;
	
	// Attack of selected monster
	private JLabel lblAttack;
	
	// Combo box to select number of items to sell
	private JComboBox<Integer> comboBoxNumItems;
	
	// Label that prompts the user how many items they want to sell
	private JLabel lblSellAmount;
	
	// Label that lists the total sell price of the selected item
	private JLabel lblSellPrice;
	
	// Label for the players gold
	private JLabel lblGold;
	
	// List of the buttons in the container
	private List<AbstractButton> listOptionButtons;
	
	/**
	 * Creates this screen.
	 * 
	 * @param gameEnvironment The game environment that the screen communicates with
	 * @param backButtonRoute A string representation of the screen that the back button transitions to
	 */
	protected InventoryScreen(GameEnvironment gameEnvironment, String backButtonRoute) {
		super("Monster Fighter Inventory", gameEnvironment, backButtonRoute);
	}
	

	@Override
	protected void initialise(Container container) {
		container.setSize(550, 450);
		
		if (getBackButtonRoute().equals("PARTY")) {
			selectedMonster = (Monster)getGameEnvironment().getSelectedObject();
		} else if (getBackButtonRoute().equals("BATTLE")) {
			System.out.println(getGameEnvironment().getPlayer().getLeadingMonster());
			System.out.println(getGameEnvironment().getPlayer().getParty());
			selectedMonster = getGameEnvironment().getPlayer().getLeadingMonster();
		}
		
		addLabelInventory(container);
		addBtns(container);
		addListInventory(container);
		if (getBackButtonRoute().equals("PARTY") || getBackButtonRoute().equals("BATTLE")) {
			addLabelsMonster(container);
		} else if (getBackButtonRoute().equals("SHOP")) {
			addComboBox(container);
			addLabelsSell(container);
		}
	}
	
	/**
	 * Creates the labels for selling an item and adds them to the container.
	 * 
	 * @param container The container to add the labels to
	 */
	private void addLabelsSell(Container container) {
		
		lblSellAmount = new JLabel("How many would you like to sell?");
		lblSellAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSellAmount.setVisible(false);
		lblSellAmount.setBounds(140, 331, 186, 14);
		container.add(lblSellAmount);
				
		lblSellPrice = new JLabel("Sell Price:");
		lblSellPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSellPrice.setVisible(false);
		lblSellPrice.setBounds(198, 356, 137, 43);
		container.add(lblSellPrice);
		
		lblGold = new JLabel("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGold.setBounds(411, 11, 113, 43);
		container.add(lblGold);	
	}


	/**
	 * Creates the label for the inventory title and adds it to the container.
	 * 
	 * @param container The container to add the label to
	 */
	private void addLabelInventory(Container container) {
		JLabel inventroyLabel = new JLabel("Inventory");
		inventroyLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		inventroyLabel.setBounds(21, 11, 168, 43);
		container.add(inventroyLabel);
	}
	
	/**
	 * Creates the labels for the selected monster adds them to the container.
	 * 
	 * @param container The container to add the labels to
	 */
	private void addLabelsMonster(Container container) {
		lblMonster = new JLabel();
		lblMonster.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMonster.setBounds(125, 312, 284, 54);
		container.add(lblMonster);
		
		lblHealth = new JLabel();
		lblHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHealth.setBounds(125, 358, 225, 23);
		container.add(lblHealth);
		
		lblAttack = new JLabel();
		lblAttack.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttack.setBounds(125, 377, 225, 23);
		container.add(lblAttack);
		
		setTextLabelMonster();
	}
	
	/**
	 * Sets the label text for the selected monster labels
	 */
	private void setTextLabelMonster() {
		lblMonster.setText("Monster: " + selectedMonster.getNickname());
		String textLblHealth = "Health: " + selectedMonster.getCurrentHealth() + "/" + selectedMonster.getMaxHealth();
		if (selectedMonster.isFainted()) {
			textLblHealth += " [FAINTED]";
		}
		lblHealth.setText(textLblHealth);
		lblAttack.setText("Attack: " + selectedMonster.getAttack());
	}
	
	/**
	 * Creates the option buttons and adds them to the container.
	 * 
	 * @param container The container to add the buttons to
	 */
	private void addBtns(Container container) {
		listOptionButtons = new ArrayList<AbstractButton>();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getBackButtonRoute().equals("MAIN_MENU")) {
					getGameEnvironment().transitionScreen(getBackButtonRoute(), "INVENTORY");
				} else {
					getGameEnvironment().transitionScreen(getBackButtonRoute(), "MAIN_MENU");
					getGameEnvironment().setSelectedObject(null);
				}	
			}
			
		});


		btnBack.setBounds(10, 358, 105, 42);
		container.add(btnBack);
		
		if (getBackButtonRoute().equals("SHOP")) {
			btnSellItem = new JButton("Sell");
			btnSellItem.setEnabled(false);
			btnSellItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int inventorySize = getGameEnvironment().getInventoryUI().size();
					for (int i = 0; i < (int)comboBoxNumItems.getSelectedItem(); i++) {
						getGameEnvironment().sellItem(listInventory.getSelectedValue().get(0));
					}
					lblGold.setText("Gold: " + getGameEnvironment().getPlayer().getGoldBalance());
					if (inventorySize != getGameEnvironment().getInventoryUI().size()) {
						listInventory.clearSelection();
						inventoryListModel.removeAllElements();
						inventoryListModel.addAll(getGameEnvironment().getInventoryUI());
					}
					getParentComponent().repaint();
					sellDisplay();
				}
				
		});
			btnSellItem.setBounds(419, 358, 105, 42);
			container.add(btnSellItem);
			listOptionButtons.add(btnSellItem);
		} else {
			btnUseItem = new JButton("Use Item");
			btnUseItem.setEnabled(false);
			btnUseItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int inventorySize = getGameEnvironment().getPlayer().inventoryNumItems();
					if (getBackButtonRoute().equals("PARTY")) {
						getGameEnvironment().useItem(selectedMonster, listInventory.getSelectedValue().get(0));
						if (inventorySize != getGameEnvironment().getPlayer().inventoryNumItems()) {
							listInventory.clearSelection();
							inventoryListModel.removeAllElements();
							inventoryListModel.addAll(getGameEnvironment().getInventoryUI());
						}
						setTextLabelMonster();
					} else if (getBackButtonRoute().equals("BATTLE")) {
						Item item = listInventory.getSelectedValue().get(0);
						getGameEnvironment().useItem(selectedMonster, listInventory.getSelectedValue().get(0));
						if (inventorySize != getGameEnvironment().getPlayer().inventoryNumItems()) { 
							getGameEnvironment().setSelectedObject(item);
							getGameEnvironment().transitionScreen(getBackButtonRoute(), "INVENTORY");
						}
					} else {
						getGameEnvironment().setSelectedObject(listInventory.getSelectedValue().get(0));
						getGameEnvironment().transitionScreen("PARTY", "INVENTORY");
						
					}
					
				}
			});

			btnUseItem.setBounds(419, 358, 105, 42);
			container.add(btnUseItem);
			listOptionButtons.add(btnUseItem);
		}	
	}
	
	/**
	 * Creates the list representing the player's inventory and adds it to the container.
	 * 
	 * @param container The container to add the list to
	 */
	private void addListInventory(Container container) {
		
		inventoryListModel = new DefaultListModel<ArrayList<Item>>();
		// Add the existing difficulties to the ListModel
		inventoryListModel.addAll(getGameEnvironment().getInventoryUI());
		
		listInventory = new JList<ArrayList<Item>>(inventoryListModel);
		listInventory.setCellRenderer(new InventoryRenderer());
		listInventory.setVisibleRowCount(-1);
		listInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listInventory.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listInventory.setBackground(Color.WHITE);
		listInventory.addListSelectionListener(e -> {
			for (AbstractButton button: listOptionButtons) {
				button.setEnabled(listInventory.getSelectedValue()!=null);
				if (getBackButtonRoute().equals("SHOP")) {
					sellDisplay();
				}
			}
		});
		if (selectedMonster!=null || getBackButtonRoute().equals("SHOP")) {
			listInventory.setBounds(10, 65, 514, 247);
		} else {
			listInventory.setBounds(10, 65, 514, 282);
		}
		container.add(listInventory);
	}
	
	/**
	 * Creates the combo box to select number of items to sell and adds it to the container.
	 * 
	 * @param container The container to add the combo box to
	 */
	private void addComboBox(Container container) {
		comboBoxNumItems = new JComboBox<Integer>();
		comboBoxNumItems.setVisible(false);
		comboBoxNumItems.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxNumItems.getSelectedItem()!=null) {
					int totalPrice = listInventory.getSelectedValue().get(0).getSellPrice() * (int)comboBoxNumItems.getSelectedItem();
					lblSellPrice.setText("Sell Price: " + totalPrice);
				}
			}
		});
		comboBoxNumItems.setBounds(337, 328, 56, 22);
		container.add(comboBoxNumItems);
	}
	
	/**
	 * Displays the sell item graphic.
	 */
	private void sellDisplay() {
		
		comboBoxNumItems.setVisible(listInventory.getSelectedValue()!=null);
		if (listInventory.getSelectedValue()!=null) {
			comboBoxNumItems.removeAllItems();
			for (int i = 1; i <= listInventory.getSelectedValue().size(); i++) {
				comboBoxNumItems.addItem(i);
			}
			comboBoxNumItems.setSelectedIndex(0);
			lblSellPrice.setText("Sell Price: " + listInventory.getSelectedValue().get(0).getSellPrice());
		}
		lblSellPrice.setVisible(listInventory.getSelectedValue()!=null);
		lblSellAmount.setVisible(listInventory.getSelectedValue()!=null);
		btnSellItem.setEnabled(listInventory.getSelectedValue()!=null);
	}
}
