package events.mainwindow;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class EventstxtConfermaTransazione implements FocusListener {
	
	private JTextField txtConfermaTransazione;
	private JButton btnConfermaTransazione;
	
	public EventstxtConfermaTransazione(JTextField txtConfermaTransazione, JButton btnConfermaTransazione) {

		this.txtConfermaTransazione = txtConfermaTransazione;
		this.btnConfermaTransazione = btnConfermaTransazione;

	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(txtConfermaTransazione.getText().toUpperCase().equals("F3")){
			btnConfermaTransazione.doClick();
			txtConfermaTransazione.setText("");
		}
	}

}
