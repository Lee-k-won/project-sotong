package control.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import manager.home.HomeManager;
import manager.home.LoginManager;
import manager.home.NeighborHomeManager;
import model.bean.FamilyHomeBean;

import com.google.gson.Gson;

import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewVO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet({"/okManagerRole.do", "/home.do","/renameHome.do", "/deleteMember.do","/detailMember.do", "/modifyMember.do","/modifyMemberOk.do", "/neighborHome.do", "/neighborProfile.do", "/searchNeighbor.do"})
@MultipartConfig
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		process(request, response);
	}

	public String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = null;
	    if (d != null) {
	    	date = fmt.format(d);
	    } else {
	    	date = "00�� 00��";
	    }
	    return date;
	}
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		switch (action) {
			case "home.do" :
				requestHome(request,response);
				break;
			case "detailMember.do" :
				requestMemberInfo(request,response);
				break;
			case "renameHome.do" :
				requestRename(request,response);
				break;
			case "modifyMember.do" :
				requestUpdateMemberProfile(request,response);
				break;
			case "modifyMemberOk.do" :
				requestUpdateMemberProfileOk(request,response);
				break;
			case "neighborHome.do" :
				requestNeighborHome(request,response);
				break;
			case "deleteMember.do" :
				requestDeleteMember(request,response);
				break;
			case "neighborProfile.do" :
				requestNeighborProfile(request,response);
				break;
			case "searchNeighbor.do" :
				requestSearchNeighbor(request,response);
				break;
			case "okManagerRole.do" :
				requestOkManagerRole(request,response);
				break;
		}
		
		/*
		if(action.equals("home.do"))
		{
			requestHome(request,response);			
		}
		else if(action.equals("detailMember.do"))
		{
			requestMemberInfo(request,response);
		}
		else if(action.equals("renameHome.do"))
		{
			requestRename(request,response);
		}
		else if(action.equals("modifyMember.do"))
		{
			requestUpdateMemberProfile(request,response);
		}
		else if(action.equals("modifyMemberOk.do"))
		{
			requestUpdateMemberProfileOk(request,response);
		}
		else if(action.equals("neighborHome.do"))
		{
			requestNeighborHome(request,response);
		}
		else if(action.equals("deleteMember.do"))
		{
			requestDeleteMember(request,response);
		}
		else if(action.equals("neighborProfile.do"))
		{
			requestNeighborProfile(request,response);
		}
		else if(action.equals("searchNeighbor.do"))
		{
			requestSearchNeighbor(request,response);
		}
		*/
	}
	
	// �߰� �޼ҵ��̴�.
	private void requestOkManagerRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HomeManager manager = new HomeManager();
		LoginManager loginManager = new LoginManager();
		
		String homeCode = request.getParameter("homeCode");
		String managerCode = request.getParameter("managerCode");
		String memberCode = request.getParameter("memberCode");
		String id = request.getParameter("managerId");
		
		System.out.println("homeCode : " + homeCode);
		System.out.println("managerCode : " + managerCode);
		System.out.println("memberCode : " + memberCode);
		System.out.println("id : " + id);
		
		int checkRole = manager.changeRole(managerCode, memberCode);
		
		if (checkRole == 1) {
			FamilyMemberVO vo = loginManager.getMemberInfo(id);
			request.getSession().setAttribute("userInfo", vo);
			System.out.println(" �Ŵ��� ���� ���� ");
		} else {
			System.out.println("�Ŵ��� ���� ����");
		}
	}
	
	
	
	private void requestSearchNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		String searchCategory = request.getParameter("search-category");
		String searchWord = request.getParameter("searchWord");
		
		System.out.println("�˻��з� : " + searchCategory);
		System.out.println("�˻��� : " + searchWord);
		HomeInfoViewVO[] homeList = manager.searchNeighbor(searchCategory, searchWord);

		if(homeList == null)
		{
			System.out.println("�˻������ ����");
			return;
		}
		else
		{
			String result = new Gson().toJson(homeList);
			System.out.println(result);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		
	}
	
	private void requestNeighborProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String serviceRouteApp = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		//������ ����������� �ڵ带 �޾ƿ�
		String memberCode = request.getParameter("memberCode"); 
		
		if(memberCode == null) // ��� ������ �ڵ尡 ������
		{
			System.out.println("�̿� �ڵ尡 ����");
			out.println("500|");
		}
		else // ��� ������ �ڵ尡 ������
		{
			System.out.println("�̿� ���������� �ڵ� : "+memberCode);			
			FamilyMemberVO memberInfo = manager.getMemberDetailInfo(memberCode);
			System.out.println("���������� ���� : " + memberInfo);
			
			if(serviceRouteWeb == null) // �� ���� X�̸�
			{
				serviceRouteApp = request.getParameter("servieRoute");
				if(serviceRouteApp == null)
				{
					System.out.println("�̿� ���������� ������� ���� ����");
					out.println("500|");
				}
				else // �� ����O
				{
					System.out.println("App ���� - �̿� ���������� ����");
				}
			}
			else // �� ����O
			{
				System.out.println("Web ���� - �̿� ���������� ����");
					
				System.out.println("������� null üũ : " + memberInfo);
				System.out.println("����ڵ� : " + memberInfo.getFamilyHomecode());
				String homeName = manager.getHomeInfo(memberInfo.getFamilyHomecode()).getFamilyHomeName();
				String birth = memberInfo.getMemberBirth();

				request.setAttribute("homeName", homeName);
				request.setAttribute("birth",birth);
				request.setAttribute("memberInfo", memberInfo);
				
				RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighborProfile.jsp");
				rd.forward(request, response);	
			}
		}
	}
	
	// �� �ϼ� �޼ҵ� �߰� �ϼ�
	private void requestDeleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		System.out.println("������������ �����Ϸ� �Դ�.");
		HomeManager manager = new HomeManager();
		
		String memberCode = request.getParameter("memberCode");
		String homeCode = request.getParameter("homeCode");
		String birth = request.getParameter("member-birth");
		
		System.out.println("���� : memberCode : " + memberCode);
		System.out.println("���� : homeCode : " + homeCode);
		System.out.println("���� : birth : " + birth);
		
		int checkDelete = manager.updateMember(memberCode);
		if (checkDelete != 0) {
			System.out.println("���� �Ǿ���.");
			
			HttpSession session = request.getSession();
			//FamilyMemberVO vo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			LoginManager loginManager = new LoginManager();
			FamilyMemberVO managerVO = loginManager.homeManagerInfo(homeCode);
			
			String[][] familyMemberList = loginManager.getMemberListWeb(homeCode, memberCode);
					
			
			for (String[] arr : familyMemberList) {
				for (String str : arr)
					System.out.println(str);
			}
			String homeName = loginManager.getHomeName(homeCode);
			
			System.out.println("���� : homeName : " + homeName);
			
			FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), birth, managerVO.getMemberPhoto(), familyMemberList); 
			request.setAttribute("familyHome", bean);
			request.setAttribute("managerCode", managerVO.getMemberCode());
			//session ��ü�� ����� ���� ����
			session.setAttribute("familyMemberList", familyMemberList);
			
			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
			rd.forward(request, response);	
		}
		
		
	}
	
	private void requestNeighborHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		// ������ Ȩ ������ �ش��ϴ� Ȩ �ڵ带 �޴´�.
		String homeCode = request.getParameter("homeCode");
		System.out.println("�̿� Ȩ �ڵ� : " +homeCode);
		
		PrintWriter out = response.getWriter();
		
		if(homeCode==null)
		{
			// Ȩ�ڵ尡 ���ٸ� �������� �̵�
			response.sendRedirect("Main.jsp");
		}
	
		
		//�������� ������ ����
		String[][] familyMemberList = null;
		
		// Ȩ �̸� ��������
		String homeName = manager.getHomeInfo(homeCode).getFamilyHomeName();
		
		//�Ŵ��� ���� ��������
		FamilyMemberVO homeManager = manager.homeManagerInfo(homeCode);
		
		System.out.println("�Ŵ���-Ȩ�ڵ� : " + homeManager.getFamilyHomecode());
		
		//��� ��� ��������
		familyMemberList = manager.getMemberList(homeCode, homeManager.getMemberCode());
		
		//Ȩ�Ŵ��� ��������
		String birth = homeManager.getMemberBirth();
		
		
		
		
		// ���� ����
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null) // web�� null
		{
			serviceRouteApp = request.getParameter("serviceRoute"); //app���� ������ �������� ����
			if(serviceRouteApp == null) // �ۿ��� null�� ���
			{
				System.out.println("���� ���� - �̿� Ȩ ����");
			}
			else // �� ����
			{
				String mCode = homeManager.getMemberCode();
               String mNickName = homeManager.getMemberNickName();
               String mBirth = homeManager.getMemberBirth();
               String mColor = homeManager.getMemberColor();
               String mPhoto = homeManager.getMemberPhoto();
	               
               out.println("300|"+mCode+"|"+mNickName+"|"+mBirth+"|"+mColor+"|"+mPhoto);
               //�� ó��
               System.out.println("App ���� - �̿� Ȩ ����");
               for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		}
		else // �� ����
		{
			//�̿�Ȩ�� üũ ����
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			String myHomeCode = userInfo.getFamilyHomecode();
			
			NeighborHomeManager neighborManager = new NeighborHomeManager();
			int neighborCheck = neighborManager.checkNeighbor(myHomeCode, homeCode);						
			
			System.out.println("�� Ȩ�ڵ� : " + userInfo.getFamilyHomecode() + "�̿� Ȩ �ڵ� : " + homeCode);
			if(userInfo.getMemberCode().equals(homeCode))
			{
				response.sendRedirect("home.do");
			}
			System.out.println("Web ���� - �̿� Ȩ ����");
			FamilyHomeBean bean = new FamilyHomeBean(homeName, homeManager.getMemberNickName(), birth, homeManager.getMemberPhoto(), familyMemberList); 
							
			request.setAttribute("managerCode", homeManager.getMemberCode());
			request.setAttribute("managerBirth", birth);
			request.setAttribute("homeManager", homeManager);
			System.out.println("-------------------Ȩ �Ŵ��� ���� : " + homeManager);
			request.setAttribute("familyHome", bean);
			String str = Integer.toString(neighborCheck);
			request.setAttribute("neighborCheck", str);
			
							
			RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighborHome.jsp");
			rd.forward(request, response);
			
		}	
		
	}
	
	private void requestUpdateMemberProfileOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		String imgRoute = getServletContext().getInitParameter("imgRoute");
		HttpSession session = request.getSession();
//		
//		//����� ����
//		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
//		System.out.println("����� ���� : " + userInfo.getMemberCode());
//		
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
//		if(userInfo != null) //����� ������ ���� ���
//		{
//			
			System.out.println("������ ���� ���� �޼ҵ�");
			
			String code = request.getParameter("code");
			System.out.println("code�� : " + code);
			String name = request.getParameter("name");
			System.out.println("�̸� : " + name);
			String phone = request.getParameter("phone");
			System.out.println("����ȣ : " + phone);
			String email = request.getParameter("email");
			System.out.println("�̸��� : " + email);
			String pw = request.getParameter("pw");
			System.out.println("��� : " +pw);
			String nickName = request.getParameter("nickName");
			System.out.println("���� : " + nickName);
			String color = request.getParameter("color");
			String birth = request.getParameter("birth");
			System.out.println("����"+birth);
			String photo = request.getParameter("photo");
			System.out.println("----�������----");
			
			
			if(serviceRouteWeb==null) // ������ ������
	         {
	            serviceRouteApp = request.getParameter("serviceRoute");
	            if(serviceRouteApp == null)
	            {
	               System.out.println("���� ����");
	            }
	            else   //������ ���ӽ� ó��
	            {         
	               System.out.println("APP ������ ���� �޼ҵ� ����.");      
	            }
	         }
	         else   //�� ���� ó��
	         {
	            Part part = request.getPart("updateImg"); 

	            String fileName = getFilename(part);
	            
	            if(fileName != null && !fileName.isEmpty())
	            { 
	               String imageFileName = makeFileName() + fileName.substring(fileName.length()-4);
	               part.write(imgRoute + "img\\profile\\" + imageFileName );
	               photo = "img/profile/"+imageFileName;
	            }
	         }
			
//			byte role = Byte.parseByte(request.getParameter("role"));
//			System.out.println(role);
			
			int num = manager.updateMemberProfile(code, name, phone, email, pw, photo, nickName, color, birth);
			if(num==-1)
			{
				//�������
				System.out.println("���濡 �����Ͽ����ϴ�.");
				return;
			}
			
			if(serviceRouteApp == null) // Web����
			{	
	//			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
	//			rd.forward(request, response);
				
				//userInfo ���� ����
				FamilyMemberVO newUserInfo = (FamilyMemberVO)session.getAttribute("userInfo");
				newUserInfo.setMemberNickName(nickName);
				newUserInfo.setMemberName(name);
				newUserInfo.setMemberPhone(phone);
				newUserInfo.setMemberEmail(email);
				newUserInfo.setMemberPw(pw);
				newUserInfo.setMemberPhoto(photo);
				newUserInfo.setMemberColor(color);
				newUserInfo.setMemberBirth(birth);
				
				session.setAttribute("userInfo", newUserInfo);
				
				System.out.println("���� - ������ ����");
				response.sendRedirect("home.do");
			}
			else
			{
				System.out.println("�� ���� ���� - ������ ����");
				response.getWriter().println("200|success");
			}
		}
	
	private void requestUpdateMemberProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//����� ����
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("����� ���� : " + userInfo.getMemberCode());
		
		//Ȩ ����
		String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
		request.setAttribute("homeName", homeName);
		
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		if(serviceRouteApp == null) // Web����
		{	
			String birth = userInfo.getMemberBirth();
			request.setAttribute("birth", birth);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
			rd.forward(request, response);
		}
		else // App����
		{
			System.out.println("App ���� - ���� ����");
			String birth = userInfo.getMemberBirth();
			PrintWriter out = response.getWriter();
			out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
					userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
					userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
					userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +birth + "|" + userInfo.getMemberRole());
		}
//		else
//		{
//			System.out.println("���� ����");
//		}
		
	}
	
	private void requestRename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//����� ����
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("����� ���� : " + userInfo.getMemberCode());
		
		//Ȩ �̸� ���� ���ؼ� jsp�� name �߰�
		String homeName = URLDecoder.decode(request.getParameter("changeName"), "UTF-8");
		System.out.println("Ȩ�̸� : " + homeName);
		
		//������� Ȩ �ڵ�
		String homeCode = userInfo.getFamilyHomecode();
		
		int num=manager.modifyHomeName(homeCode, homeName);
		
		if(num==1)
		{
			FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
			homeInfo.setFamilyHomeName(homeName);
			session.setAttribute("homeInfo", homeInfo);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeName);
		}
		else
		{
			System.out.println("�̸� ���� ����");
			return;
		}
	}
	
	private void requestHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HomeManager manager= new HomeManager();		
		HttpSession session = request.getSession();		
		
		// ����� ����
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("���������" + userInfo.getMemberCode());
		
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = null;
		if (userInfo != null) { // ����� ������ null�� �ƴϰ�
			String[][] familyMemberList = null;
			if (serviceRouteApp==null) { // ������ ����
				
				if(userInfo.getFamilyHomecode() == null)
				{
					// Ȩ �ڵ尡 ���ٸ�
					System.out.println("Ȩ�ڵ� ����");
					rd = request.getRequestDispatcher("MakeHome.jsp"); // Ȩ ���� ȭ������ �̵�
					rd.forward(request, response);
				}
				else // Ȩ�ڵ尡 �ִٸ�
				{	
					FamilyMemberVO managerVO = manager.homeManagerInfo(userInfo.getFamilyHomecode());
					
					System.out.println();
					System.out.println(managerVO);
					System.out.println();
					familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), managerVO.getMemberCode());
					
					String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
					
					request.setAttribute("managerCode", managerVO.getMemberCode());
					FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), managerVO.getMemberBirth(), managerVO.getMemberPhoto(), familyMemberList); 
					request.setAttribute("familyHome", bean);
					rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
					rd.forward(request, response);
				}
			} else {
				
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("APP ����");
				
				//���� �������� ������ �޾ƿ´�.
				familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), userInfo.getMemberCode());
				
				String birth = userInfo.getMemberBirth();
				
				//�α��� �� ȸ���� ������ �����Ѵ�.
			
				out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
						userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
						userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
						userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +
					birth + "|" + userInfo.getMemberRole());
					
				//���� �������� ������ �����Ѵ�.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} 
	}
	
	private void requestMemberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
				
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
					
		if(serviceRouteApp==null) // ���̸�
		{
			System.out.println("������");
			// ����� ����
			FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
			
			if(userInfo!=null)
			{
				//���õ� ����ڵ� �޾ƿ���
				String memberCode = request.getParameter("memberInfo");
				System.out.println("���õ� ����ڵ� ����" + memberCode);
				
				FamilyHomeVO familyHome = manager.getHomeInfo(userInfo.getFamilyHomecode());
				
				FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
				if(memberDetail != null)
				{
					String birth = memberDetail.getMemberBirth();
					System.out.println("���� : " + birth);
					System.out.println("format ��� �� : " + memberDetail.getMemberBirth());
					
					//��� �������� Ȩ���� ����				
					request.setAttribute("memberBirth", birth);				
					request.setAttribute("memberDetail", memberDetail);
					request.setAttribute("familyHome", familyHome);
				}
				else // null�̸�
				{	
					request.setAttribute("memberBirth", userInfo.getMemberBirth());
					request.setAttribute("memberDetail", userInfo);
					request.setAttribute("familyHome", familyHome);
				}	
				rd = request.getRequestDispatcher("JSP/home/familyProfile.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				 response.sendRedirect("/Main.jsp");
				//System.out.println("����� ������ �����ϴ�.");
			}
		}
		else
		{
			System.out.println("�������� - APP ���� - ����");
			
			String memberCode = request.getParameter("memberInfo");
			System.out.println("���õ� ����ڵ� ����" + memberCode);
			
			FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
			String  birth = memberDetail.getMemberBirth();
			
			out.println("200|" + memberDetail.getMemberCode() + "|" +
					memberDetail.getMemberName() + "|" + memberDetail.getMemberPhone() + "|" +  memberDetail.getMemberEmail() + "|" +
					memberDetail.getMemberPhoto() +"|"+ memberDetail.getMemberNickName() + "|" + memberDetail.getMemberColor() + "|" +
				birth + "|" + memberDetail.getMemberRole());				
		}
	}
	
	private String getFilename(Part part)
	{
		String contentDispositionHeader = part.getHeader("content-disposition");
		String[] elements = contentDispositionHeader.split(";");
		for(String element : elements)
		{
			if(element.trim().startsWith("filename"))
			{
				return element.substring(element.indexOf('=') + 1).trim().replace("\"","");
			}
		}
		return null;
	}
	
	private String makeFileName()
	{
		GregorianCalendar cal = new GregorianCalendar();
		
		String name = "sotong_" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + "_" + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return name;
	}
	
}
