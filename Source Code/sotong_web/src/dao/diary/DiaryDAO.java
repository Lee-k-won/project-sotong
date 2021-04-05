package dao.diary;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class DiaryDAO implements Serializable{

	private static final long serialVersionUID = -9114273238905569205L;
	private Connection conn;
	
	public DiaryDAO() {
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	public int insertDiary(String diaryCode, String memberCode, String sotongContentsCode, String diaryTitle, String diaryDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into diary_tb values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, diaryCode);
			pstmt.setString(2, memberCode);
			pstmt.setString(3, sotongContentsCode);
			pstmt.setString(4, diaryTitle);
			pstmt.setString(5, diaryDate);
					
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
	
	public int insertDiary(String memberCode, String sotongContentsCode, String diaryTitle, String diaryDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into diary_tb values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeCode());	// 코드 생성하는 메소드 호출.
			pstmt.setString(2, memberCode);
			pstmt.setString(3, sotongContentsCode);
			pstmt.setString(4, diaryTitle);
			pstmt.setString(5, diaryDate);
					
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
	
	public int updateDiary(String diaryCode, String memberCode, String sotongContentsCode, String diaryTitle){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "update diary_tb set sotong_contents_code=?, diary_title=? where diary_code=? and member_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongContentsCode);
			pstmt.setString(2, diaryTitle);
			pstmt.setString(3, diaryCode);
			pstmt.setString(4, memberCode);
			
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
	
	public int delete(String diaryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from diary_tb where diary_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, diaryCode);
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
	
	//간략정보
	public String[][] selectSimpleDiaryList(String memberCode)
	{
		ArrayList<String[]> list = new ArrayList<String[]>();
		PreparedStatement pstmt = null;
		try
		{
			String sql = "select diary_code, diary_title, diary_date from diary_tb where member_code = ? order by diary_date DESC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String diaryCode = rs.getString("diary_code");
				String diaryTitle = rs.getString("diary_title");
				String diaryDate = rs.getString("diary_date");
				
				list.add(new String[] {diaryCode,diaryTitle,diaryDate});
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
		
		return list.toArray(new String[list.size()][]);
	}
	
	//하나의 일기정보를 가지고 오는 메소드
	public DiaryVO selectDiaryInfo(String diaryCode, String memberCode)
	{
		DiaryVO res = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from diary_tb where diary_code = ? and member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, diaryCode);
			pstmt.setString(2, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String diaryTitle = rs.getString("diary_title");
				String diaryTempDate = rs.getString("diary_date");
				String diaryDate = diaryTempDate;
				
				res = new DiaryVO(diaryCode, memberCode, sotongContentsCode, diaryTitle, diaryDate);
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
	
	//사용자의 모든 개인일기 정보를 가져오는 메소드.
	public DiaryVO[] selectAllDiaryInfo(String memberCode)
	{
		ArrayList<DiaryVO> list = new ArrayList<DiaryVO>();
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from diary_tb where member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String diaryCode = rs.getString("diary_code");
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String diaryTitle = rs.getString("diary_title");
				String diaryTempDate = rs.getString("diary_date");
				String diaryDate = diaryTempDate;
				
				list.add(new DiaryVO(diaryCode, memberCode, sotongContentsCode, diaryTitle, diaryDate));
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
		
		return list.toArray(new DiaryVO[list.size()]);
	}
	
	public String makeCode(){
		GregorianCalendar cal = new GregorianCalendar();
		
		String diaryCode = "DD01" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return diaryCode;
	}
	
	public Date dateCut(String date)
	{
		 Integer year = new Integer(date.substring(0, 2));
		 Integer month = new Integer(date.substring(3, 5));
		 Integer day = new Integer(date.substring(6, 8));
		 
		 return new Date(year+100,month-1,day);
	}
	
	public String calDate(Date date)
	{
		int year = date.getYear()-100;
		int month = date.getMonth()+1;
		int day = date.getDate();
		
		if(month < 10)
		 {
			 return year + "-0" + month + "-" + day;
		 }		 
		 return year + "-" + month + "-" + day;
	}

	/*
	public static void main(String[] args)
	{
		
		DiaryDAO dao = new DiaryDAO();
		
		//int res = dao.insertDiary("m2", "scontents2", "테스트 일기 입니다123.", new Date());
		int res = dao.insertDiary("d9","m2", "scontents2", "테스트 일기 입니다d6.", new Date());

		System.out.println("insert 결과 값 : " + res);
		
		DiaryVO[] diary = dao.selectAllDiaryInfo("m2");
		
		for(DiaryVO temp : diary)
		{
			System.out.println(temp);
		}
		
		res = dao.updateDiary("d9", "m2", "scontents2", "테스트 일기 수정입니다8.", new Date());
		
		DiaryVO diaryRes = dao.selectDiaryInfo("d9", "m2");
		
		System.out.println(diaryRes);
		
		res = dao.delete("d9");
		
		System.out.println(res);
		
		
		String[][] simple = dao.selectSimpleDiaryList("m2");
		
		for(String[] temp : simple)
		{
			System.out.println(temp[0]);
			System.out.println(temp[1]);
			System.out.println(temp[2]);
		}

	}
	*/
 }
