package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {
	
	private String item;
    private String itemshort;
    private String itemexists;
    private String workorder;

    
    public Item(String item) {
    
        this.item = item;
    
    }
    
    public void setItem(String item) {

        this.item = item;

    }

    public String getItem() {

        return item;

    }
    
    public String checkIfItemExists(Item item) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM F4801 WHERE TRIM(WALITM) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, item.getItem());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    itemexists = "true";
                } else {
                    itemexists = "false";
                }
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Item.checkIfItemExists() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Item.checkIfItemExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Item.checkIfItemExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Item.checkIfItemExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return itemexists;

    }
   
    public String getItemShort(Item item) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT WAITM FROM F4801 WHERE TRIM(WALITM) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, item.getItem());
            
            rs = ps.executeQuery();

            while (rs.next()) {
                itemshort = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Item.getItemShort() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Item.getItemShort() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Item.getItemShort() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Item.getItemShort() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return itemshort;

    }
    
     public void setItemShort(String itemshort) {
         
         this.itemshort = itemshort;
         
     }

    public String getWorkOrder() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;

        sql = "SELECT WADOCO FROM F4801 WHERE WADCTO IN ('WA','WS','WT','WH','WI','WO','WK','WJ') AND WASRST < '94' AND TRIM(WALITM) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, item);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                workorder = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Item.getWorkOrder() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ Item.getWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ Item.getWorkOrder() ]");
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
                	LogWriter.write("[E] Errore in classe: [ Item.getWorkOrder() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");;
                }
            }

        }

        return workorder;

    }

}
