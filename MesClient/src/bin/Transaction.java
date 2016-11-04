package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Transaction {
	
    private ArrayList<String> workorders = new ArrayList<String>();
    private Phase phase;
    private String branchname;
    private String machinename; 
    private String machinedescription;
    private String machinedefaultdept;
    private String phasenumber;
    private String phasedescription;
    private String operatoraggregatedtoworkcenter;
    private String operatoraggregatedtoworkcenterdescription;
    private String workcentername;
    private String workordernumber;
    private String workorderjob; 
    private String workorderitem;
    private String workorderitemdescription;
    private String workcenterofphase;
    private String workorderbranch;
    private String operatorname;
    private String mesprogram;
    private String mesjob;
    private String mesuser;
    private ArrayList<String> idopentransaction;
    
    public Transaction(WorkCenter workcenter, WorkOrder workorder, Machine machine, Operator operator, Branch branch, Phase phase) {
    // CONTROLLATO
    	this.branchname = branch.getBranch();																						
        this.machinename = machine.getMachine();																					
        this.machinedescription = machine.getMachineDescription(machine);															
        this.machinedefaultdept = workcenter.getWorkCenterDefaultDept(workcenter);															
        this.phasenumber = phase.getPhase();																						
        this.phasedescription = phase.getPhaseDescription(workorder, phase);														
        this.operatoraggregatedtoworkcenter = workcenter.getOperatorAggregatedToWorkCenter(workcenter);								
        this.operatoraggregatedtoworkcenterdescription = workcenter.getWorkCenterOperatorAggregatedDescription(workcenter);			
        this.workcentername = workcenter.getWorkCenterName(workcenter.getWorkCenter());												
        this.workordernumber = workorder.getWorkOrder();																			
        this.workorderjob = workorder.getWorkOrderJob(workorder);																	
        this.workorderitem = workorder.getWorkOrderItem(workorder);																	
        this.workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);											
        this.workcenterofphase = workorder.getWorkCenterOfPhase(workorder,phase);																																		
        this.mesprogram = Settings.getMesProgram();																					
        this.mesjob = Settings.getMesJob();																							
        this.mesuser = Settings.getMesUser(workcenter);																															
        
    }
    
    public Transaction(WorkCenter workcenter, ArrayList<String> workorders, Machine machine, Operator operator, Phase phase) {
    // CONTROLLATO
    	this.workorders = workorders;
    	this.phase = phase; 
    	this.machinename = machine.getMachine();																					
    	this.machinedescription = machine.getMachineDescription(machine);														
    	this.machinedefaultdept = workcenter.getWorkCenterDefaultDept(workcenter);															
    	this.operatoraggregatedtoworkcenter = workcenter.getOperatorAggregatedToWorkCenter(workcenter);								
    	this.operatoraggregatedtoworkcenterdescription = workcenter.getWorkCenterOperatorAggregatedDescription(workcenter);			
    	this.workcentername = workcenter.getWorkCenterName(workcenter.getWorkCenter());	
    	this.phasenumber = phase.getPhase();
    	this.mesprogram = Settings.getMesProgram();																					
    	this.mesjob = Settings.getMesJob();																							
    	this.mesuser = Settings.getMesUser(workcenter);																							
       
    }
    
    public Transaction(WorkCenter workcenter, Operator operator) {
    // CONTROLLATO        
    	this.workcentername = workcenter.getWorkCenterName(workcenter.getWorkCenter());												
    	this.operatorname = operator.getOperator();																					
    	this.idopentransaction = workcenter.getIdOpenTransactions(workcenter);														
    
    }

    public void doStartSetUpMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
     	        + "                        ADAN8,"
     	        + "                        ADMCU,"
     	        + "                        ADYQ10DECD,"
     	        + "                        ADYQ10ACCD,"
     	        + "                        ADDOCO,"
     	        + "                        ADOPSQ,"
     	        + "                        ADSOQS,"
     	        + "                        ADSOCN,"
     	        + "                        ADREAC,"
     	        + "                        ADYQ10DERN,"
     	        + "                        ADDTUP,"
     	        + "                        ADYQ10TRCD,"
     	        + "                        ADYQ10REST,"
     	        + "                        ADYQ10PR01,"
     	        + "                        ADYQ10PR02,"
     	        + "                        ADURDT,"
     	        + "                        ADURAB,"
     	        + "                        ADURAT,"
     	        + "                        ADURCD,"
     	        + "                        ADURRF,"
     	        + "                        ADPID,"
     	        + "                        ADJOBN,"
     	        + "                        ADUSER,"
     	        + "                        ADUPMJ,"
     	        + "                        ADUPMT,"
     	        + "                        ADSOS,"    
     	        + "                        ADALPH,"
     	        + "                        ADALPH1,"
     	        + "                        ADDEPT,"
     	        + "                        ADPRJM,"
     	        + "                        ADLITM,"
     	        + "                        ADDSC1,"
     	        + "                        ADOPSQDSC1,"
     	        + "                        ADISL,"
     	        + "                        ADPROCESSEDUSER,"
     	        + "                        ADPROCESSEDDATE,"
     	        + "                        ADINSERTUSER,"
     	        + "                        ADINSERTDATE,"
     	        + "                        ADUPDATEUSER,"
     	        + "                        ADUPDATEDATE,"
     	        + "                        ADDELETEUSER,"
     	        + "                        ADDELETEDATE) "    
     	        + "                VALUES (LPAD(?,12,' '),"                                        							//01-ADMMCU
     	        + "                        ?,"                                                  							//02-ADAN8
     	        + "                        LPAD(?,12,' '),"                                       							//03-ADMCU
     	        + "                        ?,"                                                  							//04-ADYQ10DECD
     	        + "                        ?,"                                                  							//05-ADYQ10ACCD
     	        + "                        ?,"                                                  							//06-ADDOCO
     	        + "                        ?,"                                                  							//07-ADOPSQ
     	        + "                        ?,"                                                  							//08-ADSOQS
     	        + "                        ?,"                                                  							//09-ADSOCN
     	        + "                        ?,"                                                  							//10-ADREAC
     	        + "                        ?,"                                                  							//11-ADYQ10DERN
     	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
     	        + "                        ?,"                                                  							//13-ADYQ10TRCD
     	        + "                        ?,"                                                  							//14-ADYQ10REST
     	        + "                        ?,"                                                  							//15-ADYQ10PR01
     	        + "                        ?,"                                                  							//16-ADYQ10PR02
     	        + "                        ?,"                                                  							//17-ADURDT
     	        + "                        ?,"                                                  							//18-ADURAB
     	        + "                        ?,"                                                  							//19-ADURAT
     	        + "                        ?,"                                                  							//20-ADURCD
     	        + "                        ?,"                                                  							//21-ADURRF
     	        + "                        ?,"                                                  							//22-ADPID
     	        + "                        ?,"                                                  							//23-ADJOBN
     	        + "                        ?,"                                                  							//24-ADUSER
     	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
     	        + "                        ?,"                                                  							//26-ADUPMT
     	        + "                        ?,"                                                  							//27-ADSOS
     	        + "                        ?,"                                                  							//28-ADALPH
     	        + "                        ?,"                                                  							//29-ADALPH1
     	        + "                        ?,"                                                  							//30-ADDEPT
     	        + "                        ?,"                                                  							//31-ADPRJM
     	        + "                        ?,"                                                  							//32-ADLITM
     	        + "                        ?,"                                                  							//33-ADDSC1
     	        + "                        ?,"                                                  							//34-ADOPSQDSC1
     	        + "                        ?,"                                                  							//35-ADISL
     	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
     	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
     	        + "                        ?,"                                                  							//38-ADINSERTUSER
     	        + "                        ?,"                                                  							//39-ADINSERTDATE
     	        + "                        ?,"                                                  							//40-ADUPDATEUSER
     	        + "                        ?,"                                                  							//41-ADUPDATEDATE
     	        + "                        ?,"                                                  							//42-ADDELETEUSER
     	        + "                        ?)";                                                 							//43-ADDELETEDATE 
     	        
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE 
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
          
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                          						//ADAN8
	            ps.setString(3, workcenterofphase);     													//ADMCU
	            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
	            ps.setString(5, "A");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);   						    			//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);				                                     			//ADPID
	            ps.setString(23, mesjob);				                                         			//ADJOBN
	            ps.setString(24, mesuser);							                              			//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(27, Settings.getTime());                                           			//ADUPMT
	            ps.setString(28,"0");                                                          				//ADSOS
	            ps.setString(29, machinedescription); 						                      			//ADALPH
	            ps.setString(30, operatoraggregatedtoworkcenterdescription);						      	//ADALPH1
	            ps.setString(31, machinedefaultdept);						                       			//ADDEPT
	            ps.setString(32, workorderjob);                         									//ADPRJM
	            ps.setString(33, workorderitem);						                        			//ADLITM
	            ps.setString(34, workorderitemdescription);             									//ADDSC1
	            ps.setString(35, phasedescription);								                   			//ADOPSQDSC1
	            ps.setString(36, workcentername);                											//ADISL
	            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          			//ADINSERTUSER
	            ps.setString(40, null);                                                         			//ADINSERTDATE
	            ps.setString(41, " ");                                                          			//ADUPDATEUSER
	            ps.setString(42, null);                                                         			//ADUPDATEDATE
	            ps.setString(43, " ");                                                          			//ADDELETEUSER
	            ps.setString(44, null);                                                         			//ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);       			                                   			//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
	            ps.setString(5, "A");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                 			     			//ADDOCO
	            ps.setString(7, phasenumber);            		                                  			//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);          					                           			//ADPID
	            ps.setString(23, mesjob);           				                              			//ADJOBN
	            ps.setString(24, mesuser);                     							         			//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getTime());                                           			//ADUPMT
	            ps.setString(27,"0");                                                          				//ADSOS
	            ps.setString(28, machinedescription);                   					    			//ADALPH
	            ps.setString(29, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
	            ps.setString(30, machinedefaultdept);                       								//ADDEPT
	            ps.setString(31, workorderjob);                         									//ADPRJM
	            ps.setString(32, workorderitem);						                        			//ADLITM
	            ps.setString(33, workorderitemdescription);							             			//ADDSC1
	            ps.setString(34, phasedescription);                   										//ADOPSQDSC1
	            ps.setString(35, workcentername); 											               	//ADISL
	            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          			//ADINSERTUSER
	            ps.setString(39, null);                                                         			//ADINSERTDATE
	            ps.setString(40, " ");                                                          			//ADUPDATEUSER
	            ps.setString(41, null);                                                         			//ADUPDATEDATE
	            ps.setString(42, " ");                                                          			//ADDELETEUSER
	            ps.setString(43, null);                                                         			//ADDELETEDATE
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStartSetUpMachineTransactionMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[I]       " + "[ A - Inizio ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStartSetUpMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStartSetUpMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStartSetUpMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
                + "                        ADAN8,"
                + "                        ADMCU,"
                + "                        ADYQ10DECD,"
                + "                        ADYQ10ACCD,"
                + "                        ADDOCO,"
                + "                        ADOPSQ,"
                + "                        ADSOQS,"
                + "                        ADSOCN,"
                + "                        ADREAC,"
                + "                        ADYQ10DERN,"
                + "                        ADDTUP,"
                + "                        ADYQ10TRCD,"
                + "                        ADYQ10REST,"
                + "                        ADYQ10PR01,"
                + "                        ADYQ10PR02,"
                + "                        ADURDT,"
                + "                        ADURAB,"
                + "                        ADURAT,"
                + "                        ADURCD,"
                + "                        ADURRF,"
                + "                        ADPID,"
                + "                        ADJOBN,"
                + "                        ADUSER,"
                + "                        ADUPMJ,"
                + "                        ADUPMT,"
                + "                        ADSOS,"    
                + "                        ADALPH,"
                + "                        ADALPH1,"
                + "                        ADDEPT,"
                + "                        ADPRJM,"
                + "                        ADLITM,"
                + "                        ADDSC1,"
                + "                        ADOPSQDSC1,"
                + "                        ADISL,"
                + "                        ADPROCESSEDUSER,"
                + "                        ADPROCESSEDDATE,"
                + "                        ADINSERTUSER,"
                + "                        ADINSERTDATE,"
                + "                        ADUPDATEUSER,"
                + "                        ADUPDATEDATE,"
                + "                        ADDELETEUSER,"
                + "                        ADDELETEDATE) "    
                + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
                + "                        ?,"                                                  							//02-ADAN8
                + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
                + "                        ?,"                                                  							//04-ADYQ10DECD
                + "                        ?,"                                                  							//05-ADYQ10ACCD
                + "                        ?,"                                                  							//06-ADDOCO
                + "                        ?,"                                                  							//07-ADOPSQ
                + "                        ?,"                                                  							//08-ADSOQS
                + "                        ?,"                                                  							//09-ADSOCN
                + "                        ?,"                                                  							//10-ADREAC
                + "                        ?,"                                                  							//11-ADYQ10DERN
                + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
                + "                        ?,"                                                  							//13-ADYQ10TRCD
                + "                        ?,"                                                  							//14-ADYQ10REST
                + "                        ?,"                                                  							//15-ADYQ10PR01
                + "                        ?,"                                                  							//16-ADYQ10PR02
                + "                        ?,"                                                  							//17-ADURDT
                + "                        ?,"                                                  							//18-ADURAB
                + "                        ?,"                                                  							//19-ADURAT
                + "                        ?,"                                                  							//20-ADURCD
                + "                        ?,"                                                  							//21-ADURRF
                + "                        ?,"                                                  							//22-ADPID
                + "                        ?,"                                                  							//23-ADJOBN
                + "                        ?,"                                                  							//24-ADUSER
                + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
                + "                        ?,"                                                  							//26-ADUPMT
                + "                        ?,"                                                  							//27-ADSOS
                + "                        ?,"                                                  							//28-ADALPH
                + "                        ?,"                                                  							//29-ADALPH1
                + "                        ?,"                                                  							//30-ADDEPT
                + "                        ?,"                                                  							//31-ADPRJM
                + "                        ?,"                                                  							//32-ADLITM
                + "                        ?,"                                                  							//33-ADDSC1
                + "                        ?,"                                                  							//34-ADOPSQDSC1
                + "                        ?,"                                                  							//35-ADISL
                + "                        ?,"                                                  							//36-ADPROCESSEDUSER
                + "                        ?,"                                                  							//37-ADPROCESSEDDATE
                + "                        ?,"                                                  							//38-ADINSERTUSER
                + "                        ?,"                                                  							//39-ADINSERTDATE
                + "                        ?,"                                                  							//40-ADUPDATEUSER
                + "                        ?,"                                                  							//41-ADUPDATEDATE
                + "                        ?,"                                                  							//42-ADDELETEUSER
                + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                      					          	//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "ATT");                                                         //ADYQ10DECD
	            ps.setString(5, "A");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber); 			                                    //ADDOCO
	            ps.setString(7, phasenumber); 		                                            //ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename); 			                                        //ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);				                                    //ADPID
	            ps.setString(23, mesjob);            				                            //ADJOBN
	            ps.setString(24, mesuser);               						                //ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getDate());                                           //ADUPMJ
	            ps.setString(27, Settings.getTime());                                           //ADUPMT
	            ps.setString(28,"0");                                                           //ADSOS
	            ps.setString(29, machinedescription);     					                    //ADALPH
	            ps.setString(30, machinedescription);                      						//ADALPH1
	            ps.setString(31, machinedefaultdept );                       					//ADDEPT
	            ps.setString(32, workorderjob);                      						    //ADPRJM
	            ps.setString(33, workorderitem);						                        //ADLITM
	            ps.setString(34, workorderitemdescription);             						//ADDSC1
	            ps.setString(35, phasedescription);                  						    //ADOPSQDSC1
	            ps.setString(36, workcentername);     											//ADISL
	            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(38, null);                                                         //ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          //ADINSERTUSER
	            ps.setString(40, null);                                                         //ADINSERTDATE
	            ps.setString(41, " ");                                                          //ADUPDATEUSER
	            ps.setString(42, null);                                                         //ADUPDATEDATE
	            ps.setString(43, " ");                                                          //ADDELETEUSER
	            ps.setString(44, null);                                                         //ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                 	   				                //ADMMCU
	            ps.setString(2, machinename);             			                            //ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "ATT");                                                         //ADYQ10DECD
	            ps.setString(5, "A");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);        			                            //ADDOCO
	            ps.setString(7, phasenumber);                                             		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);       		                                    //ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);             							                //ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getTime());                                           //ADUPMT
	            ps.setString(27,"0");                                                           //ADSOS
	            ps.setString(28, machinedescription);                      						//ADALPH
	            ps.setString(29, machinedescription);                       				    //ADALPH1
	            ps.setString(30, machinedefaultdept);					                        //ADDEPT
	            ps.setString(31, workorderjob);  						                        //ADPRJM
	            ps.setString(32, workorderitem);						                        //ADLITM
	            ps.setString(33, workorderitemdescription); 						            //ADDSC1
	            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(35, workcentername); 											    //ADISL
	            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(37, null);                                                         //ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          //ADINSERTUSER
	            ps.setString(39, null);                                                         //ADINSERTDATE
	            ps.setString(40, " ");                                                          //ADUPDATEUSER
	            ps.setString(41, null);                                                         //ADUPDATEDATE
	            ps.setString(42, " ");                                                          //ADDELETEUSER
	            ps.setString(43, null);                                                         //ADDELETEDATE
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStartSetUpMachineTransactionMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[I]       " + "[ A - Inizio ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStartSetUpMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStartSetUpMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStopSetUpMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                       							//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                         			    		//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
	            ps.setString(5, "S");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);                 							//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);                                     							//ADPID
	            ps.setString(23, mesjob);                                         							//ADJOBN
	            ps.setString(24, mesuser);                              									//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(27, Settings.getTime());                                           			//ADUPMT
	            ps.setString(28, "0");                                                          			//ADSOS
	            ps.setString(29, machinedescription);                       								//ADALPH
	            ps.setString(30, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
	            ps.setString(31, machinedefaultdept);                       								//ADDEPT
	            ps.setString(32, workorderjob);                         									//ADPRJM
	            ps.setString(33, workorderitem);                        									//ADLITM
	            ps.setString(34, workorderitemdescription);             									//ADDSC1
	            ps.setString(35, phasedescription);                   										//ADOPSQDSC1
	            ps.setString(36, workcentername);                 											//ADISL
	            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          			//ADINSERTUSER
	            ps.setString(40, null);                                                         			//ADINSERTDATE
	            ps.setString(41, " ");                                                          			//ADUPDATEUSER
	            ps.setString(42, null);                                                         			//ADUPDATEDATE
	            ps.setString(43, " ");                                                          			//ADDELETEUSER
	            ps.setString(44, null);                                                         			//ADDELETEDATE
	            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                         			   	 		//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
	            ps.setString(5, "S");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);                 							//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);                                     							//ADPID
	            ps.setString(23, mesjob);                                         							//ADJOBN
	            ps.setString(24, mesuser);                              									//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getTime());                                           			//ADUPMT
	            ps.setString(27, "0");                                                          			//ADSOS
	            ps.setString(28, machinedescription);                       								//ADALPH
	            ps.setString(29, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
	            ps.setString(30, machinedefaultdept);                       								//ADDEPT
	            ps.setString(31, workorderjob);                         									//ADPRJM
	            ps.setString(32, workorderitem);                        									//ADLITM
	            ps.setString(33, workorderitemdescription);             									//ADDSC1
	            ps.setString(34, phasedescription);                   										//ADOPSQDSC1
	            ps.setString(35, workcentername);                 											//ADISL
	            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          			//ADINSERTUSER
	            ps.setString(39, null);                                                         			//ADINSERTDATE
	            ps.setString(40, " ");                                                          			//ADUPDATEUSER
	            ps.setString(41, null);                                                         			//ADUPDATEDATE
	            ps.setString(42, " ");                                                          			//ADDELETEUSER
	            ps.setString(43, null);                                                         			//ADDELETEDATE
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStopSetUpMachineTransactionMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[I]       " + "[ S - Fine ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStopSetUpMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStopSetUpMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                        							//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                					//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "ATT");                                                         //ADYQ10DECD
	            ps.setString(5, "S");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      			//ADDOCO
	            ps.setString(7, phasenumber);                                              		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);                                         			//ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);                              						//ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getDate());                                           //ADUPMJ
	            ps.setString(27, Settings.getTime());                                           //ADUPMT
	            ps.setString(28, "0");                                                          //ADSOS
	            ps.setString(29, machinedescription);                       					//ADALPH
	            ps.setString(30, machinedescription);                       					//ADALPH1
	            ps.setString(31, machinedefaultdept);                       					//ADDEPT
	            ps.setString(32, workorderjob);                         						//ADPRJM
	            ps.setString(33, workorderitem);                       							//ADLITM
	            ps.setString(34, workorderitemdescription);             						//ADDSC1
	            ps.setString(35, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(36, workcentername);     											//ADISL
	            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(38, null);                                                         //ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          //ADINSERTUSER
	            ps.setString(40, null);                                                         //ADINSERTDATE
	            ps.setString(41, " ");                                                          //ADUPDATEUSER
	            ps.setString(42, null);                                                         //ADUPDATEDATE
	            ps.setString(43, " ");                                                          //ADDELETEUSER
	            ps.setString(44, null);                                                         //ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                					//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "ATT");                                                         //ADYQ10DECD
	            ps.setString(5, "S");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      			//ADDOCO
	            ps.setString(7, phasenumber);                                              		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);                                         			//ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);                              						//ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getTime());                                           //ADUPMT
	            ps.setString(27, "0");                                                          //ADSOS
	            ps.setString(28, machinedescription);                       					//ADALPH
	            ps.setString(29, machinedescription);                      						//ADALPH1
	            ps.setString(30, machinedefaultdept);                       					//ADDEPT
	            ps.setString(31, workorderjob);                         						//ADPRJM
	            ps.setString(32, workorderitem);                        						//ADLITM
	            ps.setString(33, workorderitemdescription);             						//ADDSC1
	            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(35, workcentername);     											//ADISL
	            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(37, null);                                                         //ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          //ADINSERTUSER
	            ps.setString(39, null);                                                         //ADINSERTDATE
	            ps.setString(40, " ");                                                          //ADUPDATEUSER
	            ps.setString(41, null);                                                         //ADUPDATEDATE
	            ps.setString(42, " ");                                                          //ADDELETEUSER
	            ps.setString(43, null);                                                         //ADDELETEDATE
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStopSetUpMachineTransactionMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[I]       " + "[ S - Fine ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStopSetUpMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStartWorkMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                          						//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
	            ps.setString(5, "A");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);                                     							//ADPID
	            ps.setString(23, mesjob);                                         							//ADJOBN
	            ps.setString(24, mesuser);                              									//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(27, Settings.getTime());                                           			//ADUPMT
	            ps.setString(28, "0");                                                          			//ADSOS
	            ps.setString(29, machinedescription);                       								//ADALPH
	            ps.setString(30, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
	            ps.setString(31, machinedefaultdept);                       								//ADDEPT
	            ps.setString(32, workorderjob);                         									//ADPRJM
	            ps.setString(33, workorderitem);                        									//ADLITM
	            ps.setString(34, workorderitemdescription);             									//ADDSC1
	            ps.setString(35, phasedescription);                  	 									//ADOPSQDSC1
	            ps.setString(36, workcentername);                 											//ADISL
	            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          			//ADINSERTUSER
	            ps.setString(40, null);                                                         			//ADINSERTDATE
	            ps.setString(41, " ");                                                          			//ADUPDATEUSER
	            ps.setString(42, null);                                                         			//ADUPDATEDATE
	            ps.setString(43, " ");                                                          			//ADDELETEUSER
	            ps.setString(44, null);                                                         			//ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                          						//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
	            ps.setString(5, "A");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);                                     							//ADPID
	            ps.setString(23, mesjob);                                         							//ADJOBN
	            ps.setString(24, mesuser);                              									//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getTime());                                           			//ADUPMT
	            ps.setString(27, "0");                                                          			//ADSOS
	            ps.setString(28, machinedescription);                       								//ADALPH
	            ps.setString(29, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
	            ps.setString(30, machinedefaultdept);                       								//ADDEPT
	            ps.setString(31, workorderjob);                         									//ADPRJM
	            ps.setString(32, workorderitem);                        									//ADLITM
	            ps.setString(33, workorderitemdescription);             									//ADDSC1
	            ps.setString(34, phasedescription);                  	 									//ADOPSQDSC1
	            ps.setString(35, workcentername);                 											//ADISL
	            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          			//ADINSERTUSER
	            ps.setString(39, null);                                                         			//ADINSERTDATE
	            ps.setString(40, " ");                                                          			//ADUPDATEUSER
	            ps.setString(41, null);                                                         			//ADUPDATEDATE
	            ps.setString(42, " ");                                                          			//ADDELETEUSER
	            ps.setString(43, null);                                                         			//ADDELETEDATE
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStartWorkMachineTransactionMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[I]       " + "[ A - Inizio ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStartWorkMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStartWorkMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStartWorkMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
     	        + "                        ADAN8,"
     	        + "                        ADMCU,"
     	        + "                        ADYQ10DECD,"
     	        + "                        ADYQ10ACCD,"
     	        + "                        ADDOCO,"
     	        + "                        ADOPSQ,"
                + "                        ADSOQS,"
                + "                        ADSOCN,"
                + "                        ADREAC,"
                + "                        ADYQ10DERN,"
                + "                        ADDTUP,"
                + "                        ADYQ10TRCD,"
                + "                        ADYQ10REST,"
                + "                        ADYQ10PR01,"
                + "                        ADYQ10PR02,"
                + "                        ADURDT,"
                + "                        ADURAB,"
                + "                        ADURAT,"
                + "                        ADURCD,"
                + "                        ADURRF,"
                + "                        ADPID,"
                + "                        ADJOBN,"
                + "                        ADUSER,"
                + "                        ADUPMJ,"
                + "                        ADUPMT,"
                + "                        ADSOS,"    
                + "                        ADALPH,"
                + "                        ADALPH1,"
                + "                        ADDEPT,"
                + "                        ADPRJM,"
                + "                        ADLITM,"
                + "                        ADDSC1,"
                + "                        ADOPSQDSC1,"
                + "                        ADISL,"
                + "                        ADPROCESSEDUSER,"
                + "                        ADPROCESSEDDATE,"
                + "                        ADINSERTUSER,"
                + "                        ADINSERTDATE,"
                + "                        ADUPDATEUSER,"
                + "                        ADUPDATEDATE,"
                + "                        ADDELETEUSER,"
                + "                        ADDELETEDATE) "    
                + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
                + "                        ?,"                                                  							//02-ADAN8
                + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
                + "                        ?,"                                                  							//04-ADYQ10DECD
                + "                        ?,"                                                  							//05-ADYQ10ACCD
                + "                        ?,"                                                  							//06-ADDOCO
                + "                        ?,"                                                  							//07-ADOPSQ
                + "                        ?,"                                                  							//08-ADSOQS
                + "                        ?,"                                                  							//09-ADSOCN
                + "                        ?,"                                                  							//10-ADREAC
                + "                        ?,"                                                  							//11-ADYQ10DERN
                + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
                + "                        ?,"                                                  							//13-ADYQ10TRCD
                + "                        ?,"                                                  							//14-ADYQ10REST
                + "                        ?,"                                                  							//15-ADYQ10PR01
                + "                        ?,"                                                  							//16-ADYQ10PR02
                + "                        ?,"                                                  							//17-ADURDT
                + "                        ?,"                                                  							//18-ADURAB
                + "                        ?,"                                                  							//19-ADURAT
                + "                        ?,"                                                  							//20-ADURCD
                + "                        ?,"                                                  							//21-ADURRF
                + "                        ?,"                                                  							//22-ADPID
                + "                        ?,"                                                  							//23-ADJOBN
                + "                        ?,"                                                  							//24-ADUSER
                + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
                + "                        ?,"                                                  							//26-ADUPMT
                + "                        ?,"                                                  							//27-ADSOS
                + "                        ?,"                                                  							//28-ADALPH
                + "                        ?,"                                                  							//29-ADALPH1
                + "                        ?,"                                                  							//30-ADDEPT
                + "                        ?,"                                                  							//31-ADPRJM
                + "                        ?,"                                                  							//32-ADLITM
                + "                        ?,"                                                  							//33-ADDSC1
                + "                        ?,"                                                  							//34-ADOPSQDSC1
                + "                        ?,"                                                  							//35-ADISL
                + "                        ?,"                                                  							//36-ADPROCESSEDUSER
                + "                        ?,"                                                  							//37-ADPROCESSEDDATE
                + "                        ?,"                                                  							//38-ADINSERTUSER
                + "                        ?,"                                                  							//39-ADINSERTDATE
                + "                        ?,"                                                  							//40-ADUPDATEUSER
                + "                        ?,"                                                  							//41-ADUPDATEDATE
                + "                        ?,"                                                  							//42-ADDELETEUSER
                + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                					//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "LAV");                                                         //ADYQ10DECD
	            ps.setString(5, "A");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      			//ADDOCO
	            ps.setString(7, phasenumber);                                              		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);                                         			//ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);                              						//ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getDate());                                           //ADUPMJ
	            ps.setString(27, Settings.getTime());                                           //ADUPMT
	            ps.setString(28, "0");                                                          //ADSOS
	            ps.setString(29, machinedescription);                       					//ADALPH
	            ps.setString(30, machinedescription);                      	 					//ADALPH1
	            ps.setString(31, machinedefaultdept);                       					//ADDEPT
	            ps.setString(32, workorderjob);                         						//ADPRJM
	            ps.setString(33, workorderitem);                        						//ADLITM
	            ps.setString(34, workorderitemdescription);             						//ADDSC1
	            ps.setString(35, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(36, workcentername);     											//ADISL
	            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(38, null);                                                         //ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          //ADINSERTUSER
	            ps.setString(40, null);                                                         //ADINSERTDATE
	            ps.setString(41, " ");                                                          //ADUPDATEUSER
	            ps.setString(42, null);                                                         //ADUPDATEDATE
	            ps.setString(43, " ");                                                          //ADDELETEUSER
	            ps.setString(44, null);                                                         //ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                					//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "LAV");                                                         //ADYQ10DECD
	            ps.setString(5, "A");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      			//ADDOCO
	            ps.setString(7, phasenumber);                                              		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);                                         			//ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);                             							//ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getTime());                                           //ADUPMT
	            ps.setString(27, "0");                                                          //ADSOS
	            ps.setString(28, machinedescription);                       					//ADALPH
	            ps.setString(29, machinedescription);                       					//ADALPH1
	            ps.setString(30, machinedefaultdept);                       					//ADDEPT
	            ps.setString(31, workorderjob);                         						//ADPRJM
	            ps.setString(32, workorderitem);                        						//ADLITM
	            ps.setString(33, workorderitemdescription);             						//ADDSC1
	            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(35, workcentername);     											//ADISL
	            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(37, null);                                                         //ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          //ADINSERTUSER
	            ps.setString(39, null);                                                         //ADINSERTDATE
	            ps.setString(40, " ");                                                          //ADUPDATEUSER
	            ps.setString(41, null);                                                         //ADUPDATEDATE
	            ps.setString(42, " ");                                                          //ADDELETEUSER
	            ps.setString(43, null);                                                         //ADDELETEDATE
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStartWorkMachineTransactionMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[I]       " + "[ A - Inizio ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStartWorkMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStartWorkMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStartWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStopWorkMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
                + "                        ADAN8,"
                + "                        ADMCU,"
                + "                        ADYQ10DECD,"
                + "                        ADYQ10ACCD,"
                + "                        ADDOCO,"
                + "                        ADOPSQ,"
                + "                        ADSOQS,"
                + "                        ADSOCN,"
                + "                        ADREAC,"
                + "                        ADYQ10DERN,"
                + "                        ADDTUP,"
                + "                        ADYQ10TRCD,"
                + "                        ADYQ10REST,"
                + "                        ADYQ10PR01,"
                + "                        ADYQ10PR02,"
                + "                        ADURDT,"
                + "                        ADURAB,"
                + "                        ADURAT,"
                + "                        ADURCD,"
                + "                        ADURRF,"
                + "                        ADPID,"
                + "                        ADJOBN,"
                + "                        ADUSER,"
                + "                        ADUPMJ,"
                + "                        ADUPMT,"
                + "                        ADSOS,"    
                + "                        ADALPH,"
                + "                        ADALPH1,"
                + "                        ADDEPT,"
                + "                        ADPRJM,"
                + "                        ADLITM,"
                + "                        ADDSC1,"
                + "                        ADOPSQDSC1,"
                + "                        ADISL,"
                + "                        ADPROCESSEDUSER,"
                + "                        ADPROCESSEDDATE,"
                + "                        ADINSERTUSER,"
                + "                        ADINSERTDATE,"
                + "                        ADUPDATEUSER,"
                + "                        ADUPDATEDATE,"
                + "                        ADDELETEUSER,"
                + "                        ADDELETEDATE) "   
                + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
                + "                        ?,"                                                  							//02-ADAN8
                + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
                + "                        ?,"                                                  							//04-ADYQ10DECD
                + "                        ?,"                                                  							//05-ADYQ10ACCD
                + "                        ?,"                                                  							//06-ADDOCO
                + "                        ?,"                                                  							//07-ADOPSQ
                + "                        ?,"                                                  							//08-ADSOQS
                + "                        ?,"                                                  							//09-ADSOCN
                + "                        ?,"                                                  							//10-ADREAC
                + "                        ?,"                                                  							//11-ADYQ10DERN
                + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
                + "                        ?,"                                                  							//13-ADYQ10TRCD
                + "                        ?,"                                                  							//14-ADYQ10REST
                + "                        ?,"                                                  							//15-ADYQ10PR01
                + "                        ?,"                                                  							//16-ADYQ10PR02
                + "                        ?,"                                                  							//17-ADURDT
                + "                        ?,"                                                  							//18-ADURAB
                + "                        ?,"                                                  							//19-ADURAT
                + "                        ?,"                                                  							//20-ADURCD
                + "                        ?,"                                                  							//21-ADURRF
                + "                        ?,"                                                  							//22-ADPID
                + "                        ?,"                                                  							//23-ADJOBN
                + "                        ?,"                                                  							//24-ADUSER
                + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
                + "                        ?,"                                                  							//26-ADUPMT
                + "                        ?,"                                                  							//27-ADSOS
                + "                        ?,"                                                  							//28-ADALPH
                + "                        ?,"                                                  							//29-ADALPH1   
                + "                        ?,"                                                  							//30-ADDEPT
                + "                        ?,"                                                  							//31-ADPRJM                                        
                + "                        ?,"                                                  							//32-ADLITM
                + "                        ?,"                                                  							//33-ADDSC1
                + "                        ?,"                                                  							//34-ADOPSQDSC1
                + "                        ?,"                                                  							//35-ADISL
                + "                        ?,"                                                  							//36-ADPROCESSEDUSER
                + "                        ?,"                                                  							//37-ADPROCESSEDDATE
                + "                        ?,"                                                  							//38-ADINSERTUSER
                + "                        ?,"                                                  							//39-ADINSERTDATE
                + "                        ?,"                                                  							//40-ADUPDATEUSER
                + "                        ?,"                                                  							//41-ADUPDATEDATE
                + "                        ?,"                                                  							//42-ADDELETEUSER
                + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "   
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1   
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM                                        
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                          						//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
	            ps.setString(5, "S");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);                   						//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);                                     							//ADPID
	            ps.setString(23, mesjob);                                         							//ADJOBN
	            ps.setString(24, mesuser);                              									//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(27, Settings.getTime());                                           			//ADUPMT
	            ps.setString(28, "0");                                                          			//ADSOS
	            ps.setString(29, machinedescription);                       								//ADALPH
	            ps.setString(30, operatoraggregatedtoworkcenterdescription);								//ADALPH1
	            ps.setString(31, machinedefaultdept);                       								//ADDEPT
	            ps.setString(32, workorderjob);                         									//ADPRJM
	            ps.setString(33, workorderitem);                        									//ADLITM
	            ps.setString(34, workorderitemdescription);             									//ADDSC1
	            ps.setString(35, phasedescription);                   										//ADOPSQDSC1
	            ps.setString(36, workcentername);                 											//ADISL
	            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          			//ADINSERTUSER
	            ps.setString(40, null);                                                         			//ADINSERTDATE
	            ps.setString(41, " ");                                                          			//ADUPDATEUSER
	            ps.setString(42, null);                                                         			//ADUPDATEDATE
	            ps.setString(43, " ");                                                          			//ADDELETEUSER
	            ps.setString(44, null);                                                         			//ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                								//ADMMCU
	            ps.setString(2, machinename);                                          						//ADAN8
	            ps.setString(3, workcenterofphase);    														//ADMCU
	            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
	            ps.setString(5, "S");                                                           			//ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      						//ADDOCO
	            ps.setString(7, phasenumber);                                              					//ADOPSQ
	            ps.setString(8, "0");                                                           			//ADSOQS
	            ps.setString(9, "0");                                                           			//ADSOCN
	            ps.setString(10, " ");                                                          			//ADREAC
	            ps.setString(11, " ");                                                          			//ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
	            ps.setString(13, "O");                                                          			//ADYQ10TRCD
	            ps.setString(14, " ");                                                          			//ADYQ10REST
	            ps.setString(15, " ");                                                          			//ADYQ10PR01
	            ps.setString(16, " ");                                                          			//ADYQ10PR02
	            ps.setString(17, "0");                                                          			//ADURDT
	            ps.setString(18, operatoraggregatedtoworkcenter);                   						//ADURAB
	            ps.setString(19, "0");                                                          			//ADURAT
	            ps.setString(20, " ");                                                          			//ADURCD
	            ps.setString(21, " ");                                                          			//ADURRF
	            ps.setString(22, mesprogram);                                     							//ADPID
	            ps.setString(23, mesjob);                                         							//ADJOBN
	            ps.setString(24, mesuser);                              									//ADUSER
	            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
	            ps.setString(26, Settings.getTime());                                           			//ADUPMT
	            ps.setString(27, "0");                                                          			//ADSOS
	            ps.setString(28, machinedescription);                       								//ADALPH
	            ps.setString(29, operatoraggregatedtoworkcenterdescription);								//ADALPH1
	            ps.setString(30, machinedefaultdept);                       								//ADDEPT
	            ps.setString(31, workorderjob);                         									//ADPRJM
	            ps.setString(32, workorderitem);                        									//ADLITM
	            ps.setString(33, workorderitemdescription);             									//ADDSC1
	            ps.setString(34, phasedescription);                   										//ADOPSQDSC1
	            ps.setString(35, workcentername);                 											//ADISL
	            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
	            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          			//ADINSERTUSER
	            ps.setString(39, null);                                                         			//ADINSERTDATE
	            ps.setString(40, " ");                                                          			//ADUPDATEUSER
	            ps.setString(41, null);                                                         			//ADUPDATEDATE
	            ps.setString(42, " ");                                                          			//ADDELETEUSER
	            ps.setString(43, null);                                                         			//ADDELETEDATE
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStopWorkMachineTransactionMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[I]       " + "[ S - Fine ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStopWorkMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStopWorkMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "   
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1   
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM 
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                 								//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "   
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1   
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM 
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                					//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "LAV");                                                         //ADYQ10DECD
	            ps.setString(5, "S");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);                                      			//ADDOCO
	            ps.setString(7, phasenumber);                                              		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);                                         			//ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);                              						//ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getDate());                                           //ADUPMJ
	            ps.setString(27, Settings.getTime());                                           //ADUPMT
	            ps.setString(28, "0");                                                          //ADSOS
	            ps.setString(29, machinedescription);                       					//ADALPH
	            ps.setString(30, machinedescription);                       					//ADALPH1
	            ps.setString(31, machinedefaultdept);                       					//ADDEPT
	            ps.setString(32, workorderjob);                         						//ADPRJM
	            ps.setString(33, workorderitem);                       	 						//ADLITM
	            ps.setString(34, workorderitemdescription);             						//ADDSC1
	            ps.setString(35, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(36, workcentername);     											//ADISL
	            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(38, null);                                                         //ADPROCESSEDDATE
	            ps.setString(39, " ");                                                          //ADINSERTUSER
	            ps.setString(40, null);                                                         //ADINSERTDATE
	            ps.setString(41, " ");                                                          //ADUPDATEUSER
	            ps.setString(42, null);                                                         //ADUPDATEDATE
	            ps.setString(43, " ");                                                          //ADDELETEUSER
	            ps.setString(44, null);                                                         //ADDELETEDATE
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                					//ADMMCU
	            ps.setString(2, machinename);                                          			//ADAN8
	            ps.setString(3, workcenterofphase);    											//ADMCU
	            ps.setString(4, "LAV");                                                         //ADYQ10DECD
	            ps.setString(5, "S");                                                           //ADYQ10ACCD
	            ps.setString(6, workordernumber);                                     			//ADDOCO
	            ps.setString(7, phasenumber);                                              		//ADOPSQ
	            ps.setString(8, "0");                                                           //ADSOQS
	            ps.setString(9, "0");                                                           //ADSOCN
	            ps.setString(10, " ");                                                          //ADREAC
	            ps.setString(11, " ");                                                          //ADYQ10DERN
	            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
	            ps.setString(13, "O");                                                          //ADYQ10TRCD
	            ps.setString(14, " ");                                                          //ADYQ10REST
	            ps.setString(15, " ");                                                          //ADYQ10PR01
	            ps.setString(16, " ");                                                          //ADYQ10PR02
	            ps.setString(17, "0");                                                          //ADURDT
	            ps.setString(18, machinename);                                         			//ADURAB
	            ps.setString(19, "0");                                                          //ADURAT
	            ps.setString(20, " ");                                                          //ADURCD
	            ps.setString(21, " ");                                                          //ADURRF
	            ps.setString(22, mesprogram);                                     				//ADPID
	            ps.setString(23, mesjob);                                         				//ADJOBN
	            ps.setString(24, mesuser);                              						//ADUSER
	            ps.setString(25, Settings.getDate());                                           //ADUPMJ
	            ps.setString(26, Settings.getTime());                                           //ADUPMT
	            ps.setString(27, "0");                                                          //ADSOS
	            ps.setString(28, machinedescription);                       					//ADALPH
	            ps.setString(29, machinedescription);                       					//ADALPH1
	            ps.setString(30, machinedefaultdept);                       					//ADDEPT
	            ps.setString(31, workorderjob);                         						//ADPRJM
	            ps.setString(32, workorderitem);                        						//ADLITM
	            ps.setString(33, workorderitemdescription);             						//ADDSC1
	            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
	            ps.setString(35, workcentername);    											//ADISL
	            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(37, null);                                                         //ADPROCESSEDDATE
	            ps.setString(38, " ");                                                          //ADINSERTUSER
	            ps.setString(39, null);                                                         //ADINSERTDATE
	            ps.setString(40, " ");                                                          //ADUPDATEUSER
	            ps.setString(41, null);                                                         //ADUPDATEDATE
	            ps.setString(42, " ");                                                          //ADDELETEUSER
	            ps.setString(43, null);                                                         //ADDELETEDATE
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doStopWorkMachineTransactionMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[I]       " + "[ S - Fine ]");
        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doStopWorkMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
//    public void doStopWorkMachineBeforeChangeWorkOrderTransactionMonoOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql = "";
//
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql = "INSERT INTO F55FQ10001 SELECT ADMMCU,"
//                + "                              ADAN8,"
//                + "                              ADMCU,"
//                + "                              ADYQ10DECD,"
//                + "                              ?,"                                            							//01-ADYQ10ACCD
//                + "                              ADDOCO,"
//                + "                              ADOPSQ,"
//                + "                              ADSOQS,"
//                + "                              ADSOCN,"
//                + "                              ADREAC,"
//                + "                              ADYQ10DERN,"
//                + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//02-ADDTUP
//                + "                              ADYQ10TRCD,"
//                + "                              ADYQ10REST,"
//                + "                              ADYQ10PR01,"
//                + "                              ADYQ10PR02,"
//                + "                              ADURDT,"
//                + "                              ADURAB,"
//                + "                              ADURAT,"
//                + "                              ADURCD,"
//                + "                              ADURRF,"
//                + "                              ADPID,"
//                + "                              ADJOBN,"
//                + "                              ADUSER,"
//                + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //03-ADUPMJ
//                + "                              ?,"                                            							//04-ADUPMT
//                + "                              ?,"                                            							//05-ADSOS
//                + "                              ADALPH,"
//                + "                              ADALPH1,"
//                + "                              ADDEPT,"
//                + "                              ADPRJM,"
//                + "                              ADLITM,"
//                + "                              ADDSC1,"
//                + "                              ADOPSQDSC1,"
//                + "                              ADISL,"
//                + "                              ADPROCESSEDUSER,"
//                + "                              ADPROCESSEDDATE,"
//                + "                              ADINSERTUSER,"
//                + "                              ADINSERTDATE,"
//                + "                              ADUPDATEUSER,"
//                + "                              ADUPDATEDATE,"
//                + "                              ADDELETEUSER,"
//                + "                              ADDELETEDATE "    
//                + "                         FROM F55FQ10001 "
//                + "                        WHERE ADAN8 = ? "
//                + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//                + "                                          FROM F55FQ10001 B"
//                + "                                         WHERE A.ADAN8 = B.ADAN8 "
//                + "                                           AND TRIM(ADYQ10DECD) = 'LAV')";
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        	
//	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
//	            + "                              ADMMCU,"
//	            + "                              ADAN8,"
//	            + "                              ADMCU,"
//	            + "                              ADYQ10DECD,"
//	            + "                              ?,"                                            //01-ADYQ10ACCD
//	            + "                              ADDOCO,"
//	            + "                              ADOPSQ,"
//	            + "                              ADSOQS,"
//	            + "                              ADSOCN,"
//	            + "                              ADREAC,"
//	            + "                              ADYQ10DERN,"
//	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //02-ADDTUP
//	            + "                              ADYQ10TRCD,"
//	            + "                              ADYQ10REST,"
//	            + "                              ADYQ10PR01,"
//	            + "                              ADYQ10PR02,"
//	            + "                              ADURDT,"
//	            + "                              ADURAB,"
//	            + "                              ADURAT,"
//	            + "                              ADURCD,"
//	            + "                              ADURRF,"
//	            + "                              ADPID,"
//	            + "                              ADJOBN,"
//	            + "                              ADUSER,"
//	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //03-ADUPMJ
//	            + "                              ?,"                                            //04-ADUPMT
//	            + "                              ?,"                                            //05-ADSOS
//	            + "                              ADALPH,"
//	            + "                              ADALPH1,"
//	            + "                              ADDEPT,"
//	            + "                              ADPRJM,"
//	            + "                              ADLITM,"
//	            + "                              ADDSC1,"
//	            + "                              ADOPSQDSC1,"
//	            + "                              ADISL,"
//	            + "                              ADPROCESSEDUSER,"
//	            + "                              ADPROCESSEDDATE,"
//	            + "                              ADINSERTUSER,"
//	            + "                              ADINSERTDATE,"
//	            + "                              ADUPDATEUSER,"
//	            + "                              ADUPDATEDATE,"
//	            + "                              ADDELETEUSER,"
//	            + "                              ADDELETEDATE "    
//	            + "                         FROM F55FQ10001 "
//	            + "                        WHERE ADAN8 = ? "
//	            + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//	            + "                                          FROM F55FQ10001 B"
//	            + "                                         WHERE A.ADAN8 = B.ADAN8 "
//	            + "                                           AND TRIM(ADYQ10DECD) = 'LAV')";
//        
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql);
//
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//            	
//	            ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(5, Settings.getTime());                                            //ADUPMT
//	            ps.setString(6, "0");                                                           //ADSOS
//	            ps.setString(7, machinename);
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            	
//            	ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getTime());                                            //ADUPMT
//	            ps.setString(5, "0");                                                           //ADSOS
//	            ps.setString(6, machinename);
//	            
//            }
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMonoOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
//            LogWriter.write("[I] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMonoOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql);
//        	LogWriter.write("[E] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
//        	notification.setModal(true);
//        	notification.setVisible(true);
//
//        } finally {
// 
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura PreparedStatement");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
    
//    public void doStopWorkMachineBeforeChangeWorkOrderTransactionMultiOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql = "";
//
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql = "INSERT INTO F55FQ10001 SELECT ADMMCU,"
//    	        + "                              ADAN8,"
//    	        + "                              ADMCU,"
//    	        + "                              ADYQ10DECD,"
//    	        + "                              ?,"                                            							//01-ADYQ10ACCD
//    	        + "                              ADDOCO,"
//    	        + "                              ADOPSQ,"
//    	        + "                              ADSOQS,"
//    	        + "                              ADSOCN,"
//    	        + "                              ADREAC,"
//    	        + "                              ADYQ10DERN,"
//    	        + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//02-ADDTUP
//    	        + "                              ADYQ10TRCD,"
//    	        + "                              ADYQ10REST,"
//    	        + "                              ADYQ10PR01,"
//    	        + "                              ADYQ10PR02,"
//    	        + "                              ADURDT,"
//    	        + "                              ADURAB,"
//    	        + "                              ADURAT,"
//    	        + "                              ADURCD,"
//    	        + "                              ADURRF,"
//    	        + "                              ADPID,"
//    	        + "                              ADJOBN,"
//    	        + "                              ADUSER,"
//    	        + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //03-ADUPMJ
//    	        + "                              ?,"                                            							//04-ADUPMT
//    	        + "                              ?,"                                            							//05-ADSOS
//    	        + "                              ADALPH,"
//    	        + "                              ADALPH1,"
//    	        + "                              ADDEPT,"
//    	        + "                              ADPRJM,"
//    	        + "                              ADLITM,"
//    	        + "                              ADDSC1,"
//    	        + "                              ADOPSQDSC1,"
//    	        + "                              ADISL,"
//    	        + "                              ADPROCESSEDUSER,"
//    	        + "                              ADPROCESSEDDATE,"
//    	        + "                              ADINSERTUSER,"
//    	        + "                              ADINSERTDATE,"
//    	        + "                              ADUPDATEUSER,"
//    	        + "                              ADUPDATEDATE,"
//    	        + "                              ADDELETEUSER,"
//    	        + "                              ADDELETEDATE "    
//    	        + "                         FROM F55FQ10001 "
//    	        + "                        WHERE ADAN8 = ? "
//    	        + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//    	        + "                                          FROM F55FQ10001 B"
//    	        + "                                         WHERE A.ADAN8 = B.ADAN8 "
//    	        + "                                           AND TRIM(ADYQ10DECD) = 'LAV')";
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        	
//	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
//	            + "                              ADMMCU,"
//	            + "                              ADAN8,"
//	            + "                              ADMCU,"
//	            + "                              ADYQ10DECD,"
//	            + "                              ?,"                                            //01-ADYQ10ACCD
//	            + "                              ADDOCO,"
//	            + "                              ADOPSQ,"
//	            + "                              ADSOQS,"
//	            + "                              ADSOCN,"
//	            + "                              ADREAC,"
//	            + "                              ADYQ10DERN,"
//	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //02-ADDTUP
//	            + "                              ADYQ10TRCD,"
//	            + "                              ADYQ10REST,"
//	            + "                              ADYQ10PR01,"
//	            + "                              ADYQ10PR02,"
//	            + "                              ADURDT,"
//	            + "                              ADURAB,"
//	            + "                              ADURAT,"
//	            + "                              ADURCD,"
//	            + "                              ADURRF,"
//	            + "                              ADPID,"
//	            + "                              ADJOBN,"
//	            + "                              ADUSER,"
//	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //03-ADUPMJ
//	            + "                              ?,"                                            //04-ADUPMT
//	            + "                              ?,"                                            //05-ADSOS
//	            + "                              ADALPH,"
//	            + "                              ADALPH1,"
//	            + "                              ADDEPT,"
//	            + "                              ADPRJM,"
//	            + "                              ADLITM,"
//	            + "                              ADDSC1,"
//	            + "                              ADOPSQDSC1,"
//	            + "                              ADISL,"
//	            + "                              ADPROCESSEDUSER,"
//	            + "                              ADPROCESSEDDATE,"
//	            + "                              ADINSERTUSER,"
//	            + "                              ADINSERTDATE,"
//	            + "                              ADUPDATEUSER,"
//	            + "                              ADUPDATEDATE,"
//	            + "                              ADDELETEUSER,"
//	            + "                              ADDELETEDATE "    
//	            + "                         FROM F55FQ10001 "
//	            + "                        WHERE ADAN8 = ? "
//	            + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//	            + "                                          FROM F55FQ10001 B"
//	            + "                                         WHERE A.ADAN8 = B.ADAN8 "
//	            + "                                           AND TRIM(ADYQ10DECD) = 'LAV')";
//        
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql);
//
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//            	
//	            ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(5, Settings.getTime());                                            //ADUPMT
//	            ps.setString(6, "0");                                                           //ADSOS
//	            ps.setString(7, machinename);
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            	
//            	ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getTime());                                            //ADUPMT
//	            ps.setString(5, "0");                                                           //ADSOS
//	            ps.setString(6, machinename);
//	            
//            }
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMultiOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
//            LogWriter.write("[I] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMultiOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql);
//        	LogWriter.write("[E] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
//        	notification.setModal(true);
//        	notification.setVisible(true);
//
//        } finally {
// 
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura PreparedStatement");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopWorkMachineBeforeChangeWorkOrderTransactionMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
    
//    public void doStopSetUpMachineBeforeChangeWorkOrderTransactionMonoOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql = "";
//
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql = "INSERT INTO F55FQ10001 SELECT ADMMCU,"
//    	        + "                              ADAN8,"
//    	        + "                              ADMCU,"
//    	        + "                              ADYQ10DECD,"
//    	        + "                              ?,"                                            							//01-ADYQ10ACCD
//    	        + "                              ADDOCO,"
//    	        + "                              ADOPSQ,"
//    	        + "                              ADSOQS,"
//    	        + "                              ADSOCN,"
//    	        + "                              ADREAC,"
//    	        + "                              ADYQ10DERN,"
//    	        + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//02-ADDTUP
//    	        + "                              ADYQ10TRCD,"
//    	        + "                              ADYQ10REST,"
//    	        + "                              ADYQ10PR01,"
//    	        + "                              ADYQ10PR02,"
//    	        + "                              ADURDT,"
//    	        + "                              ADURAB,"
//    	        + "                              ADURAT,"
//    	        + "                              ADURCD,"
//    	        + "                              ADURRF,"
//    	        + "                              ADPID,"
//    	        + "                              ADJOBN,"
//    	        + "                              ADUSER,"
//    	        + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //03-ADUPMJ
//    	        + "                              ?,"                                            							//04-ADUPMT
//    	        + "                              ?,"                                            							//05-ADSOS
//    	        + "                              ADALPH,"
//    	        + "                              ADALPH1,"
//    	        + "                              ADDEPT,"
//    	        + "                              ADPRJM,"
//    	        + "                              ADLITM,"
//    	        + "                              ADDSC1,"
//    	        + "                              ADOPSQDSC1,"
//    	        + "                              ADISL,"
//    	        + "                              ADPROCESSEDUSER,"
//    	        + "                              ADPROCESSEDDATE,"
//    	        + "                              ADINSERTUSER,"
//    	        + "                              ADINSERTDATE,"
//    	        + "                              ADUPDATEUSER,"
//    	        + "                              ADUPDATEDATE,"
//    	        + "                              ADDELETEUSER,"
//    	        + "                              ADDELETEDATE "    
//    	        + "                         FROM F55FQ10001 A "
//    	        + "                        WHERE ADAN8 = ? "
//    	        + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//    	        + "                                          FROM F55FQ10001 B"
//    	        + "                                         WHERE A.ADAN8 = B.ADAN8 "
//    	        + "                                           AND TRIM(ADYQ10DECD) = 'ATT')";
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        	
//	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
//	            + "                              ADMMCU,"
//	            + "                              ADAN8,"
//	            + "                              ADMCU,"
//	            + "                              ADYQ10DECD,"
//	            + "                              ?,"                                            //01-ADYQ10ACCD
//	            + "                              ADDOCO,"
//	            + "                              ADOPSQ,"
//	            + "                              ADSOQS,"
//	            + "                              ADSOCN,"
//	            + "                              ADREAC,"
//	            + "                              ADYQ10DERN,"
//	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //02-ADDTUP
//	            + "                              ADYQ10TRCD,"
//	            + "                              ADYQ10REST,"
//	            + "                              ADYQ10PR01,"
//	            + "                              ADYQ10PR02,"
//	            + "                              ADURDT,"
//	            + "                              ADURAB,"
//	            + "                              ADURAT,"
//	            + "                              ADURCD,"
//	            + "                              ADURRF,"
//	            + "                              ADPID,"
//	            + "                              ADJOBN,"
//	            + "                              ADUSER,"
//	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //03-ADUPMJ
//	            + "                              ?,"                                            //04-ADUPMT
//	            + "                              ?,"                                            //05-ADSOS
//	            + "                              ADALPH,"
//	            + "                              ADALPH1,"
//	            + "                              ADDEPT,"
//	            + "                              ADPRJM,"
//	            + "                              ADLITM,"
//	            + "                              ADDSC1,"
//	            + "                              ADOPSQDSC1,"
//	            + "                              ADISL,"
//	            + "                              ADPROCESSEDUSER,"
//	            + "                              ADPROCESSEDDATE,"
//	            + "                              ADINSERTUSER,"
//	            + "                              ADINSERTDATE,"
//	            + "                              ADUPDATEUSER,"
//	            + "                              ADUPDATEDATE,"
//	            + "                              ADDELETEUSER,"
//	            + "                              ADDELETEDATE "    
//	            + "                         FROM F55FQ10001 A "
//	            + "                        WHERE ADAN8 = ? "
//	            + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//	            + "                                          FROM F55FQ10001 B"
//	            + "                                         WHERE A.ADAN8 = B.ADAN8 "
//	            + "                                           AND TRIM(ADYQ10DECD) = 'ATT')";
//        
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql);
//
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//	            
//            	ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(5, Settings.getTime());                                            //ADUPMT
//	            ps.setString(6, "0");                                                           //ADSOS
//	            ps.setString(7, machinename);
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            	
//            	ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getTime());                                            //ADUPMT
//	            ps.setString(5, "0");                                                           //ADSOS
//	            ps.setString(6, machinename);
//	            
//            }
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMonoOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
//            LogWriter.write("[I] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMonoOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql);
//        	LogWriter.write("[E] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
//        	notification.setModal(true);
//        	notification.setVisible(true);
//
//        } finally {
// 
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura PreparedStatement");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
    
//    public void doStopSetUpMachineBeforeChangeWorkOrderTransactionMultiOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql = "";
//
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql = "INSERT INTO F55FQ10001 SELECT ADMMCU,"
//    	        + "                              ADAN8,"
//    	        + "                              ADMCU,"
//    	        + "                              ADYQ10DECD,"
//    	        + "                              ?,"                                            							//01-ADYQ10ACCD
//    	        + "                              ADDOCO,"
//    	        + "                              ADOPSQ,"
//    	        + "                              ADSOQS,"
//    	        + "                              ADSOCN,"
//    	        + "                              ADREAC,"
//    	        + "                              ADYQ10DERN,"
//    	        + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//02-ADDTUP
//    	        + "                              ADYQ10TRCD,"
//    	        + "                              ADYQ10REST,"
//    	        + "                              ADYQ10PR01,"
//    	        + "                              ADYQ10PR02,"
//    	        + "                              ADURDT,"
//    	        + "                              ADURAB,"
//    	        + "                              ADURAT,"
//    	        + "                              ADURCD,"
//    	        + "                              ADURRF,"
//    	        + "                              ADPID,"
//    	        + "                              ADJOBN,"
//    	        + "                              ADUSER,"
//    	        + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y')))"     	//03-ADUPMJ
//    	        + "                              ?,"                                            							//04-ADUPMT
//    	        + "                              ?,"                                            							//05-ADSOS  
//    	        + "                              ADALPH,"
//    	        + "                              ADALPH1,"
//    	        + "                              ADDEPT,"
//    	        + "                              ADPRJM,"
//    	        + "                              ADLITM,"
//    	        + "                              ADDSC1,"
//    	        + "                              ADOPSQDSC1,"
//    	        + "                              ADISL,"
//    	        + "                              ADPROCESSEDUSER,"
//    	        + "                              ADPROCESSEDDATE,"
//    	        + "                              ADINSERTUSER,"
//    	        + "                              ADINSERTDATE,"
//    	        + "                              ADUPDATEUSER,"
//    	        + "                              ADUPDATEDATE,"
//    	        + "                              ADDELETEUSER,"
//    	        + "                              ADDELETEDATE "    
//    	        + "                         FROM F55FQ10001 A "
//    	        + "                        WHERE ADAN8 = ? "
//    	        + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//    	        + "                                          FROM F55FQ10001 B"
//    	        + "                                         WHERE A.ADAN8 = B.ADAN8 "
//    	        + "                                           AND TRIM(ADYQ10DECD) = 'ATT')";
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        	
//	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
//	            + "                              ADMMCU,"
//	            + "                              ADAN8,"
//	            + "                              ADMCU,"
//	            + "                              ADYQ10DECD,"
//	            + "                              ?,"                                            //01-ADYQ10ACCD
//	            + "                              ADDOCO,"
//	            + "                              ADOPSQ,"
//	            + "                              ADSOQS,"
//	            + "                              ADSOCN,"
//	            + "                              ADREAC,"
//	            + "                              ADYQ10DERN,"
//	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //02-ADDTUP
//	            + "                              ADYQ10TRCD,"
//	            + "                              ADYQ10REST,"
//	            + "                              ADYQ10PR01,"
//	            + "                              ADYQ10PR02,"
//	            + "                              ADURDT,"
//	            + "                              ADURAB,"
//	            + "                              ADURAT,"
//	            + "                              ADURCD,"
//	            + "                              ADURRF,"
//	            + "                              ADPID,"
//	            + "                              ADJOBN,"
//	            + "                              ADUSER,"
//	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //03-ADUPMJ
//	            + "                              ?,"                                            //04-ADUPMT
//	            + "                              ?,"                                            //05-ADSOS  
//	            + "                              ADALPH,"
//	            + "                              ADALPH1,"
//	            + "                              ADDEPT,"
//	            + "                              ADPRJM,"
//	            + "                              ADLITM,"
//	            + "                              ADDSC1,"
//	            + "                              ADOPSQDSC1,"
//	            + "                              ADISL,"
//	            + "                              ADPROCESSEDUSER,"
//	            + "                              ADPROCESSEDDATE,"
//	            + "                              ADINSERTUSER,"
//	            + "                              ADINSERTDATE,"
//	            + "                              ADUPDATEUSER,"
//	            + "                              ADUPDATEDATE,"
//	            + "                              ADDELETEUSER,"
//	            + "                              ADDELETEDATE "    
//	            + "                         FROM F55FQ10001 A "
//	            + "                        WHERE ADAN8 = ? "
//	            + "                          AND ADUKID = (SELECT MAX(ADUKID) "
//	            + "                                          FROM F55FQ10001 B"
//	            + "                                         WHERE A.ADAN8 = B.ADAN8 "
//	            + "                                           AND TRIM(ADYQ10DECD) = 'ATT')";
//        
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql);
//
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//	            
//            	ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(5, Settings.getTime());                                            //ADUPMT  
//	            ps.setString(6, "0");                                                           //ADSOS
//	            ps.setString(7, machinename);
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            	
//            	ps.setString(1, "S");                                                           //ADYQ10ACCD
//	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
//	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
//	            ps.setString(4, Settings.getTime());                                            //ADUPMT  
//	            ps.setString(5, "0");                                                           //ADSOS
//	            ps.setString(6, machinename);
//	            
//            }
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMultiOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
//            LogWriter.write("[I] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMultiOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql);
//        	LogWriter.write("[E] Dati: " + "[ S - Fine ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
//        	notification.setModal(true);
//        	notification.setVisible(true);
//
//        } finally {
// 
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura PreparedStatement");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                	LogWriter.write("[E] Errore in classe: [ Transaction.doStopSetUpMachineBeforeChangeWorkOrderTransactionMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
      
    public void doExitDisaggregationMultiOperator(){
	//COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";
    
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 SELECT NULL,"
        		+ "								 USMMCU,"
    	        + "                              USAN8,"
    	        + "                              ?,"                                            							//01-ADMMCU
    	        + "                              ?,"                                            							//02-ADYQ10DECD
    	        + "                              ?,"                                            							//03-ADYQ10ACCD
    	        + "                              USDOCO,"
    	        + "                              USOPSQ,"
    	        + "                              ?,"                                            							//04-ADSOQS
    	        + "                              ?,"                                            							//05-ADSOCN
    	        + "                              ?,"                                            							//06-ADREAC
    	        + "                              ?,"                                           								//07-ADYQ10DERN
    	        + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//08-ADDTUP
    	        + "                              ?,"                                            							//09-ADYQ10TRCD
    	        + "                              ?,"                                            							//10-ADYQ10REST
    	        + "                              ?,"                                            							//11-ADYQ10PR01
    	        + "                              ?,"                                            							//12-ADYQ10PR02
    	        + "                              USURDT,"                               
    	        + "                              USURAB,"
    	        + "                              USURAT,"
    	        + "                              USURCD,"
    	        + "                              USURRF,"
    	        + "                              USPID,"
    	        + "                              USJOBN,"
    	        + "                              USUSER,"
    	        + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //13-ADUPMJ
    	        + "                              ?,"                                            							//14-ADUPMT
    	        + "                              ?,"                                            							//15-ADSOS     
    	        + "                              USALPH,"
    	        + "                              USALPH1,"
    	        + "                              USDEPT,"
    	        + "                              USPRJM,"
    	        + "                              USLITM,"
    	        + "                              USDSC1,"
    	        + "                              USOPSQDSC1,"
    	        + "                              USISL,"
    	        + "                              ?,"                                            							//16-ADPROCESSEDUSER
    	        + "                              ?,"                                            							//17-ADPROCESSEDDATE
    	        + "                              ?,"                                            							//18-ADINSERTUSER
    	        + "                              ?,"                                            							//19-ADINSERTDATE
    	        + "                              ?,"                                            							//20-ADUPDATEUSER
    	        + "                              ?,"                                            							//21-ADUPDATEDATE
    	        + "                              ?,"                                            							//22-ADDELETEUSER
    	        + "                              ? "                                           					 			//23-ADDELETEDATE
    	        + "                         FROM F55FQ10002 " 
    	        + "                        WHERE TRIM(USISL) = ? "
    	        + "                          AND USAN8 = ? "
    	        + "                          AND USDOCO = 0";  
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	            + "                              USMMCU,"
	            + "                              USAN8,"
	            + "                              ?,"                                            //01-ADMMCU
	            + "                              ?,"                                            //02-ADYQ10DECD
	            + "                              ?,"                                            //03-ADYQ10ACCD
	            + "                              USDOCO,"
	            + "                              USOPSQ,"
	            + "                              ?,"                                            //04-ADSOQS
	            + "                              ?,"                                            //05-ADSOCN
	            + "                              ?,"                                            //06-ADREAC
	            + "                              ?,"                                            //07-ADYQ10DERN
	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //08-ADDTUP
	            + "                              ?,"                                            //09-ADYQ10TRCD
	            + "                              ?,"                                            //10-ADYQ10REST
	            + "                              ?,"                                            //11-ADYQ10PR01
	            + "                              ?,"                                            //12-ADYQ10PR02
	            + "                              USURDT,"                               
	            + "                              USURAB,"
	            + "                              USURAT,"
	            + "                              USURCD,"
	            + "                              USURRF,"
	            + "                              USPID,"
	            + "                              USJOBN,"
	            + "                              USUSER,"
	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //13-ADUPMJ
	            + "                              ?,"                                            //14-ADUPMT
	            + "                              ?,"                                            //15-ADSOS     
	            + "                              USALPH,"
	            + "                              USALPH1,"
	            + "                              USDEPT,"
	            + "                              USPRJM,"
	            + "                              USLITM,"
	            + "                              USDSC1,"
	            + "                              USOPSQDSC1,"
	            + "                              USISL,"
	            + "                              ?,"                                            //16-ADPROCESSEDUSER
	            + "                              ?,"                                            //17-ADPROCESSEDDATE
	            + "                              ?,"                                            //18-ADINSERTUSER
	            + "                              ?,"                                            //19-ADINSERTDATE
	            + "                              ?,"                                            //20-ADUPDATEUSER
	            + "                              ?,"                                            //21-ADUPDATEDATE
	            + "                              ?,"                                            //22-ADDELETEUSER
	            + "                              ? "                                            //23-ADDELETEDATE
	            + "                         FROM F55FQ10002 " 
	            + "                        WHERE TRIM(USISL) = ? "
	            + "                          AND USAN8 = ? "
	            + "                          AND USDOCO = 0";  
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "0");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
	            ps.setString(26, operatorname);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "0");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
	            ps.setString(25, operatorname);
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doExitDisaggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doExitDisaggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doExitDisaggregationMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doExitDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doExitDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }

    public void doExitDisaggregationMonoOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";
    
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 SELECT NULL,"
    	        + "                              USMMCU,"
    	        + "                              USAN8,"
    	        + "                              ?,"                                            							//01-ADMMCU
    	        + "                              ?,"                                            							//02-ADYQ10DECD
    	        + "                              ?,"                                            							//03-ADYQ10ACCD
    	        + "                              USDOCO,"
    	        + "                              USOPSQ,"
    	        + "                              ?,"                                            							//04-ADSOQS
    	        + "                              ?,"                                            							//05-ADSOCN
    	        + "                              ?,"                                            							//06-ADREAC
    	        + "                              ?,"                                            							//07-ADYQ10DERN
    	        + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//08-ADDTUP
    	        + "                              ?,"                                            							//09-ADYQ10TRCD
    	        + "                              ?,"                                            							//10-ADYQ10REST
    	        + "                              ?,"                                            							//11-ADYQ10PR01
    	        + "                              ?,"                                            							//12-ADYQ10PR02
    	        + "                              USURDT,"                               
    	        + "                              USURAB,"
    	        + "                              USURAT,"
    	        + "                              USURCD,"
    	        + "                              USURRF,"
    	        + "                              USPID,"
    	        + "                              USJOBN,"
    	        + "                              USUSER,"
    	        + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //13-ADUPMJ
    	        + "                              ?,"                                            							//14-ADUPMT
    	        + "                              ?,"                                            							//15-ADSOS     
    	        + "                              USALPH,"
    	        + "                              USALPH1,"
    	        + "                              USDEPT,"
    	        + "                              USPRJM,"
    	        + "                              USLITM,"
    	        + "                              USDSC1,"
    	        + "                              USOPSQDSC1,"
    	        + "                              USISL,"
    	        + "                              ?,"                                            							//16-ADPROCESSEDUSER
    	        + "                              ?,"                                            							//17-ADPROCESSEDDATE
    	        + "                              ?,"                                            							//18-ADINSERTUSER
    	        + "                              ?,"                                            							//19-ADINSERTDATE
    	        + "                              ?,"                                            							//20-ADUPDATEUSER
    	        + "                              ?,"                                            							//21-ADUPDATEDATE
    	        + "                              ?,"                                            							//22-ADDELETEUSER
    	        + "                              ? "                                            							//23-ADDELETEDATE
    	        + "                         FROM F55FQ10002 " 
    	        + "                        WHERE TRIM(USISL) = ? "
    	        + "                          AND USDOCO = 0";  
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	            + "                              USMMCU,"
	            + "                              USAN8,"
	            + "                              ?,"                                            //01-ADMMCU
	            + "                              ?,"                                            //02-ADYQ10DECD
	            + "                              ?,"                                            //03-ADYQ10ACCD
	            + "                              USDOCO,"
	            + "                              USOPSQ,"
	            + "                              ?,"                                            //04-ADSOQS
	            + "                              ?,"                                            //05-ADSOCN
	            + "                              ?,"                                            //06-ADREAC
	            + "                              ?,"                                            //07-ADYQ10DERN
	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //08-ADDTUP
	            + "                              ?,"                                            //09-ADYQ10TRCD
	            + "                              ?,"                                            //10-ADYQ10REST
	            + "                              ?,"                                            //11-ADYQ10PR01
	            + "                              ?,"                                            //12-ADYQ10PR02
	            + "                              USURDT,"                               
	            + "                              USURAB,"
	            + "                              USURAT,"
	            + "                              USURCD,"
	            + "                              USURRF,"
	            + "                              USPID,"
	            + "                              USJOBN,"
	            + "                              USUSER,"
	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //13-ADUPMJ
	            + "                              ?,"                                            //14-ADUPMT
	            + "                              ?,"                                            //15-ADSOS     
	            + "                              USALPH,"
	            + "                              USALPH1,"
	            + "                              USDEPT,"
	            + "                              USPRJM,"
	            + "                              USLITM,"
	            + "                              USDSC1,"
	            + "                              USOPSQDSC1,"
	            + "                              USISL,"
	            + "                              ?,"                                            //16-ADPROCESSEDUSER
	            + "                              ?,"                                            //17-ADPROCESSEDDATE
	            + "                              ?,"                                            //18-ADINSERTUSER
	            + "                              ?,"                                            //19-ADINSERTDATE
	            + "                              ?,"                                            //20-ADUPDATEUSER
	            + "                              ?,"                                            //21-ADUPDATEDATE
	            + "                              ?,"                                            //22-ADDELETEUSER
	            + "                              ? "                                            //23-ADDELETEDATE
	            + "                         FROM F55FQ10002 " 
	            + "                        WHERE TRIM(USISL) = ? "
	            + "                          AND USDOCO = 0";  
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "0");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "0");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doExitDisaggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doExitDisaggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doExitDisaggregationMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doExitDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doExitDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }

    public void doEnterAggregationMonoOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 SELECT NULL,"
        		+ "							 USMMCU,"
    	        + "                          USAN8,"
    	        + "                          ?,"                                            							//01-ADMMCU
    	        + "                          ?,"                                            							//02-ADYQ10DECD
    	        + "                          ?,"                                            							//03-ADYQ10ACCD
    	        + "                          USDOCO,"
    	        + "                          USOPSQ,"
    	        + "                          ?,"                                            							//04-ADSOQS
    	        + "                          ?,"                                            							//05-ADSOCN
    	        + "                          ?,"                                            							//06-ADREAC
    	        + "                          ?,"                                            							//07-ADYQ10DERN
    	        + "                          STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//08-ADDTUP
    	        + "                          ?,"                                            							//09-ADYQ10TRCD
    	        + "                          ?,"                                            							//10-ADYQ10REST
    	        + "                          ?,"                                            							//11-ADYQ10PR01
    	        + "                          ?,"                                            							//12-ADYQ10PR02
    	        + "                          USURDT,"                               
    	        + "                          USURAB,"
    	        + "                          USURAT,"
    	        + "                          USURCD,"
    	        + "                          USURRF,"
    	        + "                          USPID,"
    	        + "                          USJOBN,"
    	        + "                          USUSER,"
    	        + "                          CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //13-ADUPMJ
    	        + "                          ?,"                                            							//14-ADUPMT
    	        + "                          ?,"                                            							//15-ADSOS
    	        + "                          USALPH,"
    	        + "                          USALPH1,"
    	        + "                          USDEPT,"
    	        + "                          USPRJM,"
    	        + "                          USLITM,"
    	        + "                          USDSC1,"
    	        + "                          USOPSQDSC1,"
    	        + "                          USISL,"
    	        + "                          ?,"                                            							//16-ADPROCESSEDUSER
    	        + "                          ?,"                                            							//17-ADPROCESSEDDATE
    	        + "                          ?,"                                            							//18-ADINSERTUSER
    	        + "                          ?,"                                            							//19-ADINSERTDATE
    	        + "                          ?,"                                            							//20-ADUPDATEUSER
    	        + "                          ?,"                                            							//21-ADUPDATEDATE
    	        + "                          ?,"                                            							//22-ADDELETEUSER
    	        + "                          ? "                                            							//23-ADDELETEDATE
    	        + "                       FROM F55FQ10002 " 
    	        + "                      WHERE TRIM(USISL) = ? "  
    	        + "                        AND USDOCO = 0";  
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	            + "                              USMMCU,"
	            + "                              USAN8,"
	            + "                              ?,"                                            //01-ADMMCU
	            + "                              ?,"                                            //02-ADYQ10DECD
	            + "                              ?,"                                            //03-ADYQ10ACCD
	            + "                              USDOCO,"
	            + "                              USOPSQ,"
	            + "                              ?,"                                            //04-ADSOQS
	            + "                              ?,"                                            //05-ADSOCN
	            + "                              ?,"                                            //06-ADREAC
	            + "                              ?,"                                            //07-ADYQ10DERN
	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //08-ADDTUP
	            + "                              ?,"                                            //09-ADYQ10TRCD
	            + "                              ?,"                                            //10-ADYQ10REST
	            + "                              ?,"                                            //11-ADYQ10PR01
	            + "                              ?,"                                            //12-ADYQ10PR02
	            + "                              USURDT,"                               
	            + "                              USURAB,"
	            + "                              USURAT,"
	            + "                              USURCD,"
	            + "                              USURRF,"
	            + "                              USPID,"
	            + "                              USJOBN,"
	            + "                              USUSER,"
	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //13-ADUPMJ
	            + "                              ?,"                                            //14-ADUPMT
	            + "                              ?,"                                            //15-ADSOS
	            + "                              USALPH,"
	            + "                              USALPH1,"
	            + "                              USDEPT,"
	            + "                              USPRJM,"
	            + "                              USLITM,"
	            + "                              USDSC1,"
	            + "                              USOPSQDSC1,"
	            + "                              USISL,"
	            + "                              ?,"                                            //16-ADPROCESSEDUSER
	            + "                              ?,"                                            //17-ADPROCESSEDDATE
	            + "                              ?,"                                            //18-ADINSERTUSER
	            + "                              ?,"                                            //19-ADINSERTDATE
	            + "                              ?,"                                            //20-ADUPDATEUSER
	            + "                              ?,"                                            //21-ADUPDATEDATE
	            + "                              ?,"                                            //22-ADDELETEUSER
	            + "                              ? "                                            //23-ADDELETEDATE
	            + "                         FROM F55FQ10002 " 
	            + "                        WHERE TRIM(USISL) = ? "  
	            + "                          AND USDOCO = 0";  
	        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "0");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "0");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doEnterAggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doEnterAggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doEnterAggregationMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doEnterAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doEnterAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }

    public void doEnterAggregationMultiOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 SELECT NULL,"
        		+ "								 USMMCU,"
    	        + "                              USAN8,"
    	        + "                              ?,"                                            							//01-ADMMCU
    	        + "                              ?,"                                            							//02-ADYQ10DECD
    	        + "                              ?,"                                            							//03-ADYQ10ACCD
    	        + "                              USDOCO,"
    	        + "                              USOPSQ,"
    	        + "                              ?,"                                            							//04-ADSOQS
    	        + "                              ?,"                                            							//05-ADSOCN
    	        + "                              ?,"                                            							//06-ADREAC
    	        + "                              ?,"                                            							//07-ADYQ10DERN
    	        + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//08-ADDTUP
    	        + "                              ?,"                                            							//09-ADYQ10TRCD
    	        + "                              ?,"                                            							//10-ADYQ10REST
    	        + "                              ?,"                                            							//11-ADYQ10PR01
    	        + "                              ?,"                                            							//12-ADYQ10PR02
    	        + "                              USURDT,"                               
    	        + "                              USURAB,"
    	        + "                              USURAT,"
    	        + "                              USURCD,"
    	        + "                              USURRF,"
    	        + "                              USPID,"
    	        + "                              USJOBN,"
    	        + "                              USUSER,"
    	        + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //13-ADUPMJ
    	        + "                              ?,"                                            							//14-ADUPMT
    	        + "                              ?,"                                            							//15-ADSOS
    	        + "                              USALPH,"
    	        + "                              USALPH1,"
    	        + "                              USDEPT,"
    	        + "                              USPRJM,"
    	        + "                              USLITM,"
    	        + "                              USDSC1,"
    	        + "                              USOPSQDSC1,"
    	        + "                              USISL,"
    	        + "                              ?,"                                            							//16-ADPROCESSEDUSER
    	        + "                              ?,"                                            							//17-ADPROCESSEDDATE
    	        + "                              ?,"                                            							//18-ADINSERTUSER
    	        + "                              ?,"                                            							//19-ADINSERTDATE
    	        + "                              ?,"                                            							//20-ADUPDATEUSER
    	        + "                              ?,"                                            							//21-ADUPDATEDATE
    	        + "                              ?,"                                            							//22-ADDELETEUSER
    	        + "                              ? "                                            							//23-ADDELETEDATE
    	        + "                         FROM F55FQ10002 " 
    	        + "                        WHERE TRIM(USISL) = ? "
    	        + "                          AND USAN8 = ? "    
    	        + "                          AND USDOCO = 0";
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	            + "                              USMMCU,"
	            + "                              USAN8,"
	            + "                              ?,"                                            //01-ADMMCU
	            + "                              ?,"                                            //02-ADYQ10DECD
	            + "                              ?,"                                            //03-ADYQ10ACCD
	            + "                              USDOCO,"
	            + "                              USOPSQ,"
	            + "                              ?,"                                            //04-ADSOQS
	            + "                              ?,"                                            //05-ADSOCN
	            + "                              ?,"                                            //06-ADREAC
	            + "                              ?,"                                            //07-ADYQ10DERN
	            + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //08-ADDTUP
	            + "                              ?,"                                            //09-ADYQ10TRCD
	            + "                              ?,"                                            //10-ADYQ10REST
	            + "                              ?,"                                            //11-ADYQ10PR01
	            + "                              ?,"                                            //12-ADYQ10PR02
	            + "                              USURDT,"                               
	            + "                              USURAB,"
	            + "                              USURAT,"
	            + "                              USURCD,"
	            + "                              USURRF,"
	            + "                              USPID,"
	            + "                              USJOBN,"
	            + "                              USUSER,"
	            + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //13-ADUPMJ
	            + "                              ?,"                                            //14-ADUPMT
	            + "                              ?,"                                            //15-ADSOS
	            + "                              USALPH,"
	            + "                              USALPH1,"
	            + "                              USDEPT,"
	            + "                              USPRJM,"
	            + "                              USLITM,"
	            + "                              USDSC1,"
	            + "                              USOPSQDSC1,"
	            + "                              USISL,"
	            + "                              ?,"                                            //16-ADPROCESSEDUSER
	            + "                              ?,"                                            //17-ADPROCESSEDDATE
	            + "                              ?,"                                            //18-ADINSERTUSER
	            + "                              ?,"                                            //19-ADINSERTDATE
	            + "                              ?,"                                            //20-ADUPDATEUSER
	            + "                              ?,"                                            //21-ADUPDATEDATE
	            + "                              ?,"                                            //22-ADDELETEUSER
	            + "                              ? "                                            //23-ADDELETEDATE
	            + "                         FROM F55FQ10002 " 
	            + "                        WHERE TRIM(USISL) = ? "
	            + "                          AND USAN8 = ? "    
	            + "                          AND USDOCO = 0";  
	           
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "0");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
	            ps.setString(26, operatorname);
	        
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "0");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
	            ps.setString(25, operatorname);
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.doEnterAggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.doEnterAggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.doEnterAggregationMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doEnterAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.doEnterAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }
    
    public void StopAllTransactrionsBeforeDisaggregationMonoOperator(){
	//COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String value = "";
        String sql1 = "";
        String sql2 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "INSERT INTO F55FQ10001 SELECT NULL,"
        		 + "							  ADMMCU,"
   	             + "                              ADAN8,"
   	             + "                              ADMCU,"                                       
   	             + "                              ADYQ10DECD,"  
   	             + "                              ?,"                                            							//01-ADYQ10ACCD
   	             + "                              ADDOCO,"
   	             + "                              ADOPSQ,"
   	             + "                              ADSOQS," 
   	             + "                              ADSOCN,"
   	             + "                              ADREAC,"   
   	             + "                              ADYQ10DERN," 
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//02-ADDTUP
   	             + "                              ADYQ10TRCD,"                          
   	             + "                              ADYQ10REST,"                      
   	             + "                              ADYQ10PR01,"                         
   	             + "                              ADYQ10PR02,"                    
   	             + "                              ADURDT,"                               
   	             + "                              ADURAB,"
   	             + "                              ADURAT,"
   	             + "                              ADURCD,"
   	             + "                              ADURRF,"
   	             + "                              ADPID,"
   	             + "                              ADJOBN,"
   	             + "                              ADUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //03-ADUPMJ
   	             + "                              ?,"                                            							 //04-ADUPMT
   	             + "                              ?,"                                            							 //05-ADSOS
   	             + "                              ADALPH,"
   	             + "                              ADALPH1,"
   	             + "                              ADDEPT,"
   	             + "                              ADPRJM,"
   	             + "                              ADLITM,"
   	             + "                              ADDSC1,"
   	             + "                              ADOPSQDSC1,"
   	             + "                              ADISL,"
   	             + "                              ADPROCESSEDUSER," 
   	             + "                              ADPROCESSEDDATE,"  
   	             + "                              ADINSERTUSER,"  
   	             + "                              ADINSERTDATE," 
   	             + "                              ADUPDATEUSER," 
   	             + "                              ADUPDATEDATE,"  
   	             + "                              ADDELETEUSER,"  
   	             + "                              ADDELETEDATE "   
   	             + "                         FROM F55FQ10001 " 
   	             + "                        WHERE ADUKID = ?";  
   	        
   	        
   	        sql2 = "INSERT INTO F55FQ10001 SELECT NULL,"
   	        	 + "                              USMMCU,"
   	             + "                              USAN8,"
   	             + "                              ?,"                                               							//01-ADMMCU
   	             + "                              ?,"                                               							//02-ADYQ10DECD
   	             + "                              ?,"                                               							//03-ADYQ10ACCD
   	             + "                              USDOCO,"
   	             + "                              USOPSQ,"
   	             + "                              ?,"                                               							//04-ADSOQS
   	             + "                              ?,"                                               							//05-ADSOCN
   	             + "                              ?,"                                               							//06-ADREAC
   	             + "                              ?,"                                               							//07-ADYQ10DERN
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') + 1/86400,"    							//08-ADDTUP
   	             + "                              ?,"                                               							//09-ADYQ10TRCD
   	             + "                              ?,"                                               							//10-ADYQ10REST
   	             + "                              ?,"                                               							//11-ADYQ10PR01
   	             + "                              ?,"                                               							//12-ADYQ10PR02
   	             + "                              USURDT,"                               
   	             + "                              USURAB,"
   	             + "                              USURAT,"
   	             + "                              USURCD,"
   	             + "                              USURRF,"
   	             + "                              USPID,"
   	             + "                              USJOBN,"
   	             + "                              USUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"           //13-ADUPMJ
   	             + "                              ?,"                                               							//14-ADUPMT
   	             + "                              ?,"                                               							//15-ADSOS
   	             + "                              USALPH,"
   	             + "                              USALPH1,"
   	             + "                              USDEPT,"
   	             + "                              USPRJM,"
   	             + "                              USLITM,"
   	             + "                              USDSC1,"
   	             + "                              USOPSQDSC1,"
   	             + "                              USISL,"
   	             + "                              ?,"                                               							//16-ADPROCESSEDUSER
   	             + "                              ?,"                                               							//17-ADPROCESSEDDATE
   	             + "                              ?,"                                               							//18-ADINSERTUSER
   	             + "                              ?,"                                               							//19-ADINSERTDATE
   	             + "                              ?,"                                               							//20-ADUPDATEUSER
   	             + "                              ?,"                                               							//21-ADUPDATEDATE
   	             + "                              ?,"                                               							//22-ADDELETEUSER
   	             + "                              ? "                                               							//23-ADDELETEDATE
   	             + "                         FROM F55FQ10002" 
   	             + "                        WHERE TRIM(USISL) = ?"  
   	             + "                          AND USDOCO = 0";  
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql1 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              ADMMCU,"
	             + "                              ADAN8,"
	             + "                              ADMCU,"                                       
	             + "                              ADYQ10DECD,"  
	             + "                              ?,"                                            //01-ADYQ10ACCD
	             + "                              ADDOCO,"
	             + "                              ADOPSQ,"
	             + "                              ADSOQS," 
	             + "                              ADSOCN,"
	             + "                              ADREAC,"   
	             + "                              ADYQ10DERN," 
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //02-ADDTUP
	             + "                              ADYQ10TRCD,"                          
	             + "                              ADYQ10REST,"                      
	             + "                              ADYQ10PR01,"                         
	             + "                              ADYQ10PR02,"                    
	             + "                              ADURDT,"                               
	             + "                              ADURAB,"
	             + "                              ADURAT,"
	             + "                              ADURCD,"
	             + "                              ADURRF,"
	             + "                              ADPID,"
	             + "                              ADJOBN,"
	             + "                              ADUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //03-ADUPMJ
	             + "                              ?,"                                            //04-ADUPMT
	             + "                              ?,"                                            //05-ADSOS
	             + "                              ADALPH,"
	             + "                              ADALPH1,"
	             + "                              ADDEPT,"
	             + "                              ADPRJM,"
	             + "                              ADLITM,"
	             + "                              ADDSC1,"
	             + "                              ADOPSQDSC1,"
	             + "                              ADISL,"
	             + "                              ADPROCESSEDUSER," 
	             + "                              ADPROCESSEDDATE,"  
	             + "                              ADINSERTUSER,"  
	             + "                              ADINSERTDATE," 
	             + "                              ADUPDATEUSER," 
	             + "                              ADUPDATEDATE,"  
	             + "                              ADDELETEUSER,"  
	             + "                              ADDELETEDATE "   
	             + "                         FROM F55FQ10001 " 
	             + "                        WHERE ADUKID = ?";  
	        
	        
	        sql2 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              USMMCU,"
	             + "                              USAN8,"
	             + "                              ?,"                                               //01-ADMMCU
	             + "                              ?,"                                               //02-ADYQ10DECD
	             + "                              ?,"                                               //03-ADYQ10ACCD
	             + "                              USDOCO,"
	             + "                              USOPSQ,"
	             + "                              ?,"                                               //04-ADSOQS
	             + "                              ?,"                                               //05-ADSOCN
	             + "                              ?,"                                               //06-ADREAC
	             + "                              ?,"                                               //07-ADYQ10DERN
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') + 1/86400,"    //08-ADDTUP
	             + "                              ?,"                                               //09-ADYQ10TRCD
	             + "                              ?,"                                               //10-ADYQ10REST
	             + "                              ?,"                                               //11-ADYQ10PR01
	             + "                              ?,"                                               //12-ADYQ10PR02
	             + "                              USURDT,"                               
	             + "                              USURAB,"
	             + "                              USURAT,"
	             + "                              USURCD,"
	             + "                              USURRF,"
	             + "                              USPID,"
	             + "                              USJOBN,"
	             + "                              USUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"         //13-ADUPMJ
	             + "                              ?,"                                               //14-ADUPMT
	             + "                              ?,"                                               //15-ADSOS
	             + "                              USALPH,"
	             + "                              USALPH1,"
	             + "                              USDEPT,"
	             + "                              USPRJM,"
	             + "                              USLITM,"
	             + "                              USDSC1,"
	             + "                              USOPSQDSC1,"
	             + "                              USISL,"
	             + "                              ?,"                                               //16-ADPROCESSEDUSER
	             + "                              ?,"                                               //17-ADPROCESSEDDATE
	             + "                              ?,"                                               //18-ADINSERTUSER
	             + "                              ?,"                                               //19-ADINSERTDATE
	             + "                              ?,"                                               //20-ADUPDATEUSER
	             + "                              ?,"                                               //21-ADUPDATEDATE
	             + "                              ?,"                                               //22-ADDELETEUSER
	             + "                              ? "                                               //23-ADDELETEDATE
	             + "                         FROM F55FQ10002" 
	             + "                        WHERE TRIM(USISL) = ?"  
	             + "                          AND USDOCO = 0";  
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            
	        	ps.setString(1, "S");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
	            ps.setString(5, Settings.getTime());                                            //ADUPMT
	            ps.setString(6, "1");                                                           //ADSOS
	            
	            Iterator<String> iterator = idopentransaction.iterator();
	            
	            while (iterator.hasNext()) {
	            	
	            	value = iterator.next();
	                
	                ps.setString(7,value);
	                
	                ps.executeUpdate();
	                
	                LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	                LogWriter.write("[I] In esecuzione: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]"); 
	                LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10001 ] per Id transazone: [ " + value + " ]");
	                LogWriter.write("[I] Dati: " + "[ S - Fine ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	            	LogWriter.write("[I]       " + "[ 1 - Sospensione Transazione ] ");
	                LogWriter.write("[I]       " + "[ " + value + " ]");
	            	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	                
	            }
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, "S");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
	            ps.setString(4, Settings.getTime());                                            //ADUPMT
	            ps.setString(5, "1");                                                           //ADSOS
	            
	            Iterator<String> iterator = idopentransaction.iterator();
	            
	            while (iterator.hasNext()) {
	            	
	            	value = iterator.next();
	                
	                ps.setString(6,value);
	                
	                ps.executeUpdate();
	                
	                LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	                LogWriter.write("[I] In esecuzione: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]"); 
	                LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10001 ] per Id transazone: [ " + value + " ]");
	                LogWriter.write("[I] Dati: " + "[ S - Fine ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	            	LogWriter.write("[I]       " + "[ 1 - Sospensione Transazione ] ");
	                LogWriter.write("[I]       " + "[ " + value + " ]");
	            	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	                
	            }
	            
            }
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ 1 - Sospensione Transazione ] ");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {
            
        	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "1");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "1");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }

    public void StopAllTransactrionsBeforeDisaggregationMultiOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String value = "";
        String sql1 = "";
        String sql2 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "INSERT INTO F55FQ10001 SELECT NULL,"
        		 + "                              ADMMCU,"
   	             + "                              ADAN8,"
   	             + "                              ADMCU,"                                       
   	             + "                              ADYQ10DECD,"  
   	             + "                              ?,"                                            							//01-ADYQ10ACCD
   	             + "                              ADDOCO,"
   	             + "                              ADOPSQ,"
   	             + "                              ADSOQS," 
   	             + "                              ADSOCN,"
   	             + "                              ADREAC,"   
   	             + "                              ADYQ10DERN," 
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"           							//02-ADDTUP
   	             + "                              ADYQ10TRCD,"                          
   	             + "                              ADYQ10REST,"                      
   	             + "                              ADYQ10PR01,"                         
   	             + "                              ADYQ10PR02,"                    
   	             + "                              ADURDT,"                               
   	             + "                              ADURAB,"
   	             + "                              ADURAT,"
   	             + "                              ADURCD,"
   	             + "                              ADURRF,"
   	             + "                              ADPID,"
   	             + "                              ADJOBN,"
   	             + "                              ADUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"        //03-ADUPMJ
   	             + "                              ?,"                                            							 //04-ADUPMT
   	             + "                              ?,"                                            							 //05-ADSOS
   	             + "                              ADALPH,"
   	             + "                              ADALPH1,"
   	             + "                              ADDEPT,"
   	             + "                              ADPRJM,"
   	             + "                              ADLITM,"
   	             + "                              ADDSC1,"
   	             + "                              ADOPSQDSC1,"
   	             + "                              ADISL,"
   	             + "                              ADPROCESSEDUSER," 
   	             + "                              ADPROCESSEDDATE,"  
   	             + "                              ADINSERTUSER,"  
   	             + "                              ADINSERTDATE," 
   	             + "                              ADUPDATEUSER," 
   	             + "                              ADUPDATEDATE,"  
   	             + "                              ADDELETEUSER,"  
   	             + "                              ADDELETEDATE "   
   	             + "                         FROM F55FQ10001 " 
   	             + "                        WHERE TRIM(ADUKID) = ?"
   	             + "                          AND ADAN8 = ?";  
   	        
   	        
   	        sql2 = "INSERT INTO F55FQ10001 SELECT NULL,"
   	        	 + "							  USMMCU,"
   	             + "                              USAN8,"
   	             + "                              ?,"                                               							//01-ADMMCU
   	             + "                              ?,"                                               							//02-ADYQ10DECD
   	             + "                              ?,"                                               							//03-ADYQ10ACCD
   	             + "                              USDOCO,"
   	             + "                              USOPSQ,"
   	             + "                              ?,"                                               							//04-ADSOQS
   	             + "                              ?,"                                               							//05-ADSOCN
   	             + "                              ?,"                                               							//06-ADREAC
   	             + "                              ?,"                                               							//07-ADYQ10DERN
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') + 1/86400,"    							//08-ADDTUP
   	             + "                              ?,"                                               							//09-ADYQ10TRCD
   	             + "                              ?,"                                               							//10-ADYQ10REST
   	             + "                              ?,"                                               							//11-ADYQ10PR01
   	             + "                              ?,"                                               							//12-ADYQ10PR02
   	             + "                              USURDT,"                               
   	             + "                              USURAB,"
   	             + "                              USURAT,"
   	             + "                              USURCD,"
   	             + "                              USURRF,"
   	             + "                              USPID,"
   	             + "                              USJOBN,"
   	             + "                              USUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"           //13-ADUPMJ
   	             + "                              ?,"                                               							//14-ADUPMT
   	             + "                              ?,"                                               							//15-ADSOS
   	             + "                              USALPH,"
   	             + "                              USALPH1,"
   	             + "                              USDEPT,"
   	             + "                              USPRJM,"
   	             + "                              USLITM,"
   	             + "                              USDSC1,"
   	             + "                              USOPSQDSC1,"
   	             + "                              USISL,"
   	             + "                              ?,"                                               							//16-ADPROCESSEDUSER
   	             + "                              ?,"                                               							//17-ADPROCESSEDDATE
   	             + "                              ?,"                                               							//18-ADINSERTUSER
   	             + "                              ?,"                                               							//19-ADINSERTDATE
   	             + "                              ?,"                                               							//20-ADUPDATEUSER
   	             + "                              ?,"                                               							//21-ADUPDATEDATE
   	             + "                              ?,"                                               							//22-ADDELETEUSER
   	             + "                              ? "                                               							//23-ADDELETEDATE
   	             + "                         FROM F55FQ10002 " 
   	             + "                        WHERE TRIM(USISL) = ?"
   	             + "                          AND USAN8 = ?"  
   	             + "                          AND USDOCO = 0";  
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql1 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              ADMMCU,"
	             + "                              ADAN8,"
	             + "                              ADMCU,"                                       
	             + "                              ADYQ10DECD,"  
	             + "                              ?,"                                            //01-ADYQ10ACCD
	             + "                              ADDOCO,"
	             + "                              ADOPSQ,"
	             + "                              ADSOQS," 
	             + "                              ADSOCN,"
	             + "                              ADREAC,"   
	             + "                              ADYQ10DERN," 
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"           //02-ADDTUP
	             + "                              ADYQ10TRCD,"                          
	             + "                              ADYQ10REST,"                      
	             + "                              ADYQ10PR01,"                         
	             + "                              ADYQ10PR02,"                    
	             + "                              ADURDT,"                               
	             + "                              ADURAB,"
	             + "                              ADURAT,"
	             + "                              ADURCD,"
	             + "                              ADURRF,"
	             + "                              ADPID,"
	             + "                              ADJOBN,"
	             + "                              ADUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"      //03-ADUPMJ
	             + "                              ?,"                                            //04-ADUPMT
	             + "                              ?,"                                            //05-ADSOS
	             + "                              ADALPH,"
	             + "                              ADALPH1,"
	             + "                              ADDEPT,"
	             + "                              ADPRJM,"
	             + "                              ADLITM,"
	             + "                              ADDSC1,"
	             + "                              ADOPSQDSC1,"
	             + "                              ADISL,"
	             + "                              ADPROCESSEDUSER," 
	             + "                              ADPROCESSEDDATE,"  
	             + "                              ADINSERTUSER,"  
	             + "                              ADINSERTDATE," 
	             + "                              ADUPDATEUSER," 
	             + "                              ADUPDATEDATE,"  
	             + "                              ADDELETEUSER,"  
	             + "                              ADDELETEDATE "   
	             + "                         FROM F55FQ10001 " 
	             + "                        WHERE TRIM(ADUKID) = ?"
	             + "                          AND ADAN8 = ?";  
	        
	        
	        sql2 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              USMMCU,"
	             + "                              USAN8,"
	             + "                              ?,"                                               //01-ADMMCU
	             + "                              ?,"                                               //02-ADYQ10DECD
	             + "                              ?,"                                               //03-ADYQ10ACCD
	             + "                              USDOCO,"
	             + "                              USOPSQ,"
	             + "                              ?,"                                               //04-ADSOQS
	             + "                              ?,"                                               //05-ADSOCN
	             + "                              ?,"                                               //06-ADREAC
	             + "                              ?,"                                               //07-ADYQ10DERN
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') + 1/86400,"    //08-ADDTUP
	             + "                              ?,"                                               //09-ADYQ10TRCD
	             + "                              ?,"                                               //10-ADYQ10REST
	             + "                              ?,"                                               //11-ADYQ10PR01
	             + "                              ?,"                                               //12-ADYQ10PR02
	             + "                              USURDT,"                               
	             + "                              USURAB,"
	             + "                              USURAT,"
	             + "                              USURCD,"
	             + "                              USURRF,"
	             + "                              USPID,"
	             + "                              USJOBN,"
	             + "                              USUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"         //13-ADUPMJ
	             + "                              ?,"                                               //14-ADUPMT
	             + "                              ?,"                                               //15-ADSOS
	             + "                              USALPH,"
	             + "                              USALPH1,"
	             + "                              USDEPT,"
	             + "                              USPRJM,"
	             + "                              USLITM,"
	             + "                              USDSC1,"
	             + "                              USOPSQDSC1,"
	             + "                              USISL,"
	             + "                              ?,"                                               //16-ADPROCESSEDUSER
	             + "                              ?,"                                               //17-ADPROCESSEDDATE
	             + "                              ?,"                                               //18-ADINSERTUSER
	             + "                              ?,"                                               //19-ADINSERTDATE
	             + "                              ?,"                                               //20-ADUPDATEUSER
	             + "                              ?,"                                               //21-ADUPDATEDATE
	             + "                              ?,"                                               //22-ADDELETEUSER
	             + "                              ? "                                               //23-ADDELETEDATE
	             + "                         FROM F55FQ10002 " 
	             + "                        WHERE TRIM(USISL) = ?"
	             + "                          AND USAN8 = ?"  
	             + "                          AND USDOCO = 0";  
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            	ps.setString(1, "S");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
	            ps.setString(5, Settings.getTime());                                            //ADUPMT
	            ps.setString(6, "1");                                                           //ADSOS
	            
	            Iterator<String> iterator = idopentransaction.iterator();
	            
	            while (iterator.hasNext()) {
	                
	            	value = iterator.next();
	            	
	                ps.setString(7,value);
	                ps.setString(8, operatorname);
	                
	                ps.executeUpdate();   
	                
	                LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	                LogWriter.write("[I] In esecuzione: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]"); 
	                LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10001 ] per Id transazone: [ " + value + " ]");
	                LogWriter.write("[I] Dati: " + "[ S - Fine ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	            	LogWriter.write("[I]       " + "[ 1 - Sospensione Transazione ] ");
	                LogWriter.write("[I]       " + "[ " + value + " ]");
	                LogWriter.write("[I]       " + "[ " + operatorname + " ]");
	            	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            }
	            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "S");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, Settings.getDate());                                            //ADUPMJ
	            ps.setString(4, Settings.getTime());                                            //ADUPMT
	            ps.setString(5, "1");                                                           //ADSOS
	            
	            Iterator<String> iterator = idopentransaction.iterator();
	            
	            while (iterator.hasNext()) {
	                
	            	value = iterator.next();
	            	
	                ps.setString(6,value);
	                ps.setString(7, operatorname);
	                
	                ps.executeUpdate();   
	                
	                LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	                LogWriter.write("[I] In esecuzione: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]"); 
	                LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10001 ] per Id transazone: [ " + value + " ]");
	                LogWriter.write("[I] Dati: " + "[ S - Fine ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	            	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	            	LogWriter.write("[I]       " + "[ 1 - Sospensione Transazione ] ");
	                LogWriter.write("[I]       " + "[ " + value + " ]");
	                LogWriter.write("[I]       " + "[ " + operatorname + " ]");
	            	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            }
            	
            }
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ 1 - Sospensione Transazione ] ");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {
        	
        	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "1");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
	            ps.setString(26, operatorname);
	            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "USC");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "1");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
	            ps.setString(25, operatorname);
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ USC - Uscita ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StopAllTransactrionsBeforeDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }
    
    public void StartAllSuspendedTransactrionsBeforeAggregationMonoOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "INSERT INTO F55FQ10001 SELECT NULL,"
        		 + "                              USMMCU,"
   	             + "                              USAN8,"
   	             + "                              ?,"                                           							//01-ADMMCU
   	             + "                              ?,"                                           							//02-ADYQ10DECD
   	             + "                              ?,"                                           							//03-ADYQ10ACCD
   	             + "                              USDOCO,"
   	             + "                              USOPSQ,"
   	             + "                              ?,"                                           							//04-ADSOQS
   	             + "                              ?,"                                           							//05-ADSOCN
   	             + "                              ?,"                                           							//06-ADREAC
   	             + "                              ?,"                                           							//07-ADYQ10DERN
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"          							//08-ADDTUP
   	             + "                              ?,"                                           							//09-ADYQ10TRCD
   	             + "                              ?,"                                           							//10-ADYQ10REST
   	             + "                              ?,"                                           							//11-ADYQ10PR01
   	             + "                              ?,"                                           							//12-ADYQ10PR02
   	             + "                              USURDT,"                               
   	             + "                              AGAN8,"
   	             + "                              USURAT,"
   	             + "                              USURCD,"
   	             + "                              USURRF,"
   	             + "                              USPID,"
   	             + "                              USJOBN,"
   	             + "                              USUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"       //13-ADUPMJ
   	             + "                              ?,"                                           							//14-ADUPMT
   	             + "                              ?,"                                           							//15-ADSOS
   	             + "                              USALPH,"
   	             + "                              AGALPH,"
   	             + "                              USDEPT,"
   	             + "                              USPRJM,"
   	             + "                              USLITM,"
   	             + "                              USDSC1,"
   	             + "                              USOPSQDSC1,"
   	             + "                              USISL,"
   	             + "                              ?,"                                           //16-ADPROCESSEDUSER
   	             + "                              ?,"                                           //17-ADPROCESSEDDATE
   	             + "                              ?,"                                           //18-ADINSERTUSER
   	             + "                              ?,"                                           //19-ADINSERTDATE
   	             + "                              ?,"                                           //20-ADUPDATEUSER
   	             + "                              ?,"                                           //21-ADUPDATEDATE
   	             + "                              ?,"                                           //22-ADDELETEUSER
   	             + "                              ? "                                           //23-ADDELETEDATE
   	             + "                         FROM F55FQ10002 "
   	             + "                    LEFT JOIN F55FQ10007 ON (USISL = AGISL)" 
   	             + "                        WHERE TRIM(USISL) = ? "  
   	             + "                          AND USDOCO = 0";  
   	        
   	        
   	        sql2 = "INSERT INTO F55FQ10001 SELECT NULL,"
   	        	 + "                              ADMMCU,"
   	             + "                              ADAN8,"
   	             + "                              ADMCU,"                                       
   	             + "                              ADYQ10DECD,"  
   	             + "                              ?,"                                               							//01-ADYQ10ACCD
   	             + "                              ADDOCO,"
   	             + "                              ADOPSQ,"
   	             + "                              ADSOQS," 
   	             + "                              ADSOCN,"
   	             + "                              ADREAC,"   
   	             + "                              ADYQ10DERN," 
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') + 1/86400,"    							//02-ADDTUP
   	             + "                              ADYQ10TRCD,"                          
   	             + "                              ADYQ10REST,"                      
   	             + "                              ?,"                                               							//03-ADYQ10PR01                         
   	             + "                              ADYQ10PR02,"                    
   	             + "                              ADURDT,"                               
   	             + "                              AGAN8,"
   	             + "                              ADURAT,"
   	             + "                              ADURCD,"
   	             + "                              ADURRF,"
   	             + "                              ADPID,"
   	             + "                              ADJOBN,"
   	             + "                              ADUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"           //04-ADUPMJ
   	             + "                              ?,"                                               							//05-ADUPMT
   	             + "                              ?,"                                               							//06-ADSOS
   	             + "                              ADALPH,"
   	             + "                              AGALPH,"
   	             + "                              ADDEPT,"
   	             + "                              ADPRJM,"
   	             + "                              ADLITM,"
   	             + "                              ADDSC1,"
   	             + "                              ADOPSQDSC1,"
   	             + "                              ADISL,"
   	             + "                              ?,"                                               							//07-ADPROCESSEDUSER
   	             + "                              ?,"                                               							//08-ADPROCESSEDDATE  
   	             + "                              ADINSERTUSER,"  
   	             + "                              ADINSERTDATE," 
   	             + "                              ADUPDATEUSER," 
   	             + "                              ADUPDATEDATE,"  
   	             + "                              ADDELETEUSER,"  
   	             + "                              ADDELETEDATE "   
   	             + "                         FROM F55FQ10001 "
   	             + "                    LEFT JOIN F55FQ10007 ON (ADISL = AGISL)"
   	             + "                        WHERE ADSOS = '1' "
   	             + "                          AND ADYQ10ACCD = 'S' " 
   	             + "                          AND TRIM(ADISL) = ?";
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql1 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              USMMCU,"
	             + "                              USAN8,"
	             + "                              ?,"                                           //01-ADMMCU
	             + "                              ?,"                                           //02-ADYQ10DECD
	             + "                              ?,"                                           //03-ADYQ10ACCD
	             + "                              USDOCO,"
	             + "                              USOPSQ,"
	             + "                              ?,"                                           //04-ADSOQS
	             + "                              ?,"                                           //05-ADSOCN
	             + "                              ?,"                                           //06-ADREAC
	             + "                              ?,"                                           //07-ADYQ10DERN
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"          //08-ADDTUP
	             + "                              ?,"                                           //09-ADYQ10TRCD
	             + "                              ?,"                                           //10-ADYQ10REST
	             + "                              ?,"                                           //11-ADYQ10PR01
	             + "                              ?,"                                           //12-ADYQ10PR02
	             + "                              USURDT,"                               
	             + "                              AGAN8,"
	             + "                              USURAT,"
	             + "                              USURCD,"
	             + "                              USURRF,"
	             + "                              USPID,"
	             + "                              USJOBN,"
	             + "                              USUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"     //13-ADUPMJ
	             + "                              ?,"                                           //14-ADUPMT
	             + "                              ?,"                                           //15-ADSOS
	             + "                              USALPH,"
	             + "                              AGALPH,"
	             + "                              USDEPT,"
	             + "                              USPRJM,"
	             + "                              USLITM,"
	             + "                              USDSC1,"
	             + "                              USOPSQDSC1,"
	             + "                              USISL,"
	             + "                              ?,"                                           //16-ADPROCESSEDUSER
	             + "                              ?,"                                           //17-ADPROCESSEDDATE
	             + "                              ?,"                                           //18-ADINSERTUSER
	             + "                              ?,"                                           //19-ADINSERTDATE
	             + "                              ?,"                                           //20-ADUPDATEUSER
	             + "                              ?,"                                           //21-ADUPDATEDATE
	             + "                              ?,"                                           //22-ADDELETEUSER
	             + "                              ? "                                           //23-ADDELETEDATE
	             + "                         FROM F55FQ10002 "
	             + "                    LEFT JOIN F55FQ10007 ON (USISL = AGISL)" 
	             + "                        WHERE TRIM(USISL) = ? "  
	             + "                          AND USDOCO = 0";  
	        
	        
	        sql2 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              ADMMCU,"
	             + "                              ADAN8,"
	             + "                              ADMCU,"                                       
	             + "                              ADYQ10DECD,"  
	             + "                              ?,"                                               //01-ADYQ10ACCD
	             + "                              ADDOCO,"
	             + "                              ADOPSQ,"
	             + "                              ADSOQS," 
	             + "                              ADSOCN,"
	             + "                              ADREAC,"   
	             + "                              ADYQ10DERN," 
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') + 1/86400,"    //02-ADDTUP
	             + "                              ADYQ10TRCD,"                          
	             + "                              ADYQ10REST,"                      
	             + "                              ?,"                                               //03-ADYQ10PR01                         
	             + "                              ADYQ10PR02,"                    
	             + "                              ADURDT,"                               
	             + "                              AGAN8,"
	             + "                              ADURAT,"
	             + "                              ADURCD,"
	             + "                              ADURRF,"
	             + "                              ADPID,"
	             + "                              ADJOBN,"
	             + "                              ADUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"         //04-ADUPMJ
	             + "                              ?,"                                               //05-ADUPMT
	             + "                              ?,"                                               //06-ADSOS
	             + "                              ADALPH,"
	             + "                              AGALPH,"
	             + "                              ADDEPT,"
	             + "                              ADPRJM,"
	             + "                              ADLITM,"
	             + "                              ADDSC1,"
	             + "                              ADOPSQDSC1,"
	             + "                              ADISL,"
	             + "                              ?,"                                               //07-ADPROCESSEDUSER
	             + "                              ?,"                                               //08-ADPROCESSEDDATE  
	             + "                              ADINSERTUSER,"  
	             + "                              ADINSERTDATE," 
	             + "                              ADUPDATEUSER," 
	             + "                              ADUPDATEDATE,"  
	             + "                              ADDELETEUSER,"  
	             + "                              ADDELETEDATE "   
	             + "                         FROM F55FQ10001 "
	             + "                    LEFT JOIN F55FQ10007 ON (ADISL = AGISL)"
	             + "                        WHERE ADSOS = '1' "
	             + "                          AND ADYQ10ACCD = 'S' " 
	             + "                          AND TRIM(ADISL) = ?";
                
        }
        
        sql3 = "UPDATE F55FQ10001 "
             + "   SET ADSOS = ? "
             + " WHERE TRIM(ADISL) = ?";
        
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "0");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "0");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            	ps.setString(1, "A");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, " ");                                                           //ADYQ10PR01
	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
	            ps.setString(5, Settings.getDate());                                            //ADUPMJ
	            ps.setString(6, Settings.getTime());                                            //ADUPMT
	            ps.setString(7, "0");                                                           //ADSOS
	            ps.setString(8, " ");                                                           //ADPROCESSEDUSER
	            ps.setString(9, null);                                                          //ADPROCESSEDDATE
	            ps.setString(10, workcentername);

            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "A");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, " ");                                                           //ADYQ10PR01
	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
	            ps.setString(5, Settings.getTime());                                            //ADUPMT
	            ps.setString(6, "0");                                                           //ADSOS
	            ps.setString(7, " ");                                                           //ADPROCESSEDUSER
	            ps.setString(8, null);                                                          //ADPROCESSEDDATE
	            ps.setString(9, workcentername);
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10001 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ A - Inizio ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

        	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
            ps.setString(1, "0");
            ps.setString(2, workcentername);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }
    
    public void StartAllSuspendedTransactrionsBeforeAggregationMultiOperator(){
	//COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "INSERT INTO F55FQ10001 SELECT NULL,"
        		 + "                              USMMCU,"
   	             + "                              USAN8,"
   	             + "                              ?,"                                           							//01-ADMMCU
   	             + "                              ?,"                                           							//02-ADYQ10DECD
   	             + "                              ?,"                                           							//03-ADYQ10ACCD
   	             + "                              USDOCO,"
   	             + "                              USOPSQ,"
   	             + "                              ?,"                                           							//04-ADSOQS
   	             + "                              ?,"                                           							//05-ADSOCN
   	             + "                              ?,"                                           							//06-ADREAC
   	             + "                              ?,"                                           							//07-ADYQ10DERN
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"          							//08-ADDTUP
   	             + "                              ?,"                                           							//09-ADYQ10TRCD
   	             + "                              ?,"                                           							//10-ADYQ10REST
   	             + "                              ?,"                                           							//11-ADYQ10PR01
   	             + "                              ?,"                                           							//12-ADYQ10PR02
   	             + "                              USURDT,"                               
   	             + "                              AGAN8,"
   	             + "                              USURAT,"
   	             + "                              USURCD,"
   	             + "                              USURRF,"
   	             + "                              USPID,"
   	             + "                              USJOBN,"
   	             + "                              USUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"       //13-ADUPMJ
   	             + "                              ?,"                                           							//14-ADUPMT
   	             + "                              ?,"                                           							//15-ADSOS
   	             + "                              USALPH,"
   	             + "                              AGALPH,"
   	             + "                              USDEPT,"
   	             + "                              USPRJM,"
   	             + "                              USLITM,"
   	             + "                              USDSC1,"
   	             + "                              USOPSQDSC1,"
   	             + "                              USISL,"
   	             + "                              ?,"                                           							//16-ADPROCESSEDUSER
   	             + "                              ?,"                                           							//17-ADPROCESSEDDATE
   	             + "                              ?,"                                           							//18-ADINSERTUSER
   	             + "                              ?,"                                           							//19-ADINSERTDATE
   	             + "                              ?,"                                           							//20-ADUPDATEUSER
   	             + "                              ?,"                                           							//21-ADUPDATEDATE
   	             + "                              ?,"                                           							//22-ADDELETEUSER
   	             + "                              ? "                                           							//23-ADDELETEDATE
   	             + "                         FROM F55FQ10002 "
   	             + "                         LEFT JOIN F55FQ10007 ON (AGISL = USISL AND AGAN8 = USAN8) "
   	             + "                        WHERE TRIM(USISL) = ?"
   	             + "                          AND USAN8 = ?"  
   	             + "                          AND USDOCO = 0";  
   	        
   	        
   	        sql2 = "INSERT INTO F55FQ10001 SELECT NULL,"
   	        	 + "                              ADMMCU,"
   	             + "                              ADAN8,"
   	             + "                              ADMCU,"                                       
   	             + "                              ADYQ10DECD,"  
   	             + "                              ?,"                                               							//01-ADYQ10ACCD
   	             + "                              ADDOCO,"
   	             + "                              ADOPSQ,"
   	             + "                              ADSOQS," 
   	             + "                              ADSOCN,"
   	             + "                              ADREAC,"   
   	             + "                              ADYQ10DERN," 
   	             + "                              STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') + 1/86400,"    							//02-ADDTUP
   	             + "                              ADYQ10TRCD,"                          
   	             + "                              ADYQ10REST,"                      
   	             + "                              ?,"                                               							//03-ADYQ10PR01           
   	             + "                              ADYQ10PR02,"                    
   	             + "                              ADURDT,"                               
   	             + "                              AGAN8,"
   	             + "                              ADURAT,"
   	             + "                              ADURCD,"
   	             + "                              ADURRF,"
   	             + "                              ADPID,"
   	             + "                              ADJOBN,"
   	             + "                              ADUSER,"
   	             + "                              CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"          //04-ADUPMJ
   	             + "                              ?,"                                               							//05-ADUPMT
   	             + "                              ?,"                                               							//06-ADSOS
   	             + "                              ADALPH,"
   	             + "                              AGALPH,"
   	             + "                              ADDEPT,"
   	             + "                              ADPRJM,"
   	             + "                              ADLITM,"
   	             + "                              ADDSC1,"
   	             + "                              ADOPSQDSC1,"
   	             + "                              ADISL,"
   	             + "                              ?,"                                               							//07-ADPROCESSEDUSER
   	             + "                              ?,"                                               							//08-ADPROCESSEDDATE
   	             + "                              ADINSERTUSER,"  
   	             + "                              ADINSERTDATE," 
   	             + "                              ADUPDATEUSER," 
   	             + "                              ADUPDATEDATE,"  
   	             + "                              ADDELETEUSER,"  
   	             + "                              ADDELETEDATE "   
   	             + "                         FROM F55FQ10001 "
   	             + "                         LEFT JOIN F55FQ10007 ON (AGISL = ADISL AND AGAN8 = ADAN8) "
   	             + "                        WHERE ADSOS = '1' "
   	             + "                          AND ADYQ10ACCD = 'S' " 
   	             + "                          AND TRIM(ADISL) = ?"
   	             + "                          AND ADAN8 = ?";
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql1 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              USMMCU,"
	             + "                              USAN8,"
	             + "                              ?,"                                           //01-ADMMCU
	             + "                              ?,"                                           //02-ADYQ10DECD
	             + "                              ?,"                                           //03-ADYQ10ACCD
	             + "                              USDOCO,"
	             + "                              USOPSQ,"
	             + "                              ?,"                                           //04-ADSOQS
	             + "                              ?,"                                           //05-ADSOCN
	             + "                              ?,"                                           //06-ADREAC
	             + "                              ?,"                                           //07-ADYQ10DERN
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"          //08-ADDTUP
	             + "                              ?,"                                           //09-ADYQ10TRCD
	             + "                              ?,"                                           //10-ADYQ10REST
	             + "                              ?,"                                           //11-ADYQ10PR01
	             + "                              ?,"                                           //12-ADYQ10PR02
	             + "                              USURDT,"                               
	             + "                              AGAN8,"
	             + "                              USURAT,"
	             + "                              USURCD,"
	             + "                              USURRF,"
	             + "                              USPID,"
	             + "                              USJOBN,"
	             + "                              USUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"     //13-ADUPMJ
	             + "                              ?,"                                           //14-ADUPMT
	             + "                              ?,"                                           //15-ADSOS
	             + "                              USALPH,"
	             + "                              AGALPH,"
	             + "                              USDEPT,"
	             + "                              USPRJM,"
	             + "                              USLITM,"
	             + "                              USDSC1,"
	             + "                              USOPSQDSC1,"
	             + "                              USISL,"
	             + "                              ?,"                                           //16-ADPROCESSEDUSER
	             + "                              ?,"                                           //17-ADPROCESSEDDATE
	             + "                              ?,"                                           //18-ADINSERTUSER
	             + "                              ?,"                                           //19-ADINSERTDATE
	             + "                              ?,"                                           //20-ADUPDATEUSER
	             + "                              ?,"                                           //21-ADUPDATEDATE
	             + "                              ?,"                                           //22-ADDELETEUSER
	             + "                              ? "                                           //23-ADDELETEDATE
	             + "                         FROM F55FQ10002 "
	             + "                         LEFT JOIN F55FQ10007 ON (AGISL = USISL AND AGAN8 = USAN8) "
	             + "                        WHERE TRIM(USISL) = ?"
	             + "                          AND USAN8 = ?"  
	             + "                          AND USDOCO = 0";  
	        
	        
	        sql2 = "INSERT INTO F55FQ10001 SELECT F55FQ10001SEQ.NEXTVAL,"
	             + "                              ADMMCU,"
	             + "                              ADAN8,"
	             + "                              ADMCU,"                                       
	             + "                              ADYQ10DECD,"  
	             + "                              ?,"                                               //01-ADYQ10ACCD
	             + "                              ADDOCO,"
	             + "                              ADOPSQ,"
	             + "                              ADSOQS," 
	             + "                              ADSOCN,"
	             + "                              ADREAC,"   
	             + "                              ADYQ10DERN," 
	             + "                              TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') + 1/86400,"    //02-ADDTUP
	             + "                              ADYQ10TRCD,"                          
	             + "                              ADYQ10REST,"                      
	             + "                              ?,"                                               //03-ADYQ10PR01           
	             + "                              ADYQ10PR02,"                    
	             + "                              ADURDT,"                               
	             + "                              AGAN8,"
	             + "                              ADURAT,"
	             + "                              ADURCD,"
	             + "                              ADURRF,"
	             + "                              ADPID,"
	             + "                              ADJOBN,"
	             + "                              ADUSER,"
	             + "                              DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"         //04-ADUPMJ
	             + "                              ?,"                                               //05-ADUPMT
	             + "                              ?,"                                               //06-ADSOS
	             + "                              ADALPH,"
	             + "                              AGALPH,"
	             + "                              ADDEPT,"
	             + "                              ADPRJM,"
	             + "                              ADLITM,"
	             + "                              ADDSC1,"
	             + "                              ADOPSQDSC1,"
	             + "                              ADISL,"
	             + "                              ?,"                                               //07-ADPROCESSEDUSER
	             + "                              ?,"                                               //08-ADPROCESSEDDATE
	             + "                              ADINSERTUSER,"  
	             + "                              ADINSERTDATE," 
	             + "                              ADUPDATEUSER," 
	             + "                              ADUPDATEDATE,"  
	             + "                              ADDELETEUSER,"  
	             + "                              ADDELETEDATE "   
	             + "                         FROM F55FQ10001 "
	             + "                         LEFT JOIN F55FQ10007 ON (AGISL = ADISL AND AGAN8 = ADAN8) "
	             + "                        WHERE ADSOS = '1' "
	             + "                          AND ADYQ10ACCD = 'S' " 
	             + "                          AND TRIM(ADISL) = ?"
	             + "                          AND ADAN8 = ?";
                
        }
        
        sql3 = "UPDATE F55FQ10001 "
             + "   SET ADSOS = ? "
             + " WHERE TRIM(ADISL) = ?"
             + "   AND ADAN8 = ?";
        
        
        try {

            conn = Settings.getDbConnection(); 
            ps = conn.prepareStatement(sql1);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getDate());                                           //ADUPMJ
	            ps.setString(15, Settings.getTime());                                           //ADUPMT
	            ps.setString(16, "0");                                                          //ADSOS
	            ps.setString(17, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(18, null);                                                         //ADPROCESSEDDATE
	            ps.setString(19, " ");                                                          //ADINSERTUSER
	            ps.setString(20, null);                                                         //ADINSERTDATE
	            ps.setString(21, " ");                                                          //ADUPDATEUSER
	            ps.setString(22, null);                                                         //ADUPDATEDATE
	            ps.setString(23, " ");                                                          //ADDELETEUSER
	            ps.setString(24, null);                                                         //ADDELETEDATE
	            ps.setString(25, workcentername);
	            ps.setString(26, operatorname);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, "            ");                                                //ADMMCU
	            ps.setString(2, "ING");                                                         //ADYQ10DECD
	            ps.setString(3, " ");                                                           //ADYQ10ACCD
	            ps.setString(4, "0");                                                           //ADSOQS
	            ps.setString(5, "0");                                                           //ADSOCN
	            ps.setString(6, "  ");                                                          //ADREAC
	            ps.setString(7, "   ");                                                         //ADYQ10DERN
	            ps.setString(8, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(9, "O");                                                           //ADYQ10TRCD
	            ps.setString(10, " ");                                                          //ADYQ10REST
	            ps.setString(11, " ");                                                          //ADYQ10PR01
	            ps.setString(12, " ");                                                          //ADYQ10PR02
	            ps.setString(13, Settings.getDate());                                           //ADUPMJ
	            ps.setString(14, Settings.getTime());                                           //ADUPMT
	            ps.setString(15, "0");                                                          //ADSOS
	            ps.setString(16, " ");                                                          //ADPROCESSEDUSER
	            ps.setString(17, null);                                                         //ADPROCESSEDDATE
	            ps.setString(18, " ");                                                          //ADINSERTUSER
	            ps.setString(19, null);                                                         //ADINSERTDATE
	            ps.setString(20, " ");                                                          //ADUPDATEUSER
	            ps.setString(21, null);                                                         //ADUPDATEDATE
	            ps.setString(22, " ");                                                          //ADDELETEUSER
	            ps.setString(23, null);                                                         //ADDELETEDATE
	            ps.setString(24, workcentername);
	            ps.setString(25, operatorname);
            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ ING - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {
            
        	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, "A");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, " ");                                                           //ADYQ10PR01
	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
	            ps.setString(5, Settings.getDate());                                            //ADUPMJ
	            ps.setString(6, Settings.getTime());                                            //ADUPMT
	            ps.setString(7, "0");                                                           //ADSOS
	            ps.setString(8, " ");                                                           //ADPROCESSEDUSER
	            ps.setString(9, null);                                                          //ADPROCESSEDDATE
	            ps.setString(10, workcentername);
	            ps.setString(11, operatorname );
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "A");                                                           //ADYQ10ACCD
	            ps.setString(2, Settings.getTimestamp());                                       //ADDTUP
	            ps.setString(3, " ");                                                           //ADYQ10PR01
	            ps.setString(4, Settings.getDate());                                            //ADUPMJ
	            ps.setString(5, Settings.getTime());                                            //ADUPMT
	            ps.setString(6, "0");                                                           //ADSOS
	            ps.setString(7, " ");                                                           //ADPROCESSEDUSER
	            ps.setString(8, null);                                                          //ADPROCESSEDDATE
	            ps.setString(9, workcentername);
	            ps.setString(10, operatorname );
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] da Select su: [ F55FQ10001 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ A - Inizio ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {
            
        	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
            ps.setString(1, "0");
            ps.setString(2, workcentername);
            ps.setString(3, operatorname);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
             
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.StartAllSuspendedTransactrionsBeforeAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    }

    public void multiDoStopWorkMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "   
    	        + "                VALUES (LPAD(?,12,' '),"                                                  			  //01-ADMMCU
    	        + "                        ?,"                                                  						  //02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  			  //03-ADMCU
    	        + "                        ?,"                                                  						  //04-ADYQ10DECD
    	        + "                        ?,"                                                  						  //05-ADYQ10ACCD
    	        + "                        ?,"                                                  						  //06-ADDOCO
    	        + "                        ?,"                                                  						  //07-ADOPSQ
    	        + "                        ?,"                                                  						  //08-ADSOQS
    	        + "                        ?,"                                                  						  //09-ADSOCN
    	        + "                        ?,"                                                  						  //10-ADREAC
    	        + "                        ?,"                                                  						  //11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 						  //12-ADDTUP
    	        + "                        ?,"                                                  						  //13-ADYQ10TRCD
    	        + "                        ?,"                                                  						  //14-ADYQ10REST
    	        + "                        ?,"                                                  						  //15-ADYQ10PR01
    	        + "                        ?,"                                                  						  //16-ADYQ10PR02
    	        + "                        ?,"                                                  						  //17-ADURDT
    	        + "                        ?,"                                                  						  //18-ADURAB
    	        + "                        ?,"                                                  						  //19-ADURAT
    	        + "                        ?,"                                                  						  //20-ADURCD
    	        + "                        ?,"                                                  						  //21-ADURRF
    	        + "                        ?,"                                                  						  //22-ADPID
    	        + "                        ?,"                                                  						  //23-ADJOBN
    	        + "                        ?,"                                                  						  //24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"            //25-ADUPMJ
    	        + "                        ?,"                                                  						  //26-ADUPMT
    	        + "                        ?,"                                                  						  //27-ADSOS
    	        + "                        ?,"                                                  						  //28-ADALPH
    	        + "                        ?,"                                                  						  //29-ADALPH1   
    	        + "                        ?,"                                                  						  //30-ADDEPT
    	        + "                        ?,"                                                  						  //31-ADPRJM                                        
    	        + "                        ?,"                                                  						  //32-ADLITM
    	        + "                        ?,"                                                  						  //33-ADDSC1
    	        + "                        ?,"                                                  						  //34-ADOPSQDSC1
    	        + "                        ?,"                                                  						  //35-ADISL
    	        + "                        ?,"                                                  						  //36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  						  //37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  						  //38-ADINSERTUSER
    	        + "                        ?,"                                                  						  //39-ADINSERTDATE
    	        + "                        ?,"                                                  						  //40-ADUPDATEUSER
    	        + "                        ?,"                                                  						  //41-ADUPDATEDATE
    	        + "                        ?,"                                                  						  //42-ADDELETEUSER
    	        + "                        ?)";                                                 						  //43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "   
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1   
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM                                        
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
                     
            
            for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);               											//ADMMCU
		            ps.setString(2, machinename);                                          						//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
		            ps.setString(5, "S");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);                   						//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(27, Settings.getTime());                                           			//ADUPMT
		            ps.setString(28, "0");                                                          			//ADSOS
		            ps.setString(29, machinedescription);                       								//ADALPH
		            ps.setString(30, operatoraggregatedtoworkcenterdescription);								//ADALPH1
		            ps.setString(31, machinedefaultdept);                       								//ADDEPT
		            ps.setString(32, workorderjob);                         									//ADPRJM
		            ps.setString(33, workorderitem);                        									//ADLITM
		            ps.setString(34, workorderitemdescription);             									//ADDSC1
		            ps.setString(35, phasedescription);                   										//ADOPSQDSC1
		            ps.setString(36, workcentername);                 											//ADISL
		            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          			//ADINSERTUSER
		            ps.setString(40, null);                                                         			//ADINSERTDATE
		            ps.setString(41, " ");                                                          			//ADUPDATEUSER
		            ps.setString(42, null);                                                         			//ADUPDATEDATE
		            ps.setString(43, " ");                                                          			//ADDELETEUSER
		            ps.setString(44, null);                                                         			//ADDELETEDATE
		            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);               											//ADMMCU
		            ps.setString(2, machinename);                                          						//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
		            ps.setString(5, "S");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);                   						//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getTime());                                           			//ADUPMT
		            ps.setString(27, "0");                                                          			//ADSOS
		            ps.setString(28, machinedescription);                       								//ADALPH
		            ps.setString(29, operatoraggregatedtoworkcenterdescription);								//ADALPH1
		            ps.setString(30, machinedefaultdept);                       								//ADDEPT
		            ps.setString(31, workorderjob);                         									//ADPRJM
		            ps.setString(32, workorderitem);                        									//ADLITM
		            ps.setString(33, workorderitemdescription);             									//ADDSC1
		            ps.setString(34, phasedescription);                   										//ADOPSQDSC1
		            ps.setString(35, workcentername);                 											//ADISL
		            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          			//ADINSERTUSER
		            ps.setString(39, null);                                                         			//ADINSERTDATE
		            ps.setString(40, " ");                                                          			//ADUPDATEUSER
		            ps.setString(41, null);                                                         			//ADUPDATEDATE
		            ps.setString(42, " ");                                                          			//ADDELETEUSER
		            ps.setString(43, null);                                                         			//ADDELETEDATE
            		
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStopWorkMachineTransactionMonoOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
	        	LogWriter.write("[I]       " + "[ S - Fine ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            
            }
                 
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopWorkMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStopWorkMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStartSetUpMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE 
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE 
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
          
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);              												//ADMMCU
		            ps.setString(2, machinename);                                          						//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
		            ps.setString(5, "A");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(27, Settings.getTime());                                           			//ADUPMT
		            ps.setString(28,"0");                                                          				//ADSOS
		            ps.setString(29, machinedescription);                       								//ADALPH
		            ps.setString(30, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
		            ps.setString(31, machinedefaultdept);                       								//ADDEPT
		            ps.setString(32, workorderjob);                         									//ADPRJM
		            ps.setString(33, workorderitem);                        									//ADLITM
		            ps.setString(34, workorderitemdescription);             									//ADDSC1
		            ps.setString(35, phasedescription);                   										//ADOPSQDSC1
		            ps.setString(36, workcentername);                											//ADISL
		            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          			//ADINSERTUSER
		            ps.setString(40, null);                                                         			//ADINSERTDATE
		            ps.setString(41, " ");                                                          			//ADUPDATEUSER
		            ps.setString(42, null);                                                         			//ADUPDATEDATE
		            ps.setString(43, " ");                                                          			//ADDELETEUSER
		            ps.setString(44, null);                                                         			//ADDELETEDATE
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);              												//ADMMCU
		            ps.setString(2, machinename);                                          						//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
		            ps.setString(5, "A");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getTime());                                           			//ADUPMT
		            ps.setString(27,"0");                                                          				//ADSOS
		            ps.setString(28, machinedescription);                       								//ADALPH
		            ps.setString(29, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
		            ps.setString(30, machinedefaultdept);                       								//ADDEPT
		            ps.setString(31, workorderjob);                         									//ADPRJM
		            ps.setString(32, workorderitem);                        									//ADLITM
		            ps.setString(33, workorderitemdescription);             									//ADDSC1
		            ps.setString(34, phasedescription);                   										//ADOPSQDSC1
		            ps.setString(35, workcentername);                											//ADISL
		            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          			//ADINSERTUSER
		            ps.setString(39, null);                                                         			//ADINSERTDATE
		            ps.setString(40, " ");                                                          			//ADUPDATEUSER
		            ps.setString(41, null);                                                         			//ADUPDATEDATE
		            ps.setString(42, " ");                                                          			//ADDELETEUSER
		            ps.setString(43, null);                                                         			//ADDELETEDATE
		            
            	}
            	
	            ps.executeUpdate();
            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStartSetUpMachineTransactionMonoOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
	        	LogWriter.write("[I]       " + "[ A - Inizio ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	        	
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartSetUpMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStartSetUpMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopSetUpMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);                  											//ADMMCU
		            ps.setString(2, machinename);                                         			    		//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
		            ps.setString(5, "S");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);                 							//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(27, Settings.getTime());                                           			//ADUPMT
		            ps.setString(28, "0");                                                          			//ADSOS
		            ps.setString(29, machinedescription);                       								//ADALPH
		            ps.setString(30, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
		            ps.setString(31, machinedefaultdept);                       								//ADDEPT
		            ps.setString(32, workorderjob);                         									//ADPRJM
		            ps.setString(33, workorderitem);                        									//ADLITM
		            ps.setString(34, workorderitemdescription);             									//ADDSC1
		            ps.setString(35, phasedescription);                   										//ADOPSQDSC1
		            ps.setString(36, workcentername);                 											//ADISL
		            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          			//ADINSERTUSER
		            ps.setString(40, null);                                                         			//ADINSERTDATE
		            ps.setString(41, " ");                                                          			//ADUPDATEUSER
		            ps.setString(42, null);                                                         			//ADUPDATEDATE
		            ps.setString(43, " ");                                                          			//ADDELETEUSER
		            ps.setString(44, null);                                                         			//ADDELETEDATE
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);                  											//ADMMCU
		            ps.setString(2, machinename);                                         			    		//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "ATT");                                                         			//ADYQ10DECD
		            ps.setString(5, "S");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);                 							//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getTime());                                           			//ADUPMT
		            ps.setString(27, "0");                                                          			//ADSOS
		            ps.setString(28, machinedescription);                       								//ADALPH
		            ps.setString(29, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
		            ps.setString(30, machinedefaultdept);                       								//ADDEPT
		            ps.setString(31, workorderjob);                         									//ADPRJM
		            ps.setString(32, workorderitem);                        									//ADLITM
		            ps.setString(33, workorderitemdescription);             									//ADDSC1
		            ps.setString(34, phasedescription);                   										//ADOPSQDSC1
		            ps.setString(35, workcentername);                 											//ADISL
		            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          			//ADINSERTUSER
		            ps.setString(39, null);                                                         			//ADINSERTDATE
		            ps.setString(40, " ");                                                          			//ADUPDATEUSER
		            ps.setString(41, null);                                                         			//ADUPDATEDATE
		            ps.setString(42, " ");                                                          			//ADDELETEUSER
		            ps.setString(43, null);                                                         			//ADDELETEDATE
		            
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStopSetUpMachineTransactionMonoOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
	        	LogWriter.write("[I]       " + "[ S - Fine ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopSetUpMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStopSetUpMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopSetUpMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStartWorkMachineTransactionMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
	            
            		ps.setString(1, workorderbranch);                      										//ADMMCU
		            ps.setString(2, machinename);                                          						//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
		            ps.setString(5, "A");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(27, Settings.getTime());                                           			//ADUPMT
		            ps.setString(28, "0");                                                          			//ADSOS
		            ps.setString(29, machinedescription);                       								//ADALPH
		            ps.setString(30, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
		            ps.setString(31, machinedefaultdept);                       								//ADDEPT
		            ps.setString(32, workorderjob);                         									//ADPRJM
		            ps.setString(33, workorderitem);                        									//ADLITM
		            ps.setString(34, workorderitemdescription);             									//ADDSC1
		            ps.setString(35, phasedescription);                  	 									//ADOPSQDSC1
		            ps.setString(36, workcentername);                 											//ADISL
		            ps.setString(37, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(38, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          			//ADINSERTUSER
		            ps.setString(40, null);                                                         			//ADINSERTDATE
		            ps.setString(41, " ");                                                          			//ADUPDATEUSER
		            ps.setString(42, null);                                                         			//ADUPDATEDATE
		            ps.setString(43, " ");                                                          			//ADDELETEUSER
		            ps.setString(44, null);                                                         			//ADDELETEDATE
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);                      										//ADMMCU
		            ps.setString(2, machinename);                                          						//ADAN8
		            ps.setString(3, workcenterofphase);    														//ADMCU
		            ps.setString(4, "LAV");                                                         			//ADYQ10DECD
		            ps.setString(5, "A");                                                           			//ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      						//ADDOCO
		            ps.setString(7, phasenumber);                                              					//ADOPSQ
		            ps.setString(8, "0");                                                           			//ADSOQS
		            ps.setString(9, "0");                                                           			//ADSOCN
		            ps.setString(10, " ");                                                          			//ADREAC
		            ps.setString(11, " ");                                                          			//ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      			//ADDTUP
		            ps.setString(13, "O");                                                          			//ADYQ10TRCD
		            ps.setString(14, " ");                                                          			//ADYQ10REST
		            ps.setString(15, " ");                                                          			//ADYQ10PR01
		            ps.setString(16, " ");                                                          			//ADYQ10PR02
		            ps.setString(17, "0");                                                          			//ADURDT
		            ps.setString(18, operatoraggregatedtoworkcenter);       									//ADURAB
		            ps.setString(19, "0");                                                          			//ADURAT
		            ps.setString(20, " ");                                                          			//ADURCD
		            ps.setString(21, "M");                                                          			//ADURRF
		            ps.setString(22, mesprogram);                                     							//ADPID
		            ps.setString(23, mesjob);                                         							//ADJOBN
		            ps.setString(24, mesuser);                              									//ADUSER
		            ps.setString(25, Settings.getDate());                                           			//ADUPMJ
		            ps.setString(26, Settings.getTime());                                           			//ADUPMT
		            ps.setString(27, "0");                                                          			//ADSOS
		            ps.setString(28, machinedescription);                       								//ADALPH
		            ps.setString(29, operatoraggregatedtoworkcenterdescription);      							//ADALPH1
		            ps.setString(30, machinedefaultdept);                       								//ADDEPT
		            ps.setString(31, workorderjob);                         									//ADPRJM
		            ps.setString(32, workorderitem);                        									//ADLITM
		            ps.setString(33, workorderitemdescription);             									//ADDSC1
		            ps.setString(34, phasedescription);                  	 									//ADOPSQDSC1
		            ps.setString(35, workcentername);                 											//ADISL
		            ps.setString(36, " ");                                                          			//ADPROCESSEDUSER
		            ps.setString(37, null);                                                         			//ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          			//ADINSERTUSER
		            ps.setString(39, null);                                                         			//ADINSERTDATE
		            ps.setString(40, " ");                                                          			//ADUPDATEUSER
		            ps.setString(41, null);                                                         			//ADUPDATEDATE
		            ps.setString(42, " ");                                                          			//ADDELETEUSER
		            ps.setString(43, null);                                                         			//ADDELETEDATE
            		
            	}
            	
	            ps.executeUpdate();
            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStartWorkMachineTransactionMonoOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
	        	LogWriter.write("[I]       " + "[ A - Inizio ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	        	
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartWorkMachineTransactionMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStartWorkMachineTransactionMonoOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartWorkMachineTransactionMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopWorkMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "   
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1   
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM 
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "   
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1   
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM 
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);          										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "LAV");                                                         //ADYQ10DECD
		            ps.setString(5, "S");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getDate());                                           //ADUPMJ
		            ps.setString(27, Settings.getTime());                                           //ADUPMT
		            ps.setString(28, "0");                                                          //ADSOS
		            ps.setString(29, machinedescription);                       					//ADALPH
		            ps.setString(30, machinedescription);                       					//ADALPH1
		            ps.setString(31, machinedefaultdept);                       					//ADDEPT
		            ps.setString(32, workorderjob);                         						//ADPRJM
		            ps.setString(33, workorderitem);                        						//ADLITM
		            ps.setString(34, workorderitemdescription);             						//ADDSC1
		            ps.setString(35, phasedescription);                  							//ADOPSQDSC1
		            ps.setString(36, workcentername);     											//ADISL
		            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(38, null);                                                         //ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          //ADINSERTUSER
		            ps.setString(40, null);                                                         //ADINSERTDATE
		            ps.setString(41, " ");                                                          //ADUPDATEUSER
		            ps.setString(42, null);                                                         //ADUPDATEDATE
		            ps.setString(43, " ");                                                          //ADDELETEUSER
		            ps.setString(44, null);                                                         //ADDELETEDATE
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);          										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "LAV");                                                         //ADYQ10DECD
		            ps.setString(5, "S");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getTime());                                           //ADUPMT
		            ps.setString(27, "0");                                                          //ADSOS
		            ps.setString(28, machinedescription);                       					//ADALPH
		            ps.setString(29, machinedescription);                       					//ADALPH1
		            ps.setString(30, machinedefaultdept);                       					//ADDEPT
		            ps.setString(31, workorderjob);                         						//ADPRJM
		            ps.setString(32, workorderitem);                        						//ADLITM
		            ps.setString(33, workorderitemdescription);             						//ADDSC1
		            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(35, workcentername);     											//ADISL
		            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(37, null);                                                         //ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          //ADINSERTUSER
		            ps.setString(39, null);                                                         //ADINSERTDATE
		            ps.setString(40, " ");                                                          //ADUPDATEUSER
		            ps.setString(41, null);                                                         //ADUPDATEDATE
		            ps.setString(42, " ");                                                          //ADDELETEUSER
		            ps.setString(43, null);                                                         //ADDELETEDATE
            	}
            	
	            ps.executeUpdate();
            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStopWorkMachineTransactionMultiOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
	        	LogWriter.write("[I]       " + "[ S - Fine ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            
            }
                 
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopWorkMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStopWorkMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStartSetUpMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);          										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);   	 										//ADMCU
		            ps.setString(4, "ATT");                                                         //ADYQ10DECD
		            ps.setString(5, "A");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getDate());                                           //ADUPMJ
		            ps.setString(27, Settings.getTime());                                           //ADUPMT
		            ps.setString(28, "0");                                                          //ADSOS
		            ps.setString(29, machinedescription);                       					//ADALPH
		            ps.setString(30, machinedescription);                       					//ADALPH1
		            ps.setString(31, machinedefaultdept);                       					//ADDEPT
		            ps.setString(32, workorderjob);                         						//ADPRJM
		            ps.setString(33, workorderitem);                        						//ADLITM
		            ps.setString(34, workorderitemdescription);             						//ADDSC1
		            ps.setString(35, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(36, workcentername);     											//ADISL
		            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(38, null);                                                         //ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          //ADINSERTUSER
		            ps.setString(40, null);                                                         //ADINSERTDATE
		            ps.setString(41, " ");                                                          //ADUPDATEUSER
		            ps.setString(42, null);                                                         //ADUPDATEDATE
		            ps.setString(43, " ");                                                          //ADDELETEUSER
		            ps.setString(44, null);                                                         //ADDELETEDATE
            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);          										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "ATT");                                                         //ADYQ10DECD
		            ps.setString(5, "A");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getTime());                                           //ADUPMT
		            ps.setString(27, "0");                                                          //ADSOS
		            ps.setString(28, machinedescription);                       					//ADALPH
		            ps.setString(29, machinedescription);                       					//ADALPH1
		            ps.setString(30, machinedefaultdept);                       					//ADDEPT
		            ps.setString(31, workorderjob);                         						//ADPRJM
		            ps.setString(32, workorderitem);                        						//ADLITM
		            ps.setString(33, workorderitemdescription);             						//ADDSC1
		            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(35, workcentername);     											//ADISL
		            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(37, null);                                                         //ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          //ADINSERTUSER
		            ps.setString(39, null);                                                         //ADINSERTDATE
		            ps.setString(40, " ");                                                          //ADUPDATEUSER
		            ps.setString(41, null);                                                         //ADUPDATEDATE
		            ps.setString(42, " ");                                                          //ADDELETEUSER
		            ps.setString(43, null);                                                         //ADDELETEDATE
		            
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStartSetUpMachineTransactionMultiOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
	        	LogWriter.write("[I]       " + "[ A - Inizio ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	        	
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartSetUpMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStartSetUpMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopSetUpMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  			  //01-ADMMCU
    	        + "                        ?,"                                                  						  //02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  			  //03-ADMCU
    	        + "                        ?,"                                                  						  //04-ADYQ10DECD
    	        + "                        ?,"                                                  						  //05-ADYQ10ACCD
    	        + "                        ?,"                                                  						  //06-ADDOCO
    	        + "                        ?,"                                                  						  //07-ADOPSQ
    	        + "                        ?,"                                                  						  //08-ADSOQS
    	        + "                        ?,"                                                  						  //09-ADSOCN
    	        + "                        ?,"                                                  						  //10-ADREAC
    	        + "                        ?,"                                                  						  //11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 						  //12-ADDTUP
    	        + "                        ?,"                                                  						  //13-ADYQ10TRCD
    	        + "                        ?,"                                                  						  //14-ADYQ10REST
    	        + "                        ?,"                                                  						  //15-ADYQ10PR01
    	        + "                        ?,"                                                  						  //16-ADYQ10PR02
    	        + "                        ?,"                                                  						  //17-ADURDT
    	        + "                        ?,"                                                  						  //18-ADURAB
    	        + "                        ?,"                                                  						  //19-ADURAT
    	        + "                        ?,"                                                  						  //20-ADURCD
    	        + "                        ?,"                                                  						  //21-ADURRF
    	        + "                        ?,"                                                  						  //22-ADPID
    	        + "                        ?,"                                                  						  //23-ADJOBN
    	        + "                        ?,"                                                  						  //24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"            //25-ADUPMJ
    	        + "                        ?,"                                                  						  //26-ADUPMT
    	        + "                        ?,"                                                  						  //27-ADSOS
    	        + "                        ?,"                                                  						  //28-ADALPH
    	        + "                        ?,"                                                  						  //29-ADALPH1
    	        + "                        ?,"                                                  						  //30-ADDEPT
    	        + "                        ?,"                                                  						  //31-ADPRJM
    	        + "                        ?,"                                                  						  //32-ADLITM
    	        + "                        ?,"                                                  						  //33-ADDSC1
    	        + "                        ?,"                                                  						  //34-ADOPSQDSC1
    	        + "                        ?,"                                                  						  //35-ADISL
    	        + "                        ?,"                                                  						  //36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  						  //37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  						  //38-ADINSERTUSER
    	        + "                        ?,"                                                  						  //39-ADINSERTDATE
    	        + "                        ?,"                                                  						  //40-ADUPDATEUSER
    	        + "                        ?,"                                                  						  //41-ADUPDATEDATE
    	        + "                        ?,"                                                  						  //42-ADDELETEUSER
    	        + "                        ?)";                                                 						  //43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);         										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "ATT");                                                         //ADYQ10DECD
		            ps.setString(5, "S");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getDate());                                           //ADUPMJ
		            ps.setString(27, Settings.getTime());                                           //ADUPMT
		            ps.setString(28, "0");                                                          //ADSOS
		            ps.setString(29, machinedescription);                       					//ADALPH
		            ps.setString(30, machinedescription);                       					//ADALPH1
		            ps.setString(31, machinedefaultdept);                       					//ADDEPT
		            ps.setString(32, workorderjob);                         						//ADPRJM
		            ps.setString(33, workorderitem);                        						//ADLITM
		            ps.setString(34, workorderitemdescription);             						//ADDSC1
		            ps.setString(35, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(36, workcentername);     											//ADISL
		            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(38, null);                                                         //ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          //ADINSERTUSER
		            ps.setString(40, null);                                                         //ADINSERTDATE
		            ps.setString(41, " ");                                                          //ADUPDATEUSER
		            ps.setString(42, null);                                                         //ADUPDATEDATE
		            ps.setString(43, " ");                                                          //ADDELETEUSER
		            ps.setString(44, null);                                                         //ADDELETEDATE
            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);         										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "ATT");                                                         //ADYQ10DECD
		            ps.setString(5, "S");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getTime());                                           //ADUPMT
		            ps.setString(27, "0");                                                          //ADSOS
		            ps.setString(28, machinedescription);                       					//ADALPH
		            ps.setString(29, machinedescription);                       					//ADALPH1
		            ps.setString(30, machinedefaultdept);                       					//ADDEPT
		            ps.setString(31, workorderjob);                        							//ADPRJM
		            ps.setString(32, workorderitem);                       							//ADLITM
		            ps.setString(33, workorderitemdescription);             						//ADDSC1
		            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(35, workcentername);     											//ADISL
		            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(37, null);                                                         //ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          //ADINSERTUSER
		            ps.setString(39, null);                                                         //ADINSERTDATE
		            ps.setString(40, " ");                                                          //ADUPDATEUSER
		            ps.setString(41, null);                                                         //ADUPDATEDATE
		            ps.setString(42, " ");                                                          //ADDELETEUSER
		            ps.setString(43, null);                                                         //ADDELETEDATE
		            
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStopSetUpMachineTransactionMultiOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ ATT - Attrezzaggio ]");
	        	LogWriter.write("[I]       " + "[ S - Fine ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopSetUpMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ ATT - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ S - Fine ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStopSetUpMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStopSetUpMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStartWorkMachineTransactionMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "INSERT INTO F55FQ10001 (ADMMCU,"
    	        + "                        ADAN8,"
    	        + "                        ADMCU,"
    	        + "                        ADYQ10DECD,"
    	        + "                        ADYQ10ACCD,"
    	        + "                        ADDOCO,"
    	        + "                        ADOPSQ,"
    	        + "                        ADSOQS,"
    	        + "                        ADSOCN,"
    	        + "                        ADREAC,"
    	        + "                        ADYQ10DERN,"
    	        + "                        ADDTUP,"
    	        + "                        ADYQ10TRCD,"
    	        + "                        ADYQ10REST,"
    	        + "                        ADYQ10PR01,"
    	        + "                        ADYQ10PR02,"
    	        + "                        ADURDT,"
    	        + "                        ADURAB,"
    	        + "                        ADURAT,"
    	        + "                        ADURCD,"
    	        + "                        ADURRF,"
    	        + "                        ADPID,"
    	        + "                        ADJOBN,"
    	        + "                        ADUSER,"
    	        + "                        ADUPMJ,"
    	        + "                        ADUPMT,"
    	        + "                        ADSOS,"    
    	        + "                        ADALPH,"
    	        + "                        ADALPH1,"
    	        + "                        ADDEPT,"
    	        + "                        ADPRJM,"
    	        + "                        ADLITM,"
    	        + "                        ADDSC1,"
    	        + "                        ADOPSQDSC1,"
    	        + "                        ADISL,"
    	        + "                        ADPROCESSEDUSER,"
    	        + "                        ADPROCESSEDDATE,"
    	        + "                        ADINSERTUSER,"
    	        + "                        ADINSERTDATE,"
    	        + "                        ADUPDATEUSER,"
    	        + "                        ADUPDATEDATE,"
    	        + "                        ADDELETEUSER,"
    	        + "                        ADDELETEDATE) "    
    	        + "                VALUES (LPAD(?,12,' '),"                                                  				//01-ADMMCU
    	        + "                        ?,"                                                  							//02-ADAN8
    	        + "                        LPAD(?,12,' '),"                                                  				//03-ADMCU
    	        + "                        ?,"                                                  							//04-ADYQ10DECD
    	        + "                        ?,"                                                  							//05-ADYQ10ACCD
    	        + "                        ?,"                                                  							//06-ADDOCO
    	        + "                        ?,"                                                  							//07-ADOPSQ
    	        + "                        ?,"                                                  							//08-ADSOQS
    	        + "                        ?,"                                                  							//09-ADSOCN
    	        + "                        ?,"                                                  							//10-ADREAC
    	        + "                        ?,"                                                  							//11-ADYQ10DERN
    	        + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                 							//12-ADDTUP
    	        + "                        ?,"                                                  							//13-ADYQ10TRCD
    	        + "                        ?,"                                                  							//14-ADYQ10REST
    	        + "                        ?,"                                                  							//15-ADYQ10PR01
    	        + "                        ?,"                                                  							//16-ADYQ10PR02
    	        + "                        ?,"                                                  							//17-ADURDT
    	        + "                        ?,"                                                  							//18-ADURAB
    	        + "                        ?,"                                                  							//19-ADURAT
    	        + "                        ?,"                                                  							//20-ADURCD
    	        + "                        ?,"                                                  							//21-ADURRF
    	        + "                        ?,"                                                  							//22-ADPID
    	        + "                        ?,"                                                  							//23-ADJOBN
    	        + "                        ?,"                                                  							//24-ADUSER
    	        + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"              //25-ADUPMJ
    	        + "                        ?,"                                                  							//26-ADUPMT
    	        + "                        ?,"                                                  							//27-ADSOS
    	        + "                        ?,"                                                  							//28-ADALPH
    	        + "                        ?,"                                                  							//29-ADALPH1
    	        + "                        ?,"                                                  							//30-ADDEPT
    	        + "                        ?,"                                                  							//31-ADPRJM
    	        + "                        ?,"                                                  							//32-ADLITM
    	        + "                        ?,"                                                  							//33-ADDSC1
    	        + "                        ?,"                                                  							//34-ADOPSQDSC1
    	        + "                        ?,"                                                  							//35-ADISL
    	        + "                        ?,"                                                  							//36-ADPROCESSEDUSER
    	        + "                        ?,"                                                  							//37-ADPROCESSEDDATE
    	        + "                        ?,"                                                  							//38-ADINSERTUSER
    	        + "                        ?,"                                                  							//39-ADINSERTDATE
    	        + "                        ?,"                                                  							//40-ADUPDATEUSER
    	        + "                        ?,"                                                  							//41-ADUPDATEDATE
    	        + "                        ?,"                                                  							//42-ADDELETEUSER
    	        + "                        ?)";                                                 							//43-ADDELETEDATE
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql = "INSERT INTO F55FQ10001 (ADUKID,"
	            + "                        ADMMCU,"
	            + "                        ADAN8,"
	            + "                        ADMCU,"
	            + "                        ADYQ10DECD,"
	            + "                        ADYQ10ACCD,"
	            + "                        ADDOCO,"
	            + "                        ADOPSQ,"
	            + "                        ADSOQS,"
	            + "                        ADSOCN,"
	            + "                        ADREAC,"
	            + "                        ADYQ10DERN,"
	            + "                        ADDTUP,"
	            + "                        ADYQ10TRCD,"
	            + "                        ADYQ10REST,"
	            + "                        ADYQ10PR01,"
	            + "                        ADYQ10PR02,"
	            + "                        ADURDT,"
	            + "                        ADURAB,"
	            + "                        ADURAT,"
	            + "                        ADURCD,"
	            + "                        ADURRF,"
	            + "                        ADPID,"
	            + "                        ADJOBN,"
	            + "                        ADUSER,"
	            + "                        ADUPMJ,"
	            + "                        ADUPMT,"
	            + "                        ADSOS,"    
	            + "                        ADALPH,"
	            + "                        ADALPH1,"
	            + "                        ADDEPT,"
	            + "                        ADPRJM,"
	            + "                        ADLITM,"
	            + "                        ADDSC1,"
	            + "                        ADOPSQDSC1,"
	            + "                        ADISL,"
	            + "                        ADPROCESSEDUSER,"
	            + "                        ADPROCESSEDDATE,"
	            + "                        ADINSERTUSER,"
	            + "                        ADINSERTDATE,"
	            + "                        ADUPDATEUSER,"
	            + "                        ADUPDATEDATE,"
	            + "                        ADDELETEUSER,"
	            + "                        ADDELETEDATE) "    
	            + "                VALUES (F55FQ10001SEQ.NEXTVAL,"
	            + "                        LPAD(?, 12),"                                        //01-ADMMCU
	            + "                        ?,"                                                  //02-ADAN8
	            + "                        LPAD(?, 12),"                                        //03-ADMCU
	            + "                        ?,"                                                  //04-ADYQ10DECD
	            + "                        ?,"                                                  //05-ADYQ10ACCD
	            + "                        ?,"                                                  //06-ADDOCO
	            + "                        ?,"                                                  //07-ADOPSQ
	            + "                        ?,"                                                  //08-ADSOQS
	            + "                        ?,"                                                  //09-ADSOCN
	            + "                        ?,"                                                  //10-ADREAC
	            + "                        ?,"                                                  //11-ADYQ10DERN
	            + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                 //12-ADDTUP
	            + "                        ?,"                                                  //13-ADYQ10TRCD
	            + "                        ?,"                                                  //14-ADYQ10REST
	            + "                        ?,"                                                  //15-ADYQ10PR01
	            + "                        ?,"                                                  //16-ADYQ10PR02
	            + "                        ?,"                                                  //17-ADURDT
	            + "                        ?,"                                                  //18-ADURAB
	            + "                        ?,"                                                  //19-ADURAT
	            + "                        ?,"                                                  //20-ADURCD
	            + "                        ?,"                                                  //21-ADURRF
	            + "                        ?,"                                                  //22-ADPID
	            + "                        ?,"                                                  //23-ADJOBN
	            + "                        ?,"                                                  //24-ADUSER
	            + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"            //25-ADUPMJ
	            + "                        ?,"                                                  //26-ADUPMT
	            + "                        ?,"                                                  //27-ADSOS
	            + "                        ?,"                                                  //28-ADALPH
	            + "                        ?,"                                                  //29-ADALPH1
	            + "                        ?,"                                                  //30-ADDEPT
	            + "                        ?,"                                                  //31-ADPRJM
	            + "                        ?,"                                                  //32-ADLITM
	            + "                        ?,"                                                  //33-ADDSC1
	            + "                        ?,"                                                  //34-ADOPSQDSC1
	            + "                        ?,"                                                  //35-ADISL
	            + "                        ?,"                                                  //36-ADPROCESSEDUSER
	            + "                        ?,"                                                  //37-ADPROCESSEDDATE
	            + "                        ?,"                                                  //38-ADINSERTUSER
	            + "                        ?,"                                                  //39-ADINSERTDATE
	            + "                        ?,"                                                  //40-ADUPDATEUSER
	            + "                        ?,"                                                  //41-ADUPDATEDATE
	            + "                        ?,"                                                  //42-ADDELETEUSER
	            + "                        ?)";                                                 //43-ADDELETEDATE
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
        	for (String wo : workorders) {
            	
            	WorkOrder workorder = new WorkOrder(wo);
            	
            	workorderbranch = workorder.getWorkOrderBranch(workorder);
            	workordernumber = workorder.getWorkOrder();
            	workorderjob = workorder.getWorkOrderJob(workorder);
            	workorderitem = workorder.getWorkOrderItem(workorder);
            	workorderitemdescription = workorder.getWorkOrderItemDescription(workorder);
            	workcenterofphase =  workorder.getWorkCenterOfPhase(workorder,phase);
            	phasedescription = phase.getPhaseDescription(workorder, phase);
            	
            	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
		            
            		ps.setString(1, workorderbranch);          										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "LAV");                                                         //ADYQ10DECD
		            ps.setString(5, "A");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getDate());                                           //ADUPMJ
		            ps.setString(27, Settings.getTime());                                           //ADUPMT
		            ps.setString(28, "0");                                                          //ADSOS
		            ps.setString(29, machinedescription);                       					//ADALPH
		            ps.setString(30, machinedescription);                       					//ADALPH1
		            ps.setString(31, machinedefaultdept);                       					//ADDEPT
		            ps.setString(32, workorderjob);                         						//ADPRJM
		            ps.setString(33, workorderitem);                        						//ADLITM
		            ps.setString(34, workorderitemdescription);             						//ADDSC1
		            ps.setString(35, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(36, workcentername);     											//ADISL
		            ps.setString(37, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(38, null);                                                         //ADPROCESSEDDATE
		            ps.setString(39, " ");                                                          //ADINSERTUSER
		            ps.setString(40, null);                                                         //ADINSERTDATE
		            ps.setString(41, " ");                                                          //ADUPDATEUSER
		            ps.setString(42, null);                                                         //ADUPDATEDATE
		            ps.setString(43, " ");                                                          //ADDELETEUSER
		            ps.setString(44, null);                                                         //ADDELETEDATE
	        
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);          										//ADMMCU
		            ps.setString(2, machinename);                                          			//ADAN8
		            ps.setString(3, workcenterofphase);    											//ADMCU
		            ps.setString(4, "LAV");                                                         //ADYQ10DECD
		            ps.setString(5, "A");                                                           //ADYQ10ACCD
		            ps.setString(6, workordernumber);                                      			//ADDOCO
		            ps.setString(7, phasenumber);                                              		//ADOPSQ
		            ps.setString(8, "0");                                                           //ADSOQS
		            ps.setString(9, "0");                                                           //ADSOCN
		            ps.setString(10, " ");                                                          //ADREAC
		            ps.setString(11, " ");                                                          //ADYQ10DERN
		            ps.setString(12, Settings.getTimestamp());                                      //ADDTUP
		            ps.setString(13, "O");                                                          //ADYQ10TRCD
		            ps.setString(14, " ");                                                          //ADYQ10REST
		            ps.setString(15, " ");                                                          //ADYQ10PR01
		            ps.setString(16, " ");                                                          //ADYQ10PR02
		            ps.setString(17, "0");                                                          //ADURDT
		            ps.setString(18, machinename);                                         			//ADURAB
		            ps.setString(19, "0");                                                          //ADURAT
		            ps.setString(20, " ");                                                          //ADURCD
		            ps.setString(21, "M");                                                          //ADURRF
		            ps.setString(22, mesprogram);                                     				//ADPID
		            ps.setString(23, mesjob);                                         				//ADJOBN
		            ps.setString(24, mesuser);                              						//ADUSER
		            ps.setString(25, Settings.getDate());                                           //ADUPMJ
		            ps.setString(26, Settings.getTime());                                           //ADUPMT
		            ps.setString(27, "0");                                                          //ADSOS
		            ps.setString(28, machinedescription);                       					//ADALPH
		            ps.setString(29, machinedescription);                       					//ADALPH1
		            ps.setString(30, machinedefaultdept);                       					//ADDEPT
		            ps.setString(31, workorderjob);                         						//ADPRJM
		            ps.setString(32, workorderitem);                        						//ADLITM
		            ps.setString(33, workorderitemdescription);            							//ADDSC1
		            ps.setString(34, phasedescription);                   							//ADOPSQDSC1
		            ps.setString(35, workcentername);     											//ADISL
		            ps.setString(36, " ");                                                          //ADPROCESSEDUSER
		            ps.setString(37, null);                                                         //ADPROCESSEDDATE
		            ps.setString(38, " ");                                                          //ADINSERTUSER
		            ps.setString(39, null);                                                         //ADINSERTDATE
		            ps.setString(40, " ");                                                          //ADUPDATEUSER
		            ps.setString(41, null);                                                         //ADUPDATEDATE
		            ps.setString(42, " ");                                                          //ADDELETEUSER
		            ps.setString(43, null);                                                         //ADDELETEDATE
            		
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Transaction.multipleDoStartWorkMachineTransactionMultiOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10001 ] per risorsa: [ " + machinename  + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ LAV - Lavorazione ]");
	        	LogWriter.write("[I]       " + "[ A - Inizio ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename  + " ]");
	        	LogWriter.write("[I]       " + "[ M - Multi Ordine ]");
	        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderjob + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + workorderitemdescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	        	
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartWorkMachineTransactionMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ LAV - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ A - Inizio ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename  + " ]");
        	LogWriter.write("[E]       " + "[ M - Multi Ordine ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderjob + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + workorderitemdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Transaction.multipleDoStartWorkMachineTransactionMultiOperator() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Transaction.multipleDoStartWorkMachineTransactionMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
}
