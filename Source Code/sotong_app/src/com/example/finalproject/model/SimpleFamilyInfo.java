package com.example.finalproject.model;

public class SimpleFamilyInfo {
	private String memberCode;
	private String familyImage;
	private String familyName;
	private String familyBirth;
	
	public SimpleFamilyInfo() {
		super();
	}

	public SimpleFamilyInfo(String memberCode, String familyImage,
			String familyName, String familyBirth) {
		super();
		this.memberCode = memberCode;
		this.familyImage = familyImage;
		this.familyName = familyName;
		this.familyBirth = familyBirth;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public String getFamilyImage() {
		return familyImage;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getFamilyBirth() {
		return familyBirth;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public void setFamilyImage(String familyImage) {
		this.familyImage = familyImage;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setFamilyBirth(String familyBirth) {
		this.familyBirth = familyBirth;
	}

	@Override
	public String toString() {
		return "SimpleFamilyInfo [memberCode=" + memberCode + ", familyImage="
				+ familyImage + ", familyName=" + familyName + ", familyBirth="
				+ familyBirth + "]";
	}
	
}
