package ide.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.List;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import bin.IpAddress;
import bin.LogWriter;
import bin.Operator;
import bin.OperatorCache;
import bin.Settings;
import bin.WorkCenter;
import events.mainwindow.EventsChiudiProgramma;
import events.mainwindow.EventsTxtFldStato;
import events.mainwindow.EventsTxtFldTipoDichiarazione;
import events.mainwindow.EventsbtnAggiungiMatricola;
import events.mainwindow.EventsbtnAggregazione;
import events.mainwindow.EventsbtnCancellaMatricola;
import events.mainwindow.EventsbtnConfermaTransazione;
import events.mainwindow.EventsbtnDisaggregazione;
import events.mainwindow.EventsbtnMultiOrdine;
import events.mainwindow.EventstxtConfermaTransazione;
import events.mainwindow.EventstxtFldMatricola;
import events.mainwindow.EventstxtFldOdl;
import events.mainwindow.EventstxtFldOperazione;
import events.mainwindow.EventstxtFldRisorsa;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtFldMatricola;
	private JTable table;
	private JTextField txtDescrizioneCentro;
	private JTextField txtCodiceCentro;
	private JTextField txtFldTipoDichiarazione;
	private JTextField txtFldStato;
	private JTextField txtFldRisorsa;
	private JTextField txtFldOdl;
	private JTextField txtFldFase;
	private TableSituazioneMacchine tblSituazioneMacchine;	
	private JTextField txtConfermaTransazione;

	public MainWindow() {	
		
		//RECUPERO L'INDIRIZZO IP DEL CENTRO DI LAVORO
		IpAddress ipaddress = new IpAddress();
		
		WorkCenter wc = new WorkCenter(ipaddress);
		
		String environment = Settings.getEnvironment();
		String mode = wc.getWorkCenterMode(wc);
		String multiorder = wc.checkIfWorkCenterIsMultiOrder(wc);
		
		//SE IL CENTRO DI LAVORO E' ABILITATO RECUPERO I DATI RELATIVI ALLA MODALITA' MES IMPOSTATA
		if(wc.getWorkCenterMode(wc) != null){
			if(wc.getWorkCenterMode(wc).equals("0")){
				mode = "Macchina";
			}else if(wc.getWorkCenterMode(wc).equals("1")){
				mode = "Uomo";
			}else{
				mode = "Modalità non gestita";
			}
		}
		
		if(wc.getWorkCenterMode(wc) != null){
			LogWriter.write("[I] Indirizzo centro di lavoro: " + "[ " + ipaddress.getIpAddress() + " ]");
			LogWriter.write("[I] Nome centro di lavoro: " + "[ " + wc.getWorkCenterName(ipaddress) + " ]");
			LogWriter.write("[I] Descrizione centro di lavoro: " + "[ " + wc.getWorkCenterDescription(ipaddress) + " ]");
			LogWriter.write("[I] Ambiente: " + "[ " + environment + " ]");
			LogWriter.write("[I] Modalità centro di lavoro: " + "[ " + mode + " ]");
			LogWriter.write("[I] Abilitazione modalità multi ordine: " + "[ " + multiorder + " ]");
		}
		
		String image ="image.gif";
		Image icon = Toolkit.getDefaultToolkit().getImage(image);
				
		setIconImage(icon);
		setResizable(false);
		setTitle("Eurolls M.E.S. - Manufacturing Execution System - " + " [ Interfaccia: " + mode + " ] - [ Ambiente: " + environment + " ] - [ " + Settings.getParameters() + " ]");
		
		setBounds(5, 5, 1270, 900);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel pnlCentroDiLavoro = new JPanel();
		pnlCentroDiLavoro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Centro di Lavoro / Operatori Aggregati:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCentroDiLavoro.setBounds(741, 80, 513, 219);
		getContentPane().add(pnlCentroDiLavoro);
		pnlCentroDiLavoro.setLayout(null);
		
		List lstMatricoleAggregate = new List();
		lstMatricoleAggregate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lstMatricoleAggregate.setForeground(Color.BLUE);
		lstMatricoleAggregate.setBounds(10, 57, 493, 152);
		pnlCentroDiLavoro.add(lstMatricoleAggregate);
		
		//VERIFICO E CARICO LA LISTA DEGLI OPERATORI AGGREGATI AL CENTRO DI LAVORO
		if(wc.getWorkCenterMode(wc) != null){
			if(wc.getWorkCenterMode(wc).equals("0")){
				String opaggregated = wc.getOperatorAggregatedToWorkCenter(wc);
				String opaggregateddescription = wc.getWorkCenterOperatorAggregatedDescription(wc);
				
				if(opaggregated == null){	
				
				}else{
					lstMatricoleAggregate.add(opaggregated + " - " + opaggregateddescription);
					LogWriter.write("[I] Operatore aggregato al centro di lavoro: " + "[ " + opaggregated.trim() + " ]");
				}
			}else if(wc.getWorkCenterMode(wc).equals("1")){
				ArrayList<String> opsaggregated = wc.getOperatorsAggregatedToWorkCenter(wc);
				
				for(String item: opsaggregated) {
					if(!opsaggregated.isEmpty()){
						lstMatricoleAggregate.add(item);
						LogWriter.write("[I] Operatori aggregati al centro di lavoro: " + "[ " + item.trim() + " ]");
					}
				}
			}
		}
		
		Operator myoperator = new Operator();
		myoperator.setOperator(wc.getOperatorAggregatedToWorkCenter(wc));
										
		JButton btnAggregazione = new JButton("Aggregazione >>");
		btnAggregazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAggregazione.setBounds(528, 160, 203, 45);
		getContentPane().add(btnAggregazione);
		
		JButton btnDisaggregazione = new JButton("<< Disaggregazione");
		btnDisaggregazione.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDisaggregazione.setBounds(528, 217, 203, 45);
		getContentPane().add(btnDisaggregazione);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 71, -107, 23);
		getContentPane().add(scrollPane);
				
		table = new JTable();
		table.setBounds(21, 377, 154, -52);
		getContentPane().add(table);
		
		JPanel pnlOperatori = new JPanel();
		pnlOperatori.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista Operatori Memorizzati Localmente:", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlOperatori.setBounds(5, 80, 513, 219);
		getContentPane().add(pnlOperatori);
		pnlOperatori.setLayout(null);
		
		JLabel lblMatricola = new JLabel("Matricola:");
		lblMatricola.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatricola.setBounds(18, 22, 92, 28);
		pnlOperatori.add(lblMatricola);
		
		txtFldMatricola = new JTextField();
		txtFldMatricola.setForeground(Color.BLUE);
		txtFldMatricola.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldMatricola.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldMatricola.setBounds(120, 22, 165, 30);
		pnlOperatori.add(txtFldMatricola);
		txtFldMatricola.setColumns(10);
		
		JButton btnAggiungiMatricola = new JButton("Aggiungi");
		btnAggiungiMatricola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAggiungiMatricola.setBounds(295, 21, 99, 30);
		pnlOperatori.add(btnAggiungiMatricola);
		
		JButton btnCancellaMatricola = new JButton("Cancella");
		btnCancellaMatricola.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancellaMatricola.setBounds(404, 21, 99, 30);
		pnlOperatori.add(btnCancellaMatricola);
				
		List lstMatricoleAbilitate = new List();
		OperatorCache oc = new OperatorCache();
		
		//CARICO LA LISTA DEGLI OPERATORI MEMORIZZATI LOCALMENTE SUL CENTRO DI LAVORO
		ArrayList<String> operatorcacheList = oc.LoadOperatorCache(wc.getWorkCenterName(wc.getWorkCenter()));
		ArrayList<String> opsaggregated = wc.getOperatorsAggregatedToWorkCenter(wc);
		ArrayList<String> opsunion = new ArrayList<String>(operatorcacheList);
			
		opsunion.addAll(opsaggregated);
		
		ArrayList<String> opsintersec = new ArrayList<String>(operatorcacheList);
		
		opsintersec.retainAll(opsaggregated);
		opsunion.removeAll(opsintersec);
		
		for(String item: opsunion) {
			lstMatricoleAbilitate.add(item);
		}
						
		lstMatricoleAbilitate.setForeground(Color.BLUE);
		lstMatricoleAbilitate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lstMatricoleAbilitate.setBounds(10, 57, 493, 152);
		pnlOperatori.add(lstMatricoleAbilitate);
		
						
		txtDescrizioneCentro = new JTextField(wc.getWorkCenterDescription(wc.getWorkCenter()));
		txtDescrizioneCentro.setEditable(false);
		txtDescrizioneCentro.setForeground(Color.BLUE);
		txtDescrizioneCentro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDescrizioneCentro.setColumns(10);
		txtDescrizioneCentro.setBounds(122, 23, 381, 30);
		pnlCentroDiLavoro.add(txtDescrizioneCentro);
				
		txtCodiceCentro = new JTextField(wc.getWorkCenterName(wc.getWorkCenter()));
		txtCodiceCentro.setEditable(false);
		txtCodiceCentro.setForeground(Color.BLUE);
		txtCodiceCentro.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodiceCentro.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCodiceCentro.setBounds(10, 23, 108, 30);
		pnlCentroDiLavoro.add(txtCodiceCentro);
		txtCodiceCentro.setColumns(10);
		
		JPanel pnlDichiarazioni = new JPanel();
		pnlDichiarazioni.setBorder(new TitledBorder(null, "Dichiarazioni:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDichiarazioni.setBounds(741, 310, 513, 360);
		getContentPane().add(pnlDichiarazioni);
		pnlDichiarazioni.setLayout(null);
		
		JLabel lbTipoDichiarazione = new JLabel("Tipo di dichiarazione:");
		lbTipoDichiarazione.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTipoDichiarazione.setBounds(10, 35, 186, 30);
		pnlDichiarazioni.add(lbTipoDichiarazione);
		
		JLabel lblStato = new JLabel("Stato:");
		lblStato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStato.setBounds(10, 81, 186, 30);
		pnlDichiarazioni.add(lblStato);
		
		JLabel lblRisorsa = new JLabel("Risorsa:");
		
		if(wc.getWorkCenterMode(wc) != null){
			if(wc.getWorkCenterMode(wc).equals("0")){
				lblRisorsa.setText("Macchina:");
			}else if(wc.getWorkCenterMode(wc).equals("1")){
				lblRisorsa.setText("Operatore:");
			}else{
				lblRisorsa.setText("Risorsa:");
			}
		}
		
		lblRisorsa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRisorsa.setBounds(10, 136, 186, 30);
		pnlDichiarazioni.add(lblRisorsa);
		
		JLabel lblOdlFase = new JLabel("Ordine di Lavoro / Oper.:");
		lblOdlFase.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOdlFase.setBounds(10, 187, 186, 30);
		pnlDichiarazioni.add(lblOdlFase);
		
		txtFldTipoDichiarazione = new JTextField();
		txtFldTipoDichiarazione.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldTipoDichiarazione.setForeground(Color.BLUE);
		txtFldTipoDichiarazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldTipoDichiarazione.setColumns(10);
		txtFldTipoDichiarazione.setBounds(206, 32, 110, 32);
		pnlDichiarazioni.add(txtFldTipoDichiarazione);
		
		txtFldStato = new JTextField();
		txtFldStato.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldStato.setForeground(Color.BLUE);
		txtFldStato.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldStato.setColumns(10);
		txtFldStato.setBounds(206, 81, 110, 32);
		pnlDichiarazioni.add(txtFldStato);
		
		txtFldRisorsa = new JTextField();
		txtFldRisorsa.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldRisorsa.setForeground(Color.BLUE);
		txtFldRisorsa.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldRisorsa.setColumns(10);
		txtFldRisorsa.setBounds(206, 133, 110, 32);
		pnlDichiarazioni.add(txtFldRisorsa);
		
		txtFldOdl = new JTextField();
		txtFldOdl.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldOdl.setForeground(Color.BLUE);
		txtFldOdl.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldOdl.setColumns(10);
		txtFldOdl.setBounds(206, 184, 110, 32);
		pnlDichiarazioni.add(txtFldOdl);
		
		txtFldFase = new JTextField();
		txtFldFase.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldFase.setForeground(Color.BLUE);
		txtFldFase.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFldFase.setColumns(10);
		txtFldFase.setBounds(326, 184, 82, 32);
		pnlDichiarazioni.add(txtFldFase);
		
		JLabel lblLegendaLavTipoDichiarazione = new JLabel("\t[ LAV = Lavorazione ]");
		lblLegendaLavTipoDichiarazione.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaLavTipoDichiarazione.setBounds(325, 30, 178, 20);
		pnlDichiarazioni.add(lblLegendaLavTipoDichiarazione);
		
		JLabel lblLegendaInizioStato = new JLabel("[ 1 = Inizio Lavorazione ]");
		lblLegendaInizioStato.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaInizioStato.setBounds(325, 79, 178, 20);
		pnlDichiarazioni.add(lblLegendaInizioStato);
		
		JPanel pnlSituazioneMacchine = new JPanel();
		pnlSituazioneMacchine.setBorder(new TitledBorder(null, "Situazione Macchine:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSituazioneMacchine.setBounds(5, 310, 734, 551);
		getContentPane().add(pnlSituazioneMacchine);
		pnlSituazioneMacchine.setLayout(null);
		
		JScrollPane scpSituazioneMacchine = new JScrollPane();
		scpSituazioneMacchine.setBounds(10, 23, 714, 517);
		pnlSituazioneMacchine.add(scpSituazioneMacchine);
		
		tblSituazioneMacchine = new TableSituazioneMacchine(this,wc);
		tblSituazioneMacchine.getData();
		
		scpSituazioneMacchine.setViewportView(tblSituazioneMacchine);
	
		JPanel pnlCentroIndirizzoIp = new JPanel();
		pnlCentroIndirizzoIp.setBorder(new TitledBorder(null, "Centro di Lavoro / Indirizzo IP:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCentroIndirizzoIp.setBounds(5, 11, 1249, 61);
		getContentPane().add(pnlCentroIndirizzoIp);
		pnlCentroIndirizzoIp.setLayout(null);
				
		JLabel lblWorkCenterBanner = new JLabel();
		
		JButton btnConfermaTransazione = new JButton("Esegui Transazione");
		btnConfermaTransazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConfermaTransazione.setBounds(25, 235, 461, 50);
		pnlDichiarazioni.add(btnConfermaTransazione);
		
		JButton btnMultiOrdine = new JButton("Dichiarazione Multi Ordine");
		btnMultiOrdine.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMultiOrdine.setBounds(25, 296, 461, 50);
		pnlDichiarazioni.add(btnMultiOrdine);
		
		//VERIFICO CHE IL CENTRO DI LAVORO SIA ABILITATO ALLA MODALITA' MULTI ORDINE
		
		//SE E' BILITATO
		if(wc.getWorkCenterMode(wc) != null){
			if(multiorder.equals("1")){
				btnMultiOrdine.setEnabled(true); 
			//SE NON E' ABILITATO	
			}else{
				btnMultiOrdine.setEnabled(false);
			}
		}
		
		//VERIFICO CHE IL CENTRO DI LAVORO SIA ABILITATO
		
		//SE E' BILITATO
		if(wc.checkIfWorkCenterExists(wc.getWorkCenter()) == "true"){
			lblWorkCenterBanner.setText(wc.getWorkCenterName(wc.getWorkCenter()) + " - " + wc.getWorkCenterDescription(wc.getWorkCenter()) + " - [ IP: " + ipaddress.getIpAddress() + " ] - [ NOME: " + ipaddress.getHostName() + " ]");
			lblWorkCenterBanner.setForeground(Color.BLUE);
		//SE NON E' ABILITATO	
		}else{
			lblWorkCenterBanner.setText("ATTENZIONE! POSTAZIONE DI LAVORO NON ABILITATA." + " [ IP: " + ipaddress.getIpAddress() + " ]");
			LogWriter.write("[I] Postazione non abilitata");
			lblWorkCenterBanner.setForeground(Color.RED);
			btnAggiungiMatricola.setEnabled(false);
			btnCancellaMatricola.setEnabled(false);
			btnAggregazione.setEnabled(false);
			btnDisaggregazione.setEnabled(false);
			btnConfermaTransazione.setEnabled(false);
			btnMultiOrdine.setEnabled(false);
		}
				
		lblWorkCenterBanner.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWorkCenterBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkCenterBanner.setBounds(10, 21, 1226, 29);
		pnlCentroIndirizzoIp.add(lblWorkCenterBanner);
				
		JPanel pnlMessaggi = new JPanel();
		pnlMessaggi.setBorder(new TitledBorder(null, "Messaggi:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlMessaggi.setBounds(741, 670, 513, 191);
		getContentPane().add(pnlMessaggi);
		pnlMessaggi.setLayout(null);
		
		JTextArea txtAreaMessaggi = new JTextArea();
		txtAreaMessaggi.setBounds(10, 24, 516, 155);
		txtAreaMessaggi.setWrapStyleWord(true);
		txtAreaMessaggi.setLineWrap(true);
		txtAreaMessaggi.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtAreaMessaggi.setRows(7);
		txtAreaMessaggi.setEditable(false);
		
		JScrollPane scpAreaMessaggi = new JScrollPane();
		scpAreaMessaggi.setBounds(10, 20, 493, 160);
		pnlMessaggi.add(scpAreaMessaggi);
		scpAreaMessaggi.setViewportView(txtAreaMessaggi);
		
		txtConfermaTransazione = new JTextField();
		txtConfermaTransazione.setForeground(Color.BLUE);
		txtConfermaTransazione.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtConfermaTransazione.setBounds(415, 184, 1, 32);
		pnlDichiarazioni.add(txtConfermaTransazione);
		txtConfermaTransazione.setColumns(10);
		
		JLabel lblLegendaAttTipoDichiarazione = new JLabel("[ ATT = Attrezzaggio ]");
		lblLegendaAttTipoDichiarazione.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaAttTipoDichiarazione.setBounds(325, 43, 178, 20);
		pnlDichiarazioni.add(lblLegendaAttTipoDichiarazione);
		
		JLabel lblLegendaFineStato = new JLabel("[ 2 = Fine Lavorazione ]");
		lblLegendaFineStato.setHorizontalAlignment(SwingConstants.LEFT);
		lblLegendaFineStato.setBounds(325, 92, 178, 20);
		pnlDichiarazioni.add(lblLegendaFineStato);
						
		//EVENTI PULSANTE AGGIUNGI MATRICOLA
		btnAggiungiMatricola.addActionListener(new EventsbtnAggiungiMatricola(this,lstMatricoleAbilitate,lstMatricoleAggregate,txtFldMatricola,wc,myoperator));
		
		//EVENTI PULSANTE CANCELLA MATRICOLA 
		btnCancellaMatricola.addActionListener(new EventsbtnCancellaMatricola(this,lstMatricoleAbilitate,wc));	
		
		//EVENTI TEXTBOX MATRICOLA
		txtFldMatricola.addFocusListener(new EventstxtFldMatricola(this,txtFldMatricola,txtAreaMessaggi,myoperator));
		
		//EVENTI PULSANTE AGGREGAZIONE
		btnAggregazione.addActionListener(new EventsbtnAggregazione(this,txtFldTipoDichiarazione,txtAreaMessaggi,lstMatricoleAggregate,lstMatricoleAbilitate,myoperator,wc,tblSituazioneMacchine));
		
		//EVENTI PULSANTE DISAGGREGAZIONE
		btnDisaggregazione.addActionListener(new EventsbtnDisaggregazione(this,txtAreaMessaggi,lstMatricoleAggregate,lstMatricoleAbilitate,myoperator,wc,tblSituazioneMacchine));
		
		//EVENTI TEXTBOX TIPO DICHIARAZIONE
		txtFldTipoDichiarazione.addFocusListener(new EventsTxtFldTipoDichiarazione(this,txtFldTipoDichiarazione,txtAreaMessaggi));
		
		//EVENTI TEXTBOX STATO DICHIARAZIONE
		txtFldStato.addFocusListener(new EventsTxtFldStato(this,txtFldStato, txtAreaMessaggi));
		
		//EVENTI TEXTBOX RISORSA
		txtFldRisorsa.addFocusListener(new EventstxtFldRisorsa(this,txtAreaMessaggi,wc,txtFldRisorsa));
		
		//EVENTI TEXTBOX ORDINE DI PRODUZIONE
		txtFldOdl.addFocusListener(new EventstxtFldOdl(this,txtAreaMessaggi,txtFldOdl));
		
		//EVENTI TEXTBOX FASE ORDINE DI PRODUZIONE
		txtFldFase.addFocusListener(new EventstxtFldOperazione(this,txtAreaMessaggi,txtFldOdl,txtFldFase));
				
		//EVENTI TEXTBOX CONFERMA TRANSAZIONE
		txtConfermaTransazione.addFocusListener(new EventstxtConfermaTransazione(txtConfermaTransazione,btnConfermaTransazione));
		
		//EVENTI PULSANTE CONFERMA TRANSAZIONE
		btnConfermaTransazione.addActionListener(new EventsbtnConfermaTransazione(this,txtAreaMessaggi,wc,txtFldTipoDichiarazione,txtFldStato,txtFldRisorsa,txtFldOdl,txtFldFase,tblSituazioneMacchine));
		
		//EVENTI PULSANTE DICHIARAZIONE MULTI ORDINE
		btnMultiOrdine.addActionListener(new EventsbtnMultiOrdine(this,txtAreaMessaggi,wc,tblSituazioneMacchine));
		
		//EVENTI CHIUSURA MAIN WINDOWS
		addWindowListener(new EventsChiudiProgramma());
	}
}
