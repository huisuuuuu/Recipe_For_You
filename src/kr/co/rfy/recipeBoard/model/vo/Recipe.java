package kr.co.rfy.recipeBoard.model.vo;

import java.sql.Date;

public class Recipe {

	private String  boardCode;
	private int boardNo;
	private String recipeCode;
	private String levelCode;
	private String timeCode;
	private String title;
	private String subtitle;
	private String userId;
	private Date regDate;
	private int viewCount;
	private int likeNum;
	private char endYN;
	
	

	public Recipe() {
		super();
	}

	public Recipe(String boardCode, int boardNo, String recipeCode, String levelCode, String timeCode, String title,
			String subtitle, String userId, Date regDate, int viewCount, int likeNum, char endYN) {
		super();
		this.boardCode = boardCode;
		this.boardNo = boardNo;
		this.recipeCode = recipeCode;
		this.levelCode = levelCode;
		this.timeCode = timeCode;
		this.title = title;
		this.subtitle = subtitle;
		this.userId = userId;
		this.regDate = regDate;
		this.viewCount = viewCount;
		this.likeNum = likeNum;
		this.endYN = endYN;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getRecipeCode() {
		return recipeCode;
	}

	public void setRecipeCode(String recipeCode) {
		this.recipeCode = recipeCode;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public char getEndYN() {
		return endYN;
	}

	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}
	
	
	
	
	
	
	
	
	
}
