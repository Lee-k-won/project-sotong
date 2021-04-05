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

	private CalendarView monthView; // Ŭ���� ����
	private CalendarAdapter monthViewAdapter;// Ŭ���� ����2
	private ArrayList<ScheduleSummary> summaryList;
	private TextView monthText; // ����
	private final int DIALOG_CUSTOM_ID = 1;

	//
	private ArrayList<String[]> scheduleInfo;
	private String[] matchingResult;
	//

	private ListView lv;
	private ArrayList<DayData> dayData; // ��¥���� ���� �������� �����ϴ� ����Ʈ

	private int curYear;// ���� ����
	private int curMonth;// ���� ��
	private int curDay;// ���� ��
	private String txt = "";
	private int curHour;// ���� �ð�
	private int curMin;// ���� ��

	public ImageButton monthPrevious;
	public ImageButton monthNext;
	public ImageButton addSingleScheduleBtn1;

	private EditText et; // ���̾�α׿��� ���� ���� �Է� ����
	private Button save; // ���̾�α׿��� �����ư

	// ArrayAdapter<String> adapter; //�ش� ��¥�� ����� ������ ������ ����Ʈ ��
	private ArrayAdapter<ScheduleSummary> adapter;
	private ArrayList<String> as;// ����Ʈ�信 ������ ������ �����ϴ� ����Ʈ
	private String []userInfo;
	// /////////////////////////////////
	public FragmentSchedule(ArrayList<String[]> scheduleInfo, String []matchingResult, String []userInfo) {
		this.scheduleInfo = scheduleInfo;
		this.matchingResult = matchingResult;
		this.userInfo = userInfo;
	}

	// �����ڵ�
	// ����ڵ�
	// ��������
	// �������
	// �������۽ð�
	// ���� ����ð�
	// �ݺ�Ƚ��
	// ���� �޸� ����

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
		dayData = new ArrayList<DayData>();// ��¥����
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

		lv = (ListView) rootView.findViewById(R.id.listView1);// ����Ʈ �並 xml�κ���
																// ����
		if (rootView == null) {
			Log.v("rootView", "��üũ");
		}
		monthView = (CalendarView) rootView.findViewById(R.id.monthView1);
		if (monthView == null) {
			Log.v("monthView", "��üũ");
		}
		// monthViewAdapter = new CalendarAdapter(context);
		monthViewAdapter = new CalendarAdapter(context, matchingResult/* .getApplicationContext() */);
		if (monthViewAdapter == null) {
			Log.v("monthViewAdapter", "��üũ");
		}
		monthView.setAdapter(monthViewAdapter);
		monthView.setOnDataSelectionListener(new OnDataSelectionListener() { // ��¥
																				// ���ý�
																				// ������
					public void onDataSelected(AdapterView parent, View v,
							int position, long id) {
						MonthItem curItem = (MonthItem) monthViewAdapter
								.getItem(position); // ���� ������ ��¥�� ��.
						curDay = curItem.getDay();// ���� ������ ��¥�� �޾ƿ�.

						as = new ArrayList<String>();
						summaryList = new ArrayList<ScheduleSummary>();
						for (int i = 0; i < dayData.size(); i++) { // ���� ������
																	// ó������ ������
																	// �˻�
							if (dayData.get(i).getDay() == curDay) { // ���� ����
																		// ������
																		// ��¥��
																		// ����������
																		// ����Ǿ��ִٸ�
								as.add(dayData.get(i).getSchedule()); // �����ֱ�
																		// ����Ʈ��
																		// ������.
								summaryList.add(new ScheduleSummary(dayData
										.get(i).getSchedule()));

							}
						}

						updateLv();// ����Ʈ�並 ������Ʈ

					}
				});

		monthText = (TextView) rootView.findViewById(R.id.monthText1);
		setMonthText(); // �ʱ�ȭ

		
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
				
				//monthViewAdapter.setPreviousMonth(); // ���� ���� ���� �޷� ����.
				//monthViewAdapter.notifyDataSetChanged(); // �����Ͱ� ����Ǹ�,�� �����Ͱ�
															// ��Ÿ������ �Ѵ�.

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
			//	monthViewAdapter.notifyDataSetChanged();// �����Ͱ� ����Ǹ�,�� �����Ͱ�
														// ��Ÿ������ �Ѵ�.

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
		// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,as);//����͸�
		// �ʱ�ȭ�Ѵ�.��¥������ ���� ��������Ʈ�� ����ͺ�� �����ֵ�����.
		// adapter= new ArrayAdapter<String>(this,R.layout.schedule_info,as);
		adapter = new SummaryAdapter(context.getApplicationContext(),
				R.layout.schedule_info, R.id.schedule2, summaryList);
		// adapter = new
		// SummaryAdapter(context,R.layout.schedule_info,R.id.schedule1,summaryList);

		// ����͸� �ʱ�ȭ�Ѵ�.��¥������ ���� ��������Ʈ�� ����ͺ�� �����ֵ�����.

		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/* ���� �󼼺��� */

				Toast.makeText(context.getApplicationContext(), "�����󼼺��� ������",
						Toast.LENGTH_LONG).show();
				// Intent intent=new Intent();
				// intent.setClass(context.getApplicationContext(),
				// ViewScActivity.class);
				// startActivity(intent);
			}
		});
	}

	private void setMonthText() {
		curYear = monthViewAdapter.getCurYear();// ���� ������ �޾ƿ�
		curMonth = monthViewAdapter.getCurMonth();// ���� ���� �޾ƿ�.

		monthText.setText(curYear + "��" + (curMonth + 1) + "��");

	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
		menu.add("��������");

	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu);//�����޴� ��� menu.add("���� ����");
	 * return true; }
	 */

	public boolean onOptionsItemSelected(MenuItem item) {
		int curId = item.getItemId(); // ���̵� ����

		if (curId == R.id.action_settings) {
			final DayData dd = new DayData(curYear, curMonth, curDay);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					context.getApplicationContext()); // ���� ȭ�鿡�� ���̾�α� ����
			View dia = View.inflate(context.getApplicationContext(),
					R.layout.dia, null); // dia.xml���� ���̾�α� ���� �о�ͼ� ��� ����
			builder.setTitle("�����߰�");
			builder.setView(dia);
			save = (Button) dia.findViewById(R.id.quizButton); // ���̾�α׿��� �����ư
			// Button cancel=(Button)dia.findViewById(R.id.button2);//���̾�α׿��� ���
			// ��ư.
			et = (EditText) dia.findViewById(R.id.editText1); // ���̾�α׿��� ���� �ۼ�
																// �ؽ�Ʈ�ʵ�
			final TimePicker tp = (TimePicker) dia
					.findViewById(R.id.timePicker1); // �ð��� �����ϴ� Ŭ����

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
			save.setOnClickListener(saveListener);// ������ ����
		}
		/*
		 * else if(curId==R.id.FamView){ Intent intent=new
		 * Intent(ScheduleActivity.this,ViewFamScActivity.class);
		 * startActivity(intent); } ����� ���� ���� �߰��Ѵ� (�޴����� �Ϸ��� �߾�����)
		 */
		return true;
	}

	/*
	 * 
	 * public boolean onOptionsItemSelected(MenuItem item){ int curId =
	 * item.getItemId(); //���̵� �޾ƿ� if(curId == R.id.action_settings){//���� ���̵��
	 * final DayData dd = new DayData(curYear, curMonth, curDay);
	 * AlertDialog.Builder builder = new AlertDialog.Builder(this); //���� ȭ�鿡��
	 * ���̾�α� ���� View dia = View.inflate(this, R.layout.dia, null);//dia.xml����
	 * ���̾�α� ���� �о�ͼ� ��� ����.. builder.setTitle("�����߰�"); builder.setView(dia);//����
	 * �並 ����. save = (Button)dia.findViewById(R.id.quizButton);//���̾�α׿��� ���� ��ư
	 * //Button cancel=(Button)dia.findViewById(R.id.button2);//���̾�α׿��� ��� ��ư.
	 * et = (EditText)dia.findViewById(R.id.editText1);//���̾�α׿��� ���� �ۼ� �ؽ�Ʈ�ʵ�.
	 * final TimePicker tp =
	 * (TimePicker)dia.findViewById(R.id.timePicker1);//�ð��� �����ϴ� Ŭ����
	 * tp.setOnTimeChangedListener(this);//�ð� ����� ������ ����. final DialogInterface
	 * mPopupDlg=builder.show(); View.OnClickListener saveListener = new
	 * View.OnClickListener() {//�����ư Ŭ������ ������. public void onClick(View v) {
	 * txt = et.getText().toString(); dd.setTime(tp.getCurrentHour(),
	 * tp.getCurrentMinute()); dd.setString(txt); dayData.add(dd);
	 * 
	 * mPopupDlg.dismiss(); } }; save.setOnClickListener(saveListener);//������ ����
	 * 
	 * } else if(curId==R.id.FamView) { Intent intent=new
	 * Intent(ScheduleActivity.this,ViewFamScActivity.class);
	 * startActivity(intent); } return true; }
	 */

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {// ����
																			// �ð���
																			// �����ϴ�
																			// �޼ҵ�
		// TODO Auto-generated method stub
		curHour = hourOfDay;
		curMin = minute;
	}

}
