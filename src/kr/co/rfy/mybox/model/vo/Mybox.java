package kr.co.rfy.mybox.model.vo;

import java.util.Date;

public class Mybox {

	private int my_box_no; 
	private String user_id; 
	private String ingredient_code;
	private String ingredient_name;
	private String end_date;
	private String memo; 
	private String end_yn;
	
	private String middle_code;
	private String middle_name;
	private String big_code;
	private String big_name;
	
	
	public Mybox() {
		
	}

	
	
	
	

	public Mybox(String user_id, String ingredient_code, String ingredient_name, String end_date, String memo) {
		this.user_id = user_id;
		this.ingredient_code = ingredient_code;
		this.ingredient_name = ingredient_name;
		this.end_date = end_date;
		this.memo = memo;
	}



	



	public Mybox(int my_box_no, String user_id, String ingredient_code, String ingredient_name, String end_date,
			String memo, String end_yn, String middle_code, String middle_name, String big_code, String big_name) {
		this.my_box_no = my_box_no;
		this.user_id = user_id;
		this.ingredient_code = ingredient_code;
		this.ingredient_name = ingredient_name;
		this.end_date = end_date;
		this.memo = memo;
		this.end_yn = end_yn;
		this.middle_code = middle_code;
		this.middle_name = middle_name;
		this.big_code = big_code;
		this.big_name = big_name;
	}






	public int getMy_box_no() {
		return my_box_no;
	}






	public Mybox(int my_box_no, String user_id, String ingredient_code, String ingredient_name, String end_date,
			String memo, String end_yn) {
		this.my_box_no = my_box_no;
		this.user_id = user_id;
		this.ingredient_code = ingredient_code;
		this.ingredient_name = ingredient_name;
		this.end_date = end_date;
		this.memo = memo;
		this.end_yn = end_yn;
	}






	public void setMy_box_no(int my_box_no) {
		this.my_box_no = my_box_no;
	}




	public String getUser_id() {
		return user_id;
	}




	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}




	public String getIngredient_code() {
		return ingredient_code;
	}




	public void setIngredient_code(String ingredient_code) {
		this.ingredient_code = ingredient_code;
	}




	public String getIngredient_name() {
		return ingredient_name;
	}




	public void setIngredient_name(String ingredient_name) {
		this.ingredient_name = ingredient_name;
	}




	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}




	public String getMemo() {
		return memo;
	}




	public void setMemo(String memo) {
		this.memo = memo;
	}




	public String getEnd_yn() {
		return end_yn;
	}




	public void setEnd_yn(String end_yn) {
		this.end_yn = end_yn;
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
		return "Mybox [my_box_no=" + my_box_no + ", user_id=" + user_id + ", ingredient_code=" + ingredient_code
				+ ", ingredient_name=" + ingredient_name + ", end_date=" + end_date + ", memo=" + memo + ", end_yn="
				+ end_yn + "]";
	}
	
	

	
	
	
}
