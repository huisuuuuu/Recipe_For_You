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
		int recordCountPerPage = 10;
		ArrayList<AdminRecipeBoard> list = rbDAO.selectAllPostPageList(conn, currentPage, recordCountPerPage);

		// 하나의 PageNavi Bar에 보여질 Navi 개수를 설정
		int naviCountPerPage = 5;
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
		int recordCountPerPage = 10;

		ArrayList<AdminRecipeBoard> list = rbDAO.selectSearchPostList(conn, currentPage, recordCountPerPage, keyword,
				type);

		// 하나의 PageNavi Bar에 보여질 Navi 개수를 설정
		int naviCountPerPage = 5;
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
	public boolean deletePost(int boardNo, String writer) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<RecipeContent> contentList = rbDAO.selectOneRecipePostContent(conn, boardNo);
		ArrayList<RecipeIngredient> ingredientList = rbDAO.selectOneRecipePostIngredient(conn, boardNo);
		ArrayList<RecipeImage> imageList = rbDAO.selectOneRecipePostImage(conn, boardNo);
		
		int postDeleteResult = rbDAO.deletePost(conn, boardNo, writer);
		int imageDeleteResult = rbDAO.deletePostImage(conn, boardNo);
		int ingredientDeleteresult = rbDAO.deletePostIngredient(conn, boardNo);
		int contentDeleteresult = rbDAO.deletePostContent(conn, boardNo);
		
		if((postDeleteResult>0) && (contentDeleteresult==contentList.size()) && (ingredientDeleteresult==ingredientList.size()) && (imageDeleteResult==imageList.size()))
			{
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
			return true;
			}
		else
		{
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return false;
		}
		
	}

	@Override
	public boolean deleteAdminBoardList(String[] recipeBoardNoValues) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<RecipeContent> contentList = rbDAO.selectRecipePostContentList(conn, recipeBoardNoValues);
		ArrayList<RecipeIngredient> ingredientList = rbDAO.selectRecipePostIngredientList(conn, recipeBoardNoValues);
		ArrayList<RecipeImage> imageList = rbDAO.selectRecipePostImageList(conn, recipeBoardNoValues);
		
		int result = rbDAO.deleteAdminBoardList(conn, recipeBoardNoValues);
		int imageDeleteResult = rbDAO.deleteRecipeImageList(conn, recipeBoardNoValues);
		int ingredientDeleteResult = rbDAO.deleteRecipeIngredientList(conn, recipeBoardNoValues);
		int contentDeleteResult = rbDAO.deleteRecipeContentList(conn, recipeBoardNoValues);
		
		if((result==recipeBoardNoValues.length) && (contentDeleteResult==contentList.size()) && (ingredientDeleteResult==ingredientList.size()) && (imageDeleteResult==imageList.size()))
			{
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
			return true;
			}
		else
		{
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return false;
		}

	}

	@Override
	public int recipeBoardMemberBlack(String[] recipeBoardNoValues) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> recipePostWriter = rbDAO.selectRecipePostWriter(conn, recipeBoardNoValues);
		
		int result = rbDAO.recipeBoardMemberBlack(conn, recipePostWriter);	
		if(result==recipeBoardNoValues.length) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
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

	@Override
	public HashMap<String, Object> selectPostCategory(int currentPage, String recipeCode) {
		
		Connection conn = JDBCTemplate.getConnection();

		// 하나의 Page에서 몇 개의 목록으로 보여줄 것인지에 대한 값 필요
		int recordCountPerPage = 5;

		ArrayList<AdminRecipeBoard> list = rbDAO.selectPostCategoryList(conn, currentPage, recordCountPerPage, recipeCode);

		// 하나의 PageNavi Bar에 보여질 Navi 개수를 설정
		int naviCountPerPage = 5;
		String pageNavi = rbDAO.getCategoryPageNavi(conn, naviCountPerPage, recordCountPerPage, currentPage, recipeCode);
		
		HashMap<String, Object> map =new HashMap<String,Object>();
		
		map.put("list", list);
		map.put("pageNavi", pageNavi);
		
		return map;
	}

	@Override
	public boolean updateAdminRecipePost(AdminRecipeBoard arb, String[] ingredientNameValues, String[] ingredientNumValues,
			String[] recipeContentValues) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int recipePostUpdateResult = rbDAO.updateAdminRecipeBoard(conn, arb);
		int recipeIngredientResult = 0;
		int recipeContentResult = 0;
		
		for(int i=0; i<ingredientNameValues.length; i++) {
			
			recipeIngredientResult += rbDAO.updateRecipePostIngredient(conn, arb.getBoardNo(), ingredientNameValues[i], ingredientNumValues[i]);
			
		};
		
		ArrayList<RecipeContent> contentList = rbDAO.selectOneRecipePostContent(conn, arb.getBoardNo());
		
		for(int i=0; i<recipeContentValues.length; i++) {
			
			recipeContentResult += rbDAO.updateRecipePostContent(conn, arb.getBoardNo(), contentList.get(i).getContentNo(), recipeContentValues[i]);
			
		}
		
		if((recipePostUpdateResult>0)&&(recipeIngredientResult==ingredientNameValues.length)&&(recipeContentResult==recipeContentValues.length))
		{
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
			return true;
		}else
		{
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return false;
		}
		
	}

}
