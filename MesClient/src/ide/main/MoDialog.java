package ide.main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import bin.WorkCenter;
import events.modialog.EventsTxtFldStatoMoDialog;
import events.modialog.EventsTxtFldTipoDichiarazioneMoDialog;
import events.modialog.EventsbtnAnnullaMoDialog;
import events.modialog.EventsbtnCancellaOdlMoDialog;
import events.modialog.EventsbtnConfermaTransazioneMoDialog;
import events.modialog.EventstxtFldConfermaTransazioneMoDialog;
import events.modialog.EventstxtFldOdlMoDialog;
import events.modialog.EventstxtFldOperazioneMoDialog;
import events.modialog.EventstxtFldRisorsaMoDialog;

import java.awt.Font;
import java.awt.List;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFldOdl;
	private JTextField txtFldOperazione;
	private JTextField txtFldNr;
	private JTextField txtFldTipoDichiarazione;
	private JTextField txtFldStato;
	private JTextField txtFldRisorsa;
	private JTextField txtVerTipoDichiarazione;
	private JTextField txtVerStato;
	private JTextField txtVerRisorsa;
	private JTextField txtVerOdl;
	private JTextField txtVerOperazione;
	private JTextField txtVerListaOdl;
	private JTextField txtFldEseguiTransazione;


	public MoDialog(WorkCenter wc, JTextArea txtAreaMessaggi,TableSituazioneMacchine tblSituazioneMacchine) {
						
		setTitle("Dichiarazione Multi Ordine");
		setBounds(100, 100, 530, 590);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnlOdl = new JPanel();
		pnlOdl.setBounds(10, 175, 504, 224);
		pnlOdl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ordini di produzione:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPanel.add(pnlOdl);
		pnlOdl.setLayout(null);
		
		txtFldOdl = new JTextField();
		txtFldOdl.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldOdl.setForeground(Color.BLUE);
		txtFldOdl.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldOdl.setColumns(10);
		txtFldOdl.setBounds(213, 24, 250, 32);
		pnlOdl.add(txtFldOdl);
		
		JLabel lbOdl = new JLabel("Ordine di Produzione:");
		lbOdl.setHorizontalAlignment(SwingConstants.RIGHT);
		lbOdl.setBounds(10, 24, 193, 32);
		pnlOdl.add(lbOdl);
		
		List lstOdlInseriti = new List();
		lstOdlInseriti.setFont(new Font("Tahoma", Font.BOLD, 16));
		lstOdlInseriti.setForeground(Color.BLUE);
		lstOdlInseriti.setBounds(213, 66, 250, 104);
		pnlOdl.add(lstOdlInseriti);
		
		JLabel lblListaOdl = new JLabel("Lista ordini inseriti:");
		lblListaOdl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblListaOdl.setBounds(10, 66, 193, 32);
		pnlOdl.add(lblListaOdl);
		
		JButton btnCancellaOdl = new JButton("Cancella Ordine Selezionato");
		btnCancellaOdl.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancellaOdl.setBounds(213, 176, 250, 32);
		pnlOdl.add(btnCancellaOdl);
		
		JPanel pnlOperazioni = new JPanel();
		pnlOperazioni.setBounds(10, 400, 504, 73);
		pnlOperazioni.setBorder(new TitledBorder(null, "Operazione:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(pnlOperazioni);
		pnlOperazioni.setLayout(null);
		
		JLabel lblOperazione = new JLabel("Numero Operazione:");
		lblOperazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperazione.setBounds(10, 20, 193, 32);
		pnlOperazioni.add(lblOperazione);
		
		txtFldOperazione = new JTextField();
		txtFldOperazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldOperazione.setForeground(Color.BLUE);
		txtFldOperazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldOperazione.setColumns(10);
		txtFldOperazione.setBounds(213, 20, 250, 32);
		
		pnlOperazioni.add(txtFldOperazione);
		
		txtFldNr = new JTextField();
		txtFldNr.setEditable(false);
		txtFldNr.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldNr.setForeground(Color.BLUE);
		txtFldNr.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldNr.setBounds(143, 176, 60, 32);
		pnlOdl.add(txtFldNr);
		txtFldNr.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numero:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 176, 123, 32);
		pnlOdl.add(lblNewLabel);
		
		JButton btnEseguiTransazione = new JButton("Esegui Transazione");
		btnEseguiTransazione.setBounds(10, 484, 387, 50);
		contentPanel.add(btnEseguiTransazione);
		btnEseguiTransazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEseguiTransazione.setActionCommand("OK");
		getRootPane().setDefaultButton(btnEseguiTransazione);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(407, 484, 107, 50);
		contentPanel.add(btnAnnulla);
		btnAnnulla.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAnnulla.setActionCommand("Cancel");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo / Stato / Risorsa:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 504, 161);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lbTipoDichiarazione = new JLabel("Tipo di dichiarazione:");
		lbTipoDichiarazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTipoDichiarazione.setBounds(10, 27, 154, 30);
		panel.add(lbTipoDichiarazione);
		
		txtFldTipoDichiarazione = new JTextField();
		txtFldTipoDichiarazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldTipoDichiarazione.setForeground(Color.BLUE);
		txtFldTipoDichiarazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldTipoDichiarazione.setColumns(10);
		txtFldTipoDichiarazione.setBounds(174, 27, 110, 32);
		panel.add(txtFldTipoDichiarazione);
		
		JLabel lblLegendaLavTipoDichiarazione = new JLabel("\t[ LAV = Lavorazione ]");
		lblLegendaLavTipoDichiarazione.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaLavTipoDichiarazione.setBounds(294, 27, 200, 20);
		panel.add(lblLegendaLavTipoDichiarazione);
		
		JLabel lblLegendaAttTipoDichiarazione = new JLabel("[ ATT = Attrezzaggio ]");
		lblLegendaAttTipoDichiarazione.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaAttTipoDichiarazione.setBounds(294, 40, 200, 20);
		panel.add(lblLegendaAttTipoDichiarazione);
		
		JLabel lblStato = new JLabel("Stato:");
		lblStato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStato.setBounds(10, 70, 154, 30);
		panel.add(lblStato);
		
		txtFldStato = new JTextField();
		txtFldStato.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldStato.setForeground(Color.BLUE);
		txtFldStato.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldStato.setColumns(10);
		txtFldStato.setBounds(174, 70, 110, 32);
		panel.add(txtFldStato);
		
		JLabel lblLegendaInizioStato = new JLabel("[ 1 = Inizio Lavorazione ]");
		lblLegendaInizioStato.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaInizioStato.setBounds(294, 70, 200, 20);
		panel.add(lblLegendaInizioStato);
		
		JLabel lblLegendaFineStato = new JLabel("[ 2 = Fine Lavorazione ]");
		lblLegendaFineStato.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaFineStato.setBounds(294, 83, 200, 20);
		panel.add(lblLegendaFineStato);
		
		JLabel lblRisorsa = new JLabel("Risorsa:");
		lblRisorsa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRisorsa.setBounds(10, 113, 154, 30);
		panel.add(lblRisorsa);
		
		txtFldRisorsa = new JTextField();
		txtFldRisorsa.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldRisorsa.setForeground(Color.BLUE);
		txtFldRisorsa.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldRisorsa.setColumns(10);
		txtFldRisorsa.setBounds(174, 113, 110, 32);
		panel.add(txtFldRisorsa);
				
		txtVerOdl = new JTextField("1");
		txtVerOdl.setVisible(false);
		txtVerOdl.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerOdl.setForeground(Color.BLUE);
		txtVerOdl.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtVerOdl.setColumns(10);
		txtVerOdl.setBounds(474, 24, 20, 32);
		pnlOdl.add(txtVerOdl);
		
		txtVerListaOdl = new JTextField("1");
		txtVerListaOdl.setVisible(false);
		txtVerListaOdl.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerListaOdl.setForeground(Color.BLUE);
		txtVerListaOdl.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtVerListaOdl.setColumns(10);
		txtVerListaOdl.setBounds(474, 66, 20, 104);
		pnlOdl.add(txtVerListaOdl);
		
		txtVerTipoDichiarazione = new JTextField("1");
		txtVerTipoDichiarazione.setVisible(false);
		txtVerTipoDichiarazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerTipoDichiarazione.setForeground(Color.BLUE);
		txtVerTipoDichiarazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtVerTipoDichiarazione.setColumns(10);
		txtVerTipoDichiarazione.setBounds(474, 27, 20, 32);
		panel.add(txtVerTipoDichiarazione);
		
		txtVerStato = new JTextField("1");
		txtVerStato.setVisible(false);
		txtVerStato.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerStato.setForeground(Color.BLUE);
		txtVerStato.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtVerStato.setColumns(10);
		txtVerStato.setBounds(474, 70, 20, 32);
		panel.add(txtVerStato);
		
		txtVerRisorsa = new JTextField("1");
		txtVerRisorsa.setVisible(false);
		txtVerRisorsa.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerRisorsa.setForeground(Color.BLUE);
		txtVerRisorsa.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtVerRisorsa.setColumns(10);
		txtVerRisorsa.setBounds(474, 113, 20, 32);
		panel.add(txtVerRisorsa);
		
		txtVerOperazione = new JTextField("1");
		txtVerOperazione.setVisible(false);
		txtVerOperazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerOperazione.setForeground(Color.BLUE);
		txtVerOperazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtVerOperazione.setColumns(10);
		txtVerOperazione.setBounds(474, 20, 20, 32);
		pnlOperazioni.add(txtVerOperazione);
		
		txtFldEseguiTransazione = new JTextField();
		txtFldEseguiTransazione.setForeground(Color.BLUE);
		txtFldEseguiTransazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldEseguiTransazione.setBounds(468, 20, 1, 32);
		pnlOperazioni.add(txtFldEseguiTransazione);
		txtFldEseguiTransazione.setColumns(10);
		
				
		//EVENTI TEXTBOX TIPO DICHIARAZIONE
		txtFldTipoDichiarazione.addFocusListener(new EventsTxtFldTipoDichiarazioneMoDialog(this,txtFldTipoDichiarazione,txtAreaMessaggi,txtVerTipoDichiarazione));
		
		//EVENTI TEXTBOX STATO DICHIARAZIONE
		txtFldStato.addFocusListener(new EventsTxtFldStatoMoDialog(this,txtFldStato, txtAreaMessaggi,txtVerStato));
				
		//EVENTI TEXTBOX RISORSA
		txtFldRisorsa.addFocusListener(new EventstxtFldRisorsaMoDialog(this,txtAreaMessaggi,wc,txtFldStato,txtFldRisorsa,txtVerRisorsa,txtFldOperazione,lstOdlInseriti,txtVerOdl,txtVerListaOdl,txtVerOperazione,txtFldNr));
		
		//EVENTI TEXTBOX ORDINE DI PRODUZIONE
		txtFldOdl.addFocusListener(new EventstxtFldOdlMoDialog(this,txtAreaMessaggi,lstOdlInseriti,txtFldOdl,txtFldNr,txtVerOdl,txtFldOperazione));
				
		//EVENTI PULSANTE CANCELLA ORDINE SELEZIONATO
		btnCancellaOdl.addActionListener(new EventsbtnCancellaOdlMoDialog(this,lstOdlInseriti,txtFldNr,txtVerListaOdl,txtFldOperazione));
		
		//EVENTI TEXTBOX OPERAZIONE
		txtFldOperazione.addFocusListener(new EventstxtFldOperazioneMoDialog(this,txtAreaMessaggi,lstOdlInseriti,txtFldOperazione,txtVerOperazione,txtVerListaOdl));
		
		//EVENTI TEXTBOX CONFERMA TRANSAZIONE
		txtFldEseguiTransazione.addFocusListener(new EventstxtFldConfermaTransazioneMoDialog(txtFldEseguiTransazione,btnEseguiTransazione));
				
		//EVENTI PULSANTE CONFERMA TRANSAZIONE
		btnEseguiTransazione.addActionListener(new EventsbtnConfermaTransazioneMoDialog(txtFldTipoDichiarazione,txtFldStato,txtFldRisorsa,lstOdlInseriti,txtFldOperazione,wc,txtAreaMessaggi,this,txtVerTipoDichiarazione,txtVerStato,txtVerRisorsa,txtVerOdl,txtVerListaOdl,txtVerOperazione,tblSituazioneMacchine));		
									
		//EVENTI PULSANTE ANNULLA
		btnAnnulla.addActionListener(new EventsbtnAnnullaMoDialog(this));
		
	}
}
