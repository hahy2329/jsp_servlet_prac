package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		
		
		return instance;
	}
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public void getConnection() {
		
		try {
    		
    		Context initCtx = new InitialContext();
    		Context envCtx = (Context)initCtx.lookup("java:comp/env");
    		DataSource ds = (DataSource)envCtx.lookup("jdbc/pool2");
    		conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	   public void getClose() {
	    	if (rs != null)    {try {rs.close();}   catch (SQLException e) {}}
	    	if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
	        if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
	    }
	    
	
	
	
	
}
