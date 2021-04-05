package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finalproject.webserver.WebServerFamilyDiaryModifyThread;

public class FamilyDiaryDetailModifyActivity extends ActionBarActivity {

	public TextView familyDiaryDetailModifyWriteDate;
	public EditText familyDiaryDetailModifyContent;
	
	public ImageButton familyDiaryDetailModifyPictureBtn;
	public ImageButton familyDiaryDetailModifyCompleteBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_diary_detail_modify);
	
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		
		String []userInfo = bundle.getStringArray("userInformation");
		for(int ct=0; ct<userInfo.length; ct++){
			Log.v("Test55", userInfo[ct]);
		}
		
		final String []modify = bundle.getStringArray("modify");
		
		//String todayString = new String(year.substring(1)+"-"+(today.getMonth()+1)+"-"+today.getDate());
		familyDiaryDetailModifyWriteDate = (TextView)findViewById(R.id.familyDiaryDetailModifyWriteDate);
		familyDiaryDetailModifyWriteDate.setText("작성일자 : "+modify[3]);
		
		familyDiaryDetailModifyContent = (EditText)findViewById(R.id.familyDiaryDetailModifyContent);
		familyDiaryDetailModifyContent.setText(modify[5]);
		
		familyDiaryDetailModifyPictureBtn = (ImageButton)findViewById(R.id.familyDiaryDetailModifyPictureBtn);
		familyDiaryDetailModifyCompleteBtn = (ImageButton)findViewById(R.id.familyDiaryDetailModifyCompleteBtn);
			
		familyDiaryDetailModifyCompleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				String modifyContents = familyDiaryDetailModifyContent.getText().toString();
				WebServerFamilyDiaryModifyThread modifyThread = new WebServerFamilyDiaryModifyThread(
						getApplicationContext(), modify[4], modifyContents , modify[8], modify[9], "em1", FamilyDiaryDetailModifyActivity.this);
				
				modifyThread.start();
			}
		}); 
		
	}
	private String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
	
}
