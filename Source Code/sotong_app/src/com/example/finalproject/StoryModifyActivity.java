package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.finalproject.webserver.WebServerStoryModifyThread;

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

public class StoryModifyActivity extends ActionBarActivity {

	
	public EditText storyModifyContents;
	public RadioGroup storyModifyOpenGroup;
	public RadioButton modifyRadio1;
	public RadioButton modifyRadio2;
	
	public ImageButton storyModifyRegisterBtn;
	
	private String []familyStory;
	private String []userInfo;
	
	private String setPublic;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_modify);
		
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		
		familyStory = bundle.getStringArray("familyStory");
		userInfo = bundle.getStringArray("userInfo");
		
		setPublic = "����";
		
		storyModifyContents = (EditText)findViewById(R.id.storyModifyContents);
		storyModifyContents.setText(familyStory[7]);
		
		storyModifyOpenGroup = (RadioGroup)findViewById(R.id.storyModifyOpenGroup);
		modifyRadio1 = (RadioButton)findViewById(R.id.modifyRadio1);
		modifyRadio2 = (RadioButton)findViewById(R.id.modifyRadio2);
		
		storyModifyOpenGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.modifyRadio1){
					setPublic = "����";
				}else if(checkedId == R.id.modifyRadio2){
					setPublic = "�̿�";
				}
			}
		});
		 
		storyModifyRegisterBtn = (ImageButton)findViewById(R.id.storyModifyRegisterBtn);
		storyModifyRegisterBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				WebServerStoryModifyThread modifyThread = new WebServerStoryModifyThread(getApplicationContext(), familyStory[0], format(new Date()), setPublic, familyStory[8], storyModifyContents.getText().toString(), familyStory[9], familyStory[10], "em1", StoryModifyActivity.this);
				modifyThread.start();
			}
		});
		
		
	}
	private String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd hh:mm");
	    String date = fmt.format(d);
	    return date;
	}

	
}
