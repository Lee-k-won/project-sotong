package dao.sotong;

import java.io.Serializable;


public class SotongContentsVO implements Serializable{

	private static final long serialVersionUID = 4243573411261815669L;
	private String sotongContentsCode;
	private String familyHomeCode;
	private String sotongCategoryCode;
	private String contents;
	private String imageCode;
	private String emotionCode;
	
	public SotongContentsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SotongContentsVO(String sotongContentsCode, String familyHomeCode,
			String sotongCategoryCode, String contents, String imageCode,
			String emotionCode) {
		super();
		this.sotongContentsCode = sotongContentsCode;
		this.familyHomeCode = familyHomeCode;
		this.sotongCategoryCode = sotongCategoryCode;
		this.contents = contents;
		this.imageCode = imageCode;
		this.emotionCode = emotionCode;
	}
	
	public String getSotongContentsCode() {
		return sotongContentsCode;
	}
	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}
	public String getFamilyHomeCode() {
		return familyHomeCode;
	}
	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}
	public String getSotongCategoryCode() {
		return sotongCategoryCode;
	}
	public void setSotongCategoryCode(String sotongCategoryCode) {
		this.sotongCategoryCode = sotongCategoryCode;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getEmotionCode() {
		return emotionCode;
	}
	public void setEmotionCode(String emotionCode) {
		this.emotionCode = emotionCode;
	}
	
	public String toString() {
		return "SotongContentsVO [sotongContentsCode=" + sotongContentsCode
				+ ", familyHomeCode=" + familyHomeCode
				+ ", sotongCategoryCode=" + sotongCategoryCode + ", contents="
				+ contents + ", imageCode=" + imageCode + ", emotionCode="
				+ emotionCode + "]";
	}
	
	

}
