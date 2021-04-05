package com.example.finalproject;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class WishListActivity extends ActionBarActivity {

	private ViewFlow viewFlow;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list);
		
		//태영 추가
	      Intent intent=getIntent();
	      //Bundle bundle=intent.getBundleExtra("wishList");
	      final ArrayList<String[]> wishList=(ArrayList<String[]>)intent.getSerializableExtra("WishInfoList");
	      int wishCnt = intent.getIntExtra("wishCount", 0);
	      
	      String []wishes = new String[wishCnt];
	      
	      //추가2
	      String memberCode=intent.getStringExtra("memberCode");
	      String homeCode=intent.getStringExtra("homeCode");
	      for(int i=0;i<wishes.length;i++){
	         wishes[i]=wishList.get(i)[3];
	      }
	    if(wishes.length != 0){
		
	    viewFlow = (ViewFlow)findViewById(R.id.viewflow_wish);
		WishVersionAdapter adapter = new WishVersionAdapter(WishListActivity.this, wishes, wishList,memberCode,homeCode);
		viewFlow.setAdapter(adapter);
		
		
		
		TitleFlowIndicator indicator = (TitleFlowIndicator)findViewById(R.id.viewflowindic_wish);
		
		indicator.setTitleProvider(adapter);
		
		viewFlow.setFlowIndicator(indicator);
		}else{
			//String memberCode=intent.getStringExtra("memberCode");
		     // String homeCode=intent.getStringExtra("homeCode");
			Intent addIntent = new Intent(getApplicationContext(),AddWishActivity.class);
			addIntent.putExtra("memberCode", memberCode);
			addIntent.putExtra("homeCode", homeCode);
			startActivity(addIntent);
		}
	}
}

	