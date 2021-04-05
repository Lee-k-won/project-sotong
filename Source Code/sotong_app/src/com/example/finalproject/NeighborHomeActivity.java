package com.example.finalproject;

import java.util.ArrayList;

import com.example.finalproject.model.SimpleFamilyInfo;
import com.example.finalproject.webserver.WebServerFamilyProfileRequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class NeighborHomeActivity extends ActionBarActivity {

	public TextView neighborHomeShowHomeName;
	public ListView neighborHomeShowListView;
	
	public ArrayList<SimpleFamilyInfo> simpleData = null;
	public SimpleNeighborHomeInfoListAdapter simpleNeighborHomeInfoListAdapter=null;
	
	private String homeName;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neighbor_home);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		int neighborCnt = intent.getIntExtra("neighborCnt",	0);
		homeName = intent.getStringExtra("homeName");
		
		simpleData = new ArrayList<SimpleFamilyInfo>();
		
		for(int cnt=0; cnt<neighborCnt; cnt++){
			String []neighborInfo = bundle.getStringArray("neighborInfo"+(cnt+1));
			simpleData.add(new SimpleFamilyInfo(neighborInfo[0], neighborInfo[4], neighborInfo[1], neighborInfo[2]));
			
			//"http://192.168.0.20:8089/final_so_tong/"
		}
		
		simpleNeighborHomeInfoListAdapter = new SimpleNeighborHomeInfoListAdapter(getApplicationContext(), simpleData);
		neighborHomeShowHomeName = (TextView)findViewById(R.id.neighborHomeShowHomeName);
		neighborHomeShowHomeName.setText(homeName);
		neighborHomeShowListView = (ListView)findViewById(R.id.neighborHomeShowListView);
		
		neighborHomeShowListView.setAdapter(simpleNeighborHomeInfoListAdapter);
		
		
		/*
		 simpleFamilyInfoListAdapter = new SimpleFamilyInfoListAdapter(getActivity(), simpleData);
		familyHomeShowListView = (ListView)rootView.findViewById(R.id.familyHomeShowListView);
		familyHomeShowListView.setAdapter(simpleFamilyInfoListAdapter);
		
		familyHomeShowListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				WebServerFamilyProfileRequest requestThread = new WebServerFamilyProfileRequest(
						context, simpleData.get(position).getMemberCode());
				requestThread.start();
				*/
				/*
				Intent intent = new Intent(context.getApplicationContext(),FamilyProfileInfoActivity.class);
				
				Bundle bundle = new Bundle();
				bundle.putStringArray("userInfo", userInfo);
				bundle.putString("detailProfileCode", simpleData.get(position).getMemberCode());
				intent.putExtra("bundleData", bundle);
				startActivity(intent);
				
			
			}
		});
		 */
		
		
		/*
		 for(int cnt=0; cnt<simpleFamilyInfo.size(); cnt++){
			//이미지 Server 주소 + DB 값
			simpleData.add(new SimpleFamilyInfo(simpleFamilyInfo.get(cnt)[0],"http://192.168.0.20:8089/final_so_tong/"+simpleFamilyInfo.get(cnt)[4],simpleFamilyInfo.get(cnt)[1],"생년월일 : "+simpleFamilyInfo.get(cnt)[2]));
		}	//
		 */
		
		/*
		 Intent intent = new Intent(context.getApplicationContext(),NeighborHomeActivity.class);
			Bundle bundle = new Bundle();
			
			for(int cnt=0; cnt<list.size(); cnt++){
				bundle.putStringArray("neighborInfo"+(cnt+1), list.get(cnt));
			}
			intent.putExtra("bundle", bundle);
			intent.putExtra("neighborCnt", list.size());
			context.startActivity(intent);
		 */
		
		neighborHomeShowHomeName = (TextView)findViewById(R.id.neighborHomeShowHomeName);
		neighborHomeShowListView = (ListView)findViewById(R.id.neighborHomeShowListView);
		
		
		
		
		
	}

	
}
