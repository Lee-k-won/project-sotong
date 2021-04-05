package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.finalproject.webserver.WebServerIndividualDiaryModifyThread;

public class IndividualDiaryModifyActivity extends ActionBarActivity {

	public TextView individualDiaryModifyWriteDate;
	public EditText individualDiaryModifyTitle;
	public EditText individualDiaryModifyContent;
	
	public ImageButton individualDiaryModifyPictureBtn;
	public ImageButton individualDiaryModifyCompleteBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_diary_modify);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		final String []modifyDiaryInfo = bundle.getStringArray("modifyDiaryInfo");
		final String []userInfo = bundle.getStringArray("userInfo");
		
		individualDiaryModifyWriteDate = (TextView)findViewById(R.id.individualDiaryModifyWriteDate);
		individualDiaryModifyTitle = (EditText)findViewById(R.id.individualDiaryModifyTitle);
		individualDiaryModifyContent = (EditText)findViewById(R.id.individualDiaryModifyContent);
		
		individualDiaryModifyWriteDate.setText("�ۼ����� : "+modifyDiaryInfo[3]);
		individualDiaryModifyTitle.setText(modifyDiaryInfo[2]);
		individualDiaryModifyContent.setText(modifyDiaryInfo[5]);
		
		individualDiaryModifyPictureBtn = (ImageButton)findViewById(R.id.individualDiaryModifyPictureBtn);
		individualDiaryModifyCompleteBtn = (ImageButton)findViewById(R.id.individualDiaryModifyCompleteBtn);
		
		individualDiaryModifyCompleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				modifyDiaryInfo[2] = individualDiaryModifyTitle.getText().toString();
				modifyDiaryInfo[5] = individualDiaryModifyContent.getText().toString();
				
				Log.v("modify", ""+modifyDiaryInfo.length);
				Log.v("modify", ""+modifyDiaryInfo[0]); // ���̾�ڵ�
				Log.v("modify", ""+modifyDiaryInfo[1]); // �г���
				Log.v("modify", ""+modifyDiaryInfo[2]); // Ÿ��Ʋ
				Log.v("modify", ""+modifyDiaryInfo[3]); // �ۼ�����
				Log.v("modify", ""+modifyDiaryInfo[4]); // �����������ڵ�
				Log.v("modify", ""+modifyDiaryInfo[5]); // ����
				Log.v("modify", ""+modifyDiaryInfo[6]); // �̹�������
				Log.v("modify", ""+modifyDiaryInfo[7]); // �̹��� �ۼ���¥
				Log.v("modify", ""+modifyDiaryInfo[8]); // �̸�Ƽ�ܸ�
				Log.v("modify", ""+modifyDiaryInfo[9]); // �̸�Ƽ�ܰ��
				
				
				
				WebServerIndividualDiaryModifyThread modifyThread = new WebServerIndividualDiaryModifyThread(
						IndividualDiaryModifyActivity.this, userInfo[0],modifyDiaryInfo[0],modifyDiaryInfo[2],
						modifyDiaryInfo[5],modifyDiaryInfo[3],modifyDiaryInfo[4],modifyDiaryInfo[6],modifyDiaryInfo[7],
						"em1",IndividualDiaryModifyActivity.this);
				
				modifyThread.start();
				
				/*
				private Context context;
				private String memberCode;
				private String diaryCode;
				private String title;
				private String contents;
				private String writtenDate;
				private String sotongContentsCode;
				private String imageName; // ���
				private String imageWrittenDate;//myFormat
				private String emoticonCode; //em1 em2 em3
				
				private Handler handler;
				
				public ActionBarActivity actionBarActivity;*/
			}
		});
		
	}

	
}
