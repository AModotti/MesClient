package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TimeCalculator {

	private String lowertime;
	private String uppertime;
	private ArrayList<Integer> entryds;
	private int count;
	private long timedifferencemilliseconds;
	private long timedifferenceseconds;
	private long timedifferenceminutes;
	private long timedifferencehours;
	private String dateofid;
	private String typeofid;
	
	
	public TimeCalculator(){
		
		
	}
	
	public String getLowerTime(Machine machine, WorkCenter workcenter) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT ADDTUP FROM F55FQ10001 A WHERE ADAN8 = ? AND TRIM(ADURRF) = 'M' AND ADYQ10ACCD = 'A' AND TRIM(ADISL) = ? "
        	+ "   AND ADUKID = (SELECT MIN(ADUKID) "
            + "                   FROM F55FQ10001 B "
            + "                  WHERE B.ADAN8 = A.ADAN8 "
            + "                    AND TRIM(B.ADURRF) = TRIM(A.ADURRF) "
            + "                    AND B.ADYQ10ACCD = A.ADYQ10ACCD)";
        
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	lowertime = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getLowerTime() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getLowerTime() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getLowerTime() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getLowerTime() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		
		return lowertime;
	}
	
	public String getUpperTime(Machine machine, WorkCenter workcenter) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT ADDTUP FROM F55FQ10001 A WHERE ADAN8 = ? AND TRIM(ADURRF) = 'M' AND ADYQ10ACCD = 'S' AND TRIM(ADISL) = ? "
        	+ "   AND ADUKID = (SELECT MAX(ADUKID) "
            + "                   FROM F55FQ10001 B "
            + "                  WHERE B.ADAN8 = A.ADAN8 "
            + "                    AND TRIM(B.ADURRF) = TRIM(A.ADURRF) "
            + "                    AND B.ADYQ10ACCD = A.ADYQ10ACCD)";
        
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	uppertime = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getUppreTime() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getUppreTime() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getUppreTime() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getUppreTime() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		return uppertime;
	}
		
	public int getCount(Machine machine, WorkCenter workcenter) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F55FQ10001 WHERE ADAN8 = ? AND TRIM(ADURRF) = 'M' AND TRIM(ADISL) = ?"; 
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	count = rs.getInt(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getCount() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getCount() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getCount() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getCount() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		return count;
		
	}

	public String getIdTime(int id) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT ADDTUP FROM F55FQ10001 WHERE TRIM(ADUKID) = ?"; 
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	dateofid = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getIdTime() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getIdTime() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getIdTime() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getIdTime() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		return dateofid;
		
	}
	
	public String getIdType(int id) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT ADYQ10ACCD FROM F55FQ10001 WHERE TRIM(ADUKID) = ?"; 
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	typeofid = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getIdType() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getIdType() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getIdType() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getIdType() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		return typeofid;
		
	}

	public ArrayList<Integer> getAllIds(Machine machine, WorkCenter workcenter) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        ArrayList<Integer> ids = new ArrayList<Integer>();
    	
        sql = "SELECT ADUKID FROM F55FQ10001 WHERE ADAN8 = ? AND TRIM(ADURRF) = 'M'  AND TRIM(ADISL) = ? ORDER BY ADDOCO,ADYQ10ACCD";
        
       
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	ids.add(rs.getInt(1));
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getAllIds() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getAllIds() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getAllIds() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getAllIds() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		return ids;
	}

	public ArrayList<Integer> getExitIds(Machine machine, WorkCenter workcenter) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        ArrayList<Integer> exitids = new ArrayList<Integer>();
    	
        sql = "SELECT ADUKID FROM F55FQ10001 WHERE ADAN8 = ? AND TRIM(ADURRF) = 'M' AND ADYQ10ACCD = 'S' AND TRIM(ADISL) = ? ORDER BY ADUKID";
        
       
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, machine.getMachine());
            ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	exitids.add(rs.getInt(1));
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.getExitIds() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.getExitIds() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getExitIds() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.getExitIds() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		return exitids;
	}
	
	public ArrayList<Integer> getEntryIds(Machine machine, WorkCenter workcenter) {
		
		return entryds;
	}
	
	public long calculateTimeDiffereceMilliSeconds(String StartDate, String StopDate){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		long diff = 0;
		
		try {
			diff = format.parse(StopDate).getTime() - format.parse(StartDate).getTime();
		} catch (ParseException e) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.calculateTimeDiffereceMilliSeconds() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ TimeCalculator.calculateTimeDiffereceMilliSeconds() ]","Errore durante il calcolo della differenza di tempo",e.getMessage());
        	notification.setModal(true);
        	notification.setVisible(true);
		}
		
		timedifferencemilliseconds = diff;

		return timedifferencemilliseconds;
		
	}

	public long calculateTimeDiffereceSeconds(String StartDate, String StopDate){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		long diff = 0;
		
		try {
			diff = format.parse(StopDate).getTime() - format.parse(StartDate).getTime();
		} catch (ParseException e) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.calculateTimeDiffereceSeconds() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ TimeCalculator.calculateTimeDiffereceSeconds() ]","Errore durante il calcolo della differenza di tempo",e.getMessage());
        	notification.setModal(true);
        	notification.setVisible(true);
		}
		
		timedifferenceseconds = diff / 1000;

		return timedifferenceseconds;
		
	}
	
	public long calculateTimeDiffereceMinutes(String StartDate, String StopDate){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

		long diff = 0;
		
		try {
			diff = format.parse(StopDate).getTime() - format.parse(StartDate).getTime();
		} catch (ParseException e) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.calculateTimeDiffereceMinutes() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ TimeCalculator.calculateTimeDiffereceMinutes() ]","Errore durante il calcolo della differenza di tempo",e.getMessage());
        	notification.setModal(true);
        	notification.setVisible(true);
		}
		
		timedifferenceminutes = diff / (60 * 1000);

		return timedifferenceminutes;
		
	}
	
	public long calculateTimeDiffereceHours(String StartDate, String StopDate){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

		long diff = 0;
		
		try {
			diff = format.parse(StopDate).getTime() - format.parse(StartDate).getTime();
		} catch (ParseException e) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.calculateTimeDiffereceHours() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ TimeCalculator.calculateTimeDiffereceHours() ]","Errore durante il calcolo della differenza di tempo",e.getMessage());
        	notification.setModal(true);
        	notification.setVisible(true);
		}
		
		timedifferencehours = diff / (60 * 60 * 1000);

		return timedifferencehours;
		
	}
	
	public void updateTimeOfRecords(Machine machine, WorkCenter workcenter, ArrayList<Integer> ids, long time){
	
		Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";

        if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){

        	sql = "UPDATE F55FQ10001 SET ADDTUP = STR_TO_DATE(?,'%Y-m%-d% %H:%i:%s') + (?/(24*60*60)) WHERE TRIM(ADUKID) = ?";
        	
        }else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
        
        	sql = "UPDATE F55FQ10001 SET ADDTUP = TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') + (?/(24*60*60)) WHERE TRIM(ADUKID) = ?";
        
        }
        
        try {
            
            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            long increment = 0;
            long tuning = 0;
            String starttime = "";
            String type = "";
            
            for (int id : ids) {
            	if(ids.indexOf(id) == 0){
            		starttime = getIdTime(id);
            	}else{ 
            		type = getIdType(id);
            		
            		if(type.equals("A")){
    	            	increment = increment + 1;
    	            	tuning = (tuning + 1);
            		}else{
    	            	increment = increment + time;
            		}
            		
            		if((ids.indexOf(id)) == (ids.size()-1)){
            			ps.setString(1,starttime);
    	            	ps.setLong(2,increment-tuning);
    	                ps.setInt(3, id);
            		}else{
            			ps.setString(1,starttime);
    	            	ps.setLong(2,increment);
    	                ps.setInt(3, id);
            		}
	            	
	                ps.executeUpdate();
            	}
            }    

        } catch (SQLException Sqlex) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ TimeCalculator.updateTimeOfRecords() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ TimeCalculator.updateTimeOfRecords() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.updateTimeOfRecords() ]");
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
                	LogWriter.write("[E] Errore in classe: [ TimeCalculator.updateTimeOfRecords() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }
		
		
	}
		
}
