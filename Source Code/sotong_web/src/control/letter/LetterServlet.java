package control.letter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.HomeManager;
import manager.letter.LetterManager;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewVO;
import dao.letter.LetterViewVO;

/**
 * Servlet implementation class LetterServlet
 */
@WebServlet({ "/LetterServlet", "/letter.do","/letter_write.do","/letter_writing.do","/letter_delete.do","/letter_detail.do"})
public class LetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		process(request,response);
	}
	
	private void process(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		if(request.getRequestURI().contains("/letter_write.do"))
		{
			requestSendingLetter(request,response);
		}
		else if(request.getRequestURI().contains("/letter_delete.do"))
		{
			requestDeleteLetter(request,response);
		}
		else if(request.getRequestURI().contains("/letter_detail.do"))
		{
			requestViewDetailLetterInfo(request,response);
		}
		else if(request.getRequestURI().contains("/letter_writing.do"))
		{
			requestWritingLetter(request,response);
		}
		else
		{
			requestGettingDetailLetterContents(request,response);
		}
	}
	
	public void requestGettingDetailLetterContents(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();		
	
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp= null;
		
		LetterManager manager=new LetterManager();	
		
		
		/*for(int i=0;i<info.length;i++)
		{
			for(int j=0;j<info[i].length;j++)
			{
				System.out.println(info[i][j]);
			}
		}*/
		
		//제목,날짜,이름,코드
	
		
		if(serviceRouteWeb==null) // 웹으로 접속상태가 아님
		{
			serviceRouteApp=request.getParameter("serviceRoute"); // 앱으로 접속상태를 확인함
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
				out.println("500|");
				
			}
			else // 앱 접속 상태
			{
				System.out.println("App 접속 - 우체통");
				
				String memberCode=request.getParameter("memberCode");
				System.out.println("멤버 코드 값 : " + memberCode);
				
				if(memberCode == null)
				{
					System.out.println("App 접속 우체통 읽기 실패");
					out.println("500|");					
				}
				String[][] info= manager.getStringLetterInfoList(memberCode);
				
				String str="200|";
				String data="";
				for(int i=0;i<info.length;i++)
				{
					for(int j=0;j<info[i].length;j++)
					{
						data=data+info[i][j]+"|".trim();
						
					}
					out.println(str+data+"".trim());
					data="";
				}
				//System.out.println("eewewr"+str+data+"".trim());
			}			
		}
		else
		{
			System.out.println("Web 접속 - 우체통");
			//웹 코드 들어가야함.
			
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			if(userInfo == null)
			{
				System.out.println("Web 접속 우체통 읽기 실패");
				response.sendRedirect("main.html");
			}
			String memberCode = userInfo.getMemberCode();
			
			String[][] info= manager.getStringLetterInfoList(memberCode);
			
			request.setAttribute("letterInfo", info);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/letter/letterbox.jsp");
			rd.forward(request, response);
		}
		
		
	}
	public void requestDeleteLetter(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		
		String serviceRouteApp = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		LetterManager manager=new LetterManager();
		
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
				out.println("500|");
			}
			else
			{
				System.out.println("App - 편지 삭제 접속");
				String letterCode=request.getParameter("letterCode");
				if(letterCode == null)
				{
					System.out.println("App- 편지 코드 읽기 실패");
					out.println("500|");
				}
				else
				{
					System.out.println("letterCode:"+letterCode);
					boolean isDeleted=manager.deleteLetterInfo(letterCode);				
					if(isDeleted)
					{
						System.out.println("App- 편지 삭제 성공");
						out.println("200|");
					}
					else
					{
						System.out.println("App- 편지 삭제 실패");
						out.println("500|");
					}
				}
			}
		}
		else
		{
			System.out.println("Web- 편지 삭제 접속");
			String letterCode=request.getParameter("letterCode");
			if(letterCode == null)
			{
				System.out.println("Web- 편지 코드 읽기 실패");
				response.sendRedirect("letter.do");
			}
			else
			{
				System.out.println("Web- 편지 코드 읽기 성공");
				System.out.println("letterCode : " + letterCode);
				boolean isDeleted=manager.deleteLetterInfo(letterCode);				
				if(isDeleted)
				{
					System.out.println("Web- 편지 삭제 성공");
					response.sendRedirect("letter.do");
				}
				else
				{
					System.out.println("Web- 편지 삭제 실패");
					response.sendRedirect("letter.do");
				}
				
			}
		}
		

	}
	
	
	public void requestSendingLetter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();	

		String serviceRouteWeb=(String)session.getAttribute("serviceRoute");
		String serviceRouteApp=null;
		
		LetterManager manager = new LetterManager();
		
		if(serviceRouteWeb == null) // 웹접속X
		{
			serviceRouteApp=request.getParameter("serviceRoute");
			
			if(serviceRouteApp==null) // 앱접속X
			{
				System.out.println("접속 에러");
				out.println("500|");
			}
			else // 앱접속O
			{
				System.out.println("APP 접속 성공 - 편지쓰기");
				
				String senderCode=request.getParameter("senderCode");
				String receiverCode=request.getParameter("receiverCode");
				String letterTitle=request.getParameter("title");
				String contents=request.getParameter("contents");
				String imageName=request.getParameter("imageName");
				String emoticonCode=request.getParameter("emoticonCode");
				
				String letterWrittenDate=request.getParameter("letterWrittenDate");
				
				HomeManager homeManager = new HomeManager();
				
				String homeCode = homeManager.getMemberDetailInfo(senderCode).getFamilyHomecode();
				
				
				
				System.out.println(senderCode+"/"+receiverCode+"/"+letterTitle+"/"+imageName+"/"+emoticonCode+"/");
				boolean isSuccessed= manager.addLetterInfo(homeCode, senderCode, receiverCode, letterTitle, contents, imageName, emoticonCode, letterWrittenDate);
				if(isSuccessed)
				{
					System.out.println("APP 편지쓰기 성공");
					out.println("200|");
				}
				else
				{
					System.out.println("APP 접속 실패");
					out.println("500|");
				}
			}
		}
		else
		{
			System.out.println("Web접속 - 편지 쓰기");
			
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			String homeCode = userInfo.getFamilyHomecode();
//			String[][] memberList = (String[][])session.getAttribute("familyMemberList");
			
			HomeManager homeManager = new HomeManager();
		    HomeInfoViewVO[] memberList = homeManager.getMemberList(userInfo.getFamilyHomecode());
			
			String title = request.getParameter("title");
			System.out.println("타이틀 : " + title);
			String receiver =  request.getParameter("receiverList");
			System.out.println("수신자 별명" + receiver);
			String contents = request.getParameter("contents");
			System.out.println("내용" + contents);
			String sendDate = request.getParameter("sendDate").substring(2);
			System.out.println("발신일 : " + sendDate);
			
			String receiverCode = null;
			
//			for(int i=0; i<memberList.length; i++)
//			{
//				System.out.println("멤버목록");
//				System.out.println("별명+코드" +memberList[i][0] + memberList[i][1]);
//				if(memberList[i][1].equals(receiver)) //별명과 비교
//				{
//					receiverCode = memberList[i][0];					
//					break;
//				}
//			}
			
			for(int i=0; i<memberList.length; i++)
			{
				System.out.println("별명 : " + memberList[i].getMemberNickName() + "코드 : " + memberList[i].getMemberCode());
				if(memberList[i].getMemberNickName().equals(receiver))
				{
					receiverCode = memberList[i].getMemberCode();
					break;
				}
			}
			boolean isSuccessed=manager.addLetterInfo(homeCode, userInfo.getMemberCode(), receiverCode, title, contents, "imageRoute", "em1", sendDate);
				
			if(isSuccessed)
			{
				System.out.println("Web 전송 성공 - 편지쓰기");
				response.sendRedirect("letter.do");
			}
			else
			{
				System.out.println("Web 전송 실패");
				response.sendRedirect("main.html");
			}
			
		}
		
	}
	public void requestViewDetailLetterInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		
		String letterCode=request.getParameter("letterCode");
		System.out.println("편지코드" + letterCode);
		
		request.setAttribute("letterCode", letterCode);
		
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp=null;
		LetterManager manager=new LetterManager();

		LetterViewVO vo=manager.getLetterInfo(letterCode);												
		//제목,날짜,이름,코드
		System.out.println("편지읽기로 옴?");
		
		if(serviceRouteWeb == null) // 웹 접속 X
		{
			serviceRouteApp=request.getParameter("serviceRoute");
			if(serviceRouteApp == null) // 앱 접속 X
			{
				System.out.println("접속 에러");
				out.write("500|");							
			}
			else // 앱 접속 O
			{
				if(vo == null)
				{
					System.out.println("App 접속, 편지 정보 로드 실패");
					out.println("500|");
				}
				else
				{
					System.out.println("App 접속 성공 - 편지읽기");
					String str="200|"+vo.getReceiver()+"|"+vo.getContents()+"|"+changeDate(vo.getSendDate())+"|"+vo.getSender()+"|";
					out.println(str);
				}
			}
		}
		else // 웹 접속 O
		{
			if(vo == null)
			{
				System.out.println("Web 접속, 편지 정보 로드 실패");
				response.sendRedirect("main.html");
			}
			else
			{
				System.out.println("Web 접속 성공 - 편지읽기");
				
				request.setAttribute("letter", vo);
				request.setAttribute("letterDate", changeDate(vo.getSendDate()));
				RequestDispatcher rd = request.getRequestDispatcher("JSP/letter/readLetter.jsp");
				rd.forward(request, response);
			}
		}
		
	}
	public Date format(String date)
	{
		Date newDate=null;
		try {
			newDate=new SimpleDateFormat("yy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}
	public String changeDate(Date date)
	{
		return new SimpleDateFormat("yy-MM-dd").format(date);
	}

	public void requestWritingLetter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		  HttpSession session = request.getSession();
	      FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
	   
	      HomeManager manager = new HomeManager();
	      HomeInfoViewVO[] memberList = manager.getMemberList(userInfo.getFamilyHomecode());
	      request.setAttribute("familyMemberList", memberList);
	      
	      RequestDispatcher rd = request.getRequestDispatcher("JSP/letter/writeLetter.jsp");
	      rd.forward(request, response);
	}
}