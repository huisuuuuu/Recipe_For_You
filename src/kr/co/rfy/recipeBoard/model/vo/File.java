package kr.co.rfy.recipeBoard.model.vo;

public class File {
	
	private int boardNo;
	private int fileNo;
	private String fileName;
	private String filePath;
	
	
	public File() {
		super();
	}

	public File(int boardNo, int fileNo, String fileName, String filePath) {
		super();
		this.boardNo = boardNo;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.filePath = filePath;
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
	
	
	
}
