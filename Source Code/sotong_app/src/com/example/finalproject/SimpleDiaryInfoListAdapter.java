package com.example.finalproject;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.finalproject.model.SimpleIndividualDiaryInfo;

public class SimpleDiaryInfoListAdapter extends BaseAdapter{
	public Context context;
	public ArrayList<SimpleIndividualDiaryInfo> data;
	public LayoutInflater layoutInflater;
	
	public SimpleDiaryInfoListAdapter() {
		super();
	}

	public SimpleDiaryInfoListAdapter(Context context, ArrayList<SimpleIndividualDiaryInfo> data) {
		super();
		
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(this.context);
		Log.v("Here 생성자", "생성자는 들어오니?ㅠㅠㅠㅠ 그리고 : "+data.size());
		
	}
	
	@Override
	public int getCount() {

		return data.size();
	}
	@Override
	public Object getItem(int position) {
		return data.get(position);
	}
	@Override
	public long getItemId(int position) {
	
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.v("Here", "들어오니~~~~~~~~~~~~~~~~~~~~`");
		
		View itemLayout = layoutInflater.inflate(R.layout.simple_indi_diary,null);
		
		//CheckBox checkBox = (CheckBox)itemLayout.findViewById(R.id.simpleIndiDiaryCheckBox);
		TextView titleTextView = (TextView)itemLayout.findViewById(R.id.simpleIndiDiaryTitle);
		TextView dateTextView = (TextView)itemLayout.findViewById(R.id.simpleIndiDiaryDate);
		
		//checkBox.setChecked(false);
		titleTextView.setText(data.get(position).getDiaryTitle());
		dateTextView.setText(data.get(position).getDiaryDate());
	
		return itemLayout;
	}
	
	
	
	
}
