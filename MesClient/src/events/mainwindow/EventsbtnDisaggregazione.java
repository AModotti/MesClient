package events.mainwindow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.List;

import bin.LogWriter;
import bin.Operator;
import bin.Presence;
import bin.Settings;
import bin.Transaction;
import bin.WorkCenter;
import ide.main.Notifications;
import ide.main.TableSituazioneMacchine;

public class EventsbtnDisaggregazione implements ActionListener{
	
	private List lstMatricoleAggregate;
	private Operator myoperator;
	private WorkCenter myworkcenter;
	private List lstMatricoleAbilitate;
	private JTextArea txtAreaMessaggi;
	private JFrame frmEurollsMes;
	private TableSituazioneMacchine tblSituazioneMacchine;
	
	public EventsbtnDisaggregazione(JFrame frmEurollsMes, JTextArea txtAreaMessaggi, List lstMatricoleAggregate, List lstMatricoleAbilitate, Operator myoperator, WorkCenter myworkcenter, TableSituazioneMacchine tblSituazioneMacchine) {

		this.frmEurollsMes = frmEurollsMes;
		this.lstMatricoleAggregate = lstMatricoleAggregate;
		this.myoperator = myoperator;
		this.myworkcenter = myworkcenter;
		this.lstMatricoleAbilitate = lstMatricoleAbilitate;
		this.txtAreaMessaggi = txtAreaMessaggi;
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
		
				if(lstMatricoleAggregate.getSelectedItem()!= null){
					
					int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di volerti disaggregare dal centro?", "Disaggregazione Centro",JOptionPane.YES_NO_OPTION);
					
					if(dialogResult == JOptionPane.YES_OPTION){
						//CONTROLLO SE IL CENTRO E' MONO O MULTI OPERATORE
						String checkworkcentermode = myworkcenter.getWorkCenterMode(myworkcenter);
						//SE IL CENTRO E' MONO OPERATORE
						if(checkworkcentermode.equals("0")){
							myoperator.setOperator(myworkcenter.getOperatorAggregatedToWorkCenter(myworkcenter)); 
							//VERIFICO SE CI SONO TRANSAZIONI APERTE DA SOSPENDERE CAUSA DISAGGREGAZIONE SU CENTRO
							String checkopentransactions = myworkcenter.checkOpenTransactions(myworkcenter,myoperator);
							//SE ESISTONO TRANSAZIONI APERTE
							if(checkopentransactions.equals("true")){
								String setworkcenteroperatordisaggregation = myworkcenter.setWorkCenterOperatorDisaggregation(myworkcenter,myoperator);
								//SE LA DISAGGREGAZIONE E' ANDATA A BUON FINE
			                    if(setworkcenteroperatordisaggregation.contains("true")) {
			                    	
			                    	LogWriter.write("[A] Esecuzione Classe: EventsbtnDisaggregazione.actionPerformed()");
			            			LogWriter.write("[A] Disaggregazione dal centro di lavoro con operatore: " + "[ " + lstMatricoleAggregate.getSelectedItem().trim() + " ]");
			            			
			                    	Presence mypresence = new Presence(myworkcenter,myoperator);
			    			        Transaction mytransaction = new Transaction(myworkcenter,myoperator);
			    			        
			    					mytransaction.StopAllTransactrionsBeforeDisaggregationMonoOperator();	//COSTRUTTORE CON 2 MEMBRI	
			                        mypresence.doDisaggregationAfterStopAllTransactonsMonoOperator();		//COSTRUTTORE CON 2 MEMBRI
			                        
			                    	lstMatricoleAbilitate.add(lstMatricoleAggregate.getSelectedItem()); 
			    					lstMatricoleAggregate.remove(lstMatricoleAggregate.getSelectedItem());
			    					txtAreaMessaggi.setForeground(Color.WHITE);
			    					txtAreaMessaggi.setBackground(new Color(34, 139, 34));
			    					txtAreaMessaggi.setText("OPERATORE DISAGGREGATO DAL CENTRO CON SUCCESSO.");
			                    //SE LA DISAGGREGAZION NON E' ANDATA A BUON FINE        
			                    }else {
			                    	txtAreaMessaggi.setForeground(Color.BLACK);
			            			txtAreaMessaggi.setBackground(Color.RED);
			            			txtAreaMessaggi.setText(setworkcenteroperatordisaggregation);
			                    }   
							//SE NON ESISTONO TRANSAZIONI APERTE	
							}else{
								
								LogWriter.write("[A] Esecuzione Classe: EventsbtnDisaggregazione.actionPerformed()");
								LogWriter.write("[A] Disaggregazione dal centro di lavoro con operatore: " + "[ " + lstMatricoleAggregate.getSelectedItem().trim() + " ]");
								
								Presence pr = new Presence(myworkcenter,myoperator);
						        Transaction tr = new Transaction(myworkcenter,myoperator);
						        
								tr.doExitDisaggregationMonoOperator();		//COSTRUTTORE CON 2 MEMBRI	
			                    pr.doDisaggregationMonoOperator();			//COSTRUTTORE CON 2 MEMBRI
			                    myworkcenter.setWorkCenterOperatorDisaggregation(myworkcenter,myoperator);
			                    
								lstMatricoleAbilitate.add(lstMatricoleAggregate.getSelectedItem());
								lstMatricoleAggregate.remove(lstMatricoleAggregate.getSelectedItem());
								txtAreaMessaggi.setForeground(Color.WHITE);
								txtAreaMessaggi.setBackground(new Color(34, 139, 34));
								txtAreaMessaggi.setText("OPERATORE DISAGGREGATO DAL CENTRO CON SUCCESSO.");
						}	
						//SE IL CENTRO E' MULTI OPERATORE	
						}else{
							myoperator.setOperator(lstMatricoleAggregate.getSelectedItem().substring(0,5));
							String checkopentransactions = myworkcenter.checkOpenTransactions(myworkcenter,myoperator);
		                    
							//VERIFICO SE CI SONO LAVORAZIONI IN CORSO SUL CENTRO DI LAVORO        
		                    if(checkopentransactions.equals("true")){ 
		                    	
		                    	LogWriter.write("[A] Esecuzione Classe: EventsbtnDisaggregazione.actionPerformed()");
		                        LogWriter.write("[A] Disaggregazione dal centro di lavoro con operatore: " + "[ " + lstMatricoleAggregate.getSelectedItem().trim() + " ]");
		                        
		                    	Presence pr = new Presence(myworkcenter,myoperator);
						        Transaction tr = new Transaction(myworkcenter,myoperator);
						        
		                        tr.StopAllTransactrionsBeforeDisaggregationMultiOperator();		//COSTRUTTORE CON 2 MEMBRI	
		                        pr.doDisaggregationAfterStopAllTransactonsMultiOperator();		//COSTRUTTORE CON 2 MEMBRI
		                        
		                        myworkcenter.setWorkCenterOperatorDisaggregation(myworkcenter,myoperator);
		                        
		                        lstMatricoleAbilitate.add(lstMatricoleAggregate.getSelectedItem());
								lstMatricoleAggregate.remove(lstMatricoleAggregate.getSelectedItem());
								txtAreaMessaggi.setForeground(Color.WHITE);
								txtAreaMessaggi.setBackground(new Color(34, 139, 34));
								txtAreaMessaggi.setText("OPERATORE DISAGGREGATO DAL CENTRO CON SUCCESSO.");
		                    }else {   
		                    	
		                    	LogWriter.write("[A] Esecuzione Classe: EventsbtnDisaggregazione.actionPerformed()");
		                        LogWriter.write("[A] Disaggregazione dal centro di lavoro con operatore: " + "[ " + lstMatricoleAggregate.getSelectedItem().trim() + " ]");
		                        
		                    	Presence pr = new Presence(myworkcenter,myoperator);
						        Transaction tr = new Transaction(myworkcenter,myoperator);
						        
		                        tr.doExitDisaggregationMultiOperator();		//COSTRUTTORE CON 2 MEMBRI		
		                        pr.doDisaggregationMultiOperator();			//COSTRUTTORE CON 2 MEMBRI
		
		                        myworkcenter.setWorkCenterOperatorDisaggregation(myworkcenter,myoperator);
		                        
		                        lstMatricoleAbilitate.add(lstMatricoleAggregate.getSelectedItem());
								lstMatricoleAggregate.remove(lstMatricoleAggregate.getSelectedItem());
								txtAreaMessaggi.setForeground(Color.WHITE);
								txtAreaMessaggi.setBackground(new Color(34, 139, 34));
								txtAreaMessaggi.setText("OPERATORE DISAGGREGATO DAL CENTRO CON SUCCESSO.");
		                    }
						}
						tblSituazioneMacchine.getData();
					}
				}
				
				frmEurollsMes.setCursor(normalCursor);
			}

		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventsbtnDisaggregazione.actionPerformed() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
		
	}

}
