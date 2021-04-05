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
import android.util.Log;
import android.widget.Toast;

public class WebServerIndividualDiaryModifyThread extends Thread{
	//private static final String INDIVIDUAL_DIARY_MODIFY_URL = "http://192.168.0.14:8089/final_so_tong/diary_update.do";
	//private static final String INDIVIDUAL_DIARY_MODIFY_URL = "http://192.168.0.20:8089/final_so_tong/diary_update.do";
	
	private static final String INDIVIDUAL_DIARY_MODIFY_URL = URLAddress.myUrl+"/final_so_tong/diary_update.do";
	
	private Context context;
	private String memberCode;
	private String diaryCode;
	private String title;
	private String contents; 
	private String writtenDate;
	private String sotongContentsCode;
	private String imageName; // 경로
	private String imageWrittenDate;//myFormat
	private String emoticonCode; //em1 em2 em3
	
	private Handler handler;
	
	public ActionBarActivity actionBarActivity;
	
	public WebServerIndividualDiaryModifyThread(Context context,
			String memberCode, String diaryCode, String title, String contents,
			String writtenDate, String sotongContentsCode, String imageName,
			String imageWrittenDate, String emoticonCode,
			ActionBarActivity actionBarActivity) {
		super();
		this.context = context;
		this.memberCode = memberCode;
		this.diaryCode = diaryCode;
		this.title = title;
		this.contents = contents;
		this.writtenDate = writtenDate;
		this.sotongContentsCode = sotongContentsCode;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.emoticonCode = emoticonCode;
		this.actionBarActivity = actionBarActivity;
		
		this.handler = new Handler();
	}

	public void run() {
		String returnLine = request();
		
		if(returnLine != null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인일기 수정완료", Toast.LENGTH_SHORT).show();
					actionBarActivity.finish();
				}
			});
			
		}else if(returnLine == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인일기 수정실패", Toast.LENGTH_SHORT).show();
				
				}
			});
		}
	
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(INDIVIDUAL_DIARY_MODIFY_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute", "1000"));
			fields.add(new BasicNameValuePair("memberCode", memberCode));
			fields.add(new BasicNameValuePair("diaryCode", diaryCode));
			fields.add(new BasicNameValuePair("diaryTitle", title));
			fields.add(new BasicNameValuePair("contents", contents));
			fields.add(new BasicNameValuePair("diaryDate", writtenDate));
			fields.add(new BasicNameValuePair("sotongContentsCode", sotongContentsCode));
			fields.add(new BasicNameValuePair("imageName", imageName));
			fields.add(new BasicNameValuePair("imageWrittenDate", imageWrittenDate));
			fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
			
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			HttpResponse reponse = client.execute(httpPost);
			InputStream inStream = reponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			line = reader.readLine();
			Log.v("Modify Line Check", line);
			
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
