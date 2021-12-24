package kr.co.rfy.qna.model.vo;

import java.sql.Date;

public class Qna {
	private String board_code;
	private String q_code;
	private int board_no;
	private String title;
	private String content;
	private String user_id;
	private Date regDate;
	private char end_yn;
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	public Qna(String board_code, String q_code, int board_no, String title, String content, String user_id,
			Date regDate, char end_yn) {
		super();
		this.board_code = board_code;
		this.q_code = q_code;
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.regDate = regDate;
		this.end_yn = end_yn;
	}






	@Override
	public String toString() {
		return "Qna [board_code=" + board_code + ", q_code=" + q_code + ", board_no=" + board_no + ", title=" + title
				+ ", content=" + content + ", user_id=" + user_id + ", regDate=" + regDate + ", end_yn=" + end_yn
				+ ", getBoard_code()=" + getBoard_code() + ", getQ_code()=" + getQ_code() + ", getBoard_no()="
				+ getBoard_no() + ", getTitle()=" + getTitle() + ", getContent()=" + getContent() + ", getUser_id()="
				+ getUser_id() + ", getRegDate()=" + getRegDate() + ", getEnd_yn()=" + getEnd_yn() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}



	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getQ_code() {
		return q_code;
	}
	public void setQ_code(String q_code) {
		this.q_code = q_code;
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
	
	
}
