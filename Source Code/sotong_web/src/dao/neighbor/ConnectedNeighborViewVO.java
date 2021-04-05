package dao.neighbor;

import java.io.Serializable;

public class ConnectedNeighborViewVO implements Serializable{

	private static final long serialVersionUID = 6289853542299444159L;
	private String neighborCode;
	private String firstHomeCode;
	private String firstHomeName;
	private String firstHomeManagerName;
	private String secondHomeCode;
	private String secondHomeName;
	private String secondHomeManagerName;
		
	public ConnectedNeighborViewVO() {
		super();
	}

	public ConnectedNeighborViewVO(String neighborCode, String firstHomeCode,
			String firstHomeName, String firstHomeManagerName,
			String secondHomeCode, String secondHomeName,
			String secondHomeManagerName) {
		super();
		this.neighborCode = neighborCode;
		this.firstHomeCode = firstHomeCode;
		this.firstHomeName = firstHomeName;
		this.firstHomeManagerName = firstHomeManagerName;
		this.secondHomeCode = secondHomeCode;
		this.secondHomeName = secondHomeName;
		this.secondHomeManagerName = secondHomeManagerName;
	}

	public String getNeighborCode() {
		return neighborCode;
	}

	public String getFirstHomeCode() {
		return firstHomeCode;
	}

	public String getFirstHomeName() {
		return firstHomeName;
	}

	public String getFirstHomeManagerName() {
		return firstHomeManagerName;
	}

	public String getSecondHomeCode() {
		return secondHomeCode;
	}

	public String getSecondHomeName() {
		return secondHomeName;
	}

	public String getSecondHomeManagerName() {
		return secondHomeManagerName;
	}

	public void setNeighborCode(String neighborCode) {
		this.neighborCode = neighborCode;
	}

	public void setFirstHomeCode(String firstHomeCode) {
		this.firstHomeCode = firstHomeCode;
	}

	public void setFirstHomeName(String firstHomeName) {
		this.firstHomeName = firstHomeName;
	}

	public void setFirstHomeManagerName(String firstHomeManagerName) {
		this.firstHomeManagerName = firstHomeManagerName;
	}

	public void setSecondHomeCode(String secondHomeCode) {
		this.secondHomeCode = secondHomeCode;
	}

	public void setSecondHomeName(String secondHomeName) {
		this.secondHomeName = secondHomeName;
	}

	public void setSecondHomeManagerName(String secondHomeManagerName) {
		this.secondHomeManagerName = secondHomeManagerName;
	}

	@Override
	public String toString() {
		return "ConnectedNeighborViewVO [neighborCode=" + neighborCode
				+ ", firstHomeCode=" + firstHomeCode + ", firstHomeName="
				+ firstHomeName + ", firstHomeManagerName="
				+ firstHomeManagerName + ", secondHomeCode=" + secondHomeCode
				+ ", secondHomeName=" + secondHomeName
				+ ", secondHomeManagerName=" + secondHomeManagerName + "]";
	}
	
	
}
