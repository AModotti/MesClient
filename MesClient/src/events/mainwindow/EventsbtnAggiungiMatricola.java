package events.mainwindow;

import ide.main.Notifications;

import java.awt.Cursor;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Operator;
import bin.OperatorCache;
import bin.Settings;
import bin.WorkCenter;

public class EventsbtnAggiungiMatricola implements ActionListener{

	private JFrame frmEurollsMes;
	private List lstMatricoleAbilitate;
	private List lstMatricoleAggregate;
	private Operator myoperator;
	private JTextField txtFldMatricola;	
	private WorkCenter myworkcenter;
	private int operatorexists = 0;
	
	public EventsbtnAggiungiMatricola(JFrame frmEurollsMes,List lstMatricoleAbilitate,List lstMatricoleAggregate, JTextField txtFldMatricola, WorkCenter myworkcenter, Operator myoperator) {

		this.frmEurollsMes = frmEurollsMes;
		this.lstMatricoleAbilitate = lstMatricoleAbilitate;
		this.lstMatricoleAggregate = lstMatricoleAggregate;
		this.txtFldMatricola = txtFldMatricola;
		this.myworkcenter = myworkcenter;
		this.myoperator = myoperator;

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
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
		
				if(txtFldMatricola.getText().trim().length() != 0){
					if(myoperator.checkIfOperatorExists(myoperator) == "false"){
						
					}else{
						for(int i=0;i<lstMatricoleAggregate.getItemCount();i++){ 
						     if(txtFldMatricola.getText().trim().equals(lstMatricoleAggregate.getItem(i).substring(0,5))){
						    	 operatorexists = 1;
						     }
						}
						if(operatorexists == 0){
							OperatorCache oc = new OperatorCache();
							if(oc.CheckIfOperatorIsAlreadyInCache(myworkcenter.getWorkCenterName(myworkcenter.getWorkCenter()), myoperator.getOperator()) == "false"){
								oc.AddOperatorInCache(myworkcenter.getWorkCenterName(myworkcenter.getWorkCenter()), myoperator.getOperator(), myoperator.getOperatorDescription(myoperator));	
								lstMatricoleAbilitate.add(myoperator.getOperator() + " - " + myoperator.getOperatorDescription(myoperator));
								txtFldMatricola.setText("");
								LogWriter.write("[A] Esecuzione Classe: EventsbtnAggiungiMatricola.actionPerformed()");
								LogWriter.write("[A] Aggiunto operatore in cache locale: " + "[ " + myoperator.getOperator() + " - " + myoperator.getOperatorDescription(myoperator) + " ]");
							}else{
								JOptionPane.showMessageDialog(null, "Attenzione! Operatore già presente all'interno della lista operatori memorizzati.", "Errore", JOptionPane.ERROR_MESSAGE);							
							}
						}else{
							JOptionPane.showMessageDialog(null, "Attenzione! Operatore già presente all'interno della lista operatori memorizzati ed attualmente aggregato al centro.", "Errore", JOptionPane.ERROR_MESSAGE);							
						}	
					}
				}
				frmEurollsMes.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventsbtnAggiungiMatricola.actionPerformed() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
	}
	
}
