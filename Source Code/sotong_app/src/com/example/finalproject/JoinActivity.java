package com.example.finalproject;

import com.example.finalproject.webserver.WebServerJoinConnectThread;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends ActionBarActivity {

	public TextView joinText;
	
	public EditText joinIdText;            // 16자리 
	public EditText joinPasswordText;		// 6 ~ 10
	public EditText joinPasswordCheckText;
	
	public EditText joinNameText;   // 5자리
	public EditText joinEmailText; // 30자리
	public EditText joinPhoneText; 
	
	
	public Button joinjoinBtn;
	public Button joinCancelBtn;
	
	public ActionBarActivity actionBarActivity = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		
		joinText = (TextView)findViewById(R.id.joinText);
		joinText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font3.ttf"));
		
		joinIdText = (EditText)findViewById(R.id.joinIdText);
		joinPasswordText = (EditText)findViewById(R.id.joinPasswordText);
		joinPasswordCheckText = (EditText)findViewById(R.id.joinPasswordCheckText);
		joinNameText = (EditText)findViewById(R.id.joinNameText);
		joinEmailText = (EditText)findViewById(R.id.joinEmailText);
		joinPhoneText = (EditText)findViewById(R.id.joinPhoneText);
		 
		joinjoinBtn = (Button)findViewById(R.id.joinjoinBtn);
		joinjoinBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean check = true;
				String id = joinIdText.getText().toString();
				String password = joinPasswordText.getText().toString();
				String passwordCheck = joinPasswordCheckText.getText().toString();
				String name = joinNameText.getText().toString();
				String email = joinEmailText.getText().toString();
				String phoneNum = joinPhoneText.getText().toString();
				
				if(id.length() > 16){
					Toast.makeText(getApplicationContext(), "아이디는 16자리 이하로 입력해야 합니다", Toast.LENGTH_SHORT).show();
					check = false;
				}if(!(password.length() >= 6 && password.length() <= 10)){
					Toast.makeText(getApplicationContext(), "비밀번호는 6~10자리로 입력해야 합니다", Toast.LENGTH_SHORT).show();
					check = false;
				}if(name.length() > 5){
					Toast.makeText(getApplicationContext(), "이름은 5자리 이하로 입력해야 합니다", Toast.LENGTH_SHORT).show();
					check = false;
				}if(email.length() > 30){
					Toast.makeText(getApplicationContext(), "이메일 주소는 30자리 이하로 입력해야 합니다", Toast.LENGTH_SHORT).show();
					check = false;
				}
				
				
				if(password.equals(passwordCheck) && (!password.equals("")) && (!passwordCheck.equals(""))){
					check = true;
					if(check == true){
						WebServerJoinConnectThread serverConnection = new WebServerJoinConnectThread(actionBarActivity, actionBarActivity, id, passwordCheck, name, email, phoneNum, "h1");
						serverConnection.start();
					}
					
				}else{
					check = false;
					Toast.makeText(getApplicationContext(), "비밀번호 확인이 일치하지 않습니다", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		joinCancelBtn = (Button)findViewById(R.id.joinCancelBtn);
		joinCancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		//intro1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font3.ttf"));
	}

	
}
