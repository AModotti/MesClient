package ide.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bin.IpAddress;
import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Phase;
import bin.Settings;
import bin.WorkCenter;
import events.moresourcedialog.EventsCambioLavorazioneMoResourceDialog;
import events.moresourcedialog.EventsFineLavorazioneMoResourceDialog;

import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

public class MoResourceDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtCentro;
	private JTextField txtRisorsa;
	private JTextField txtStato;
	private JTextField txtData;
	private JTextField txtReparto;
	private JTextField txtCentroOperazione;
	private JTextField txtOperazione;
	private JTextField txtOperatoreAggregato;
	private JButton btnCambioLavorazione;
	private JButton btnFineLavorazione;
	private TableSituazioneMacchine tm;;
	private String machine;
	private String operator;
	private String phase;
	private String stato;
	private JTable tableCommessaWoArticolo;
	
    private String[] columnNames = { "Commessa", "Descrizione Commessa", "OdL", "Articolo", "Descrizione Articolo" };
	
	private Object[][] datatest = {
									{ "0001", "AA", "BB", "CC", "DD" },
									{ "0002", "FF", "GG", "HH", "II" },
									{ "0003", "MM", "OO", "PP", "QQ" },
					  			  };

	
	public MoResourceDialog(TableSituazioneMacchine tm) {
		
		this.tm = tm;
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		setBounds(100, 100, 880, 695);
		getContentPane().setLayout(null);
		
		JPanel pnlRisorsa = new JPanel();
		pnlRisorsa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Stato Risorsa:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlRisorsa.setBounds(10, 11, 854, 630);
		getContentPane().add(pnlRisorsa);
		pnlRisorsa.setLayout(null);
		
		JLabel lblCentro = new JLabel("Centro di Lavoro:");
		lblCentro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCentro.setBounds(10, 21, 149, 30);
		pnlRisorsa.add(lblCentro);
		
		txtCentro = new JTextField();
		txtCentro.setForeground(Color.BLUE);
		txtCentro.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCentro.setEditable(false);
		txtCentro.setBounds(169, 21, 675, 30);
		pnlRisorsa.add(txtCentro);
		txtCentro.setColumns(10);
		
		JPanel pnlInformazioni = new JPanel();
		pnlInformazioni.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informazioni Lavorazione Multi Ordine:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlInformazioni.setBounds(10, 64, 834, 490);
		pnlRisorsa.add(pnlInformazioni);
		pnlInformazioni.setLayout(null);
		
		JLabel lblRisorsa = new JLabel("Risorsa:");
		lblRisorsa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRisorsa.setBounds(10, 27, 216, 30);
		pnlInformazioni.add(lblRisorsa);
		
		txtRisorsa = new JTextField();
		txtRisorsa.setForeground(Color.BLUE);
		txtRisorsa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtRisorsa.setEditable(false);
		txtRisorsa.setBounds(236, 27, 588, 30);
		pnlInformazioni.add(txtRisorsa);
		txtRisorsa.setColumns(10);
		
		JLabel lblStato = new JLabel("Stato Risorsa:");
		lblStato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStato.setBounds(10, 68, 216, 30);
		pnlInformazioni.add(lblStato);
		
		txtStato = new JTextField();
		txtStato.setForeground(Color.BLUE);
		txtStato.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtStato.setEditable(false);
		txtStato.setColumns(10);
		txtStato.setBounds(236, 68, 588, 30);
		pnlInformazioni.add(txtStato);
		
		JLabel lblData = new JLabel("Data e ora evento:");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setBounds(10, 109, 216, 30);
		pnlInformazioni.add(lblData);
		
		txtData = new JTextField();
		txtData.setForeground(Color.BLUE);
		txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(236, 109, 588, 30);
		pnlInformazioni.add(txtData);
		
		JLabel lblReparto = new JLabel("Reparto:");
		lblReparto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReparto.setBounds(10, 150, 216, 30);
		pnlInformazioni.add(lblReparto);
		
		txtReparto = new JTextField();
		txtReparto.setForeground(Color.BLUE);
		txtReparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtReparto.setEditable(false);
		txtReparto.setColumns(10);
		txtReparto.setBounds(236, 150, 588, 30);
		pnlInformazioni.add(txtReparto);
		
		JLabel lblCentroOperazione = new JLabel("Centro Operazione:");
		lblCentroOperazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCentroOperazione.setBounds(10, 191, 216, 30);
		pnlInformazioni.add(lblCentroOperazione);
		
		txtCentroOperazione = new JTextField();
		txtCentroOperazione.setForeground(Color.BLUE);
		txtCentroOperazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCentroOperazione.setEditable(false);
		txtCentroOperazione.setColumns(10);
		txtCentroOperazione.setBounds(236, 191, 588, 30);
		pnlInformazioni.add(txtCentroOperazione);
		
		JLabel lblOperazione = new JLabel("Operazione:");
		lblOperazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperazione.setBounds(10, 275, 216, 30);
		pnlInformazioni.add(lblOperazione);
		
		txtOperazione = new JTextField();
		txtOperazione.setForeground(Color.BLUE);
		txtOperazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOperazione.setEditable(false);
		txtOperazione.setColumns(10);
		txtOperazione.setBounds(236, 275, 588, 30);
		pnlInformazioni.add(txtOperazione);
		
		txtOperatoreAggregato = new JTextField();
		txtOperatoreAggregato.setForeground(Color.BLUE);
		txtOperatoreAggregato.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOperatoreAggregato.setEditable(false);
		txtOperatoreAggregato.setColumns(10);
		txtOperatoreAggregato.setBounds(236, 234, 588, 30);
		pnlInformazioni.add(txtOperatoreAggregato);
		
		JLabel lblOperatoreAggregato = new JLabel("Operatore Aggregato:");
		lblOperatoreAggregato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperatoreAggregato.setBounds(10, 232, 216, 32);
		pnlInformazioni.add(lblOperatoreAggregato);
		
		JScrollPane scpCommessaWoArticolo = new JScrollPane();
		scpCommessaWoArticolo.setBounds(10, 316, 814, 163);
		tableCommessaWoArticolo = new JTable();
		tableCommessaWoArticolo.setBounds(236, 273, 508, 114);
			
		DefaultTableModel model = new DefaultTableModel(datatest, columnNames);
		
		tableCommessaWoArticolo.setModel(model);
		scpCommessaWoArticolo.setViewportView(tableCommessaWoArticolo);
		pnlInformazioni.add(scpCommessaWoArticolo);
		
		btnCambioLavorazione = new JButton("Cambio Lavorazione Multi O.");
		btnCambioLavorazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCambioLavorazione.setBounds(10, 565, 340, 54);
		pnlRisorsa.add(btnCambioLavorazione);
		
		btnFineLavorazione = new JButton("Fine Lavorazione  Multi O.");
		btnFineLavorazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFineLavorazione.setBounds(360, 565, 340, 54);
		pnlRisorsa.add(btnFineLavorazione);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChiudi.setBounds(709, 565, 135, 54);
		pnlRisorsa.add(btnChiudi);
				
		//EVENTI PULSANTE CHIUDI FINESTRA
		btnChiudi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}
	
	public void GetData(WorkCenter workcenter,String resource){
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String descrizionestato = ""; 
        String sql = "";
        ArrayList<ArrayList<String>> datalist = new ArrayList<ArrayList<String>>();
        ArrayList<String> OdlInseriti = new ArrayList<String>();
        		        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
        	
        	sql = "SELECT TRIM(IMUKID),"
	            + "       (ISALPH),"
	            + "       IFNULL(USYQ10USST,0),"
	            + "       IMAN8,"
	            + "       IFNULL(TRIM(USMMCU),' '),"
	            + "       IFNULL(USDOCO,' '),"
	            + "       IFNULL((SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO),' '),"
	            + "       IFNULL(USOPSQ,' '),"
	            + "       IFNULL(DATE_FORMAT(CONVERT_TZ(USDTUP,'UTC','SYSTEM'),'%d/%m/%Y %H.%i.%s'),'NESSUN EVENTO REGISTRATO'),"
	            + "       IFNULL(USURAB,' '),"
	            + "       TRIM(ISDEPT),"  
	            + "       TRIM(IMALPH),"    
	            + "       TRIM(IMALPH),"
	            + "       IFNULL(TRIM(USLITM),' '),"
	            + "       IFNULL((SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO),' ')," 
	            + "       IFNULL(TRIM(USOPSQDSC1),' '),"
	            + "       IFNULL(USPRJM,' '),"
	            + "       IFNULL((SELECT TRIM(ABALPH) FROM F0101 WHERE ABAN8 = (SELECT WAAN8 FROM F4801 WHERE WADOCO = USPRJM)),' '),"
	            + "       TRIM(DPALPH),"
	            + "       IFNULL((SELECT TRIM(MCDL01) FROM F0006 WHERE MCMCU = USMMCU),' '),"
	            + "       IFNULL((SELECT TRIM(WLMCU) FROM F3112 WHERE WLDOCO = USDOCO AND WLOPSQ = USOPSQ),' '),"
	            + "       (SELECT TRIM(MCDL01) FROM F0006 WHERE MCMCU = (SELECT WLMCU FROM F3112 WHERE WLDOCO = USDOCO AND WLOPSQ = USOPSQ)) "
	            + "  FROM F55FQ10008 "
	            + "  LEFT JOIN F55FQ10006 ON (ISUKID = IMUKID) "
	            + "  LEFT JOIN F55FQ10009 ON (ISDEPT = DPDEPT)"
	            + "  LEFT JOIN F55FQ10002 ON (IMAN8 = USAN8 AND IMUKID = USISL) " 
	            + " WHERE USYQ10USST IN ('3','4','5') "
	            + "   AND TRIM(IMUKID) = ? "
	            + "   AND IMAN8 = ? "
	            + " ORDER BY USDOCO";
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "SELECT TRIM(IMUKID),"
	        	    + "       (ISALPH),"
	        	    + "       DECODE(USYQ10USST,NULL,0,USYQ10USST),"
	        	    + "       IMAN8,"
	        	    + "       DECODE(TRIM(USMMCU),NULL,' ',TRIM(USMMCU))," 
	        	    + "       DECODE(USDOCO,NULL,' ',USDOCO),"
	        	    + "       DECODE((SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO),NULL,' ',(SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO))," 
	        	    + "       DECODE(USOPSQ,NULL,' ',USOPSQ),"
	        	    + "       CASE "
	        	    + "       WHEN USDTUP IS NULL THEN "
	        	    + "       	'NESSUN EVENTO REGISTRATO' "
	        	    + "       ELSE "
	        	    + "       	TO_CHAR((CAST((FROM_TZ(CAST(TO_DATE(TO_CHAR(USDTUP,'DD/MM/YYYY HH24.MI.SS'),'DD/MM/YYYY HH24.MI.SS') AS TIMESTAMP),'GMT') AT TIME ZONE 'Europe/Rome') AS DATE)),'DD/MM/YYYY HH24.MI.SS') " 
	        	    + "       END,"
	        	    + "       DECODE(USURAB,NULL,' ',USURAB),"
	        	    + "       TRIM(ISDEPT),"    
	        	    + "       TRIM(IMALPH),"    
	        	    + "       TRIM(IMALPH),"
	        	    + "       DECODE(TRIM(USLITM),NULL,' ',TRIM(USLITM)),"  
	        	    + "       DECODE((SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO),NULL,' ',(SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO)),"
	        	    + "       DECODE(TRIM(USOPSQDSC1),NULL,' ',TRIM(USOPSQDSC1)),"
	        	    + "       DECODE(USPRJM,NULL,' ',USPRJM),"
	        	    + "       DECODE((SELECT TRIM(ABALPH) FROM F0101 WHERE ABAN8 = (SELECT WAAN8 FROM F4801 WHERE WADOCO = USPRJM)),NULL,' ',(SELECT TRIM(ABALPH) FROM F0101 WHERE ABAN8 = (SELECT WAAN8 FROM F4801 WHERE WADOCO = USPRJM))),"    
	        	    + "       TRIM(DPALPH),"
	        	    + "       DECODE((SELECT TRIM(MCDL01) FROM F0006 WHERE MCMCU = USMMCU),NULL,' ',(SELECT TRIM(MCDL01) FROM F0006 WHERE MCMCU = USMMCU)),"
	        	    + "       DECODE((SELECT TRIM(WLMCU) FROM F3112 WHERE WLDOCO = USDOCO AND WLOPSQ = USOPSQ),NULL,' ',(SELECT TRIM(WLMCU) FROM F3112 WHERE WLDOCO = USDOCO AND WLOPSQ = USOPSQ)),"
	        	    + "       (SELECT TRIM(MCDL01) FROM F0006 WHERE MCMCU = (SELECT WLMCU FROM F3112 WHERE WLDOCO = USDOCO AND WLOPSQ = USOPSQ)) "
	        	    + "  FROM F55FQ10008 "
	        	    + "  LEFT JOIN F55FQ10006 ON (ISUKID = IMUKID) "
	        	    + "  LEFT JOIN F55FQ10009 ON (ISDEPT = DPDEPT)"
	        	    + "  LEFT JOIN F55FQ10002 ON (IMAN8 = USAN8 AND IMUKID = USISL) " 
	        	    + " WHERE USYQ10USST IN ('3','4','5') "
	        	    + "   AND TRIM(IMUKID) = ? "
	        	    + "   AND IMAN8 = ? "
	        	    + " ORDER BY USDOCO";
        
        }
		
		try {
			 
	            conn = Settings.getDbConnection();
	            ps = conn.prepareStatement(sql);

	            ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
	            ps.setString(2, resource);
	            
	            rs = ps.executeQuery();

	            while (rs.next()) {
	            	
	            	ArrayList<String> innerdata = new ArrayList<String>();
	            	
	            	txtCentro.setText(" " + rs.getString(1) + " - " + rs.getString(2));
	            	machine = rs.getString(4);
	            	txtRisorsa.setText(" " + rs.getString(4) + " - " + rs.getString(12));
	            	stato = rs.getString(3);
	            	if(stato.equals("1")){
	            		stato = "USC";
	            		descrizionestato = "RISORSA IN STATO DI FERMO SENZA PRESIDIO";
	            	}else if(stato.equals("2")){
	            		stato = "ING";
	            		descrizionestato = "RISORSA IN STATO DI FERMO CON PRESIDIO";
	            	}else if(stato.equals("3")){
	            		stato = "ATT";
	            		descrizionestato = "RISORSA IN ATTREZZAGGIO";;
	            	}else if(stato.equals("4")){
	            		stato = "LAV";
	            		descrizionestato = "RISORSA IN LAVORAZIONE";
	            	}else if(stato.equals("5")){
	            		stato = "SOS";
	            		descrizionestato = "RISORSA IN STATO DI SOSPENSIONE ATTIVITA'";
	            	}
	            	txtStato.setText(" " + stato + " - " + descrizionestato);
	            	txtData.setText(" " + rs.getString(9));
	            	txtReparto.setText(" " + rs.getString(11) + " - " + rs.getString(19));
	            	if(rs.getString(21) != null){
	            		txtCentroOperazione.setText(" " + rs.getString(21) + " - " + rs.getString(22));
	            	}else{
	            		txtCentroOperazione.setText(" ");
	            	}
		            	
	            	OdlInseriti.add(rs.getString(6));
	            	
	            	if((stato.equals("ATT"))||(stato.equals("LAV"))){
	            		innerdata.add(rs.getString(17));
	            		innerdata.add(rs.getString(18));
	            		innerdata.add(rs.getString(6));
	            		innerdata.add(rs.getString(14));
	            		innerdata.add(rs.getString(15));
	            	}
	            	
	            	if(!rs.getString(8).equals("0")){
	            		phase =  rs.getString(8);
	            		txtOperazione.setText(" " + rs.getString(8) + " - " + rs.getString(16));
	            	}else{
	            		txtOperazione.setText("");
	            	}
	            	operator = rs.getString(10);
	            	txtOperatoreAggregato.setText(" " + rs.getString(10) + " - " + rs.getString(13));
	            	
	            	datalist.add(innerdata);
	            	
	            }

	            Object[][] data = new Object[datalist.size()][1];
	    		
	    		int w = 0, j = 0;
	    		for (ArrayList<String> row : datalist) {
	    			data[w] = new String[row.size()];
	    		    j = 0;
	    		    for (String str : row) {
	    		    	data[w][j] = str;
	    		        j++;
	    		    }
	    		    w++;
	    		}
	    		
	    		tableCommessaWoArticolo.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
	    		tableCommessaWoArticolo.setFont(new Font("Tahoma", Font.BOLD, 12));
	    		tableCommessaWoArticolo.setForeground(Color.BLUE);
	    		
	    		DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    		tableCommessaWoArticolo.setModel(model);
	    		
	    		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

	    		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    				
	    		tableCommessaWoArticolo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(0).setPreferredWidth(90);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);	
	    		tableCommessaWoArticolo.getColumnModel().getColumn(1).setPreferredWidth(240);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(2).setPreferredWidth(80);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(3).setPreferredWidth(140);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	    		tableCommessaWoArticolo.getColumnModel().getColumn(4).setPreferredWidth(240);
    		

	        } catch (SQLException Sqlex) {
	        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	        	LogWriter.write("[E] Errore in classe: [ MoResourceDialog.GetData() ]");
	        	LogWriter.write("[E] " + Sqlex.getMessage());
	        	LogWriter.write("[E] " + sql);
	        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	        	Notifications notification = new Notifications("[ MoResourceDialog.getData() ]",Sqlex.getMessage(),sql);
	        	notification.setModal(true);
	        	notification.setVisible(true);

	        } finally {
	 
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	                	LogWriter.write("[E] Errore in classe: [ MoResourceDialog.getData() ]");
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
	                	LogWriter.write("[E] Errore in classe: [ MoResourceDialog.getData() ]");
	                	LogWriter.write("[E] Chiusura Connection");
	                	LogWriter.write("[E] " + e.getMessage());
	                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	                }
	            }

	        }

		if(stato.equals("ATT")){
			btnCambioLavorazione.setText("INIZIO LAVORAZIONE MULTI O.");
			btnCambioLavorazione.setBackground(new Color(34, 139, 34));
			btnCambioLavorazione.setForeground(Color.WHITE);
			btnFineLavorazione.setText("FINE LAVORAZIONE MULTI O.");
			btnFineLavorazione.setBackground(Color.RED);
		}else if(stato.equals("LAV")){	 
			btnCambioLavorazione.setText("INIZIO ATTREZZAGGIO MULTI O.");
			btnCambioLavorazione.setBackground(Color.YELLOW);
			btnCambioLavorazione.setForeground(Color.BLACK);
			btnFineLavorazione.setText("FINE LAVORAZIONE MULTI O.");
			btnFineLavorazione.setBackground(Color.RED);
		}else{
			btnCambioLavorazione.setVisible(false);
			btnFineLavorazione.setVisible(false);
		}
		
		IpAddress ipadress = new IpAddress();
		
		WorkCenter wc = new WorkCenter(ipadress);
		Machine mc = new Machine(machine);
		Operator op = new Operator(operator);
		Phase ph = new Phase(phase);
				
		//EVENTI PULSANTE CAMBIO LAVORAZIONE
		btnCambioLavorazione.addActionListener(new EventsCambioLavorazioneMoResourceDialog(tm, this, wc, OdlInseriti, mc, op, ph, stato));
				
		//EVENTI PULSANTE FINE LAVORAZIONE
		btnFineLavorazione.addActionListener(new EventsFineLavorazioneMoResourceDialog(tm, this, wc, OdlInseriti, mc, op, ph, stato));
		
	}
}
