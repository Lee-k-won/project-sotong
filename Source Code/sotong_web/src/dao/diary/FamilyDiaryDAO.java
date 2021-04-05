package dao.diary;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class FamilyDiaryDAO implements Serializable{
	private static final long serialVersionUID = -8509569950698005672L;
	private Connection conn;
	
	public FamilyDiaryDAO() {
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	public int insertDiary(String familyDiaryCode, String familyHomeCode, String familyDiaryDate)
	{	
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, familyDiaryDate);
					
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}	
	// 가족일기 최초생성시, 가족일기를 생성하고 그것의 코드를 리턴해줘야 가족일기 개인파트를 추가할 수 있다!
	public String insertDiary(String familyHomeCode,String familyDiaryDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String code = makeCode();	// 코드 생성하는 메소드 호출.
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);	
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, familyDiaryDate);
					
			rowNum = pstmt.executeUpdate();
			//리턴결과 값에 따라 처리할 내용 추후 의논
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return code;
	}	
	
	public int delete(String familyDiaryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_diary_tb where family_diary_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	
	public FamilyDiaryVO selectDiaryInfo(String familyHomeCode, String familyDiaryDate)
	{
		FamilyDiaryVO res = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_tb where family_home_code =? and family_diary_date = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
			pstmt.setString(2, familyDiaryDate);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				res = new FamilyDiaryVO(familyDiaryCode, familyHomeCode, familyDiaryDate);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return res;
	}
	
	public FamilyDiaryVO[] selectAllDiaryInfo(String familyHomeCode)
	{
		ArrayList<FamilyDiaryVO> list = new ArrayList<FamilyDiaryVO>();
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_tb where family_home_code=?  order by family_diary_date DESC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				String familyDiaryDate = rs.getString("family_diary_date");
				
				list.add(new FamilyDiaryVO(familyDiaryCode, familyHomeCode, familyDiaryDate));
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return list.toArray(new FamilyDiaryVO[list.size()]);
	}
	
	//식별코드 생성
	public String makeCode(){
		GregorianCalendar cal = new GregorianCalendar();
		
		String familyDiaryCode = "FD02" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return familyDiaryCode;
	}

	/*
	public static void main(String[] args)
	{
		FamilyDiaryDAO dao = new FamilyDiaryDAO();
		
		
		//int res = dao.insertDiary("h1", new Date());
		
		//System.out.println("insert 결과 : " + res);
		
		
		//int res = dao.insertDiary("fdc4", "h1", new Date());
		
		//System.out.println("insert 결과 : " + res);
		
		FamilyDiaryVO[] diary = dao.selectAllDiaryInfo("h1");
		
		System.out.println("모든 가족일기 출력");
		for(FamilyDiaryVO temp : diary)
		{
			System.out.println(temp);
		}
		
		
		System.out.println("하나의 일기 결과 출력");
		
	//	System.out.println(dao.selectDiaryInfo("h1", new Date()));
		
		res = dao.delete("fdc3");
		
		System.out.println("delete 결과 : " + res);
		
		
		
	}
	 */
}
