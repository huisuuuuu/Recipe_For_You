package kr.co.rfy.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.member.model.vo.Member;

public class AdminMemberDAO {

	public ArrayList<Member> selectAllMemberList(Connection conn, int currentPage, int recordCountPerPage) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage * recordCountPerPage;
		
		
		String query = 
				"  SELECT *  " + 
				"    FROM (SELECT ROW_NUMBER() OVER(order BY user_NO DESC)AS NUM, member.* " + 
				"          FROM member " + 
				"      WHERE BLACK_YN='N') " + 
				"  WHERE NUM BETWEEN ? AND ? ";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Member m = new Member();
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
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public String getPageNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
		
		int recordTotalCount = totalCount(conn); //전체 글 갯수
		
		int pageTotalCount = 0; //전체 페이지 개수
		
		//만약 post 수가 5개 라면? -> 1page
		//만약 post 수가 6개라면? ->2page
		//만약 post 수가 105개라면? -> 21page
		//만약 post 수가 108개라면? -> 22page
		
		if((recordTotalCount % recordCountPerPage)>0)
		{
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		}else
		{
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		//pageTotalCount = (int)Math.ceil(recordTotalCount)/(double)recordCountPerPage);
		
	
		int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
		
		if(endNavi > pageTotalCount)
		{
			endNavi = pageTotalCount;
		}

		
		//PageNavi 모양 만들기
		
StringBuilder sb = new StringBuilder();
		
		if(startNavi==1) {
				
		sb.append("<li class='page-item disabled'><span class='page-link'" +
		"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
		"</span></li>");
				
		}else
		{
		sb.append("<li class='page-item'><a class='page-link text-dark'" +	"href='/admin/memberAllList.do?currentPage="+(startNavi-1)+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
					"</a></li>");
			}
			
			for(int i=startNavi; i<=endNavi; i++)
			{	
				if(i==currentPage)
				{
					sb.append("<li class='page-item active' aria-current='page' >" +
							"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/memberAllList.do?currentPage="+i+"'>"+i+"</a></li>");
				}else
				{
					sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/memberAllList.do?currentPage="+i+"'>"+i+"</a></li>");
				}
				
			}
			
			if(endNavi==pageTotalCount)
			{
				sb.append(" <li class='page-item disabled'><span class='page-link'" +
						" aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
						" </span></li>");
			}else
			{
				sb.append("<li class='page-item'><a class='page-link text-dark'" +
					"  href='/admin/memberAllList.do?currentPage="+(endNavi+1)+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
					" </a></li>");
			}
			
			return sb.toString();
		
	}
	
	public int totalCount(Connection conn)
	{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String query = "SELECT COUNT(*) as count FROM MEMBER WHERE BLACK_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	
	public ArrayList<Member> SearchMember(Connection conn, int currentPage, int recordCountPerPage, String keyword,
			String type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage * recordCountPerPage;
		
		String query ="";
		
		switch(type)
		{
		case "userId" : 
			query = "SELECT * "+
					" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO DESC) AS NUM, MEMBER.* "+
				    "	 FROM MEMBER "+
					"	WHERE BLACK_YN='N' AND USER_ID like ? "+
					"  )  WHERE NUM BETWEEN ? AND ?";
				break;
		case "userName" :
			query = "SELECT * "+
					" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO DESC) AS NUM, MEMBER.* "+
				    "	 FROM MEMBER "+
					"	WHERE BLACK_YN='N' AND USER_NAME like ? "+
					"  )  WHERE NUM BETWEEN ? AND ?";
				break;
			
			
		case "all" :
			query ="SELECT * "+
					" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO DESC) AS NUM, MEMBER.* "+
				    "	 FROM MEMBER "+
					"	WHERE BLACK_YN='N' AND (USER_ID like ? OR USER_NAME like ?)"+
					"  )  WHERE NUM BETWEEN ? AND ?";
			
			
			
				break;
		}
				
		try {
			pstmt = conn.prepareStatement(query);
			
			if(type.equals("all"))
			{
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2,"%"+keyword+"%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}else
			{
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Member m = new Member();
				
				
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
				list.add(m);
				
				
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public String getSearchPageNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
			String keyword, String type) {
		
		int recordTotalCount = totalSearchCount(conn,keyword,type); 
		
		int pageTotalCount = 0;
		
		
		if((recordTotalCount % recordCountPerPage)>0)
		{
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		}else
		{
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
	
		
		int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
		if(endNavi > pageTotalCount)
		{
			endNavi = pageTotalCount;
		}

		
		// PageNavi 모양 만들기
		
				StringBuilder sb = new StringBuilder();
				
				if(startNavi!=1)
				{
					sb.append("<li class='page-item'><a class='page-link text-dark'" +	
				"href='/admin/MemberSearch.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
							"</a></li>");
					
				}
				else {
					sb.append("<li class='page-item disabled'><span class='page-link'" +
							"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
							"</span></li>");
				}
				
				for(int i=startNavi; i<=endNavi;i++)
				{
					if(i==currentPage)
					{
						sb.append("<li class='page-item active' aria-current='page'>" +
								"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/MemberSearch.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
	
					}else
					{

						sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/MemberSearch.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
					}
					
				}
				
				if(endNavi!=pageTotalCount)
				{
				
					sb.append("<li class='page-item'><a class='page-link text-dark'" +
							"href='/admin/MemberSearch.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
							"</a></li>");
				}else
				{
					sb.append("<li class='page-item disabled'><span class='page-link'" +
							"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
							"</span></li>");
				}
		
		
		return sb.toString();
	}
	private int totalSearchCount(Connection conn, String keyword, String type) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String query ="";
		
		switch(type)
		{
		case "userId" : 
				query = " SELECT COUNT(*) as count FROM MEMBER "+
						" WHERE BLACK_YN='N' AND USER_ID like ? ";    
				break;
			
		case "userName":
				query =  " SELECT COUNT(*) as count FROM MEMBER "+
						" WHERE BLACK_YN='N' AND USER_NAME like ? ";
				break;
		
		case "all" :
				query = " SELECT COUNT(*) as count FROM MEMBER "+
						" WHERE BLACK_YN='N' AND (user_id like ? OR USER_NAME LIKE ?) ";
				break;
		
			
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			if(type.equals("all"))
			{
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
			}else
			{
				pstmt.setString(1, "%"+keyword+"%");
			}
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}



public ArrayList<Member> BlackList(Connection conn, int currentPage, int recordCountPerPage) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	ArrayList<Member> list = new ArrayList<Member>();
	
	int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	int end = currentPage * recordCountPerPage;
	
	
	String query = 
			"  SELECT *  " + 
			"    FROM (SELECT ROW_NUMBER() OVER(order BY user_NO DESC)AS NUM, member.* " + 
			"          FROM member " + 
			"       WHERE BLACK_YN='Y') " + 
			"  WHERE NUM BETWEEN ? AND ? ";

	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			Member m = new Member();
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
			list.add(m);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;
}

public String getPageNaviBlack(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
	int recordTotalCount = BlacktotalCount(conn); 
	
	int pageTotalCount = 0; 
	
	
	if((recordTotalCount % recordCountPerPage)>0)
	{
		pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
	}else
	{
		pageTotalCount = recordTotalCount / recordCountPerPage;
	}
	
	//pageTotalCount = (int)Math.ceil(recordTotalCount)/(double)recordCountPerPage);
	

	int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
	int endNavi = startNavi + (naviCountPerPage-1);
	
	
	if(endNavi > pageTotalCount)
	{
		endNavi = pageTotalCount;
	}

	
	//PageNavi 모양 만들기
	
	StringBuilder sb = new StringBuilder();
	
	if(startNavi!=1)
	{
		sb.append("<li class='page-item'><a class='page-link text-dark'" +	"href='/admin/blackList.do?currentPage="+(startNavi-1)+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
				"</a></li>");
		
		
		
	}else {
		sb.append("<li class='page-item disabled'><span class='page-link'" +
				"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
				"</span></li>");
	}
	
	for(int i = startNavi; i<=endNavi; i++)
	{
		if(i==currentPage)
		{
			
			
			sb.append("<li class='page-item active' aria-current='page'>" +
					"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/blackList.do?currentPage="+i+"'>"+i+"</a></li>");
			
		}
		else
		{
		
		sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/blackList.do?currentPage="+i+"'>"+i+"</a></li>");
		}
	}
	
	if(endNavi!=pageTotalCount)
	{
	
		
		sb.append("<li class='page-item'><a class='page-link text-dark'" +
				"href='/admin/blackList.do?currentPage="+(endNavi+1)+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
				"</a></li>");
		
	}
	else {
		sb.append("<li class='page-item disabled'><span class='page-link'" +
				"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
				"</span></li>");
	}
	
	
	return sb.toString();
}

private int BlacktotalCount(Connection conn) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	int count = 0;
	
	String query = "SELECT COUNT(*) as count FROM MEMBER WHERE BLACK_YN='Y'";
	
	try {
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			count = rset.getInt("count");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(pstmt);
	}
	return count;
}





public ArrayList<Member> SearchMemberBlack(Connection conn, int currentPage, int recordCountPerPage, String keyword,
		String type) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	ArrayList<Member> list = new ArrayList<Member>();
	
	
	int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	int end = currentPage * recordCountPerPage;
	
	String query ="";
	
	switch(type)
	{
	case "userId" : 
		query = "SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO DESC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='Y' AND USER_ID like ? "+
				"  )  WHERE NUM BETWEEN ? AND ?";
			break;
	case "userName" :
		query = "SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO DESC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE  BLACK_YN='Y' AND USER_NAME like ? "+
				"  )  WHERE NUM BETWEEN ? AND ?";
			break;
		
		
	case "all" :
		query ="SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO DESC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='Y' AND (USER_ID like ? OR USER_NAME like ?)"+
				"  )  WHERE NUM BETWEEN ? AND ?";
		
		
		
			break;
}
	try {
		pstmt = conn.prepareStatement(query);
		
		if(type.equals("all"))
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
		}else
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
		}
		
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			Member m = new Member();
			
			
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
			list.add(m);
			
			
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;

	}

public String getSearchPageNaviBlack(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
		String keyword, String type) {
	int recordTotalCount = totalSearchCountBlack(conn,keyword,type); 
	
	int pageTotalCount = 0;
	
	
	if((recordTotalCount % recordCountPerPage)>0)
	{
		pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
	}else
	{
		pageTotalCount = recordTotalCount / recordCountPerPage;
	}

	
	int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
	int endNavi = startNavi + (naviCountPerPage-1);
	
	if(endNavi > pageTotalCount)
	{
		endNavi = pageTotalCount;
	}

	
	// PageNavi 모양 만들기
	
			StringBuilder sb = new StringBuilder();
			
			if(startNavi!=1)
			{
				sb.append("<li class='page-item'><a class='page-link text-dark'" +	
			"href='/admin/MemberSearchBlack.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
						"</a></li>");
				
			}
			else {
				sb.append("<li class='page-item disabled'><span class='page-link'" +
						"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
						"</span></li>");
			}
			
			for(int i=startNavi; i<=endNavi;i++)
			{
				if(i==currentPage)
				{
					sb.append("<li class='page-item active' aria-current='page'>" +
							"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/MemberSearchBlack.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");

				}else
				{

					sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/MemberSearchBlack.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
				}
				
			}
			
			if(endNavi!=pageTotalCount)
			{

				sb.append("<li class='page-item'><a class='page-link text-dark'" +
						"href='/admin/MemberSearchBlack.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
						"</a></li>");

			}
			else {
				sb.append("<li class='page-item disabled'><span class='page-link'" +
						"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
						"</span></li>");
			}
	
	
	return sb.toString();
}

private int totalSearchCountBlack(Connection conn, String keyword, String type) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	int count = 0;
	
	String query ="";
	
	switch(type)
	{
	case "userId" : 
			query = " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='Y' AND USER_ID like ? ";    
			break;
		
	case "userName":
			query =  " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='Y' AND USER_NAME like ? ";
			break;
	
	case "all" :
			query = " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='Y' AND (user_id like ? OR USER_NAME LIKE ?) ";
			break;
	
		
	}
	
	try {
		pstmt = conn.prepareStatement(query);
		if(type.equals("all"))
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
		}else
		{
			pstmt.setString(1, "%"+keyword+"%");
		}
		
		
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			count = rset.getInt("count");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(pstmt);
	}
	return count;
}






public Member selectOne(Connection conn, int userNo) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	Member m = null;
	
	String query = "SELECT * FROM MEMBER " + 
			" WHERE USER_NO =? AND END_YN='N' ";
	
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, userNo);
		
		rset=pstmt.executeQuery();
		
		if(rset.next())
		{
			m = new Member();
			
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

public int updateMemberBlackYN(int userNo, char blackYN, Connection conn) {
	PreparedStatement pstmt = null;
	int result = 0;
	
	String query = "UPDATE MEMBER SET black_YN=? WHERE user_No=? ";
	
	try {
		pstmt=conn.prepareStatement(query);
		
		pstmt.setString(1, String.valueOf(blackYN));
		pstmt.setInt(2, userNo);
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
	}
	
	return result;
}

public ArrayList<Member> selectAllMemberListUp(Connection conn, int currentPage, int recordCountPerPage) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	ArrayList<Member> list = new ArrayList<Member>();
	
	int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	int end = currentPage * recordCountPerPage;
	
	
	String query = 
			"  SELECT *  " + 
			"    FROM (SELECT ROW_NUMBER() OVER(order BY user_NO ASC)AS NUM, member.* " + 
			"          FROM member " + 
			"      WHERE BLACK_YN='N') " + 
			"  WHERE NUM BETWEEN ? AND ? ";

	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			Member m = new Member();
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
			list.add(m);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;
}

public String getPageNaviUp(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
	int recordTotalCount = totalCountUp(conn); //전체 글 갯수
	
	int pageTotalCount = 0; //전체 페이지 개수
	
	if((recordTotalCount % recordCountPerPage)>0)
	{
		pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
	}else
	{
		pageTotalCount = recordTotalCount / recordCountPerPage;
	}
	
	//pageTotalCount = (int)Math.ceil(recordTotalCount)/(double)recordCountPerPage);
	

	int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
	int endNavi = startNavi + (naviCountPerPage-1);
	
	
	if(endNavi > pageTotalCount)
	{
		endNavi = pageTotalCount;
	}

	
	//PageNavi 모양 만들기
	
	StringBuilder sb = new StringBuilder();
	
	if(startNavi==1) {
		
		sb.append("<li class='page-item disabled'><span class='page-link'" +
		"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
		"</span></li>");
				
		}else
		{
		sb.append("<li class='page-item'><a class='page-link text-dark'" +	"href='/admin/memberAllListUp.do?currentPage="+(startNavi-1)+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
					"</a></li>");
			}
			
			for(int i=startNavi; i<=endNavi; i++)
			{	
				if(i==currentPage)
				{
					sb.append("<li class='page-item active' aria-current='page' >" +
							"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/memberAllListUp.do?currentPage="+i+"'>"+i+"</a></li>");
				}else
				{
					sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/memberAllListUp.do?currentPage="+i+"'>"+i+"</a></li>");
				}
				
			}
			
			if(endNavi==pageTotalCount)
			{
				sb.append(" <li class='page-item disabled'><span class='page-link'" +
						" aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
						" </span></li>");
			}else
			{
				sb.append("<li class='page-item'><a class='page-link text-dark'" +
					"  href='/admin/memberAllListUp.do?currentPage="+(endNavi+1)+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
					" </a></li>");
			}
			
			return sb.toString();

}

private int totalCountUp(Connection conn) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	int count = 0;
	
	String query = "SELECT COUNT(*) as count FROM MEMBER WHERE BLACK_YN='N'";
	
	try {
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			count = rset.getInt("count");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(pstmt);
	}
	return count;
}

public ArrayList<Member> SearchMemberUp(Connection conn, int currentPage, int recordCountPerPage, String keyword,
		String type) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	ArrayList<Member> list = new ArrayList<Member>();
	
	
	int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	int end = currentPage * recordCountPerPage;
	
	String query ="";
	
	switch(type)
	{
	case "userId" : 
		query = "SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO ASC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='N' AND USER_ID like ? "+
				"  )  WHERE NUM BETWEEN ? AND ?";
			break;
	case "userName" :
		query = "SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO ASC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='N' AND USER_NAME like ? "+
				"  )  WHERE NUM BETWEEN ? AND ?";
			break;
		
		
	case "all" :
		query ="SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO ASC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='N' AND (USER_ID like ? OR USER_NAME like ?)"+
				"  )  WHERE NUM BETWEEN ? AND ?";
		
		
		
			break;
	}
			
	try {
		pstmt = conn.prepareStatement(query);
		
		if(type.equals("all"))
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
		}else
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
		}
		
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			Member m = new Member();
			
			
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
			list.add(m);
			
			
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;
}

public String getSearchPageNaviUp(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
		String keyword, String type) {
	int recordTotalCount = totalSearchCountUp(conn,keyword,type); 
	
	int pageTotalCount = 0;
	
	
	if((recordTotalCount % recordCountPerPage)>0)
	{
		pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
	}else
	{
		pageTotalCount = recordTotalCount / recordCountPerPage;
	}

	
	int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
	int endNavi = startNavi + (naviCountPerPage-1);
	
	if(endNavi > pageTotalCount)
	{
		endNavi = pageTotalCount;
	}

	
	// PageNavi 모양 만들기
	
			StringBuilder sb = new StringBuilder();
			
			if(startNavi!=1)
			{
				sb.append("<li class='page-item'><a class='page-link text-dark'" +	
			"href='/admin/MemberSearchUP.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
						"</a></li>");
				
			}
			else {
				sb.append("<li class='page-item disabled'><span class='page-link'" +
						"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
						"</span></li>");
			}
			
			for(int i=startNavi; i<=endNavi;i++)
			{
				if(i==currentPage)
				{
					sb.append("<li class='page-item active' aria-current='page'>" +
							"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/MemberSearchUP.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");

				}else
				{

					sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/MemberSearchUP.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
				}
				
			}
			
			if(endNavi!=pageTotalCount)
			{

				sb.append("<li class='page-item'><a class='page-link text-dark'" +
						"href='/admin/MemberSearchUP.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
						"</a></li>");

			}
			else {
				sb.append("<li class='page-item disabled'><span class='page-link'" +
						"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
						"</span></li>");
			}
	
	
	return sb.toString();
}
private int totalSearchCountUp(Connection conn, String keyword, String type) {
	
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	int count = 0;
	
	String query ="";
	
	switch(type)
	{
	case "userId" : 
			query = " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='N' AND USER_ID like ? ";    
			break;
		
	case "userName":
			query =  " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='N' AND USER_NAME like ? ";
			break;
	
	case "all" :
			query = " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='N' AND (user_id like ? OR USER_NAME LIKE ?) ";
			break;
	
		
	}
	
	try {
		pstmt = conn.prepareStatement(query);
		if(type.equals("all"))
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
		}else
		{
			pstmt.setString(1, "%"+keyword+"%");
		}
		
		
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			count = rset.getInt("count");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(pstmt);
	}
	return count;
}

public Member selectOneUp(Connection conn, int userNo) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	Member m = null;
	
	String query = "SELECT * FROM MEMBER " + 
			" WHERE USER_NO =? AND END_YN='N' ";
	
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, userNo);
		
		rset=pstmt.executeQuery();
		
		if(rset.next())
		{
			m = new Member();
			
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

public int updateMemberBlackYNUp(int userNo, char blackYN, Connection conn) {
	PreparedStatement pstmt = null;
	int result = 0;
	
	String query = "UPDATE MEMBER SET black_YN=? WHERE user_No=? ";
	
	try {
		pstmt=conn.prepareStatement(query);
		
		pstmt.setString(1, String.valueOf(blackYN));
		pstmt.setInt(2, userNo);
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
	}
	
	return result;
}

public ArrayList<Member> BlackListUp(Connection conn, int currentPage, int recordCountPerPage) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	ArrayList<Member> list = new ArrayList<Member>();
	
	int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	int end = currentPage * recordCountPerPage;
	
	
	String query = 
			"  SELECT *  " + 
			"    FROM (SELECT ROW_NUMBER() OVER(order BY user_NO ASC)AS NUM, member.* " + 
			"          FROM member " + 
			"      WHERE BLACK_YN='Y') " + 
			"  WHERE NUM BETWEEN ? AND ? ";

	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			Member m = new Member();
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
			list.add(m);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;
}



public String getPageNaviBlackUp(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
int recordTotalCount = totalCountBlackUp(conn); //전체 글 갯수
	
	int pageTotalCount = 0; //전체 페이지 개수
	
	if((recordTotalCount % recordCountPerPage)>0)
	{
		pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
	}else
	{
		pageTotalCount = recordTotalCount / recordCountPerPage;
	}
	
	//pageTotalCount = (int)Math.ceil(recordTotalCount)/(double)recordCountPerPage);
	

	int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
	int endNavi = startNavi + (naviCountPerPage-1);
	
	
	if(endNavi > pageTotalCount)
	{
		endNavi = pageTotalCount;
	}

	
	//PageNavi 모양 만들기
	
	StringBuilder sb = new StringBuilder();
	
	if(startNavi!=1)
	{
		sb.append("<li class='page-item'><a class='page-link text-dark'" +	"href='/admin/blackListUp.do?currentPage="+(startNavi-1)+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
				"</a></li>");
		
		
		
	}else {
		sb.append("<li class='page-item disabled'><span class='page-link'" +
				"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
				"</span></li>");
	}
	
	for(int i = startNavi; i<=endNavi; i++)
	{
		if(i==currentPage)
		{
			
			
			sb.append("<li class='page-item active' aria-current='page'>" +
					"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/blackListUp.do?currentPage="+i+"'>"+i+"</a></li>");
			
		}
		else
		{
		
		sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/blackListUp.do?currentPage="+i+"'>"+i+"</a></li>");
		}
	}
	
	if(endNavi!=pageTotalCount)
	{
	
		
		sb.append("<li class='page-item'><a class='page-link text-dark'" +
				"href='/admin/blackListUp.do?currentPage="+(endNavi+1)+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
				"</a></li>");
		
	}
	else {
		sb.append("<li class='page-item disabled'><span class='page-link'" +
				"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
				"</span></li>");
	}
	
	
	return sb.toString();
}

private int totalCountBlackUp(Connection conn) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	int count = 0;
	
	String query = "SELECT COUNT(*) as count FROM MEMBER WHERE BLACK_YN='Y'";
	
	try {
		pstmt = conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			count = rset.getInt("count");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(pstmt);
	}
	return count;
}

public ArrayList<Member> SearchMemberBlackUp(Connection conn, int currentPage, int recordCountPerPage, String keyword,
		String type) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	ArrayList<Member> list = new ArrayList<Member>();
	
	
	int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	int end = currentPage * recordCountPerPage;
	
	String query ="";
	
	switch(type)
	{
	case "userId" : 
		query = "SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO ASC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='Y' AND USER_ID like ? "+
				"  )  WHERE NUM BETWEEN ? AND ?";
			break;
	case "userName" :
		query = "SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO ASC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='Y' AND USER_NAME like ? "+
				"  )  WHERE NUM BETWEEN ? AND ?";
			break;
		
		
	case "all" :
		query ="SELECT * "+
				" FROM (SELECT ROW_NUMBER() OVER(order BY USER_NO ASC) AS NUM, MEMBER.* "+
			    "	 FROM MEMBER "+
				"	WHERE BLACK_YN='Y' AND (USER_ID like ? OR USER_NAME like ?)"+
				"  )  WHERE NUM BETWEEN ? AND ?";
		
		
		
			break;
	}
			
	try {
		pstmt = conn.prepareStatement(query);
		
		if(type.equals("all"))
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
		}else
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
		}
		
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			Member m = new Member();
			
			
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
			list.add(m);
			
			
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return list;
}

public String getSearchPageNaviBlackUp(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
		String keyword, String type) {
int recordTotalCount = totalSearchCountBlackUp(conn,keyword,type); 
	
	int pageTotalCount = 0;
	
	
	if((recordTotalCount % recordCountPerPage)>0)
	{
		pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
	}else
	{
		pageTotalCount = recordTotalCount / recordCountPerPage;
	}

	
	int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
	int endNavi = startNavi + (naviCountPerPage-1);
	
	if(endNavi > pageTotalCount)
	{
		endNavi = pageTotalCount;
	}

	
	// PageNavi 모양 만들기
	
	StringBuilder sb = new StringBuilder();
	
	if(startNavi!=1)
	{
		sb.append("<li class='page-item'><a class='page-link text-dark'" +	
	"href='/admin/MemberSearchBlackUP.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
				"</a></li>");
		
	}
	else {
		sb.append("<li class='page-item disabled'><span class='page-link'" +
				"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
				"</span></li>");
	}
	
	for(int i=startNavi; i<=endNavi;i++)
	{
		if(i==currentPage)
		{
			sb.append("<li class='page-item active' aria-current='page'>" +
					"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/admin/MemberSearchBlackUP.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");

		}else
		{

			sb.append("<li class='page-item'><a class='page-link text-dark' href='/admin/MemberSearchBlackUP.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
		}
		
	}
	
	if(endNavi!=pageTotalCount)
	{
		
	
	
	
		sb.append("<li class='page-item'><a class='page-link text-dark'" +
				"href='/admin/MemberSearchBlackUP.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
				"</a></li>");

	}
	else
	{
		sb.append("<li class='page-item disabled'><span class='page-link'" +
				"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
				"</span></li>");
	}


return sb.toString();
}
private int totalSearchCountBlackUp(Connection conn, String keyword, String type) {
	
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	int count = 0;
	
	String query ="";
	
	switch(type)
	{
	case "userId" : 
			query = " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='Y' AND USER_ID like ? ";    
			break;
		
	case "userName":
			query =  " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='Y' AND USER_NAME like ? ";
			break;
	
	case "all" :
			query = " SELECT COUNT(*) as count FROM MEMBER "+
					" WHERE BLACK_YN='Y' AND (user_id like ? OR USER_NAME LIKE ?) ";
			break;
	
		
	}
	
	try {
		pstmt = conn.prepareStatement(query);
		if(type.equals("all"))
		{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
		}else
		{
			pstmt.setString(1, "%"+keyword+"%");
		}
		
		
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			count = rset.getInt("count");
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(pstmt);
	}
	return count;
}



}



