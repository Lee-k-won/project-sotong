package control.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.NeighborHomeManager;
import model.bean.NeighborListBean;

import com.google.gson.Gson;

import dao.connection.ConnectionWaitingViewVO;
import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;
import dao.neighbor.ConnectedNeighborViewVO;

/**
 * Servlet implementation class NeighborHomeServlet
 */
/**
 * @author star
 *
 */
@WebServlet({"/neighbor.do", "/connect.do", "/connectOk.do", "/cutNeighbor.do", "/waitingNeighbor.do", "/rejectNeighbor.do", "/countAlarm.do"})
public class NeighborHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeighborHomeServlet() {
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
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		
		if(action.equals("neighbor.do"))
		{
			requestConnectedNeighborList(request,response);			
		}
		else if(action.equals("connect.do"))
		{
			requestConnectionWaiting(request,response);
		}
		else if(action.equals("connectOk.do"))
		{
			requestConnectOkNeighbor(request,response);
		}
		else if(action.equals("cutNeighbor.do"))
		{
			requestCutNeighbor(request,response);
		}
		else if(action.equals("waitingNeighbor.do"))
		{
			requestWaitingNeighbor(request,response);
		}
		else if(action.equals("rejectNeighbor.do"))
		{
			requestRejectNeighbor(request,response);
		}
		else if(action.equals("countAlarm.do"))
		{
			requestCountAlarm(request,response);
		}
		
	}
	
	
	// ���� ���� ī��Ʈ
	// Ajax ó��
	private void requestCountAlarm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();
		
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		
		if(homeInfo == null)
		{
			System.out.println("Ȩ ������ �����ϴ�.");
			return;
		}
		else
		{			
			String homeCode = homeInfo.getFamilyHomeCode();
			int count = manager.countWaiting(homeCode);
			if(count<0)
			{
				System.out.println("������ �б� ����");
			}
			else
			{
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(""+	count);
			}
			
		}
		
		
		
		
	}
	
	private void requestRejectNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();

		String connectingCode = request.getParameter("waitingCode");
		System.out.println("��û�ڵ� : " + connectingCode);
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		if(connectingCode == null)
		{
			System.out.println("���� ���� - ���� �ڵ� ����");
			return;
		}
		
		int check = manager.deleteConnectionWaiting(connectingCode);
		if(check == -1)
		{
			System.out.println("�̿� ��û ���� ����");
			return;
		}
		else
		{
			System.out.println("�̿���û ���� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
	}
	
	// ���� ��� ���
	// Ajax ���
	
	private void requestWaitingNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();
		
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		//�����û ��� ������
		ConnectionWaitingViewVO[] waitingList = manager.getConnectionWaitingList(homeCode);
		if(waitingList == null)
		{
			System.out.println("�����û ����� ����");
		}
		else
		{
			//request.setAttribute("waitingList", waitingList);
			String waiting = new Gson().toJson(waitingList);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(waiting);
		}
		
	}
	
	// ���� ����
	private void requestCutNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();

		String neighborCode = request.getParameter("neighborCode");
		System.out.println("�̿� �ڵ� : " + neighborCode);
		System.out.println("���⼭ ��� �̿� �ڵ� : " + neighborCode );
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		if(neighborCode == null || homeCode == null)
		{
			System.out.println("Ȩ �ڵ尡 �����ϴ�.");
			System.out.println("�̿� �ڵ� : " + neighborCode);
			System.out.println("Ȩ �ڵ� : " + homeCode);
			return;
		}
		
		int check = manager.cutNeighbor(homeCode, neighborCode);
		if(check == -1)
		{
			System.out.println("�̿��� �������� ����");
		}
		else
		{
			System.out.println("�������� ����");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
	}
	
	// ���� ��û�� ���ؼ� OK �� ��� �����ϴ� �޼ҵ�
	// aJax ���
	private void requestConnectOkNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("ok�� ����");
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();

		String neighborCode = request.getParameter("senderHomeCode");
		System.out.println("�̿� Ȩ �ڵ� : " + neighborCode);
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		if(neighborCode == null || homeCode == null)
		{
			System.out.println("Ȩ �ڵ尡 �����ϴ�.");
			System.out.println("�̿� �ڵ� : " + neighborCode);
			System.out.println("Ȩ �ڵ� : " + homeCode);
			return;
		}
		
		int check = manager.connectNeighbor(homeCode, neighborCode);
		if(check == -1)
		{
			System.out.println("�̿��� ���� ����");
			return;
		}
		else
		{
			System.out.println("�̿��� ���� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
		
	}	
	
	// �̿� ����� ������ ��� �����ϴ� �޼ҵ�
	private void requestConnectedNeighborList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		//����
		
		//���� ���ǿ��� ������
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		//���� serviceRoute���� �����
		String serviceRouteApp = null;
		
		//Ȩ �ڵ�
		String homeCode = null;
		if(serviceRouteWeb == null) // �� ���� null�̸� 
		{
			serviceRouteApp = request.getParameter("serviceRoute"); // �� ���񽺰��� �޾ƿ�
			if(serviceRouteApp == null) // �� ���� null �̸�
			{
				System.out.println("���� ����");
				out.println("500|");
				response.sendRedirect("Main.jsp");
			}
			else
			{
				System.out.println("�� ���� - �̿� ��� ����");
				//�� ó��
				
				homeCode = request.getParameter("homeCode");
				
				//first : first_home �� �����ϴ� �̿���� (second�� �Ķ���� �� Ȩ��)
				ConnectedNeighborViewVO[] firstNeighborList = manager.getFirstConnectedNeighborList(homeCode);
				
				//second : second_home �� �����ϴ� �̿���� (first�� �Ķ���� �� Ȩ)
				ConnectedNeighborViewVO[] secondNeighborList = manager.getSecondConnectedNeighborList(homeCode);
				
				
				//���� ���
				ArrayList<String[]> neighborList = new ArrayList<String[]>();
				
				if(firstNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : firstNeighborList)
					{
						//���� ��Ͽ� �߰�
						//NeighborListBean bean = new NeighborListBean(vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName());
						neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
					}
				}
			
				if(secondNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : secondNeighborList)
					{
						//���� ��Ͽ� �߰�
						//NeighborListBean bean = new NeighborListBean(vo.getSecondHomeCode(), vo.getSecondHomeName(), vo.getSecondHomeManagerName());
						neighborList.add(new String[]{vo.getSecondHomeCode(), vo.getSecondHomeName(), vo.getSecondHomeManagerName()});
					}
				}
				
				if(neighborList.size() != 0)
				{
//					NeighborListBean[] neighbor = neighborList.toArray(new NeighborListBean[neighborList.size()]);
//					System.out.println(neighbor[0].getHomeName());
			
					for(String[] neighbor : neighborList)
					{
						System.out.println(neighbor[0]);
						out.println("200|" + neighbor[0] + "|" + neighbor[1] + "|" + neighbor[2]);
					}
				}
				else
				{
					System.out.println("App - �̿� ��Ϻ��� ����");
					out.println("500|");
				}
			}
		}
		else // �� ���� null�� �ƴϸ� - �� ����
		{
			System.out.println("�� ���� - �̿� ��� ����");
			//�� ó��
			
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			System.out.println("Ȩ �ڵ� : " + userInfo.getFamilyHomecode());
			
			homeCode = userInfo.getFamilyHomecode();
	
			NeighborListBean[] neighborList = manager.getAllNeighborList(homeCode);
			/*
			for(NeighborListBean temp : neighborList)
			{
				System.out.println(temp);
			}
				*/
			//�̿� ��� ����
			request.setAttribute("neighborList", neighborList);
		
			RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighbor.jsp");
			rd.forward(request, response);				
			
		}
	}

	//�̿��� �����û
	private void requestConnectionWaiting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String homeCode = request.getParameter("neighbor");
		FamilyHomeVO myHome = (FamilyHomeVO)session.getAttribute("homeInfo");
		String myHomeCode = myHome.getFamilyHomeCode();
		
		NeighborHomeManager manager =new NeighborHomeManager();
		
		if(homeCode == null || myHomeCode==null)
		{
			System.out.println("Ȩ �ڵ尡 ���� - �̿� ���� ��û");
			System.out.println("�̿� Ȩ�ڵ� : " + homeCode);
			System.out.println("�� Ȩ�ڵ� : " + myHomeCode);
			return;
		}
		
		System.out.println("���� ��û�� �޴� Ȩ �ڵ� : " + homeCode);
		System.out.println("���� ��û�� �ϴ� Ȩ �ڵ� : " + myHomeCode);
		
		int checkNum = manager.addConnectionWaiting(homeCode, myHomeCode);
		if(checkNum == 1)
		{
			System.out.println("�̿� �����û ����");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
		else
		{
			System.out.println("�̿� �����û ����");
		}
		
	}
	
}


