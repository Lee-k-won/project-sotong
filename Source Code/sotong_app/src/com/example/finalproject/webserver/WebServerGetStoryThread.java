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

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.example.finalproject.FragmentFamily;
import com.example.finalproject.R;
import com.example.finalproject.model.MyFamilyStory;
import com.example.finalproject.model.URLAddress;

public class WebServerGetStoryThread extends Thread{
	//private static final String MY_FAMILY_STORY_URL = "http://192.168.0.20:8089/final_so_tong/story.do";
	//private static final String NEIGHBOR_STORY_URL = "http://192.168.0.20:8089/final_so_tong/neighborStory.do";
	//private static final String MY_FAMILY_STORY_URL = "http://192.168.0.110:8089/final_so_tong/story.do";
	//private static final String NEIGHBOR_STORY_URL = "http://192.168.0.110:8089/final_so_tong/neighborStory.do";
	//private static final String MY_FAMILY_STORY_URL = "http://192.168.0.20:8089/final_so_tong/story.do";
	//private static final String NEIGHBOR_STORY_URL = "http://192.168.0.20:8089/final_so_tong/neighborStory.do";
	
	private static final String MY_FAMILY_STORY_URL = URLAddress.myUrl+"/final_so_tong/story.do";
	private static final String NEIGHBOR_STORY_URL = URLAddress.myUrl+"/final_so_tong/neighborStory.do";
	
	
	private static final int STORY_CNT = 17;
	private Context context;
	private String homeCode;
	private String []userInfo;
	private Handler handler;
	  
	public FragmentManager fm;
	public Fragment fr; 
	
	public ActionBarActivity activity;
	
	public WebServerGetStoryThread(Context context, String homeCode,
			String[] userInfo, FragmentManager fm, Fragment fr, ActionBarActivity activity) {
		super();
		this.context = context;
		this.homeCode = homeCode;
		this.userInfo = userInfo;
		this.fm = fm;
		this.fr = fr;
		this.activity = activity;
		this.handler = new Handler();
	}

	@SuppressLint("NewApi")
	public void run() {
	
		ArrayList<MyFamilyStory> myFamilyList = requestMyFamily();
		ArrayList<MyFamilyStory> neighborList = requestNeighbor();
		
		// Data 없을때 처리 해야함!!
		
		fr = new FragmentFamily(userInfo,myFamilyList,neighborList, activity);
		
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.fragment_place, fr);
		ft.commit();
		
		
	}
	
	private ArrayList<MyFamilyStory> requestMyFamily(){
		ArrayList<MyFamilyStory> list = new ArrayList<MyFamilyStory>();
		String line = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(MY_FAMILY_STORY_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
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
	        			String []storyInfo = new String[STORY_CNT];
	        			
	        			String code = st.nextToken();
	        			
	        			list.add(new MyFamilyStory(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
	        					st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
	        					st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
	        					Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken()));
	        	
	        			
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
	private ArrayList<MyFamilyStory> requestNeighbor(){
		ArrayList<MyFamilyStory> list = new ArrayList<MyFamilyStory>();
		String line = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(NEIGHBOR_STORY_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("homeCode", homeCode));
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
	        			String []storyInfo = new String[STORY_CNT];
	        			
	        			String code = st.nextToken();
	        			
	        			list.add(new MyFamilyStory(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
	        					st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
	        					st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
	        					Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken()));
	        			
	        		
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
