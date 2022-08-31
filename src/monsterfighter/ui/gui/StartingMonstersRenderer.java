package monsterfighter.ui.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import monsterfighter.core.Monster;

/**
 * Custom renderer for the starting monsters JList in a {@link SetupScreen}.
 * @see <a href="https://stackoverflow.com/a/70007594">Custom List Cell Renderer</a>
 */
public class StartingMonstersRenderer implements ListCellRenderer<Monster>{
	
	// The label that will be displayed 
	private final JLabel label;
	
	/**
	 * Constructs the starting monsters renderer
	 */
	public StartingMonstersRenderer() {
		this.label = new JLabel();
		label.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Monster> list, Monster monster, int index,
			boolean isSelected, boolean cellHasFocus) {
        label.setText(monster.basicDescription());

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

