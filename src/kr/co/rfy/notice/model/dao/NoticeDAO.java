package kr.co.rfy.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.notice.model.vo.Notice;

public class NoticeDAO {
	
	//페이지 처리한 공지사항 리스트
	public ArrayList<Notice> selectAllNoticeList(Connection conn, int currentPage, int recourdCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();

		int strat = currentPage*recourdCountPerPage-(recourdCountPerPage-1);
		int end = currentPage*recourdCountPerPage;

		String sql = "select * from (SELECT row_number() over(order by BOARD_NO desc) as num, BOARD_NOTICE.* from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID)where BOARD_NOTICE.end_yn='N')where num between ? and ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, strat);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setBoard_no(rset.getInt("board_no"));
				n.setTitle(rset.getString("title"));
				n.setUser_id(rset.getString("user_id"));
				n.setRegDate(rset.getDate("regDate"));
				n.setView_count(rset.getInt("view_count"));
				n.setEnd_yn(rset.getString("end_yn").charAt(0));
				int count = rset.getInt("view_count");
				count++;
				list.add(n);
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

	
	
	//페이지 네비게이션 바
	public String getPageNivi(Connection conn, int naviCountPerPage, int recourdCountPerPage, int currentPage) {
		// TODO Auto-generated method stub
		int recodeTotalCount =totalCount(conn);//전체 글 숫자
		int pageTotalCount=0;//전체 페이지 수
		pageTotalCount = (int)Math.ceil(recodeTotalCount/(double)recourdCountPerPage);
		
		int startNavi = ((currentPage-1)/naviCountPerPage * naviCountPerPage) + 1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
		if(endNavi>pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(startNavi!=1)
		{
			sb.append("<a class='prev' style='color:black' href='/customerServiceCenter/notice.do?currentPage="+(startNavi-1)+"'>< Prev</a>	");
		}else
		{
			sb.append("<a class='prev' style='visibility:hidden' href='/customerServiceCenter/notice.do?currentPage="+(startNavi-1)+"'>< Prev</a>	");
		}
		
		for(int i=startNavi; i<=endNavi;i++)
		{
			if(i==currentPage)
			{
				sb.append("<a class='naviNum' style='color:black' href='/customerServiceCenter/notice.do?currentPage="+i+"'>"+i+"</a> ");
			}else
			{
				sb.append("<a class='naviNum' style='color:black' href='/customerServiceCenter/notice.do?currentPage="+i+"'>"+i+"</a> ");
			}
			
		}
		
		if(endNavi!=pageTotalCount)
		{
			sb.append(" <a class='next' style='color:black' href='/customerServiceCenter/notice.do?currentPage="+(endNavi+1)+"'>Next ></a> ");
		}else
		{
			sb.append(" <a class='next' style='visibility:hidden' href='/customerServiceCenter/notice.do?currentPage="+(endNavi+1)+"'>Next ></a> ");
		}
		return sb.toString();
	}
	
	
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String sql = "select count(*) as count from BOARD_NOTICE where BOARD_NOTICE.END_YN='N'";
		
		try {
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				count=rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	//페이지 네비게이션 바 끝
	//페이지 처리한 공지사항 리스트 끝

	
	//조회수 증가 
	public int viewCount(Connection conn,int board_no) {
		PreparedStatement pstmt = null;
		String sql = "update BOARD_NOTICE set VIEW_COUNT = VIEW_COUNT+1 where BOARD_NOTICE.BOARD_NO = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,board_no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//조회수 증가 끝 

	
	//검색기능 구현
	public ArrayList<Notice> noticeSearch(Connection conn, int currentPage, int recourdCountPerPage, String keyword,
			String type) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		int start = currentPage * recourdCountPerPage - (recourdCountPerPage -1);
		int end = currentPage * recourdCountPerPage;
		
		String sql = "";
		
		switch(type) {
			case "title":
				sql = "select * from (SELECT row_number() over(order by BOARD_NO desc) as num, BOARD_NOTICE.* from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID)where BOARD_NOTICE.end_yn='N' and title like ?)where num between ? and ?";
				break;
			
			case "content":
				sql = "select * from (SELECT row_number() over(order by BOARD_NO desc) as num, BOARD_NOTICE.* from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID)where BOARD_NOTICE.end_yn='N' and content like ?)where num between ? and ?";
				break;
			
			case "all":
				sql="select * from (SELECT row_number() over(order by BOARD_NO desc) as num, BOARD_NOTICE.* from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID)where BOARD_NOTICE.end_yn='N' and (title like ? or content like ?))where num between ? and ?";
				break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(type.equals("all")) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}else {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setBoard_no(rset.getInt("board_no"));
				n.setTitle(rset.getString("title"));
				n.setUser_id(rset.getString("user_id"));
				n.setRegDate(rset.getDate("regDate"));
				n.setView_count(rset.getInt("view_count"));
				//int count = rset.getInt("view_count");
				//count++;
				list.add(n);
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
	
	public String searchPageNavi(Connection conn,int naviCountPerPage, int recourdCountPerPage, int currentPage,
			String keyword, String type) {
		
		int recordTotalCount = totalSearchCount(conn,keyword,type);
		int pageTotalCount = 0;
		pageTotalCount = (int)Math.ceil(recordTotalCount/(double)recourdCountPerPage);
		
		int startNavi = (((currentPage-1)/naviCountPerPage) * naviCountPerPage)+1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(startNavi!=1)
		{
			sb.append("<a class='prev' style='color:black href='/customerServiceCenter/notice.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"'>< Prev</a>	");
		}else
		{
			sb.append("<a class='prev'  style='visibility:hidden' href='/customerServiceCenter/notice.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"'>< Prev</a>	");
		}
		
		for(int i=startNavi; i<=endNavi;i++)
		{
			if(i==currentPage)
			{
				sb.append("<a class='naviNum' style='color:black href='/customerServiceCenter/notice.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a> ");
			}else
			{
				sb.append("<a class='naviNum' style='color:black href='/customerServiceCenter/notice.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a> ");
			}
			
		}
		
		if(endNavi!=pageTotalCount)
		{
			sb.append(" <a class='next' style='color:black href='/customerServiceCenter/notice.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"'>Next ></a> ");
		}else
		{
			sb.append(" <a class='next' style='visibility:hidden' href='/customerServiceCenter/notice.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"'>Next ></a> ");
		}
		return sb.toString();
	}
	
	
	//검색시 나오는 총 게시글 겟수
	private int totalSearchCount(Connection conn,String keyword,String type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		String sql ="";
		
		switch(type) {
			case "title":
				sql = "select count(*) as count from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID) WHERE BOARD_NOTICE.end_yn='N' and title like ?";
				break;
			
			case "content":
				sql = "select count(*) as count from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID) WHERE BOARD_NOTICE.end_yn='N' and content like ?";
				break;
			
			case "all":
				sql = "select count(*) as count from BOARD_NOTICE left join MEMBER on(BOARD_NOTICE.USER_ID = MEMBER.USER_ID) WHERE BOARD_NOTICE.end_yn='N' and (content like ? or title like ?)";
				break;
		}
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			if(type.equals("all")) {
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
			}else {
				pstmt.setString(1, "%"+keyword+"%");
			}
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count=rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	//검색시 나오는 총 게시글 겟수 끝
 	//검색기능 구현 끝


	//뷰단
	public Notice noticeView(Connection conn, int board_no) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice n = null;
		
		String sql = "select BOARD_NOTICE.* from BOARD_NOTICE LEFT JOIN MEMBER on (BOARD_NOTICE.USER_ID = MEMBER.USER_ID) where board_no=? and BOARD_NOTICE.end_yn='N'";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice();
				n.setBoard_code(rset.getString("board_code"));
				n.setBoard_no(rset.getInt("board_no"));
				n.setTitle(rset.getString("title"));
				n.setContent(rset.getString("content"));
				n.setUser_id(rset.getString("user_id"));
				n.setRegDate(rset.getDate("regDate"));
				n.setView_count(rset.getInt("view_count"));
				n.setEnd_yn(rset.getString("end_yn").charAt(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
	}
	//뷰단 끝
}
