package dao.wish;

import java.io.Serializable;
import java.util.Date;

public class WishVO implements Serializable{

private static final long serialVersionUID = -8613082113216497593L;
private String wishCode;
private String sotongContentsCode;
private String memberCode;
private String wishTitle;
private Date wishDate;
private Date wishEndDate;
private byte wishFinish;

public WishVO()
{
	
}

public WishVO(String wishCode, String sotongContentsCode,
		String memberCode, String wishTitle,Date wishDate, Date wishEndDate,byte wishFinish) {
	this.wishCode = wishCode;
	this.sotongContentsCode = sotongContentsCode;
	this.memberCode = memberCode;
	this.wishTitle=wishTitle;
	this.wishDate = wishDate;
	this.wishEndDate = wishEndDate;
	this.wishFinish = wishFinish;
}

public String getWishCode() {
	return wishCode;
}

public void setWishCode(String wishCode) {
	this.wishCode = wishCode;
}

public String getSotongContentsCode() {
	return sotongContentsCode;
}

public void setSotongContentsCode(String sotongContentsCode) {
	this.sotongContentsCode = sotongContentsCode;
}

public String getMemberCode() {
	return memberCode;
}

public void setMemberCode(String memberCode) {
	this.memberCode = memberCode;
}

public String getWishTitle() {
	return wishTitle;
}

public void setWishTitle(String wishTitle) {
	this.wishTitle = wishTitle;
}

public Date getWishDate() {
	return wishDate;
}

public void setWishDate(Date wishDate) {
	this.wishDate = wishDate;
}

public byte getWishFinish() {
	return wishFinish;
}

public void setWishFinish(byte wishFinish) {
	this.wishFinish = wishFinish;
}

public Date getWishEndDate() {
	return wishEndDate;
}

public void setWishEndDate(Date wishEndDate) {
	this.wishEndDate = wishEndDate;
}


public String toString() {
	return "WishInfoVO [wishCode=" + wishCode + ", sotongContentsCode="
			+ sotongContentsCode + ", memberCode=" + memberCode
			+ ", wishTitle=" + wishTitle + ", wishDate=" + wishDate
			+ ", wishEndDate=" + wishEndDate + ", wishFinish=" + wishFinish
			+ "]";
}






}
