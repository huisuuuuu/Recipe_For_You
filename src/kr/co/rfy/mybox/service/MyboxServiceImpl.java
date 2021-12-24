package kr.co.rfy.mybox.service;

import java.sql.Connection;
import java.util.List;

import kr.co.rfy.commons.JDBCTemplate;
import kr.co.rfy.mybox.dao.MyboxDAO;
import kr.co.rfy.mybox.vo.Ingredient;
import kr.co.rfy.mybox.vo.Mybox;
import kr.co.rfy.mybox.vo.ProductBig;
import kr.co.rfy.mybox.vo.ProductMiddle;

public class MyboxServiceImpl implements MyboxService {

	MyboxDAO myboxDAO = new MyboxDAO();
	
	@Override
	public int myboxCreate(List<Mybox> myboxList, String userId) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 모두 삭제 후, 전부 다시 추가
		
		// 1. 기존 재료 전부 삭제
		myboxDAO.myboxDeleteAll(conn, userId);
		
		// 2. 나의 재료 추가
		int result = myboxDAO.myboxCreate(conn, myboxList);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public List<Mybox> myboxList(String userId) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		List<Mybox> myboxList = myboxDAO.myboxList(conn, userId);
		JDBCTemplate.close(conn);
		
		return myboxList;
	}

	@Override
	public List<ProductBig> productBigList() throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		List<ProductBig> productBigList = myboxDAO.productBigList(conn);
		return productBigList;
	}

	@Override
	public List<ProductMiddle> productMiddleList(String big_code) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		List<ProductMiddle> productMiddelList = myboxDAO.productMiddleList(conn, big_code);
		return productMiddelList;
	}

	@Override
	public List<Ingredient> ingredientList(String middle_code) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		List<Ingredient> ingredientList = myboxDAO.ingredientList(conn, middle_code);
		return ingredientList;
	}

	@Override
	public int deleteMyBox(String myBoxNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		int result = myboxDAO.myboxDelete(conn, myBoxNo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	

}
