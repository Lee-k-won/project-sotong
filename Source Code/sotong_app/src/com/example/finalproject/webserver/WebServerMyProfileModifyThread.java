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
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.MyProfileModifyActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerMyProfileModifyThread extends Thread{
	//private static final String MY_PROFILE_MODIFY_URL = "http://192.168.0.14:8089/final_so_tong/";
	//private static final String MY_PROFILE_MODIFY_URL = "http://192.168.0.25:8089/final_so_tong/modifyMemberOk.do";
	//private static final String JOIN_URL = "http://192.168.0.14:8089/so-tong/join.do";
	//private static final String MY_PROFILE_MODIFY_URL = "http://192.168.0.20:8089/final_so_tong/modifyMemberOk.do";
	
	private static final String MY_PROFILE_MODIFY_URL = URLAddress.myUrl+"/final_so_tong/modifyMemberOk.do";
	private static final String IMG_SAVE_URL = URLAddress.myUrl+"/final_so_tong/image_save.do";
	private Context context;
	private String []modifyInfoArr;
	
	private Handler handler;
	
	private byte[] imageFile;
	/*
	public WebServerMyProfileModifyThread(Context context, String []modifyInfoArr) {
		this.context = context;
		this.modifyInfoArr = modifyInfoArr;
		this.handler = new Handler();
	}*/
	public WebServerMyProfileModifyThread(Context context,String[] modifyInfoArr,byte[] imageFile) {
	      // TODO Auto-generated constructor stub
	      this.context = context;
	      this.modifyInfoArr = modifyInfoArr;
	      this.imageFile=imageFile;
	      this.handler=new Handler();
	}
	
	public void run() {
		String result = request();
		int result2 = 1;
		if(imageFile != null){
			result2 = sendImage();
		}
		if(result != null && result2 != 0 ){
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context.getApplicationContext(), "수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
					((MyProfileModifyActivity)context).finish();
				}
			});
			
		}else{
			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(context.getApplicationContext(), "오류가 발생했습니다", Toast.LENGTH_SHORT).show();
				}
			});
			
		}
	 
	}
	
	private String request(){
		String line = null;
		String resultStr = null;
		try{
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(MY_PROFILE_MODIFY_URL);
			List<NameValuePair> fields = new ArrayList<NameValuePair>();
			fields.add(new BasicNameValuePair("serviceRoute","1000"));
			fields.add(new BasicNameValuePair("code", modifyInfoArr[0])); // MemberCode
			fields.add(new BasicNameValuePair("name", modifyInfoArr[2])); // MemberName
			fields.add(new BasicNameValuePair("phone", modifyInfoArr[3])); // MemberPhone
			fields.add(new BasicNameValuePair("email", modifyInfoArr[4])); // MemberEmail
			fields.add(new BasicNameValuePair("pw", modifyInfoArr[6])); //MemberPassword
			fields.add(new BasicNameValuePair("nickName", modifyInfoArr[8])); //MemberNickName
			fields.add(new BasicNameValuePair("color", modifyInfoArr[9])); //MemberColor
			fields.add(new BasicNameValuePair("birth", modifyInfoArr[10])); //MemberBirth
			fields.add(new BasicNameValuePair("photo", modifyInfoArr[7]));
			//fields.add(new BasicNameValuePair("role", modifyInfoArr[11]));
			
			//httpPost.setEntity(new UrlEncodedFormEntity(fields));
			httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));
			
			HttpResponse reponse = client.execute(httpPost);
			InputStream inStream = reponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			line = reader.readLine();
			if(line != null){
				if(line.startsWith("200")){
					StringTokenizer st = new StringTokenizer(line,"|");
					String code = st.nextToken();
					resultStr = st.nextToken();
				}else{
					resultStr = null;
				}
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return resultStr;
	}
	
	public int sendImage(){ //이미지파일 추가를 위한 메소드
	      
	      String line=null;
	      int res=0;
	      try
	      {
	         HttpClient client=new DefaultHttpClient();
	         HttpPost post=new HttpPost(IMG_SAVE_URL);//이미지 서블릿 주소
	         List<NameValuePair> fields = new ArrayList<NameValuePair>();
	         
	         String imageToString = Base64.encodeToString(imageFile, Base64.DEFAULT);//생성자로부터 받아온 바이트 배열을 Base64를 통해 String 형태로 바꾼다.
	         
	         fields.add(new BasicNameValuePair("image", imageToString.trim()));
	         fields.add(new BasicNameValuePair("imageName",modifyInfoArr[7]));
	            
	         
	         post.setEntity(new UrlEncodedFormEntity(fields));
	         
	         HttpResponse response=client.execute(post);
	         InputStream inStream = response.getEntity().getContent();
	         BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	         
	         line = reader.readLine();
	         //System.out.println(line);
	         
	         if(line != null){
	            if(line.startsWith("200")){
	               res=1;
	            }else{
	               res=0;
	            }
	         }
	      }catch(IOException ioe){
	         
	      }
	      return res;
	      }
	
	
}
