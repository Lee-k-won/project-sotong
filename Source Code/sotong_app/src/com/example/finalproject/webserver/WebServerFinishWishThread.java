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
import android.widget.Toast;

public class WebServerFinishWishThread extends Thread {
   //private static final String FINISH_WISH_URL = "http://192.168.0.35:8089/final_so_tong/wish_finish.do";
   //private static final String FINISH_WISH_URL = "http://192.168.0.20:8089/final_so_tong/wish_finish.do";
	private static final String FINISH_WISH_URL = URLAddress.myUrl+"/final_so_tong/wish_finish.do";
	
   private Context context;
   private String wishCode;
   private String finishDate;
   private Handler handler;

   public WebServerFinishWishThread(Context context, String wishCode,
         String finishDate) {

      this.context = context;
      this.wishCode = wishCode;
      this.finishDate = finishDate;
      this.handler = new Handler();
   }

   public void run() {
      int res = request();
      if (res != -1) {
         handler.post(new Runnable() {
            @Override
            public void run() {
               Toast.makeText(context.getApplicationContext(),
                     "위시 성취 완료 !", Toast.LENGTH_LONG).show();
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
         HttpPost httpPost = new HttpPost(FINISH_WISH_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
         fields.add(new BasicNameValuePair("wishCode", wishCode));
         fields.add(new BasicNameValuePair("finishDate", finishDate));

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
                  // Toast.makeText(context.getApplicationContext(),
                  // "위시완료", Toast.LENGTH_LONG).show();
               } else if (line.startsWith("500")) {
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