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

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.finalproject.DetailPostActivity;
import com.example.finalproject.PostBoxActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerDeleteLetterThread extends Thread {

   //private static final String GET_SIMPLE_LETTER_LIST_URL = "http://192.168.0.35:8089/Server/letter_delete.do";
   //private static final String GET_SIMPLE_LETTER_LIST_URL = "http://192.168.0.20:8089/final_so_tong/letter_delete.do";
	private static final String GET_SIMPLE_LETTER_LIST_URL = URLAddress.myUrl+"/final_so_tong/letter_delete.do";
   @SuppressWarnings("deprecation")
   private ActionBarActivity actionBarActivity;
   private Context context;
   private String letterCode;
   private Handler handler;

   public WebServerDeleteLetterThread(ActionBarActivity actionBarActivity,
         Context context, String letterCode) {
      this.actionBarActivity = actionBarActivity;
      this.context = context;
      this.letterCode = letterCode;
      this.handler = new Handler();
   }

   public void run() {
      int res = request();
      if (res != -1) {
         handler.post(new Runnable() {
            public void run() {
               ((DetailPostActivity) context).finish();
            }
         });

      } else {
         handler.post(new Runnable() {
            @Override
            public void run() {

            }
         });

      }
   }

   private int request() {
      String line = null;
      int res = 0;
      try {
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(GET_SIMPLE_LETTER_LIST_URL);
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
                  Toast.makeText(context.getApplicationContext(), "삭제완료", Toast.LENGTH_LONG).show();
               }
               else if (line.startsWith("500")) {
                  res = -1;
               }
            }
         }

      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
      return res;
   }

}