package kr.co.fty.NoticeQNA.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.qna.model.vo.Qna;

public class QnaAdminDAO {
	//qna리스트 
	public ArrayList<Qna> selectAllQnaList(Connection conn, int currentPage,int recourdCountPerPage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list= new ArrayList<Qna>();
		
		int strat = currentPage * recourdCountPerPage - (recourdCountPerPage-1);
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
	
	
	//qna네비게이션 바
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
				sb.append("<a class='prev' style='color:black' href='/customerServiceCenter/qnaAdmin.do?currentPage="+(startNavi-1)+"'>< Prev</a>	");
			}else
			{
				sb.append("<a class='prev' style='visibility:hidden' href='/customerServiceCenter/qnaAdmin.do?currentPage="+(startNavi-1)+"'>< Prev</a>	");
			}
			
			for(int i=startNavi; i<=endNavi;i++)
			{
				if(i==currentPage)
				{
					sb.append("<a class='naviNum' style='color:black' href='/customerServiceCenter/qnaAdmin.do?currentPage="+i+"'>"+i+"</a> ");
				}else
				{
					sb.append("<a class='naviNum' style='color:black' href='/customerServiceCenter/qnaAdmin.do?currentPage="+i+"'>"+i+"</a> ");
				}
				
			}
			
			if(endNavi!=pageTotalCount)
			{
				sb.append(" <a class='next' style='color:black' href='/customerServiceCenter/qnaAdmin.do?currentPage="+(endNavi+1)+"'>Next ></a> ");
			}else
			{
				sb.append(" <a class='next' style='visibility:hidden' href='/customerServiceCenter/qnaAdmin.do?currentPage="+(endNavi+1)+"'>Next ></a> ");
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
	//qna네비게이션 바 끝
	//qna리스트 끝

		
		//검색기능 구현
		public ArrayList<Qna> qnaAdminSearch(Connection conn, int currentPage, int recourdCountPerPage, String keyword,
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

		//페이지 네비게이션
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
				sb.append("<a class='prev' style='color:black href='/customerServiceCenter/qna.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"'>< Prev</a>	");
			}else
			{
				sb.append("<a class='prev'  style='visibility:hidden' href='/customerServiceCenter/qna.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"'>< Prev</a>	");
			}
			
			for(int i=startNavi; i<=endNavi;i++)
			{
				if(i==currentPage)
				{
					sb.append("<a class='naviNum' style='color:black href='/customerServiceCenter/qna.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a> ");
				}else
				{
					sb.append("<a class='naviNum' style='color:black href='/customerServiceCenter/qna.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a> ");
				}
				
			}
			
			if(endNavi!=pageTotalCount)
			{
				sb.append(" <a class='next' style='color:black href='/customerServiceCenter/qna.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"'>Next ></a> ");
			}else
			{
				sb.append(" <a class='next' style='visibility:hidden' href='/customerServiceCenter/qna.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"'>Next ></a> ");
			}
			return sb.toString();
		}
		
		
		private int totalSearchCount(Connection conn,String keyword,String type) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			int count = 0;
			String sql ="";
			
			switch(type) {
				case "title":
					sql = "select count(*) as count from Board_QA left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID) WHERE Board_QA.end_yn='N' and title like ?";
					break;
				
				case "content":
					sql = "select count(*) as count from Board_QA left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID) WHERE Board_QA.end_yn='N' and content like ?";
					break;
				
				case "all":
					sql = "select count(*) as count from Board_QA left join MEMBER on(Board_QA.USER_ID = MEMBER.USER_ID) WHERE Board_QA.end_yn='N' and (content like ? or title like ?)";
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
		//페이지 네비게이션 끝
		//검색기능 구현 끝

		
		//뷰
		public Qna qnaAdminView(Connection conn, int board_no) {
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
		//뷰 끝


		//글 쓰기
		public int insertQna(Connection conn, Qna qna) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			int result = 0;
			
			String sql = "INSERT INTO BOARD_QA VALUES('BOARD-3',?,QA_Seq.NEXTVAL,?,?,?,SYSDATE,'N')";
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, qna.getQ_code());
				pstmt.setString(2, qna.getTitle());
				pstmt.setString(3, qna.getContent());
				pstmt.setString(4, qna.getUser_id());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		
		public int searchBoardNo(Connection conn, Qna qna) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt  = null;
			ResultSet rset = null;
			
			int board_no = 0;
			String sql = "select boardno from (select row_number() over (order by boardno desc) as num, BOARD_QA.* from BOARD_QA where BOARD_QA.user_id=? and title=? and content=?) where num =1";
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, qna.getUser_id());
				pstmt.setString(2, qna.getTitle());
				pstmt.setString(3, qna.getContent());
				
				rset = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return board_no;
		}
		//글 쓰기 끝

		
		//글 수정
		public int updateView(Connection conn, Qna qna,int board_no,String user_id,String title,String content,String q_code) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			int result = 0;
			
			String sql = "update BOARD_QA set Q_CODE=?,TITLE=?,CONTENT=? where BOARDNO=? and BOARD_QA.user_id=?";
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, q_code);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.setInt(4, board_no);
				pstmt.setString(5, user_id);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//글 수정 끝


		//글 삭제
		public int deleteView(Connection conn, String user_id, int board_no) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			int result = 0;
			
			String sql = "UPDATE BOARD_QA SET END_YN='Y' WHERE BOARDNO=? AND BOARD_QA.user_id=?";
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setInt(1, board_no);
				pstmt.setString(2, user_id);
				
				result = pstmt.executeUpdate();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//글 삭제 끝



}
