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
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.FragmentDiary;
import com.example.finalproject.R;
import com.example.finalproject.model.SimpleIndividualDiaryInfo;
import com.example.finalproject.model.URLAddress;

public class WebServerGetIndividualDiarySimpleListThread extends Thread{
	//private static final String GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL = "http://192.168.0.14:8089/final_so_tong/diary.do";//
	//private static final String GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL = "http://192.168.0.20:8089/final_so_tong/diary.do";//
	//private static final String GET_SIMPLE_FAMILY_DIARY_LIST_URL = "http://192.168.0.20:8089/final_so_tong/familyDiary.do";
	
	private static final String GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL = URLAddress.myUrl+"/final_so_tong/diary.do";//
	private static final String GET_SIMPLE_FAMILY_DIARY_LIST_URL = URLAddress.myUrl+"/final_so_tong/familyDiary.do";
	
	
	private static final int INDIVIDUAL_SIMPLE_CNT=3;
	private static final int FAMILY_SIMPLE_CNT=3;
	private Context context;
	private String []userInfo;
	private Handler handler;
	public FragmentManager fm;
	//public FragmentTransaction fragmentTransaction;
	public Fragment fr;
	
	public WebServerGetIndividualDiarySimpleListThread(Context context, String []userInfo, FragmentManager fm, Fragment fr) {
		super();
		this.context = context;
		this.userInfo = userInfo;
		this.fm = fm;

		this.fr = fr;
		this.handler = new Handler();
	}	
	
	@SuppressLint("NewApi")
	public void run() {
		final ArrayList<String[]> list = requestIndividualInfo();
		final ArrayList<String[]> familyList = requestFamilyInfo();
		//   = requestFamily
		ArrayList<SimpleIndividualDiaryInfo> data = new ArrayList<SimpleIndividualDiaryInfo>();
		ArrayList<String[]> familyData = new ArrayList<String[]>();
		
		if(list != null){
			for(int cnt=0; cnt<list.size(); cnt++){
				data.add(new SimpleIndividualDiaryInfo(list.get(cnt)[0], list.get(cnt)[1], list.get(cnt)[2]));
				Log.v("SimpleIndividualDiaryInfo", data.get(cnt).getDiaryTitle());
			}
			
		}
		if(familyList != null){
			for(int cnt=0; cnt<familyList.size(); cnt++){
				familyData.add(new String[]{familyList.get(cnt)[0],familyList.get(cnt)[1],familyList.get(cnt)[2]});
				
			}
		}
		
		fr = new FragmentDiary(userInfo,data,familyData);
		
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.fragment_place, fr);
		ft.commit();

		/*
		if(list != null){
			ArrayList<SimpleIndividualDiaryInfo> data = new ArrayList<SimpleIndividualDiaryInfo>();
			
			for(int cnt=0; cnt<list.size(); cnt++){
				data.add(new SimpleIndividualDiaryInfo(list.get(cnt)[0], list.get(cnt)[1], list.get(cnt)[2]));
				Log.v("SimpleIndividualDiaryInfo", data.get(cnt).getDiaryTitle());
				
				fr = new FragmentDiary(userInfo,data);
				
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(R.id.fragment_place, fr);
				ft.commit();
			
			}
			
			
		}
		*/
		/*
		else if(list == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "간략정보읽어오기 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
		*/

	}
	
	//familyDiaryCode
	//familyHomeCode;
	//familyDiaryDate;
	
	private ArrayList<String[]> requestFamilyInfo(){
		String line = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		try{
			 HttpClient client = new DefaultHttpClient();
	         HttpPost httpPost = new HttpPost(GET_SIMPLE_FAMILY_DIARY_LIST_URL);
	         List<NameValuePair> fields = new ArrayList<NameValuePair>();
	         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	         fields.add(new BasicNameValuePair("homeCode", userInfo[1]));
	         
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
	        			 String []familyDiarySimpleInfo = new String[FAMILY_SIMPLE_CNT];
	        			 
	        			 String code = st.nextToken();
	        			 
	        			 familyDiarySimpleInfo[0] = st.nextToken();
	        			 Log.v("familySimpleDiary", "Diary Code is : "+familyDiarySimpleInfo[0]);
	        			 
	        			 familyDiarySimpleInfo[1] = st.nextToken();
	        			 Log.v("familySimpleDiary", "Home Code is : "+familyDiarySimpleInfo[1]);
	        			 
	        			 familyDiarySimpleInfo[2] = st.nextToken();
	        			 Log.v("familySimpleDiary", "Written Date is : "+familyDiarySimpleInfo[2]);
	        			
	        			 list.add(familyDiarySimpleInfo);
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
	
	
	private ArrayList<String[]> requestIndividualInfo(){
		String line = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		//
		try{
			 HttpClient client = new DefaultHttpClient();
	         HttpPost httpPost = new HttpPost(GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL);
	         List<NameValuePair> fields = new ArrayList<NameValuePair>();
	         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	         fields.add(new BasicNameValuePair("memberCode", userInfo[0]));
	         
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
	        			 String []simpleInfo = new String[INDIVIDUAL_SIMPLE_CNT];
	        			 String code = st.nextToken();
	        			
	        			 simpleInfo[0] = st.nextToken();
	        			 Log.v("simpleDiary", "Diary Code is : "+simpleInfo[0]);
	        			 
	        			 simpleInfo[1] = st.nextToken();
	        			 Log.v("simpleDiary", "Diary Title is : "+simpleInfo[1]);
	        			 
	        			 simpleInfo[2] = st.nextToken();
	        			 Log.v("simpleDiary", "Diary Date is : "+simpleInfo[2]);
	        			 
	        			 //diaryCode
	        			 //diaryTitle
	        			 //diaryDate
	        			 list.add(simpleInfo);
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


/*
public class WebServerGetIndividualDiarySimpleListThread extends Thread{
	//private static final String GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL = "http://192.168.0.20:8089/final_so_tong/";//
	private static final String GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL = "http://192.168.0.14:8089/final_so_tong/diary.do";//
	private static final int INDIVIDUAL_SIMPLE_CNT=3;
	private Context context;
	private String memberCode;
	private ListView listView;
	private Fragment fragmentDiary;
	private Handler handler;
	private SimpleDiaryInfoListAdapter simpleDiaryInfoListAdapter;
	private ArrayList<SimpleIndividualDiaryInfo> data;
	
	
	public WebServerGetIndividualDiarySimpleListThread(Context context, String memberCode, ListView listView, 
			Fragment fragmentDiary, SimpleDiaryInfoListAdapter simpleDiaryInfoListAdapter,ArrayList<SimpleIndividualDiaryInfo> data) {
		this.context = context;
		this.memberCode = memberCode;
		this.listView = listView;
		this.fragmentDiary = fragmentDiary;
		this.simpleDiaryInfoListAdapter = simpleDiaryInfoListAdapter;
		this.data = data;
		this.handler = new Handler();
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("NewApi")
	public void run() {
		final ArrayList<String[]> list = request();
		if(list != null){
			data = new ArrayList<SimpleIndividualDiaryInfo>();
			for(int cnt=0; cnt<list.size(); cnt++){
				data.add(new SimpleIndividualDiaryInfo(list.get(cnt)[0], list.get(cnt)[1], list.get(cnt)[2]));
				Log.v("SimpleIndividualDiaryInfo", data.get(cnt).getDiaryTitle());
			}
			simpleDiaryInfoListAdapter = new SimpleDiaryInfoListAdapter(fragmentDiary.getActivity(),data);
			Log.v("Count", ""+simpleDiaryInfoListAdapter.getCount());
			
			handler.post(new Runnable() {
				public void run() {		
					listView.setAdapter(simpleDiaryInfoListAdapter);
					Toast.makeText(context, "간략정보읽어오기 성공", Toast.LENGTH_SHORT).show();
				}
			});
		
		}else if(list == null){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context, "간략정보읽어오기 실패", Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
	
	public ArrayList<SimpleIndividualDiaryInfo> getData(){
		return data;
	}
	
	private ArrayList<String[]> request(){
		String line = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		//
		try{
			 HttpClient client = new DefaultHttpClient();
	         HttpPost httpPost = new HttpPost(GET_SIMPLE_INDIVIDUAL_DIARY_LIST_URL);
	         List<NameValuePair> fields = new ArrayList<NameValuePair>();
	         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
	         fields.add(new BasicNameValuePair("memberCode", memberCode));
	         
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
	        			 String []simpleInfo = new String[INDIVIDUAL_SIMPLE_CNT];
	        			 String code = st.nextToken();
	        			
	        			 simpleInfo[0] = st.nextToken();
	        			 Log.v("simpleDiary", "Diary Code is : "+simpleInfo[0]);
	        			 
	        			 simpleInfo[1] = st.nextToken();
	        			 Log.v("simpleDiary", "Diary Title is : "+simpleInfo[1]);
	        			 
	        			 simpleInfo[2] = st.nextToken();
	        			 Log.v("simpleDiary", "Diary Date is : "+simpleInfo[2]);
	        			 
	        			 //diaryCode
	        			 //diaryTitle
	        			 //diaryDate
	        			 list.add(simpleInfo);
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
*/
