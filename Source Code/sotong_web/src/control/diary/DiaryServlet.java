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
			System.out.println("���� ��� �ɸ���111");
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
		else if(action.equals("diary_delete.do"))	// ������ ������ ���.
		{
			requestDeleteDiary(request,response);
			requestGetSimpleList(request,response);
		}
	}
	
	//���� ������ �ҷ����� �޼ҵ�.
	private void requestGetSimpleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DiaryManager manager = new DiaryManager();	
		HttpSession session = request.getSession();
		String memberCode = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // ���� ���ǿ��� �޾ƿ�
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else	//������ ���ӽ� ó��
			{			
				System.out.println("APP Diary ���ø���Ʈ �޼ҵ� ����.");	
				memberCode = request.getParameter("memberCode");	
			}
		}
		else	//�� ���� ó��
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			memberCode = userInfo.getMemberCode();
		}


		String[][] simpleDiaryList = manager.getSimpleIndividualDiaryList(memberCode);
		//�ش� ������ ���� �ڵ�� �����ϱⰣ���������� �ҷ��´�.

		
		if(serviceRouteWeb == null)	//�� ���ӽ� ó��.
		{
			PrintWriter out = response.getWriter();

			if(simpleDiaryList.length != 0)	// �����ϱ� �������� �б� ���� �� ó��
			{
				for(String[] simpleDiary : simpleDiaryList)
				{
					out.println("200|" + simpleDiary[0] + "|" + simpleDiary[1] + "|" + simpleDiary[2]);
				}
			}
			else	// ���� ��
			{
				out.println("500");
			}
		}
		else	// �� ���ӽ� ó��.
		{	
			String uri = request.getRequestURI();
			int lastIndex = uri.lastIndexOf("/");
			String action = uri.substring(lastIndex+1);
			
			if(simpleDiaryList.length != 0)	// �����ϱ� �������� �б⸦ �����ߴٸ� ���� �ֱ��� �ϱ��� �������� �о�´�.
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
			if(action.equals("diaryList.do"))		//�����ϱ� ����Ʈ�� �ʿ��� ���.
			{
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/MyDiaryWrite.jsp");
				rd.forward(request, response);
			}
			else if(action.equals("diary_modify.do"))	// ����ȭ������ �Ѿ���� ���.
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
	
	//�ۿ��� �����ϱ� ������ ��û�� ȣ���ϴ� �޼ҵ�
	private void requestGetDiaryInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // ���� ���ǿ��� �޾ƿ�
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else
			{
				//������ ���ӽ� ó��
				System.out.println("APP DiaryInfo ����.");
				PrintWriter out = response.getWriter();
				DiaryManager manager = new DiaryManager();
				
				String diaryCode = request.getParameter("diaryCode");	
				
				DiaryViewVO diaryInfo = manager.getIndividualDiaryInfo(diaryCode);
				
				if(diaryInfo != null)	// �����ϱ� ������ ������
				{
					out.println("200|" + diaryInfo.getDiaryCode() + "|" + diaryInfo.getMemberNickname() + "|" 
							+ diaryInfo.getDiaryTitle() + "|" + diaryInfo.getDiaryDate() + "|" + diaryInfo.getSotongContentsCode() 
							+ "|" + diaryInfo.getContents() + "|" + diaryInfo.getImageName() + "|" + diaryInfo.getImageWrittenDate() 
							+ "|" + diaryInfo.getEmoticonName() + "|" + diaryInfo.getEmoticonRoute());
				}
				else	//���н�
				{
					out.println("500");
				}
			}
		}
		else	//�� ���ӽ� ó��.
		{
			
		}
		
	}
	
	//�����ϱ⸦ �߰��ϴ� �޼ҵ�.
	private void requestAddDiary(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
	{
		DiaryManager manager = new DiaryManager();
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // ���� ���ǿ��� �޾ƿ�
		String serviceRouteApp = null;
		
		String memberCode = null;
		String familyHomeCode = null;
		
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else	//������ ���ӽ� ó��
			{			
				System.out.println("APP Diary ���� �޼ҵ� ����.");	
				memberCode = request.getParameter("memberCode");	
				familyHomeCode = request.getParameter("familyHomeCode");
			}
		}
		else	//�� ���� ó��
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			memberCode = userInfo.getMemberCode();
			familyHomeCode = userInfo.getFamilyHomecode();
		}
		
		String diaryTitle = request.getParameter("diaryTitle");
		String contents = request.getParameter("diaryContents");
		String diaryDate = format();
		String imageName = "img/nono";		// image�� �̸�Ƽ���� ���� ����.
		String imageWrittenDate = diaryDate;
		String emoticonCode = "em1";

		int res = manager.addIndividualDiary(memberCode, familyHomeCode, diaryTitle, diaryDate, contents, imageName, imageWrittenDate, emoticonCode);
		
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else	//������ ���ӽ� ó��
			{			
				System.out.println("APP Diary ���� �޼ҵ� ����.");	
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
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // ���� ���ǿ��� �޾ƿ�
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else	//������ ���ӽ� ó��
			{			
				System.out.println("APP Diary ���� �޼ҵ� ����.");	
				memberCode = request.getParameter("memberCode");	
			}
		}
		else	//�� ���� ó��
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
	
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else	//������ ���ӽ� ó��
			{			
				System.out.println("APP Diary ���� �޼ҵ� ����.");	
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
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // ���� ���ǿ��� �޾ƿ�
		String serviceRouteApp = null;
		
		String diaryCode = request.getParameter("diaryCode");
		String sotongContentsCode = request.getParameter("sotongContentsCode");
		
		int res = manager.deleteIndividualDiary(diaryCode,sotongContentsCode);			
		
		if(serviceRouteWeb==null) // ������ ������
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("���� ����");
			}
			else	//������ ���ӽ� ó��
			{			
				System.out.println("APP Diary ���� �޼ҵ� ����.");	
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
	
	public String format(){ // Date�� String���� ������/ ������ ���� �� ���
		Date today = new Date();
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(today);
	    return date;
	}
}
