package kr.co.rfy.member.model.service;

import java.sql.Connection;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.member.model.dao.MemberDAO;
import kr.co.rfy.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {

	private MemberDAO mDAO = new MemberDAO();
	
	@Override
	public Member selectOneMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = mDAO.selectOneMember(userId,userPwd,conn);
		JDBCTemplate.close(conn);
		return m;
	}

}
