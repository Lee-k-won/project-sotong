package dao.neighbor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class ConnectedNeighborDAO implements Serializable{

	private static final long serialVersionUID = 6811404947402608685L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public ConnectedNeighborDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeNeighborCode(){ //임시코드
		GregorianCalendar cal = new GregorianCalendar();
		
		String neighborCode = "CN09" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return neighborCode;	
	}
	
	public int insertConnectedNeighbor(String neighbor_code, String home1_code, String home2_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into connected_neighbor_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, neighbor_code);
			pstmt.setString(2, home1_code);
			pstmt.setString(3, home2_code);
			rowNum = pstmt.executeUpdate();
			
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
		return rowNum;
	}
	public int insertConnectedNeighbor(String home1_code, String home2_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into connected_neighbor_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, makeNeighborCode());
			pstmt.setString(2, home1_code);
			pstmt.setString(3, home2_code);
			rowNum = pstmt.executeUpdate();
			
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
		return rowNum;
	}
	
	public int deleteConnectedNeighbor(String neighbor_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from connected_neighbor_tb where neighbor_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, neighbor_code);
			rowNum = pstmt.executeUpdate();
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
		return rowNum;
	}
	
	public int deleteConnectedNeighbor(String homeCode, String neighborHomeCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from connected_neighbor_tb where (home1_code=? AND home2_code=?) OR (home1_code=? AND home2_code=?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, homeCode);
			pstmt.setString(2, neighborHomeCode);
			pstmt.setString(3, neighborHomeCode);
			pstmt.setString(4, homeCode);
			
			rowNum = pstmt.executeUpdate();
			
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
		return rowNum;
	}
	
	public ConnectedNeighborVO[] selectConnectedNeighborList(String homeCode){
		ConnectedNeighborVO vo[] = null;
		PreparedStatement pstmt = null;
		ArrayList<ConnectedNeighborVO> list = new ArrayList<ConnectedNeighborVO>();
		
		try{
			String sql = "select * from connected_neighbor_tb where home1_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String neighborCode1 = rs.getString("neighbor_code");
				String home1Code1 = rs.getString("home1_code");
				String home2Code1 = rs.getString("home2_code");
				
				list.add(new ConnectedNeighborVO(neighborCode1, home1Code1, home2Code1));
			}
			
			vo = new ConnectedNeighborVO[list.size()];
			for(int cnt=0; cnt<list.size(); cnt++){
				vo[cnt] = list.get(cnt);
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
		
		
		return vo;
	}
	
	public int checkNeighbor(String homeCode, String neighborHomeCode){
		int rowNum = 0;
	      PreparedStatement pstmt = null;
	      try{
	         String sql = "select neighbor_code from connected_neighbor_tb where (home1_code=? AND home2_code=?) OR (home1_code=? AND home2_code=?)";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, homeCode);
	         pstmt.setString(2, neighborHomeCode);
	         pstmt.setString(3, neighborHomeCode);
	         pstmt.setString(4, homeCode);
	         
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()){
	            String neighbor_code1 = rs.getString("neighbor_code");
	            
	            if(neighbor_code1 != null){
	               rowNum = 1;
	            }else{
	               rowNum = 0;
	            }
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
		return rowNum;
		
	}
	public static void main(String[] args) {
		ConnectedNeighborDAO dao = new ConnectedNeighborDAO();
		//dao.insertConnectedNeighbor("cn2", "h1","h3");
		//dao.deleteConnectedNeighbor("cn11");
		//dao.deleteConnectedNeighbor("h1", "h3");
		//System.out.println(dao.checkNeighbor("h1", "h2"));
		
		/*
		ConnectedNeighborVO vo[] = dao.selectConnectedNeighborList("h1");
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
		*/
	}
	
	
}
