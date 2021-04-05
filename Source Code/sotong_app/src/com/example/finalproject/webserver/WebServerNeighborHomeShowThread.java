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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.finalproject.NeighborHomeActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerNeighborHomeShowThread extends Thread{
	//private static final String NEIGHBOR_HOME_URL = "http://192.168.0.20:8089/final_so_tong/neighborHome.do";
	
	private static final String NEIGHBOR_HOME_URL = URLAddress.myUrl+"/final_so_tong/neighborHome.do";
	
	private static final int NEIGHBOR_SIMPLE_INFO_CNT = 5;
	private Context context;
	private String homeCode;
	private String homeName;
	private Handler handler;
	
	public WebServerNeighborHomeShowThread(Context context, String homeCode, String homeName) {
		this.context = context;
		this.homeCode = homeCode;
		this.homeName = homeName;
		this.handler = new Handler();
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		ArrayList<String[]> list = request();
		
		if(list != null){
			Intent intent = new Intent(context.getApplicationContext(),NeighborHomeActivity.class);
			Bundle bundle = new Bundle();
			
			for(int cnt=0; cnt<list.size(); cnt++){
				bundle.putStringArray("neighborInfo"+(cnt+1), list.get(cnt));
			}
			intent.putExtra("bundle", bundle);
			intent.putExtra("neighborCnt", list.size());
			intent.putExtra("homeName", homeName);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}

	}
	
	private ArrayList<String[]> request(){
		String line = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(NEIGHBOR_HOME_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute","1000"));
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
					if(line.startsWith("300")){
						StringTokenizer st = new StringTokenizer(line,"|");
						String []neighborInfo = new String[NEIGHBOR_SIMPLE_INFO_CNT];
						
						String code = st.nextToken();
						neighborInfo[0] = st.nextToken();
						neighborInfo[1] = st.nextToken();
						neighborInfo[2] = st.nextToken();
						neighborInfo[3] = st.nextToken();
						neighborInfo[4] = st.nextToken();
						list.add(neighborInfo);
						
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
