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
import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

public class SimpleNeighborHomeInfoListAdapter extends BaseAdapter{
	public Context context;
	public ArrayList<SimpleFamilyInfo> data;
	public LayoutInflater layoutInflater;
	
	public SimpleNeighborHomeInfoListAdapter() {
		// TODO Auto-generated constructor stub
	}

	public SimpleNeighborHomeInfoListAdapter(Context context,
			ArrayList<SimpleFamilyInfo> data) {
		super();
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(this.context);
		
	}
	public int getCount() {

		return data.size();
	}

	public Object getItem(int position) {

		return data.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemLayout = layoutInflater.inflate(R.layout.simple_neighbor_info, null);
		
		ImageView simpleNeighborInfoImage = (ImageView)itemLayout.findViewById(R.id.simpleNeighborInfoImage);
		TextView simpleNeighborInfoName = (TextView)itemLayout.findViewById(R.id.simpleNeighborInfoName);
		TextView simpleNeighborInfoBirth = (TextView)itemLayout.findViewById(R.id.simpleNeighborInfoBirth);
		
		new SimpleProfileDownloadImageTask(simpleNeighborInfoImage).execute(URLAddress.myUrl+"/final_so_tong/"+data.get(position).getFamilyImage());
		//"http://192.168.0.20:8089/final_so_tong/"
		
		simpleNeighborInfoName.setText(data.get(position).getFamilyName());
		simpleNeighborInfoBirth.setText("생년월일 : "+data.get(position).getFamilyBirth());
		
		return itemLayout;
	}
	
	
	
	
	
}
