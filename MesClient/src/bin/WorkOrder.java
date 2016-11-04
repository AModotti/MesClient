package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkOrder {
	
	private String workorder;
    private String workorderdescription;
    private String workorderbranch;
    private String workorderbranchdescription;
    private String workorderjob;
    private String workorderjobdescription;
    private String workorderitem;
    private String workorderitemdescription;
    private String workcenterofphase;
    private String workordercenterdescription;
    private String workorderexists;
    private String workorderisclose;
    private String workorderitemshort;
    private String workorderisone;
    private String centerexists;

        
    public WorkOrder(String workorder) {
        
        this.workorder = workorder;
    
    }
    
    public void setWorkOrder(String workorder) {

        this.workorder = workorder;

    }

    public String getWorkOrder() {

        return workorder;

    }
        
    public String checkIfWorkOrderExists(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F4801 WHERE WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    workorderexists = "true";
                } else {
                    workorderexists = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.checkIfWorkOrderExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderexists;
    }

    public String checkIfWorkOrderIsClose(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F4801 WHERE WASRST < '94' AND WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    workorderisclose = "true";
                } else {
                	workorderisclose = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderIsClose() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.checkIfWorkOrderIsClose() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderIsClose() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderIsClose() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderisclose;
    }

    public String getWorkOrderDescription(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = ?";
        
        try {
 
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderdescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderdescription;

    }

    public String getWorkOrderBranch(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        String epurateworkorder;

        sql = "SELECT TRIM(WAMMCU) FROM F4801 WHERE WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            if((workorder.getWorkOrder() != null)&&(!workorder.getWorkOrder().equals(""))){
	            if(workorder.getWorkOrder().substring(1,2).equals("*")){
	            	epurateworkorder = workorder.getWorkOrder().substring(2,workorder.getWorkOrder().length());
	            }else{
	            	epurateworkorder =  workorder.getWorkOrder();
	            }
	            
	            ps.setString(1, epurateworkorder);
	            
	            rs = ps.executeQuery();
	
	            while (rs.next()) {
	                workorderbranch = rs.getString(1);
	            }
	        }else{
	        	workorderbranch = "";
	        }
            
        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderBranch() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderBranch() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderBranch() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderBranch() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderbranch;

    }

    public String checkIfWorkOrderIsOne(Item item) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F4801 WHERE WADCTO IN ('WO','WK') AND WASRST < '94' AND WAITM = ?";
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, item.getItemShort(item)); 
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    workorderisone = "true";
                } else {
                    workorderisone = "false.";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderIsOne() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.checkIfWorkOrderIsOne() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderIsOne() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkOrderIsOne() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderisone;

    }
    
    public String getWorkOrderBranchDescription(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(MCDL01) FROM F0006 WHERE MCMCU = (SELECT WAMMCU FROM F4801 WHERE WADOCO = ?)";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderbranchdescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderBranchDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderBranchDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderBranchDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderBranchDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderbranchdescription;

    }

    public String getWorkOrderJob(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT WAPRJM FROM F4801T WHERE WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderjob = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderJob() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderJob() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderJob() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderJob() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderjob;

    }

    public String getWorkOrderJobDescription() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(ABALPH) FROM F0101 WHERE ABAN8 = (SELECT WAAN8 FROM F4801 WHERE WADOCO = ?)";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, this.workorderjob);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderjobdescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderJobDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderJobDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderJobDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderJobDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderjobdescription;

    }
    
    public String getWorkOrderItem(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(WALITM) FROM F4801 WHERE WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderitem = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItem() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderItem() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItem() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItem() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderitem;

    }
    
    public String getWorkOrderItemShort(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT WAITM FROM F4801 WHERE WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderitemshort = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItemShort() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderItemShort() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItemShort() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItemShort() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderitemshort;

    }
    
    public String getWorkOrderItemDescription(WorkOrder workorder) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(WADL01) FROM F4801 WHERE WADOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorderitemdescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItemDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderItemDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItemDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderItemDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workorderitemdescription;

    }

    public String getWorkCenterOfPhase(WorkOrder workorder, Phase phase) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        
         sql = "SELECT TRIM(WLMCU) FROM F3112 WHERE WLDOCO = ? AND WLOPSQ = ?";  
         
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            ps.setString(2, phase.getPhase());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workcenterofphase = rs.getString(1);
            }

        }catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkCenterOfPhase() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkCenterOfPhase() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkCenterOfPhase() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkCenterOfPhase() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workcenterofphase;
    }

    public String checkIfWorkCenterExists(String workcenter) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F3112 WHERE TRIM(WLMCU) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workcenter);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    centerexists = "true";
                } else {
                    centerexists = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkCenterExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.checkIfWorkCenterExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkCenterExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.checkIfWorkCenterExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return centerexists;
    }

    public String getWorkOrderWorkCenterDescription(String workcenter) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(WLDSC1) FROM F3112 WHERE WLDOCO = ? AND TRIM(WLMCU) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, this.workorder);
            ps.setString(2, workcenter);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workordercenterdescription = (rs.getString(1));
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderWorkCenterDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkOrder.getWorkOrderWorkCenterDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderWorkCenterDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkOrder.getWorkOrderWorkCenterDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workordercenterdescription;

    }

}
