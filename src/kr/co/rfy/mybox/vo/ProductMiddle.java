package kr.co.rfy.mybox.vo;

public class ProductMiddle {

	private String big_code;
	private String middle_code;
	private String middle_name;
	
	public ProductMiddle() {
		
	}
	
	public ProductMiddle(String big_code, String middle_code, String middle_name) {
		this.big_code = big_code;
		this.middle_code = middle_code;
		this.middle_name = middle_name;
	}

	public String getBig_code() {
		return big_code;
	}

	public void setBig_code(String big_code) {
		this.big_code = big_code;
	}

	public String getMiddle_code() {
		return middle_code;
	}

	public void setMiddle_code(String middle_code) {
		this.middle_code = middle_code;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	@Override
	public String toString() {
		return "ProductMiddle [big_code=" + big_code + ", middle_code=" + middle_code + ", middle_name=" + middle_name
				+ "]";
	}
	
	
	
	
}
