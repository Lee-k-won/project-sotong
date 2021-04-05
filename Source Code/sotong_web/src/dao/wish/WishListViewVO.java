package dao.wish;

import java.io.Serializable;

public class WishListViewVO implements Serializable {
	private static final long serialVersionUID = 2854895951386499639L;
	private String wishCode;
	private String memberNickName;
	private String familyHomeCode;
	private String sotongContentsCode;
	private String contents;
	private String emoticonName;
	private String emoticonRoute;
	private String imageName;
	private String imageWrittenDate;
	private String wishTitle;
	private String wishDate;
	private String wishEndDate;
	private String wishFinish;
	public WishListViewVO() {
		super();
	}
	public WishListViewVO(String wishCode, String memberNickName,
			String familyHomeCode, String sotongContentsCode, String contents,
			String emoticonName, String emoticonRoute, String imageName,
			String imageWrittenDate, String wishTitle, String wishDate,
			String wishEndDate, String wishFinish) {
		super();
		this.wishCode = wishCode;
		this.memberNickName = memberNickName;
		this.familyHomeCode = familyHomeCode;
		this.sotongContentsCode = sotongContentsCode;
		this.contents = contents;
		this.emoticonName = emoticonName;
		this.emoticonRoute = emoticonRoute;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.wishTitle = wishTitle;
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
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getFamilyHomeCode() {
		return familyHomeCode;
	}
	public void setFamilyHomeCode(String familyHomeCode) {
		this.familyHomeCode = familyHomeCode;
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageWrittenDate() {
		return imageWrittenDate;
	}
	public void setImageWrittenDate(String imageWrittenDate) {
		this.imageWrittenDate = imageWrittenDate;
	}
	public String getWishTitle() {
		return wishTitle;
	}
	public void setWishTitle(String wishTitle) {
		this.wishTitle = wishTitle;
	}
	public String getWishDate() {
		return wishDate;
	}
	public void setWishDate(String wishDate) {
		this.wishDate = wishDate;
	}
	public String getWishEndDate() {
		return wishEndDate;
	}
	public void setWishEndDate(String wishEndDate) {
		this.wishEndDate = wishEndDate;
	}
	public String getWishFinish() {
		return wishFinish;
	}
	public void setWishFinish(String wishFinish) {
		this.wishFinish = wishFinish;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WishListViewVO [wishCode=" + wishCode + ", memberNickName="
				+ memberNickName + ", familyHomeCode=" + familyHomeCode
				+ ", sotongContentsCode=" + sotongContentsCode + ", contents="
				+ contents + ", emoticonName=" + emoticonName
				+ ", emoticonRoute=" + emoticonRoute + ", imageName="
				+ imageName + ", imageWrittenDate=" + imageWrittenDate
				+ ", wishTitle=" + wishTitle + ", wishDate=" + wishDate
				+ ", wishEndDate=" + wishEndDate + ", wishFinish=" + wishFinish
				+ "]";
	}
}
