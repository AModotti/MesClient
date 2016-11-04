package events.modialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.List;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Settings;
import bin.WorkCenter;
import ide.main.MoDialog;
import ide.main.Notifications;

public class EventstxtFldRisorsaMoDialog implements FocusListener {
	
	private MoDialog mybcdialog;
	private JTextArea txtAreaMessaggi;
	private WorkCenter myworkcenter;
	private JTextField txtFldStato;
	private JTextField txtFldRisorsa;
	private JTextField txtVerRisorsa;
	private JTextField txtFldOperazione;
	private List lstOdlInseriti;
	private JTextField txtFldNr;
	private JTextField txtVerOdl;
	private JTextField txtVerListaOdl;
	private JTextField txtVerOperazione;
	
	public EventstxtFldRisorsaMoDialog(MoDialog mybcdialog,JTextArea txtAreaMessaggi,WorkCenter myworkcenter,JTextField txtFldStato,JTextField txtFldRisorsa,JTextField txtVerRisorsa,JTextField txtFldOperazione,List lstOdlInseriti,JTextField txtVerOdl,JTextField txtVerListaOdl,JTextField txtVerOperazione,JTextField txtFldNr){
		
		this.mybcdialog = mybcdialog;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.myworkcenter = myworkcenter;
		this.txtFldStato = txtFldStato;
		this.txtFldRisorsa = txtFldRisorsa;
		this.txtVerRisorsa = txtVerRisorsa;
		this.txtFldOperazione = txtFldOperazione;
		this.lstOdlInseriti = lstOdlInseriti;
		this.txtFldNr = txtFldNr;
		this.txtVerOdl = txtVerOdl;
		this.txtVerListaOdl = txtVerListaOdl;
		this.txtVerOperazione = txtVerOperazione;

	}

	@Override
	public void focusLost(FocusEvent e) {
		
		int isnumeric = 0;
		int isallnumeric = 0;
		
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
				txtAreaMessaggi.setBackground(Color.WHITE);
				
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
					txtAreaMessaggi.setText("MACCHINA INESISTENTE");
					txtVerRisorsa.setText("1");
				}else{
					//SE IL CAMPO RISORSA NON E' VUOTO
					if(!txtFldRisorsa.getText().equals("")){
						//SE IL CENTRO E' MONO OPERATORE
						if(myworkcenter.getWorkCenterMode(myworkcenter).equals("0")){
							//SE LA MACCHINA NON ESISTE
							if(mc.checkIfMachineExists(mc).equals("false")){
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("MACCHINA NON CODIFICATA");
								txtVerRisorsa.setText("1");
							//SE LA MACCHINA ESISTE	
							}else if(mc.checkIfMachineExists(mc).equals("true")){
								//SE LA MACCHINA NON APPARTIENE AL CENTRO
								if(mc.checkIfMachineBelongsToWorkCenter(mc,myworkcenter).equals("false")){
									txtFldRisorsa.setForeground(Color.RED);
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText("MACCHINA NON APPARTENENTE A QUESTO CENTRO");
									txtVerRisorsa.setText("1");
								//SE LA MACCHINA APPARTIENE AL CENTRO	
								}else if(mc.checkIfMachineBelongsToWorkCenter(mc,myworkcenter).equals("true")){
									//SE CI SONO LAVORAZIONI MONO ORDINE IN CORSO
									if(mc.isMachineInActivityOnMonoWorkOrder(mc).equals("true")){
										txtFldRisorsa.setForeground(Color.RED);
										txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("SU QUESTA MACCHINA SONO IN CORSO LAVORAZIONI NON MULTI ORDINE. PRIMA DI PROCEDERE E' NECESSARIO TERMINARLE");
										txtVerRisorsa.setText("1");
									}else{
										//SE SI VUOLE TERMINARE UNA TRANSAZIONE IN CORSO
										if(txtFldStato.getText().equals("2")){
											lstOdlInseriti.removeAll();
											for (int i = 0; i < mc.multiWorkOrderGetWorkOrderToStop(mc).size(); i++) {
												lstOdlInseriti.add(mc.multiWorkOrderGetWorkOrderToStop(mc).get(i));												
											}
											txtFldNr.setText(Integer.toString(mc.multiWorkOrderGetWorkOrderToStop(mc).size()));
											txtVerOdl.setText("0");
											txtVerListaOdl.setText("0");
											String operazione = mc.multiWorkOrderGetPhaseToStop(mc);
											txtFldOperazione.setText(operazione);
											txtVerOperazione.setText("0");
											txtVerRisorsa.setText("0");

											txtFldRisorsa.setForeground(Color.BLUE);
											txtAreaMessaggi.setText("");
											txtAreaMessaggi.setBackground(Color.WHITE);;
										//SE NON SI VUOLE TERMINARE UNA TRANSAZIONE IN CORSO
										}else{
											txtFldRisorsa.setForeground(Color.BLUE);
											txtAreaMessaggi.setText("");
											txtAreaMessaggi.setBackground(Color.WHITE);
											txtVerRisorsa.setText("0");
										}
									}
								//SE C'E' UN ERRORE
								}else{
									txtFldRisorsa.setForeground(Color.RED);
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText(mc.checkIfMachineBelongsToWorkCenter(mc,myworkcenter));
									txtVerRisorsa.setText("1");
								}
							//SE C'E' UN ERRORE	
							}else{
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText(mc.checkIfMachineExists(mc));
								txtVerRisorsa.setText("1");
							}
						//SE IL CENTRO E' MULTI OPERATORE
						}else{
							//SE L'OPERATORE NON ESISTE
							if(op.checkIfOperatorExists(op).equals("false")){
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("OPERATORE NON CODIFICATO");
								txtVerRisorsa.setText("1");
							//SE L'OPERATORE ESISTE	
							}else if(op.checkIfOperatorExists(op).equals("true")){
								//SE L'OPERATORE NON E' AGGREGATO
								if(op.isOperatorAggregated(op).equals("false")){
									txtFldRisorsa.setForeground(Color.RED);
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText("OPERATORE NON AGGREGATO A QUESTO CENTRO");
									txtVerRisorsa.setText("1");
								//SE L'OPERATORE E' AGGREGATO	
								}else{
									//SE CI SONO LAVORAZIONI MONO ORDINE IN CORSO
									if(mc.isMachineInActivityOnMonoWorkOrder(mc).equals("true")){
										txtFldRisorsa.setForeground(Color.RED);
										txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("SU QUESTO OPERATORE SONO IN CORSO LAVORAZIONI NON MULTI ORDINE. PRIMA DI PROCEDERE E' NECESSARIO TERMINARLE");
										txtVerRisorsa.setText("1");
									}else{
//*******************************************************************************************************************************************
										//SE SI VUOLE TERMINARE UNA TRANSAZIONE IN CORSO
										if(txtFldStato.getText().equals("2")){
											lstOdlInseriti.removeAll();
											for (int i = 0; i < mc.multiWorkOrderGetWorkOrderToStop(mc).size(); i++) {
												lstOdlInseriti.add(mc.multiWorkOrderGetWorkOrderToStop(mc).get(i));												
											}
											txtFldNr.setText(Integer.toString(mc.multiWorkOrderGetWorkOrderToStop(mc).size()));
											txtVerOdl.setText("0");
											txtVerListaOdl.setText("0");
											String operazione = mc.multiWorkOrderGetPhaseToStop(mc);
											txtFldOperazione.setText(operazione);
											txtVerOperazione.setText("0");
											txtVerRisorsa.setText("0");

											txtFldRisorsa.setForeground(Color.BLUE);
											txtAreaMessaggi.setText("");
											txtAreaMessaggi.setBackground(Color.WHITE);;
										//SE NON SI VUOLE TERMINARE UNA TRANSAZIONE IN CORSO
										}else{
//*******************************************************************************************************************************************
											txtFldRisorsa.setForeground(Color.BLUE);
											txtAreaMessaggi.setText("");
											txtVerRisorsa.setText("0");
										}
									}
								}
							//SE C'E' UN ERRORE	
							}else{
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText(op.checkIfOperatorExists(op));
								txtVerRisorsa.setText("1");
							}
						}	
					//SE IL CAMPO RISORSA E' VUOTO	
					}else{
						txtAreaMessaggi.setText("");
						txtAreaMessaggi.setBackground(Color.WHITE);
						txtVerRisorsa.setText("1");
					}
					txtFldRisorsa.setBackground(Color.WHITE);
				}
				
				mybcdialog.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventstxtFldRisorsaMoDialog.focusLost() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
		
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		txtFldRisorsa.setBackground(Color.YELLOW);
		
	}

}
