package events.modialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bin.LogWriter;
import ide.main.MoDialog;

public class EventsbtnAnnullaMoDialog implements ActionListener{

	private MoDialog mybcdialog;
	
	public EventsbtnAnnullaMoDialog(MoDialog mybcdialog){
		
		this.mybcdialog = mybcdialog;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		LogWriter.write("[A] Esecuzione Classe: EventsbtnAnnullaMoDialog.actionPerformed()");	
		LogWriter.write("[A] E' stato premuto il tasto di chiusura della finestra di immissione dati Dichiarazione Multi Ordine");
		mybcdialog.dispose();
		
	}
	

}
