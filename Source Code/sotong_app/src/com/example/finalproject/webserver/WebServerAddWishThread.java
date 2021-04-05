package com.example.finalproject.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.AddWishActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerAddWishThread extends Thread {

   //private static final String ADD_WISH_URL = "http://192.168.0.35:8089/final_so_tong/wish_add.do";
	
   //private static final String ADD_WISH_URL = "http://192.168.0.20:8089/final_so_tong/wish_add.do";
	private static final String ADD_WISH_URL = URLAddress.myUrl+"/final_so_tong/wish_add.do";
 
   @SuppressWarnings("deprecation")
   private Context context;
   private String memberCode;
   private String homeCode;
   private String wishTitle;
   private String contents;
   private String imageName;
   private String emoticonCode;
   private String wishWrittenDate;
   private String wishEndDate;

   private Handler handler;

   public WebServerAddWishThread(Context context, String memberCode,
         String homeCode, String wishTitle, String contents,
         String imageName, String emoticonCode, String wishWrittenDate,
         String wishEndDate) {
      this.context = context;
      this.memberCode = memberCode;
      this.homeCode = homeCode;
      this.wishTitle = wishTitle;
      this.contents = contents;
      this.imageName = imageName;
      this.emoticonCode = emoticonCode;
      this.wishWrittenDate = wishWrittenDate;
      this.wishEndDate = wishEndDate;
      this.handler = new Handler();

   }

   public void run() {
      int res = request();
      if (res != -1) {
         handler.post(new Runnable() {
            public void run() {
               ((AddWishActivity) context).finish();
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
         HttpPost httpPost = new HttpPost(ADD_WISH_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
         fields.add(new BasicNameValuePair("memberCode", memberCode));
         fields.add(new BasicNameValuePair("homeCode", homeCode));
         fields.add(new BasicNameValuePair("wishTitle", wishTitle));
         fields.add(new BasicNameValuePair("contents", contents));
         fields.add(new BasicNameValuePair("imageName", imageName));
         fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
         fields.add(new BasicNameValuePair("wishWrittenDate",
               wishWrittenDate));
         fields.add(new BasicNameValuePair("wishEndDate", wishEndDate));
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
                  // "위시 추가완료", Toast.LENGTH_LONG).show();
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

   public Date changeStringToDate(String date) {
      Date dt = null;
      try {
         dt = new SimpleDateFormat("yy-MM-dd").parse(date);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return dt;

   }

   public String changeDateToString(Date date) {
      return new SimpleDateFormat("yy-MM-dd").format(date);
   }
}