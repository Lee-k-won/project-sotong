package dao.memu;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class LunchDAO implements Serializable{

	private static final long serialVersionUID = -5441879204717238625L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public LunchDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public LunchDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode() {
		GregorianCalendar cal = new GregorianCalendar();
		
		String lunchCode = "LD07" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return lunchCode;
	}
	
	public int insertLunch(String lunchCode, String memberCode, String menuCode, String lunch) {		
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into lunch_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,lunchCode);
			pstmt.setString(2, memberCode);
			pstmt.setString(3, menuCode);
			pstmt.setString(4,lunch);
			
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
	
	public int updateLunch (String lunchCode, String memberCode, String menuCode, String lunch) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "update lunch_tb set member_code=?, menu_code =?, lunch = ? where lunch_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, memberCode);
			pstmt.setString(2, menuCode);
			pstmt.setString(3, lunch);
			pstmt.setString(4, lunchCode);
			
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
	
	public int deleteCode(String lunchCode) {
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from lunch_tb where LUNCH_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lunchCode);
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
	
	public LunchVO selectCode(String code){
		LunchVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from lunch_tb where lunch_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
			
				String memberCode = rs.getString("member_code");
				String menuCode = rs.getString("menu_code");
				String lunch = rs.getString("lunch");
				
				vo = new LunchVO(code,memberCode,menuCode,lunch);
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
		
	
	public static void main(String[] args) {
		LunchDAO dao = new LunchDAO();
		
		System.out.println(dao.deleteCode("lunch1"));
		System.out.println(dao.insertLunch("chea1133l", "m2", "menu2", "아이스 초코라떼"));
		System.out.println(dao.updateLunch("lunch3", "m1", "menu1", "emfhekfm"));
		System.out.println(dao.selectCode("lunch4"));
		
	}
}
