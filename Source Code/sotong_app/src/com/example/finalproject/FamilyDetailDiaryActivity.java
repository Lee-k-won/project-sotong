package com.example.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalproject.model.FamilyDiaryDetail;
import com.example.finalproject.webserver.WebServerFamilyDiaryDeleteThread;

public class FamilyDetailDiaryActivity extends ActionBarActivity {

	
	public ListView familyDetailDiaryListView;
	public FamilyDetailVersionAdapter adapter;
	
	public ImageButton familyDiaryDetailDeleteBtn;
	public ImageButton familyDiaryDetailModifyBtn;
	public ImageButton familyDiaryDetailWriteBtn;
	
	public ArrayList<FamilyDiaryDetail> data = null;
	public ActionBarActivity actionBarActivity = this;
	
	private String []familyDiaryCommonInfo;
	private String []userInfo;
	
	private boolean check=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_detail_diary);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		
		familyDiaryCommonInfo = bundle.getStringArray("familyDiaryCommonInfo");
		userInfo = bundle.getStringArray("userInfo");
		
		
		for(int cnt=0; cnt<userInfo.length; cnt++){
			Log.v("Test33", userInfo[cnt]);
		}
		
		
		int familyCnt = intent.getIntExtra("familyCnt", 0);
		
		data = new ArrayList<FamilyDiaryDetail>();
		
		for(int cnt=1; cnt<=familyCnt; cnt++){
			String partInfo[] = bundle.getStringArray("partInfo"+cnt);
			
			data.add(new FamilyDiaryDetail(partInfo[0], partInfo[1], partInfo[2], partInfo[3], partInfo[4], partInfo[5], partInfo[6], partInfo[7], partInfo[8], partInfo[9]));
			//data.add(new FamilyDiaryDetail(familyDiaryPartCode, memberNickName, familyDiaryPartDate, sotongContentsCode, contents, emoticonName, emoticonRoute, imageName, imageWrittenDate))
		}
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		adapter = new FamilyDetailVersionAdapter(FamilyDetailDiaryActivity.this, data);
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		familyDetailDiaryListView.setAdapter(adapter);
		/*
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		data = new ArrayList<FamilyDiaryDetail>();
		data.add(new FamilyDiaryDetail("장재영", "@drawable/brad", "오늘 즐거운 식사였습니다\n안녕히주무세요", new Date(2015,7,23)));
		data.add(new FamilyDiaryDetail("이경원", "@drawable/brad", "오늘 즐거운 만찬이였습니다\n안녕히주무세요", new Date(2015,7,22)));
		data.add(new FamilyDiaryDetail("김철연", "@drawable/brad", "오늘도 즐거운 하루였습니다\n안녕히주무세요", new Date(2015,7,22)));
		
		adapter = new FamilyDetailVersionAdapter(FamilyDetailDiaryActivity.this, data);
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		familyDetailDiaryListView.setAdapter(adapter);
		*/
		/*
		 	ArrayList<String[]> list = request();
		
		if(list != null){
			//ArrayList<FamilyDiaryDetail> data = new ArrayList<FamilyDiaryDetail>();
			String []familyDiaryCommonInfo = list.get(0);
	
			Bundle bundle = new Bundle();
			bundle.putStringArray("familyDiaryCommonInfo", familyDiaryCommonInfo);
			
			
			for(int cnt=1; cnt<list.size(); cnt++){
				bundle.putStringArray("partInfo"+cnt, list.get(cnt));
			}
			
			Intent intent = new Intent(context.getApplicationContext(), FamilyDetailDiaryActivity.class);
			intent.putExtra("familyCnt", list.size()-1);
			intent.putExtra("bundle", bundle);
			
			context.startActivity(intent);
		 */
		
		
		familyDiaryDetailDeleteBtn = (ImageButton)findViewById(R.id.familyDiaryDetailDeleteBtn);
		familyDiaryDetailModifyBtn = (ImageButton)findViewById(R.id.familyDiaryDetailModifyBtn);
		familyDiaryDetailWriteBtn = (ImageButton)findViewById(R.id.familyDiaryDetailWriteBtn);
		
		familyDiaryDetailDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(actionBarActivity);
				builder.setMessage("정말로 삭제하시겠습니까?");
				builder.setTitle("삭제");
				
				builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						WebServerFamilyDiaryDeleteThread deleteThread = new WebServerFamilyDiaryDeleteThread(getApplicationContext(), userInfo[0], familyDiaryCommonInfo[0], FamilyDetailDiaryActivity.this);
						deleteThread.start();
						
						/*
						 this.context = context;
					     this.memberCode = memberCode;
		                 this.familyDiaryCode = familyDiaryCode;
						 this.activity = activity;
		 				 this.handler = new Handler();
						 */
						
						//Toast.makeText(actionBarActivity, "삭제합니다", Toast.LENGTH_SHORT).show();
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
		
		familyDiaryDetailWriteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),FamilyDiaryDetailWriteActivity.class);
				Bundle bundle = new Bundle();
				bundle.putStringArray("userInfo", userInfo);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
			}
		});
		
		familyDiaryDetailModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//원래는 코드 비교를 해야 하는데.. 지금은 닉네임으로 비교함!!
				for(int cnt=0; cnt<data.size(); cnt++){
					if(data.get(cnt).getMemberNickName().equals(userInfo[8])){
						check = true;
						String []modify = new String[10];
							
						modify[0] = data.get(cnt).getFamilyDiaryPartCode();
						modify[1] = data.get(cnt).getMemberNickName();
						modify[2] = data.get(cnt).getMemberPhoto();
						modify[3] = data.get(cnt).getFamilyDiaryPartDate();
						modify[4] = data.get(cnt).getSotongContentsCode();   //
						modify[5] = data.get(cnt).getContents();             //
						modify[6] = data.get(cnt).getEmoticonName();         // e code
						modify[7] = data.get(cnt).getEmoticonRoute();
						modify[8] = data.get(cnt).getImageName();            //
						modify[9] = data.get(cnt).getImageWrittenDate();     // 
						
						Intent intent = new Intent(getApplicationContext(),	FamilyDiaryDetailModifyActivity.class);
						Bundle bundle = new Bundle();
						bundle.putStringArray("modify", modify);
						bundle.putStringArray("userInformation", userInfo);
						intent.putExtra("bundle", bundle);
						
						for(int ct=0; ct<userInfo.length; ct++){
							Log.v("Test44", userInfo[ct]);
						}
						
						
						startActivity(intent);
						
						
					}
				}
				if(check==false){
					Toast.makeText(getApplicationContext(), "오늘 작성한 일기가 없습니다", Toast.LENGTH_SHORT).show();
				}
				//Intent intent = new Intent(getApplicationContext(),FamilyDiaryDetailModifyActivity.class);
				
				
				
				//startActivity(intent);
			}
		});
		/*
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		data = new ArrayList<FamilyDiaryDetail>();
		data.add(new FamilyDiaryDetail("장재영", "@drawable/brad", "오늘 즐거운 식사였습니다\n안녕히주무세요", new Date(2015,7,23)));
		data.add(new FamilyDiaryDetail("이경원", "@drawable/brad", "오늘 즐거운 만찬이였습니다\n안녕히주무세요", new Date(2015,7,22)));
		data.add(new FamilyDiaryDetail("김철연", "@drawable/brad", "오늘도 즐거운 하루였습니다\n안녕히주무세요", new Date(2015,7,22)));
		
		adapter = new FamilyDetailVersionAdapter(FamilyDetailDiaryActivity.this, data);
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		familyDetailDiaryListView.setAdapter(adapter);
		*/
		
	}

	
}
