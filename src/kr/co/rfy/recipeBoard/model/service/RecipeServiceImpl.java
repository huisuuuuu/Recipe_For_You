package kr.co.rfy.recipeBoard.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.recipeBoard.model.doa.RecipeDAO;
import kr.co.rfy.recipeBoard.model.vo.Content;
import kr.co.rfy.recipeBoard.model.vo.File;
import kr.co.rfy.recipeBoard.model.vo.Ingredient;
import kr.co.rfy.recipeBoard.model.vo.MiddleCode;
import kr.co.rfy.recipeBoard.model.vo.MyboxIngredient;
import kr.co.rfy.recipeBoard.model.vo.OurRecipe;
import kr.co.rfy.recipeBoard.model.vo.RecipeDetail;
import kr.co.rfy.recipeBoard.model.vo.UserRecipeBoard;

public class RecipeServiceImpl implements RecipeService {
	
	RecipeDAO rDAO = new RecipeDAO();

	//모든 레시피 리스트 가져오기
	@Override
	public HashMap<String, Object> selectAllPostList(int currentPage,String type) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//1.한페이지에 n개의 글을 가져올 것인지 설정
		int recordCountPerPage =12;
		ArrayList<OurRecipe> list =rDAO.selectAllPostPageList(conn,currentPage,recordCountPerPage,type);
			
		//2.해당 페이지에 필요한 네비바 생성
		// 하나의 pageNavi bar에 보여질 navi 갯수를 설정
		
		int naviCountPerPage=5;
		
		String pageNavi = rDAO.getNavi(conn,naviCountPerPage,recordCountPerPage,currentPage);
		
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		hm.put("list", list);
		hm.put("pageNavi", pageNavi);
		
		return hm;
	}

	//하나의 레시피 가져오기 (userid !=null 일 때)
	@Override
	public HashMap<String,Object> selectOnePost(int boardNo,String userId) {
	
	Connection conn = JDBCTemplate.getConnection();
	
		//총 4개의 데이터를 가져와야한다.
		//1. 레시피 게시판 테이블에 있는 모든 데이터
		//2. 해당 내용
		//3. 해당 파일 경로
		//4. 재료
		//5. 마이냉장고 재료
		RecipeDetail recipe=rDAO.selectOnePost(conn,boardNo);
		ArrayList<Content> contentList = rDAO.selectOnePostContent(conn,boardNo);
		ArrayList<File> fileList = rDAO.selectOnePostFile(conn,boardNo);
		ArrayList<Ingredient> ingredientList = rDAO.selectOnePostIngredient(conn,boardNo);
		ArrayList<MyboxIngredient> myBoxList = rDAO.selectMyBox(conn,userId);
		
		
		JDBCTemplate.close(conn);
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("recipeInfo", recipe);
		hm.put("contentList", contentList);
		hm.put("fileList", fileList);
		hm.put("ingredientList", ingredientList);
		hm.put("myBoxList", myBoxList);
		
		return hm;
		
	}
	
	//하나의 레시피 가져오기 (userId==null 일 때)
	@Override
	public HashMap<String,Object> selectOnePost(int boardNo) {
	
	Connection conn = JDBCTemplate.getConnection();
	
		//총 4개의 데이터를 가져와야한다.
		//1. 레시피 게시판 테이블에 있는 모든 데이터
		//2. 해당 내용
		//3. 해당 파일 경로
		//4. 재료
		//5. 마이냉장고 재료
		RecipeDetail recipe=rDAO.selectOnePost(conn,boardNo);
		ArrayList<Content> contentList = rDAO.selectOnePostContent(conn,boardNo);
		ArrayList<File> fileList = rDAO.selectOnePostFile(conn,boardNo);
		ArrayList<Ingredient> ingredientList = rDAO.selectOnePostIngredient(conn,boardNo);
		
		
		
		JDBCTemplate.close(conn);
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("recipeInfo", recipe);
		hm.put("contentList", contentList);
		hm.put("fileList", fileList);
		hm.put("ingredientList", ingredientList);
		
		
		return hm;
		
	}
	
	
	
	
	
	
	//레시피 추천 +1 반영
	@Override
	public int postLike(int boardNo,int likeNum) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result =rDAO.postLike(conn,boardNo,likeNum);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	@Override
	public int postLikeCancel(int boardNo, int likeNum) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result =rDAO.postLikeCancel(conn,boardNo,likeNum);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	//최신 likeNum 값 가져오기
	@Override
	public int getLike(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int likeNum=rDAO.getLike(conn,boardNo);
		
		JDBCTemplate.close(conn);
		
		return likeNum;
	}

	@Override
	
	//게시물을 삭제하려면 총 4개의 테이블에서 삭제해야하므로 DAO 호출을 4번 해야한다.
	public boolean deletePost(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
			int result1=rDAO.deleteBoardPost(conn,boardNo);
			int	result2	=rDAO.deleteBoardFile(conn,boardNo);
			int	result3	=rDAO.deleteBoardContent(conn,boardNo);
			int result4 =rDAO.deleteBoardIngredient(conn,boardNo);
		
			boolean result=false;
			if((result1+result2+result3+result4)==4)
			{
				result=true; 
				JDBCTemplate.commit(conn);
			}
			else
			{
				result=false;
				JDBCTemplate.rollback(conn);
			}
		
			JDBCTemplate.close(conn);
			return result;
		
	}


	
	//카테고리별 레시피 목록 가져오기
	@Override
	public HashMap<String, Object> selectRecipeKindAllList(int currentPage, String recipeKind) {
	
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage=5;
		
		//1.해당 페이지에 필요한 목록을 가져온다
		//해당페이지에 글목록을 가져오기 위해서는 Connection과 현재페이지 값과 현재페이지에 몇개의 글목록을 가져올지에 대한 변수를 보낸다.
		ArrayList<OurRecipe> list=rDAO.selectRecipeKindAllList(conn,currentPage,recordCountPerPage,recipeKind);
		
		//2.해당 페이지에 필요한 네비바 생성
		// 하나의 pageNavi bar에 보여질 navi 갯수를 설정
				
		int naviCountPerPage=5;
				
		String pageNavi = rDAO.getKindNavi(conn,naviCountPerPage,recordCountPerPage,currentPage,recipeKind);
				
		JDBCTemplate.close(conn);
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		hm.put("list", list);
		hm.put("pageNavi", pageNavi);
		
		return hm;
		
		
		
		
	}

	//나의 레시피 목록 가져온다. 
		@Override
		public HashMap<String, Object> selectMyRecipeList(int currentPage,String userId) {
			
			Connection conn = JDBCTemplate.getConnection();
			
			//1.한페이지에 n개의 글을 가져올 것인지 설정
			int recordCountPerPage =12;
			ArrayList<OurRecipe> list =rDAO.selectMyRecipeList(conn,currentPage,recordCountPerPage,userId);
			
			
			//2.해당 페이지에 필요한 네비바 생성
			// 하나의 pageNavi bar에 보여질 navi 갯수를 설정
					
			int naviCountPerPage=12;
					
			String pageNavi = rDAO.getMyRecipeNavi(conn,naviCountPerPage,recordCountPerPage,currentPage);
			
			JDBCTemplate.close(conn);
			
			HashMap<String, Object> hm = new HashMap<String, Object>();
			
			hm.put("list", list);
			hm.put("pageNavi", pageNavi);
			
			return hm;
			
			
			
			
			
			
		}
	
	//대분류코드를 이용하여 중분류 코드를 가져온다.
	@Override
	public ArrayList<MiddleCode> getBigCode(String bigCode) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MiddleCode> mList=rDAO.getBigCode(conn,bigCode);
		
		JDBCTemplate.close(conn);
		
		return mList;
		
		
		
		
	}

	//중분류 코드를 이용하여 재료명들을 가져온다.
	@Override
	public ArrayList<MiddleCode> getMiddleCode(String middleCode) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MiddleCode> mList=rDAO.getMiddleCode(conn,middleCode);
		
		JDBCTemplate.close(conn);
		
		return mList;
	}

	@Override
	public int insertUserRecipePost(UserRecipeBoard arb, String[] uploadImageNameValues,
			String[] uploadImagePathValues, String[] ingredientNameValues, String[] ingredientNum,
			String[] recipeContent) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = rDAO.insertUserRecipeBoard(conn, arb);
		int totalResult = result;
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
			
			int boardNo = rDAO.selectOneRecipePost(conn, arb.getTitle(), arb.getUserId());
			
			int ingredientResult = rDAO.insertRecipePostIngredient(conn, boardNo, ingredientNameValues, ingredientNum);
				if(ingredientResult==ingredientNameValues.length) JDBCTemplate.commit(conn);
				else JDBCTemplate.rollback(conn);
			int contentResult = rDAO.insertRecipePostContent(conn, boardNo, recipeContent);
				if(contentResult==recipeContent.length) JDBCTemplate.commit(conn);
				else JDBCTemplate.rollback(conn);
			int imageResult =rDAO.insertRecipePostImage(conn, boardNo, uploadImageNameValues, uploadImagePathValues);
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
