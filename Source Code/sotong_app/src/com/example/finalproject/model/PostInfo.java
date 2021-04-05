package com.example.finalproject.model;

import java.util.Date;

public class PostInfo {
	private String postTitle;
	private String postContent;
	private Date postDate;
	private String postWriter;
	
	public PostInfo() {
		super();
	}
	
	public PostInfo(String postTitle, String postContent, Date postDate, String postWriter) {
		super();
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postDate = postDate;
		this.postWriter = postWriter;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public Date getPostDate() {
		return postDate;
	}

	public String getPostWriter() {
		return postWriter;
	}
	
	public String getPostDateToString(){
		String year = new String(""+(postDate.getYear()-1900));
		return year.substring(1)+"-"+postDate.getMonth()+"-"+postDate.getDate();
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public void setPostWriter(String postWriter) {
		this.postWriter = postWriter;
	}

	@Override
	public String toString() {
		return "PostInfo [postTitle=" + postTitle + ", postContent="
				+ postContent + ", postDate=" + postDate + ", postWriter="
				+ postWriter + "]";
	}
	
	
	
}
