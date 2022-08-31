package monsterfighter.ui.gui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import monsterfighter.core.Item;

/**
 * Custom renderer for the inventory JList in a {@link InventoryScreen}.
 * @see <a href="https://stackoverflow.com/a/70007594">Custom List Cell Renderer</a>
 */
public class InventoryRenderer implements ListCellRenderer<ArrayList<Item>>{
	
	// The label that will be displayed 
	private final JLabel label;
	
	/**
	 * Constructs the inventory renderer
	 */
	public InventoryRenderer() {
		this.label = new JLabel();
		label.setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ArrayList<Item>> list, ArrayList<Item> items,
			int index, boolean isSelected, boolean cellHasFocus) {
		
        label.setText("[" + items.size() + "] " + items.get(0).toString());

        if (isSelected) {
            label.setBackground(list.getSelectionBackground());
            label.setForeground(list.getSelectionForeground());
        } else {
            label.setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }

        return label;
	}

}
