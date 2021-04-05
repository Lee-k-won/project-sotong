package com.example.finalproject;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.finalproject.model.SimpleFamilyInfo;
import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.WebServerFamilyProfileRequest;
import com.example.finalproject.webserver.WebServerNeighborHomeShowThread;

@SuppressLint("NewApi")
public class FragmentHome extends Fragment {
	
	public ViewFlow viewFlow;
	public Context context;
	
	public ImageButton postBoxBtn;
	public ImageButton handsBtn;
	 
	public ListView familyHomeShowListView = null;
	public ArrayList<SimpleFamilyInfo> simpleData = null;
	public SimpleFamilyInfoListAdapter simpleFamilyInfoListAdapter;
	
	public ListView neighborHomeShowListView = null;
	public ArrayList<String[]> simpleNeighborData = null;
	public SimpleNeighborInfoListAdapter simpleNeighborInfoListAdapter;
	
	private String []userInfo;
	
	public FragmentHome(ArrayList<String[]> simpleFamilyInfo, ArrayList<String[]> neighborList, String []userInfo) {
		simpleData = new ArrayList<SimpleFamilyInfo>();
		simpleNeighborData = new ArrayList<String[]>();
		this.userInfo = userInfo;
		
		for(int cnt=0; cnt<neighborList.size(); cnt++){
			simpleNeighborData.add(new String[]{neighborList.get(cnt)[0], neighborList.get(cnt)[1], neighborList.get(cnt)[2]});
		}
		/*
		simpleNeighborData = new ArrayList<String[]>();
		simpleNeighborData.add(new String[]{"쩌려니의 홈","김철연"});
		simpleNeighborData.add(new String[]{"별이네 홈","장한별"});
		simpleNeighborData.add(new String[]{"경원이네 집","이경원"});
		*/
		for(int cnt=0; cnt<simpleFamilyInfo.size(); cnt++){
			//이미지 Server 주소 + DB 값
			simpleData.add(new SimpleFamilyInfo(simpleFamilyInfo.get(cnt)[0],URLAddress.myUrl+"/final_so_tong/"+simpleFamilyInfo.get(cnt)[4],simpleFamilyInfo.get(cnt)[1],"생년월일 : "+simpleFamilyInfo.get(cnt)[2]));
		}	//"http://192.168.0.110:8089/so-tong/login.do"
		
		/*
		simpleData.add(new SimpleFamilyInfo("", "장재영", "생년월일 : 03월 24일"));
		simpleData.add(new SimpleFamilyInfo("", "장한별", "생년월일 : 04월 24일"));
		simpleData.add(new SimpleFamilyInfo("", "이경원", "생년월일 : 05월 24일"));
		simpleData.add(new SimpleFamilyInfo("", "백태영", "생년월일 : 06월 24일"));
		*/
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		context = rootView.getContext();
		
		viewFlow = (ViewFlow)rootView.findViewById(R.id.viewflow_home);
		HomeVersionAdapter adapter = new HomeVersionAdapter(context);
		viewFlow.setAdapter(adapter,0);
		TitleFlowIndicator indicator = (TitleFlowIndicator)rootView.findViewById(R.id.viewflowindic_home);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		
		
		//simpleData = new ArrayList<SimpleFamilyInfo>();
		/*
		simpleData.add(new SimpleFamilyInfo("", "장재영", "생년월일 : 03월 24일"));
		simpleData.add(new SimpleFamilyInfo("", "장한별", "생년월일 : 04월 24일"));
		simpleData.add(new SimpleFamilyInfo("", "이경원", "생년월일 : 05월 24일"));
		simpleData.add(new SimpleFamilyInfo("", "백태영", "생년월일 : 06월 24일"));
		*/
		simpleFamilyInfoListAdapter = new SimpleFamilyInfoListAdapter(getActivity(), simpleData);
		familyHomeShowListView = (ListView)rootView.findViewById(R.id.familyHomeShowListView);
		familyHomeShowListView.setAdapter(simpleFamilyInfoListAdapter);
		
		familyHomeShowListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				WebServerFamilyProfileRequest requestThread = new WebServerFamilyProfileRequest(
						context, simpleData.get(position).getMemberCode());
				requestThread.start();
				/*
				Intent intent = new Intent(context.getApplicationContext(),FamilyProfileInfoActivity.class);
				
				Bundle bundle = new Bundle();
				bundle.putStringArray("userInfo", userInfo);
				bundle.putString("detailProfileCode", simpleData.get(position).getMemberCode());
				intent.putExtra("bundleData", bundle);
				startActivity(intent);
				*/
			
			}
		});
		/*
		simpleNeighborData = new ArrayList<String[]>();
		simpleNeighborData.add(new String[]{"쩌려니의 홈","김철연"});
		simpleNeighborData.add(new String[]{"별이네 홈","장한별"});
		simpleNeighborData.add(new String[]{"경원이네 집","이경원"});
		*/
		
		
		simpleNeighborInfoListAdapter = new SimpleNeighborInfoListAdapter(getActivity(), simpleNeighborData);
		neighborHomeShowListView = (ListView)rootView.findViewById(R.id.neighborHomeShowListView);
		neighborHomeShowListView.setAdapter(simpleNeighborInfoListAdapter);
		neighborHomeShowListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				WebServerNeighborHomeShowThread neighborThread = new WebServerNeighborHomeShowThread(context.getApplicationContext(), simpleNeighborData.get(position)[0], simpleNeighborData.get(position)[1]);
				
				Log.v("myCheck", simpleNeighborData.get(position)[0]);
				Log.v("myCheck", simpleNeighborData.get(position)[1]);
				//simpleNeighborData.get(position)[0], simpleNeighborData.get(position)[1]
				neighborThread.start();
			//	Intent intent = new Intent(context.getApplicationContext(),FamilyProfileInfoActivity.class);
			//	startActivity(intent);
			}
		});
		
		
		
		return rootView;

		
	}
	/*
	public static final int HOUR = 24;
	
	
	public int getAMonthDate(int year, int month){
		return 0;
	}
	*/
	
	
}
