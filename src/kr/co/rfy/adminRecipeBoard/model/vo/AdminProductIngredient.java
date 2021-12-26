package kr.co.rfy.adminRecipeBoard.model.vo;

public class AdminProductIngredient {
	
	private String middleCode;
	private String ingredientCode;
	private String ingredientName;
	
	public AdminProductIngredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminProductIngredient(String middleCode, String ingredientCode, String ingredientName) {
		super();
		this.middleCode = middleCode;
		this.ingredientCode = ingredientCode;
		this.ingredientName = ingredientName;
	}

	public String getMiddleCode() {
		return middleCode;
	}

	public void setMiddleCode(String middleCode) {
		this.middleCode = middleCode;
	}

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	
}	
