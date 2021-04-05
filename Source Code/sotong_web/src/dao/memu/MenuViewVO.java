package dao.memu;

import java.io.Serializable;

public class MenuViewVO implements Serializable{

	private static final long serialVersionUID = -6060022450623927712L;
	private String menuCode;
	private String familyHomeCode;
	private String shareDate;
	private String memberNickname;
	private String lunch;
	private String dinner;
	
	public MenuViewVO() {
		// TODO Auto-generated constructor stub
	}

	public MenuViewVO(String menuCode, String familyHomeCode, String shareDate,
			String memberNickname, String lunch, String dinner) {
		super();
		this.menuCode = menuCode;
		this.familyHomeCode = familyHomeCode;
		this.shareDate = shareDate;
		this.memberNickname = memberNickname;
		this.lunch = lunch;
		this.dinner = dinner;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public String getShareDate() {
		return shareDate;
	}

	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MenuViewVO [menuCode=" + menuCode + ", familyHomeCode="
				+ familyHomeCode + ", shareDate=" + shareDate
				+ ", memberNickname=" + memberNickname + ", lunch=" + lunch
				+ ", dinner=" + dinner + "]";
	}
}
