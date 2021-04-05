package dao.schedule;

import java.io.Serializable;

public class FamilyEventVO implements Serializable{

	private static final long serialVersionUID = -7354055841841346870L;
	private String familyEventCode;
	private String familyScheduleCode;
	private String familyEventRequest;
	
	public FamilyEventVO() {
		super();
	}
	public FamilyEventVO(String familyEventCode, String familyScheduleCode,
			String familyEventRequest) {
		super();
		this.familyEventCode = familyEventCode;
		this.familyScheduleCode = familyScheduleCode;
		this.familyEventRequest = familyEventRequest;
	}
	public String getFamilyEventCode() {
		return familyEventCode;
	}
	public String getFamilyScheduleCode() {
		return familyScheduleCode;
	}
	public String getFamilyEventRequest() {
		return familyEventRequest;
	}
	public void setFamilyEventCode(String familyEventCode) {
		this.familyEventCode = familyEventCode;
	}
	public void setFamilyScheduleCode(String familyScheduleCode) {
		this.familyScheduleCode = familyScheduleCode;
	}
	public void setFamilyEventRequest(String familyEventRequest) {
		this.familyEventRequest = familyEventRequest;
	}
	public String toString() {
		return "FamilyEventVO [familyEventCode=" + familyEventCode
				+ ", familyScheduleCode=" + familyScheduleCode
				+ ", familyEventRequest=" + familyEventRequest + "]";
	}
	
	
}
