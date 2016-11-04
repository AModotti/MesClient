package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Machine {
	
	private String machine;
    private String machinedescription;
    private String deptdescription;
    private String machineexists;
    private String workcenterofmachine;
    private String machinelinked;
    private String machinebelongstoworkcenter;
    private String machinealreadyenter;
    private String machinealreadyexit;
    private String machinealreadyinwork;
    private String machinealreadyinsetup;
    private String machinealreadyinstop;
    private String multiworkorderismachinealreadyinwork;
    private String multiworkorderismachinealreadyinsetup;
    private String multiworkorderismachinealreadyinstop;  
    private String machineinactivityonmultiworkorder;
    private String machineinactivityonmonoworkorder;
    private String workorderthesameinwork;
    private String multipleworkorderisthesameinwork;
    private String workordertostop;
    private String phasetostop;
    private String multiworkorderphasetostop;
    
    
    public Machine(String machine) {
        
        this.machine = machine;
        
    }
    
    public void setMachine(String machine) {

        this.machine = machine;

    }

    public String getMachine() {

        return machine;

    }
            
    public String checkIfMachineExists(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F0101 WHERE TRIM(ABAT1) IN ('WC','E') AND ABAN8 = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    machineexists = "true";
                } else {
                    machineexists = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineExists() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machineexists;
    }

    public String getMachineDescription(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(ABALPH) FROM F0101 WHERE TRIM(ABAT1) IN ('WC','E') AND ABAN8 = ?";
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                machinedescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDescription() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.getMachineDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinedescription;

    }
    
//    public String getMachineDefaultDept(Machine machine) {
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs;
//        String sql;
//        
//        sql = "SELECT TRIM(ISDEPT) FROM F55FQ10006 WHERE ISUKID = (SELECT IMUKID FROM F55FQ10008 WHERE IMAN8 = ?)";
//        
//        try {
//
//            conn = Settings.getDbConnection();
//            ps = conn.prepareStatement(sql);
//
//            ps.setString(1, machine.getMachine());
//            
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                machinedefaultdept = rs.getString(1);
//            }
//
//        } catch (SQLException Sqlex) {
//
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDefaultDept() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql);
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Machine.getMachineDefaultDept() ]",Sqlex.getMessage(),sql);
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
//                	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDefaultDept() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDefaultDept() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//        return machinedefaultdept;
//    }

    public String getMachineDeptDescription(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        
        sql = "SELECT TRIM(DPALPH) FROM F55FQ10009 WHERE DPDEPT = (SELECT ISDEPT FROM F55FQ10006 WHERE ISUKID = (SELECT IMUKID FROM F55FQ10008 WHERE IMAN8 = ?))";

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	deptdescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDeptDescription() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.getMachineDeptDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDeptDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.getMachineDeptDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return deptdescription;

    }

    public String isMachineLinkedToWorkCenter(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        
        sql = "SELECT COUNT(*) FROM F55FQ10008 WHERE IMAN8 = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    machinelinked = "true";
                } else {
                	machinelinked = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.isMachineLinkedToWorkCenter() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.isMachineLinkedToWorkCenter() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.isMachineLinkedToWorkCenter() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.isMachineLinkedToWorkCenter() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinelinked;

    }
        
    public String getWorkCenterOfMachine(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(IMUKID) FROM F55FQ10008 WHERE IMAN8 = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                workcenterofmachine = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.getWorkCenterOfMachine() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.getWorkCenterOfMachine() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.getWorkCenterOfMachine() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.getWorkCenterOfMachine() ]");;
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workcenterofmachine;
        
    }
    
    public String checkIfMachineBelongsToWorkCenter(Machine machine,WorkCenter workcenter) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10008 WHERE IMAN8 = ? AND TRIM(IMUKID) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                	machinebelongstoworkcenter = "true";
                } else {
                	machinebelongstoworkcenter = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineBelongsToWorkCenter() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineBelongsToWorkCenter() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineBelongsToWorkCenter() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineBelongsToWorkCenter() ]");;
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinebelongstoworkcenter;

    }

    public String checkIfMachineIsAlreadyCameIn(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USYQ10USST = '2' AND USAN8 = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                	machinealreadyenter = "true";
                } else {
                	machinealreadyenter = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyCameIn() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineIsAlreadyCameIn() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyCameIn() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyCameIn() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinealreadyenter;

    }

    public String checkIfMachineIsAlreadyCameOut(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USYQ10USST = '1' AND USAN8 = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                	machinealreadyexit = "true";
                } else {
                	machinealreadyexit = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyCameOut() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineIsAlreadyCameOut() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyCameOut() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyCameOut() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinealreadyexit;
    }
 
    public String checkIfMachineIsAlreadyInWork(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST = '4'";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    machinealreadyinwork = "true";
                } else {
                	machinealreadyinwork = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInWork() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineIsAlreadyInWork() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInWork() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInWork() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinealreadyinwork;

    }

    public String checkIfMachineIsAlreadyInSetup(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST = '3'";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                	machinealreadyinsetup = "true";
                } else {
                	machinealreadyinsetup = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInSetup() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineIsAlreadyInSetup() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInSetup() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInSetup() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinealreadyinsetup;

    }

    public String checkIfMachineIsAlreadyInStop(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? ";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    machinealreadyinstop = "true";
                } else {
                	machinealreadyinstop = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInStop() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfMachineIsAlreadyInStop() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInStop() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfMachineIsAlreadyInStop() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machinealreadyinstop;

    }
    
    public String checkIfWorkOrderIsTheSameInWork(Machine machine,WorkOrder workorder,Phase phase) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) "
            + "  FROM F55FQ10002 "
            + " WHERE (USAN8 = ? AND USDOCO = ? AND USOPSQ = ? AND USYQ10USST IN ('3','4')) "
            + "    OR (USAN8 = ? AND USDOCO = 0 AND USOPSQ = 0 AND USYQ10USST IN ('1'))";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workorder.getWorkOrder());
            ps.setString(3, phase.getPhase());
            ps.setString(4, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    workorderthesameinwork = "true";
                } else {
                	workorderthesameinwork = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.checkIfWorkOrderIsTheSameInWork() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.checkIfWorkOrderIsTheSameInWork() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfWorkOrderIsTheSameInWork() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.checkIfWorkOrderIsTheSameInWork() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderthesameinwork;

    }
 
    public String getWorkOrderToStop(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT USDOCO FROM F55FQ10002 WHERE USAN8 = ? AND USDOCO != 0";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workordertostop = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.getWorkOrderToStop() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.getWorkOrderToStop() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.getWorkOrderToStop() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.getWorkOrderToStop() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workordertostop;

    }
    
    public String getPhaseToStop(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT USOPSQ FROM F55FQ10002 WHERE USAN8 = ? AND USOPSQ != 0";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                phasetostop = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.getPhaseToStop() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.getPhaseToStop() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.getPhaseToStop() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.getPhaseToStop() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return phasetostop;

    }

	public String multiWorkOrderCheckIfWorkOrderIsTheSameInWork(Machine machine,ArrayList<String> workorders,Phase phase) {
	
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs;
	    String sql;
	
	    sql = "SELECT COUNT(*) "
	        + "  FROM F55FQ10002 "
	        + " WHERE (USAN8 = ? AND USDOCO = ? AND USOPSQ = ? AND USYQ10USST IN ('3','4')) "
	        + "    OR (USAN8 = ? AND USDOCO = 0 AND USOPSQ = 0 AND USYQ10USST IN ('1'))";
	    
	    try {
	
	        conn = Settings.getDbConnection();
	        ps = conn.prepareStatement(sql);
	       
	        for (String wo : workorders) {
            	
            	ps.setString(1, machine.getMachine());
            	ps.setString(2, wo);
            	ps.setString(3, phase.getPhase());
    	        ps.setString(4, machine.getMachine());

		        rs = ps.executeQuery();
		
		        while (rs.next()) {
		            if (rs.getString(1).equals("1")) {
		            	multipleworkorderisthesameinwork = "true";
		            } else {
		            	multipleworkorderisthesameinwork = "false";
		            }
		        }
		        
            }
            
	    } catch (SQLException Sqlex) {
	
	    	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfWorkOrderIsTheSameInWork() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	    	Notifications notification = new Notifications("[ Machine.multiWorkOrderCheckIfWorkOrderIsTheSameInWork() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	
	    } finally {
	
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	            	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	            	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfWorkOrderIsTheSameInWork() ]");
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
	            	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfWorkOrderIsTheSameInWork() ]");
	            	LogWriter.write("[E] Chiusura Connection");
	            	LogWriter.write("[E] " + e.getMessage());
	            	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	            }
	        }
	
	    }
	
	    return multipleworkorderisthesameinwork;
	
	}

	public String multiWorkOrderCheckIfMachineIsAlreadyInWork(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST = '4' AND TRIM(USURRF) = 'M'";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                	multiworkorderismachinealreadyinwork = "false";
                } else {
                	multiworkorderismachinealreadyinwork = "true";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInWork() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.multiWorkOrderCheckIfMachineIsAlreadyInWork() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInWork() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInWork() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return multiworkorderismachinealreadyinwork;

    }

    public String multiWorkOrderCheckIfMachineIsAlreadyInSetup(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST = '3' AND TRIM(USURRF) = 'M'";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                	multiworkorderismachinealreadyinsetup = "false";
                } else {
                	multiworkorderismachinealreadyinsetup = "true";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInSetup() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.multiWorkOrderCheckIfMachineIsAlreadyInSetup() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInSetup() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInSetup() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return multiworkorderismachinealreadyinsetup;

    }

    public String multiWorkOrderCheckIfMachineIsAlreadyInStop(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST IN ('3','4')";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                	multiworkorderismachinealreadyinstop = "true";
                } else {
                	multiworkorderismachinealreadyinstop = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInStop() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.multiWorkOrderCheckIfMachineIsAlreadyInStop() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInStop() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderCheckIfMachineIsAlreadyInStop() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return multiworkorderismachinealreadyinstop;

    }

    public String isMachineInActivityOnMultiWorkOrder(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST IN ('3','4','5') AND TRIM(USURRF) = 'M' ";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                	machineinactivityonmultiworkorder = "false";
                } else {
                	machineinactivityonmultiworkorder = "true";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.isMachineInActivityOnMultiWorkOrder() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.isMachineInActivityOnMultiWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.isMachineInActivityOnMultiWorkOrder() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.isMachineInActivityOnMultiWorkOrder() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machineinactivityonmultiworkorder;

    }

    public String isMachineInActivityOnMonoWorkOrder(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10002 WHERE USAN8 = ? AND USYQ10USST IN ('3','4','5') AND TRIM(USURRF) != 'M' ";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("0")) {
                	machineinactivityonmonoworkorder = "false";
                } else {
                	machineinactivityonmonoworkorder = "true";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.isMachineInActivityOnMonoWorkOrder() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.isMachineInActivityOnMonoWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.isMachineInActivityOnMonoWorkOrder() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.isMachineInActivityOnMonoWorkOrder() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return machineinactivityonmonoworkorder;

    }
    
    public ArrayList<String> multiWorkOrderGetWorkOrderToStop(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT USDOCO FROM F55FQ10002 WHERE USAN8 = ? AND USDOCO != 0";
        
        ArrayList<String> multiworkorderworkordertostop = new ArrayList<String>();
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	multiworkorderworkordertostop.add(rs.getString(1));
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderGetWorkOrderToStop() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.multiWorkOrderGetWorkOrderToStop() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderGetWorkOrderToStop() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderGetWorkOrderToStop() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return multiworkorderworkordertostop;

    }
    
    public String multiWorkOrderGetPhaseToStop(Machine machine) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT DISTINCT USOPSQ FROM F55FQ10002 WHERE USAN8 = ? AND USOPSQ != 0";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	multiworkorderphasetostop = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderGetPhaseToStop() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Machine.multiWorkOrderGetPhaseToStop() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderGetPhaseToStop() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Machine.multiWorkOrderGetPhaseToStop() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return multiworkorderphasetostop;

    }
}
