package events.modialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.List;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import bin.LogWriter;
import bin.Settings;
import bin.WorkOrder;
import ide.main.MoDialog;
import ide.main.Notifications;


public class EventstxtFldOdlMoDialog implements FocusListener {
	
	private MoDialog mybcdialog;
	private JTextArea txtAreaMessaggi;
	private List lstOdlInseriti;
	private JTextField txtFldOdl;	
	private JTextField txtFldNr;
	private JTextField txtVerOdl;

	
	public EventstxtFldOdlMoDialog(MoDialog mybcdialog,JTextArea txtAreaMessaggi,List lstOdlInseriti,JTextField txtFldOdl,JTextField txtFldNr,JTextField txtVerOdl,JTextField txtFldOperazione) {

		this.mybcdialog = mybcdialog;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.txtFldOdl = txtFldOdl;
		this.txtFldNr = txtFldNr;
		this.txtVerOdl = txtVerOdl;
		this.lstOdlInseriti = lstOdlInseriti;

	}
	
	@Override
	public void focusLost(FocusEvent e) {
		
		int isnumeric = 0;
		int isallnumeric = 0;
		int isorderadded = 0;
		int isallorderadded = 0;
		int isorderinsamebranch = 0;
		int isallorderinsamebranch = 0;
		String epurateworkorder;
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				
		mybcdialog.setCursor(hourglassCursor);
		
		try {
			//CONTROLLO CHE IL DATABASE SIA RAGGIUNGIBILE
			String checkdbconnection = Settings.checkDbConnection();
			
			//SE NON E' RAGGIUNGIBILE
			if(checkdbconnection.equals("false")){
				Notifications notification = new Notifications("[ Settings.checkDbConnection() ]","Impossibile connettersi al Database","Non è stato possibile connettersi al Database a causa di un errore. Controllare la connettività o le impostazioni di rete.");
	        	notification.setModal(true);
	        	notification.setVisible(true);
	        	mybcdialog.setCursor(normalCursor);
			//SE E' RAGGIUNGIBILE	
			}else{
				txtFldOdl.setBackground(Color.WHITE);
				
				WorkOrder wo = new WorkOrder(txtFldOdl.getText());
				//SE IL CAMPO RELATIVO AL NUMERO DI WORK ORDER NON E' VUOTO
				if(!txtFldOdl.getText().equals("")){
					for (char c:txtFldOdl.getText().toCharArray()){
				        if (!Character.isDigit(c)) {
				        	isnumeric = 1;
				        }else{
				        	isnumeric = 0;
				        }
				        isallnumeric = isallnumeric + isnumeric;
				    }
					//SE IL CAMPO RELATIVO AL NUMERO DI WORK ORDER NON E' UN VALORE NUMERICO
					if(isallnumeric >= 1){
						txtFldOdl.setForeground(Color.RED);
						txtAreaMessaggi.setForeground(Color.BLACK);
						txtAreaMessaggi.setBackground(Color.RED);
						txtAreaMessaggi.setText("ORDINE DI PRODUZIONE INESISTENTE");
						txtFldOdl.requestFocus();
						txtVerOdl.setText("1");
					//SE IL CAMPO RELATIVO AL NUMERO DI WORK ORDER E' UN VALORE NUMERICO	
					}else{
						//SE IL WORK ORDER NON ESISTE
						if(wo.checkIfWorkOrderExists(wo).equals("false")){
							txtFldOdl.setForeground(Color.RED);
							txtAreaMessaggi.setForeground(Color.BLACK);
							txtAreaMessaggi.setBackground(Color.RED);
							txtAreaMessaggi.setText("ORDINE DI PRODUZIONE INESISTENTE");
							txtFldOdl.requestFocus();
							txtVerOdl.setText("1");
						//ALTRIMENTI SE IL WORK ORDER E' IN STATO CHIUSO	
						}else if(wo.checkIfWorkOrderIsClose(wo).equals("false")){
							txtFldOdl.setForeground(Color.RED);
							txtAreaMessaggi.setForeground(Color.BLACK);
							txtAreaMessaggi.setBackground(Color.RED);
							txtAreaMessaggi.setText("ORDINE DI PRODUZIONE CHIUSO O ANNULLATO");
							txtFldOdl.requestFocus();
							txtVerOdl.setText("1");
						//SE IL WORK ORDER ESISTE E NON E' CHIUSO	
						}else{
							//VERIFICO CHE TUTTI I WORK ORDER APPARTENGANO ALLO STESSO BRANCH	
							WorkOrder workorder = new WorkOrder(txtFldOdl.getText().trim());
							String branchworkorder = workorder.getWorkOrderBranch(workorder);
							
							for(int i=0;i<lstOdlInseriti.getItemCount();i++){
								WorkOrder workorderinlist = new WorkOrder(lstOdlInseriti.getItem(i));
								String branchworkorderinlist = workorderinlist.getWorkOrderBranch(workorderinlist);
								
								if(!branchworkorderinlist.equals(branchworkorder)){
									isorderinsamebranch = 1;								
								}else{
									isorderinsamebranch = 0;
								}
								isallorderinsamebranch = isorderinsamebranch + isorderinsamebranch;
							}
							//SE NON APPARTENGANO ALLO STESSO BRANCH
							if(isallorderinsamebranch >= 1){
								txtFldOdl.setForeground(Color.RED);
								txtAreaMessaggi.setForeground(Color.BLACK);
								txtAreaMessaggi.setBackground(Color.RED);
								txtAreaMessaggi.setText("ORDINI NON APPARTENENTI ALLO STESSO BRANCH");
								txtFldOdl.requestFocus();
								txtVerOdl.setText("1");
							//APPARTENGANO ALLO STESSO BRANCH	
							}else{
								//SE LA LISTA ORDINI E' VUOTA
								if(lstOdlInseriti.getItemCount() == 0){
									lstOdlInseriti.add(txtFldOdl.getText());
									txtFldNr.setText(Integer.toString(lstOdlInseriti.getItemCount()));
									txtFldOdl.setForeground(Color.BLUE);
									txtAreaMessaggi.setBackground(Color.WHITE);
									txtAreaMessaggi.setText("");
									txtFldOdl.setText("");
									txtFldOdl.requestFocus();
									txtVerOdl.setText("0");
								//SE LA LISTA ORDINI NON E' VUOTA	
								}else{
									for(int i=0;i<lstOdlInseriti.getItemCount();i++){
										if(lstOdlInseriti.getItem(i).substring(1,2).equals("*")){
							            	epurateworkorder = lstOdlInseriti.getItem(i).substring(3,lstOdlInseriti.getItem(i).length());
							            }else{
							            	epurateworkorder =  lstOdlInseriti.getItem(i);
							            }
										if(epurateworkorder.equals(txtFldOdl.getText().trim())){
											isorderadded = 1;								
										}else{
											isorderadded = 0;
										}
										isallorderadded = isallorderadded + isorderadded;
									}
									if(isallorderadded >= 1){
										txtFldOdl.setForeground(Color.RED);
										txtAreaMessaggi.setForeground(Color.BLACK);
										txtAreaMessaggi.setBackground(Color.RED);
										txtAreaMessaggi.setText("ORDINE GIA' PRESENTE ALL'INTERNO DELLA LISTA ORDINI INSERITI");
										txtFldOdl.requestFocus();
										txtVerOdl.setText("1");
									}else{
										lstOdlInseriti.add(txtFldOdl.getText());
										txtFldNr.setText(Integer.toString(lstOdlInseriti.getItemCount()));
										txtFldOdl.setForeground(Color.BLUE);
										txtAreaMessaggi.setBackground(Color.WHITE);
										txtAreaMessaggi.setText("");
										txtFldOdl.setText("");
										txtFldOdl.requestFocus();
										txtVerOdl.setText("0");
									}
								}
							}
						}				
					}
				}else{
		
				}
				
				mybcdialog.setCursor(normalCursor);
			}
			
		} catch (Exception dbexc) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ EventstxtFldOdlMoDialog.focusLost() ]");
        	LogWriter.write("[E] " + dbexc.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		txtFldOdl.setBackground(Color.YELLOW);
	}
}
