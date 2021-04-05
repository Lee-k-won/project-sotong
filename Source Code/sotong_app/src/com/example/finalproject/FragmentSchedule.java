package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.example.finalproject.calendar.CalendarAdapter;
import com.example.finalproject.calendar.CalendarView;
import com.example.finalproject.calendar.DayData;
import com.example.finalproject.calendar.MonthItem;
import com.example.finalproject.calendar.OnDataSelectionListener;
import com.example.finalproject.calendar.SummaryAdapter;
import com.example.finalproject.model.ScheduleSummary;
import com.example.finalproject.webserver.WebServerGetScheduleMatchingThread;

@SuppressLint("NewApi")
public class FragmentSchedule extends Fragment implements OnTimeChangedListener {
	private ViewFlow viewFlow;
	public Context context;

	private CalendarView monthView; // 클래스 변수
	private CalendarAdapter monthViewAdapter;// 클래스 변수2
	private ArrayList<ScheduleSummary> summaryList;
	private TextView monthText; // 현재
	private final int DIALOG_CUSTOM_ID = 1;

	//
	private ArrayList<String[]> scheduleInfo;
	private String[] matchingResult;
	//

	private ListView lv;
	private ArrayList<DayData> dayData; // 날짜들의 일정 정보들을 저장하는 리스트

	private int curYear;// 현재 연도
	private int curMonth;// 현재 월
	private int curDay;// 현재 일
	private String txt = "";
	private int curHour;// 현재 시간
	private int curMin;// 현재 분

	public ImageButton monthPrevious;
	public ImageButton monthNext;
	public ImageButton addSingleScheduleBtn1;

	private EditText et; // 다이얼로그에서 일정 내용 입력 공간
	private Button save; // 다이얼로그에서 저장버튼

	// ArrayAdapter<String> adapter; //해당 날짜에 저장된 일정을 보여줄 리스트 뷰
	private ArrayAdapter<ScheduleSummary> adapter;
	private ArrayList<String> as;// 리스트뷰에 저장할 일정을 저장하는 리스트
	private String []userInfo;
	// /////////////////////////////////
	public FragmentSchedule(ArrayList<String[]> scheduleInfo, String []matchingResult, String []userInfo) {
		this.scheduleInfo = scheduleInfo;
		this.matchingResult = matchingResult;
		this.userInfo = userInfo;
	}

	// 일정코드
	// 멤버코드
	// 일정제목
	// 일정장소
	// 일정시작시간
	// 일정 종료시간
	// 반복횟수
	// 일정 메모 내용

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_schedule, container,
				false);
		context = rootView.getContext();

		/*
		 * viewFlow = (ViewFlow)rootView.findViewById(R.id.viewflow_schedule);
		 * //ScheduleVersionAdapter adapter = new
		 * ScheduleVersionAdapter(context); ScheduleVersionAdapter adapter = new
		 * ScheduleVersionAdapter(context.getApplicationContext());
		 * viewFlow.setAdapter(adapter,0); TitleFlowIndicator indicator =
		 * (TitleFlowIndicator
		 * )rootView.findViewById(R.id.viewflowindic_schedule);
		 * indicator.setTitleProvider(adapter);
		 * viewFlow.setFlowIndicator(indicator)
		 */;

		// //////////////////////////////////////////////
		dayData = new ArrayList<DayData>();// 날짜정보
		for (int i = 0; i < scheduleInfo.size(); i++) {
			Date startDate = null;
			try {
				startDate = new SimpleDateFormat("yy-MM-dd").parse(scheduleInfo
						.get(i)[4]);
			} catch (Exception e) {

			}
			DayData dateInfo = new DayData(startDate.getYear() + 1900,
					startDate.getMonth() + 1, startDate.getDate());
			dateInfo.setString(scheduleInfo.get(i)[2]);
			dayData.add(dateInfo);
		}
		// return rootView;

		lv = (ListView) rootView.findViewById(R.id.listView1);// 리스트 뷰를 xml로부터
																// 연결
		if (rootView == null) {
			Log.v("rootView", "널체크");
		}
		monthView = (CalendarView) rootView.findViewById(R.id.monthView1);
		if (monthView == null) {
			Log.v("monthView", "널체크");
		}
		// monthViewAdapter = new CalendarAdapter(context);
		monthViewAdapter = new CalendarAdapter(context, matchingResult/* .getApplicationContext() */);
		if (monthViewAdapter == null) {
			Log.v("monthViewAdapter", "널체크");
		}
		monthView.setAdapter(monthViewAdapter);
		monthView.setOnDataSelectionListener(new OnDataSelectionListener() { // 날짜
																				// 선택시
																				// 리스너
					public void onDataSelected(AdapterView parent, View v,
							int position, long id) {
						MonthItem curItem = (MonthItem) monthViewAdapter
								.getItem(position); // 현재 선택한 날짜의 값.
						curDay = curItem.getDay();// 현재 선택한 날짜를 받아옴.

						as = new ArrayList<String>();
						summaryList = new ArrayList<ScheduleSummary>();
						for (int i = 0; i < dayData.size(); i++) { // 일정 정보들
																	// 처음부터 끝까지
																	// 검사
							if (dayData.get(i).getDay() == curDay) { // 만약 현재
																		// 선택한
																		// 날짜에
																		// 일정정보가
																		// 저장되어있다면
								as.add(dayData.get(i).getSchedule()); // 보여주기
																		// 리스트에
																		// 저장함.
								summaryList.add(new ScheduleSummary(dayData
										.get(i).getSchedule()));

							}
						}

						updateLv();// 리스트뷰를 업데이트

					}
				});

		monthText = (TextView) rootView.findViewById(R.id.monthText1);
		setMonthText(); // 초기화

		
		addSingleScheduleBtn1 = (ImageButton)rootView.findViewById(R.id.addSingleScheduleBtn1);
		
		monthPrevious = (ImageButton) rootView.findViewById(R.id.monthPrevious1);
		monthPrevious.setOnClickListener(new OnClickListener() {

		//WebServerGetSimpleIndividualScheduleListThread scheduleThread= new WebServerGetSimpleIndividualScheduleListThread(getApplicationContext(),userInfo, fm, fr);
	    //scheduleThread.start();
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				WebServerGetScheduleMatchingThread matchingThread = 
						new WebServerGetScheduleMatchingThread(context.getApplicationContext(), monthViewAdapter, userInfo, (byte)1);
				matchingThread.start();
				
				//monthViewAdapter.setPreviousMonth(); // 현재 달을 이전 달로 설정.
				//monthViewAdapter.notifyDataSetChanged(); // 데이터가 변경되면,새 데이터가
															// 나타나도록 한다.

				setMonthText();

			}
		});

		monthNext = (ImageButton) rootView.findViewById(R.id.monthNext1);
		monthNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				WebServerGetScheduleMatchingThread matchingThread = 
						new WebServerGetScheduleMatchingThread(context.getApplicationContext(), monthViewAdapter, userInfo, (byte)2);
				matchingThread.start();
			//	monthViewAdapter.setNextMonth();
			//	monthViewAdapter.notifyDataSetChanged();// 데이터가 변경되면,새 데이터가
														// 나타나도록 한다.

				setMonthText();
			}

		});
		// //////////////////////////////////////////////
		setHasOptionsMenu(true);
		return rootView;
	}

	/*
	 * public void onResume() {
	 * 
	 * super.onResume(); monthViewAdapter.notifyDataSetChanged(); }
	 */
	public void updateLv() {
		// adapter= new
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,as);//어댑터를
		// 초기화한다.날짜마다의 일정 정보리스트를 어댑터뷰로 보여주도록함.
		// adapter= new ArrayAdapter<String>(this,R.layout.schedule_info,as);
		adapter = new SummaryAdapter(context.getApplicationContext(),
				R.layout.schedule_info, R.id.schedule2, summaryList);
		// adapter = new
		// SummaryAdapter(context,R.layout.schedule_info,R.id.schedule1,summaryList);

		// 어댑터를 초기화한다.날짜마다의 일정 정보리스트를 어댑터뷰로 보여주도록함.

		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/* 일정 상세보기 */

				Toast.makeText(context.getApplicationContext(), "일정상세보기 들어가야함",
						Toast.LENGTH_LONG).show();
				// Intent intent=new Intent();
				// intent.setClass(context.getApplicationContext(),
				// ViewScActivity.class);
				// startActivity(intent);
			}
		});
	}

	private void setMonthText() {
		curYear = monthViewAdapter.getCurYear();// 현재 연도를 받아옴
		curMonth = monthViewAdapter.getCurMonth();// 현재 월을 받아옴.

		monthText.setText(curYear + "년" + (curMonth + 1) + "월");

	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
		menu.add("개인일정");

	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu);//하위메뉴 사용 menu.add("가족 일정");
	 * return true; }
	 */

	public boolean onOptionsItemSelected(MenuItem item) {
		int curId = item.getItemId(); // 아이디를 받음

		if (curId == R.id.action_settings) {
			final DayData dd = new DayData(curYear, curMonth, curDay);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					context.getApplicationContext()); // 현재 화면에서 다이얼로그 생성
			View dia = View.inflate(context.getApplicationContext(),
					R.layout.dia, null); // dia.xml에서 다이얼로그 형태 읽어와서 뷰로 만듬
			builder.setTitle("일정추가");
			builder.setView(dia);
			save = (Button) dia.findViewById(R.id.quizButton); // 다이얼로그에서 저장버튼
			// Button cancel=(Button)dia.findViewById(R.id.button2);//다이얼로그에서 취소
			// 버튼.
			et = (EditText) dia.findViewById(R.id.editText1); // 다이얼로그에서 일정 작성
																// 텍스트필드
			final TimePicker tp = (TimePicker) dia
					.findViewById(R.id.timePicker1); // 시간을 설정하는 클래스

			tp.setOnTimeChangedListener(this);
			final DialogInterface mPopupDlg = builder.show();
			View.OnClickListener saveListener = new View.OnClickListener() {
				public void onClick(View v) {
					txt = et.getText().toString();
					dd.setTime(tp.getCurrentHour(), tp.getCurrentMinute());
					dd.setString(txt);
					dayData.add(dd);
					mPopupDlg.dismiss();
				}
			};
			save.setOnClickListener(saveListener);// 리스너 지정
		}
		/*
		 * else if(curId==R.id.FamView){ Intent intent=new
		 * Intent(ScheduleActivity.this,ViewFamScActivity.class);
		 * startActivity(intent); } 여기는 가족 일정 추가한다 (메뉴에서 하려고 했었던것)
		 */
		return true;
	}

	/*
	 * 
	 * public boolean onOptionsItemSelected(MenuItem item){ int curId =
	 * item.getItemId(); //아이디를 받아옴 if(curId == R.id.action_settings){//현재 아이디와
	 * final DayData dd = new DayData(curYear, curMonth, curDay);
	 * AlertDialog.Builder builder = new AlertDialog.Builder(this); //현재 화면에서
	 * 다이얼로그 생성 View dia = View.inflate(this, R.layout.dia, null);//dia.xml에서
	 * 다이얼로그 형태 읽어와서 뷰로 만듬.. builder.setTitle("일정추가"); builder.setView(dia);//만든
	 * 뷰를 세팅. save = (Button)dia.findViewById(R.id.quizButton);//다이얼로그에서 저장 버튼
	 * //Button cancel=(Button)dia.findViewById(R.id.button2);//다이얼로그에서 취소 버튼.
	 * et = (EditText)dia.findViewById(R.id.editText1);//다이얼로그에서 일정 작성 텍스트필드.
	 * final TimePicker tp =
	 * (TimePicker)dia.findViewById(R.id.timePicker1);//시간을 설정하는 클래스
	 * tp.setOnTimeChangedListener(this);//시간 변경시 리스터 설정. final DialogInterface
	 * mPopupDlg=builder.show(); View.OnClickListener saveListener = new
	 * View.OnClickListener() {//저장버튼 클릭시의 리스너. public void onClick(View v) {
	 * txt = et.getText().toString(); dd.setTime(tp.getCurrentHour(),
	 * tp.getCurrentMinute()); dd.setString(txt); dayData.add(dd);
	 * 
	 * mPopupDlg.dismiss(); } }; save.setOnClickListener(saveListener);//리스너 지정
	 * 
	 * } else if(curId==R.id.FamView) { Intent intent=new
	 * Intent(ScheduleActivity.this,ViewFamScActivity.class);
	 * startActivity(intent); } return true; }
	 */

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {// 현재
																			// 시간을
																			// 변경하는
																			// 메소드
		// TODO Auto-generated method stub
		curHour = hourOfDay;
		curMin = minute;
	}

}
