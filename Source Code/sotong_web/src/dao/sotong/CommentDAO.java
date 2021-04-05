package dao.sotong;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class CommentDAO implements Serializable{

	private static final long serialVersionUID = 5884280723578985557L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public CommentDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public CommentDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode() {
		GregorianCalendar cal = new GregorianCalendar();
		
		String commentCode = "CD15" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return commentCode;
	}
	
	public int insertComment(String commentCode, String memberCode, String commentcontents,
			Date commentDate, String emoticonCode, String familyDiaryCode, String storyCode) {
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = sendChangeDate(commentDate);
		
		try{
			String sql = "insert into comment_tb values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,commentCode);
			pstmt.setString(2, memberCode);
			pstmt.setString(3, commentcontents);
			pstmt.setString(4,nowDate);
			pstmt.setString(5, emoticonCode);
			pstmt.setString(6, familyDiaryCode);
			pstmt.setString(7,storyCode);
			
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	public String sendChangeDate(Date dateTime) {
		int year= dateTime.getYear() - 100;
		String mon = (dateTime.getMonth()+1)>9 ? "" + (dateTime.getMonth()+1) : "0" + (dateTime.getMonth()+1);
		String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
	    String hour = dateTime.getHours()>9 ? "" + dateTime.getHours() : "0" + dateTime.getHours();
	    
	    String min = dateTime.getMinutes()>9 ? "" + dateTime.getMinutes() : "0" + dateTime.getMinutes();
	    
		return  year + "-" + mon +"-" + day + "-" + hour + ":" + min; 
	}
	
	public int updateComment(String commentCode, String memberCode, String commentcontents,
	Date commentDate, String emoticonCode, String familyDiaryCode, String storyCode) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = sendChangeDate(commentDate);
		
		
		try{
			String sql = "update comment_tb set member_code=?, comment_contents =?, comment_date = ?,"
					+ " emoticon_code=?, family_diary_code = ?, story_code=? where comment_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, memberCode);
			pstmt.setString(2, commentcontents);
			pstmt.setString(3, nowDate);
			pstmt.setString(4, emoticonCode);
			pstmt.setString(5, familyDiaryCode);
			pstmt.setString(6, storyCode);
			pstmt.setString(7, commentCode);
			rowNum = pstmt.executeUpdate();
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	public int deleteCode(String commentCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from comment_tb"
					+ " where COMMENT_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commentCode);
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	public CommentVO selectCode(String code){
		CommentVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from comment_tb where comment_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String commentCode = rs.getString("comment_code");
				String memberCode = rs.getString("member_code");
				String commentComtents = rs.getString("comment_contents");
				String commentDate = rs.getString("comment_date");
				String emoticonCode = rs.getString("emoticon_code");
				String familyDiaryCode = rs.getString("family_diary_code");
				String storyCode = rs.getString("story_code");
				
				
				String writenDate = commentDate;

				vo = new CommentVO(commentCode,memberCode,commentComtents,writenDate,
						emoticonCode,familyDiaryCode,storyCode);
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return vo;
	}
	
	//manager 도출 후 메소드 추가
	public boolean addFamilyDiaryComment(String memberCode, String commentContens, Date commentDate,
			String code, String emoticonCode) {

		PreparedStatement pstmt = null;
		int rowNum = 0;
		String nowDate = sendChangeDate(commentDate);
		String commentCode = makeMenuCode();
		
		try{
			String sql = "insert into comment_tb values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,commentCode);
			pstmt.setString(2, memberCode);
			pstmt.setString(3, commentContens);
			pstmt.setString(4, nowDate);
			pstmt.setString(5, emoticonCode);
			pstmt.setString(6, code);
			pstmt.setString(7, null);
			
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		if (rowNum != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addStoryComment(String memberCode, String commentContens, Date commentDate,
			String code, String emoticonCode) {
		
		PreparedStatement pstmt = null;
		int rowNum = 0;
		String nowDate = sendChangeDate(commentDate);
		String commentCode = makeMenuCode();
		
		try{
			String sql = "insert into comment_tb values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,commentCode);
			pstmt.setString(2, memberCode);
			pstmt.setString(3, commentContens);
			pstmt.setString(4, nowDate);
			pstmt.setString(5, emoticonCode);
			pstmt.setString(6, null);
			pstmt.setString(7, code);
			
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		if (rowNum != 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean deleteAllFamilyDiaryComment(String code) {
	
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from comment_tb where FAMILY_DIARY_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String commentCode = rs.getString("comment_code");
				deleteCode(commentCode);
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if (rs != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteAllStoryComment(String code) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from comment_tb where STORY_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String commentCode = rs.getString("comment_code");
				deleteCode(commentCode);
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if (rs != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public static void main(String[] args) {
		CommentDAO dao = new CommentDAO();
		
		System.out.println(dao.deleteCode("comment2"));
		System.out.println(dao.insertComment("chea1133l", "m2", "asdsadsadasdwd", new Date(), "em1", null, "story3"));
		System.out.println(dao.updateComment("comment3", "m2", "댓글 내용 이라능~!", new Date(), "em1", "fdc2",  null));
		System.out.println(dao.selectCode("chea1133l"));
		
	}
}
