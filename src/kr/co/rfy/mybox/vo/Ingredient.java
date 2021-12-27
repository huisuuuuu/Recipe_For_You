package kr.co.rfy.mybox.vo;

public class Ingredient {

	
	private String middle_code;
	private String ingredient_code;
	private String ingredient_name;
	public Ingredient() {
	}
	public Ingredient(String middle_code, String ingredient_code, String ingredient_name) {
		this.middle_code = middle_code;
		this.ingredient_code = ingredient_code;
		this.ingredient_name = ingredient_name;
	}
	public String getMiddle_code() {
		return middle_code;
	}
	public void setMiddle_code(String middle_code) {
		this.middle_code = middle_code;
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
	@Override
	public String toString() {
		return "Ingredient [middle_code=" + middle_code + ", ingredient_code=" + ingredient_code + ", ingredient_name="
				+ ingredient_name + "]";
	}
	
	
}
