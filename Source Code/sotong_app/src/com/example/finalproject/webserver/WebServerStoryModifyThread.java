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

public class WebServerStoryModifyThread extends Thread{
	//private static final String STORY_MODIFY_URL = "http://192.168.0.20:8089/final_so_tong/story-update.do";//
	
	private static final String STORY_MODIFY_URL = URLAddress.myUrl+"/final_so_tong/story-update.do";//
	
	//private static final String STORY_MODIFY_URL = "http://192.168.0.110:8089/final_so_tong/story-update.do";//
	private Context context;
	private String storyCode;
	private String storyModifyDate;
	private String storyScope;
	private String sotongContentsCode;
	private String contents;
	private String imageName;
	private String imageWrittenDate;
	private String emoticonCode;
	
	 
	
	private Handler handler;
	public ActionBarActivity activity;
	public WebServerStoryModifyThread(Context context, String storyCode,
			String storyModifyDate, String storyScope,
			String sotongContentsCode, String contents, String imageName,
			String imageWrittenDate, String emoticonCode,
			ActionBarActivity activity) {
		super();
		this.context = context;
		this.storyCode = storyCode;
		this.storyModifyDate = storyModifyDate;
		this.storyScope = storyScope;
		this.sotongContentsCode = sotongContentsCode;
		this.contents = contents;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.emoticonCode = emoticonCode;
		this.activity = activity;
		this.handler = new Handler();
	}
	
	public void run() {
		String returnLine = request();
		if(returnLine != null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "이야기수정하기 성공", Toast.LENGTH_SHORT).show();
					activity.finish();
				}
			});
		}else if(returnLine == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "이야기수정하기 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		
		try{ 
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(STORY_MODIFY_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("story-code", storyCode));
	        fields.add(new BasicNameValuePair("storyModifyDate", storyModifyDate));
	        fields.add(new BasicNameValuePair("public-scope", storyScope));
	        fields.add(new BasicNameValuePair("sotong-contents-code", sotongContentsCode));
	        fields.add(new BasicNameValuePair("modifyContents", contents));
	        fields.add(new BasicNameValuePair("imageName", imageName));
	        fields.add(new BasicNameValuePair("imageWrittenDate", imageWrittenDate));
	        fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
	        
	        //httpPost.setEntity(new UrlEncodedFormEntity(fields));
	        httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
	        
	        HttpResponse response = client.execute(httpPost);
	        InputStream inStream = response.getEntity().getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	        //에러
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
