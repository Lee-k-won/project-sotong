package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimpleNeighborInfoListAdapter extends BaseAdapter {
	public Context context;
	public ArrayList<String[]> data;
	public LayoutInflater layoutInflater;
	
	public SimpleNeighborInfoListAdapter() {
		// TODO Auto-generated constructor stub
	}
	public SimpleNeighborInfoListAdapter(Context context, ArrayList<String[]> data){
		super();
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(this.context);
	}
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
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemLayout = layoutInflater.inflate(R.layout.simple_neighbor_home, null);
		
		TextView simpleNeighborHomeName = (TextView)itemLayout.findViewById(R.id.simpleNeighborHomeName);
		TextView simpleNeighborName = (TextView)itemLayout.findViewById(R.id.simpleNeighborName);
		
		simpleNeighborHomeName.setText(data.get(position)[1]);
		simpleNeighborName.setText(data.get(position)[2]);
		
		return itemLayout;
	}
}
