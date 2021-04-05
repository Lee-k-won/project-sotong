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
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.IndividualDiaryWriteActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerIndividualDiaryWriteThread extends Thread{
	//private static final String INDIVIDUAL_DIARY_WRITE_URL = "http://192.168.0.14:8089/final_so_tong/diary_insert.do";
	//private static final String INDIVIDUAL_DIARY_WRITE_URL = "http://192.168.0.20:8089/final_so_tong/diary_insert.do";
	private static final String INDIVIDUAL_DIARY_WRITE_URL = URLAddress.myUrl+"/final_so_tong/diary_insert.do";
	
	private Context context;
	private String memberCode;
	private String homeCode;
	private String title;
	private String writtenDate;
	private String contents;
	private String imageName; // 경로
	private String imageWrittenDate;//myFormat
	private String emoticonCode; //em1 em2 em3
	
	private Handler handler;
	
	public ActionBarActivity actionBarActivity;
	
	public WebServerIndividualDiaryWriteThread(Context context,
			String memberCode, String homeCode, String title,
			String writtenDate, String contents, String imageName,
			String imageWrittenDate, String emoticonCode, ActionBarActivity actionBarActivity) {
		super();
		this.context = context;
		this.memberCode = memberCode;
		this.homeCode = homeCode;
		this.title = title;
		this.writtenDate = writtenDate;
		this.contents = contents;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.emoticonCode = emoticonCode;
		this.actionBarActivity = actionBarActivity;
		this.handler = new Handler();
	}
	public void run() {
		String result = request();
		
		if(result!=null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인일기작성성공", Toast.LENGTH_SHORT).show();
					//Log.v("class Type", ""+context.); 
					//Log.v("class Type", ""+context.getApplicationContext().toString());
					//context.getClass()
					actionBarActivity.finish();
					//((IndividualDiaryWriteActivity)(context.getApplicationContext())).finish();
				}
			});
		}else if(result == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "개인일기작성실패", Toast.LENGTH_SHORT).show();
					//((IndividualDiaryWriteActivity)context.getApplicationContext()).finish();
				}
			});
		}
		
	}
	
	private String request(){
		String line = null;
		String returnLine = null;
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(INDIVIDUAL_DIARY_WRITE_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute", "1000"));
			fields.add(new BasicNameValuePair("memberCode", memberCode));
			fields.add(new BasicNameValuePair("familyHomeCode", homeCode));
			fields.add(new BasicNameValuePair("diaryTitle", title));
			fields.add(new BasicNameValuePair("diaryDate", writtenDate));
			fields.add(new BasicNameValuePair("diaryContents", contents));
			fields.add(new BasicNameValuePair("imageName", imageName));
			fields.add(new BasicNameValuePair("imageWrittenDate", imageWrittenDate));
			fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
			
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			HttpResponse reponse = client.execute(httpPost);
			InputStream inStream = reponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			line = reader.readLine();
			
			if(line.startsWith("200")){
				StringTokenizer st = new StringTokenizer(line,"|");
				String code = st.nextToken();
				
				returnLine = st.nextToken();
				Log.v("myTag", returnLine);
			}else if(line.startsWith("500")){
				returnLine = null;
			}
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return returnLine;
	}
	
	
	
	
	
	//멤버코드
	//홈코드
	//제목
	//작성날짜
	//작성내용
	//이미지이름
	//이미지작성날짜
	//이모티콘 코드
	
	
}
