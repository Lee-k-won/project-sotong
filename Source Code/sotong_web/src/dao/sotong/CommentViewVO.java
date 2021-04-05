package dao.sotong;

import java.io.Serializable;
import java.util.Date;

public class CommentViewVO implements Serializable{
	
	private static final long serialVersionUID = 1693773369333211419L;
	private String commentCode;
	private String commentContents;
	
	private String memberNickname;
	private String memberPhoto;
	private String memberColor;
	
	private String commentDate;
	private String emoticonName;
	
	private String familyDiaryCode;
	private String storyCode;
	private String emoticonRoute;
	
	public CommentViewVO() {
		// TODO Auto-generated constructor stub
	}

	public CommentViewVO(String commentCode, String commentContents,
			String memberNickname, String memberPhoto, String memberColor,
			String commentDate, String emoticonName, String familyDiaryCode,
			String storyCode, String emoticonRoute) {
		super();
		this.commentCode = commentCode;
		this.commentContents = commentContents;
		this.memberNickname = memberNickname;
		this.memberPhoto = memberPhoto;
		this.memberColor = memberColor;
		this.commentDate = commentDate;
		this.emoticonName = emoticonName;
		this.familyDiaryCode = familyDiaryCode;
		this.storyCode = storyCode;
		this.emoticonRoute = emoticonRoute;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
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

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getEmoticonName() {
		return emoticonName;
	}

	public void setEmoticonName(String emoticonName) {
		this.emoticonName = emoticonName;
	}

	public String getFamilyDiaryCode() {
		return familyDiaryCode;
	}

	public void setFamilyDiaryCode(String familyDiaryCode) {
		this.familyDiaryCode = familyDiaryCode;
	}

	public String getStoryCode() {
		return storyCode;
	}

	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}

	public String getEmoticonRoute() {
		return emoticonRoute;
	}

	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CommentViewVO [commentCode=" + commentCode
				+ ", commentContents=" + commentContents + ", memberNickname="
				+ memberNickname + ", memberPhoto=" + memberPhoto
				+ ", memberColor=" + memberColor + ", commentDate="
				+ commentDate + ", emoticonName=" + emoticonName
				+ ", familyDiaryCode=" + familyDiaryCode + ", storyCode="
				+ storyCode + ", emoticonRoute=" + emoticonRoute + "]";
	}
	
	
}
