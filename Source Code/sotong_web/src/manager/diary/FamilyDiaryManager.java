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
		
		FamilyDiaryVO fDiary = familyDiaryDAO.selectDiaryInfo(familyHomeCode, familyDiaryDate);	// ������ �� ���� �����ϱⰡ �����ϴ��� �˻�.
		if(fDiary == null)	//���ٸ� ���ο� �����ϱ� ����.
		{
			familyDiaryCode = familyDiaryDAO.insertDiary(familyHomeCode, familyDiaryDate);
		}
		else		//�ִٸ� ������ �����ϱ⿡ ȸ���� �ۼ��� ��Ʈ�κ��� �ִ��� �˻�..
		{
			familyDiaryCode = fDiary.getFamilyDiaryCode();
			
			FamilyDiaryPartVO fDiaryPart = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);
			System.out.println(fDiaryPart);
			if(fDiaryPart != null)	// �ۼ��� ��Ʈ �κ��� �ִٸ� return �Ͽ� �����ϱ� ȭ������ �̵��ϰ� �Ѵ�.
			{
				System.out.println("������ �ۼ��Ѱ� ������ 	���� �ɸ���?");
				return 2;	// ������ �ۼ��� ��Ʈ�� �ִٸ� 2���� �����ϰ� �����ϱ�� �Ѿ��.
			}
		}
		
		System.out.println("�����ϱ� ��Ʈ �ۼ��ϱ�");
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
		FamilyDiaryPartVO vo = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);	// �����ϱ⿡ ȸ���� �ۼ��� ��Ʈ�ϱⰡ �ִ��� �˻�
		
		if(vo != null)	//�ִٸ� ��Ʈ�ϱ��� ���� ����
		{
			return familyDiaryViewDAO.selectDiaryPartInfo(vo.getFamilyDiaryPartCode());
		}
		return null; //���ٸ� �� ����.
	}
	
	public int updateFamilyDiaryPart(String sotongContentsCode, String contents, String imageName, String imageWrittenDate, String emoticonCode)
	{
		SotongManager sManager = new SotongManager();
		
		return sManager.updateSotongContents(sotongContentsCode, contents, imageName, imageWrittenDate, emoticonCode);
	}
	
	public int deleteFamilyDiary(String familyDiaryCode, String familyMemberCode)
	{
		SotongManager sManager = new SotongManager();	
		FamilyDiaryPartVO vo = familyDiaryPartDAO.selectDiaryInfo(familyDiaryCode, familyMemberCode);	// �����ϱ⿡ ȸ���� �ۼ��� ��Ʈ�ϱⰡ �ִ��� �˻�
		
		
		if(vo != null)	//�����ϱ⿡ ȸ���� �ۼ��� ��Ʈ�ϱⰡ �ִٸ�
		{
			String sotongContentsCode = vo.getSotongContentsCode();
			int res = familyDiaryPartDAO.delete(vo.getFamilyDiaryPartCode());	// ������Ʈ ����
			
			if(res == 1)	//������ �����ߴٸ�
			{
				int res2 = sManager.deleteSotongContents(sotongContentsCode);	// ������Ʈ�� ������������ ����
				
				if(res2 == 1)	//�� ������ �����ߴٸ� �����ϱ⿡ ���� ��Ʈ �ϱ���� �ִ��� �˻�
				{
					FamilyDiaryPartVO[] partVO = familyDiaryPartDAO.selectAllDiaryPartInfo(familyDiaryCode);
					
					if(partVO.length == 0)	// �����ϱ⿡ ������Ʈ�� �������� �ʴ´ٸ� 
					{
						return familyDiaryDAO.delete(familyDiaryCode);	//�����ϱ⵵ ����
					}
					else	// ������Ʈ�� �ִٸ� ��û�� ������Ʈ�� ������ ���������Ƿ� 1 ����.
					{
						return 1;	
					}
				}
			}
		}
		return 0;	
	}
}
