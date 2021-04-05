package dao.memu;

import java.io.Serializable;

public class LunchVO implements Serializable{

	private static final long serialVersionUID = 8590680331021724448L;
	private String lunchCode;
	private String memberCode;
	private String menuCode;
	private String lunch;
	public LunchVO() {
		super();
	}
	public LunchVO(String lunchCode, String memberCode, String menuCode,
			String lunch) {
		super();
		this.lunchCode = lunchCode;
		this.memberCode = memberCode;
		this.menuCode = menuCode;
		this.lunch = lunch;
	}
	public String getLunchCode() {
		return lunchCode;
	}
	public void setLunchCode(String lunchCode) {
		this.lunchCode = lunchCode;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	
	public String toString() {
		return "LunchVO [lunchCode=" + lunchCode + ", memberCode=" + memberCode
				+ ", menuCode=" + menuCode + ", lunch=" + lunch + "]";
	}
	
}
