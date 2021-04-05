package dao.letter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DBConnectionModule;

public class LetterViewDAO implements Serializable{

	private static final long serialVersionUID = 6667267076047900740L;
private DBConnectionModule connModule;
private Connection conn;

public LetterViewDAO()
{
	connModule=DBConnectionModule.getInstance();
	conn=connModule.getConn();
}

public LetterViewVO select(String letterCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	LetterViewVO viewVO=null;
	try
	{
		
		String sql="SELECT * FROM letter_view where LETTER_CODE=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, letterCode);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		viewVO=new LetterViewVO();
		
		viewVO.setLetterCode(rs.getString("letter_code"));
		viewVO.setSender(rs.getString("sender"));
		viewVO.setReceiver(rs.getString("receiver"));
		viewVO.setLetterTitle(rs.getString("letter_title"));
		
		viewVO.setSendDate(changeDate(rs.getString("send_date")));
		viewVO.setImageName(rs.getString("image_name"));
		viewVO.setSotongContentsCode(rs.getString("SOTONG_CONTENTS_CODE"));
		viewVO.setContents(rs.getString("contents"));
		viewVO.setImageWrittenDate(changeDate(rs.getString("image_written_date")));
		viewVO.setEmoticonName(rs.getString("emoticon_name"));
		viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
		
		//System.out.println(year+"/"+month+"/"+day);
		
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
	
	return viewVO;
			
}
public List<LetterViewVO> selectByMember(String memberCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	List<LetterViewVO> list=new ArrayList<LetterViewVO>();
	LetterViewVO viewVO=null;
	try
	{
		
		String sql="select * from letter_view where \"receiver\"=?";
				
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, memberCode);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
		viewVO=new LetterViewVO();
		viewVO.setLetterCode(rs.getString("letter_code"));
		viewVO.setSender(rs.getString("sender"));
		viewVO.setReceiver(rs.getString("receiver"));
		viewVO.setLetterTitle(rs.getString("letter_title"));
		viewVO.setSendDate(changeDate(rs.getString("send_date")));
		viewVO.setSotongContentsCode(rs.getString("SOTONG_CONTENTS_CODE"));
		viewVO.setContents(rs.getString("contents"));
		viewVO.setImageName(rs.getString("image_name"));
		viewVO.setImageWrittenDate(changeDate(rs.getString("image_written_date")));
		viewVO.setEmoticonName(rs.getString("emoticon_name"));
		viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
		
		//System.out.println(year+"/"+month+"/"+day);
		list.add(viewVO);
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
	
	return list;
			
}

public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
    String date = fmt.format(d);
    return date;
}
	
public Date changeDate(String dateTime) { // String으로 가져온 data를 Date로 변경. 태영수정 8/6 21:11
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
	LetterViewDAO dao=new LetterViewDAO();
	//dao.delete("201563202230");
	//LetterInfoViewVO view=dao.select("l1");
	//dao.update("15113193819", dao.select("l2"));
	//System.out.println(view);
	//System.out.println(view.getSendDate().getYear()+"/"+view.getSendDate().getMonth()+"/"+view.getSendDate().getDate()+"/"+view.getSendDate().getDay());
	//System.out.println(view.getImageWrittenDate().getYear()+"/"+view.getImageWrittenDate().getMonth()+"/"+view.getImageWrittenDate().getDate()+"/"+view.getImageWrittenDate().getDay());
	for(int i=0;i<dao.selectByMember("경워니").size();i++)
	{
		System.out.println(dao.selectByMember("경워니").get(i));
	}
}
}
