package dao.schedule;

import java.io.Serializable;
import java.util.Date;

public class FamilyScheduleViewVO implements Serializable{

	private static final long serialVersionUID = 8221646093522624355L;
	private String familyScheduleCode;
	private String familyHomeCode;
	private String memberCode;
	private String memberNickname;
	private String familyScheduleTitle;
	private String familySchedulePlace;
	private String familyScheduleStartDate;
	private String familyScheduleEndDate;
	private String familyScheduleAlarm;
	private int familyScheduleRepeat;
	private String familyScheduleMemo;
	private String familyEventRequest;
	private String memberName;
	private String familyResponseContents;
	
	public FamilyScheduleViewVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FamilyScheduleViewVO(String familyScheduleCode,
			String familyHomeCode, String memberCode, String memberNickname,
			String familyScheduleTitle, String familySchedulePlace,
			String familyScheduleStartDate, String familyScheduleEndDate,
			String familyScheduleAlarm, int familyScheduleRepeat,
			String familyScheduleMemo, String familyEventRequest,
			String memberName, String familyResponseContents) {
		super();
		this.familyScheduleCode = familyScheduleCode;
		this.familyHomeCode = familyHomeCode;
		this.memberCode = memberCode;
		this.memberNickname = memberNickname;
		this.familyScheduleTitle = familyScheduleTitle;
		this.familySchedulePlace = familySchedulePlace;
		this.familyScheduleStartDate = familyScheduleStartDate;
		this.familyScheduleEndDate = familyScheduleEndDate;
		this.familyScheduleAlarm = familyScheduleAlarm;
		this.familyScheduleRepeat = familyScheduleRepeat;
		this.familyScheduleMemo = familyScheduleMemo;
		this.familyEventRequest = familyEventRequest;
		this.memberName = memberName;
		this.familyResponseContents = familyResponseContents;
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
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
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
	public String getFamilyEventRequest() {
		return familyEventRequest;
	}
	public void setFamilyEventRequest(String familyEventRequest) {
		this.familyEventRequest = familyEventRequest;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getFamilyResponseContents() {
		return familyResponseContents;
	}
	public void setFamilyResponseContents(String familyResponseContents) {
		this.familyResponseContents = familyResponseContents;
	}
	@Override
	public String toString() {
		return "FamilyScheduleViewVO [familyScheduleCode=" + familyScheduleCode
				+ ", familyHomeCode=" + familyHomeCode + ", memberCode="
				+ memberCode + ", memberNickname=" + memberNickname
				+ ", familyScheduleTitle=" + familyScheduleTitle
				+ ", familySchedulePlace=" + familySchedulePlace
				+ ", familyScheduleStartDate=" + familyScheduleStartDate
				+ ", familyScheduleEndDate=" + familyScheduleEndDate
				+ ", familyScheduleAlarm=" + familyScheduleAlarm
				+ ", familyScheduleRepeat=" + familyScheduleRepeat
				+ ", familyScheduleMemo=" + familyScheduleMemo
				+ ", familyEventRequest=" + familyEventRequest
				+ ", memberName=" + memberName + ", familyResponseContents="
				+ familyResponseContents + "]";
	}
}
	