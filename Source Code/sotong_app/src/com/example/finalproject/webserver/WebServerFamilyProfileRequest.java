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

import com.example.finalproject.FamilyProfileInfoActivity;
import com.example.finalproject.model.URLAddress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class WebServerFamilyProfileRequest extends Thread{
	//private static final String FAMILY_INFO_URL="http://192.168.0.20:8089/final_so_tong/detailMember.do";
	private static final String FAMILY_INFO_URL=URLAddress.myUrl+"/final_so_tong/detailMember.do";
	
	//private static final String FAMILY_INFO_URL="http://192.168.0.25:8089/final_so_tong/detailMember.do";
	//private static final String FAMILY_INFO_URL="http://192.168.0.14:8089/final_so_tong/detailMember.do";
	private static final int FAMILY_INFO_CNT = 9;
	
	private Context context;
	private String memberCode;
	
	private Handler handler;
	public WebServerFamilyProfileRequest(Context context, String memeberCode) {
		this.context = context;
		this.memberCode = memeberCode; 
		this.handler = new Handler();
	} 
	
	/*
	Intent intent = new Intent(context.getApplicationContext(),FamilyProfileInfoActivity.class);
	
	Bundle bundle = new Bundle();
	bundle.putStringArray("userInfo", userInfo);
	bundle.putString("detailProfileCode", simpleData.get(position).getMemberCode());
	intent.putExtra("bundleData", bundle);
	startActivity(intent);
	*/
	
	public void run() {
		String []familyMemberInfo = request();
		if(familyMemberInfo != null){
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putStringArray("familyMemberInfo", familyMemberInfo);
			intent.putExtra("familyInfo", bundle);
			
			intent.setClass(context, FamilyProfileInfoActivity.class);
			context.startActivity(intent);
			
			
		}else if(familyMemberInfo == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인 프로필을 읽어올 수 없습니다", Toast.LENGTH_SHORT).show();
				}
			});
			
		}
	}
	
	private String[] request(){
		String line = null;
		String []familyInfo = new String[FAMILY_INFO_CNT];
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(FAMILY_INFO_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute", "1000"));
			fields.add(new BasicNameValuePair("memberInfo", memberCode));
			
			
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			HttpResponse response = client.execute(httpPost);
			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			while(true){
				line = reader.readLine();
				if(line== null){
					break;
				}else{
					if(line.startsWith("200")){
						StringTokenizer st = new StringTokenizer(line,"|");
						//String []familyInfo = new String[FAMILY_INFO_CNT];
						String code = st.nextToken(); Log.v("FamilyTag", "Code : "+code);
						familyInfo[0] = st.nextToken(); Log.v("FamilyTag", "Member Code : "+familyInfo[0]);
						familyInfo[1] = st.nextToken(); Log.v("FamilyTag", "Member Name : "+familyInfo[1]);
						familyInfo[2] = st.nextToken(); Log.v("FamilyTag", "Member Phone : "+familyInfo[2]);
						familyInfo[3] = st.nextToken(); Log.v("FamilyTag", "Member Email : "+familyInfo[3]);
						familyInfo[4] = st.nextToken(); Log.v("FamilyTag", "Member Photo : "+familyInfo[4]);
						familyInfo[5] = st.nextToken(); Log.v("FamilyTag", "Member NickName : "+familyInfo[5]);
						familyInfo[6] = st.nextToken(); Log.v("FamilyTag", "Member Color : "+familyInfo[6]);
						familyInfo[7] = st.nextToken(); Log.v("FamilyTag", "Member Birth : "+familyInfo[7]);
						familyInfo[8] = st.nextToken(); Log.v("FamilyTag", "Member Role : "+familyInfo[8]);
						 
						
					}else if(line.startsWith("500")){
						familyInfo = null; 
					}
						
				}
			}
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return familyInfo;
		
	}
	
}
