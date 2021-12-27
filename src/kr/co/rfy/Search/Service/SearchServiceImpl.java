package kr.co.rfy.Search.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.Search.dao.SearchDAO;
import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.recipe.vo.Recipe;

public class SearchServiceImpl implements SearchService {

	private SearchDAO sDAO = new SearchDAO();


	@Override
	public HashMap<String, Object> selectSearchPost(int currentPage, String keyword,String type) {
		Connection conn = JDBCTemplate.getConnection();
		
		//하나의 page에서 몇개의 목록으로 보여줄 것인지에 대한 값이 필요
		int recordCountPerPage = 12;
		
		ArrayList<Recipe> list = sDAO.selectSearchPostList(conn,currentPage,recordCountPerPage,keyword,type);
		
		int naviCountPerPage = 5;
		
		String pageNavi = sDAO.getSearchPageNavi(conn,naviCountPerPage,recordCountPerPage,currentPage,keyword,type);
		
		int count = sDAO.count(conn,keyword);
		//DB 연결 해제
		JDBCTemplate.close(conn);
		
		//리턴을 하기 위하여 HashMap 객체를 만들어서 리턴
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		map.put("count",count);
	
		
		return map;
	}



}
