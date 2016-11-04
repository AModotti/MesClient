package events.moresourcedialog;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Phase;
import bin.Presence;
import bin.Transaction;
import bin.WorkCenter;
import ide.main.TableSituazioneMacchine;

public class EventsCambioLavorazioneMoResourceDialog implements ActionListener{

	private Transaction tr;
	private Presence pr;
	private TableSituazioneMacchine tm;
	private JDialog dl;
	private WorkCenter wc;
	private Machine mc;
	private Operator op;
	private Phase ph;
	private String stato;
	private ArrayList<String> OdlInseriti = new ArrayList<String>();
	
	public EventsCambioLavorazioneMoResourceDialog(TableSituazioneMacchine tm, JDialog dl, WorkCenter wc, ArrayList<String> OdlInseriti, Machine mc, Operator op, Phase ph, String stato){
		
		this.tm = tm;
		this.dl = dl;
		this.wc = wc;
		this.OdlInseriti = OdlInseriti;
		this.mc = mc;
		this.op = op;
		this.ph = ph;
		this.stato = stato;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler cambiare lavorazione?", "Cambio Lavorazione",JOptionPane.YES_NO_OPTION);
		
		if(dialogResult == JOptionPane.YES_OPTION){
			
			dl.setCursor(hourglassCursor);
			
			tr = new Transaction(wc,OdlInseriti,mc,op,ph);
        	pr = new Presence(wc,OdlInseriti,mc,op,ph);
        	
//************************************************************************************************************************************************************************			
// SEZIONE MONO OPERATORE ************************************************************************************************************************************************
//************************************************************************************************************************************************************************
        	if (wc.getWorkCenterMode(wc).equals("0")){
//ATT ********************************************************************************************************************************************************************				
//ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//ATT ********************************************************************************************************************************************************************        		
	        	if(stato.equals("ATT")){
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsCambioLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto cambio lavorazione da ATT - Attrezzaggio a LAV - Lavorazione");
	        		tr.multiDoStopSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI	
	        		pr.multiDoStartWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI
	                tr.multiDoStartWorkMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI	
	                dl.setCursor(normalCursor);
	                dl.dispose();
//LAV ********************************************************************************************************************************************************************				
//LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV ********************************************************************************************************************************************************************	        		
	        	}else if(stato.equals("LAV")){
	        		dl.setCursor(hourglassCursor);;
	        		LogWriter.write("[A] Esecuzione Classe: EventsCambioLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto cambio lavorazione da LAV - Lavorazione a ATT - Attrezzaggio");
	        		tr.multiDoStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 5 MEMBRI		
	        		pr.multiDoStartSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI
	        		tr.multiDoStartSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI
	        		dl.setCursor(normalCursor);
	        		dl.dispose();	
	        	}
        	}
//************************************************************************************************************************************************************************			
// SEZIONE MULTI OPERATORE ***********************************************************************************************************************************************
//************************************************************************************************************************************************************************
			if(wc.getWorkCenterMode(wc).equals("1")){
//ATT ********************************************************************************************************************************************************************				
//ATT SE RISORSA IN ATT - SE L'OPERATORE E' IN FASE DI ATTREZZAGGIO
//ATT ********************************************************************************************************************************************************************				
				if(stato.equals("ATT")){
					dl.setCursor(hourglassCursor);
					LogWriter.write("[A] Esecuzione Classe: EventsCambioLavorazioneMoResourceDialog.actionPerformed()");
					LogWriter.write("[A] E'stato premuto il tasto cambio lavorazione da ATT - Attrezzaggio a LAV - Lavorazione");
	        		tr.multiDoStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI	
	        		pr.multiDoStartWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI
	                tr.multiDoStartWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI	
	                dl.setCursor(normalCursor);
	                dl.dispose();
//LAV ********************************************************************************************************************************************************************				
//LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV ********************************************************************************************************************************************************************	        		
	        	}else if(stato.equals("LAV")){
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsCambioLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto cambio lavorazione da LAV - Lavorazione a ATT - Attrezzaggio");
	        		tr.multiDoStopWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI	
	        		pr.multiDoStartSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI
	        		tr.multiDoStartSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI	
	        		dl.setCursor(normalCursor);
	        		dl.dispose();	
	        	}
			}
			
        	tm.getData();	
        	
        	dl.setCursor(normalCursor);
		}
	}
}
