package com.example.finalproject.model;

import java.util.Date;

public class IndividualDiaryInfo {
	private String indiDiaryTitle;
	private String indiDiaryContent;
	private Date indiDiaryDate;
	
	public IndividualDiaryInfo() {
		super();
	}

	public IndividualDiaryInfo(String indiDiaryTitle, String indiDiaryContent, Date indiDiaryDate) {
		this.indiDiaryTitle = indiDiaryTitle;
		this.indiDiaryContent = indiDiaryContent;
		this.indiDiaryDate = indiDiaryDate;
	}

	public String getIndiDiaryTitle() {
		return indiDiaryTitle;
	}

	public String getIndiDiaryContent() {
		return indiDiaryContent;
	}

	public Date getIndiDiaryDate() {
		return indiDiaryDate;
	}
	
	public String getIndiDiaryDateToString(){
		String year = new String(""+(indiDiaryDate.getYear()-1900));
		return year.substring(1)+"-"+indiDiaryDate.getMonth()+"-"+indiDiaryDate.getDate();
	}

	public void setIndiDiaryTitle(String indiDiaryTitle) {
		this.indiDiaryTitle = indiDiaryTitle;
	}

	public void setIndiDiaryContent(String indiDiaryContent) {
		this.indiDiaryContent = indiDiaryContent;
	}

	public void setIndiDiaryDate(Date indiDiaryDate) {
		this.indiDiaryDate = indiDiaryDate;
	}

	@Override
	public String toString() {
		return "IndividualDiaryInfo [indiDiaryTitle=" + indiDiaryTitle
				+ ", indiDiaryContent=" + indiDiaryContent + ", indiDiaryDate="
				+ indiDiaryDate + "]";
	}
	
	
	
	
}
