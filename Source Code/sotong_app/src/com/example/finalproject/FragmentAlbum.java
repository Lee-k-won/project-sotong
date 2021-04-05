package com.example.finalproject;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.finalproject.model.URLAddress;
import com.example.finalproject.webserver.SimpleProfileDownloadImageTask;

@SuppressLint("NewApi")
public class FragmentAlbum extends Fragment{
	
	private ViewFlow viewFlow;
	public Context context;
	
	//public Button viewPicBtn;
	
	public AlbumDialog albumDialog;
	public TableLayout tableLayout;
	
	private ArrayList<String[]> imageInfoList;
	
	public FragmentAlbum(ArrayList<String[]> imageInfoList) {
	      this.imageInfoList = imageInfoList;
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	         Bundle savedInstanceState) {
	      View rootView = inflater.inflate(R.layout.fragment_album, container,
	            false);
	      context = rootView.getContext();

	      viewFlow = (ViewFlow) rootView.findViewById(R.id.viewflow_album);
	      if(imageInfoList==null ||imageInfoList.size()==0)
	      {
	         Toast.makeText(context, "앨범에 저장된 사진이 없습니다.", Toast.LENGTH_SHORT);
	      }
	      else{
	      AlbumVersionAdapter adapter = new AlbumVersionAdapter(context,
	            imageInfoList);
	      viewFlow.setAdapter(adapter, 0);
	      TitleFlowIndicator indicator = (TitleFlowIndicator) rootView
	            .findViewById(R.id.viewflowindic_album);
	      indicator.setTitleProvider(adapter);
	      viewFlow.setFlowIndicator(indicator);

	      /*
	       * button1=(ImageButton)rootView.findViewById(R.id.prevPage);
	       * button1.setOnClickListener(new OnClickListener() {
	       * 
	       * @Override public void onClick(View v) { // TODO Auto-generated method
	       * stub if(adapter.==0) {
	       * 
	       * return; } nowPage-=1; notifyDataSetChanged(); } });
	       * button2=(ImageButton)convertView.findViewById(R.id.nextPage);
	       * button2.setOnClickListener(new OnClickListener() {
	       * 
	       * @Override public void onClick(View v) { // TODO Auto-generated method
	       * stub if(nowPage==pageCnt) {
	       * 
	       * return; } nowPage+=1;
	       * 
	       * } });
	       */
	      // ///////////////////////////////////

	      /*
	       * final ArrayList<Integer> images = new ArrayList<Integer>(); for(int
	       * cnt=1; cnt<5; cnt++){
	       * images.add(getResources().getIdentifier("cat"+cnt, "drawable",
	       * rootView.getContext().getPackageName())); }
	       */
	      // 앨범 다이얼로그 생성자에, 이미지 정보값들을 모두 넣어줌. 넣어준 후, 내부 어댑터에서 new
	      // SimpleProfileDownloadImageTask로 이미지 다운로드.

	      // final AlbumDialog albumDialog=new AlbumDialog(context,
	      // imageInfoList);

	      tableLayout = (TableLayout) rootView
	            .findViewById(R.id.albumTableLayout);

	      ImageView imageview[] = new ImageView[10];
	      imageview[0] = (ImageView) rootView.findViewById(R.id.albumImageView1);
	      imageview[1] = (ImageView) rootView.findViewById(R.id.albumImageView2);
	      imageview[2] = (ImageView) rootView.findViewById(R.id.albumImageView3);
	      imageview[3] = (ImageView) rootView.findViewById(R.id.albumImageView4);
	      imageview[4] = (ImageView) rootView.findViewById(R.id.albumImageView5);
	      imageview[5] = (ImageView) rootView.findViewById(R.id.albumImageView6);
	      imageview[6] = (ImageView) rootView.findViewById(R.id.albumImageView7);
	      imageview[7] = (ImageView) rootView.findViewById(R.id.albumImageView8);
	      imageview[8] = (ImageView) rootView.findViewById(R.id.albumImageView9);
	      imageview[9] = (ImageView) rootView.findViewById(R.id.albumImageView10);
	      for (int i = 0; i < 10; i++) {

	         
	         if (i==imageInfoList.size())
	         {
	            break; 
	         }
	         

	         new SimpleProfileDownloadImageTask(imageview[i])
	               .execute(URLAddress.myUrl+"/final_so_tong/"
	                     + imageInfoList.get(i)[1].trim() + ".jpg".trim());


	      }
	      imageview[0].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 0).show();

	         }
	      });
	      imageview[1].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 1).show();
	         }
	      });
	      imageview[2].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 2).show();
	         }
	      });
	      imageview[3].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 3).show();
	         }
	      });
	      imageview[4].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 4).show();
	         }
	      });
	      imageview[5].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 5).show();
	         }
	      });
	      imageview[6].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 6).show();
	         }
	      });
	      imageview[7].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 7).show();
	         }
	      });
	      imageview[8].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 8).show();
	         }
	      });
	      imageview[9].setOnClickListener(new OnClickListener() {

	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub

	            new AlbumDialog(context, imageInfoList, 9).show();
	         }
	      });
	      /*
	       * tableLayout.setOnClickListener(new OnClickListener() { public void
	       * onClick(View v) {
	       * 
	       * albumDialog.show(); albumDialog.setStrat(start); } });
	       */

	      /*
	       * viewPicBtn = (Button)rootView.findViewById(R.id.viewPic);
	       * viewPicBtn.setOnClickListener(new OnClickListener() { public void
	       * onClick(View v) { albumDialog.show(); } });
	       */

	      // ///////////////////////////////////
	      }
	      return rootView;
	   }
	  
}
