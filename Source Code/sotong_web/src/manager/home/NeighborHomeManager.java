package manager.home;

import java.util.ArrayList;

import model.bean.NeighborListBean;
import dao.connection.ConnectionWaitingDAO;
import dao.connection.ConnectionWaitingViewDAO;
import dao.connection.ConnectionWaitingViewVO;
import dao.neighbor.ConnectedNeighborDAO;
import dao.neighbor.ConnectedNeighborViewDAO;
import dao.neighbor.ConnectedNeighborViewVO;

public class NeighborHomeManager {
	private ConnectedNeighborViewDAO connectedNeighborViewDAO;
	private ConnectedNeighborDAO connectedNeighborDAO;
	private ConnectionWaitingViewDAO connectionWaitingViewDAO;
	private ConnectionWaitingDAO connectionWaitingDAO;
	
	public int countWaiting(String homeCode)
	{
		return connectionWaitingDAO.countConnectionWaiting(homeCode);
	}
	
	public NeighborHomeManager() {
		connectedNeighborViewDAO = new ConnectedNeighborViewDAO();
		connectedNeighborDAO = new ConnectedNeighborDAO();
		connectionWaitingViewDAO = new ConnectionWaitingViewDAO();
		connectionWaitingDAO = new ConnectionWaitingDAO();
		// TODO Auto-generated constructor stub
	}
	
	public int cutNeighbor(String homeCode, String neighborHomeCode)
	{
		return connectedNeighborDAO.deleteConnectedNeighbor(homeCode, neighborHomeCode);
	}
	
	public int connectNeighbor(String homeCode, String neighborHomeCode)
	{
		int check = connectionWaitingDAO.deleteConnectionWaiting(homeCode, neighborHomeCode);
		if(check == 1)
		{
			return connectedNeighborDAO.insertConnectedNeighbor(homeCode, neighborHomeCode);
		}
		else
		{
			return -1;
		}
	}
	
	public int checkNeighbor(String homeCode, String neighborHomeCode)
	{
		return connectedNeighborDAO.checkNeighbor(homeCode, neighborHomeCode);
	}
	
	public ConnectedNeighborViewVO[] getFirstConnectedNeighborList(String homeCode)
	{
		System.out.println(homeCode);
		return connectedNeighborViewDAO.selectFirstConnectedNeighborList(homeCode);
	}
	
	public ConnectedNeighborViewVO[] getSecondConnectedNeighborList(String homeCode)
	{
		return connectedNeighborViewDAO.selectSecondConnectedNeighborList(homeCode);
	}
	
	public ConnectionWaitingViewVO[] getConnectionWaitingList(String homeCode)
	{
		return connectionWaitingViewDAO.selectGetConnectionWaitingList(homeCode);
	}
	
	public ConnectionWaitingViewVO[] getConnectionSendList(String homeCode)
	{
		return connectionWaitingViewDAO.selectGetConnectionSendList(homeCode);
	}
	
	public int addConnectionWaiting(String homeCode, String neighborHomeCode)
	{
		int num =connectedNeighborDAO.checkNeighbor(homeCode, neighborHomeCode);
		if(num==1)
		{
			return -1;
		}
		else
		{
			return connectionWaitingDAO.insertConnectionWaiting(homeCode, neighborHomeCode);
		}
	}
	
	public int deleteConnectionWaiting(String waitingCode)
	{
		return connectionWaitingDAO.deleteConnectionWaiting(waitingCode);
	}
	
	public int deleteConnectionWaiting(String homeCode, String neighborHomeCode)
	{
		return connectionWaitingDAO.deleteConnectionWaiting(homeCode, neighborHomeCode);
	}
	
	public NeighborListBean[] getAllNeighborList(String homeCode)
	{
		ConnectedNeighborViewVO[] firstNeighborList = getFirstConnectedNeighborList(homeCode);
		
		//second : second_home 에 존재하는 이웃목록 (first가 파라미터 값 홈)
		ConnectedNeighborViewVO[] secondNeighborList = getSecondConnectedNeighborList(homeCode);
		
		//연결 목록
		ArrayList<NeighborListBean> neighborBeanList = new ArrayList<NeighborListBean>();
		
		if(firstNeighborList!=null)
		{
			for(ConnectedNeighborViewVO vo : firstNeighborList)
			{
				//연결 목록에 추가
				NeighborListBean bean = new NeighborListBean(vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName());
				//neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
				neighborBeanList.add(bean);
				System.out.println("11111" + bean);
			}
		}
	
		if(secondNeighborList!=null)
		{
			for(ConnectedNeighborViewVO vo : secondNeighborList)
			{
				//연결 목록에 추가
				NeighborListBean bean = new NeighborListBean(vo.getSecondHomeCode(), vo.getSecondHomeName(), vo.getSecondHomeManagerName());
				//neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
				neighborBeanList.add(bean);
				
				System.out.println("2222" + bean);
			}
		}
		
		if(neighborBeanList.size() >= 1)
		{
			return neighborBeanList.toArray(new NeighborListBean[neighborBeanList.size()]);
		}
		else
		{
			return null;
		}
	}
	
	public String[] getAllNeighborHomeCodeList(String homeCode)
	{
		ConnectedNeighborViewVO[] firstNeighborList = getFirstConnectedNeighborList(homeCode);
		
		//second : second_home 에 존재하는 이웃목록 (first가 파라미터 값 홈)
		ConnectedNeighborViewVO[] secondNeighborList = getSecondConnectedNeighborList(homeCode);
		
		//연결 목록
		ArrayList<String> neighborBeanList = new ArrayList<String>();
		
		if(firstNeighborList!=null)
		{
			for(ConnectedNeighborViewVO vo : firstNeighborList)
			{
				//연결 목록에 추가
				neighborBeanList.add(vo.getFirstHomeCode());
			}
		}
	
		if(secondNeighborList!=null)
		{
			for(ConnectedNeighborViewVO vo : secondNeighborList)
			{
				//연결 목록에 추가
				neighborBeanList.add(vo.getSecondHomeCode());
			}
		}
		
		if(neighborBeanList.size() != 0)
		{
			return neighborBeanList.toArray(new String[neighborBeanList.size()]);
		}
		else
		{
			return null;
		}
	}
	
	public static void main(String[] args)
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		System.out.println("ㅎㅎ?");
		System.out.println(manager.getFirstConnectedNeighborList("h2"));
		System.out.println("연결목록");
		NeighborListBean[] list = manager.getAllNeighborList("h2");
		
		for(int i=0; i<list.length;i++)
		{
			System.out.println(list[i].getHomeName());
		}
	}

}
