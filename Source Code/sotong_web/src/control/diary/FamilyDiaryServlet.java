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

import manager.diary.FamilyDiaryManager;
import model.bean.FamilyDiaryPartBean;
import model.bean.FamilyDiaryViewBean;
import dao.diary.FamilyDiaryVO;
import dao.diary.FamilyDiaryViewVO;
import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class FamilyDiaryServlet
 */
@WebServlet(urlPatterns={"/familyDiary.do" , "/familyDiaryList.do", "/familyDiaryInfo.do", "/familyDiary_insert.do", "/familyDiary_modify.do", "/familyDiary_update.do" , "/familyDiary_delete.do"})
public class FamilyDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FamilyDiaryServlet() {
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

		if(action.equals("familyDiary.do") || action.equals("familyDiaryList.do") || action.equals("familyDiary_modify.do"))
		{
			requestGetSimpleFamilyDiaryList(request,response);
		}
		else if(action.equals("familyDiary_insert.do"))
		{
			requestAddFamilyDiary(request,response);
			requestGetSimpleFamilyDiaryList(request,response);
		}
		else if(action.equals("familyDiaryInfo.do"))
		{
			requestFamilyDiaryInfo(request,response);
		}
		else if(action.equals("familyDiary_update.do"))
		{
			requestUpdateFamilyDiary(request,response);
			requestGetSimpleFamilyDiaryList(request,response);
		}
		else if(action.equals("familyDiary_delete.do"))
		{
			requestDeleteFamilyDiary(request,response);
			requestGetSimpleFamilyDiaryList(request,response);
		}
	
	}
	
	
	private void requestGetSimpleFamilyDiaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FamilyDiaryManager manager = new FamilyDiaryManager();
		HttpSession session = request.getSession();
		String homeCode = null;
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
				System.out.println("APP FamilyDiary 심플리스트 메소드 접속.");	
				homeCode = request.getParameter("homeCode");	
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			homeCode = userInfo.getFamilyHomecode();		
		}
		
		FamilyDiaryVO[] res = manager.getSimpleFamilyDiaryList(homeCode);
		
		
		if(serviceRouteWeb == null)	//앱 접속시 처리.
		{
			PrintWriter out = response.getWriter();

			if(res.length != 0)	// 개인일기 간략정보 읽기 성공 시 처리
			{
				for(FamilyDiaryVO temp : res)
				{
					out.println("200|" + temp.getFamilyDiaryCode() + "|" + temp.getFamilyHomeCode() + "|" + temp.getFamilyDiaryDate());
					//앱으로 데이터 보내는 부분.
				}
			}
			else	// 실패 시
			{
				out.println("500");
			}
		}
		else	// 웹 접속시 처리.
		{	
			request.setAttribute("simpleFamilyDiaryList", res);
			
			String uri = request.getRequestURI();
			int lastIndex = uri.lastIndexOf("/");
			String action = uri.substring(lastIndex+1);
			
			if(action.equals("familyDiaryList.do"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/FamilyDiaryWrite.jsp");
				rd.forward(request, response);	
			}
			else if(action.equals("familyDiary_modify.do"))
			{
				String familyDiaryCode = (String)request.getParameter("familyDiaryCode");
				String memberCode = ((FamilyMemberVO)session.getAttribute("userInfo")).getMemberCode();
						
				FamilyDiaryViewVO vo= manager.getFamilyDiaryPartInfo(familyDiaryCode,memberCode);
				if(vo != null)
				{
					request.setAttribute("diaryPartInfo", vo);
					
					RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/FamilyDiaryModify.jsp");
					rd.forward(request, response);	
				}
				else
				{
					RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/FamilyDiaryWrite.jsp");
					rd.forward(request, response);	
				}	
			}
			else
			{
				if(res.length != 0)
				{
					String familyDiaryCode = request.getParameter("familyDiaryCode");
					
					if(familyDiaryCode == null)
					{
						familyDiaryCode = res[0].getFamilyDiaryCode();
					}
					FamilyDiaryViewVO[] familyDiaryInfo = manager.getFamilyDiaryInfo(familyDiaryCode);
					
					if(familyDiaryInfo.length != 0)
					{						
						FamilyDiaryPartBean[] partBean = new FamilyDiaryPartBean[familyDiaryInfo.length];
					
						for(int i = 0 ; i < familyDiaryInfo.length ; i++)	
						{
							partBean[i] = new FamilyDiaryPartBean(familyDiaryInfo[i].getFamilyDiaryPartCode(),familyDiaryInfo[i].getMemberNickname(),familyDiaryInfo[i].getMemberPhoto(),familyDiaryInfo[i].getFamilyDiaryPartDate(),familyDiaryInfo[i].getSotongContentsCode(),familyDiaryInfo[i].getContents(),familyDiaryInfo[i].getEmoticonName(),familyDiaryInfo[i].getEmoticonRoute(),familyDiaryInfo[i].getImageName(),familyDiaryInfo[i].getImageWrittenDate());
						}
					
						FamilyDiaryViewBean fDiaryInfo = new FamilyDiaryViewBean(familyDiaryInfo[0].getFamilyDiaryCode(),familyDiaryInfo[0].getFamilyHomeCode(),familyDiaryInfo[0].getFamilyDiaryDate(),partBean);
						
						request.setAttribute("fDiaryInfo", fDiaryInfo);
					}		
				}
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/FamilyDiary.jsp");
				rd.forward(request, response);	
			}
		}
	}
	
	private void requestFamilyDiaryInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
				System.out.println("APP FamilyDiaryInfo 접속.");
				PrintWriter out = response.getWriter();
				FamilyDiaryManager manager = new FamilyDiaryManager();
				
				
				String familyDiaryCode = request.getParameter("familyDiaryCode");	
				
				FamilyDiaryViewVO[] familyDiaryInfo = manager.getFamilyDiaryInfo(familyDiaryCode);
				
				if(familyDiaryInfo != null)
				{
					out.println("200|" + familyDiaryInfo[0].getFamilyDiaryCode() + "|" + familyDiaryInfo[0].getFamilyHomeCode() + "|" + familyDiaryInfo[0].getFamilyDiaryDate());
					
					System.out.println(familyDiaryInfo[0].getFamilyDiaryCode());
					System.out.println(familyDiaryInfo[0].getFamilyHomeCode());
					System.out.println(familyDiaryInfo[0].getFamilyDiaryDate());
					
					for(FamilyDiaryViewVO temp : familyDiaryInfo)
					{
						System.out.println("패밀리 다이어리 확인");
						System.out.println(temp.getContents());
						System.out.println(temp.getEmoticonName());
						System.out.println(temp.getEmoticonRoute());
						System.out.println( temp.getImageName());
						System.out.println(temp.getImageWrittenDate());
						
						out.println("300|" + temp.getFamilyDiaryPartCode() + "|" + temp.getMemberNickname() + "|" + temp.getMemberPhoto() + "|" 
								+ temp.getFamilyDiaryPartDate() + "|" + temp.getSotongContentsCode() + "|" + temp.getContents() + "|" 
								+ temp.getEmoticonName() + "|" + temp.getEmoticonRoute() + "|" + temp.getImageName() + "|" + temp.getImageWrittenDate());
					}					
				}
				else
				{
					out.println("500");
				}
			}
		}
		else	//웹 접속시 처리.
		{
			
		}
		
	}

	private void requestAddFamilyDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FamilyDiaryManager manager = new FamilyDiaryManager();

		HttpSession session = request.getSession();
		String homeCode = null;
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
				System.out.println("APP FamilyDiary 추가 메소드 접속.");	
				homeCode = request.getParameter("homeCode");	
				memberCode = request.getParameter("memberCode");
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			homeCode = userInfo.getFamilyHomecode();
			memberCode = userInfo.getMemberCode();
		}
		
		String familyDiaryPartDate = format();
		String contents = request.getParameter("familyDiaryContents");
		String imageName = "img/nono";	//request.getParameter("imageName");
		String imageWrittenDate = familyDiaryPartDate;
		String emoticonCode = "em1";	//request.getParameter("emoticonCode");
		
		int res = manager.addFamilyDiary(homeCode, memberCode, familyDiaryPartDate, contents, imageName, imageWrittenDate, emoticonCode);
		
		if(serviceRouteApp != null)
		{
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
		else
		{
			if(res == 2)
			{
				//수정하기 화면으로 이동.
			}
		}
	}
	
	private void requestUpdateFamilyDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FamilyDiaryManager manager = new FamilyDiaryManager();

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
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP FamilyDiary 수정 메소드 접속.");	
			}
		}
		else	//웹 접속 처리
		{

		}
		
		String sotongContentsCode = request.getParameter("sotongContentsCode");
		String contents = request.getParameter("contents");
		String imageName = "img/nonono"; /*request.getParameter("imageName");*/
		String imageWrittenDate = format();	/*request.getParameter("imageWrittenDate");*/
		String emoticonCode = "em1";  /*request.getParameter("emoticonCode");*/
		
		
		System.out.println(sotongContentsCode);
		System.out.println(contents);
		
		
		int res = manager.updateFamilyDiaryPart(sotongContentsCode, contents, imageName, imageWrittenDate, emoticonCode);
		
		if(serviceRouteApp != null)
		{
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
	
	private void requestDeleteFamilyDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FamilyDiaryManager manager = new FamilyDiaryManager();
		
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
				System.out.println("APP FamilyDiary 삭제 메소드 접속.");	
				memberCode = request.getParameter("memberCode");
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			memberCode = userInfo.getMemberCode();
		}
		
		String familyDiaryCode = request.getParameter("familyDiaryCode");

		int res = manager.deleteFamilyDiary(familyDiaryCode, memberCode);
		
		if(serviceRouteApp != null)
		{
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
	
	public String format(){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
		Date today = new Date();
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(today);
	    return date;
	}

}
