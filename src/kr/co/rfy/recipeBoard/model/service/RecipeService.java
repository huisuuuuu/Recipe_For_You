package kr.co.rfy.recipeBoard.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.recipeBoard.model.vo.MiddleCode;
import kr.co.rfy.recipeBoard.model.vo.Recipe;

public interface RecipeService {

	HashMap<String, Object> selectAllPostList(int currentPage,String type);

	HashMap<String,Object> selectOnePost(int boardNo,String userId);
	HashMap<String,Object> selectOnePost(int boardNo);

	int postLike(int boardNo,int likeNum);

	int postLikeCancel(int boardNo, int likeNum);

	int getLike(int boardNo);

	boolean deletePost(int boardNo);

	ArrayList<MiddleCode> getBigCode(String bigCode);

	HashMap<String, Object> selectRecipeKindAllList(int currentPage, String recipeKind);

	HashMap<String, Object> selectMyRecipeList(int currentPage,String userId );

	ArrayList<MiddleCode> getMiddleCode(String middleCode);





}
