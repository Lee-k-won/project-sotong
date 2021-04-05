package manager.story;

import java.util.ArrayList;
import java.util.Collections;

import manager.SotongManager;
import manager.home.NeighborHomeManager;
import model.bean.CustomComparator;
import dao.story.StoryDAO;
import dao.story.StoryViewDAO;
import dao.story.StoryViewVO;

public class StoryManager {
	private StoryDAO storyDAO;
	private StoryViewDAO storyViewDAO;
	
	public StoryManager() {
		this.storyDAO = new StoryDAO();
		this.storyViewDAO = new StoryViewDAO();
	}
	
	public StoryViewVO[] getStoryList(String homeCode) {
		return this.storyViewDAO.getStoryList(homeCode);
	}
	
	public StoryViewVO[] getNeighborStoryList(String homeCode) {
		
		NeighborHomeManager NHManager = new NeighborHomeManager();
		String[] homeCodeList = NHManager.getAllNeighborHomeCodeList(homeCode);
		
		ArrayList<StoryViewVO> neighborStoryList = new ArrayList<StoryViewVO>();
		
		this.storyViewDAO.getNeighborStoryList(homeCode, neighborStoryList);
		
		System.out.println("접속한 홈 코드 : " + homeCode);
		if (homeCodeList != null) {
			for (String neighborHomeCode : homeCodeList) {
				System.out.println("연결된 홈 코드 : " + neighborHomeCode);
				this.storyViewDAO.getNeighborStoryList(neighborHomeCode, neighborStoryList);
			}
		} 
		if (neighborStoryList != null) {
			Collections.sort(neighborStoryList, new CustomComparator());
			
			return neighborStoryList.toArray(new StoryViewVO[neighborStoryList.size()]);
		}
		else {
			return null;
		}
		
		
	}

	public boolean addStory(String homeCode, String memberCode, String imageName,  
			String contents, String emoticonCode, String storyDate, String scope) {
		
		SotongManager sManager = new SotongManager();
		String sotongContentsCode = sManager.addSotongContents(homeCode, "sc2", contents,  imageName, storyDate, emoticonCode);
		
		return storyDAO.insertStory(homeCode, memberCode, sotongContentsCode, storyDate, scope);
	}
	
	public boolean deleteStory(String homeCode, int storyIndex) {
		SotongManager sManager = new SotongManager();
		String storyCode = this.storyViewDAO.storyIndexCode(homeCode, storyIndex);
	
		if (storyCode == null) {
			System.out.println(" 코드를 찾지 못했다.");
			return false;
		} else {
			System.out.println(storyCode);
		}
		
		String sotongContentsCode = this.storyViewDAO.getSotongContentsCode(storyCode);
		if (sotongContentsCode == null) {
			System.out.println(" 소통 코드를 찾지 못했다.");
			return false;
		} else {
			System.out.println(sotongContentsCode);
		}
		
		int checkDelete = storyDAO.deleteCode(storyCode);
		
		sManager.deleteSotongContents(sotongContentsCode);
		
		System.out.println(checkDelete);
		if (checkDelete != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int deleteStory(String storyCode, String sotongContentsCode) {
		SotongManager sManager = new SotongManager();
		storyDAO.deleteCode(storyCode);
		return sManager.deleteSotongContents(sotongContentsCode);
	}
	
	public int updateStory(String sotongContentsCode, String modifyContents, String storyCode, String scope, String modifyDate) {
		SotongManager sManager = new SotongManager();
		int checkSotongContents = sManager.updateContents(sotongContentsCode, modifyContents);
		if (checkSotongContents != 0) {
			System.out.println("소통 이야기 수정은 되었다.");
			return this.storyDAO.storyPublicScope(storyCode, scope, modifyDate);
		} else {
			System.out.println("소통 내용을 수정 실패 했다.. 0 return 한다.");
			return 0;
		}
	}
	
	public int updateHeart(String storyCode, int heart){
		return this.storyDAO.incrementStoryHeart(storyCode, heart);
	}
}
