package kr.co.fty.NoticeAdmin.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.fty.NoticeAdmin.model.dao.NoticeAdminDAO;
import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.notice.model.vo.Notice;
import oracle.net.aso.n;

public class NoticeAdminServiceImpl implements NoticeAdminService{
	private NoticeAdminDAO adminDAO = new NoticeAdminDAO();
	//관리자 공지사항 리스트 (페이징 처리 완료)
	@Override
	public HashMap<String, Object> selectAllNoticeList(int currentPage) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		//1개 페이지에 보여줄 글 수
		int recourdCountPerPage = 10;
		ArrayList<Notice> list= adminDAO.selectAllNoticeList(conn,recourdCountPerPage,currentPage);
		
		//네비게이션 바
		int naviCountPerPage = 5;
		String pageNavi = adminDAO.getPageNivi(conn,naviCountPerPage,recourdCountPerPage,currentPage);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi",pageNavi);
		
		return hm;
	}
	//관리자 공지사항 리스트 (페이징 처리 완료) 끝
	
	//관리자 공지사항 검색(페이징 처리 완료)
	@Override
	public HashMap<String, Object> noticeAdminSearch(int currentPage, String keyword, String type) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		//1개 페이지에 보여줄 글 수
		int recourdCountPerPage =10;
		ArrayList<Notice> list= adminDAO.noticeAdminSearch(conn,currentPage,recourdCountPerPage,keyword,type);
		
		//네비게이션바에 표시할 수
		int naviCountPerPage = 5;
		String pageNavi = adminDAO.searchPageNavi(conn, naviCountPerPage, recourdCountPerPage, currentPage, keyword, type);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		return map;
	}
	//관리자 공지사항 검색(페이징 처리 완료) 끝

	
	//관리자 페이지 뷰
	@Override
	public Notice noticeAdminView(int board_no) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		Notice n = adminDAO.noticeAdminView(conn,board_no);
		
		if(n != null) {
			adminDAO.viewCount(conn, board_no);
		}
		JDBCTemplate.close(conn);
		return n;
	}
	//관리자 페이지 뷰 끝

	//글쓰기
	@Override
	public int insertNotice(Notice notice) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		int result = adminDAO.insertNotice(conn,notice);
		
		if(result > 0 )JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public int searchBoardNo(Notice notice) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int board_no = adminDAO.searchBoardNo(conn,notice);
		JDBCTemplate.close(conn);
		return board_no;
	}
	//글쓰기 끝




	//글 삭제
	@Override
	public int deleteView(int board_no, String user_id) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		int result = adminDAO.deleteView(conn,user_id,board_no);
		
		if(result > 0 )JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}
	//글 삭제 끝


	

	//글 수정
	@Override
	public int updateView(Notice notice, String title, String content, int board_no, String user_id) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result= adminDAO.updateView(conn,notice,title,content,board_no,user_id);

		if(result > 0 )JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//글 수정 끝
}
