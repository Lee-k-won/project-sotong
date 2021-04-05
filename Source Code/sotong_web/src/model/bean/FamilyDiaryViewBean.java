package model.bean;

import java.util.Arrays;

public class FamilyDiaryViewBean {
	private String familyDiaryCode;
	private String familyHomeCode;
	private String familyDiaryDate;
	private FamilyDiaryPartBean[] familyDiaryPart;
	
	public FamilyDiaryViewBean() {
		// TODO Auto-generated constructor stub
	}

	public FamilyDiaryViewBean(String familyDiaryCode, String familyHomeCode,
			String familyDiaryDate, FamilyDiaryPartBean[] familyDiaryPart) {
		super();
		this.familyDiaryCode = familyDiaryCode;
		this.familyHomeCode = familyHomeCode;
		this.familyDiaryDate = familyDiaryDate;
		this.familyDiaryPart = familyDiaryPart;
	}

	public String getFamilyDiaryCode() {
		return familyDiaryCode;
	}

	public void setFamilyDiaryCode(String familyDiaryCode) {
		this.familyDiaryCode = familyDiaryCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public String getFamilyDiaryDate() {
		return familyDiaryDate;
	}

	public void setFamilyDiaryDate(String familyDiaryDate) {
		this.familyDiaryDate = familyDiaryDate;
	}

	public FamilyDiaryPartBean[] getFamilyDiaryPart() {
		return familyDiaryPart;
	}

	public void setFamilyDiaryPart(FamilyDiaryPartBean[] familyDiaryPart) {
		this.familyDiaryPart = familyDiaryPart;
	}

	@Override
	public String toString() {
		return "FamilyDiaryViewBean [familyDiaryCode=" + familyDiaryCode
				+ ", familyHomeCode=" + familyHomeCode + ", familyDiaryDate="
				+ familyDiaryDate + ", familyDiaryPart="
				+ Arrays.toString(familyDiaryPart) + "]";
	}
}
