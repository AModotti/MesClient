package events.modialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import ide.main.MoDialog;

public class EventsTxtFldStatoMoDialog implements FocusListener {

	private MoDialog mybcdialog;
	private JTextField txtFldStato;
	private JTextArea txtAreaMessaggi;
	private JTextField txtVerStato;
	
	public EventsTxtFldStatoMoDialog(MoDialog mybcdialog,JTextField txtFldStato,JTextArea txtAreaMessaggi,JTextField txtVerStato) {

		this.mybcdialog = mybcdialog;
		this.txtFldStato = txtFldStato;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtVerStato = txtVerStato;
		
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		mybcdialog.setCursor(hourglassCursor);
		txtFldStato.setBackground(Color.WHITE);
		
		if(!txtFldStato.getText().equals("")){
			if((!txtFldStato.getText().equals("1")&&(!txtFldStato.getText().equals("2")))){
				txtFldStato.setForeground(Color.RED);
				txtAreaMessaggi.setForeground(Color.BLACK);
				txtAreaMessaggi.setBackground(Color.RED);
				txtAreaMessaggi.setText("STATO NON VALIDO");
				txtVerStato.setText("1");
			}else{
				txtFldStato.setForeground(Color.BLUE);
				txtAreaMessaggi.setText("");
				txtAreaMessaggi.setBackground(Color.WHITE);
				txtVerStato.setText("0");
			}
		}else{
			txtAreaMessaggi.setText("");
			txtAreaMessaggi.setBackground(Color.WHITE);
			txtVerStato.setText("1");
		}
		
		mybcdialog.setCursor(normalCursor);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		txtFldStato.setBackground(Color.YELLOW);
		
	}

}
