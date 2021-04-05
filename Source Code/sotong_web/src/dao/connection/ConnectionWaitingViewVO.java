package dao.connection;

import java.io.Serializable;

public class ConnectionWaitingViewVO implements Serializable{

	private static final long serialVersionUID = -7153191322185331210L;
	private String waitingCode;
	private String senderHomeCode;
	private String senderHomeName;
	private String senderHomeManagerName;
	private String receiverHomeCode;
	
	public ConnectionWaitingViewVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConnectionWaitingViewVO(String waitingCode, String senderHomeCode,
			String senderHomeName, String senderHomeManagerName,
			String receiverHomeCode) {
		super();
		this.waitingCode = waitingCode;
		this.senderHomeCode = senderHomeCode;
		this.senderHomeName = senderHomeName;
		this.senderHomeManagerName = senderHomeManagerName;
		this.receiverHomeCode = receiverHomeCode;
	}

	public String getWaitingCode() {
		return waitingCode;
	}

	public void setWaitingCode(String waitingCode) {
		this.waitingCode = waitingCode;
	}

	public String getSenderHomeCode() {
		return senderHomeCode;
	}

	public void setSenderHomeCode(String senderHomeCode) {
		this.senderHomeCode = senderHomeCode;
	}

	public String getSenderHomeName() {
		return senderHomeName;
	}

	public void setSenderHomeName(String senderHomeName) {
		this.senderHomeName = senderHomeName;
	}

	public String getSenderHomeManagerName() {
		return senderHomeManagerName;
	}

	public void setSenderHomeManagerName(String senderHomeManagerName) {
		this.senderHomeManagerName = senderHomeManagerName;
	}

	public String getReceiverHomeCode() {
		return receiverHomeCode;
	}

	public void setReceiverHomeCode(String receiverHomeCode) {
		this.receiverHomeCode = receiverHomeCode;
	}

	@Override
	public String toString() {
		return "ConnectionWaitingViewVO [waitingCode=" + waitingCode
				+ ", senderHomeCode=" + senderHomeCode + ", senderHomeName="
				+ senderHomeName + ", senderHomeManagerName="
				+ senderHomeManagerName + ", receiverHomeCode="
				+ receiverHomeCode + "]";
	}
	
	
	

}
