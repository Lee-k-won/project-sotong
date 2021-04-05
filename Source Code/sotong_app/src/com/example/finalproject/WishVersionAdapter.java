package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.taptwo.android.widget.TitleProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.webserver.WebServerDeleteWishThread;
import com.example.finalproject.webserver.WebServerFinishWishThread;

public class WishVersionAdapter extends BaseAdapter implements TitleProvider{
   private LayoutInflater mInflater;
   //태영추가
   private Context context;
   private String homeCode;
   private String memberCode;
   //
   private String []wishes;
   private ArrayList<String[]> wishList;
   
   public WishVersionAdapter(Context context, String[] wishes,ArrayList<String[]> wishList,String memberCode,String homeCode){
      mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      this.wishes = wishes;
      //태영 추가
      this.context=context;
      this.wishList=wishList;
      //2
      this.memberCode=memberCode;
      this.homeCode=homeCode;
   }   
   public int getCount() {
      return wishes.length;
   }

   public Object getItem(int position) {
      
      return position;
   }   
   
   public long getItemId(int position) {
      
      return position;
   }
   
   public String getTitle(int position) {
   
      return wishes[position];
   }
   
   public View getView(int position, View convertView, ViewGroup parent) {
      
      if(convertView == null){
         convertView = mInflater.inflate(R.layout.wish_list_form, null);
      }
         //태영 추가
         //0 위시코드
         //1 별명
         //2 소통컨텐츠코드
         //3 위시제목
         //4 위시 내용
         //5 위시 이모티콘명
         //6 위시 이모티콘경로
         //7 위시 작성일자
         //8 위시 만료일자
         //9 위시 완료여부
         
         if(wishList != null){
         
         ImageView addWishButton=(ImageView)convertView.findViewById(R.id.wishListRegister);
         ImageView editWishButton=(ImageView)convertView.findViewById(R.id.wishListUpdate);
         ImageView deleteWishButton=(ImageView)convertView.findViewById(R.id.WishListDelete);
         ImageView finishWishButton=(ImageView)convertView.findViewById(R.id.WishListFinish);
         
         
         TextView wishListTitle=(TextView)convertView.findViewById(R.id.wishListTitle);
         TextView wishListContents=(TextView)convertView.findViewById(R.id.wishListContents);
         TextView wishListWriter=(TextView)convertView.findViewById(R.id.wishListWriter);
         TextView wishListWriteDate=(TextView)convertView.findViewById(R.id.wishListWriteDate);
         TextView wishListCompleteDate=(TextView)convertView.findViewById(R.id.wishListCompleteDate);
         
         ImageView isFinished=(ImageView)convertView.findViewById(R.id.isFinishedCheck);
         
         final AlertDialog.Builder dialog=new AlertDialog.Builder(context);
         
         
         final String wishCode=wishList.get(position)[0];
         final String[] wishInfo=wishList.get(position);
         
         wishListTitle.setText(wishList.get(position)[3]);
         wishListWriter.setText(wishList.get(position)[1]);
         wishListContents.setText(wishList.get(position)[4]);
         wishListWriteDate.setText(wishList.get(position)[7]);
         wishListCompleteDate.setText("만료일 : "+wishList.get(position)[8]);
         if(wishList.get(position)[9].equals("1"))
         {
            
            isFinished.setVisibility(View.VISIBLE);
            finishWishButton.setVisibility(View.GONE);
         }
         else
         {
            isFinished.setVisibility(View.GONE);
            finishWishButton.setVisibility(View.VISIBLE);
         }
         final Intent intent=new Intent();
         
         intent.putExtra("homeCode",homeCode);
         intent.putExtra("memberCode", memberCode);
         
         addWishButton.setOnClickListener(new OnClickListener() {   
            @Override
            public void onClick(View v) {
               // TODO Auto-generated method stub
               
               intent.setClass(context,AddWishActivity.class);
               context.startActivity(intent);
               
            }
         });
         editWishButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               // TODO Auto-generated method stub
               if(wishInfo[9].equals("1"))
               {
                  new AlertDialog.Builder(context)
                           .setTitle("수정 오류")
                           .setMessage("이미 완료된 소망글입니다")
                           .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                     
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                 // TODO Auto-generated method stub
                                    dialog.dismiss();
                                 }
                              })
                              .setCancelable(false)
                              .show();             
                  return;                  
               }
               intent.putExtra("wishCode", wishInfo[0]);
               intent.putExtra("wishInfo", wishInfo);
               intent.setClass(context, EditWishActivity.class);
               context.startActivity(intent);
            }
         });
         deleteWishButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               // TODO Auto-generated method stub
               
               
               dialog.setTitle("위시 삭제 확인")
                     .setMessage("해당 위시를 정말 삭제하시겠습니까?")
                     .setCancelable(false)
                     .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // TODO Auto-generated method stub
                           
                           WebServerDeleteWishThread thread=new WebServerDeleteWishThread(context,wishCode);
                           thread.start();
                           //dialog.dismiss();
                           
                        }
                     })
                     .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // TODO Auto-generated method stub
                           dialog.dismiss();
                        }
                     })
                     .show();
            }
         });
         
         finishWishButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               // TODO Auto-generated method stub
               dialog.setTitle("소망 완료 확인")
               .setMessage("해당 소망이 성취되셨습니까?")
               .setCancelable(false)
               .setPositiveButton("예", new DialogInterface.OnClickListener() {
                  
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                     // TODO Auto-generated method stub
                     
                     WebServerFinishWishThread thread2=new WebServerFinishWishThread(context,wishCode,new SimpleDateFormat("yy-MM-dd").format(new Date()));
                     thread2.start();
                     
                     
                  }
               })
               .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                  
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                     // TODO Auto-generated method stub
                     dialog.dismiss();
                  }
               })
               .show();
            }
         });
         
         
      }
      
      
      
      return convertView;
   }
   
}