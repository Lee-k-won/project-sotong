package manager.diary;

import java.util.Date;

import manager.SotongManager;
import dao.diary.DiaryDAO;
import dao.diary.DiaryViewDAO;
import dao.diary.DiaryViewVO;

public class DiaryManager {
	private DiaryDAO diaryDAO;
	private DiaryViewDAO diaryViewDAO;
	
	public DiaryManager() {
		this.diaryDAO = new DiaryDAO();
		this.diaryViewDAO = new DiaryViewDAO();
	}
	
	public String[][] getSimpleIndividualDiaryList(String memberCode)
	{
		return diaryDAO.selectSimpleDiaryList(memberCode);
	}
	
	public DiaryViewVO getIndividualDiaryInfo(String diaryCode)
	{
		return diaryViewDAO.selectDiaryInfo(diaryCode);
	}
	
	public int addIndividualDiary(String memberCode, String familyHomeCode, String diaryTitle, String diaryDate, String contents, String imageName, String imageWrittenDate, String emoticonCode)
	{
		SotongManager sManager = new SotongManager();
		
		String sotongContentsCode = sManager.addSotongContents(familyHomeCode, "sc2", contents,  imageName, imageWrittenDate, emoticonCode);
		
		return diaryDAO.insertDiary(memberCode, sotongContentsCode, diaryTitle, diaryDate);
	}
	
	
	public int updateIndividualDiary(String diaryCode, String memberCode, String sotongContentsCode, String diaryTitle, String diaryUpdateDate, String contents, String imageName, String imageWrittenDate, String emoticonCode)
	{
		SotongManager sManager = new SotongManager();
		
		sManager.updateSotongContents(sotongContentsCode, contents, imageName, imageWrittenDate, emoticonCode);
		
		return diaryDAO.updateDiary(diaryCode, memberCode, sotongContentsCode, diaryTitle);
	}
	
	
	
	public int deleteIndividualDiary(String diaryCode,String sotongContentsCode)
	{
		SotongManager sManager = new SotongManager();
		
		diaryDAO.delete(diaryCode);
		
		return sManager.deleteSotongContents(sotongContentsCode);
	}
		
		
}
