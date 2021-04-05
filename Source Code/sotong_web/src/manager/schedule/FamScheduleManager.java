package manager.schedule;

import dao.memu.MenuViewDAO;
import dao.schedule.FamilyEventDAO;
import dao.schedule.FamilyScheduleDAO;
import dao.schedule.FamilyScheduleVO;
import dao.schedule.FamilyScheduleViewDAO;
import dao.schedule.FamilyScheduleViewVO;

public class FamScheduleManager {
	private FamilyScheduleDAO familyScheduleDAO;
	private FamilyScheduleViewDAO familyScheduleViewDAO;
	private FamilyEventDAO familyEventDAO;
	private MenuViewDAO menuViewDAO;
	
	public FamScheduleManager() {
		this.familyScheduleDAO = new FamilyScheduleDAO();
		this.familyScheduleViewDAO = new FamilyScheduleViewDAO();
		this.familyEventDAO = new FamilyEventDAO();
		this.menuViewDAO = new MenuViewDAO();
	}
	
	public FamilyScheduleVO getFamilyScheduleInfo(String familyScheduleCode)
	{
		return this.familyScheduleDAO.selectFamilySchedule(familyScheduleCode);
	}
	
	public FamilyScheduleViewVO[] getFamilyScheduleInfoListWeb(String family_schedule_code) 
	{
		return familyScheduleViewDAO.selectFamilyScheduleView(family_schedule_code);
	}
	
	public FamilyScheduleViewVO[] getFamilyScheduleInfoListWebByHomeCode(String familyHomeCode) 
	{
		return familyScheduleViewDAO.selectFamilyScheduleViewByHomeCode(familyHomeCode);
	}
	
	public FamilyScheduleVO[] getSimpleFamilyScheduleList(String familyHomeCode)
	{
		return familyScheduleDAO.selectFamilyScheduleList(familyHomeCode);
	}
	
	public String addFamilyScheduleInfo(String family_home_code, String member_code, String family_schedule_title, String family_schedule_place, String family_schedule_start_date, String family_schedule_end_date, String family_schedule_alarm, int family_schedule_repeat, String family_schedule_memo)
	{
		return familyScheduleDAO.insertFamilyScheduleAndReturnCode(family_home_code, member_code, family_schedule_title, family_schedule_place, family_schedule_start_date, family_schedule_end_date, family_schedule_alarm, family_schedule_repeat, family_schedule_memo);
	}
	
	public int updateFamilyScheduleInfo(String family_schedule_code, String family_schedule_title, String family_schedule_place, String family_schedule_start_date, String family_schedule_end_date, String family_schedule_alarm, int family_schedule_repeat, String family_schedule_memo)
	{
		return familyScheduleDAO.updateFamilySchedule(family_schedule_code, family_schedule_title, family_schedule_place, family_schedule_start_date, family_schedule_end_date, family_schedule_alarm, family_schedule_repeat, family_schedule_memo);
	}
	
	public int deleteFamilyScheduleInfo(String family_schedule_code)
	{
		return familyScheduleDAO.deleteFamilySchedule(family_schedule_code);
	}
}
