package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Presence {
	
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
    private String operatordescription;
    private String operatordefaultdept;
    private String mesprogram;
    private String mesjob;
    private String mesuser;

    public Presence(WorkCenter workcenter, WorkOrder workorder, Machine machine, Operator operator, Branch branch, Phase phase) {
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
    
    public Presence(WorkCenter workcenter, ArrayList<String> workorders, Machine machine, Operator operator, Phase phase) {
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
    
    public Presence(WorkCenter workcenter, Operator operator, Branch branch) {
	// CONTROLLATO    
    	this.branchname = branch.getBranch();
    	this.workcentername = workcenter.getWorkCenterName(workcenter.getWorkCenter());
    	this.operatorname = operator.getOperator();
    	this.operatordescription = operator.getOperatorDescription(operator);
    	this.mesprogram = Settings.getMesProgram();
    	this.mesjob = Settings.getMesJob();
    	this.mesuser = Settings.getMesUser(workcenter);
    	this.operatordefaultdept = workcenter.getWorkCenterDefaultDept(workcenter);
    
    }
    
    public Presence(WorkCenter workcenter, Operator operator) {
    // CONTROLLATO
    	this.workcentername = workcenter.getWorkCenterName(workcenter.getWorkCenter());
    	this.operatorname = operator.getOperator();
    	this.operatordescription = operator.getOperatorDescription(operator);
    
    }
    
//    public void doEnterMachinePresenceMonoOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql1 = "";
//        String sql2 = "";
//
//        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
//        
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//			
//        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//                 + "                        USAN8,"
//                 + "                        USMCU,"
//                 + "                        USYQ10USST,"
//                 + "                        USDOCO,"
//                 + "                        USOPSQ,"
//                 + "                        USDTUP,"
//                 + "                        USURDT,"
//                 + "                        USURAB,"
//                 + "                        USURAT,"
//                 + "                        USURCD,"
//                 + "                        USURRF,"
//                 + "                        USPID,"
//                 + "                        USJOBN,"
//                 + "                        USUSER,"
//                 + "                        USUPMJ,"
//                 + "                        USUPMT,"
//                 + "                        USSOS,"   
//                 + "                        USALPH,"
//                 + "                        USALPH1,"
//                 + "                        USDEPT,"
//                 + "                        USPRJM,"
//                 + "                        USLITM,"
//                 + "                        USDSC1,"
//                 + "                        USOPSQDSC1,"
//                 + "                        USISL) "    
//                 + "                VALUES (LPAD(?,12,' '),"                                                //01-USMMCU
//                 + "                        ?,"                                                 			//02-USAN8
//                 + "                        LPAD(?,12,' '),"                                                //03-USMCU
//                 + "                        ?,"                                                 			//04-USYQ10USST
//                 + "                        ?,"                                                 			//05-USDOCO
//                 + "                        ?,"                                                 			//06-USOPSQ
//                 + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                			//07-USDTUP
//                 + "                        ?,"                                                 			//08-USURDT
//                 + "                        ?,"                                                 			//09-USURAB
//                 + "                        ?,"                                                 			//10-USURAT
//                 + "                        ?,"                                                 			//11-USURCD
//                 + "                        ?,"                                                 			//12-USURRF
//                 + "                        ?,"                                                 			//13-USPID
//                 + "                        ?,"                                                 			//14-USJOBN
//                 + "                        ?,"                                                 			//15-USUSER
//                 + "                        STR_TO_DATE(TO_DATE(?,'%d/%m/%Y')),"           					//16-USUPMJ
//                 + "                        ?,"                                                 			//17-USUPMT
//                 + "                        ?,"                                                				//18-USSOS
//                 + "                        ?,"                                                 			//19-USALPH
//                 + "                        ?,"                                                 			//20-USALPH1
//                 + "                        ?,"                                                 			//21-USDEPT
//                 + "                        ?,"                                                 			//22-USPRJM
//                 + "                        ?,"                                                 			//23-USLITM
//                 + "                        ?,"                                                 			//24-USDSC1
//                 + "                        ?,"                                                 			//25-USOPSQDSC1
//                 + "                        ?)";                                                			//26-USISL
//
//		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//			
//			sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//		         + "                        USAN8,"
//		         + "                        USMCU,"
//		         + "                        USYQ10USST,"
//		         + "                        USDOCO,"
//		         + "                        USOPSQ,"
//		         + "                        USDTUP,"
//		         + "                        USURDT,"
//		         + "                        USURAB,"
//		         + "                        USURAT,"
//		         + "                        USURCD,"
//		         + "                        USURRF,"
//		         + "                        USPID,"
//		         + "                        USJOBN,"
//		         + "                        USUSER,"
//		         + "                        USUPMJ,"
//		         + "                        USUPMT,"
//		         + "                        USSOS,"   
//		         + "                        USALPH,"
//		         + "                        USALPH1,"
//		         + "                        USDEPT,"
//		         + "                        USPRJM,"
//		         + "                        USLITM,"
//		         + "                        USDSC1,"
//		         + "                        USOPSQDSC1,"
//		         + "                        USISL) "    
//		         + "                VALUES (LPAD(?, 12),"                                                	//01-USMMCU
//		         + "                        ?,"                                                 			//02-USAN8
//		         + "                        LPAD(?, 12),"                                                	//03-USMCU
//		         + "                        ?,"                                                 			//04-USYQ10USST
//		         + "                        ?,"                                                 			//05-USDOCO
//		         + "                        ?,"                                                 			//06-USOPSQ
//		         + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                			//07-USDTUP
//		         + "                        ?,"                                                 			//08-USURDT
//		         + "                        ?,"                                                 			//09-USURAB
//		         + "                        ?,"                                                 			//10-USURAT
//		         + "                        ?,"                                                 			//11-USURCD
//		         + "                        ?,"                                                 			//12-USURRF
//		         + "                        ?,"                                                 			//13-USPID
//		         + "                        ?,"                                                 			//14-USJOBN
//		         + "                        ?,"                                                 			//15-USUSER
//		         + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           			//16-USUPMJ
//		         + "                        ?,"                                                 			//17-USUPMT
//		         + "                        ?,"                                                				//18-USSOS
//		         + "                        ?,"                                                 			//19-USALPH
//		         + "                        ?,"                                                 			//20-USALPH1
//		         + "                        ?,"                                                 			//21-USDEPT
//		         + "                        ?,"                                                 			//22-USPRJM
//		         + "                        ?,"                                                 			//23-USLITM
//		         + "                        ?,"                                                 			//24-USDSC1
//		         + "                        ?,"                                                 			//25-USOPSQDSC1
//		         + "                        ?)";                                                			//26-USISL
//
//		}
//  
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql1);
//
//            ps.setString(1, machinename);
//             
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doEnterMachinePresenceMonoOperator() ]"); 
//            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMonoOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql1);
//        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doEnterMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMonoOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql2);
//
//            ps.setString(1, branchname);                                								//USMMCU
//            ps.setString(2, machinename);                                          						//USAN8
//            ps.setString(3, " ");                                                           			//USMCU
//            ps.setString(4, "2");                                                           			//USYQ10USST
//            ps.setString(5, "0");                                                           			//USDOCO
//            ps.setString(6, "0");                                                           			//USOPSQ
//            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
//            ps.setString(8, "0");                                                           			//USURDT
//            ps.setString(9, operatoraggregatedtoworkcenter);        									//USURAB
//            ps.setString(10, "0");                                                          			//USURAT
//            ps.setString(11, " ");                                                          			//USURCD
//            ps.setString(12, " ");                                                          			//USURRF
//            ps.setString(13, mesprogram);                                     							//USPID
//            ps.setString(14, mesjob);                                         							//USJOBN
//            ps.setString(15, mesuser);                              									//USUSER
//            ps.setString(16, Settings.getTimestamp());                                      			//USUPMJ
//            ps.setString(17, Settings.getTime());                                           			//USUPMT
//            ps.setString(18, "0");                                                         				//USSOS
//            ps.setString(19, machinedescription);                       								//USALPH
//            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
//            ps.setString(21, machinedefaultdept);                       								//USDEPT
//            ps.setString(22, "0");                                                          			//USPRJM
//            ps.setString(23, " ");                                                          			//USLITM
//            ps.setString(24, " ");                                                          			//USDSC1
//            ps.setString(25, " ");                                                          			//USOPSQDSC1
//            ps.setString(26, workcentername);                											//USISL
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doEnterMachinePresenceMonoOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
//            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
//        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
//        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
//        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMonoOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql2);
//        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
//        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
//        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doEnterMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMonoOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
    
//    public void doEnterMachinePresenceMultiOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql1 = "";
//        String sql2 = "";
//
//        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
//        
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//                 + "                        USAN8,"
//                 + "                        USMCU,"
//                 + "                        USYQ10USST,"
//                 + "                        USDOCO,"
//                 + "                        USOPSQ,"
//                 + "                        USDTUP,"
//                 + "                        USURDT,"
//                 + "                        USURAB,"
//                 + "                        USURAT,"
//                 + "                        USURCD,"
//                 + "                        USURRF,"
//                 + "                        USPID,"
//                 + "                        USJOBN,"
//                 + "                        USUSER,"
//                 + "                        USUPMJ,"
//                 + "                        USUPMT,"
//                 + "                        USSOS,"   
//                 + "                        USALPH,"
//                 + "                        USALPH1,"
//                 + "                        USDEPT,"
//                 + "                        USPRJM,"
//                 + "                        USLITM,"
//                 + "                        USDSC1,"
//                 + "                        USOPSQDSC1,"
//                 + "                        USISL) "    
//                 + "                VALUES (LPAD(?,12,' '),"                                                					//01-USMMCU
//                 + "                        ?,"                                                 								//02-USAN8
//                 + "                        LPAD(?,12,' '),"                                                 					//03-USMCU
//                 + "                        ?,"                                                 								//04-USYQ10USST
//                 + "                        ?,"                                                 								//05-USDOCO
//                 + "                        ?,"                                                 								//06-USOPSQ
//                 + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                								//07-USDTUP
//                 + "                        ?,"                                                 								//08-USURDT
//                 + "                        ?,"                                                 								//09-USURAB
//                 + "                        ?,"                                                 								//10-USURAT
//                 + "                        ?,"                                                 								//11-USURCD
//                 + "                        ?,"                                                 								//12-USURRF
//                 + "                        ?,"                                                 								//13-USPID
//                 + "                        ?,"                                                 								//14-USJOBN
//                 + "                        ?,"                                                 								//15-USUSER
//                 + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"           		//16-USUPMJ
//                 + "                        ?,"                                                 								//17-USUPMT
//                 + "                        ?,"                                                 								//18-USSOS
//                 + "                        ?,"                                                 								//19-USALPH
//                 + "                        ?,"                                                 								//20-USALPH1
//                 + "                        ?,"                                                 								//21-USDEPT
//                 + "                        ?,"                                                 								//22-USPRJM
//                 + "                        ?,"                                                 								//23-USLITM
//                 + "                        ?,"                                                 								//24-USDSC1
//                 + "                        ?,"                                                 								//25-USOPSQDSC1
//                 + "                        ?)";                                                								//26-USISL
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        	
//        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//                 + "                        USAN8,"
//                 + "                        USMCU,"
//                 + "                        USYQ10USST,"
//                 + "                        USDOCO,"
//                 + "                        USOPSQ,"
//                 + "                        USDTUP,"
//                 + "                        USURDT,"
//                 + "                        USURAB,"
//                 + "                        USURAT,"
//                 + "                        USURCD,"
//                 + "                        USURRF,"
//                 + "                        USPID,"
//                 + "                        USJOBN,"
//                 + "                        USUSER,"
//                 + "                        USUPMJ,"
//                 + "                        USUPMT,"
//                 + "                        USSOS,"   
//                 + "                        USALPH,"
//                 + "                        USALPH1,"
//                 + "                        USDEPT,"
//                 + "                        USPRJM,"
//                 + "                        USLITM,"
//                 + "                        USDSC1,"
//                 + "                        USOPSQDSC1,"
//                 + "                        USISL) "    
//                 + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
//                 + "                        ?,"                                                 //02-USAN8
//                 + "                        LPAD(?, 12),"                                       //03-USMCU
//                 + "                        ?,"                                                 //04-USYQ10USST
//                 + "                        ?,"                                                 //05-USDOCO
//                 + "                        ?,"                                                 //06-USOPSQ
//                 + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
//                 + "                        ?,"                                                 //08-USURDT
//                 + "                        ?,"                                                 //09-USURAB
//                 + "                        ?,"                                                 //10-USURAT
//                 + "                        ?,"                                                 //11-USURCD
//                 + "                        ?,"                                                 //12-USURRF
//                 + "                        ?,"                                                 //13-USPID
//                 + "                        ?,"                                                 //14-USJOBN
//                 + "                        ?,"                                                 //15-USUSER
//                 + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
//                 + "                        ?,"                                                 //17-USUPMT
//                 + "                        ?,"                                                 //18-USSOS
//                 + "                        ?,"                                                 //19-USALPH
//                 + "                        ?,"                                                 //20-USALPH1
//                 + "                        ?,"                                                 //21-USDEPT
//                 + "                        ?,"                                                 //22-USPRJM
//                 + "                        ?,"                                                 //23-USLITM
//                 + "                        ?,"                                                 //24-USDSC1
//                 + "                        ?,"                                                 //25-USOPSQDSC1
//                 + "                        ?)";                                                //26-USISL
//
//        }
//                
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql1);
//   
//            ps.setString(1, operatorname);
//             
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doEnterMachinePresenceMultiOperator() ]"); 
//            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMultiOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql1);
//        	LogWriter.write("[E] Dati: " + "[ " + operatorname + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doEnterMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMultiOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//           
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql2);
// 
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//            	
//	            ps.setString(1, branchname);                                					//USMMCU
//	            ps.setString(2, operatorname);                                        			//USAN8
//	            ps.setString(3, " ");                                                           //USMCU
//	            ps.setString(4, "2");                                                           //USYQ10USST
//	            ps.setString(5, "0");                                                           //USDOCO
//	            ps.setString(6, "0");                                                           //USOPSQ
//	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
//	            ps.setString(8, "0");                                                           //USURDT
//	            ps.setString(9, operatorname);                                        			//USURAB
//	            ps.setString(10, "0");                                                          //USURAT
//	            ps.setString(11, " ");                                                          //USURCD
//	            ps.setString(12, " ");                                                          //USURRF
//	            ps.setString(13, mesprogram);                                     				//USPID
//	            ps.setString(14, mesjob);                                         				//USJOBN
//	            ps.setString(15, mesuser);                              						//USUSER
//	            ps.setString(16, Settings.getTimestamp());                                      //USUPMJ
//	            ps.setString(17, Settings.getTime());                                           //USUPMT
//	            ps.setString(18, Settings.getTime());                                           //USUPMT
//	            ps.setString(19, "0");                                                          //USSOS
//	            ps.setString(20, operatordescription);                    						//USALPH
//	            ps.setString(21, operatordescription);                    						//USALPH1
//	            ps.setString(22, operatordefaultdept);                    						//USDEPT
//	            ps.setString(23, "0");                                                          //USPRJM
//	            ps.setString(24, " ");                                                          //USLITM
//	            ps.setString(25, " ");                                                          //USDSC1
//	            ps.setString(26, " ");                                                          //USOPSQDSC1
//	            ps.setString(27, workcentername);     											//USISL
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            
//            	ps.setString(1, branchname);                                					//USMMCU
//	            ps.setString(2, operatorname);                                        			//USAN8
//	            ps.setString(3, " ");                                                           //USMCU
//	            ps.setString(4, "2");                                                           //USYQ10USST
//	            ps.setString(5, "0");                                                           //USDOCO
//	            ps.setString(6, "0");                                                           //USOPSQ
//	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
//	            ps.setString(8, "0");                                                           //USURDT
//	            ps.setString(9, operatorname);                                        			//USURAB
//	            ps.setString(10, "0");                                                          //USURAT
//	            ps.setString(11, " ");                                                          //USURCD
//	            ps.setString(12, " ");                                                          //USURRF
//	            ps.setString(13, mesprogram);                                     				//USPID
//	            ps.setString(14, mesjob);                                         				//USJOBN
//	            ps.setString(15, mesuser);                              						//USUSER
//	            ps.setString(16, Settings.getTimestamp());                                      //USUPMJ
//	            ps.setString(17, Settings.getTime());                                           //USUPMT
//	            ps.setString(18, "0");                                                          //USSOS
//	            ps.setString(19, operatordescription);                    						//USALPH
//	            ps.setString(20, operatordescription);                    						//USALPH1
//	            ps.setString(21, operatordefaultdept);                    						//USDEPT
//	            ps.setString(22, "0");                                                          //USPRJM
//	            ps.setString(23, " ");                                                          //USLITM
//	            ps.setString(24, " ");                                                          //USDSC1
//	            ps.setString(25, " ");                                                          //USOPSQDSC1
//	            ps.setString(26, workcentername);     											//USISL
//            	
//            }	
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doEnterMachinePresenceMultiOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
//            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
//        	LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMultiOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql2);
//        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doEnterMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMultiOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doEnterMachinePresenceMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
    
//    public void doExitMachinePresenceMonoOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql1 = "";
//        String sql2 = "";
//
//        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
//        
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//   	             + "                        USAN8,"
//   	             + "                        USMCU,"
//   	             + "                        USYQ10USST,"
//   	             + "                        USDOCO,"
//   	             + "                        USOPSQ,"
//   	             + "                        USDTUP,"
//   	             + "                        USURDT,"
//   	             + "                        USURAB,"
//   	             + "                        USURAT,"
//   	             + "                        USURCD,"
//   	             + "                        USURRF,"
//   	             + "                        USPID,"
//   	             + "                        USJOBN,"
//   	             + "                        USUSER,"
//   	             + "                        USUPMJ,"
//   	             + "                        USUPMT,"
//   	             + "                        USSOS,"   
//   	             + "                        USALPH,"
//   	             + "                        USALPH1,"
//   	             + "                        USDEPT,"
//   	             + "                        USPRJM,"
//   	             + "                        USLITM,"
//   	             + "                        USDSC1,"
//   	             + "                        USOPSQDSC1,"
//   	             + "                        USISL) "    
//   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
//   	             + "                        ?,"                                                 							//02-USAN8
//   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
//   	             + "                        ?,"                                                 							//04-USYQ10USST
//   	             + "                        ?,"                                                 							//05-USDOCO
//   	             + "                        ?,"                                                 							//06-USOPSQ
//   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
//   	             + "                        ?,"                                                 							//08-USURDT
//   	             + "                        ?,"                                                 							//09-USURAB
//   	             + "                        ?,"                                                 							//10-USURAT
//   	             + "                        ?,"                                                 							//11-USURCD
//   	             + "                        ?,"                                                 							//12-USURRF
//   	             + "                        ?,"                                                 							//13-USPID
//   	             + "                        ?,"                                                 							//14-USJOBN
//   	             + "                        ?,"                                                 							//15-USUSER
//   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"          	//16-USUPMJ
//   	             + "                        ?,"                                                 							//17-USUPMT
//   	             + "                        ?,"                                                 							//18-USSOS
//   	             + "                        ?,"                                                 							//19-USALPH
//   	             + "                        ?,"                                                 							//20-USALPH1
//   	             + "                        ?,"                                                 							//21-USDEPT
//   	             + "                        ?,"                                                 							//22-USPRJM
//   	             + "                        ?,"                                                 							//23-USLITM
//   	             + "                        ?,"                                                 							//24-USDSC1
//   	             + "                        ?,"                                                 							//25-USOPSQDSC1
//   	             + "                        ?)";                                                							//26-USISL
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        
//	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//	             + "                        USAN8,"
//	             + "                        USMCU,"
//	             + "                        USYQ10USST,"
//	             + "                        USDOCO,"
//	             + "                        USOPSQ,"
//	             + "                        USDTUP,"
//	             + "                        USURDT,"
//	             + "                        USURAB,"
//	             + "                        USURAT,"
//	             + "                        USURCD,"
//	             + "                        USURRF,"
//	             + "                        USPID,"
//	             + "                        USJOBN,"
//	             + "                        USUSER,"
//	             + "                        USUPMJ,"
//	             + "                        USUPMT,"
//	             + "                        USSOS,"   
//	             + "                        USALPH,"
//	             + "                        USALPH1,"
//	             + "                        USDEPT,"
//	             + "                        USPRJM,"
//	             + "                        USLITM,"
//	             + "                        USDSC1,"
//	             + "                        USOPSQDSC1,"
//	             + "                        USISL) "    
//	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
//	             + "                        ?,"                                                 //02-USAN8
//	             + "                        LPAD(?, 12),"                                       //03-USMCU
//	             + "                        ?,"                                                 //04-USYQ10USST
//	             + "                        ?,"                                                 //05-USDOCO
//	             + "                        ?,"                                                 //06-USOPSQ
//	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
//	             + "                        ?,"                                                 //08-USURDT
//	             + "                        ?,"                                                 //09-USURAB
//	             + "                        ?,"                                                 //10-USURAT
//	             + "                        ?,"                                                 //11-USURCD
//	             + "                        ?,"                                                 //12-USURRF
//	             + "                        ?,"                                                 //13-USPID
//	             + "                        ?,"                                                 //14-USJOBN
//	             + "                        ?,"                                                 //15-USUSER
//	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
//	             + "                        ?,"                                                 //17-USUPMT
//	             + "                        ?,"                                                 //18-USSOS
//	             + "                        ?,"                                                 //19-USALPH
//	             + "                        ?,"                                                 //20-USALPH1
//	             + "                        ?,"                                                 //21-USDEPT
//	             + "                        ?,"                                                 //22-USPRJM
//	             + "                        ?,"                                                 //23-USLITM
//	             + "                        ?,"                                                 //24-USDSC1
//	             + "                        ?,"                                                 //25-USOPSQDSC1
//	             + "                        ?)";                                                //26-USISL
//        
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql1);
//            
//            ps.setString(1, machinename);
//                 
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doExitMachinePresenceMonoOperator() ]"); 
//            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//          
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMonoOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql1);
//        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doExitMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMonoOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql2);
//
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//            	
//	            ps.setString(1, branchname);                                								//USMMCU
//	            ps.setString(2, machinename);                                          						//USAN8
//	            ps.setString(3, " ");                                                           			//USMCU
//	            ps.setString(4, "1");                                                           			//USYQ10USST
//	            ps.setString(5, "0");                                                           			//USDOCO
//	            ps.setString(6, "0");                                                           			//USOPSQ
//	            ps.setString(7, Settings.getTimestamp());                                      				//USDTUP
//	            ps.setString(8, "0");                                                           			//USURDT
//	            ps.setString(9, operatoraggregatedtoworkcenter);        									//USURAB
//	            ps.setString(10, "0");                                                          			//USURAT
//	            ps.setString(11, " ");                                                          			//USURCD
//	            ps.setString(12, " ");                                                          			//USURRF
//	            ps.setString(13, mesprogram);                                     							//USPID
//	            ps.setString(14, mesjob);                                         							//USJOBN
//	            ps.setString(15, mesuser);                              									//USUSER
//	            ps.setString(16, Settings.getTimestamp());                                      			//USUPMJ
//	            ps.setString(17, Settings.getTime());                                           			//USUPMT
//	            ps.setString(18, Settings.getTime());                                           			//USUPMT
//	            ps.setString(19, "0");                                                          			//USSOS
//	            ps.setString(20, machinedescription);                       								//USALPH
//	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
//	            ps.setString(22, machinedefaultdept);                       								//USDEPT
//	            ps.setString(23, "0");                                                          			//USPRJM
//	            ps.setString(24, " ");                                                          			//USLITM
//	            ps.setString(25, " ");                                                          			//USDSC1
//	            ps.setString(26, " ");                                                          			//USOPSQDSC1
//	            ps.setString(27, workcentername);                 											//USISL
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            	
//            	ps.setString(1, branchname);                                								//USMMCU
//	            ps.setString(2, machinename);                                          						//USAN8
//	            ps.setString(3, " ");                                                           			//USMCU
//	            ps.setString(4, "1");                                                           			//USYQ10USST
//	            ps.setString(5, "0");                                                           			//USDOCO
//	            ps.setString(6, "0");                                                           			//USOPSQ
//	            ps.setString(7, Settings.getTimestamp());                                      				//USDTUP
//	            ps.setString(8, "0");                                                           			//USURDT
//	            ps.setString(9, operatoraggregatedtoworkcenter);        									//USURAB
//	            ps.setString(10, "0");                                                          			//USURAT
//	            ps.setString(11, " ");                                                          			//USURCD
//	            ps.setString(12, " ");                                                          			//USURRF
//	            ps.setString(13, mesprogram);                                     							//USPID
//	            ps.setString(14, mesjob);                                         							//USJOBN
//	            ps.setString(15, mesuser);                              									//USUSER
//	            ps.setString(16, Settings.getTimestamp());                                      			//USUPMJ
//	            ps.setString(17, Settings.getTime());                                           			//USUPMT
//	            ps.setString(18, "0");                                                          			//USSOS
//	            ps.setString(19, machinedescription);                       								//USALPH
//	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
//	            ps.setString(21, machinedefaultdept);                       								//USDEPT
//	            ps.setString(22, "0");                                                          			//USPRJM
//	            ps.setString(23, " ");                                                          			//USLITM
//	            ps.setString(24, " ");                                                          			//USDSC1
//	            ps.setString(25, " ");                                                          			//USOPSQDSC1
//	            ps.setString(26, workcentername);                 											//USISL
//	            
//            }
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doExitMachinePresenceMonoOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
//            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
//        	LogWriter.write("[I]       " + "[ 1 - Uscita ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
//        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMonoOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql2);
//        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
//        	LogWriter.write("[E]       " + "[ 1 - Uscita ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
//        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doExitMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMonoOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMonoOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
    
//    public void doExitMachinePresenceMultiOperator() {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        String sql1 = "";
//        String sql2 = "";
//
//        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
//        
//        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//
//        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//   	             + "                        USAN8,"
//   	             + "                        USMCU,"
//   	             + "                        USYQ10USST,"
//   	             + "                        USDOCO,"
//   	             + "                        USOPSQ,"
//   	             + "                        USDTUP,"
//   	             + "                        USURDT,"
//   	             + "                        USURAB,"
//   	             + "                        USURAT,"
//   	             + "                        USURCD,"
//   	             + "                        USURRF,"
//   	             + "                        USPID,"
//   	             + "                        USJOBN,"
//   	             + "                        USUSER,"
//   	             + "                        USUPMJ,"
//   	             + "                        USUPMT,"
//   	             + "                        USSOS,"   
//   	             + "                        USALPH,"
//   	             + "                        USALPH1,"
//   	             + "                        USDEPT,"
//   	             + "                        USPRJM,"
//   	             + "                        USLITM,"
//   	             + "                        USDSC1,"
//   	             + "                        USOPSQDSC1,"
//   	             + "                        USISL) "    
//   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
//   	             + "                        ?,"                                                 							//02-USAN8
//   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
//   	             + "                        ?,"                                                 							//04-USYQ10USST
//   	             + "                        ?,"                                                 							//05-USDOCO
//   	             + "                        ?,"                                                 							//06-USOPSQ
//   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
//   	             + "                        ?,"                                                 							//08-USURDT
//   	             + "                        ?,"                                                 							//09-USURAB
//   	             + "                        ?,"                                                 							//10-USURAT
//   	             + "                        ?,"                                                 							//11-USURCD
//   	             + "                        ?,"                                                 							//12-USURRF
//   	             + "                        ?,"                                                 							//13-USPID
//   	             + "                        ?,"                                                 							//14-USJOBN
//   	             + "                        ?,"                                                 							//15-USUSER
//   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
//   	             + "                        ?,"                                                 							//17-USUPMT
//   	             + "                        ?,"                                                 							//18-USSOS
//   	             + "                        ?,"                                                 							//19-USALPH
//   	             + "                        ?,"                                                 							//20-USALPH1
//   	             + "                        ?,"                                                 							//21-USDEPT
//   	             + "                        ?,"                                                 							//22-USPRJM
//   	             + "                        ?,"                                                 							//23-USLITM
//   	             + "                        ?,"                                                 							//24-USDSC1
//   	             + "                        ?,"                                                 							//25-USOPSQDSC1
//   	             + "                        ?)";                                                							//26-USISL
//        	
//        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//        
//	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
//	             + "                        USAN8,"
//	             + "                        USMCU,"
//	             + "                        USYQ10USST,"
//	             + "                        USDOCO,"
//	             + "                        USOPSQ,"
//	             + "                        USDTUP,"
//	             + "                        USURDT,"
//	             + "                        USURAB,"
//	             + "                        USURAT,"
//	             + "                        USURCD,"
//	             + "                        USURRF,"
//	             + "                        USPID,"
//	             + "                        USJOBN,"
//	             + "                        USUSER,"
//	             + "                        USUPMJ,"
//	             + "                        USUPMT,"
//	             + "                        USSOS,"   
//	             + "                        USALPH,"
//	             + "                        USALPH1,"
//	             + "                        USDEPT,"
//	             + "                        USPRJM,"
//	             + "                        USLITM,"
//	             + "                        USDSC1,"
//	             + "                        USOPSQDSC1,"
//	             + "                        USISL) "    
//	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
//	             + "                        ?,"                                                 //02-USAN8
//	             + "                        LPAD(?, 12),"                                       //03-USMCU
//	             + "                        ?,"                                                 //04-USYQ10USST
//	             + "                        ?,"                                                 //05-USDOCO
//	             + "                        ?,"                                                 //06-USOPSQ
//	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
//	             + "                        ?,"                                                 //08-USURDT
//	             + "                        ?,"                                                 //09-USURAB
//	             + "                        ?,"                                                 //10-USURAT
//	             + "                        ?,"                                                 //11-USURCD
//	             + "                        ?,"                                                 //12-USURRF
//	             + "                        ?,"                                                 //13-USPID
//	             + "                        ?,"                                                 //14-USJOBN
//	             + "                        ?,"                                                 //15-USUSER
//	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
//	             + "                        ?,"                                                 //17-USUPMT
//	             + "                        ?,"                                                 //18-USSOS
//	             + "                        ?,"                                                 //19-USALPH
//	             + "                        ?,"                                                 //20-USALPH1
//	             + "                        ?,"                                                 //21-USDEPT
//	             + "                        ?,"                                                 //22-USPRJM
//	             + "                        ?,"                                                 //23-USLITM
//	             + "                        ?,"                                                 //24-USDSC1
//	             + "                        ?,"                                                 //25-USOPSQDSC1
//	             + "                        ?)";                                                //26-USISL
//        }
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql1);
//
//            ps.setString(1, operatorname);
//             
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doExitMachinePresenceMultiOperator() ]"); 
//            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMultiOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql1);
//        	LogWriter.write("[E] Dati: " + "[ " + operatorname + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doExitMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMultiOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql2);
//            
//            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
//            	
//	            ps.setString(1, branchname);                                					//USMMCU
//	            ps.setString(2, operatorname);                                        			//USAN8
//	            ps.setString(3, " ");                                                           //USMCU
//	            ps.setString(4, "1");                                                           //USYQ10USST
//	            ps.setString(5, "0");                                                           //USDOCO
//	            ps.setString(6, "0");                                                           //USOPSQ
//	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
//	            ps.setString(8, "0");                                                           //USURDT
//	            ps.setString(9, operatorname);                                        			//USURAB
//	            ps.setString(10, "0");                                                          //USURAT
//	            ps.setString(11, " ");                                                          //USURCD
//	            ps.setString(12, " ");                                                          //USURRF
//	            ps.setString(13, mesprogram);                                     				//USPID
//	            ps.setString(14, mesjob);                                         				//USJOBN
//	            ps.setString(15, mesuser);                              						//USUSER
//	            ps.setString(16, Settings.getTimestamp());                                      //USUPMJ
//	            ps.setString(17, Settings.getTime());                                           //USUPMT
//	            ps.setString(18, Settings.getTime());                                           //USUPMT
//	            ps.setString(19, "0");                                                          //USSOS
//	            ps.setString(20, operatordescription);                    						//USALPH
//	            ps.setString(21, operatordescription);                    						//USALPH1
//	            ps.setString(22, machinedefaultdept);                      						//USDEPT
//	            ps.setString(23, "0");                                                          //USPRJM
//	            ps.setString(24, " ");                                                          //USLITM
//	            ps.setString(25, " ");                                                          //USDSC1
//	            ps.setString(26, " ");                                                          //USOPSQDSC1
//	            ps.setString(27, workcentername);     											//USISL
//            
//            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
//            	
//            	ps.setString(1, branchname);                                					//USMMCU
//	            ps.setString(2, operatorname);                                        			//USAN8
//	            ps.setString(3, " ");                                                           //USMCU
//	            ps.setString(4, "1");                                                           //USYQ10USST
//	            ps.setString(5, "0");                                                           //USDOCO
//	            ps.setString(6, "0");                                                           //USOPSQ
//	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
//	            ps.setString(8, "0");                                                           //USURDT
//	            ps.setString(9, operatorname);                                        			//USURAB
//	            ps.setString(10, "0");                                                          //USURAT
//	            ps.setString(11, " ");                                                          //USURCD
//	            ps.setString(12, " ");                                                          //USURRF
//	            ps.setString(13, mesprogram);                                     				//USPID
//	            ps.setString(14, mesjob);                                         				//USJOBN
//	            ps.setString(15, mesuser);                              						//USUSER
//	            ps.setString(16, Settings.getTimestamp());                                      //USUPMJ
//	            ps.setString(17, Settings.getTime());                                           //USUPMT
//	            ps.setString(18, "0");                                                          //USSOS
//	            ps.setString(19, operatordescription);                    						//USALPH
//	            ps.setString(20, operatordescription);                    						//USALPH1
//	            ps.setString(21, machinedefaultdept);                       					//USDEPT
//	            ps.setString(22, "0");                                                          //USPRJM
//	            ps.setString(23, " ");                                                          //USLITM
//	            ps.setString(24, " ");                                                          //USDSC1
//	            ps.setString(25, " ");                                                          //USOPSQDSC1
//	            ps.setString(26, workcentername);     											//USISL
//	            
//            }
//            
//            ps.executeUpdate();
//            
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            LogWriter.write("[I] In esecuzione: [ Presence.doExitMachinePresenceMultiOperator() ]"); 
//            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
//            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[I]       " + "[ 1 - Uscita ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
//        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
//            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
//            
//        } catch (SQLException Sqlex) {
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMultiOperator() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql2);
//        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[E]       " + "[ 1 - Uscita ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
//        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
//        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
//        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
//        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Presence.doExitMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMultiOperator() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Presence.doExitMachinePresenceMultiOperator() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//    }
 
    public void doStartSetUpMachinePresenceMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
            
            ps.setString(1, machinename);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
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
            	
	            ps.setString(1, branchname);                                								//USMMCU 
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU                                            
	            ps.setString(4, "2");                                                           			//USYQ10USST                                               
	            ps.setString(5, "0");                                                           			//USDOCO                                               
	            ps.setString(6, "0");                                                           			//USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
	            ps.setString(8, "0");                                                           			//USURDT 
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT                                              
	            ps.setString(11, " ");                                                          			//USURCD                                              
	            ps.setString(12, " ");                                                          			//USURRF                                              
	            ps.setString(13, mesprogram);                                     							//USPID                        
	            ps.setString(14, mesjob);                                         							//USJOBN                           
	            ps.setString(15, mesuser);                             			    						//USUSER                          
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT                
	            ps.setString(23, "0");                                                          			//USPRJM                                             
	            ps.setString(24, " ");                                                          			//USLITM                                             
	            ps.setString(25, " ");                                                          			//USDSC1                                             
	            ps.setString(26, " ");                                                          			//USOPSQDSC1                                            
	            ps.setString(27, workcentername);                 											//USISL  
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                								//USMMCU 
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU                                            
	            ps.setString(4, "2");                                                           			//USYQ10USST                                               
	            ps.setString(5, "0");                                                           			//USDOCO                                               
	            ps.setString(6, "0");                                                           			//USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
	            ps.setString(8, "0");                                                           			//USURDT 
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT                                              
	            ps.setString(11, " ");                                                          			//USURCD                                              
	            ps.setString(12, " ");                                                          			//USURRF                                              
	            ps.setString(13, mesprogram);                                     							//USPID                        
	            ps.setString(14, mesjob);                                         							//USJOBN                           
	            ps.setString(15, mesuser);                             			    						//USUSER                          
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ                             
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT                
	            ps.setString(22, "0");                                                          			//USPRJM                                             
	            ps.setString(23, " ");                                                          			//USLITM                                             
	            ps.setString(24, " ");                                                          			//USDSC1                                             
	            ps.setString(25, " ");                                                          			//USOPSQDSC1                                            
	            ps.setString(26, workcentername);                 											//USISL 
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, workcenterofphase);    														//USMCU      
	            ps.setString(4, "3");                                                           			//USYQ10USST                                     
	            ps.setString(5, workordernumber);                                      						//USDOCO                         
	            ps.setString(6, phasenumber);                                              					//USOPSQ                          
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT                                    
	            ps.setString(11, " ");                                                          			//USURCD                                            
	            ps.setString(12, " ");                                                          			//USURRF                                              
	            ps.setString(13, mesprogram);                                     							//USPID                      
	            ps.setString(14, mesjob);                                         							//USJOBN                           
	            ps.setString(15, mesuser);                              									//USUSER                           
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT                               
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT               
	            ps.setString(23, workorderjob);                         									//USPRJM                 
	            ps.setString(24, workorderitem);                        									//USLITM               
	            ps.setString(25, workorderitemdescription);             									//USDSC1    
	            ps.setString(26, phasedescription);                   										//USOPSQDSC1                    
	            ps.setString(27, workcentername);                 											//USISL              
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, workcenterofphase);    														//USMCU      
	            ps.setString(4, "3");                                                           			//USYQ10USST                                     
	            ps.setString(5, workordernumber);                                      						//USDOCO                         
	            ps.setString(6, phasenumber);                                              					//USOPSQ                          
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT                                    
	            ps.setString(11, " ");                                                          			//USURCD                                            
	            ps.setString(12, " ");                                                          			//USURRF                                              
	            ps.setString(13, mesprogram);                                     							//USPID                      
	            ps.setString(14, mesjob);                                         							//USJOBN                           
	            ps.setString(15, mesuser);                              									//USUSER                           
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ                               
	            ps.setString(17, Settings.getTime());                                           			//USUPMT                               
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT               
	            ps.setString(22, workorderjob);                         									//USPRJM                 
	            ps.setString(23, workorderitem);                        									//USLITM               
	            ps.setString(24, workorderitemdescription);             									//USDSC1    
	            ps.setString(25, phasedescription);                   										//USOPSQDSC1                    
	            ps.setString(26, workcentername);                 											//USISL  
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 3 - Attrezzaggio ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 3 - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStartSetUpMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
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
            	
	            ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU                                          
	            ps.setString(4, "2");                                                           //USYQ10USST                                              
	            ps.setString(5, "0");                                                           //USDOCO                                               
	            ps.setString(6, "0");                                                           //USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                      
	            ps.setString(8, "0");                                                           //USURDT 
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT                                              
	            ps.setString(11, " ");                                                          //USURCD                                              
	            ps.setString(12, " ");                                                          //USURRF                                              
	            ps.setString(13, mesprogram);                                     				//USPID                        
	            ps.setString(14, mesjob);                                         				//USJOBN                            
	            ps.setString(15, mesuser);                              						//USUSER                           
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT                
	            ps.setString(23, "0");                                                          //USPRJM                                              
	            ps.setString(24, " ");                                                          //USLITM                                            
	            ps.setString(25, " ");                                                          //USDSC1                                             
	            ps.setString(26, " ");                                                          //USOPSQDSC1                                             
	            ps.setString(27, workcentername);     											//USISL               
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU                                          
	            ps.setString(4, "2");                                                           //USYQ10USST                                              
	            ps.setString(5, "0");                                                           //USDOCO                                               
	            ps.setString(6, "0");                                                           //USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                      
	            ps.setString(8, "0");                                                           //USURDT 
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT                                              
	            ps.setString(11, " ");                                                          //USURCD                                              
	            ps.setString(12, " ");                                                          //USURRF                                              
	            ps.setString(13, mesprogram);                                     				//USPID                        
	            ps.setString(14, mesjob);                                         				//USJOBN                            
	            ps.setString(15, mesuser);                              						//USUSER                           
	            ps.setString(16, Settings.getDate());                                           //USUPMJ                             
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT                
	            ps.setString(22, "0");                                                          //USPRJM                                              
	            ps.setString(23, " ");                                                          //USLITM                                            
	            ps.setString(24, " ");                                                          //USDSC1                                             
	            ps.setString(25, " ");                                                          //USOPSQDSC1                                             
	            ps.setString(26, workcentername);     											//USISL    
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);

            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                					//USMMCU 
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, workcenterofphase);    											//USMCU      
	            ps.setString(4, "3");                                                           //USYQ10USST                                              
	            ps.setString(5, workordernumber);                                      			//USDOCO                          
	            ps.setString(6, phasenumber);                                              		//USOPSQ                                    
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                          
	            ps.setString(8, "0");                                                           //USURDT  
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT                                              
	            ps.setString(11, " ");                                                          //USURCD                                            
	            ps.setString(12, " ");                                                          //USURRF                                              
	            ps.setString(13, mesprogram);                                     				//USPID                         
	            ps.setString(14, mesjob);                                         				//USJOBN                          
	            ps.setString(15, mesuser);                              						//USUSER                          
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT                            
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT                 
	            ps.setString(23, workorderjob);                         						//USPRJM                   
	            ps.setString(24, workorderitem);                        						//USLITM                    
	            ps.setString(25, workorderitemdescription);             						//USDSC1     
	            ps.setString(26, phasedescription);                   							//USOPSQDSC1               
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                					//USMMCU 
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, workcenterofphase);    											//USMCU      
	            ps.setString(4, "3");                                                           //USYQ10USST                                              
	            ps.setString(5, workordernumber);                                      			//USDOCO                          
	            ps.setString(6, phasenumber);                                              		//USOPSQ                                    
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                          
	            ps.setString(8, "0");                                                           //USURDT  
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT                                              
	            ps.setString(11, " ");                                                          //USURCD                                            
	            ps.setString(12, " ");                                                          //USURRF                                              
	            ps.setString(13, mesprogram);                                     				//USPID                         
	            ps.setString(14, mesjob);                                         				//USJOBN                          
	            ps.setString(15, mesuser);                              						//USUSER                          
	            ps.setString(16, Settings.getDate());                                           //USUPMJ                            
	            ps.setString(17, Settings.getTime());                                           //USUPMT                            
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT                 
	            ps.setString(22, workorderjob);                         						//USPRJM                   
	            ps.setString(23, workorderitem);                        						//USLITM                    
	            ps.setString(24, workorderitemdescription);             						//USDSC1     
	            ps.setString(25, phasedescription);                   							//USOPSQDSC1               
	            ps.setString(26, workcentername);     											//USISL
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 3 - Attrezzaggio ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 3 - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStopSetUpMachinePresenceMonoOperator(){
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
		         + "                        USAN8,"
		         + "                        USMCU,"
		         + "                        USYQ10USST,"
		         + "                        USDOCO,"
		         + "                        USOPSQ,"
		         + "                        USDTUP,"
		         + "                        USURDT,"
		         + "                        USURAB,"
		         + "                        USURAT,"
		         + "                        USURCD,"
		         + "                        USURRF,"
		         + "                        USPID,"
		         + "                        USJOBN,"
		         + "                        USUSER,"
		         + "                        USUPMJ,"
		         + "                        USUPMT,"
		         + "                        USSOS,"   
		         + "                        USALPH,"
		         + "                        USALPH1,"    
		         + "                        USDEPT,"
		         + "                        USPRJM,"
		         + "                        USLITM,"
		         + "                        USDSC1,"
		         + "                        USOPSQDSC1,"
		         + "                        USISL) "    
		         + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
		         + "                        ?,"                                                 							//02-USAN8
		         + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
		         + "                        ?,"                                                 							//04-USYQ10USST
		         + "                        ?,"                                                 							//05-USDOCO
		         + "                        ?,"                                                 							//06-USOPSQ
		         + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
		         + "                        ?,"                                                 							//08-USURDT
		         + "                        ?,"                                                 							//09-USURAB
		         + "                        ?,"                                                 							//10-USURAT
		         + "                        ?,"                                                 							//11-USURCD
		         + "                        ?,"                                                 							//12-USURRF
		         + "                        ?,"                                                 							//13-USPID
		         + "                        ?,"                                                 							//14-USJOBN
		         + "                        ?,"                                                 							//15-USUSER
		         + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
		         + "                        ?,"                                                 							//17-USUPMT
		         + "                        ?,"                                                 							//18-USSOS
		         + "                        ?,"                                                 							//19-USALPH
		         + "                        ?,"                                                 							//20-USALPH1
		         + "                        ?,"                                                 							//21-USDEPT
		         + "                        ?,"                                                 							//22-USPRJM
		         + "                        ?,"                                                 							//23-USLITM
		         + "                        ?,"                                                 							//24-USDSC1
		         + "                        ?,"                                                 							//25-USOPSQDSC1
		         + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]");
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
            	
	            ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, "0");                                                          			//USPRJM
	            ps.setString(24, " ");                                                          			//USLITM
	            ps.setString(25, " ");                                                          			//USDSC1
	            ps.setString(26, " ");                                                          			//USOPSQDSC1
	            ps.setString(27, workcentername);                 											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, "0");                                                          			//USPRJM
	            ps.setString(23, " ");                                                          			//USLITM
	            ps.setString(24, " ");                                                          			//USDSC1
	            ps.setString(25, " ");                                                          			//USOPSQDSC1
	            ps.setString(26, workcentername);                 											//USISL
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStopSetUpMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
  
            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]");
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
            	
	            ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                    				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStartWorkMachinePresenceMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"
   	             + "                        USALPH,"
   	             + "                        USALPH1,"
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
       
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"
	             + "                        USALPH,"
	             + "                        USALPH1,"
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);
                             
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
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
            	
	            ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, "0");                                                          			//USPRJM
	            ps.setString(24, " ");                                                          			//USLITM
	            ps.setString(25, " ");                                                          			//USDSC1
	            ps.setString(26, " ");                                                          			//USOPSQDSC1
	            ps.setString(27, workcentername);                 											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, "0");                                                          			//USPRJM
	            ps.setString(23, " ");                                                          			//USLITM
	            ps.setString(24, " ");                                                          			//USDSC1
	            ps.setString(25, " ");                                                          			//USOPSQDSC1
	            ps.setString(26, workcentername);                 											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, workcenterofphase);    														//USMCU
	            ps.setString(4, "4");                                                           			//USYQ10USST
	            ps.setString(5, workordernumber);                                      						//USDOCO
	            ps.setString(6, phasenumber);                                              					//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, workorderjob);                         									//USPRJM
	            ps.setString(24, workorderitem);                        									//USLITM
	            ps.setString(25, workorderitemdescription);             									//USDSC1
	            ps.setString(26, phasedescription);                   										//USOPSQDSC1
	            ps.setString(27, workcentername);                 											//USISL
	            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, workcenterofphase);    														//USMCU
	            ps.setString(4, "4");                                                           			//USYQ10USST
	            ps.setString(5, workordernumber);                                      						//USDOCO
	            ps.setString(6, phasenumber);                                              					//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, workorderjob);                         									//USPRJM
	            ps.setString(23, workorderitem);                        									//USLITM
	            ps.setString(24, workorderitemdescription);             									//USDSC1
	            ps.setString(25, phasedescription);                   										//USOPSQDSC1
	            ps.setString(26, workcentername);                 											//USISL
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 4 - Lavorazione ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 4 - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStartWorkMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL  
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"
   	             + "                        USALPH,"
   	             + "                        USALPH1,"
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL  
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"
	             + "                        USALPH,"
	             + "                        USALPH1,"
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
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
            	
	            ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
           
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, workcenterofphase);    											//USMCU
	            ps.setString(4, "4");                                                           //USYQ10USST
	            ps.setString(5, workordernumber);                                      			//USDOCO
	            ps.setString(6, phasenumber);                                              		//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                      						//USDEPT
	            ps.setString(23, workorderjob);                         						//USPRJM
	            ps.setString(24, workorderitem);                        						//USLITM
	            ps.setString(25, workorderitemdescription);             						//USDSC1
	            ps.setString(26, phasedescription);                   							//USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                               			 			//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, workcenterofphase);    											//USMCU
	            ps.setString(4, "4");                                                           //USYQ10USST
	            ps.setString(5, workordernumber);                                      			//USDOCO
	            ps.setString(6, phasenumber);                                              		//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT
	            ps.setString(22, workorderjob);                         						//USPRJM
	            ps.setString(23, workorderitem);                        						//USLITM
	            ps.setString(24, workorderitemdescription);            				 			//USDSC1
	            ps.setString(25, phasedescription);                   							//USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStartWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 4 - Lavorazione ]");
        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 4 - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStartWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStartWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doStopWorkMachinePresenceMonoOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);
             
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMonoOperator() ]");
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
            	
	            ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                      				//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, "0");                                                          			//USPRJM
	            ps.setString(24, " ");                                                          			//USLITM
	            ps.setString(25, " ");                                                          			//USDSC1
	            ps.setString(26, " ");                                                          			//USOPSQDSC1
	            ps.setString(27, workcentername);                 											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);                                								//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                      				//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, "0");                                                          			//USPRJM
	            ps.setString(23, " ");                                                          			//USLITM
	            ps.setString(24, " ");                                                          			//USDSC1
	            ps.setString(25, " ");                                                          			//USOPSQDSC1
	            ps.setString(26, workcentername);                					 						//USISL
            	
            }
            
            ps.executeUpdate();

            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
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
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doStopWorkMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 6 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
    
            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMultiOperator() ]");
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
	            
            	ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);                                					//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                      						//USDEPT
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doStopWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doStopWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doStopWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void doAggregationMultiOperator() {
    //COSTRUTTORE CON 3 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL 
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, operatorname);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
           
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMultiOperator() ]");
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
            	
	            ps.setString(1, branchname);													//USMMCU
	            ps.setString(2, operatorname);                                        			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, operatorname);                                        			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, operatordescription);                    						//USALPH
	            ps.setString(21, operatordescription);                    						//USALPH1
	
	            if(operatordefaultdept == null){
	
	                ps.setString(22, " ");                                                      //USDEPT
	
	            }else{
	
	                ps.setString(22, operatordefaultdept);                						//USDEPT
	            }     
	                   
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, branchname);													//USMMCU
	            ps.setString(2, operatorname);                                        			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, operatorname);                                        			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, operatordescription);                    						//USALPH
	            ps.setString(20, operatordescription);                    						//USALPH1
	
	            if(operatordefaultdept == null){
	
	                ps.setString(21, " ");                                                      //USDEPT
	
	            }else{
	
	                ps.setString(21, operatordefaultdept);                						//USDEPT
	            }     
	                   
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
            }
            
            ps.executeUpdate();
            

            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatordefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatordefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doAggregationMonoOperator() {
    //COSTRUTTORE CON 3 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE TRIM(USISL) = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 "
   	             + "            SELECT LPAD(?,12,' '),"                                                      				//01-USMMCU
   	             + "                   IMAN8,"
   	             + "                   LPAD(?,12,' '),"                                                      				//02-USMCU
   	             + "                   ?,"                                                      							//03-USYQ10USST
   	             + "                   ?,"                                                      							//04-USDOCO
   	             + "                   ?,"                                                      							//05-USOPSQ
   	             + "                   STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                     							//06-USDTUP
   	             + "                   ?,"                                                      							//07-USURDT
   	             + "                   ?,"                                                      							//08-USURAB
   	             + "                   ?,"                                                      							//09-USURAT
   	             + "                   ?,"                                                      							//10-USURCD
   	             + "                   ?,"                                                      							//11-USURRF
   	             + "                   ?,"                                                      							//12-USPID
   	             + "                   ?,"                                                      							//13-USJOBN
   	             + "                   ?,"                                                      							//14-USUSER
   	             + "                   CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"                  //15-USUPMJ
   	             + "                   ?,"                                                      							//16-USUPMT
   	             + "                   ?,"                                                      							//17-USSOS
   	             + "                   TRIM(IMALPH),"
   	             + "                   ?,"                                                      							//18-USALPH1
   	             + "                   TRIM(ISDEPT),"
   	             + "                   ?,"                                                      							//19-USPRJM
   	             + "                   ?,"                                                      							//20-USLITM
   	             + "                   ?,"                                                      							//21-USDSC1
   	             + "                   ?,"                                                      							//22-USOPSQDSC1
   	             + "                   TRIM(IMUKID) "   
   	             + "     FROM F55FQ10008 "
   	             + "LEFT JOIN F55FQ10006 ON (ISUKID = IMUKID)"
   	             + "    WHERE TRIM(IMUKID) = ? "                                                							//23-IMUKID
   	             + "      AND IMDELETED = '0' "
   	             + "      AND IMENABLED = '1'";
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql2 = "INSERT INTO F55FQ10002 "
	             + "            SELECT LPAD(?, 12),"                                            //01-USMMCU
	             + "                   IMAN8,"
	             + "                   LPAD(?, 12),"                                            //02-USMCU
	             + "                   ?,"                                                      //03-USYQ10USST
	             + "                   ?,"                                                      //04-USDOCO
	             + "                   ?,"                                                      //05-USOPSQ
	             + "                   TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                     //06-USDTUP
	             + "                   ?,"                                                      //07-USURDT
	             + "                   ?,"                                                      //08-USURAB
	             + "                   ?,"                                                      //09-USURAT
	             + "                   ?,"                                                      //10-USURCD
	             + "                   ?,"                                                      //11-USURRF
	             + "                   ?,"                                                      //12-USPID
	             + "                   ?,"                                                      //13-USJOBN
	             + "                   ?,"                                                      //14-USUSER
	             + "                   DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"                //15-USUPMJ
	             + "                   ?,"                                                      //16-USUPMT
	             + "                   ?,"                                                      //17-USSOS
	             + "                   TRIM(IMALPH),"
	             + "                   ?,"                                                      //18-USALPH1
	             + "                   TRIM(ISDEPT),"
	             + "                   ?,"                                                      //19-USPRJM
	             + "                   ?,"                                                      //20-USLITM
	             + "                   ?,"                                                      //21-USDSC1
	             + "                   ?,"                                                      //22-USOPSQDSC1
	             + "                   TRIM(IMUKID) "   
	             + "     FROM F55FQ10008 "
	             + "LEFT JOIN F55FQ10006 ON (ISUKID = IMUKID)"
	             + "    WHERE TRIM(IMUKID) = ? "                                                //23-IMUKID
	             + "      AND IMDELETED = '0' "
	             + "      AND IMENABLED = '1'";
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, workcentername);
                 
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per il centro: [ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMonoOperator() ]");
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
            
	            ps.setString(1, branchname);													//USMMCU
	            ps.setString(2, " ");                                                           //USMCU
	            ps.setString(3, "2");                                                           //USYQ10USST
	            ps.setString(4, "0");                                                           //USDOCO
	            ps.setString(5, "0");                                                           //USOPSQ
	            ps.setString(6, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(7, "0");                                                           //USURDT
	            ps.setString(8, operatorname);                                        			//USURAB
	            ps.setString(9, "0");                                                           //USURAT
	            ps.setString(10, " ");                                                          //USURCD
	            ps.setString(11, " ");                                                          //USURRF
	            ps.setString(12, mesprogram);                                     				//USPID
	            ps.setString(13, mesjob);                                         				//USJOBN
	            ps.setString(14, mesuser);                              						//USUSER
	            ps.setString(15, Settings.getDate());                                           //USUPMJ
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, operatordescription);                    						//USALPH1
	            ps.setString(20, "0");                                                          //USPRJM
	            ps.setString(21, " ");                                                          //USLITM
	            ps.setString(22, " ");                                                          //USDSC1
	            ps.setString(23, " ");                                                          //USOPSQDSC1
	            ps.setString(24, workcentername);     											//IMUKID
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, branchname);													//USMMCU
	            ps.setString(2, " ");                                                           //USMCU
	            ps.setString(3, "2");                                                           //USYQ10USST
	            ps.setString(4, "0");                                                           //USDOCO
	            ps.setString(5, "0");                                                           //USOPSQ
	            ps.setString(6, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(7, "0");                                                           //USURDT
	            ps.setString(8, operatorname);                                        			//USURAB
	            ps.setString(9, "0");                                                           //USURAT
	            ps.setString(10, " ");                                                          //USURCD
	            ps.setString(11, " ");                                                          //USURRF
	            ps.setString(12, mesprogram);                                     				//USPID
	            ps.setString(13, mesjob);                                         				//USJOBN
	            ps.setString(14, mesuser);                              						//USUSER
	            ps.setString(15, Settings.getDate());                                           //USUPMJ
	            ps.setString(16, Settings.getTime());                                           //USUPMT
	            ps.setString(17, "0");                                                          //USSOS
	            ps.setString(18, operatordescription);                    						//USALPH1
	            ps.setString(19, "0");                                                          //USPRJM
	            ps.setString(20, " ");                                                          //USLITM
	            ps.setString(21, " ");                                                          //USDSC1
	            ps.setString(22, " ");                                                          //USOPSQDSC1
	            ps.setString(23, workcentername);     											//IMUKID
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname + " ]");
            LogWriter.write("[I] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + branchname + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }

    public void doDisaggregationMultiOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "UPDATE F55FQ10002 "
             + "   SET USSOS = USYQ10USST "
             + " WHERE TRIM(USISL) = ? "
             + "   AND USAN8 = ? "  
             + "   AND USDOCO != 0"; 
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "UPDATE F55FQ10002 "
   	             + "   SET USYQ10USST = ?,"
   	             + "       USDTUP = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"
   	             + "       USUPMJ = CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"
   	             + "       USUPMT = ? "   
   	             + " WHERE TRIM(USISL) = ? "
   	             + "   AND USAN8 = ? ";   
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql2 = "UPDATE F55FQ10002 "
	             + "   SET USYQ10USST = ?,"
	             + "       USDTUP = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"
	             + "       USUPMJ = DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"
	             + "       USUPMT = ? "   
	             + " WHERE TRIM(USISL) = ? "
	             + "   AND USAN8 = ? ";   
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, workcentername);
            ps.setString(2, operatorname);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationMultiOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMultiOperator() ]");
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
            	
	            ps.setString(1, "1");
	            ps.setString(2, Settings.getTimestamp());
	            ps.setString(3, Settings.getDate());
	            ps.setString(4, Settings.getDate());
	            ps.setString(5, Settings.getTime());
	            ps.setString(6, workcentername);
	            ps.setString(7, operatorname);
	            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "1");
	            ps.setString(2, Settings.getTimestamp());
	            ps.setString(3, Settings.getDate());
	            ps.setString(4, Settings.getTime());
	            ps.setString(5, workcentername);
	            ps.setString(6, operatorname);
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationMultiOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ 1 - Uscita ]");
            LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ 1 - Uscita ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    
    }
    
    public void doDisaggregationMonoOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "UPDATE F55FQ10002 "
             + "   SET USSOS = USYQ10USST "
             + " WHERE TRIM(USISL) = ? "   
             + "   AND USDOCO != 0"; 
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "UPDATE F55FQ10002 "
   	             + "   SET USYQ10USST = ?,"
   	             + "       USDTUP = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"
   	             + "       USUPMJ = CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"
   	             + "       USUPMT = ? "   
   	             + " WHERE TRIM(USISL) = ? ";   
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "UPDATE F55FQ10002 "
	             + "   SET USYQ10USST = ?,"
	             + "       USDTUP = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"
	             + "       USUPMJ = DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"
	             + "       USUPMT = ? "   
	             + " WHERE TRIM(USISL) = ? ";   
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, workcentername);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationMonoOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMonoOperator() ]");
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
	            
            	ps.setString(1, "1");
	            ps.setString(2, Settings.getTimestamp());
	            ps.setString(3, Settings.getDate());
	            ps.setString(4, Settings.getDate());
	            ps.setString(5, Settings.getTime());
	            ps.setString(6, workcentername);
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, "1");
	            ps.setString(2, Settings.getTimestamp());
	            ps.setString(3, Settings.getDate());
	            ps.setString(4, Settings.getTime());
	            ps.setString(5, workcentername);
	            
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationMonoOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ 1 - Uscita ]");
            LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ 1 - Uscita ]");
            LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    
    }
    
    public void doDisaggregationAfterStopAllTransactonsMonoOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "UPDATE F55FQ10002"
    	            + "   SET USDTUP = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
    	            + " WHERE TRIM(USISL) = ?"; 
    	                	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql1 = "UPDATE F55FQ10002"
	            + "   SET USDTUP = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
	            + " WHERE TRIM(USISL) = ?"; 

        }
        
        sql2 = "UPDATE F55FQ10002"
	            + "   SET USSOS = USYQ10USST,"
	            + "       USYQ10USST = ? "
	            + " WHERE USYQ10USST IN ('3','4') "
	            + "   AND TRIM(USISL) = ?";
       
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, Settings.getTimestamp()); 
            ps.setString(2, workcentername);
           
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
            
            ps.setString(1, "5");
            ps.setString(2, workcentername);
           
            ps.executeUpdate(); 
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ 5 - Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ 5 - Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    
    }
    
    public void doDisaggregationAfterStopAllTransactonsMultiOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "UPDATE F55FQ10002"
    	            + "   SET USDTUP = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
    	            + " WHERE TRIM(USISL) = ?"
    	            + "   AND USAN8 = ?";  
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql1 = "UPDATE F55FQ10002"
	            + "   SET USDTUP = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
	            + " WHERE TRIM(USISL) = ?"
	            + "   AND USAN8 = ?";  
        
        }
        
        sql2 = "UPDATE F55FQ10002"
            + "   SET USSOS = USYQ10USST,"
            + "       USYQ10USST = ?"
            + " WHERE USYQ10USST IN ('3','4') "
            + "   AND TRIM(USISL) = ?"
            + "   AND USAN8 = ?";
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
            
            ps.setString(1, Settings.getTimestamp()); 
            ps.setString(2, workcentername);
            ps.setString(3, operatorname);
           
            ps.executeUpdate();  
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
         
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
             
            ps.setString(1, "5");
            ps.setString(2, workcentername);
            ps.setString(3, operatorname);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ 5 - Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ 5 - Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doDisaggregationAfterStopAllTransactonsMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    
    }
    
    public void doAggregationAfterStopAllTransactonsMonoOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "UPDATE F55FQ10002"
   	             + "   SET USDTUP = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"
   	             + "       USURAB = ?,"
   	             + "       USALPH1 = ?"
   	             + " WHERE TRIM(USISL) = ?"; 
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql1 = "UPDATE F55FQ10002"
	             + "   SET USDTUP = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"
	             + "       USURAB = ?,"
   	             + "       USALPH1 = ?"
	             + " WHERE TRIM(USISL) = ?"; 
        
        }
        
        sql2 = "UPDATE F55FQ10002"
             + "   SET USYQ10USST = USSOS,"
             + "       USSOS = ?"
             + " WHERE USYQ10USST = '5'"
             + "   AND TRIM(USISL) = ?";
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
            
            ps.setString(1, Settings.getTimestamp()); 
            ps.setString(2, operatorname);
            ps.setString(3, operatordescription);
            ps.setString(4, workcentername);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ " + Settings.getTimestamp() + " ]");
            LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
         	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {
            
            conn = Settings.getDbConnection();        
            ps = conn.prepareStatement(sql2);
            
            ps.setString(1, "0");
            ps.setString(2, workcentername);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname  + " ]");
            LogWriter.write("[I] Dati: " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ 0 - Eliminazione Sospensione ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    
    }
    
    public void doAggregationAfterStopAllTransactonsMultiOperator(){
    //COSTRUTTORE CON 2 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql1 = "UPDATE F55FQ10002"
   	             + "   SET USDTUP = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
   	             + " WHERE TRIM(USISL) = ?"
   	             + "   AND USAN8 = ?"; 
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){

	        sql1 = "UPDATE F55FQ10002"
	             + "   SET USDTUP = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
	             + " WHERE TRIM(USISL) = ?"
	             + "   AND USAN8 = ?"; 
        
        }
        
        sql2 = "UPDATE F55FQ10002"
             + "   SET USYQ10USST = USSOS,"
             + "       USSOS = ?,"
             + "       USURAB = ?,"
             + "       USALPH1 = ?"
             + " WHERE USYQ10USST = '5'"
             + "   AND TRIM(USISL) = ?"
             + "   AND USAN8 = ?"; 
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
            
            ps.setString(1, Settings.getTimestamp()); 
            ps.setString(2, workcentername);
            ps.setString(3, operatorname);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per centro: [ " + workcentername + " ]");
            LogWriter.write("[I] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {
            
            conn = Settings.getDbConnection();        
            ps = conn.prepareStatement(sql2);
            
            ps.setString(1, "0");
            ps.setString(2, operatorname);
            ps.setString(3, operatordescription);
            ps.setString(4, workcentername);
            ps.setString(5, operatorname);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]"); 
            LogWriter.write("[I] Aggiornamento record su tabella : [ F55FQ10002 ] per risorsa: [ " + operatorname  + " ]");
            LogWriter.write("[I] Dati: " + "[ 0 - Eliminazione Sospensione ]");
            LogWriter.write("[I]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[I]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[I]       " + "[ " + operatorname + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ 0 - Eliminazione Sospensione ]");
            LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E]       " + "[ " + operatordescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E]       " + "[ " + operatorname + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.doAggregationAfterStopAllTransactonsMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
    
    }

    public void multiDoStartSetUpMachinePresenceMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
            
            ps.setString(1, machinename);
            
            ps.executeUpdate();
            
			LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]"); 
			LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
			LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");;
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
                        	
        	WorkOrder workorder = new WorkOrder(workorders.get(0));

        	workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
        	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
        		
	            ps.setString(1, workorderbranch);                       									//USMMCU 
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU                                            
	            ps.setString(4, "2");                                                           			//USYQ10USST                                               
	            ps.setString(5, "0");                                                           			//USDOCO                                               
	            ps.setString(6, "0");                                                           			//USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
	            ps.setString(8, "0");                                                           			//USURDT 
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT                                              
	            ps.setString(11, " ");                                                          			//USURCD                                              
	            ps.setString(12, " ");                                                          			//USURRF                                              
	            ps.setString(13, mesprogram);                                     							//USPID                        
	            ps.setString(14, mesjob);                                         							//USJOBN                           
	            ps.setString(15, mesuser);                             			    						//USUSER                          
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT                
	            ps.setString(23, "0");                                                          			//USPRJM                                             
	            ps.setString(24, " ");                                                          			//USLITM                                             
	            ps.setString(25, " ");                                                          			//USDSC1                                             
	            ps.setString(26, " ");                                                          			//USOPSQDSC1                                            
	            ps.setString(27, workcentername);                 											//USISL
            
        	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
        		ps.setString(1, workorderbranch);                       									//USMMCU 
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU                                            
	            ps.setString(4, "2");                                                           			//USYQ10USST                                               
	            ps.setString(5, "0");                                                           			//USDOCO                                               
	            ps.setString(6, "0");                                                           			//USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
	            ps.setString(8, "0");                                                           			//USURDT 
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT                                              
	            ps.setString(11, " ");                                                          			//USURCD                                              
	            ps.setString(12, " ");                                                          			//USURRF                                              
	            ps.setString(13, mesprogram);                                     							//USPID                        
	            ps.setString(14, mesjob);                                         							//USJOBN                           
	            ps.setString(15, mesuser);                             			    						//USUSER                          
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ                             
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT                
	            ps.setString(22, "0");                                                          			//USPRJM                                             
	            ps.setString(23, " ");                                                          			//USLITM                                             
	            ps.setString(24, " ");                                                          			//USDSC1                                             
	            ps.setString(25, " ");                                                          			//USOPSQDSC1                                            
	            ps.setString(26, workcentername);                 											//USISL
        		
        	}
        	
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
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
            		
		            ps.setString(1, workorderbranch);                       									//USMMCU
		            ps.setString(2, machinename);                                          						//USAN8
		            ps.setString(3, workcenterofphase);    														//USMCU      
		            ps.setString(4, "3");                                                           			//USYQ10USST                                     
		            ps.setString(5, workordernumber);                                      						//USDOCO                         
		            ps.setString(6, phasenumber);                                              					//USOPSQ                          
		            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
		            ps.setString(8, "0");                                                           			//USURDT
		            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
		            ps.setString(10, "0");                                                          			//USURAT                                    
		            ps.setString(11, " ");                                                          			//USURCD                                            
		            ps.setString(12, "M");                                                          			//USURRF                                              
		            ps.setString(13, mesprogram);                                     							//USPID                      
		            ps.setString(14, mesjob);                                         							//USJOBN                           
		            ps.setString(15, mesuser);                              									//USUSER                           
		            ps.setString(16, Settings.getDate());                                           			//USUPMJ
		            ps.setString(17, Settings.getDate());                                           			//USUPMJ
		            ps.setString(18, Settings.getTime());                                           			//USUPMT                               
		            ps.setString(19, "0");                                                          			//USSOS
		            ps.setString(20, machinedescription);                       								//USALPH
		            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
		            ps.setString(22, machinedefaultdept);                       								//USDEPT               
		            ps.setString(23, workorderjob);                         									//USPRJM                 
		            ps.setString(24, workorderitem);                        									//USLITM               
		            ps.setString(25, workorderitemdescription);             									//USDSC1    
		            ps.setString(26, phasedescription);                   										//USOPSQDSC1                    
		            ps.setString(27, workcentername);                					 						//USISL     
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            		ps.setString(1, workorderbranch);                       									//USMMCU
		            ps.setString(2, machinename);                                          						//USAN8
		            ps.setString(3, workcenterofphase);    														//USMCU      
		            ps.setString(4, "3");                                                           			//USYQ10USST                                     
		            ps.setString(5, workordernumber);                                      						//USDOCO                         
		            ps.setString(6, phasenumber);                                              					//USOPSQ                          
		            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP                          
		            ps.setString(8, "0");                                                           			//USURDT
		            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
		            ps.setString(10, "0");                                                          			//USURAT                                    
		            ps.setString(11, " ");                                                          			//USURCD                                            
		            ps.setString(12, "M");                                                          			//USURRF                                              
		            ps.setString(13, mesprogram);                                     							//USPID                      
		            ps.setString(14, mesjob);                                         							//USJOBN                           
		            ps.setString(15, mesuser);                              									//USUSER                           
		            ps.setString(16, Settings.getDate());                                           			//USUPMJ                               
		            ps.setString(17, Settings.getTime());                                           			//USUPMT                               
		            ps.setString(18, "0");                                                          			//USSOS
		            ps.setString(19, machinedescription);                       								//USALPH
		            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
		            ps.setString(21, machinedefaultdept);                       								//USDEPT               
		            ps.setString(22, workorderjob);                         									//USPRJM                 
		            ps.setString(23, workorderitem);                        									//USLITM               
		            ps.setString(24, workorderitemdescription);             									//USDSC1    
		            ps.setString(25, phasedescription);                   										//USOPSQDSC1                    
		            ps.setString(26, workcentername);                 											//USISL  
            		
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
	        	LogWriter.write("[I]       " + "[ 3 - Attrezzaggio ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
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
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 3 - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopSetUpMachinePresenceMonoOperator(){
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
            	
            WorkOrder workorder = new WorkOrder(workorders.get(0));
        	
            workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, workorderbranch);                       									//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, "0");                                                          			//USPRJM
	            ps.setString(24, " ");                                                          			//USLITM
	            ps.setString(25, " ");                                                          			//USDSC1
	            ps.setString(26, " ");                                                          			//USOPSQDSC1
	            ps.setString(27, workcentername);                											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, workorderbranch);                       									//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                    						//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, "0");                                                          			//USPRJM
	            ps.setString(23, " ");                                                          			//USLITM
	            ps.setString(24, " ");                                                          			//USDSC1
	            ps.setString(25, " ");                                                          			//USOPSQDSC1
	            ps.setString(26, workcentername);                 											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
           
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
  
    public void multiDoStartWorkMachinePresenceMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"
   	             + "                        USALPH,"
   	             + "                        USALPH1,"
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"
	             + "                        USALPH,"
	             + "                        USALPH1,"
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);
                             
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);

            WorkOrder workorder = new WorkOrder(workorders.get(0));
            
            workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, workorderbranch);					    									//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, "0");                                                          			//USPRJM
	            ps.setString(24, " ");                                                          			//USLITM
	            ps.setString(25, " ");                                                          			//USDSC1
	            ps.setString(26, " ");                                                          			//USOPSQDSC1
	            ps.setString(27, workcentername);                	 										//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, workorderbranch);					    									//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, "0");                                                          			//USPRJM
	            ps.setString(23, " ");                                                          			//USLITM
	            ps.setString(24, " ");                                                          			//USDSC1
	            ps.setString(25, " ");                                                          			//USOPSQDSC1
	            ps.setString(26, workcentername);                 											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
                        
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
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
            		
		            ps.setString(1, workorderbranch);                       									//USMMCU
		            ps.setString(2, machinename);                                          						//USAN8
		            ps.setString(3, workcenterofphase);    														//USMCU
		            ps.setString(4, "4");                                                           			//USYQ10USST
		            ps.setString(5, workordernumber);                                      						//USDOCO
		            ps.setString(6, phasenumber);                                              					//USOPSQ
		            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
		            ps.setString(8, "0");                                                           			//USURDT
		            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
		            ps.setString(10, "0");                                                          			//USURAT
		            ps.setString(11, " ");                                                          			//USURCD
		            ps.setString(12, "M");                                                          			//USURRF
		            ps.setString(13, mesprogram);                                     							//USPID
		            ps.setString(14, mesjob);                                         							//USJOBN
		            ps.setString(15, mesuser);                              									//USUSER
		            ps.setString(16, Settings.getDate());                                           			//USUPMJ
		            ps.setString(17, Settings.getDate());                                           			//USUPMJ
		            ps.setString(18, Settings.getTime());                                           			//USUPMT
		            ps.setString(19, "0");                                                          			//USSOS
		            ps.setString(20, machinedescription);                       								//USALPH
		            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
		            ps.setString(22, machinedefaultdept);                       								//USDEPT
		            ps.setString(23, workorderjob);                         									//USPRJM
		            ps.setString(24, workorderitem);                        									//USLITM
		            ps.setString(25, workorderitemdescription);             									//USDSC1
		            ps.setString(26, phasedescription);                   										//USOPSQDSC1
		            ps.setString(27, workcentername);                 											//USISL
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            		ps.setString(1, workorderbranch);                       									//USMMCU
		            ps.setString(2, machinename);                                          						//USAN8
		            ps.setString(3, workcenterofphase);    														//USMCU
		            ps.setString(4, "4");                                                           			//USYQ10USST
		            ps.setString(5, workordernumber);                                      						//USDOCO
		            ps.setString(6, phasenumber);                                              					//USOPSQ
		            ps.setString(7, Settings.getTimestamp());                                       			//USDTUP
		            ps.setString(8, "0");                                                           			//USURDT
		            ps.setString(9, operatoraggregatedtoworkcenter);                 							//USURAB
		            ps.setString(10, "0");                                                          			//USURAT
		            ps.setString(11, " ");                                                          			//USURCD
		            ps.setString(12, "M");                                                          			//USURRF
		            ps.setString(13, mesprogram);                                     							//USPID
		            ps.setString(14, mesjob);                                         							//USJOBN
		            ps.setString(15, mesuser);                              									//USUSER
		            ps.setString(16, Settings.getDate());                                           			//USUPMJ
		            ps.setString(17, Settings.getTime());                                           			//USUPMT
		            ps.setString(18, "0");                                                          			//USSOS
		            ps.setString(19, machinedescription);                       								//USALPH
		            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
		            ps.setString(21, machinedefaultdept);                       								//USDEPT
		            ps.setString(22, workorderjob);                         									//USPRJM
		            ps.setString(23, workorderitem);                        									//USLITM
		            ps.setString(24, workorderitemdescription);             									//USDSC1
		            ps.setString(25, phasedescription);                   										//USOPSQDSC1
		            ps.setString(26, workcentername);                 											//USISL
            		
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
	        	LogWriter.write("[I]       " + "[ 4 - Lavorazione ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
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
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        	}

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 4 - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopWorkMachinePresenceMonoOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);
             
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
            
            WorkOrder workorder = new WorkOrder(workorders.get(0));
                
            workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, workorderbranch);                       									//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                      				//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getDate());                                           			//USUPMJ
	            ps.setString(18, Settings.getTime());                                           			//USUPMT
	            ps.setString(19, "0");                                                          			//USSOS
	            ps.setString(20, machinedescription);                       								//USALPH
	            ps.setString(21, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(22, machinedefaultdept);                       								//USDEPT
	            ps.setString(23, "0");                                                          			//USPRJM
	            ps.setString(24, " ");                                                          			//USLITM
	            ps.setString(25, " ");                                                          			//USDSC1
	            ps.setString(26, " ");                                                          			//USOPSQDSC1
	            ps.setString(27, workcentername);                 											//USISL

            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, workorderbranch);                       									//USMMCU
	            ps.setString(2, machinename);                                          						//USAN8
	            ps.setString(3, " ");                                                           			//USMCU
	            ps.setString(4, "2");                                                           			//USYQ10USST
	            ps.setString(5, "0");                                                           			//USDOCO
	            ps.setString(6, "0");                                                           			//USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                      				//USDTUP
	            ps.setString(8, "0");                                                           			//USURDT
	            ps.setString(9, operatoraggregatedtoworkcenter);                  							//USURAB
	            ps.setString(10, "0");                                                          			//USURAT
	            ps.setString(11, " ");                                                          			//USURCD
	            ps.setString(12, " ");                                                          			//USURRF
	            ps.setString(13, mesprogram);                                     							//USPID
	            ps.setString(14, mesjob);                                         							//USJOBN
	            ps.setString(15, mesuser);                              									//USUSER
	            ps.setString(16, Settings.getDate());                                           			//USUPMJ
	            ps.setString(17, Settings.getTime());                                           			//USUPMT
	            ps.setString(18, "0");                                                          			//USSOS
	            ps.setString(19, machinedescription);                       								//USALPH
	            ps.setString(20, operatoraggregatedtoworkcenterdescription);      							//USALPH1
	            ps.setString(21, machinedefaultdept);                       								//USDEPT
	            ps.setString(22, "0");                                                          			//USPRJM
	            ps.setString(23, " ");                                                          			//USLITM
	            ps.setString(24, " ");                                                          			//USDSC1
	            ps.setString(25, " ");                                                          			//USOPSQDSC1
	            ps.setString(26, workcentername);                 											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenter + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + operatoraggregatedtoworkcenterdescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMonoOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStartSetUpMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
            
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);

            WorkOrder workorder = new WorkOrder(workorders.get(0));
            
            workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU                                          
	            ps.setString(4, "2");                                                           //USYQ10USST                                              
	            ps.setString(5, "0");                                                           //USDOCO                                               
	            ps.setString(6, "0");                                                           //USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                      
	            ps.setString(8, "0");                                                           //USURDT 
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT                                              
	            ps.setString(11, " ");                                                          //USURCD                                              
	            ps.setString(12, " ");                                                          //USURRF                                              
	            ps.setString(13, mesprogram);                                     				//USPID                        
	            ps.setString(14, mesjob);                                         				//USJOBN                            
	            ps.setString(15, mesuser);                              						//USUSER                           
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT                
	            ps.setString(23, "0");                                                          //USPRJM                                              
	            ps.setString(24, " ");                                                          //USLITM                                            
	            ps.setString(25, " ");                                                          //USDSC1                                             
	            ps.setString(26, " ");                                                          //USOPSQDSC1                                             
	            ps.setString(27, workcentername);     											//USISL  
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU                                          
	            ps.setString(4, "2");                                                           //USYQ10USST                                              
	            ps.setString(5, "0");                                                           //USDOCO                                               
	            ps.setString(6, "0");                                                           //USOPSQ                                               
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                      
	            ps.setString(8, "0");                                                           //USURDT 
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT                                              
	            ps.setString(11, " ");                                                          //USURCD                                              
	            ps.setString(12, " ");                                                          //USURRF                                              
	            ps.setString(13, mesprogram);                                     				//USPID                        
	            ps.setString(14, mesjob);                                         				//USJOBN                            
	            ps.setString(15, mesuser);                              						//USUSER                           
	            ps.setString(16, Settings.getDate());                                           //USUPMJ                             
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT                
	            ps.setString(22, "0");                                                          //USPRJM                                              
	            ps.setString(23, " ");                                                          //USLITM                                            
	            ps.setString(24, " ");                                                          //USDSC1                                             
	            ps.setString(25, " ");                                                          //USOPSQDSC1                                             
	            ps.setString(26, workcentername);     											//USISL  
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);

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
            		
		            ps.setString(1, workorderbranch);          	 									//USMMCU 
		            ps.setString(2, machinename);                                          			//USAN8
		            ps.setString(3, workcenterofphase);    											//USMCU      
		            ps.setString(4, "3");                                                           //USYQ10USST                                              
		            ps.setString(5, workordernumber);                                      			//USDOCO                          
		            ps.setString(6, phasenumber);                                              		//USOPSQ                                    
		            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                          
		            ps.setString(8, "0");                                                           //USURDT  
		            ps.setString(9, machinename);                                          			//USURAB
		            ps.setString(10, "0");                                                          //USURAT                                              
		            ps.setString(11, " ");                                                          //USURCD                                            
		            ps.setString(12, "M");                                                          //USURRF                                              
		            ps.setString(13, mesprogram);                                     				//USPID                         
		            ps.setString(14, mesjob);                                         				//USJOBN                          
		            ps.setString(15, mesuser);                              						//USUSER                          
		            ps.setString(16, Settings.getDate());                                           //USUPMJ
		            ps.setString(17, Settings.getDate());                                           //USUPMJ
		            ps.setString(18, Settings.getTime());                                           //USUPMT                            
		            ps.setString(19, "0");                                                          //USSOS
		            ps.setString(20, machinedescription);                       					//USALPH
		            ps.setString(21, machinedescription);                       					//USALPH1
		            ps.setString(22, machinedefaultdept);                       					//USDEPT                 
		            ps.setString(23, workorderjob);                         						//USPRJM                   
		            ps.setString(24, workorderitem);                        						//USLITM                    
		            ps.setString(25, workorderitemdescription);             						//USDSC1     
		            ps.setString(26, phasedescription);                   							//USOPSQDSC1               
		            ps.setString(27, workcentername);     											//USISL
		        
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            		
            		ps.setString(1, workorderbranch);           									//USMMCU 
		            ps.setString(2, machinename);                                          			//USAN8
		            ps.setString(3, workcenterofphase);    											//USMCU      
		            ps.setString(4, "3");                                                           //USYQ10USST                                              
		            ps.setString(5, workordernumber);                                      			//USDOCO                          
		            ps.setString(6, phasenumber);                                              		//USOPSQ                                    
		            ps.setString(7, Settings.getTimestamp());                                       //USDTUP                          
		            ps.setString(8, "0");                                                           //USURDT  
		            ps.setString(9, machinename);                                          			//USURAB
		            ps.setString(10, "0");                                                          //USURAT                                              
		            ps.setString(11, " ");                                                          //USURCD                                            
		            ps.setString(12, "M");                                                          //USURRF                                              
		            ps.setString(13, mesprogram);                                     				//USPID                         
		            ps.setString(14, mesjob);                                         				//USJOBN                          
		            ps.setString(15, mesuser);                              						//USUSER                          
		            ps.setString(16, Settings.getDate());                                           //USUPMJ                            
		            ps.setString(17, Settings.getTime());                                           //USUPMT                            
		            ps.setString(18, "0");                                                          //USSOS
		            ps.setString(19, machinedescription);                       					//USALPH
		            ps.setString(20, machinedescription);                       					//USALPH1
		            ps.setString(21, machinedefaultdept);                       					//USDEPT                 
		            ps.setString(22, workorderjob);                         						//USPRJM                   
		            ps.setString(23, workorderitem);                        						//USLITM                    
		            ps.setString(24, workorderitemdescription);             						//USDSC1     
		            ps.setString(25, phasedescription);                   							//USOPSQDSC1               
		            ps.setString(26, workcentername);     											//USISL
            		
            	}
	            
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
	        	LogWriter.write("[I]       " + "[ 3 - Attrezzaggio ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
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
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 3 - Attrezzaggio ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopSetUpMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                    	//01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                    	//03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
  
            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);

            WorkOrder workorder = new WorkOrder(workorders.get(0));
            
            workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                             							//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            
            	ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopSetUpMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStartWorkMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL  
   	        
   	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"
   	             + "                        USALPH,"
   	             + "                        USALPH1,"
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL  
	        
	        sql3 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"
	             + "                        USALPH,"
	             + "                        USALPH1,"
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);

            ps.setString(1, machinename);
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
            	
        	WorkOrder workorder = new WorkOrder(workorders.get(0));
        	
        	workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
        	if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
        		
	            ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
        	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        	
        		ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
        		
        	}
        	
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");

           
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql3);
            
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
            		
		            ps.setString(1, workorderbranch);           									//USMMCU
		            ps.setString(2, machinename);                                          			//USAN8
		            ps.setString(3, workcenterofphase);   											//USMCU
		            ps.setString(4, "4");                                                           //USYQ10USST
		            ps.setString(5, workordernumber);                                      			//USDOCO
		            ps.setString(6, phasenumber);                                              		//USOPSQ
		            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
		            ps.setString(8, "0");                                                           //USURDT
		            ps.setString(9, machinename);                                          			//USURAB
		            ps.setString(10, "0");                                                          //USURAT
		            ps.setString(11, " ");                                                          //USURCD
		            ps.setString(12, "M");                                                          //USURRF
		            ps.setString(13, mesprogram);                                     				//USPID
		            ps.setString(14, mesjob);                                         				//USJOBN
		            ps.setString(15, mesuser);                             		 					//USUSER
		            ps.setString(16, Settings.getDate());                                           //USUPMJ
		            ps.setString(17, Settings.getDate());                                           //USUPMJ
		            ps.setString(18, Settings.getTime());                                           //USUPMT
		            ps.setString(19, "0");                                                          //USSOS
		            ps.setString(20, machinedescription);                       					//USALPH
		            ps.setString(21, machinedescription);                       					//USALPH1
		            ps.setString(22, machinedefaultdept);                       					//USDEPT
		            ps.setString(23, workorderjob);                         						//USPRJM
		            ps.setString(24, workorderitem);                        						//USLITM
		            ps.setString(25, workorderitemdescription);             						//USDSC1
		            ps.setString(26, phasedescription);                   							//USOPSQDSC1
		            ps.setString(27, workcentername);     											//USISL
	            
            	}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            		ps.setString(1, workorderbranch);           									//USMMCU
		            ps.setString(2, machinename);                                          			//USAN8
		            ps.setString(3, workcenterofphase);    											//USMCU
		            ps.setString(4, "4");                                                           //USYQ10USST
		            ps.setString(5, workordernumber);                                      			//USDOCO
		            ps.setString(6, phasenumber);                                              		//USOPSQ
		            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
		            ps.setString(8, "0");                                                           //USURDT
		            ps.setString(9, machinename);                                          			//USURAB
		            ps.setString(10, "0");                                                          //USURAT
		            ps.setString(11, " ");                                                          //USURCD
		            ps.setString(12, "M");                                                          //USURRF
		            ps.setString(13, mesprogram);                                     				//USPID
		            ps.setString(14, mesjob);                                         				//USJOBN
		            ps.setString(15, mesuser);                              						//USUSER
		            ps.setString(16, Settings.getDate());                                           //USUPMJ
		            ps.setString(17, Settings.getTime());                                           //USUPMT
		            ps.setString(18, "0");                                                          //USSOS
		            ps.setString(19, machinedescription);                       					//USALPH
		            ps.setString(20, machinedescription);                       					//USALPH1
		            ps.setString(21, machinedefaultdept);                       					//USDEPT
		            ps.setString(22, workorderjob);                         						//USPRJM
		            ps.setString(23, workorderitem);                        						//USLITM
		            ps.setString(24, workorderitemdescription);            	 						//USDSC1
		            ps.setString(25, phasedescription);                   							//USOPSQDSC1
		            ps.setString(26, workcentername);     											//USISL
            		
            	}
            	
	            ps.executeUpdate();
	            
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
	            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]"); 
	            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
	            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
	        	LogWriter.write("[I]       " + "[ 4 - Lavorazione ]");
	        	LogWriter.write("[I]       " + "[ " + workcenterofphase + " ]");
	        	LogWriter.write("[I]       " + "[ " + workordernumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasenumber + " ]");
	        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
	        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
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
	        	LogWriter.write("[I]       " + "[ " + workorderitem + " ]");
	        	LogWriter.write("[I]       " + "[ " + phasedescription + " ]");
	        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
	            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
            }

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql3);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 4 - Lavorazione ]");
        	LogWriter.write("[E]       " + "[ " + workcenterofphase + " ]");
        	LogWriter.write("[E]       " + "[ " + workordernumber + " ]");
        	LogWriter.write("[E]       " + "[ " + phasenumber + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
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
        	LogWriter.write("[E]       " + "[ " + workorderitem + " ]");
        	LogWriter.write("[E]       " + "[ " + phasedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql3);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStartWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
    
    public void multiDoStopWorkMachinePresenceMultiOperator() {
    //COSTRUTTORE CON 5 MEMBRI
    	
        Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "";
        String sql2 = "";

        sql1 = "DELETE FROM F55FQ10002 WHERE USAN8 = ?";
        
        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
   	             + "                        USAN8,"
   	             + "                        USMCU,"
   	             + "                        USYQ10USST,"
   	             + "                        USDOCO,"
   	             + "                        USOPSQ,"
   	             + "                        USDTUP,"
   	             + "                        USURDT,"
   	             + "                        USURAB,"
   	             + "                        USURAT,"
   	             + "                        USURCD,"
   	             + "                        USURRF,"
   	             + "                        USPID,"
   	             + "                        USJOBN,"
   	             + "                        USUSER,"
   	             + "                        USUPMJ,"
   	             + "                        USUPMT,"
   	             + "                        USSOS,"   
   	             + "                        USALPH,"
   	             + "                        USALPH1,"    
   	             + "                        USDEPT,"
   	             + "                        USPRJM,"
   	             + "                        USLITM,"
   	             + "                        USDSC1,"
   	             + "                        USOPSQDSC1,"
   	             + "                        USISL) "    
   	             + "                VALUES (LPAD(?,12,' '),"                                                 				//01-USMMCU
   	             + "                        ?,"                                                 							//02-USAN8
   	             + "                        LPAD(?,12,' '),"                                                 				//03-USMCU
   	             + "                        ?,"                                                 							//04-USYQ10USST
   	             + "                        ?,"                                                 							//05-USDOCO
   	             + "                        ?,"                                                 							//06-USOPSQ
   	             + "                        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"                							//07-USDTUP
   	             + "                        ?,"                                                 							//08-USURDT
   	             + "                        ?,"                                                 							//09-USURAB
   	             + "                        ?,"                                                 							//10-USURAT
   	             + "                        ?,"                                                 							//11-USURCD
   	             + "                        ?,"                                                 							//12-USURRF
   	             + "                        ?,"                                                 							//13-USPID
   	             + "                        ?,"                                                 							//14-USJOBN
   	             + "                        ?,"                                                 							//15-USUSER
   	             + "                        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE(?,'%d/%m/%Y'))),"             //16-USUPMJ
   	             + "                        ?,"                                                 							//17-USUPMT
   	             + "                        ?,"                                                 							//18-USSOS
   	             + "                        ?,"                                                 							//19-USALPH
   	             + "                        ?,"                                                 							//20-USALPH1
   	             + "                        ?,"                                                 							//21-USDEPT
   	             + "                        ?,"                                                 							//22-USPRJM
   	             + "                        ?,"                                                 							//23-USLITM
   	             + "                        ?,"                                                 							//24-USDSC1
   	             + "                        ?,"                                                 							//25-USOPSQDSC1
   	             + "                        ?)";                                                							//26-USISL
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
	        sql2 = "INSERT INTO F55FQ10002 (USMMCU,"
	             + "                        USAN8,"
	             + "                        USMCU,"
	             + "                        USYQ10USST,"
	             + "                        USDOCO,"
	             + "                        USOPSQ,"
	             + "                        USDTUP,"
	             + "                        USURDT,"
	             + "                        USURAB,"
	             + "                        USURAT,"
	             + "                        USURCD,"
	             + "                        USURRF,"
	             + "                        USPID,"
	             + "                        USJOBN,"
	             + "                        USUSER,"
	             + "                        USUPMJ,"
	             + "                        USUPMT,"
	             + "                        USSOS,"   
	             + "                        USALPH,"
	             + "                        USALPH1,"    
	             + "                        USDEPT,"
	             + "                        USPRJM,"
	             + "                        USLITM,"
	             + "                        USDSC1,"
	             + "                        USOPSQDSC1,"
	             + "                        USISL) "    
	             + "                VALUES (LPAD(?, 12),"                                       //01-USMMCU
	             + "                        ?,"                                                 //02-USAN8
	             + "                        LPAD(?, 12),"                                       //03-USMCU
	             + "                        ?,"                                                 //04-USYQ10USST
	             + "                        ?,"                                                 //05-USDOCO
	             + "                        ?,"                                                 //06-USOPSQ
	             + "                        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"                //07-USDTUP
	             + "                        ?,"                                                 //08-USURDT
	             + "                        ?,"                                                 //09-USURAB
	             + "                        ?,"                                                 //10-USURAT
	             + "                        ?,"                                                 //11-USURCD
	             + "                        ?,"                                                 //12-USURRF
	             + "                        ?,"                                                 //13-USPID
	             + "                        ?,"                                                 //14-USJOBN
	             + "                        ?,"                                                 //15-USUSER
	             + "                        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"           //16-USUPMJ
	             + "                        ?,"                                                 //17-USUPMT
	             + "                        ?,"                                                 //18-USSOS
	             + "                        ?,"                                                 //19-USALPH
	             + "                        ?,"                                                 //20-USALPH1
	             + "                        ?,"                                                 //21-USDEPT
	             + "                        ?,"                                                 //22-USPRJM
	             + "                        ?,"                                                 //23-USLITM
	             + "                        ?,"                                                 //24-USDSC1
	             + "                        ?,"                                                 //25-USOPSQDSC1
	             + "                        ?)";                                                //26-USISL
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql1);
    
            ps.setString(1, machinename);

            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Cancellazione record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql1);
        	LogWriter.write("[E] Dati: " + "[ " + machinename + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql1);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql2);
            
            WorkOrder workorder = new WorkOrder(workorders.get(0));
            
            workorderbranch = workorder.getWorkOrderBranch(workorder);
        	
            if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
            	
	            ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getDate());                                           //USUPMJ
	            ps.setString(18, Settings.getTime());                                           //USUPMT
	            ps.setString(19, "0");                                                          //USSOS
	            ps.setString(20, machinedescription);                       					//USALPH
	            ps.setString(21, machinedescription);                       					//USALPH1
	            ps.setString(22, machinedefaultdept);                       					//USDEPT
	            ps.setString(23, "0");                                                          //USPRJM
	            ps.setString(24, " ");                                                          //USLITM
	            ps.setString(25, " ");                                                          //USDSC1
	            ps.setString(26, " ");                                                          //USOPSQDSC1
	            ps.setString(27, workcentername);     											//USISL
            
            }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
            	
            	ps.setString(1, workorderbranch);           									//USMMCU
	            ps.setString(2, machinename);                                          			//USAN8
	            ps.setString(3, " ");                                                           //USMCU
	            ps.setString(4, "2");                                                           //USYQ10USST
	            ps.setString(5, "0");                                                           //USDOCO
	            ps.setString(6, "0");                                                           //USOPSQ
	            ps.setString(7, Settings.getTimestamp());                                       //USDTUP
	            ps.setString(8, "0");                                                           //USURDT
	            ps.setString(9, machinename);                                          			//USURAB
	            ps.setString(10, "0");                                                          //USURAT
	            ps.setString(11, " ");                                                          //USURCD
	            ps.setString(12, " ");                                                          //USURRF
	            ps.setString(13, mesprogram);                                     				//USPID
	            ps.setString(14, mesjob);                                         				//USJOBN
	            ps.setString(15, mesuser);                              						//USUSER
	            ps.setString(16, Settings.getDate());                                           //USUPMJ
	            ps.setString(17, Settings.getTime());                                           //USUPMT
	            ps.setString(18, "0");                                                          //USSOS
	            ps.setString(19, machinedescription);                       					//USALPH
	            ps.setString(20, machinedescription);                       					//USALPH1
	            ps.setString(21, machinedefaultdept);                       					//USDEPT
	            ps.setString(22, "0");                                                          //USPRJM
	            ps.setString(23, " ");                                                          //USLITM
	            ps.setString(24, " ");                                                          //USDSC1
	            ps.setString(25, " ");                                                          //USOPSQDSC1
	            ps.setString(26, workcentername);     											//USISL
            	
            }
            
            ps.executeUpdate();
            
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
            LogWriter.write("[I] In esecuzione: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]"); 
            LogWriter.write("[I] Inserimento record su tabella : [ F55FQ10002 ] per risorsa: [ " + machinename + " ]");
            LogWriter.write("[I] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinename + " ]");
        	LogWriter.write("[I]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[I]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[I]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[I]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[I]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[I]       " + "[ " + workcentername + " ]");
            LogWriter.write("[I] ----------------------------------------------------------------------------------------------------");
        
        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql2);
        	LogWriter.write("[E] Dati: " + "[ " + workorderbranch + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ 2 - Ingresso ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTimestamp() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinename + " ]");
        	LogWriter.write("[E]       " + "[ " + mesprogram + " ]");
        	LogWriter.write("[E]       " + "[ " + mesjob + " ]");
        	LogWriter.write("[E]       " + "[ " + mesuser + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getDate() + " ]");
        	LogWriter.write("[E]       " + "[ " + Settings.getTime() + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedescription + " ]");
        	LogWriter.write("[E]       " + "[ " + machinedefaultdept + " ]");
        	LogWriter.write("[E]       " + "[ " + workcentername + " ]");
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]",Sqlex.getMessage(),sql2);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Presence.multiDoStopWorkMachinePresenceMultiOperator() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

    }
}
