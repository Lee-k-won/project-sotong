package com.example.finalproject.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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
import android.os.Handler;
import android.util.Log;

import com.example.finalproject.calendar.CalendarAdapter;
import com.example.finalproject.model.URLAddress;

public class WebServerGetScheduleMatchingThread extends Thread{
	private static final String GET_MATCHING_URL = URLAddress.myUrl+"/final_so_tong/matching.do";
	private Context context;
	private CalendarAdapter adapter;
	private Handler handler;

	private String []userInfo;
	private byte prevOrNext;
	public WebServerGetScheduleMatchingThread(Context context, CalendarAdapter adapter, String[] userInfo, byte prevOrNext) {
		super();
		this.context = context;
		this.adapter = adapter;
		this.userInfo = userInfo;
		this.handler = new Handler();
		this.prevOrNext = prevOrNext;
	}
	
	public void run() {
		if(prevOrNext == 1){ // 이전달로 이동시
			adapter.setPreviousMonth();
		}else if(prevOrNext == 2){ // 다음달로 이동시
			adapter.setNextMonth();
		}
		
		String resultString[] = requestMatchingResult();
		
		if(resultString != null){
		
			adapter.setMatchingResult(resultString);
			handler.post(new Runnable() {
				public void run() {
					adapter.notifyDataSetChanged();
				}
			});
		}
		
	}
	private String[] requestMatchingResult(){
  	  String resultString[] = null;
  	  String line = null;
  	  ArrayList<String> list = new ArrayList<String>();
  	  try{
  		  HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(GET_MATCHING_URL);
            List<NameValuePair> fields = new ArrayList<NameValuePair>();
            fields.add(new BasicNameValuePair("serviceRoute", "1000"));
            fields.add(new BasicNameValuePair("homeCode", userInfo[1]));
         
            String year = new String(""+adapter.getCurYear()).substring(2, 4);
            int month=adapter.getCurMonth()+1;
            String strMonth = null;
            if(month <10){
          	  strMonth = "0"+month;
            }else{
          	  strMonth = ""+month;
            }
            
            Log.v("year", year);
            Log.v("month", strMonth);
            
            fields.add(new BasicNameValuePair("year", year));   
            fields.add(new BasicNameValuePair("month", strMonth));
            
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
          			  String code = st.nextToken();
          			  
          			  String result = st.nextToken();  Log.v("calendar", "Check : "+result);
          			  
          			  list.add(result);
          		  }else if(line.startsWith("500")){
          			  resultString = null;
          		  }
          	  }
            }
            
            if(list.size() != 0){
            	resultString = new String[list.size()];
            	for(int cnt=0; cnt<list.size(); cnt++){
            		resultString[cnt] = list.get(cnt);
          	  
            	}
            
            	for(int cnt=0; cnt<resultString.length; cnt++){
            		Log.v("111 result is : ", resultString[cnt]);
            	}
            }
  	  }catch (IOException ioe) {
            ioe.printStackTrace();
        }
  	  
  	  return resultString;
    }
	
	
	
	
	
}
