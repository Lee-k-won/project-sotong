package dao.sotong;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class SotongCategoryDAO implements Serializable{

	private static final long serialVersionUID = 2501895732722185319L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public SotongCategoryDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeSotongCode(){ //�ӽ��ڵ�
		GregorianCalendar cal = new GregorianCalendar();
		
		String sotongCategoryCode = "SC20" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return sotongCategoryCode;
	}
	
	public int insertSotongCategory(String SotongCategory)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into sotong_category_tb values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeSotongCode());
			pstmt.setString(2, SotongCategory);
						
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int updateSotongCategory(String sotongCode, String sotongCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update sotong_category_tb set sotong_category=? where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongCategory);
			pstmt.setString(2, sotongCode);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int updateSotongCategoryByCategory(String SotongCategory, String changeCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update sotong_category_tb set sotong_category=? where sotong_category=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, changeCategory);
			pstmt.setString(2, SotongCategory);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int deleteSotongCategory(String sotongCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from sotong_category_tb where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongCode);
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int deleteSotongCategoryByCategory(String SotongCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from sotong_category_tb where sotong_category=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, SotongCategory);
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public SotongCategoryVO selectSotongCategory(String sotongCode)
	{
		SotongCategoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from sotong_category_tb where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sotongCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String SotongCategory = rs.getString("sotong_category");
		
				vo = new SotongCategoryVO(sotongCode,SotongCategory);
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
	
	public SotongCategoryVO selectSotongCategoryByCategory(String SotongCategory)
	{
		SotongCategoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from sotong_category_tb where sotong_category=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SotongCategory);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				
				String sotongCode = rs.getString("sotong_category_code");
		
				vo = new SotongCategoryVO(sotongCode,SotongCategory);
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
	
	public static void main(String[] args)
	{
		SotongCategoryDAO dao = new SotongCategoryDAO();
		System.out.println("����"+dao.insertSotongCategory("����"));
		System.out.println("��ȸ-ī�װ���"+dao.selectSotongCategoryByCategory("�ϱ�"));
		System.out.println("����"+dao.updateSotongCategory("sc2", "�����ϱ�"));
		System.out.println("��ȸ-�ڵ�"+dao.selectSotongCategory("sc2"));
		System.out.println("����"+dao.deleteSotongCategory("sc1"));
		System.out.println("��ȸ-�ڵ�"+dao.selectSotongCategoryByCategory("����"));
	}
	
}
