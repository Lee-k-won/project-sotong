package com.example.finalproject;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.finalproject.webserver.WebServerGetImageListThread;
import com.example.finalproject.webserver.WebServerGetIndividualDiarySimpleListThread;
import com.example.finalproject.webserver.WebServerGetSimpleIndividualScheduleListThread;
import com.example.finalproject.webserver.WebServerGetSimpleLetterListThread;
import com.example.finalproject.webserver.WebServerGetStoryThread;

@SuppressLint({ "NewApi", "CommitTransaction" })
public class MainMenuActivity extends ActionBarActivity {

	public ImageButton postBoxBtn;
	public ImageButton handsBtn;
	
	//private Intent userIntent;
	private ArrayList<String[]> list = null;
	private ArrayList<String[]> neighborList = null;
	private String []userInfo = null;
	//public AlbumDialog albumDialog;	
	
	public FragmentManager fm;
	//public FragmentTransaction fragmentTransaction;
	public Fragment fr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main_menu);
		
		fm = getFragmentManager();
		//fragmentTransaction = fm.beginTransaction();
		fr=null;
		
		Intent userIntent = getIntent();
		
		Bundle bundle = userIntent.getBundleExtra("userInitialInfo");
		userInfo = bundle.getStringArray("loginUserInfo");
		// 0 --> memberCode
		// 1 --> homeCode
		// 2 --> memberName
		// 3 --> memberPhoneNum
		// 4 --> memberEmail
		// 5 --> id
		// 6 --> password
		// 7 --> img
		// 8 --> nickName
		// 9 --> color
		// 10 --> birthDay
		// 11 --> role
		
		int familyMemberCnt = userIntent.getIntExtra("familyMemberCnt", 0);
		Log.v("myTag", "MemberCnt는 몇인가 "+familyMemberCnt);
		list = new ArrayList<String[]>();
		 
		for(int cnt=0; cnt<familyMemberCnt; cnt++){
			list.add(bundle.getStringArray("familySimpleInfo"+(cnt+1)));
		}
		
		int neighborCnt = userIntent.getIntExtra("neighborCnt", 0);
		neighborList = new ArrayList<String[]>();
		for(int cnt=0; cnt<neighborCnt; cnt++){
			neighborList.add(bundle.getStringArray("neighborHomeInfo"+(cnt+1)));
		}
		/*
		for(int cnt=0; cnt<list.size(); cnt++){
			Log.v("myTag", "for문에 들어오는가 + "+cnt);
			
			Log.v("myTag", list.get(cnt)[0]);   // 가족의 memberCode
			Log.v("myTag", list.get(cnt)[1]);   // 가족의 NickName
			Log.v("myTag", list.get(cnt)[2]);   // 가족의 Birth
			Log.v("myTag", list.get(cnt)[3]);   // 가족의 Color
			Log.v("myTag", list.get(cnt)[4]);   // 가족의 Photo
		}
		*/
		
		/*
			
			Intent loginIntent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putStringArray("loginUserInfo", list.get(0));
			
			for(int cnt=1; cnt<list.size(); cnt++){
				bundle.putStringArray("familySimpleInfo"+cnt, list.get(cnt));
			}
			loginIntent.putExtra("userInitialInfo", bundle);
			
			loginIntent.setClass(context, MainMenuActivity.class);
			context.startActivity(loginIntent);
		 */
			
		
		postBoxBtn = (ImageButton)findViewById(R.id.postboxBtn);
		handsBtn = (ImageButton)findViewById(R.id.handsBtn);
		
		postBoxBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				WebServerGetSimpleLetterListThread thread = new WebServerGetSimpleLetterListThread(MainMenuActivity.this, userInfo[0], userInfo[1], list);
				thread.start();
				//Intent intent = new Intent(getApplicationContext(),PostBoxActivity.class);
				//startActivity(intent);
			}
		});
		
		
		
		//View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		//context = rootView.getContext();
		
		
		//final ArrayList<Integer> images = new ArrayList<Integer>();
		//for(int cnt=1; cnt<5; cnt++){
		//	images.add(getResources().getIdentifier("cat"+cnt, "drawable", getApplicationContext().getPackageName()));
		//}
		
		//albumDialog = new AlbumDialog(getApplicationContext(),images);
		
	}
	
	//public void imageClick(View v){
	//	albumDialog.show();
	//}

	@SuppressLint("NewApi")
	public void selectFrag(View view){
		//FragmentManager fm = getFragmentManager();
		//FragmentTransaction fragmentTransaction = fm.beginTransaction();
		//Fragment fr=null;
		
		if(view == findViewById(R.id.familyStoryBtn)){
			WebServerGetStoryThread storyThread = new WebServerGetStoryThread(
					getApplicationContext(), userInfo[1], userInfo, fm, fr, MainMenuActivity.this);
			storyThread.start();
			//fr = new FragmentFamily();
			//FragmentTransaction ft = fm.beginTransaction();
			//ft.replace(R.id.fragment_place, fr);
			//ft.commit();
			
			
		}else if(view == findViewById(R.id.diaryBtn)){
			//fr = new FragmentDiary(userInfo);
			WebServerGetIndividualDiarySimpleListThread diaryThread = 
					new WebServerGetIndividualDiarySimpleListThread(getApplicationContext(), userInfo, fm, fr); 
			diaryThread.start();
			//Context context, String []userInfo, FragmentManager fm, FragmentTransaction fragmentTransaction, Fragment fr
		}else if(view == findViewById(R.id.albumBtn)){
			//fr = new FragmentAlbum();
			WebServerGetImageListThread imageThread=new WebServerGetImageListThread(getApplicationContext(), userInfo[1], fm, fr);//프래그먼트 제어를 위해 생성자에 파라미터 추가.
	         imageThread.start();
			
		}else if(view == findViewById(R.id.scheduleBtn)){
			//fr = new FragmentSchedule();
			WebServerGetSimpleIndividualScheduleListThread scheduleThread= new WebServerGetSimpleIndividualScheduleListThread(getApplicationContext(),userInfo, fm, fr);
	        scheduleThread.start();
			 
		}else if(view == findViewById(R.id.etcBtn)){
			fr = new FragmentETC(userInfo, list);
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.fragment_place, fr);
			ft.commit();
		}else{
			fr = new FragmentHome(list, neighborList ,userInfo);
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.fragment_place, fr);
			ft.commit();
			
		}
		
		//FragmentManager fm = getFragmentManager();
		//FragmentTransaction fragmentTransaction = fm.beginTransaction();
		
		//fragmentTransaction.replace(R.id.fragment_place, fr);
		//fragmentTransaction.commit();
		
	
		 
	}
	
	
	
}
