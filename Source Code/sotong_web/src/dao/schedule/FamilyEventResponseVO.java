package dao.schedule;

import java.io.Serializable;

public class FamilyEventResponseVO implements Serializable{

	private static final long serialVersionUID = 4284704375699333402L;
	private String familyEventResponseCode;
	private String memberCode;
	private String familyEventCode;
	private String familyResponseContents;
	
	public FamilyEventResponseVO() {
		super();
	}

	public FamilyEventResponseVO(String familyEventResponseCode,
			String memberCode, String familyEventCode,
			String familyResponseContents) {
		super();
		this.familyEventResponseCode = familyEventResponseCode;
		this.memberCode = memberCode;
		this.familyEventCode = familyEventCode;
		this.familyResponseContents = familyResponseContents;
	}

	public String getFamilyEventResponseCode() {
		return familyEventResponseCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public String getFamilyEventCode() {
		return familyEventCode;
	}

	public String getFamilyResponseContents() {
		return familyResponseContents;
	}

	public void setFamilyEventResponseCode(String familyEventResponseCode) {
		this.familyEventResponseCode = familyEventResponseCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public void setFamilyEventCode(String familyEventCode) {
		this.familyEventCode = familyEventCode;
	}

	public void setFamilyResponseContents(String familyResponseContents) {
		this.familyResponseContents = familyResponseContents;
	}

	public String toString() {
		return "FamilyEventResponseVO [familyEventResponseCode="
				+ familyEventResponseCode + ", memberCode=" + memberCode
				+ ", familyEventCode=" + familyEventCode
				+ ", familyResponseContents=" + familyResponseContents + "]";
	}
	
	
	
}
