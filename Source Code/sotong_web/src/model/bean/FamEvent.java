package model.bean;

public class FamEvent {
	private String id;
	private String title;
	private String start;
	private String end;
	private String place;
	private Boolean allDay;
	private String backgroundColor;
	private int repeat;
	private String eventRequest;
	private String eventResponse;
	private String alarm;
	private String memo;
	public FamEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FamEvent(String id, String title, String start, String end,
			String place, Boolean allDay, String backgroundColor, int repeat,
			String eventRequest, String eventResponse, String alarm, String memo) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.place = place;
		this.allDay = allDay;
		this.backgroundColor = backgroundColor;
		this.repeat = repeat;
		this.eventRequest = eventRequest;
		this.eventResponse = eventResponse;
		this.alarm = alarm;
		this.memo = memo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Boolean getAllDay() {
		return allDay;
	}
	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public String getEventRequest() {
		return eventRequest;
	}
	public void setEventRequest(String eventRequest) {
		this.eventRequest = eventRequest;
	}
	public String getEventResponse() {
		return eventResponse;
	}
	public void setEventResponse(String eventResponse) {
		this.eventResponse = eventResponse;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "FamEvent [id=" + id + ", title=" + title + ", start=" + start
				+ ", end=" + end + ", place=" + place + ", allDay=" + allDay
				+ ", backgroundColor=" + backgroundColor + ", repeat=" + repeat
				+ ", eventRequest=" + eventRequest + ", eventResponse="
				+ eventResponse + ", alarm=" + alarm + ", memo=" + memo + "]";
	}
	
	
}
