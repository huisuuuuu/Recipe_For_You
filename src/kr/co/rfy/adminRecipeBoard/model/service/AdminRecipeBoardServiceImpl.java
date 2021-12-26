package kr.co.rfy.adminRecipeBoard.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.adminRecipeBoard.model.dao.AdminRecipeBoardDAO;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductBig;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductIngredient;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductMiddle;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard;
import kr.co.rfy.adminRecipeBoard.model.vo.RecipeContent;
import kr.co.rfy.adminRecipeBoard.model.vo.RecipeImage;
import kr.co.rfy.adminRecipeBoard.model.vo.RecipeIngredient;
import kr.co.rfy.common.JDBCTemplate;

public class AdminRecipeBoardServiceImpl implements AdminRecipeBoardService {

	private AdminRecipeBoardDAO rbDAO = new AdminRecipeBoardDAO();

	@Override
	public HashMap<String, Object> selectAllPostList(int currentPage) {

		Connection conn = JDBCTemplate.getConnection();

		// 하나의 Page에서 몇 개의 목록으로 보여줄 것인지에 대한 값 필요
		int recordCountPerPage = 5;
		ArrayList<AdminRecipeBoard> list = rbDAO.selectAllPostPageList(conn, currentPage, recordCountPerPage);

		// 하나의 PageNavi Bar에 보여질 Navi 개수를 설정
		int naviCountPerPage = 10;
		String pageNavi = rbDAO.getPageNavi(conn, naviCountPerPage, recordCountPerPage, currentPage);

		// 2가지 방법
		// 1. 별도의 VO를 따로 만들어서 작업하는 방법(객체를 만들어서)
		// 2. HashMap을 이용하는 방법

		HashMap<String, Object> hm = new HashMap<String, Object>();

		hm.put("list", list);
		hm.put("pageNavi", pageNavi);

		JDBCTemplate.close(conn);
		return hm;
	}

	@Override
	public HashMap<String, Object> selectOnePost(int boardNo) {

		Connection conn = JDBCTemplate.getConnection();
		AdminRecipeBoard recipeBoard = rbDAO.selectOneRecipePost(conn, boardNo);
		ArrayList<RecipeContent> contentList = rbDAO.selectOneRecipePostContent(conn, boardNo);
		ArrayList<RecipeIngredient> ingredientList = rbDAO.selectOneRecipePostIngredient(conn, boardNo);
		ArrayList<RecipeImage> imageList = rbDAO.selectOneRecipePostImage(conn, boardNo);

		HashMap<String, Object> hm = new HashMap<String, Object>();

		hm.put("recipeBoard", recipeBoard);
		hm.put("contentList", contentList);
		hm.put("ingredientList", ingredientList);
		hm.put("imageList", imageList);

		JDBCTemplate.close(conn);
		return hm;

	}

	@Override
	public HashMap<String,Object> selectSearchPost(int currentPage, String keyword, String type) {

		Connection conn = JDBCTemplate.getConnection();

		// 하나의 Page에서 몇 개의 목록으로 보여줄 것인지에 대한 값 필요
		int recordCountPerPage = 5;

		ArrayList<AdminRecipeBoard> list = rbDAO.selectSerachPostList(conn, currentPage, recordCountPerPage, keyword,
				type);

		// 하나의 PageNavi Bar에 보여질 Navi 개수를 설정
		int naviCountPerPage = 10;
		String pageNavi = rbDAO.getSearchPageNavi(conn, naviCountPerPage, recordCountPerPage, currentPage, keyword,
				type);
		
		HashMap<String, Object> map =new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public int updateRecipePostLike(int boardNo, int likeNum) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = rbDAO.updateRecipePostLike(conn, boardNo, likeNum);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int deletePost(int boardNo, String writer) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = rbDAO.deletePost(conn, boardNo, writer);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int deleteAdminPost(String[] recipeBoardNoValues) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = rbDAO.deleteAdminPost(conn, recipeBoardNoValues);
		if(result==recipeBoardNoValues.length) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	@Override
	public int recipeBoardMemberBlack(String[] recipePostWriterIdValues) {
		
		Connection conn = JDBCTemplate.getConnection();
		/*int result = */rbDAO.recipeBoardMemberBlack(conn, recipePostWriterIdValues);
		/*if(result==recipeWriterIdValues.length) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);*/
		return 0;
	}

	@Override
	public ArrayList<AdminProductBig> selectProductBig() {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AdminProductBig> list = rbDAO.selectProductBig(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	@Override
	public ArrayList<AdminProductMiddle> selectProductMiddle(String bigCode) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AdminProductMiddle> list = rbDAO.selectProductMiddle(conn, bigCode);
		JDBCTemplate.close(conn);
		return list;
	}

	@Override
	public ArrayList<AdminProductIngredient> selectProductIngredient(String middleCode) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<AdminProductIngredient> list = rbDAO.selectProductIngredient(conn, middleCode);
		JDBCTemplate.close(conn);
		return list;
	}

	@Override
	public int insertAdminRecipePost(AdminRecipeBoard arb, String[] uploadImageNameValues,
			String [] uploadImagePathValues, String[] ingredientNameValues, String[] ingredientNum,
			String [] recipeContent) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = rbDAO.insertAdminRecipeBoard(conn, arb);
		int totalResult = result;
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
			
			int boardNo = rbDAO.selectOneRecipePost(conn, arb.getTitle(), arb.getUserId());
			System.out.println("boardNo : "+boardNo);
			
			int ingredientResult = rbDAO.insertRecipePostIngredient(conn, boardNo, ingredientNameValues, ingredientNum);
				if(ingredientResult==ingredientNameValues.length) JDBCTemplate.commit(conn);
				else JDBCTemplate.rollback(conn);
			int contentResult = rbDAO.insertRecipePostContent(conn, boardNo, recipeContent);
				if(contentResult==recipeContent.length) JDBCTemplate.commit(conn);
				else JDBCTemplate.rollback(conn);
			int imageResult =rbDAO.insertRecipePostImage(conn, boardNo, uploadImageNameValues, uploadImagePathValues);
				if(imageResult==uploadImageNameValues.length) JDBCTemplate.commit(conn);
				else JDBCTemplate.rollback(conn);
			
				totalResult += ingredientNameValues.length+recipeContent.length+uploadImageNameValues.length;
		}else
		{
			JDBCTemplate.rollback(conn);
		}
		
		return totalResult;
		
	}

}
