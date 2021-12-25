package kr.co.rfy.recipeBoard.model.vo;

public class Ingredient {

	private int baordNo;
	private String ingredientName;
	private String ingredientNum;
	
	
	
	public Ingredient() {
		super();
	}


	public Ingredient(int baordNo, String ingredientName, String ingredientNum) {
		super();
		this.baordNo = baordNo;
		this.ingredientName = ingredientName;
		this.ingredientNum = ingredientNum;
	}


	public int getBaordNo() {
		return baordNo;
	}


	public void setBaordNo(int baordNo) {
		this.baordNo = baordNo;
	}


	public String getIngredientName() {
		return ingredientName;
	}


	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}


	public String getIngredientNum() {
		return ingredientNum;
	}


	public void setIngredientNum(String ingredientNum) {
		this.ingredientNum = ingredientNum;
	}
	
	
	
	
	
}
