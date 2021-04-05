package dao.sotong;

import java.io.Serializable;


public class EmoticonCategoryVO implements Serializable{

	private static final long serialVersionUID = 3711581994323635911L;
	private String emoticonCategoryCode;
	private String emoticonCategory;
	
	public EmoticonCategoryVO(String emoticonCategoryCode,
			String emoticonCategory) {
		super();
		this.emoticonCategoryCode = emoticonCategoryCode;
		this.emoticonCategory = emoticonCategory;
	}
	public EmoticonCategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEmoticonCategoryCode() {
		return emoticonCategoryCode;
	}
	public void setEmoticonCategoryCode(String emoticonCategoryCode) {
		this.emoticonCategoryCode = emoticonCategoryCode;
	}
	public String getEmoticonCategory() {
		return emoticonCategory;
	}
	public void setEmoticonCategory(String emoticonCategory) {
		this.emoticonCategory = emoticonCategory;
	}
	
	public String toString() {
		return "EmoticonCategoryVO [emoticonCategoryCode="
				+ emoticonCategoryCode + ", emoticonCategory="
				+ emoticonCategory + "]";
	}

	
	

}
