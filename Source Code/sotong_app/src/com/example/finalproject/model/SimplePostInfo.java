package com.example.finalproject.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimplePostInfo {
	private String postTitle;
	private String postWriter;
	private Date postDate;
	
	public SimplePostInfo() { 
		super();
	} 

	public SimplePostInfo(String postTitle, String postWriter, Date postDate) {
		super();
		this.postTitle = postTitle;
		this.postWriter = postWriter;
		this.postDate = postDate;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public String getPostWriter() {
		return postWriter;
	}

	public Date getPostDate() {
		return postDate;
	}
	
	public String getPostDateToString(){
		return new SimpleDateFormat("yy-MM-dd").format(postDate);
		//String year = new String(""+(postDate.getYear()-1900));
		//return year.substring(1)+"-"+postDate.getMonth()+"-"+postDate.getDate();
	}
	

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setPostWriter(String postWriter) {
		this.postWriter = postWriter;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "SimplePostInfo [postTitle=" + postTitle + ", postWriter="
				+ postWriter + ", postDate=" + postDate + "]";
	}
	
	
	
	
}
