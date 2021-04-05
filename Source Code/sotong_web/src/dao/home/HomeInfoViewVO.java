package dao.home;

import java.io.Serializable;
import java.util.Date;

public class HomeInfoViewVO implements Serializable{

	private static final long serialVersionUID = 9056765019262544915L;
	private String familyHomeCode;
	private String familyHomeName;
	private String memberCode;	
	private String memberPhone;
	private String memberEmail;
	private String memberPhoto;
	private String memberNickName;
	private String memberColor;
	private String memberBirth;
	private String memberRole;
	
	public HomeInfoViewVO() {
		// TODO Auto-generated constructor stub
	}

	public HomeInfoViewVO(String familyHomeCode, String familyHomeName,
			String memberCode, String memberPhone, String memberEmail,
			String memberPhoto, String memberNickName, String memberColor,
			String memberBirth, String memberRole) {
		super();
		this.familyHomeCode = familyHomeCode;
		this.familyHomeName = familyHomeName;
		this.memberCode = memberCode;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberPhoto = memberPhoto;
		this.memberNickName = memberNickName;
		this.memberColor = memberColor;
		this.memberBirth = memberBirth;
		this.memberRole = memberRole;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public String getFamilyHomeName() {
		return familyHomeName;
	}

	public void setFamilyHomeName(String familyHomeName) {
		this.familyHomeName = familyHomeName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getMemberColor() {
		return memberColor;
	}

	public void setMemberColor(String memberColor) {
		this.memberColor = memberColor;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "HomeInfoViewVO [familyHomeCode=" + familyHomeCode
				+ ", familyHomeName=" + familyHomeName + ", memberCode="
				+ memberCode + ", memberPhone=" + memberPhone
				+ ", memberEmail=" + memberEmail + ", memberPhoto="
				+ memberPhoto + ", memberNickName=" + memberNickName
				+ ", memberColor=" + memberColor + ", memberBirth="
				+ memberBirth + ", memberRole=" + memberRole + "]";
	}
}
	
	