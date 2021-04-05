package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class ScheduleVO implements Serializable{

	private static final long serialVersionUID = 565402312757734695L;
	private String scheduleCode;
	private String memberCode;
	private String scheduleTitle;
	private String schedulePlace;
	private String scheduleStartDate;
	private String scheduleEndDate;
	private String scheduleAlarm;
	private int scheduleRepeat;
	private String scheduleMemo;
	
	public ScheduleVO() {
		// TODO Auto-generated constructor stub
	}

	public ScheduleVO(String scheduleCode, String memberCode,
			String scheduleTitle, String schedulePlace,
			String scheduleStartDate, String scheduleEndDate,
			String scheduleAlarm, int scheduleRepeat, String scheduleMemo) {
		super();
		this.scheduleCode = scheduleCode;
		this.memberCode = memberCode;
		this.scheduleTitle = scheduleTitle;
		this.schedulePlace = schedulePlace;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.scheduleAlarm = scheduleAlarm;
		this.scheduleRepeat = scheduleRepeat;
		this.scheduleMemo = scheduleMemo;
	}

	public String getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
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

	@Override
	public String toString() {
		return "ScheduleVO [scheduleCode=" + scheduleCode + ", memberCode="
				+ memberCode + ", scheduleTitle=" + scheduleTitle
				+ ", schedulePlace=" + schedulePlace + ", scheduleStartDate="
				+ scheduleStartDate + ", scheduleEndDate=" + scheduleEndDate
				+ ", scheduleAlarm=" + scheduleAlarm + ", scheduleRepeat="
				+ scheduleRepeat + ", scheduleMemo=" + scheduleMemo + "]";
	}
}
