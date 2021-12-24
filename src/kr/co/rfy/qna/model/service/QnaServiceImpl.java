package kr.co.rfy.qna.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.commons.JDBCTemplate;
import kr.co.rfy.qna.model.dao.QnaDAO;
import kr.co.rfy.qna.model.vo.Qna;


public class QnaServiceImpl implements QnaService{
	private QnaDAO dao = new QnaDAO();
	
	//페이지 처리된 qna리스트단
	@Override
	public HashMap<String, Object> selectAllQnaList(int currentPage) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		int recourdCountPerPage = 10;
		ArrayList<Qna> list = dao.selectAllQnaList(conn,currentPage,recourdCountPerPage);
		
		//네비게이션바에 표시할 수
		int naviCountPerPage = 5;
		String pageNavi = dao.getPageNavi(conn,naviCountPerPage,recourdCountPerPage,currentPage);
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi",pageNavi);
		
		return hm;
	}
	//페이지 처리된 qna리스트단 끝
	
	
	//검색기능에 페이지 처리된 qna리스트단
	@Override
	public HashMap<String, Object> qnaSearch(int currentPage, String keyword, String type) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		//1개 페이지에 보여줄 글 수
		int recourdCountPerPage =10;
		ArrayList<Qna> list = dao.qnaSearch(conn,currentPage,recourdCountPerPage,keyword,type);
		
		//네비게이션바에 표시할 수
		int naviCountPerPage = 5;
		String pageNavi = dao.searchPageNavi(conn, naviCountPerPage, recourdCountPerPage, currentPage, keyword, type);
		
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		return map;

	}
	//검색기능에 페이지 처리된 qna리스트단 끝


	@Override
	public Qna qnaView(int board_no) {
		// TODO Auto-generated method stub
		Connection conn=JDBCTemplate.getConnection();
		
		Qna qna = dao.qnaView(conn,board_no);
		JDBCTemplate.close(conn);
		
		return qna;
	}
	
	
	

}
