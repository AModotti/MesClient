package bin;

import ide.main.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkCenter {

	private String workcenterexists;
	private String workcentername;
	private String workcenterdescription;
	private String enableditemselection;
	private String workcentermode;
	private String workcenterdefaultdept;
	private String workcentermultiorder;
	private String setworkcenterdescription;
	private String workcenterhasaggregation;
	private String workcenterhasaggregations;
	private String workcenteroperatoraggregation;
	private String workcenteroperatordiaggregation;
	private String workcenteroperatoraggregationdescription;
	private String workcenteropentransactions;
	private String operatorisaggregable;
	private String workcentersuspendedtransactions;
	private String operatoraggregatedtoworkcenter;

	private IpAddress ipaddress;

	public WorkCenter(IpAddress ipaddress) {

		this.ipaddress = ipaddress;

	}

	public void setWorkCenter(IpAddress ipaddress) {

		this.ipaddress = ipaddress;

	}

	public IpAddress getWorkCenter() {

		return ipaddress;

	}

	public String getWorkCenterName(IpAddress ipaddress) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT TRIM(ISUKID) FROM F55FQ10006 WHERE TRIM(ISADDR) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, ipaddress.getIpAddress());

			rs = ps.executeQuery();

			workcentername = null;

			while (rs.next()) {
				workcentername = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterName() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getWorkCenterName() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterName() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterName() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcentername;

	}

	public String checkIfWorkCenterHasEnabledTheItemSelection(IpAddress ipaddress) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT ISENABLEDITEMSEL FROM F55FQ10006 WHERE TRIM(ISADDR) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, ipaddress.getIpAddress());

			rs = ps.executeQuery();

			while (rs.next()) {
				enableditemselection = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasEnabledTheItemSelection() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkIfWorkCenterHasEnabledTheItemSelection() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasEnabledTheItemSelection() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasEnabledTheItemSelection() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return enableditemselection;

	}

	public String getWorkCenterMode(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT ISMODE FROM F55FQ10006 WHERE TRIM(ISUKID) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {
				workcentermode = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterMode() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getWorkCenterMode() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterMode() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterMode() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcentermode;

	}

	public String getWorkCenterDefaultDept(WorkCenter workcenter) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        
        sql = "SELECT TRIM(ISDEPT) FROM F55FQ10006 WHERE TRIM(ISADDR) = ?";
        
        try {

            conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, workcenter.getWorkCenter().getIpAddress());
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	workcenterdefaultdept = rs.getString(1);
            }

        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterDefaultDept() ]");
        	LogWriter.write("[E] " + Sqlex.getMessage());
        	LogWriter.write("[E] " + sql);
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ WorkCenter.getWorkCenterDefaultDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterDefaultDept() ]");
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
                	LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterDefaultDept() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

        }

        return workcenterdefaultdept;
    }

	public String checkIfWorkCenterIsMultiOrder(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT ISMULTI FROM F55FQ10006 WHERE TRIM(ISUKID) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {
				workcentermultiorder = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterIsMultiOrder() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkIfWorkCenterIsMultiOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterIsMultiOrder() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterIsMultiOrder() ]");;
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcentermultiorder;

	}	

	public String setWorkCenterName(IpAddress ipaddress, String workcentername) {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql;

		sql = "UPDATE F55FQ10006 SET TRIM(ISUKID) = ? WHERE TRIM(ISADDR) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcentername);
			ps.setString(2, ipaddress.getIpAddress());

			ps.executeUpdate();

			workcentername = "true";

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterName() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.setWorkCenterName() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterName() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterName() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcentername;

	}

	public String checkIfWorkCenterExists(IpAddress ipaddress) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT COUNT(*) FROM F55FQ10006 WHERE TRIM(ISADDR) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, ipaddress.getIpAddress());

			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals("1")) {
					workcenterexists = "true";
				} else {
					workcenterexists = "false";
				}
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkIfWorkCenterExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterExists() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterExists() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenterexists;
	}

	public String getWorkCenterDescription(IpAddress ipaddress) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT TRIM(ISALPH) FROM F55FQ10006 WHERE TRIM(ISADDR) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, ipaddress.getIpAddress());

			rs = ps.executeQuery();

			while (rs.next()) {
				workcenterdescription = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getWorkCenterDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterDescription() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterDescription() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenterdescription;

	}

	public String setWorkCenterDescription(IpAddress ipaddress,String workcenterdescription) {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql;

		sql = "UPDATE F55FQ10006 SET ISALPH = ? WHERE TRIM(ISADDR) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenterdescription);
			ps.setString(2, ipaddress.getIpAddress());

			ps.executeUpdate();

			setworkcenterdescription = "true";

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.setWorkCenterDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterDescription() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterDescription() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return setworkcenterdescription;

	}

	public String getOperatorAggregatedToWorkCenter(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;
		
		sql = "SELECT AGAN8 FROM F55FQ10007 WHERE TRIM(AGISL) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {
				operatoraggregatedtoworkcenter = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getOperatorAggregatedToWorkCenter() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getOperatorAggregatedToWorkCenter() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getOperatorAggregatedToWorkCenter() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getOperatorAggregatedToWorkCenter() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return operatoraggregatedtoworkcenter;

	}

	public ArrayList<String> getOperatorsAggregatedToWorkCenter(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;
		ArrayList<String> operatorsaggregatedtoworkcenter = new ArrayList<String>();
		
		sql = "SELECT AGAN8,AGALPH FROM F55FQ10007 WHERE TRIM(AGISL) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {
				operatorsaggregatedtoworkcenter.add(rs.getString(1) + " - " + rs.getString(2));
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getOperatorsAggregatedToWorkCenter() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getOperatorsAggregatedToWorkCenter() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getOperatorsAggregatedToWorkCenter() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getOperatorsAggregatedToWorkCenter() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return operatorsaggregatedtoworkcenter;

	}
	
	public String setWorkCenterOperatorAggregation(WorkCenter workcenter,Operator operator) {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";

		if(Settings.getDriver().equalsIgnoreCase("com.mysql.jdbc.Driver")){
			
			sql = "INSERT INTO F55FQ10007 (AGISL," 
			        + "                    AGAN8," 
					+ "                    AGALPH,"
					+ "                    AGTRDJ) " 
					+ "            VALUES (?," 
					+ "                    ?,"
					+ "                    ?," 
					+ "                    STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'))";

		}else if(Settings.getDriver().equalsIgnoreCase("oracle.jdbc.driver.OracleDriver")){
			
			sql = "INSERT INTO F55FQ10007 (AGISL," 
			        + "                    AGAN8," 
					+ "                    AGALPH,"
					+ "                    AGTRDJ) " 
					+ "            VALUES (?," 
					+ "                    ?,"
					+ "                    ?," 
					+ "                    TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'))";

		}
		
		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
			ps.setString(2, operator.getOperator());
			ps.setString(3, operator.getOperatorDescription(operator));
			ps.setString(4, Settings.getTimestamp());

			ps.executeUpdate();

			workcenteroperatoraggregation = "true";

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterOperatorAggregation() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.setWorkCenterOperatorAggregation() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterOperatorAggregation() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterOperatorAggregation() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenteroperatoraggregation;

	}

	public String setWorkCenterOperatorDisaggregation(WorkCenter workcenter,Operator operator) {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql;

		sql = "DELETE FROM F55FQ10007 WHERE AGAN8 = ? AND TRIM(AGISL) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, operator.getOperator());
			ps.setString(2, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			ps.executeUpdate();

			workcenteroperatordiaggregation = "true";

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterOperatorDisaggregation() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.setWorkCenterOperatorDisaggregation() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterOperatorDisaggregation() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.setWorkCenterOperatorDisaggregation() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenteroperatordiaggregation;

	}

	public String getWorkCenterOperatorAggregatedDescription(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT TRIM(AGALPH) FROM F55FQ10007 WHERE TRIM(AGISL) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {
				workcenteroperatoraggregationdescription = rs.getString(1);
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterOperatorAggregatedDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getWorkCenterOperatorAggregatedDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterOperatorAggregatedDescription() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getWorkCenterOperatorAggregatedDescription() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenteroperatoraggregationdescription;

	}

	public String checkIfWorkCenterHasSpecificAggregation(WorkCenter workcenter,Operator operator) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		if(workcenter.getWorkCenterMode(workcenter).equals("1")) {

			sql = "SELECT COUNT(*) FROM F55FQ10007 WHERE TRIM(AGISL) = ? AND AGAN8 = ?";

		}else{

			sql = "SELECT COUNT(*) FROM F55FQ10007 WHERE TRIM(AGISL) = ?";

		}

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			if (workcenter.getWorkCenterMode(workcenter).equals("1")) {

				ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
				ps.setString(2, operator.getOperator());

			} else {

				ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			}

			rs = ps.executeQuery();

			while (rs.next()) {
				if (!rs.getString(1).equals("0")) {
					workcenterhasaggregation = "true";
				} else {
					workcenterhasaggregation = "false";
				}

			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasSpecificAggregation() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkIfWorkCenterHasSpecificAggregation() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasSpecificAggregation() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasSpecificAggregation() ]");;
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenterhasaggregation;

	}

	public String checkIfOperatorIsAggregable(WorkCenter workcenter,Operator operator) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		if (workcenter.getWorkCenterMode(workcenter).equals("1")) {

			sql = "SELECT COUNT(*) FROM F55FQ10008 WHERE TRIM(IMUKID) = ? AND IMAN8 = ?";

			try {

				conn = Settings.getDbConnection();
				ps = conn.prepareStatement(sql);

				ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
				ps.setString(2, operator.getOperator());

				rs = ps.executeQuery();

				while (rs.next()) {
					if (!rs.getString(1).equals("0")) {
						operatorisaggregable = "true";
					} else {
						operatorisaggregable = "false";
					}

				}

			} catch (SQLException Sqlex) {
				
				LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfOperatorIsAggregable() ]");
				LogWriter.write("[E] " + Sqlex.getMessage());
				LogWriter.write("[E] " + sql);
				LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				Notifications notification = new Notifications("[ WorkCenter.checkIfOperatorIsAggregable() ]",Sqlex.getMessage(),sql);
	        	notification.setModal(true);
	        	notification.setVisible(true);

			} finally {

				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
						LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfOperatorIsAggregable() ]");
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
						LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfOperatorIsAggregable() ]");
						LogWriter.write("[E] Chiusura Connection");
						LogWriter.write("[E] " + e.getMessage());
						LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					}
				}

			}
		}else if(workcenter.getWorkCenterMode(workcenter).equals("0")) {

			operatorisaggregable = "true";
		}

		return operatorisaggregable;

	}

	public String checkIfWorkCenterHasAggregations(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		sql = "SELECT COUNT(*) FROM F55FQ10007 WHERE TRIM(AGISL) = ?";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {
				if (!rs.getString(1).equals("0")) {
					workcenterhasaggregations = "true";
				} else {
					workcenterhasaggregations = "false";
				}

			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasAggregations() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkIfWorkCenterHasAggregations() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasAggregations() ]");
					LogWriter.write("[E] Chiusura PreparedStatement");;
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkIfWorkCenterHasAggregations() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenterhasaggregations;

	}

	public String checkOpenTransactions(WorkCenter workcenter,Operator operator) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql = null;

		if (workcenter.getWorkCenterMode(workcenter).equals("1")) {

			sql = "SELECT COUNT(*) " + "  FROM(SELECT MAX(ADUKID) AS ID," + "              ADISL,"
					+ "              ADAN8," + "              ADDOCO," + "              ADOPSQ"
					+ "         FROM F55FQ10001" + "        WHERE EXISTS(SELECT 'X'"
					+ "                       FROM F55FQ10002" + "                      WHERE USYQ10USST IN ('3','4')"
					+ "                        AND USAN8 = ADAN8" + "                        AND USDOCO = ADDOCO"
					+ "                        AND USOPSQ = ADOPSQ" + "                        AND USISL = ADISL)"
					+ "           AND TRIM(ADISL) = ?" + "           AND ADAN8 = ?"
					+ "           AND ADYQ10DECD IN ('LAV','ATT')" + "         GROUP BY ADISL,ADAN8,ADDOCO,ADOPSQ"
					+ "         ORDER BY MAX(ADUKID) DESC) X";

		} else if (workcenter.getWorkCenterMode(workcenter).equals("0")) {

			sql = "SELECT COUNT(*) " + "  FROM(SELECT MAX(ADUKID) AS ID," + "              ADISL,"
					+ "              ADAN8," + "              ADDOCO," + "              ADOPSQ"
					+ "         FROM F55FQ10001" + "        WHERE EXISTS(SELECT 'X'"
					+ "                       FROM F55FQ10002" + "                      WHERE USYQ10USST IN ('3','4')"
					+ "                        AND USAN8 = ADAN8" + "                        AND USDOCO = ADDOCO"
					+ "                        AND USOPSQ = ADOPSQ" + "                        AND USISL = ADISL)"
					+ "           AND TRIM(ADISL) = ?" + "           AND ADURAB = ?"
					+ "           AND ADYQ10DECD IN ('LAV','ATT')" + "         GROUP BY ADISL,ADAN8,ADDOCO,ADOPSQ"
					+ "         ORDER BY MAX(ADUKID) DESC) X";

		}

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
			ps.setString(2, operator.getOperator());

			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals("0")) {
					workcenteropentransactions = "false";
				} else {
					workcenteropentransactions = "true";
				}
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkOpenTransactions() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkOpenTransactions() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkOpenTransactions() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkOpenTransactions() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenteropentransactions;

	}

	public ArrayList<String> getIdOpenTransactions(WorkCenter workcenter) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql;

		ArrayList<String> workcenteractualopenidtransactions = new ArrayList<String>();

		sql = "SELECT X.ID " 
		    + "  FROM(SELECT MAX(ADUKID) AS ID," 
		    + "              ADISL, " 
		    + "              ADAN8, "
			+ "              ADDOCO, " 
		    + "              ADOPSQ " 
			+ "         FROM F55FQ10001 "
			+ "        WHERE EXISTS(SELECT 'X' " 
			+ "                       FROM F55FQ10002 "
			+ "                      WHERE USYQ10USST IN ('3','4') " 
			+ "                        AND USAN8 = ADAN8 "
			+ "                        AND USDOCO = ADDOCO " 
			+ "                        AND USOPSQ = ADOPSQ "
			+ "                        AND USISL = ADISL) " 
			+ "           AND TRIM(ADISL) = ? "
			+ "           AND ADYQ10DECD IN ('LAV','ATT') " 
			+ "         GROUP BY ADISL,ADAN8,ADDOCO,ADOPSQ "
			+ "         ORDER BY MAX(ADUKID) DESC) X";

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			rs = ps.executeQuery();

			while (rs.next()) {

				workcenteractualopenidtransactions.add(rs.getString(1));

			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.getIdOpenTransactions() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.getIdOpenTransactions() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getIdOpenTransactions() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.getIdOpenTransactions() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcenteractualopenidtransactions;

	}

	public String checkSuspendedTransactions(WorkCenter workcenter,Operator operator) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String sql = null;

		if (workcenter.getWorkCenterMode(workcenter).equals("1")) {

			sql = "SELECT COUNT(*) " 
			    + "  FROM F55FQ10002 " 
				+ " WHERE USDOCO != 0 " 
			    + "   AND USSOS != 0"
				+ "   AND TRIM(USISL) = ?" 
			    + "   AND USAN8 = ?";

		} else if (workcenter.getWorkCenterMode(workcenter).equals("0")) {

			sql = "SELECT COUNT(*) " 
			    + "  FROM F55FQ10002 " 
				+ " WHERE USDOCO != 0 " 
			    + "   AND USSOS != 0"
				+ "   AND TRIM(USISL) = ?";

		}

		try {

			conn = Settings.getDbConnection();
			ps = conn.prepareStatement(sql);

			if (workcenter.getWorkCenterMode(workcenter).equals("1")) {

				ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));
				ps.setString(2, operator.getOperator());

			} else if (workcenter.getWorkCenterMode(workcenter).equals("0")) {

				ps.setString(1, workcenter.getWorkCenterName(workcenter.getWorkCenter()));

			}

			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals("0")) {
					workcentersuspendedtransactions = "false";
				} else {
					workcentersuspendedtransactions = "true";
				}
			}

		} catch (SQLException Sqlex) {

			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ WorkCenter.checkSuspendedTransactions() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			Notifications notification = new Notifications("[ WorkCenter.checkSuspendedTransactions() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkSuspendedTransactions() ]");
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
					LogWriter.write("[E] Errore in classe: [ WorkCenter.checkSuspendedTransactions() ]");
					LogWriter.write("[E] Chiusura Connection");
					LogWriter.write("[E] " + e.getMessage());
					LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
				}
			}

		}

		return workcentersuspendedtransactions;

	}

}
