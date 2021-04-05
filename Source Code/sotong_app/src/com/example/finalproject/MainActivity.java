package com.example.finalproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject.webserver.WebServerConnectThread;

public class MainActivity extends ActionBarActivity {

	public TextView intro1;
	public TextView intro2;
	public TextView intro3;
	
	public Button loginBtn;
	public Button joinBtn;
	
	public EditText loginIdText;
	public EditText loginPasswordText;
	
	public ActionBarActivity actionBarActivity = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
	    //    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		setContentView(R.layout.activity_main);
		
	
		intro1 = (TextView)findViewById(R.id.login_intro1);
		intro2 = (TextView)findViewById(R.id.login_intro2);
		intro3 = (TextView)findViewById(R.id.login_intro3);
		
		intro1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font3.ttf"));
		intro2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font3.ttf"));
		intro3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font3.ttf"));
		
		loginBtn = (Button)findViewById(R.id.loginBtn);
		joinBtn = (Button)findViewById(R.id.joinBtn);
		
		loginIdText = (EditText)findViewById(R.id.loginIdText);
		loginPasswordText = (EditText)findViewById(R.id.loginPasswordText);
		
		loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*
				Intent userAndFriendsIntent = new Intent();
				userAndFriendsIntent.setClass(context, MemberActivity.class);
				userAndFriendsIntent.putExtras(myBundle);
				context.startActivity(userAndFriendsIntent);
				*/
				String id = loginIdText.getText().toString();
				String password = loginPasswordText.getText().toString();
				WebServerConnectThread serverConnection = new WebServerConnectThread(actionBarActivity, id, password);
				serverConnection.start();
				/*
				Intent loginIntent = new Intent();
				loginIntent.setClass(getApplicationContext(), MainMenuActivity.class);
				startActivity(loginIntent);
				*/
			}
		});
		
		joinBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
