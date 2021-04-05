package com.example.finalproject.calendar;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
//MonthItem을 한 달 분량만큼 모아서 찍어주는 클래스.
public class CalendarAdapter extends BaseAdapter{

   public static final String TAG = "CalandarMonthAdapter";
   
   Context mContext;
   
   public static int oddColor = Color.rgb(225, 225, 225); //색상 지정
   public static int headColor = Color.rgb(12, 32, 158); //
   
   private int selectedPosition = -1; //선택된 날짜의 위치.
   
   private MonthItem[] items;//한 달의 날짜들을 저장하는 배열
   
   private int countColumn = 7;//일주일의 날 수.
   
   int mStartDay; //주의 시작일
   int curYear; //현재 연도
   int curMonth;//현재 월
   
   int firstDay;
   int lastDay;
   int startDay;
   
   Calendar mCalendar;
   boolean recreateItems = false;
   
   //추가
   private ArrayList<DayData> scheduled;
   private String[] matchingResult;
   
   public CalendarAdapter(Context context, String[] matchingResult){
      super();
      this.mContext = context;
      this.matchingResult = matchingResult;
      init();
   }
   
   public CalendarAdapter(Context context,AttributeSet attr){
      super();
      mContext = context;
      init();
   }
   
   public void setMatchingResult(String []matchingResult){
	   this.matchingResult = matchingResult;
   }
   
   private void init(){
      items = new MonthItem[7 * 6]; //최대 6주까지 표현할 수 있도록 배열 생성
      
      mCalendar = Calendar.getInstance();//실제 현재 달력.
      scheduled=new ArrayList<DayData>();
      recalculate();
      resetDayNumbers();
   }
   
   public void recalculate(){
      
      
      mCalendar.set(Calendar.DAY_OF_MONTH, 1);
      
      //현재 날짜의 요일을 받음
      int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
      firstDay = getFirstDay(dayOfWeek); //요일을 통해 첫날을 구함.
      Log.d(TAG,"firstDay : " + firstDay);
      
      mStartDay = mCalendar.getFirstDayOfWeek(); //일주일에서의 첫 요일을 구함(월요일 혹은 일요일.)
      curYear = mCalendar.get(Calendar.YEAR); //현재 연도를 구함.
      curMonth = mCalendar.get(Calendar.MONTH);//현재 월을 구함.
      lastDay = getMonthLastDay(curYear, curMonth);//이번 달의 마지막 날을 구함.
      
      Log.d(TAG, "curYear : " + curYear +", curMonth : " + curMonth + ", lastDay : "+ lastDay);
      
      int diff = mStartDay - Calendar.SUNDAY -1;
      startDay = getFirstDayOfWeek(); // 
      Log.d(TAG,"mStartDay :" + mStartDay + ", startDay : " + startDay);
      
   }
   
   private int getFirstDay(int dayOfWeek){
      int result = 0;
      if(dayOfWeek == Calendar.SUNDAY){
         result = 0;
      }else if (dayOfWeek == Calendar.MONDAY){
         result = 1;
      }else if (dayOfWeek == Calendar.TUESDAY){
         result = 2;
      }else if (dayOfWeek == Calendar.WEDNESDAY){
         result = 3;
      }else if (dayOfWeek == Calendar.THURSDAY){
         result = 4;
      }else if (dayOfWeek == Calendar.FRIDAY){
         result = 5;
      }else if (dayOfWeek == Calendar.SATURDAY){
         result = 6;
      }
      
      return result;
   }
   
   private int getFirstDayOfWeek(){
      int startDay = Calendar.getInstance().getFirstDayOfWeek();
      if(startDay == Calendar.SATURDAY){
         return Time.SATURDAY;
      }else if(startDay == Calendar.MONDAY){
         return Time.MONDAY;
      }else{
         return Time.SUNDAY;
      }
   }
   
   private int getMonthLastDay(int curYear, int curMonth){//현재 연도와 월을 입력해 입력한 월의 일 수를 구할 수 있다. 윤년여부도 체크
      switch(curMonth){ 
      case 0:
      case 2:
      case 4:
      case 6:
      case 7:
      case 9:
      case 11:
         return(31);
      case 3:
      case 5:
      case 8:
      case 10:
         return(30);
         
         default:
            if(((curYear%4==0)&&(curYear%100!=0)) ||(curYear%400==0)){
               return (29);
            }else{
               return (28);
            }
         
      }
   }
   
   
   public void resetDayNumbers(){
      for(int i =0; i<42; i++){
         int dayNumber = (i+1) - firstDay;
         if(dayNumber <1 || dayNumber > lastDay){
            dayNumber = 0;
         }
         
         items[i] = new MonthItem(dayNumber);
      }
   }
   
   @Override
   public int getCount() {
      // TODO Auto-generated method stub
      return 7*6;
   }
   
   

   @Override
   public Object getItem(int arg0) { 
      // TODO Auto-generated method stub
      return items[arg0]; 
   }

   @Override
   public long getItemId(int arg0) {
      // TODO Auto-generated method stub
      return arg0;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {//달력 뷰 제작.
      // TODO Auto-generated method stub
      Log.d(TAG,"getView(" + position + ") called.");
      
      MonthItemView itemView;
      if(convertView ==null){
         itemView = new MonthItemView(mContext);
      
      }else{
         itemView = (MonthItemView) convertView;
      }
      
      GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 120);
      
      int rowIndex = position / countColumn;
      int columnIndex = position % countColumn;
      
      Log.d(TAG, "Index 번호 : " + rowIndex + "," + columnIndex);
      
      /*
      if(position%2 != 0){
    	  itemView.setBackgroundColor(Color.MAGENTA);
      }else{
    	  itemView.setBackgroundColor(Color.YELLOW);
      }
      */
      
      //if( itemView.getText().toString()!=null ){
      if(items[position].getDay() != 0){
      
      		//6
    	  //if(items[position].getDay() != 0){
    		  if(matchingResult[items[position].getDay()-1].equals("false")){
    			  itemView.setBackgroundColor(Color.YELLOW);
    		  }else{
    			  itemView.setBackgroundColor(Color.rgb(212,255,234));
    		  }
    	 // }
    	  
      }else{
    	  itemView.setBackgroundColor(Color.rgb(212,255,234));
      }
      
      itemView.setItem(items[position]);
      itemView.setLayoutParams(params);
      itemView.setPadding(2, 2, 2, 2);// 상하좌우 공간 두기.
      
   itemView.setGravity(Gravity.LEFT);
   // itemView 가 글씨구만
   
   if(columnIndex == 0){
      itemView.setTextColor(Color.RED);
   }
   else if(columnIndex==6)
   {
      itemView.setTextColor(Color.BLUE);
   }
   else{
      itemView.setTextColor(Color.BLACK);
   }
   
   /*
   if(position == getSelectedPosition()){
      itemView.setBackgroundColor(Color.YELLOW);
   }
  
   else{
      itemView.setBackgroundColor(Color.rgb(212,255,234));
   }
   */
      
      return itemView;
   }
   //추가
   public void addScheduled(int Y, int M, int D)
   {
      this.scheduled.add(new DayData(Y,M,D));
   }
   public void addScheduled(DayData data)
   {
      this.scheduled.add(data);
   }
   //
   
   public void setSelectedPosition(int selectedPosition){
      this.selectedPosition = selectedPosition;
   }
   
   public int getSelectedPosition(){
      return selectedPosition;
   }
   
   public void setPreviousMonth(){
      mCalendar.add(Calendar.MONTH, -1);
      recalculate();
      resetDayNumbers();
      selectedPosition = -1;
      
      
   }
   
   public void setNextMonth(){
      mCalendar.add(Calendar.MONTH, 1);
      recalculate();
      
      resetDayNumbers();
      selectedPosition = -1;
   }
   
   public int getCurYear(){
      return curYear;
   }
   public int getCurMonth(){
      return curMonth;
   }
   


}