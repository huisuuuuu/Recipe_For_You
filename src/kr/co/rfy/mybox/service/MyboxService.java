package kr.co.rfy.mybox.service;

import java.util.List;

import kr.co.rfy.mybox.vo.Ingredient;
import kr.co.rfy.mybox.vo.Mybox;
import kr.co.rfy.mybox.vo.ProductBig;
import kr.co.rfy.mybox.vo.ProductMiddle;
import kr.co.rfy.mybox.vo.RecipeWithFile;

public interface MyboxService {

	// 나의 재료 등록
	public int myboxCreate( List<Mybox> myboxList, String userId ) throws Exception;

	// 나의 재료 목록
	public List<Mybox> myboxList(String userId)  throws Exception;
	
	// 나의 재료 단일 삭제
	public int deleteMyBox (String myBoxNo) throws Exception;
	
	// 대분류 목록
	public List<ProductBig> productBigList() throws Exception;
	
	// 중분류 목록
	public List<ProductMiddle> productMiddleList(String big_code) throws Exception;
	
	// 제품명 목록
	public List<Ingredient> ingredientList(String middle_code) throws Exception;
	
	// 재료가 있는 레시피 가져오기
	public List<RecipeWithFile> topMatchedRecipes(String user_id) throws Exception;
}







