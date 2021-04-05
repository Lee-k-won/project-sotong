package dao.schedule;

import java.io.Serializable;

public class AlarmFamilyVO implements Serializable{

	private static final long serialVersionUID = -2006992513156787634L;
	private String alarmFamilyCode;
	private String memberCode;
	private String scheduleCode;
		
	public AlarmFamilyVO() {
		super();
	}

	public AlarmFamilyVO(String alarmFamilyCode, String memberCode, String scheduleCode) {
		super();
		this.alarmFamilyCode = alarmFamilyCode;
		this.memberCode = memberCode;
		this.scheduleCode = scheduleCode;
	}

	public String getAlarmFamilyCode() {
		return alarmFamilyCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public String getScheduleCode() {
		return scheduleCode;
	}

	public void setAlarmFamilyCode(String alarmFamilyCode) {
		this.alarmFamilyCode = alarmFamilyCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public String toString() {
		return "AlarmFamilyVO [alarmFamilyCode=" + alarmFamilyCode
				+ ", memberCode=" + memberCode + ", scheduleCode="
				+ scheduleCode + "]";
	}
	
	
}
