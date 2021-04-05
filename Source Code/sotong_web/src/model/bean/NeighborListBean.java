package model.bean;

public class NeighborListBean {
	private String homeCode;
	private String homeName;
	private String managerName;
	
	public NeighborListBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NeighborListBean(String homeCode, String homeName, String managerName) {
		super();
		this.homeCode = homeCode;
		this.homeName = homeName;
		this.managerName = managerName;
	}
	public String getHomeCode() {
		return homeCode;
	}
	public void setHomeCode(String homeCode) {
		this.homeCode = homeCode;
	}
	public String getHomeName() {
		return homeName;
	}
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	@Override
	public String toString() {
		return "NeighborListBean [homeCode=" + homeCode + ", homeName="
				+ homeName + ", managerName=" + managerName + "]";
	}
	
	

}
