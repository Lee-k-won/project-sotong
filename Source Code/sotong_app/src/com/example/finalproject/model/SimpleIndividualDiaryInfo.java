package com.example.finalproject.model;

import java.util.Date;

public class SimpleIndividualDiaryInfo {
	private String diaryCode;
	private String diaryTitle;
	private String diaryDate;
	
	public SimpleIndividualDiaryInfo() {
		super();
	}

	public SimpleIndividualDiaryInfo(String diaryCode, String diaryTitle,
			String diaryDate) {
		super();
		this.diaryCode = diaryCode;
		this.diaryTitle = diaryTitle;
		this.diaryDate = diaryDate;
	}

	public String getDiaryCode() {
		return diaryCode;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public String getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryCode(String diaryCode) {
		this.diaryCode = diaryCode;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}

	@Override
	public String toString() {
		return "SimpleIndividualDiaryInfo [diaryCode=" + diaryCode
				+ ", diaryTitle=" + diaryTitle + ", diaryDate=" + diaryDate
				+ "]";
	}

	
	
	
}
