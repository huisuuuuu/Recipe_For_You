package kr.co.rfy.recipeBoard.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard;
import kr.co.rfy.recipeBoard.model.vo.MiddleCode;
import kr.co.rfy.recipeBoard.model.vo.Recipe;
import kr.co.rfy.recipeBoard.model.vo.UserRecipeBoard;

public interface RecipeService {

	HashMap<String, Object> selectAllPostList(int currentPage,String type);

	HashMap<String,Object> selectOnePost(int boardNo,String userId);
	HashMap<String,Object> selectOnePost(int boardNo);

	int postLike(int boardNo,int likeNum);

	int postLikeCancel(int boardNo, int likeNum);

	int getLike(int boardNo);

	boolean deletePost(int boardNo);

	ArrayList<MiddleCode> getBigCode(String bigCode);

	HashMap<String, Object> selectRecipeKindAllList(int currentPage, String recipeKind,String type);

	HashMap<String, Object> selectMyRecipeList(int currentPage,String userId );

	ArrayList<MiddleCode> getMiddleCode(String middleCode);

	int insertUserRecipePost(UserRecipeBoard arb, String[] uploadImageNameValues, String[] uploadImagePathValues,
			String[] ingredientNameValues, String[] ingredientNum, String[] recipeContent);

	
	
	boolean updateUserRecipePost(AdminRecipeBoard arb, String[] ingredientNameValues, String[] ingredientNumValues,
			String[] recipeContentValues);





}
