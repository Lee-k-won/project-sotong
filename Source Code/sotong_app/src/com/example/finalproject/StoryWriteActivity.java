package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.finalproject.webserver.WebServerStoryWriteThread;

public class StoryWriteActivity extends ActionBarActivity {

	public ImageButton storyWriteRegisterBtn;
	public ImageButton storyWriteCancelBtn;
	
	public EditText storyWriteContents;
	public RadioGroup storyWriteOpenGroup;
	public RadioButton radio1;
	public RadioButton radio2;
	
	private String setPublic;
	
	//public ImageButton storyWriteImoticonBtn;
	//public ImageButton storyWriteAlbumBtn;
	
	private String []userInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_write);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		userInfo = bundle.getStringArray("userInfo");
		
		
		setPublic = "가족";
		
		storyWriteRegisterBtn = (ImageButton)findViewById(R.id.storyWriteRegisterBtn);
		storyWriteRegisterBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Log.v("Contents", storyWriteContents.getText().toString());
				//Log.v("RadioButton", setPublic);
				//Log.v("current time", format(new Date()));
				
				WebServerStoryWriteThread writeThread = new WebServerStoryWriteThread(getApplicationContext(), userInfo[1], userInfo[0], storyWriteContents.getText().toString(), format(new Date()), "myImageRoute", "em1", setPublic, StoryWriteActivity.this);
				writeThread.start();
			}
		});
		
		
		storyWriteContents = (EditText)findViewById(R.id.storyWriteContents);
		storyWriteOpenGroup = (RadioGroup)findViewById(R.id.storyWriteOpenGroup);
		radio1 = (RadioButton)findViewById(R.id.radio1);
		radio2 = (RadioButton)findViewById(R.id.radio2);
		
		
		
		storyWriteOpenGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.radio1){
					setPublic = "가족";
				}else if(checkedId == R.id.radio2){
					setPublic = "이웃";
				}
			}
		});
		
		
	}
	private String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd hh:mm");
	    String date = fmt.format(d);
	    return date;
	}

	
}
