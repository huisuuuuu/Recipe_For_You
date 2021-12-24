package kr.co.rfy.admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.admin.model.dao.AdminMemberDAO;
import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.member.model.vo.Member;

public class AdminMemberServiceImpl implements AdminMemberService {
	private AdminMemberDAO adDAO = new AdminMemberDAO();
	
	@Override
	
public HashMap<String, Object> selectAllMemberList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 10;
		ArrayList<Member> list = adDAO.selectAllMemberList(conn,currentPage,recordCountPerPage);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getPageNavi(conn,naviCountPerPage,recordCountPerPage,currentPage);
		
		JDBCTemplate.close(conn);
		
HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi", pageNavi);
		
		return hm;
		
	}

	@Override
	public HashMap<String, Object> SearchMember(int currentPage, String keyword, String type) {
	Connection conn = JDBCTemplate.getConnection();
		
		//하나의 page에서 몇개의 목록으로 보여줄 것인지에 대한 값이 필요
		int recordCountPerPage = 10;
		
		ArrayList<Member> list = adDAO.SearchMember(conn,currentPage,recordCountPerPage,keyword,type);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getSearchPageNavi(conn,naviCountPerPage,recordCountPerPage,currentPage,keyword,type);
		
		
		//DB 연결 해제
		JDBCTemplate.close(conn);
		
		//리턴을 하기 위하여 HashMap 객체를 만들어서 리턴
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public HashMap<String, Object> BlackList(int currentPage) {
Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 10;
		ArrayList<Member> list = adDAO.BlackList(conn,currentPage,recordCountPerPage);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getPageNaviBlack(conn,naviCountPerPage,recordCountPerPage,currentPage);
		
		JDBCTemplate.close(conn);
		
HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi", pageNavi);
		
		return hm;
	}

	@Override
	public HashMap<String, Object> SearchMemberBlack(int currentPage, String keyword, String type) {
Connection conn = JDBCTemplate.getConnection();
		
		
		int recordCountPerPage = 10;
		
		ArrayList<Member> list = adDAO.SearchMemberBlack(conn,currentPage,recordCountPerPage,keyword,type);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getSearchPageNaviBlack(conn,naviCountPerPage,recordCountPerPage,currentPage,keyword,type);
		
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public Member selectOne(int userNo) {
Connection conn = JDBCTemplate.getConnection();
		
		//1개의 게시물을 가져오기 위한 DAO
		Member m = adDAO.selectOne(conn, userNo);
		
	
		
		JDBCTemplate.close(conn);
		return m;
	}

	@Override
	public int updateMemberBlackYN(int userNo, char blackYN) {
		Connection conn = JDBCTemplate.getConnection();
		int result=adDAO.updateMemberBlackYN(userNo,blackYN,conn);
		if(result>0)JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public HashMap<String, Object> selectAllMemberListUp(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 10;
		ArrayList<Member> list = adDAO.selectAllMemberListUp(conn,currentPage,recordCountPerPage);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getPageNaviUp(conn,naviCountPerPage,recordCountPerPage,currentPage);
		
		JDBCTemplate.close(conn);
		
HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi", pageNavi);
		
		return hm;
	}

	@Override
	public HashMap<String, Object> SearchMemberUp(int currentPage, String keyword, String type) {
		Connection conn = JDBCTemplate.getConnection();
		
		
		int recordCountPerPage = 10;
		
		ArrayList<Member> list = adDAO.SearchMemberUp(conn,currentPage,recordCountPerPage,keyword,type);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getSearchPageNaviUp(conn,naviCountPerPage,recordCountPerPage,currentPage,keyword,type);
		
		
		//DB 연결 해제
		JDBCTemplate.close(conn);
		
		//리턴을 하기 위하여 HashMap 객체를 만들어서 리턴
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public Member selectOneUp(int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		//1개의 게시물을 가져오기 위한 DAO
		Member m = adDAO.selectOneUp(conn, userNo);
		
	
		
		JDBCTemplate.close(conn);
		return m;
	}

	@Override
	public int updateMemberBlackYNUp(int userNo, char blackYN) {
		Connection conn = JDBCTemplate.getConnection();
		int result=adDAO.updateMemberBlackYNUp(userNo,blackYN,conn);
		if(result>0)JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public HashMap<String, Object> BlackListUp(int currentPage) {
	Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 10;
		ArrayList<Member> list = adDAO.BlackListUp(conn,currentPage,recordCountPerPage);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getPageNaviBlackUp(conn,naviCountPerPage,recordCountPerPage,currentPage);
		
		JDBCTemplate.close(conn);
		
HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("list",list);
		hm.put("pageNavi", pageNavi);
		
		return hm;
	}

	@Override
	public HashMap<String, Object> SearchMemberBlackUp(int currentPage, String keyword, String type) {
Connection conn = JDBCTemplate.getConnection();
		
		
		int recordCountPerPage = 10;
		
		ArrayList<Member> list = adDAO.SearchMemberBlackUp(conn,currentPage,recordCountPerPage,keyword,type);
		
		int naviCountPerPage = 5;
		
		String pageNavi = adDAO.getSearchPageNaviBlackUp(conn,naviCountPerPage,recordCountPerPage,currentPage,keyword,type);
		
		
		//DB 연결 해제
		JDBCTemplate.close(conn);
		
		//리턴을 하기 위하여 HashMap 객체를 만들어서 리턴
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	

	

	}
