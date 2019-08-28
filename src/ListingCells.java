import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ListingCells extends JLabel implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		if(value instanceof JPanel)
		{	
			Component component = (Component) value;
			if(index%2==0)
				component.setBackground (isSelected ? UIManager.getColor("Table.focusCellForeground") : Color.white);
			else 
				component.setBackground(isSelected ? UIManager.getColor("Table.focusCellForeground") :Color.LIGHT_GRAY);
			return component;
		}
		
	    else
	    {
	      // TODO - I get one String here when the JList is first rendered; proper way to deal with this?
	      //System.out.println("Got something besides a JPanel: " + value.getClass().getCanonicalName());
	      return new JLabel("");
	    }
	}
}	

