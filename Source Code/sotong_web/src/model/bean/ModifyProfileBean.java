package model.bean;

public class ModifyProfileBean {
	
	private String pw;
	private String nickName;
	private String phone;
	private String email;
	private String photo;
	private String birth;
	private String color;
	
	public ModifyProfileBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModifyProfileBean(String pw, String nickName,
			String phone, String email, String photo, String birth, String color) {
		super();
		this.pw = pw;
		this.nickName = nickName;
		this.phone = phone;
		this.email = email;
		this.photo = photo;
		this.birth = birth;
		this.color = color;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	

}
