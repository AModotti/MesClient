package ide.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;

import bin.LogWriter;
import bin.Settings;
import bin.WorkCenter;

public class TableButtonEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;

	protected JButton button;
	private String label;
	private boolean isPushed;
	private JTable table;
	private JFrame frmEurollsMes;
	private WorkCenter workcenter;
	private TableSituazioneMacchine tm;

	public TableButtonEditor(JFrame frmEurollsMes, WorkCenter workcenter, JTable table, TableSituazioneMacchine tm, JCheckBox checkBox) {
		
		super(checkBox);
		this.table = table;
		this.frmEurollsMes = frmEurollsMes;
		this.workcenter = workcenter;
		this.tm = tm;
		button = new JButton();
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if(isSelected) {
			if(value != null){
				if(value.equals("ING")){
					button.setBackground(Color.RED);
					button.setForeground(Color.BLACK);
				}else if(value.equals("ATT")){
					button.setBackground(Color.YELLOW);
					button.setForeground(Color.BLACK);
				}else if(value.equals("LAV")){
					button.setBackground(new Color(34, 139, 34));
					button.setForeground(Color.WHITE);
				}else if(value.equals("SOS")){
					button.setBackground(Color.GRAY);
					button.setForeground(Color.YELLOW);
				}else if(value.equals("USC")){
					button.setBackground(Color.GRAY);
					button.setForeground(Color.BLACK);
				}
			}
		}else{
			if(value != null){
				if(value.equals("ING")){
					button.setBackground(Color.RED);
					button.setForeground(Color.BLACK);
				}else if(value.equals("ATT")){
					button.setBackground(Color.YELLOW);
					button.setForeground(Color.BLACK);
				}else if(value.equals("LAV")){
					button.setBackground(new Color(34, 139, 34));
					button.setForeground(Color.WHITE);
				}else if(value.equals("SOS")){
					button.setBackground(Color.GRAY);
					button.setForeground(Color.YELLOW);
				}else if(value.equals("USC")){
					button.setBackground(Color.GRAY);
					button.setForeground(Color.BLACK);
				}
			}
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		frmEurollsMes.setCursor(hourglassCursor);
		
		try {
			//CONTROLLO CHE IL DATABASE SIA RAGGIUNGIBILE
			String checkdbconnection = Settings.checkDbConnection();
			
			//SE NON E' RAGGIUNGIBILE
			if(checkdbconnection.equals("false")){
				Notifications notification = new Notifications("[ Settings.checkDbConnection() ]","Impossibile connettersi al Database","Non è stato possibile connettersi al Database a causa di un errore. Controllare la connettività o le impostazioni di rete.");
	        	notification.setModal(true);
	        	notification.setVisible(true);
	        	frmEurollsMes.setCursor(normalCursor);
			//SE E' RAGGIUNGIBILE	
			}else{
		
				if(isPushed) {
					if(!label.equals("")){
						if(table.getModel().getValueAt(table.getSelectedRow(),4).equals("Multi O.")){
							MoResourceDialog mord = new MoResourceDialog(tm);
							mord.setTitle("Risorsa: " + table.getModel().getValueAt(table.getSelectedRow(),1) + " - " + table.getModel().getValueAt(table.getSelectedRow(),2));
							String resource = table.getModel().getValueAt(table.getSelectedRow(),1).toString(); 
							mord.GetData(workcenter,resource);
							mord.setModal(true);
							mord.setVisible(true);
							frmEurollsMes.setCursor(normalCursor);
						}else{
							ResourceDialog rd = new ResourceDialog(tm);
							rd.setTitle("Risorsa: " + table.getModel().getValueAt(table.getSelectedRow(),1) + " - " + table.getModel().getValueAt(table.getSelectedRow(),2));
							String resource = table.getModel().getValueAt(table.getSelectedRow(),1).toString(); 
							rd.GetData(workcenter,resource);
							rd.setModal(true);
							rd.setVisible(true);
							frmEurollsMes.setCursor(normalCursor);
						}
					}
				}

			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ TableButtonEditor.getCellEditorValue() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
		
		isPushed = false;
		return new String(label);
	}

	@Override
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
