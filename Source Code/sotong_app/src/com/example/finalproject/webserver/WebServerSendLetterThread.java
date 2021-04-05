package com.example.finalproject.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
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
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.finalproject.PostWriteActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerSendLetterThread extends Thread {

   //private static final String GET_SIMPLE_LETTER_LIST_URL = "http://192.168.0.35:8089/Server/letter_write.do";
   //private static final String GET_SIMPLE_LETTER_LIST_URL = "http://192.168.0.20:8089/final_so_tong/letter_write.do";
   
	private static final String GET_SIMPLE_LETTER_LIST_URL = URLAddress.myUrl+"/final_so_tong/letter_write.do";
	
   private Context context;
   private String senderCode;
   private String receiverCode;
   private String title;
   private String contents;
   private String imageName;
   private String emoticonCode;
   private String letterWrittenDate;
   private Handler handler;

   public WebServerSendLetterThread(Context context, String senderCode,
         String receiverCode, String title, String contents,
         String imageName, String emoticonCode, String letterWrittenDate) {
      
      this.context = context;
      this.senderCode = senderCode;
      this.receiverCode = receiverCode;
      this.title = title;
      this.contents = contents;
      this.imageName = imageName;
      this.emoticonCode = emoticonCode;
      this.letterWrittenDate = letterWrittenDate;
      this.handler = new Handler();

   }

   public void run() {
      int res = request();
      if (res != -1) {
         handler.post(new Runnable() {
            @Override
            public void run() {

               ((PostWriteActivity) context).finish();

            }
         });

      } else {
         handler.post(new Runnable() {
            @Override
            public void run() {
               Toast.makeText(context.getApplicationContext(), "�몄� �꾩넚�ㅽ뙣",
                     Toast.LENGTH_SHORT).show();
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
         fields.add(new BasicNameValuePair("senderCode", senderCode));
         fields.add(new BasicNameValuePair("receiverCode", receiverCode));
         fields.add(new BasicNameValuePair("title", title));
         
         //fields.add(new BasicNameValuePair("title", new String(title.getBytes(),"UTF-8")));
         
         
         //fields.add(new BasicNameValuePair("title", URLEncoder.encode(title,"utf-8")));
         
         fields.add(new BasicNameValuePair("contents", contents));
         //fields.add(new BasicNameValuePair("contents", new String(contents.getBytes(),"UTF-8")));
         
         //fields.add(new BasicNameValuePair("contents", URLEncoder.encode(contents,"utf-8")));
         fields.add(new BasicNameValuePair("imageName", imageName));
         fields.add(new BasicNameValuePair("emoticonCode", emoticonCode));
         fields.add(new BasicNameValuePair("letterWrittenDate",letterWrittenDate));
         
         
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