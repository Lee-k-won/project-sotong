package dao.sotong;

import java.io.Serializable;

public class EmoticonVO implements Serializable{

	private static final long serialVersionUID = -481159539529538107L;
	private String emoticonCode;
	private String emoticonName;
	private String emoticonRoute;
	private String emoticonCategoryCode;
	public EmoticonVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmoticonVO(String emoticonCode, String emoticonName,
			String emoticonRoute, String emoticonCategoryCode) {
		super();
		this.emoticonCode = emoticonCode;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
		this.emoticonCategoryCode = emoticonCategoryCode;
	}
	public String getEmoticonCode() {
		return emoticonCode;
	}
	public void setEmoticonCode(String emoticonCode) {
		this.emoticonCode = emoticonCode;
	}
	public String getEmoticonName() {
		return emoticonName;
	}
	public void setEmoticonName(String emoticonName) {
		this.emoticonName = emoticonName;
	}
	public String getEmoticonRoute() {
		return emoticonRoute;
	}
	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}
	public String getEmoticonCategoryCode() {
		return emoticonCategoryCode;
	}
	public void setEmoticonCategoryCode(String emoticonCategoryCode) {
		this.emoticonCategoryCode = emoticonCategoryCode;
	}

	public String toString() {
		return "emoticonVO [emoticonCode=" + emoticonCode + ", emoticonName="
				+ emoticonName + ", emoticonRoute=" + emoticonRoute
				+ ", emoticonCategoryCode=" + emoticonCategoryCode + "]";
	}
	
	

}
