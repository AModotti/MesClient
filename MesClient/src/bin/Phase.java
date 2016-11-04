package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Phase {
	
	private String phase;
    private String phasedescription;
    private String phaseexists;
   
    
    public Phase(String phase) {
        
        this.phase = phase;
        
    }
    
    public void setPhase(String phase) {

        this.phase = phase;

    }

    public String getPhase() {

        return phase;

    }
    
    public String checkIfPhaseExists(WorkOrder workorder, Phase phase) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F3112 WHERE WLOPSQ = ? AND WLDOCO = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, phase.getPhase());
            ps.setString(2, workorder.getWorkOrder());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    phaseexists = "true";
                } else {
                    phaseexists = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ Phase.checkIfPhaseExists()) ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Phase.checkIfPhaseExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Phase.checkIfPhaseExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Phase.checkIfPhaseExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return phaseexists;
    }

    public String getPhaseDescription(WorkOrder workorder, Phase phase) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT TRIM(WLDSC1) FROM F3112 WHERE WLDOCO = ? AND WLOPSQ = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workorder.getWorkOrder());
            ps.setString(2, phase.getPhase());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                phasedescription = (rs.getString(1));
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ Phase.getPhaseDescription()) ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Phase.getPhaseDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Phase.getPhaseDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Phase.getPhaseDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return phasedescription;

    }

}
