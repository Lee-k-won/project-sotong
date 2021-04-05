package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.model.SimpleFamilyInfo;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

public class SimpleFamilyInfoListAdapter extends BaseAdapter{
	public Context context;
	public ArrayList<SimpleFamilyInfo> data;
	public LayoutInflater layoutInflater;
	
	public SimpleFamilyInfoListAdapter() {
		super();
	}
	public SimpleFamilyInfoListAdapter(Context context, ArrayList<SimpleFamilyInfo> data){
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
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemLayout = layoutInflater.inflate(R.layout.simple_family_info, null);
		
		ImageView simpleFamilyInfoImage = (ImageView)itemLayout.findViewById(R.id.simpleFamilyInfoImage);
		TextView simpleFamilyInfoName = (TextView)itemLayout.findViewById(R.id.simpleFamilyInfoName);
		TextView simpleFamilyInfoBirth = (TextView)itemLayout.findViewById(R.id.simpleFamilyInfoBirth);
		
		new SimpleProfileDownloadImageTask(simpleFamilyInfoImage).execute(data.get(position).getFamilyImage());
		//simpleFamilyInfoImage.setImageResource(R.drawable.brad);
	
		simpleFamilyInfoName.setText(data.get(position).getFamilyName());
		simpleFamilyInfoBirth.setText(data.get(position).getFamilyBirth());
		
		return itemLayout;
	}
	
}
