package com.example.finalproject;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.model.MyFamilyStory;
import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;
import com.example.finalproject.webserver.WebServerStoryDeleteThread;

public class MyFamilyStoryListAdapter extends BaseAdapter{

	public Context context;
	public ArrayList<MyFamilyStory> data;
	private String []userInfo;
	public LayoutInflater layoutInflater;
	public ActionBarActivity activity;
	public MyFamilyStoryListAdapter(Context context, ArrayList<MyFamilyStory> data, String []userInfo, ActionBarActivity activity) {
		super();
		this.context = context;
		this.data = data;
		this.userInfo = userInfo;
		this.layoutInflater = LayoutInflater.from(this.context);
		this.activity = activity;
	
	}
	
	public int getCount() {
	
		return data.size();
	}
	
	public Object getItem(int position) {
	
		return data.get(position);
	}
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		final int finalPosition = position;
		
		View itemLayout = layoutInflater.inflate(R.layout.family_story_form, null);
		
		ImageView myFamilyStoryProfilePicture = (ImageView)itemLayout.findViewById(R.id.myFamilyStoryProfilePicture);
		TextView myFamilyStoryNickName = (TextView)itemLayout.findViewById(R.id.myFamilyStoryNickName);
		TextView myFamilyStoryWrittenDate = (TextView)itemLayout.findViewById(R.id.myFamilyStoryWrittenDate);
		TextView myFamilyStoryContentsView = (TextView)itemLayout.findViewById(R.id.myFamilyStoryContentsView);
		ImageButton myFamilyStoryModifyBtn = (ImageButton)itemLayout.findViewById(R.id.myFamilyStoryModifyBtn); 
		ImageButton myFamilyStoryDeleteBtn = (ImageButton)itemLayout.findViewById(R.id.myFamilyStoryDeleteBtn);
		
		myFamilyStoryModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(data.get(finalPosition).getMemberCode().equals(userInfo[0])){
					String []familyStory = new String[17];
					familyStory[0] = data.get(finalPosition).getStoryCode();
					familyStory[1] = data.get(finalPosition).getFamilyHomeCode();
					familyStory[2] = data.get(finalPosition).getFamilyHomeName();
					familyStory[3] = data.get(finalPosition).getMemberCode();
					familyStory[4] = data.get(finalPosition).getMemberPhoto();
					familyStory[5] = data.get(finalPosition).getMemberColor();
					familyStory[6] = data.get(finalPosition).getMemberNickName();
					familyStory[7] = data.get(finalPosition).getContents();
					familyStory[8] = data.get(finalPosition).getSotongContentsCode();
					familyStory[9] = data.get(finalPosition).getImageName();
					familyStory[10] = data.get(finalPosition).getImageWrittenDate();
					familyStory[11] = data.get(finalPosition).getEmoticonName();
					familyStory[12] = data.get(finalPosition).getEmoticonRoute();
					familyStory[13] = data.get(finalPosition).getStoryDate();
					familyStory[14] = new String(""+(data.get(finalPosition).getStoryHeart()));
					familyStory[15] = data.get(finalPosition).getStoryModifyDate();
					familyStory[16] = data.get(finalPosition).getStoryScope();
						
					
					Intent intent = new Intent(context.getApplicationContext(),StoryModifyActivity.class);
					Bundle bundle = new Bundle();
					bundle.putStringArray("userInfo", userInfo);
					bundle.putStringArray("familyStory", familyStory);
					intent.putExtra("bundle", bundle);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
					
				}else{
					Toast.makeText(context, "수정할 권한이 없습니다", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		myFamilyStoryDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(data.get(finalPosition).getMemberCode().equals(userInfo[0])){
					//Toast.makeText(context, "삭제할 권한이 있습니다", Toast.LENGTH_SHORT).show();
					//AlertDialog.Builder builder = new Builder(context.getApplicationContext());
					
					AlertDialog.Builder builder = new Builder(activity);
					builder.setMessage("정말로 삭제하시겠습니까?");
					builder.setTitle("삭제");
					
					builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							WebServerStoryDeleteThread deleteThread = new WebServerStoryDeleteThread(context.getApplicationContext(), data.get(finalPosition).getStoryCode(), 
									data.get(finalPosition).getSotongContentsCode());
							deleteThread.start();
							
							/*
							WebServerStoryDeleteThread deleteThread = new WebServerStoryDeleteThread(context.getApplicationContext(), data.get(finalPosition).getStoryCode(), 
									data.get(finalPosition).getSotongContentsCode());
							deleteThread.start();
							*/
							
							/*
							 	private Context context;
								private String storyCode;
								private String sotongContentsCode;
	
								private Handler handler;
								public ActionBarActivity activity;
							 */
							
							
							//Toast.makeText(context.getApplicationContext(), "삭제합니다", Toast.LENGTH_SHORT).show();
							
						}
					});
					
					builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
					
					builder.create().show();
					
					
				}else{
					Toast.makeText(context, "삭제할 권한이 없습니다", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		new SimpleProfileDownloadImageTask(myFamilyStoryProfilePicture).execute(URLAddress.myUrl+"/final_so_tong/"+data.get(position).getMemberPhoto());
		myFamilyStoryNickName.setText(data.get(position).getMemberNickName());
		myFamilyStoryWrittenDate.setText(data.get(position).getStoryDate());
		myFamilyStoryContentsView.setText(data.get(position).getContents());
		
		return itemLayout;
	}
	
	
	
}
