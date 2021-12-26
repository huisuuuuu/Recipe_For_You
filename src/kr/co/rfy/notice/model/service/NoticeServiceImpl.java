package kr.co.rfy.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.notice.model.dao.NoticeDAO;
import kr.co.rfy.notice.model.vo.Notice;

public class NoticeServiceImpl implements NoticeService{
	private NoticeDAO ndao = new NoticeDAO();
	//공지사항 게시판 (페이지 처리한 거)
	@Override
	public HashMap<String, Object> selectAllNoticeList(int currentPage) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		//1개 페이지에 보여줄 글 수
		int recourdCountPerPage = 10;
		ArrayList<Notice> list = ndao.selectAllNoticeList(conn,currentPage,recourdCountPerPage);
		
		//네비게이션바에 표시할 수
		int naviCountPerPage = 5;
		String pageNavi = ndao.getPageNivi(conn,naviCountPerPage,recourdCountPerPage,currentPage);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi",pageNavi);
		
		return hm;

	}
	//공지사항 게시판 (페이지 처리한 거)끝
	
	
	//공지사항 검색기능 게시판(페이지 처리한 거)
	@Override
	public HashMap<String, Object> noticeSearch(int currentPage, String keyword, String type) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		//1개 페이지에 보여줄 글 수
		int recourdCountPerPage =10;
		ArrayList<Notice> list = ndao.noticeSearch(conn,currentPage,recourdCountPerPage,keyword,type);
		
		//네비게이션바에 표시할 수
		int naviCountPerPage = 5;
		String pageNavi = ndao.searchPageNavi(conn, naviCountPerPage, recourdCountPerPage, currentPage, keyword, type);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		return map;
	}
	//공지사항 검색기능 게시판(페이지 처리한 거 끝)


	//뷰단
	@Override
	public Notice noticeView(int board_no) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		Notice n = ndao.noticeView(conn,board_no);
		
		//조회수 증가
		if(n != null) {
			ndao.viewCount(conn, board_no);
		}
		
		JDBCTemplate.close(conn);
		
		return n;
	}
	//뷰단 끝
	
}
