package kr.co.rfy.notice.model.vo;

import java.sql.Date;

public class Notice {
	private String board_code;
	private int board_no;
	private String title;
	private String content;
	private String user_id;
	private Date regDate;
	private char end_yn;
	private int view_count;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(String board_code, int board_no, String title, String content, String user_id, Date regDate,
			char end_yn, int view_count) {
		super();
		this.board_code = board_code;
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.regDate = regDate;
		this.end_yn = end_yn;
		this.view_count = view_count;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public char getEnd_yn() {
		return end_yn;
	}
	public void setEnd_yn(char end_yn) {
		this.end_yn = end_yn;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	
}
