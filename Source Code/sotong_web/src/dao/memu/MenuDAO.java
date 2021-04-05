package dao.memu;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class MenuDAO implements Serializable{

	private static final long serialVersionUID = 4928199630700378540L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public MenuDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public MenuDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode(){
		GregorianCalendar cal = new GregorianCalendar();
		
		String menuCode = "MD08" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return menuCode;
			
	}
	
	public int insertMenu(String menuCode, String dinner, String shareDate){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = shareDate;
		try{
			String sql = "insert into menu_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,menuCode);
			pstmt.setString(2, dinner);
			pstmt.setString(3, nowDate);
			
					
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
	
	public int updateMenu(String menuCode, String dinner, String shareDate){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		 String nowDate = shareDate;
		
		
		try{
			String sql = "update menu_tb set dinner=?,share_date=? where menu_code=?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, dinner);
			pstmt.setString(2, nowDate);
			pstmt.setString(3, menuCode);
			
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
	
	public int deleteCode(String menuCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from menu_tb where menu_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuCode);
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
	
	public MenuVO selectCode(String menuCode){
		MenuVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from menu_tb where menu_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String code = rs.getString("menu_code");
				String dinner = rs.getString("dinner");
				String shareDate = rs.getString("share_date");

				vo = new MenuVO(code,dinner,shareDate);
				System.out.println(vo.getShareDate());
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
		MenuDAO dao = new MenuDAO();
		
	//	System.out.println(dao.selectCode("menu1"));
		System.out.println(dao.deleteCode("menu1"));
		System.out.println(dao.deleteCode("200029157195248"));
		//System.out.println(dao.updateMenu("menu1", "masasas1", new Date()));
		//System.out.println(dao.updateMenu("200029157195113", "masasas1", new Date()));
	}
}
