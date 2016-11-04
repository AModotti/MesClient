package events.mainwindow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventsTxtFldTipoDichiarazione implements FocusListener {
	
	private JFrame frmEurollsMes;
	private JTextField txtFldTipoDichiarazione;
	private JTextArea txtAreaMessaggi;
	
	public EventsTxtFldTipoDichiarazione(JFrame frmEurollsMes,JTextField txtFldTipoDichiarazione, JTextArea txtAreaMessaggi) {

		this.frmEurollsMes = frmEurollsMes;
		this.txtFldTipoDichiarazione = txtFldTipoDichiarazione;
		this.txtAreaMessaggi = txtAreaMessaggi;
	}
	
	@Override
	public void focusLost(FocusEvent arg0) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		frmEurollsMes.setCursor(hourglassCursor);
		
		if(!txtFldTipoDichiarazione.getText().equals("")){
			if((!txtFldTipoDichiarazione.getText().toUpperCase().equals("LAV")&&(!txtFldTipoDichiarazione.getText().toUpperCase().equals("ATT")))){
				txtFldTipoDichiarazione.setForeground(Color.RED);
				txtAreaMessaggi.setForeground(Color.BLACK);
				txtAreaMessaggi.setBackground(Color.RED);
				txtAreaMessaggi.setText("TIPO DI DICHIARAZIONE NON VALIDA.");
			}else{
				txtFldTipoDichiarazione.setText(txtFldTipoDichiarazione.getText().toUpperCase());
				txtFldTipoDichiarazione.setForeground(Color.BLUE);
				txtAreaMessaggi.setText("");
				txtAreaMessaggi.setBackground(Color.WHITE);
			}
		}else{
			txtAreaMessaggi.setText("");
			txtAreaMessaggi.setBackground(Color.WHITE);
		}
		txtFldTipoDichiarazione.setBackground(Color.WHITE);
		frmEurollsMes.setCursor(normalCursor);
	}

	@Override
	public void focusGained(FocusEvent e) {
		txtFldTipoDichiarazione.setBackground(Color.YELLOW);
		
	}

}
