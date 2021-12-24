package kr.co.rfy.recipeBoard.model.vo;

import java.sql.Date;

public class RecipeDetail {

	
	private int boardNo;
	private String userId;
	private String title;
	private String subTitle;
	private int likeNum;
	
	
	public RecipeDetail() {
		super();
	}


	public RecipeDetail(int boardNo, String userId, String title, String subTitle, int likeNum) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.title = title;
		this.subTitle = subTitle;
		this.likeNum = likeNum;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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


	public int getLikeNum() {
		return likeNum;
	}


	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	
	
	
	
}
