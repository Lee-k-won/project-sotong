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

import manager.schedule.FamScheduleManager;
import model.bean.FamEvent;

import com.google.gson.Gson;

import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;
import dao.schedule.FamilyScheduleViewVO;

/**
 * Servlet implementation class FamilyScheduleServlet
 */
@WebServlet(urlPatterns={ "/famSchedule.do", "/famScheduleList.do", "/famScheduleInsert.do", "/famScheduleUpdate.do", "/famScheduleDelete.do" })
public class FamilyScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FamilyScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		if(action.equals("famSchedule.do"))
		{
			requestFamSchedule(request, response);
		}
		else if(action.equals("famScheduleList.do"))
		{
			requestSimpleFamilyScheduleList(request, response);
		}
		else if(action.equals("famScheduleInsert.do"))
		{
			requestAddFamSchedule(request, response);
		}
		else if(action.equals("famScheduleUpdate.do"))
		{
			requestModifyFamilySchedule(request, response);
		}
		else if(action.equals("famScheduleDelete.do"))
		{
			requestDeleteFamilySchedule(request,response);
		}
		
	}

	
	// /famScheduleDelete.do
	
	private void requestDeleteFamilySchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		FamScheduleManager manager = new FamScheduleManager();
		
		String scheduleCode =request.getParameter("scheduleCode");
		System.out.println("���� : " + scheduleCode);
		
		int check = manager.deleteFamilyScheduleInfo(scheduleCode);
		if(check <= 0)
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
	
	// /famScheduleList.do
	private void requestSimpleFamilyScheduleList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("famScheduleList.do ����");
		HttpSession session = request.getSession();
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String familyHomeCode = homeInfo.getFamilyHomeCode();
		
		FamScheduleManager manager = new FamScheduleManager();
		
		// ���� ������ ������
		FamilyScheduleViewVO[] voList = manager.getFamilyScheduleInfoListWebByHomeCode(familyHomeCode);
		ArrayList<FamEvent> events = new ArrayList<FamEvent>();
		
		// ���� ������ ������
		
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
				String start = voList[i].getFamilyScheduleStartDate();
				System.out.println("������ : " + start);
				String end = voList[i].getFamilyScheduleEndDate();
				System.out.println("������ : " + end);
				
				
				if(start.equals(end)) // �����ϰ� �������� ������
				{
					FamEvent event = new FamEvent(voList[i].getFamilyScheduleCode(), voList[i].getFamilyScheduleTitle(), start, end, voList[i].getFamilySchedulePlace(), true, "#00CCFF", voList[i].getFamilyScheduleRepeat(), voList[i].getFamilyEventRequest(), voList[i].getFamilyResponseContents(),voList[i].getFamilyScheduleAlarm(), voList[i].getFamilyScheduleMemo());
					events.add(event);
				}
				else // �����ϰ� �������� �ٸ���
				{
					FamEvent event = new FamEvent(voList[i].getFamilyScheduleCode(), voList[i].getFamilyScheduleTitle(), start, end, voList[i].getFamilySchedulePlace(), false, "#00CCFF", voList[i].getFamilyScheduleRepeat(), voList[i].getFamilyEventRequest(), voList[i].getFamilyResponseContents(), voList[i].getFamilyScheduleAlarm(), voList[i].getFamilyScheduleMemo());
					events.add(event);
				}
			}
			
			FamEvent[] eventList = events.toArray(new FamEvent[events.size()]);
			String result = new Gson().toJson(eventList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("JSP/schedule/FamilySchedule.jsp");
			rd.forward(request, response);
		}
		
	}
	
	// /famSchedule.do
	private void requestFamSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		FamScheduleManager manager = new FamScheduleManager();
		
		System.out.println("famSchedule.do ����");
		
//		String familyScheduleCode = request.getParameter("famScheduleCode");
		
//		FamilyScheduleVO vo = manager.getFamilyScheduleInfo(familyScheduleCode);
		
//		System.out.println("���� ���� �˻� ���(68����) : " + vo);
		
//		request.setAttribute("famScheInfo", vo);
		
		RequestDispatcher rd = request.getRequestDispatcher("JSP/famSche/FamilySchedule.jsp");
		rd.forward(request, response);
	}
	
	// /famScheduleInsert.do
	private void requestAddFamSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		FamScheduleManager manager = new FamScheduleManager();
		
		String familyHomeCode = userInfo.getFamilyHomecode();
		String memberCode = userInfo.getMemberCode();
		
		
//		String familyScheduleTitle = request.getParameter("famScheTitle");
//		String familySchedulePlace = request.getParameter("famSchePlace");
//		
//		String familyScheduleStartDate = request.getParameter("famScheStartDate");
//		String familyScheduleStartTime = request.getParameter("famScheStartTime");
//		
//		String familyScheduleEndDate = request.getParameter("famScheEndDate");
//		String familyScheduleEndTime = request.getParameter("famScheEndTime");
//		
//		String familyScheduleRepeat = request.getParameter("famScheRepeatSelec");
//		
//		String familyScheduleAlarm = request.getParameter("famScheAlarmSelec");
//		
//		String familyScheduleEventQ = request.getParameter("famScheEventQ");
//		
//		String familyScheduleMemo = request.getParameter("famScheMemo");
		
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
		int scheduleRepeat = -1;
		String repeat = request.getParameter("repeatSelec");
		if(repeat != null)
		{
			scheduleRepeat = Integer.parseInt(request.getParameter("repeatSelec"));
		}
		else
		{
			scheduleRepeat =0;
		}
		
		//����
		
		String eventRequest = request.getParameter("eventQ");
		//�˶�
		
		String scheduleAlarm = request.getParameter("alarmSelec");
		
		// �޸�
		
		String scheduleMemo = request.getParameter("memo");
		
		String scheduleCode = manager.addFamilyScheduleInfo(familyHomeCode, memberCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
			
		System.out.println("�������� �߰� ������ ���.");
		
		System.out.println("���� Ȩ �ڵ� : " + familyHomeCode);
		System.out.println("��� �ڵ� : " + memberCode);
		System.out.println("���� ����  : " + scheduleTitle);
		System.out.println("���� ��� : " + schedulePlace);
		System.out.println("���� ���۽ð� : " + scheduleStartDate);
		System.out.println("���� ����ð� : " + scheduleEndDate );
		System.out.println("���� �ݺ� : " + scheduleRepeat);
		System.out.println("���� �˶� : " + scheduleAlarm);
		System.out.println("���� �̺�Ʈ ���� : " + eventRequest);
		System.out.println("���� �޸� : " + scheduleMemo);
		
		if(scheduleCode == null)
		{
			System.out.println("�������� �߰� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("���������߰��� �����߽��ϴ�.");
		}
		else
		{
			System.out.println("�������� �ڵ� : " + scheduleCode);
			FamEvent event = null;
			if(scheduleEndDate.equals(scheduleStartDate))
			{
				event = new FamEvent(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, true, "#00CCFF", scheduleRepeat, eventRequest, null, scheduleAlarm, scheduleMemo);		
			}
			else
			{
				event = new FamEvent(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, false, "#00CCFF", scheduleRepeat, eventRequest, null, scheduleAlarm, scheduleMemo);
			}
			String result = new Gson().toJson(event);
			System.out.println("�������� �߰� ����");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		//manager.addFamilyScheduleInfo(familyHomeCode, memberCode, familyScheduleTitle, familySchedulePlace, familyScheduleStartDate+" "+familyScheduleStartTime, familyScheduleEndDate+" "+familyScheduleEndTime, familyScheduleAlarm, familyScheduleRepeat, familyScheduleMemo, familyScheduleEventQ);

	}
	
	// /famScheduleUpdate.do
	private void requestModifyFamilySchedule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		FamScheduleManager manager = new FamScheduleManager();
		
		String familyHomeCode = userInfo.getFamilyHomecode();
		String memberCode = userInfo.getMemberCode();
		
		// ������ �ڵ�
		
		String scheduleCode = request.getParameter("scheduleCode");
		//����
		String scheduleTitle = request.getParameter("scheduleTitle");
		System.out.println(scheduleTitle);
		
		//���
		String schedulePlace = request.getParameter("schedulePlace");
		System.out.println(schedulePlace);
		
		//������
		String startDate = request.getParameter("startDate");
		System.out.println("���۳� : " + startDate);
		String startTime = request.getParameter("startTime");
		System.out.println("���۽ð� : " + startTime);
				
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
		
		//����
		
		String eventRequest = request.getParameter("eventQ");
		//�˶�
		
		String scheduleAlarm = request.getParameter("alarmSelec");
		
		// �޸�
		
		String scheduleMemo = request.getParameter("memo");
			
		System.out.println("�������� �߰� ������ ���.");
		
		System.out.println("���� Ȩ �ڵ� : " + familyHomeCode);
		System.out.println("��� �ڵ� : " + memberCode);
		System.out.println("������ �ڵ� : " + scheduleCode);
		System.out.println("���� ����  : " + scheduleTitle);
		System.out.println("���� ��� : " + schedulePlace);
		System.out.println("���� ���۽ð� : " + scheduleStartDate);
		System.out.println("���� ����ð� : " + scheduleEndDate );
		System.out.println("���� �ݺ� : " + scheduleRepeat);
		System.out.println("���� �˶� : " + scheduleAlarm);
		System.out.println("���� �̺�Ʈ ���� : " + eventRequest);
		System.out.println("���� �޸� : " + scheduleMemo);
		
		int check = manager.updateFamilyScheduleInfo(scheduleCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
		
		if(check <= 0)
		{
			System.out.println("�������� ���� ����");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("�������������� �����߽��ϴ�.");
		}
		else
		{
			System.out.println("�������� �ڵ� : " + scheduleCode);
			FamEvent event = null;
			if(scheduleEndDate.equals(scheduleStartDate))
			{
				event = new FamEvent(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, true, "#00CCFF", scheduleRepeat, eventRequest, null, scheduleAlarm, scheduleMemo);		
			}
			else
			{
				event = new FamEvent(scheduleCode, scheduleTitle,scheduleStartDate, scheduleEndDate, schedulePlace, false, "#00CCFF", scheduleRepeat, eventRequest, null, scheduleAlarm, scheduleMemo);
			}
			String result = new Gson().toJson(event);
			System.out.println("�������� ���� ����");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
	}
		
}
