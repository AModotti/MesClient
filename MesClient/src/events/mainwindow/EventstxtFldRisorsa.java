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
import bin.Machine;
import bin.Operator;
import bin.Settings;
import bin.WorkCenter;

public class EventstxtFldRisorsa implements FocusListener {
	
	private JFrame frmEurollsMes;
	private JTextArea txtAreaMessaggi;
	private WorkCenter myworkcenter;
	private JTextField txtFldRisorsa;
	
	public EventstxtFldRisorsa(JFrame frmEurollsMes,JTextArea txtAreaMessaggi, WorkCenter myworkcenter, JTextField txtFldRisorsa){
		
		this.frmEurollsMes = frmEurollsMes;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.myworkcenter = myworkcenter;
		this.txtFldRisorsa = txtFldRisorsa;

	}

	@Override
	public void focusLost(FocusEvent e) {
		int isnumeric = 0;
		int isallnumeric = 0;
		
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
				txtFldRisorsa.setBackground(Color.WHITE);
				
				Operator op = new Operator(txtFldRisorsa.getText());
				Machine mc = new Machine(txtFldRisorsa.getText());
				
				for (char c:txtFldRisorsa.getText().toCharArray()){
			        if (!Character.isDigit(c)) {
			        	isnumeric = 1;
			        }else{
			        	isnumeric = 0;
			        }
			        isallnumeric = isallnumeric + isnumeric;
			    }
				if(isallnumeric >= 1){
					txtFldRisorsa.setForeground(Color.RED);
					txtAreaMessaggi.setForeground(Color.BLACK);
					txtAreaMessaggi.setBackground(Color.RED);
					txtAreaMessaggi.setText("MACCHINA INESISTENTE.");
				}else{
					//SE IL CAMPO RISORSA E' VUOTO
					if(!txtFldRisorsa.getText().equals("")){
						//SE IL CENTRO E' MONO OPERATORE
						if(myworkcenter.getWorkCenterMode(myworkcenter).equals("0")){
							//SE LA MACCHINA NON ESISTE
							if(mc.checkIfMachineExists(mc).equals("false")){
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("MACCHINA NON CODIFICATA.");
							//SE LA MACCHINA ESISTE	
							}else if(mc.checkIfMachineExists(mc).equals("true")){
								//SE LA MACCHINA NON APPARTIENE AL CENTRO
								if(mc.checkIfMachineBelongsToWorkCenter(mc,myworkcenter).equals("false")){
									txtFldRisorsa.setForeground(Color.RED);
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText("MACCHINA NON APPARTENENTE A QUESTO CENTRO.");
								//SE LA MACCHINA APPARTIENE AL CENTRO	
								}else if(mc.checkIfMachineBelongsToWorkCenter(mc,myworkcenter).equals("true")){
									//SE CI SONO LAVORAZIONI MULTI ORDINE IN CORSO
									if(mc.isMachineInActivityOnMultiWorkOrder(mc).equals("false")){
										txtFldRisorsa.setForeground(Color.BLUE);
										txtAreaMessaggi.setText("");
										txtAreaMessaggi.setBackground(Color.WHITE);
									}else{
										txtFldRisorsa.setForeground(Color.RED);
										txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("SU QUESTA MACCHINA SONO IN CORSO LAVORAZIONI MULTI ORDINE. PRIMA DI PROCEDERE E' NECESSARIO TERMINARLE");
									}
								//SE C'E' UN ERRORE
								}else{
									txtFldRisorsa.setForeground(Color.RED);
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText(mc.checkIfMachineBelongsToWorkCenter(mc,myworkcenter));
								}
							//SE C'E' UN ERRORE	
							}else{
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText(mc.checkIfMachineExists(mc));
							}
						//SE IL CENTRO E' MULTI OPERATORE
						}else{
							//SE L'OPERATORE NON ESISTE
							if(op.checkIfOperatorExists(op).equals("false")){
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("OPERATORE NON CODIFICATO.");
							//SE L'OPERATORE ESISTE	
							}else if(op.checkIfOperatorExists(op).equals("true")){
								//SE L'OPERATORE NON E' AGGREGATO
								if(op.isOperatorAggregated(op).equals("false")){
									txtFldRisorsa.setForeground(Color.RED);
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText("OPERATORE NON AGGREGATO A QUESTO CENTRO.");
								//SE L'OPERATORE E' AGGREGATO	
								}else{
									//SE CI SONO LAVORAZIONI MULTI ORDINE IN CORSO
									if(mc.isMachineInActivityOnMultiWorkOrder(mc).equals("false")){
										txtFldRisorsa.setForeground(Color.BLUE);
										txtAreaMessaggi.setText("");
										txtAreaMessaggi.setBackground(Color.WHITE);
									}else{
										txtFldRisorsa.setForeground(Color.RED);
										txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("SU QUESTO OPERATORE SONO IN CORSO LAVORAZIONI MULTI ORDINE. PRIMA DI PROCEDERE E' NECESSARIO TERMINARLE");
									}
								}
							//SE C'E' UN ERRORE	
							}else{
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText(op.checkIfOperatorExists(op));
							}
						}
					//SE IL CAMPO RISORSA NON E' VUOTO	
					}else{
						txtAreaMessaggi.setText("");
						txtAreaMessaggi.setBackground(Color.WHITE);
					}
				}
				
				frmEurollsMes.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventstxtFldRisorsa.focusLost() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		txtFldRisorsa.setBackground(Color.YELLOW);
		
	}

}
