package kr.co.rfy.member.model.vo;

import java.sql.Date;

public class Member {

	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userEmail;
	private String userPhone;
	private char agreeYN;
	private Date enrollDate;
	private char blackYN;
	private String roll;
	private char endYN;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(int userNo, String userId, String userPwd, String userName, String userEmail, String userPhone,
			char agreeYN, Date enrollDate, char blackYN, String roll, char endYN) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.agreeYN = agreeYN;
		this.enrollDate = enrollDate;
		this.blackYN = blackYN;
		this.roll = roll;
		this.endYN = endYN;
	}

	public Member(String userId, String userPwd, String userName, String userEmail, String userPhone, char agreeYN) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.agreeYN = agreeYN;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public char getAgreeYN() {
		return agreeYN;
	}

	public void setAgreeYN(char agreeYN) {
		this.agreeYN = agreeYN;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public char getBlackYN() {
		return blackYN;
	}

	public void setBlackYN(char blackYN) {
		this.blackYN = blackYN;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public char getEndYN() {
		return endYN;
	}

	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}
	
	
}
