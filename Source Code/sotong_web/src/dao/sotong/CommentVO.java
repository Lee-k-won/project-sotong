package dao.sotong;

import java.io.Serializable;
import java.util.Date;

public class CommentVO implements Serializable{

	private static final long serialVersionUID = 3297700490996486057L;
	private String commentCode;
	private String memberCode;
	
	private String commentContets;
	private String commentDate;
	
	private String emoticonCode;
	private String familyDiaryCode;
	private String storyCode;
	
	public CommentVO() {
		// TODO Auto-generated constructor stub
	}

	public CommentVO(String commentCode, String memberCode,
			String commentContets, String commentDate, String emoticonCode,
			String familyDiaryCode, String storyCode) {
		super();
		this.commentCode = commentCode;
		this.memberCode = memberCode;
		this.commentContets = commentContets;
		this.commentDate = commentDate;
		this.emoticonCode = emoticonCode;
		this.familyDiaryCode = familyDiaryCode;
		this.storyCode = storyCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getCommentContets() {
		return commentContets;
	}

	public void setCommentContets(String commentContets) {
		this.commentContets = commentContets;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getEmoticonCode() {
		return emoticonCode;
	}

	public void setEmoticonCode(String emoticonCode) {
		this.emoticonCode = emoticonCode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CommentVO [commentCode=" + commentCode + ", memberCode="
				+ memberCode + ", commentContets=" + commentContets
				+ ", commentDate=" + commentDate + ", emoticonCode="
				+ emoticonCode + ", familyDiaryCode=" + familyDiaryCode
				+ ", storyCode=" + storyCode + "]";
	}	
}
