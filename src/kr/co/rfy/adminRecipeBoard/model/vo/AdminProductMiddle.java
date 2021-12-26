package kr.co.rfy.adminRecipeBoard.model.vo;

public class AdminProductMiddle {
	
	private String bigCode;
	private String middleCode;
	private String middleName;
	
	public AdminProductMiddle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminProductMiddle(String bigCode, String middleCode, String middleName) {
		super();
		this.bigCode = bigCode;
		this.middleCode = middleCode;
		this.middleName = middleName;
	}

	public String getBigCode() {
		return bigCode;
	}

	public void setBigCode(String bigCode) {
		this.bigCode = bigCode;
	}

	public String getMiddleCode() {
		return middleCode;
	}

	public void setMiddleCode(String middleCode) {
		this.middleCode = middleCode;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
}
