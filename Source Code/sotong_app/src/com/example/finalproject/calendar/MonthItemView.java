package com.example.finalproject.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;
//MonthItem을 시각적으로 볼 수 있도록 값을 지정하는 클래스.
public class MonthItemView extends TextView{
	
	private MonthItem item;
	
	public MonthItemView(Context context){
		super(context);
		
		init();
	}
	
	public MonthItemView(Context context, AttributeSet attrs){
		super(context,attrs);
		init();
	}
	
	private void init(){//배경색설정
		setBackgroundColor(Color.WHITE);
	}
	
	public MonthItem getItem(){
		return item;
	}
	
	public void setItem(MonthItem item){
		this.item = item;
		
		int day = item.getDay();
		if(day !=0){//입력한 날짜의 유효값이 0이 아니면
			setText(String.valueOf(day));
			//입력한 날짜의 값을 문자열로 바꾸어 텍스트로 집어넣는다.
		}else{
			setText("");
		}
	}
}
