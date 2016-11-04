package events.mainwindow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EventsTxtFldStato implements FocusListener {

	private JFrame frmEurollsMes;
	private JTextField txtFldStato;
	private JTextArea txtAreaMessaggi;
	
	public EventsTxtFldStato(JFrame frmEurollsMes,JTextField txtFldStato,JTextArea txtAreaMessaggi) {

		this.frmEurollsMes = frmEurollsMes;
		this.txtFldStato = txtFldStato;
		this.txtAreaMessaggi = txtAreaMessaggi;
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		frmEurollsMes.setCursor(hourglassCursor);
		txtFldStato.setBackground(Color.WHITE);
		
		if(!txtFldStato.getText().equals("")){
			if((!txtFldStato.getText().equals("1")&&(!txtFldStato.getText().equals("2")))){
				txtFldStato.setForeground(Color.RED);
				txtAreaMessaggi.setForeground(Color.BLACK);
				txtAreaMessaggi.setBackground(Color.RED);
				txtAreaMessaggi.setText("STATO NON VALIDO.");
			}else{
				txtFldStato.setForeground(Color.BLUE);
				txtAreaMessaggi.setText("");
				txtAreaMessaggi.setBackground(Color.WHITE);
			}
		}else{
			txtAreaMessaggi.setText("");
			txtAreaMessaggi.setBackground(Color.WHITE);
		}
		frmEurollsMes.setCursor(normalCursor);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		txtFldStato.setBackground(Color.YELLOW);
		
	}

}
