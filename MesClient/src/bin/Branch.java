package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Branch {
	
	private String branch;
    private String branchdescription;
    private String branchexists;

    
    public Branch(String branch) {
    
        this.branch = branch;
    
    }

    public void setBranch(String branch) {

        this.branch = branch;

    }

    public String getBranch() {

        return branch;

    }
    
    public String checkIfBranchExists(Branch branch) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F0006 WHERE TRIM(MCMCU) = ?";

        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, branch.getBranch());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    branchexists = "true";
                } else {
                    branchexists = "false";
                }
            }

        } catch (SQLException Sqlex) {
        	
        	LogWriter.write("[E] --------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Branch.checkIfBranchExists() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] --------------------------------------------------");
        	Notifications notification = new Notifications("[ Branch.checkIfBranchExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] --------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Branch.checkIfBranchExists() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] --------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] --------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Branch.checkIfBranchExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] --------------------------------------------------");
                }
            }

        }

        return branchexists;
    }

    public String getBranchDescription(Branch branch) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(MCDL01) FROM F0006 WHERE TRIM(MCMCU) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, branch.getBranch());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                branchdescription = rs.getString(1);
            }

        }  catch (SQLException Sqlex) {

        	LogWriter.write("[E] --------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Branch.getBranchDescription() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] --------------------------------------------------");
        	Notifications notification = new Notifications("[ Branch.getBranchDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] --------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Branch.getBranchDescription() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] --------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] --------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Branch.getBranchDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] --------------------------------------------------");
                }
            }

        }

        return branchdescription;

    }

}
