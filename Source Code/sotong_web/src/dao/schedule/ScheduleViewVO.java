package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class ScheduleViewVO implements Serializable{

	private static final long serialVersionUID = 140909102521968164L;
	private String scheduleCode;
	private String familyHomeCode;
	private String scheduleMemberCode;
	private String scheduleMember;
	private String scheduleTitle;
	private String schedulePlace;
	private String scheduleStartDate;
	private String scheduleEndDate;
	private String scheduleAlarm;
	private int scheduleRepeat;
	private String scheduleMemo;
	private String alarmMember;
		
	public ScheduleViewVO() {
		// TODO Auto-generated constructor stub
	}



	public ScheduleViewVO(String scheduleCode, String familyHomeCode,
			String scheduleMemberCode, String scheduleMember,
			String scheduleTitle, String schedulePlace,
			String scheduleStartDate, String scheduleEndDate,
			String scheduleAlarm, int scheduleRepeat, String scheduleMemo,
			String alarmMember) {
		super();
		this.scheduleCode = scheduleCode;
		this.familyHomeCode = familyHomeCode;
		this.scheduleMemberCode = scheduleMemberCode;
		this.scheduleMember = scheduleMember;
		this.scheduleTitle = scheduleTitle;
		this.schedulePlace = schedulePlace;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.scheduleAlarm = scheduleAlarm;
		this.scheduleRepeat = scheduleRepeat;
		this.scheduleMemo = scheduleMemo;
		this.alarmMember = alarmMember;
	}



	public String getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public String getScheduleMemberCode() {
		return scheduleMemberCode;
	}

	public void setScheduleMemberCode(String scheduleMemberCode) {
		this.scheduleMemberCode = scheduleMemberCode;
	}

	public String getScheduleMember() {
		return scheduleMember;
	}

	public void setScheduleMember(String scheduleMember) {
		this.scheduleMember = scheduleMember;
	}

	public String getScheduleTitle() {
		return scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public String getSchedulePlace() {
		return schedulePlace;
	}

	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}

	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public String getScheduleAlarm() {
		return scheduleAlarm;
	}

	public void setScheduleAlarm(String scheduleAlarm) {
		this.scheduleAlarm = scheduleAlarm;
	}

	public int getScheduleRepeat() {
		return scheduleRepeat;
	}

	public void setScheduleRepeat(int scheduleRepeat) {
		this.scheduleRepeat = scheduleRepeat;
	}

	public String getScheduleMemo() {
		return scheduleMemo;
	}

	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}

	public String getAlarmMember() {
		return alarmMember;
	}

	public void setAlarmMember(String alarmMember) {
		this.alarmMember = alarmMember;
	}
	
	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}



	@Override
	public String toString() {
		return "ScheduleViewVO [scheduleCode=" + scheduleCode
				+ ", familyHomeCode=" + familyHomeCode
				+ ", scheduleMemberCode=" + scheduleMemberCode
				+ ", scheduleMember=" + scheduleMember + ", scheduleTitle="
				+ scheduleTitle + ", schedulePlace=" + schedulePlace
				+ ", scheduleStartDate=" + scheduleStartDate
				+ ", scheduleEndDate=" + scheduleEndDate + ", scheduleAlarm="
				+ scheduleAlarm + ", scheduleRepeat=" + scheduleRepeat
				+ ", scheduleMemo=" + scheduleMemo + ", alarmMember="
				+ alarmMember + "]";
	}


}
	