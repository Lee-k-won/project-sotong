package com.example.finalproject;

import java.util.ArrayList;

import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class AlbumDialog extends Dialog {
   private Gallery album;
   private Button exitBtn;
   private Context context;
   private ArrayList<String[]> imageInfoList;
   private int start;
   private ImgAdapter imageAdapter;
   public AlbumDialog(Context context, ArrayList<String[]> imageInfoList,int start) {
      super(context);
      this.context = context;
      this.imageInfoList = imageInfoList;
      this.imageAdapter=new ImgAdapter(this.context);
      this.start=start;
   
   
   }
   

   protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      
      setContentView(R.layout.album_view);
      
      album=((Gallery) findViewById(R.id.gallery1));
      album.setAdapter(imageAdapter);
      album.setSelection(start);
      exitBtn = (Button) findViewById(R.id.exitBtn);

      exitBtn.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            dismiss();
         }
      });

   }
   public void setStart(int start)
   {
      this.start=start;
   }
   
   class ImgAdapter extends BaseAdapter {
      private final Context context;
      public LayoutInflater inflater;
      
      public ImgAdapter(Context c) {
         
         context = c;
         inflater = (LayoutInflater) context
               .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
      }
      
      public int getCount() {
         return imageInfoList.size();
      }

      public Object getItem(int position) {

         return imageInfoList.get(position);
      }

      public long getItemId(int position) {

         return position;
      }
      public View getView(int position, View convertView, ViewGroup parent) {
         if (convertView == null) {
            convertView = inflater.inflate(R.layout.album_item, parent,
                  false);
         }
         
         
         ImageView imgView = (ImageView) convertView
               .findViewById(R.id.imgGalView1);
         
         //imgView.setImageResource(images.get(position));
         
         
         new SimpleProfileDownloadImageTask(imgView).execute(URLAddress.myUrl+"/final_so_tong/"+imageInfoList.get(position)[1]+".jpg".trim());
         // 바꾸고싶은 이미지뷰 이름. //이미지 전체 경로.
         
         return convertView;
      }
   }



   

}