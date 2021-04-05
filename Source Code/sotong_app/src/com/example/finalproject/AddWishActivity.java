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

import com.example.finalproject.webserver.WebServerAddWishThread;

public class AddWishActivity extends ActionBarActivity {

   EditText wishListTitle, wishListContents;
   TextView wishEndDate;
   ImageView samplePicture, setWishEnd, addPicture;
   ImageButton addWishBtn, addWishCancelBtn;

   String endDate;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add_wish);
      Intent intent = getIntent();
      final String memberCode = intent.getStringExtra("memberCode");
      final String homeCode = intent.getStringExtra("homeCode");

      wishListTitle = (EditText) findViewById(R.id.wishListTitle);
      wishListContents = (EditText) findViewById(R.id.wishListContents);
      wishEndDate = (TextView) findViewById(R.id.wishListCompleteDate);
      samplePicture = (ImageView) findViewById(R.id.samplePicture1);
      setWishEnd = (ImageView) findViewById(R.id.setWishEnd);
      addWishBtn = (ImageButton) findViewById(R.id.addWish);
      addWishCancelBtn = (ImageButton) findViewById(R.id.addWishCancel);

      setWishEnd.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            OnDateSetListener callBack = new OnDateSetListener() {
               public void onDateSet(DatePicker view, int year,
                     int monthOfYear, int dayOfMonth) {
                  String str = new SimpleDateFormat("yy-MM-dd")
                        .format(new Date(year - 1900, monthOfYear,
                              dayOfMonth));
                  wishEndDate.setText(str);
                  endDate = wishEndDate.getText().toString();
               }
            };
            Date date = new Date();
            new DatePickerDialog(AddWishActivity.this, callBack, date
                  .getYear() + 1900, date.getMonth(), date.getDate())
                  .show();
         }
      });

      addWishCancelBtn.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
         }
      });
      addWishBtn.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            String wishWrittenDate = new SimpleDateFormat("yy-MM-dd")
                  .format(new Date());

            WebServerAddWishThread thread = new WebServerAddWishThread(
                  AddWishActivity.this, memberCode, homeCode,
                  wishListTitle.getText().toString(), wishListContents
                        .getText().toString(), "images/wish/pic9",
                  "em1", wishWrittenDate, endDate);
            thread.start();
         }
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.add_wish, menu);
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