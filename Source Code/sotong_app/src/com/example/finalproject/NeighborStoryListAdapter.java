package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.model.MyFamilyStory;
import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

public class NeighborStoryListAdapter extends BaseAdapter{
	
	public Context context;
	public ArrayList<MyFamilyStory> data;
	public LayoutInflater layoutInflater;
	public NeighborStoryListAdapter(Context context, ArrayList<MyFamilyStory> data) {
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
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemLayout = layoutInflater.inflate(R.layout.neighbor_story_form, null);
		
		//neighborStoryModifyBtn
		//neighborStoryDeleteBtn
		
		ImageView neighborStoryProfilePicture = (ImageView)itemLayout.findViewById(R.id.neighborStoryProfilePicture);
		TextView neighborStoryNickName = (TextView)itemLayout.findViewById(R.id.neighborStoryNickName);
		TextView neighborStoryWrittenDate = (TextView)itemLayout.findViewById(R.id.neighborStoryWrittenDate);
		TextView neighborStoryContentsView = (TextView)itemLayout.findViewById(R.id.neighborStoryContentsView);
		
		new SimpleProfileDownloadImageTask(neighborStoryProfilePicture).execute(URLAddress.myUrl+"/final_so_tong/"+data.get(position).getMemberPhoto());
		neighborStoryNickName.setText(data.get(position).getMemberNickName());
		neighborStoryWrittenDate.setText(data.get(position).getStoryDate());
		neighborStoryContentsView.setText(data.get(position).getContents());
		
		return itemLayout;
	}

}
