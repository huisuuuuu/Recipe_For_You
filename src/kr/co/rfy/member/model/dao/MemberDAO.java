package kr.co.rfy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.member.model.vo.Member;

public class MemberDAO {

	public Member selectOneMember(String userId, String userPwd, Connection conn) {
		PreparedStatement pstmt = null;
		Member m = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM MEMBER WHERE USER_ID=? AND USER_PWD=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				m= new Member();
				m.setUser_No(rset.getInt("user_No"));
				m.setUser_Id(rset.getString("user_Id"));
				m.setUser_Pwd(rset.getString("user_Pwd"));
				m.setUser_Name(rset.getString("user_Name"));
				m.setUser_Email(rset.getString("user_Email"));
				m.setUser_Phone(rset.getString("user_Phone"));
				m.setAgree_YN(rset.getString("agree_YN").charAt(0));
				m.setEnroll_date(rset.getDate("enroll_date"));
				m.setBlack_YN(rset.getString("black_YN").charAt(0));
				m.setRoll(rset.getString("roll"));
				m.setEnd_YN(rset.getString("end_YN").charAt(0));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

}
