package dao.neighbor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnectionModule;

public class ConnectedNeighborViewDAO implements Serializable{

	private static final long serialVersionUID = 7477490366272546016L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public ConnectedNeighborViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public ConnectedNeighborViewVO[] selectFirstConnectedNeighborList(String homeCode){
		ConnectedNeighborViewVO []vo = null;
		PreparedStatement pstmt = null;
		ArrayList<ConnectedNeighborViewVO> list = new ArrayList<ConnectedNeighborViewVO>();
		
		
		try{
			System.out.println("DAO에서 홈코드 찍기:" + homeCode);
			String sql = "select * from connected_neighbor_view where \"second_home_code\"=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String neighborCode1 = rs.getString("neighbor_code");
				String firstHomeCode1 = rs.getString("first_home_code");
				String firstHomeName1 = rs.getString("first_home_name");
				String firstManagerName1 = rs.getString("first_manager_name");
				String secondHomeCode1 = rs.getString("second_home_code");
				String secondHomeName1 = rs.getString("second_home_name");
				String secondManagerName1 = rs.getString("second_manager_name");
				
				list.add(new ConnectedNeighborViewVO(neighborCode1, firstHomeCode1, firstHomeName1, firstManagerName1, secondHomeCode1, secondHomeName1, secondManagerName1));	 
						
			}
			
//			vo = new ConnectedNeighborViewVO[list.size()];
//			for(int cnt=0; cnt<list.size(); cnt++){
//				vo[cnt] = list.get(cnt);
//			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		if(list ==null || list.size()<1)
		{
			return null;
		}
		else
		{
			vo = list.toArray(new ConnectedNeighborViewVO[list.size()]);
			return vo;
		}
		
	}
	
	public ConnectedNeighborViewVO[] selectSecondConnectedNeighborList(String homeCode){
		ConnectedNeighborViewVO []vo = null;
		PreparedStatement pstmt = null;
		ArrayList<ConnectedNeighborViewVO> list = new ArrayList<ConnectedNeighborViewVO>();
		try{
			String sql = "select * from connected_neighbor_view where \"first_home_code\"=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String neighborCode1 = rs.getString("neighbor_code");
				String firstHomeCode1 = rs.getString("first_home_code");
				String firstHomeName1 = rs.getString("first_home_name");
				String firstManagerName1 = rs.getString("first_manager_name");
				String secondHomeCode1 = rs.getString("second_home_code");
				String secondHomeName1 = rs.getString("second_home_name");
				String secondManagerName1 = rs.getString("second_manager_name");
				
				list.add(new ConnectedNeighborViewVO(neighborCode1, firstHomeCode1, firstHomeName1, firstManagerName1, secondHomeCode1, secondHomeName1, secondManagerName1));	 
						
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		if(list ==null || list.size()<1)
		{
			return null;
		}
		else
		{
			vo = list.toArray(new ConnectedNeighborViewVO[list.size()]);
			return vo;
		}
		
	}
	
	public static void main(String[] args) {
		ConnectedNeighborViewDAO dao = new ConnectedNeighborViewDAO();
		
		ConnectedNeighborViewVO vo[] = dao.selectFirstConnectedNeighborList("h2");
		System.out.println(vo.length);
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
	}
	
}
