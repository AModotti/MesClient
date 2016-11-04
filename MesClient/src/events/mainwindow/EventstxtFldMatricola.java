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
import bin.Operator;
import bin.Settings;

public class EventstxtFldMatricola implements FocusListener {
	
	private JFrame frmEurollsMes;
	private JTextField txtFldMatricola;
	private JTextArea txtAreaMessaggi;
	private Operator myoperator; 
	
	public EventstxtFldMatricola(JFrame frmEurollsMes,JTextField txtFldMatricola, JTextArea txtAreaMessaggi, Operator myoperator) {

		this.frmEurollsMes = frmEurollsMes;
		this.txtFldMatricola = txtFldMatricola;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.myoperator = myoperator;

	}
	
	@Override
	public void focusLost(FocusEvent arg0) {
		
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
				myoperator.setOperator(txtFldMatricola.getText());	
				if(txtFldMatricola.getText().trim().length() != 0){
					if(myoperator.checkIfOperatorExists(myoperator) == "false"){
						txtFldMatricola.setForeground(Color.RED);
						txtAreaMessaggi.setForeground(Color.BLACK);
						txtAreaMessaggi.setBackground(Color.RED);
						txtAreaMessaggi.setText("ATTENZIONE MATRICOLA OPERATORE INESISTENTE!");
					}else{
						txtFldMatricola.setForeground(Color.BLUE);
						txtAreaMessaggi.setText("");
						txtAreaMessaggi.setBackground(Color.WHITE);
					};
				}else{
					txtAreaMessaggi.setText("");
					txtAreaMessaggi.setBackground(Color.WHITE);
				}
		
				frmEurollsMes.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventstxtFldMatricola.focusLost() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		txtFldMatricola.setForeground(Color.BLUE);
	}

}
