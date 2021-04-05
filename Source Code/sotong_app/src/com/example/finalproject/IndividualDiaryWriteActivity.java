package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.finalproject.webserver.WebServerIndividualDiaryWriteThread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class IndividualDiaryWriteActivity extends ActionBarActivity {

	public TextView individualDiaryWriteWriteDate;
	
	public EditText individualDiaryWriteTitle;
	public EditText individualDiaryWriteContent;
	
	public ImageButton individualDiaryWriteCompleteBtn;
	
	private String []userInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_diary_write);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		userInfo = bundle.getStringArray("userInfo");
		
		Date today = new Date();
		final String todayString = format(today);
		
		individualDiaryWriteWriteDate = (TextView)findViewById(R.id.individualDiaryWriteWriteDate);
		individualDiaryWriteWriteDate.setText("작성일자 : "+todayString);
		
		individualDiaryWriteTitle = (EditText)findViewById(R.id.individualDiaryWriteTitle);
		individualDiaryWriteContent = (EditText)findViewById(R.id.individualDiaryWriteContent);
		
		individualDiaryWriteCompleteBtn = (ImageButton)findViewById(R.id.individualDiaryWriteCompleteBtn);
		individualDiaryWriteCompleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String title = individualDiaryWriteTitle.getText().toString();
				String contents = individualDiaryWriteContent.getText().toString();
				
				WebServerIndividualDiaryWriteThread writeThread = new WebServerIndividualDiaryWriteThread(getApplicationContext(), userInfo[0], userInfo[1], title, todayString, contents, "ImageName1", todayString, "em1",IndividualDiaryWriteActivity.this);
				writeThread.start();
			}
		});
		

	}
	
	private String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}

	
}
