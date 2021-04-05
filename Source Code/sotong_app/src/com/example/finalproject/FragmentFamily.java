package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalproject.model.MyFamilyStory;
import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

@SuppressLint("NewApi")
public class FragmentFamily extends Fragment{
	
	private ViewFlow viewFlow;
	public Context context;

	public ImageView myFamilyStoryMyPicture;
	public TextView myFamilyStoryMyNickName;
	public TextView myFamilyStoryMyWrittenDate;
	public ImageButton myFamilyStoryWriteButton;
	
	
	public ListView myFamilyStoryListView;
	public ImageButton myFamilyStoryWriteBtn;

	
	public ArrayList<MyFamilyStory> myFamilyList;
	public ArrayList<MyFamilyStory> neighborList;
	public String []userInfo;
	
	public MyFamilyStoryListAdapter myFamilyStoryListAdapter;
	
	/*
	public ImageButton myFamilyStoryModifyBtn; 
	public ImageButton myFamilyStoryDeleteBtn;
	*/ 
	/////////////////////////////////////////
	//이웃쪽
	
	public ListView neighborStoryListView;
	public NeighborStoryListAdapter neighborStoryListAdapter;
	
	////////////////////////////////////////
	
	public ActionBarActivity activity;
	
	public FragmentFamily(String []userInfo, ArrayList<MyFamilyStory> myFamilyList, ArrayList<MyFamilyStory> neighborList, ActionBarActivity activity) {
		this.userInfo = userInfo;
		this.myFamilyList = myFamilyList;
		this.neighborList = neighborList;
		this.activity = activity;
	}
	// 뭔가 크기의 문제가 있는 것 같기도 하고 
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_family, container, false);
		context = rootView.getContext();

		viewFlow = (ViewFlow)rootView.findViewById(R.id.viewflow);
		AndroidVersionAdapter adapter = new AndroidVersionAdapter(context);
		viewFlow.setAdapter(adapter, 3);
		TitleFlowIndicator indicator = (TitleFlowIndicator) rootView.findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		
		
		myFamilyStoryMyPicture = (ImageView)rootView.findViewById(R.id.myFamilyStoryMyPicture);
		myFamilyStoryMyNickName = (TextView)rootView.findViewById(R.id.myFamilyStoryMyNickName);
		myFamilyStoryMyWrittenDate = (TextView)rootView.findViewById(R.id.myFamilyStoryMyWrittenDate);
		myFamilyStoryWriteButton = (ImageButton)rootView.findViewById(R.id.myFamilyStoryWriteButton);
		
		new SimpleProfileDownloadImageTask(myFamilyStoryMyPicture).execute(URLAddress.myUrl+"/final_so_tong/"+userInfo[7]);
		myFamilyStoryMyNickName.setText(userInfo[8]);
		myFamilyStoryMyWrittenDate.setText(format(new Date()));
		myFamilyStoryWriteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Intent intent = new Intent(context.getApplicationContext(),StoryWriteActivity.class);
				//startActivity(intent);
			}
		});
		
		myFamilyStoryListView = (ListView)rootView.findViewById(R.id.myFamilyStoryListView);
		myFamilyStoryListAdapter = new MyFamilyStoryListAdapter(context.getApplicationContext(), myFamilyList, userInfo, activity);
		myFamilyStoryListView.setAdapter(myFamilyStoryListAdapter);
		
		
		
		myFamilyStoryWriteButton = (ImageButton)rootView.findViewById(R.id.myFamilyStoryWriteButton);
		myFamilyStoryWriteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context.getApplicationContext(),StoryWriteActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArray("userInfo", userInfo);
				intent.putExtra("bundle", bundle);
				
				context.startActivity(intent);
			}
		});
		/*
		myFamilyStoryModifyBtn = (ImageButton)rootView.findViewById(R.id.myFamilyStoryModifyBtn); 
		myFamilyStoryDeleteBtn = (ImageButton)rootView.findViewById(R.id.myFamilyStoryDeleteBtn);
		
		myFamilyStoryModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		*/
		
		
		/////////////////////////////////////////////////
		//이웃쪽
		neighborStoryListView = (ListView)rootView.findViewById(R.id.neighborStoryListView);
		neighborStoryListAdapter = new NeighborStoryListAdapter(context.getApplicationContext(), neighborList);
		neighborStoryListView.setAdapter(neighborStoryListAdapter);
		
		
		
		return rootView;
		
	}
	private String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd hh:mm");
	    String date = fmt.format(d);
	    return date;
	}

}
