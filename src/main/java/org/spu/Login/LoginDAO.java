package org.spu.Login;



import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;



import oracle.jdbc.OracleTypes;

@Repository
public class LoginDAO {

	@Autowired
	DataSource dataSource;

	public List<LoginBean> fetchlogin() throws SQLException {
		
		CallableStatement callstmnt = null;

		Connection conn = null;

//		String selectSQL ="{ call  HOSTEL_DB.HOSTEL_LOGIN_CURSOR(?) }";
		String selectSQL = "begin HOSTEL_DB.HOSTEL_LOGIN_CURSOR(?);end; ";
		List<LoginBean> loginlist = new ArrayList<LoginBean>();

		try {

			conn = dataSource.getConnection();

			callstmnt =null;
			callstmnt  = (CallableStatement)conn.prepareCall( selectSQL);
			callstmnt.registerOutParameter(1, OracleTypes.CURSOR);
		
				callstmnt.executeQuery();
			
				ResultSet rs =(ResultSet)callstmnt.getObject(1);	
				
						while (rs.next()) {

				LoginBean loginobj = new LoginBean();
				loginobj.setLogin_id(rs.getBigDecimal("login_id"));
				loginobj.setLogin_name(rs.getString("login_name"));
				loginobj.setLogin_email(rs.getString("login_email"));
				loginobj.setLogin_password(rs.getString("login_password"));
				loginobj.setLogin_rank(rs.getString("login_rank"));
				
							loginlist.add(loginobj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return loginlist;
	}

	public List<LoginBean> createlogin( BigDecimal login_id,String login_name, String login_email, String login_password,String login_rank) throws SQLException {
		

		Connection conn = null;
		
		List<LoginBean> loginList = new ArrayList<LoginBean>();

		try {

			conn = dataSource.getConnection();
			CallableStatement callStmnt = null;
			

			callStmnt  = (CallableStatement)conn.prepareCall("{ call  HOSTEL_DB_LOGIN_PKG.createlogin(?,?,?,?,?) }");
			callStmnt.registerOutParameter(1, OracleTypes.DECIMAL);
			callStmnt.setString(2, login_name);
			callStmnt.setString(3, login_email);
			callStmnt.setString(4, login_password);
			callStmnt.setString(5, login_rank);
			
			callStmnt.executeUpdate();
	

			callStmnt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		loginList = fetchlogin();
		return loginList;
		
	}

	public List<LoginBean> updatelogin(BigDecimal login_id, String login_name, String login_email, String login_password,String login_rank) throws SQLException {
	
		Connection conn = null;
	
		List<LoginBean> loginList = new ArrayList<LoginBean>();
		try {

			conn = dataSource.getConnection();
			CallableStatement callStmnt = null;
			

			callStmnt  = (CallableStatement)conn.prepareCall("{ call  HOSTEL_DB_LOGIN_PKG.updatelogin(?,?,?,?,?) }");
			callStmnt.setBigDecimal(1, login_id);
			callStmnt.setString(2, login_name);
			callStmnt.setString(3, login_email);
			callStmnt.setString(4, login_password);
			callStmnt.setString(5, login_rank);
			
			callStmnt.executeUpdate();
			callStmnt .close();
				conn.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		} 
		loginList = fetchlogin();
		return loginList;

		}

	public List<LoginBean> deletelogin(BigDecimal login_id) throws SQLException {
		CallableStatement cst = null;

		Connection conn = null;

		String selectSQL = "{ call  HOSTEL_DB_LOGIN_PKG.deletelogin(?) }";
		List<LoginBean> loginList = new ArrayList<LoginBean>();

		try {

			conn = dataSource.getConnection();

			cst = (CallableStatement)conn.prepareCall(selectSQL);
			cst.setBigDecimal(1,login_id);
		cst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		loginList = fetchlogin();
		return loginList;
		}


}
