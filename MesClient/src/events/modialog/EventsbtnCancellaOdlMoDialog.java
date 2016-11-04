package events.modialog;

import java.awt.Cursor;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ide.main.MoDialog;

public class EventsbtnCancellaOdlMoDialog implements ActionListener {
	
	private MoDialog mybcdialog;
	private List lstOdlInseriti;
	private JTextField txtFldNr;
	private JTextField txtVerListaOdl;
	private JTextField txtFldOperazione;
	
	public EventsbtnCancellaOdlMoDialog(MoDialog mybcdialog,List lstOdlInseriti,JTextField txtFldNr,JTextField txtVerListaOdl,JTextField txtFldOperazione) {

		this.mybcdialog = mybcdialog;
		this.lstOdlInseriti = lstOdlInseriti;
		this.txtFldNr = txtFldNr;
		this.txtVerListaOdl = txtVerListaOdl;
		this.txtFldOperazione = txtFldOperazione;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		mybcdialog.setCursor(hourglassCursor);
		
		int selectedIndex = lstOdlInseriti.getSelectedIndex();
		if (selectedIndex != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sucuro di voler cancellare l'ordine di produzione selezionato?", "Cancellazione Ordine di produzione",JOptionPane.OK_CANCEL_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				lstOdlInseriti.remove(selectedIndex);
				txtFldNr.setText(Integer.toString(lstOdlInseriti.getItemCount()));
				txtFldOperazione.requestFocus();
				txtFldOperazione.setText("");
				if(lstOdlInseriti.getItemCount() == 0){
					txtVerListaOdl.setText("1");
				}else{
					txtVerListaOdl.setText("0");
				}
			}
		}
				
		mybcdialog.setCursor(normalCursor);
		
	}
	

}
