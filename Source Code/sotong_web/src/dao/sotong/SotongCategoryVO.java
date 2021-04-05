package dao.sotong;

import java.io.Serializable;


public class SotongCategoryVO implements Serializable{

	private static final long serialVersionUID = -3585555671092516581L;
	private String sotongCategoryCode;
	private String sotongCategory;
	public SotongCategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SotongCategoryVO(String sotongCategoryCode, String sotongCategory) {
		super();
		this.sotongCategoryCode = sotongCategoryCode;
		this.sotongCategory = sotongCategory;
	}
	public String getSotongCategoryCode() {
		return sotongCategoryCode;
	}
	public void setSotongCategoryCode(String sotongCategoryCode) {
		this.sotongCategoryCode = sotongCategoryCode;
	}
	public String getSotongCategory() {
		return sotongCategory;
	}
	public void setSotongCategory(String sotongCategory) {
		this.sotongCategory = sotongCategory;
	}
	
	public String toString() {
		return "SotongCategoryVO [sotongCategoryCode=" + sotongCategoryCode
				+ ", sotongCategory=" + sotongCategory + "]";
	}
	
	

}
