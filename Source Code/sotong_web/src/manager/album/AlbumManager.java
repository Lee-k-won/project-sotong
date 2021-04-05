package manager.album;

import dao.sotong.ImageDAO;
import dao.sotong.ImageVO;
import dao.sotong.SotongContentsDAO;
import dao.sotong.SotongContentsVO;

public class AlbumManager 
{
	private ImageDAO imageDAO;
	private SotongContentsDAO sotongContentsDAO;
	public AlbumManager()
	{
		imageDAO=new ImageDAO();
	}
	public ImageVO[] getAlbumListByHomeCode(String homeCode)
	{
		sotongContentsDAO=new SotongContentsDAO();
		SotongContentsVO[] voList=sotongContentsDAO.selectSotongContentsByHomeCode(homeCode);
		ImageVO[] imageVOList=new ImageVO[voList.length];
		for(int i=0;i<voList.length;i++)
		{
			imageVOList[i]=imageDAO.selectImage(voList[i].getImageCode());
		}
		if(imageVOList==null||imageVOList.length==0)
		{
			return null;
		}
		else 
			return imageVOList; 
	}
	public ImageVO[] getAlbumList(String galleryCategoryCode)
	{
		return imageDAO.selectImageByCategory(galleryCategoryCode);
	}
	public ImageVO getImageInfo(String imageCode)
	{
		return imageDAO.selectImage(imageCode);
	}
	
}




