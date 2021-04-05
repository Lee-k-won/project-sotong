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
		System.out.println("삭제 : " + scheduleCode);
		
		int check = manager.deleteFamilyScheduleInfo(scheduleCode);
		if(check <= 0)
		{
			System.out.println("일정 삭제 실패");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("일정 삭제에 실패했습니다.");
		}
		else
		{
			System.out.println("일정 삭제 성공");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(scheduleCode);
		}
	}
	
	// /famScheduleList.do
	private void requestSimpleFamilyScheduleList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("famScheduleList.do 들어옴");
		HttpSession session = request.getSession();
		FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
		String familyHomeCode = homeInfo.getFamilyHomeCode();
		
		FamScheduleManager manager = new FamScheduleManager();
		
		// 가족 일정을 가져옴
		FamilyScheduleViewVO[] voList = manager.getFamilyScheduleInfoListWebByHomeCode(familyHomeCode);
		ArrayList<FamEvent> events = new ArrayList<FamEvent>();
		
		// 개인 일정을 가져옴
		
		if(voList != null)
		{
			System.out.println("일정 출력" + voList.length);
//			for(int i=0; i<voList.length;i++)
//			{
//				
////				System.out.println("시작일 : " + returnISODate(voList[i].getScheduleStartDate()));
////				System.out.println("종료일 : " + returnISODate(voList[i].getScheduleEndDate()));
//				Event event = new Event(voList[i].getScheduleTitle(), returnISODate(voList[i].getScheduleStartDate()), returnISODate(voList[i].getScheduleEndDate()));
//				events.add(event);
//			}
			
			for(int i=0; i<voList.length; i++)
			{
				String start = voList[i].getFamilyScheduleStartDate();
				System.out.println("시작일 : " + start);
				String end = voList[i].getFamilyScheduleEndDate();
				System.out.println("종료일 : " + end);
				
				
				if(start.equals(end)) // 시작일과 종료일이 같으면
				{
					FamEvent event = new FamEvent(voList[i].getFamilyScheduleCode(), voList[i].getFamilyScheduleTitle(), start, end, voList[i].getFamilySchedulePlace(), true, "#00CCFF", voList[i].getFamilyScheduleRepeat(), voList[i].getFamilyEventRequest(), voList[i].getFamilyResponseContents(),voList[i].getFamilyScheduleAlarm(), voList[i].getFamilyScheduleMemo());
					events.add(event);
				}
				else // 시작일과 종료일이 다르면
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
		
		System.out.println("famSchedule.do 들어옴");
		
//		String familyScheduleCode = request.getParameter("famScheduleCode");
		
//		FamilyScheduleVO vo = manager.getFamilyScheduleInfo(familyScheduleCode);
		
//		System.out.println("가족 일정 검색 결과(68라인) : " + vo);
		
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
		
		//제목
		String scheduleTitle = request.getParameter("scheduleTitle");
		System.out.println(scheduleTitle);
		
		//장소
		String schedulePlace = request.getParameter("schedulePlace");
		System.out.println(schedulePlace);
		
		//시작일
		String startDate = request.getParameter("startDate");
		System.out.println(startDate);
		String startTime = request.getParameter("startTime");
		System.out.println(startTime);
				
		String scheduleStartDate = startDate.substring(2,4)+"-" + startDate.substring(5,7)+"-" + startDate.substring(8,10)+ " " + startTime;
		
		//종료일
		
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
		
		//반복
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
		
		//질문
		
		String eventRequest = request.getParameter("eventQ");
		//알람
		
		String scheduleAlarm = request.getParameter("alarmSelec");
		
		// 메모
		
		String scheduleMemo = request.getParameter("memo");
		
		String scheduleCode = manager.addFamilyScheduleInfo(familyHomeCode, memberCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
			
		System.out.println("가족일정 추가 데이터 출력.");
		
		System.out.println("가족 홈 코드 : " + familyHomeCode);
		System.out.println("멤버 코드 : " + memberCode);
		System.out.println("일정 제목  : " + scheduleTitle);
		System.out.println("일정 장소 : " + schedulePlace);
		System.out.println("일정 시작시간 : " + scheduleStartDate);
		System.out.println("일정 종료시간 : " + scheduleEndDate );
		System.out.println("일정 반복 : " + scheduleRepeat);
		System.out.println("일정 알람 : " + scheduleAlarm);
		System.out.println("일정 이벤트 질문 : " + eventRequest);
		System.out.println("일정 메모 : " + scheduleMemo);
		
		if(scheduleCode == null)
		{
			System.out.println("가족일정 추가 실패");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("가족일정추가에 실패했습니다.");
		}
		else
		{
			System.out.println("가족일정 코드 : " + scheduleCode);
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
			System.out.println("가족일정 추가 성공");
			
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
		
		// 스케쥴 코드
		
		String scheduleCode = request.getParameter("scheduleCode");
		//제목
		String scheduleTitle = request.getParameter("scheduleTitle");
		System.out.println(scheduleTitle);
		
		//장소
		String schedulePlace = request.getParameter("schedulePlace");
		System.out.println(schedulePlace);
		
		//시작일
		String startDate = request.getParameter("startDate");
		System.out.println("시작날 : " + startDate);
		String startTime = request.getParameter("startTime");
		System.out.println("시작시간 : " + startTime);
				
		String scheduleStartDate = startDate.substring(2,4)+"-" + startDate.substring(5,7)+"-" + startDate.substring(8,10)+ " " + startTime;
		
		//종료일
		
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
		
		//반복
		
		int scheduleRepeat = Integer.parseInt(request.getParameter("repeatSelec"));
		
		//질문
		
		String eventRequest = request.getParameter("eventQ");
		//알람
		
		String scheduleAlarm = request.getParameter("alarmSelec");
		
		// 메모
		
		String scheduleMemo = request.getParameter("memo");
			
		System.out.println("가족일정 추가 데이터 출력.");
		
		System.out.println("가족 홈 코드 : " + familyHomeCode);
		System.out.println("멤버 코드 : " + memberCode);
		System.out.println("스케쥴 코드 : " + scheduleCode);
		System.out.println("일정 제목  : " + scheduleTitle);
		System.out.println("일정 장소 : " + schedulePlace);
		System.out.println("일정 시작시간 : " + scheduleStartDate);
		System.out.println("일정 종료시간 : " + scheduleEndDate );
		System.out.println("일정 반복 : " + scheduleRepeat);
		System.out.println("일정 알람 : " + scheduleAlarm);
		System.out.println("일정 이벤트 질문 : " + eventRequest);
		System.out.println("일정 메모 : " + scheduleMemo);
		
		int check = manager.updateFamilyScheduleInfo(scheduleCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
		
		if(check <= 0)
		{
			System.out.println("가족일정 수정 실패");
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("가족일정수정에 실패했습니다.");
		}
		else
		{
			System.out.println("가족일정 코드 : " + scheduleCode);
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
			System.out.println("가족일정 수정 성공");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
	}
		
}
