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

public class EventsFineLavorazioneMoResourceDialog implements ActionListener{

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
	
	public EventsFineLavorazioneMoResourceDialog(TableSituazioneMacchine tm, JDialog dl, WorkCenter wc, ArrayList<String> OdlInseriti, Machine mc, Operator op, Phase ph, String stato){
		
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
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler terminare la  lavorazione?", "Fine Lavorazione",JOptionPane.YES_NO_OPTION);
	
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
	        		LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando la macchina era in ATT - Attrezzaggio");
	        		tr.multiDoStopSetUpMachineTransactionMonoOperator();	//COSTRUTTORE CON 5 MEMBRI	
                    pr.multiDoStopSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI
                    dl.setCursor(normalCursor);
	                dl.dispose();
//LAV ********************************************************************************************************************************************************************				
//LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV ********************************************************************************************************************************************************************
	        	}else if(stato.equals("LAV")){	
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando la macchina era in LAV - Lavorazione");
	        		tr.multiDoStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 5 MEMBRI		
                    pr.multiDoStopWorkMachinePresenceMonoOperator();		//COSTRUTTORE CON 5 MEMBRI
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
					LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando l'operatore era in ATT - Attrezzaggio");
	        		tr.multiDoStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI	
                    pr.multiDoStopSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI
                    dl.setCursor(normalCursor);
	                dl.dispose();
	        	}else if(stato.equals("LAV")){
//LAV ********************************************************************************************************************************************************************				
//LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV ********************************************************************************************************************************************************************	        		
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneMoResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando l'operatore era in LAV - Lavorazione");
	        		tr.multiDoStopWorkMachineTransactionMultiOperator();	//COSTRUTTORE CON 5 MEMBRI	
                    pr.multiDoStopWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 5 MEMBRI
                    dl.setCursor(normalCursor);
	        		dl.dispose();
	        	}
			}
			
        	tm.getData();
        	
        	dl.setCursor(normalCursor);
		}
	}
}
