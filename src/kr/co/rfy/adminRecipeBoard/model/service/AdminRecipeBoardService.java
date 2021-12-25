package kr.co.rfy.adminRecipeBoard.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard;

public interface AdminRecipeBoardService {

	/**
	 * 작성자: 조희수 
	 * @param currentPage
	 * @return HashMap<String,Object> 
	 * 작성일: 2021. 12. 22.
	 *
	 * Description : 요청한 페이지(currentPage)에 대한 목록을 리턴해주는 메소드
	 */
	HashMap<String,Object> selectAllPostList(int currentPage);

	
	/**
	 * 작성자: 조희수 
	 * @param boardNo
	 * @return HashMap<String,Object> 
	 * 작성일: 2021. 12. 23.
	 *
	 * Description : 요청한 boardNo을 가진 게시글의 모든 정보를 리턴해주는 메소드
	 */
	HashMap<String, Object> selectOnePost(int boardNo);


	/**
	 * 작성자: 조희수 
	 * @param currentPage
	 * @param keyword
	 * @param type
	 * @return HashMap<String,Object> 
	 * 작성일: 2021. 12. 23.
	 *
	 * Description : 사용자가 입력한 검색 keyword와 type을 가지고 검색 결과에 해당하는 게시글 목록을 리턴해주는 메소드
	 */
	HashMap<String,Object> selectSearchPost(int currentPage, String keyword, String type);


	int updateRecipePostLike(int boardNo, int likeNum);


	int deletePost(int boardNo, String writer);


	int deleteAdminPost(String[] recipeBoardNoValues);


	int recipeBoardMemberBlack(String[] recipeBoardNoValues);
	
}
