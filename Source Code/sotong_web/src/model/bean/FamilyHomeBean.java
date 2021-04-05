package model.bean;

import java.io.Serializable;
import java.util.Arrays;

public class FamilyHomeBean implements Serializable {
	private String homeName;
	private String memberName;
	private String memberBirth;
	private String memberPhoto;
	private String[][] familyMemberList;
	
	public FamilyHomeBean() {
		super();
	}
	public FamilyHomeBean(String homeName, String memberName,
			String memberBirth, String memberPhoto, String[][] familyMemberList) {
		super();
		this.homeName = homeName;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.memberPhoto = memberPhoto;
		this.familyMemberList = familyMemberList;
	}
	public String getHomeName() {
		return homeName;
	}
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getMemberPhoto() {
		return memberPhoto;
	}
	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}
	public String[][] getFamilyMemberList() {
		return familyMemberList;
	}
	public void setFamilyMemberList(String[][] familyMemberList) {
		this.familyMemberList = familyMemberList;
	}
	@Override
	public String toString() {
		return "FamilyHomeBean [homeName=" + homeName + ", memberName="
				+ memberName + ", memberBirth=" + memberBirth
				+ ", memberPhoto=" + memberPhoto + ", familyMemberList="
				+ Arrays.toString(familyMemberList) + "]";
	}
}
