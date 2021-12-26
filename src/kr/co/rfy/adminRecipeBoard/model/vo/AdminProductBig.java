package kr.co.rfy.adminRecipeBoard.model.vo;

public class AdminProductBig {
	
	private String bigCode;
	private String bigName;
	
	public AdminProductBig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminProductBig(String bigCode, String bigName) {
		super();
		this.bigCode = bigCode;
		this.bigName = bigName;
	}

	public String getBigCode() {
		return bigCode;
	}

	public void setBigCode(String bigCode) {
		this.bigCode = bigCode;
	}

	public String getBigName() {
		return bigName;
	}

	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
	
}
