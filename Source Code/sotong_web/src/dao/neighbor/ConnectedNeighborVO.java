package dao.neighbor;

import java.io.Serializable;

public class ConnectedNeighborVO implements Serializable{

	private static final long serialVersionUID = 2536148912812182291L;
	private String neighborCode;
	private String home1Code;
	private String home2Code;
	
	public ConnectedNeighborVO() {
		super();
	}

	public ConnectedNeighborVO(String neighborCode, String home1Code,
			String home2Code) {
		super();
		this.neighborCode = neighborCode;
		this.home1Code = home1Code;
		this.home2Code = home2Code;
	}

	public String getNeighborCode() {
		return neighborCode;
	}

	public String getHome1Code() {
		return home1Code;
	}

	public String getHome2Code() {
		return home2Code;
	}

	public void setNeighborCode(String neighborCode) {
		this.neighborCode = neighborCode;
	}

	public void setHome1Code(String home1Code) {
		this.home1Code = home1Code;
	}

	public void setHome2Code(String home2Code) {
		this.home2Code = home2Code;
	}

	@Override
	public String toString() {
		return "ConnectedNeighborVO [neighborCode=" + neighborCode
				+ ", home1Code=" + home1Code + ", home2Code=" + home2Code + "]";
	}

	
	
}
