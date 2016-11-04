package bin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class LogWriter {

    static BufferedWriter bw = null;
    
    static String path;
    static String decodedPath;
    static String parentPath;
    
    static Date date = new Date();
    static DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
    static DateFormat timeformat = new SimpleDateFormat("HH.mm.ss");
    
	static String datenow;
	static String timenow;
	
	static String logtracing = Settings.getLogTracing();
    	
	public static void OpenFile(){
		
		if(logtracing.equals("1")){
			
	        datenow = dateformat.format(date);
	        timenow = timeformat.format(date);
	               
	        File log = new File("log");
	        
	        if(!log.exists()){
	        	log.mkdir();
	        }
	        
			try {
				bw = new BufferedWriter(new FileWriter("log" + File.separator + datenow + "_" + timenow + ".log", true));
		    } catch (IOException ioe) {
		    	JOptionPane.showMessageDialog(null, ioe.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		    	ioe.printStackTrace();
		    }
		}
	}
	
	public static void write(String data) {
		 
		if(logtracing.equals("1")){
			
	        datenow = dateformat.format(date);
	        timenow = timeformat.format(date);
	
			try {			
				bw.write(datenow + " " + timenow + " ---> " + data);
				bw.newLine();
				bw.flush();
		    } catch (IOException ioe) {
		    	JOptionPane.showMessageDialog(null, ioe.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		    	ioe.printStackTrace();
		    } 
		}
	}	
	
	public static void CloseFile(){
		
		if(logtracing.equals("1")){
			
			try {
				bw.close();;
		    } catch (IOException ioe) {
		    	 ioe.printStackTrace();
		    } finally {            
		    	if (bw != null) try {
		    		bw.close();
		    	} catch (IOException ioe) {
		    		JOptionPane.showMessageDialog(null, ioe.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		    		ioe.printStackTrace();
		    	}
		    } 
		}
	}
	
}
