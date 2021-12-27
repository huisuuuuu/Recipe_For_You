package kr.co.rfy.recipeBoard.model.vo;

public class MyboxIngredient {

	private String userId;
	private String ingredientCode; 
	private String ingredientName;
	
	
	public MyboxIngredient() {
		super();
	}


	public MyboxIngredient(String userId, String ingredientCode, String ingredientName) {
		super();
		this.userId = userId;
		this.ingredientCode = ingredientCode;
		this.ingredientName = ingredientName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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
