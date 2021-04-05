package com.example.finalproject;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

public class AlbumVersionAdapter extends BaseAdapter implements TitleProvider {
   private LayoutInflater mInflater;
   private static final String[] versions = { "1.5", "1.6", "2.1", "2.2",
         "2.3", "3.0", "x.y" };
   private static final String[] names = { "이야기앨범", "일기앨범" };

   // 태영 추가
   private ArrayList<String[]> imageInfoList;
   private int pageCnt;
   private int nowPage;
   ImageButton button1, button2;


   public AlbumVersionAdapter(Context context,
         ArrayList<String[]> imageInfoList) {
      mInflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      // 태영 추가
      this.imageInfoList = imageInfoList;

      
   }

   public int getCount() {
      return names.length;
   }

   public Object getItem(int position) {
      return position;
   }

   public long getItemId(int position) {
      return position;
   }

   public String getTitle(int position) {

      return names[position];
   }

   public View getView(int position, View convertView, ViewGroup parent) {

      if (convertView == null) {

         convertView = mInflater.inflate(R.layout.story_album, null);
         // final AlbumDialog albumDialog = new
         // AlbumDialog(convertView.getContext(),images);
         if (position == 0) {

           
         
         }

      }

      return convertView;
   }
/*
   public int getNowPage() {
      return this.nowPage;
   }

   public void setNextPage() {
      if (nowPage == pageCnt) {
         return;
      }
      this.nowPage += 1;
      return;
   }

   public void setPrevPage() {
      if (nowPage == 0) {
         return;
      }
      this.nowPage -= 1;
      return;
   }
*/
}