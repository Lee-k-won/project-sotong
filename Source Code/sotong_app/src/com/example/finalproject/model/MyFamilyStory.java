package com.example.finalproject.model;

public class MyFamilyStory {
	private String storyCode;
	private String familyHomeCode;
	private String familyHomeName;
	private String memberCode;
	private String memberPhoto;
	private String memberColor;
	private String memberNickName;
	private String contents;
	private String sotongContentsCode;
	private String imageName;
	private String imageWrittenDate;
	private String emoticonName;
	private String emoticonRoute;
	private String storyDate;
	private int storyHeart;
	private String storyModifyDate;
	private String storyScope;
	
	public MyFamilyStory(String storyCode, String familyHomeCode,
			String familyHomeName, String memberCode, String memberPhoto,
			String memberColor, String memberNickName, String contents,
			String sotongContentsCode, String imageName,
			String imageWrittenDate, String emoticonName, String emoticonRoute,
			String storyDate, int storyHeart, String storyModifyDate,
			String storyScope) {
		super();
		this.storyCode = storyCode;
		this.familyHomeCode = familyHomeCode;
		this.familyHomeName = familyHomeName;
		this.memberCode = memberCode;
		this.memberPhoto = memberPhoto;
		this.memberColor = memberColor;
		this.memberNickName = memberNickName;
		this.contents = contents;
		this.sotongContentsCode = sotongContentsCode;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
		this.storyDate = storyDate;
		this.storyHeart = storyHeart;
		this.storyModifyDate = storyModifyDate;
		this.storyScope = storyScope;
	}
	public String getStoryCode() {
		return storyCode;
	}
	public String getFamilyHomeCode() {
		return familyHomeCode;
	}
	public String getFamilyHomeName() {
		return familyHomeName;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public String getMemberPhoto() {
		return memberPhoto;
	}
	public String getMemberColor() {
		return memberColor;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public String getContents() {
		return contents;
	}
	public String getSotongContentsCode() {
		return sotongContentsCode;
	}
	public String getImageName() {
		return imageName;
	}
	public String getImageWrittenDate() {
		return imageWrittenDate;
	}
	public String getEmoticonName() {
		return emoticonName;
	}
	public String getEmoticonRoute() {
		return emoticonRoute;
	}
	public String getStoryDate() {
		return storyDate;
	}
	public int getStoryHeart() {
		return storyHeart;
	}
	public String getStoryModifyDate() {
		return storyModifyDate;
	}
	public String getStoryScope() {
		return storyScope;
	}
	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}
	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}
	public void setFamilyHomeName(String familyHomeName) {
		this.familyHomeName = familyHomeName;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}
	public void setMemberColor(String memberColor) {
		this.memberColor = memberColor;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public void setImageWrittenDate(String imageWrittenDate) {
		this.imageWrittenDate = imageWrittenDate;
	}
	public void setEmoticonName(String emoticonName) {
		this.emoticonName = emoticonName;
	}
	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}
	public void setStoryDate(String storyDate) {
		this.storyDate = storyDate;
	}
	public void setStoryHeart(int storyHeart) {
		this.storyHeart = storyHeart;
	}
	public void setStoryModifyDate(String storyModifyDate) {
		this.storyModifyDate = storyModifyDate;
	}
	public void setStoryScope(String storyScope) {
		this.storyScope = storyScope;
	}
	@Override
	public String toString() {
		return "MyFamilyStory [storyCode=" + storyCode + ", familyHomeCode="
				+ familyHomeCode + ", familyHomeName=" + familyHomeName
				+ ", memberCode=" + memberCode + ", memberPhoto=" + memberPhoto
				+ ", memberColor=" + memberColor + ", memberNickName="
				+ memberNickName + ", contents=" + contents
				+ ", sotongContentsCode=" + sotongContentsCode + ", imageName="
				+ imageName + ", imageWrittenDate=" + imageWrittenDate
				+ ", emoticonName=" + emoticonName + ", emoticonRoute="
				+ emoticonRoute + ", storyDate=" + storyDate + ", storyHeart="
				+ storyHeart + ", storyModifyDate=" + storyModifyDate
				+ ", storyScope=" + storyScope + "]";
	}
	
	
}
