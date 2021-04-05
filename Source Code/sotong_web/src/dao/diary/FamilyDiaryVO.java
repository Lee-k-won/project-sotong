package dao.diary;

import java.io.Serializable;
import java.util.Date;

public class FamilyDiaryVO implements Serializable{

	private static final long serialVersionUID = 2327613871909235235L;
	private String familyDiaryCode;
	private String familyHomeCode;
	private String familyDiaryDate;
	
	public FamilyDiaryVO() {
		// TODO Auto-generated constructor stub
	}

	public FamilyDiaryVO(String familyDiaryCode, String familyHomeCode,
			String familyDiaryDate) {
		super();
		this.familyDiaryCode = familyDiaryCode;
		this.familyHomeCode = familyHomeCode;
		this.familyDiaryDate = familyDiaryDate;
	}

	public String getFamilyDiaryCode() {
		return familyDiaryCode;
	}

	public void setFamilyDiaryCode(String familyDiaryCode) {
		this.familyDiaryCode = familyDiaryCode;
	}

	public String getFamilyHomeCode() {
		return familyHomeCode;
	}

	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
	}

	public String getFamilyDiaryDate() {
		return familyDiaryDate;
	}

	public void setFamilyDiaryDate(String familyDiaryDate) {
		this.familyDiaryDate = familyDiaryDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FamilyDiaryVO [familyDiaryCode=" + familyDiaryCode
				+ ", familyHomeCode=" + familyHomeCode + ", familyDiaryDate="
				+ familyDiaryDate + "]";
	}
}
