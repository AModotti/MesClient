package ide.main;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bin.LogWriter;
import bin.Settings;
import bin.WorkCenter;

public class TableSituazioneMacchine extends JTable {

	private static final long serialVersionUID = 1L;
	private WorkCenter workcenter;
	private JFrame frmEurollsMes;
	private Vector<String> columnnames = new Vector<String>();
									  
	public TableSituazioneMacchine(JFrame frmEurollsMes, WorkCenter workcenter) {

		this.workcenter = workcenter;
		this.frmEurollsMes = frmEurollsMes;
		
		columnnames.addElement("Stato"); 
		columnnames.addElement("Risorsa");
		columnnames.addElement("Descrizione Risorsa");
		columnnames.addElement("Commessa");
		columnnames.addElement("OdL");
		columnnames.addElement("Oper.");
		
		DefaultTableModel model = new DefaultTableModel(null, columnnames);
		
		this.setModel(model);
		
		this.getColumn("Stato").setCellRenderer(new TableButtonRenderer());
		this.getColumn("Stato").setCellEditor(new TableButtonEditor(frmEurollsMes, workcenter, this, this, new JCheckBox()));
						
	}
	
	public void getData(){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;
		Vector<Object> data = new Vector<Object>();
		
		sql = "SELECT CASE " 
		    + "       	WHEN ADYQ10ACCD = 'S' THEN " 
			+ "       		CAST('ING' AS NCHAR(3)) "
		    + "       	WHEN ADSOS = '1' THEN " 
		    + "       		CAST('SOS' AS NCHAR(3)) "
			+ "      	ELSE " 
			+ "       		ADYQ10DECD " 
			+ "       END," 
			+ "       ADURRF,"
			+ "       IMAN8," 
			+ "       TRIM(IMALPH),"
			+ "       ADPRJM," 
			+ "       ADDOCO,"
			+ "       ADOPSQ," 
			+ "       ADURAT,"
            + "       ADSOS,"
            + "       TRIM(ADMMCU) "
			+ "  FROM F55FQ10008 " 
			+ "  LEFT JOIN F55FQ10001 ON (IMAN8 = ADAN8 AND IMUKID = ADISL)" 
			+ " WHERE TRIM(IMUKID) = ? "
			+ "   AND ADUKID IN (SELECT X.MAXID " 
			+ "                    FROM (SELECT MAX(ADUKID) AS MAXID,"
			+ "                         ADAN8," 
			+ "                         ADISL "
			+ "                    FROM F55FQ10001 " 
			+ "                   WHERE EXISTS(SELECT 'X' FROM F55FQ10008 WHERE IMAN8 = ADAN8 AND IMUKID = ADISL) "
			+ "                GROUP BY ADAN8,ADISL) X) "
            + " ORDER BY IMSEQ";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			data.removeAllElements();
			
			while (rs.next()) {
				
				Vector<Object> innerdata = new Vector<Object>();
				
				if(rs.getString(1).equals("0")){
					innerdata.addElement("");
				}else{
					innerdata.addElement(rs.getString(1));
				}
				
				innerdata.addElement(rs.getString(3));
				innerdata.addElement(" " + rs.getString(4));
				
				if(rs.getString(5).equals("0")){
					if(rs.getString(2).trim().equals("M")){
						innerdata.addElement("Multi O.");
					}else{
						innerdata.addElement("");
					}
				}else{
					if((rs.getString(1).equals("ING"))||(rs.getString(1).equals("USC"))){
						innerdata.addElement("");
					}else{
						if(rs.getString(2).trim().equals("M")){
							innerdata.addElement("Multi O.");
						}else{
							innerdata.addElement(rs.getString(5));
						}
					}
				}
				if(rs.getString(6).equals("0")){
					innerdata.addElement("");
				}else{
					if((rs.getString(1).equals("ING"))||(rs.getString(1).equals("USC"))){
						innerdata.addElement("");
					}else{
						if(rs.getString(2).trim().equals("M")){
							innerdata.addElement("Multi O.");
						}else{
							innerdata.addElement(rs.getString(6));
						}
					}
				}
				if(rs.getString(7).equals("0")){
					innerdata.addElement("");
				}else{
					if((rs.getString(1).equals("ING"))||(rs.getString(1).equals("USC"))){
						innerdata.addElement("");
					}else{
						innerdata.addElement(rs.getString(7));
					}
				}

				data.addElement(innerdata);
				
			}
			

		} catch (SQLException Sqlex) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ TableSituazioneMacchine.GetData() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ TableSituazioneMacchine.getData() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TableSituazioneMacchine.getData() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TableSituazioneMacchine.getData() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}
				
		DefaultTableModel model = new DefaultTableModel(data, columnnames);
		this.setModel(model);
		
		this.getColumn("Stato").setCellRenderer(new TableButtonRenderer());
		this.getColumn("Stato").setCellEditor(new TableButtonEditor(frmEurollsMes,workcenter,this,this,new JCheckBox()));
				
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(80);
		this.getColumnModel().getColumn(1).setPreferredWidth(80);
		this.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		this.getColumnModel().getColumn(2).setPreferredWidth(320);
		this.getColumnModel().getColumn(3).setPreferredWidth(90);
		this.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		this.getColumnModel().getColumn(4).setPreferredWidth(70);
		this.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		this.getColumnModel().getColumn(5).setPreferredWidth(70);
		this.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		

		for (int i = 0; i < this.getRowCount(); i++) {
			this.setRowHeight(i, 25);
		}

		this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setForeground(Color.BLUE);
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		if(columnIndex == 0){
			return true;
		}else{
			return false;
		}
	}
	
}
