package dao.home;

import java.io.Serializable;

public class FamilyHomeVO implements Serializable{

	private static final long serialVersionUID = 2268214318229019595L;
	private String familyHomeCode;
	private String familyHomeName;
	
	public FamilyHomeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FamilyHomeVO(String familyHomeCode, String familyHomeName) {
		super();
		this.familyHomeCode = familyHomeCode;
		this.familyHomeName = familyHomeName;
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

	public String toString() {
		return "FamilyHomeVO [familyHomeCode=" + familyHomeCode
				+ ", familyHomeName=" + familyHomeName + "]";
	}
	
	
}
