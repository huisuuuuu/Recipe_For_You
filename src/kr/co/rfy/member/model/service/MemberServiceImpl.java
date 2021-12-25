package kr.co.rfy.member.model.service;

import java.sql.Connection;

import kr.co.rfy.commons.JDBCTemplate;
import kr.co.rfy.member.model.dao.MemberDAO;
import kr.co.rfy.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {
	MemberDAO mDAO = new MemberDAO();
	@Override
	public String memberIdCheck(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		String id = mDAO.memberIdCheck(conn, userId);
		return id;
	}
	@Override
	public int memberJoin(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.memberJoin(conn, m);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	@Override
	public Member memberLogin(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = mDAO.memberLogin(conn, userId, userPwd);
		JDBCTemplate.close(conn);
		return m;
	}
	@Override
	public int memberWithDraw(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.memberWithDraw(conn, userId);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	@Override
	public int memberUpdate(String userEmail, String userPhone, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.memberUpdate(conn, userEmail, userPhone, userId);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	@Override
	public int memberPwdChange(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.memberPwdChange(conn, userId, userPwd);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	@Override
	public String emailCheck(String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		String email = mDAO.memberEmailCheck(conn, userEmail);
		JDBCTemplate.close(conn);
		return email;
	}
	@Override
	public String memberFindId(String userName, String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		String userId = mDAO.memberFindId(userName, userEmail, conn);
		JDBCTemplate.close(conn);
		return userId;
	}
	@Override
	public int memberFindPwd(String userId, String userEmail, String tmpPwd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = mDAO.memberFindPwd(userId, userEmail, tmpPwd, conn);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

}
