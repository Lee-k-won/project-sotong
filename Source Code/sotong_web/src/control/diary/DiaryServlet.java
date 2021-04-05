package control.diary;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.diary.DiaryManager;
import dao.diary.DiaryViewVO;
import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class Diary
 */
@WebServlet(urlPatterns={"/diary.do" , "/diaryInfo.do", "/diaryList.do" , "/diary_insert.do" ,"/diary_update.do", "/diary_modify.do", "/diary_delete.do"})
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet() 
     */
    public DiaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }                                                                                     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);

	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		if(action.equals("diary.do") || action.equals("diaryList.do") || action.equals("diary_modify.do"))
		{
			System.out.println("여기 몇번 걸리냐111");
			requestGetSimpleList(request,response);
		}
		else if(action.equals("diaryInfo.do"))
		{
			requestGetDiaryInfo(request,response);
		}
		else if(action.equals("diary_insert.do"))
		{
			requestAddDiary(request,response);
			requestGetSimpleList(request,response);
		}
		else if(action.equals("diary_update.do"))
		{
			requestUpdateDiary(request,response);
			requestGetSimpleList(request,response);
		}
		else if(action.equals("diary_delete.do"))	// 삭제를 눌렀을 경우.
		{
			requestDeleteDiary(request,response);
			requestGetSimpleList(request,response);
		}
	}
	
	//간략 정보를 불러오는 메소드.
	private void requestGetSimpleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DiaryManager manager = new DiaryManager();	
		HttpSession session = request.getSession();
		String memberCode = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // 웹은 세션에서 받아옴
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP Diary 심플리스트 메소드 접속.");	
				memberCode = request.getParameter("memberCode");	
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			memberCode = userInfo.getMemberCode();
		}


		String[][] simpleDiaryList = manager.getSimpleIndividualDiaryList(memberCode);
		//해당 유저에 대한 코드로 개인일기간략정보들을 불러온다.

		
		if(serviceRouteWeb == null)	//앱 접속시 처리.
		{
			PrintWriter out = response.getWriter();

			if(simpleDiaryList.length != 0)	// 개인일기 간략정보 읽기 성공 시 처리
			{
				for(String[] simpleDiary : simpleDiaryList)
				{
					out.println("200|" + simpleDiary[0] + "|" + simpleDiary[1] + "|" + simpleDiary[2]);
				}
			}
			else	// 실패 시
			{
				out.println("500");
			}
		}
		else	// 웹 접속시 처리.
		{	
			String uri = request.getRequestURI();
			int lastIndex = uri.lastIndexOf("/");
			String action = uri.substring(lastIndex+1);
			
			if(simpleDiaryList.length != 0)	// 개인일기 간략정보 읽기를 성공했다면 가장 최근의 일기의 상세정보를 읽어온다.
			{
				request.setAttribute("simpleDiaryList", simpleDiaryList);
				
				if(!(action.equals("diaryList.do")) && !(action.equals("diary_modify.do")))
				{
					String diaryCode = request.getParameter("diaryCode");
					
					System.out.println(diaryCode);
					if(diaryCode == null)
					{
		
						diaryCode = simpleDiaryList[0][0];
					}
					DiaryViewVO diaryInfo = manager.getIndividualDiaryInfo(diaryCode);
					request.setAttribute("diaryInfo", diaryInfo);
				}
			}
			if(action.equals("diaryList.do"))		//개인일기 리스트만 필요한 경우.
			{
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/MyDiaryWrite.jsp");
				rd.forward(request, response);
			}
			else if(action.equals("diary_modify.do"))	// 수정화면으로 넘어갈때의 경우.
			{
				String diaryCode = request.getParameter("diaryCode");
									
				DiaryViewVO diaryInfo = manager.getIndividualDiaryInfo(diaryCode);
				
				request.setAttribute("diaryInfo", diaryInfo);
				
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/MyDiaryModify.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/MyDiary.jsp");
				rd.forward(request, response);	
			}
		}
	}
	
	//앱에서 개인일기 상세정보 요청시 호출하는 메소드
	private void requestGetDiaryInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // 웹은 세션에서 받아옴
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else
			{
				//앱으로 접속시 처리
				System.out.println("APP DiaryInfo 접속.");
				PrintWriter out = response.getWriter();
				DiaryManager manager = new DiaryManager();
				
				String diaryCode = request.getParameter("diaryCode");	
				
				DiaryViewVO diaryInfo = manager.getIndividualDiaryInfo(diaryCode);
				
				if(diaryInfo != null)	// 개인일기 상세정보 성공시
				{
					out.println("200|" + diaryInfo.getDiaryCode() + "|" + diaryInfo.getMemberNickname() + "|" 
							+ diaryInfo.getDiaryTitle() + "|" + diaryInfo.getDiaryDate() + "|" + diaryInfo.getSotongContentsCode() 
							+ "|" + diaryInfo.getContents() + "|" + diaryInfo.getImageName() + "|" + diaryInfo.getImageWrittenDate() 
							+ "|" + diaryInfo.getEmoticonName() + "|" + diaryInfo.getEmoticonRoute());
				}
				else	//실패시
				{
					out.println("500");
				}
			}
		}
		else	//웹 접속시 처리.
		{
			
		}
		
	}
	
	//개인일기를 추가하는 메소드.
	private void requestAddDiary(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
	{
		DiaryManager manager = new DiaryManager();
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // 웹은 세션에서 받아옴
		String serviceRouteApp = null;
		
		String memberCode = null;
		String familyHomeCode = null;
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP Diary 수정 메소드 접속.");	
				memberCode = request.getParameter("memberCode");	
				familyHomeCode = request.getParameter("familyHomeCode");
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			memberCode = userInfo.getMemberCode();
			familyHomeCode = userInfo.getFamilyHomecode();
		}
		
		String diaryTitle = request.getParameter("diaryTitle");
		String contents = request.getParameter("diaryContents");
		String diaryDate = format();
		String imageName = "img/nono";		// image와 이모티콘은 아직 보류.
		String imageWrittenDate = diaryDate;
		String emoticonCode = "em1";

		int res = manager.addIndividualDiary(memberCode, familyHomeCode, diaryTitle, diaryDate, contents, imageName, imageWrittenDate, emoticonCode);
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP Diary 수정 메소드 접속.");	
				PrintWriter out = response.getWriter();
				
				if(res == 1)
				{
					out.println("200|success");
				}
				else
				{
					out.println("500");
				}
			}
		}
	
	}
	
	private void requestUpdateDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DiaryManager manager = new DiaryManager();
		HttpSession session = request.getSession();
		String memberCode = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // 웹은 세션에서 받아옴
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP Diary 수정 메소드 접속.");	
				memberCode = request.getParameter("memberCode");	
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			memberCode = userInfo.getMemberCode();
		}
		
		String diaryCode = request.getParameter("diaryCode");
		String diaryTitle = request.getParameter("diaryTitle");
		String contents = request.getParameter("contents");
		String diaryDate = request.getParameter("diaryDate");
		String sotongContentsCode = request.getParameter("sotongContentsCode");
		String imageName = "img/testData";
		String imageWrittenDate = diaryDate;
		String emoticonCode = "em1";

		int res = manager.updateIndividualDiary(diaryCode, memberCode, sotongContentsCode, diaryTitle, diaryDate, contents, imageName, imageWrittenDate, emoticonCode);
	
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP Diary 수정 메소드 접속.");	
				PrintWriter out = response.getWriter();
				
				if(res == 1)
				{
					out.println("200|success");
				}
				else
				{
					out.println("500");
				}
			}
		}
	}
	
	private void requestDeleteDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DiaryManager manager = new DiaryManager();
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // 웹은 세션에서 받아옴
		String serviceRouteApp = null;
		
		String diaryCode = request.getParameter("diaryCode");
		String sotongContentsCode = request.getParameter("sotongContentsCode");
		
		int res = manager.deleteIndividualDiary(diaryCode,sotongContentsCode);			
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP Diary 수정 메소드 접속.");	
				PrintWriter out = response.getWriter();
				
				if(res == 1)
				{
					out.println("200|success");
				}
				else
				{
					out.println("500");
				}
			}
		}
	}
	
	public String format(){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
		Date today = new Date();
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(today);
	    return date;
	}
}
