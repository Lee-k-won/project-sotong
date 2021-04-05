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

import com.example.finalproject.FragmentAlbum;
import com.example.finalproject.MainMenuActivity;
import com.example.finalproject.R;
import com.example.finalproject.model.URLAddress;

public class WebServerGetImageListThread extends Thread {

   private static final String GET_IMAGE_LIST_URL = URLAddress.myUrl+"/final_so_tong/get_image_list.do";
   private Context context;
   private String homeCode;
   private Handler handler;
   private FragmentManager fm;
   private Fragment fr;

   public WebServerGetImageListThread(Context context, String homeCode,
         FragmentManager fm, Fragment fr) {
      this.context = context;
      this.homeCode = homeCode;
      this.fm = fm;
      this.fr = fr;
      this.handler = new Handler();

   }

   @SuppressLint("NewApi")
   @Override
   public void run() {
      // TODO Auto-generated method stub
      ArrayList<String[]> imageInfoList = request();
      if (imageInfoList != null) {
         fr = new FragmentAlbum(imageInfoList); // 프래그먼트에 이미지 목록 정보를 생성자
                                       // 파라미터로 전달하여 생성.
         FragmentTransaction ft = fm.beginTransaction();
         ft.replace(R.id.fragment_place, fr);
         ft.commit();
      }

   }

   public ArrayList<String[]> request() {
      String line = null;
      ArrayList<String[]> imageInfo = new ArrayList<String[]>();
      try {
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(GET_IMAGE_LIST_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute", "1000"));
         fields.add(new BasicNameValuePair("homeCode", homeCode));

         httpPost.setEntity(new UrlEncodedFormEntity(fields));

         HttpResponse response = client.execute(httpPost);
         InputStream inStream = response.getEntity().getContent();
         BufferedReader reader = new BufferedReader(new InputStreamReader(
               inStream));

         while (true) {
            line = reader.readLine();
            // System.out.println("getImageList:"+line);
            if (line == null) {
               break;
            } else {
               if (line.startsWith("200")) {
                  StringTokenizer st = new StringTokenizer(line, "|");
                  String[] simpleInfo = new String[4];
                  String code = st.nextToken();
                  // imageCode;
                  // imageName;
                  // imageWrittenDate;
                  // galleryCategoryCode;
                  simpleInfo[0] = st.nextToken();
                  simpleInfo[1] = st.nextToken();
                  simpleInfo[2] = st.nextToken();
                  simpleInfo[3] = st.nextToken();

                  imageInfo.add(simpleInfo);

                  // Toast.makeText(context.getApplicationContext(),
                  // "사진불러오기", Toast.LENGTH_LONG).show();
               } else if (line.startsWith("500")) {
                  imageInfo = null;
               }
            }
         }

      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
      return imageInfo;
   }

}