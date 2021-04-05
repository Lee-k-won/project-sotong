package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class FamilyScheduleVO implements Serializable{

	private static final long serialVersionUID = -1651747871688446510L;
	private String familyScheduleCode;
	private String familyHomeCode;
	private String memberCode;
	private String familyScheduleTitle;
	private String familySchedulePlace;
	private String familyScheduleStartDate;
	private String familyScheduleEndDate;
	private String familyScheduleAlarm;
	private int familyScheduleRepeat;
	private String familyScheduleMemo;
	
	public FamilyScheduleVO() {
		// TODO Auto-generated constructor stub
	}

	public FamilyScheduleVO(String familyScheduleCode, String familyHomeCode,
			String memberCode, String familyScheduleTitle,
			String familySchedulePlace, String familyScheduleStartDate,
			String familyScheduleEndDate, String familyScheduleAlarm,
			int familyScheduleRepeat, String familyScheduleMemo) {
		super();
		this.familyScheduleCode = familyScheduleCode;
		this.familyHomeCode = familyHomeCode;
		this.memberCode = memberCode;
		this.familyScheduleTitle = familyScheduleTitle;
		this.familySchedulePlace = familySchedulePlace;
		this.familyScheduleStartDate = familyScheduleStartDate;
		this.familyScheduleEndDate = familyScheduleEndDate;
		this.familyScheduleAlarm = familyScheduleAlarm;
		this.familyScheduleRepeat = familyScheduleRepeat;
		this.familyScheduleMemo = familyScheduleMemo;
	}

	public String getFamilyScheduleCode() {
		return familyScheduleCode;
	}

	public void setFamilyScheduleCode(String familyScheduleCode) {
		this.familyScheduleCode = familyScheduleCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getFamilyScheduleTitle() {
		return familyScheduleTitle;
	}

	public void setFamilyScheduleTitle(String familyScheduleTitle) {
		this.familyScheduleTitle = familyScheduleTitle;
	}

	public String getFamilySchedulePlace() {
		return familySchedulePlace;
	}

	public void setFamilySchedulePlace(String familySchedulePlace) {
		this.familySchedulePlace = familySchedulePlace;
	}

	public String getFamilyScheduleStartDate() {
		return familyScheduleStartDate;
	}

	public void setFamilyScheduleStartDate(String familyScheduleStartDate) {
		this.familyScheduleStartDate = familyScheduleStartDate;
	}

	public String getFamilyScheduleEndDate() {
		return familyScheduleEndDate;
	}

	public void setFamilyScheduleEndDate(String familyScheduleEndDate) {
		this.familyScheduleEndDate = familyScheduleEndDate;
	}

	public String getFamilyScheduleAlarm() {
		return familyScheduleAlarm;
	}

	public void setFamilyScheduleAlarm(String familyScheduleAlarm) {
		this.familyScheduleAlarm = familyScheduleAlarm;
	}

	public int getFamilyScheduleRepeat() {
		return familyScheduleRepeat;
	}

	public void setFamilyScheduleRepeat(int familyScheduleRepeat) {
		this.familyScheduleRepeat = familyScheduleRepeat;
	}

	public String getFamilyScheduleMemo() {
		return familyScheduleMemo;
	}

	public void setFamilyScheduleMemo(String familyScheduleMemo) {
		this.familyScheduleMemo = familyScheduleMemo;
	}

	@Override
	public String toString() {
		return "FamilyScheduleVO [familyScheduleCode=" + familyScheduleCode
				+ ", familyHomeCode=" + familyHomeCode + ", memberCode="
				+ memberCode + ", familyScheduleTitle=" + familyScheduleTitle
				+ ", familySchedulePlace=" + familySchedulePlace
				+ ", familyScheduleStartDate=" + familyScheduleStartDate
				+ ", familyScheduleEndDate=" + familyScheduleEndDate
				+ ", familyScheduleAlarm=" + familyScheduleAlarm
				+ ", familyScheduleRepeat=" + familyScheduleRepeat
				+ ", familyScheduleMemo=" + familyScheduleMemo + "]";
	}
}
