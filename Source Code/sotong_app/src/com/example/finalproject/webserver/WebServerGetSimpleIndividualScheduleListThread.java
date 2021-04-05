package com.example.finalproject.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.FragmentSchedule;
import com.example.finalproject.R;
import com.example.finalproject.model.URLAddress;

public class WebServerGetSimpleIndividualScheduleListThread extends Thread {
   private static final String GET_SCHEDULE_LIST_URL = URLAddress.myUrl+"/final_so_tong/scheduleList.do";
   private static final String GET_MATCHING_URL = URLAddress.myUrl+"/final_so_tong/matching.do";
   
      //private ActionBarActivity actionBarActivity;
      private Context context;
      private FragmentManager fm; 
      private Fragment fr;
      private Handler handler;

      private String []userInfo;
      
      public WebServerGetSimpleIndividualScheduleListThread(Context context, String []userInfo, FragmentManager fm, Fragment fr) {
         //this.actionBarActivity = actionBarActivity;
         this.context = context;
         this.userInfo = userInfo;
         this.fm=fm;
         this.fr=fr;
         this.handler = new Handler();
      }

      @SuppressLint("NewApi")
   public void run() {
         ArrayList<String[]> list = request();
         String []returnString = requestMatchingResult(); 
         //String[]
         if (list != null) {
            fr = new FragmentSchedule(list, returnString,userInfo); // 프래그먼트에 이미지 목록 정보를 생성자
              // 파라미터로 전달하여 생성.
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place, fr);
            ft.commit();
           
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
           
           /* intent.setClass(context, PostBoxActivity.class);
            context.startActivity(intent);*/

         } else {
            handler.post(new Runnable() {
               @Override
               public void run() {
                  Toast.makeText(context, "일정이 없습니다.", Toast.LENGTH_SHORT)
                        .show();
               }
            });

         }
      }
      private String[] requestMatchingResult(){
    	  String resultString[] = null;
    	  String line = null;
    	  ArrayList<String> list = new ArrayList<String>();
    	  try{
    		  HttpClient client = new DefaultHttpClient();
              HttpPost httpPost = new HttpPost(GET_MATCHING_URL);
              List<NameValuePair> fields = new ArrayList<NameValuePair>();
              fields.add(new BasicNameValuePair("serviceRoute", "1000"));
              fields.add(new BasicNameValuePair("homeCode", userInfo[1]));
              fields.add(new BasicNameValuePair("year", (new Date().getYear()-100)+""));
              
              int month=(new Date().getMonth()+1);
              String strMonth = null;
              if(month <10){
            	  strMonth = "0"+month;
              }else{
            	  strMonth = ""+month;
              }
              
              fields.add(new BasicNameValuePair("month", strMonth));

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
            			  String code = st.nextToken();
            			  
            			  String result = st.nextToken();  Log.v("calendar", "Check : "+result);
            			  
            			  list.add(result);
            		  }else if(line.startsWith("500")){
            			  resultString = null;
            		  }
            	  }
              }
              
              resultString = new String[list.size()];
              for(int cnt=0; cnt<list.size(); cnt++){
            	  resultString[cnt] = list.get(cnt);
            	  
              }
              
              for(int cnt=0; cnt<resultString.length; cnt++){
            	  Log.v("111 result is : ", resultString[cnt]);
              }
              
    	  }catch (IOException ioe) {
              ioe.printStackTrace();
          }
    	  
    	  return resultString;
      }

      private ArrayList<String[]> request() {
         String line = null;
         ArrayList<String[]> list = new ArrayList<String[]>();
         try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(GET_SCHEDULE_LIST_URL);
            List<NameValuePair> fields = new ArrayList<NameValuePair>();
            fields.add(new BasicNameValuePair("serviceRoute", "1000"));
            fields.add(new BasicNameValuePair("memberCode", userInfo[0]));
            fields.add(new BasicNameValuePair("year", (new Date().getYear()-100)+""));
            fields.add(new BasicNameValuePair("month", (new Date().getMonth()+1)+""));

            //httpPost.setEntity(new UrlEncodedFormEntity(fields));
            httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));

            HttpResponse response = client.execute(httpPost);
            InputStream inStream = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                  inStream));

            while (true) {
               line = reader.readLine();
               // Log.v("@@@test",line);
               if (line == null) {
                  break;
               } else {
                  if (line.startsWith("200")) {
                     StringTokenizer st = new StringTokenizer(line, "|");
                     String[] scheduleInfo = new String[8];
                     String code = st.nextToken();
                     while (st.hasMoreTokens()) {
                        scheduleInfo[0] = st.nextToken();// 일정코드
                        scheduleInfo[1] = st.nextToken();// 멤버코드
                        scheduleInfo[2] = st.nextToken();// 일정제목
                        scheduleInfo[3] = st.nextToken();// 일정장소
                        scheduleInfo[4] = st.nextToken();// 일정시작시간
                        scheduleInfo[5] = st.nextToken();// 일정 종료시간
                        scheduleInfo[6] = st.nextToken();// 반복횟수
                        scheduleInfo[7] = st.nextToken();// 일정 메모 내용
                        
                        list.add(scheduleInfo);
                     }
                  }

                  else if (line.startsWith("500")) {
                     Toast.makeText(context.getApplicationContext(), "일정을 읽어올수 없음",
                           Toast.LENGTH_SHORT).show();
                     list = null;

                  }
               }
            }

         } catch (IOException ioe) {
            ioe.printStackTrace();
         }
         return list;
      }
}
