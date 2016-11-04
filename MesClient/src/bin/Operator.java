package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operator {
	
	private String operator;
    private String operatordescription;
    private String operatorexists;
    private String operatorhasaggregations;
    private String deptdescription;
    private String operatoraggregated;
    private String operatorlinked;
    
    public Operator() {
        
    }

    public Operator(String operator) {
        
        this.operator = operator;
        
    }
    
    public void setOperator(String operator) {

        this.operator = operator;

    }

    public String getOperator() {

        return operator;

    }
    
    public String checkIfOperatorExists(Operator operator) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F0101 WHERE TRIM(ABAT1) = 'E' AND ABAN8 = ?";
        
        try {
 
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, operator.getOperator());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    operatorexists = "true";
                } else {
                    operatorexists = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Operator.checkIfOperatorExists() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Operator.checkIfOperatorExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Operator.checkIfOperatorExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Operator.checkIfOperatorExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return operatorexists;
    }

    public String isOperatorLinkedToWorkCenter(WorkCenter workcenter,Operator operator) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10008 WHERE IMAN8 = ? AND TRIM(IMUKID) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, operator.getOperator());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                	operatorlinked = "true";
                } else {
                	operatorlinked = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Operator.isOperatorLinkedToWorkCenter() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Operator.isOperatorLinkedToWorkCenter() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Operator.isOperatorLinkedToWorkCenter() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Operator.isOperatorLinkedToWorkCenter() ]");;
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return operatorlinked;

    }
    
    public String checkIfOperatorHasAggregations(Operator operator) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10007 WHERE AGAN8 = ?";
        
        try {
 
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, operator.getOperator());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                	operatorhasaggregations = "true";
                } else {
                	operatorhasaggregations = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Operator.checkIfOperatorHasAggregations() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Operator.checkIfOperatorHasAggregations() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Operator.checkIfOperatorHasAggregations() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Operator.checkIfOperatorHasAggregations() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return operatorhasaggregations;
    }
    
    public String getOperatorDescription(Operator operator) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(ABALPH) FROM F0101 WHERE TRIM(ABAT1) = 'E' AND ABAN8 = ?";

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, operator.getOperator());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                operatordescription = rs.getString(1);
            }
            
        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDescription() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Operator.getOperatorDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return operatordescription;

    }
            
//    public String getOperatorDefaultDept(Operator operator) {
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
//            ps.setString(1, operator.getOperator());
//            
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                operatordefaultdept = rs.getString(1);
//            }
//
//        } catch (SQLException Sqlex) {
//
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDefaultDept() ]");
//        	LogWriter.write("[E] " + Sqlex.getMessage());
//        	LogWriter.write("[E] " + sql);
//        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//        	Notifications notification = new Notifications("[ Operator.getOperatorDefaultDept() ]",Sqlex.getMessage(),sql);
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
//                	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDefaultDept() ]");
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
//                	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDefaultDept() ]");
//                	LogWriter.write("[E] Chiusura Connection");
//                	LogWriter.write("[E] " + e.getMessage());
//                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
//                }
//            }
//
//        }
//
//        return operatordefaultdept;
//    }

    public String getOperatorDeptDescription(Operator operator) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(DPALPH) FROM F55FQ10009 WHERE DPDEPT = (SELECT ISDEPT FROM F55FQ10006 WHERE ISUKID = (SELECT IMUKID FROM F55FQ10008 WHERE IMAN8 = ?))";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, operator.getOperator());
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	deptdescription = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDeptDescription() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Operator.getOperatorDeptDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDeptDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Operator.getOperatorDeptDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return deptdescription;

    }

    public String isOperatorAggregated(Operator operator) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10007 WHERE AGAN8 = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, operator.getOperator());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    operatoraggregated = "true";
                } else {
                    operatoraggregated = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Operator.isOperatorAggregated() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Operator.isOperatorAggregated() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Operator.isOperatorAggregated() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Operator.isOperatorAggregated() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return operatoraggregated;

    }


}
