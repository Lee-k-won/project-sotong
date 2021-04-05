package com.example.finalproject.webserver;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class SimpleProfileDownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	private ImageView bmImage;
	
	public SimpleProfileDownloadImageTask(ImageView bmImage) {
		this.bmImage = bmImage;
	}
	
	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
		Bitmap mIcon11 = null;
		
		try{
			InputStream in = new java.net.URL(urldisplay).openStream();
			mIcon11 = BitmapFactory.decodeStream(in);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return mIcon11;
	}
	protected void onPostExecute(Bitmap result) {
		bmImage.setImageBitmap(result);
	}
}
