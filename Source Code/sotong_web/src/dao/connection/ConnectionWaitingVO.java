package dao.connection;

import java.io.Serializable;
import java.util.Date;

public class ConnectionWaitingVO implements Serializable{

	private static final long serialVersionUID = 9144009900782698964L;
	private String waitingCode;
	private String receiverHomeCode;
	private String senderHomeCode;
	private Date waitingDate;
	
	public ConnectionWaitingVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConnectionWaitingVO(String waitingCode, String receiverHomeCode,
			String senderHomeCode, Date waitingDate) {
		super();
		this.waitingCode = waitingCode;
		this.receiverHomeCode = receiverHomeCode;
		this.senderHomeCode = senderHomeCode;
		this.waitingDate = waitingDate;
	}
	public String getWaitingCode() {
		return waitingCode;
	}
	public void setWaitingCode(String waitingCode) {
		this.waitingCode = waitingCode;
	}
	public String getReceiverHomeCode() {
		return receiverHomeCode;
	}
	public void setReceiverHomeCode(String receiverHomeCode) {
		this.receiverHomeCode = receiverHomeCode;
	}
	public String getSenderHomeCode() {
		return senderHomeCode;
	}
	public void setSenderHomeCode(String senderHomeCode) {
		this.senderHomeCode = senderHomeCode;
	}
	public Date getWaitingDate() {
		return waitingDate;
	}
	public void setWaitingDate(Date waitingDate) {
		this.waitingDate = waitingDate;
	}
	@Override
	public String toString() {
		return "ConnectionWaitingVO [waitingCode=" + waitingCode
				+ ", receiverHomeCode=" + receiverHomeCode
				+ ", senderHomeCode=" + senderHomeCode + ", waitingDate="
				+ waitingDate + "]";
	}
	
}
	