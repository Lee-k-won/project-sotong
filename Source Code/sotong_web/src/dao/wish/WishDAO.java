package dao.wish;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class WishDAO implements Serializable{

private static final long serialVersionUID = 3466705589424033336L;
private DBConnectionModule connModule;
private Connection conn;

public WishDAO()
{
	connModule=DBConnectionModule.getInstance();
	conn=connModule.getConn();
}

public String makeWishCode() {
	GregorianCalendar cal = new GregorianCalendar();
	
	String wishCode = "WD23" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
	
	return wishCode;
}

public int insertWish(String sotongContentsCode,String memberCode,String wishTitle,Date wishDate,Date wishEndDate,byte wishFinish)
{
	int rowNum=0;
	Date date=null;
	PreparedStatement pstmt=null;
	try
	{date=new Date();
		String sql="insert into wish_tb values(?,?,?,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, makeWishCode());
		pstmt.setString(2,sotongContentsCode);
		pstmt.setString(3,memberCode);
		pstmt.setString(4,wishTitle);
		String date2=format(wishDate);
		String date3=format(wishEndDate);
		pstmt.setString(5,date2);
		pstmt.setString(6,date3);
		if(wishFinish==(byte)1)
		{
			pstmt.setString(7,"완료");
		}
		else
		{
			pstmt.setString(7,"진행중");

		}
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			//se.printStackTrace();
		}
	}
	
	return rowNum;
			
}
public int deleteWish(String wishCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		String sql="Delete FROM wish_tb WHERE wish_code=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, wishCode);
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		//se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
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
public int updateWish(String wishCode,String sotongContentsCode,String memberCode,String wishTitle,Date wishDate,Date wishEndDate,byte isFinished)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE wish_tb SET sotong_contents_code=?,member_code=?,wish_title=?,wish_date=?,wish_end_date=?, wish_finish=? WHERE wish_code=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,sotongContentsCode);
		pstmt.setString(2,memberCode);
		pstmt.setString(3,wishTitle);
		pstmt.setString(4,format(wishDate));
		pstmt.setString(5,format(wishEndDate));
		
		
		if(isFinished==(byte)1)
		{
			pstmt.setString(6,"완료");
		}
		else
		{
			pstmt.setString(6,"진행중");
		}
		pstmt.setString(7,wishCode);
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
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
public int updateWish(String wishCode,WishVO infoVo)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE wish_tb SET sotong_contents_code=?,member_code=?,wish_title=?,wish_date=?,wish_end_date=?, wish_finish=? WHERE wish_code=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,infoVo.getSotongContentsCode());
		pstmt.setString(2,infoVo.getMemberCode());
		pstmt.setString(3,infoVo.getWishTitle());
		pstmt.setString(4,format(infoVo.getWishDate()));
		pstmt.setString(5,format(infoVo.getWishEndDate()));
		
		Byte isFinished=infoVo.getWishFinish();
		if(isFinished==(byte)1)
		{
			pstmt.setString(6,"완료");
		}
		else
		{
			pstmt.setString(6,"진행중");
		}
		pstmt.setString(7,wishCode);
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
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
public WishVO selectWish(String wishCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	WishVO infoVo=null;
	try
	{
		
		String sql="SELECT * FROM wish_tb where WISH_CODE=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, wishCode);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		infoVo=new WishVO();
		infoVo.setWishCode(rs.getString("wish_code"));
		infoVo.setSotongContentsCode(rs.getString("sotong_contents_code"));
		infoVo.setMemberCode(rs.getString("member_code"));
		infoVo.setWishTitle(rs.getString("wish_title"));
	
		infoVo.setWishDate(changeDate(rs.getString("wish_date")));
	
		infoVo.setWishEndDate(changeDate(rs.getString("wish_end_date")));
		
		String str=rs.getString("wish_Finish");
		if(str.equals("완료"))
		{
			infoVo.setWishFinish((byte)1);
		}
		else
		{
			infoVo.setWishFinish((byte)0);
		}
	
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return infoVo;
			
}
public int finishWish(String wishCode,Date finishDate)
{
int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE wish_tb SET wish_end_date=?, wish_finish=? WHERE wish_code=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,format(finishDate));
		pstmt.setString(2,"완료");
		pstmt.setString(3,wishCode);
		
		
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}	
				
	return 1;
}
public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
    String date = fmt.format(d);
    return date;
}
	
public Date changeDate(String dateTime) { // String으로 가져온 data를 Date로 변경
	Date dt = null;
	try {
		dt = new SimpleDateFormat("yy-MM-dd").parse(dateTime);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dt; 
}
public static void main(String args[])
{
	WishDAO dao=new WishDAO();
	//dao.delete("1563091630");
	//dao.insert("scontents4", "m3", "위시제목테스트1", new Date(),new Date(115,6,30),(byte)0);
	//dao.update("ㅈ",new WishInfoVO("w4","scontents1","m2","test2",new Date(),new Date(115,12,1),(byte)1));//사용자가 날짜 지정시 ex)2015-7-30입력시 115,6,30으로 초기화 (연도에서 -1900한 값, 월에서 -1한값.)
	//System.out.println(dao.select("w2")); 
	dao.finishWish("w2", new Date(115,6,31));
}
}
