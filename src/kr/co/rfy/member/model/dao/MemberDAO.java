package kr.co.rfy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.member.model.vo.Member;

public class MemberDAO {

	// 회원 아이디 중복검사
	public String memberIdCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String id = "";
		String sql = "SELECT * FROM MEMBER WHERE USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				id = rset.getString("user_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return id;
	}

	// 회원가입
	public int memberJoin(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,DEFAULT,'N',DEFAULT,'N')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getUserEmail());
			pstmt.setString(5, m.getUserPhone());
			pstmt.setString(6, Character.toString(m.getAgreeYN()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 회원 로그인
	public Member memberLogin(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String sql = "SELECT * FROM MEMBER WHERE USER_ID=? AND USER_PWD=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				m = new Member();
				m.setUserNo(rset.getInt("user_no"));
				m.setUserId(rset.getString("user_id"));
				m.setUserPwd(rset.getString("user_pwd"));
				m.setUserName(rset.getString("user_name"));
				m.setUserEmail(rset.getString("user_email"));
				m.setUserPhone(rset.getString("user_phone"));
				m.setAgreeYN(rset.getString("agree_yn").charAt(0));
				m.setEnrollDate(rset.getDate("enroll_date"));
				m.setBlackYN(rset.getString("black_yn").charAt(0));
				m.setRoll(rset.getString("roll"));
				m.setEndYN(rset.getString("end_yn").charAt(0));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	// 회원 탈퇴(회원 이름, 이메일, 전화번호, 이용약관동의, 탈퇴여부 수정) 
	public int memberWithDraw(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "UPDATE MEMBER SET USER_NAME='-', USER_EMAIL='-', USER_PHONE='-', AGREE_YN='N', END_YN='Y' WHERE USER_ID=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 회원 개인정보 수정(이메일, 전화번호)
	public int memberUpdate(Connection conn, String userEmail, String userPhone, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "UPDATE MEMBER SET USER_EMAIL=?, USER_PHONE=? WHERE USER_ID=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPhone);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 회원 비밀번호 변경
	public int memberPwdChange(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "UPDATE MEMBER SET USER_PWD=? WHERE USER_ID=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 회원 이메일 중복 검사
	public String memberEmailCheck(Connection conn, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String email = "";
		String sql = "SELECT * FROM MEMBER WHERE USER_EMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				email = rset.getString("user_email");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return email;
	}

	// 회원 아이디 찾기
	public String memberFindId(String userName, String userEmail, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String userId = "";
		
		String sql = "SELECT * FROM MEMBER WHERE USER_NAME=? AND USER_EMAIL=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userId = rset.getString("user_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return userId;
	}

	// 회원 비밀번호 찾기 (임시발급된 비밀번호로 비밀번호를 업데이트)
	public int memberFindPwd(String userId, String userEmail, String tmpPwd, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "UPDATE MEMBER SET USER_PWD=? WHERE USER_ID=? AND USER_EMAIL=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tmpPwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userEmail);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
