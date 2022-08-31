package monsterfighter.ui.gui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import monsterfighter.core.Purchasable;

/**
 * Custom renderer for the shop JList in a {@link ShopScreen}.
 * @see <a href="https://stackoverflow.com/a/70007594">Custom List Cell Renderer</a>
 */
public class ShopRenderer implements ListCellRenderer<ArrayList<Purchasable>>{

	// The label that will be displayed 
	private final JLabel label;
	
	/**
	 * Constructs the shop renderer
	 */
	public ShopRenderer() {
		this.label = new JLabel();
		label.setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ArrayList<Purchasable>> list,
			ArrayList<Purchasable> objects, int index, boolean isSelected, boolean cellHasFocus) {
		
		label.setText("[" + objects.size() + "] " + objects.get(0).buyDescription());

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
