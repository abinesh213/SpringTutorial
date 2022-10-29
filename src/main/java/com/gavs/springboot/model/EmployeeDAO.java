package com.gavs.springboot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class EmployeeDAO {
	
	public static Connection myConnection() {
		String MYSQL_DRIVERNAME="com.mysql.cj.jdbc.Driver";
		String MYSQL_CONNECTION_URL="jdbc:mysql://localhost:3306/trainingproject";
		String strUserName="root";
        String strPassword="admin";
        try {
			Class.forName(MYSQL_DRIVERNAME);
			return DriverManager.getConnection(MYSQL_CONNECTION_URL,strUserName,strPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int addEmployee(Employee emp) {
		try {
			String sql = "INSERT INTO EMPLOYEEDETAILS VALUES (?,?)";
			PreparedStatement ps = myConnection().prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getName());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			if (myConnection() != null) {
				try {
					myConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public List<Employee> fetchEmployeeDAO() throws SQLException{
		ResultSet rs = null;
		List<Employee> employeeList = null;
		Employee emp = null;
		try {
			String sql = "SELECT ID,NAME FROM EmployeeDETAILS";
			PreparedStatement ps = myConnection().prepareStatement(sql);
			rs = ps.executeQuery(sql);
			employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				employeeList.add(emp);
			}
			return employeeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (myConnection() != null) {
				myConnection().close();
			}
		}
		
	}
	
	public Employee findEmployee(Employee med) {
		ResultSet rs = null;
		Employee mediDetails=null;
		try {
			String sql = "SELECT ID,NAME FROM EmployeeDETAILS WHERE ID=?";
			PreparedStatement ps = myConnection().prepareStatement(sql);
			ps.setInt(1, med.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				mediDetails = new Employee();
				mediDetails.setId(rs.getInt(1));
				mediDetails.setName(rs.getString(2));
			}
			
			return mediDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (myConnection() != null) {
				try {
					myConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public int modifyEmployee(Employee emp) {
		try {
			String sql = "UPDATE `employeedetails` SET `NAME` = ? WHERE (`ID` = ?);";
			PreparedStatement ps = myConnection().prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getId());
			int res = ps.executeUpdate();
			return res;
		} catch (Exception e) {
			return 0;
		}finally {
			if (myConnection() != null) {
				try {
					myConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public int deleteEmployee(Employee emp) {
		try {
			String sql = "DELETE FROM EMPLOYEEDETAILS WHERE ID=?";
			PreparedStatement ps = myConnection().prepareStatement(sql);
			ps.setInt(1, emp.getId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (myConnection() != null) {
				try {
					myConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
	
	
	

