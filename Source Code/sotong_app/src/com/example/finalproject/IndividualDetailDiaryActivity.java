package com.example.finalproject;

import com.example.finalproject.webserver.WebServerIndividualDiaryDeleteThread;

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

public class IndividualDetailDiaryActivity extends ActionBarActivity {

	public TextView individualDiaryDetailWriteDate;
	public TextView individualDiaryDetailTitle;
	public TextView individualDiaryDetailContent;
	
	public ImageButton individualDiaryDetailDeleteBtn;
	public ImageButton individualDiaryDetailModifyBtn;
	public ImageButton individualDiaryDetailWriteBtn;
	
	public Handler handler;
	public ActionBarActivity actionBarActivity = this;
	
	private String []diaryInfo = null;
	private String []userInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_detail_diary);
		
		handler = new Handler();
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		diaryInfo = bundle.getStringArray("diaryInfo"); 
		userInfo = bundle.getStringArray("userInfo");
		
		/*
		 Intent intent = new Intent(context.getApplicationContext(),IndividualDetailDiaryActivity.class);
			Bundle bundle = new Bundle();
			bundle.putStringArray("diaryInfo", diaryInfo);
			intent.putExtra("bundle", bundle);
			context.startActivity(intent);
		 */
		
		individualDiaryDetailWriteDate = (TextView)findViewById(R.id.individualDiaryDetailWriteDate);
		individualDiaryDetailTitle = (TextView)findViewById(R.id.individualDiaryDetailTitle);
		individualDiaryDetailContent = (TextView)findViewById(R.id.individualDiaryDetailContent);
		
		handler.post(new Runnable() {
			public void run() {
				individualDiaryDetailWriteDate.setText("�ۼ����� : "+diaryInfo[3]);
				individualDiaryDetailTitle.setText(diaryInfo[2]);
				individualDiaryDetailContent.setText(diaryInfo[5]);
			}
		});
		
		/*
		
		handler.post(new Runnable() {
			public void run() {
				individualDiaryDetailWriteDate.setText("�ۼ����� : "+value[2]);
				individualDiaryDetailTitle.setText(value[0]);
				individualDiaryDetailContent.setText(value[1]);
				
			}
		});
		*/
		
		/*
		detailData = new ArrayList<IndividualDiaryInfo>();
		detailData.add(new IndividualDiaryInfo("���ִ� ��", "���󿡴� �� ���ִ� �� ����", new Date(2015,7,23)));
		detailData.add(new IndividualDiaryInfo("�б�", "�б��� ���ư��� �ʹ�\n�װ����� �� ���� ����", new Date(2015,7,22)));
		detailData.add(new IndividualDiaryInfo("�ູ�̶�", "�ູ�ϰ� �ʹ�", new Date(2015,7,21)));
		 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context.getApplicationContext(),IndividualDetailDiaryActivity.class);
				 
				String value[] = new String[3];
				value[0] = detailData.get(position).getIndiDiaryTitle();
				value[1] = detailData.get(position).getIndiDiaryContent();
				value[2] = detailData.get(position).getIndiDiaryDateToString();
				intent.putExtra("individualDiaryInfo", value);
				
				startActivity(intent);
				
			}
		});
		
		*/
		/*
		
		handler = new Handler();
		Intent intent = getIntent();
		final String []value = intent.getStringArrayExtra("individualDiaryInfo");
		*/
		
		individualDiaryDetailDeleteBtn = (ImageButton)findViewById(R.id.individualDiaryDetailDeleteBtn);
		individualDiaryDetailModifyBtn = (ImageButton)findViewById(R.id.individualDiaryDetailModifyBtn);
		individualDiaryDetailWriteBtn = (ImageButton)findViewById(R.id.individualDiaryDetailWriteBtn);
		
		individualDiaryDetailDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(actionBarActivity);
				builder.setMessage("������ �����Ͻðڽ��ϱ�?");
				builder.setTitle("����");
				
				builder.setPositiveButton("��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						WebServerIndividualDiaryDeleteThread deleteThread = new WebServerIndividualDiaryDeleteThread(getApplicationContext(), diaryInfo[0], diaryInfo[4]);
						deleteThread.start();
						//Toast.makeText(actionBarActivity, "�����մϴ�", Toast.LENGTH_SHORT).show();
						(IndividualDetailDiaryActivity.this).finish();
					}
				});
				
				builder.setNegativeButton("�ƴϿ�", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				builder.create().show();
			}
		});
		
		individualDiaryDetailModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(actionBarActivity.getApplicationContext(),IndividualDiaryModifyActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArray("modifyDiaryInfo", diaryInfo);
				bundle.putStringArray("userInfo", userInfo);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
			}
		});
		
		
		individualDiaryDetailWriteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),IndividualDiaryWriteActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArray("userInfo", userInfo);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
			}
		});
	
		
	}

	
}
