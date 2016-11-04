package events.mainwindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

import bin.LogWriter;

public class EventsChiudiProgramma implements WindowListener {
	
	public EventsChiudiProgramma(){
		
	}
	
	@Override
	public void windowClosing(WindowEvent windowEvent) {
		
		int answ = JOptionPane.showConfirmDialog(null,"Sei sicuro di voler uscire dal programma?", "Chiusura Programma",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (answ == 0){
        	LogWriter.write("[F] ************************************************** PROGRAMMA TERMINATO **************************************************");
        	LogWriter.CloseFile();
            System.exit(0);
        }
    }

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


