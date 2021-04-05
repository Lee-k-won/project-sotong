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
import android.util.Log;
import android.widget.Toast;

public class WebServerIndividualDiaryDeleteThread extends Thread{
	//private static final String DELETE_INFO_URL="http://192.168.0.20:8089/final_so_tong/diary_delete.do";
	//private static final String DELETE_INFO_URL="http://192.168.0.14:8089/final_so_tong/diary_delete.do";
	
	private static final String DELETE_INFO_URL = URLAddress.myUrl+"/final_so_tong/diary_delete.do";
	
	private Context context;
	private String diaryCode;
	private String sotongContentsCode;
	
	private Handler handler;
	
	public WebServerIndividualDiaryDeleteThread(Context context, String diaryCode, String sotongContentsCode) {
		this.context = context;
		this.diaryCode = diaryCode;
		this.sotongContentsCode = sotongContentsCode;
		this.handler = new Handler();
		// TODO Auto-generated constructor stub
	}
	 
	public void run() {
		String returnLine = request();
		
		if(returnLine != null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인일기 삭제에 성공하였습니다", Toast.LENGTH_SHORT).show();
				}
			});
		}else if(returnLine == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인일기 삭제에 실패하였습니다", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(DELETE_INFO_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute", "1000"));
			fields.add(new BasicNameValuePair("diaryCode", diaryCode));
			fields.add(new BasicNameValuePair("sotongContentsCode", sotongContentsCode));
			
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			HttpResponse response = client.execute(httpPost);
			InputStream inStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			line = reader.readLine();
			
			Log.v("Delete Line Check", line);
			
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
