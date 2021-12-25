package kr.co.rfy.adminRecipeBoard.model.vo;

public class RecipeContent {
	
	private int boardNo;
	private int contentNo;
	private String content;
	private char endYN;
	
	public RecipeContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeContent(int boardNo, int contentNo, String content, char endYN) {
		super();
		this.boardNo = boardNo;
		this.contentNo = contentNo;
		this.content = content;
		this.endYN = endYN;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getContentNo() {
		return contentNo;
	}

	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public char getEndYN() {
		return endYN;
	}

	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}
	
}
