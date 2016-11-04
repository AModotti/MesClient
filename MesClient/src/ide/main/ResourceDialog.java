package ide.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import bin.Branch;
import bin.IpAddress;
import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Phase;
import bin.Settings;
import bin.WorkCenter;
import bin.WorkOrder;
import events.resourcedialog.EventsCambioLavorazioneResourceDialog;
import events.resourcedialog.EventsFineLavorazioneResourceDialog;

import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResourceDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtCentro;
	private JTextField txtRisorsa;
	private JTextField txtStato;
	private JTextField txtData;
	private JTextField txtBranch;
	private JTextField txtReparto;
	private JTextField txtCentroOperazione;
	private JTextField txtCommessa;
	private JTextField txtOdl;
	private JTextField txtOperazione;
	private JTextField txtArticolo;
	private JTextField txtOperatoreAggregato;
	private JButton btnCambioLavorazione;
	private JButton btnFineLavorazione;
	private TableSituazioneMacchine tm;
	private String machine;
	private String operator;
	private String branch;
	private String odl;
	private String phase;
	private String stato;

	public ResourceDialog(TableSituazioneMacchine tm) {
		
		this.tm = tm;
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		setBounds(100, 100, 750, 690);
		getContentPane().setLayout(null);
		
		JPanel pnlRisorsa = new JPanel();
		pnlRisorsa.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Stato Risorsa:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlRisorsa.setBounds(10, 11, 724, 630);
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
		txtCentro.setBounds(169, 21, 545, 30);
		pnlRisorsa.add(txtCentro);
		txtCentro.setColumns(10);
		
		JPanel pnlInformazioni = new JPanel();
		pnlInformazioni.setBorder(new TitledBorder(null, "Informazioni:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformazioni.setBounds(10, 64, 704, 490);
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
		txtRisorsa.setBounds(236, 27, 458, 30);
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
		txtStato.setBounds(236, 68, 458, 30);
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
		txtData.setBounds(236, 109, 458, 30);
		pnlInformazioni.add(txtData);
		
		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBranch.setBounds(10, 191, 216, 30);
		pnlInformazioni.add(lblBranch);
		
		txtBranch = new JTextField();
		txtBranch.setForeground(Color.BLUE);
		txtBranch.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtBranch.setEditable(false);
		txtBranch.setColumns(10);
		txtBranch.setBounds(236, 191, 458, 30);
		pnlInformazioni.add(txtBranch);
		
		JLabel lblReparto = new JLabel("Reparto:");
		lblReparto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReparto.setBounds(10, 150, 216, 30);
		pnlInformazioni.add(lblReparto);
		
		txtReparto = new JTextField();
		txtReparto.setForeground(Color.BLUE);
		txtReparto.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtReparto.setEditable(false);
		txtReparto.setColumns(10);
		txtReparto.setBounds(236, 150, 458, 30);
		pnlInformazioni.add(txtReparto);
		
		JLabel lblCentroOperazione = new JLabel("Centro Operazione:");
		lblCentroOperazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCentroOperazione.setBounds(10, 232, 216, 30);
		pnlInformazioni.add(lblCentroOperazione);
		
		txtCentroOperazione = new JTextField();
		txtCentroOperazione.setForeground(Color.BLUE);
		txtCentroOperazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCentroOperazione.setEditable(false);
		txtCentroOperazione.setColumns(10);
		txtCentroOperazione.setBounds(236, 232, 458, 30);
		pnlInformazioni.add(txtCentroOperazione);
		
		txtCommessa = new JTextField();
		txtCommessa.setForeground(Color.BLUE);
		txtCommessa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtCommessa.setEditable(false);
		txtCommessa.setColumns(10);
		txtCommessa.setBounds(236, 275, 458, 30);
		pnlInformazioni.add(txtCommessa);
		
		JLabel lblCommessa = new JLabel("Commessa:");
		lblCommessa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommessa.setBounds(10, 273, 216, 32);
		pnlInformazioni.add(lblCommessa);
		
		JLabel lblOdl = new JLabel("Work Order:");
		lblOdl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOdl.setBounds(10, 316, 216, 30);
		pnlInformazioni.add(lblOdl);
		
		txtOdl = new JTextField();
		txtOdl.setForeground(Color.BLUE);
		txtOdl.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOdl.setEditable(false);
		txtOdl.setColumns(10);
		txtOdl.setBounds(236, 316, 458, 30);
		pnlInformazioni.add(txtOdl);
		
		JLabel lblOperazione = new JLabel("Operazione:");
		lblOperazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperazione.setBounds(10, 357, 216, 30);
		pnlInformazioni.add(lblOperazione);
		
		txtOperazione = new JTextField();
		txtOperazione.setForeground(Color.BLUE);
		txtOperazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOperazione.setEditable(false);
		txtOperazione.setColumns(10);
		txtOperazione.setBounds(236, 357, 458, 30);
		pnlInformazioni.add(txtOperazione);
		
		JLabel lblArticolo = new JLabel("Articolo:");
		lblArticolo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArticolo.setBounds(10, 398, 216, 30);
		pnlInformazioni.add(lblArticolo);
		
		txtArticolo = new JTextField();
		txtArticolo.setForeground(Color.BLUE);
		txtArticolo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtArticolo.setEditable(false);
		txtArticolo.setColumns(10);
		txtArticolo.setBounds(236, 398, 458, 30);
		pnlInformazioni.add(txtArticolo);
		
		txtOperatoreAggregato = new JTextField();
		txtOperatoreAggregato.setForeground(Color.BLUE);
		txtOperatoreAggregato.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOperatoreAggregato.setEditable(false);
		txtOperatoreAggregato.setColumns(10);
		txtOperatoreAggregato.setBounds(236, 441, 458, 30);
		pnlInformazioni.add(txtOperatoreAggregato);
		
		JLabel lblOperatoreAggregato = new JLabel("Operatore Aggregato:");
		lblOperatoreAggregato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperatoreAggregato.setBounds(10, 439, 216, 32);
		pnlInformazioni.add(lblOperatoreAggregato);
		
		btnCambioLavorazione = new JButton("Cambio Lavorazione");
		btnCambioLavorazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCambioLavorazione.setBounds(10, 565, 270, 54);
		pnlRisorsa.add(btnCambioLavorazione);
		
		btnFineLavorazione = new JButton("Fine Lavorazione");
		btnFineLavorazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFineLavorazione.setBounds(290, 565, 270, 54);
		pnlRisorsa.add(btnFineLavorazione);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChiudi.setBounds(570, 565, 144, 54);
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
		
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
        	
        	sql = "SELECT TRIM(IMUKID),"
    	        + "       TRIM(ISALPH),"
    	        + "       IFNULL(USYQ10USST,0),"
    	        + "       IMAN8,"
    	        + "       IFNULL(TRIM(USMMCU),' ')," 
    	        + "       IFNULL(USDOCO,' '),"
    	        + "       IFNULL((SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = USDOCO),' '), "
    	        + "       IFNULL(USOPSQ,' '),"
    	        + "       IFNULL(DATE_FORMAT(CONVERT_TZ(USDTUP,'UTC','SYSTEM'),'%d/%m/%Y %H.%i.%s'),'NESSUN EVENTO REGISTRATO'),"
    	        + "       IFNULL(USURAB,' '),"
    	        + "       TRIM(ISDEPT),"  
    	        + "       TRIM(USALPH),"    
    	        + "       TRIM(USALPH1),"
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
    	        + " WHERE TRIM(IMUKID) = ? "
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
	    	    + "       'NESSUN EVENTO REGISTRATO' "
	    	    + "       ELSE "
	    	    + "       TO_CHAR((CAST((FROM_TZ(CAST(TO_DATE(TO_CHAR(USDTUP,'DD/MM/YYYY HH24.MI.SS'),'DD/MM/YYYY HH24.MI.SS') AS TIMESTAMP),'GMT') AT TIME ZONE 'Europe/Rome') AS DATE)),'DD/MM/YYYY HH24.MI.SS') " 
	    	    + "       END,"
	    	    + "       DECODE(USURAB,NULL,' ',USURAB),"
	    	    + "       TRIM(ISDEPT),"    
	    	    + "       TRIM(USALPH),"    
	    	    + "       TRIM(USALPH1),"
	    	    + "       DECODE(TRIM(USLITM),NULL,' ',TRIM(USLITM)),"  
	    	    + "       DECODE((SELECT TRIM(IMDSC1) FROM F4101 WHERE IMITM = (SELECT WAITM FROM F4801 WHERE WADOCO = USDOCO)),NULL,' ',(SELECT TRIM(IMDSC1) FROM F4101 WHERE IMITM = (SELECT WAITM FROM F4801 WHERE WADOCO = USDOCO))),"
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
	    	    + " WHERE TRIM(IMUKID) = ? "
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

	            	txtCentro.setText(" " + rs.getString(1) + " - " + rs.getString(2));
	            	machine = rs.getString(4);
	            	txtRisorsa.setText(" " + rs.getString(4) + " - " + rs.getString(12));
	            	stato = rs.getString(3);
	            	if(stato.equals("0")){
	            		stato = "USC";
	            		descrizionestato = "RISORSA IN STATO DI FERMO SENZA PRESIDIO";
	            	}else if(stato.equals("1")){
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
	            	if(rs.getString(22) != null){
	            		txtCentroOperazione.setText(" " + rs.getString(21) + " - " + rs.getString(22));
	            	}else{
	            		txtCentroOperazione.setText(" ");
	            	}
	            	branch = rs.getString(5);
	            	if(!rs.getString(5).equals(" ")){
	            		txtBranch.setText(" " + rs.getString(5) + " - " + rs.getString(20));
	            	}else{
	            		txtBranch.setText(" ");
	            	}
	            	if((!rs.getString(17).equals(" "))&&(!rs.getString(17).equals("0"))){
	            		txtCommessa.setText(" " + rs.getString(17) + " - " + rs.getString(18));
	            	}else{
	            		txtCommessa.setText(" ");
	            	}
	            	odl = rs.getString(6);
	            	if((!rs.getString(6).equals(" "))&&(!rs.getString(6).equals("0"))){
	            		txtOdl.setText(" " + rs.getString(6) + " - " + rs.getString(7));
	            	}else{
	            		txtOdl.setText(" ");
	            	}
	            	phase =  rs.getString(8);
	            	if((!rs.getString(8).equals(" "))&&(!rs.getString(8).equals("0"))){
	            		txtOperazione.setText(" " + rs.getString(8) + " - " + rs.getString(16));
	            	}else{
	            		txtOperazione.setText(" ");
	            	}
	            	if(!rs.getString(14).equals("")){
	            		txtArticolo.setText(" " + rs.getString(14) + " - " + rs.getString(15));
	            	}else{
	            		txtArticolo.setText(" ");
	            	}
	            	operator = rs.getString(10);
	            	txtOperatoreAggregato.setText(" " + rs.getString(10) + " - " + rs.getString(13));
	            	
	            }

	        } catch (SQLException Sqlex) {
	        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	        	LogWriter.write("[E] Errore in classe: [ ResourceDialog.GetData() ]");
	        	LogWriter.write("[E] " + Sqlex.getMessage());
	        	LogWriter.write("[E] " + sql);
	        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	        	Notifications notification = new Notifications("[ ResourceDialog.getData() ]",Sqlex.getMessage(),sql);
	        	notification.setModal(true);
	        	notification.setVisible(true);

	        } finally {
	 
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	                	LogWriter.write("[E] Errore in classe: [ ResourceDialog.getData() ]");
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
	                	LogWriter.write("[E] Errore in classe: [ ResourceDialog.getData() ]");
	                	LogWriter.write("[E] Chiusura Connection");
	                	LogWriter.write("[E] " + e.getMessage());
	                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	                }
	            }

	        }

		if(stato.equals("ATT")){
			btnCambioLavorazione.setText("INIZIO LAVORAZIONE");
			btnCambioLavorazione.setBackground(new Color(34, 139, 34));
			btnCambioLavorazione.setForeground(Color.WHITE);
			btnFineLavorazione.setText("FINE LAVORAZIONE");
			btnFineLavorazione.setBackground(Color.RED);
		}else if(stato.equals("LAV")){	 
			btnCambioLavorazione.setText("INIZIO ATTREZZAGGIO");
			btnCambioLavorazione.setBackground(Color.YELLOW);
			btnCambioLavorazione.setForeground(Color.BLACK);
			btnFineLavorazione.setText("FINE LAVORAZIONE");
			btnFineLavorazione.setBackground(Color.RED);
		}else{
			btnCambioLavorazione.setVisible(false);
			btnFineLavorazione.setVisible(false);
		}
		
		IpAddress ipadress = new IpAddress();
		
		WorkCenter wc = new WorkCenter(ipadress);
		WorkOrder wo = new WorkOrder(odl);
		Machine mc = new Machine(machine);
		Operator op = new Operator(operator);
		Branch br = new Branch(branch);
		Phase ph = new Phase(phase);
		
		
		//EVENTI PULSANTE CAMBIO LAVORAZIONE
		btnCambioLavorazione.addActionListener(new EventsCambioLavorazioneResourceDialog(tm, this, wc, wo, mc, op, br, ph, stato));
				
		//EVENTI PULSANTE FINE LAVORAZIONE
		btnFineLavorazione.addActionListener(new EventsFineLavorazioneResourceDialog(tm, this, wc, wo, mc, op, br, ph, stato));
		
	}
}
