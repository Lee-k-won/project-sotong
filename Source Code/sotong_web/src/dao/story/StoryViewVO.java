package dao.story;

import java.io.Serializable;
import java.util.Date;

public class StoryViewVO implements Serializable{

	private static final long serialVersionUID = -1773581059160251030L;
	private String storyCode;
	private String familyHomeCode;
	
	private String familyHomeName;
	
	private String memberCode;
	private String memberPhoto;
	private String memberColor;
	
	private String memberNickname;
	
	private String contents;
	private String contetsCode;
	
	private String imageName;
	private String imageWritenDate;
	private String emotioconName;
	
	private String emoticonRoute;
	private String storyDate;
	
	private int stroyHeart;
	private String storyModifyDate;
	
	private String storyScope;
	
	public StoryViewVO() {
		// TODO Auto-generated constructor stub
	}

	public StoryViewVO(String storyCode, String familyHomeCode,
			String familyHomeName, String memberCode, String memberPhoto,
			String memberColor, String memberNickname, String contents,
			String contetsCode, String imageName, String imageWritenDate,
			String emotioconName, String emoticonRoute, String storyDate,
			int stroyHeart, String storyModifyDate, String storyScope) {
		super();
		this.storyCode = storyCode;
		this.familyHomeCode = familyHomeCode;
		this.familyHomeName = familyHomeName;
		this.memberCode = memberCode;
		this.memberPhoto = memberPhoto;
		this.memberColor = memberColor;
		this.memberNickname = memberNickname;
		this.contents = contents;
		this.contetsCode = contetsCode;
		this.imageName = imageName;
		this.imageWritenDate = imageWritenDate;
		this.emotioconName = emotioconName;
		this.emoticonRoute = emoticonRoute;
		this.storyDate = storyDate;
		this.stroyHeart = stroyHeart;
		this.storyModifyDate = storyModifyDate;
		this.storyScope = storyScope;
	}

	public String getStoryCode() {
		return storyCode;
	}

	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public String getFamilyHomeName() {
		return familyHomeName;
	}

	public void setFamilyHomeName(String familyHomeName) {
		this.familyHomeName = familyHomeName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public String getMemberColor() {
		return memberColor;
	}

	public void setMemberColor(String memberColor) {
		this.memberColor = memberColor;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getContetsCode() {
		return contetsCode;
	}

	public void setContetsCode(String contetsCode) {
		this.contetsCode = contetsCode;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageWritenDate() {
		return imageWritenDate;
	}

	public void setImageWritenDate(String imageWritenDate) {
		this.imageWritenDate = imageWritenDate;
	}

	public String getEmotioconName() {
		return emotioconName;
	}

	public void setEmotioconName(String emotioconName) {
		this.emotioconName = emotioconName;
	}

	public String getEmoticonRoute() {
		return emoticonRoute;
	}

	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}

	public String getStoryDate() {
		return storyDate;
	}

	public void setStoryDate(String storyDate) {
		this.storyDate = storyDate;
	}

	public int getStroyHeart() {
		return stroyHeart;
	}

	public void setStroyHeart(int stroyHeart) {
		this.stroyHeart = stroyHeart;
	}

	public String getStoryModifyDate() {
		return storyModifyDate;
	}

	public void setStoryModifyDate(String storyModifyDate) {
		this.storyModifyDate = storyModifyDate;
	}

	public String getStoryScope() {
		return storyScope;
	}

	public void setStoryScope(String storyScope) {
		this.storyScope = storyScope;
	}

	@Override
	public String toString() {
		return "StoryViewVO [storyCode=" + storyCode + ", familyHomeCode="
				+ familyHomeCode + ", familyHomeName=" + familyHomeName
				+ ", memberCode=" + memberCode + ", memberPhoto=" + memberPhoto
				+ ", memberColor=" + memberColor + ", memberNickname="
				+ memberNickname + ", contents=" + contents + ", contetsCode="
				+ contetsCode + ", imageName=" + imageName
				+ ", imageWritenDate=" + imageWritenDate + ", emotioconName="
				+ emotioconName + ", emoticonRoute=" + emoticonRoute
				+ ", storyDate=" + storyDate + ", stroyHeart=" + stroyHeart
				+ ", storyModifyDate=" + storyModifyDate + ", storyScope="
				+ storyScope + "]";
	}
	
	
}
