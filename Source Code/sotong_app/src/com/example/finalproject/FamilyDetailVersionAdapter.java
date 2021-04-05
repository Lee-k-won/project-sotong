package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.model.FamilyDiaryDetail;
import com.example.finalproject.model.SimpleFamilyInfo;
import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

public class FamilyDetailVersionAdapter extends BaseAdapter{
	public Context context;
	public ArrayList<FamilyDiaryDetail> data;
	public LayoutInflater layoutInflater;
	
	public FamilyDetailVersionAdapter() {
		super();
	}

	public FamilyDetailVersionAdapter(Context context, ArrayList<FamilyDiaryDetail> data) {
		super();
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(this.context);
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
		
		if((position%2) == 0){
		
			View itemLayout = layoutInflater.inflate(R.layout.family_diary_form, null);
		
			ImageView familyDetailDiaryPicture = (ImageView)itemLayout.findViewById(R.id.familyDetailDiaryPicture);
			TextView familyDetailDiaryName = (TextView)itemLayout.findViewById(R.id.familyDetailDiaryName);
			TextView familyDetailDiaryContent = (TextView)itemLayout.findViewById(R.id.familyDetailDiaryContent);
		//new SimpleProfileDownloadImageTask(simpleFamilyInfoImage).execute(data.get(position).getFamilyImage());
			
			new SimpleProfileDownloadImageTask(familyDetailDiaryPicture).execute(URLAddress.myUrl+"/final_so_tong/"+data.get(position).getMemberPhoto());
			//Log.v("Picture", "http://192.168.0.20:8089/final_so_tong/"+data.get(position).getImageName());
			//familyDetailDiaryPicture.setImageResource(R.drawable.brad);
			familyDetailDiaryName.setText(data.get(position).getMemberNickName());
			familyDetailDiaryContent.setText(data.get(position).getContents());
			
			//familyDetailDiaryPicture.setImageResource(R.drawable.brad);
			//familyDetailDiaryName.setText(data.get(position).getName());
			//familyDetailDiaryContent.setText(data.get(position).getContent());
			
			//simpleData.add(new SimpleFamilyInfo(simpleFamilyInfo.get(cnt)[0],"http://192.168.0.20:8089/final_so_tong/"+simpleFamilyInfo.get(cnt)[4],simpleFamilyInfo.get(cnt)[1],"생년월일 : "+simpleFamilyInfo.get(cnt)[2]));
			
			return itemLayout;
		}else if((position%2)!=0){
			
			View itemLayout2 = layoutInflater.inflate(R.layout.family_diary_form2, null);
		 
			ImageView familyDetailDiaryPicture2 = (ImageView)itemLayout2.findViewById(R.id.familyDetailDiaryPicture2);
			TextView familyDetailDiaryName2 = (TextView)itemLayout2.findViewById(R.id.familyDetailDiaryName2);
			TextView familyDetailDiaryContent2 = (TextView)itemLayout2.findViewById(R.id.familyDetailDiaryContent2);
		//new SimpleProfileDownloadImageTask(simpleFamilyInfoImage).execute(data.get(position).getFamilyImage());
			
			new SimpleProfileDownloadImageTask(familyDetailDiaryPicture2).execute(URLAddress.myUrl+"/final_so_tong/"+data.get(position).getMemberPhoto()); 
			//familyDetailDiaryPicture2.setImageResource(R.drawable.brad);
			familyDetailDiaryName2.setText(data.get(position).getMemberNickName());
			familyDetailDiaryContent2.setText(data.get(position).getContents());
			
			/*
			familyDetailDiaryPicture2.setImageResource(R.drawable.brad);
			familyDetailDiaryName2.setText(data.get(position).getName());
			familyDetailDiaryContent2.setText(data.get(position).getContent());
			*/
			return itemLayout2;
		}else{
			return layoutInflater.inflate(R.layout.family_diary_form, null);
		}
		
		
		
	}
	

}
