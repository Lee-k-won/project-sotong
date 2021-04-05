package com.example.finalproject.calendar;

//달력을 
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class CalendarView extends GridView{

   private OnDataSelectionListener selectionListener;
   
   CalendarAdapter adapter;
   
   public CalendarView(Context context){
      super(context);
      init();
   }
   
   public CalendarView(Context context,AttributeSet attr){
      super(context, attr);
      init();
   }
   
   private void init(){
      setBackgroundColor(Color.WHITE);
      setVerticalSpacing(5);//행  공간
      setHorizontalSpacing(5);//열 공간
      setStretchMode(GridView.STRETCH_COLUMN_WIDTH);//
      setNumColumns(7);//열 개수 지정
      
      
      setOnItemClickListener(new OnItemClickAdapter()); //해당 그리드뷰에 리스너를 연결
   }
   
   
   
   
   class OnItemClickAdapter implements OnItemClickListener {//아이템(하루날짜) 클릭시의 리스너
      public OnItemClickAdapter(){
         
      }
      
      public void onItemClick(AdapterView parent, View v, int position, long id){
         if(adapter != null){
            adapter.setSelectedPosition(position);//선택된 위치를, 입력받은 값으로 설정한다.
            adapter.notifyDataSetInvalidated();//CalendarView 갱신.
            
         }
         
         if(selectionListener!=null){ //리스너가 널값이 아니면,  데이터 지정.
            selectionListener.onDataSelected(parent, v,position, id);
         }
      }
   }
   
   public void setAdapter(BaseAdapter adapter){
      super.setAdapter(adapter);
      this.adapter = (CalendarAdapter) adapter;
   }
   
   public BaseAdapter getAdapter(){
      return (BaseAdapter)super.getAdapter();
   }
   
   public void setOnDataSelectionListener(OnDataSelectionListener listener){
      this.selectionListener = listener;
   }
   
   public OnDataSelectionListener getOnDataSelectionListener(){
      return selectionListener;
   }
}