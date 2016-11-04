package events.mainwindow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.Branch;
import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Phase;
import bin.Presence;
import bin.Settings;
import bin.Transaction;
import bin.WorkCenter;
import bin.WorkOrder;
import ide.main.Notifications;
import ide.main.TableSituazioneMacchine;


public class EventsbtnConfermaTransazione implements ActionListener{
	
	private JFrame frmEurollsMes;
	private JTextArea txtAreaMessaggi;
	private WorkCenter wc;
	private JTextField txtFldTipoDichiarazione;
	private JTextField txtFldStato;
	private JTextField txtFldRisorsa;
	private JTextField txtFldOdl;
	private JTextField txtFldFase;
	private TableSituazioneMacchine tblSituazioneMacchine;
	
	public EventsbtnConfermaTransazione(JFrame frmEurollsMes, JTextArea txtAreaMessaggi, WorkCenter wc, JTextField txtFldTipoDichiarazione, JTextField txtFldStato, JTextField txtFldRisorsa, JTextField txtFldOdl, JTextField txtFldFase, TableSituazioneMacchine tblSituazioneMacchine){
		
		this.wc = wc;
		this.frmEurollsMes = frmEurollsMes;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtFldTipoDichiarazione = txtFldTipoDichiarazione;
		this.txtFldStato = txtFldStato;
		this.txtFldRisorsa = txtFldRisorsa;
		this.txtFldOdl = txtFldOdl;
		this.txtFldFase = txtFldFase;
		this.tblSituazioneMacchine = tblSituazioneMacchine;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
				
				String message = "";
				String errors = "0";
				Operator op = new Operator(txtFldRisorsa.getText());
				Machine mc = new Machine(txtFldRisorsa.getText());
				WorkOrder wo = new WorkOrder(txtFldOdl.getText());
				Branch br = new Branch(wo.getWorkOrderBranch(wo));
				Phase ph =  new Phase(txtFldFase.getText());
				Transaction tr;
				Presence pr;

//**********************************************************************************************************************************************************************************************
//********** INIZIO CONTROLLI ******************************************************************************************************************************************************************
//**********************************************************************************************************************************************************************************************

				//CONTROLLO SE CI SONO OPERATORI AGGREGATI AL CENTRO
				if(wc.checkIfWorkCenterHasSpecificAggregation(wc,op).equals("false")){
					message = "NESSUN OPERATORE AGGREGATO A QUESTO CENTRO DI LAVORO." + "\n";
					errors = "1";	
				}
				//CONTROLLO SE IL TIPO DI DICHIARAZIONE E' VALIDO
				if((!txtFldTipoDichiarazione.getText().toUpperCase().equals("LAV")&&(!txtFldTipoDichiarazione.getText().toUpperCase().equals("ATT")))){
					message = message + "TIPO DI DICHIARAZIONE NON VALIDA." + "\n";
				}
				//CONTROLLO SE LO STATO DELLA DICHIARAZIONE E' VALIDO
				if((!txtFldStato.getText().equals("1")&&(!txtFldStato.getText().equals("2")))){
					message = message + "STATO NON VALIDO." + "\n";
					errors = "1";
				}
				//SE IL CENTRO E' MONO OPERATORE
				if(wc.getWorkCenterMode(wc).equals("0")){
					//SE LA MACCHINA NON ESISTE
					if(mc.checkIfMachineExists(mc).equals("false")){
						message = message + "MACCHINA NON CODIFICATA."+ "\n";
						errors = "1";
					//SE LA MACCHINA ESISTE	
					}else if(mc.checkIfMachineExists(mc).equals("true")){
						//SE LA MACCHINA NON APPARTIENE AL CENTRO
						if(mc.checkIfMachineBelongsToWorkCenter(mc,wc).equals("false")){
							message = message + "MACCHINA NON APPARTENENTE A QUESTO CENTRO." + "\n";
							errors = "1";
						//SE LA MACCHINA APPARTIENE AL CENTRO	
						}else if(mc.checkIfMachineBelongsToWorkCenter(mc,wc).equals("true")){
							//SE CI SONO LAVORAZIONI MULTI ORDINE IN CORSO
							if(mc.isMachineInActivityOnMultiWorkOrder(mc).equals("false")){
								txtFldRisorsa.setForeground(Color.BLUE);
								txtAreaMessaggi.setText("");
								txtAreaMessaggi.setBackground(Color.WHITE);
							}else{
								txtFldRisorsa.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								message = message + "SU QUESTA MACCHINA SONO IN CORSO LAVORAZIONI MULTI ORDINE. PRIMA DI PROCEDERE E' NECESSARIO TERMINARLE." + "\n";
								errors = "1";
							}
						//SE C'E' UN ERRORE
						}else{
						message = message + mc.checkIfMachineBelongsToWorkCenter(mc,wc);
						errors = "1";
					}
					//SE C'E' UN ERRORE	
					}else{
						message = message + mc.checkIfMachineExists(mc);
						errors = "1";
					}
				//SE IL CENTRO E' MULTI OPERATORE
				}else{
					//SE L'OPERATORE NON ESISTE
					if(op.checkIfOperatorExists(op).equals("false")){
						message = message + "OPERATORE NON CODIFICATO." + "\n";
						errors = "1";
					//SE L'OPERATORE ESISTE	
					}else if(op.checkIfOperatorExists(op).equals("true")){
						//SE L'OPERATORE NON E' AGGREGATO
						if(op.isOperatorAggregated(op).equals("false")){
							message = message + "OPERATORE NON AGGREGATO A QUESTO CENTRO." + "\n";
							errors = "1";
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
								message = message + "SU QUESTO OPERATORE SONO IN CORSO LAVORAZIONI MULTI ORDINE. PRIMA DI PROCEDERE E' NECESSARIO TERMINARLE." + "\n";
								errors = "1";
							}
						}
					//SE C'E' UN ERRORE	
					}else{
						message = message + op.checkIfOperatorExists(op);
						errors = "1";
					}
				}
				//CONTROLLO SE IL WORK ORDER ESISTE
				if(wo.checkIfWorkOrderExists(wo).equals("false")){
					message = message + "WORK ORDER INESISTENTE." + "\n";
					errors = "1";
					//CONTROLLO SE IL WORK ORDER E' CHIUSO	
				}else if(wo.checkIfWorkOrderIsClose(wo).equals("false")){
					message = message + "WORK ORDER CHIUSO O ANNULLATO." + "\n";
					errors = "1";
				}
				//CONTROLLO SE LA FASE ESISTE
				if(ph.checkIfPhaseExists(wo,ph).equals("false")){
					message = message + "FASE INESISTENTE." + "\n";
					errors = "1";
				}
		
				//CONTROLLO SE CI SONO ERRORI DAI CONTROLLI DI CUI SOPRA
				if(errors.equals("1")){
					txtAreaMessaggi.setForeground(Color.BLACK);
					txtAreaMessaggi.setBackground(Color.RED);
					txtAreaMessaggi.setText(message);
					frmEurollsMes.setCursor(normalCursor);
				//SE NON CI SONO ERRORI	
				}else{
					
//************************************************************************************************************************************************************************
//********** INIZIO DICHIARAZIONI ****************************************************************************************************************************************
//************************************************************************************************************************************************************************	
				LogWriter.write("[A] Esecuzione Classe: EventsbtnConfermaTransazione.actionPerformed()");	
				LogWriter.write("[A] E' stato premuto il tasto Esegui Transazione");
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
		
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);	
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 1 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO WORK ORDER/OPERAZIONE.");
		 
		                            }
		                        }
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************				
//ATT DIC 1 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStartSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                tr.doStartSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 6 MEMBRI
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		                                message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		
		                        }
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 1 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) { 
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
			                        	tr = new Transaction(wc,wo,mc,op,br,ph);
			                        	pr = new Presence(wc,wo,mc,op,br,ph);
			                        	
			                            pr.doStartSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
			                            tr.doStartSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 6 MEMBRI 
			                            
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + txtFldOdl.getText() + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
			        					txtFldTipoDichiarazione.setText("");
			        					txtFldStato.setText("");
			        					txtFldRisorsa.setText("");
			        					txtFldOdl.setText("");
			        					txtFldFase.setText("");
			        					txtFldTipoDichiarazione.requestFocus();
		                        	}
		                        }
							}
//ATT DIC 2***************************************************************************************************************************************************************
//ATT DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//ATT DIC 2***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 2 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopSetUpMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                            	pr.doStopSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                            	
		                            	txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		                            	message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		                            	
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		                        }
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************				
//ATT DIC 2 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 2 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) {
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
								
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 1 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopSetUpMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStartWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                tr.doStartWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		                                message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		                            	
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		                        }
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************				
//LAV DIC 1 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 1 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) {
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
			                        	tr = new Transaction(wc,wo,mc,op,br,ph);
			                        	pr = new Presence(wc,wo,mc,op,br,ph);
			                        	
			                        	pr.doStartWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
			                        	tr.doStartWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
			                        	
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + txtFldOdl.getText() + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
			        					txtFldTipoDichiarazione.setText("");
			        					txtFldStato.setText("");
			        					txtFldRisorsa.setText("");
			        					txtFldOdl.setText("");
			        					txtFldFase.setText("");
			        					txtFldTipoDichiarazione.requestFocus();
		                        	}
		                        }
							}
//LAV DIC 2***************************************************************************************************************************************************************
//LAV DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//LAV DIC 2**************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 2 RIS IN ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		                        }
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************				
//LAV DIC 2 RIS IN LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO IN MACCHINA
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO IN MACCHINA
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStopWorkMachinePresenceMonoOperator();			//COSTRUTTORE CON 6 MEMBRI
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		
		                        }
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 2 RIS IN FER SE RISORSA IN FERMO - SE LA MACCHINA E' IN STATO DI FERMO
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) {
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
							
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 1 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//ATT DIC 1 RIS IN ATT****************************************************************************************************************************************************                        
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		                        }
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************              
//ATT DIC 1 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//ATT DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopWorkMachineTransactionMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStartSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                tr.doStartSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
		                            	
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		
		                        }
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 1 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//ATT DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) {        
		                        	tr = new Transaction(wc,wo,mc,op,br,ph);
		                        	pr = new Presence(wc,wo,mc,op,br,ph);
		                        	
		                        	pr.doStartSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                        	tr.doStartSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
		                        	
		                            txtAreaMessaggi.setForeground(Color.WHITE);
		        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		        					
		        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		        							+ "Stato: " + txtFldStato.getText() + "\n"
		        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		        							+ "Work Order: " + txtFldOdl.getText() + "\n"
		        							+ "Operazione: " + txtFldFase.getText() + "\n";
		        					
		        					txtAreaMessaggi.setText(message);
		        					txtFldTipoDichiarazione.setText("");
		        					txtFldStato.setText("");
		        					txtFldRisorsa.setText("");
		        					txtFldOdl.setText("");
		        					txtFldFase.setText("");
		        					txtFldTipoDichiarazione.requestFocus();
		    					}						
							}
//ATT DIC 2***************************************************************************************************************************************************************
//ATT DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//ATT DIC 2***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);					
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//ATT DIC 2 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//ATT DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStopSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		                        }
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************             
//ATT DIC 2 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//ATT DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************              
//ATT DIC 2 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//ATT DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) { 
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
			                        	tr = new Transaction(wc,wo,mc,op,br,ph);
			                        	pr = new Presence(wc,wo,mc,op,br,ph);
			                        	
			                        	pr.doStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
			                        	tr.doStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
			                        	
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + txtFldOdl.getText() + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
			        					txtFldTipoDichiarazione.setText("");
			        					txtFldStato.setText("");
			        					txtFldRisorsa.setText("");
			        					txtFldOdl.setText("");
			        					txtFldFase.setText("");
			        					txtFldTipoDichiarazione.requestFocus();
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
								
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 1 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//LAV DIC 1 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                tr.doStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
		                            	
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		                        }
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************              
//LAV DIC 1 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV DIC 1 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		
		                        }
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 1 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//LAV DIC 1 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) {        
		                        	tr = new Transaction(wc,wo,mc,op,br,ph);
		                        	pr = new Presence(wc,wo,mc,op,br,ph);
		                        	
		                        	pr.doStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                        	tr.doStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
		                        	
		                            txtAreaMessaggi.setForeground(Color.WHITE);
		        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		        					
		        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		        							+ "Stato: " + txtFldStato.getText() + "\n"
		        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		        							+ "Work Order: " + txtFldOdl.getText() + "\n"
		        							+ "Operazione: " + txtFldFase.getText() + "\n";
		        					
		        					txtAreaMessaggi.setText(message);
		        					txtFldTipoDichiarazione.setText("");
		        					txtFldStato.setText("");
		        					txtFldRisorsa.setText("");
		        					txtFldOdl.setText("");
		        					txtFldFase.setText("");
		        					txtFldTipoDichiarazione.requestFocus();
		    					}						
							}
//LAV DIC 2***************************************************************************************************************************************************************
//LAV DIC 2 - SE LA DICHIARAZIONE E' DI FINE
//LAV DIC 2***************************************************************************************************************************************************************
							if(txtFldStato.getText().equals("2")){
								
								String machinealreadyinsetup = mc.checkIfMachineIsAlreadyInSetup(mc);
		                        String machinealreadyinwork = mc.checkIfMachineIsAlreadyInWork(mc);
		                        String machinealreadyinstop = mc.checkIfMachineIsAlreadyInStop(mc);
								
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************				
//LAV DIC 2 RIS IN ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//LAV DIC 2 RIS IN ATT****************************************************************************************************************************************************
		                        if(machinealreadyinsetup.contains("true")) {
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                            //SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI ATTREZZAGGIO SULLO STESSO WORK ORDER/OPERAZIONE.");
		                            }
		                        }
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************              
//LAV DIC 2 RIS IN LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV DIC 2 RIS IN LAV****************************************************************************************************************************************************
		                        if(machinealreadyinwork.contains("true")) {       
		
		                            String isworkorderthesameinjob = mc.checkIfWorkOrderIsTheSameInWork(mc,wo,ph);
		
		                            //SE IL WORKORDER E' DIVERSO DA QUELLO DICHIARATO DELL'OPERATORE
		                            if(isworkorderthesameinjob.contains("false")) {
		                            	txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("OPERATORE GIA' IN FASE DI LAVORAZIONE SU UN ALTRO WORK ORDER/OPERAZIONE.");
		                    		//SE IL WORKORDER E' UGUALE A QUELLO DICHIARATO DELL'OPERATORE
		                            }else{
		                            	tr = new Transaction(wc,wo,mc,op,br,ph);
		                            	pr = new Presence(wc,wo,mc,op,br,ph);
		                            	
		                            	tr.doStopWorkMachineTransactionMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                                pr.doStopWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
		                            	
		                                txtAreaMessaggi.setForeground(Color.WHITE);
		            					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
		            					
		            					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
		            							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
		            							+ "Stato: " + txtFldStato.getText() + "\n"
		            							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
		            							+ "Work Order: " + txtFldOdl.getText() + "\n"
		            							+ "Operazione: " + txtFldFase.getText() + "\n";
		            					
		            					txtAreaMessaggi.setText(message);
		            					txtFldTipoDichiarazione.setText("");
		            					txtFldStato.setText("");
		            					txtFldRisorsa.setText("");
		            					txtFldOdl.setText("");
		            					txtFldFase.setText("");
		            					txtFldTipoDichiarazione.requestFocus();
		                            }
		
		                        }
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************              
//LAV DIC 2 RIS IN FER SE RISORSA IN FERMO - SE L'OPERATORE E' IN STATO DI FERMO
//LAV DIC 2 RIS IN FER****************************************************************************************************************************************************
		                        if(machinealreadyinstop.contains("true")) {        
		                        	if(txtFldStato.getText().equals("2")){
		                        		txtAreaMessaggi.setForeground(Color.BLACK);
		                    			txtAreaMessaggi.setBackground(Color.RED);
		                    			txtAreaMessaggi.setText("MACCHINA IN STATO DI FERMO, IMPOSSIBILE TERMINARE UNA LAVORAZIONE SENZA AVERLA PRIMA INIZIATA.");
		                        	}else{
			                        	tr = new Transaction(wc,wo,mc,op,br,ph);
			                        	pr = new Presence(wc,wo,mc,op,br,ph);
			                        	
			                        	pr.doStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
			                        	tr.doStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
			                        	
			                            txtAreaMessaggi.setForeground(Color.WHITE);
			        					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			        					
			        					message = "TRANSAZIONE ESEGUITA CON SUCCESSO." + "\n"
			        							+ "Tipo Dichiarazione: " + txtFldTipoDichiarazione.getText() + "\n"
			        							+ "Stato: " + txtFldStato.getText() + "\n"
			        							+ "Macchina: " + txtFldRisorsa.getText() + "\n"
			        							+ "Work Order: " + txtFldOdl.getText() + "\n"
			        							+ "Operazione: " + txtFldFase.getText() + "\n";
			        					
			        					txtAreaMessaggi.setText(message);
			        					txtFldTipoDichiarazione.setText("");
			        					txtFldStato.setText("");
			        					txtFldRisorsa.setText("");
			        					txtFldOdl.setText("");
			        					txtFldFase.setText("");
			        					txtFldTipoDichiarazione.requestFocus();
		                        	}
		    					}
							
							}
						}
					}
					
					tblSituazioneMacchine.getData();
					frmEurollsMes.setCursor(normalCursor);
				}
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventsbtnConfermaTransazione.actionPerformed() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
					
	}
	
}
