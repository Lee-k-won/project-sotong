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

public class WebServerStoryWriteThread extends Thread{
	//private static final String STORY_WRITE_URL = "http://192.168.0.20:8089/final_so_tong/story-write.do";//
	
	private static final String STORY_WRITE_URL = URLAddress.myUrl+"/final_so_tong/story-write.do";//
	
	//private static final String STORY_WRITE_URL = "http://192.168.0.110:8089/final_so_tong/story-write.do";//
	//private static final String STORY_WRITE_URL = "http://192.168.0.110:8089/final_so_tong/story-write.do";//
	
	private Context context;
	private String homeCode;
	private String memberCode;
	private String contents;
	private String writtenDate;
	private String imageName;
	private String emoticonCode;
	private String publicScope;

	private Handler handler;
	public ActionBarActivity activity;
	
	public WebServerStoryWriteThread(Context context, String homeCode,
			String memberCode, String contents, String writtenDate,
			String imageName, String emoticonCode, String publicScope,
			ActionBarActivity activity) {
		super();
		this.context = context;
		this.homeCode = homeCode;
		this.memberCode = memberCode;
		this.contents = contents;
		this.writtenDate = writtenDate;
		this.imageName = imageName;
		this.emoticonCode = emoticonCode;
		this.publicScope = publicScope;
		this.activity = activity;
		this.handler = new Handler();
	}
	
	public void run() {
		String returnLine = request();
		if(returnLine != null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "이야기작성하기 성공", Toast.LENGTH_SHORT).show();
					activity.finish();
				}
			});
		}else if(returnLine == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "이야기작성하기 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
	
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(STORY_WRITE_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("homeCode", homeCode));
	        fields.add(new BasicNameValuePair("memberCode", memberCode));
	        fields.add(new BasicNameValuePair("family-board-content-write", contents));
	        fields.add(new BasicNameValuePair("writtenDate", writtenDate));
	        fields.add(new BasicNameValuePair("imageName", imageName));
	        fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
	        fields.add(new BasicNameValuePair("public-scope", publicScope));
	       
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
