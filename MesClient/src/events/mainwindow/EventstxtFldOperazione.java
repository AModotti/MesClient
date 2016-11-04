package events.mainwindow;

import ide.main.Notifications;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Phase;
import bin.Settings;
import bin.WorkOrder;

public class EventstxtFldOperazione implements FocusListener {
	
	private JFrame frmEurollsMes;
	private JTextArea txtAreaMessaggi;
	private JTextField txtFldOdl;
	private JTextField txtFldFase;
	
	public EventstxtFldOperazione(JFrame frmEurollsMes,JTextArea txtAreaMessaggi, JTextField txtFldOdl, JTextField txtFldFase){
		
		this.frmEurollsMes = frmEurollsMes;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtFldOdl = txtFldOdl;
		this.txtFldFase = txtFldFase;

	}

	@Override
	public void focusLost(FocusEvent e) {
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
				txtFldFase.setBackground(Color.WHITE);
				
				Phase ph =  new Phase(txtFldFase.getText());
				WorkOrder wo = new WorkOrder(txtFldOdl.getText());
				if(!txtFldFase.getText().equals("")){
					if(ph.checkIfPhaseExists(wo,ph).equals("false")){
						txtFldFase.setForeground(Color.RED);
						txtAreaMessaggi.setForeground(Color.BLACK);
						txtAreaMessaggi.setBackground(Color.RED);
						txtAreaMessaggi.setText("FASE INESISTENTE.");
					}
					else{
						txtFldFase.setForeground(Color.BLUE);
						txtAreaMessaggi.setText("");
						txtAreaMessaggi.setBackground(Color.WHITE);
					}
				}else{
					txtAreaMessaggi.setText("");
					txtAreaMessaggi.setBackground(Color.WHITE);
				}
				frmEurollsMes.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventstxtFldOperazione.focusLost() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		txtFldFase.setBackground(Color.YELLOW);
		
	}
	
}
