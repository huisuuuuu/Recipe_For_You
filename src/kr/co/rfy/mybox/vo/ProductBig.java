package kr.co.rfy.mybox.vo;

public class ProductBig {

	private String big_code;
	private String big_name;
	
	
	public ProductBig() {
		
	}

	public ProductBig(String big_code, String big_name) {
		this.big_code = big_code;
		this.big_name = big_name;
	}

	public String getBig_code() {
		return big_code;
	}

	public void setBig_code(String big_code) {
		this.big_code = big_code;
	}

	public String getBig_name() {
		return big_name;
	}

	public void setBig_name(String big_name) {
		this.big_name = big_name;
	}

	@Override
	public String toString() {
		return "ProductBig [big_code=" + big_code + ", big_name=" + big_name + "]";
	}
	
	
	
	
	
	
	
}
