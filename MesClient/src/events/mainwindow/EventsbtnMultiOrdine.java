package events.mainwindow;

import ide.main.MoDialog;
import ide.main.TableSituazioneMacchine;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import bin.LogWriter;
import bin.WorkCenter;

public class EventsbtnMultiOrdine implements ActionListener{
	
	private JFrame frmEurollsMes;
	private JTextArea txtAreaMessaggi;
	private WorkCenter wc;
	private TableSituazioneMacchine tblSituazioneMacchine;
	
	public EventsbtnMultiOrdine(JFrame frmEurollsMes, JTextArea txtAreaMessaggi, WorkCenter wc, TableSituazioneMacchine tblSituazioneMacchine){
		
		this.wc = wc;
		this.frmEurollsMes = frmEurollsMes;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.tblSituazioneMacchine = tblSituazioneMacchine;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		frmEurollsMes.setCursor(hourglassCursor);
		
		LogWriter.write("[A] Esecuzione Classe: EventsbtnMultiOrdine.actionPerformed()");
		LogWriter.write("[A] E' stato premuto il tasto per l'apertura della finestra di immissione dati Dichiarazione Multi Ordine");
		
		MoDialog bc = new MoDialog(wc,txtAreaMessaggi,tblSituazioneMacchine);
		bc.setModal(true);
		bc.setVisible(true);
		
		frmEurollsMes.setCursor(normalCursor);
	}

}
