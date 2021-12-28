package kr.co.rfy.qnaAdmin.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.qna.model.vo.Qna;
import kr.co.rfy.qnaAdmin.model.dao.QnaAdminDAO;

public class QnaAdminServiceImpl implements QnaAdminService{
	private QnaAdminDAO adminDAO = new QnaAdminDAO();
	
	//qna전체 리스트
	@Override
	public HashMap<String, Object> selectAllQnaList(int currentPage) {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getConnection();
		
		//페이지 수
		int recourdCountPerPage = 10;
		ArrayList<Qna> list = adminDAO.selectAllQnaList(conn,currentPage,recourdCountPerPage);
		
		//네비게이션바
		int naviCountPerPage = 5;
		String pageNavi = adminDAO.getPageNavi(conn,naviCountPerPage,recourdCountPerPage,currentPage);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi",pageNavi);
		
		return hm;
	}
	//qna전체 리스트 끝
	
	
	
	//검색기능 구현
	@Override
	public HashMap<String, Object> qnaAdminSearch(int currentPage, String keyword, String type) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		//1개 페이지에 보여줄 글 수
		int recourdCountPerPage =10;
		ArrayList<Qna> list = adminDAO.qnaAdminSearch(conn,currentPage,recourdCountPerPage,keyword,type);
		
		//네비게이션바에 표시할 수
		int naviCountPerPage = 5;
		String pageNavi = adminDAO.searchPageNavi(conn, naviCountPerPage, recourdCountPerPage, currentPage, keyword, type);
				
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		return map;
	}
	//검색기능 구현 끝


	//뷰단
	@Override
	public Qna qnaAdminView(int board_no) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		Qna qna = adminDAO.qnaAdminView(conn,board_no);
		JDBCTemplate.close(conn);
		
		return qna;
	}
	//뷰단 끝


	//글 쓰기
	@Override
	public int insertQna(Qna qna) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		int result = adminDAO.insertQna(conn,qna);
		
		if(result > 0 )JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

	
	@Override
	public int searchBoardNo(Qna qna) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int board_no = adminDAO.searchBoardNo(conn,qna);
		JDBCTemplate.close(conn);
		return board_no;
	}
	//글 쓰기 끝



	//글 수정
	@Override
	public int updateView(Qna qna,int board_no,String user_id,String title,String content,String q_code) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result= adminDAO.updateView(conn,qna,board_no,user_id,title,content,q_code);
		
		if(result > 0 )JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	//글 수정


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
	//글 삭제
}
