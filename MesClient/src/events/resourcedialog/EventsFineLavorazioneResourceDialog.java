package events.resourcedialog;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import bin.Branch;
import bin.LogWriter;
import bin.Machine;
import bin.Operator;
import bin.Phase;
import bin.Presence;
import bin.Transaction;
import bin.WorkCenter;
import bin.WorkOrder;
import ide.main.TableSituazioneMacchine;

public class EventsFineLavorazioneResourceDialog implements ActionListener{

	private Transaction tr;
	private Presence pr;
	private TableSituazioneMacchine tm;
	private JDialog dl;
	private WorkCenter wc;
	private WorkOrder wo;
	private Machine mc;
	private Operator op;
	private Branch br;
	private Phase ph;
	private String stato;
	
	public EventsFineLavorazioneResourceDialog(TableSituazioneMacchine tm, JDialog dl, WorkCenter wc, WorkOrder wo, Machine mc, Operator op, Branch br, Phase ph, String stato){
		
		this.tm = tm;
		this.dl = dl;
		this.wc = wc;
		this.wo = wo;
		this.mc = mc;
		this.op = op;
		this.br = br;
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
			
			tr = new Transaction(wc,wo,mc,op,br,ph);
        	pr = new Presence(wc,wo,mc,op,br,ph);
        	
//************************************************************************************************************************************************************************			
// SEZIONE MONO OPERATORE ************************************************************************************************************************************************
//************************************************************************************************************************************************************************
        	if (wc.getWorkCenterMode(wc).equals("0")){
//ATT ********************************************************************************************************************************************************************				
//ATT SE RISORSA IN ATT - SE LA MACCHINA E' IN FASE DI ATTREZZAGGIO
//ATT ********************************************************************************************************************************************************************        		
	        	if(stato.equals("ATT")){	
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando la macchina era in ATT - Attrezzaggio");
	        		tr.doStopSetUpMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
                    pr.doStopSetUpMachinePresenceMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
                    dl.setCursor(normalCursor);
	                dl.dispose();
//LAV ********************************************************************************************************************************************************************				
//LAV SE RISORSA IN LAV - SE LA MACCHINA E' IN FASE DI LAVORAZIONE
//LAV ********************************************************************************************************************************************************************
	        	}else if(stato.equals("LAV")){	
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando la macchina era in LAV - Lavorazione");
	        		tr.doStopWorkMachineTransactionMonoOperator();		//COSTRUTTORE CON 6 MEMBRI
                    pr.doStopWorkMachinePresenceMonoOperator();			//COSTRUTTORE CON 6 MEMBRI
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
					LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando l'operatore era in ATT - Attrezzaggio");
	        		tr.doStopSetUpMachineTransactionMultiOperator();	//COSTRUTTORE CON 6 MEMBRI
                    pr.doStopSetUpMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
                    dl.setCursor(normalCursor);
	                dl.dispose();
	        	}else if(stato.equals("LAV")){
//LAV ********************************************************************************************************************************************************************				
//LAV SE RISORSA IN LAV - SE L'OPERATORE E' IN FASE DI LAVORAZIONE
//LAV ********************************************************************************************************************************************************************	        		
	        		dl.setCursor(hourglassCursor);
	        		LogWriter.write("[A] Esecuzione Classe: EventsFineLavorazioneResourceDialog.actionPerformed()");
	        		LogWriter.write("[A] E'stato premuto il tasto fine lavorazione quando l'operatore era in LAV - Lavorazione");
	        		tr.doStopWorkMachineTransactionMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
                    pr.doStopWorkMachinePresenceMultiOperator();		//COSTRUTTORE CON 6 MEMBRI
                    dl.setCursor(normalCursor);
	        		dl.dispose();
	        	}
			}
			
        	tm.getData();
        	
        	dl.setCursor(normalCursor);
		}
	}
}
