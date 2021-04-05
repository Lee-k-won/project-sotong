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
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.DetailPostActivity;
import com.example.finalproject.PostBoxActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerGetDetailLetterInfoThread extends Thread {

   //private static final String GET_DETAIL_LETTER_INFO_URL = "http://192.168.0.35:8089/Server/letter_detail.do";
   //private static final String GET_DETAIL_LETTER_INFO_URL = "http://192.168.0.20:8089/final_so_tong/letter_detail.do";
	private static final String GET_DETAIL_LETTER_INFO_URL = URLAddress.myUrl+"/final_so_tong/letter_detail.do";
	
	private ActionBarActivity actionBarActivity;
   private Context context;
   private String letterCode;

   private Handler handler;

   public WebServerGetDetailLetterInfoThread(ActionBarActivity actionBarActivity, Context context, String letterCode) {

      this.actionBarActivity = actionBarActivity;
      this.context = context;
      this.letterCode = letterCode;
      this.handler = new Handler();
   }

   public void run() {
      String[] list = request();
      if (list != null) {
         Intent intent = new Intent();
         intent.putExtra("letterCount", list.length);
         intent.putExtra("letterCode", this.letterCode);
         intent.putExtra("postInfo", list);
         intent.setClass(context, DetailPostActivity.class);
         context.startActivity(intent);
         
      } else {
         handler.post(new Runnable() {
            @Override
            public void run() {
              
               Toast.makeText(context, "편지 목록이 없습니다.", Toast.LENGTH_SHORT)
                     .show();
            }
         });

      }
   }

   private String[] request() {
      String line = null;
      String[] list = null;
      try {
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(GET_DETAIL_LETTER_INFO_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
         fields.add(new BasicNameValuePair("letterCode", letterCode));

         //httpPost.setEntity(new UrlEncodedFormEntity(fields));
         httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));

         HttpResponse response = client.execute(httpPost);
         InputStream inStream = response.getEntity().getContent();
         BufferedReader reader = new BufferedReader(new InputStreamReader(
               inStream));

         while (true) {
            line = reader.readLine();
 
            if (line == null) {
               break;
            } else {
               if (line.startsWith("200")) {
                  StringTokenizer st = new StringTokenizer(line, "|");
                  list = new String[4];
                  String code = st.nextToken();
                  list[0] = st.nextToken(); // 받는 사람
                  list[1] = st.nextToken();
                  list[2] = st.nextToken(); // 날짜
                  list[3] = st.nextToken(); // 보내는 사람
                     
               }
               else if (line.startsWith("500")) {
                  Toast.makeText(context, "편지를 읽어올수 없음",
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