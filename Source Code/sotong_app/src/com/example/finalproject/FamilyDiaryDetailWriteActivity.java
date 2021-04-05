package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.finalproject.webserver.WebServerFamilyDiaryWriteThread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class FamilyDiaryDetailWriteActivity extends ActionBarActivity {

	public TextView familyDiaryDetailWriteWriteDate;
	public EditText familyDiaryDetailWriteContent;
	public ImageButton familyDiaryDetailWriteCompleteBtn;
	public ImageButton familyDiaryDetailWritePictureBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_diary_detail_write);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		final String []userInfo = bundle.getStringArray("userInfo");
		
		familyDiaryDetailWriteCompleteBtn = (ImageButton)findViewById(R.id.familyDiaryDetailWriteCompleteBtn);
		familyDiaryDetailWritePictureBtn = (ImageButton)findViewById(R.id.familyDiaryDetailWritePictureBtn);
		familyDiaryDetailWriteContent = (EditText)findViewById(R.id.familyDiaryDetailWriteContent);	
		
		
		final String todayString = format(new Date());
		familyDiaryDetailWriteWriteDate = (TextView)findViewById(R.id.familyDiaryDetailWriteWriteDate);
		familyDiaryDetailWriteWriteDate.setText("작성일자 : "+todayString);
		
		
		familyDiaryDetailWriteCompleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String contents = familyDiaryDetailWriteContent.getText().toString();
				
				WebServerFamilyDiaryWriteThread writeThread = 
						new WebServerFamilyDiaryWriteThread(getApplicationContext(), userInfo[1], userInfo[0], todayString, contents, "imageNameTest", todayString, "em1", FamilyDiaryDetailWriteActivity.this);
				writeThread.start();
				
				/*
				this.context = context;
				this.homeCode = homeCode;
				this.memberCode = memberCode;
				this.familyDiaryPartDate = familyDiaryPartDate;
				this.contents = contents;
				this.imageName = imageName;
				this.imageWrittenDate = imageWrittenDate;
				this.emoticonCode = emoticonCode;
				this.activity = activity;
				this.handler = new Handler();
				 */
			}
		});
		
	}
	private String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
	
}
