package boardAdvance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import boardAdvance.dto.MainBoardDTO;
import login.dto.MemberDTO;

public class BoardAdvanceDAO {
	private BoardAdvanceDAO() {}
	
	private static BoardAdvanceDAO instance = new BoardAdvanceDAO();
	
	public static BoardAdvanceDAO getInstance() {
		
		return instance;
		
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void getConnection() {
		
		try {
			
			Context initctx = new InitialContext();
			Context envctx = (Context)initctx.lookup("java:comp/env");
			DataSource ds = (DataSource)envctx.lookup("jdbc/pool3");
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
	
	public int getAllBoardCnt(String searchKeyWord, String searchWord) {
		
		int totalBoardCnt = 0;
		
		try {
			getConnection();
			
			String sql = "";
			if(searchKeyWord.equals("total")) {
				if(searchWord.equals("")) {
					sql = "SELECT COUNT(*) FROM MAIN_BOARD";
					pstmt = conn.prepareStatement(sql);
				}
				else {
					sql = "SELECT COUNT(*) FROM MAIN_BOARD";
					sql += "WHERE WRITER LIKE CONCAT('%', ? ,'%')";
					sql +="OR SUBJECT LIKE CONCAT('%', ?, '%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
				}
			}
			
			else {
				
				sql = "SELECT COUNT(*) FROM MAIN_BOARD WHERE" + searchKeyWord + "LIKE CONCAT('%', ?, '%')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchWord);
				
				
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalBoardCnt = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return totalBoardCnt;
		
	}
	
	public ArrayList<MainBoardDTO> getBoardList(String searchKeyWord, String searchWord, int startBoardIdx, int onePageViewCnt ){
		ArrayList<MainBoardDTO> boardList = new ArrayList<MainBoardDTO>();
		
		try {
			getConnection();
			
			String sql = "";
			
			if(searchKeyWord.equals("total")) {
				if(searchWord.equals("")) {
					sql = "SELECT * FROM MAIN_BOARD ORDER BY ENROLL_DT DESC LIMIT = ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, startBoardIdx);
					pstmt.setInt(2, onePageViewCnt);
					
				}
				else {
					sql = "SELECT * FROM MAIN_BOARD";
					sql +="WHERE SUBJECT LIKE CONCAT('%', ?, '%')";
					sql +="OR WRITER LIKE CONCAT('%', ?, '%')";
					sql +="ORDER BY ENROLL_DT DESC";
					sql +="LIMIT ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					pstmt.setInt(3, startBoardIdx);
					pstmt.setInt(4, onePageViewCnt);
				}
			}
			
			else {
				sql = "SELECT * FROM MAIN_BOARD" +searchKeyWord + "LIKE CONCAT('%',?,'%') ORDER BY ENROLL_DT DESC LIMIT = ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, startBoardIdx);
				pstmt.setInt(3, onePageViewCnt);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MainBoardDTO mainBoardDTO =new MainBoardDTO();
				mainBoardDTO.setBoardId(rs.getLong("BOARD_ID"));
				mainBoardDTO.setWriter(rs.getString("WRITER"));
				mainBoardDTO.setSubject(rs.getString("SUBJECT"));
				mainBoardDTO.setContent(rs.getString("CONTENT"));
				mainBoardDTO.setReadCnt(rs.getLong("READ_CNT"));
				boardList.add(mainBoardDTO);
				
				
				
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return boardList;
	}
	
	
}
