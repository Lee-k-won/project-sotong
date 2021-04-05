package com.example.finalproject;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproject.webserver.WebServerGetWishListThread;

@SuppressLint("NewApi")
public class FragmentETC extends Fragment{
	
	//public Button simpleQuizBtn;
	public Button wishListBtn;
	public Button myProfileModifyBtn;
	public Button logoutBtn;

	public Context context;
	
	private String []loginUserInfo;
	private ArrayList<String[]> list; // 가족정보
	
	public FragmentETC(String []loginUserInfo, ArrayList<String[]> list) {
		this.loginUserInfo = loginUserInfo;
		this.list = list;
		//userInfo = bundle.getStringArray("loginUserInfo");
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
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_etc, container, false);
		context = rootView.getContext();
		
		//simpleQuizBtn = (Button)rootView.findViewById(R.id.simpleQuizBtn);
		wishListBtn = (Button)rootView.findViewById(R.id.wishListBtn);
		myProfileModifyBtn = (Button)rootView.findViewById(R.id.myProfileModifyBtn);
		logoutBtn = (Button)rootView.findViewById(R.id.logoutBtn);
		
		
		myProfileModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			
				Intent intent = new Intent(context.getApplicationContext(),MyProfileModifyActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArray("loginUserInfo", loginUserInfo);
				intent.putExtra("bundle", bundle);
				intent.putExtra("familyList", list);
				context.startActivity(intent);
			}
		}); 
		  
		wishListBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Intent intent = new Intent(context.getApplicationContext(),WishListActivity.class);
				//startActivity(intent);
				WebServerGetWishListThread thread = new WebServerGetWishListThread(context,loginUserInfo[1],loginUserInfo[0]);
	            
	            //System.out.println(loginUserInfo[1]+"/"+loginUserInfo[0]);   
	            thread.start();
				
			}
		});
		
		logoutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((MainMenuActivity)context).finish();
			}
		});
	
		return rootView;
	}
}
