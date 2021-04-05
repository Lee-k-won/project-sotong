package dao.diary;

import java.io.Serializable;
import java.util.Date;

public class DiaryVO implements Serializable{

	private static final long serialVersionUID = 62937784930136745L;
	private String diaryCode;
	private String memberCode;
	private String sotongContentsCode;
	private String diaryTitle;
	private String diaryDate;
	
	public DiaryVO() {
		// TODO Auto-generated constructor stub
	}

	public DiaryVO(String diaryCode, String memberCode,
			String sotongContentsCode, String diaryTitle, String diaryDate) {
		super();
		this.diaryCode = diaryCode;
		this.memberCode = memberCode;
		this.sotongContentsCode = sotongContentsCode;
		this.diaryTitle = diaryTitle;
		this.diaryDate = diaryDate;
	}

	public String getDiaryCode() {
		return diaryCode;
	}

	public void setDiaryCode(String diaryCode) {
		this.diaryCode = diaryCode;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getSotongContentsCode() {
		return sotongContentsCode;
	}

	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DiaryVO [diaryCode=" + diaryCode + ", memberCode=" + memberCode
				+ ", sotongContentsCode=" + sotongContentsCode
				+ ", diaryTitle=" + diaryTitle + ", diaryDate=" + diaryDate
				+ "]";
	}
}
