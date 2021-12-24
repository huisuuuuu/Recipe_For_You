package kr.co.rfy.qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.rfy.commons.JDBCTemplate;
import kr.co.rfy.qna.model.vo.Qna;

public class QnaDAO {
	
	
	//페이지 처리한 리스트
	public ArrayList<Qna> selectAllQnaList(Connection conn, int currentPage, int recourdCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list= new ArrayList<Qna>();
		
		int strat = currentPage*recourdCountPerPage-(recourdCountPerPage-1);
		int end = currentPage*recourdCountPerPage;
		
		String sql = "select * from (select row_number() over(order by BOARDNO desc) as num, Board_QA.* from Board_QA left join member on (Board_QA.USER_ID = MEMBER.USER_ID) where Board_QA.end_yn='N')where num between ? and ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, strat);
			pstmt.setInt(2, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Qna qna = new Qna();
				qna.setBoard_code(rset.getString("board_code"));
				qna.setQ_code(rset.getString("q_code"));
				qna.setBoard_no(rset.getInt("boardno"));
				qna.setTitle(rset.getString("title"));
				qna.setContent(rset.getString("content"));
				qna.setUser_id(rset.getString("user_id"));
				qna.setRegDate(rset.getDate("regDate"));
				list.add(qna);
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
	
	//네비게이션 바 갯수
	public String getPageNavi(Connection conn, int naviCountPerPage, int recourdCountPerPage, int currentPage) {
		// TODO Auto-generated method stub
		int recodeTotalCount=totalCount(conn);//전체 글 갯수
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
			sb.append("<a class='prev' style='color:black' href='/customerServiceCenter/qna.do?currentPage="+(startNavi-1)+"'>< Prev</a>	");
		}else
		{
			sb.append("<a class='prev' style='visibility:hidden' href='/customerServiceCenter/qna.do?currentPage="+(startNavi-1)+"'>< Prev</a>	");
		}
		
		for(int i=startNavi; i<=endNavi;i++)
		{
			if(i==currentPage)
			{
				sb.append("<a class='naviNum' style='color:black' href='/customerServiceCenter/qna.do?currentPage="+i+"'>"+i+"</a> ");
			}else
			{
				sb.append("<a class='naviNum' style='color:black' href='/customerServiceCenter/qna.do?currentPage="+i+"'>"+i+"</a> ");
			}
			
		}
		
		if(endNavi!=pageTotalCount)
		{
			sb.append(" <a class='next' style='color:black' href='/customerServiceCenter/qna.do?currentPage="+(endNavi+1)+"'>Next ></a> ");
		}else
		{
			sb.append(" <a class='next' style='visibility:hidden' href='/customerServiceCenter/qna.do?currentPage="+(endNavi+1)+"'>Next ></a> ");
		}
		return sb.toString();
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String sql = "select count(*) as count from Board_QA where Board_QA.END_YN='N'";
		
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
	//네비게이션 바 갯수 끝
	//페이지 처리한 리스트 끝

	
	//검색기능 구현한 구간
	public ArrayList<Qna> qnaSearch(Connection conn, int currentPage, int recourdCountPerPage, String keyword,
			String type) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list= new ArrayList<Qna>();
		
		int start = currentPage * recourdCountPerPage - (recourdCountPerPage -1);
		int end = currentPage * recourdCountPerPage;
		
		String sql = "";
		
		switch(type) {
			case "title":
				sql = "select * from (SELECT row_number() over(order by BOARDNO desc) as num, Board_QA.* from Board_QA left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID)where Board_QA.end_yn='N' and title like ?)where num between ? and ?";
				break;
			
			case "content":
				sql = "select * from (SELECT row_number() over(order by BOARDNO desc) as num, Board_QA.* from Board_QA left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID)where Board_QA.end_yn='N' and content like ?)where num between ? and ?";
				break;
			
			case "all":
				sql	= "select * from (SELECT row_number() over(order by BOARDNO desc) as num, Board_QA.* from Board_QA left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID)where Board_QA.end_yn='N' and (content like ? or  title like ?))where num between ? and ?";
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
				Qna qna = new Qna();
				
				qna.setBoard_code(rset.getString("board_code"));
				qna.setQ_code(rset.getString("q_code"));
				qna.setBoard_no(rset.getInt("boardno"));
				qna.setTitle(rset.getString("title"));
				qna.setContent(rset.getString("content"));
				qna.setUser_id(rset.getString("user_id"));
				qna.setRegDate(rset.getDate("regDate"));
				
				list.add(qna);
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


	//네비게이션 바 갯수 
	public String searchPageNavi(Connection conn, int naviCountPerPage, int recourdCountPerPage, int currentPage,
			String keyword, String type) {
		// TODO Auto-generated method stub
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
			sb.append("<a class='prev'  href='/qna/qnaSearch.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"'>< Prev</a>	");
		}else
		{
			sb.append("<a class='prev'  style='visibility:hidden' href='/qna/qnaSearch.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"'>< Prev</a>	");
		}
		
		for(int i=startNavi; i<=endNavi;i++)
		{
			if(i==currentPage)
			{
				sb.append("<a class='naviNum'  href='/qna/qnaSearch.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a> ");
			}else
			{
				sb.append("<a class='naviNum'  href='/qna/qnaSearch.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a> ");
			}
			
		}
		
		if(endNavi!=pageTotalCount)
		{
			sb.append(" <a class='next'  href='/qna/qnaSearch.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"'>Next ></a> ");
		}else
		{
			sb.append(" <a class='next' style='visibility:hidden' href='/customerServiceCenter/qna.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"'>Next ></a> ");
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
					sql = "select count(*) as count from Board_QA "
							+ "left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID) "
							+ "WHERE Board_QA.end_yn='N' and title like ?";
					break;
				
				case "content":
					sql = "select count(*) as count from Board_QA "
							+ "left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID) "
							+ "WHERE Board_QA.end_yn='N' and content like ?";
					break;
				
				case "all":
					sql = "select count(*) as count from Board_QA "
							+ "left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID) "
							+ "WHERE Board_QA.end_yn='N' and (content like ? or title like ?)";
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
	//네비게이션 바 갯수 끝 
	//검색기능 구현한 구간 끝

		public Qna qnaView(Connection conn, int board_no) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Qna qna = null;
			
			String sql = "select Board_QA.* from Board_QA LEFT JOIN MEMBER on (Board_QA.USER_ID = MEMBER.USER_ID) where boardno=? and Board_QA.end_yn='N'";
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, board_no);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					qna = new Qna();
					qna.setBoard_code(rset.getString("board_code"));
					qna.setQ_code(rset.getString("q_code"));
					qna.setBoard_no(rset.getInt("boardno"));
					qna.setTitle(rset.getString("title"));
					qna.setContent(rset.getString("content"));
					qna.setUser_id(rset.getString("user_id"));
					qna.setRegDate(rset.getDate("regDate"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return qna;
		}
}
