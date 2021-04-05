package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.webserver.WebServerEditWishThread;

public class EditWishActivity extends ActionBarActivity {

   EditText wishListTitle, wishListContents;
   ImageView samplePicture, setWishEnd, editPicture;
   ImageButton editWishBtn, editWishCancelBtn;
   ImageView editWishEndDate;
   TextView editWishListCompleteDate, wishWrittenDate;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_edit_wish);
      Intent intent = getIntent();
      final String memberCode = intent.getStringExtra("memberCode");
      final String homeCode = intent.getStringExtra("homeCode");
      final String wishCode = intent.getStringExtra("wishCode");
      String[] wishInfo = intent.getStringArrayExtra("wishInfo");
      // 0 위시코드
      // 1 별명
      // 2 소통컨텐츠코드
      // 3 위시제목
      // 4 위시 내용
      // 5 위시 이모티콘명
      // 6 위시 이모티콘경로
      // 7 위시 작성일자
      // 8 위시 만료일자
      // 9 위시 완료여부

      wishListTitle = (EditText) findViewById(R.id.editWishListTitle);
      wishListContents = (EditText) findViewById(R.id.editWishListContents);

      samplePicture = (ImageView) findViewById(R.id.samplePicture2);
      editWishBtn = (ImageButton) findViewById(R.id.editWish);
      editWishCancelBtn = (ImageButton) findViewById(R.id.editWishCancel);
      editWishEndDate = (ImageView) findViewById(R.id.editWishEndDate);
      editWishListCompleteDate = (TextView) findViewById(R.id.editWishListCompleteDate);
      wishWrittenDate = (TextView) findViewById(R.id.wishListWriteDate2);

      wishListTitle.setHint(wishInfo[3]);
      wishListContents.setHint(wishInfo[4]);
      editWishListCompleteDate.setText(wishInfo[8]);

      editWishEndDate.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            OnDateSetListener callBack = new OnDateSetListener() {
               public void onDateSet(DatePicker view, int year,
                     int monthOfYear, int dayOfMonth) {
                  String str = new SimpleDateFormat("yy-MM-dd")
                        .format(new Date(year - 1900, monthOfYear,
                              dayOfMonth));
                  editWishListCompleteDate.setText(str);

               }
            };
            Date date = new Date();
            new DatePickerDialog(EditWishActivity.this, callBack, date
                  .getYear() + 1900, date.getMonth(), date.getDate())
                  .show();
         }
      });
 
      editWishBtn.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            System.out.println(wishCode + "/" + memberCode + "/" + homeCode
                  + "/" + wishListTitle.getText().toString() + "/"
                  + wishListContents.getText().toString() + "/"
                  + "images/wish/pic9" + "/" + "em2" + "/"
                  + new SimpleDateFormat("yy-MM-dd").format(new Date())
                  + "/" + editWishListCompleteDate.getText().toString());
            WebServerEditWishThread thread = new WebServerEditWishThread(
                  EditWishActivity.this, wishCode, memberCode, homeCode,
                  wishListTitle.getText().toString(), wishListContents
                        .getText().toString(), "images/wish/pic9",
                  "em2", new SimpleDateFormat("yy-MM-dd")
                        .format(new Date()), editWishListCompleteDate
                        .getText().toString());
            thread.start();
         }
      });
      editWishCancelBtn.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
         }
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.edit_wish, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      if (id == R.id.action_settings) {
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
}