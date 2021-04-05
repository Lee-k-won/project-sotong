package manager.schedule;

import dao.schedule.AlarmFamilyDAO;
import dao.schedule.ScheduleDAO;
import dao.schedule.ScheduleVO;
import dao.schedule.ScheduleViewDAO;
import dao.schedule.ScheduleViewVO;

public class ScheduleManager {
	
	private ScheduleDAO scheduleDAO;
	private ScheduleViewDAO scheduleViewDAO;
	private AlarmFamilyDAO alarmFamilyDAO;
	
	public ScheduleManager() {
		super();
		this.scheduleDAO = new ScheduleDAO();
		this.scheduleViewDAO = new ScheduleViewDAO();
		this.alarmFamilyDAO = new AlarmFamilyDAO();
	}

	public ScheduleManager(ScheduleDAO scheduleDAO,
		ScheduleViewDAO scheduleViewDAO, AlarmFamilyDAO alarmFamilyDAO) {
		super();
		this.scheduleDAO = scheduleDAO;
		this.scheduleViewDAO = scheduleViewDAO;
		this.alarmFamilyDAO = alarmFamilyDAO;
	}
	
	public ScheduleVO[] getSimpleIndividualScheduleInfoListWeb(String memberCode, String year, String mon)
	{
		return scheduleDAO.selectSimpleScheduleInfoListWeb(memberCode, year, mon);
	}
	
	public ScheduleViewVO[] getIndividualScheduleInfoListWeb(String memberCode, String year, String mon)
	{
		return scheduleViewDAO.selectScheduleInfoListWeb(memberCode, year, mon);
	}
	
	public ScheduleViewVO getShceduleViewInfo(String scheduleCode)
	{
		return scheduleViewDAO.selectScheduleView(scheduleCode);
	}
	
	public String[][] getSimpleIndividualScheduleInfoList(String memberCode, String year, String mon, String date)
	{
		return scheduleDAO.selectSimpleScheduleInfoByDate(memberCode, year, mon, date);
	}
	
	public ScheduleVO getIndividualScheduleInfo(String scheduleCode)
	{
		return scheduleDAO.selectSchedule(scheduleCode);
	}
	
	public String addIndividualScheduleInfo(String memberCode, String scheduleTitle, String schedulePlace, String scheduleStartDate, String scheduleEndDate, String scheduleAlarm, int scheduleRepeat, String scheduleMemo, String[] alarmFamilyMemberCode)
	{		
		return scheduleDAO.insertScheduleAndReturnCode(memberCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
		
//		if(scheduleCode==null) // 스케쥴 코드 값이 null이면 insert가 안되었으므로 -1 리턴
//		{
//			return -1;
//		}
//		else // 스케쥴 코드 값을 가지고 왔으면
//		{
//			if(alarmFamilyMemberCode != null) // 알람 멤버가 null이 아니면
//			{
//				for(int i=0; i<alarmFamilyMemberCode.length;i++) // 알람멤버 명수 만큼 반복
//				{
//					int rowNum = alarmFamilyDAO.insertAlarmFamily(alarmFamilyMemberCode[i], scheduleCode);	// 알람멤버 추가
//					if(rowNum != 1) // 알람멤버 추가가 실패하였으면 
//					{
//						return -1;
//					}
//				}
//			}
//		}
//		return 1;
		
	}
	
	
	
	public int updateIndividualScheduleInfo(String scheduleCode, String memberCode, String scheduleTitle, String schedulePlace,  String scheduleStartDate, String scheduleEndDate, String scheduleAlarm, int scheduleRepeat, String scheduleMemo, String[] alarmFamilyMemberCode)
	{
		int num = scheduleDAO.updateSchedule(scheduleCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo);
		if(num <= 0)
		{
			return -1;
		}
		else
		{
			if(alarmFamilyMemberCode != null)
			{
				int deleteNum = alarmFamilyDAO.deleteAlarmFamilyByScheduleCode(scheduleCode); // 등록되어 있는 알람멤버 전체를 삭제한다.
				if(deleteNum<=0)
				{
					return -1; // 삭제에 실패하면 -1 리턴
				}
				else
				{
					for(int i=0;i<alarmFamilyMemberCode.length;i++) // 알람멤버 등록 수 만큼 반복
					{
						int insertNum = alarmFamilyDAO.insertAlarmFamily(alarmFamilyMemberCode[i], memberCode, scheduleCode); // 알람멤버 추가
						if(insertNum <= 0) {return -1;} // 추가 실패시 -1 리턴
					}
				}
			}
			return 1; 
		}
		
	}
	
	public int deleteIndividualScheduleInfo(String scheduleCode)
	{
//		int deleteNum = alarmFamilyDAO.deleteAlarmFamilyByScheduleCode(scheduleCode); // 스케쥴코드로 알람 전체 삭제
//		if(deleteNum<=0) // 삭제가 안되었을 경우
//		{
//			return -1;
//		}
//		else
//		{
			int deleteScheNum = scheduleDAO.deleteSchedule(scheduleCode);
			if(deleteScheNum<=0)
			{
				return -1;
			}
//		}
		return 1;
	}
	
//	public String[] doScheduleMatching(Date date)
//	{
//		s
//	}
	
	public ScheduleViewVO[] getAllFamilyScheduleList(String familyHomeCode)
	{
		return scheduleViewDAO.selectAllFamilySchedule(familyHomeCode);
	}
	
	public ScheduleViewVO[] getAllFamilyScheduleList(String familyHomeCode, String year, String mon)
	{
		return scheduleViewDAO.selectAllFamilySchedule(familyHomeCode, year, mon);
	}
	
	public static void main(String[] args)
	{
		String[] member = {"m1", "m2", "m3"};
		ScheduleManager man = new ScheduleManager();
		//System.out.println(man.addIndividualScheduleInfo("m2", "일정제목이라능", "장소라능", new Date(), new Date(), new Date(), 5, "메모라능", member));
		//System.out.println(man.getShceduleViewInfo("SD14415812337"));
		System.out.println(man.deleteIndividualScheduleInfo("SD144158123624"));
//		System.out.println(man.updateIndividualScheduleInfo(scheduleCode, memberCode, scheduleTitle, schedulePlace, scheduleStartDate, scheduleEndDate, scheduleAlarm, scheduleRepeat, scheduleMemo, alarmFamilyMemberCode));
	}

}
