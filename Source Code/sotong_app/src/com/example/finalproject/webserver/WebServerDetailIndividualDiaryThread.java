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

import com.example.finalproject.IndividualDetailDiaryActivity;
import com.example.finalproject.model.URLAddress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class WebServerDetailIndividualDiaryThread extends Thread{
	//private static final String INDIVIDUAL_DIARY_INFO_URL = "http://192.168.0.14:8089/final_so_tong/diaryInfo.do";//
	//private static final String INDIVIDUAL_DIARY_INFO_URL = "http://192.168.0.20:8089/final_so_tong/diaryInfo.do";//
	private static final String INDIVIDUAL_DIARY_INFO_URL = URLAddress.myUrl+"/final_so_tong/diaryInfo.do";//
	private static final int INDIVIDUAL_INFO_CNT=10;
	private Context context;
	private String diaryCode;
	private String []userInfo;
	private Handler handler;
	
	public WebServerDetailIndividualDiaryThread(Context context, String diaryCode, String []userInfo) {
		this.context = context;
		this.diaryCode = diaryCode;
		this.userInfo = userInfo;
		this.handler = new Handler();

	}
	
	
	public void run() {
		String []diaryInfo = request();
		
		if(diaryInfo != null){
			final Intent intent = new Intent(context.getApplicationContext(),IndividualDetailDiaryActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Bundle bundle = new Bundle();
			bundle.putStringArray("diaryInfo", diaryInfo);
			bundle.putStringArray("userInfo", userInfo);
			intent.putExtra("bundle", bundle);
			handler.post(new Runnable() {
				public void run() {
					context.startActivity(intent);
				}
			});
			
			
		}else if(diaryInfo == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "상세정보 읽어오기 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
	
	}
	private String[] request(){
		String line = null;
		String []diaryInfo = new String[INDIVIDUAL_INFO_CNT];
		try{
			HttpClient client = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(INDIVIDUAL_DIARY_INFO_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("diaryCode", diaryCode));
	        
	        //httpPost.setEntity(new UrlEncodedFormEntity(fields));
	        httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
	        
	        
	        HttpResponse response = client.execute(httpPost);
	        InputStream inStream = response.getEntity().getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	        
	        line = reader.readLine();
	        
	        if(line.startsWith("200")){
	        	StringTokenizer st = new StringTokenizer(line,"|");
   			 //	String []diaryInfo = new String[INDIVIDUAL_INFO_CNT];
   			 	String code = st.nextToken();
   			 	
   			 	diaryInfo[0] = st.nextToken(); Log.v("diaryCode", "diary Code is "+diaryInfo[0]);
   			 	diaryInfo[1] = st.nextToken(); Log.v("diaryCode", "diary NickName is "+diaryInfo[1]);
   			 	diaryInfo[2] = st.nextToken(); Log.v("diaryCode", "diary Title is "+diaryInfo[2]);
   			 	diaryInfo[3] = st.nextToken(); Log.v("diaryCode", "diary Date is "+diaryInfo[3]);
   			 	diaryInfo[4] = st.nextToken(); Log.v("diaryCode", "diary S_C Code is "+diaryInfo[4]);
   			 	diaryInfo[5] = st.nextToken(); Log.v("diaryCode", "diary Contents is "+diaryInfo[5]);
   			 	diaryInfo[6] = st.nextToken(); Log.v("diaryCode", "diary ImageName is "+diaryInfo[6]);
   			 	diaryInfo[7] = st.nextToken(); Log.v("diaryCode", "diary ImageDate is "+diaryInfo[7]);
   			 	diaryInfo[8] = st.nextToken(); Log.v("diaryCode", "diary EmoticonName is "+diaryInfo[8]);
   			 	diaryInfo[9] = st.nextToken(); Log.v("diaryCode", "diary EmoticonRoute is "+diaryInfo[9]);
   			
	        }else if(line.startsWith("500")){
	        	diaryInfo = null;
	        }
	        
	        
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return diaryInfo;
	}
}
