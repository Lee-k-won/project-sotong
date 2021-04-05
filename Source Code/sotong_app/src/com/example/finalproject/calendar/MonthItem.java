package com.example.finalproject.calendar;
//한 달의 각 날짜들의 값을 저장하는 클래스
public class MonthItem {
	private int dayValue;
	
	public MonthItem(){
		
	}
	
	public MonthItem(int day){
		dayValue = day;
	}
	
	public int getDay(){
		return dayValue;
	}
	
	public void setDay(int day){
		this.dayValue = day;
	}
}
