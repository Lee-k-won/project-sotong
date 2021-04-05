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

public class EmoticonCategoryDAO implements Serializable{

	private static final long serialVersionUID = -8292871749458474114L;
		private DBConnectionModule connModule;
		private Connection conn;
		
		public EmoticonCategoryDAO() {
			connModule = DBConnectionModule.getInstance();
			conn = connModule.getConn();
		}
		
		public String makeEmoticonCode(){ //임시코드
			GregorianCalendar cal = new GregorianCalendar();
			
			String emoticonCategoryCode = "EC16" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
			
			return emoticonCategoryCode;
		}
		
		public int insertEmoticonCategory(String EmoticonCategory)
		{
			int rowNum = 0;
			PreparedStatement pstmt = null;
			try{
				String sql = "insert into Emoticon_category_tb values(?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, makeEmoticonCode());
				pstmt.setString(2, EmoticonCategory);
							
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
		
		public int updateEmoticonCategory(String EmoticonCode, String EmoticonCategory){
			int rowNum = 0;
			PreparedStatement pstmt = null;
			try{
				String sql = "update Emoticon_category_tb set Emoticon_category=? where Emoticon_category_code=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, EmoticonCategory);
				pstmt.setString(2, EmoticonCode);
				
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
		
		public int updateEmoticonCategoryByCategory(String EmoticonCategory, String changeCategory){
			int rowNum = 0;
			PreparedStatement pstmt = null;
			try{
				String sql = "update Emoticon_category_tb set Emoticon_category=? where Emoticon_category=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, changeCategory);
				pstmt.setString(2, EmoticonCategory);
				
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
		
		public int deleteEmoticonCategory(String EmoticonCode){
			int rowNum = 0;
			PreparedStatement pstmt = null;
			try{
				String sql = "delete from Emoticon_category_tb where Emoticon_category_code=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, EmoticonCode);
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
		
		public int deleteEmoticonCategoryByCategory(String EmoticonCategory){
			int rowNum = 0;
			PreparedStatement pstmt = null;
			try{
				String sql = "delete from Emoticon_category_tb where Emoticon_category=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, EmoticonCategory);
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
		
		public EmoticonCategoryVO selectEmoticonCategory(String EmoticonCode)
		{
			EmoticonCategoryVO vo = null;
			PreparedStatement pstmt = null;
			try{
				String sql = "select * from Emoticon_category_tb where Emoticon_category_code=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, EmoticonCode);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
									
					String EmoticonCategory = rs.getString("Emoticon_category");
			
					vo = new EmoticonCategoryVO(EmoticonCode,EmoticonCategory);
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
		
		public EmoticonCategoryVO selectEmoticonCategoryByCategory(String EmoticonCategory)
		{
			EmoticonCategoryVO vo = null;
			PreparedStatement pstmt = null;
			try{
				String sql = "select * from Emoticon_category_tb where Emoticon_category=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, EmoticonCategory);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					
					
					String EmoticonCode = rs.getString("Emoticon_category_code");
			
					vo = new EmoticonCategoryVO(EmoticonCode,EmoticonCategory);
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
			EmoticonCategoryDAO dao = new EmoticonCategoryDAO();
			System.out.println("삽입"+dao.insertEmoticonCategory("연락"));
			System.out.println("조회-카테고리명"+dao.selectEmoticonCategoryByCategory("안부"));
			System.out.println("수정"+dao.updateEmoticonCategory("ec2", "우울"));
			System.out.println("조회-코드"+dao.selectEmoticonCategory("ec2"));
			System.out.println("삭제"+dao.deleteEmoticonCategory("40003015793351"));
			System.out.println("삭제-카테고리명" +dao.deleteEmoticonCategoryByCategory("축하"));
			System.out.println("조회-코드"+dao.selectEmoticonCategoryByCategory("사랑"));
		}
		
	}

