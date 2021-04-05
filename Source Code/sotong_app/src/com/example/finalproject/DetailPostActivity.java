package com.example.finalproject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.webserver.WebServerDeleteLetterThread;

public class DetailPostActivity extends ActionBarActivity {

	public TextView postDetailInfoTitle;
	public TextView postDetailInfoReceiver;
	public TextView postDetailInfoContent;
	public TextView postDetailSenderInfo;
	
	public ImageButton postDetailInfoDeleteBtn;
	public ImageButton postDetailInfoWriteBtn;
	
	public String letterCode;
	
	public Handler handler;
	public String[] value;
	
	public ActionBarActivity actionBarActivity = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_post);
		
		Intent intent = getIntent();
		value = intent.getStringArrayExtra("postInfo");
		this.letterCode = intent.getStringExtra("letterCode");
		
		handler = new Handler();
		
		postDetailInfoTitle = (TextView)findViewById(R.id.postDetailInfoTitle);
		postDetailInfoReceiver = (TextView)findViewById(R.id.postDetailInfoReceiver);
		postDetailInfoContent = (TextView)findViewById(R.id.postDetailInfoContent);
		postDetailSenderInfo = (TextView)findViewById(R.id.postDetailSenderInfo);
		
		postDetailInfoDeleteBtn = (ImageButton)findViewById(R.id.postDetailInfoDeleteBtn);
		postDetailInfoWriteBtn = (ImageButton)findViewById(R.id.postDetailInfoWriteBtn);
		
		handler.post(new Runnable() {
			public void run() {
				postDetailInfoTitle.setText("To : "+value[0]);
				postDetailInfoReceiver.setText("[ 보낸날짜 : "+value[2]+" ]");
				postDetailInfoContent.setText(value[1]);
				postDetailSenderInfo.setText("[ 보낸 사람 : "+value[3]+" ]");
			}
		});
		
		
		postDetailInfoDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(actionBarActivity);
				builder.setMessage("정말로 삭제하시겠습니까?");
				builder.setTitle("삭제");
				
				builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						WebServerDeleteLetterThread deleteThread = new WebServerDeleteLetterThread(
	                              DetailPostActivity.this, DetailPostActivity.this, letterCode);
	                        deleteThread.start();
						
						Toast.makeText(actionBarActivity, "삭제합니다", Toast.LENGTH_SHORT).show();
						
					}
				});
				
				builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				builder.create().show();
			}
		});
		
		
	}

	
}
