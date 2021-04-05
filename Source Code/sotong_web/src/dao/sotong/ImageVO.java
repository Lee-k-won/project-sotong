package dao.sotong;


import java.io.Serializable;
import java.util.Date;

public class ImageVO implements Serializable{

	private static final long serialVersionUID = 2285869080271439240L;
	private String imageCode;
	private String imageName;
	private String imageWrittenDate;
	private String galleryCategoryCode;
	
	public ImageVO() {
		// TODO Auto-generated constructor stub
	}

	public ImageVO(String imageCode, String imageName, String imageWrittenDate,
			String galleryCategoryCode) {
		super();
		this.imageCode = imageCode;
		this.imageName = imageName;
		this.imageWrittenDate = imageWrittenDate;
		this.galleryCategoryCode = galleryCategoryCode;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
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

	public String getGalleryCategoryCode() {
		return galleryCategoryCode;
	}

	public void setGalleryCategoryCode(String galleryCategoryCode) {
		this.galleryCategoryCode = galleryCategoryCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ImageVO [imageCode=" + imageCode + ", imageName=" + imageName
				+ ", imageWrittenDate=" + imageWrittenDate
				+ ", galleryCategoryCode=" + galleryCategoryCode + "]";
	}

}
