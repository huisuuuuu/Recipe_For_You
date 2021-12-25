package kr.co.rfy.adminRecipeBoard.model.vo;

public class RecipeIngredient {

	private int boardNo;
	private String ingredientName;
	private String ingredientNum;
	private char endYN;
	
	public RecipeIngredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeIngredient(int boardNo, String ingredientName, String ingredientNum, char endYN) {
		super();
		this.boardNo = boardNo;
		this.ingredientName = ingredientName;
		this.ingredientNum = ingredientNum;
		this.endYN = endYN;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public char getEndYN() {
		return endYN;
	}

	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}
	
	
}
