package control.story;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.story.StoryManager;
import dao.home.FamilyMemberVO;
import dao.story.StoryViewVO;

/**
 * Servlet implementation class NeighborStoryServlet
 */
@WebServlet({ "/neighborStory.do" })
public class NeighborStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		requestStoryList(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		requestStoryList(request, response);
	}
	
private void requestStoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ���� ����
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		StoryManager manager = new StoryManager();
		
		String homeCode = request.getParameter("homeCode");
		if (homeCode == null) {
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			homeCode = userInfo.getFamilyHomecode();
		}
		
/*
		FamilyMemberVO asdf = (FamilyMemberVO)request.getSession().getAttribute("userInfo");
		System.out.println("fahfiuwhiufhwaf : " + asdf.getFamilyHomecode());
		
*/		
		StoryViewVO[] storyList = manager.getNeighborStoryList(homeCode);

		
/*
		System.out.println(storyList);
		
		for(StoryViewVO temp : storyList)
		{
			System.out.println(temp);
		}
*/	
		
		
		if(serviceRouteWeb == null) // web�� null
		{
			serviceRouteApp = request.getParameter("serviceRoute"); //app���� ������ �������� ����
			if(serviceRouteApp == null) // �ۿ��� null�� ���
			{
				System.out.println("���� ���� - �̿� Ȩ");
			}
			else // �� ����
			{
				PrintWriter out = response.getWriter();
				if (storyList != null) {
					System.out.println("�� ���� : �̿� ���� ");
					for (StoryViewVO vo : storyList) {
						
						System.out.println("200|" + vo.getStoryCode() + "|"  + vo.getFamilyHomeCode() + "|" + vo.getFamilyHomeName() + "|"
								+ vo.getMemberCode() + "|" + vo.getMemberPhoto() + "|" + vo.getMemberColor() + "|" + vo.getMemberNickname() + "|" 
								+ vo.getContents() + "|"	+ vo.getContetsCode() + "|" + vo.getImageName() + "|" + vo.getImageWritenDate() + "|" 
								+ vo.getEmotioconName() + "|" + vo.getEmoticonRoute() + "|" + vo.getStoryDate() + "|" + vo.getStroyHeart() + "|" 
								+ vo.getStoryModifyDate() + "|" + vo.getStoryScope());
						System.out.println();
						
						out.println("200|" + vo.getStoryCode() + "|"  + vo.getFamilyHomeCode() + "|" + vo.getFamilyHomeName() + "|"
								+ vo.getMemberCode() + "|" + vo.getMemberPhoto() + "|" + vo.getMemberColor() + "|" + vo.getMemberNickname() + "|" 
								+ vo.getContents() + "|"	+ vo.getContetsCode() + "|" + vo.getImageName() + "|" + vo.getImageWritenDate() + "|" 
								+ vo.getEmotioconName() + "|" + vo.getEmoticonRoute() + "|" + vo.getStoryDate() + "|" + vo.getStroyHeart() + "|" 
								+ vo.getStoryModifyDate() + "|" + vo.getStoryScope());
					}
					
				} else {
					System.out.println(" �̿� Ȩ�ڵ� ���� ����");
					out.println("500");
				}
				
			}
		}
		else // �� ����
		{
			request.setAttribute("neighborList", storyList);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/story/NeighborStory.jsp");
			rd.forward(request, response);
		}
		
	}
	
}
