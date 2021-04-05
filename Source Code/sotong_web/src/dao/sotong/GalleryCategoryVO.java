package dao.sotong;

import java.io.Serializable;


public class GalleryCategoryVO implements Serializable{

	private static final long serialVersionUID = 805231131802231370L;
	private String galleryCategoryCode;
	private String galleryCategory;
	
	public GalleryCategoryVO() {
		// TODO Auto-generated constructor stub
	}

	public GalleryCategoryVO(String galleryCategoryCode, String galleryCategory) {
		super();
		this.galleryCategoryCode = galleryCategoryCode;
		this.galleryCategory = galleryCategory;
	}

	public String getGalleryCategoryCode() {
		return galleryCategoryCode;
	}

	public void setGalleryCategoryCode(String galleryCategoryCode) {
		this.galleryCategoryCode = galleryCategoryCode;
	}

	public String getGalleryCategory() {
		return galleryCategory;
	}

	public void setGalleryCategory(String galleryCategory) {
		this.galleryCategory = galleryCategory;
	}


	public String toString() {
		return "GalleryCategoryVO [galleryCategoryCode=" + galleryCategoryCode
				+ ", galleryCategory=" + galleryCategory + "]";
	}
	
	

}
 