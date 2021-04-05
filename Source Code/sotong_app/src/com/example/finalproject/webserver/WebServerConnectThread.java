package com.example.finalproject.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.MainMenuActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerConnectThread extends Thread {
	
	//private static final String LOGIN_URL = "http://192.168.0.20:8089/final_so_tong/login.do";
	//private static final String LOGIN_URL = "http://192.168.0.14:8089/final_so_tong/login.do";
	//private static final String LOGIN_URL = "http://192.168.0.20:8089/final_so_tong/login.do";
	//private static final String LOGIN_NEIGHBOR_URL = "http://192.168.0.20:8089/final_so_tong/neighbor.do";
	
	private static final String LOGIN_URL = URLAddress.myUrl+"/final_so_tong/login.do";
	private static final String LOGIN_NEIGHBOR_URL = URLAddress.myUrl+"/final_so_tong/neighbor.do";
	
	private static final int USER_INFO_CNT = 12;
	private static final int FAMILY_SIMPLE_INFO_CNT = 5;
	private static final int NEIGHBOR_HOME_CNT = 3;
	
	private Context context; 
	private String userId;
	private String userPassword;
	
	private Handler handler;
	
	public WebServerConnectThread(Context context, String userId, String userPassword) {
		this.context = context;
		this.userId = userId;
		this.userPassword = userPassword;
		this.handler = new Handler();
	}
	
	public void run() {
		ArrayList<String[]>list = request();
		
		if(list != null){
			
			ArrayList<String[]> listNeighbor = requestNeighbor(list.get(0)[1]);
			
			Intent loginIntent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putStringArray("loginUserInfo", list.get(0));
			
			for(int cnt=1; cnt<list.size(); cnt++){
				bundle.putStringArray("familySimpleInfo"+cnt, list.get(cnt));
			}
			
			if(listNeighbor != null){
				for(int cnt=0; cnt<listNeighbor.size(); cnt++){
					
					bundle.putStringArray("neighborHomeInfo"+(cnt+1), listNeighbor.get(cnt));
				}
			}
			loginIntent.putExtra("userInitialInfo", bundle);
			
			Log.v("myTag", "First Cnt : "+list.size());
			
			loginIntent.putExtra("familyMemberCnt", list.size()-1);
			
			if(listNeighbor != null){
				loginIntent.putExtra("neighborCnt", listNeighbor.size());
			}else{
				loginIntent.putExtra("neighborCnt", 0);
			}
			loginIntent.setClass(context, MainMenuActivity.class);
			context.startActivity(loginIntent);
			
		}else if(list == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
				}
			});
		
		
		}
	}
	
	private ArrayList<String[]> requestNeighbor(String homeCode){
		String line = null;
		ArrayList<String []> list = new ArrayList<String[]>();
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(LOGIN_NEIGHBOR_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute","1000"));
			fields.add(new BasicNameValuePair("homeCode", homeCode));
			
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			
			HttpResponse response = client.execute(httpPost);
			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			while(true){
				line = reader.readLine();
				if(line == null){
					break;
				}else{
					if(line.startsWith("200")){
						StringTokenizer st = new StringTokenizer(line,"|");
						String neighbor[] = new String[NEIGHBOR_HOME_CNT];
						
						String code = st.nextToken(); 
						neighbor[0] = st.nextToken(); Log.v("neighborCode", neighbor[0]);
						neighbor[1] = st.nextToken(); Log.v("neighborCode", neighbor[1]);
						neighbor[2] = st.nextToken(); Log.v("neighborCode", neighbor[2]);
						
						list.add(neighbor); 
						
					}else if(line.startsWith("500")){
						list = null;
					}
				}
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return list;
	}
	
	private ArrayList<String[]> request(){
		String line = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		try{ 
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(LOGIN_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute","1000"));
			fields.add(new BasicNameValuePair("user-id", userId));
			fields.add(new BasicNameValuePair("user-pw", userPassword));
			httpPost.setEntity(new UrlEncodedFormEntity(fields));
			
			HttpResponse response = client.execute(httpPost);
			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			 
			while(true){
				line = reader.readLine();
				if(line == null){
					break;
				}else{
					if(line.startsWith("200")){
						StringTokenizer st = new StringTokenizer(line,"|");
						String []userInfo = new String[USER_INFO_CNT];
						String code = st.nextToken();
						Log.v("myTag", "code is "+code);
						
						userInfo[0] = st.nextToken(); // memberCode
						Log.v("myTag", "memberCode is "+userInfo[0]);
						
						userInfo[1] = st.nextToken(); // familyHomeCode
						Log.v("myTag", "familyHomeCode is "+userInfo[1]);
						
						userInfo[2] = st.nextToken(); // memberName
						Log.v("myTag", "memberName is "+userInfo[2]);
						
						userInfo[3] = st.nextToken(); // memberPhone
						Log.v("myTag", "memberPhone is "+userInfo[3]);
						
						userInfo[4] = st.nextToken(); // memberEmail
						Log.v("myTag", "memberEmail is "+userInfo[4]);
						
						userInfo[5] = st.nextToken(); // memberId
						Log.v("myTag", "memberId id "+userInfo[5]);
						
						userInfo[6] = st.nextToken(); // memberPw
						Log.v("myTag", "memberPw is "+userInfo[6]);
						
						userInfo[7] = st.nextToken(); // memberPhoto
						Log.v("myTag", "memberPhoto is "+userInfo[7]);
						
						userInfo[8] = st.nextToken(); // memberNickName
						Log.v("myTag", "memberNickName is "+userInfo[8]);
						
						userInfo[9] = st.nextToken(); // memberColor
						Log.v("myTag", "memberColor is "+userInfo[9]);
						
						userInfo[10] = st.nextToken(); // memberBirth
						Log.v("myTag", "memberBirth is "+userInfo[10]);
						
						userInfo[11] = st.nextToken(); // memberRole
						Log.v("myTag", "memberRole is "+userInfo[11]);
						list.add(userInfo);	
						
					}else if(line.startsWith("300")){
						StringTokenizer st = new StringTokenizer(line,"|");
						String []familyInfo = new String[FAMILY_SIMPLE_INFO_CNT];
						
						String code = st.nextToken();
						familyInfo[0] = st.nextToken();
						Log.v("myTag", "memberCode "+familyInfo[0]);
						familyInfo[1] = st.nextToken();
						Log.v("myTag", "memberNickName "+familyInfo[1]);
						familyInfo[2] = st.nextToken();
						Log.v("myTag", "memberBirth "+familyInfo[2]);
						familyInfo[3] = st.nextToken();
						Log.v("myTag", "memberColor "+familyInfo[3]);
						familyInfo[4] = st.nextToken();
						Log.v("myTag", "memberPhoto "+familyInfo[4]); //memberPhoto
						
						list.add(familyInfo);
					}else if(line.startsWith("500")){
						list = null;
					}
				}
					
			}
			
			
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return list;
	}
	
	
	
}
