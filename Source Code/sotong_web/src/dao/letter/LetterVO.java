package dao.letter;

import java.io.Serializable;
import java.util.Date;

public class LetterVO implements Serializable{

	private static final long serialVersionUID = 5700709002471476037L;
	private String letterCode;
	private String sotongContentsCode;
	private String senderCode;
	private String receiverCode;
	private String letterTitle;
	private String sendDate;
	
	public LetterVO() {
		// TODO Auto-generated constructor stub
	}

	public LetterVO(String letterCode, String sotongContentsCode,
			String senderCode, String receiverCode, String letterTitle,
			String sendDate) {
		super();
		this.letterCode = letterCode;
		this.sotongContentsCode = sotongContentsCode;
		this.senderCode = senderCode;
		this.receiverCode = receiverCode;
		this.letterTitle = letterTitle;
		this.sendDate = sendDate;
	}

	public String getLetterCode() {
		return letterCode;
	}

	public void setLetterCode(String letterCode) {
		this.letterCode = letterCode;
	}

	public String getSotongContentsCode() {
		return sotongContentsCode;
	}

	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}

	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getLetterTitle() {
		return letterTitle;
	}

	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LetterVO [letterCode=" + letterCode + ", sotongContentsCode="
				+ sotongContentsCode + ", senderCode=" + senderCode
				+ ", receiverCode=" + receiverCode + ", letterTitle="
				+ letterTitle + ", sendDate=" + sendDate + "]";
	}
	
	

}
