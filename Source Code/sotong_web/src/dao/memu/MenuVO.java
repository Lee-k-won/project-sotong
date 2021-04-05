package dao.memu;

import java.io.Serializable;
import java.util.Date;

public class MenuVO implements Serializable{	

	private static final long serialVersionUID = 574362065756671716L;
	private String menuCode;
	private String dinner;
	private String shareDate;
	
	public MenuVO() {
		// TODO Auto-generated constructor stub
	}

	public MenuVO(String menuCode, String dinner, String shareDate) {
		super();
		this.menuCode = menuCode;
		this.dinner = dinner;
		this.shareDate = shareDate;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public String getShareDate() {
		return shareDate;
	}

	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MenuVO [menuCode=" + menuCode + ", dinner=" + dinner
				+ ", shareDate=" + shareDate + "]";
	}
}
