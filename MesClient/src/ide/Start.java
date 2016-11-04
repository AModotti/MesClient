package ide;
import ide.main.MainWindow;
import ide.main.Notifications;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import bin.LogWriter;
import bin.Settings;
import bin.XMLParser;

public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					XMLParser xmlp = new XMLParser();
					
					if(xmlp.checkIfXMLFileExsists().equals("0")){
						xmlp.writeXMLFile();
						JOptionPane.showMessageDialog(null, "File di configurazione config.xml creato correttamente. Prima di poter utilizzare l'applicaizone è necessario configurarlo","Creazione File di Configurazione",JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}else{
						xmlp.readXMLFile();
					}
					
					LogWriter.OpenFile();
					LogWriter.write("[S] ************************************************** PROGRAMMA AVVIATO ****************************************************");
					//CONTROLLO CHE IL DATABASE SIA RAGGIUNGIBILE
					String checkdbconnection = Settings.checkDbConnection();
					
					//SE NON E' RAGGIUNGIBILE
					if(checkdbconnection.equals("false")){
						Notifications notification = new Notifications("[ Settings.checkDbConnection() ]","Impossibile connettersi al Database","Non è stato possibile connettersi al Database a causa di un errore. Controllare la connettività o le impostazioni di rete.");
			        	notification.setModal(true);
			        	notification.setVisible(true);
						System.exit(0);
					//SE E' RAGGIUNGIBILE	
					}else{
						MainWindow window = new MainWindow();
						window.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
