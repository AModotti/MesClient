package events.mainwindow;

import ide.main.Notifications;

import java.awt.Cursor;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bin.LogWriter;
import bin.OperatorCache;
import bin.Settings;
import bin.WorkCenter;

public class EventsbtnCancellaMatricola implements ActionListener{
	
	private JFrame frmEurollsMes;
	private List lstMatricoleAbilitate;
	private WorkCenter myworkcenter;
	
	public EventsbtnCancellaMatricola(JFrame frmEurollsMes,List lstMatricoleAbilitate, WorkCenter myworkcenter) {

		this.frmEurollsMes = frmEurollsMes;
		this.lstMatricoleAbilitate = lstMatricoleAbilitate;
		this.myworkcenter = myworkcenter;

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
		
				int selectedIndex = lstMatricoleAbilitate.getSelectedIndex();
				if (selectedIndex != -1) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler cancellare l'operatore selezionato?", "Cancellazione Operatore",JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION){
						OperatorCache oc = new OperatorCache();
						oc.DeleteOperatorFromCache(myworkcenter.getWorkCenterName(myworkcenter.getWorkCenter()), lstMatricoleAbilitate.getSelectedItem().substring(0,5));
						LogWriter.write("[A] Esecuzione Classe: EventsbtnCancellaMatricola.actionPerformed()");
						LogWriter.write("[A] Cancellato operatore in cache locale: " + "[ " + lstMatricoleAbilitate.getSelectedItem().trim() + " ]");
						lstMatricoleAbilitate.remove(selectedIndex);
						JOptionPane.showMessageDialog(null, "Operatore cancellato con successo dalla lista operatori memorizzati.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				frmEurollsMes.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventsbtnCancellaMatricola.actionPerformed() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
	}

}
