package events.modialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Phase;
import bin.Presence;
import bin.Settings;
import bin.TimeCalculator;
import bin.Transaction;
import bin.WorkCenter;
import ide.main.MoDialog;
import ide.main.Notifications;
import ide.main.TableSituazioneMacchine;

public class EventsbtnConfermaTransazioneMoDialog implements ActionListener{

	private MoDialog mybcdialog;
	private JTextField txtFldTipoDichiarazione;
	private JTextField txtFldStato;
	private JTextField txtFldRisorsa;
	private List lstOdlInseriti;
	private ArrayList<String> wos = new ArrayList<String>();
	private JTextField txtFldFase;
	private WorkCenter wc;
	private JTextArea txtAreaMessaggi;
	private JTextField txtVerTipoDichiarazione;
	private JTextField txtVerStato;
	private JTextField txtVerRisorsa;
	private JTextField txtVerOdl;
	private JTextField txtVerListaOdl;
	private JTextField txtVerOperazione;
	private TableSituazioneMacchine tblSituazioneMacchine;
	
	public EventsbtnConfermaTransazioneMoDialog(JTextField txtFldTipoDichiarazione,JTextField txtFldStato,JTextField txtFldRisorsa,List lstOdlInseriti,JTextField txtFldFase,WorkCenter wc,JTextArea txtAreaMessaggi,MoDialog mybcdialog,JTextField txtVerTipoDichiarazione,JTextField txtVerStato,JTextField txtVerRisorsa,JTextField txtVerOdl,JTextField txtVerListaOdl,JTextField txtVerOperazione,TableSituazioneMacchine tblSituazioneMacchine){
		
		this.mybcdialog = mybcdialog;
		this.txtFldTipoDichiarazione = txtFldTipoDichiarazione;
		this.txtFldStato = txtFldStato;
		this.txtFldRisorsa = txtFldRisorsa;
		this.lstOdlInseriti = lstOdlInseriti;
		this.txtFldFase = txtFldFase;
		this.wc = wc;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtVerTipoDichiarazione = txtVerTipoDichiarazione;
		this.txtVerStato = txtVerStato;
		this.txtVerRisorsa = txtVerRisorsa;
		this.txtVerOdl = txtVerOdl;
		this.txtVerListaOdl = txtVerListaOdl;
		this.txtVerOperazione = txtVerOperazione;
		this.tblSituazioneMacchine = tblSituazioneMacchine;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
				
				String message = "";
				String workorders = "";
				Operator op = new Operator(txtFldRisorsa.getText());
				Machine mc = new Machine(txtFldRisorsa.getText());
				Phase ph =  new Phase(txtFldFase.getText());
				Transaction tr;
				Presence pr;
				
				String[] items = lstOdlInseriti.getItems();
			       
		        for(int i=0; i < items.length; i++){
		        	wos.add(items[i]);
		        }
				
				if((txtVerTipoDichiarazione.getText().equals("0"))&&(txtVerStato.getText().equals("0"))&&(txtVerRisorsa.getText().equals("0"))&&
				   (txtVerOdl.getText().equals("0"))&&(txtVerListaOdl.getText().equals("0"))&&(txtVerOperazione.getText().equals("0"))){
			
//************************************************************************************************************************************************************************
//********** INIZIO DICHIARAZIONI ****************************************************************************************************************************************
//************************************************************************************************************************************************************************	
					LogWriter.write("[A] Esecuzione Classe: EventsbtnMultiOrdine.actionPerformed()");	
					LogWriter.write("[A] E' stato premuto il tasto Esegui Transazione Multi Ordine");
//************************************************************************************************************************************************************************			
// SEZIONE MONO OPERATORE ************************************************************************************************************************************************
//************************************************************************************************************************************************************************
					if(wc.getWorkCenterMode(wc).equals("0")){
//ATT DIC*****************************************************************************************************************************************************************
//ATT DIC - SE LA DICHIARAZIONE E' DI ATTREZZAGGIO
//ATT DIC*****************************************************************************************************************************************************************
						if(txtFldTipoDichiarazione.getText().equals("ATT")){
//ATT DIC 1***************************************************************************************************************************************************************
//ATT DIC 1 - SE LA DICHIARAZIONE E' DI INIZIO
//ATT DIC 1***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("1")){
		
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);	
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 1 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                            String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE I WORKORDERS SONO DIVERSI DA QUELLO/I IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                			//SE I WORKORDERS SONO UGUALI A QUELLO/I IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		 
		                            }
		
		                        }
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************				
//ATT DIC 1 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                            String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE I WORKORDERS SONO DIVERSI DA QUELLO/I IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                    		//SE I WORKORDERS SONO UGUALI A QUELLO/I IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 5 MEMBRI												
		                                pr.multiDoStartSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI								
		                                tr.multiDoStartSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI										
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		            					
		                                message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					txtAreaMessaggi.setText(message);
		
		                            }
		
		                        }
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 1 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) { 
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
		                        		tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
			                        	
			                            pr.multiDoStartSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI										
			                            tr.multiDoStartSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI	 									
			                            
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
			        					
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + workorders + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
		                        	}
		                        }
							}
//ATT DIC 2***************************************************************************************************************************************************************
//ATT DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//ATT DIC 2***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);	
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 2 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI	
//INIZIO PROCEDURA DI RIPARTIZIONE TEMPI *********************************************************************************************************************************
		                            	TimeCalculator tc = new TimeCalculator();
		                            	
		                            	String sd = tc.getLowerTime(mc,wc);
		                            	String ed = tc.getUpperTime(mc,wc);
		                            	
		                            	int i = tc.getCount(mc, wc)/2;
		                            	long time = tc.calculateTimeDiffereceSeconds(sd, ed);
		                            	long interval = time/i;
		                            	
		                            	ArrayList<Integer> ids = tc.getAllIds(mc, wc);
		                            	
		                            	tc.updateTimeOfRecords(mc,wc,ids,interval);
		                            	
		                            	//JOptionPane.showMessageDialog(null, "Start date:     " + sd + "\n" + "Finish date:    " + ed + "\n" + "Time step:     " + interval + " sec.","Ripartizione Tempi", JOptionPane.INFORMATION_MESSAGE);
//FINE PROCEDURA DI PROPORZIONAMENTO TEMPI *******************************************************************************************************************************		                            
		                            	pr.multiDoStopSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI										
		                            	
		                            	txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		            					
		                            	message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		                            	
		            					txtAreaMessaggi.setText(message);
		                            }
		                        }
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************				
//ATT DIC 2 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 2 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) {
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
			                        	txtAreaMessaggi.setForeground(Color.BLACK);
			                			txtAreaMessaggi.setBackground(Color.RED);
			                			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO.");
		                        	}
		                        }
							}
						}
//LAV DIC*****************************************************************************************************************************************************************
//LAV DIC - SE LA DICHIARAZIONE E' DI LAVORAZIONE
//LAV DIC*****************************************************************************************************************************************************************
						if(txtFldTipoDichiarazione.getText().equals("LAV")){
//LAV DIC 1***************************************************************************************************************************************************************
//LAV DIC 1 - SE LA DICHIARAZIONE E' DI INIZIO
//LAV DIC 1***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("1")){
								
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);	
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 1 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI										
		                                pr.multiDoStartWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI									
		                                tr.multiDoStartWorkMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI										
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		            					
		                                message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		                            	
		            					txtAreaMessaggi.setText(message);
		
		                            }
		                        }
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************				
//LAV DIC 1 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 1 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) {
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
		                        		tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
			                        	
			                        	pr.multiDoStartWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI									
			                        	tr.multiDoStartWorkMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI										
			                        	
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		                                
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + workorders + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
		
		                        	}
		                        }
							}
//LAV DIC 2***************************************************************************************************************************************************************
//LAV DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//LAV DIC 2**************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);	
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 2 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		                        }
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************				
//LAV DIC 2 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 5 MEMBRI			
//INIZIO PROCEDURA DI RIPARTIZIONE TEMPI *********************************************************************************************************************************
		                            	TimeCalculator tc = new TimeCalculator();
		                            	
		                            	String sd = tc.getLowerTime(mc,wc);
		                            	String ed = tc.getUpperTime(mc,wc);
		                            	
		                            	int i = tc.getCount(mc, wc)/2;
		                            	long time = tc.calculateTimeDiffereceSeconds(sd, ed);
		                            	long interval = time/i;
		                            	
		                            	ArrayList<Integer> ids = tc.getAllIds(mc, wc);
		                            	
		                            	tc.updateTimeOfRecords(mc,wc,ids,interval);
		                            	
		                            	//JOptionPane.showMessageDialog(null, "Start date:     " + sd + "\n" + "Finish date:    " + ed + "\n" + "Time step:     " + interval + " sec.","Ripartizione Tempi", JOptionPane.INFORMATION_MESSAGE);
//FINE PROCEDURA DI PROPORZIONAMENTO TEMPI *******************************************************************************************************************************		                      	                            	
		                                pr.multiDoStopWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI										
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		                                
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		
		                            }
		
		                        }
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 2 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) {
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
			                        	txtAreaMessaggi.setForeground(Color.BLACK);
			                			txtAreaMessaggi.setBackground(Color.RED);
			                			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO.");
		                        	}
		                        }
							}
						}
					}
//************************************************************************************************************************************************************************			
// SEZIONE MULTI OPERATORE ***********************************************************************************************************************************************
//************************************************************************************************************************************************************************
					if(wc.getWorkCenterMode(wc).equals("1")){
//ATT DIC*****************************************************************************************************************************************************************
//ATT DIC - SE LA DICHIARAZIONE E' DI ATTREZZAGGIO
//ATT DIC*****************************************************************************************************************************************************************				
						if(txtFldTipoDichiarazione.getText().equals("ATT")){
//ATT DIC 1***************************************************************************************************************************************************************
//ATT DIC 1 - SE LA DICHIARAZIONE E' DI INIZIO
//ATT DIC 1***************************************************************************************************************************************************************					
							if(txtFldStato.getText().equals("1")){
							
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 1 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************                        
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		                        }
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************              
//ATT DIC 1 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI									
		                                pr.multiDoStartSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI									
		                                tr.multiDoStartSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI										
		                            	
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		            					
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		
		                            }
		
		                        }
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 1 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) {        
		                        	tr = new Transaction(wc,wos,mc,op,ph);
		                        	pr = new Presence(wc,wos,mc,op,ph);
		                        	
		                        	pr.multiDoStartSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI											
		                        	tr.multiDoStartSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI									
		                        	
		                            txtAreaMessaggi.setForeground(Color.WHITE);
		        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		        					
		        					for (String wo : wos) {
	                                	workorders = workorders + wo + ", ";
	                                }
		                            
		                            workorders = workorders.substring(0,workorders.length()-2);
		        					
		        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		        							+ "Stato: " + txtFldStato.getText() + "\n"
		        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		        							+ "Work Order: " + workorders + "\n"
		        							+ "Operazione: " + txtFldFase.getText() + "\n";
		        					
		        					txtAreaMessaggi.setText(message);
		
		    					}						
							}
//ATT DIC 2***************************************************************************************************************************************************************
//ATT DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//ATT DIC 2***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);					
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 2 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI
//INIZIO PROCEDURA DI RIPARTIZIONE TEMPI *********************************************************************************************************************************
		                            	TimeCalculator tc = new TimeCalculator();
		                            	
		                            	String sd = tc.getLowerTime(mc,wc);
		                            	String ed = tc.getUpperTime(mc,wc);
		                            	
		                            	int i = tc.getCount(mc, wc)/2;
		                            	long time = tc.calculateTimeDiffereceSeconds(sd, ed);
		                            	long interval = time/i;
		                            	
		                            	ArrayList<Integer> ids = tc.getAllIds(mc, wc);
		                            	
		                            	tc.updateTimeOfRecords(mc,wc,ids,interval);
		                            	
		                            	//JOptionPane.showMessageDialog(null, "Start date:     " + sd + "\n" + "Finish date:    " + ed + "\n" + "Time step:     " + interval + " sec.","Ripartizione Tempi", JOptionPane.INFORMATION_MESSAGE);
//FINE PROCEDURA DI PROPORZIONAMENTO TEMPI *******************************************************************************************************************************		                      
		                                pr.multiDoStopSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI										
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		                                
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		
		                            }
		                        }
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************             
//ATT DIC 2 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 2 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) { 
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
		                        		tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
			                        	
			                        	pr.multiDoStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI										
			                        	tr.multiDoStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI										
			                        	
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		                                
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + workorders + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
		
		                        	}
		    					}
							}					
						}
//LAV DIC*****************************************************************************************************************************************************************
//LAV DIC - SE LA DICHIARAZIONE E' DI LAVORAZIONE
//LAV DIC*****************************************************************************************************************************************************************
						if(txtFldTipoDichiarazione.getText().equals("LAV")){
//LAV DIC 1***************************************************************************************************************************************************************
//LAV DIC 1 - SE LA DICHIARAZIONE E' DI INIZIO
//LAV DIC 1***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("1")){
								
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);	
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 1 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI										
		                                pr.multiDoStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI								
		                                tr.multiDoStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI									
		                            	
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		                                
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		
		                            }
		                        }
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************              
//LAV DIC 1 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 1 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) {        
		                        	tr = new Transaction(wc,wos,mc,op,ph);
		                        	pr = new Presence(wc,wos,mc,op,ph);
		                        	
		                        	pr.multiDoStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI									
		                        	tr.multiDoStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI									
		                        	
		                            txtAreaMessaggi.setForeground(Color.WHITE);
		        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		        					
		        					for (String wo : wos) {
	                                	workorders = workorders + wo + ", ";
	                                }
		                            
		                            workorders = workorders.substring(0,workorders.length()-2);
		        					
		        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		        							+ "Stato: " + txtFldStato.getText() + "\n"
		        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		        							+ "Work Order: " + workorders + "\n"
		        							+ "Operazione: " + txtFldFase.getText() + "\n";
		        					
		        					txtAreaMessaggi.setText(message);
		
		    					}						
							}
//LAV DIC 2***************************************************************************************************************************************************************
//LAV DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//LAV DIC 2***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String multipleworkordermachinealreadyinsetup = mc.multiWorkOrderCheckIfMachineIsAlreadyInSetup(mc);
		                        String multipleworkordermachinealreadyinwork = mc.multiWorkOrderCheckIfMachineIsAlreadyInWork(mc);
		                        String multipleworkordermachinealreadyinstop = mc.multiWorkOrderCheckIfMachineIsAlreadyInStop(mc);		
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 2 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinsetup.contains("true")) {
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                            }
		                        }
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************              
//LAV DIC 2 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinwork.contains("true")) {       
		
		                        	String multipleworkorisderthesameinjob = mc.multiWorkOrderCheckIfWorkOrderIsTheSameInWork(mc,wos,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(multipleworkorisderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO GRUPPO DI WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
		                            	
		                            	tr.multiDoStopWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI		
//INIZIO PROCEDURA DI RIPARTIZIONE TEMPI *********************************************************************************************************************************
		                            	TimeCalculator tc = new TimeCalculator();
		                            	
		                            	String sd = tc.getLowerTime(mc,wc);
		                            	String ed = tc.getUpperTime(mc,wc);
		                            	
		                            	int i = tc.getCount(mc, wc)/2;
		                            	long time = tc.calculateTimeDiffereceSeconds(sd, ed);
		                            	long interval = time/i;
		                            	
		                            	ArrayList<Integer> ids = tc.getAllIds(mc, wc);
		                            	
		                            	tc.updateTimeOfRecords(mc,wc,ids,interval);
		                            	
		                            	//JOptionPane.showMessageDialog(null, "Start date:     " + sd + "\n" + "Finish date:    " + ed + "\n" + "Time step:     " + interval + " sec.","Ripartizione Tempi", JOptionPane.INFORMATION_MESSAGE);
//FINE PROCEDURA DI PROPORZIONAMENTO TEMPI *******************************************************************************************************************************		                      
		                                pr.multiDoStopWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI									
		                            	
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
		                                
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + workorders + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		
		                            }
		
		                        }
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 2 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(multipleworkordermachinealreadyinstop.contains("true")) {        
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
		                        		tr = new Transaction(wc,wos,mc,op,ph);
		                            	pr = new Presence(wc,wos,mc,op,ph);
			                        	
			                        	pr.multiDoStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI							
			                        	tr.multiDoStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI									
			                        	
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					for (String wo : wos) {
		                                	workorders = workorders + wo + ", ";
		                                }
		                                
		                                workorders = workorders.substring(0,workorders.length()-2);
			        					
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + " - Multi Ordine" + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + workorders + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
		
		                        	}
		    					}
							
							}
						}
					}
					
					tblSituazioneMacchine.getData();
		
//************************************************************************************************************************************************************************
//********** FINE DICHIARAZIONI ******************************************************************************************************************************************
//************************************************************************************************************************************************************************
					mybcdialog.setCursor(normalCursor);
					mybcdialog.dispose();	
				}else{
					wos.clear();
					txtAreaMessaggi.setForeground(Color.BLACK);
					txtAreaMessaggi.setBackground(Color.RED);
					txtAreaMessaggi.setText("ATTENZIONE: CI SONO ERRORI NELLA FASE DI INSERIMENTO DATI.");
					mybcdialog.setCursor(normalCursor);
				}
			}
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventsbtnConfermaTransazioneMoDialog.actionPerformed() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}

	}

}
