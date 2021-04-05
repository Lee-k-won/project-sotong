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
	
	
	// 연결 갯수 카운트
	// Ajax 처리
	private void requestCountAlarm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();
		
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		
		if(homeInfo == null)
		{
			System.out.println("홈 정보가 없습니다.");
			return;
		}
		else
		{			
			String homeCode = homeInfo.getFamilyHomeCode();
			int count = manager.countWaiting(homeCode);
			if(count<0)
			{
				System.out.println("데이터 읽기 실패");
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
		System.out.println("요청코드 : " + connectingCode);
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		if(connectingCode == null)
		{
			System.out.println("연결 거절 - 연결 코드 없음");
			return;
		}
		
		int check = manager.deleteConnectionWaiting(connectingCode);
		if(check == -1)
		{
			System.out.println("이웃 요청 거절 실패");
			return;
		}
		else
		{
			System.out.println("이웃요청 거절 성공");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
	}
	
	// 연결 대기 목록
	// Ajax 사용
	
	private void requestWaitingNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();
		
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		//연결요청 목록 가져옴
		ConnectionWaitingViewVO[] waitingList = manager.getConnectionWaitingList(homeCode);
		if(waitingList == null)
		{
			System.out.println("연결요청 목록이 없음");
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
	
	// 연결 해제
	private void requestCutNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();

		String neighborCode = request.getParameter("neighborCode");
		System.out.println("이웃 코드 : " + neighborCode);
		System.out.println("여기서 찍는 이웃 코드 : " + neighborCode );
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		if(neighborCode == null || homeCode == null)
		{
			System.out.println("홈 코드가 없습니다.");
			System.out.println("이웃 코드 : " + neighborCode);
			System.out.println("홈 코드 : " + homeCode);
			return;
		}
		
		int check = manager.cutNeighbor(homeCode, neighborCode);
		if(check == -1)
		{
			System.out.println("이웃과 연결해제 실패");
		}
		else
		{
			System.out.println("연결해제 성공");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
	}
	
	// 연결 요청에 대해서 OK 할 경우 수행하는 메소드
	// aJax 사용
	private void requestConnectOkNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("ok에 접속");
		NeighborHomeManager manager = new NeighborHomeManager();
		HttpSession session = request.getSession();

		String neighborCode = request.getParameter("senderHomeCode");
		System.out.println("이웃 홈 코드 : " + neighborCode);
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String homeCode = homeInfo.getFamilyHomeCode();
		
		if(neighborCode == null || homeCode == null)
		{
			System.out.println("홈 코드가 없습니다.");
			System.out.println("이웃 코드 : " + neighborCode);
			System.out.println("홈 코드 : " + homeCode);
			return;
		}
		
		int check = manager.connectNeighbor(homeCode, neighborCode);
		if(check == -1)
		{
			System.out.println("이웃과 연결 실패");
			return;
		}
		else
		{
			System.out.println("이웃과 연결 성공");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
		
	}	
	
	// 이웃 목록을 가져올 경우 수행하는 메소드
	private void requestConnectedNeighborList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		//접속
		
		//웹은 세션에서 가져옴
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		//앱은 serviceRoute에서 끊어옴
		String serviceRouteApp = null;
		
		//홈 코드
		String homeCode = null;
		if(serviceRouteWeb == null) // 웹 값이 null이면 
		{
			serviceRouteApp = request.getParameter("serviceRoute"); // 앱 서비스값을 받아옴
			if(serviceRouteApp == null) // 앱 값도 null 이면
			{
				System.out.println("접속 에러");
				out.println("500|");
				response.sendRedirect("Main.jsp");
			}
			else
			{
				System.out.println("앱 접속 - 이웃 목록 보기");
				//앱 처리
				
				homeCode = request.getParameter("homeCode");
				
				//first : first_home 에 존재하는 이웃목록 (second가 파라미터 값 홈임)
				ConnectedNeighborViewVO[] firstNeighborList = manager.getFirstConnectedNeighborList(homeCode);
				
				//second : second_home 에 존재하는 이웃목록 (first가 파라미터 값 홈)
				ConnectedNeighborViewVO[] secondNeighborList = manager.getSecondConnectedNeighborList(homeCode);
				
				
				//연결 목록
				ArrayList<String[]> neighborList = new ArrayList<String[]>();
				
				if(firstNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : firstNeighborList)
					{
						//연결 목록에 추가
						//NeighborListBean bean = new NeighborListBean(vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName());
						neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
					}
				}
			
				if(secondNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : secondNeighborList)
					{
						//연결 목록에 추가
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
					System.out.println("App - 이웃 목록보기 에러");
					out.println("500|");
				}
			}
		}
		else // 웹 값이 null이 아니면 - 웹 접속
		{
			System.out.println("웹 접속 - 이웃 목록 보기");
			//웹 처리
			
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			System.out.println("홈 코드 : " + userInfo.getFamilyHomecode());
			
			homeCode = userInfo.getFamilyHomecode();
	
			NeighborListBean[] neighborList = manager.getAllNeighborList(homeCode);
			/*
			for(NeighborListBean temp : neighborList)
			{
				System.out.println(temp);
			}
				*/
			//이웃 목록 세팅
			request.setAttribute("neighborList", neighborList);
		
			RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighbor.jsp");
			rd.forward(request, response);				
			
		}
	}

	//이웃과 연결요청
	private void requestConnectionWaiting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String homeCode = request.getParameter("neighbor");
		FamilyHomeVO myHome = (FamilyHomeVO)session.getAttribute("homeInfo");
		String myHomeCode = myHome.getFamilyHomeCode();
		
		NeighborHomeManager manager =new NeighborHomeManager();
		
		if(homeCode == null || myHomeCode==null)
		{
			System.out.println("홈 코드가 없음 - 이웃 연결 요청");
			System.out.println("이웃 홈코드 : " + homeCode);
			System.out.println("내 홈코드 : " + myHomeCode);
			return;
		}
		
		System.out.println("연결 요청을 받는 홈 코드 : " + homeCode);
		System.out.println("연결 요청을 하는 홈 코드 : " + myHomeCode);
		
		int checkNum = manager.addConnectionWaiting(homeCode, myHomeCode);
		if(checkNum == 1)
		{
			System.out.println("이웃 연결요청 성공");
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeCode);
		}
		else
		{
			System.out.println("이웃 연결요청 실패");
		}
		
	}
	
}


