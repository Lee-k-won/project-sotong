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

import com.example.finalproject.model.URLAddress;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class WebServerStoryDeleteThread extends Thread{
	
	private static final String STORY_DELETE_URL = URLAddress.myUrl+"/final_so_tong/story-delete.do";//
	
	//private static final String STORY_DELETE_URL = "http://192.168.0.20:8089/final_so_tong/story-delete.do";//
	//private static final String STORY_DELETE_URL = "http://192.168.0.110:8089/final_so_tong/story-delete.do";//
	
	private Context context;
	private String storyCode;
	private String sotongContentsCode;
	
	private Handler handler;
	
	public WebServerStoryDeleteThread(Context context, String storyCode,
			String sotongContentsCode) {
		super();
		this.context = context;
		this.storyCode = storyCode;
		this.sotongContentsCode = sotongContentsCode;
		this.handler = new Handler();
	}
	
	public void run() {
		String returnLine = request();
		
		if(returnLine != null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "이야기삭제 성공", Toast.LENGTH_SHORT).show();
				} 
			});
		}else if(returnLine == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "이야기삭제 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
	
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(STORY_DELETE_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("story-code", storyCode));
	        fields.add(new BasicNameValuePair("sotong-contents-code", sotongContentsCode));
	      
	        //httpPost.setEntity(new UrlEncodedFormEntity(fields));
	        httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
	        
	        HttpResponse response = client.execute(httpPost);
	        InputStream inStream = response.getEntity().getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	        
	        line = reader.readLine();
	        
	        if(line.startsWith("200")){
	        	StringTokenizer st = new StringTokenizer(line,"|");
	        	String code = st.nextToken();
	        	returnLine = st.nextToken();
	        
	        }else if(line.startsWith("500")){
	        	returnLine = null;
	        }
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return returnLine; 
		
	}
	
	
	
}
