package dao.home;

import java.io.Serializable;
import java.util.Date;


public class FamilyMemberVO implements Serializable{

	private static final long serialVersionUID = 2286605032761996195L;
	private String memberCode;
	private String familyHomecode;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberId;
	private String memberPw;
	private String memberPhoto;
	private String memberNickName;
	private String memberColor;
	private String memberBirth;
	private String memberRole;
	
	public FamilyMemberVO() {
		// TODO Auto-generated constructor stub
	}

	public FamilyMemberVO(String memberCode, String familyHomecode,
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth,
			String memberRole) {
		super();
		this.memberCode = memberCode;
		this.familyHomecode = familyHomecode;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberPhoto = memberPhoto;
		this.memberNickName = memberNickName;
		this.memberColor = memberColor;
		this.memberBirth = memberBirth;
		this.memberRole = memberRole;
	}



	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getFamilyHomecode() {
		return familyHomecode;
	}

	public void setFamilyHomecode(String familyHomecode) {
		this.familyHomecode = familyHomecode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
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
		return "FamilyMemberVO [memberCode=" + memberCode + ", familyHomecode="
				+ familyHomecode + ", memberName=" + memberName
				+ ", memberPhone=" + memberPhone + ", memberEmail="
				+ memberEmail + ", memberId=" + memberId + ", memberPw="
				+ memberPw + ", memberPhoto=" + memberPhoto
				+ ", memberNickName=" + memberNickName + ", memberColor="
				+ memberColor + ", memberBirth=" + memberBirth
				+ ", memberRole=" + memberRole + "]";
	}
}
