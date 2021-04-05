package com.example.finalproject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;
import com.example.finalproject.webserver.WebServerMyProfileModifyThread;

public class MyProfileModifyActivity extends ActionBarActivity {


	public Button myProfileModifyModifyBtn;
	public Button myProfileModifyCancelBtn;
	
	public TextView myProfileName;
	public EditText myProfileId;
	public EditText myProfilePhoneNum;
	public EditText myProfileEmail;
	public EditText myProfilePassword;
	public EditText myProfilePasswordCheck;
	public EditText myProfileBirthday;
	public EditText myProfileNickname;
	public TextView myProfileColor;
	
	//////////////////////////////////
	//�¿��߰�
	public final int REQ_CODE_SELECT_IMAGE = 100;
	public ImageView myProfileImage;
	public byte[] imageFile;
	   
	public String changedProfileRoute;
	
	////////////////////////////////
	
	
	
	public ActionBarActivity actionBarActivity = this;
	
	private String []loginUserInfo;
	private ArrayList<String[]> list;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile_modify);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		loginUserInfo = bundle.getStringArray("loginUserInfo");
		list = (ArrayList<String[]>)intent.getSerializableExtra("familyList");
		for(int cnt=0; cnt<loginUserInfo.length; cnt++){
			Log.v("LoginUserInfo", loginUserInfo[cnt]);
		}
		
		/////////////////////////////////
		//�¿�
		myProfileImage = (ImageView) findViewById(R.id.myProfileImage);

	    if (loginUserInfo[7] == null) {
	       new SimpleProfileDownloadImageTask(myProfileImage)
	               .execute(URLAddress.myUrl+"/final_so_tong/img/profile/default.jpg");
	    }else{
	         new SimpleProfileDownloadImageTask(myProfileImage)
	               .execute(URLAddress.myUrl+"/final_so_tong/"
	                     + loginUserInfo[7]);
	    }
	    myProfileImage.setOnClickListener(new OnClickListener() {
	       @Override
	       public void onClick(View v) {
	          // TODO Auto-generated method stub
	          Intent intent = new Intent(Intent.ACTION_PICK);
	          intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
	          intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	          startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
	       }
	    });
		
		
		//////////////////////////////////
		
		myProfileName = (TextView)findViewById(R.id.myProfileName);
		myProfileName.setText(loginUserInfo[2]);
		
		myProfileId = (EditText)findViewById(R.id.myProfileId);
		myProfileId.setText(loginUserInfo[5]);
		
		myProfilePhoneNum = (EditText)findViewById(R.id.myProfilePhoneNum);
		myProfilePhoneNum.setText(loginUserInfo[3]);
		
		myProfileEmail = (EditText)findViewById(R.id.myProfileEmail);
		myProfileEmail.setText(loginUserInfo[4]);
		
		myProfilePassword = (EditText)findViewById(R.id.myProfilePassword);
		myProfilePasswordCheck = (EditText)findViewById(R.id.myProfilePasswordCheck);
		
		myProfileBirthday = (EditText)findViewById(R.id.myProfileBirthday);
		myProfileBirthday.setText(loginUserInfo[10]);
		
		myProfileNickname = (EditText)findViewById(R.id.myProfileNickname);
		myProfileNickname.setText(loginUserInfo[8]);
		
		myProfileColor = (TextView)findViewById(R.id.myProfileColor);
		myProfileColor.setText(loginUserInfo[9]);
		
		myProfileModifyModifyBtn = (Button)findViewById(R.id.myProfileModifyModifyBtn);
		myProfileModifyCancelBtn = (Button)findViewById(R.id.myProfileModifyCancelBtn);
		
		myProfileModifyModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String []modifyUserInfo = new String[loginUserInfo.length];
				modifyUserInfo[0] = loginUserInfo[0];
				modifyUserInfo[1] = loginUserInfo[1];
				modifyUserInfo[2] = myProfileName.getText().toString();
				modifyUserInfo[3] = myProfilePhoneNum.getText().toString();
				modifyUserInfo[4] = myProfileEmail.getText().toString();
				modifyUserInfo[5] = myProfileId.getText().toString();
				modifyUserInfo[6] = myProfilePassword.getText().toString();
				
				///////////////////////////////////////////////
				/*
				if (changedProfileRoute == null) {
		               modifyUserInfo[7] = loginUserInfo[7];// ���� ���ε�� ���ϸ� �ٲ�����.
		            } else {
		               modifyUserInfo[7] = changedProfileRoute;
		            }
				 */
				if (changedProfileRoute != null) {
		               loginUserInfo[7] = changedProfileRoute;
		               for(int cnt=0; cnt<list.size(); cnt++){
		            	   if(loginUserInfo[0].equals(list.get(cnt)[0])){
		            		   list.get(cnt)[4] = changedProfileRoute;
		            	   }
		               }
		            }
				modifyUserInfo[7] = loginUserInfo[7];
				
				
				
				///////////////////////////////////////////////
				//modifyUserInfo[7] = loginUserInfo[7];
				modifyUserInfo[8] = myProfileNickname.getText().toString();
				modifyUserInfo[9] = myProfileColor.getText().toString();
				modifyUserInfo[10] = myProfileBirthday.getText().toString();
				modifyUserInfo[11] = loginUserInfo[11];

				WebServerMyProfileModifyThread thread = new WebServerMyProfileModifyThread(
						actionBarActivity, modifyUserInfo, imageFile);
				thread.start();
				// ��й�ȣ �Է����� ���� ��쵵 ó���� ��!!
				// ��й�ȣ Ȯ�ΰ� ��ġ���� ���� ��� ó���� ��!!

				
			}
		});
		
		myProfileModifyCancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				actionBarActivity.finish();
			}
		});
		
	}
	
	/////////////////////////////////////////////
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {//������ ���� ���� ���� , �� ��Ƽ��Ƽ�� ���ƿ� �����. 
		if (requestCode == REQ_CODE_SELECT_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {
				try {
	               // Uri���� �̹��� �̸��� ���´�
	               Toast.makeText(getBaseContext(),"path:" + getImagePath(data.getData()), Toast.LENGTH_SHORT).show();
	               String name_Str = getImageNameToUri(data.getData());

	               Bitmap image_bitmap = Images.Media.getBitmap(
	                     getContentResolver(), data.getData());//������ �̹����� ��Ʈ������ ����
	               image_bitmap = Bitmap.createScaledBitmap(image_bitmap,
	                     (int) (image_bitmap.getWidth() * 0.3),
	                     (int) (image_bitmap.getHeight() * 0.3), true);//������ ��Ʈ���� ���μ��θ� 30%�� ����. �������� ũ�� ����.
	               imageFile = bitmapToByteArray(image_bitmap);//��Ʈ���� ����Ʈ �迭�� �ٲ� ����� ����.
	               /*if (imageFile == null) {
	                  Toast.makeText(getBaseContext(), "it is null",
	                        Toast.LENGTH_SHORT).show();
	               } else {
	                  Toast.makeText(getBaseContext(), "not null",
	                        Toast.LENGTH_SHORT).show();
	                  System.out.println(imageFile.length);
	               }*/
	               // ImageView image =
	               // (ImageView)findViewById(R.id.imageView1);
	               // ��ġ�س��� ImageView�� set
	               // image.setImageBitmap(image_bitmap);
	               
	               myProfileImage.setImageBitmap(image_bitmap); //���� �����ʻ����� �ҷ��� �̹������Ϸ� ��ȯ.
	               
	               //hangedProfileRoute="img/profile/"+name_Str; //����� ��ο� �̸� ����.
	               int lastIndex = name_Str.lastIndexOf(".");
	               String imageType = name_Str.substring(lastIndex);
	               changedProfileRoute="img/profile/"+makeFileName()+imageType;
	               // onResume();
	               // image_bitmap.

	            } catch (FileNotFoundException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            } catch (IOException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }

	   public String getImageNameToUri(Uri data) { //Uri�κ��� �̹������ϸ��� �޾ƿ�.
	      String imgPath = getImagePath(data);
	      String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

	      return imgName;
	   }

	   public String getImagePath(Uri data) {//�н��� �޾ƿ�
	      String[] proj = { MediaStore.Images.Media.DATA };
	      Cursor cursor = managedQuery(data, proj, null, null, null);
	      int column_index = cursor
	            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

	      cursor.moveToFirst();

	      String imgPath = cursor.getString(column_index);
	      return imgPath;
	   }

	   // @SuppressLint("NewApi")
	   public byte[] bitmapToByteArray(Bitmap bitmap) {//��Ʈ���� ����Ʈ �迭 ���·� �ٲ�.
	      ByteArrayOutputStream stream = new ByteArrayOutputStream();
	      bitmap.compress(CompressFormat.PNG, 100, stream);
	      byte[] byteArray = stream.toByteArray();

	      return byteArray;
	   }
	   private String makeFileName()
	      {
	         GregorianCalendar cal = new GregorianCalendar();
	         
	         String name = "sotong_" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + "_" + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
	         
	         return name;
	      }
	

	/////////////////////////////////////////////
	
}
