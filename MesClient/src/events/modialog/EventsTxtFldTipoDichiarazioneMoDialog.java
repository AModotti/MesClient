package events.modialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import ide.main.MoDialog;

public class EventsTxtFldTipoDichiarazioneMoDialog implements FocusListener {
	
	private MoDialog mybcdialog;
	private JTextField txtFldTipoDichiarazione;
	private JTextArea txtAreaMessaggi;
	private JTextField txtVerTipoDichiarazione;
	
	public EventsTxtFldTipoDichiarazioneMoDialog(MoDialog mybcdialog,JTextField txtFldTipoDichiarazione,JTextArea txtAreaMessaggi,JTextField txtVerTipoDichiarazione) {

		this.mybcdialog = mybcdialog;
		this.txtFldTipoDichiarazione = txtFldTipoDichiarazione;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtVerTipoDichiarazione = txtVerTipoDichiarazione;

	}
	
	@Override
	public void focusLost(FocusEvent arg0) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mybcdialog.setCursor(hourglassCursor);
		txtFldTipoDichiarazione.setBackground(Color.WHITE);
		
		if(!txtFldTipoDichiarazione.getText().equals("")){
			if((!txtFldTipoDichiarazione.getText().toUpperCase().equals("LAV")&&(!txtFldTipoDichiarazione.getText().toUpperCase().equals("ATT")))){
				txtFldTipoDichiarazione.setForeground(Color.RED);
				txtAreaMessaggi.setForeground(Color.BLACK);
				txtAreaMessaggi.setBackground(Color.RED);
				txtAreaMessaggi.setText("TIPO DI DICHIARAZIONE NON VALIDA");
				txtVerTipoDichiarazione.setText("1");
			}else{
				txtFldTipoDichiarazione.setText(txtFldTipoDichiarazione.getText().toUpperCase());
				txtFldTipoDichiarazione.setForeground(Color.BLUE);
				txtAreaMessaggi.setText("");
				txtAreaMessaggi.setBackground(Color.WHITE);
				txtVerTipoDichiarazione.setText("0");
			}
		}else{
			txtAreaMessaggi.setText("");
			txtAreaMessaggi.setBackground(Color.WHITE);
			txtVerTipoDichiarazione.setText("1");
		}
		
		mybcdialog.setCursor(normalCursor);
	}

	@Override
	public void focusGained(FocusEvent e) {
		txtFldTipoDichiarazione.setBackground(Color.YELLOW);
		
	}

}
