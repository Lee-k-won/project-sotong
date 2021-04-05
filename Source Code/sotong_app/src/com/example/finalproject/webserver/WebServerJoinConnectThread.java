package com.example.finalproject.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

public class WebServerJoinConnectThread extends Thread{
	//private static final String JOIN_URL = "http://192.168.0.110:8089/so-tong/join.do";
	//private static final String JOIN_URL = "http://192.168.0.14:8089/final_so_tong/join.do";
	//private static final String JOIN_URL = "http://192.168.0.20:8089/final_so_tong/join.do";
	
	private static final String JOIN_URL = URLAddress.myUrl+"/final_so_tong/join.do";
	
	public ActionBarActivity actionBarActivity;
	private Context context;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String homeCode;  
	
	private Handler handler;

	public WebServerJoinConnectThread(ActionBarActivity actionBarActivity, Context context, String id, String password, String name, String email, String phoneNum, String homeCode) {
		super();
		this.actionBarActivity = actionBarActivity;
		this.context = context;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.homeCode = homeCode;
		this.handler = new Handler();
	}
	
	public void run() {
		super.run();
		String returnString = request();
		
		if(returnString.equals("success")){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
					actionBarActivity.finish();
				}
			});
			 
		}else if(returnString.equals("fail")){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
				}
			});
		}	
	}
	private String request(){
		String line = null; 
		try{ 
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(JOIN_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("joinServiceRoute","1000"));
			fields.add(new BasicNameValuePair("joinId",id));
			fields.add(new BasicNameValuePair("joinPassword",password));
			fields.add(new BasicNameValuePair("joinName",name));
			fields.add(new BasicNameValuePair("joinEmail",email));
			fields.add(new BasicNameValuePair("joinPhoneNum",phoneNum));
			//fields.add(new BasicNameValuePair("joinHomeCode","h1"));
			 
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			HttpResponse reponse = client.execute(httpPost);
			InputStream inStream = reponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			line = reader.readLine();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return line;
	}
	
	

}
