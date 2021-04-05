package com.example.finalproject;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeVersionAdapter extends BaseAdapter implements TitleProvider{
	private LayoutInflater mInflater;
	private static final String[] names = {"홈보기","이웃홈보기"};
	
	public HomeVersionAdapter(Context context) {
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
	public String getTitle(int position) {
		return names[position];
	}
	public View getView(int position, View convertView, ViewGroup parent) {	
		if(convertView == null){
			if(position == 0){
				convertView = mInflater.inflate(R.layout.family_home_show, null);
			}else if(position == 1){
				convertView = mInflater.inflate(R.layout.neighbor_home_show, null);
			}
			
		}
		
		return convertView;
	}
	
	
}
