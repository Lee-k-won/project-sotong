package com.example.finalproject.webserver;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.finalproject.model.URLAddress;

import android.content.Context;

public class WebServerHomeInfoThread extends Thread{
	//private static final String LOGIN_URL = "http://192.168.0.110:8089/final_so_tong/login.do";
	//private static final String LOGIN_URL = "http://192.168.0.20:8089/final_so_tong/login.do";
	private static final String LOGIN_URL = URLAddress.myUrl+"/final_so_tong/login.do";
	
	private Context context;
	
	public WebServerHomeInfoThread(Context context) {
		this.context = context;
	}
	public void run() {
		String [][]returnString = request();
	}
	private String[][] request(){
		String [][]line = null;
		/* 
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(LOGIN_URL);
			List<NameValuePair> fields
		}
		*/
		return null;
	}
	
}
