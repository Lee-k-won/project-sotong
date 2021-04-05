package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.finalproject.model.PostInfo;
import com.example.finalproject.model.SimplePostInfo;
import com.example.finalproject.webserver.WebServerGetDetailLetterInfoThread;

public class PostBoxActivity extends ActionBarActivity {

	public ArrayList<SimplePostInfo> data = null;
	public ArrayList<PostInfo> detailData = null;
	public ListView listView = null;
	public SimplePostInfoListAdapter adapter = null;
	public ActionBarActivity postBoxActivity = this;
	
	public ImageButton postBoxWriteBtn;
	public ImageButton postBoxDeleteBtn;
	
	public ActionBarActivity actionBarActivity = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_box);
		
		Intent intent = getIntent();
	    final Bundle bundle = intent.getBundleExtra("SimpleLetterInfos");
	    final int letterCount = intent.getIntExtra("letterCount", 0);
		
	    final String memberCode=intent.getStringExtra("memberCode");
	    final String homeCode=intent.getStringExtra("homeCode");
	    final ArrayList<String[]> familyInfoList =(ArrayList<String[]>)intent.getSerializableExtra("familyInfoList");
	    
		postBoxWriteBtn = (ImageButton)findViewById(R.id.postBoxWriteButton);
		postBoxDeleteBtn = (ImageButton)findViewById(R.id.postBoxDeleteButton);
		postBoxWriteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(actionBarActivity.getApplicationContext(),PostWriteActivity.class);
				
				intent.putExtra("familyInfoList", familyInfoList);
	            intent.putExtra("memberCode", memberCode);
	            intent.putExtra("homeCode", homeCode);
				
				startActivity(intent);
				
				
			}
		}); 
		
		postBoxDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		
		
		
		data = new ArrayList<SimplePostInfo>();
		
		/*
		data.add(new SimplePostInfo("사랑하는 아들","엄마",new Date(2015,7,25)));
		data.add(new SimplePostInfo("사랑하는 형","동생",new Date(2015,7,23)));
		data.add(new SimplePostInfo("사랑하는 동생","형",new Date(2015,7,24)));
		*/
		
		for (int i = 0; i < letterCount; i++) {
	         String[] str = bundle.getStringArray("simpleLetterInfo" + (i + 1));
	         Date date = null;
	         try {
	            date = new SimpleDateFormat("yy-MM-dd").parse(str[1]);

	         }catch (Exception e) {
	        	 e.printStackTrace();
	         }
	         System.out.println(date);
	         data.add(new SimplePostInfo(str[0], str[2], date));
	      }
		
		// data가 없을때
		
		if(letterCount != 0){
			adapter = new SimplePostInfoListAdapter(postBoxActivity, data);
			listView = (ListView)findViewById(R.id.postListView);
			listView.setAdapter(adapter);
			
			
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					
					String[] str = bundle.getStringArray("simpleLetterInfo"+(position+1));
			        WebServerGetDetailLetterInfoThread detailInfoThread = new WebServerGetDetailLetterInfoThread(
			                 PostBoxActivity.this, PostBoxActivity.this, str[3]);
			        detailInfoThread.start();
					
					//Intent intent = new Intent(actionBarActivity.getApplicationContext(),DetailPostActivity.class);
					/*
					String value[] = new String[4];
					value[0] = detailData.get(position).getPostTitle();
					value[1] = detailData.get(position).getPostContent();
					value[2] = detailData.get(position).getPostDateToString();
					value[3] = detailData.get(position).getPostWriter();
					intent.putExtra("postInfo", value);
					*/
					//startActivity(intent);
				}
			});
		}
		/*
		detailData = new ArrayList<PostInfo>();
		
		detailData.add(new PostInfo("사랑하는 아들","사랑하는 아들아 !! 힘내", new Date(2015,7,25), "엄마"));
		detailData.add(new PostInfo("사랑하는 형","사랑하는 형 !! 힘내", new Date(2015,7,23), "동생"));
		detailData.add(new PostInfo("사랑하는 동생","사랑하는 동생 !! 힘내", new Date(2015,7,24), "형"));
		*/
		
		
	}

	
}
