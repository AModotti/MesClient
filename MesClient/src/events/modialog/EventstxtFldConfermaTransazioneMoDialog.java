package events.modialog;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class EventstxtFldConfermaTransazioneMoDialog implements FocusListener {
	
	private JTextField txtFldEseguiTransazione;
	private JButton btnEseguiTransazione;
	
	public EventstxtFldConfermaTransazioneMoDialog(JTextField txtFldEseguiTransazione, JButton btnEseguiTransazione) {

		this.txtFldEseguiTransazione = txtFldEseguiTransazione;
		this.btnEseguiTransazione = btnEseguiTransazione;

	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(txtFldEseguiTransazione.getText().toUpperCase().equals("F3")){
			btnEseguiTransazione.doClick();
			txtFldEseguiTransazione.setText("");
		}
	}


}
