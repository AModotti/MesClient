package ide.main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableButtonRenderer extends JButton implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public TableButtonRenderer() {

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		if (isSelected) {
			if(value != null){
				if(value.equals("ING")){
					setBackground(Color.RED);
					setForeground(Color.BLACK);
				}else if(value.equals("ATT")){
					setBackground(Color.YELLOW);
					setForeground(Color.BLACK);
				}else if(value.equals("LAV")){
					setBackground(new Color(34, 139, 34));
					setForeground(Color.WHITE);
				}else if(value.equals("SOS")){
					setBackground(Color.GRAY);
					setForeground(Color.YELLOW);
				}else if(value.equals("USC")){
					setBackground(Color.GRAY);
					setForeground(Color.BLACK);
				}
			}
		}else{
			if(value != null){
				if(value.equals("ING")){
					setBackground(Color.RED);
					setForeground(Color.BLACK);
				}else if(value.equals("ATT")){
					setBackground(Color.YELLOW);
					setForeground(Color.BLACK);
				}else if(value.equals("LAV")){
					setBackground(new Color(34, 139, 34));
					setForeground(Color.WHITE);
				}else if(value.equals("SOS")){
					setBackground(Color.GRAY);
					setForeground(Color.YELLOW);
				}else if(value.equals("USC")){
					setBackground(Color.GRAY);
					setForeground(Color.BLACK);
				}
			}
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
	
}
