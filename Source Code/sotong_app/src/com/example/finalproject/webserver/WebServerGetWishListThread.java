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
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.WishListActivity;
import com.example.finalproject.model.URLAddress;

public class WebServerGetWishListThread extends Thread {

   //private static final String GET_WISH_LIST_LIST_URL = "http://192.168.0.35:8089/final_so_tong/wish.do";
   //private static final String GET_WISH_LIST_LIST_URL = "http://192.168.0.20:8089/final_so_tong/wish.do";
   
   private static final String GET_WISH_LIST_LIST_URL = URLAddress.myUrl+"/final_so_tong/wish.do";
   
   private Context context;
   private String homeCode;
   private String memberCode;
 
   private Handler handler;
 
   public WebServerGetWishListThread(Context context, String homeCode,
         String memberCode) {
      this.context = context;
      this.homeCode = homeCode;
      this.memberCode = memberCode;
      this.handler = new Handler();
   }

   public void run() {
      ArrayList<String[]> list = request();
      
         Intent intent = new Intent();
       
         intent.putExtra("WishInfoList", list);
        
 
         intent.putExtra("memberCode", memberCode);
         intent.putExtra("homeCode", homeCode);
         if(list != null){
        	 intent.putExtra("wishCount", list.size());
         }else{
        	 intent.putExtra("wishCount", 0);
         }
         
         
         //System.out.println("wishCount:" + list.size());
         // intent.putExtra("wishList", bundle);
         intent.setClass(context, WishListActivity.class);
         context.startActivity(intent);

       
      /*
      else if(list==null){
    	  Log.v("Wish is Null", "here");
         handler.post(new Runnable() {
            @Override
            public void run() {
               Toast.makeText(context.getApplicationContext(),
                     "���� ����� �����ϴ�.", Toast.LENGTH_SHORT).show();
            }
         });

      }*/
   }

   private ArrayList<String[]> request() {
      String line = null;
      ArrayList<String[]> list = new ArrayList<String[]>();
      try {
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(GET_WISH_LIST_LIST_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
         fields.add(new BasicNameValuePair("homeCode", homeCode));

         //httpPost.setEntity(new UrlEncodedFormEntity(fields));
         httpPost.setEntity(new UrlEncodedFormEntity(fields,"utf-8"));

         HttpResponse response = client.execute(httpPost);
         InputStream inStream = response.getEntity().getContent();
         BufferedReader reader = new BufferedReader(new InputStreamReader(
               inStream));

         while (true) {
            line = reader.readLine();
            // Log.v("test",line);
            if (line == null) {
               break;
            } else {
               if (line.startsWith("200")) {
                  StringTokenizer st = new StringTokenizer(line, "|");
                  String[] wishList = new String[10];
                  String code = st.nextToken();

                  wishList[0] = st.nextToken(); Log.v("wish", wishList[0]); // �����ڵ�     
                  wishList[1] = st.nextToken(); Log.v("wish", wishList[1]);// ����
                  wishList[2] = st.nextToken(); Log.v("wish", wishList[2]);// �����ڵ�
                  wishList[3] = st.nextToken(); Log.v("wish", wishList[3]);// ���� ����
                  wishList[4] = st.nextToken(); Log.v("wish", wishList[4]);// ���ó���
                  wishList[5] = st.nextToken(); Log.v("wish", wishList[5]);// �̸�Ƽ�ܸ�
                  wishList[6] = st.nextToken(); Log.v("wish", wishList[6]);// �̸�Ƽ�ܰ��
                  wishList[7] = st.nextToken(); Log.v("wish", wishList[7]);// �����ۼ���
                  wishList[8] = st.nextToken(); Log.v("wish", wishList[8]);// ���ø�����
                  wishList[9] = st.nextToken(); Log.v("wish", wishList[9]);// �������Ῡ��

                  list.add(wishList);

               }

               else if (line.startsWith("500")) {
                  handler.post(new Runnable() {
					public void run() {
						Toast.makeText(context.getApplicationContext(),
		                        "���ø���� �о�ü� ����", Toast.LENGTH_SHORT).show();	
					}
				});
            	   
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