package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperatorCache {
	
    private String operatoralreadyinincache;

	public OperatorCache() {


	}

	public void AddOperatorInCache(String workcenter, String operator, String operatordescription) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;

        sql = "INSERT INTO F55FQ10010 (CHISL,CHAN8,CHALPH) VALUES (?,?,?)";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
          
            ps.setString(1, workcenter);                              
            ps.setString(2, operator);                                          
            ps.setString(3, operatordescription);                 
            
            ps.executeUpdate();

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ OperatorCache.AddOperatorInCache()) ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ OperatorCache.AddOperatorInCache() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ OperatorCache.AddOperatorInCache() ]");
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
                	LogWriter.write("[E] Errore in classe: [ OperatorCache.AddOperatorInCache() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

	}

	public void DeleteOperatorFromCache(String workcenter, String operator) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;

        sql = "DELETE FROM F55FQ10010 WHERE TRIM(CHISL) = ? AND CHAN8 = ? ";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
          
            ps.setString(1, workcenter);                              
            ps.setString(2, operator);                                                         
            
            ps.executeUpdate();

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ OperatorCache.DeleteOperatorFromCache()) ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ OperatorCache.DeleteOperatorFromCache() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ OperatorCache.DeleteOperatorFromCache() ]");
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
                	LogWriter.write("[E] Errore in classe: [ OperatorCache.DeleteOperatorFromCache() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

		
		
	}

	public String CheckIfOperatorIsAlreadyInCache(String workcenter, String operator) {
			
		 Connection conn = null;
	     PreparedStatement ps = null;
	     ResultSet rs;
	     String sql;
	
	     sql = "SELECT COUNT(*) FROM F55FQ10010 WHERE TRIM(CHISL) = ? AND CHAN8 = ?";
	     
	     try {
	
	         conn = Settings.getDbConnection();
	         ps = conn.prepareStatement(sql);
	
	         ps.setString(1, workcenter);
	         ps.setString(2, operator);
	         
	         rs = ps.executeQuery();
	
	         while (rs.next()) {
	             if (rs.getString(1).equals("1")) {
	                 operatoralreadyinincache = "true";
	             } else {
	            	 operatoralreadyinincache = "false";
	             }
	         }
	
	     } catch (SQLException Sqlex) {
	
	    	 LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	    	 LogWriter.write("[E] Errore in classe: [ OperatorCache.CheckIfOperatorIsAlreadyInCache()) ]");
	         LogWriter.write("[E] " + Sqlex.getMessage());
	         LogWriter.write("[E] " + sql);
	         LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	    	 Notifications notification = new Notifications("[ OperatorCache.CheckIfOperatorIsAlreadyInCache() ]",Sqlex.getMessage(),sql);
	    	 notification.setModal(true);
	    	 notification.setVisible(true);
	
	     } finally {
	
	         if (ps != null) {
	             try {
	                 ps.close();
	             } catch (SQLException e) {
	            	 LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	            	 LogWriter.write("[E] Errore in classe: [ OperatorCache.CheckIfOperatorIsAlreadyInCache() ]");
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
	            	 LogWriter.write("[E] Errore in classe: [ OperatorCache.CheckIfOperatorIsAlreadyInCache() ]");
	            	 LogWriter.write("[E] Chiusura Connection");
	            	 LogWriter.write("[E] " + e.getMessage());
	            	 LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	             }
	         }
	
	     }
	
	     return operatoralreadyinincache;
	     
	}
	
	public ArrayList<String> LoadOperatorCache(String workcenter) {
		
		 Connection conn = null;
	     PreparedStatement ps = null;
	     ResultSet rs;
	     String sql;
	     ArrayList<String> operatorcache = new ArrayList<String>();
	
	     sql = "SELECT CHAN8,CHALPH FROM F55FQ10010 WHERE TRIM(CHISL) = ?";
	     
	     try {
	
	         conn = Settings.getDbConnection();
	         ps = conn.prepareStatement(sql);
	
	         ps.setString(1, workcenter);
	         
	         rs = ps.executeQuery();
	
	         while (rs.next()) {
	        	 operatorcache.add(rs.getString(1) + " - " + rs.getString(2));
	         }
	
	     } catch (SQLException Sqlex) {
	
	    	 LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	    	 LogWriter.write("[E] Errore in classe: [ OperatorCache.LoadOperatorCache()) ]");
	         LogWriter.write("[E] " + Sqlex.getMessage());
	         LogWriter.write("[E] " + sql);
	         LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	    	 Notifications notification = new Notifications("[ OperatorCache.LoadOperatorCache() ]",Sqlex.getMessage(),sql);
	    	 notification.setModal(true);
	    	 notification.setVisible(true);
	
	     } finally {
	
	         if (ps != null) {
	             try {
	                 ps.close();
	             } catch (SQLException e) {
	            	 LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	            	 LogWriter.write("[E] Errore in classe: [ OperatorCache.LoadOperatorCache() ]");
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
	            	 LogWriter.write("[E] Errore in classe: [ OperatorCache.LoadOperatorCache() ]");
	            	 LogWriter.write("[E] Chiusura Connection");
	            	 LogWriter.write("[E] " + e.getMessage());
	            	 LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
	             }
	         }
	
	     }
	
	     return operatorcache;
	     
	}
	
}
