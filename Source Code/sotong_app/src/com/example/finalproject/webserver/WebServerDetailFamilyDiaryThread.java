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

import com.example.finalproject.FamilyDetailDiaryActivity;
import com.example.finalproject.model.FamilyDiaryDetail;
import com.example.finalproject.model.URLAddress;

public class WebServerDetailFamilyDiaryThread extends Thread{
	//private static final String FAMILY_DIARY_INFO_URL = "http://192.168.0.20:8089/final_so_tong/familyDiaryInfo.do";
	private static final String FAMILY_DIARY_INFO_URL = URLAddress.myUrl+"/final_so_tong/familyDiaryInfo.do";
	private static final int FAMILY_DIARY_COMMON_CNT = 3;
	private static final int FAMILY_DIARY_CNT = 10;
	private Context context;
	private String familyDiaryCode;
	private String []userInfo;
	private Handler handler;
	
	public WebServerDetailFamilyDiaryThread(Context context, String familyDiaryCode, String []userInfo) {
		this.context = context;
		this.familyDiaryCode = familyDiaryCode;
		this.userInfo = userInfo;
		this.handler = new Handler();
	}
	
	public void run() {
		ArrayList<String[]> list = request();
		
		if(list != null){
			//ArrayList<FamilyDiaryDetail> data = new ArrayList<FamilyDiaryDetail>();
			String []familyDiaryCommonInfo = list.get(0);
	
			Bundle bundle = new Bundle();
			bundle.putStringArray("familyDiaryCommonInfo", familyDiaryCommonInfo);
			bundle.putStringArray("userInfo", userInfo);
			
			for(int cnt=0; cnt<userInfo.length; cnt++){
				Log.v("Test22", userInfo[cnt]);
			}
			
			for(int cnt=1; cnt<list.size(); cnt++){
				bundle.putStringArray("partInfo"+cnt, list.get(cnt));
			}
			
			Intent intent = new Intent(context.getApplicationContext(), FamilyDetailDiaryActivity.class);
			intent.putExtra("familyCnt", list.size()-1);
			intent.putExtra("bundle", bundle);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			
			
		}else if(list == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context.getApplicationContext(), "상세보기실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
	private ArrayList<String[]> request(){
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		String line = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(FAMILY_DIARY_INFO_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("familyDiaryCode", familyDiaryCode));
	        
	        //httpPost.setEntity(new UrlEncodedFormEntity(fields));
	        httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
	        
	        HttpResponse response = client.execute(httpPost);
	        InputStream inStream = response.getEntity().getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	        
	        while(true){
	        	line = reader.readLine();
	        	
	        	//Log.v("Line is!!", line);
	        	
	        	if(line == null){
	        		break;
	        	}else{
	        		if(line.startsWith("200")){
	        			StringTokenizer st = new StringTokenizer(line,"|");
	        			String []familyDiaryCommonInfo = new String[FAMILY_DIARY_COMMON_CNT];
	        			
	        			String code = st.nextToken();
	        			familyDiaryCommonInfo[0] = st.nextToken();  Log.v("commonData", familyDiaryCommonInfo[0]);
	        			familyDiaryCommonInfo[1] = st.nextToken();	Log.v("commonData", familyDiaryCommonInfo[1]);
	        			familyDiaryCommonInfo[2] = st.nextToken();	Log.v("commonData", familyDiaryCommonInfo[2]);
	        			
	        			list.add(familyDiaryCommonInfo);
	        		}else if(line.startsWith("300")){
	        			StringTokenizer st = new StringTokenizer(line,"|");
	        			String []individualInfo = new String[FAMILY_DIARY_CNT];
	        			
	        			String code = st.nextToken();
	        			individualInfo[0] = st.nextToken(); Log.v("commonData", individualInfo[0]);
	        			individualInfo[1] = st.nextToken(); Log.v("commonData", individualInfo[1]);
	        			individualInfo[2] = st.nextToken(); Log.v("commonData", individualInfo[2]);
	        			individualInfo[3] = st.nextToken(); Log.v("commonData", individualInfo[3]);
	        			individualInfo[4] = st.nextToken(); Log.v("commonData", individualInfo[4]);
	        			individualInfo[5] = st.nextToken(); Log.v("commonData", individualInfo[5]);
	        			individualInfo[6] = st.nextToken(); Log.v("commonData", individualInfo[6]);
	        			individualInfo[7] = st.nextToken(); Log.v("commonData", individualInfo[7]);
	        			individualInfo[8] = st.nextToken(); Log.v("commonData", individualInfo[8]);
	        			individualInfo[9] = st.nextToken(); Log.v("commonData", individualInfo[9]);
	        			list.add(individualInfo);
	        		}else if(line.startsWith("500")){
	        			list = null;
	        		}
	        	}
	        }
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		//f d code, f h code , f d writtenDate
		//f d p c, memberNickname, f d p dAte, sotongContentsCode, contents, emoticonName, emoticonRoute, imagename, imageWrittenDate
		
		return list;
		
	}
	
	/*
	 familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		data = new ArrayList<FamilyDiaryDetail>();
		data.add(new FamilyDiaryDetail("장재영", "@drawable/brad", "오늘 즐거운 식사였습니다\n안녕히주무세요", new Date(2015,7,23)));
		data.add(new FamilyDiaryDetail("이경원", "@drawable/brad", "오늘 즐거운 만찬이였습니다\n안녕히주무세요", new Date(2015,7,22)));
		data.add(new FamilyDiaryDetail("김철연", "@drawable/brad", "오늘도 즐거운 하루였습니다\n안녕히주무세요", new Date(2015,7,22)));
		
		adapter = new FamilyDetailVersionAdapter(FamilyDetailDiaryActivity.this, data);
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		familyDetailDiaryListView.setAdapter(adapter);
		
	 */
	
	
	//famiyDiaryCode
	
	
}
