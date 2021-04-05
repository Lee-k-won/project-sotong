package dao.letter;

import java.io.Serializable;
import java.util.Date;

public class LetterViewVO implements Serializable{

	private static final long serialVersionUID = 6024313018684615466L;
	private String letterCode;
	private String sender;
	private String receiver;
	private String letterTitle;
	private Date sendDate;
	private String sotongContentsCode;
	private String contents;
	private String imageName;
	private Date imageWrittenDate;
	private String emoticonName;
	private String emoticonRoute;
	public LetterViewVO()
	{
		
	}
	
	public LetterViewVO(String letterCode, String sender, String receiver,
			String letterTitle, Date sendDate, String sotongContentsCode,
			String contents, String imageName, Date imageWrittenDate,
			String emoticonName, String emoticonRoute) {
		this.letterCode = letterCode;
		this.sender = sender;
		this.receiver = receiver;
		this.letterTitle = letterTitle;
		this.sendDate = sendDate;
		this.sotongContentsCode = sotongContentsCode;
		this.contents = contents;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
	}

	public String getLetterCode() {
		return letterCode;
	}
	public void setLetterCode(String letterCode) {
		this.letterCode = letterCode;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getLetterTitle() {
		return letterTitle;
	}
	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getSotongContentsCode() {
		return sotongContentsCode;
	}

	public void setSotongContentsCode(String sotongContentsCode) {
		this.sotongContentsCode = sotongContentsCode;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getImageWrittenDate() {
		return imageWrittenDate;
	}
	public void setImageWrittenDate(Date imageWrittenDate) {
		this.imageWrittenDate = imageWrittenDate;
	}
	public String getEmoticonName() {
		return emoticonName;
	}
	public void setEmoticonName(String emoticonName) {
		this.emoticonName = emoticonName;
	}
	public String getEmoticonRoute() {
		return emoticonRoute;
	}
	public void setEmoticonRoute(String emoticonRoute) {
		this.emoticonRoute = emoticonRoute;
	}

	@Override
	public String toString() {
		return "LetterViewVO [letterCode=" + letterCode + ", sender=" + sender
				+ ", receiver=" + receiver + ", letterTitle=" + letterTitle
				+ ", sendDate=" + sendDate + ", sotongContentsCode="
				+ sotongContentsCode + ", contents=" + contents
				+ ", imageName=" + imageName + ", imageWrittenDate="
				+ imageWrittenDate + ", emoticonName=" + emoticonName
				+ ", emoticonRoute=" + emoticonRoute + "]";
	}
	
	
	
}
