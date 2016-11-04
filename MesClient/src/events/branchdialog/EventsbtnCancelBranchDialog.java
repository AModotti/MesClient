package events.branchdialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ide.main.BranchDialog;

public class EventsbtnCancelBranchDialog implements ActionListener{
	
	private BranchDialog mybrancdialog;
	
	public EventsbtnCancelBranchDialog(BranchDialog mybrancdialog){
		
		this.mybrancdialog = mybrancdialog;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		mybrancdialog.dispose();
	
	}
}
