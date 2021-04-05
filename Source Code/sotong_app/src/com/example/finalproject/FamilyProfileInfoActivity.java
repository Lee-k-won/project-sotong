package com.example.finalproject;

import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FamilyProfileInfoActivity extends ActionBarActivity {

	public Button familyProfileCheckBtn;
	public Button familyProfileCancelBtn;
	
	public ImageView familyProfile_role_pic;
	public ImageView familyProfile_profile_pic;
	public TextView familyProfileMemberName;
	
	public TextView familyProfileViewMemberPhoneNum;
	public TextView familyProfileViewMemberEmail;
	public TextView familyProfileViewMemberBirthDay;
	public TextView familyProfileViewMemberNickName;
	public TextView familyProfileViewMemberColor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_profile_info);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("familyInfo");
		String familyMemberInfo[] = bundle.getStringArray("familyMemberInfo");
		
		
		familyProfileCheckBtn = (Button)findViewById(R.id.familyProfileCheckBtn);
		familyProfileCancelBtn = (Button)findViewById(R.id.familyProfileCancelBtn);
		
		familyProfile_role_pic = (ImageView)findViewById(R.id.familyProfile_role_pic);
		familyProfile_profile_pic = (ImageView)findViewById(R.id.familyProfile_profile_pic);
		familyProfileMemberName = (TextView)findViewById(R.id.familyProfileMemberName);
		
		familyProfileViewMemberPhoneNum = (TextView)findViewById(R.id.familyProfileViewMemberPhoneNum);
		familyProfileViewMemberEmail = (TextView)findViewById(R.id.familyProfileViewMemberEmail);
		familyProfileViewMemberBirthDay = (TextView)findViewById(R.id.familyProfileViewMemberBirthDay);
		familyProfileViewMemberNickName = (TextView)findViewById(R.id.familyProfileViewMemberNickName);
		familyProfileViewMemberColor = (TextView)findViewById(R.id.familyProfileViewMemberColor);
		
		new SimpleProfileDownloadImageTask(familyProfile_profile_pic).execute(URLAddress.myUrl+"/final_so_tong/"+familyMemberInfo[4]);
		familyProfileMemberName.setText(familyMemberInfo[5]);
		familyProfileViewMemberPhoneNum.setText(familyMemberInfo[2]);
		familyProfileViewMemberEmail.setText(familyMemberInfo[3]);
		familyProfileViewMemberBirthDay.setText(familyMemberInfo[7]);
		familyProfileViewMemberNickName.setText(familyMemberInfo[5]);
		familyProfileViewMemberColor.setText(familyMemberInfo[6]);
		
	}

	
}
