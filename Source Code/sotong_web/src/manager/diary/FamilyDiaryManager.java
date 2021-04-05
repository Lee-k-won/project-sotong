package manager.diary;

import manager.SotongManager;
import dao.diary.FamilyDiaryDAO;
import dao.diary.FamilyDiaryPartDAO;
import dao.diary.FamilyDiaryPartVO;
import dao.diary.FamilyDiaryVO;
import dao.diary.FamilyDiaryViewDAO;
import dao.diary.FamilyDiaryViewVO;

public class FamilyDiaryManager 
{
	private FamilyDiaryViewDAO familyDiaryViewDAO;
	private FamilyDiaryDAO familyDiaryDAO;
	private FamilyDiaryPartDAO familyDiaryPartDAO;
	
	public FamilyDiaryManager() {
		familyDiaryViewDAO = new FamilyDiaryViewDAO();
		familyDiaryDAO = new FamilyDiaryDAO();
		familyDiaryPartDAO = new FamilyDiaryPartDAO();
	}
	
	public FamilyDiaryVO[] getSimpleFamilyDiaryList(String homeCode)
	{
		return familyDiaryDAO.selectAllDiaryInfo(homeCode);
	}
	
	public FamilyDiaryViewVO[] getFamilyDiaryInfo(String familyDiaryCode)
	{
		return familyDiaryViewDAO.selectDiaryInfo(familyDiaryCode);
	}
	
	public int addFamilyDiary(String familyHomeCode, String familyMemberCode, String familyDiaryDate, String contents, String imageName,String imageWrittenDate, String emoticonCode)
	{
		String familyDiaryCode = null;
		
		FamilyDiaryVO fDiary = familyDiaryDAO.selectDiaryInfo(familyHomeCode, familyDiaryDate);	// 기존에 그 날의 가족일기가 존재하는지 검색.
		if(fDiary == null)	//없다면 새로운 가족일기 생성.
		{
			familyDiaryCode = familyDiaryDAO.insertDiary(familyHomeCode, familyDiaryDate);
		}
		else		//있다면 기존의 가족일기에 회원이 작성한 파트부분이 있는지 검사..
		{
			familyDiaryCode = fDiary.getFamilyDiaryCode();
			
			FamilyDiaryPartVO fDiaryPart = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);
			System.out.println(fDiaryPart);
			if(fDiaryPart != null)	// 작성한 파트 부분이 있다면 return 하여 수정하기 화면으로 이동하게 한다.
			{
				System.out.println("기존에 작성한게 있으면 	여기 걸리니?");
				return 2;	// 기존에 작성한 파트가 있다면 2값을 리턴하고 수정하기로 넘어간다.
			}
		}
		
		System.out.println("가족일기 파트 작성하기");
		System.out.println(familyHomeCode);
		System.out.println(contents);
		System.out.println(imageName);
		System.out.println(imageWrittenDate);
		System.out.println(emoticonCode);
		
		SotongManager sManager = new SotongManager();
		String sotongContentsCode = sManager.addSotongContents(familyHomeCode, "sc2", contents, imageName, imageWrittenDate, emoticonCode);
		
		return familyDiaryPartDAO.insertFamilyDiaryPart(familyDiaryCode, familyMemberCode, sotongContentsCode, familyDiaryDate);
	}
	
	public FamilyDiaryViewVO getFamilyDiaryPartInfo(String familyDiaryCode, String familyMemberCode)
	{
		FamilyDiaryPartVO vo = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);	// 가족일기에 회원이 작성한 파트일기가 있는지 검사
		
		if(vo != null)	//있다면 파트일기의 내용 리턴
		{
			return familyDiaryViewDAO.selectDiaryPartInfo(vo.getFamilyDiaryPartCode());
		}
		return null; //없다면 널 리턴.
	}
	
	public int updateFamilyDiaryPart(String sotongContentsCode, String contents, String imageName, String imageWrittenDate, String emoticonCode)
	{
		SotongManager sManager = new SotongManager();
		
		return sManager.updateSotongContents(sotongContentsCode, contents, imageName, imageWrittenDate, emoticonCode);
	}
	
	public int deleteFamilyDiary(String familyDiaryCode, String familyMemberCode)
	{
		SotongManager sManager = new SotongManager();	
		FamilyDiaryPartVO vo = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);	// 가족일기에 회원이 작성한 파트일기가 있는지 검사
		
		
		if(vo != null)	//가족일기에 회원이 작성한 파트일기가 있다면
		{
			String sotongContentsCode = vo.getSotongContentsCode();
			int res = familyDiaryPartDAO.delete(vo.getFamilyDiaryPartCode());	// 개인파트 삭제
			
			if(res == 1)	//삭제에 성공했다면
			{
				int res2 = sManager.deleteSotongContents(sotongContentsCode);	// 개인파트의 소통컨텐츠도 삭제
				
				if(res2 == 1)	//그 삭제도 성공했다면 가족일기에 남은 파트 일기들이 있는지 검사
				{
					FamilyDiaryPartVO[] partVO = familyDiaryPartDAO.selectAllDiaryPartInfo(familyDiaryCode);
					
					if(partVO.length == 0)	// 가족일기에 개인파트가 존재하지 않는다면 
					{
						return familyDiaryDAO.delete(familyDiaryCode);	//가족일기도 삭제
					}
					else	// 개인파트가 있다면 요청한 개인파트의 삭제는 성공했으므로 1 리턴.
					{
						return 1;	
					}
				}
			}
		}
		return 0;	
	}
}
