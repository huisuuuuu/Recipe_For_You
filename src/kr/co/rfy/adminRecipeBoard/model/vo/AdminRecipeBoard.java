package kr.co.rfy.adminRecipeBoard.model.vo;

import java.sql.Date;

public class AdminRecipeBoard {
	
	private String boardCode;
	private int boardNo;
	private String recipeCode;
	private String levelCode;
	private String timeCode;
	private String title;
	private String subTitle;
	private String userId;
	private Date regDate;
	private int likeNum;
	private int viewCount;
	private char endYN;
	private String userName;
	private String recipeName;
	
	
	public AdminRecipeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AdminRecipeBoard(String boardCode, int boardNo, String recipeCode, String levelCode, String timeCode,
			String title, String subTitle, String userId, Date regDate, int likeNum, int viewCount, char endYN,
			String userName, String recipeName) {
		super();
		this.boardCode = boardCode;
		this.boardNo = boardNo;
		this.recipeCode = recipeCode;
		this.levelCode = levelCode;
		this.timeCode = timeCode;
		this.title = title;
		this.subTitle = subTitle;
		this.userId = userId;
		this.regDate = regDate;
		this.likeNum = likeNum;
		this.viewCount = viewCount;
		this.endYN = endYN;
		this.userName = userName;
		this.recipeName = recipeName;
	}


	public AdminRecipeBoard(int boardNo, String title, Date regDate, String userName, String recipeName) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.regDate = regDate;
		this.userName = userName;
		this.recipeName = recipeName;
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


	public String getSubTitle() {
		return subTitle;
	}


	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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


	public int getLikeNum() {
		return likeNum;
	}


	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}


	public int getViewCount() {
		return viewCount;
	}


	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}


	public char getEndYN() {
		return endYN;
	}


	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
}
