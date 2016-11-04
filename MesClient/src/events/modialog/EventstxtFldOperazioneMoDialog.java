package events.modialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.List;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Phase;
import bin.Settings;
import bin.WorkOrder;
import ide.main.MoDialog;
import ide.main.Notifications;

public class EventstxtFldOperazioneMoDialog implements FocusListener {
	
	private MoDialog mybcdialog;
	private JTextArea txtAreaMessaggi;
	private List lstOdlInseriti;
	private JTextField txtFldOperazione;
	private JTextField txtVerOperazione;
	private JTextField txtVerListaOdl;
	
	public EventstxtFldOperazioneMoDialog(MoDialog mybcdialog,JTextArea txtAreaMessaggi,List lstOdlInseriti,JTextField txtFldOperazione,JTextField txtVerOperazione,JTextField txtVerListaOdl) {

		this.mybcdialog = mybcdialog;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.lstOdlInseriti = lstOdlInseriti;
		this.txtFldOperazione = txtFldOperazione;
		this.txtVerOperazione = txtVerOperazione;
		this.txtVerListaOdl = txtVerListaOdl;

	}

	@Override
	public void focusLost(FocusEvent e) {
		int isnumeric = 0;
		int isallnumeric = 0;
		int phaseexists = 0;
		String iteminerror = "";
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		mybcdialog.setCursor(hourglassCursor);
		
		try {
			//CONTROLLO CHE IL DATABASE SIA RAGGIUNGIBILE
			String checkdbconnection = Settings.checkDbConnection();
			
			//SE NON E' RAGGIUNGIBILE
			if(checkdbconnection.equals("false")){
				Notifications notification = new Notifications("[ Settings.checkDbConnection() ]","Impossibile connettersi al Database","Non è stato possibile connettersi al Database a causa di un errore. Controllare la connettività o le impostazioni di rete.");
	        	notification.setModal(true);
	        	notification.setVisible(true);
	        	mybcdialog.setCursor(normalCursor);
			//SE E' RAGGIUNGIBILE	
			}else{
				txtFldOperazione.setBackground(Color.WHITE);
				
				Phase ph = new Phase(txtFldOperazione.getText());
				if(!txtFldOperazione.getText().equals("")){
					for (char c:txtFldOperazione.getText().toCharArray()){
				        if (!Character.isDigit(c)) {
				        	isnumeric = 1;
				        }else{
				        	isnumeric = 0;
				        }
				        isallnumeric = isallnumeric + isnumeric;
				    }
					if(isallnumeric >= 1){
						txtFldOperazione.setForeground(Color.RED);
						txtAreaMessaggi.setForeground(Color.BLACK);
						txtAreaMessaggi.setBackground(Color.RED);
						txtAreaMessaggi.setText("OPERAZIONE INESISTENTE");
						txtFldOperazione.setForeground(Color.BLUE);
						txtVerOperazione.setText("1");
					}else{
						for(int i=0;i<lstOdlInseriti.getItemCount();i++){
							if(lstOdlInseriti.getItem(i).contains("*")){
								lstOdlInseriti.replaceItem(lstOdlInseriti.getItem(i).substring(3,lstOdlInseriti.getItem(i).length()), i);
							}
							
							WorkOrder wo = new WorkOrder(lstOdlInseriti.getItem(i));
							if(ph.checkIfPhaseExists(wo,ph).equals("false")){
								iteminerror = lstOdlInseriti.getItem(i);
								lstOdlInseriti.replaceItem(" * " + iteminerror, i);
								phaseexists = 1;
								txtVerListaOdl.setText("1");
							}
		
						}
						if(phaseexists == 1){
								txtFldOperazione.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("OPERAZIONE INESISTENTE PER UNO O PIU' ORDINI DI PRODUZIONE INSERITI: VEDI SEGNALAZIONE CON ASTERISCO");
								txtFldOperazione.setForeground(Color.BLUE);
								txtVerOperazione.setText("1");
						}else{
							txtFldOperazione.setForeground(Color.BLUE);
							txtAreaMessaggi.setText("");
							txtAreaMessaggi.setBackground(Color.WHITE);
							txtVerOperazione.setText("0");
							txtVerListaOdl.setText("0");
						}
					}
		
				}else{
					txtVerOperazione.setText("1");
				}
				mybcdialog.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventstxtFldOperazioneMoDialog.focusLost() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		txtFldOperazione.setBackground(Color.YELLOW);
	}

}
