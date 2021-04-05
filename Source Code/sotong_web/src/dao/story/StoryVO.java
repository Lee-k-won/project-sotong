package dao.story;

import java.io.Serializable;
import java.util.Date;

public class StoryVO implements Serializable{

	private static final long serialVersionUID = -4387927110225542199L;
	private String storyCode;
	private String memberCode;
	private String homeCode;
	private String sotongCotentsCode;
	private Date storyDate;
	private int storyHeart;
	private Date modifyDate;
	private Boolean storyScope;
	public StoryVO() {
		super();
	}
	public StoryVO(String storyCode, String memberCode, String homeCode,
			String sotongCotentsCode, Date storyDate, int storyHeart,
			Date modifyDate, Boolean storyScope) {
		super();
		this.storyCode = storyCode;
		this.memberCode = memberCode;
		this.homeCode = homeCode;
		this.sotongCotentsCode = sotongCotentsCode;
		this.storyDate = storyDate;
		this.storyHeart = storyHeart;
		this.modifyDate = modifyDate;
		this.storyScope = storyScope;
	}
	public String getStoryCode() {
		return storyCode;
	}
	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getHomeCode() {
		return homeCode;
	}
	public void setHomeCode(String homeCode) {
		this.homeCode = homeCode;
	}
	public String getSotongCotentsCode() {
		return sotongCotentsCode;
	}
	public void setSotongCotentsCode(String sotongCotentsCode) {
		this.sotongCotentsCode = sotongCotentsCode;
	}
	public Date getStoryDate() {
		return storyDate;
	}
	public void setStoryDate(Date storyDate) {
		this.storyDate = storyDate;
	}
	public int getStoryHeart() {
		return storyHeart;
	}
	public void setStoryHeart(int storyHeart) {
		this.storyHeart = storyHeart;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Boolean getStoryScope() {
		return storyScope;
	}
	public void setStoryScope(Boolean storyScope) {
		this.storyScope = storyScope;
	}
	
	public String toString() {
		return "StoryVO [storyCode=" + storyCode + ", memberCode=" + memberCode
				+ ", homeCode=" + homeCode + ", sotongCotentsCode="
				+ sotongCotentsCode + ", storyDate=" + storyDate
				+ ", storyHeart=" + storyHeart + ", modifyDate=" + modifyDate
				+ ", storyScope=" + storyScope + "]";
	}
	
}
