package com.example.finalproject.model;

import java.util.Date;

public class FamilyDiaryDetail {
	
	//f d p c, memberNickname, 
	//f d p dAte, sotongContentsCode, contents, 
	//emoticonName, emoticonRoute, 
	//imagename, imageWrittenDate
	
	private String familyDiaryPartCode;
	private String memberNickName;
	private String memberPhoto;
	private String familyDiaryPartDate;
	private String sotongContentsCode;  //
	private String contents;            //
	private String emoticonName;        
	private String emoticonRoute;       
	private String imageName;         //
	private String imageWrittenDate;   //
	
	public FamilyDiaryDetail() {
		// TODO Auto-generated constructor stub
	}

	public FamilyDiaryDetail(String familyDiaryPartCode, String memberNickName,
			String memberPhoto, String familyDiaryPartDate,
			String sotongContentsCode, String contents, String emoticonName,
			String emoticonRoute, String imageName, String imageWrittenDate) {
		super();
		this.familyDiaryPartCode = familyDiaryPartCode;
		this.memberNickName = memberNickName;
		this.memberPhoto = memberPhoto;
		this.familyDiaryPartDate = familyDiaryPartDate;
		this.sotongContentsCode = sotongContentsCode;
		this.contents = contents;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
	}

	public String getFamilyDiaryPartCode() {
		return familyDiaryPartCode;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public String getMemberPhoto() {
		return memberPhoto;
	}

	public String getFamilyDiaryPartDate() {
		return familyDiaryPartDate;
	}

	public String getSotongContentsCode() {
		return sotongContentsCode;
	}

	public String getContents() {
		return contents;
	}

	public String getEmoticonName() {
		return emoticonName;
	}

	public String getEmoticonRoute() {
		return emoticonRoute;
	}

	public String getImageName() {
		return imageName;
	}

	public String getImageWrittenDate() {
		return imageWrittenDate;
	}

	public void setFamilyDiaryPartCode(String familyDiaryPartCode) {
		this.familyDiaryPartCode = familyDiaryPartCode;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public void setFamilyDiaryPartDate(String familyDiaryPartDate) {
		this.familyDiaryPartDate = familyDiaryPartDate;
	}

	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setEmoticonName(String emoticonName) {
		this.emoticonName = emoticonName;
	}

	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setImageWrittenDate(String imageWrittenDate) {
		this.imageWrittenDate = imageWrittenDate;
	}

	@Override
	public String toString() {
		return "FamilyDiaryDetail [familyDiaryPartCode=" + familyDiaryPartCode
				+ ", memberNickName=" + memberNickName + ", memberPhoto="
				+ memberPhoto + ", familyDiaryPartDate=" + familyDiaryPartDate
				+ ", sotongContentsCode=" + sotongContentsCode + ", contents="
				+ contents + ", emoticonName=" + emoticonName
				+ ", emoticonRoute=" + emoticonRoute + ", imageName="
				+ imageName + ", imageWrittenDate=" + imageWrittenDate + "]";
	}

	
	
	
	
}
