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

public class WebServerFamilyDiaryModifyThread extends Thread{
	//private static final String FAMILY_DIARY_UPDATE_URL = "http://192.168.0.20:8089/final_so_tong/familyDiary_update.do";//
	private static final String FAMILY_DIARY_UPDATE_URL = URLAddress.myUrl+"/final_so_tong/familyDiary_update.do";//
	
	private Context context;
	private String sotongContentsCode;
	private String contents;
	private String imageName;
	private String imageWrittenDate;
	private String emoticonCode;
	
	private Handler handler;
	private ActionBarActivity activity;
	public WebServerFamilyDiaryModifyThread(Context context,
			String sotongContentsCode, String contents, String imageName,
			String imageWrittenDate, String emoticonCode,
			ActionBarActivity activity) {
		super();
		this.context = context;
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
			handler.post(new Runnable() {
			public void run() {
				
				Toast.makeText(context, "가족일기수정하기 성공", Toast.LENGTH_SHORT).show();
				activity.finish();
			}
		});
		if(returnLine != null){
			
		}else if(returnLine == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "가족일기수정하기 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(FAMILY_DIARY_UPDATE_URL);
	        List<NameValuePair> fields = new ArrayList<NameValuePair>();
	        fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	        fields.add(new BasicNameValuePair("sotongContentsCode", sotongContentsCode));
	        fields.add(new BasicNameValuePair("contents", contents));
	        fields.add(new BasicNameValuePair("imageName", imageName));
	        fields.add(new BasicNameValuePair("imageWrittenDate", imageWrittenDate));
	        fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
	        
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
	
	
	
	/*
	 modify[0] = data.get(cnt).getFamilyDiaryPartCode();
	modify[1] = data.get(cnt).getMemberNickName();
	modify[2] = data.get(cnt).getMemberPhoto();
	modify[3] = data.get(cnt).getFamilyDiaryPartDate();
	modify[4] = data.get(cnt).getSotongContentsCode();   //
	modify[5] = data.get(cnt).getContents();             //
	modify[6] = data.get(cnt).getEmoticonName();         // e code
	modify[7] = data.get(cnt).getEmoticonRoute();
	modify[8] = data.get(cnt).getImageName();            //
	modify[9] = data.get(cnt).getImageWrittenDate();     // 
	 */
	
}
