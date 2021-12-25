package kr.co.rfy.adminRecipeBoard.model.vo;

public class RecipeImage {
	
	private int boardNo;
	private int fileNo;
	private String fileName;
	private String filePath;
	private char endYN;
	
	public RecipeImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeImage(int boardNo, int fileNo, String fileName, String filePath, char endYN) {
		super();
		this.boardNo = boardNo;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.filePath = filePath;
		this.endYN = endYN;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public char getEndYN() {
		return endYN;
	}

	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}
	
}
