package ide.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import bin.Branch;
import bin.Operator;
import bin.WorkCenter;
import events.branchdialog.EventsbtnCancelBranchDialog;
import events.branchdialog.EventsbtnOkBranchDialog;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class BranchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	public BranchDialog(WorkCenter myworkcenter,Operator myoperator,List lstMatricoleAggregate,List lstMatricoleAbilitate,JTextArea txtAreaMessaggi) {
		
		Branch branch = new Branch("");
		
		setTitle("Eurolls M.E.S. - Manufacturing Execution System");

		setBounds(100, 100, 500, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selezione Azienda / Stabilimento / Branch: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		ButtonGroup rdbtngroup = new ButtonGroup();
				
		JRadioButton rdbtn20mg1o = new JRadioButton("[ 20MG1 ] - Eurolls Via Degli Ortolani, 54 (Attimis)");
		rdbtn20mg1o.setForeground(Color.BLUE);
		rdbtn20mg1o.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn20mg1o.setBounds(26, 35, 440, 23);
		rdbtngroup.add(rdbtn20mg1o);
		contentPanel.add(rdbtn20mg1o);
				
		rdbtn20mg1o.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("20MG1");
			}
		});
		
		JRadioButton rdbtn20mg1m = new JRadioButton("[ 20MG1 ] - Eurolls Via Malignani, 14 (Attimis)");
		rdbtn20mg1m.setForeground(Color.BLUE);
		rdbtn20mg1m.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn20mg1m.setBounds(26, 70, 440, 23);
		rdbtngroup.add(rdbtn20mg1m);
		contentPanel.add(rdbtn20mg1m);
		
		rdbtn20mg1m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("20MG1");
			}
		});
		
		JRadioButton rdbtn29mg1 = new JRadioButton("[ 29MG1 ] - Eurolls Via Strada di Salt, 66  (Remanzacco)");
		rdbtn29mg1.setForeground(Color.BLUE);
		rdbtn29mg1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn29mg1.setBounds(26, 105, 440, 23);
		rdbtngroup.add(rdbtn29mg1);
		contentPanel.add(rdbtn29mg1);
		
		rdbtn29mg1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("29MG1");
			}
		});
		
		JRadioButton rdbtn29mg2 = new JRadioButton("[ 29MG2 ] - Eurolls Via Strada di Salt, 66  (Remanzacco)");
		rdbtn29mg2.setForeground(Color.BLUE);
		rdbtn29mg2.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn29mg2.setBounds(26, 140, 440, 23);
		rdbtngroup.add(rdbtn29mg2);
		contentPanel.add(rdbtn29mg2);
		
		rdbtn29mg2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("29MG2");
			}
		});
		
		JRadioButton rdbtn29ric = new JRadioButton("[ 29RIC  ] - Eurolls Via Strada di Salt, 66  (Remanzacco)");
		rdbtn29ric.setForeground(Color.BLUE);
		rdbtn29ric.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn29ric.setBounds(26, 175, 440, 23);
		rdbtngroup.add(rdbtn29ric);
		contentPanel.add(rdbtn29ric);
		
		rdbtn29ric.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("29RIC");
			}
		});
		
		JRadioButton rdbtn21mg122 = new JRadioButton("[ 21MG1 ] - RollEng Via Divisione Julia, 22 (Villa Santina)");
		rdbtn21mg122.setForeground(Color.BLUE);
		rdbtn21mg122.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn21mg122.setBounds(26, 210, 440, 23);
		rdbtngroup.add(rdbtn21mg122);
		contentPanel.add(rdbtn21mg122);
		
		rdbtn21mg122.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("21MG1");
			}
		});
		
		
		JRadioButton rdbtn21mg135 = new JRadioButton("[ 21MG1 ] - RollEng Via Divisione Julia, 35 (Villa Santina)");
		rdbtn21mg135.setForeground(Color.BLUE);
		rdbtn21mg135.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtn21mg135.setBounds(26, 245, 440, 23);
		rdbtngroup.add(rdbtn21mg135);
		contentPanel.add(rdbtn21mg135);
		
		rdbtn21mg135.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				branch.setBranch("21MG1");
			}
		});
				
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		//EVENTI PULSANTE OK
		okButton.addActionListener(new EventsbtnOkBranchDialog(this,myworkcenter,myoperator,branch,lstMatricoleAggregate,lstMatricoleAbilitate,txtAreaMessaggi,rdbtn20mg1o,rdbtn20mg1m,rdbtn29mg1,rdbtn29mg2,rdbtn29ric,rdbtn21mg122,rdbtn21mg135));						

		//EVENTI PULSANTE CANCEL
		cancelButton.addActionListener(new EventsbtnCancelBranchDialog(this));	

	}
}
