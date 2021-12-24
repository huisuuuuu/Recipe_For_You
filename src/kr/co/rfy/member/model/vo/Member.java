package kr.co.rfy.member.model.vo;

import java.sql.Date;

public class Member {

	private int user_No;
	private String user_Id;
	private String user_Pwd;
	private String user_Name;
	private String user_Email;
	private String user_Phone;
	private char agree_YN;
	private Date enroll_date;
	private char black_YN;
	private String roll;
	private char end_YN;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Member [user_No=" + user_No + ", user_Id=" + user_Id + ", user_Pwd=" + user_Pwd + ", user_Name="
				+ user_Name + ", user_Email=" + user_Email + ", user_Phone=" + user_Phone + ", agree_YN=" + agree_YN
				+ ", enroll_date=" + enroll_date + ", black_YN=" + black_YN + ", roll=" + roll + ", end_YN=" + end_YN
				+ "]";
	}
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int user_No, String user_Id, String user_Pwd, String user_Name, String user_Email, String user_Phone,
			char agree_YN, Date enroll_date, char black_YN, String roll, char end_YN) {
		super();
		this.user_No = user_No;
		this.user_Id = user_Id;
		this.user_Pwd = user_Pwd;
		this.user_Name = user_Name;
		this.user_Email = user_Email;
		this.user_Phone = user_Phone;
		this.agree_YN = agree_YN;
		this.enroll_date = enroll_date;
		this.black_YN = black_YN;
		this.roll = roll;
		this.end_YN = end_YN;
	}
	public int getUser_No() {
		return user_No;
	}
	public void setUser_No(int user_No) {
		this.user_No = user_No;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getUser_Pwd() {
		return user_Pwd;
	}
	public void setUser_Pwd(String user_Pwd) {
		this.user_Pwd = user_Pwd;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Email() {
		return user_Email;
	}
	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}
	public String getUser_Phone() {
		return user_Phone;
	}
	public void setUser_Phone(String user_Phone) {
		this.user_Phone = user_Phone;
	}
	public char getAgree_YN() {
		return agree_YN;
	}
	public void setAgree_YN(char agree_YN) {
		this.agree_YN = agree_YN;
	}
	public Date getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}
	public char getBlack_YN() {
		return black_YN;
	}
	public void setBlack_YN(char black_YN) {
		this.black_YN = black_YN;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public char getEnd_YN() {
		return end_YN;
	}
	public void setEnd_YN(char end_YN) {
		this.end_YN = end_YN;
	}
	
	
	
	
	
}
