package bin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Settings {
    
	private static String dbconnectionstring;
    private static String driver; 
    private static String dbuser; 
    private static String importtojdeuser;
    private static String dbpassword;
    private static String environment;
    private static String mesuser;
    private static String mesprogram;
    private static String mesjob;
    private static String mesversion;
    private static String isenabledonmesversion;
    private static String updateserverpath;
    private static String application;
    private static String enablelog;

    public static void storageSettings(String xmldbconnectionstring,String xmldriver,String xmldbuser,String xmlimporttojdeuser,String xmlupdateserverpath,String xmlapplication,String xmlenablelog){
    	
    	dbconnectionstring = xmldbconnectionstring.trim();
    	driver = xmldriver.trim();
    	dbuser = xmldbuser.trim();
    	importtojdeuser = xmlimporttojdeuser.trim();
    	updateserverpath = xmlupdateserverpath.trim();
    	application = xmlapplication.trim();
    	enablelog = xmlenablelog.trim();
    	
    	if(dbuser.equals("PRODDTA")){
    		dbpassword = "PRODDTA";
    	}else if(dbuser.equals("CRPDTA")){
    		dbpassword = "CRPDTA";
    	}else if(dbuser.equals("admin")){
    		dbpassword = "sowhat";
    	}else if(dbuser.equals("root")){
    		dbpassword = "sowhat";
    	}else{
    		dbpassword = "";
    	}
    	
    }
    
    public static String getParameters() {
    	
    	return "Update server : " + updateserverpath + " ] - [ " + "Log : " + enablelog;
    }
    
    public static String getUpdateServerPath() {
    	
    	return updateserverpath;
    }
    
    public static String getDriver() {
    	
    	return driver;
    }

    public static String getImportToJdeUser() {
    	
    	return importtojdeuser;
    }

    public static String getLogTracing() {
    	
    	return enablelog;
    }
    
    public static String getApplication() {
    	
    	return application;
    }

    public static String checkDbConnection() {
    	 
    	 String checkdbConnection = null;
         
         try{
             Class.forName(driver).newInstance();
             checkdbConnection = "true";

         }catch (ClassNotFoundException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
             checkdbConnection = "false";

         }catch (InstantiationException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
             checkdbConnection = "false";

         }catch (IllegalAccessException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
             checkdbConnection = "false";
         }
         

         try{
         	Connection dbConnection = DriverManager.getConnection(dbconnectionstring,dbuser,dbpassword);
         	dbConnection.close();
         	checkdbConnection = "true";
         }catch (SQLException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
             checkdbConnection = "false";
         }

         return checkdbConnection;
 
    }
    
    public static Connection getDbConnection() {
 
    	 Connection dbConnection = null;

         try{

             Class.forName(driver).newInstance();

         }catch (ClassNotFoundException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         }catch (InstantiationException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         }catch (IllegalAccessException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         }
         

         try{

             dbConnection = DriverManager.getConnection(dbconnectionstring,dbuser,dbpassword);

             return dbConnection;

         }catch (SQLException e) {
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
         	LogWriter.write("[E] " + e.getMessage());
         	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
         }

         return dbConnection;
 
    }
    
    public static String getEnvironment(){
        
    	if(dbuser.equals("CRPDTA")){ 
            environment = "Test";
        }else if (dbuser.equals("PRODDTA")){ 
            environment = "Produzione";  
        }else if (dbuser.equals("admin")){ 
            environment = "Dipartimentale";  
        }else if (dbuser.equals("root")){ 
            environment = "Dipartimentale";  
        }
        
        return environment;
    
    }
    
    public static String getMesUser(WorkCenter workcenter) {
        
        if(workcenter.getWorkCenterMode(workcenter).equals("1")){
            
            mesuser = "E20MAN"; 
        
        }else{
            
            mesuser = "E20MACHINE";
            
        }
        
        return mesuser;
        
    }
    
    public static String getMesProgram() {
        
        mesprogram = "ENET";
        
        return mesprogram;
        
    }
    
    public static String getMesJob() {
        
        mesjob = "EWEBJ";
        
        return mesjob;
        
    }
    
    public static String getMesVersion(WorkCenter workcenter) {
        
        if(workcenter.getWorkCenterMode(workcenter).equals("1")){
        
            mesversion = "MESMAN";
            
        }else{
        
            mesversion = "MESMACHINE";
        }
        
        return mesversion;
        
    }
    
    public static String isEnabledOnMesVersion(WorkCenter workcenter,Operator operator) {

        if(workcenter.getWorkCenterMode(workcenter).equals("1")){
            
            if(operator.getOperator().length() == 5){

                isenabledonmesversion = "true";
            
            }else{
            
                isenabledonmesversion = "false";
                
            }
            
        }else if(workcenter.getWorkCenterMode(workcenter).equals("0")){
        
            if(operator.getOperator().length() == 7){

                isenabledonmesversion = "true";
            
            }else{
            
                isenabledonmesversion = "false";
                
            }
            
        }else{
        
            isenabledonmesversion = "false";
            
        }
        
        return isenabledonmesversion;
        
    }
    
    public static String getDate(){
        
        String datenow;
        
        Date date = new Date(); 
        
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                              
        datenow = dateformat.format(date);
        
        return datenow;
        
    }
    
    public static String getDateForFileLog(){
        
        String datenow;
        
        Date date = new Date(); 
        
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                              
        datenow = dateformat.format(date);
        
        return datenow;
        
    }
    
    public static String getDateForEventLog(){
        
        String datenow;
        
        Date date = new Date(); 
        
        DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
                              
        datenow = dateformat.format(date);
        
        return datenow;
        
    }
    
    public static String getTime(){
        
        String timenow;
        
        Date date = new Date(); 

        DateFormat timeformat = new SimpleDateFormat("HHmmss");

        timenow = timeformat.format(date);
        
        return timenow;
        
    }
    
    public static String getTimeForEventLog(){
        
        String timenow;
        
        Date date = new Date(); 

        DateFormat timeformat = new SimpleDateFormat("HH.mm.ss");

        timenow = timeformat.format(date);
        
        return timenow;
        
    }
    
    public static String getTimestamp(){
        
        String timestamp;
        
        Date date = new Date(); 
        
        DateFormat dateformatGMT = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss");
        
        dateformatGMT.setTimeZone(TimeZone.getTimeZone("GMT")); 
        
        timestamp = dateformatGMT.format(date);
                
        return timestamp;
        
    }
    
    public static String getEventDate(){
        
        String eventdate;
        
        Date date = new Date(); 
        
        DateFormat dateformatevent = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                
        eventdate = dateformatevent.format(date);
        
        return eventdate;
        
    }
    
    
}
