package kr.co.rfy.recipeBoard.model.vo;

public class Content {

	private int boardNo;
	private int contentNo;
	private String content;
	
	
	public Content() {
		super();
	}


	public Content(int boardNo, int contentNo, String content) {
		super();
		this.boardNo = boardNo;
		this.contentNo = contentNo;
		this.content = content;
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
	
	
	
	
	
}
