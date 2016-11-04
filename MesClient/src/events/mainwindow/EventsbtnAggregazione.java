package events.mainwindow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Operator;
import bin.Presence;
import bin.Settings;
import bin.Transaction;
import bin.WorkCenter;
import ide.main.BranchDialog;
import ide.main.Notifications;
import ide.main.TableSituazioneMacchine;

public class EventsbtnAggregazione implements ActionListener{
	
	private List lstMatricoleAggregate;
	private Operator myoperator;
	private WorkCenter myworkcenter;
	private List lstMatricoleAbilitate;
	private JTextArea txtAreaMessaggi;
	private JTextField txtFldTipoDichiarazione;
	private JFrame frmEurollsMes; 
	private TableSituazioneMacchine tblSituazioneMacchine;
	
	public EventsbtnAggregazione(JFrame frmEurollsMes, JTextField txtFldTipoDichiarazione, JTextArea txtAreaMessaggi, List lstMatricoleAggregate, List lstMatricoleAbilitate, Operator myoperator, WorkCenter myworkcenter, TableSituazioneMacchine tblSituazioneMacchine) {

		this.frmEurollsMes = frmEurollsMes;
		this.lstMatricoleAggregate = lstMatricoleAggregate;
		this.myoperator = myoperator;
		this.myworkcenter = myworkcenter;
		this.lstMatricoleAbilitate = lstMatricoleAbilitate;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtFldTipoDichiarazione = txtFldTipoDichiarazione;
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
				
				//SE SELEZIONO UNA MATRICOLA DALLA LISTA MATRICOLE ABILITATE
				if(lstMatricoleAbilitate.getSelectedItem() != null){
					
					int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di volerti aggregare dal centro?", "Aggregazione Centro",JOptionPane.YES_NO_OPTION);
					
					if(dialogResult == JOptionPane.YES_OPTION){
					
						myoperator.setOperator(lstMatricoleAbilitate.getSelectedItem().substring(0,5));
						//SE IL CENTRO E' MONO OPERATORE 
						if(myworkcenter.getWorkCenterMode(myworkcenter).equals("0")){
							//SE IL CENTRO NON HA ALCUN OPERATORE AGGREGATO
							if(myworkcenter.checkIfWorkCenterHasAggregations(myworkcenter).equals("false")){
								//SE L'OPERATORE NON E' AGGREGATO AD UN ALTRO CENTRO
								if(myoperator.checkIfOperatorHasAggregations(myoperator).equals("false")){
				                    //VERIFICO CHE NON CI SIANO TRANSAZIONI SOSPESE CAUSA DISAGGREGAZIONE SU CENTRO
									String checksuspendedtransaction = myworkcenter.checkSuspendedTransactions(myworkcenter,myoperator);
									//SE CI SONO TRANSAZIONI SOSPESE
				                    if(checksuspendedtransaction.equals("true")){
				                    	String setworkcenteroperatoraggregation = myworkcenter.setWorkCenterOperatorAggregation(myworkcenter,myoperator);
				                        //SE L'AGGREGAZIONE E' ANDATA A BUON FINE
				                        if(setworkcenteroperatoraggregation.contains("true")) {
				                        	
				                        	LogWriter.write("[A] Esecuzione Classe: EventsbtnAggregazione.actionPerformed()");
				    						LogWriter.write("[A] Aggregazione al centro di lavoro con operatore: " + "[ " + lstMatricoleAbilitate.getSelectedItem().trim() + " ]");
				    						
				                        	Presence mypresence = new Presence(myworkcenter,myoperator);
				    				        Transaction mytransaction = new Transaction(myworkcenter,myoperator);
				    				        
				                            mypresence.doAggregationAfterStopAllTransactonsMonoOperator();						//COSTRUTTORE CON 2 MEMBRI
				                            mytransaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator();		//COSTRUTTORE CON 2 MEMBRI	
				    	                    
				    						lstMatricoleAggregate.add(lstMatricoleAbilitate.getSelectedItem());
				    						lstMatricoleAbilitate.remove(lstMatricoleAbilitate.getSelectedItem());
				    						txtAreaMessaggi.setForeground(Color.WHITE);
				    						txtAreaMessaggi.setBackground(new Color(34, 139, 34));
				    						txtAreaMessaggi.setText("OPERATORE AGGREGATO AL CENTRO CON SUCCESSO.");
				                        //SE L'AGGREGAZIONE NON E' ANDATA A BUON FINE        
				                        }else {
				                        	txtAreaMessaggi.setForeground(Color.BLACK);
				                			txtAreaMessaggi.setBackground(Color.RED);
				                			txtAreaMessaggi.setText(setworkcenteroperatoraggregation);
				                        }
				                    //SE NON CI SONO TRANSAZIONI SOSPESE    
				                    }else{
				        				BranchDialog bd = new BranchDialog(myworkcenter,myoperator,lstMatricoleAggregate,lstMatricoleAbilitate,txtAreaMessaggi);
				        				bd.setModal(true);
				        				bd.setVisible(true);
				                    }
				                    txtFldTipoDichiarazione.requestFocus();
								}else{
									txtAreaMessaggi.setForeground(Color.BLACK);
									txtAreaMessaggi.setBackground(Color.RED);
									txtAreaMessaggi.setText("OPERATORE GIA' AGGREGATO AD UN ALTRO CENTRO.");
								}
							//SE IL CENTRO HA GIA' UN OPERATORE AGGREGATO	
							}else{
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("ESISTE GIA' UN OPERATORE AGGREGATO A QUESTO CENTRO CHE E' STATO IMPOSTATO COME CENTRO MONO OPERATORE.");
							}
						}
						//SE IL CENTRO E' MULTI OPERATORE 
						if(myworkcenter.getWorkCenterMode(myworkcenter).equals("1")){
							//VERIFICO CHE LA RISORSA SIA ABILITATA A LAVORARE SUL CENTRO
							if(myoperator.isOperatorLinkedToWorkCenter(myworkcenter,myoperator).equals("true")){
								//SE IL CENTRO NON HA ALCUN OPERATORE AGGREGATO
								if((myworkcenter.checkIfWorkCenterHasAggregations(myworkcenter).equals("false"))){
									//SE L'OPERATORE E' ABILITATO AD AGGREGARSI A QUESTO CENTRO
				                    if(myworkcenter.checkIfOperatorIsAggregable(myworkcenter,myoperator).contains("true")){
				                    	//SE L'OPERATORE NON E' AGGREGATO AD UN ALTRO CENTRO
										if(myoperator.checkIfOperatorHasAggregations(myoperator).equals("false")){
											//VERIFICO CHE NON CI SIANO TRANSAZIONI SOSPESE CAUSA DISAGGREGAZIONE SU CENTRO
											String checksuspendedtransaction = myworkcenter.checkSuspendedTransactions(myworkcenter,myoperator);
											//SE CI SONO TRANSAZIONI SOSPESE
											if(checksuspendedtransaction.equals("true")){
												String setworkcenteroperatoraggregation = myworkcenter.setWorkCenterOperatorAggregation(myworkcenter,myoperator);
						                        //SE L'AGGREGAZIONE E' ANDATA A BUON FINE
						                        if(setworkcenteroperatoraggregation.contains("true")) {
						                        	
						                        	LogWriter.write("[A] Esecuzione Classe: EventsbtnAggregazione.actionPerformed()");
						                        	LogWriter.write("[A] Aggregazione al centro di lavoro con operatore: " + "[ " + lstMatricoleAbilitate.getSelectedItem().trim() + " ]");
						                        	
													Presence mypresence = new Presence(myworkcenter,myoperator);
											        Transaction mytransaction = new Transaction(myworkcenter,myoperator);
													
													mypresence.doAggregationAfterStopAllTransactonsMultiOperator();					//COSTRUTTORE CON 2 MEMBRI
					                                mytransaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator();	//COSTRUTTORE CON 2 MEMBRI	
					                                
					                                lstMatricoleAggregate.add(lstMatricoleAbilitate.getSelectedItem());
													lstMatricoleAbilitate.remove(lstMatricoleAbilitate.getSelectedItem());
													txtAreaMessaggi.setForeground(Color.WHITE);
													txtAreaMessaggi.setBackground(new Color(34, 139, 34));
													txtAreaMessaggi.setText("OPERATORE AGGREGATO AL CENTRO CON SUCCESSO.");	
												//SE L'AGGREGAZIONE NON E' ANDATA A BUON FIN
						                        }else {
						                        	txtAreaMessaggi.setForeground(Color.BLACK);
						                			txtAreaMessaggi.setBackground(Color.RED);
						                			txtAreaMessaggi.setText(setworkcenteroperatoraggregation);
						                        }	
						                    //SE NON CI SONO TRANSAZIONI SOSPESE    
						                    }else{
						                    	BranchDialog bd = new BranchDialog(myworkcenter,myoperator,lstMatricoleAggregate,lstMatricoleAbilitate,txtAreaMessaggi);
						        				bd.setModal(true);
						        				bd.setVisible(true);
						                    }
										}else{
											txtAreaMessaggi.setForeground(Color.BLACK);
											txtAreaMessaggi.setBackground(Color.RED);
											txtAreaMessaggi.setText("OPERATORE GIA' AGGREGATO AD UN ALTRO CENTRO.");
										}	
				                    }else{
				                    	txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("OPERATORE NON ABILITATO AD AGGREGARSI A QUESTO CENTRO.");
				                    }
								//SE IL CENTRO HA GIA' UN OPERATORE AGGREGATO	
								}else{
									//SE L'OPERATORE NON E' AGGREGATO AD UN ALTRO CENTRO
									if(myoperator.checkIfOperatorHasAggregations(myoperator).equals("false")){
										//VERIFICO CHE NON CI SIANO TRANSAZIONI SOSPESE CAUSA DISAGGREGAZIONE SU CENTRO
										String checksuspendedtransaction = myworkcenter.checkSuspendedTransactions(myworkcenter,myoperator);
										//SE CI SONO TRANSAZIONI SOSPESE
										if(checksuspendedtransaction.equals("true")){
											String setworkcenteroperatoraggregation = myworkcenter.setWorkCenterOperatorAggregation(myworkcenter,myoperator);
					                        //SE L'AGGREGAZIONE E' ANDATA A BUON FINE
					                        if(setworkcenteroperatoraggregation.contains("true")) {
					                        	
					                        	LogWriter.write("[A] Esecuzione Classe: EventsbtnAggregazione.actionPerformed()");
					                            LogWriter.write("[A] Aggregazione al centro di lavoro con operatore: " + "[ " + lstMatricoleAbilitate.getSelectedItem().trim() + " ]");
					                            
												Presence mypresence = new Presence(myworkcenter,myoperator);
										        Transaction mytransaction = new Transaction(myworkcenter,myoperator);
												
												mypresence.doAggregationAfterStopAllTransactonsMultiOperator();					//COSTRUTTORE CON 2 MEMBRI
					                            mytransaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator();	//COSTRUTTORE CON 2 MEMBRI	
					                            
					                            lstMatricoleAggregate.add(lstMatricoleAbilitate.getSelectedItem());
												lstMatricoleAbilitate.remove(lstMatricoleAbilitate.getSelectedItem());
												txtAreaMessaggi.setForeground(Color.WHITE);
												txtAreaMessaggi.setBackground(new Color(34, 139, 34));
												txtAreaMessaggi.setText("OPERATORE AGGREGATO AL CENTRO CON SUCCESSO.");
											//SE L'AGGREGAZIONE NON E' ANDATA A BUON FIN
					                        }else {
					                        	txtAreaMessaggi.setForeground(Color.BLACK);
					                			txtAreaMessaggi.setBackground(Color.RED);
					                			txtAreaMessaggi.setText(setworkcenteroperatoraggregation);
					                        }
					                    //SE NON CI SONO TRANSAZIONI SOSPESE    
					                    }else{
					                    	BranchDialog bd = new BranchDialog(myworkcenter,myoperator,lstMatricoleAggregate,lstMatricoleAbilitate,txtAreaMessaggi);
					        				bd.setModal(true);
					        				bd.setVisible(true);
					                    }
									}else{
										txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("OPERATORE GIA' AGGREGATO AD UN ALTRO CENTRO.");
									}	
								}
							}else{
								JOptionPane.showMessageDialog(null, "Operatore non abilitato a lavorare su questo centro.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						tblSituazioneMacchine.getData();
						frmEurollsMes.setCursor(normalCursor);
					}
				}
				
				frmEurollsMes.setCursor(normalCursor);
				
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventsbtnAggregazione.actionPerformed() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
		
	}

}
