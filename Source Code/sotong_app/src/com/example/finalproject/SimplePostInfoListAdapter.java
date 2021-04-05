package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.finalproject.model.SimplePostInfo;

public class SimplePostInfoListAdapter extends BaseAdapter{
	public Context context;
	public ArrayList<SimplePostInfo> data;
	public LayoutInflater layoutInflater;
	
	//public TextView titleTextView;
	//public TextView writerTextView;
	//public TextView dateTextView;
	
	public SimplePostInfoListAdapter() {
		
	}

	public SimplePostInfoListAdapter(Context context, ArrayList<SimplePostInfo> data) {
		super();
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(this.context);
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
		View itemLayout = layoutInflater.inflate(R.layout.simple_post, null);
		
		TextView titleTextView = (TextView)itemLayout.findViewById(R.id.simplePostTitle);
		TextView writerTextView = (TextView)itemLayout.findViewById(R.id.simplePostWriter);
		TextView dateTextView = (TextView)itemLayout.findViewById(R.id.simplePostDate);
		
		titleTextView.setText(data.get(position).getPostTitle());
		writerTextView.setText(data.get(position).getPostWriter());
		dateTextView.setText(data.get(position).getPostDateToString());
		
		return itemLayout;
	}
	
	
	
	
	
	
	
}
