package events.branchdialog;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import bin.Branch;
import bin.LogWriter;
import bin.Operator;
import bin.Presence;
import bin.Transaction;
import bin.WorkCenter;
import ide.main.BranchDialog;

public class EventsbtnOkBranchDialog implements ActionListener{
	
	private BranchDialog mybrancdialog;
	private List lstMatricoleAggregate;
	private Operator myoperator;
	private WorkCenter myworkcenter;
	private Branch mybranch;
	private List lstMatricoleAbilitate;
	private JTextArea txtAreaMessaggi;
	private JRadioButton rdbtn20mg1o;
	private JRadioButton rdbtn20mg1m;
	private JRadioButton rdbtn29mg1;
	private JRadioButton rdbtn29mg2;
	private JRadioButton rdbtn29ric;
	private JRadioButton rdbtn21mg122;
	private JRadioButton rdbtn21mg135; 

	public EventsbtnOkBranchDialog(BranchDialog mybrancdialog,WorkCenter myworkcenter,Operator myoperator,Branch mybranch,List lstMatricoleAggregate,List lstMatricoleAbilitate,JTextArea txtAreaMessaggi,JRadioButton rdbtn20mg1o,JRadioButton rdbtn20mg1m,JRadioButton rdbtn29mg1,JRadioButton rdbtn29mg2,JRadioButton rdbtn29ric,JRadioButton rdbtn21mg122,JRadioButton rdbtn21mg135){
	
		this.mybrancdialog = mybrancdialog;
		this.myworkcenter = myworkcenter;
		this.myoperator = myoperator;
		this.mybranch = mybranch;
		this.lstMatricoleAggregate = lstMatricoleAggregate;
		this.lstMatricoleAbilitate = lstMatricoleAbilitate;
		this.txtAreaMessaggi = txtAreaMessaggi;
		this.rdbtn20mg1o = rdbtn20mg1o;
		this.rdbtn20mg1m = rdbtn20mg1m;
		this.rdbtn29mg1 = rdbtn29mg1;
		this.rdbtn29mg2 = rdbtn29mg2;
		this.rdbtn29ric = rdbtn29ric;
		this.rdbtn21mg122 = rdbtn21mg122;
		this.rdbtn21mg135 = rdbtn21mg135;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);		
		Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		
		mybrancdialog.setCursor(hourglassCursor);
		
		if((rdbtn20mg1o.isSelected() == false)&&(rdbtn20mg1m.isSelected() == false)&&(rdbtn29mg1.isSelected() == false)&&(rdbtn29mg2.isSelected() == false)&&(rdbtn29ric.isSelected() == false)&&(rdbtn21mg122.isSelected() == false)&&(rdbtn21mg135.isSelected() == false)){
			JOptionPane.showMessageDialog(null, "Attenzione è necessario selezionare almeno uno stabilimento.", "Info", JOptionPane.INFORMATION_MESSAGE);
		}else{
			//SE IL CENTRO E' MONO OPERATORE
			if(myworkcenter.getWorkCenterMode(myworkcenter).equals("0")){
				Presence mypresence = new Presence(myworkcenter,myoperator,mybranch);
		        Transaction mytransaction = new Transaction(myworkcenter,myoperator);
		        
		        mypresence.doAggregationMonoOperator();				//COSTRUTTORE CON 3 MEMBRI
				mytransaction.doEnterAggregationMonoOperator();		//COSTRUTTORE CON 2 MEMBRI		                          	
	            myworkcenter.setWorkCenterOperatorAggregation(myworkcenter,myoperator);
	            
				lstMatricoleAggregate.add(lstMatricoleAbilitate.getSelectedItem());
				LogWriter.write("[A] Esecuzione Classe: EventsbtnOkBranchDialog.actionPerformed()");
				LogWriter.write("[A] Aggregazione al centro di lavoro con operatore: " + "[ " + lstMatricoleAbilitate.getSelectedItem().trim() + " ]");
				LogWriter.write("[A] Aggregazione su stabilimento: " + "[ " + mybranch.getBranch() + " ]");
				lstMatricoleAbilitate.remove(lstMatricoleAbilitate.getSelectedItem());
				txtAreaMessaggi.setForeground(Color.WHITE);
				txtAreaMessaggi.setBackground(new Color(34, 139, 34));
				txtAreaMessaggi.setText("OPERATORE AGGREGATO AL CENTRO CON SUCCESSO.");
				mybrancdialog.dispose();
	    	}
			//SE IL CENTRO E' MULTI OPERATORE
			if(myworkcenter.getWorkCenterMode(myworkcenter).equals("1")){	
				Presence mypresence = new Presence(myworkcenter,myoperator,mybranch);
		        Transaction mytransaction = new Transaction(myworkcenter,myoperator);
		        
		        mypresence.doAggregationMultiOperator();			//COSTRUTTORE CON 3 MEMBRI
				mytransaction.doEnterAggregationMultiOperator();	//COSTRUTTORE CON 2 MEMBRI	                            	
	            myworkcenter.setWorkCenterOperatorAggregation(myworkcenter,myoperator);
	            
				lstMatricoleAggregate.add(lstMatricoleAbilitate.getSelectedItem());
				LogWriter.write("[A] Esecuzione Classe: EventsbtnOkBranchDialog.actionPerformed()");
				LogWriter.write("[A] Aggregazione al centro di lavoro con operatore: " + "[ " + lstMatricoleAbilitate.getSelectedItem().trim() + " ]");
				LogWriter.write("[A] Aggregazione su stabilimento: " + "[ " + mybranch.getBranch() + " ]");
				lstMatricoleAbilitate.remove(lstMatricoleAbilitate.getSelectedItem());
				txtAreaMessaggi.setForeground(Color.WHITE);
				txtAreaMessaggi.setBackground(new Color(34, 139, 34));
				txtAreaMessaggi.setText("OPERATORE AGGREGATO AL CENTRO CON SUCCESSO.");
				mybrancdialog.dispose();
			}
		}
		
		mybrancdialog.setCursor(normalCursor);
		
	}
	

}
