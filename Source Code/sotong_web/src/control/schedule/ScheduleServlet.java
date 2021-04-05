package control.schedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.HomeManager;
import manager.schedule.ScheduleManager;
import model.bean.Event;

import com.google.gson.Gson;

import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;
import dao.schedule.ScheduleVO;
import dao.schedule.ScheduleViewVO;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet(name="ScheduleServelt", urlPatterns={"/schedule.do","/scheduleList.do", "/addSchedule.do", "/modifySchedule.do", "/deleteSchedule.do", "/allFamilySchedule.do"})
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");	
		process(request,response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		if(action.equals("schedule.do"))
		{
			requestSchedule(request,response);
		}
		else if(action.equals("scheduleList.do"))
		{
			requestSimpleScheduleList(request,response);
		}
		else if(action.equals("addSchedule.do"))
		{
			requestAddSchedule(request,response);
		}
		else if(action.equals("modifySchedule.do"))
		{
			requestModifySchedule(request, response);
		}
		else if(action.equals("deleteSchedule.do"))
		{
			requestDeleteSchedule(request, response);
		}
		else if(action.equals("allFamilySchedule.do"))
		{
			requestAllFamilySchedule(request,response);
		}
	}
	/*
	private String returnISODate(Date date)
	{
		date.setYear(date.getYear()+100);
		System.out.println(date.getYear());
		date.setMonth(date.getMonth()-1);
		String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
		return formatted.substring(0, 22) + ":" + formatted.substring(22);
	}
	*/
	
	private void requestSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyMemberVO member = (FamilyMemberVO)session.getAttribute("userInfo");
		System.out.println(member.getMemberName()+"���� ����");
		
		RequestDispatcher rd = request.getRequestDispatcher("JSP/schedule/selfCalendar.jsp");
		rd.forward(request, response);
	}
	
	//0811 3��25�� �߰�
	
	// /allFamilySchedule.do
	private void requestAllFamilySchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");

		if(homeInfo == null)
		{
			System.out.println("Ȩ ������ �����ϴ�.");
			return;
		}
		else
		{
			String familyHomeCode = homeInfo.getFamilyHomeCode();
			System.out.println("Ȩ ���� ��� : " + homeInfo.getFamilyHomeCode());
			ScheduleManager manager = new ScheduleManager();
			ScheduleViewVO[] scheduleList = manager.getAllFamilyScheduleList(familyHomeCode);
			ArrayList<Event> events = new ArrayList<Event>();
			
			if(scheduleList != null)
			{
				System.out.println("���� ���" + scheduleList.length);
				HomeManager homeManager = new HomeManager();
				String color = null;
				
				
				for (int i=0; i<scheduleList.length; i++)
				{
					String start = scheduleList[i].getScheduleStartDate();
					System.out.println("������ : " + start);
					String end = scheduleList[i].getScheduleEndDate();
					System.out.println("������ : " + end);
					
					String memberCode = scheduleList[i].getScheduleMemberCode();
					FamilyMemberVO vo = homeManager.getMemberDetailInfo(memberCode);
					color = vo.getMemberColor();
					
					if(start.equals(end)) // �����ϰ� �������� ������
					{
						Event event = new Event(scheduleList[i].getScheduleCode(), scheduleList[i].getScheduleTitle(), start, end, scheduleList[i].getSchedulePlace(), true, color, scheduleList[i].getScheduleRepeat(), scheduleList[i].getScheduleAlarm(), scheduleList[i].getScheduleMemo());
						events.add(event);
					}
					else // �����ϰ� �������� �ٸ���
					{
						Event event = new Event(scheduleList[i].getScheduleCode(), scheduleList[i].getScheduleTitle(), start, end, scheduleList[i].getSchedulePlace(), true, color, scheduleList[i].getScheduleRepeat(), scheduleList[i].getScheduleAlarm(), scheduleList[i].getScheduleMemo());
						events.add(event);
					}
				}
				
				Event[] eventList = events.toArray(new Event[events.size()]);
				String result = new Gson().toJson(eventList);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(result);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("JSP/schedule/selfCalendar.jsp");
				rd.forward(request, response);
			}
		}
		
	}
	
	
	//������� 12:34 ���� & �߰�
	
	
	//  /scheduleList.do
	private void requestSimpleScheduleList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		ScheduleManager manager = new ScheduleManager();
		ScheduleVO[] voList = manager.getSimpleIndividualScheduleInfoListWeb(userInfo.getMemberCode(), "15", "08");
		ArrayList<Event> events = new ArrayList<Event>();
		if(voList != null)
		{
			System.out.println("���� ���" + voList.length);
//			for(int i=0; i<voList.length;i++)
//			{
//				
////				System.out.println("������ : " + returnISODate(voList[i].getScheduleStartDate()));
////				System.out.println("������ : " + returnISODate(voList[i].getScheduleEndDate()));
//				Event event = new Event(voList[i].getScheduleTitle(), returnISODate(voList[i].getScheduleStartDate()), returnISODate(voList[i].getScheduleEndDate()));
//				events.add(event);
//			}
			
			for(int i=0; i<voList.length; i++)
			{
				String start = voList[i].getScheduleStartDate();
				System.out.println("������ : " + start);
				String end = voList[i].getScheduleEndDate();
				System.out.println("������ : " + end);
				
				if(start.equals(end)) // �����ϰ� �������� ������
				{
					Event event = new Event(voList[i].getScheduleCode(), voList[i].getScheduleTitle(), start, end, voList[i].getSchedulePlace(), true, userInfo.getMemberColor(), voList[i].getScheduleRepeat(), voList[i].getScheduleAlarm(), voList[i].getScheduleMemo());
					events.add(event);
				}
				else // �����ϰ� �������� �ٸ���
				{
					Event event = new Event(voList[i].getScheduleCode(), voList[i].getScheduleTitle(), start, end, voList[i].getSchedulePlace(), false, userInfo.getMemberColor(), voList[i].getScheduleRepeat(), voList[i].getScheduleAlarm(), voList[i].getScheduleMemo());
					events.add(event);
				}
			}
			
			Event[] eventList = events.toArray(new Event[events.size()]);
			String result = new Gson().toJson(eventList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("JSP/schedule/selfCalendar.jsp");
			rd.forward(request, response);
		}
	}
	
	//  /addSchedule.do
	private void requestAddSchedule(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		System.out.println("�߰� - ���� ��� : "+ userInfo.getMemberCode());
		
		ScheduleManager manager = new ScheduleManager();
		
		//����ڰ� �Է��� ������ �о����
		
		//����
		String scheduleTitle = request.getParameter("scheduleTitle");
		System.out.println(scheduleTitle);
		
		//���
		String schedulePlace = request.getParameter("schedulePlace");
		System.out.println(schedulePlace);
		
		//������
		String startDate = request.getParameter("startDate");
		System.out.println(startDate);
		String startTime = request.getParameter("startTime");
		System.out.println(startTime);
				
		String scheduleStartDate = startDate.substring(2,4)+"-" + startDate.substring(5,7)+"-" + startDate.substring(8,10)+ " " + startTime;
		
		//������
		
		String endDate = request.getParameter("endDate");
		System.out.println(endDate);
		String endTime = request.getParameter("endTime");
		System.out.println(endTime);
		String scheduleEndDate =null;
		if(endDate == null || endDate == "")
		{
			scheduleEndDate = scheduleStartDate;
		}
		else
		{
			scheduleEndDate = endDate.substring(2,4)+"-" + endDate.substring(5,7)+"-" + endDate.substring(8,10) + " " + endTime;
		}
		System.out.println(scheduleEndDate);
		
		//�ݺ�
		
		int scheduleRepeat = Integer.parseInt(request.getParameter("repeatSelec"));
		
		//�˸� ����
		
		//�˶�
		
		String scheduleAlarm = request.getParameter("alarmSelec");
		
		// �޸�
		
		String scheduleMemo = request.getParameter("memo");
		
		String scheduleCode = manager.addIndividualScheduleInfo(userInfo.getMemberCode(), scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo, null);
		
		if(scheduleCode == null)
		{
			System.out.println("���� �߰� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("�����߰��� �����߽��ϴ�.");
		}
		else
		{
			System.out.println("���� �ڵ� : " + scheduleCode);
			Event event = null;
			if(scheduleEndDate.equals(scheduleStartDate))
			{
				event = new Event(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, true, userInfo.getMemberColor(), scheduleRepeat, scheduleAlarm, scheduleMemo);		
			}
			else
			{
				event = new Event(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, false, userInfo.getMemberColor(), scheduleRepeat, scheduleAlarm, scheduleMemo);
			}
			String result = new Gson().toJson(event);
			System.out.println("���� �߰� ����");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
	}
	
	
	// /modifySchedule.do
	private void requestModifySchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("����-����");
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		System.out.println("���� - ���� ��� : "+ userInfo.getMemberCode());
		
		ScheduleManager manager = new ScheduleManager();
		
		// ������ �о���� �κ�
		
		//����
		String scheduleTitle = request.getParameter("scheduleTitle");
		System.out.println(scheduleTitle);
		
		//���
		String schedulePlace = request.getParameter("schedulePlace");
		System.out.println(schedulePlace);
		
		//������
		String startDate = request.getParameter("startDate");
		System.out.println(startDate);
		String startTime = request.getParameter("startTime");
		System.out.println(startTime);
				
		String scheduleStartDate = startDate.substring(2,4)+"-" + startDate.substring(5,7)+"-" + startDate.substring(8,10)+ " " + startTime;
		
		//������		
		String endDate = request.getParameter("endDate");
		System.out.println(endDate);
		String endTime = request.getParameter("endTime");
		System.out.println(endTime);
	
		String scheduleEndDate = endDate.substring(2,4)+"-" + endDate.substring(5,7)+"-" + endDate.substring(8,10) + " " + endTime;
		System.out.println(scheduleEndDate);
		
		//�ݺ�		
		int scheduleRepeat = Integer.parseInt(request.getParameter("repeatSelec"));
		
		//�˸� ����
		
		//�˶�
		
		String scheduleAlarm = request.getParameter("alarmSelec");
		
		// �޸�
		
		String scheduleMemo = request.getParameter("memo");
		
		// ������ �ڵ�
		
		String scheduleCode = request.getParameter("scheduleCode");
		System.out.println("������ �ڵ� : " + scheduleCode);
		int check = manager.updateIndividualScheduleInfo(scheduleCode, userInfo.getMemberCode(), scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo, null);
		
		if(check == -1)
		{
			System.out.println("���� ���� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("���� ������ �����߽��ϴ�.");
		}
		else
		{
			Event event = null;
			if(scheduleEndDate.equals(scheduleStartDate))
			{
				event = new Event(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, true, userInfo.getMemberColor(), scheduleRepeat, scheduleAlarm, scheduleMemo);		
			}
			else
			{
				event = new Event(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, false, userInfo.getMemberColor(), scheduleRepeat, scheduleAlarm, scheduleMemo);
			}
			String result = new Gson().toJson(event);
			System.out.println("���� ���� ����");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		
	}
	
	// /deleteSchedule.do
	private void requestDeleteSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		System.out.println("���� - ���� ��� : "+ userInfo.getMemberCode());
		
		ScheduleManager manager = new ScheduleManager();
		
		//������ �о��
		String scheduleCode = request.getParameter("scheduleCode");
		System.out.println(scheduleCode);
		
		int check = manager.deleteIndividualScheduleInfo(scheduleCode);
		if(check == -1)
		{
			System.out.println("���� ���� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("���� ������ �����߽��ϴ�.");
		}
		else
		{
			System.out.println("���� ���� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(scheduleCode);
		}
	}


}
