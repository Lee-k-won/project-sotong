package manager;

import dao.sotong.EmoticonDAO;
import dao.sotong.ImageDAO;
import dao.sotong.SotongContentsDAO;
import dao.sotong.SotongContentsVO;

public class SotongManager {
	private ImageDAO imageDAO;
	private SotongContentsDAO sotongContentsDAO;
	private EmoticonDAO emoticonDAO;
	
	public SotongManager()
	{
		imageDAO=new ImageDAO();
		emoticonDAO=new EmoticonDAO();
		sotongContentsDAO = new SotongContentsDAO();
	}
	
	public String addSotongContents(String familyHomeCode, String sotongCategoryCode, String contents, String imageName, String imageWrittenDate, String emoticonCode)
	{
		String imageCode = imageDAO.insertImageAndReturnImageCode(imageName, imageWrittenDate, "c2");
		
		
		String sotongContentsCode = sotongContentsDAO.insertSotongContentsCode(familyHomeCode, sotongCategoryCode, contents, imageCode, emoticonCode);
		
		
		return sotongContentsCode;		
	}
	
	public int updateSotongContents(String sotongContentsCode, String contents, String imageName, String imageWrittenDate, String emoticonCode)
	{
		SotongContentsVO vo = sotongContentsDAO.selectSotongContents(sotongContentsCode);
		
		imageDAO.updateImage(vo.getImageCode(), imageName, imageWrittenDate, "c2");
		
		return sotongContentsDAO.updateSotongContents(sotongContentsCode, contents, vo.getImageCode(), emoticonCode);
	}
	
	
	
	public int deleteSotongContents(String sotongContentsCode)
	{
		SotongContentsVO vo = sotongContentsDAO.selectSotongContents(sotongContentsCode);

	//	if(vo.getImageCode() != null)
	//	{
	//		imageDAO.deleteImage(vo.getImageCode());
	//	}

		return sotongContentsDAO.deleteSotongContents(sotongContentsCode);		
	}
	
	public int updateContents(String sotongContentsCode, String contents) {
		return this.sotongContentsDAO.updateContents(sotongContentsCode, contents);
	}

	
}
