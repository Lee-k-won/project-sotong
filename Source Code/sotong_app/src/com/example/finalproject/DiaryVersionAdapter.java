package com.example.finalproject;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DiaryVersionAdapter extends BaseAdapter implements TitleProvider{
	private LayoutInflater mInflater;
	private static final String[] versions = {"1.5","1.6","2.1","2.2","2.3","3.0","x.y" };
	private static final String[] names = {"개인일기장","가족일기장"};

	public DiaryVersionAdapter(Context context) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public int getCount() {
		
		return names.length;
	}

	public Object getItem(int position) {
		
		return position;
	}	
	
	public long getItemId(int position) {
		
		return position;
	}
	
	@Override
	public String getTitle(int position) {
	
		return names[position];
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			if(position == 0){
				convertView = mInflater.inflate(R.layout.individual_diary, null);
			}else if(position == 1){
				convertView = mInflater.inflate(R.layout.family_diary, null);
			}
			
		}
		
		return convertView;
	}
	
	
	
}
